<#import "layout.ftl" as page>

<@page.layout true>
    <script src="index.js"></script>
    <div id="map"></div>
    <script>
        let map;
    </script>
    <script async
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDqi7Ngt01gJIj44HonX4VeLJ-C5ZfhRLk&callback=initMap">
    </script>
</@page.layout>