package utils;

import java.io.File;

import org.apache.log4j.Logger;

public class DeleteFile {
	
	static Logger logger = Logger.getLogger(DeleteFile.class);
	
	 public static void delete(String fileName) {
		    try {
		      // Construct a File object for the file to be deleted.
		      File target = new File(fileName);

		      if (!target.exists()) {
		    	  
		        System.err.println("File " + fileName           + " not present to begin with!");
		    	logger.warn ("File " + fileName           + " not present to begin with!"); 
		        
		        return;
		      }

		      // Quick, now, delete it immediately:
		      if (target.delete()){
		    	  
		        System.err.println("** Deleted " + fileName + " **");
		        logger.warn ("** Deleted " + fileName + " **" ); 
		      }
		      else{
		        System.err.println("Failed to delete " + fileName);
		        logger.warn ("Failed to delete " + fileName  ); 
		      }
		    } catch (SecurityException e) {
		    	
		      System.err.println("Unable to delete " + fileName + "("
		          + e.getMessage() + ")");
		      
		      logger.error ("Unable to delete " + fileName + "("
			          + e.getMessage() + ")" ); 
		      
		    }
		  }

}
