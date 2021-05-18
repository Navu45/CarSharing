<#import "layout.ftl" as page>

<@page.layout true>
    <div id="map"></div>
    <script>
        let map;

        function initMap() {
            const map = new google.maps.Map(document.getElementById("map"), {
                zoom: 4,
                center: uluru,
            });

            const marker = new google.maps.Marker({
                position: uluru,
                map: map,
            });
        }

    </script>
    <script async
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDqi7Ngt01gJIj44HonX4VeLJ-C5ZfhRLk&callback=initMap">
    </script>
</@page.layout>