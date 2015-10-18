<%-- 
    Document   : map
    Created on : Oct 6, 2015, 1:34:58 PM
    Author     : haydarkarrar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Maps</title>
    <style>
      html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=geometry&key=AIzaSyA2soF--GqM4WkkKQyAdwSTyYkvzBTfO14"></script> 
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
    <script>


var map;

// center: new google.maps.LatLng(42.349457,-71.100739),

function initialize() {
  var mapOptions = {
    center: new google.maps.LatLng(42.349457,-71.100739),
    zoom: 4
   };
      
   map = new google.maps.Map(document.getElementById('map-canvas'),
     mapOptions);
 
   //code from http://www.devx.com/webdev/advanced-google-maps-functionality-tutorial.html
   // Set bounds 
   var strictBounds = new google.maps.LatLngBounds( new google.maps.LatLng(28.70, -127.50), new google.maps.LatLng(48.85, -55.90) ); 
   // Add the dragend listener to the map 
   google.maps.event.addListener(map, 'dragend', function() { 
   // The map is still inside the bounds 
   // Don't do anything 
   if (strictBounds.contains(map.getCenter())) return; // The map is out of bounds 
   // Move the map back within the bounds 
   var c = map.getCenter(), 
       x = c.lng(), y = c.lat(), 
       maxX = strictBounds.getNorthEast().lng(), 
       maxY = strictBounds.getNorthEast().lat(), 
       minX = strictBounds.getSouthWest().lng(), 
       minY = strictBounds.getSouthWest().lat(); 

   if (x < minX) x = minX; 
   if (x > maxX) x = maxX; 
   if (y < minY) y = minY; 
   if (y > maxY) y = maxY; 
   map.setCenter(new google.maps.LatLng(y, x)); });
   // - See more at: http://www.devx.com/webdev/advanced-google-maps-functionality-tutorial.html#sthash.ieJ3kq94.dpuf

   //get lat-long on click 
   google.maps.event.addListener(map,'click',function(event)
     {        
	placeMarker(event.latLng);
     });

}

var marker;

//code from http://www.w3schools.com/googleapi/google_maps_events.asp
function placeMarker(location) {
  
  if (marker != null) 
  {
     //if a marker was previously set(map clicked) remove it
     marker.setMap(null);  	
  }
  marker = new google.maps.Marker({
    position: location,
    map: map,
  });
  var infowindow = new google.maps.InfoWindow({
    content: 'Latitude: ' + location.lat() +
    '<br>Longitude: ' + location.lng()
  });
  infowindow.open(map,marker);
}


google.maps.event.addDomListener(window, 'load', initialize);

    </script>
  </head>
  <body>	
    <br />
    <br />
    <div id="map-canvas"></div>
  </body>
</html>


