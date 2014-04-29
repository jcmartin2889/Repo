<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
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
		<title><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLNAVI")%></title>
		<script type="text/javascript">
			var RTL = <%=equationUser.isLanguageRTL()%>;
			eqInit();		
		</script>
	</head>
	<frameset id="navigatorFrameset" rows="0,*" frameborder="no" framespacing="0" border="0">
		<frame id="navigatorToolbarFrame" name="navigatorToolbarFrame" title="NavigatorToolbarFrame" src="<html:rewrite page='/equation/jsp/navigatorToolbar.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" noresize="noresize" scrolling="no">
		<frame id="navigatorViewFrame" name="navigatorViewFrame" title="NavigatorViewFrame" src="<html:rewrite page='/equation/jsp/navigatorView.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" scrolling="no">
	</frameset>
</html:html>