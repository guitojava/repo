package com.memopage.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.koin.dao.KoinAreaCodeDao;
import com.memopage.Constants;
import com.memopage.Transforms;
import com.memopage.context.AppContext;
import com.memopage.dao.MemopageCategoryDao;
import com.memopage.dto.Memopage;
import com.memopage.dto.MemopageCategory;
import com.memopage.dto.User;
import com.memopage.service.MemopageService;
import com.memopage.service.impl.MemopageServiceImpl;
import com.memopage.service.impl.UserServiceImpl;
import com.memopage.view.*;

import flexjson.JSONSerializer;


public class MemopageAppServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {

	static Logger logger = Logger.getLogger(MemopageAppServlet.class);
	
	
	
	UserServiceImpl UserService = new UserServiceImpl();
 
	MemopageServiceImpl MemopageService = new MemopageServiceImpl();
	ApplicationContext ctx = AppContext.getApplicationContext();
	MemopageCategoryDao memopageCategoryDao = (MemopageCategoryDao) ctx.getBean("memopageCategoryDao");   
	
	JSONSerializer serializer = new JSONSerializer();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String json = "['empty_reply']"; // reply
		logger.debug("+++++++++++++++++++++++++++++++++++");
		logger.debug("MemopageAppServlet ENTER");

		String charSet = request.getCharacterEncoding();
		logger.debug(charSet == null ? "charSet is null (ISO-8859-1 is used by default)" : "charSet is set to " + charSet);

		request.setCharacterEncoding( "utf-8");
		logger.debug( " request.setCharacterEncoding  always set to  utf-8"  );
		
