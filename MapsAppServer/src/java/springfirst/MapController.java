/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springfirst;

import java.util.Random;
import java.util.Vector;
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
    
    @RequestMapping(value = "/map", method = RequestMethod.GET)

    public String map(ModelMap model) {
        model.addAttribute("msg", "JCG Hello World!");
        return "map";
    }
    
    @RequestMapping("/nearby")
    @ResponseBody
    public Vector<Location> nearby(@RequestParam(value="x") float x, @RequestParam(value="y") float y)
    {
        //TODO: change the algorithm
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
        Random rnd = new Random();
        int x;
        int y;
        for (int i = 0; i < 10; i++)
        {
            x = rnd.nextInt((int)x2 - (int)x1);
            y = rnd.nextInt((int)y2 - (int)y1);
            points.add(new Location("Test", x1+x, y1+y));
        }
        
        return points;
    }
}
