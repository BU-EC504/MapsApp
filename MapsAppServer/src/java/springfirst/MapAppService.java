/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springfirst;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author haydarkarrar
 */
public class MapAppService {

    public static Vector<Location> locations = new Vector<Location>();
    public static String prop;
    
    public MapAppService(String str){
    }
    
    public MapAppService(){
        Random rnd = new Random();
        int x;
        int y;
        for (int i = 0; i < 10; i++)
        {
            x = rnd.nextInt((int)100 - (int)20);
            y = rnd.nextInt((int)80 - (int)70);
            locations.add(new Location("Test", 20+x, 70+y));
        }
    }
    
    public void setProp(String s){
        this.prop = s;
    }
    
    public String getProp(){
        return this.prop;
    }
}
