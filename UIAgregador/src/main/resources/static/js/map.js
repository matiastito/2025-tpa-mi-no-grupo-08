var map = L.map('map').setView([-34.628823, -58.454016], 13);
L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(map);
var popup = L.popup();
function onMapClick(e) {
    popup
        .setLatLng(e.latlng)
        .setContent("Coordenadas: " + e.latlng.toString())
        .openOn(map);
}
map.on('click', onMapClick);
