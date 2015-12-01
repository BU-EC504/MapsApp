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

    public Location(String state,String province, float latitude, float longitude) {
        this.state = state;
        this.province = province;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    private float distance = Float.MAX_VALUE;
    
    public float getDistance(){
        return this.distance;
    }
    
    public void setDistance(float _lat, float _long){
        
        double x = Math.cos((Math.toRadians(this.latitude)+Math.toRadians(_lat))/2.0d);
        x = x * Math.toRadians(this.longitude - _long);
        
        double y = Math.toRadians(this.latitude - _lat);
        
        this.distance = (float)Math.sqrt(y*y + x*x) * 6371;
        
    }
    
    private String state;
    

    /**
     * Get the value of state
     *
     * @return the value of state
     */
    public String getState() {
        return state;
    }

    /**
     * Set the value of state
     *
     * @param state new value of state
     */
    public void setState(String state) {
        this.state = state;
    }

    
    private String province;
        /**
     * Get the value of province
     *
     * @return the value of province
     */
    public String getProvince() {
        return province;
    }

    /**
     * Set the value of province
     *
     * @param province new value of province
     */
    public void setProvince(String province) {
        this.province = province;
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
