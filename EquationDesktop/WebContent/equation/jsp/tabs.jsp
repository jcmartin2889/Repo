<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
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
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type='text/JavaScript' src="<html:rewrite page='/equation/scripts/maxmin.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<title><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000007")%></title>
		<script type="text/javascript">
			var RTL = <%=equationUser.isLanguageRTL()%>;
			eqInit();			
		</script>
	</head>
		<frameset id="tabFrameset" cols="*,0,0,0,0,0" framespacing="0" border="0" frameborder="no">
			<frame id="consoleFrame" name="consoleFrame" title="ConsoleFrame" src="<html:rewrite page='/equation/jsp/console.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" noresize="noresize">
			
			<%
				if (eqMenu != null)
				{
					%><frame id="referralsFrame" name="referralsFrame" title="ReferralsFrame" src="<html:rewrite page='/equation/jsp/referrals.jsp'/>" marginwidth="0" marginheight="0" scrolling="yes" frameborder="0" noresize="noresize"><%
				}
				if (eqMenu != null)
				{
					%><frame id="messagesFrame" name="messagesFrame" title="MessagesFrame" src="<html:rewrite page='/equation/jsp/messages.jsp'/>" marginwidth="0" marginheight="0" scrolling="yes" frameborder="0" noresize="noresize"><%
				}
				if (eqMenu != null && eqMenu.isAuthSystemStatus())
				{	
					%><frame id="systemFrame" name="systemFrame" title="SystemFrame" src="<html:rewrite page='/equation/jsp/system.jsp'/>" marginwidth="0" marginheight="0" scrolling="yes" frameborder="0" noresize="noresize"><%
				}
				if (eqMenu != null && eqMenu.isAuthJoblog())
				{
					%><frame id="joblogFrame" name="joblogFrame" title="JoblogFrame" src="<html:rewrite page='/equation/jsp/joblog.jsp'/>" marginwidth="0" marginheight="0" scrolling="yes" frameborder="0" noresize="noresize"><%
				}
				if (eqMenu != null && EquationCommonContext.getContext().isLRPProcessing())
				{
					%><frame id="commentFrame" name="commentFrame" title="CommentFrame" src="<html:rewrite page='/equation/jsp/comment.jsp'/>" marginwidth="0" marginheight="0" scrolling="yes" frameborder="0" noresize="noresize"><%
				}
			%>
		</frameset>
</html:html>
