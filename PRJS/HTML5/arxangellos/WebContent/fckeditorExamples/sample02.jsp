<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%--
 * FCKeditor - The text editor for Internet - http://www.fckeditor.net
 * Copyright (C) 2004-2010 Frederico Caldeira Knabben
 *
 * == BEGIN LICENSE ==
 *
 * Licensed under the terms of any of the following licenses at your
 * choice:
 *
 *  - GNU General Public License Version 2 or later (the "GPL")
 *    http://www.gnu.org/licenses/gpl.html
 *
 *  - GNU Lesser General Public License Version 2.1 or later (the "LGPL")
 *    http://www.gnu.org/licenses/lgpl.html
 *
 *  - Mozilla Public License Version 1.1 or later (the "MPL")
 *    http://www.mozilla.org/MPL/MPL-1.1.html
 *
 * == END LICENSE ==
 * @version: $Id: sample02.jsp 4785 2009-12-21 20:10:28Z mosipov $
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>FCKeditor - JSP Sample</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="robots" content="noindex, nofollow" />
		<link href="sample.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="fckeditor.gif" 	type="image/x-icon" />
		<script type="text/javascript">
			function FCKeditor_OnComplete(editorInstance) {
				window.status = editorInstance.Description;
			}
		</script>
	</head>
	<body>
		<h1>FCKeditor - JSP - Sample 2</h1>
		This sample displays a normal HTML form with an FCKeditor with full 
		features enabled.<br />
		This sample uses the FCKeditor Taglib: this method is preferred to 
		<a href="sample01.jsp">the API version</a><br />
		All other sample pages will use this method.
		<hr />
		<form action="sampleposteddata.jsp" method="post" target="_blank">
			<FCK:editor instanceName="EditorDefault">
				<jsp:attribute name="value">This is some <strong>sample text
					</strong>. You are using <a href="http://www.fckeditor.net">
					FCKeditor</a>.
				</jsp:attribute>
			</FCK:editor>
			<br />
			<input type="submit" value="Submit" />
		</form>
	</body>
</html>