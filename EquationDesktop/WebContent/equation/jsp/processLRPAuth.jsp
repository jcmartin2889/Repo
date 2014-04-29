<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.function.context.EquationFunctionContext"%>
<%@page import="com.misys.equation.function.runtime.FunctionKeys"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
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
		<script type="text/javaScript" src="<html:rewrite page='/equation/scripts/lrp.js'/>"></script>
		<script type="text/javaScript" src="<html:rewrite page='/equation/scripts/processLRPAuth.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/pvWS.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/childWindow.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/popup.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/dt_function.js'/>"></script>
		<script type="text/javascript">
			var RTL = window.opener.RTL;
			var webFacing = false;
			eqInit();
		</script>
		<%
			// function key
			String ckey = request.getParameter("ckey");
			if (ckey==null)
			{
				ckey="00";
			}
			
			// Variable field sizes 
			int userSize = 5;
			int userMaxLength = 4;
			String inputClass =  "wf_ul wf_hi wf_default wf_field";

			// OCR32R standard meta-data
			String ocr32ReturnFieldName = "'$OCBBN','$OCUSID','$OCRBBN','$OCNAME','$OCPHN','$OCAVIL','$OCONLN'";
			String ocr32ReturnFieldLabels = "getWindowLanguageLabel('GBLBRNM'),getWindowLanguageLabel('GBL000233'),getWindowLanguageLabel('GBL000234'),getWindowLanguageLabel('GBLNAME'),getWindowLanguageLabel('GBL000235'),getWindowLanguageLabel('GBLAVAIL'),getWindowLanguageLabel('GBLONLN')";
			String ocr32ReturnFieldPositions = "'0','4','8','12','47','67','68'";
			String ocr32ReturnFieldLengths = "'4','4','4','35','20','1','1'";
			String ocr32DataFields = "'$OCBBN,.*,0,4,N,N,A,N,$OCUSID,referUserId,4,4,N,N,A,N,$OCRBBN,.*,8,4,N,N,A,N,$OCNAME,.*,12,35,N,N,A,N,$OCPHN,.*,47,20,N,N,A,N,$OCAVIL,.*,67,1,N,N,A,N,$OCONLN,.*,68,1,N,N,A,N,$OCEMH,$OCEMH,69,740,N,N,A,N,$OCEA,$OCEA,809,300,N,N,A,N,$OCEB,$OCEB,1109,80,N,N,A,N,$OCDR,$OCDR,1189,20,N,N,A,N,$OCBFUS,.*,1209,30,N,N,A,N,$OCUSID,userId,4,4,Y,N,A,N,$OCONLN,$OCONLN,68,1,Y,N,A,N',";
			String ocr32ReturnDbFields = "'WDBBN','WDUID','WDRBBN','','','',''";
			String ocr32ReturnFieldHdrPos = "'','','','','','',''";
			String ocr32Decode = "'A'";
			
			if( EquationFunctionContext.getContext().isEquationAuthentication())
			{
				 inputClass += " wf_UPPERCASE";
			}
			else
			{
				// CAS:
				userSize = 30;
				userMaxLength = 30;

				// OCR32R meta-data with BankFusion user id OCBFUS available as an input field
				ocr32ReturnFieldName = "'$OCBBN', '$OCBFUS','$OCUSID','$OCRBBN','$OCNAME','$OCPHN','$OCAVIL','$OCONLN'";
				ocr32ReturnFieldLabels = "getWindowLanguageLabel('GBLBRNM'),getWindowLanguageLabel('GBL000400'), getWindowLanguageLabel('GBL000233'),getWindowLanguageLabel('GBL000234'),getWindowLanguageLabel('GBLNAME'), getWindowLanguageLabel('GBL000235'),getWindowLanguageLabel('GBLAVAIL'),getWindowLanguageLabel('GBLONLN')";
				ocr32ReturnFieldPositions = "'0','1209','4','8','12','47','67','68'";
				ocr32ReturnFieldLengths = "'4','30','4','4','35','20','1','1'";
				ocr32DataFields = "'$OCBBN,.*,0,4,N,N,A,N,$OCUSID,.*,4,4,N,N,A,N,$OCRBBN,.*,8,4,N,N,A,N,$OCNAME,.*,12,35,N,N,A,N,$OCPHN,.*,47,20,N,N,A,N,$OCAVIL,.*,67,1,N,N,A,N,$OCONLN,.*,68,1,N,N,A,N,$OCEMH,$OCEMH,69,740,N,N,A,N,$OCEA,$OCEA,809,300,N,N,A,N,$OCEB,$OCEB,1109,80,N,N,A,N,$OCDR,$OCDR,1189,20,N,N,A,N,$OCBFUS,referUserId,1209,30,N,N,A,N,$OCBFUS,referUserId,1209,30,Y,N,A,N,$OCONLN,$OCONLN,68,1,Y,N,A,N',";
				ocr32ReturnDbFields = "'WDBBN','','WDUID','WDRBBN','','','', ''";
				ocr32ReturnFieldHdrPos = "'','','','','','','', ''";
				// Decode B only selects supervisors with a CAS user id mapping defined on OCPF
				ocr32Decode = "'B'";
			}

		%>
		
		<script type="text/javascript">
			var ckey = <%=ckey%>;
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
		<div id="reasonDiv">
			<table id="reasonTable" class="modalTable">
				<tr class="modalHeader">
					<td>
						<span id="reasonTitle" class="modalHeaderText"></span>
					</td>
				</tr>
				<tr>
					<td valign="top" class="modalCell">
						<table class="modalInnerTable">
						<% if (ckey.equals(String.valueOf(FunctionKeys.KEY_REFER)))
						{
						%>					
						<tr class="modalBody">
								<td class="labelText">
									<span id="userIdLabel"> </span>
								</td>
								<td  class="labelText" width="80%">		
									<input type="text" id="referUserId" value='' size="<%=Integer.toString(userSize)%>" maxlength="<%=Integer.toString(userMaxLength)%>" class="<%=inputClass%>">
									<script type="text/javascript">
									setPromptButtonNoWF(getWindowLanguageLabel('GBLSUPVS'), 
											'referUserId', 
											'OCR32R', 
											<%=ocr32Decode%>, 
											'',
											'', 
											'', 
											[<%=ocr32ReturnFieldName%>],
											[<%=ocr32ReturnFieldLabels%>],
											[<%=ocr32ReturnFieldPositions%>],
											[<%=ocr32ReturnFieldLengths%>],
											0,
											12,
											<%=ocr32DataFields%>
											[<%=ocr32ReturnDbFields%>],
											[<%=ocr32ReturnFieldHdrPos%>],
											16);
									</script>
									<img  style="visibility:hidden" id="userProgressBar" src="../images/EqSpin.gif" border="0">
								</td>
								<td class="labelText"/>
							</tr>
						<%
						}
						%>
							<tr class="modalBody">
								<td class="labelText">
									<span id="reasonLabel"> </span>
								</td>
								<td  class="labelText">		
									<input type="text" id="reason" value='' size="50" maxlength="50" class="wf_ul wf_hi wf_default wf_field" onkeydown="return reason_onkeydown(event)"">
								</td>
								<td class="labelText"/>
							</tr>
				<tr class="modalBody">
					<td  class="modalButtons"/>
					<td  class="modalButtons">
						<input type="button" id="okButton" class="modalButton" value="" onclick="ok_onclick(event);"">
						<input type="button" id="cancelButton" class="modalButton" value="" onclick="cancel_onclick(event);">
					</td>
					<td class="modalButtons" width="100%" />
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
			<input type="hidden" id="$OCONLN" value="">
			<input type="hidden" id="$OCEMH" value="KSM7608">
			<input type="hidden" id="$OCEA"  value="">
			<input type="hidden" id="$OCEB"  value="">
			<input type="hidden" id="$OCDR"  value="">
		</div>
		</form>
		
		<script type="text/javascript">
			//need to change the title appropriately
			document.title = window.opener.getLanguageLabel('GBL000377');
			document.getElementById('reasonTitle').innerHTML = document.title;
			document.getElementById('reasonLabel').innerHTML = window.opener.getLanguageLabel('GBLCOMNT');

			<% if (ckey.equals(String.valueOf(FunctionKeys.KEY_REFER))) {	%>	
				document.getElementById('userIdLabel').innerHTML = window.opener.getLanguageLabel('GBL000233');
				document.getElementById('reasonLabel').innerHTML = window.opener.getLanguageLabel('GBL000380');
			<% } %>

			<% if (ckey.equals(String.valueOf(FunctionKeys.KEY_AUTHA)) || ckey.equals(String.valueOf(FunctionKeys.KEY_DEL))) {	%>	
			document.getElementById('reasonLabel').innerHTML = window.opener.getLanguageLabel('GBL000379');
			<% } %>

			<% if (ckey.equals(String.valueOf(FunctionKeys.KEY_DECL))) {	%>	
			document.getElementById('reasonLabel').innerHTML = window.opener.getLanguageLabel('GBL000378');
			<% } %>
		
			document.getElementById('okButton').value = window.opener.getLanguageLabel('GBLOK');
			document.getElementById('cancelButton').value = window.opener.getLanguageLabel('GBLCAN');

			var reasonField = window.opener.getFrameCurrent().rtvCommentField();
			document.getElementById('reason').value = reasonField;
		</script>
		
		<html:errors bundle="equation"/>
	</body>
	
</html:html>