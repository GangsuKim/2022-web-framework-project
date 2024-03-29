var geocoder = new kakao.maps.services.Geocoder(); // GeoCoder 객체
var ps = new kakao.maps.services.Places();
var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
let posx: Number, posy: Number;

geocoder.addressSearch(getParameter1("id"), (res, status) => {
    if(status === kakao.maps.services.Status.OK) {
        posx = res[0].x;
        posy = res[0].y;

        var coords = new kakao.maps.LatLng(res[0].y, res[0].x);


        var options = {
            //지도를 생성할 때 필요한 기본 옵션
            center: coords, //지도의 중심좌표.
            level: 2, //지도의 레벨(확대, 축소 정도)
        };

        var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });
    }
});

// https://gomest.tistory.com/10
function getParameter1(name: String): String {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}