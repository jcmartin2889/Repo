<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<html:html>
	<%
	EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
	EquationUnit equationUnit = (EquationUnit)request.getSession().getAttribute("equationUnit");
	EquationLogin equationLogin = (EquationLogin)request.getSession().getAttribute("equationLogin");
	%>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<script type='text/JavaScript' src="<html:rewrite page='/equation/scripts/maxmin.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<title><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000016")%></title>
		<script type="text/javascript">
			var RTL = <%=equationUser.isLanguageRTL()%>;
			eqInit();		
		</script>
	</head>
	<%if(equationLogin.chkUXPUserInterface()) { %>
	<frameset id="uxpTabFrameset" cols="0,0,*" framespacing="0" border="0" frameborder="no" >
		<frame id="uxpCommentsFrame" name="uxpCommentsFrame" title="uxpCommentsFrame" src="<html:rewrite page='/equation/jsp/uxpComments.jsp'/>" marginwidth="0" marginheight="0" frameborder="0">		
		<frame id="infoSplitter" name="infoSplitter" title="infoSplitter" src="<html:rewrite page='/equation/jsp/splitterv.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" scrolling="no">
	<% } %>
		<frameset id="infoFrameset" rows="<%=equationLogin.chkUXPUserInterface() ? 25 : 26%>,*" framespacing="0" border="0" frameborder="no">
			<frame id="tabBarFrame" name="tabBarFrame" title="tabBarFrame" src="<html:rewrite page='/equation/jsp/tabBar.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" noresize="noresize" scrolling="no">
			<frame id="tabsFrame" name="tabsFrame" title="tabsFrame" src="<html:rewrite page='/equation/jsp/tabs.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" noresize="noresize">
		</frameset>
	<%if(equationLogin.chkUXPUserInterface()) { %>	
	</frameset>
	<% } %>	
</html:html>