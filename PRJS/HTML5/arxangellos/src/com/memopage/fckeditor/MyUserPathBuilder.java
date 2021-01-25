package com.memopage.fckeditor;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.memopage.Constants;
import com.memopage.dto.User;




import net.fckeditor.requestcycle.UserPathBuilder;

public class MyUserPathBuilder implements UserPathBuilder 
{
	static Logger logger = Logger.getLogger(MyUserPathBuilder.class);
	@Override
	public String getUserFilesAbsolutePath(HttpServletRequest request) {
		
		logger.debug("getUserFilesAbsolutePath ENTER  >>  " );
		
		// request.getContextPath()
		
		
		String absPath = Constants.FCK_EDITOR_IMG_HEAD_PATH ;
		
		String path ="";
		User userObj = (User) request.getSession().getAttribute("user");
		if ( userObj != null ){
			path  = absPath + "/userfiles/"  + userObj.getUname() ;
		}else {
			
			path=  absPath + "/tempuserfiles"; 
		}
		
		logger.debug("path ==  "+ path );
		
		logger.debug("getUserFilesAbsolutePath EXIT ");
		
		return path ;
	}
	
	
		
	@Override
	public String getUserFilesPath(HttpServletRequest request) {
		
		logger.debug("getUserFilesPath ENTER"); 
		
		String path ="";
		User userObj = (User) request.getSession().getAttribute("user");
		if ( userObj != null ){
			path  = request.getContextPath() + "/userfiles/"  + userObj.getUname() ;
		}else {
			
			path= request.getContextPath() +  "/tempuserfiles"; 
		}
		
		logger.debug("path ==  "+ path );
		
		logger.debug("getUserFilesPath EXIT ");
		return path ;
	}
	
	
}
