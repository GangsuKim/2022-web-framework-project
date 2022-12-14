var kakao = window.kakao; // kakao 에러 방지
// const $ = (window as any);
var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
var options = {
    //지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(37.88355, 127.738202),
    level: 3, //지도의 레벨(확대, 축소 정도)
};
var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
// GetCenterPos
var GetCenterPos = document.getElementById("getCenterPos");
GetCenterPos.addEventListener("click", function () {
    console.log(map.getCenter());
});
showCvsOnMap(getJson('../data/cvs_coord_data.json'));
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
