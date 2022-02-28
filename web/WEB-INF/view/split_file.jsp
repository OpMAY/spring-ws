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
    let fileInput = undefined;

    var chunk_size = 1024 * 1024 * 4; // 10mb
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
        if (offset >= file.size) {
            $.post('http://localhost:8080/upload/split/general', {
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
                data: compressText(data),
                eof: false,
            };
            // send payload, and buffer next chunk to be uploaded
            $.post('http://localhost:8080/upload/split/general',
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
    }
</script>
<%--TODO Compress--%>
<script>
    function compressText(text) {
        var result = '';
        if (text.length > 0) {
            var count = 1;
            var value = text[0];
            for (var i = 1; i < text.length; ++i) {
                var entry = text[ i ];
                if (entry == value) {
                    count += 1;
                } else {
                    result += count + '' + value;
                    count = 1;
                    value = entry;
                }
            }
            result += count + '' + entry;
        }
        return result;
    }
</script>
</html>