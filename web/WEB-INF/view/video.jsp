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
    <h1>General</h1>
    <div class="video-container">
        <video id="my-video" class="video-js"
               controlsList="nodownload"
               controls preload="auto"
               width="600" height="400"
               poster="../../files/red-velvet-really-bad-boy-rbb-joy-irene-yeri-seulgi-wendy-s7812 (1).jpg"
               data-setup="{}">
            <source src="https://okiwi-test2.s3.ap-northeast-2.amazonaws.com/bulk/test/Nature+-+105936.mp4"/>
            <p class="vjs-no-js">
                To view this video please enable JavaScript, and consider upgrading to a
                web browser that
                <a href="https://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
            </p>
        </video>
    </div>
    <div class="video-container">
        <video id="my-video-1" class="video-js"
               controlsList="nodownload"
               controls preload="auto"
               width="600" height="400"
               poster="../../files/red-velvet-really-bad-boy-rbb-joy-irene-yeri-seulgi-wendy-s7812 (1).jpg"
               data-setup="{}">
            <source src="https://okiwi-test2.s3.ap-northeast-2.amazonaws.com/bulk/test/test_audio.mp3"/>
            <p class="vjs-no-js">
                To view this video please enable JavaScript, and consider upgrading to a
                web browser that
                <a href="https://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
            </p>
        </video>
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
<%-- Video.js--%>
<script src="../../resources/js/video/video.min.js"></script>
<script src="../../resources/js/video/videojs.hotkeys.min.js"></script>
<script>
    $(document).ready(function () {
        $('.video-container').on('contextmenu', function (e) {
            e.preventDefault();
        });

        let options = {};
        let player = videojs('my-video', options, function onPlayerReady() {
            console.log(this);
            console.log('video play ready callback');
            // In this context, `this` is the player that was created by Video.js.
            this.play();
            // How about an event listener?
            this.on('ended', function () {
                console.log('video play end work callback');
            });
        }).ready(function () {
            this.hotkeys({
                volumeStep: 0.1, //10% sound control
                seekStep: 10, //10fps next or prev control
                enableModifiersForNumbers: false
            });
        });

        let options1 = {};
        let player1 = videojs('my-video-1', options, function onPlayerReady() {
            console.log(this);
            console.log('video play ready callback');
            // In this context, `this` is the player that was created by Video.js.
            this.play();
            // How about an event listener?
            this.on('ended', function () {
                console.log('video play end work callback');
            });
        }).ready(function () {
            this.hotkeys({
                volumeStep: 0.1, //10% sound control
                seekStep: 10, //10fps next or prev control
                enableModifiersForNumbers: false
            });
        });
    });
</script>
</body>
</html>