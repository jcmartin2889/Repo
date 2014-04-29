<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<html:html>
	<head>
		<TITLE>
			<bean:message key="login.title" bundle="equation"/>
		</TITLE>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
	
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
		
		<%
			String link = EqDesktopToolBox.getParameterAsURL(request);
		%>
	</head>	
	<frameset id="inputFrameset" rows="*,0" framespacing="0">
		<frame id="loginFrame" name="loginFrame" title="loginFrame" src="<html:rewrite page='/equation/jsp/login.jsp'/><%=link%>"  marginwidth="0" marginheight="0" frameborder="0" scrolling="no">
		<frame id="blankInLoginFrame" name="blankInLoginFrame" title="blankInLoginFrame" src="<html:rewrite page='/equation/jsp/blankInLogin.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" noresize="noresize" scrolling="no">
	</frameset>
</html:html>
