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
public class Location {

    public Location(String name, double longitude, double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
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

}
