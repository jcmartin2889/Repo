<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.utilities.Toolbox"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.function.context.EquationFunctionContext"%>
<%@page import="com.misys.equation.function.runtime.FunctionInfo"%>
<html:html>
	<%
	EquationLogin equationLogin = (EquationLogin)request.getSession().getAttribute("equationLogin");
	EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
	EquationUnit equationUnit = (EquationUnit)request.getSession().getAttribute("equationUnit");
	String sessionId = equationUser.getSession().getSessionIdentifier();
	FunctionInfo functionInfo = EquationFunctionContext.getContext().getFunctionHandler(sessionId).getFhd().getFunctionInfo();
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
		<title><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000007")%></title> 
		<script type="text/javascript">
			var RTL = <%=equationUser.isLanguageRTL()%>;
			eqInit();
		</script>
	</head>
	<body <%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> >
		<table class="footer" cellpadding="0" cellspacing="0" height="100%"> 
				<tr id="statusBar">
					<td class="statusBarText" align="center" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLSYS")%>"><label id ="systemID"><%=equationLogin.getSystem()%></label></td>
					<td class="statusBarSpacer" align="center"></td>
					<td class="statusBarText" align="center" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLUNIT")%>"><label id ="unitID"><%=equationLogin.getUnitId()%></label></td>
					<td class="statusBarSpacer" align="center"></td>
					<% if(	 EquationFunctionContext.getContext().isEquationAuthentication()) {%>
					<td class="statusBarText" align="center" title="<%=equationUser.getUserName()%>"><label id ="userID"><%=equationLogin.getUserId()%></label></td>
					<% } else { %>
					<td class="statusBarText" align="center" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000401")%>"><label id ="userID"><%=EquationFunctionContext.getContext().getLoginUserBySessionId(sessionId)%></label></td>
					<td class="statusBarSpacer" align="center"></td>
					<td class="statusBarText" align="center" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000402")%>"><label id ="eqUser"><%=Toolbox.trim(equationLogin.getUserId(), 4)%></label></td>
					<td class="statusBarSpacer" align="center"></td>
					<td class="statusBarText" align="center" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000403")%>"><label id ="IBMiUser"><%=equationLogin.getUserId()%></label></td>
					<% } %>
					<td class="statusBarSpacer" align="center"></td>
					<td class="statusBarText" align="center" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLWSID")%>"><label id ="workstationID"><%=functionInfo.getWorkStationId()%></label></td>
					<td class="statusBarSpacer" align="center"></td>
					<td class="statusBarText" align="center" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000074")%>"><label id ="inputbranchID"><%=equationUser.getInputBranch()%></label></td>
					<td class="statusBarSpacer" align="center"></td>
					<td class="statusBarText" align="center" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLLANG")%>"><label id ="languageID"><%=equationUser.getLanguageId()%></label></td>
					<td class="statusBarSpacer" align="center"></td>
					<td class="statusBarText" align="center" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLJOB")%>" id="jobid"></td>
					<td class="statusBarSpacer" align="center"></td>
					<td class="statusBarText" align="center" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLPHS")%>"><label id ="phaseID"><%=equationUnit.getPhase()%></label></td>
					<td class="statusBarSpacer" align="center"></td>
					<td class="statusBarText" align="center" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000075")%>"><label id ="unitinformationID"><%=equationUnit.getUnitInformation()%></label></td>
					<td class="statusBarSpacer" align="center"></td>
					<td class="statusBarText" align="center" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000076")%>"><label id ="descriptionID"><%=equationUnit.getDescription()%></label></td>
					<td class="statusBarSpacer" align="center"></td>
					<td class="statusBarText" align="center" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000077")%>"><label id ="processingdateID"><%=equationUnit.getProcessingDate()%></label></td>
					<td class="statusBarSpacer" align="center"></td>
					<td class="statusBarText" align="center" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLVERSN")%>"><label id ="pluginversionID"><%=EquationCommonContext.PLUGIN_VERSION%></label></td>
					<td class="statusBarSpacer" align="center"></td>
					<td class="labelText" align="right" width="100%"></td>
					<td class="statusBarText"><div class="footerLogo">&nbsp;</div></td>
				</tr>
		</table>
		<script type="text/javascript">
			/* Set the height of the footer */
			document.getElementById('statusBar').height = getFrameFooter().frameElement.height - 2;
		</script>
	</body>
</html:html>