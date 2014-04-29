<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<%@page import="com.misys.equation.common.utilities.EqTimingTest"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.function.runtime.FunctionInfo"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.misys.equation.ui.context.EquationDesktopContext"%>
<%@page import="com.misys.equation.common.utilities.Toolbox"%>
<html:html>
	<%
	EquationUser equationUser = (EquationUser) request.getSession().getAttribute("equationUser");
	EquationUnit equationUnit = (EquationUnit) request.getSession().getAttribute("equationUnit");
	EquationLogin equationLogin = (EquationLogin)request.getSession().getAttribute("equationLogin");

	// No Equation user, so something wrong with the server, display login page
	if (equationUser == null)
	{
		%>
			<jsp:forward page="../jsp/relogin.jsp">
			<jsp:param name="x" value=''/>
			</jsp:forward>
		<%
	}
	
	// Initialise style sheet if has not been initialised
	if (!EqDesktopToolBox.isStyleSheetInitialise(request))
	{
		EqDesktopToolBox.initStyleSheetMain(request, getServletContext(), equationLogin.getSystem().toLowerCase(), 
				equationLogin.getUnitId().toLowerCase(), equationLogin.getUserId().toLowerCase());
	}

	%>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<link rel="SHORTCUT ICON" href="<html:rewrite page='/equation/images/login/misysicon16x16.ico'/>" />

		<%
			// Parameters
			String mode = request.getParameter("mode");
			String unit = request.getParameter("unit");
			String optionId = request.getParameter("optionId");
			String context = request.getParameter("context");
			String name = request.getParameter("name");
			String control = request.getParameter("control");
			String sessionId = request.getParameter("sessionId");
			String transactionId = request.getParameter("transactionId");
			String userId = request.getParameter("userId"); 
			int scrnNo = Toolbox.parseInt(request.getParameter("scrnNo"),0);
			int screenSetId = Toolbox.parseInt(request.getParameter("screenSetId"),0);
			String status = request.getParameter("status");		 
			String authLevel = request.getParameter("authLevel");				 
			String taskId = request.getParameter("taskId");
			String taskType = request.getParameter("taskType");			
	
			String link = "";
			if (control == null && mode != null && unit != null && optionId != null && context != null && name != null)
			{
				link = "?mode=" + mode + 
						"&unit=" + unit.toUpperCase() + 
						"&optionId=" + optionId.toUpperCase() + 
						"&context=" + URLEncoder.encode(context.toUpperCase(), "UTF-8") +
						"&name=" + name;
			}
			if (control != null && mode != null && unit != null && optionId != null && context != null && name != null
			&& sessionId != null && transactionId != null && userId != null && status !=null && authLevel != null )
			{
				link = "?mode=" + mode + 
					"&unit=" + unit.toUpperCase() + 
					"&optionId=" + optionId.toUpperCase() + 
					"&context=" + context.toUpperCase() +
					"&name=" + name +
					"&control=" + control +
					"&sessionId=" + sessionId +
					"&transactionId=" + transactionId +
					"&userId=" + userId +
					"&scrnNo=" + scrnNo +
					"&screenSetId=" + screenSetId +
					"&status=" + status +
					"&authLevel=" + authLevel;
			}
			if( "4".equals(control))
			{
				link = "?mode=" + mode + 
					"&name=" + name + 
					"&optionId=" + optionId.toUpperCase() + 
					"&control=" + control +
					"&taskId=" + taskId +
					"&taskType=" + taskType;
			}
			int btnBarRows = equationLogin.chkUXPUserInterface() ? 25 : 26;
		%>
		
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		
		<% if (equationLogin.getSessionType()== EquationCommonContext.SESSION_DIRECT_TRANS_DESKTOP)
		{
			%>
			<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/language.js'/>"></script>
			<%
		}
		%>

		<script type="text/javascript">
			var RTL = <%=equationUser.isLanguageRTL()%>;
			eqInit();
			setCookie('languageId','<%=equationUser.getLanguageId()%>');
			setCookie('helpURL','<%=equationUnit.getHelpURL().replaceAll("\\\\","\\\\\\\\")%>');
			setCookie('optionID', '<%=equationLogin.getOptId()%>');
		</script>
		<title><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000237")%></title>
	</head>	
	<frameset id="inputFrameset" rows="<%=btnBarRows%>,*"  framespacing="0" border="0" frameborder="no">
		<frame id="buttonBarFrame" name="buttonBarFrame" title="buttonBarFrame" src="<html:rewrite page='/equation/jsp/buttonbar.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" noresize="noresize" scrolling="no">
		<%if(equationLogin.chkUXPUserInterface()) { %>
		<frameset id="txnFrameset" rows="*,0,0" bordercolor="#ff8080" framespacing="0" border="0" frameborder="no">
			<frame id="screenFrame" name="screenFrame" title="screenFrame" src='<html:rewrite page='/equation/jsp/eqwfWrap.jsp'/><%=link%>'  marginwidth="0" marginheight="0" frameborder="0">
			<frame id="grabFrame" name="grabFrame" title="grabFrame" src="<html:rewrite page='/equation/jsp/splitter.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" noresize="noresize">			 
			<frame id="infoFrame" name="infoFrame" title="infoFrame" src="<html:rewrite page='/equation/jsp/info.jsp'/>"  marginwidth="0" marginheight="0" frameborder="0" scrolling="no" noresize="noresize" >
		</frameset>
		<% } else { %>
		<frameset rows="*,100" bordercolor="buttonface" framespacing="5" border="5" frameborder="yes">
			<frame id="screenFrame" name="screenFrame" title="screenFrame" src='<html:rewrite page='/equation/jsp/eqwfWrap.jsp'/><%=link%>'  marginwidth="0" marginheight="0" frameborder="0">
			<frame id="infoFrame" name="infoFrame" title="infoFrame" src="<html:rewrite page='/equation/jsp/info.jsp'/>"  marginwidth="0" marginheight="0" frameborder="0" scrolling="no">
		</frameset>
		<% } %>
	</frameset>
</html:html>
