<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.function.context.EquationFunctionContext"%>
<%@page import="com.misys.equation.function.runtime.FunctionMsgManager"%>
<%@page import="com.misys.equation.function.runtime.FunctionHandlerTable"%>
<%@page import="com.misys.equation.function.runtime.FunctionHandler"%>
<%@page import="com.misys.equation.function.runtime.FunctionHandlerData"%>
<%@page import="com.misys.equation.function.runtime.FunctionMessages"%>
<%@page import="com.misys.equation.function.tools.FunctionRuntimeToolbox"%>
<%@page import="com.misys.equation.function.tools.MakerCheckerUtility"%><html:html>
	<HEAD>
		<TITLE>
		</TITLE>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
		<link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetMain(request)%>'/>" type="text/css">
		<link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetUserStyle(request)%>'/>" type="text/css">
		<script type="text/javaScript" src="<html:rewrite page='/equation/scripts/constant.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/javaScript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javaScript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
		<script type="text/javaScript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/ftt.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/msf.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/pvWS.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/dt_sel.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/dt_key.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/dt_function.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/supervisor.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/pvUtils.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/windowProperties.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/childWindow.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/popup.js'/>"></script>
		<script type="text/javascript">
			var RTL = window.opener.RTL;
			var webFacing = false;
			var leftAnchor = 0;
			eqInit();
			makerchecker = true;
			radioButtonSelectionOnly = true;
			document.title = window.opener.getLanguageLabel('GBL000397');
			tranType = '<%=request.getParameter("tranType")%>';

			function supervisor4_onload()
			{
				// get checker list
				getCheckerList();
			}
				
			var isEquationAuthentication = '<%=EquationFunctionContext.getContext().isEquationAuthentication()%>';
		</script>
		<%
            // retrieve the name of the Function Handler to be processed
            String name = request.getParameter("name");
            if (name == null)
            {
                name = (String) request.getSession().getAttribute(FunctionRuntimeToolbox.UNIQUE_NAME);
            }
            if (name == null)
            {
                name = "";
            }
            EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
            FunctionHandlerTable functionHandlerTable = (FunctionHandlerTable)request.getSession().getAttribute(FunctionRuntimeToolbox.NAME);
            FunctionHandler functionHandler = functionHandlerTable.getFunctionHandler(name);
            FunctionHandlerData fhd = functionHandler.getFhd();
            if (MakerCheckerUtility.isMakerCheckerRequired(equationUser.getSession(), fhd.getScreenSetHandler().rtvScreenSetMain().getOptionId()))
            {
                 FunctionMsgManager functionMsgManager = fhd.getFunctionMsgManager();
                 FunctionMessages functionMessages = fhd.getFunctionMsgManager().getFunctionMessages();
                 functionMsgManager.generateMessage(equationUser.getSession(), functionMessages, fhd.getScreenSetHandler().rtvScrnSetCurrent().getId(),
                 100, "", 0, null, "KSM7613", "", "", FunctionMessages.MSG_NONE);
            }
		%>
	</HEAD>
	<body id = "passwordBody" class="inputBackground" 
		onload="supervisor4_onload()"
		onhelp="return false" 
		onkeyup="return password_bodyKeyUp(event)" 
		onkeydown="return password_bodyKeyDown(event)"
		<%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> 
		>
		<form>
			<div id = 'mainPopupDiv'></div>
			<input type="hidden" id="userId" value="">					
			<input type="hidden" id="$OCEMH" value="<%=fhd.getFunctionMsgManager().getFunctionMessages().rtvMessageIds()%>">
			<input type="hidden" id="sel_numrec">
			<input type="hidden" id="sel_numcol">
		</form>
		<html:errors bundle="equation"/>
	</body>
	
</html:html>