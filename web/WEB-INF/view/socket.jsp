<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>
<main>
    <section>
        <noscript>
            <h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being enabled. Please enable Javascript and reload this page!</h2>
        </noscript>
        <div id="main-content" class="container">
            <div class="row">
                <div class="col-md-6">
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="connect">WebSocket connection:</label>
                            <button id="connect" class="btn btn-default" type="submit">Connect</button>
                            <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Disconnect
                            </button>
                        </div>
                    </form>
                </div>
                <div class="col-md-6">
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="message">Enter message</label>
                            <input type="text" id="message" class="form-control" placeholder="enter message">
                        </div>
                        <button id="send" class="btn btn-default" type="button" onclick="sendMessage()">Send</button>
                        <button class="btn btn-default" type="button" onclick="disconnect()">Disconnect</button>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <table id="conversation" class="table table-striped">
                        <thead>
                        <tr>
                            <th>Greetings</th>
                        </tr>
                        </thead>
                        <tbody id="greetings">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
</main>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
        crossorigin="anonymous"></script>
<%--<script src="/webjars/sockjs-client/sockjs.min.js"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.2/sockjs.min.js"></script>--%>
<script src="/resources/js/stomp.js"></script>
<script src="/resources/js/sockjs.js"></script>
<script>
    let client = null;

    document.addEventListener('DOMContentLoaded', () => {
        connect();
    });

    function connect() {
        // 웹소켓 주소
        let wsUri = "/stomp";
        console.log('connecting to ', wsUri);
        client = Stomp.over(new SockJS(wsUri));

        client.connect({}, () => {
            console.log('connect stomp');
            client.subscribe('/topic/message', (event) => {
                console.log(event);
                console.log('message: ', JSON.parse(event.body).message);
            });
        });
    }

    function disconnect() {
        if (client !== null) {
            client.disconnect();
            client = null;
        }
        console.log("Disconnected");
    }

    function sendMessage() {
        if (client === null) {
            alert('연결되지 않음');
            return;
        }
        const message = document.querySelector('#message').value;
        const data = {message};
        client.send('/test', {}, JSON.stringify(data));
    }
</script>
</body>
</html>
