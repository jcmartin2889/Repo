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
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/tabbar.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/buttonbar.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/comment.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/windowProperties.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/constant.js'/>"></script>
		<title><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLCOMNT")%></title>
		<script type="text/javascript">
			var RTL = <%=equationUser.isLanguageRTL()%>;
			eqInit();
		</script>
	</head>
	<BODY class="commentBody" <%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> >

		<div id='commentAddDiv' style="visibility:hidden" class="commentAddDiv">
			<span class="labelText"><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL900078")%> </span>
			<input id="commentField" maxlength="70" size="70" type="text" class="wf_field"> </input>
		</div>
	
		<div id='commentDiv'>
		</div>
		<script type="text/javascript">
			if (RTL) setFieldRTL('commentDiv');
		</script>
	</BODY>
</html:html>
