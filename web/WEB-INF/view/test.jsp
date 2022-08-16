<%--
  Created by IntelliJ IDEA.
  User: kevin
  Date: 2022-07-28
  Time: 오전 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <meta name ="google-signin-client_id" content="${GOOGLE_KEY}">
</head>
<body>
<div id="naverIdLogin"></div>
<button type="button" class="btn btn-secondary d-none" id="naver-logout">네이버 로그아웃</button>
<button type="button" class="btn btn-primary" id="kakao-popup">카카오 로그인 팝업</button>
<button type="button" class="btn btn-primary" id="kakao">카카오 로그인</button>
<button type="button" class="brn btn-danger" id="naver-rest">네이버 rest</button>
<button type="button" class="btn btn-pink" id="GgCustomLogin">
    <a href="javascript:void(0)">
        <span>Login with Google</span>
    </a>
</button>

<input type="hidden" class="key-property" id="KAKAO_KEY" value="${KAKAO_KEY}">
<input type="hidden" class="key-property" id="KAKAO_SECRET" value="${KAKAO_SECRET}">
<input type="hidden" class="key-property" id="KAKAO_JAVASCRIPT" value="${KAKAO_JAVASCRIPT}">
<input type="hidden" class="key-property" id="NAVER_KEY" value="${NAVER_KEY}">
<input type="hidden" class="key-property" id="NAVER_SECRET" value="${NAVER_SECRET}">
<input type="hidden" class="key-property" id="GOOGLE_KEY" value="${GOOGLE_KEY}">
<input type="hidden" class="key-property" id="GOOGLE_SECRET" value="${GOOGLE_SECRET}">


<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
        crossorigin="anonymous"></script>
<script src="../../resources/js/module/api/naver/naver-login.js" type="text/javascript"></script>
<script src="../../resources/js/module/api/kakao/kakao-login.js" type="text/javascript"></script>
<script src="../../resources/js/module/api/google/google-login.js" type="text/javascript"></script>
</body>
</html>
