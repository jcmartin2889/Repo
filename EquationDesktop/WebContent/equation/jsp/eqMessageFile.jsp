<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
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
	<head>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetMain(request)%>'/>" type="text/css">
		<link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetUserStyle(request)%>'/>" type="text/css">
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<title><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000046")%></title>
		<script type="text/javascript">
			var RTL = <%=equationUser.isLanguageRTL()%>;
			eqInit_StandAlone();
		</script>		  
   </head>

	<body <%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> >

		<%
			String errMsg 	= request.getParameter("errorMsg");
			String msgId 	= request.getParameter("msgId");
			String msgSev   = request.getParameter("msgSev");
			String msg		= request.getParameter("msg");
			String msgHelp 	= request.getParameter("msgHelp");
			String msgType 	= request.getParameter("msgType");
			
			if(msgHelp!=null) 
			{
				if (msgHelp.indexOf("&N") == 0) msgHelp = msgHelp.replaceFirst("&N"," ");
				msgHelp = msgHelp.replaceAll("&N", "<br>");
			}
		%>
		
		<%
			if (errMsg == null)
			{
		%>
				<table>
					<tr valign="top">
						<td class="labelText">
							<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000061")%>
						</td>
						<td class="fieldValue" > 
							<%=msgId%>
						</td>
				
					</tr>
					
					<tr valign="top" >
						<td class="labelText">
							<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000049")%>
						</td>
						<td class="fieldValue" > 
							<%=msgSev%>
						</td>
				
					</tr>
					
					<% if (!msgType.equals(""))
					{
					 %>
					<tr valign="top" >
						<td class="labelText">
							<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000050")%>
						</td>
						<td class="fieldValue" > 
							<%=msgType%>
						</td>
				
					</tr>
					<%
					 }
					 %>
					 
					<tr valign="top">
						<td class="labelText">
							<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLMSG")%>
						</td>
						<td class="fieldValue" >
							<%=msg%>
						</td>
					</tr>
					
					<tr valign="top">
						<td class="labelText">
							&nbsp;
						</td>
						<td class="fieldValue" >
							<%=msgHelp%>
						</td>
				
					</tr>
				</table>
		
		<%
			}
			
			else
			{
		%>
				<table>
					<tr valign="top">
						<td> 
							<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLMSG")%> 
						</td>
						<td class="fieldValue" > 
							<%=msg%>
						</td>
					</tr>
					<tr valign="top">
						<td> 
							<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000053")%>
						</td>
						<td class="fieldValue" >
							<%=errMsg%>
						</td>
				
					</tr>
				</table>
		
		<%
			}
		%>

	</body>
</html:html>