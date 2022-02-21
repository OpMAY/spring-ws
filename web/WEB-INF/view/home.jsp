<%--
  Created by IntelliJ IDEA.
  User: zlzld
  Date: 2021-08-22
  Time: 오후 2:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <!--Datepicker CSS-->
    <link rel="stylesheet" href="../../resources/datepicker/bootstrap-datepicker.css">
    <!--Search CSS-->
    <link rel="stylesheet" href="../../resources/css/search.css">
    <link rel="stylesheet/scss" href="../../resources/scss/test.scss">
    <title>Home</title>
</head>
<body style="margin-top: 10vh; margin-bottom: 10vh;">
<h1>Home</h1>
<br/>
<form action="/" method="post">
    <button type="submit">POST HOME</button>
</form>
<br/>
<div id="scroll" style="overflow: scroll; width: 200px;max-width: 100px;">
    asdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasd
</div>
<br/>

<!--TODO File Upload Max Size Limit-->
<h4>4개 제한 통으로 업로드</h4>
<form action="/" method="get">
    <input id="input-uploadmax" type="file" multiple="multiple" name="files" accept="image/jpg, image/jpeg, image/png">
    <div id="input-uploadmax-preview" style="display: flex;">
    </div>
</form>
<script>
    document.getElementById('input-uploadmax').addEventListener('change', (event) => {
        if (event.currentTarget.files.length === 0) {
            console.log('업로드 제한 0개');
        } else if (event.currentTarget.files.length >= 1 && event.currentTarget.files.length <= 4) {
            console.log('업로드 가능');
            previewImages(event.currentTarget);
        } else {
            console.log('업로드 제한');
        }
    });

    function previewImages(target) {
        document.getElementById('input-uploadmax-preview').innerHTML = '';
        if (target.files) {
            [].forEach.call(target.files, readAndPreview);
        }

        function readAndPreview(file) {
            // Make sure `file.name` matches our extensions criteria
            if (!/\.(jpe?g|png|gif)$/i.test(file.name)) {
                return alert(file.name + " is not an image");
            } // else...
            var reader = new FileReader();
            reader.addEventListener("load", function () {
                var preview = document.getElementById('input-uploadmax-preview');
                var div = document.createElement('div');
                div.style.width = '100px';
                div.style.height = '100px';
                div.style.backgroundImage = 'url(\'' + this.result + '\')';
                preview.appendChild(div);
            });
            reader.readAsDataURL(file);
        }
    }
</script>
<%--TODO File Upload Max Size Limit End--%>

<%--TODO Inspection Test--%>
<h4>Inspection Test</h4>
<form action="#" type="get">
    <h6>input을 클릭하면 inpsection 실행</h6>
    <input id="email" type="text" placeholder="email을 입력해주세요"/>
</form>
<script>
    document.getElementById('email').addEventListener('click', event => {
        inspection({
            id_type: 'id', id: 'email', value: document.getElementById('email').value, type: 'email',
            empty: function () {
                console.log('empty');
            },
            success: function () {
                console.log('success');
            },
            failed: function () {
                console.log('failed');
            }
        })
    });
</script>
<%--TODO Insepection Test End--%>

<%--TODO Datepicker--%>
<h4>Datepicker Test</h4>
<form action="#" type="get">
    <h6>input을 클릭하면 datepicker 실행</h6>
    <input id="datepicker" type="text" placeholder="날짜를 선택해주세요."/>
</form>
<%--TODO Datepicker End--%>

<%--TODO Search--%>
<h4>Search</h4>
<div class="search-container">
    <input id="search" onkeypress="productSearch(this);" type="text">
    <div class="suggest-container" id="suggest-container">
        <ul class="list-group list-group-flush suggest-list">
            <a href="#" class="list-group-item list-group-item-action suggest-item">
                <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1 title">list group item test string</h5>
                    <small class="date">3 days ago</small>
                </div>
                <p class="mb-1 desc">Some placeholder content in a paragraph.</p>
                <small class="sub-desc">And some small print.</small>
            </a>
            <a href="#" class="list-group-item list-group-item-action suggest-item">
                <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1 title">Test the title string</h5>
                    <small class="date">3 days ago</small>
                </div>
                <p class="mb-1 desc">Some placeholder content in a paragraph.</p>
                <small class="sub-desc">And some small print.</small>
            </a>
            <a href="#" class="list-group-item list-group-item-action suggest-item">
                <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1 title">first class test string</h5>
                    <small class="date">3 days ago</small>
                </div>
                <p class="mb-1 desc">Some placeholder content in a paragraph.</p>
                <small class="sub-desc">And some small print.</small>
            </a>
        </ul>
    </div>
