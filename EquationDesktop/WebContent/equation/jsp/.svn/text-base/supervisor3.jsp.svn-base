<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<html:html>
	<%
	EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
	%>
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
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/ftt.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/msf.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/dt_key.js'/>"></script>
		<script type="text/javaScript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
		<script type="text/javaScript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/windowProperties.js'/>"></script>
		<script type="text/javaScript" src="<html:rewrite page='/equation/scripts/supervisor3.js'/>"></script>
		<script type="text/javascript">
			var RTL = window.opener.RTL;
			var webFacing = false;
			eqInit();
		</script>
		
	</HEAD>
	<body id = "reasonBody" oncontextmenu="return false" 
							class="inputBackground" 
							ondragstart="return false" 
							onload="return reason_onload(event)" 
							onresize="return reason_onresize(event)" 
							onhelp='return false' 
							onkeyup='return reason_bodyKeyUp(event)' 
							onkeydown='return reason_bodyKeyDown(event)'
							<%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> 
							>
		<form>
		<div id="reasonDiv" >
			<table id="reasonTable" class="modalTable">
				<tr class="modalHeaderWarning">
					<td>
						<span id="reasonTitle" class="modalHeaderText"></span>
					</td>
				</tr>
				<tr>
					<td valign="top" class="modalCell" >
						<table class="modalInnerTable">
							<tr class="modalBody">
								<td class="labelText modalInnerCell" >
									<span id="reasonLabel"> </span>
								</td>
								<td  class="labelText modalInnerCell">		
									<input type="text" id="reason" value='' size="70" maxlength="70" class="wf_ul wf_hi wf_default wf_field" onkeydown="return reason_onkeydown(event)"">
								</td>
							</tr>
							<tr class="modalBody">
								<td class="modalButtons">
								</td>
								<td  class="modalButtons">
									<input type="button" id="okButton" class="modalButton" value="" onclick="ok_onclick(event);"">
									<input type="button" id="cancelButton" class="modalButton" value="" onclick="cancel_onclick(event);">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr class="modalFooter">
						<td class="labelText">
							<span id="status"> 
								&nbsp; 
								<img class="buttonDisabled" border="0" src="../images/EQError.gif" style="visibility:hidden;">		
							</span>
						</td>
				</tr>
			</table>
		</div>
		</form>
		
		<script type="text/javascript">
			document.title = window.opener.getLanguageLabel('GBL000395');
			document.getElementById('reasonTitle').innerHTML = document.title;
			document.getElementById('reasonLabel').innerHTML = window.opener.getLanguageLabel('GBL000396');
			document.getElementById('okButton').value = window.opener.getLanguageLabel('GBLOK');
			document.getElementById('cancelButton').value = window.opener.getLanguageLabel('GBLCAN');
		</script>
		
		<html:errors bundle="equation"/>
	</body>
	
</html:html>