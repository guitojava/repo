<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<title>FCKeditor - JSP Sample</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="robots" content="noindex, nofollow" />
		
		<link href="sample.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="fckeditor.gif" 	type="image/x-icon" />




		<script type="text/javascript">


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


			

			function FCKeditor_OnComplete(editorInstance) {
				var oCombo = document.getElementById( 'cmbLanguages' );
				for (code in editorInstance.Language.AvailableLanguages) {
					AddComboOption( oCombo,	editorInstance.Language.AvailableLanguages[code] + '(' + code + ')', code);
				}

				oCombo.value = editorInstance.Language.ActiveLanguage.Code;
			}

			function AddComboOption(combo, optionText, optionValue) {
				var oOption = document.createElement("OPTION");
				combo.options.add(oOption);

				oOption.innerHTML = optionText;
				oOption.value = optionValue;
	
				return oOption;
			}

			function ChangeLanguage(languageCode) {
				if (languageCode != "-1")
					window.location.href = window.location.pathname + "?code=" + languageCode;
			}


			
			
		</script>
	</head>
	<body>
		

		<h1>FCKeditor - pamempala </h1>
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

		<form action="editorposteddata.jsp" method="post" target="_blank">
			

			<input type="hidden" id="userSid" name="userSid" > </input>
				


			<FCK:editor instanceName="EditorDefault"  height="700px" width="900" >
				<jsp:attribute name="value">
					
					This is some <strong>sample text</strong>. You are using <a href="http://www.fckeditor.net">FCKeditor</a>.

				</jsp:attribute>
				<jsp:body>

					<FCK:config AutoDetectLanguage="${empty param.code ? true : false}" DefaultLanguage="${empty param.code ? 'en' : param.code}" />
					<!-- <FCK:config FullPage="true"/>-->

				</jsp:body>
			</FCK:editor>
			<br />
			<input type="submit" value="Submit" />
		</form>
	</body>
</html>