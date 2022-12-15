<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="ko">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>로그인 | ROOMER </title>

        <!-- Local Style -->
        <link rel="stylesheet" href="./css/universal.css">
        <link rel="stylesheet" href="./css/user/style.css">
        <link rel="stylesheet" href="./css/fonts.css">
        <link rel="stylesheet" href="./css/scroll.css">
    </head>

    <body>
        <div class="topBar">
            <p class="title" onclick="location.href='../'">ROOMER</p>
        </div>

        <main>
            <p class="title">ROOMER 로그인</p>
            <hr style="margin: 5px 0px 20px 0px; height: 2px; background-color: black;">
            <form action="/login/request.do" method="POST">
                <p class="info-title">아이디</p>
                <input type="text" name="id" id="id"> <br>

                <p class="info-title" style="margin-top: 20px;">비밀번호</p>
                <input type="password" name="passwd" id="passwd"> <br>

                <input type="submit" value="로그인">
    
                <input type="text" name="type" id="type" value="login" hidden>
            </form>
        </main>
    </body>

    </html>