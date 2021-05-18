<#import "layout.ftl" as page>

<@page.layout true "Home page | CarSharingApp">
    <div id="map"></div>
    <script>
        let map;
        let current_marker = null;
        let current_infowindow = null

        function initMap() {
            map = new google.maps.Map(document.getElementById("map"), {
                center: { lat: 55.67027036367935, lng: 37.479971162608706 },
                zoom: 15,
            });

            toUserPosition(map)

            const locationButton = document.createElement("button");
            locationButton.textContent = "Pan to Current Location";
            locationButton.classList.add("custom-map-control-button");
            map.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);
            locationButton.addEventListener("click", () => {
                toUserPosition(map)
            });

            let pos = map.getCenter()

            let i = 0
            fetch("/car/close_to_user/" + pos.lat() + "/" + pos.lng())
                .then(response => response.json())
                .then(data => {

                    for (const car of data) {
                        i++
                    }
                    if (i === 0) {
                        fetch("/car/init/"+ pos.lat() + "/" + pos.lng(),
                            {
                                method: 'POST',
                                headers: {
                                    'Content-Type': 'application/json;charset=utf-8'
                                },
                                body: ""
                            }
                        )
                    }
                })

            fetch("/car/close_to_user/" + pos.lat() + "/" + pos.lng())
                .then(response => response.json())
                .then(data => {
                    for (const car of data) {
                        const contentString =
                            '<div id="content">' +
                            '<div id="siteNotice"></div>' +
                            '<h1 id="firstHeading" class="firstHeading">Volkswagen</h1>' +
                            '<div id="bodyContent">' +
                            'Fuel: ' + car.fuel + '%' + '<br>' +
                            'Service cost: 8,99' + '<br>' +
                            'Car number: ' + car.carNumber + '<br>' +
                            '<button id="book" class="btn btn-primary mb-2 mt-2"' +
                            ' onclick="book(' + car.id + ')">Book</button>' +
                            '<button hidden id="close_booking" class="btn btn-primary mb-2 mt-2"' +
                            ' onclick="closeBooking(' + car.id + ')">Complete</button>' +
                            '</div>' +
                            '</div>'

                        const infowindow = new google.maps.InfoWindow({
                            content: contentString,
                        });

                        infowindow.addListener("closeclick", () => {
                            current_marker = null
                            current_infowindow = null
                        })

                        const marker = new google.maps.Marker({
                            position: {lat: car.latitude, lng: car.longitude},
                            map
                        });

                        marker.addListener("click", () => {
                            if (current_marker == null) {
                                infowindow.open(map, marker);
                                current_marker = marker
                                current_infowindow = infowindow
                            }
                            else if (current_marker === marker){
                                infowindow.close()
                                current_marker = null
                                current_infowindow = null
                            }
                        });
                    }
                })
                .catch(console.error);

            //Array.prototype.forEach.call(cars, (car) => {

            //})
        }

        function toUserPosition(map) {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(
                    (position) => {
                        const pos = {
                            lat: position.coords.latitude,
                            lng: position.coords.longitude,
                        };
                        map.setCenter(pos);
                    },
                    () => {
                        let infoWindow = new google.maps.InfoWindow();
                        handleLocationError(true, infoWindow, map.getCenter());
                    }
                );
            }
            else {
                let infoWindow = new google.maps.InfoWindow();
                handleLocationError(false, infoWindow, map.getCenter())
            }
            return map.getCenter();
        }

        function handleLocationError(browserHasGeolocation, infoWindow, pos) {
            infoWindow.setPosition(pos);
            infoWindow.setContent(
                browserHasGeolocation
                    ? "Error: The Geolocation service failed."
                    : "Error: Your browser doesn't support geolocation."
            );
            infoWindow.open(map);
        }

        function book(car_id) {
            fetch("/booking/create/" + car_id,
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8'
                    }
                }
            )
            document.getElementById("book").hidden = true
            document.getElementById("close_booking").hidden = false
        }

        function closeBooking(car_id) {
            fetch("/booking/close_c/" + car_id,
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8'
                    }
                }
            )
            document.getElementById("book").hidden = false
            document.getElementById("close_booking").hidden = true
        }

    </script>
    <script
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDqi7Ngt01gJIj44HonX4VeLJ-C5ZfhRLk&callback=initMap&libraries=&v=weekly"
            async
    ></script>
</@page.layout>