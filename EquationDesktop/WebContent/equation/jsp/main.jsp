<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<html:html>
	<%
	EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
	EquationUnit equationUnit = (EquationUnit)request.getSession().getAttribute("equationUnit");
	%>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<script type='text/JavaScript' src="<html:rewrite page='/equation/scripts/maxmin.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<title><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLMAIN")%></title>
		<script type="text/javascript">
			var RTL = <%=equationUser.isLanguageRTL()%>;
			eqInit();
		</script>
		<%
		// The frameset dimesions can be preset especially in the case of global processing (switching unit) 
		final String mainFramesetDimensions = (String)request.getSession().getAttribute("mainFrameset");
		%>
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
	</head>
	<%
		if(equationUser.isLanguageRTL()) 
		{
	%>
		<frameset id="mainFrameset" cols="<%=(mainFramesetDimensions==null)?"80%,20%":mainFramesetDimensions%>" bordercolor="#9d9d9d" framespacing="1" border="1" frameborder="yes" >
			<frame id="inputFrame" name="inputFrame" src="<html:rewrite page='/equation/jsp/input.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" scrolling="no">
			<frame id="navigatorFrame" name="navigatorFrame" title="NavigatorFrame" src="<html:rewrite page='/equation/jsp/navigator.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" scrolling="no">
		</frameset>	
	<%
		}
		else 
		{
			String cols =(mainFramesetDimensions==null)? "20%,80%" : mainFramesetDimensions;
			if(equationUnit.hasGlobalProcessing())
			{
				cols = "28%,72%";
			}
	%>
		<frameset id="mainFrameset" cols="<%=(mainFramesetDimensions==null)?"20%,80%":mainFramesetDimensions%>" bordercolor="#9d9d9d" framespacing="1" border="1" frameborder="yes" >
			<frame id="navigatorFrame" name="navigatorFrame" title="NavigatorFrame" src="<html:rewrite page='/equation/jsp/navigator.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" scrolling="no">
			<frame id="inputFrame" name="inputFrame" src="<html:rewrite page='/equation/jsp/input.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" scrolling="no">
		</frameset>	
	<%
		}
	%>
</html:html>