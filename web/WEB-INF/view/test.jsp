<%@ page import="com.model.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: sangwoo
  Date: 2022-02-19
  Time: 오후 6:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<%
    List<User> users = (List<User>) request.getAttribute("users");
    request.setAttribute("users", users);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<main>
    <section>
        <button onclick="postTest1();">POST Test1</button>
        <button onclick="postTest2();">POST Test2</button>
        <button onclick="postTest3();">POST Test3</button>
        <button onclick="putTest();">PUT Test</button>
        <button onclick="patchTest();">PATCH Test</button>
        <button onclick="deleteTest();">DELETE Test</button>
    </section>
    <section>
        <form action="/file.do" enctype="multipart/form-data" method="post">
            <input type="file" name="file">
            <button type="submit">단일업로드</button>
        </form>
        <form action="/files.do" enctype="multipart/form-data" method="post">
            <input type="file" multiple="multiple" name="files">
            <button type="submit">복수업로드1</button>
        </form>
        <form action="/filemap.do" enctype="multipart/form-data" method="post">
            <input type="file" name="file1">
            <input type="file" name="file2">
            <input type="file" name="file3">
            <button type="submit">복수업로드2</button>
        </form>
    </section>
    <section>
        <table>
            <tr>
                <th>PK</th>
                <th>이름</th>
                <th>생성날짜</th>
                <th>수정날짜</th>
            </tr>
            <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.no}</td>
                <td>${user.name}</td>
                <td><tf:formatDatetime value="${user.reg_datetime}"/></td>
                <td><tf:formatDatetime value="${user.updated_datetime}"/></td>
            </tr>
            </c:forEach>
        </table>
        <form action="/user.do" method="post">
            <input type="text" name="name" placeholder="회원이름을 입력하세요.">
            <button type="submit">회원등록</button>
        </form>
    </section>
    <section>
        <span>쿠키 : </span>
        <span id="cookie">?</span>
        <input type="text" id="cookie-input" placeholder="쿠키에 넣을 숫자를 입력하세요.">
        <button type="button" onclick="addCookie();">브라우저 삽입</button>
        <button type="button" onclick="getDecryptedCookie();">쿠키정보보기</button>
    </section>
    <h1>대용량 파일 업로드</h1>
    <form action="/upload/bulk" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="text" name="test" value="test">
        <button type="submit">대용량 파일 업로드</button>
    </form>
</main>

<script src="/resources/js/cookie.js"></script>
<script>

    async function postTest1() {
        const data = {test: "test string"}
        const options = {
            method: "POST",
            body: new URLSearchParams(data)
        };
        try {
            const result = await fetch('/post-test1.do', options).then(res => res.json());
            console.log(result);
            alert(result);
        } catch (e) {
            console.error(e);
        }
    }
    async function postTest2() {
        const data = {test: "test string"}
        const options = {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;'
            },
            body: JSON.stringify(data)
        };
        try {
            const result = await fetch('/post-test2.do', options).then(res => res.json());
            console.log(result);
            alert(result);
        } catch (e) {
            console.error(e);
        }
    }
    async function postTest3() {
        const data = {
            email: "test",
            id: "test1",
            name: "test2"
        }
        const options = {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;'
            },
            body: JSON.stringify(data)
        };
        try {
            const result = await fetch('/post-test3.do', options).then(res => res.json());
            console.log(result);
            alert(result);
        } catch (e) {
            console.error(e);
        }
    }

    async function putTest() {
        const data = {test: "test string"}
        const options = {
            method: "PUT",
            body: new URLSearchParams(data)
        };
        try {
            const result = await fetch('/put-test.do', options).then(res => res.json());
            console.log(result);
            alert(result);
        } catch (e) {
            console.error(e);
        }
    }

    async function patchTest() {
        const data = {test: "test string"}
        const options = {
            method: "PATCH",
            body: new URLSearchParams(data)
        };
        try {
            const result = await fetch('/patch-test.do', options).then(res => res.json());
            console.log(result);
            alert(result);
        } catch (e) {
            console.error(e);
        }
    }

    async function deleteTest() {
        const data = {test: "test string"}
        const options = {
            method: "DELETE",
            body: new URLSearchParams(data)
        };
        try {
            const result = await fetch('/delete-test.do', options).then(res => res.json());
            console.log(result);
            alert(result);
        } catch (e) {
            console.error(e);
        }
    }

    document.addEventListener('DOMContentLoaded', function () {
        const cookie = getCookie('test');
        document.querySelector('#cookie').innerText = cookie;
    });

    async function addCookie() {
        let value = document.querySelector('#cookie-input').value;
        const data = {value};
        const options = {
            method: "POST",
            body: new URLSearchParams(data)
        };
        const result = await fetch('/encrypt.do', options).then(res => res.json()).then(res => res.data.result);
        setCookie({name: 'test', value: result});

        document.querySelector('#cookie').innerText = result;
    }
    async function getDecryptedCookie() {
        const cookie = getCookie('test');
        if (cookie === null) {
            alert('쿠키가 없습니다.');
            return;
        }
        const data = {value: cookie};
        const options = {
            method: "POST",
            body: new URLSearchParams(data)
        };
        const result = await fetch('/decrypt.do', options).then(res => res.json()).then(res => res.data.result);
        alert(result);
    }
</script>
</body>
</html>
