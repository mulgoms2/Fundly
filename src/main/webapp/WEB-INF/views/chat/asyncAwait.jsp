<%--
  Created by IntelliJ IDEA.
  User: dobigulbi
  Date: 2/20/24
  Time: 7:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button id="as">asyncTest</button>
<script>
    document.querySelector("#as").addEventListener("click", myFunction);


    async function myFunction() {

        const myObj = {
            name: "한윤재",
            age: 30,
            region: "부천"
        };

        const jsonObj = JSON.stringify(myObj);

        console.log(jsonObj);

        const response = await fetch("/chat/test", {
            method: "POST",
            body: jsonObj
        }); // Q1: resul1 = Promise<Response> -> await -> Response -> await response.json()

        const response1 = fetch("/chat/test", {
            method: "POST",
            body: jsonObj
        }).then((hi)=>{
            return hi.json();
        }).then((hi)=>{
            return "hi";
        }).then((hi)=> hi);

        new Promise((resolve, reject) => {resolve() , reject(new Error()) }).then((value)=>value).then().then().catch(hi=>console.log(hi)).finally()

        const result2 = await result1; // Q2: result2 = Response

        const result3 = new Promise((resolve, reject)=>{ setTimeout(1000000, resolve) });

        const result4 = await (await fetch("/chat/file", {
            method: "POST",
            headers: {},
            body: JSON.stringify(myObj)
        })).json();

        // await 은  promise.then(prev) 에서 prev의 값을 가져온다.
        // const wait = await promise;
        // console.log(wait);

        const promise2 = promise.then(result => {
            const promi = result.json();
            // 리턴값으로 프로미스가 리턴되었다.
            return promi;
        });

        // const promise3 = promise2.then(prev => console.log(prev));

        // 왜 result.json()은 프로미스인데. then에서 받아진 값은 객체 자체인가?
        // then 의 콜백함수의 리턴값은 해당 promise 객체의 내부 값이 된다.
        // then 의 콜백함수의 리턴값은 promise 객체일 수도 있고, 일반적인 형태의 값일 수도 있다.

        const testPromise = new Promise(resolve => resolve("hello")).then(hello => {
            // 리턴값으로 프로미스가 오거나. 일반적인 형태의 값이 올 수 있다.
            // return new Promise(resolve => resolve("byeee"));
            // return "byeee";
            return Promise.resolve(hello);
        });

        console.log(testPromise);

        // const me = meee().then(console.log);
    }

    async function meee() {
        //     return new Promise(resolve => resolve("hee"));
        //      return Promise.resolve("hee");
        return "hee";
    }

</script>
</body>
</html>
