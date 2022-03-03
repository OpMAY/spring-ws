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
    <button type="button" onclick="download();">다운로드</button>
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
    let slice_size = 1024 * 1024 * 10;
    let next_slice;
    let blob;
    let index;
    let payload;
    let size_done;
    let percent_done;

    /**
     * @dates 2022.03.02
     * @author kimwoosik
     * @description Client to server file upload start function
     */
    function start_upload() {
        reader = new FileReader();
        file = document.querySelector('#file-input').files[0];
        upload_file(0);
    }

    /**
     * @dates 2022.03.02
     * @author kimwoosik
     * @description x의 n 거듭제곱을 반환하는 함수
     * @param {start} file upload의 chunk의 시작지점 (byte 기준)
     * Use upload_file(chunk start byte)
     */
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
                file_name: file.name,
                file_type: file.type,
                order_index: index
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
                        /** Next Chunk Upload (Recursive)*/
                        /** Update Percent View*/
                        $('#progress').html(`Uploading File -  \${percent_done}%`);
                        upload_file(next_slice);
                    } else {
                        /** End Data Send */
                        payload = {
                            eof: true,
                            file_name: file.name,
                            file_type: file.type,
                            order_index: ++index
                        };
                        /** Send to server end file upload message*/
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

    function download() {
        if (document.querySelector('#file-input').files.length === 0) {
            alert('파일이 없습니다.');
            return;
        }
        const file_name = document.querySelector('#file-input').files[0].name;
        window.open('/upload/split/bulk/download?file=' + file_name);
    }
</script>
</html>