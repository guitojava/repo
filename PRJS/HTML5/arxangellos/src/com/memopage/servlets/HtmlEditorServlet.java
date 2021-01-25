package com.memopage.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.memopage.Constants;
import com.memopage.dto.Memopage;
import com.memopage.dto.User;
import com.memopage.service.impl.MemopageServiceImpl;
import com.memopage.service.impl.UserServiceImpl;
import flexjson.JSONSerializer;


public class HtmlEditorServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {


	static Logger logger = Logger.getLogger(HtmlEditorServlet.class);
	JSONSerializer serializer = new JSONSerializer();
	
	UserServiceImpl UserService = new UserServiceImpl();
	MemopageServiceImpl memopageService = new MemopageServiceImpl();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("+++++++++++++++++++++++++++++++++++");
		logger.debug("HtmlEditorServlet ENTER");
		logger.debug("+++++++++++++++++++++++++++++++++++");
		
		String charSet = request.getCharacterEncoding();
		logger.debug(charSet == null ? "charSet is null (ISO-8859-1 is used by default)" : "charSet is set to " + charSet);
		
		request.setCharacterEncoding( "utf-8");
		logger.debug( " request.setCharacterEncoding  always set to  utf-8"  );
		
		
		String json = "['empty']"; // reply
		
			
		User userObj = (User) request.getSession().getAttribute("user");
		
		
		if (userObj == null ) logger.debug(" userObj is nULL   ");
		
		
		
		if (null != userObj) {
			logger.debug("  **  IN HTML  SERVELET >>  getSessionId ="
					+ userObj.getSessionId());
			
			
			request.getSession().setAttribute("user", UserService.getUser( userObj.getSid()   )   ); 
			// refresh it  so up to date always 	since we have totalGames and totalTeams variables 
			
			
			String action = request.getParameter("action");
			if (action == null)
				action = Constants.NULL;
				logger.debug("action: " + action);
		
		
		/************/
		
			if (  action.equals("action_command" )) {
				logger.debug("action_command  START  "    );
			//	String sid=request.getParameter("sid")==null?Constants.NULL:request.getParameter("sid");
			//	String newContent=request.getParameter("EditorDefault")==null?Constants.NULL:request.getParameter("EditorDefault");
			//	logger.debug("newContent  >>> " + newContent   );
				// save new content
				// Example 
				//Player player = 	PlayerService.getPlayer(userObj)	; 
				//	player.setPlayerNotes( newContent  ) ; 
				//PlayerService.updatePlayer(player);
			//	json = "  Save was sucessful. <br> Close this window and continue .    "; 
				// TODO return better message 
				logger.debug("action_command  END   "    );
				
			}
			
			
			
			else if (action.equals("saveProfileImage")) {
				logger.debug("saveProfileImage  ENTER");
				
				String sid=request.getParameter("sid")==null?Constants.NULL:request.getParameter("sid");
				String profileImg=request .getParameter("profileImg")==null?Constants.NULL:request.getParameter("profileImg");
				
			    logger.debug("sid="+sid);
				logger.debug("profileImg="+profileImg);
				
				userObj.setProfileImage(profileImg);
				if (  UserService.updateImage(userObj) > 0 ){
					userObj = UserService.getUser( userObj.getSid() ) ;
					// reset User Obj on Session (sync it up )  Important 
					request.getSession().setAttribute("user", userObj ); // when the user first logins	
					json = "<br> H εικόνα προφιλ σου είναι : <br> <img height=60px src='"+userObj.getProfileImage() +"'  ></img>";  
				}
				else{
					json = "{success:false,errors:{reason: 'Failed to UserService.updateTel '}}";
				}
				
				logger.debug("updateUserTel  EXIT");
			}
			
			
			
			
	/************/		
			
			
			
			
			
			
			else if (  action.equals("saveMemoPage" )) {
				
				logger.debug("saveMemoPage  Start "    );
				String sid=request.getParameter("sid")==null?Constants.NULL:request.getParameter("sid");
				String newContent=request.getParameter("EditorDefault")==null?Constants.NULL:request.getParameter("EditorDefault");
				logger.debug("saveMemoPage  sid   >>> " + sid   );
				int sidInt;
				try {
					sidInt = Integer.parseInt(sid); 
					// check ROLE 
					// if ( userObj.getRoleType().equals(Constants.ROLE_TYPE_PLAYER)){
					// }
					
					if ( sidInt == -1  )  {
						// new 
						
					} else if  (sidInt > 0 )  {
						// check for existing 
						Memopage mpage = memopageService.getMemopage(   sidInt ) ; 
						if (null!= mpage && userObj.getSid() == mpage.getUser().getSid() ){ // check that its his 
							 mpage.setPageContent(newContent );
							 
							 memopageService.updateMemopage(mpage);
							 
						}
					}
					
				} catch (Exception ex) {
					
					
				}	
					
				
				json = " Eνημέρωση.'Εγινε αποθήκευση πληροφοριών    :  <br> <br>" +  newContent; 
				
				logger.debug("saveMemoPage  end  "    );
				
			}	
			
			
			
			
			
			
			
			
			
			
			
		}// if User logged in 
		
		
		
		 
		 
		 
		// RETURN JSON ALWAYS 
		logger.debug("json return value=  " + json);
		response.setContentType("text/html; charset=UTF-8");
		logger.debug("+++++++++++++++++++++++++++++++++++");
		logger.debug("HtmlEditorServlet  EXIT");
		logger.debug("+++++++++++++++++++++++++++++++++++");
		response.getWriter().write(json);
		response.getWriter().flush();
		response.getWriter().close();
	}

	private static final long serialVersionUID = 2348614143535363877L;

	public HtmlEditorServlet() {
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