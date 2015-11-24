/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springfirst;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Dana
 */
public class MapInitialize implements ServletContextListener{
    
    public static LocationTree lt;// = new LocationTree();
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
            lt.clearTree();
            System.out.println("ServletContextListener destroyed");
	}

        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("ServletContextListener started");
                ServletContext context = event.getServletContext();
                lt = new LocationTree();
                event.getServletContext().setAttribute("locationTree", lt);
          
	}
}
