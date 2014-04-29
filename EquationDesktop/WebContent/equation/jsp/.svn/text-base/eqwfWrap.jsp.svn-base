<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<%@page import="java.net.URLEncoder"%>
<html:html>
	<%
	EquationLogin equationLogin = (EquationLogin)request.getSession().getAttribute("equationLogin");
	EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
	%>
	<HEAD>
		<%			
			// user language RTL
			boolean isLanguageRTL = false;
			if (equationUser!=null)
			{
				isLanguageRTL = equationUser.isLanguageRTL();
			}
			
			// session type
			int sessionType = equationLogin.getSessionType();
		%>
	
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<script type='text/JavaScript' src="<html:rewrite page='/equation/scripts/maxmin.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<title>eqwfWrap</title>
		<script type="text/javascript">
			var RTL = <%=isLanguageRTL%>;
			var sessionType = <%=equationLogin.getSessionType()%>;
			eqInit();		
		</script>
	</head>
	<frameset id="eqwfWrapFrameset" rows="0,*,5"  framespacing="0" border="0" frameborder="no">
		<frame id="blankFrame" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" noresize="noresize">
		
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
			String scrnNo = request.getParameter("scrnNo"); 	 
			String screenSetId = request.getParameter("screenSetId");		 
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
						&& sessionId != null && transactionId != null && userId != null && scrnNo != null && screenSetId != null && status !=null && authLevel != null )
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
			// There will be 2 frames.  But only one frame will be active (and visible)
			// at any given time
			// 1. For Equation Desktop functions
			// 2. For Non-Equation Desktop functions

			// The system need to decide which page needs to be loaded
			// 1. Classic desktop (only if WebFacing is available)
			if (sessionType == EquationCommonContext.SESSION_CLASSIC_DESKTOP)
			{
				%>
				<frameset id="screenFrameset" cols="0%,100%"  framespacing="0" border="0" frameborder="no">
				    <frame id="desktopFrame" name="desktopFrame" title="desktopFrame" src="<html:rewrite page='/equation/jsp/blank.jsp'/>" marginwidth="0" marginheight="0" frameborder="0">
				    <frame id="webfacingFrame" name="webfacingFrame" title="webfacingFrame" src="<html:rewrite page='/equation/jsp/eqwfLogin.jsp'/>" marginwidth="0" marginheight="0" frameborder="0">
				</frameset>
			    <%
			}
		
			// 2. Direct Transaction or Full desktop
			else
			{
				%>
				<frameset id="screenFrameset" cols="100%,0%"  framespacing="0" border="0" frameborder="no">
				    <frame id="desktopFrame" name="desktopFrame" title="desktopFrame" src='welcome.jsp<%=link%>' marginwidth="0" marginheight="0" frameborder="0">
				    <frame id="webfacingFrame" name="webfacingFrame" title="webfacingFrame" src="<html:rewrite page='/equation/jsp/blank.jsp'/>" marginwidth="0" marginheight="0" frameborder="0">
				</frameset>
			    <%
			}
		%>
	    
		<frame id="bottomBarFrame" name="bottomBarFrame" title="bottomBarFrame" src="<html:rewrite page='/equation/jsp/bottombar.jsp'/>"  marginwidth="0" marginheight="0" frameborder="0" scrolling="no">
	</frameset>
</html:html>
