<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.misys.equation.common.access.EquationStandardSession"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<html:html>
	<HEAD>
		<TITLE>uxpError.jsp</TITLE>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
	</HEAD>
<%
	EquationLogin equationLogin = (EquationLogin)request.getSession().getAttribute("equationLogin");
	EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
	EquationUnit equationUnit = (EquationUnit)request.getSession().getAttribute("equationUnit");
		    HttpSession httpSession = request.getSession();    
		    EquationStandardSession eqSession = equationUser.getSession();
				System.out.println("equationUser = [" + equationUser + "], httpSession = [" + httpSession + "],eqSession = [" + eqSession + "], equationUser = [" + equationUser + "]");

	%>	
	<body onload="javascript:loginload()">
	<p>httpSession [<%=request.getSession()%>]</p>
	<p>equationUser [<%=equationUser%>]</p>
	<p>equationUnit [<%=equationUnit%>]</p>
	<p>eqSession [<%=eqSession%>]</p>
	<p>HttpSession getCreationTime: [<%=request.getSession().getCreationTime()%>]</p>
	
	
	</body>

		<script type="text/javascript">
		function loginload()
		{
			window.alert("Either the server has been restarted/stopped or the session timed-out.");
		}	
	   </script>
	
</html:html>