<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.misys.equation.common.access.EquationStandardSession"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.misys.equation.function.runtime.FunctionHandler"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.function.runtime.FunctionHandlerTable"%>
<%@page import="com.misys.equation.function.tools.FunctionRuntimeToolbox"%>
<%@page import="com.misys.equation.function.runtime.FunctionHandlerData"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.function.runtime.FunctionMessage"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.misys.equation.common.utilities.Toolbox"%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.struts.*" %>
<%@ page import="org.apache.struts.util.*" %>
<%@ page import="org.apache.struts.action.*" %>
<%@page import="com.misys.equation.common.utilities.EqTimingTest"%>
<html:html>
	<%
	EquationLogin equationLogin = (EquationLogin)request.getSession().getAttribute("equationLogin");
	EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
	EquationUnit equationUnit = (EquationUnit)request.getSession().getAttribute("equationUnit");
	%>

<head>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetMain(request)%>'/>" type="text/css">
		<link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetUserStyle(request)%>'/>" type="text/css">
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/constant.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/buttonbar.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/bottombar.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/mainBody.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/pgminfo.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/dt_event.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/dt_key.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/msf.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/tabbar.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/lrp.js'/>"></script>


<title>Request iSeries Password</title>
</head>

<body>
	<script>
				// Stop the spinner graphic that would be running after the user selected an option
				try
				{
					gblTabFrame = getFrameTab();
					gblConsoleFrame = gblTabFrame.frames['consoleFrame'];
					gblConsoleFrame.tabHidePopup();

					// hide busy icons
					endLoadingTransaction();
					hideSpinnerButton();

					gblButtonbarFrame = getFrameButtonBar();
					gblButtonbarFrame.endErrorMonitor();
				}
				catch (e)
				{
				}


				// Called when form is submitted
				function getPassword_submitForm()
				{
					// Start the spinner graphic
					startLoadingTransaction();

				}

				
	</script>

	  <html:form focus="password" action="/getpwd" styleId="getpwd" onsubmit="getPassword_submitForm()">

		<p align="left" class="labelText">
			<bean:message key="cas.iseries.password.description1" bundle="equation"/>
		</p>
		
		<p align="left" class="labelText">
			<bean:message key="cas.iseries.password.description2" bundle="equation"/>
		</p>
		

		<table border="0" cellspacing="1" cellpadding="4" class="wf_default wf_field">
			
			<tr valign="middle" align="center">		
				<td valign="top">													
					<table width="300" border="0" cellspacing="3" cellpadding="3" class="wf_default wf_field">
						<tr>
							<td align="left" class="labelText">
								<bean:message key="login.password.label" bundle="equation"/>:
							</td>
							<td align="left" class="labelText">
								<html:password property="password" styleId="passWord" size="10" maxlength="128" value='' /></td>

						</tr>
						<tr>	
							<td align="left">
							</td>
										
							<td align="left" class="labelText">
								<html:submit styleClass="inputButton">
									<bean:message key="cas.iseries.password.submit" bundle="equation"/>
								</html:submit>
								<html:cancel styleClass="inputButton">
									<bean:message key="cas.iseries.password.cancel" bundle="equation"/>
								</html:cancel>
							</td>							
							
						</tr>	
						<tr>	
							<td align="left">
								</td>			
							<td align="left">

							</td>							
						</tr>	
						<tr>
							<td align="left"><html:hidden property="systemName" styleId="systemName" value='${param.systemName}'/></td>
							<td align="left"><html:hidden property="user" styleId="userId" value='${param.user}'/></td>
							<td align="left"><html:hidden property="unit" styleId="unit" value='${param.unit}'/></td>
							<td align="left"><html:hidden property="option" styleId="optionId" value='${param.option}'/></td>
							<td align="left"><html:hidden property="mode" styleId="mode" value='${param.mode}'/></td>
							<td align="left"><html:hidden property="context" styleId="context" value='${param.context}'/></td>
							<td align="left"><html:hidden property="mainFhName" styleId="mainFhName" value='${param.mainFhName}'/></td>
						</tr>					
						
					</table>
				</td>
			</tr>
		</table>

	  </html:form>
	  
	   <!--  this id is required by setMsgCtl() -->
		<div id="errorHeader">
		</div>
	

		<%
		// Retrieve an error message and pass to setMsgCtl() to display it in the problems tab
		String errmsg = (String)request.getAttribute("errmsg");
		if (errmsg == null) errmsg = "";
		if (errmsg.equals(""))
		{
				%>
				<script type="text/javascript">
				setMsgCtl(null, null);
	  			</script>
				<%
		}
		else
		{
			%>
			<script type="text/javascript">
			setMsgCtl(<%=errmsg%>,"errorHeader");
			alertNewMsgCtl();
  			</script>
			<%
		}
		%>
		

</body>
</html:html>