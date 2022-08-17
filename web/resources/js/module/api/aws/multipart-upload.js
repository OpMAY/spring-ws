const SIZE_5MB = 1024 * 1024 * 5; // 5MB
const SIZE_10MB = 1024 * 1024 * 10; // 10MB
const CHUNK_SIZE = SIZE_5MB;
const BUCKET_NAME = 'gugong';
const UPLOAD_MODE = Object.freeze({
    LOCAL: 'LOCAL',
    AWS: 'AWS'
})
const MULTIPART_UPLOAD_MODE = UPLOAD_MODE.AWS;

let s3; // SDK Object
let detect_leave = false;

class FileUploadInfo {
    constructor(file) {
        this.file = file;
        this.byte_flag = 0;
        this.part_index = 1;
        this.uuid = getUUID().substr(-6) + '_';
        this.canceled = false;
        this.upload_id = null;
    }
}

let fnOnLeave = () => {
}
window.onbeforeunload = () => {
    if (detect_leave) return true;
};
window.onunload = () => {
    fnOnLeave();
};// 나가면 실행되는
/* 사용 예시
*<script src="https://sdk.amazonaws.com/js/aws-sdk-2.1101.0.min.js"></script>
*$(document).ready(()=>{
*    initAWSS3SDK();
*});
**/

function initAWSS3SDK() {
    const AWS_REGION = 'ap-northeast-2';
    const IDENTITY_POOL_ID = 'ap-northeast-2:6ec05f63-7d98-4614-964b-dd77eb385337'; // aws cognito 자격증명 풀 ID
    AWS.config.region = AWS_REGION;
    AWS.config.credentials = new AWS.CognitoIdentityCredentials({
        IdentityPoolId: IDENTITY_POOL_ID,
    });
    s3 = new AWS.S3({
        Bucket: BUCKET_NAME
    });
}

function cancelMultipartUpload(callback) {
    if (confirm('전송중인 파일이 삭제됩니다. 중단하시겠습니까?')) {
        console.log('전송을 중단합니다.');
        multipart_upload_canceled = true;
        callback();
    }
}

function handleMultipartUploadFail() {
    console.log(part_index + '번째 파트 전송중 실패');
    unobservePageLeave();
}

function handleMultipartUploadCancel() {
    console.log(part_index + '번째 파트 전송중 중단');
    unobservePageLeave();
}

function handleMultipartUploadComplete() {
    console.log('전송완료');
    unobservePageLeave();
    viewAlert({content: '업로드 완료'});
}

function handlePartUploadSuccess(progress_percent) {
    console.log(progress_percent + '% 진행');
    viewAlert({content: progress_percent + '% 진행'});
}

function observePageLeave(callback) {
    detect_leave = true;
    fnOnLeave = callback;
}

function unobservePageLeave() {
    detect_leave = false;
    fnOnLeave = () => {
    };
}

