<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.datastructure.EqDS_DSJOBE"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<html:html>
	<HEAD>
		<TITLE>
		</TITLE>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">

		<% 
		EquationLogin equationLogin = (EquationLogin)request.getSession().getAttribute("equationLogin");
		EquationUnit equationUnit = (EquationUnit)request.getSession().getAttribute("equationUnit");
		EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
		int sessionType = EquationCommonContext.SESSION_CLASSIC_DESKTOP;	
		if (equationLogin != null)
		{
			sessionType = equationLogin.getSessionType();
		}		
		
		String rtlStr = request.getParameter("RTL");
		boolean rtl = false;
		if (rtlStr != null)
		{
			if (rtlStr.equals("true"))
			{
				rtl = true;
			}
		}
			
		if (rtl)
		{
			%><link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetRTL(request)%>'/>" type="text/css"><%
		} 
		else 
		{
			%><link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetMain(request)%>'/>" type="text/css"><%
		}
		%>
		<link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetUserStyle(request)%>'/>" type="text/css">
		
		<script type="text/javascript">
			var inpd = '<%=equationUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.INPD)%>';
			var sessionType = <%=sessionType%>;
			var unitDate = 	'<%=equationUnit.getProcessingDateCYYMMDD()%>';
			var screenDate = '<%=equationUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.ZDATE)%>';						
		</script>
		
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/constant.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/popupAsWindow.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/childWindow.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/windowProperties.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/msf.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/cal.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/frq.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/list.js'/>"></script>
		
		<script type="text/javascript">
			var RTL = <%=rtl%>;
			var RTLOrg = RTL;
			var leftAnchor = <%=request.getParameter("leftAnchor")%>;
			var webFacing = false;
			eqInit();			
		</script>
		
	</HEAD>
	<body id = "body" class="inputBackground popup" 
			oncontextmenu="return false" 
			onload="popupAsWindow_onload(event)"  
			onhelp="return false" 
			onkeyup="return popupAsWindow_bodyKeyUp(event)" 
			onkeydown="return popupAsWindow_bodyKeyDown(event)" 
			onresize="return popupAsWindow_onresize(event)"
			<%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%>  
	>

		<div id = 'anchorTag'>
		</div>
	</body>
	
</html:html>