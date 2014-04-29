<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<html:html>
	<%
	EquationLogin equationLogin = (EquationLogin)request.getSession().getAttribute("equationLogin");
	EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
	EquationUnit equationUnit = (EquationUnit)request.getSession().getAttribute("equationUnit");
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
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/eqWS.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/pvWS.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/function.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/funckeytext.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/msf.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/tabbar.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/mainBody.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/childWindow.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/popup.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/windowProperties.js'/>"></script>
		<script type="text/javascript">
			var RTL = <%=equationUser.isLanguageRTL()%>;
			eqInit();			
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
					</td>
					<td class="textInput" align="center">						
						<INPUT class="inputText" style="TEXT-TRANSFORM:uppercase" onkeypress="toolbarEnter(event)" id="contextInput" name="contextInput" type="text" size="30" maxlength="55">
					</td>
					<td id='tdgoLabel' class="button" align="center">
						<a href="javascript:routeToOption2('*D',unitId,optionInput.value,contextInput.value)" tabIndex='-1'>
							<img src="<html:rewrite page='/equation/images/go.gif'/>" alt="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLGO")%>" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLGO")%>" border="0">
						</a>
					</td>
					<td id='tdsearchLabel' class="labelText" align="center">
						<label id="searchLabel" for="optionSearch" accesskey="s"></label>
						<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL800002")%>
					</td>
					<td class="labelText" align="center">
						<input class="inputText" onkeypress="searchEnter(event)" onkeyup="searchUpDown(event)" name="optionSearch" id="optionSearch" />
					</td>
					<td class="button" align="center">
						<a href="javascript:getOptionList(document.getElementById('optionSearch').value,getFrameNavigatorView().document.getElementById('optionSearchResults')); switchMenu('SearchDiv')" tabIndex='-1'>
							<img src="<html:rewrite page='/equation/images/search.gif'/>" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLSEARCH")%>" id="optionSearchButton" border="0">
						</a>
					</td>
					<td class="button" align="center">				
						<img src="<html:rewrite page='/equation/images/EqSpin.gif'/>" id="progressBarButton" border="0" style="display:none">
					</td>	
					<td id='tdcontextSearch' class="labelText" align="center">
						<label id="contextSearch" for="BRNM" accesskey="E"></label>
							Set&nbsp;<u>E</u>nvironment&nbsp;&nbsp;
					</td>
                                <TD nowrap align="center">
                                	<INPUT
                                    class="wf_LTOR wf_ul wf_hi wf_default wf_field"
                                    id="BRNM"
                                    maxlength="4"
                                    name="BRNM"
                                    size="4.6"
                                    title="Branch">
									<SCRIPT type="text/javascript">setPromptButtonNoWF('Account', 'BRNM', 'SCR01R', '', '','', '', ['UNMNM','SCAB','SCAN','SCAS','NEEAN','SCSHN','SCACT','SCCCY'],['Unit','Branch','cus no','suffix','External account number','Short name','Type','Ccy'],['0','3','7','13','16','36','51','53'],['3','4','6','3','20','15','2','3'],0,13,'UNMNM,.*,0,3,N,N,A,N,SCAB,BRNM,3,4,N,N,A,N,SCAN,CPNC,7,6,N,N,A,N,SCAS,AS,13,3,N,N,A,N,NEEAN,.*,16,20,N,N,A,N,SCSHN,.*,36,15,N,N,A,N,SCACT,.*,51,2,N,N,A,N,SCCCY,.*,53,3,N,N,A,N,SCSHN,AS$$$Output,36,15,Y,N,A,N,SCAS,AS$$$DB,13,3,Y,N,A,N,SCAN,CPNC$$$DB,7,6,Y,N,A,N,SCAB,BRNM$$$DB,3,4,Y,N,A,N',['','SCAB','SCAN','SCAS','NEEAN','SCSHN','SCACT','SCCCY'],['','','','','','','',''],16);</SCRIPT>
                                <TD nowrap align="center">
                                	<INPUT align="middle"
                                    class="wf_LTOR wf_ul wf_hi wf_default wf_field"
                                    id="CPNC"
                                    maxlength="6" name="CPNC"
                                    size="6.9"
                                    title="Customer number">
 									<A style="width:2px;visibility:hidden; vertical-align:middle;" tabIndex="-1" href=""><IMG src="/equation/images/folly.gif" border="0"></A>
                                </TD>
                                <TD nowrap align="center">
                                	<INPUT
                                    class="wf_LTOR wf_ul wf_hi wf_default wf_field"
                                    id="AS"
                                    maxlength="3" name="AS"
                                    size="3.45"
                                    title="Suffix">
                                    <SCRIPT type="text/javascript">setPromptButtonNoWF('Account', 'AS', 'SCR01R', '', '','', '', ['UNMNM','SCAB','SCAN','SCAS','NEEAN','SCSHN','SCACT','SCCCY'],['Unit','Branch','cus no','suffix','External account number','Short name','Type','Ccy'],['0','3','7','13','16','36','51','53'],['3','4','6','3','20','15','2','3'],0,13,'UNMNM,unitSelect,0,3,N,N,A,N,SCAB,BRNM,3,4,N,N,A,N,SCAN,CPNC,7,6,N,N,A,N,SCAS,AS,13,3,N,N,A,N,NEEAN,.*,16,20,N,N,A,N,SCSHN,.*,36,15,N,N,A,N,SCACT,.*,51,2,N,N,A,N,SCCCY,.*,53,3,N,N,A,N,SCSHN,AS$$$Output,36,15,Y,N,A,N,SCAS,AS$$$DB,13,3,Y,N,A,N,SCAN,CPNC$$$DB,7,6,Y,N,A,N,SCAB,BRNM$$$DB,3,4,Y,N,A,N',['','SCAB','SCAN','SCAS','NEEAN','SCSHN','SCACT','SCCCY'],['','','','','','','',''],16);</SCRIPT>
                                </TD>
								<td class="button" align="center">
									<a href="javaScript:setContext(document.getElementById('unitSelect').value,document.getElementById('BRNM').value,document.getElementById('CPNC').value,document.getElementById('AS').value);" tabIndex='-1'>
										<img src="<html:rewrite page='/equation/images/go.gif'/>"  border="0">
									</a>
								</td>
								<td class="button" align="center">
									<a href="javaScript:resetContext(document.getElementById('unitSelect'),document.getElementById('BRNM'),document.getElementById('CPNC'),document.getElementById('AS'));" tabIndex='-1'>
										<img src="<html:rewrite page='/equation/images/remove.gif'/>"  border="0">
									</a>
								</td>
					<% if (equationUser.isLanguageRTL()) { %>
					<td class="labelText" align="left" width="100%" id="fillerBar">
					<% } else { %>
					<td class="labelText" align="right" width="100%" id="fillerBar">
					<% } %>
					<div id="UnitWidgetContainer">
						<table id="UnitWidgetHeader">
							<tr>
								<td id='tdunitLabel' class="labelText">
									<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLUNIT")%> 
									<SELECT name="unitSelect" id="unitSelect"></SELECT>	
									<td align="right">										
											<script type="text/javascript">
											   var units = new Array();
											   var unitString = '<%=equationUser.getValidUnits()%>';	
											   units=unitString.split('!_');											  
											   var selectEl = document.getElementById('unitSelect');
											   for(x=0;x<10;x++)
											   {
											   		if(units[x] != '' && units[x] != null)
											   		{
											   			appendOption(selectEl,units[x],units[x]);	
											   		}	
											   }
											   selectEl.value = '<%=equationLogin.getUnitId()%>';
											</script>										
									</td>
							</tr>
						</table>
					</div>	
					</td>										
					<td id="tdswitchunitLabel" class="button" align="center" id="refreshTD">
						<a href="javaScript:refresh(unitSelect.value);">
							<img src="<html:rewrite page='/equation/images/home.gif'/>" id="homeButton" border="0">
								<script type="text/javascript">
									if (RTL) document.getElementById('homeButton').title=getLanguageLabel("GBL000069").trim()+"=Alt+F2";
									else document.getElementById('homeButton').title="Alt+F2=" + getLanguageLabel("GBL000069");
									if (RTL) setFieldRTL('tdswitchunitLabel');
								</script>
						</a>
					</td>					
					<td id="tdhelpLabel" class="button" align="center">
						<a href="javascript:dispHelp(null,'')">
							<img src="<html:rewrite page='/equation/images/help.gif'/>" id="helpButton" border="0">
								<script type="text/javascript">
									if (RTL) document.getElementById('helpButton').title=getLanguageLabel("GBLHELP").trim() + "=Alt+F1";
									else document.getElementById('helpButton').title="Alt+F1=" + getLanguageLabel("GBLHELP");
									if (RTL) setFieldRTL('tdhelpLabel');
								</script>
						</a>
					</td>				
					<td class="button" align="center" id="funcKeyToggle">
						<a href="javaScript:toggleFuncKey('footerTexts','funcKeyToggle','funcKeyAnchor');" id="funcKeyAnchor">
							<img src="<html:rewrite page='/equation/images/EqFuncKey.gif'/>" border="0">
								<script type="text/javascript">
									document.getElementById('funcKeyToggle').title=getLanguageLabel("GBL000034").trim();
									setFuncKeyToolbarImage('funcKeyToggle');
									if (RTL) setFieldRTL('funcKeyToggle');
								</script>
						</a>
					</td>						
					<td id="tdslideLabel" class="button" align="center">
						<a href="javaScript:slideHeader('sliderAnchor');" id="sliderAnchor">
							<img src="<html:rewrite page='/equation/images/up.gif'/>" id="slideButton" border="0">
								<script type="text/javascript">
									document.getElementById('slideButton').title=getLanguageLabel("GBL000029").trim();
									if (RTL) setFieldRTL('tdslideLabel');
								</script>
						</a>
					</td>						
					<td id="tdlogoutLabel" class="button" align="center">
						<a href='javaScript:logoff();'>
							<img src="<html:rewrite page='/equation/images/EQExit.gif'/>" id="logoutButton" border="0">
								<script type="text/javascript">
									if (RTL) document.getElementById('logoutButton').title=getLanguageLabel("GBLLGOF").trim() + "=Alt+F3";
									else document.getElementById('logoutButton').title="Alt+F3=" + getLanguageLabel("GBLLGOF");
									if (RTL) setFieldRTL('tdlogoutLabel');
								</script>
						</a>
					</td>
				</tr>
		</table> 
	</body>	
	<script type="text/javascript">		
		var unitId = '<%=equationLogin.getUnitId()%>';	
		var mode = '*D';
		var userId = '<%=equationLogin.getUserId()%>';
		var system = '<%=equationLogin.getSystem()%>';		
		
		if (RTL) setFieldRTL('tdoptionLabel');
		if (RTL) setFieldRTL('tdgoLabel');
		if (RTL) setFieldRTL('tdsearchLabel');
		if (RTL) setFieldRTL('tdunitLabel');
		
		function refreshHome()
		{
			 document.getElementById("UnitWidgetContainer").style.display = "";				 
	    }
		
	</script>	
</html:html>