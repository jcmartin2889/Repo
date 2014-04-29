<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%
	response.setHeader("Cache-Control","no-cache"); 
	response.setHeader("Pragma","no-cache"); 
	response.setDateHeader ("Expires", -1);
%>

<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.ui.context.EquationDesktopContext"%>
<html:html>
	<%
	EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
	EquationUnit equationUnit = (EquationUnit)request.getSession().getAttribute("equationUnit");
	%>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<link rel="SHORTCUT ICON" href="<html:rewrite page='/equation/images/login/misysicon16x16.ico'/>" />
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
    	
    	<title><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000237")%></title>
		<script type="text/javascript">
			var RTL = <%=equationUser.isLanguageRTL()%>;
			eqInit();					
			setCookie('languageId','<%=equationUser.getLanguageId()%>');
			setCookie('helpURL','<%=equationUnit.getHelpURL().replaceAll("\\\\","\\\\\\\\")%>');
			setCookie("cWorkstationID","<%=request.getAttribute(EquationDesktopContext.PARAM_WORKSTATION)%>");
			setCookie("cStyle","<%=request.getAttribute(EqDesktopToolBox.STYLE_PARAMETER)%>");
		</script>
	    <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/language.js'/>"></script>
	</head>
	<frameset id="containerFrameset" title="ContainerFrameset" rows="43,25,*,18,0"  framespacing="0" border="0" frameborder="no" >
		<frame id="headerFrame" name="headerFrame" title="HeaderFrame" src="<html:rewrite page='/equation/jsp/header.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" noresize="noresize" scrolling="no">
		<frame id="toolbarFrame" name="toolbarFrame" title="ToolbarFrame" src="<html:rewrite page='/equation/jsp/toolbar.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" noresize="noresize" scrolling="no">
		<frame id="mainFrame" name="mainFrame" title="MainFrame" src="<html:rewrite page='/equation/jsp/main.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" scrolling="no">
		<frame id="footerFrame" name="footerFrame" title="FooterFrame" src="<html:rewrite page='/equation/jsp/footer.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" noresize="noresize" scrolling="no">
		<frame id="blankFrame" name="blankFrame" title="blankFrame" src="<html:rewrite page='/equation/jsp/blankInCont.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" noresize="noresize" scrolling="no">
	</frameset>
	
	<HEAD>
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="-1">
	</HEAD>
	
</html:html>