/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springfirst;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    
    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/map/draw", method = RequestMethod.GET)
    public String draw(ModelMap model) {
        
        return "draw";
        //return "Testing";
    }

}
