<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?xml version="1.0" encoding="UTF-8" ?><%@page import="com.koin.dto.KoinAreaCode"%><%@page 
	import="com.koin.dao.KoinAreaCodeDao"%><%@page 
	import="com.memopage.context.AppContext"%><%@page 
	import="org.springframework.context.ApplicationContext"%><%@ 
	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%><%@page 
	import="com.memopage.dto.User"%><%@page
	import="org.apache.log4j.Logger"%><%@page
	import="com.memopage.Constants"%><%@page
	import="com.memopage.service.impl.MemopageServiceImpl"%><%@page 
	import="com.memopage.dto.Memopage"%>
	
&nbsp;&nbsp;&nbsp; <a target="_blank" class="colorlink"  style=" font-size:11px" href="http://www.arxangellos.com/" > δημοσίευσε κι εσύ  εύκολα και  γρηγορα  παντους τυπου   πληροφοριες μετο  <b> www.arxangellos.com </b> </a> 
<br>
<br>
	<%

	MemopageServiceImpl memopageService = new MemopageServiceImpl();
	Logger logger = Logger.getLogger("public.jsp");
	String content = " Not  Found  ";
	
	ApplicationContext ctx = AppContext.getApplicationContext(); 
	KoinAreaCodeDao koinAreaCodeDao = (KoinAreaCodeDao) ctx.getBean("koinAreaCodeDao");      
	
	
	
	String sid=request.getParameter("sid")==null?Constants.NULL:request.getParameter("sid");
	
	int sidInt;
	
	try {
	
		sidInt = Integer.parseInt(sid); 
					
				if (  sidInt > 0 ){
			
					Memopage mpage = memopageService.getMemopage(   sidInt ) ; 
					
					if (  mpage != null &&  mpage.getStatus() == 1 ){   
 					
					content = mpage .getPageContent() ;
					
					
					
					int areaCodeSid = -1;
					try {
						areaCodeSid = mpage.getAreaCode();
					} catch (Exception e) {
					}
						
					KoinAreaCode areaCodeObj = 	koinAreaCodeDao.getKoinAreaCodeWithSid(areaCodeSid);
					
					if ( areaCodeSid == -1  ) areaCodeObj.setDescription("Όλη την Ελλάδα");
					
					
					
					
					String pageHtml ="";
					
					
					if ( mpage .getUser().getProfileImage() != null && mpage .getUser().getProfileImage().length() > 3 ) { 
						pageHtml =  pageHtml   +   "<img  height=60px src='"+ mpage .getUser().getProfileImage() +"'  ></img>"  ;
					}	
					
					

					pageHtml = pageHtml  
							+   "<br><br>" 
							+	"<small>α/α: " + mpage.getSid() +"</small>" +   "&nbsp;&nbsp;&nbsp; <b>"+ mpage.getPageName()  +"</b>" ;  
						
					
							
					if (mpage.getPageSummary().length()> 3 ) { 
						pageHtml =  pageHtml   +   "<br>" +  " <b>Περίληψη:</b> " +   mpage.getPageSummary()+" " ;
					}	
							
							
					if (mpage.getPageCateg().getSid() != -1  ) { 
						pageHtml =  pageHtml   +   "<br><b>Θέμα:</b> "+ mpage.getPageCateg().getCategName() ;
					}		
							
					
							
					
					

					
					if ( mpage.getPageType() != -1  ) { 
						pageHtml = pageHtml  	+   "<br>" 
							+	"<b>Νομός:</b> " +  areaCodeObj.getDescription() +" ";
					} else {
						pageHtml = pageHtml  	+   "<br>" ;
					}
					
				
					
					
					
					
					
					if (mpage.getPageAddress().length()> 3 ) { 
						pageHtml =  pageHtml  +  "&nbsp;&nbsp;&nbsp; <b>Διεύθυνση:</b>" +   mpage.getPageAddress()+" " ;
					}
					
					if ( !mpage.getPageLat().equals("-1.0")  &&   !mpage.getPageLng().equals("-1.0")  ) { 
						pageHtml = pageHtml + " " +
								"&nbsp;&nbsp;&nbsp; <a class='colorlink'  target='_blank' " +
								" href ='./mapDisplay.html?mapZoom="+mpage.getMapZoom()+"&ads=" +mpage.getPageAddress() + "&lat="+ mpage.getPageLat() + "&lng="+mpage.getPageLng()+"'>[χάρτης]</a>";
						
					}
					
					
					
					
					String  linkurl = Constants.DIRECT_PUBLIC_URL+ "?sid="+mpage.getSid();
					String  linkname = mpage.getPageName();
					
					String shareHtml = 
					" <a class='colorlink' target='_blank'   " +		        //noslz
					" href='http://www.addtoany.com/share_save?linkname="+ linkname+"&amp;linkurl="+linkurl+"'>" +         //noslz
					"  [κοινοποίηση σε φίλους] </a> " +           //noslz
					" <script type='text/javascript'>a2a_linkname='"+linkname+"';a2a_linkurl='"+linkurl+"';</script>";           //noslz
					pageHtml = pageHtml  +    "<br>"   +shareHtml;
						
					
					
					if  ( mpage.getPageTel().length()> 0 ){
						pageHtml = pageHtml  	+
						"&nbsp;&nbsp;&nbsp; <b>Τελ.</b> "  + mpage.getPageTel(); 
					}	
					
					
					if  ( mpage.getPageEmail().length()> 0 ){
						pageHtml = pageHtml  	+
						"&nbsp;&nbsp;&nbsp; <a class='colorlink'  target='_blank' href ='mailto:"+mpage.getPageEmail()+"'>"+ mpage.getPageEmail() +"</a> "; 
					}
					
					if  ( mpage.getPageWebLink().length()> 0 ){
						pageHtml = pageHtml  	+
						"&nbsp;&nbsp;&nbsp; <a class='colorlink'  target='_blank' href ='"+mpage.getPageWebLink()+"'> "+mpage.getPageWebLink()+" </a> "; 
					}
					
					
					
					
					if  ( mpage.getPriceEuros().length() > 0 ){
						pageHtml = pageHtml  	+ 	   "<br>" +
						"ΖΗΤΟΥΜΕΝΗ ΤΙΜΗ.   "+  mpage.getPriceEuros()  + "  &euro;</b> "  ;
					}
					
					
					
					
					
					
					content = content.replace("<head>", "<head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><link rel='stylesheet' type='text/css' href='public.css' />" ) ;
					
					content = content.replace("<title></title>", "<title>"+mpage.getPageName()+"</title>" ) ;
					
					content = content.replace("<body>", "<body  style='padding-left:77px; padding-top: 20px;'> <br> "  + pageHtml + "<br><br>"  );
					
					
					} 
					else {
						
						content = " <BR><BR>  Δεν βρέθηκε τίποτα   ή  η  συγκεκριμένη ιστοσελίδα είναι απενεργοποιημένη από τον δημιουργό της " ; 
					}
					
					/*
					String   headHtml =  content.substring( 0, content.indexOf( "<body>" ) );
					String   restHtml =  content.substring( content.indexOf( "<body>" ) );
					logger.debug("headHtml");
					logger.debug(headHtml);
					logger.debug("restHtml");
					logger.debug(restHtml);
					*/
					
					
					
					
		} // user act
		
	
} catch (Exception ex) {
		
}
%><%=content%>




