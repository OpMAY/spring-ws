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

    <title>Home</title>
</head>
<body>
<h1>Home</h1>
<h1>File Upload</h1>
<form action="/upload.do" enctype="multipart/form-data" method="post">
    <input type="text" placeholder="id" name="id">
    <input type="text" placeholder="password" name="password">
    <input type="file" name="file">
    <button type="submit">업로드</button>
</form>

<form action="/uploads.do" enctype="multipart/form-data" method="post">
    <input type="text" placeholder="id" name="id">
    <input type="text" placeholder="password" name="password">
    <input type="file" multiple="multiple" name="files">
    <button type="submit">업로드</button>
</form>

<form action="/uploadsOther.do" enctype="multipart/form-data" method="post">
    <input type="text" placeholder="id" name="id">
    <input type="text" placeholder="password" name="password">
    <input type="file" name="file-1">
    <input type="file" name="file-2">
    <input type="file" name="file-3">
    <button type="submit">업로드</button>
</form>
<form action="/" method="post">
    <button type="submit">POST HOME</button>
</form>
<div id="scroll" style="overflow: scroll; width: 200px;max-width: 100px;">
    asdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasdasdsadasd
</div>
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
<script src="../../resources/js/scroll.js"></script>
<script src="../../resources/js/mousewheel.js"></script>
<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
-->
<script>
    $(document).ready(function () {
        sample();
        //errorSample();
        moveToScroll({move_id: 'scroll', top: 400, speed: 400, isClass: false});
        setMousewheel({
            id: 'scroll', isClass: false, prevent: true, onMouseWheel: function (e, delta) {
                console.log('custom', e, delta);
            }
        });
    });
</script>
</body>
</html>