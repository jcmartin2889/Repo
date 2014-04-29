<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.function.context.EquationFunctionContext"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<% EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser"); %>
<html:html>
	<HEAD>
		<TITLE>
			<bean:message key="login.title" bundle="equation"/>
		</TITLE>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<link rel="SHORTCUT ICON" href="<html:rewrite page='/equation/images/login/misysicon16x16.ico'/>" />
		<link rel="stylesheet" href="<html:rewrite page='/equation/styles/login.css'/>" type="text/css">
		<link rel="stylesheet" href="<html:rewrite page='/equation/fonts/opensans.css'/>" type="text/css">
		<link rel="stylesheet" href="<html:rewrite page='/equation/styles/custom-elements.css'/>" type="text/css">
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/windowProperties.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/dt_key.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/custom-form-elements.js'/>"></script>
		<script type="text/javascript">
			var RTL = false;
			<%if(request.getAttribute("isLogin")!=null){%>
			var errors = "${tempError}";
			var e = errors.substr(errors.indexOf("error.parameterised[")+20,errors.length);
			var err = e.substr(0,e.indexOf("]"));
			var errMessage = "Redirecting to Login Page.";
			<%if (equationUser != null){%>
			errMessage = "<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLRDTLGP")%>";
			<%}%>
			alert(err+"\n"+ errMessage);
				window.top.location.replace("equation/jsp/loginMain.jsp" + "?" + mandatoryParameterOnLogin());
			<%}%>
		</script>

		<%
			// Check if this form is automatically submitted
			boolean autosubmit = "true".equals(request.getParameter("autosubmit")) ? true: false;
			// Always set it back to 'false' in order to display error messages
			request.setAttribute("autosubmit","false");
			// Password change?
			boolean passwordchanged = "true".equals(request.getParameter("passwordchanged")) ? true: false;
			
			// Set optId and optParm to blank if not specified (to prevent null value)
			String optId = request.getParameter("optId");
			if (optId == null) optId="";
			String optParm = request.getParameter("optParm");
			if (optParm == null) optParm="";
			
			// Is optId specified?
			boolean dispOption = false;
			if (!optId.equals("")) dispOption = true;
			
			// Set context details to blank if not specified
			String cus = request.getParameter("cus");
			if (cus == null) cus = "";
			
			String clc = request.getParameter("clc");
			if (clc == null) clc = "";
			
			String brnm = request.getParameter("brnm");
			if (brnm == null) brnm = "";
			
			String abc = request.getParameter("abc");
			if (abc == null) abc = "";
			
			String anc = request.getParameter("anc");
			if (anc == null) anc = "";
			
			String ans = request.getParameter("ans");
			if (ans == null) ans = "";
			
			String ean = request.getParameter("ean");
			if (ean == null) ean = "";
			
			String dlp = request.getParameter("dlp");
			if (dlp == null) dlp = "";
			
			String dlr = request.getParameter("dlr");
			if (dlr == null) dlr = "";
			
			String debug = request.getParameter("debug");
			if (debug == null) debug = "";
			
			String rollback = request.getParameter("rollback");
			if (rollback == null) rollback = "";

			String workstationId = request.getParameter("workstationId");
			if (workstationId == null) workstationId = "";
			
			String style = request.getParameter("style");
			if (style == null) style = "";
			
			String forceWorkstationId = request.getParameter("forceWorkstationId");
			if (forceWorkstationId == null) forceWorkstationId = "false";
		%>

		<script type="text/javascript">
		var defaultFieldSizes = [12, 10, 12, 10];
		<%if(!EquationFunctionContext.getContext().isEquationAuthentication()){%>var customFieldSizes = [<%=EqDesktopToolBox.getUserIdLength()%>, <%=EqDesktopToolBox.getUserIdLength()%>, <%=EqDesktopToolBox.getUserIdLength()%>, <%=EqDesktopToolBox.getPasswordLength()%>];<%}%>

		// put details into cookie
		if (<%=passwordchanged%>)
		{
			// when the login has been due to password change action, do not override the cookie
		}
		else
		{
			// store selected parameter as cookie so that it can be passed back to login.jsp when it is called again
			setCookie("cWorkstationID","<%=workstationId%>");
			setCookie("cStyle","<%=style%>");
		}
		function login_onLoad()
		{
			// Set user and password field sizes:
			setFieldSizes(typeof(customFieldSizes) === 'undefined' || <%="1".equals(request.getParameter("isClassic"))%> ? defaultFieldSizes : customFieldSizes );			
			
			// set the focus to the user id
			document.getElementById('userId').focus();
			
			// when autosubmit and option id is specified, then hide the login form
			if(<%=autosubmit%> && <%=dispOption%>)
			{
				var obj = document.getElementById('logincontainer');
				obj .style.visibility = 'hidden';
			}

			// when not autosubmit and option id is specified, then remove the login form
			// so that it will not occupy space.  Just display the errors if exist
			if(!<%=autosubmit%> && <%=dispOption%>)
			{
				var obj = document.getElementById('logincontainer');
				obj.outerHTML = ' ';
			}

			// check if it should be automatically submitted
			if(<%=autosubmit%>)
			{
				var form = document.getElementsByTagName('form')[0];
				form.submit();
			}

			// password changed, overwrite some details
			if (<%=passwordchanged%>)
			{
				document.getElementById('workstationId').value = getCookie('cWorkstationID');
				document.getElementById('style').value = getCookie('cStyle');
			}

			// radio boxes
			Custom.init();
			setClassicRadioButton();			
		}

		function login_onUnload()
		{
		}

		function login_onSubmit()
		{
			document.getElementById('submitButton').disabled=true;
		}

		function toggleClassic()
		{
			if( typeof(customFieldSizes) !== 'undefined')
			{
				setFieldSizes(defaultFieldSizes);
			}
		}

		function toggleDesktop()
		{
			if( typeof(customFieldSizes) !== 'undefined')
			{
				setFieldSizes(customFieldSizes);
			}
		}

		function setFieldSizes( sizeArray)
		{
			var userTag = document.getElementsByName( "userId" )[0];
			var passwordTag = document.getElementsByName( "passWord" )[0];
			userTag.size = sizeArray[0];
			userTag.maxLength = sizeArray[1];
			passwordTag.size = sizeArray[2];
			passwordTag.maxLength = sizeArray[3];
		}


		function login_cmdLogin()
		{
			var form = document.getElementsByTagName('form')[0];
			getClassicRadioButton();
			form.submit();
			login_onSubmit();
		}

		function login_onkeyup(e)
		{
			var keynum = rtvKeyboardKey(e);
			if (keynum==13)
			{
				if (!document.getElementById('submitButton').disabled)
				{
					login_cmdLogin();
				}
			}
		}

		// Set the isClassic hidden input field based on the radio button
		function getClassicRadioButton()
		{
			document.getElementById('isClassic').checked = document.getElementById('isClassicRadio').checked;
		}

		// Set the radio buttons based on the classic hidden input field
		function setClassicRadioButton()
		{
			var value = document.getElementById('isClassic').checked;
			document.getElementById('isDesktopRadio').checked = !value;
			document.getElementById('isClassicRadio').checked = value;
			document.getElementById('isClassicRadio').onchange();
		}
		
		</script>
		
	</HEAD>
	<body oncontextmenu="return false" 
	 	  ondragstart="return false" 
	 	  onload="javascript:login_onLoad()" 
	 	  onunload="javascript: login_onUnload()"
		  class="mys"
	>
		<html:form action="/login" onsubmit="login_onSubmit()">
		<div class="logincontainer" id ="logincontainer" name="logincontainer" onkeyup="return login_onkeyup(event)">
			<img id="logo" title="Misys" alt="Misys" src="<html:rewrite page='/equation/images/login/Logo_Misys.png'/>" /> 
			<div id="applicationName"><img src="<html:rewrite page='/equation/images/login/equation_logo.png'/>" title="BankFusion" alt="BankFusion"/></div>
		
			<div class="inputoutputcontainer">
				<html:errors bundle="equation"/>
			
				<div class="mlogininput">
	
					<div>
						<label id="usernameLabel"><bean:message key="login.user.label" bundle="equation"/></label>
						<html:text property="userId" styleId="userId" value='<%=request.getParameter("user")%>' size="10" maxlength="10"/>
					</div>
	
					<div>
						<label id="passwordLabel"><bean:message key="login.password.label" bundle="equation"/></label>
						<html:password property="passWord" styleId="passWord" value='<%=request.getParameter("password")%>' size="10" maxlength="10"/>
					</div>
				
					<div>
						<label id="system"><bean:message key="login.system.label" bundle="equation"/></label>
						<html:text property="systemName" styleId="systemName" value='<%=request.getParameter("system")%>' size="10" maxlength="10"/>
					</div>
					
					<div>
						<label id="unit"><bean:message key="login.unit.label" bundle="equation"/></label>
						<html:text property="unitName" styleId="unitName" value='<%=request.getParameter("unit")%>' size="3" maxlength="3"/>
					</div>
					<div>
						<label><bean:message key="login.displaymode.label" bundle="equation"/></label>
						<input type="radio" name="loginStyle" id="isDesktopRadio" value="0" class="styled" checked onclick="toggleDesktop()"> 
							&nbsp<label style="display:inline"><bean:message key="login.desktop.label" bundle="equation"/></label><p></p>
						<input type="radio" name="loginStyle" id="isClassicRadio" value="1" class="styled" onclick="toggleClassic()">
							&nbsp<label style="display:inline"><bean:message key="login.classic.label" bundle="equation"/></label>
					</div>
				</div>
				
				<div class="loginbutton">
					<input id="submitButton" type="button" onclick="login_cmdLogin();" NAME="submitButton" value="<bean:message key="login.submit.button" bundle="equation"/>" />
				</div>
			</div>
			

			<div style="display: none;">
			<html:text property="optId" styleId="optId" value="<%=optId%>" size="3" maxlength="3" />
			<html:text property="optParm" styleId="optParm" value="<%=optParm%>" size="55" maxlength="55" />
			<html:text property="ctxtCus" styleId="ctxtCus" value="<%=cus%>" size="6" maxlength="6" />
			<html:text property="ctxtClc" styleId="ctxtClc" value="<%=clc%>" size="3" maxlength="3" />
			<html:text property="ctxtBrnm" styleId="ctxtBrnm" value="<%=brnm%>" size="4" maxlength="4" />
			<html:text property="ctxtAbc" styleId="ctxtAbc" value="<%=abc%>" size="4" maxlength="4" />
			<html:text property="ctxtAnc" styleId="ctxtAnc" value="<%=anc%>" size="6" maxlength="6" />
			<html:text property="ctxtAns" styleId="ctxtAns" value="<%=ans%>" size="3" maxlength="3" />
			<html:text property="ctxtEan" styleId="ctxtEan" value="<%=ean%>" size="20" maxlength="20" />
			<html:text property="ctxtDlp" styleId="ctxtDlp" value="<%=dlp%>" size="3" maxlength="3" />
			<html:text property="ctxtDlr" styleId="ctxtDlr" value="<%=dlr%>" size="13" maxlength="13" />
			<html:text property="debug" styleId="debug" value="<%=debug%>" size="10" maxlength="10" />
			<html:text property="rollback" styleId="rollback" value="<%=rollback%>" size="10" maxlength="10" />
			<html:text property="workstationId" styleId="workstationId" value="<%=workstationId%>" size="10" maxlength="10" />
			<html:text property="style" styleId="style" value="<%=style%>" size="30" maxlength="30" />
			<html:text property="forceWorkstationId" styleId="forceWorkstationId" value="<%=forceWorkstationId%>" size="10" maxlength="10" />
			<input type="text" name="newPassword" value="<%=request.getParameter("newPassword")%>" />
			<html:checkbox property="isClassic" styleId="isClassic" value="1" />
			</div>
			
		</div>
		</html:form>

		<input type="hidden" id="logintimeout" value="<bean:message key='login.sessiontimeout' bundle='equation'/>">
		<input type="hidden" id="passwordsuccessful" value="<bean:message key='changepassword.successful' bundle='equation'/>">
			
	</BODY>
		<script type="text/javascript">

		// check for timeout
		var timeout = <%=request.getParameter("timeout")%>;
		if(timeout==true)
		{
			displayMessage(document.getElementById('logintimeout').value);
		}

		// check for password changed
		var passwordchanged = <%=request.getParameter("passwordchanged")%>;
		if(passwordchanged==true)
		{
			displayMessage(document.getElementById('passwordsuccessful').value);
		}
	
	   </script>
	
</html:html>