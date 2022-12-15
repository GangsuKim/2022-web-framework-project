var geocoder = new kakao.maps.services.Geocoder(); // GeoCoder 객체
var ps = new kakao.maps.services.Places();
var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스

geocoder.addressSearch(getParameter("id"), (res, status) => {
    if(status === kakao.maps.services.Status.OK) {
        var coords = new kakao.maps.LatLng(res[0].y, res[0].x);

        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });
    }
}) ;


var options = {
    //지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(37.88355, 127.738202), //지도의 중심좌표.
    level: 3, //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴


// https://gomest.tistory.com/10
function getParameter(name: String): String {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}