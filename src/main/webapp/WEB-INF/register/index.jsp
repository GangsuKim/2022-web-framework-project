<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
</head>

<body>
    <form action="/login/request.do" method="POST">
        아이디 <input type="text" name="id" id="id"> <br>
        비밀번호 <input type="password" name="passwd" id="passwd"> <br>
        이름 <input type="text" name="name" id="name"> <br>
        닉네임 <input type="text" name="username" id="username"> <br>
        이메일 <input type="email" name="email" id="email"> <br>
        <input type="submit" value="가입하기">

        <input type="text" name="type" id="type" value="register" hidden>
    </form>
</body>

</html>