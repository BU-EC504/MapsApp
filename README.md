# MapsApp
In the MapsApp project we’ll have to build an interactive application that displays a map of US and enables certain queries on a huge number of reference points in the US. We will use ref points extracted from the official US Board on Geographic Names dataset as input

#Project Structure
--MapsAppServer: Spring 4.1 Based Web application
----src
------java: Folder contains controllers implementation
--------MapController.java: main controller, which implements three actions.
----web
------WEB-INF: Folder contains jsp files

#Navigation
1. url/map - returns the map.jsp page, which displays the map.
2. url/nearby?x={float}&y={float} - returns the 10 closest points to the location(x,y), where x - latitude and y - longitude, in JSON format.
3. url/area?x1={float}&y1={float}&x2={float}&y2={float}&x3={float}&y3={float}&x4={float}&y4={float} - returns the points bounded by (x1,y1),(x2,y2),(x3,y3),(x4,y4) in JSON format.

#Algorithm to calculate the distance between coordinates
http://www.movable-type.co.uk/scripts/latlong.html

