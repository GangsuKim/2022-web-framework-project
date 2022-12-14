<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <form action="/login/request.do" method="POST">
        아이디 <input type="text" name="id" id="id"> <br>
        비밀번호 <input type="password" name="passwd" id="passwd"> <br>
        <input type="submit" value="로그인">

        <input type="text" name="type" id="type" value="login" hidden>
    </form>
</body>

</html>