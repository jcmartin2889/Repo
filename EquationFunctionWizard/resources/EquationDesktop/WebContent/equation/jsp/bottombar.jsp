<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<html:html>
	<%
	EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
	%>
	<head>
		<META http-equiv="content-type" content="charset=UTF-8">		
		<META http-equiv="Content-Style-Type" content="text/css">
		<%				
			// retrieve user's language RTL property
			boolean isLanguageRTL = false;
			if (equationUser != null)
			{
				isLanguageRTL = equationUser.isLanguageRTL();
			}
			if (isLanguageRTL)
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
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/mainBody.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/bottombar.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/dt_key.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/funckeytext.js'/>"></script>
		<script type="text/javascript">
			var RTL = <%=isLanguageRTL%>;
			eqInit();
			var gblCurrentFrame = getFrameCurrent();
		</script>		  
	</head>
	<body 	class="bottombarBody"
			onkeydown='return botbar_bodyKeyDown(event)' 
			onhelp='return false' 
		    onfocus='return botbar_onfocus(event)'
		    class="bottombarframe"
		    <%=EqDesktopToolBox.getHTMLDirAttribute(isLanguageRTL)%> 
			>
		
		<!-- A bar containing the proceed, delete and override buttons -->
		<div id="EqBottomBarDiv" class="bottomBar">
		</div>
		
		<!-- For the footer texts -->
		<div id="footerTexts" class="footertexts" style="width:100%">
			<!-- A bar containing the text versions of the option keys -->
			<div id="optionKeys" class="optionKeys"  style="width:100%">
			</div>
			
			<!-- A bar containing the text versions of the function keys -->
			<div id="functionKeys" class="functionKeys"  style="width:100%">
			</div>
		</div>

		<script type="text/javascript">
		if (RTL) 
		{
			setFieldRTL('footerTexts');
			setFieldRTL('functionKeys');
			setFieldRTL('optionKeys');
		}
		</script>

	</body>
</html:html>