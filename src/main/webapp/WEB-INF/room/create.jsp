<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="ko">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>기여 | ROOMER </title>

        <!-- Local Style -->
        <link rel="stylesheet" href="../css/universal.css">
        <link rel="stylesheet" href="../css/room/style.css">
        <link rel="stylesheet" href="../css/fonts.css">
        <link rel="stylesheet" href="../css/scroll.css">

        <!-- Kakao Map API -->
        <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=03b766d9319c5acf32c24de7281e3949&libraries=services"></script>
    </head>

    <body>
        <div class="topBar">
            <p class="title" onclick="location.href='../'">ROOMER</p>
        </div>

        <main>
            <p class="title">ROOMER에 건물 정보 등록</p>
            <hr style="margin: 5px 0px 20px 0px; height: 2px; background-color: black;">
            <div class="left-box">
                <div id="map"></div>
            </div>

            <div class="right-box">
                <form action="/room/register" method="POST">
                    <p class="info-title">건물 이름</p>
                    <input type="text" name="placeName" id="placeName"> <br>

                    <p class="info-title" style="margin-top: 20px;">건물 주소</p>
                    <input type="text" name="id" id="id" value="${address}" readonly> <br>

                    <input type="submit" value="등록하기">
                </form>
            </div>
        </main>

        <script src="../js/room/roomMap.js"></script>
    </body>

    </html>