<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<%@page import="com.misys.equation.ui.context.EquationDesktopContext"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<% EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser"); %>
<% EquationLogin equationLogin = (EquationLogin)request.getSession().getAttribute("equationLogin");
if(equationUser != null)
{
	request.setAttribute("system",equationLogin.getSystem());
	request.setAttribute("user",equationLogin.getUserId());
	request.setAttribute("unit",equationLogin.getUnitId());
	request.removeAttribute("show");
}
else
{
	request.setAttribute("show","true");
}

String newPass = (String)request.getAttribute("newPassword");
if(newPass != null){
	request.setAttribute("show","true");
}
%>
<html:html>
	<HEAD>
		<TITLE>
			<bean:message key="login.title" bundle="equation"/>
		</TITLE>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<link rel="stylesheet" href="<html:rewrite page='/equation/styles/EqUI.css'/>" type="text/css">
		<link rel="stylesheet" href="<html:rewrite page='/equation/styles/userUI.css'/>" type="text/css">
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/msf.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/tabbar.js'/>"></script>
		
		<script type="text/Javascript">
			var RTL = false;
			function populateTitleBar(){
				<%if(request.getAttribute("show")==null){%>
					var loginFrame = window.top.frames['loginFrame'];
					var mainFrame = loginFrame.frames['mainFrame'];
					var inputFrame = mainFrame.frames['inputFrame'];
					var buttonBarFrame = inputFrame.frames['buttonBarFrame'];
					var bar = buttonBarFrame.document.getElementById('buttonBarTitle');
					bar.innerText = '<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLCHGPAS")%>';
				<%}%>
			}
			function changePassSubmit() {
				<% //-- Automatically Change Password. 
				if(request.getAttribute("newPassword")!=null){%>
						document.getElementById('chgpwd').submit();
				<%}%>
			}

			function transferMessage()
			{
				// are frame setup, if not, exit
				var frame = getFrameMainFrame();
				if (frame == null)
				{
					return;
				}
				// clear error message
				setMsgCtl(null,null);
				
				// are there any error message?
				if (document.getElementById("errorHeader")==null)
				{
					return;
				}

				// get the error message
				var errors = document.getElementsByTagName("li");
				var arrMsgf = new Array();
				for (var i=0; i<errors.length; i++)
				{
					arrMsgf[i] = errors[i].innerText;
				}

				// transfer the error messages to the problem area
				setMsgCtl(arrMsgf,"errorHeader");
			}
		</script>
		
	</HEAD>
  <body onload="javascript:document.getElementById('oldPassword').focus();populateTitleBar();changePassSubmit();eqInit_StandAlone();
  				transferMessage();">	
	  <html:form action="/chgpwd" styleId="chgpwd">
	  	<logic:present name="show">	
	  		<table cellSpacing="10" cellPadding="10" border="0">
				<tr>
					<td>
						<br>
						<img border="0" src="<html:rewrite page='/equation/images/logo.gif'/>">
					</td>
					</tr>
			</table>
		</logic:present>		  	      
		<table border="0" cellspacing="1" cellpadding="4" class="wf_default wf_field">
			
			<tr valign="middle" align="center">		
				<td valign="top">													
					<table width="300" border="0" cellspacing="3" cellpadding="3" class="wf_default wf_field">
						<tr>
							<td align="left" class="labelText">
								<bean:message key="login.system.label" bundle="equation"/>:			
							</td>							
								<td align="left"><html:text disabled="true" property="systemName" styleId="systemName" size="10" maxlength="10" value='${system}'/></td>
						</tr>
						<tr>
							<td align="left" class="labelText">
								<bean:message key="login.user.label" bundle="equation"/>:
							</td>
							<td align="left"><html:text disabled="true" property="userId" styleId="userId" size="10" maxlength="10" value='${user}'/></td>
						</tr>
						<tr>
							<td align="left" class="labelText">
								<bean:message key="changepassword.currentpassword" bundle="equation"/>:
							</td>
							<td align="left"><html:password property="oldPassword" styleId="oldPassword" size="10" maxlength="128" value='${oldPassword}'/></td>
						</tr>
						<tr>
							<td align="left" class="labelText">
								<bean:message key="changepassword.newpassword" bundle="equation"/>:
							</td>
							<td align="left"><html:password property="newPassword" styleId="newPassword" size="10" maxlength="128" value="${newPassword}"/></td>
						</tr>
						<tr>
							<td align="left" class="labelText">
								<bean:message key="changepassword.confirmpassword" bundle="equation"/>:
							</td>
							<td align="left"><html:password property="newPassword2" styleId="newPassword2" size="10" maxlength="128" value="${newPassword}"/></td>
						</tr>
						<tr>	
							<td align="left">
								</td>			
							<td align="left">
								<html:submit><bean:message key="changepassword.change" bundle="equation"/></html:submit>
							</td>							
						</tr>	
						<tr>
							<td align="left">
								</td>
							<td align="left">
								<logic:present name="show"><html:link styleClass="passwordText" href="javascript:goToLogin();"><bean:message key="changepassword.cancel" bundle="equation"/></html:link></logic:present>
								<logic:notPresent name="show"><html:link styleClass="passwordText" styleId="cancelOK" href="welcome.jsp"><bean:message key="changepassword.cancel" bundle="equation"/></html:link></logic:notPresent>									
							</td>				
						</tr>
						<tr>
							<td align="left"><html:hidden property="systemName" styleId="systemName" value='${system}'/></td>
							<td align="left"><html:hidden property="userId" styleId="userId" value='${user}'/></td>
							<td align="left"><html:hidden property="unit" styleId="unit" value='${unit}'/></td>
							<td align="left"><html:hidden property="isClassic" styleId="isClassic" value='${isClassic}'/></td>
							<td align="left"><html:hidden property="optId" styleId="optId" value='${optId}'/></td>
							<td align="left"><html:hidden property="style" styleId="style" value='${style}'/></td>
							<td align="left"><html:hidden property="workstationId" styleId="workstationId" value='${workstationId}'/></td>
							<td align="left"><html:hidden property="optParm" styleId="optParm" value='${optParm}'/></td>
							<logic:notPresent name="show">
							<td align="left"><html:hidden property="isMainPage" styleId="isMainPage" value='true'/></td>
							</logic:notPresent>
						</tr>					
					</table>
				</td>
			</tr>
		</table>
		
	  </html:form>
	  <html:errors bundle="equation"/>
	  
	  <script>
	  var cancelObj = document.getElementById("cancelOK");
	  if (cancelObj != null)
	  {
		  var href = cancelObj.href;
		  href = '/' + getWebAppName() + "/equation/jsp/welcome.jsp";
		  cancelObj.href = href;
	  }

	  var errorHeaderTitle = document.getElementById("errorHeaderTitle");
	  if (errorHeaderTitle != null)
	  {
		  errorHeaderTitle.className = "errorHeader";
		  errorHeaderTitle.innerText = "Password error";
	  }

	  var errorHeader = document.getElementById("errorHeader");
	  if (errorHeader != null && errorHeaderTitle != null)
	  {
		  var hr = document.createElement("hr");
		  errorHeader.insertBefore(hr,errorHeaderTitle);
	  }
	  
	  </script>
  </body>
</html:html>