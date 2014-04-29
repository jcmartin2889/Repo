<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.ui.helpers.EqNavigator"%>
<%@page import="com.misys.equation.ui.beans.EqMenu"%>

<html:html>
	<%
	EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
	EquationUnit equationUnit = (EquationUnit)request.getSession().getAttribute("equationUnit");
	EqNavigator eqNavigator = (EqNavigator)request.getSession().getAttribute("eqNavigator");
	EqMenu eqMenu = null;
	if (eqNavigator != null)
	{
		eqMenu = eqNavigator.getEqMenu();
	}
	
	%>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
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
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/buttonbar.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/tabbar.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
		<title><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000007")%></title> 
		<script type="text/javascript">
			var RTL = <%=equationUser.isLanguageRTL()%>;
			eqInit();		

			function tabBar_onload()
			{
				// default to console tab
				toggleBottomFrames('console');
			}

		</script>
	</head>
	<body onload="tabBar_onload()"
	      <%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> 
	>
	<%	
		if (eqMenu == null) // UXP
		{
			%>
		<div class="ux_problems_title"><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLPROBS")%></div>
	<%
	} else {
	%>
		<table class="tabbar" cellpadding="0" cellspacing="0" height="26">
				<tr id="infoTabBar">
					<td id="consoleTabImage" class="tabBarImageSelected" onclick="javaScript:toggleBottomFrames('console');"><img src="<html:rewrite page='/equation/images/problems.gif'/>" border="0"></td>
					<td id="consoleTabText" class="tabBarTextSelected" onclick="javaScript:toggleBottomFrames('console');"><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLPROBS")%></td>
					
			 		<%	
						if (eqMenu != null)
						{
							%><td id="referralsTabImage" class="tabBarImage" onclick="javaScript:toggleBottomFrames('referrals');"><img src="<html:rewrite page='/equation/images/referrals.gif'/>" border="0"></td>
							<td id="referralsTabText" class="tabBarText" onclick="javaScript:toggleBottomFrames('referrals');"><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLREFS")%></td><%
			 			}	

						if (eqMenu != null)
						{
							%><td id="messagesTabImage" class="tabBarImage" onclick="javaScript:toggleBottomFrames('messages');"><img src="<html:rewrite page='/equation/images/messages.gif'/>" border="0"></td>
							<td id="messagesTabText" class="tabBarText" onclick="javaScript:toggleBottomFrames('messages');"><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLMSGS")%></td><%
						}
						if (eqMenu != null && eqMenu.isAuthSystemStatus())
						{	
							%><td id="systemTabImage" class="tabBarImage" onclick="javaScript:toggleBottomFrames('system');"><img src="<html:rewrite page='/equation/images/kapstatus.gif'/>" border="0"></td>
							  <td id="systemTabText" class="tabBarText" onclick="javaScript:toggleBottomFrames('system');"><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000007")%></td><%
						}	
						if (eqMenu != null && eqMenu.isAuthJoblog())
						{
							%><td id="joblogTabImage" class="tabBarImage" onclick="javaScript:toggleBottomFrames('joblog');"><img src="<html:rewrite page='/equation/images/joblog.gif'/>" border="0"></td>
							  <td id="joblogTabText" class="tabBarText" onclick="javaScript:toggleBottomFrames('joblog');"><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLJOBLOG")%></td><%
						}
						if (eqMenu != null || EquationCommonContext.getContext().isLRPProcessing())
						{
							%><td id="commentTabImage" class="tabBarImage" onclick="javaScript:toggleBottomFrames('comment');"><img src="<html:rewrite page='/equation/images/comment.gif'/>" border="0"></td>
							  <td id="commentTabText" class="tabBarText" onclick="javaScript:toggleBottomFrames('comment');"><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLCOMNT")%></td><%
						}
					%>
					<td class="labelText" align="right" width="100%"></td>
				</tr>
		</table> 
	<% }  %>
	</body>	
</html:html>