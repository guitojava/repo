package utils;
import java.io.File;

import org.apache.log4j.Logger;

public  class MakeDir {
	    
	static Logger logger = Logger.getLogger(MakeDir.class);
	
	public static  boolean  makeUserImageDir ( String realPath,   String folderName) {
			// so works for win and linux 
			if ( realPath.contains("\\") ) {
				realPath = realPath.replace( "\\" , "/"  ); 
			}
			logger.debug("Real path= " + realPath ); 
	    	// folderName   e.g.   "leon"      no slases 

			// hard coded into imagemanager 
			String 
//	    	baseDir = realPath + "js/ext_tiny/lib/tiny_mce/plugins/imagemanager/files/";
	    	
			baseDir = realPath + "userfiles/";
			
	    	String createDir = baseDir + folderName ; 
	    	
	    	
	    	logger.debug("createDir path = " + createDir ); 
	    	File f = new File(createDir);
	    	
	    	f.mkdir(); 
	    	
	    	f = new File(createDir+"/image/");
	    	
	    	
	    	// TODO add some images 
	    	
	    	return  	f.mkdir(); 
	}
	
	
}
