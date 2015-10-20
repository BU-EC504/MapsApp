/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springfirst;

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
            @RequestParam(value="x2") float x2, @RequestParam(value="y2") float y2,
            @RequestParam(value="x3") float x3, @RequestParam(value="y3") float y3,
            @RequestParam(value="x4") float x4, @RequestParam(value="y4") float y4)
    {
        Vector<Location> points = new Vector<Location>();
        for (int i = 0; i < 10; i++)
        {
            points.add(new Location("Test", x1+x2+x3+x4, y1+y2+y3+y4));
        }
        
        return points;
    }
}
