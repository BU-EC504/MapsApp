# MapsApp
##Description
In the MapsApp project we’ll have to build an interactive application that displays a map of US and enables certain queries on a huge number of reference points in the US. We will use ref points extracted from the official US Board on Geographic Names dataset as input

##File Structures
  - *src*
    - *java* - Folder contains controllers implementation
        - **MapController.java** - main controller, which implements three actions;
        - **MapService.java** - service which initialize RTree during application startup;
        - **Location.java** - model class for reference points in map;
        - **RTree.java** - RTree implementation.
    - web
        - *WEB-INF*: Folder contains jsp files;
            - **map.jsp**: jsp file, which displays the map to client;

##Navigation
1. **url/map** - returns the map.jsp page, which displays the map.
2. **url/nearby?x={float}&y={float}** - returns the 10 closest points to the location(x,y), where x - latitude and y - longitude, in JSON format.
3. **url/area?x1={float}&y1={float}&x2={float}&y2={float}** - returns the points bounded by (x1,y1),(x2,y2) in JSON format.

##Classes documentation
####MapController.java
- **map** - action, returns map.jsp;
- **nearby** - action, returns near by reference points (Location class objects) according to passed parameters;
- **area** - action, returns reference points (Location objects) inside specified rectangle. Rectangle is constructed by top-left and bottom-right points passed as parameters to action;  
####MapService.java

####RTree.java
####Location.java



