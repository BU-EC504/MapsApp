/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springfirst;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author haydarkarrar
 */
@RestController
@RequestMapping("/rest")
public class MapRestController {
    
    @RequestMapping(value = "/nearby", method=GET)
    public Location nearby(@RequestParam(value="name", defaultValue="World") String name) {
        
        return new Location(String.format("%s", name), 32.0, 34.0);
    }
}