</div>

<%-- 카카오로그인 API START --%>
<div>
    <a id="btn-kakao" href="">Kakao 계정으로 로그인</a>
</div>

<%-- 네이버로그인 API START--%>
<a id="btn-naver"
   href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=CJua6oPGv9mb8DvUw0Lk&redirect_uri=http://localhost:8080/login.do&state=state(RandomString)">
    <img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/>
</a>

<%--구글로그인 API START--%>
<a class="d-flex" id="btn-google"
   href="#">
    <div style="width:18px;height:18px;" class="pr-4">
        <svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="18px" height="18px" viewBox="0 0 48 48"
             class="abcRioButtonSvg">
            <g>
                <path fill="#EA4335"
                      d="M24 9.5c3.54 0 6.71 1.22 9.21 3.6l6.85-6.85C35.9 2.38 30.47 0 24 0 14.62 0 6.51 5.38 2.56 13.22l7.98 6.19C12.43 13.72 17.74 9.5 24 9.5z"></path>
                <path fill="#4285F4"
                      d="M46.98 24.55c0-1.57-.15-3.09-.38-4.55H24v9.02h12.94c-.58 2.96-2.26 5.48-4.78 7.18l7.73 6c4.51-4.18 7.09-10.36 7.09-17.65z"></path>
                <path fill="#FBBC05"
                      d="M10.53 28.59c-.48-1.45-.76-2.99-.76-4.59s.27-3.14.76-4.59l-7.98-6.19C.92 16.46 0 20.12 0 24c0 3.88.92 7.54 2.56 10.78l7.97-6.19z"></path>
                <path fill="#34A853"
                      d="M24 48c6.48 0 11.93-2.13 15.89-5.81l-7.73-6c-2.15 1.45-4.92 2.3-8.16 2.3-6.26 0-11.57-4.22-13.47-9.91l-7.98 6.19C6.51 42.62 14.62 48 24 48z"></path>
                <path fill="none" d="M0 0h48v48H0z"></path>
            </g>
        </svg>
    </div>
    <span style="font-size:16px; line-height: 18px;" class="abcRioButtonContents"><span id="not_signed_invlexrt6s8czh">Sign in with Google</span><span
            id="connectedvlexrt6s8czh" style="display:none">Signed in with Google</span></span>
