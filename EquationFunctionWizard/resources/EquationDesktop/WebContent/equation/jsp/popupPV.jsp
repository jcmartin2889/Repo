<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<html:html>
	<%
	EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
	%>
	<HEAD>
		<TITLE>
		</TITLE>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<% 
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
		
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/constant.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/pvWS.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/dt_sel.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/dt_key.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/popupPVAsWindow.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/windowProperties.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/pvUtils.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/childWindow.js'/>"></script>
		<script type="text/javascript">
			var RTL = <%=rtl%>;
			var RTLOrg = RTL;
			var leftAnchor = <%=request.getParameter("leftAnchor")%>;
			var webFacing = false;
			eqInit();			
		</script>
		
	</HEAD>
	<body id = "body" class="inputBackground popupPV" 
			onload="popupPVAsWindow_onload(event)"  
			onunload="popupPVAsWindow_onunload(event)"  
			onhelp="return false" 
			onkeyup="return popupPVAsWindow_bodyKeyUp(event)" 
			onkeydown="return popupPVAsWindow_bodyKeyDown(event)" 
			onresize="return popupPVAsWindow_onresize(event)"
			<%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%>  
	>
			
		<div id = 'mainPopupDiv'></div>

		<input type="hidden" id="sel_numrec">
		<input type="hidden" id="sel_numcol">

		<script type="text/javascript">
			document.title = getWindowLanguageLabel('GBLPRMPT');
		</script>
	</body>	
</html:html>