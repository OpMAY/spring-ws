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
    <%--Video js--%>
    <link href="https://vjs.zencdn.net/7.17.0/video-js.css" rel="stylesheet"/>
    <link href="https://unpkg.com/@videojs/themes@1/dist/city/index.css" rel="stylesheet"/>
    <title>Hello, world!</title>
    <style>
        .video-container {
            position: relative;
        }

        .video-js .vjs-big-play-button {
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
        }
    </style>
</head>
<body>
<div class="container mt-5 mb-5">
    <h1>Email Token 인증</h1>
    <div class="input-group mb-3">
        <input type="text" placeholder="Email을 입력해주세요." value="zlzldntlr@naver.com" name="email"
               id="email"
               class="form-control">
        <div class="input-group-append">
            <button type="button" id="email-addon" class="btn btn-outline-secondary">이메일 인증</button>
        </div>
    </div>
</div>
<!-- Optional JavaScript; choose one of the two! -->

<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
        crossorigin="anonymous"></script>
<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js" integrity="sha384-VHvPCCyXqtD5DqJeNxl2dtTyhF78xXNXdkwX1CZeRusQfRKp+tA7hAShOK/B/fQ2" crossorigin="anonymous"></script>
-->
<script>
    $(document).ready(function () {
        $('#email-addon').click((e) => {
            console.log(e);
            let data = {
                email: $('#email').val()
            }
            const options = {
                method: "POST",
                headers: {
                    'x-requested-with': 'XMLHttpRequest',
                },
                body: new URLSearchParams(data)
            };
            fetch('/auth/email', options).then(res => res.json())
                .then(res => {
                    if (res.data.status) {
                        alert('Authorized');
                    } else {
                        alert('Un authorized')
                    }
                }).catch(e => {
                new Error('Email authorization error');
            });
        });
    });
</script>
</body>
</html>