package com.memopage.servlets;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import net.fckeditor.handlers.PropertiesLoader;

public class ServletContextLoadUp implements ServletContextListener{
	ServletContext context;
	
	
	public void contextInitialized(ServletContextEvent contextEvent) {
		 System.out.println("Starting up  ...................... created by George Leon Karpathos Othos Greece 2009 ");
		 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		 
		 //  TODO  start a deamon to run each day once and delete any old records 
		
		  PropertiesLoader.setProperty("connector.userPathBuilderImpl",    "com.memopage.fckeditor.MyUserPathBuilder");
		  PropertiesLoader.setProperty("connector.userActionImpl",         "net.fckeditor.requestcycle.impl.EnabledUserAction");
		  PropertiesLoader.setProperty("connector.impl",         		   "net.fckeditor.connector.impl.LocalConnector");
			
		  
		  System.out.println("<< SET fckeditor properties (DONE OK) >> ");
		  
		//context = contextEvent.getServletContext();
		//// set variable to servlet context
		//context.setAttribute("TEST", "TEST_VALUE");
	}
	public void contextDestroyed(ServletContextEvent contextEvent) {
		//context = contextEvent.getServletContext();
		//System.out.println("Context Destroyed");
	}
}
