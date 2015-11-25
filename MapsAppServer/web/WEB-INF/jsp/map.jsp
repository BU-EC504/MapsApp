<%-- 
    Document   : map
    Created on : Oct 6, 2015, 1:34:58 PM
    Author     : haydarkarrar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Maps App</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <style>
      html, body {
        height: 100%;
        margin: 0px;
        padding: 0;
		background-color: black;
		background-image: url("http://thumbs.dreamstime.com/z/%E5%9F%8E%E5%B8%82%E8%99%9A%E6%9E%84%E7%9A%84%E6%98%A0%E5%B0%84%E9%80%8F%E8%A7%86%E5%9B%BE-19154899.jpg");
		background-repeat: no-repeat;
		background-size: 100%;
      }
	  #map {
	     min-width: 200px;
		width: 95%;
		min-height: 200px;
		height: 75%;
		border-style: solid;
		border-color: green;
		margin-left:30px;		
      }
    </style>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script>
		window.onload = function() {

    	var ex1 = document.getElementById('option1');
    	var ex2 = document.getElementById('option2');

    	ex1.onclick = initMap;
    	ex2.onclick = initMap;    	
    }
	</script>
  </head>
  <body>
    <div id="border">
	<br/>
	<br/>
		<div class="btn-group" style="margin-left:30px;" >
			<label class="btn btn-primary btn-lg active">
				<input type="radio" name="options" id="option1" checked > Reference points in an area
			</label>
			<label class="btn btn-primary btn-lg">
				<input type="radio" name="options" id="option2"> Find 10 closest points
			</label>
		</div>
	</div>
	<br/>
	<br />
    <div id="map"></div>
	<br/>
	<br />
    <script>
	var map;
	
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: new google.maps.LatLng(42.349457,-71.100739),
        zoom: 4
    });
  
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
  
    var selectedShape;

    function clearShape(){
        if (selectedShape){
            selectedShape.setEditable(false);
            selectedShape.setMap(null);
            selectedShape = null;
            while(markers.length){
                markers.pop().setMap(null);
            }
        }
    }

    function setSelection(shape) {
        clearShape();
        selectedShape = shape;
        shape.setEditable(true);
        selectColor(shape.get('fillColor') || shape.get('strokeColor'));
    }  
    addListenerToMap();
    var markers=[];
    function addListenerToMap(){
        var rectButton = document.getElementById('option1');
	if(rectButton.checked)
	{
            var drawingManagerRectangle = new google.maps.drawing.DrawingManager({
                drawingMode: google.maps.drawing.OverlayType.RECTANGLE,
		drawingControl: true,
		drawingControlOptions: {
                    position: google.maps.ControlPosition.TOP_RIGHT,
                    drawingModes: [        
                        google.maps.drawing.OverlayType.RECTANGLE
                    ]
		},
 		rectangleOptions: {
                    strokeColor: '#FF0000',
                    strokeOpacity: 0.8,
                    strokeWeight: 2,
                    fillColor: '#FF0000',
                    fillOpacity: 0.35,  
		}
            });
  
            google.maps.event.addListener(drawingManagerRectangle, 'overlaycomplete', function(e) {
                if (e.type == google.maps.drawing.OverlayType.RECTANGLE) 
                {
                    bounds = e.overlay.getBounds();
                    var NE = bounds.getNorthEast();
                    var SW = bounds.getSouthWest();
                    var NWLat = SW.lat();
                    var NWLng = NE.lng();
                    var SELat = NE.lat();
                    var SELng = SW.lng();
                    map.panTo(NE);
                    map.setZoom(6);
                    var refPoints;
                    
                  //  alert("http://localhost:8080/MapsAppServer/area?x1="+SELat+"&y1="+SELng+"&x2="+NWLat+"&y2="+NWLng);
                   
                    // Guess 100 random points inside the bounds, 
                    // put a marker at the first one contained by the polygon and break out of the loop
                    //alert("http://localhost:8080/MapsAppServer/area?x1="+SELat+"&y1="+SELng+"&x2="+NWLat+"&y2="+NWLng);
                    $.get("http://localhost:8080/MapsAppServer/area?x1="+SELat+"&y1="+SELng+"&x2="+NWLat+"&y2="+NWLng,function(data,status){
                        refPoints = data;
                        alert("Data "+data+ "\n Status:"+status);
                    });
                    alert("here2");
                    alert(Object.keys(refPoints).length);
                    
                    //var randomMarkers =Math.floor(Math.random() * 30) + 5 ;
                    for (var i = 0; i < Object.keys(refPoints).length; i++) 
                    {
                        //var ptLat = Math.random() * (NE.lat() - SW.lat()) + SW.lat();
			//var ptLng = Math.random() * (NE.lng() - SW.lng()) + SW.lng();
                        //alert("here3");
                        //we need to fix the way were sending the data from server before
                        var pLng = refPoints[i].longitude  ;
                        var pLat = refPoints[i].latitude;
                        var pName = refPoints[i].name;
                        //alert(pLat+","+pLng+" : "+ i);
			var point = new google.maps.LatLng(pLat,pLng);
                        
			var marker2 = new google.maps.Marker({position:point, map:map, customInfo: pName});
                        
			google.maps.event.addListener(marker2, 'mouseover', function() {
                            this.info = new google.maps.InfoWindow({
				content: this.customInfo,
				map: map
                            });
                            this.info.open(map, this);
			});
                        
			google.maps.event.addListener(marker2, 'mouseout', function() {
                            this.info.close();
			});
                        
                        
			markers.push(marker2);
                        //alert(markers);
                        //alert("hello");	 
                    }
		}
		 
    
                //reset drawing mode after drawing a shape.
                drawingManagerRectangle.setDrawingMode(null);

                var newShape = e.overlay;
                newShape.type = e.type;
                setSelection(newShape);
                //clearShape;
            });
            drawingManagerRectangle.setMap(map);
		
            //Delete the current shape when the drawing mode is changed, or when the map is clicked.
            google.maps.event.addListener(drawingManagerRectangle, 'drawingmode_changed', clearShape);
            google.maps.event.addListener(map, 'click', clearShape);
	}
	else
	{
            var drawingManagerMarker = new google.maps.drawing.DrawingManager({
                drawingMode: google.maps.drawing.OverlayType.MARKER,
		drawingControl: true,
		drawingControlOptions: {
                    position: google.maps.ControlPosition.TOP_RIGHT,
                    drawingModes: [
                        google.maps.drawing.OverlayType.MARKER
                    ]
		},
		/* markerOptions: {
		 icon: pinImage,
		},*/
  
		/* rectangleOptions: {
		strokeColor: '#FF0000',
		strokeOpacity: 0.8,
		strokeWeight: 2,
		fillColor: '#FF0000',
		fillOpacity: 0.35,  
		}*/

            });
            google.maps.event.addListener(drawingManagerMarker, 'overlaycomplete', function(e) 
            {
                if (e.type == google.maps.drawing.OverlayType.MARKER) 
                {
                    map.panTo(e.overlay.getPosition());
                    map.setZoom(7);
                    e.overlay.setIcon('http://maps.google.com/mapfiles/ms/icons/green-dot.png');			
                    var infowindow = new google.maps.InfoWindow({
                        content: '<p>Location:' + e.overlay.getPosition() + '</p>'
                    });
                    infowindow.open(map,e.overlay);
                    var circle = new google.maps.Circle({
                        radius: 250*1000, 
                        center: e.overlay,
                        map: map,
                        fillColor: '#FF0000',
                        fillOpacity: 0.2,
                        strokeColor: '#FF0000',
                        strokeOpacity: 0.6
                    }); 
			
                    circle.bindTo('center',e.overlay,'position');

                    var markerLat;
                    var markerLng;
                    var refPoints;
                    alert("http://localhost:8080/MapsAppServer/nearby?x="+e.overlay.getPosition().lat()+"&y="+e.overlay.getPosition().lng());
                    $.get("http://localhost:8080/MapsAppServer/nearby?x="+e.overlay.getPosition().lat()+"&y="+e.overlay.getPosition().lng(),function(data,status){
                        refPoints = data;
                        //alert("Data "+data+ "\n Status:"+status);
                    });
                    alert(Object.keys(refPoints).length);
                    for(var j=0; j<Object.keys(refPoints).length;j++)
                    {                       
                        markerLat = refPoints[j].latitude;
                        markerLng = refPoints[j].longitude;
                        var newmappoint = new google.maps.LatLng(markerLat, markerLng);
                        var randMarker = new google.maps.Marker({
                            position:newmappoint,
                            map: map
                        });
                       
                        google.maps.event.addListener(randMarker, 'mouseover', function() {	
			this.info = new google.maps.InfoWindow({
                            content: this.getPosition().lat()+","+this.getPosition().lng(),
                            map: map
			});
							
                        this.info.open(map, this);
                        });
				
                        google.maps.event.addListener(randMarker, 'mouseout', function() {
			this.info.close();
                        });
                        				
					
                        markers.push(randMarker);                   
                    };  
                }
    

                //reset drawing mode after drawing a shape.
                drawingManagerMarker.setDrawingMode(null);

                var newShape = e.overlay;
                newShape.type = e.type;
                setSelection(newShape);
                //clearShape;
            });

            drawingManagerMarker.setMap(map);
            //Delete the current shape when the drawing mode is changed, or when the map is clicked.
            google.maps.event.addListener(drawingManagerMarker, 'drawingmode_changed', clearShape);
            google.maps.event.addListener(map, 'click', clearShape);
	}
    
    }


    function getRandomArbitrary(min, max) {
        return Math.random() * (max - min) + min;
    }
 
 }
 </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA2soF--GqM4WkkKQyAdwSTyYkvzBTfO14&signed_in=true&libraries=drawing,geometry&callback=initMap"
         async defer></script>
  </body>
</html>