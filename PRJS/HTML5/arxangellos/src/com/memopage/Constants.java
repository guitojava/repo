package com.memopage;


public final class Constants {
	
	
	
	
	
	
	
	
	/*
	 *  check  
	 *  Constants.java 
	 *  globals.js
	 
	 *  htmlEditor.jsp
	 *  pickProfileImage.jsp
	 *  templates_config.xml
	 *   
	 *   applicationContext.xml 
	 * 
	 * 
	 */
	
	
	
	

	// laptop
	
	
	public final static String rootName = "arxangellos";  // memopage
	
	// production 
	/*
	public final static String FCK_EDITOR_IMG_HEAD_PATH = "/var/www/vhosts/leoninternet.com/jakarta-tomcat/webapps/"+ rootName;
	public final static String DIRECT_PUBLIC_URL	= "http://leoninternet.com/"+rootName+"/public.jsp" ;
	public final static String DB_conString =	"jdbc:mysql://leoninternet.com:3306/leoninte_memopage?useUnicode=true&amp;characterEncoding=UTF-8&amp;autoReconnect=true" ;
	public final static String DB_username = "leoni_memopage" ; 
	public final static String DB_password  ="arxangellos123";
	*/
	
	//localhost
	
	 public final static String DB_conString =	"jdbc:mysql://127.0.0.1:3306/memopage?useUnicode=true&amp;characterEncoding=UTF-8&amp;autoReconnect=true" ;
	public final static String DB_username = "memopage" ; 
	public final static String DB_password  ="memopage";
	public final static String FCK_EDITOR_IMG_HEAD_PATH = "C:/dev/apache-tomcat-6.0.18/webapps/"+ rootName;
	public final static String DIRECT_PUBLIC_URL	= "http://77.83.31.15/"+rootName+"/public.jsp" ;
	


	public final static String DEFAULT_EXTERNAL_PLAYER_IMAGE = "/"+rootName+"/css/logo/logo.png";
	public final static String DEFAULT_TEAM_IMAGE = "/"+rootName+"/css/logo/logo.png";
	public final static String DEFAULT_FIELD_IMAGE = "/"+rootName+"/css/logo/logo.png";
	public final static String DEFAULT_PLAYER_IMAGE = "/"+rootName+"/css/logo/logo.png";
	
	
	
	// user status 
	public final static int USER_STATUS_ACTIVE = 1;
	public final static int USER_STATUS_NOT_ACTIVE = 2;
	
	
	public final static String SEARCH_TYPE_PAGE = "pageSearch";
	public final static String SEARCH_TYPE_USER = "userSearch";
	public final static String SEARCH_TYPE_CALANDER = "calSearch";

	
	// memopage status 
	public final static int MEMOPAGE_STATUS_ACTIVE = 1;
	public final static int MEMOPAGE_STATUS_NOT_ACTIVE = 2;
	
	
	public final static int MEMOPAGE_IS_LISTED = 1;
	public final static int MEMOPAGE_IS_NOT_LISTED = 0;
	
	
	// sequences 
	public final static String 
	USER_SEQ							= "user";
	
	public final static String 
	MEMOPAGE_SEQ							= "memopage";
	
	
	public final static String 
	MESSAGES_SEQ							= "messages";
	
	
	
	// MESSAGE READ Status flags
	public final static int MESSAGE_READ_YES = 1;
	public final static int MESSAGE_READ_NO = 0;
	
 	
	
	// uSER ROLE_TYPE S
	public  final static String ROLE_TYPE_PLAYER = "PLAYER"; 
	public  final static String ROLE_TYPE_FIELD = "FIELD"; 
	
	
	
	
	// general Status flags
	public final static String NULL = "NULL";
	public final static String OK = "OK";
	public final static String ERROR= "ERROR";
	
	
	// map infopoint status 
	public final static String NEW= "NEW";
	public final static String EDIT= "EDIT";
	public final static String ON= "ON";
	public final static String OFF= "OFF";
	
	
	
	
	public String cleanString (String in){
		// removes all  ' and "  from String 
		return in.replace("'", "").replace("\"", "");
	}
	
}
