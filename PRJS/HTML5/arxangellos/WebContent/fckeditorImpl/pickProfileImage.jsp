<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?xml version="1.0" encoding="UTF-8" ?><%@ 
page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ 
taglib uri="http://java.fckeditor.net" prefix="FCK" %><%@page 
	import="com.memopage.dto.User"%>

	<%@page 
	import="org.apache.log4j.Logger"%>
	<%@page 
	import="com.memopage.Constants"%>
	
	<html  xmlns="http://www.w3.org/1999/xhtml"><%
	
	Logger logger = Logger.getLogger("editor");	
	
	String content = " <h1>Warning !!! Not logged in !!!  </h1> ";
	
	User userObj = (User) request.getSession().getAttribute("user");
	
	String    HtmlEditorServlet =  "/"+Constants.rootName+"/HtmlEditorServlet";
	
	
	String act=request.getParameter("act")==null?Constants.NULL:request.getParameter("act");
	String sid=request.getParameter("sid")==null?Constants.NULL:request.getParameter("sid");
	
	logger.debug("  act=" + act);
	logger.debug("  sid=" + act);
	
	
	if (userObj != null && act != null) {

		
			if ( act.equals("saveProfileImage")) {
					
				content = userObj.getProfileImage();
				
			}
		
		//	content = "profile image content "; 
			

	} // user act 
	
	
	
	
	
	
%>

	<head>

		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="robots" content="noindex, nofollow" />
		
		<link href="editor.css" rel="stylesheet" type="text/css" />
		


		<script type="text/javascript">

/* development*/
 		
 		var  rootName ="arxangellos";
 		
 		
		var  conStr = 
			"http://" + location.hostname +":"+location.port
			+ "/"+rootName+"/fckeditor/editor/filemanager/browser/default/browser.html?Type=Image&"+
			"Connector="+ 	"http://" + location.hostname +":"+location.port		
			+"/"+rootName+"/fckeditor/editor/filemanager/connectors/php/connector.java";



/*
		
		For production  

		
		var  conStr = 
			"http://" + location.hostname +":"+location.port
			+ "/fckeditor/editor/filemanager/browser/default/browser.html?Type=Image&"+
			"Connector="+ 	"http://" + location.hostname +":"+location.port		
			+"/fckeditor/editor/filemanager/connectors/php/connector.java";

*
*/
		//alert ( conStr ); 
		
		// File Picker modification for FCK Editor v2.0 - www.fckeditor.net
		// by: Pete Forde <pete@unspace.ca> @ Unspace Interactive
		
		
		var urlobj;
		function BrowseServer(obj)
		{
		urlobj = obj;

		// set div here 	
		
		OpenServerBrowser(
			conStr ,
			screen.width * 0.7,
			screen.height * 0.5 ) ;
		}
		
		function OpenServerBrowser( url, width, height )
		{
			var iLeft = (screen.width - width) / 2 ;
			var iTop = (screen.height - height) / 2 ;
			var sOptions = "toolbar=no,status=no,resizable=yes,dependent=yes" ;
			sOptions += ",width=" + width ;
			sOptions += ",height=" + height ;
			sOptions += ",left=" + iLeft ;
			sOptions += ",top=" + iTop ;
			var oWindow = window.open( url, "BrowseWindow", sOptions ) ;
		}
		
		function SetUrl( url, width, height, alt )
		{
			document.getElementById(urlobj).value = url ;
			oWindow = null;
		}


		

		function getUrlParam( name )
		{
		  //  http://localhost:8080/KMapExtJs/KMap/newMap.html?uid=uid_35.54315670289364_27.151358127593994 
		  // get parameter from url 
		  name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
		  var regexS = "[\\?&]"+name+"=([^&#]*)";
		  var regex = new RegExp( regexS );
		  var results = regex.exec( window.location.href );
		  if( results == null )
		    return "null";
		  else
		    return results[1];
		}


			
		var  sid =  getUrlParam( "sid" );  
		var  act =  getUrlParam( "act" );  
			

		function initAll() {
			document.getElementById( 'sid' ).value = sid ;
			document.getElementById( 'action' ).value = act ;						
		}

			
		</script>
	</head>
	<body  onload="initAll();" >
		

		
		<!-- 
		<p>Basic FCKeditor information:</p>
		<ul>
			<li><FCK:check command="CompatibleBrowser" /></li>
			<li><FCK:check command="GetResources" /></li>
			<li><FCK:check command="FileUpload" /></li>
			<li><FCK:check command="CreateFolder" /></li>
		</ul>


		<hr />
		

		<table cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td>
					Select a language:&nbsp;
				</td>
				<td>
					<select id="cmbLanguages" onchange="ChangeLanguage(this.value);" >
						<option value="-1">none selected</option>
					</select>
				</td>
			</tr>
		</table>
		<br />
 		-->

		<form action=  "<%=HtmlEditorServlet%>"  method="post">

			<input type="hidden" id="sid" name="sid"></input>
			<input type="hidden" id="action" name="action"></input>

			<input type="submit" size="50" value=" Αποθήκευση " />  

			<br/>
			<br/>
			<br/>
		    <input type="text"  id="profileImg"  name="profileImg" size="70" />	<button type="button" onclick="BrowseServer('profileImg');"> διαλεξε εικονα </button>

			
		</form>
	</body>


</html>



