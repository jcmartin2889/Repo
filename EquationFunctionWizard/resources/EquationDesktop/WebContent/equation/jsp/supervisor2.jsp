<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.function.context.EquationFunctionContext"%>
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
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/constant.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/ftt.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/msf.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/dt_key.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/supervisor2.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/windowProperties.js'/>"></script>
		<script type="text/javascript">
			var RTL = window.opener.RTL;
			var webFacing = false;
			eqInit();
		</script>
		
		<%
			// supervisor
			String supervisor = request.getParameter("supervisor");
			if (supervisor==null)
			{
				supervisor="";
			}

			// function key
			String ckey = request.getParameter("ckey");
			if (ckey==null)
			{
				ckey="00";
			}
			// Variable field sizes 
			int userPasswordSize = 12;
			int userMaxLength = 10;
			int passwordMaxLength = 10;
			String inputClass =  "wf_ul wf_hi wf_default wf_field";
			if( EquationFunctionContext.getContext().isEquationAuthentication())
			{
				 inputClass += " wf_UPPERCASE";
			}
			else
			{
				userPasswordSize = 30;
				userMaxLength = 30;
				passwordMaxLength =50;
			}
		%>
		
		<script type="text/javascript">
			var ckey = <%=ckey%>;
		</script>

		
	</HEAD>
	<body id = "passwordBody" oncontextmenu="return false" 
							class="inputBackground" 
							ondragstart="return false" 
							onload="return password_onload(event)" 
							onresize="return password_onresize(event)" 
							onhelp='return false' 
							onfocus='return password_focus(event)' 
							onkeyup='return password_bodyKeyUp(event)' 
							onkeydown='return password_bodyKeyDown(event)' 
							<%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> 
							>
		<form>
		<div id="passwordDiv">
			<table id="passwordTable" class="modalTable">
				<tr class="modalHeaderWarning">
					<td>
						<span id="passwordTitle" class="modalHeaderText"></span>
					</td>
				</tr>
				<tr>
					<td valign="top" class="modalCell" >
						<table class="modalInnerTable" >
							<tr class="modalBody">
								<td class="labelText modalInnerCell">
									<span id="userIdLabel"> </span>
								</td>
								<td  class="labelText modalInnerCell" width="70%">		
									<input type="text" id="userId" value='<%=supervisor%>' size="<%=Integer.toString(userPasswordSize)%>" maxlength="<%=Integer.toString(userMaxLength)%>" class="<%=inputClass%>" readonly>
									<img  id="passwordProgressBar" src="../images/EqSpin.gif" border="0">
								</td>
								<td class="labelText">
								</td>
							</tr>
							<tr class="modalBody">
								<td class="labelText modalInnerCell">
									<span id="passwordLabel"> </span>
								</td>
								<td  class="labelText modalInnerCell" width="70%">		
									<input type="password" id="passwordText" value='' size="<%=Integer.toString(userPasswordSize)%>" maxlength="<%=Integer.toString(passwordMaxLength)%>" class="<%=inputClass%>"  onkeydown="passwordText_onkeydown(event)" onfocus="return field_focus(event)">
								</td>
								<td class="labelText">
								</td>
							</tr>
							<tr class="modalBody" >
								<td class="modalButtons">
								</td>
								<td  class="modalButtons">
									<input type="button" id="okButton" class="modalButton" value="" onclick="ok_onclick(event);">
									<input type="button" id="cancelButton" class="modalButton" value="" onclick="cancel_onclick(event);">
								</td>
								<td class="modalButtons" width="100%" >
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr class="modalFooter">
						<td class=labelText>
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
			document.title = window.opener.getLanguageLabel('GBL000232');
			document.getElementById('passwordTitle').innerHTML = document.title;

			document.getElementById('userIdLabel').innerHTML = window.opener.getLanguageLabel('GBLSUPVS');
			document.getElementById('passwordLabel').innerHTML = window.opener.getLanguageLabel('GBLPWD');
			
			document.getElementById('okButton').value = window.opener.getLanguageLabel('GBLOK');
			document.getElementById('cancelButton').value = window.opener.getLanguageLabel('GBLCAN');
		</script>
			
		<html:errors bundle="equation"/>
	</body>
	
</html:html>