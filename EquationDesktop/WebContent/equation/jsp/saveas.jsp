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
		<link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetMain(request)%>'/>" type="text/css">
		<link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetUserStyle(request)%>'/>" type="text/css">
		<script type="text/javaScript" src="<html:rewrite page='/equation/scripts/constant.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/javaScript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/ftt.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/msf.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/dt_key.js'/>"></script>
		<script type="text/javaScript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
		<script type="text/javaScript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/windowProperties.js'/>"></script>
		<script type="text/javaScript" src="<html:rewrite page='/equation/scripts/saveas.js'/>"></script>
		<script type="text/javascript">
			var RTL = window.opener.RTL;
			eqInit();
		</script>
		
		<%
			// default transaction id
			String transactionid = request.getParameter("transactionid");
			if (transactionid==null)
			{
				transactionid="";
			}

			// default transaction id
			String ckey = request.getParameter("ckey");
			if (ckey==null)
			{
				ckey="51"; // default to save
			}
			else if (!ckey.equals("51") && !ckey.equals("53"))
			{
				ckey="51"; // default to save
			}
		%>
	</HEAD>
	<body id = "saveBody" oncontextmenu="return false" 
							class="inputBackground" 
							ondragstart="return false" 
							onload="return save_onload(event)" 
							onhelp='return false' 
							onkeyup='return save_bodyKeyUp(event)' 
							onkeydown='return save_bodyKeyDown(event)' 
							<%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> 
							>
		<form>
		<div id="saveDiv">
			<table id="saveTable" cellSpacing="1" cellPadding="1" border="0">
				<tr class="modalHeader">
					<td>
						<span id="saveTitle" class="labelText"></span>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<table cellSpacing="1" cellPadding="4" border="0">
							<tr class="modalBody">
								<td class="labelText">
									<span id="tranIdLabel"> </span>
								</td>
								<td  class="labelText" width="30%">		
									<input type="text" id="tranId" value='<%=transactionid%>' size="50" maxlength="50" class="wf_ul wf_hi wf_default wf_field wf_UPPERCASE" onkeydown="return tranid_onkeydown(event)"">
								</td>
							</tr>
							<tr class="modalBody">
								<td class="labelText">
								</td>
								<td  class="labelText">
												<input type="button" id="okButton" class="inputButton" value="" onclick="ok_onclick(event);"">
												<input type="button" id="cancelButton" class="inputButton" value="" onclick="cancel_onclick(event);">
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

		<input type="hidden" id="ckey" value="<%=ckey%>">
		</form>
		
		<script type="text/javascript">
			<%
				if (ckey.equals("51"))
				{
					%>
					document.title = window.opener.getLanguageLabel('GBLSAVE');
					<%
				}
				else
				{
					%>
					document.title = window.opener.getLanguageLabel('GBL000090');
					<%
				}
			%>
			document.getElementById('saveTitle').innerHTML = document.title;
			document.getElementById('tranIdLabel').innerHTML = window.opener.getLanguageLabel('GBL000236');
			document.getElementById('okButton').value = window.opener.getLanguageLabel('GBLOK');
			document.getElementById('cancelButton').value = window.opener.getLanguageLabel('GBLCAN');
		</script>
		
		<html:errors bundle="equation"/>
	</body>
	
</html:html>