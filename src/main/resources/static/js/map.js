var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
var kakao = window.kakao; // kakao 에러 방지
var overAddress = null;
var overName = null;
var customOverlay;
var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
var options = {
    //지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(37.88355, 127.738202),
    level: 3, //지도의 레벨(확대, 축소 정도)
};
var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
var geocoder = new kakao.maps.services.Geocoder(); // GeoCoder 객체
var ps = new kakao.maps.services.Places();
// 지도 Click 이벤트 발생
kakao.maps.event.addListener(map, 'click', function (mouseEvent) {
    var latlng = mouseEvent.latLng;
    var latitude = latlng.getLat();
    var longitude = latlng.getLng();
    // 좌표 -> 주소 -> 해당 주소의 0번째 장소 이름 
    searchDetailAddrFromCoords(latlng, function (result, status) {
        if (status == kakao.maps.services.Status.OK) {
            var address = result[0].road_address.address_name;
            overAddress = address;
            setOverlayOnMap(address, latlng, map);
        }
    });
});
showCvsOnMap(getJson('../data/cvs_coord_data.json'));
window.onload = function () {
    if (getParameter('message')) {
        if (getParameter('message') === 'login') {
            alert('로그인이 필요한 기능 입니다. 로그인을 해 주세요.');
            history.pushState(null, null, 'http://localhost:8082/');
        }
        else {
            history.pushState(null, null, 'http://localhost:8082/');
        }
    }
};
// Show all CVS
function showCvsOnMap(json_data) {
    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png";
    var imageSize = new kakao.maps.Size(32, 34.5);
    var imageOption = { offset: new kakao.maps.Point(13.5, 34.5) };
    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
    var markerPosition = new kakao.maps.LatLng(37.87233678, 127.7199721);
    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        position: markerPosition,
        image: markerImage,
    });
    marker.setMap(map);
}
function getJson(url) {
    $.ajax({
        type: "get",
        url: url,
        dataType: "json",
        async: false,
        success: function (data) {
            return data;
        }
    });
    return;
}
function postJson(url) {
    $.ajax({
        type: "post",
        url: url,
        dataType: "json",
        async: false,
        success: function (data) {
            return data;
        }
    });
    return;
}
// Coords로 주소 검색
function searchDetailAddrFromCoords(coords, callback) {
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}
function setOverlayOnMap(address, position, map) {
    return __awaiter(this, void 0, void 0, function () {
        var infoJson;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    if (customOverlay != null) {
                        customOverlay.setMap(null);
                    }
                    $.ajax({
                        type: "get",
                        url: 'http://localhost:8082/room/info/' + address,
                        dataType: "json",
                        async: false,
                        success: function (data) {
                            infoJson = data;
                        }
                    });
                    return [4 /*yield*/, ps.keywordSearch(address, function (res, status) {
                            var _a, _b, _c, _d, _e;
                            var place_name = (_a = infoJson === null || infoJson === void 0 ? void 0 : infoJson.placeName) !== null && _a !== void 0 ? _a : true; // 장소 이름의 기본값
                            var place_address;
                            var isPublicPlace = false;
                            if (res.length != 0) { // 만약 검색된 장소가 존재할 경우
                                // console.log(res)
                                // 해당 장소들 중 주거시설로 등록된 주소지만 가져온다.
                                var living_things = res.filter(function (place) {
                                    var pl_name = place.category_name;
                                    // 주소 중 공공기관 / 학교가 하나라도 포함되어 있다면 예외처리
                                    if (pl_name.includes('공공기관') || pl_name.includes('학교')) {
                                        isPublicPlace = true;
                                    }
                                    return pl_name.includes('부동산 > 주거시설');
                                });
                                // 만약 주거시설로 등록된 주소지가 있을 경우
                                if (living_things.length != 0) {
                                    place_name = living_things[0].place_name; // 해당 주소지 배열 중 가장 첫번째의 장소명 저장
                                }
                            }
                            var content;
                            if (place_name == true) {
                                content = '    <div class="overlay">' +
                                    '    <div class="title-bar">' +
                                    '        <p class="title">정보가 없어요!</p>' +
                                    '    </div>' +
                                    '    <p class="please">ROOMER의 기여자가 되어 주세요!</p>' +
                                    '    <div class="create">' +
                                    '        <p class="write" onclick="create()"><i class="bi bi-pencil-square"></i> 정보 기입하기</p>' +
                                    '    </div>' +
                                    '    <div class="arrow"></div>' +
                                    '</div>';
                            }
                            else {
                                overName = place_name;
                                var review = '    <div class="review-box" onclick="write_review()">리뷰를 작성해 주세요!</div>';
                                if ((_b = infoJson === null || infoJson === void 0 ? void 0 : infoJson.review_cnt) !== null && _b !== void 0 ? _b : false) {
                                    if (infoJson.review_cnt != 0) {
                                        review = '    <div class="review-box">' + (infoJson === null || infoJson === void 0 ? void 0 : infoJson.recent_review) + '<span>+' + (infoJson.review_cnt - 1) + '</span></div>';
                                    }
                                }
                                content = '    <div class="overlay">' +
                                    '    <div class="title-bar">' +
                                    '        <p class="title">' + place_name + '</p>' +
                                    '        <div class="star">' + ((_c = (infoJson === null || infoJson === void 0 ? void 0 : infoJson.avg_star).toFixed(1)) !== null && _c !== void 0 ? _c : 0) + '<img src="./images/icons/star.png" alt="Star" width="30"></div>' +
                                    '    </div>' +
                                    review +
                                    '    <div class="price-box">' +
                                    '        <p class="price">' + ((_d = infoJson === null || infoJson === void 0 ? void 0 : infoJson.avg_price) !== null && _d !== void 0 ? _d : 0) + '만</p>' +
                                    '        <p class="payback">/ 보증금 ' + ((_e = infoJson === null || infoJson === void 0 ? void 0 : infoJson.avg_parking) !== null && _e !== void 0 ? _e : 0) + '만</p>' +
                                    '    </div>' +
                                    '    <div class="arrow"></div>' +
                                    '</div>';
                            }
                            customOverlay = new kakao.maps.CustomOverlay({
                                position: position,
                                content: content,
                                xAnchor: 0.07,
                                yAnchor: 1.05
                            });
                            // 공공기관이 아닐 경우에만 출력
                            if (!isPublicPlace) {
                                customOverlay.setMap(map);
                            }
                        })];
                case 1:
                    _a.sent();
                    return [2 /*return*/];
            }
        });
    });
}
function create() {
    location.href = 'http://localhost:8082/room/create?id=' + overAddress;
}
function write_review() {
    location.href = 'http://localhost:8082/room/review?id=' + overAddress + '&name=' + overName;
}
https: //gomest.tistory.com/10
 function getParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
