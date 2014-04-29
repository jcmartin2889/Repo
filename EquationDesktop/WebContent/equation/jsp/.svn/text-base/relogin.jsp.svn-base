<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html:html>
	<HEAD>
		<TITLE>
			<bean:message key="login.title" bundle="equation"/>
		</TITLE>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script>
			function loginload()
			{
				// Non-UXP, then display message
				if (!isUXP())
				{
					displayMessage(getLanguageLabel('GBL900084'));
					closeBrowser();
					return;
				}
	
				// UXP
				else
				{
					endLoadingTransaction();
					hideSpinnerButton();
					displayUXPErrorMessage(getLanguageLabel('GBL900084'));
					var height = getTopMostFrame().screen.availHeight;
					document.getElementById('mainTD').style.height = (height-50) + 'px'; 
				}
			}
	
			var eqDriver = 'Y';
		</script>
	</HEAD>
	
	<body style="height:100%;width:100%;"
		  onload="javascript:loginload()"
		  onkeydown='return disableF5(event)'>
		  
		  <div id="mainTD" name="mainTD" style="height:100%;width:100%;">
		  </div>
	</body>
	
</html:html>