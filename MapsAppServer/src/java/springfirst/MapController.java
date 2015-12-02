/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springfirst;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
//import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author haydarkarrar
 */



@Controller
public class MapController {
    
    private float _latitudeAdjust = 0.029f;
    private float _longitudeAdjust = 0.025f;
    private float _longitudeSquare = 0.05f;
    private float _latitudeSquare = 0.058f;
    
    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String map(ModelMap model) {
        model.addAttribute("msg", "JCG Hello World!");
        return "map";
    }
    
    @RequestMapping("/nearby")
    @ResponseBody
    public ArrayList<Location> nearby(@RequestParam(value="x") float x, @RequestParam(value="y") float y)
    {
        
        float y2 = Math.abs(y) - _longitudeAdjust;
        float x2 = x - _latitudeAdjust;
        
        float[] dimensions = new float[]{_latitudeSquare,_longitudeSquare};
        float[] coords = new float[]{x2,y2};
        
        ArrayList<Location> data = MapService.findArea(coords,dimensions);
        
        while(data.size() < 10)
        {
            //TODO: adjust the coordinates
            dimensions[0] += _latitudeSquare;
            dimensions[1] += _longitudeSquare;
            coords[0] -= _latitudeAdjust;
            coords[1] -= _longitudeAdjust;
            
            data = MapService.findArea(coords,dimensions);
            
        }
        
        for(Location loc : data){
            loc.setDistance(x, y);
        }
        
        data.sort(null);
        
        return data;
    }

    @RequestMapping("/area")
    @ResponseBody
    public ArrayList<Location> area(@RequestParam(value="x1") float x1, @RequestParam(value="y1") float y1,
            @RequestParam(value="x2") float x2, @RequestParam(value="y2") float y2)
    {
        
        float width = Math.abs(x2-x1);
        float height = Math.abs(y2-y1);
        y1 = Math.abs(y1);
        
        float[] dimensions = new float[]{width,height};
        float[] coords = new float[]{x1,y1};
        
        ArrayList<Location> data = MapService.findArea(coords,dimensions);
        
        return data;
    }
}