</a>
<script>
    /* 카카오 로그인 start */
    document.addEventListener("DOMContentLoaded", () => {
        kakaoLoginInit('btn-kakao');
        naverLoginInit('btn-naver');
        googleLoginInit('btn-google');
    });

    function kakaoLoginInit(k) {
        /**Kakao*/
        /**href="https://kauth.kakao.com/oauth/authorize?client_id=91a45959f25504f9d6b34be90863e5a4&redirect_uri=http://www.athalal.com/auth/login.do&response_type=code"*/
        let h = 'https://kauth.kakao.com/oauth/authorize';
        let c = '282b5c2ab35c942a331f07c4bc13542d';
        let r = 'http://localhost:8080/login.do';
        let t = 'code';
        document.getElementById(k).href = h + '?' + 'client_id=' + c + '&redirect_uri=' + r + '&response_type=' + t;
    }

    /* 카카오 로그인 end */

    /* 네이버 로그인 Start*/
    function naverLoginInit(n) {
        /**Naver*/
        /**href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=CJua6oPGv9mb8DvUw0Lk&redirect_uri=http://localhost:8080/login.do&state=state(RandomSTring)"*/
        let h = 'https://nid.naver.com/oauth2.0/authorize';
        let c = 'CJua6oPGv9mb8DvUw0Lk';
        let r = 'http://localhost:8080/login.do';
        let t = 'code';
        let s = randomString();
        document.getElementById(n).href = h + '?' + 'response_type=' + t + '&client_id=' + c + '&redirect_uri=' + r + '&state=' + s;
    }

    function randomString() {
        var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
        var string_length = 20;
        var randomstring = '';
        for (var i = 0; i < string_length; i++) {
            var rnum = Math.floor(Math.random() * chars.length);
            randomstring += chars.substring(rnum, rnum + 1);
        }
        return randomstring;
    }
    /* 네이버 로그인 end */

    /* 구글 로그인 Start*/
    function googleLoginInit(g) {
        /**Google*/
        /**href="https://accounts.google.com/o/oauth2/v2/auth?&scope=https://www.googleapis.com/auth/userinfo.email&response_type=code&state=state(RandomString)>&redirect_uri=http://www.athalal.com/auth/login.do&client_id=982940656821-bl1slfkqi4psro9mdi18aaj0mjtoa1lr.apps.googleusercontent.com"*/
        var h = 'https://accounts.google.com/o/oauth2/v2/auth';
        var sp = 'https://www.googleapis.com/auth/userinfo.email';
        var t = 'code';
        var s = randomString();
        var r = 'http://localhost:8080/login.do';
        var c = '982940656821-bl1slfkqi4psro9mdi18aaj0mjtoa1lr.apps.googleusercontent.com';
        document.getElementById(g).href = h + '?' + 'scope=' + sp + '&response_type=' + t + '&state=' + s + '&redirect_uri=' + r + '&client_id=' + c;
    }
    /* 구글 로그인 end*/
</script>


<%--TODO Search End--%>
<!-- Optional JavaScript; choose one of the two! -->

<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<%--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>--%>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>
<script src="../../resources/js/ajax.js"></script>
<script src="../../resources/js/mousewheel.js"></script>
<script src="../../resources/js/inspection.js"></script>
<script src="../../resources/js/cookie.js"></script>
<script src="../../resources/js/common.js"></script>
<script src="../../resources/datepicker/bootstrap-datepicker.js"></script>
<script src="../../resources/datepicker/bootstrap-datepicker.ko.min.js"></script>
<script src="../../resources/js/search.js"></script>
<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
-->
<script>
    $(document).ready(function () {
        /*TODO Ajax Sample*/
        sample();
        //errorSample();
        /*TODO Ajax Sample End*/

        moveToScroll({move_id: 'scroll', top: 400, speed: 400, isClass: false});
        setMousewheel({
            id: 'scroll', isClass: false, prevent: true, onMouseWheel: function (e, delta) {
                console.log('custom', e, delta);
            }
        });
    });

    <%--TODO Datepicker--%>
    $('#datepicker').datepicker('destroy');
    $('#datepicker').datepicker({
        autoclose: true,
        todayHighlight: true,
        language: "ko",
        orientation: "bottom auto"
    });
    <%--TODO Datepicker End--%>

    /*TODO Search*/
    const search = new Search({search_id: 'search', result_id: 'suggest-container'});
    search.init({value: '', placeholder: 'placeholder change test'});

    function productSearch() {
        if (event.keyCode === 13) {
            console.log('search');
            var data = [
                {
                    href: '#',
                    title: 'test1',
                    date: '2021-11-24',
                    desc: '설명',
                    sub_desc: '부가 설명',
                },
                {
                    href: '#',
                    title: 'test1',
                    date: '2021-11-24',
                    desc: '설명',
                    sub_desc: '부가 설명',
                },
                {
                    href: '#',
                    title: 'test1',
                    date: '2021-11-24',
                    desc: '설명',
                    sub_desc: '부가 설명',
                },
                {
                    href: '#',
                    title: 'test1',
                    date: '2021-11-24',
                    desc: '설명',
                    sub_desc: '부가 설명',
                },
            ]
            search.setData(data);
            search.openList();
        }
    }

    /*TODO Search End*/
</script>
</body>
</html>