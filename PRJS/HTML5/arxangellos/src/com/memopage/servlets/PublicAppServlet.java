package com.memopage.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.el.GreekAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.springframework.context.ApplicationContext;

import com.koin.dao.KoinAreaCodeDao;
import com.koin.dto.KoinAreaCode;
import com.koin.view.ExtJsPagingKoinAreaCodeList;
import com.memopage.weatherService.GetWeatherHttp;
import com.memopage.weatherService.Weather;

import com.memopage.Constants;
import com.memopage.SendGMail;
import com.memopage.SendJangoMail;
import com.memopage.Transforms;

import com.memopage.context.AppContext;
import com.memopage.dto.*;
import com.memopage.view.*;
import com.memopage.dto.User;
import com.memopage.search.SearchServiceImpl;
import com.memopage.service.impl.MemopageServiceImpl;
import com.memopage.service.impl.UserServiceImpl;
import com.memopage.view.CalenderJsonItem;

import flexjson.JSONSerializer;




public class PublicAppServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {

	static Logger logger = Logger.getLogger(PublicAppServlet.class);
	
		
	UserServiceImpl UserService = new UserServiceImpl();
	MemopageServiceImpl MemopageService = new MemopageServiceImpl();
	SearchServiceImpl SearchService = new SearchServiceImpl();
	
	ApplicationContext ctx = AppContext.getApplicationContext();
	
	KoinAreaCodeDao koinAreaCodeDao = (KoinAreaCodeDao) ctx.getBean("koinAreaCodeDao");   

	
	JSONSerializer serializer = new JSONSerializer();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String json = "['empty_reply']"; // reply
		logger.debug("+++++++++++++++++++++++++++++++++++");
		logger.debug("PublicAppServlet ENTER");

		String charSet = request.getCharacterEncoding();
		logger.debug(charSet == null ? "charSet is null (ISO-8859-1 is used by default)" : "charSet is set to " + charSet);
		request.setCharacterEncoding( "utf-8");
		logger.debug( " request.setCharacterEncoding  always set to  utf-8"  );
		
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy_HH:mm");
		
		
			
		
		
			String action = request.getParameter("action");
			if (action == null)
				action = Constants.NULL;
			logger.debug("action: " + action);

			
			
			//==============
			// ACTIONS 
			//===============

			if (action.equals("searchForMemopages")) {
				logger.debug("searchForMemopages  ENTER");
				
				String sid=request.getParameter("sid")==null?Constants.NULL:request.getParameter("sid");
				
				String searchTerms=request.getParameter("searchTerms")==null?Constants.NULL:request.getParameter("searchTerms");
				String searchType=request.getParameter("searchType")==null?Constants.NULL:request.getParameter("searchType");
				
				String nomos=request.getParameter("nomos")==null?Constants.NULL:request.getParameter("nomos");
				String pageType=request.getParameter("pageType")==null?Constants.NULL:request.getParameter("pageType");
				
				
				
				
				searchTerms = searchTerms.trim();
				
				int  iSid =-1;
				try {
					iSid = Integer.parseInt( searchTerms );
				} catch (Exception e) {
					iSid = -1;
				}
				
				
				int  nomosSid =-1;
				try {
					nomosSid = Integer.parseInt( nomos );
				} catch (Exception e) {
					nomosSid = -1;
				}
				
				int  pageTypeSid =-1;
				try {
					pageTypeSid = Integer.parseInt( pageType );
				} catch (Exception e) {
					pageTypeSid = -1;
				}
				
				if ( iSid > 0  ) {
					
					Memopage mpage = MemopageService.getMemopage( iSid ) ;  
					
					if (null != mpage){
						json = mpage.getPageContent();
					}
					else{ 
						json =  SearchService.getSearchResults(searchTerms, searchType, nomosSid, pageTypeSid );
					}
					
				}else {
					
					json = SearchService.getSearchResults(searchTerms, searchType, nomosSid, pageTypeSid);
					
				}
				
	// test 			
				
				
				logger.debug("searchForMemopages  EXIT");
			} 
			
			
			
