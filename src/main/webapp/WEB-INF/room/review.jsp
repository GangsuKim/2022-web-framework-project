<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="ko">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>리뷰 작성 | ROOMER </title>

        <!-- Local Style -->
        <link rel="stylesheet" href="../css/universal.css">
        <link rel="stylesheet" href="../css/user/style.css">
        <link rel="stylesheet" href="../css/review/style.css">
        <link rel="stylesheet" href="../css/fonts.css">
        <link rel="stylesheet" href="../css/scroll.css">

        <!-- Bootstrap Icon -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
    </head>

    <body>
        <div class="topBar">
            <p class="title" onclick="location.href='../'">ROOMER</p>
        </div>

        <main>
            <p class="title">${place_name} 리뷰 작성</p>
            <hr style="margin: 5px 0px 20px 0px; height: 2px; background-color: black;">
            <form action="/room/review/register" method="POST">
                <p class="info-title">한줄 리뷰 내용</p>
                <input type="text" name="r_value" id="r_value"> <br>

                <p class="info-title" style="margin-top: 20px;">별점</p>
                <div class="star-div">
                    <input type="number" name="star" id="star" min="1" max="5">
                    <p class="star-point">점</p>
                </div>
                <!-- <input type="password" name="passwd" id="passwd"> <br> -->
                <br>
                <input type="submit" value="저장하기">
    
                <input type="text" name="room_id" id="room_id" value="${address}" hidden>
                <input type="text" name="name" id="name" value="${place_name}" hidden>
                <!-- <input type="text" name="writer" id="writer" value="${place_name}" hidden> -->
            </form>
        </main>
    </body>

    </html>