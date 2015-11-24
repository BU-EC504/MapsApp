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
    @Context
    private ServletContext context;
    
    @RequestMapping(value = "/map", method = RequestMethod.GET)

    
    
    public String map(ModelMap model) {
        model.addAttribute("msg", "JCG Hello World!");
        return "map";
    }
    
    
    /*@PostConstruct
    public void startUp()
    {
        LocationTree lt = new LocationTree();
    }*/
    
    @RequestMapping("/nearby")
    @ResponseBody
    public Vector<Location> nearby(@RequestParam(value="x") float x, @RequestParam(value="y") float y)
    {
        //TODO: change the algorithm
       // System.out.println("nearby function called");
        Vector<Location> points = new Vector<Location>();
        for (int i = 0; i < 10; i++)
        {
            points.add(new Location("Test", 1+i + x, 2*i + y));
        }
        
        return points;
    }

    @RequestMapping("/area")
    @ResponseBody
    public Vector<Location> area(@RequestParam(value="x1") float x1, @RequestParam(value="y1") float y1,
            @RequestParam(value="x2") float x2, @RequestParam(value="y2") float y2)
    {
        Vector<Location> points = new Vector<Location>();
        
        LocationTree ltree =(LocationTree)context.getAttribute("locationTree");
        //x2 is NWLat and y2 is NWlng ->needed for search
        //calculate rect dimensions here
        float width = Math.abs(x2-x1);
        float height = Math.abs(y2-y1);
        float[] dimensions = new float[]{width,height};
        float[] coords = new float[]{x2,y2};
        ArrayList<String> data = ltree.findArea(coords,dimensions);
        for(int i=0;i<data.size();i++)
        {
            //parse each string
            String[] str = data.get(i).split("\\s+");
            points.add(new Location(data.get(i), Float.parseFloat(str[str.length-2]), Float.parseFloat(str[str.length-1])));
        }

/*        Random rnd = new Random();
        int x;
        int y;
        for (int i = 0; i < 10; i++)
        {
            x = rnd.nextInt(Math.abs((int)x2 - (int)x1));
            y = rnd.nextInt(Math.abs((int)y2 - (int)y1));
            points.add(new Location("Test", x1+x, y1+y));
        }*/
        
        return points;
    }
}
