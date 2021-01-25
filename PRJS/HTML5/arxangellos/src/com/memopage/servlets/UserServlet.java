package com.memopage.servlets;

import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


import com.memopage.Constants;
import com.memopage.dto.User;

import com.memopage.service.impl.UserServiceImpl;

import flexjson.JSONSerializer;
import utils.MakeDir;


public class UserServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {


	static Logger logger = Logger.getLogger(UserServlet.class);
	JSONSerializer serializer = new JSONSerializer();
	UserServiceImpl UserService = new UserServiceImpl();
	
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String json = "['empty']"; // reply
		logger.debug("+++++++++++++++++++++++++++++++++++");
		logger.debug("UserServlet ENTER");
		
		String charSet = request.getCharacterEncoding();
		logger.debug(charSet == null ? "charSet is null (ISO-8859-1 is used by default)": "charSet is set to " + charSet);
		request.setCharacterEncoding( "utf-8");
		logger.debug( " request.setCharacterEncoding  always set to  utf-8"  );
		
		String sessionId =   (String) request.getSession().getAttribute("sid") ==null
									? Constants.NULL: 
									(String)request.getSession().getAttribute("sid") ;
		
		logger.debug("sessionId=" + sessionId);
		
		
		String action = request.getParameter("action");
		if (action == null)
			action = Constants.NULL;

		logger.debug("action: " + action);

		
		
		if (action.equals("login")) {
			logger.debug("login  ENTER");
			
			String loginUsername=request.getParameter("loginUsername")==null?Constants.NULL:request.getParameter("loginUsername");
			String loginPassword=request.getParameter("loginPassword")==null?Constants.NULL:request.getParameter("loginPassword");
			
			
			logger.debug("loginUsername="+loginUsername);
			logger.debug("loginPassword="+loginPassword);
			
			if (null != loginUsername && loginUsername.length() > 0) {
				
				if (null != loginUsername && loginUsername.length() > 0) {
					
					
					User user = UserService.  getUser( loginUsername , loginPassword );
					
					if ( null != user){
						logger.debug("  **    >>  getSessionId =" + user.getSessionId());
						
						//  copy to session for latter use 
						// TODO check the userSession stuff 
						
						request.getSession().setAttribute("user", user ); // when the user first logins	

						User userTest = (User) request.getSession().getAttribute("user");
						logger.debug("  **  >>  userTest OK =" + userTest.getSessionId());
						
							
						UserService.updateLastLogin(userTest); 
						
						
						
						json = "{success:true, user:  "+  serializer.exclude("class").serialize( userTest )  +"  }";
						
						json = "true";
						
					}else {
						logger.debug("  **  >>  User  = is NULL " );
						json = "{success:true,errors:{reason:'Login failed.Try again'}}";
						
						json = "false"; 
					}

					
				} else {
					json = "{success:true,errors:{reason:'Login failed.Try again'}}";
				}
			
			
			}
			
			// check database 
			
			
			logger.debug("login  EXIT");
		}
		
		
		else if (action.equals("signup")) {
			logger.debug("signup  ENTER");
			
			String userId=request.getParameter("userId")==null?Constants.NULL:request.getParameter("userId");
			String pword1=request.getParameter("pword1")==null?Constants.NULL:request.getParameter("pword1");
			String pword2=request.getParameter("pword2")==null?Constants.NULL:request.getParameter("pword2");
			String email=request .getParameter("email")==null?Constants.NULL:request.getParameter("email");
			String tel=request .getParameter("tel")==null?Constants.NULL:request.getParameter("tel");
		    String roleType=request  .getParameter("roletype")==null?Constants.NULL:request.getParameter("roletype");
	
		    // override always PLAYER  for now till we create new role types
		    
		    roleType =Constants.ROLE_TYPE_PLAYER; 
		    
		    
		    //trim 
		    email = email.trim();
		    userId= userId.trim();
		    pword1=pword1.trim(); 
		    pword2=pword2.trim(); 
		    tel= tel.trim(); 
		    
		    userId = userId.replace(" " , ""); 
		    pword1 = pword1.replace(" " , ""); 
		    pword2 = pword2.replace(" " , ""); 
		    tel = tel.replace(" " , ""); 
		    email = email.replace(" " , ""); 
		    
		    
		    
		    logger.debug("userId="+userId);
			logger.debug("pword1="+pword1);
			logger.debug("pword2="+ pword2);
			logger.debug("email="+email);
			logger.debug("tel="+tel);
			logger.debug("roletype=" + roleType);
			
			User user = new User();
			
			user.setSid( -1 );   // undefined  
			user.setUname( userId.toLowerCase() );
			user.setPword(pword1);
			user.setRoleType(roleType);
			user.setEmail( email.toLowerCase() );
			user.setTel(tel);
			user.setLastLoginDt( Calendar.getInstance().getTime() );
			user.setCreateDt(Calendar.getInstance().getTime());
			user.setStatus( -1 ); // undefined 
			
			
			logger.debug("try to save new user ");
			
			
			// TODO   add   userName validations
			// UserName must be unique  
			// No Spaces and funny characters 
			// upto 30 chars  
			// TODO   add   tel , email    very important  validations
			// userName also used to create userImage directory   see below 

			
			
			// Add Server side validations  
			
			
			
			
			// check that user name is unique ?
			
			
			User nameUser = UserService.getUserByUName(userId); 
			
			if ( nameUser == null  )  // that uname is not used 
			{

				User emailUser = UserService.getUserByEmail(email); 
				if ( emailUser == null  )  // that uname is not used 
				{
				
					
					// create user and userdir 
					if (  UserService.craeteNewUser(user) >0 ){
						
						json = "{success:true}";
						String realPath = getServletContext().getRealPath("/");
						
						
						if (  MakeDir.makeUserImageDir(  realPath,  user.getUname()  )   ) {
							logger.info ("  Success Created  user image dirctory OK   realPath= " +realPath  );
							
							
							// send sign up email here >>> 
							
						} else {
							logger.error ("  NO user image directory created   FAIL       ");
						}
					}
					else{
						json = "{success:false,errors:{reason: 'Failed to UserService.craeteNewUser(user)'}}";
					}
					
					
					
				} else {
					
					json = "{success:false, errors:{reason: ' The email "+email+" is NOT unique, someone else has it, pick a different one and try again.  '}}";

				}
				
					
					
			} else {
				
		json = "{success:false, errors:{reason: ' The username "+userId+" is NOT unique, someone else has it, pick a different one and try again.  '}}";

				
			}
			
			
			logger.debug("signup  EXIT");
		}
		
