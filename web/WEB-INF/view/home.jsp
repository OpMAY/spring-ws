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