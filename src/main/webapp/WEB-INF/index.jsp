<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ROOMER</title>

        <!-- Local Style -->
        <link rel="stylesheet" href="./css/universal.css">
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="./css/fonts.css">
        <link rel="stylesheet" href="./css/scroll.css">

        <!-- Kakao Map API -->
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=03b766d9319c5acf32c24de7281e3949"></script>

        <!-- jQuery -->
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    </head>

    <body>
        <div class="topBar">
            <p class="title">ROOMER</p>
        </div>
        <main>
            <div class="map-box">
                <div class="top-controller">
                    <div class="slider-box">
                        <input type="range" min="1" max="100" value="100" id="slider">
                        <div class="checkbox-box">
                            <input type="checkbox" name="use-range" id="use-range">
                        </div>
                    </div>
                </div>
                <div id="map"></div>
            </div>
            <div class="right-box">
                <div class="star">
                    <p class="title">ROOMER의 평점</p>
                    <div class="star-box">
                        <img src="./images/icons/star.png" alt="평점" class="star-image">
                        <p class="star-score">3.7</p>
                    </div>
                    <p class="oneline">대체적으로 조용하고 깔끔해요</p>
                </div>
                <div class="comment-box">
                    <p class="title">ROOMER 안 사람들의 한마디</p>
                    <div class="comment-box-inner custom-scroll">
                        <div class="top-shadow"></div>
                        <div class="comment">
                            <p class="name">김강수</p>
                            <p class="date">2022-11-17</p>
                            <p class="value">이 방 깨끗하고 좋아요~!</p>
                        </div>
                        <div class="comment">
                            <p class="name">김강수</p>
                            <p class="date">2022-11-17</p>
                            <p class="value">이 방 깨끗하고 좋아요~!</p>
                        </div>
                        <div class="comment">
                            <p class="name">김강수</p>
                            <p class="date">2022-11-17</p>
                            <p class="value">이 방 깨끗하고 좋아요~!</p>
                        </div>
                        <div class="comment">
                            <p class="name">김강수</p>
                            <p class="date">2022-11-17</p>
                            <p class="value">이 방 깨끗하고 좋아요~!</p>
                        </div>
                        <div class="comment">
                            <p class="name">김강수</p>
                            <p class="date">2022-11-17</p>
                            <p class="value">이 방 깨끗하고 좋아요~!</p>
                        </div>
                        <div class="comment">
                            <p class="name">김강수</p>
                            <p class="date">2022-11-17</p>
                            <p class="value">이 방 깨끗하고 좋아요~!</p>
                        </div>
                        <div class="comment">
                            <p class="name">김강수</p>
                            <p class="date">2022-11-17</p>
                            <p class="value">이 방 깨끗하고 좋아요~!</p>
                        </div>
                        <div class="comment">
                            <p class="name">김강수</p>
                            <p class="date">2022-11-17</p>
                            <p class="value">이 방 깨끗하고 좋아요~!</p>
                        </div>
                        <div class="comment">
                            <p class="name">김강수</p>
                            <p class="date">2022-11-17</p>
                            <p class="value">이 방 깨끗하고 좋아요~!</p>
                        </div>
                        <div class="comment">
                            <p class="name">김강수</p>
                            <p class="date">2022-11-17</p>
                            <p class="value">이 방 깨끗하고 좋아요~!</p>
                        </div>
                        <div class="comment">
                            <p class="name">김강수</p>
                            <p class="date">2022-11-17</p>
                            <p class="value">이 방 깨끗하고 좋아요~!</p>
                        </div>
                        <div class="comment">
                            <p class="name">김강수</p>
                            <p class="date">2022-11-17</p>
                            <p class="value">이 방 깨끗하고 좋아요~!</p>
                        </div>
                        <div class="comment">
                            <p class="name">김강수</p>
                            <p class="date">2022-11-17</p>
                            <p class="value">이 방 깨끗하고 좋아요~!</p>
                        </div>
                        <div class="comment">
                            <p class="name">김강수</p>
                            <p class="date">2022-11-17</p>
                            <p class="value">이 방 깨끗하고 좋아요~!</p>
                        </div>
                        <div class="bottom-shadow"></div>
                    </div>
                </div>
            </div>
        </main>
        <script src="./js/map.js"></script>
    </body>
</html>