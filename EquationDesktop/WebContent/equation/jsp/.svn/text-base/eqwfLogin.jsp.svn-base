<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.Map"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationStandardSession"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<%@page import="com.misys.equation.function.context.EquationFunctionContext"%>
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<%@page import="com.misys.equation.common.utilities.Toolbox"%>
<html:html>
	<%
	EquationLogin equationLogin = (EquationLogin)request.getSession().getAttribute("equationLogin");
	%>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<title>eqwfLogin</title>
   </head>
	<body>
		<%
			String mode =  request.getParameter("mode");
			String unit =  request.getParameter("unit");
			String optionId = request.getParameter("optionId");
			String context = Toolbox.pad(request.getParameter("context"), 55);

			String dswsid1 = Toolbox.pad(equationLogin.getContextData1(),512);
			String dswsid2 = Toolbox.pad(equationLogin.getContextData2(),512);
			String workstationId = request.getParameter("workstationId");

			String userId = null;
			String password = null;
			String tokenString = "''";		// Empty string
			
			if( EquationFunctionContext.getContext().isEquationAuthentication() || equationLogin.getSessionType() == EquationCommonContext.SESSION_CLASSIC_DESKTOP || !EquationFunctionContext.getContext().useCasCommonProfileForWebFacing())
			{
				// Start WebFacing job with the credentials supplied 
				// when logging into the Equation Desktop 
				userId = equationLogin.getUserId();
				password = equationLogin.getPassword();
			}
			else
			{
				// Start WebFacing job with the unit Admin credentials and 
				// switch the job to the actual user by means of a profile token 
				String equationUserName = Toolbox.pad(equationLogin.getUserId(),10);

				String [] unitIds = EquationCommonContext.getConfigProperty("eq.units").split(",");
				String [] systemIds = EquationCommonContext.getConfigProperty("eq.systems").split(",");
				String [] userIds = EquationCommonContext.getConfigProperty("eq.admin.users").split(",");
				String [] passwords = EquationCommonContext.getConfigProperty("eq.admin.passwords").split(",");

				Map<String, String[]> aliasDetails = EquationCommonContext.getAdminAlias();
				if (aliasDetails != null)
				{
					userIds = aliasDetails.get("Users");
					passwords = aliasDetails.get("Passwords");
				}

				EquationUnit equationUnit = EquationCommonContext.getContext().getEquationUnit(equationLogin.getSessionId());
				
				for( int index = 0; index < unitIds.length; index++)
				{
					if( unitIds[index].equals(equationUnit.getUnitId()) && systemIds[index].equals(equationUnit.getSystem().getSystemId()))
					{
						userId = userIds[index];
						password = passwords[index];
						break;		
					}
				}
		
				// Get profile token:				
				EquationStandardSession adminSession = equationUnit.getEquationSessionPool().getEquationStandardSession();
				String utr58rPassword = equationUserName.equals(adminSession.getUserId()) ? "" :  "*NOPWDCHK";
				byte[] userTokenBytes = adminSession.getUserToken(equationUserName, utr58rPassword, "2");
				
				StringBuffer tokenStringBuffer= new StringBuffer( "X'" );
				tokenStringBuffer.append(Toolbox.cvtBytesToHexString(userTokenBytes));
				tokenStringBuffer.append("'");
				// Note that the hex bytes of the profile token are passed to the RPG W97HMR program using 
				// CL syntax: i.e. X'48A24C844FA222FE13D297032E9....'
				tokenString = tokenStringBuffer.toString();
			}
			
			String clcmd;

			if (equationLogin.getSessionType() == EquationCommonContext.SESSION_CLASSIC_DESKTOP) 
			{
			    clcmd = "call kapsgnon";
			}
			else if (optionId == null)
			{
			    clcmd = "call w97hmr";
			}
			else
			{
			    clcmd = "call w97hmr ('" + mode + "' '" + unit + "' '" + optionId + "' '" + context + "' '" + dswsid1 + "' '" + dswsid2 + "' '" + workstationId + "' " + tokenString + ")";
			}
		%>
		
		<jsp:forward page="/WFInvocation.do">
			<jsp:param name="clcmd" value="<%=clcmd%>"/>
			<jsp:param name="host" value="<%=equationLogin.getSystem()%>"/>
			<jsp:param name="userid" value="<%=userId%>"/>
			<jsp:param name="password" value="<%=password%>"/>
		</jsp:forward>
	</body>
</html:html>