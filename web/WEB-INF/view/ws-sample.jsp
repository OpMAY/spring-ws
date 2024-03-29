<%--
  Created by IntelliJ IDEA.
  User: zlzld
  Date: 2022-08-17
  Time: 오전 2:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
          crossorigin="anonymous">
    <!-- Base -->
    <link rel="stylesheet"
          href="/resources/css/base/reset.css">
    <link rel="stylesheet"
          href="/resources/css/base/default.css">
    <link rel="stylesheet"
          href="/resources/css/base/common.css">
    <!-- Theme -->
    <link rel="stylesheet"
          href="/resources/css/theme/theme.css">
    <!-- Layout -->
    <link rel="stylesheet"
          href="/resources/css/layout/layout.css">
    <link rel="stylesheet"
          href="/resources/css/layout/res-layout.css">
    <!-- Plugin -->
    <link rel="stylesheet"
          href="/resources/css/plugin/sample.css">
    <!-- State -->
    <link rel="stylesheet"
          href="/resources/css/state/state.css">
    <link rel="stylesheet"
          href="/resources/css/state/res-state.css">
    <!-- Module -->
    <link rel="stylesheet"
          href="/resources/css/module/module.css">
    <link rel="stylesheet"
          href="/resources/css/module/res-module.css">

    <title>Hello, world!</title>
</head>
<body>
<div class="row">
    <div class="col-12">
        <button class="btn btn-primary" id="test">send ws Data</button>
        <span class="medium-h5" id="status">websocket not connected</span>
        <span class="medium-h5" style="color: #950B02" id="count">0</span>
    </div>
    <div class="col-12 p-16 ml-16">
        <label class="form-label" for="test-input">INPUT</label>
        <textarea class="form-control" id="test-input" style="resize: none" rows="8"></textarea>
    </div>
</div>
<footer id="footer"
        class="l-footer-fixed"></footer>

<!-- Optional JavaScript; choose one of the two! -->

<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<!-- Jquery 3.6.0 -->
<script src="https://code.jquery.com/jquery-3.6.0.js"
        integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>

<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
-->

<script src="/resources/js/utility.js"></script>
<script src="/resources/js/common.js"></script>
<script src="/resources/js/app.js"></script>
<script src="/resources/js/plugin/library-sample.js"></script>
<!-- Module JS -->
<!-- Module JS 는 특정 페이지가 아니라 다양한 페이지에서 작동하는 부분으로 Event 및 Element 생성 및 다양한 화면에 진입했을 때
     공통적으로 로직을 수행하는 Javascript를 Module JS라고 한다.
     Selector (선택자)의 Length 및 Empty 여부를 예외처리로 해줘야한다.
     (선택자가 없으면 또는 선택자의 Length가 0이면 에러가 터질 수 있기 때문에) -->
<script src="/resources/js/module/sample.js"></script>
<script src="/resources/js/validation.js"></script>

<script>
    /**
     * Static JS
     * Static JS는 특정 페이지 에서만 작동하는 부분으로 Event 및 Element 생성 및 화면에 진입했을 때의
     * 해당 화면만의 특정 로직을 수행하는 Javascript를 Static JS라고 한다.
     * */
        // TODO WS INPUT, ACTION 동기화 문제
    let socket = null;
    let i = 1;
    let input_typing = false;
    $(document).ready(function () {
        console.log('Static JS is ready');
        connect();
    });

    function connect() {
        const ws = new WebSocket('ws://localhost:8080/ws');
        socket = ws;
        let status = $('#status');

        ws.onopen = (event) => {
            // TODO INITIAL SETTINGS ON WS OPEN
            console.log('websocket connected : ' + event);
            status.text('WS CONNECTED');
        }

        ws.onmessage = (event) => {
            console.log('websocket onmessage : ' + event);
            console.log('data : ' + event.data);
            console.log('log : ' + event.timeStamp);
            // TODO CALLBACKS
            try {
                let obj = JSON.parse(event.data);
                if (obj.type === 'input') {
                    if (!obj.isMyData)
                        $('#test-input').val(obj.data);
                } else if (obj.type === 'span') {
                    i = obj.data;
                    $('#count').text(obj.data);
                } else {
                    console.log('type or parse error');
                }
            } catch (e) {
                console.log('parse error : ' + e);
            }

        }

        // WebSocket Close 시 자동 WebSocket Connect
        ws.onclose = (event) => {
            // TODO INITIAL SETTINGS ON WS CLOSE
            console.log('websocket closed : ' + event);
            status.text('WS Disconnected');
            setTimeout(function () {
                connect();
            }, 1000)
        }

        // WebSocket Error 시 WebSocket Close
        ws.onerror = (error) => {
            console.log('websocket error : ' + error);
            ws.close();
        }

    }

    $('#test').on('click', function () {
        if (checkSocketReady()) {
            let obj = {
                'type': 'span',
                'data': ++i,
                'isMyData': false,
            }
            socket.send(JSON.stringify(obj));
        }
    })

    $('#test-input').on('input', function () {
        input_typing = true;
        let value = $(this).val();
        if (checkSocketReady()) {
            let obj = {
                'type': 'input',
                'data': value,
                'isMyData': false,
            }
            socket.send(JSON.stringify(obj));
        }
        input_typing = false;
    })

    function checkSocketReady() {
        if (socket.readyState !== 1) {
            console.log('socket not ready : ' + socket.readyState);
        }
        return socket.readyState === 1;
    }

</script>
</body>
</html>