function awsMultipartUpload(dir_path, file, front_complete_callback, progress_callback) {
    let fileUploadInfo = new FileUploadInfo(file);

    observePageLeave(abortMultipartUpload);

    const eTagParts = []; // etags for complete
    const Multipart_Object_Key = dir_path + fileUploadInfo.uuid + fileUploadInfo.file.name;
    const params = {
        Bucket: BUCKET_NAME,
        Key: Multipart_Object_Key
    };
    s3.createMultipartUpload(params, partUpload);

    function partUpload(err, data) {
        if (err) { // an error occurred
            console.log(err, err.stack);
            abortMultipartUpload();
            handleMultipartUploadFail();
            return;
        }

        if (data.UploadId) { // init 됐을때 받은 upload id 저장
            fileUploadInfo.upload_id = data.UploadId;
        } else { // 파일 전송시
            const progress_percent = Math.floor(((fileUploadInfo.byte_flag - CHUNK_SIZE) / fileUploadInfo.file.size) * 100);
            handlePartUploadSuccess(progress_percent);
            progress_callback(progress_percent, fileUploadInfo.byte_flag);
        }
        if (data.ETag) { // 성공한 request 의 ETag 담기
            eTagParts.push({
                ETag: data.ETag,
                PartNumber: fileUploadInfo.part_index - 1
            });
        }
        if (fileUploadInfo.byte_flag >= fileUploadInfo.file.size) { // 전송 완료
            completeMultipartUpload(err, data);
            return;
        }
        if (fileUploadInfo.canceled) { // 업로드 취소
            abortMultipartUpload();
            handleMultipartUploadCancel();
            return;
        }

        const blob = file.slice(fileUploadInfo.byte_flag, Math.min(fileUploadInfo.file.size, fileUploadInfo.byte_flag + CHUNK_SIZE));
        uploadBlob(blob); // recursive for awsMulitpartUpload
        /* ex)
        data = {
         Bucket: "examplebucket",
         Key: "largeobject",
         UploadId: "ibZBv_75gd9r8lH_gqXatLdxMVpAlj6ZQjEs.OwyF3953YdwbcQnMA2BLGn8Lx12fQNICtMw5KyteFeHw.Sjng--"
        }
        */
        function uploadBlob(blob) {
            const reader = new FileReader();
            reader.readAsDataURL(blob);
            reader.onloadend = () => {
                const uploadPart_params = {
                    Body: blob,
                    Bucket: BUCKET_NAME,
                    Key: Multipart_Object_Key,
                    PartNumber: fileUploadInfo.part_index,
                    UploadId: fileUploadInfo.upload_id
                };
                fileUploadInfo.byte_flag += CHUNK_SIZE;
                fileUploadInfo.part_index += 1;
                s3.uploadPart(uploadPart_params, partUpload); // upload next part
            }
        }
    }

    function completeMultipartUpload(err, data) {
        if (err) { // an error occurred
            console.log(err, err.stack);
            abortMultipartUpload();
        } else { // successful response
            const complete_params = {
                Bucket: BUCKET_NAME,
                Key: Multipart_Object_Key,
                MultipartUpload: {
                    Parts: eTagParts
                },
                UploadId: fileUploadInfo.upload_id
            };
            s3.completeMultipartUpload(complete_params, completeCallback);
        }

        /* ex)
        data = {
         ETag: "\"d8c2eafd90c266e19ab9dcacc479f8af\""
        }
        */

        function completeCallback(err, data) {
            if (err) console.log(err, err.stack); // an error occurred
            else { // successful response
                console.log(data);
                handleMultipartUploadComplete();
                front_complete_callback(data.Location);
            }
            /* ex)
            data = {
             Bucket: "acexamplebucket",
             ETag: "\"4d9031c7644d8081c2829f4ea23c55f7-2\"",
             Key: "bigobject",
             Location: "https://examplebucket.s3.<Region>.amazonaws.com/bigobject"
            }
            */
        }
    }

    function abortMultipartUpload() {
        const abort_params = {
            Bucket: BUCKET_NAME,
            Key: Multipart_Object_Key,
            UploadId: upload_id
        };
        console.log('전송 취소');
        s3.abortMultipartUpload(abort_params, (err, data) => {
            if (err) console.log(err, err.stack); // an error occurred
            else console.log(data);           // successful response
            /*
            data = {}
            */
        });
    }
}

function localMultipartUpload(file, front_complete_callback, progress_callback) {
    let fileUploadInfo = new FileUploadInfo(file);

    const LOCAL_UPLOAD_PATH = '/files/';
    const file_name = fileUploadInfo.uuid + file.name;

    observePageLeave(() => {
        deleteLocalFile(file_name);
    });
    partUpload();

    function partUpload() {
        const blob = file.slice(fileUploadInfo.byte_flag, Math.min(file.size, fileUploadInfo.byte_flag + CHUNK_SIZE));
        const reader = new FileReader();
        reader.readAsDataURL(blob);
        reader.onloadend = async () => {
            if (fileUploadInfo.byte_flag >= file.size) {
                handleMultipartUploadComplete();
                front_complete_callback(LOCAL_UPLOAD_PATH + file_name);
                return;
            }

            const success = await sendEncodedByteData(reader.result);
            if (success && !fileUploadInfo.canceled) { // 전송 성공
                const progress_percent = Math.floor((fileUploadInfo.byte_flag / file.size) * 100);
                handlePartUploadSuccess(progress_percent);
                progress_callback(progress_percent, fileUploadInfo.byte_flag);

                fileUploadInfo.byte_flag += CHUNK_SIZE;
                fileUploadInfo.part_index += 1;
                partUpload();
            } else if (fileUploadInfo.canceled) { // 전송 취소
                handleMultipartUploadCancel();
                deleteLocalFile(file_name);
            } else { // 전송 실패
                handleMultipartUploadFail();
                deleteLocalFile(file_name);
            }
        }
    }

    function deleteLocalFile(file_name) {
        const data = {file_name};
        const options = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json;'
            },
            body: JSON.stringify(data)
        }
        fetch('/local-delete', options)
            .then(res => res.json())
            .then(res => {
            })
            .catch(e => {
                console.error(e);
            });
    }

    function sendEncodedByteData(byte_data) {
        const data = {
            data: byte_data,
            file_name: file_name,
            part_index: fileUploadInfo.part_index
        };
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;'
            },
            body: JSON.stringify(data)
        }
        return fetch('/local-upload', options)
            .then(res => res.json())
            .then(res => res.data.status)
            .catch(e => {
                console.error(e);
                return false;
            });
    }
}

function multipartUpload({
                             file,
                             front_complete_callback = () => {
                             },
                             progress_callback = () => {
                             },
                             dir_path
                         }) {
    if (MULTIPART_UPLOAD_MODE === UPLOAD_MODE.LOCAL) {
        localMultipartUpload(file, front_complete_callback, progress_callback);
    } else if (MULTIPART_UPLOAD_MODE === UPLOAD_MODE.AWS) {
        awsMultipartUpload(dir_path, file, front_complete_callback, progress_callback);
    }
}