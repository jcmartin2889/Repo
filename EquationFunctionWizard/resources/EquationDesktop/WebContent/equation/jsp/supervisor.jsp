<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
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
			eqInit();
			asPopupPVWindow = true;
		</script>
		
		<%
			// supervisor
			String supervisor = request.getParameter("supervisor");
			if (supervisor==null)
			{
				supervisor="";
			}
			
			// timeout
			String retry = request.getParameter("retry");
			if (retry==null)
			{
				retry="010";
			}
			// Variable field sizes 
			int userSize = 5;
			int passwordSize = 12;
			int userMaxLength = 4;
			int passwordMaxLength = 10;
			String inputClass =  "wf_ul wf_hi wf_default wf_field";
			
			// OCR32R standard meta-data
			String ocr32ReturnFieldName = "'$OCBBN','$OCUSID','$OCRBBN','$OCNAME','$OCPHN','$OCAVIL','$OCONLN'";
			String ocr32ReturnFieldLabels = "getWindowLanguageLabel('GBLBRNM'),getWindowLanguageLabel('GBL000233'),getWindowLanguageLabel('GBL000234'),getWindowLanguageLabel('GBLNAME'),getWindowLanguageLabel('GBL000235'),getWindowLanguageLabel('GBLAVAIL'),getWindowLanguageLabel('GBLONLN')";
			String ocr32ReturnFieldPositions = "'0','4','8','12','47','67','68'";
			String ocr32ReturnFieldLengths = "'4','4','4','35','20','1','1'";
			String ocr32DataFields = "'$OCBBN,.*,0,4,N,N,A,N,$OCUSID,userId,4,4,N,N,A,N,$OCRBBN,.*,8,4,N,N,A,N,$OCNAME,.*,12,35,N,N,A,N,$OCPHN,.*,47,20,N,N,A,N,$OCAVIL,.*,67,1,N,N,A,N,$OCONLN,.*,68,1,N,N,A,N,$OCEMH,$OCEMH,69,740,N,N,A,N,$OCEA,$OCEA,809,300,N,N,A,N,$OCEB,$OCEB,1109,80,N,N,A,N,$OCDR,$OCDR,1189,20,N,N,A,N,$OCBFUS,.*,1209,30,N,N,A,N,$OCUSID,userId,4,4,Y,N,A,N,$OCONLN,$OCONLN,68,1,Y,N,A,N',";
			String ocr32ReturnDbFields = "'WDBBN','WDUID','WDRBBN','','','',''";
			String ocr32ReturnFieldHdrPos = "'','','','','','',''";
			String ocr32Decode = "'A'";

			if( EquationFunctionContext.getContext().isEquationAuthentication())
			{
				 inputClass += " wf_UPPERCASE";
			}
			else
			{
				userSize = 30;
				passwordSize = 30;
				userMaxLength = 30;
				passwordMaxLength =50;
				
				// OCR32R meta-data with BankFusion user id OCBFUS available as an input field
				ocr32ReturnFieldName = "'$OCBBN', '$OCBFUS','$OCUSID','$OCRBBN','$OCNAME','$OCPHN','$OCAVIL','$OCONLN'";
				ocr32ReturnFieldLabels = "getWindowLanguageLabel('GBLBRNM'),getWindowLanguageLabel('GBL000400'), getWindowLanguageLabel('GBL000233'),getWindowLanguageLabel('GBL000234'),getWindowLanguageLabel('GBLNAME'), getWindowLanguageLabel('GBL000235'),getWindowLanguageLabel('GBLAVAIL'),getWindowLanguageLabel('GBLONLN')";
				ocr32ReturnFieldPositions = "'0','1209','4','8','12','47','67','68'";
				ocr32ReturnFieldLengths = "'4','30','4','4','35','20','1','1'";
				ocr32DataFields = "'$OCBBN,.*,0,4,N,N,A,N,$OCUSID,.*,4,4,N,N,A,N,$OCRBBN,.*,8,4,N,N,A,N,$OCNAME,.*,12,35,N,N,A,N,$OCPHN,.*,47,20,N,N,A,N,$OCAVIL,.*,67,1,N,N,A,N,$OCONLN,.*,68,1,N,N,A,N,$OCEMH,$OCEMH,69,740,N,N,A,N,$OCEA,$OCEA,809,300,N,N,A,N,$OCEB,$OCEB,1109,80,N,N,A,N,$OCDR,$OCDR,1189,20,N,N,A,N,$OCBFUS,userId,1209,30,N,N,A,N,$OCBFUS,userId,1209,30,Y,N,A,N,$OCONLN,$OCONLN,68,1,Y,N,A,N',";
				ocr32ReturnDbFields = "'WDBBN','','WDUID','WDRBBN','','','', ''";
				ocr32ReturnFieldHdrPos = "'','','','','','','', ''";
				// Decode B only selects supervisors with a CAS user id mapping defined on OCPF
				ocr32Decode = "'B'";
				
			}
		%>
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
					<td valign="top">
						<table cellSpacing="1" cellPadding="4" border="0">
							<tr class="modalBody">
								<td class="labelText">
									<span id="retryAfterLabel"> </span>
								</td>
								<td  class="labelText">
									<input type="text" id="retry" value='<%=retry%>' size="3" maxlength="3" class="wf_ul wf_hi wf_default wf_field" onkeypress="retry_onkeydown(event);" onfocus="return field_focus(event)">
									<span id="secondLabel"> </span>
								</td>
							</tr>
							
							<tr class="modalBody">
								<td class="labelText">
									<span id="userIdLabel"> </span>
								</td>
								<td  class="labelText" width="70%">		
									<input type="text" id="userId" value='<%=supervisor%>' size="<%=Integer.toString(userSize)%>" maxlength="<%=Integer.toString(userMaxLength)%>" class="<%=inputClass%>" onkeydown="userId_onkeydown(event)" onfocus="return field_focus(event)">
									<script type="text/javascript">
									setPromptButtonNoWF(getWindowLanguageLabel('GBLSUPVS'), 
											'userId', 
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
									<img  id="passwordProgressBar" src="../images/EqSpin.gif" border="0">
								</td>
							</tr>
							<tr class="modalBody">
								<td class="labelText">
									<span id="passwordLabel"> </span>
								</td>
								<td  class="labelText" width="70%">		
									<input type="password" id="passwordText" value='' size="<%=Integer.toString(passwordSize)%>" maxlength="<%=Integer.toString(passwordMaxLength)%>" class="<%=inputClass%>"  onkeydown="passwordText_onkeydown(event)" onfocus="return field_focus(event)">
								</td>
							</tr>
						</table>
						
					</td>
				</tr>
				<tr>
					<td  class="modalButtons">
						<input type="button" id="okButton" class="modalButton" value="" onclick="ok_onclick(event);" onfocus="return field_focus(event)">
						<input type="button" id="cancelButton" class="modalButton" value="" onclick="cancel_onclick(event);" onfocus="return field_focus(event)">
						<input type="button" id="remoteButton" class="modalButton" value="" onclick="remote_onclick(event);" onfocus="return field_focus(event)">
						<input type="button" id="offlineButton" class="modalButton" value="" onclick="offline_onclick(event);" onfocus="return field_focus(event)">

						<% 
						// disable this button for now
						if (false && EquationCommonContext.getContext().isLRPProcessing())
						{
							%>
							<input type="button" id="lrpButton" class="wf_ul wf_hi wf_default wf_field" value="" onclick="lrp_onclick(event);" onfocus="return field_focus(event)">
							<%
						}
						%>
					</td>
				</tr>
				<tr class="modalFooter">
						<td class="labelText">
							<span id="status"> 
								&nbsp; 
								<img class="buttonDisabled" border="0" src="../images/EQError.gif" style="visibility:hidden;">		
							</span>
							<span id="status2">&nbsp; </span>
						</td>
				</tr>
			</table>
		</div>

		<input type="hidden" id="awaiting" value="">
		<input type="hidden" id="timeout" value="">
		<input type="hidden" id="timeout2" value="">
		<input type="hidden" id="review" value="">
		<input type="hidden" id="decline" value="">
		<input type="hidden" id="approve" value="">
		<input type="hidden" id="approveone" value="">
		<input type="hidden" id="supercancel" value="">
		<input type="hidden" id="usercancel" value="">
		<input type="hidden" id="closebutton" value="">
		
		<input type="hidden" id="$OCONLN" value="">
		<input type="hidden" id="$OCEMH" value="<%=request.getParameter("msgIds")%>">
		<input type="hidden" id="$OCEA"  value="<%=request.getParameter("msgAmts")%>">
		<input type="hidden" id="$OCEB"  value="<%=request.getParameter("msgBrnms")%>">
		<input type="hidden" id="$OCDR"  value="<%=request.getParameter("msgDrCrs")%>">

		</form>
		
		<script type="text/javascript">
			tranType = '<%=request.getParameter("tranType")%>';
			
			document.title = window.opener.getLanguageLabel('GBL000232');
			document.getElementById('passwordTitle').innerHTML = document.title;
			
			document.getElementById('userIdLabel').innerHTML = window.opener.getLanguageLabel('GBLSUPVS');
			document.getElementById('passwordLabel').innerHTML = window.opener.getLanguageLabel('GBLPWD');
			document.getElementById('retryAfterLabel').innerHTML = window.opener.getLanguageLabel('GBL900063');
			document.getElementById('secondLabel').innerHTML = window.opener.getLanguageLabel('GBLSECS');

			document.getElementById('okButton').value = window.opener.getLanguageLabel('GBLOK');
			document.getElementById('cancelButton').value = window.opener.getLanguageLabel('GBLCAN');
			document.getElementById('remoteButton').value = window.opener.getLanguageLabel('GBL000230');
			document.getElementById('offlineButton').value = window.opener.getLanguageLabel('GBL000231');

			var obj = document.getElementById('lrpButton'); 
			if(obj !=null)
			{
				document.getElementById('lrpButton').value = window.opener.getLanguageLabel('GBL000376');
			}
			
			document.getElementById('awaiting').value = window.opener.getLanguageLabel('GBL900054');
			document.getElementById('timeout').value = window.opener.getLanguageLabel('GBL900055');
			document.getElementById('timeout2').value = window.opener.getLanguageLabel('GBL900056');
			document.getElementById('review').value = window.opener.getLanguageLabel('GBL900057');
			document.getElementById('decline').value = window.opener.getLanguageLabel('GBL900058');
			document.getElementById('approve').value = window.opener.getLanguageLabel('GBL900059');
			document.getElementById('approveone').value = window.opener.getLanguageLabel('GBL900060');
			document.getElementById('supercancel').value = window.opener.getLanguageLabel('GBL900061');
			document.getElementById('usercancel').value = window.opener.getLanguageLabel('GBL900062');
			document.getElementById('closebutton').value = window.opener.getLanguageLabel('GBLCLOSE');
		</script>
		
		<input type="hidden" id="sel_numrec">
		<input type="hidden" id="sel_numcol">
		<html:errors bundle="equation"/>
	</body>
	
</html:html>