<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Enumeration;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>FCKeditor - Samples - Posted Data</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="robots" content="noindex, nofollow" />
		<link href="sample.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="fckeditor.gif" 	type="image/x-icon" />
	</head>
	<%
		Enumeration<String> params = (Enumeration<String>) request.getParameterNames();
	%>
	<body>
		<h1>FCKeditor - Samples - Posted Data</h1>
		This page lists all data posted by the form.
		<hr />
		<table width="100%" border="1" cellspacing="0">
			<tr style="FONT-WEIGHT: bold; COLOR: #dddddd; BACKGROUND-COLOR: #999999">
				<td nowrap="nowrap">Field Name&nbsp;&nbsp;</td>
				<td>Value</td>
			</tr>
			<%
				String parameter;
				while(params.hasMoreElements()) {
					parameter = params.nextElement();
			%>
			<tr>
				<td nowrap="nowrap"><b><%=parameter%></b></td>
				<td width="100%"><%=request.getParameter(parameter)%></td>
			</tr>
			<%
				}
			%>
		</table>
	</body>
</html>
