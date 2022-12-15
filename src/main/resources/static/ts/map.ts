const kakao = (window as any).kakao; // kakao 에러 방지
var customOverlay;
// import { sha256 } from '../js/sha256';
// import sha256 from 'crypto-js/sha256';

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
            setOverlayOnMap(address, latlng, map);
        }
    })
});

showCvsOnMap(getJson('../data/cvs_coord_data.json'));

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
    return
}

// Coords로 주소 검색
function searchDetailAddrFromCoords(coords: any, callback: Function) {
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

async function setOverlayOnMap(address: String, position: any, map: any) {
    if (customOverlay != null) {
        customOverlay.setMap(null);
    }

    await ps.keywordSearch(address, (res: any, status: any) => {
        var place_name = '정보 없음'; // 장소 이름의 기본값
        if(res.length != 0) { // 만약 검색된 장소가 존재할 경우
            console.log(res)
            // 해당 장소들 중 주거시설로 등록된 주소지만 가져온다.
            const living_things = res.filter((place) => {
                const pl_name: String = place.category_name;
                return pl_name.includes('부동산 > 주거시설');
            })

            // 만약 주거시설로 등록된 주소지가 있을 경우
            if(living_things.length != 0) {
                place_name = living_things[0].place_name; // 해당 주소지 배열 중 가장 첫번째의 장소명 저장
            }
        }

        var content: String = '    <div class="overlay">' +
            '    <div class="title-bar">' +
            '        <p class="title">' + place_name + '</p>' +
            '        <div class="star">3.4 <img src="./images/icons/star.png" alt="Star" width="30"></div>' +
            '    </div>' +
            '    <div class="review-box">좋긴 좋아요 근데 방음이...<span>+32</span></div>' +
            '    <div class="price-box">' +
            '        <p class="price">30.7만</p>' +
            '        <p class="payback">/ 보증금 300만</p>' +
            '    </div>' +
            '    <div class="arrow"></div>' +
            '</div>';

        customOverlay = new kakao.maps.CustomOverlay({
            position: position,
            content: content,
            xAnchor: 0.07,
            yAnchor: 1.05
        });

        customOverlay.setMap(map);
    });
}