		else if (action.equals("updateUserEmail")) {
			logger.debug("updateUserEmail  ENTER");
			
			String userId=request.getParameter("userId")==null?Constants.NULL:request.getParameter("userId");
			String email=request .getParameter("email")==null?Constants.NULL:request.getParameter("email");
			
			// check if email is used by other user 
			
			User  existingUser =  UserService.getUserByEmail( email );
			
			
			if  ( existingUser == null   )  {
			
			    logger.debug("userId="+userId);
				logger.debug("email="+email);
				
				User user = new User();
				user.setSid( Integer.parseInt(userId) );   
				user.setEmail(email);
				
				
				if (  UserService.updateEmail(user) >0 ){
					user = UserService.getUser( user.getSid() ) ;
					// reset User Obj on Session (sync it up )  Important 
					request.getSession().setAttribute("user", user ); // when the user first logins	
					json = "{success:true}";
				}
				else{
					json = "{success:false,errors:{reason: 'Failed to UserService.updateUserEmail'}}";
				}
				
				
			}  else{
				json = "{success:false ,errors:{reason: 'Email is used by someone else. '}}";
			}
			
			
			
			
			logger.debug("updateUserEmail  EXIT");
		}
		else if (action.equals("updateUserTel")) {
			logger.debug("updateUserTel  ENTER");
			
			String userId=request.getParameter("userId")==null?Constants.NULL:request.getParameter("userId");
			String tel=request .getParameter("tel")==null?Constants.NULL:request.getParameter("tel");
			
		    logger.debug("userId="+userId);
			logger.debug("tel="+tel);
			
			User user = new User();
			user.setSid( Integer.parseInt(userId) );   
			user.setTel(tel);
			
			
			if (  UserService.updateTel(user) >0 ){
				user = UserService.getUser( user.getSid() ) ;
				// reset User Obj on Session (sync it up )  Important 
				request.getSession().setAttribute("user", user ); // when the user first logins	
				json = "{success:true}";
			}
			else{
				json = "{success:false,errors:{reason: 'Failed to UserService.updateTel '}}";
			}
			
			logger.debug("updateUserTel  EXIT");
		}
		
		
	
		
		
		
		
		else if (action.equals("updateUserPassword")) {
			logger.debug("updateUserPassword  ENTER");
			
			String userId=request.getParameter("userId")==null?Constants.NULL:request.getParameter("userId");
			String pass1=request .getParameter("pass1")==null?Constants.NULL:request.getParameter("pass1");
			String pass2=request .getParameter("pass2")==null?Constants.NULL:request.getParameter("pass2");
			
		    logger.debug("userId="+userId);
			logger.debug("pass1="+pass1);
			logger.debug("pass2="+pass2);
			
			User user = new User();
			user.setSid( Integer.parseInt(userId) );   
			user.setPword(pass1);
			
			if ( pass1.equals(pass2 ) ) {
				// server side validation  TODO   
				
			
				if (  UserService.updatePassword(user) > 0 ){
					// user = UserService.getUser( user.getSid() ) ;
					// reset User Obj on Session (sync it up )  Important 
					// request.getSession().setAttribute("user", user ); // when the user first logins	
					json = "{success:true}";
				}
				else{
					json = "{success:false,errors:{reason: 'Failed to UserService.update Password(user)'}}";
				}
			
			
			}
			
			
			
			logger.debug("updateUserPassword  EXIT");
		}
		
		
		
		
		
		
		
		
		// RETURN JSON ALWAYS 
		
		logger.debug("json return value=  " + json);
		response.setContentType("text/javascript; charset=UTF-8");
		logger.debug("UserServlet EXIT");
		logger.debug("+++++++++++++++++++++++++++++++++++");
		response.getWriter().write(json);
		response.getWriter().flush();
		response.getWriter().close();
	}

	private static final long serialVersionUID = 2348614143535363877L;

	public UserServlet() {
		super();
	}

	protected void doService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}