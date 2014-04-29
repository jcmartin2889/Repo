<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@page import="com.misys.equation.common.access.EquationStandardSession"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.misys.equation.function.runtime.FunctionHandler"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.function.runtime.FunctionHandlerTable"%>
<%@page import="com.misys.equation.function.tools.FunctionRuntimeToolbox"%>
<%@page import="com.misys.equation.function.runtime.FunctionHandlerData"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<%@page import="com.misys.equation.common.utilities.EqTimingTest"%>
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.function.beans.NextAction"%>
<%@page import="org.apache.log4j.Logger"%>
<%! static Logger LOG = Logger.getLogger("com.misys.equation.jsp.welcome.jsp"); %>
<html:html>
	<%
	EqTimingTest.printStartTime("Start of welcome.jsp","");
	EquationLogin equationLogin = (EquationLogin)request.getSession().getAttribute("equationLogin");
	EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
	EquationUnit equationUnit = (EquationUnit)request.getSession().getAttribute("equationUnit");
	NextAction nextAction = (NextAction)request.getSession().getAttribute("nextAction");
	request.getSession().setAttribute("nextAction", null);
	%>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetMain(request)%>'/>" type="text/css">
		<link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetUserStyle(request)%>'/>" type="text/css">
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/constant.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/buttonbar.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/bottombar.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/mainBody.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/pgminfo.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/dt_event.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/dt_key.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/msf.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/tabbar.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/lrp.js'/>"></script>
		
		<%
		    HttpSession httpSession = request.getSession();
		    // no equser, so something wrong with the server, display login page
			if (equationUser == null || httpSession == null || equationUser.getSession() == null)
			{
				LOG.error("equationUser = [" + equationUser + "], httpSession = [" + httpSession + "]");
				%>
					<jsp:forward page="../jsp/relogin.jsp">
					<jsp:param name="x" value=''/>
					</jsp:forward>
				<%
			}
		%>
		<script type="text/javascript">
			var isPV = false;
			var RTL = false;
			var webFacing = true;
			var eqDriver = 'Y';
			var sessionType = <%=equationLogin.getSessionType()%>;
			eqInit();			
		</script>
			<%
				String name = request.getParameter("name");
				if (name == null)
				{
					name = (String)request.getSession().getAttribute(FunctionRuntimeToolbox.UNIQUE_NAME);
				}
				
			    if (name == null)
				{
					name = "";
				}

				if( LOG.isInfoEnabled())
				{
					LOG.info("welcome.jsp  Name = [" + name +"] nextAction = [" + nextAction + "]");
				}
				
				if (nextAction != null)
				{
			%>
 				<script type="text/javascript">
 				nextAction = new Object();
				nextAction.optionType = '<%=nextAction.getOptionType()%>';
				nextAction.optionId = '<%=nextAction.getOptionId()%>';
				nextAction.optionDescription = '<%=nextAction.getOptionDescription()%>';
				nextAction.context = '<%=nextAction.getContext()%>'; 
				if (nextAction != null)
				{
				 	window.top.equxp.routeToOption2(nextAction.optionType, nextAction.optionId, nextAction.optionDescription, nextAction.context);
				}
				</script>
			<%
				}
			    
				FunctionHandlerTable functionHandlerTable = (FunctionHandlerTable)request.getSession().getAttribute(FunctionRuntimeToolbox.NAME);
				FunctionHandler functionHandler = functionHandlerTable.getFunctionHandler(name);
				if (functionHandler != null)
				{
					FunctionHandlerData fhd = functionHandler.getFhd();
					if (!fhd.getOptionId().equals("") && fhd.isLegacyOption())
					{
						FunctionRuntimeToolbox.updateOptionInGH(fhd.getEquationUser().getSession(),"",fhd.getEquationUser().getUserId(),fhd.getFunctionInfo().getSessionId());
					}
				}
				else if (equationLogin.chkUXPUserInterface())
				{
					// Add a new function handler
					functionHandler = functionHandlerTable.addChild(name, EquationCommonContext.SESSION_FULL_DESKTOP);
				}
				
				// log-off session?
				String logoff = request.getParameter("logoff");
				if (logoff == null)
				{
					logoff="";
				}

				// refresh session? note: this is getAttribute() not getParameter because this is dynamically added (not part of the link)
				String refreshMain = (String) request.getSession().getAttribute(FunctionRuntimeToolbox.REFRESH_MAIN_WINDOW);
				if (refreshMain == null)
				{   
					refreshMain="";
				}

				String optionId = request.getParameter("optionId");
				
				// direct to a function via parameter
				if (optionId != null)
				{
					if (!logoff.equals("true") && !optionId.equals(""))
					{

						String control = request.getParameter("control");
						if( "4".equals( control))
						{
						LOG.info("welcome.jsp : Direct to LRP Task");
						%>
						<jsp:forward page="../jsp/function.jsp">
							<jsp:param name="taskId" value='<%=request.getParameter("taskId")%>'/>
							<jsp:param name="taskType" value='<%=request.getParameter("taskType")%>'/>
							<jsp:param name="control" value='<%=request.getParameter("control")%>'/>
						</jsp:forward>
						<%
						}
						else
						{
						%>
						<jsp:forward page="../jsp/function.jsp">
							<jsp:param name="mode" value='<%=request.getParameter("mode")%>'/>
							<jsp:param name="unit" value='<%=request.getParameter("unit")%>'/>
							<jsp:param name="optionId" value='<%=request.getParameter("optionId")%>'/>
							<jsp:param name="context" value='<%=request.getParameter("context")%>'/>
							<jsp:param name="name" value='<%=request.getParameter("name")%>'/>
						</jsp:forward>
						<%
						}
					}
				}
				
				// direct to a function via equation Desktop login
				if (functionHandler!= null && !logoff.equals("true") && equationLogin.getSessionType()==EquationCommonContext.SESSION_DIRECT_TRANS_DESKTOP)
				{
					LOG.info("welcome.jsp : Direct to function via EQD login");
					%>
					<jsp:forward page="../jsp/function.jsp">
						<jsp:param name="mode" value="*D"/>
						<jsp:param name="unit" value="<%=equationUnit.getUnitId()%>"/>
						<jsp:param name="optionId" value="<%=equationLogin.getOptId()%>"/>
						<jsp:param name="context" value="<%=equationLogin.getOptParm()%>"/>
					</jsp:forward>
					<%
				}
				LOG.info("welcome.jsp : NOT FORWARDING");
			%>
		
	</HEAD>
	<body id = "welcomeBody" 
			class="welcomeBackground"
			onhelp='return false'
			onload='return welcome_bodyOnLoad(event)' 
			onkeydown='return welcome_bodyKeyDown(event)'
			<%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> 
	>
	    <INPUT id="___nameElement" name="___nameElement" type="hidden" value="<%=name%>">
	    <INPUT id="___jobIdElement" name="___jobIdElement" type="hidden" value="<%=equationUser.getSession().getConnectionWrapper().getJobId()%>">
	    
		<span style="visibility:hidden;display:none;" id="welcomeTitle">Welcome to BankFusion Equation</span>
		<table>
			<tr>
				<td width="100%">
				</td>
				<td valign="middle">
					<a target="_blank" href="http://www.misys.com/">
						<img class="corporateImage" src="<html:rewrite page='/equation/images/MisysLogo.png'/>"/>
					</a>
				</td>
			</tr>
		</table>
		<div id="errorHeader">
		</div>

		<%
			// Retrieve parameters
			String errmsg = request.getParameter("errmsg");
			String clearmsg = request.getParameter("clearmsg");
			if (errmsg == null) errmsg = "";
			if (clearmsg == null) clearmsg = "";
			
			// write the completed task
			if (functionHandler != null)
			{
				FunctionHandlerData fhd = functionHandler.getFhd();
				%>
				<script>
				var completedTask = '<%=fhd.getCompletedTask()%>';
				</script>
				<%
				
				fhd.setCompletedTask("");
			}
			
		%>

		<script type="text/javascript">
			var refreshMain = '<%=refreshMain%>';

			document.getElementById('welcomeTitle').innerHTML = getLanguageLabel('GBL000097');
		
			setFunctionKeyButtons('','','welcomeTitle',''); 
			
			// adjust the row of the eqwfWrap frameset			
			var eqwfWrapFrameset = getFrameScreen().document.getElementById('eqwfWrapFrameset');
			eqwfWrapFrameset.setAttribute('rows','0,*,0');

			// adjust the col of the screen frameset
			var screenFrameset = getFrameScreen().document.getElementById('screenFrameset');
			screenFrameset.setAttribute("cols","100%,0%");
			
			try
			{
				gblToolbarFrame = getFrameToolbar();
				if (gblToolbarFrame != null)
				{
					gblToolbarFrame.document.getElementById('optionInput').readOnly=false;
					gblToolbarFrame.document.getElementById('contextInput').readOnly=false;
					gblToolbarFrame.document.getElementById('optionInput').focus();
					gblToolbarFrame.document.getElementById('optionInput').select();
				}
			}
			catch (e)
			{
			}
			
			function welcome_bodyOnLoad(e)
			{
				// When Equation desktop is called with a link to a transaction, ensure driver is not
				// displayed
				if (sessionType == SESSION_DIRECT_TRANS_DESKTOP)
				{
					setTimeout( 
						function() 
						{
							logoff_Processing();								
							window.top.location.replace("../equation/jsp/login.jsp");
							closeBrowser();
						}
						, 100);
					return;
				}
				
				// is this a child window and trying to refresh the main window?
				if (refreshMain == 'true')
				{
					if (window.top.opener != null)
					{
						window.top.opener.refreshEnquiry();
					}
				}

				// UXP mode, child window (which means Equation Services)
				var name = rtvFunctionHandlerName();
				if (isUXP() && name != '')
				{
					endLoadingTransaction();
					hideSpinnerButton();

					var errmsg = "<%=errmsg%>";
					if (errmsg.trim().length > 0)
					{
						displayUXPErrorMessage(errmsg);
					}

					closeEquationTab(name);
					return true;
				}
								
				// is this a child window?
				if (name != '')
				{
					closeBrowser();
					return true;
				}
				
				// clear the bottom bar
				var gblBottomBar = getFrameBottomBar();
				var obj = gblBottomBar.document.getElementById('EqBottomBarDiv');
				if (obj != null)
				{
					obj.innerHTML = '<table id="EqBottombarTable" cellpadding="0" cellspacing="0"></table>';
				}
				var obj = gblBottomBar.document.getElementById('functionKeys');
				if (obj != null)
				{
					obj.innerHTML = '';
				}
				
				// job id
				setTimeout( 
					function() 
					{
						<%
						// setup console message
						if (errmsg.equals(""))
						{
							if (!clearmsg.equals("false"))
							{
								%>
								setMsgCtl(null, null);
								<%
							}
						}
						else
						{
							%>
							setMsgCtl(<%=errmsg%>,"errorHeader");
							alertNewMsgCtl();
							<%
						}
						%>
						
						obj = document.getElementById(OBJ_JOBID);
						jobName = obj.value.substr(0,10).trim();
						jobUser = obj.value.substr(10,10).trim();
						jobNum = obj.value.substr(20).trim();
						var jobUpdated = false;
						if (!jobUpdated)
						{
							setTimeout( 
									function() 
									{
										try
										{
											setJobName(jobName,jobUser,jobNum);
										}
										catch (e)
										{
										}
									}
									, 1000);
						}

						// remove from workload list
						removeCompletedTask(completedTask);
						
						// Position cursor
						if (gblToolbarFrame != null)
						{
							gblToolbarFrame.document.getElementById('optionInput').focus();
							gblToolbarFrame.document.getElementById('optionInput').select();
						}
					}
					, 50);

				// remove popups in the console tab, put in a try-catch in case this frame
				// has not been loaded yet
				try
				{
					gblTabFrame = getFrameTab();
					gblConsoleFrame = gblTabFrame.frames['consoleFrame'];
					gblConsoleFrame.tabHidePopup();

					// hide busy icons
					pageLoading = false;
					endLoadingTransaction();
					hideSpinnerButton();

					gblButtonbarFrame = getFrameButtonBar();
					gblButtonbarFrame.endErrorMonitor();
				}
				catch (e)
				{
				}

				// lrp processing
				lrpInitialisation();

				// reset option id to blank
				setCookie('optionID','');
				
				return true;
			}

			function welcome_bodyKeyDown(e)
			{
				// F1 Help
				var keynum = rtvKeyboardKey(e);
				if (keynum==112  && !isShiftKey(e))
				{
					dispHelp();
					disableKeyboardKey(e);
					return false;
				} 
			}

		</script>
	</BODY>
</html:html>