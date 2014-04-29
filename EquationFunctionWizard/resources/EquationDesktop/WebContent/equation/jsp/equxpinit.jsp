<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.ui.context.EquationDesktopContext"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.function.runtime.FunctionHandlerTable"%>
<%@page import="com.misys.equation.function.runtime.FunctionHandler"%>
<%@page import="com.misys.equation.function.tools.FunctionRuntimeToolbox"%>
<html:html>
	<%
	boolean sessionExist = false;
	
	// Retrieve the user token
	String ut =  request.getParameter("ut");
	String language = "";
	
	// Token is specified, then put the EQ objects into the session
	if( ut != null)
	{
		sessionExist = EqDesktopToolBox.putBFEQIntoHTTPSession(request,ut, true);
	}
	
	// Setup Webfacing flag - this is needed as the FunctionHandler is setup in EqServicePlugin
	// but the EquationCommonContext.webFacing has not been setup.
	if (sessionExist)
	{		
		FunctionHandlerTable functionHandlerTable = (FunctionHandlerTable)request.getSession().getAttribute(FunctionRuntimeToolbox.NAME);
		FunctionHandler functionHandler = functionHandlerTable.getFunctionHandler();
		functionHandler.getFhd().getFunctionInfo().setWebFacingInstalled(EquationCommonContext.isWebFacing());
		
		EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
		language = equationUser.getLanguageId();
	}	
	
	// Add the HTTP session to the context
	EquationDesktopContext.getContext().addHttpSession(ut, request.getSession());
	
	%>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<script type="text/javascript">
			window.top.equxp.initJspCallback('<%=session.getId()%>', <%=sessionExist%>, '<%=language%>');
		</script>
		<title>equxpinit</title>
	</head>	
	<body>
		<p>EQ iframe page</p>
	</body>
	
</html:html>