			else if (action.equals("getMemopagesCalander")    ) {
				
				String sid=request.getParameter("sid")==null?Constants.NULL:request.getParameter("sid");
				
				List<CalenderJsonItem> 	calItems = SearchService.getCalanderResultsForAUserSearch( sid  );

				 json = 	serializer
				.exclude("class").exclude("*.class")
				.serialize( calItems );
				
				
				}
				
		
			
			else if (action.equals("getCategs")    ) {
				
				
				String areaCode=request.getParameter("areaCode")==null?Constants.NULL:request.getParameter("areaCode");
				
				json = 	Transforms.getMemopageCategorySelectOptionHtml( Integer.parseInt( areaCode )  );
			
			}
			
			
			else if (action.equals("getNomous")    ) {
				
				json = 	Transforms.getAreaCodesUsedForMemopagesSelectOptionHtml();
			
			}
			
			
			
			
			 
			else if (action.equals("loadAllAreaCodes")) {
				logger.debug("loadAllAreaCodes  ENTER");
				
				ExtJsPagingKoinAreaCodeList retList = new ExtJsPagingKoinAreaCodeList();
				
				List <KoinAreaCode> cList = koinAreaCodeDao.loadAllAreaCodes();
				retList.setTotal("" + cList.size() ); 
				retList.setResults( cList );
				
				json = serializer
				.exclude("class").exclude("*.class")
				.include("results")
				.serialize(retList);
				
				logger.debug("loadAllAreaCodes  EXIT");
				
			} 
			 
			 
			else if (action.equals("loadAreaCodes")) {
				logger.debug("loadAreaCodes  ENTER");
				
				ExtJsPagingKoinAreaCodeList retList = new ExtJsPagingKoinAreaCodeList();
				
				List <KoinAreaCode> cList = koinAreaCodeDao.loadAllAreaCodes();
				retList.setTotal("" + cList.size() ); 
				retList.setResults( cList );
				
				json = serializer
				.exclude("class").exclude("*.class")
				.include("results")
				.serialize(retList);
				
				logger.debug("loadAreaCodes  EXIT");
				
			} 
			
			 
			
		
			
			
			
			
			
			
			
			 
			// Send password Action  
			else if (action.equals("sendMeMyPassword")) {
				logger.debug("sendMeMyPassword  ENTER");
				
				
				String pval = request.getParameter("pval");
				String flagCmbx = request.getParameter("flagCmbx");
				
				
				User user = null;
				if ( flagCmbx.equals("email")){
					 user  = UserService.getUserByEmail(pval); 
				}
				else if ( flagCmbx.equals("uname")){
					user = UserService.getUserByUName(pval);  
				}
					
				
				if (user != null ){
					
					
					user = UserService.getUserWithPassword( user.getSid() );
					
					logger.debug(user.getPword()  );
					// send email 
					
					 SendGMail sendMail = new SendGMail();   
				//	SendJangoMail sendMail = new SendJangoMail();   
					 String[] sendTo = new String[1];
					
					 sendTo[0] = user.getEmail() ;
					
					 try {
						
						 
						 sendMail.sendSSLMessage( sendTo , 
								" EMAIL ME TON KODIKO SOU GIA www.arxangellos.com"  , 
								" ONOMA: " + user.getUname()+ "      KODIKOS: " + user.getPword()	, 
								"no reply  <noreply@arxangellos.com>" );
						
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					
					
				}
				
				
				
				logger.debug("sendMeMyPassword  EXIT");
			}
			 
		
			
		
		
		
		// RETURN JSON ALWAYS 
		logger.debug("json RETURN value=  " + json);
		response.setContentType("text/javascript; charset=UTF-8");
		logger.debug("PublicAppServlet  EXIT");
		logger.debug("+++++++++++++++++++++++++++++++++++");
		response.getWriter().write(json);
		response.getWriter().flush();
		response.getWriter().close();
	}

	private static final long serialVersionUID = 2348614143535363877L;

	public PublicAppServlet() {
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