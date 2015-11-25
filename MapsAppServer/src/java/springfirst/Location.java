/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springfirst;

import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author haydarkarrar
 */
public class Location implements Comparable<Location>{

    public Location(String name, double latitude, double longitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    private double distance = Double.MAX_VALUE;
    
    public double getDistance(){
        return this.distance;
    }
    
    public void setDistance(double _lat, double _long){
        
        double x = Math.cos((Math.toRadians(this.latitude)+Math.toRadians(_lat))/2.0d);
        x = x * Math.toRadians(this.longitude - _long);
        
        double y = Math.toRadians(this.latitude - _lat);
        
        this.distance = Math.sqrt(y*y + x*x) * 6371;
        
    }
    
    private String name;

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    
    private double longitude;

    /**
     * Get the value of longitude
     *
     * @return the value of longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set the value of longitude
     *
     * @param longitude new value of longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private double latitude;

    /**
     * Get the value of latitude
     *
     * @return the value of latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Set the value of latitude
     *
     * @param latitude new value of latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public int compareTo(Location loc) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;
        
        if (this == loc) return EQUAL;
        
        if(this.distance <= loc.getDistance()) 
            return BEFORE;
        else 
            return AFTER;
    }

}
