<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<html:html>
	<%
	EquationLogin equationLogin = (EquationLogin)request.getSession().getAttribute("equationLogin");
	EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
	EquationUnit equationUnit = (EquationUnit)request.getSession().getAttribute("equationUnit");
	%>
	<head>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EDGE" />

		<% 
			if (equationUser.isLanguageRTL()) 
			{ 
				%><link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetRTL(request)%>'/>" type="text/css"><%
			} 
			else 
			{
				%><link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetMain(request)%>'/>" type="text/css"><%
			}
		%>
		<link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetUserStyle(request)%>'/>" type="text/css">
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/tabbar.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/buttonbar.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/msgq.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/windowProperties.js'/>"></script>
		<title><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLMSGS")%></title>
		<script type="text/javascript">
			var RTL = <%=equationUser.isLanguageRTL()%>;
			var webFacUser = '<%=equationLogin.getUserId()%>';
			var frameName = 'messages';
			eqInit();		
		</script>
	</head>
	<BODY class="messagesBody" 
		  <%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> 
	>
		<div id='messagesDiv'>
		</div>
		<script type="text/javascript">
		if (RTL) setFieldRTL('messagesDiv');
		</script>
	</BODY>
</html:html>