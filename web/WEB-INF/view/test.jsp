<%--
  Created by IntelliJ IDEA.
  User: sangwoo
  Date: 2022-02-19
  Time: 오후 6:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</main>

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

</script>
</body>
</html>
