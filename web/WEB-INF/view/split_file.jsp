<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>
<div class="container">
    <h1>Commons Multipart Upload 대용량 분할 파일 업로드</h1>
    <form id="my-form">
        <input type="file" id="file-input"/>
    </form>
    <span id="progress"></span>
    <button type="button" onclick="start_upload();">전송</button>
</div>
<!-- Optional JavaScript; choose one of the two! -->

<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
        crossorigin="anonymous"></script>
<script src="../../resources/dev-suggest/bootstrap-4.5.0-dist/js/jquery.js"></script>
<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js" integrity="sha384-VHvPCCyXqtD5DqJeNxl2dtTyhF78xXNXdkwX1CZeRusQfRKp+tA7hAShOK/B/fQ2" crossorigin="anonymous"></script>
-->
</body>
<script>
    let reader = {};
    let file = {};
    let slice_size = 1024 * 1024 * 8;
    let next_slice;
    let blob;
    let index;
    let payload;
    let size_done;
    let percent_done;

    function start_upload() {
        reader = new FileReader();
        file = document.querySelector('#file-input').files[0];
        upload_file(0);
    }

    function upload_file(start) {
        next_slice = start + slice_size + 1;
        blob = file.slice(start, next_slice);
        reader.onloadend = function (event) {
            if (event.target.readyState !== FileReader.DONE) {
                new Error('File upload does not file type or empty file input')
                return;
            }
            index = Math.floor(start / slice_size);
            payload = {
                eof: false,
                file_data: event.target.result,
                filename: file.name,
                file_type: file.type,
                index: index
            }
            console.log(payload.filename, payload.index, payload.file_type);
            $.ajax({
                url: '/upload/split/general',
                type: 'POST',
                dataType: 'json',
                cache: false,
                data: payload,
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR, textStatus, errorThrown);
                },
                success: function (data) {
                    size_done = start + slice_size;
                    percent_done = Math.floor((size_done / file.size) * 100);
                    if (next_slice < file.size) {
                        // Update upload progress
                        $('#progress').html(`Uploading File -  \${percent_done}%`);
                        // More to upload, call function recursively
                        upload_file(next_slice);
                    } else {
                        payload = {
                            eof: true,
                            filename: file.name,
                            file_type: file.type,
                        };
                        console.log(payload);
                        $.ajax({
                            url: '/upload/split/general',
                            type: 'POST',
                            dataType: 'json',
                            cache: false,
                            data: payload,
                            error: function (jqXHR, textStatus, errorThrown) {
                                console.log(jqXHR, textStatus, errorThrown);
                            },
                            success: function (data) {
                                // Update upload progress
                                $('#progress').html('Upload Complete!');
                            }
                        });
                    }
                }
            });
        };
        reader.readAsDataURL(blob);
    }

    /*function startForm() {
        console.log('startForm');
        let fileInput = document
            .getElementById('file-input')
            .files[0];
        // begin upload process

        let chunks = sliceFile(fileInput, 10);
        chunks.forEach((chunk, index) => {

        });
        console.log('startForm End');
    }*/

    /*/!**
     * @param {File|Blob} - file to slice
     * @param {Number} - chunksAmount
     * @return {Array} - an array of Blobs
     **!/
    function sliceFile(file, chunksAmount) {
        var byteIndex = 0;
        var chunks = [];
        for (var i = 0; i < chunksAmount; i += 1) {
            var byteEnd = Math.ceil((file.size / chunksAmount) * (i + 1));
            chunks.push(file.slice(byteIndex, byteEnd));
            console.log(byteIndex, byteEnd);
            byteIndex += (byteEnd - byteIndex);
        }
        console.log(chunks);
        return chunks;
    }*/

    /*let fileInput = undefined;

    var chunk_size = 1024 * 1024 * 1;
    var reader = new FileReader();
    let checkBase64 = '';

    function startForm() {
        console.log('startForm');
        fileInput = document
            .getElementById('file-input')
            .files[0];
        // begin upload process
        uploadFile(fileInput);
        console.log('startForm End');
    }

    function uploadFile(fileInput) {
        _uploadChunk(fileInput, 0, chunk_size);
    }

    function _uploadChunk(file, offset, range) {
        // if no more chunks, send EOF
        console.log(offset, range);
        if (offset >= file.size) {
            console.log('last file upload');
            $.post('/upload/split/general', {
                filename: file.name,
                eof: true
            });
            console.log('end', file.name, true);
            return;
        }

        // prepare reader with an event listener
        reader.addEventListener('load', function (e) {
            var filename = file.name;
            var index = offset / chunk_size;
            var data = e.target.result.split(';base64,')[1];
            // build payload with indexed chunk to be sent
            var payload = {
                filename: filename,
                index: index,
                data: data,
                eof: false,
            };
            console.log(data,index,filename,payload);
            // send payload, and buffer next chunk to be uploaded
            $.post('/upload/split/general',
                payload,
                function () {
                    _uploadChunk(file, offset + range, chunk_size);
                }
            );
            console.log(index, filename, false);
        }, {once: true}); // register as a once handler!

        // chunk and read file data
        var chunk = file.slice(offset, offset + range);
        reader.readAsDataURL(chunk);
    }*/
</script>
</html>