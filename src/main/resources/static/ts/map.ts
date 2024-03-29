const kakao = (window as any).kakao; // kakao 에러 방지
let overAddress:String = null;
let overName:String = null;
var customOverlay;


var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
var options = {
    //지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(37.88355, 127.738202), //지도의 중심좌표.
    level: 3, //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
var geocoder = new kakao.maps.services.Geocoder(); // GeoCoder 객체
var ps = new kakao.maps.services.Places();

// 지도 Click 이벤트 발생
kakao.maps.event.addListener(map, 'click', function (mouseEvent) {
    var latlng = mouseEvent.latLng;
    const latitude = latlng.getLat();
    const longitude = latlng.getLng();

    // 좌표 -> 주소 -> 해당 주소의 0번째 장소 이름 
    searchDetailAddrFromCoords(latlng, (result, status) => {
        if (status == kakao.maps.services.Status.OK) {
            const address = result[0].road_address.address_name;
            overAddress = address;
            setOverlayOnMap(address, latlng, map);
        }
    })
});

showCvsOnMap(getJson('../data/cvs_coord_data.json'));

window.onload = () => {
    if(getParameter('message')) {
        if(getParameter('message') === 'login') {
            alert('로그인이 필요한 기능 입니다. 로그인을 해 주세요.');
            history.pushState(null, null, 'http://localhost:8082/')
        } else {
            history.pushState(null, null, 'http://localhost:8082/')
        }
    }
}

// Show all CVS
function showCvsOnMap(json_data: JSON) {
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

function getJson(url: string): JSON {
    $.ajax({ //jquery ajax
        type: "get",
        url: url,
        dataType: "json",
        async: false,
        success: function (data: JSON) {
            return data;
        }
    })
    return;
}

function postJson(url: string): JSON {
    $.ajax({ //jquery ajax
        type: "post",
        url: url,
        dataType: "json",
        async: false,
        success: function (data: JSON) {
            return data;
        }
    })
    return;
}

// Coords로 주소 검색
function searchDetailAddrFromCoords(coords: any, callback: Function) {
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

async function setOverlayOnMap(address: string, position: any, map: any) {
    let infoJson: any;

    if (customOverlay != null) {
        customOverlay.setMap(null);
    }

    $.ajax({ //jquery ajax
        type: "get",
        url: 'http://localhost:8082/room/info/' + address,
        dataType: "json",
        async: false,
        success: function (data: JSON) {
            infoJson = data;
        }
    })

    await ps.keywordSearch(address, (res: any, status: any) => {
        var place_name: String | Boolean = infoJson?.placeName ?? true; // 장소 이름의 기본값
        let place_address: String;
        var isPublicPlace: Boolean = false;

        if(res.length != 0) { // 만약 검색된 장소가 존재할 경우
            // console.log(res)
            // 해당 장소들 중 주거시설로 등록된 주소지만 가져온다.
            const living_things = res.filter((place) => {
                const pl_name: String = place.category_name;
                
                // 주소 중 공공기관 / 학교가 하나라도 포함되어 있다면 예외처리
                if(pl_name.includes('공공기관') || pl_name.includes('학교')) {
                    isPublicPlace = true;
                }

                return pl_name.includes('부동산 > 주거시설');
            })

            // 만약 주거시설로 등록된 주소지가 있을 경우
            if(living_things.length != 0) {
                place_name = living_things[0].place_name; // 해당 주소지 배열 중 가장 첫번째의 장소명 저장
            }
        }

        var content: String;

        if(place_name == true) {
            content = '    <div class="overlay">'+
            '    <div class="title-bar">'+
            '        <p class="title">정보가 없어요!</p>'+
            '    </div>'+
            '    <p class="please">ROOMER의 기여자가 되어 주세요!</p>'+
            '    <div class="create">'+
            '        <p class="write" onclick="create()"><i class="bi bi-pencil-square"></i> 정보 기입하기</p>'+
            '    </div>'+
            '    <div class="arrow"></div>'+
            '</div>';
        } else {
            overName = place_name as String;
            let review = '    <div class="review-box" onclick="write_review()">리뷰를 작성해 주세요!</div>'

            if(infoJson?.review_cnt ?? false) {
                if(infoJson.review_cnt != 0) {
                    review = '    <div class="review-box">' + infoJson?.recent_review + '<span>+' + (infoJson.review_cnt - 1) + '</span></div>'
                }
            }

            content = '    <div class="overlay">' +
            '    <div class="title-bar">' +
            '        <p class="title">' + place_name + '</p>' +
            '        <div class="star">' + ((infoJson?.avg_star).toFixed(1) ?? 0) + '<img src="./images/icons/star.png" alt="Star" width="30"></div>' +
            '    </div>' +
            review +
            '    <div class="price-box">' +
            '        <p class="price">' + (infoJson?.avg_price ?? 0) + '만</p>' +
            '        <p class="payback">/ 보증금 ' + (infoJson?.avg_parking ?? 0) + '만</p>' +
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
        if(!isPublicPlace) {
            customOverlay.setMap(map);
        }
    });
}

function create() {
    location.href = 'http://localhost:8082/room/create?id=' + overAddress;
}

function write_review() {
    location.href = 'http://localhost:8082/room/review?id=' + overAddress + '&name=' + overName;
}

https://gomest.tistory.com/10
function getParameter(name: String): String {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}