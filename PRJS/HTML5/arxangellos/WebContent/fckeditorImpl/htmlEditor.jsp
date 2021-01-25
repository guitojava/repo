<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?xml version="1.0" encoding="UTF-8" ?><%@ 
page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%><%@ 
taglib uri="http://java.fckeditor.net" prefix="FCK"%><%@page 
	import="com.memopage.dto.User"%><%@page
	import="org.apache.log4j.Logger"%><%@page
	import="com.memopage.Constants"%><%@page
	import="com.memopage.service.impl.MemopageServiceImpl"%><%@page 
	import="com.memopage.dto.Memopage"%><%@page 
	import="org.springframework.dao.EmptyResultDataAccessException"%><html xmlns="http://www.w3.org/1999/xhtml"><%
	
	Logger logger = Logger.getLogger("HtmlEditor.jsp");
	
	String content = " <h1>Warning !!! Not logged in !!!  </h1> ";
	
	
	String    templateDir = "/"+Constants.rootName+"/fckeditorImpl/templates_config.xml" ; 
	String    HtmlEditorServlet =  "/"+Constants.rootName+"/HtmlEditorServlet";

	MemopageServiceImpl memopageService = new MemopageServiceImpl();
	User userObj = (User) request.getSession().getAttribute("user");
	
	String act=request.getParameter("act")==null?Constants.NULL:request.getParameter("act");
	String sid=request.getParameter("sid")==null?Constants.NULL:request.getParameter("sid");
	
	int sidInt;
	
	try {
	
		sidInt = Integer.parseInt(sid); 
	
	
		logger.debug("  act=" + act);
		logger.debug("  sid=" + sid);
	
		if (userObj != null && act != null ) {
			
			
			if ( act.equals("saveMemoPage") ) { 
			
				// set  template directory  here based on action 
				templateDir = "/"+Constants.rootName+"/fckeditorImpl/templates_config.xml" ; 
					
				if (  sidInt > 0 ){
			
					Memopage mpage = memopageService.getMemopage(   sidInt ) ; 
					
					if (null!= mpage && userObj.getSid() == mpage.getUser().getSid() ){ // check that its his 
						content = mpage .getPageContent() ;
					} else {
						content ="<h1>Warning !!! Not logged in !!!  </h1>"; 
						logger.error( "memopage with sid "+ sidInt  + "not found"  ); 
					}
			
				} else if (sidInt == -1 ) {
					// new page 
					content = "<br><br> ... Type here the content of your new memopage and press save button ";
				}
					
					
				
			} // action
			
			
			
		} // user act
		
	
	
} catch (Exception ex) {
		
		
}
	
	
	
	
%>

<head>

<title></title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="robots" content="noindex, nofollow" />

<link href="editor.css" rel="stylesheet" type="text/css" />


<script type="text/javascript">






	function getUrlParam(name) {
		// get parameter from url 
		name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
		var regexS = "[\\?&]" + name + "=([^&#]*)";
		var regex = new RegExp(regexS);
		var results = regex.exec(window.location.href);
		if (results == null)
			return "null";
		else
			return results[1];
	}

	var sid = getUrlParam("sid");
	var act = getUrlParam("act");

	function initAll() {
		// set the hidden values 
		document.getElementById('sid').value = sid; 
		document.getElementById('action').value = act;
	}


	function FCKeditor_OnComplete( editorInstance )
	{
	 	//    alert( editorInstance.Name ) ;
	 	
//	 	alert ( "loaded OK" )
	 	
	 	document.getElementById("loading").value="";
	 	
	    editorInstance.Commands.GetCommand('FitWindow').Execute(); // this makes it full screen
	}
	
</script>

</head>
<body onload="initAll();">



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


<div id="loading" >
	
	<h1> please wait loading ..... </h1>

</div>

<form action=  "<%=HtmlEditorServlet%>"  method="post">


  <input type="hidden" id="sid" name="sid"></input> 
  <input type="hidden" id="action" name="action"></input> 
  
  
  <FCK:editor instanceName="EditorDefault" height="100%" width="100%">
  
				<jsp:attribute name="value">
					
							<%=content%>
					
				</jsp:attribute>
	
				<jsp:body>
					
					<FCK:config Plugins.Add="tablecommands" />
					<FCK:config Plugins.Add="dragresizetable" />
					
					<FCK:config AutoDetectLanguage="true" />
					
					<FCK:config DefaultLanguage="el" />
					
					<FCK:config ContentLangDirection="ltr" />
					
					<FCK:config EnableXHTML="true" />	
					<FCK:config EnableSourceXHTML="true" />	
					
					
					<FCK:config ProcessHTMLEntities="true" />
					<FCK:config IncludeLatinEntities="true" />	
					<FCK:config IncludeGreekEntities="false" />	
					
					<FCK:config FillEmptyBlocks="false" />	
					
					<FCK:config FormatSource="true" />	
					<FCK:config FormatOutput="false" />	
		
					 
					<FCK:config FullPage="true" />
					
					<FCK:config TemplatesXmlPath = "<%=templateDir%>"  /> 
					<FCK:config TemplateReplaceAll = "false"   /> 
						
						
				</jsp:body>

</FCK:editor>
</form>



</body>


</html>



