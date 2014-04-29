<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<%@page import="com.misys.equation.common.access.EquationConfigurationPropertiesBean"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<html:html>
	<%
	EquationLogin equationLogin = (EquationLogin)request.getSession().getAttribute("equationLogin");
	EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
	EquationUnit equationUnit = (EquationUnit)request.getSession().getAttribute("equationUnit");
	Boolean isAGPSession= (Boolean)request.getSession().getAttribute("isAGPSession"); 
	%>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<title><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000018")%></title>
	
		<% 
			if (equationUser.isLanguageRTL()) 
			{ 
				%><link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetRTL(request)%>'/>" type="text/css"><%
			} 
			else 
			{
				%><link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetMain(request)%>'/>" type="text/css"><%
			}
		%>
		<link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetUserStyle(request)%>'/>" type="text/css">
		
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/constant.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/pvWS.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/eqWS.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/function.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/funckeytext.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/msf.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/tabbar.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/mainBody.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/childWindow.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/popup.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/windowProperties.js'/>"></script>
		<script type="text/JavaScript">
			var RTL = <%=equationUser.isLanguageRTL()%>;
			eqInit();		

			function switchUnit()
			{
		        var frame = getFrameMainFrame().document.getElementById('mainFrameset');
				var cols = frame.getAttribute('cols');
				cols = cols.replace("%", "PERCENTAGE");
				cols = cols.replace("%", "PERCENTAGE");
				disableLogoffOnBlankFrame();
				disableLogoffOnBlankFrameLogin();
				if(frame != null)
				{
					window.top.location.replace("../../switchtohome.do?mainFrameset="+cols + "&" + mandatoryParameterOnLogin());
				}
				else
				{
					window.top.location.replace("../../switchtohome.do" + "&" + mandatoryParameterOnLogin());
				}
			}

		</script>
   </head>
	<body id="toolbarBody" 
	      class="toolbarBody" 
	      <%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> 
	>
		<table class="toolbar" cellpadding="0" cellspacing="0" height="100%">
				<tr>
					<td id='tdoptionLabel' class="labelText" align="center">
						<label id="optionLabel" for="optionInput" accesskey="o"></label>
						<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL800001")%>
					</td>
					<td class="textInput" align="center">						
						<INPUT class="inputText" style="TEXT-TRANSFORM:uppercase" onkeypress="toolbarEnter(event)" id="optionInput" name="optionInput" type="text" size="3" maxlength="3"> 
						<INPUT type="hidden" id="#USID" name="#USID" value="<%=equationUser.getUserId() %>"></INPUT> 
					</td>
					<td class="button" align="center">
							<a href="JavaScript:getNewPVListAsWindow('Option','optionInput','GBR39R','V','','','','#ZLOIDS,#ZLONMS,#ZLOMTS',
								'Option/Menu!:!Option/Menu Title!:!Type','0,10,7','3,35,3','0','3','#ZLOIDS,optionInput,
								0,3,N,N,A,N,#USID,#USID,3,4,N,N,V,N,#ZLOMTS,.*,7,3,N,N,A,N,#ZLONMS,.*,10,35,N,N,A,N,#ZLWMN1,
								.*,45,1,N,N,A,N,#ZLOIDS,optionInput$$$DB,0,3,Y,N,A,N,#ZLONMS,optionInput$$$Output,10,35,Y,
								N,A,N','GBOID,GBONM,GBOMT',',,',true,16)">
						        <img src="<html:rewrite page='/equation/images/search.gif'/>" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLSEARCH")%>" id="optionInput$$ButtonPMT" border="0">
						  </a>
					</td>
					<td id='inputText$$ButtonPMT'></td>
					<td class="textInput" align="center">						
						<INPUT class="inputText" style="TEXT-TRANSFORM:uppercase" onkeypress="toolbarEnter(event)" id="contextInput" name="contextInput" type="text" size="30" maxlength="55">
					</td>
					<td id='tdgoLabel' class="button" align="center">
						<a href="JavaScript:routeToOption2('*D',unitId,optionInput.value,contextInput.value)" tabIndex='-1'>
							<img 
								<%
									if (equationUser.isLanguageRTL())
									{
										%>
										src="<html:rewrite page='/equation/images/gor.gif'/>"
										<% 
									}
									else
									{
										%>
										src="<html:rewrite page='/equation/images/go.gif'/>"
										<% 
									}
								%>
							
								alt="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLGO")%>" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLGO")%>" border="0">
						</a>
					</td>					
					<td class="button" align="center">				
						<img src="<html:rewrite page='/equation/images/EqSpin.gif'/>" id="progressBarButton" style="padding-top:2px; visibility:hidden">
					</td>
						
					<% if (equationUser.isLanguageRTL()) { %>
					<td class="labelText" align="center" width="100%" id="fillerBar">
					<% } else { %>
					<td class="labelText" align="center" width="100%" id="fillerBar">
					<% } %>
					<div id="UnitWidgetContainer">
						<table id="UnitWidgetHeader">
								<tr>
								<td id='tdunitLabel' class="labelText" align="center">
								<span style="font-size:10pt" id="welcomeTitle">
								<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLUNIT")%>	
								&nbsp;
								<%=equationUnit.getUnitId()%>
								&nbsp;-&nbsp; 
								<%=equationUnit.getDescription()%>								
								</span>
								</td>
								</tr>
						</table>
					</div>	
					</td>
							
					<td id="tdswitchunitLabel" class="button" align="center" style="display:none">
						<a href="#" onclick="JavaScript:switchUnit()">
							<img src="<html:rewrite page='/equation/images/home.gif'/>" id="homeButton" border="0">
								<script type="text/JavaScript">
									if (RTL) document.getElementById('homeButton').title=getLanguageLabel("GBL000069").trim()+"=Alt+F2";
									else document.getElementById('homeButton').title="Alt+F2=" + getLanguageLabel("GBL000069");
									if (RTL) setFieldRTL('tdswitchunitLabel');
								</script>
						</a>
					</td>
					<td id="tdhelpLabel" class="button" align="center">
						<a href="JavaScript:dispHelp(null,'')">
							<img src="<html:rewrite page='/equation/images/help.gif'/>" id="helpButton" border="0">
								<script type="text/JavaScript">
									if (RTL) document.getElementById('helpButton').title=getLanguageLabel("GBLHELP").trim() + "=Alt+F1";
									else document.getElementById('helpButton').title="Alt+F1=" + getLanguageLabel("GBLHELP");
									if (RTL) setFieldRTL('tdhelpLabel');
								</script>
						</a>
					</td>				
					<td class="button" align="center" id="funcKeyToggle">
						<a href="JavaScript:toggleFuncKey('footerTexts','funcKeyToggle','funcKeyAnchor');" id="funcKeyAnchor">
							<img src="<html:rewrite page='/equation/images/EqFuncKey.gif'/>" border="0">
								<script type="text/JavaScript">
									document.getElementById('funcKeyToggle').title=getLanguageLabel("GBL000034").trim();
									setFuncKeyToolbarImage('funcKeyToggle');
									if (RTL) setFieldRTL('funcKeyToggle');
								</script>
						</a>
					</td>						
					<td id="tdslideLabel" class="button" align="center">
						<a href="JavaScript:slideHeader('sliderAnchor');" id="sliderAnchor">
							<img src="<html:rewrite page='/equation/images/up.gif'/>" id="slideButton" border="0">
								<script type="text/JavaScript">
									document.getElementById('slideButton').title=getLanguageLabel("GBL000029").trim();
									if (RTL) setFieldRTL('tdslideLabel');
								</script>
						</a>
					</td>						
					<td id="tdlogoutLabel" class="button" align="center">
						<a href='JavaScript:logoff();'>
							<img src="<html:rewrite page='/equation/images/EQExit.gif'/>" id="logoutButton" border="0">
								<script type="text/JavaScript">
									if (RTL) document.getElementById('logoutButton').title=getLanguageLabel("GBLLGOF").trim() + "=Alt+F3";
									else document.getElementById('logoutButton').title="Alt+F3=" + getLanguageLabel("GBLLGOF");
									if (RTL) setFieldRTL('tdlogoutLabel');
								</script>
						</a>
					</td>
				</tr>
		</table> 
	</body>	
	<script type="text/JavaScript">		
		// condition display of the home button on the desktop and the unit being Global Processing enabled.
		// Default is to not display the button.
		if (<%=isAGPSession.booleanValue()%>)
		{
			document.getElementById("tdswitchunitLabel").style.display = "";
		}
		else
		{
			document.getElementById("tdswitchunitLabel").style.display = "none";
		}
	
		var unitId = '<%=equationLogin.getUnitId()%>';	
		var mode = '*D';
		var userId = '<%=equationLogin.getUserId()%>';
		var system = '<%=equationLogin.getSystem()%>';		
		
		if (RTL) setFieldRTL('tdoptionLabel');
		if (RTL) setFieldRTL('tdgoLabel');
		//if (RTL) setFieldRTL('tdsearchLabel');
		if (RTL) setFieldRTL('tdunitLabel');
		
		function refreshHome()
		{
			 document.getElementById("UnitWidgetContainer").style.display = "";				 
	    }
		
	</script>	
</html:html>