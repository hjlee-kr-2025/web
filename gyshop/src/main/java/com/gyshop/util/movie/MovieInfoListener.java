package com.gyshop.util.movie;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MovieInfoListener
 *
 */
@WebListener
public class MovieInfoListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
	private MovieInfoSave movieInfoSave;
    public MovieInfoListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	movieInfoSave.stop();
    	System.out.println("movieInfoSave stop");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	System.out.println("movieInfoSave start");
    	movieInfoSave = new MovieInfoSave(60*60, "2025");
    	movieInfoSave.start();
    }
	
}
