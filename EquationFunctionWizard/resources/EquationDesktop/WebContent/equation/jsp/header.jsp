<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
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
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<title><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLHDR")%></title>
		<script type="text/javascript">
			var RTL = <%=equationUser.isLanguageRTL()%>;
			eqInit();		
		</script>
	</HEAD>
	<body class="header" 
		<%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> 
	>	
		<table class="headBanner" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
			<tr>
				<%
					if (!(equationUnit.getCorpLnkURL()==null || equationUnit.getCorpLnkURL().equals(""))) 
					{
				%>
						<td class="externalLink">
							<a target="_blank" title='<%=equationUnit.getCorpLnkTxt()%>' href='<%=equationUnit.getCorpLnkURL()%>'>
								<img class="corporateImage" src="<html:rewrite page='/equation/images/logo_header.gif'/>"/>
							</a>
						</td>
				<%
					}
					else
					{
				%>
						<td class="externalLink" >
							<a target="_blank" href="http://www.misys.com/">
								<img class="corporateImage" src="<html:rewrite page='/equation/images/logo_header.gif'/>"/>
							</a>
						</td>
				<%
					}
				%>
				<td align="left" valign="middle" class="wf_LTOR">
					<span id="appName" class='appName'>
						<script type="text/javascript">
							document.getElementById('appName').innerHTML=getLanguageLabel("GBL000237");
						</script>
					</span>
				</td>
				<td class="labelText" align="right" width="100%">
				</td>
				<%
					if (!(equationUnit.getExtLnk1URL()==null || equationUnit.getExtLnk1URL().equals(""))) 
					{
				%>
						<td valign="middle" class="wf_LTOR">
							<a target="_blank" href='<%=equationUnit.getExtLnk1URL()%>' title='<%=equationUnit.getExtLnk1URL()%>'>
								<font class="externalLink">
									<%=equationUnit.getExtLnk1Txt()%>
								</font>
							</a>
						</td><td>&nbsp;|&nbsp;</td>
				<%
					}
				%>
				<%
					if (!(equationUnit.getExtLnk2URL()==null || equationUnit.getExtLnk2URL().equals(""))) 
					{
				%>
						<td valign="middle" class="wf_LTOR">
							<a target="_blank" href='<%=equationUnit.getExtLnk2URL()%>' title='<%=equationUnit.getExtLnk2URL()%>'>
								<font class="externalLink" >
									<%=equationUnit.getExtLnk2Txt()%>
								</font>
							</a>
						</td><td>&nbsp;|&nbsp;</td>
				<%
					}
				%>
				<%
					if (!(equationUnit.getExtLnk3URL()==null || equationUnit.getExtLnk3URL().equals(""))) 
					{
				%>
						<td valign="middle" class="wf_LTOR">
							<a target="_blank" href='<%=equationUnit.getExtLnk3URL()%>' title='<%=equationUnit.getExtLnk3URL()%>'>
								<font class="externalLink" >
									<%=equationUnit.getExtLnk3Txt()%>
								</font>
							</a>
						</td><td>&nbsp;|&nbsp;</td>
				<%
					}
				%>
				<%
					if (!(equationUnit.getExtLnk4URL()==null || equationUnit.getExtLnk4URL().equals(""))) 
					{
				%>
						<td valign="middle" class="wf_LTOR">
							<a target="_blank" href='<%=equationUnit.getExtLnk4URL()%>' title='<%=equationUnit.getExtLnk4URL()%>'>
								<font class="externalLink" >
									<%=equationUnit.getExtLnk4Txt()%>
								</font>
							</a>
						</td><td>&nbsp;|&nbsp;</td>
				<%
					}
				%>
				<%
					if (!(equationUnit.getExtLnk5URL()==null || equationUnit.getExtLnk5URL().equals(""))) 
					{
				%>
						<td valign="middle" class="wf_LTOR">
							<a target="_blank" href='<%=equationUnit.getExtLnk5URL()%>' title='<%=equationUnit.getExtLnk5URL()%>'>
								<font class="externalLink" >
									<%=equationUnit.getExtLnk5Txt()%>
								</font>
							</a>
						</td><td>&nbsp;|&nbsp;</td>
				<%
					}
				%>
				<td valign="middle" class="wf_LTOR">
					<a href="javaScript:getFrameToolbar().logoff();">
						<font class="externalLink" >
							<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLLGOF")%>
						</font>
					</a>
				</td>
				<td class="banner">
					<img src="<html:rewrite page='/equation/images/banner.jpg'/>"/>
				</td>
			</tr>
		</table>
		<table class="externalLinkBar">
			<tr>
				<td valign="middle">
				</td>
			</tr>
		</table>		
	</body>
</html:html>