		User userObj = (User) request.getSession().getAttribute("user");
		
		
		if (null != userObj) {
			logger.debug("  **  IN  App  SERVELET >>  getSessionId ="
					+ userObj.getSessionId());
			
			
			request.getSession().setAttribute("user", UserService.getUser( userObj.getSid()   )   ); 
			// refresh it  so up to date always 	

			
			
			String action = request.getParameter("action");
			if (action == null)
				action = Constants.NULL;
			logger.debug("action: " + action);

			
			// ACTIONS 
			
			if (action.equals("getUserInfo")) {
				logger.debug("getUserInfo  ENTER");
				
				// send back the userObject as json
				json = serializer.exclude("class").serialize(  userObj   );

//				json = serializer.exclude("class").serialize( UserService.getUser( userObj.getSid()   ) );

				logger.debug("getUserInfo  EXIT");
			} 
			
			else if (action.equals("loadMyMemopages")) {
				logger.debug("loadMyMemopages  ENTER");
				
				//String limit=request.getParameter("limit")==null?Constants.NULL:request.getParameter("limit");
				//String start=request.getParameter("start")==null?Constants.NULL:request.getParameter("start");
				//String sort=request.getParameter("sort")==null?Constants.NULL:request.getParameter("sort");
				//String dir=request.getParameter("dir")==null?Constants.NULL:request.getParameter("dir");
				//logger.debug("sort  " + sort);
				//logger.debug("dir  " + dir);  // TODO   add to SQL 
				
				ExtJsMemopageList retList = new ExtJsMemopageList();
				
				List<MemopageBacking> allGames = MemopageService.getUserMemopages( userObj ) ;  
				retList.setTotal("" + allGames.size()  );
				retList.setResults( allGames );
				
				json = serializer
					.exclude("class").exclude("*.class")
					.include("results")
					.serialize(retList);
				
				logger.debug("loadMyMemopages  EXIT");
			} 
			
			
			else if (action.equals("createMemopage")) {
				logger.debug("createMemopage  ENTER");
				
					
					Memopage mpage    = new Memopage();
					
					mpage.setUser(  userObj );
					mpage.setStatus( Constants.USER_STATUS_ACTIVE);
					mpage.setPageName("  + Νέα σελίδα + "); 
					
					mpage.setIsListed( Constants.MEMOPAGE_IS_NOT_LISTED   );   
					mpage.setPageLang("EL");
					mpage.setAreaCode(0);
					mpage.setMapZoom("9");
					
					mpage.setCalendarCssClass("memopage");
					
					MemopageService .insertMemopage(mpage);
					MemopageBacking mbak = Transforms.transformMemopageBacking(mpage);
					
					json = serializer
					.exclude("class").exclude("*.class")
					.serialize(mbak);
					
				logger.debug("createMemopage  EXIT");
			} 
			
			
			
			else if (action.equals("cloneMemopage")) {
				logger.debug("cloneMemopage  ENTER");
					String sid=request.getParameter("sid")==null?Constants.NULL:request.getParameter("sid");
					int sidInt;
					try {
					sidInt = Integer.parseInt(sid); 
					if  (sidInt > 0 )  {
						// check for existing 
						Memopage orgMpage = MemopageService.getMemopage(   sidInt ) ; 
						if (null!= orgMpage && userObj.getSid() == orgMpage.getUser().getSid() ){ // check that its his 
							Memopage newMpage    = orgMpage ;
							newMpage.setPageName( " + Νέα σελίδα +  " + orgMpage.getPageName() ) ;
							MemopageService .insertMemopage(newMpage);
						}
					}
					} catch (Exception ex) {
						ex.printStackTrace();
						
					}	
					
				logger.debug("cloneMemopage  EXIT");
			} 
			
			
			
			
			
			
			
			
			
			
			// deleteMemopage
			
			else if (  action.equals("deleteMemopage" )) {
				
				logger.debug("deleteMemopage  Start "    );
				String sid=request.getParameter("sid")==null?Constants.NULL:request.getParameter("sid");
				logger.debug("deleteMemopage  sid   >>> " + sid   );
				int sidInt;
				try {
					sidInt = Integer.parseInt(sid); 
					
					if  (sidInt > 0 )  {
						// check for existing 
						Memopage mpage = MemopageService.getMemopage(   sidInt ) ; 
						if (null!= mpage && userObj.getSid() == mpage.getUser().getSid() ){ // check that its his 
							 MemopageService.deleteMemopage(sidInt);
						}
					}
					} catch (Exception ex) {
						ex.printStackTrace();
						
					}	
				json = "delete OK"; 
				logger.debug("deleteMemopage  end  "    );
			}	
			
			
			
			
			
			// get Memopage
			else if (action.equals("getMemopage")) {
				logger.debug("getMemopage  ENTER");
				
				String sid=request.getParameter("sid")==null?Constants.NULL:request.getParameter("sid");
				int  iSid =-1;
				try {
					iSid = Integer.parseInt(sid);
				} catch (Exception e) {
					iSid = -1;
				}
				
				if ( iSid > 0  ) {
					
					Memopage mpage   = MemopageService.getMemopage( iSid ) ;  
					
					if (    null  != mpage &&    
							null  != mpage.getUser() && 
							userObj.getSid() ==  mpage.getUser().getSid()  ) {  // check that belongs to user here
						
						MemopageBacking mbak = Transforms.transformMemopageBacking(mpage);
						
						json = serializer
						.exclude("class").exclude("*.class")
						.serialize(mbak);
					}
					
					
				}
				
				logger.debug("getMemopage  EXIT");
			} 
			
			
			
			
			
			else if (action.equals("loadAllCategs")) {
				logger.debug("loadAllCategs  ENTER");
				
				ExtJsPagingComboItemList retList = new ExtJsPagingComboItemList();
				List <MemopageCategory> catList = memopageCategoryDao.getCategories();
				List <ComboItem> cList = new ArrayList<ComboItem>();;
				for (MemopageCategory cat : catList) {
					ComboItem ci = new ComboItem();
					ci.setKey( ""+cat.getSid()  );
					ci.setDisplayFld( cat.getCategName()  );
					cList.add(  ci  ); 
				}
				
				retList.setTotal("" + cList.size() ); 
				retList.setResults( cList );
				
				json = serializer
				.exclude("class").exclude("*.class")
				.include("results")
				.serialize(retList);
				
				logger.debug("loadCategs  EXIT");
				
			} 
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			else if (  action.equals("saveBasicInfo" )) {
				logger.debug("saveBasicInfo  Start "    );
				String sid=request.getParameter("sid")==null?Constants.NULL:request.getParameter("sid");
				
				String status=request.getParameter("status")==null?Constants.NULL:request.getParameter("status");
				String pageType=request.getParameter("pageType")==null?Constants.NULL:request.getParameter("pageType");
				String isListed=request.getParameter("isListed")==null?Constants.NULL:request.getParameter("isListed");
				
				String pageWhenDay=request.getParameter("pageWhenDay")==null?Constants.NULL:request.getParameter("pageWhenDay");
				String pageWhenTime=request.getParameter("pageWhenTime")==null?Constants.NULL:request.getParameter("pageWhenTime");
				String pageWhenEndDay=request.getParameter("pageWhenEndDay")==null?Constants.NULL:request.getParameter("pageWhenEndDay");
				String pageWhenEndTime=request.getParameter("pageWhenEndTime")==null?Constants.NULL:request.getParameter("pageWhenEndTime");
				
				String pagekeywords=request.getParameter("pagekeywords")==null?Constants.NULL:request.getParameter("pagekeywords");
				String pageName=request.getParameter("pageName")==null?Constants.NULL:request.getParameter("pageName");
				String pageUrl=request.getParameter("pageUrl")==null?Constants.NULL:request.getParameter("pageUrl");
				String pageSummary=request.getParameter("pageSummary")==null?Constants.NULL:request.getParameter("pageSummary");
				
				String pageLang=request.getParameter("pageLang")==null?Constants.NULL:request.getParameter("pageLang");
				
				String hasComments=request.getParameter("hasComments")==null?Constants.NULL:request.getParameter("hasComments");
				String commentsAreReviewed=request.getParameter("commentsAreReviewed")==null?Constants.NULL:request.getParameter("commentsAreReviewed");
				
				String areaCode=request.getParameter("areaCode")==null?Constants.NULL:request.getParameter("areaCode");
				
				String pageAddress=request.getParameter("pageAddress")==null?Constants.NULL:request.getParameter("pageAddress");
				
				String pageLat=request.getParameter("pageLat")==null?Constants.NULL:request.getParameter("pageLat");
				
				String pageLng=request.getParameter("pageLng")==null?Constants.NULL:request.getParameter("pageLng");
			
				
				String pageTel=request.getParameter("pageTel")==null?Constants.NULL:request.getParameter("pageTel");
				String pageEmail=request.getParameter("pageEmail")==null?Constants.NULL:request.getParameter("pageEmail");
				String pageWebLink=request.getParameter("pageWebLink")==null?Constants.NULL:request.getParameter("pageWebLink");
				String priceEuros=request.getParameter("priceEuros")==null?Constants.NULL:request.getParameter("priceEuros");
				
				String calendarCssClass=request.getParameter("calendarCssClass")==null?Constants.NULL:request.getParameter("calendarCssClass");
				
				
				
				double dLat = -1; 
				double dLng = -1; 
				
				
				try {
					dLat =  Double.parseDouble(pageLat ) ;
					try {
						dLng =  Double.parseDouble(pageLng ) ;
					} catch (NumberFormatException  e) {
						dLng =-1 ;
						dLat =-1 ;
					}
				} catch (NumberFormatException  e) {
					dLng =-1 ;
					dLat =-1 ;
				}
				
				
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
				
				// get data here 
				logger.debug("saveBasicInfo  sid   >>> " + sid   );
				int sidInt;
				try {
					sidInt = Integer.parseInt(sid); 
					if  (sidInt > 0 )  {
						// check for existing 
						Memopage mpage = MemopageService.getMemopage(   sidInt ) ; 
						if (null!= mpage && userObj.getSid() == mpage.getUser().getSid() ){ // check that its his 
							 
							// mpage.setPageContent(newContent );
							 // set data here 
							
							
							mpage.setPageKeywords(pagekeywords.trim().replaceAll("\\<.*?\\>", ""));// remove html 
							mpage.setPageName(pageName.replaceAll("\\<.*?\\>", ""));
							// mpage.setPageUrl(pageUrl);
							
							String noHtmlPageSummary = pageSummary.trim().replaceAll("\\<.*?\\>", "");
							mpage.setPageSummary(noHtmlPageSummary);
							
							mpage.setPageType(  Integer.parseInt(pageType)  );
							mpage.setStatus(  Integer.parseInt(status)  );
							mpage.setIsListed(  Integer.parseInt(isListed)  );
							
							mpage.setPageLang(  pageLang   );
							
							mpage.setPageAddress(pageAddress.trim().replaceAll("\\<.*?\\>", ""));
							mpage.setPageLat(""+ dLat   );
							mpage.setPageLng(  ""+dLng   );
							
							mpage.setHasComments(  Integer.parseInt(hasComments)  );
							mpage.setCommentsAreReviewed(  Integer.parseInt(commentsAreReviewed)  );
							mpage.setAreaCode ( Integer.parseInt(areaCode)   );
												
							Date when =  sdf.parse(  pageWhenDay +" "+ pageWhenTime  );
							mpage.setPageWhen(when);
							
							Date whenEnd =  sdf.parse(  pageWhenEndDay +" "+ pageWhenEndTime  );
							
							mpage.setPageWhenEnd(whenEnd);
							mpage.setCalendarCssClass(calendarCssClass);
							
							
							
							mpage.setPageTel(    pageTel);
							mpage.setPageEmail( pageEmail);
							mpage.setPageWebLink( pageWebLink);
							mpage.setPriceEuros( priceEuros);
							
						
							
							
							
							
							MemopageService.updateMemopage(mpage);
							 
						}
					}
				} catch (Exception ex) {
					
					ex.printStackTrace();
					
					
				}	
				json = "OK"; 
				logger.debug("saveBasicInfo  end  "    );
			}	
			
			else if (  action.equals("saveMemopageMapInfo" )) {
				logger.debug("saveLocInfo  Start "    );
				String sid=request.getParameter("sid")==null?Constants.NULL:request.getParameter("sid");
				
				String latlng=request.getParameter("latlng")==null?Constants.NULL:request.getParameter("latlng");
				String address=request.getParameter("address")==null?Constants.NULL:request.getParameter("address");
				
				String mapZoom=request.getParameter("mapZoom")==null?Constants.NULL:request.getParameter("mapZoom");
				
				
				// get data here 
				logger.debug("saveMapInfo  sid   >>> " + sid   );
				int sidInt;
				try {
					sidInt = Integer.parseInt(sid); 
					if  (sidInt > 0 )  {
						// check for existing 
						Memopage mpage = MemopageService.getMemopage(   sidInt ) ; 
						if (null!= mpage && userObj.getSid() == mpage.getUser().getSid() ){ // check that its his 
							 
							mpage.setPageAddress(address.trim().replaceAll("\\<.*?\\>", ""));
							
							if ( latlng.contains(",") ){
								String[] sLatlng = latlng.split(",");
								mpage.setPageLat(sLatlng[0]);
								mpage.setPageLng(sLatlng[1]);
								
								mpage.setMapZoom(mapZoom);
								
							} 
							
							// mpage.setPageContent(newContent );
							// set data here 
							MemopageService.updateMemopage(mpage);
							 
						}
					}
				} catch (Exception ex) {
					
					ex.printStackTrace();
					
				}	
				json = "OK"; 
				logger.debug("saveMapInfo  end  "    );
			}	
			
			
			
			
			
			
			
			
			
			
			
			
			
//******** ADD Actions here  	
			
			
			
//++++++++
// KILL Session 
//+++++++
else if (action.equals("killSession")) {
	logger.debug("killSession  ENTER");
	
	//User user = (User) request.getSession().getAttribute("user");
	
	request.getSession().invalidate(); 
	
	
	logger.debug("killSession EXIT   ");
}
			
			
			
			
			

	
}	else {
	
	logger.debug("USER NOT LOGGING :  No User authenticated yet. ");
	
}


// RETURN JSON ALWAYS 
logger.debug("json RETURN value=  " + json);
response.setContentType("text/javascript; charset=UTF-8");
logger.debug(" MemopageAppServlet EXIT");
logger.debug("+++++++++++++++++++++++++++++++++++");
	response.getWriter().write(json);
	response.getWriter().flush();
	response.getWriter().close();
}

	private static final long serialVersionUID = 2348614143535363877L;

	public MemopageAppServlet() {
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