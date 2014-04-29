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
		<title><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000060")%></title>
		<script type="text/javascript">
			var RTL = <%=equationUser.isLanguageRTL()%>;
			eqInit_StandAlone();
		</script>		  
   </head>


	<body <%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> >
		<table>
			<tr valign="top">
				<td class="labelText">
					<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLERROR")%>
				</td>
			</tr>
			<tr valign="top">
				<td>
					<%=request.getParameter("ErrorMsg")%>
				</td>
			</tr>
			
			<tr>
				<td class="labelText">
					<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000055")%>
				</td>
			</tr>
			<tr valign="top">
				<td>
					<%=request.getParameter("spoolName")%>
				</td>
			</tr>
			
			<tr>
				<td class="labelText">
					<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000056")%>
				</td>
			</tr>
			<tr valign="top">
				<td>
					<%=request.getParameter("jobName")%>
				</td>
			</tr>
			
			<tr>
				<td class="labelText">
					<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000057")%>
				</td>
			</tr>
			<tr valign="top">
				<td>
					<%=request.getParameter("jobUser")%>
				</td>
			</tr>
			
			<tr>
				<td class="labelText">
					<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000058")%>
				</td>
			</tr>
			<tr valign="top">
				<td>
					<%=request.getParameter("jobNumber")%>
				</td>
			</tr>
			
			<tr>
				<td class="labelText">
					<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000059")%>
				</td>
			</tr>
			<tr valign="top">
				<td>
					<%=request.getParameter("spoolNumber")%>
				</td>
			</tr>
			
		</table>
		
	</body>
</html:html>