<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.misys.equation.common.utilities.Toolbox"%>
<%@page import="com.misys.equation.function.runtime.FunctionMessage"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.function.runtime.FunctionHandler"%>
<%@page import="com.misys.equation.function.runtime.FunctionHandlerData"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.function.runtime.FunctionHandlerTable"%>
<%@page import="com.misys.equation.function.context.EquationFunctionContext"%>
<%@page import="com.misys.equation.function.tools.FunctionRuntimeToolbox"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<%@page import="com.misys.equation.common.utilities.EqTimingTest"%>
<%@page import="com.misys.equation.function.tools.HTMLToolbox"%>
<%@page import="com.misys.equation.function.runtime.ScreenSet"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.common.access.EquationStandardSession"%>
<%@page import="com.misys.equation.common.access.IEquationStandardObject"%>
<%@page import="com.misys.equation.common.datastructure.EqDS_DSJOBE"%>
<%@page import="org.apache.log4j.Logger"%>
<%! static Logger LOG = Logger.getLogger("com.misys.equation.jsp.function.jsp"); %>
<html:html>
	<%
		EqDesktopToolBox.setupThreadData(request);
		EqTimingTest.printStartTime("Start of function.jsp","");
		EquationLogin equationLogin = (EquationLogin)request.getSession().getAttribute("equationLogin");
		EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
		EquationUnit equationUnit = (EquationUnit)request.getSession().getAttribute("equationUnit");
		
		// No Equation user, so something wrong with the server, display login page
		if (equationUser == null || equationUser.getSession() == null )
		{
			%>
				<jsp:forward page="../jsp/relogin.jsp">
				<jsp:param name="x" value=''/>
				</jsp:forward>
			<%
		}
		
		boolean resetGpFlags = false;
		//Have we come back here from drilling up from a GP enquiry? We need to restore the equationLogin
		if(!equationLogin.getUnitId().equals(equationUnit.getUnitId()) || !equationLogin.getSystem().equals(equationUnit.getSystem().getSystemId()))
		{				
			EquationLogin baseLogin = EquationCommonContext.getContext().getEquationLogin(equationUser.getSession().getSessionIdentifier());
			equationLogin = baseLogin;
			request.getSession().setAttribute("equationLogin", equationLogin);
			resetGpFlags = true;
			%>
			<script>
				var gpUnit = '<%=equationLogin.getUnitId()%>';
				var gpSystem = '<%=equationLogin.getSystem()%>';
				
				var toolbarframe = window.top.frames['loginFrame'];
				if (toolbarframe == null)
				{
					toolbarframe = window.top;
				}				
				var toolbarFrameFinal = toolbarframe.frames['toolbarFrame'];
			
				toolbarFrameFinal.document.getElementById('welcomeTitle').innerText = '<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"globalprocessing.homeunit")%>' + ": " + gpSystem +  "/" + gpUnit;
			</script>
			<%			
		}
		
		// parameters:
		// name - name of the FunctionHandler.  This identify whether it is the main window or a child window
		// control - determine whether restoring a session.  If null, then use the "optionId"
		// mode - mode sa per W97HMR's mode
		// unit - unit mnemonic
		// optionId - option to invoke.  If option is null, then it means it is redisplaying the details currently held in
		//				FunctionHandler
		// context - context similar to ZLSLT
		// dawsid1 - context data area 1
		// dawsid2 - context data area 2
		
		// .. when restoring from a session
		// sessionId - the session Id
		// transactionId - the transaction Id
		// userId - the user Id
		// scrnNo - the "current" screen number when the session was saved
		// screenSetId - the "current" screen set id when the session was saved
		// status - the current status of the session
		// authLevel - the authorisation level

		// .. when restoring from a LRP task
		// taskId - the task id
		// taskType - the task type
		
		// retrieve the name of the Function Handler to be processed
		String name = request.getParameter("name");
		if (name == null)
		{
			name = (String) request.getSession().getAttribute(FunctionRuntimeToolbox.UNIQUE_NAME);
		}
		if (name == null)
		{
			name = "";
		}
		FunctionHandlerTable functionHandlerTable = (FunctionHandlerTable)request.getSession().getAttribute(FunctionRuntimeToolbox.NAME);
		FunctionHandler functionHandler = functionHandlerTable.getFunctionHandler(name);
		
		// For Services in UXP:
		LOG.info("functionhandler name [" + name +"]");		
		if( functionHandler == null)
		{
			%>
			<jsp:forward page="../jsp/relogin.jsp?msg=1">
			<jsp:param name="x" value=''/>
			</jsp:forward>
			<%
		}
		FunctionHandlerData fhd = functionHandler.getFhd();
		
		if(resetGpFlags)
		{
			fhd.setGpSystem("");
			fhd.setGpUnit("");
		}
		
		// does this has parent?
		String mainFhName = "EXCLUDED";
		if (fhd.getParentFunctionHandler() != null)
		{
			mainFhName = name;
		}
		
		// Control attributes
		// 1 - Restore a work in progress session
		// 2 - Restore a session for the supervisor override
		// 3 - Restore a session that has been authorised/declined by the supervisor
		// 4 - Restore a LRP task
		// others - Invoke the function
		String control = request.getParameter("control");
		
		// Parameters
		String mode = request.getParameter("mode"); // only for webfacing function
		String unit = request.getParameter("unit"); // only for webfacing function
		String optionId = request.getParameter("optionId");
		String context = request.getParameter("context");
		String dawsid1 = null;
		if (request.getParameter("dawsid1")!=null)
		{
			dawsid1 = request.getParameter("dawsid1");
		}
		else
		{
			dawsid1 = Toolbox.pad(equationLogin.getContextData1(),512);
		}
		String dawsid2 = null;
		if (request.getParameter("dawsid2")!=null)
		{
			dawsid2 = request.getParameter("dawsid2");
		}
		else
		{
			dawsid2 = Toolbox.pad(equationLogin.getContextData2(),512);
		}

		// Session key and details
		String sessionId = request.getParameter("sessionId");
		String transactionId = request.getParameter("transactionId");
		String userId = request.getParameter("userId");
		int scrnNo = Toolbox.parseInt(request.getParameter("scrnNo"),0);
		int screenSetId = Toolbox.parseInt(request.getParameter("screenSetId"),0);
		String status = request.getParameter("status");
		String authLevel = request.getParameter("authLevel");
		String taskId = request.getParameter("taskId");
		String taskType = request.getParameter("taskType");
		
		// If Global Processing has been installed and we have a context set in search shutter
		// override context if none set on function call		
		if (equationUnit.hasGlobalProcessing())
		{
			dawsid1 = fhd.getGlobalProcessingContextHandler().getDswsid1Str();
			dawsid2 = fhd.getGlobalProcessingContextHandler().getDswsid2Str();
			equationLogin.setContextData1(dawsid1);
			equationLogin.setContextData2(dawsid2);
		}

		// Determine processing to take
		boolean ok = true;
		
		// Any other messages to display in addition to the function
		String errmsg = "";
		
		// No control pass?
		List<FunctionMessage> listMessages = new ArrayList<FunctionMessage>();
		if (control==null)
		{
			if(optionId == null || !equationUnit.isCustomOption(optionId))
			{
				ok = functionHandler.process(optionId,context,dawsid1,dawsid2);
				listMessages = new ArrayList<FunctionMessage>(fhd.getFunctionMsgManager().getOtherMessages().getMessages());
				if (optionId != null) // For the first call to a function we need to display any function errors here as FunctionForm isn't called
					listMessages.addAll(fhd.getFunctionMsgManager().getFunctionMessages().getMessages());
			}
			else
			{
				listMessages = fhd.getFunctionMsgManager().getFunctionMessages().getMessages();			
			}
		}
		
		// Restoring a session (work in progress)
		else if (control.equals("1"))
		{
			ok = functionHandler.processSession(optionId,sessionId,transactionId,userId);
			if (ok)
			{
				listMessages = fhd.getFunctionMsgManager().getFunctionMessages().getMessages();
			}
		}
		
		// Supervisor trying to process a request for authorisation
		else if (control.equals("2"))
		{
			ok = functionHandler.processSuper(optionId,sessionId,transactionId,userId,screenSetId,scrnNo);
			if (ok)
			{
				listMessages = fhd.getFunctionMsgManager().getFunctionMessages().getMessages();
			}
		}
			
		// User trying to access an authorised/declined transaction session
		else if (control.equals("3"))
		{
			ok = functionHandler.processOverride(optionId,sessionId,transactionId,userId,status,authLevel,screenSetId,scrnNo);
			if (ok)
			{
				listMessages = fhd.getFunctionMsgManager().getFunctionMessages().getMessages();
			}
		}
		
		// User trying to access a LRP task
		else if (control.equals("4"))
		{
			ok = functionHandler.processLRPTask(optionId,taskId,taskType);
			if (ok)
			{
				listMessages = fhd.getFunctionMsgManager().getFunctionMessages().getMessages();
				listMessages.addAll(fhd.getFunctionMsgManager().getOtherMessages().getMessages());
			}
		}

		// not ok, redirect to welcome page
		if (!ok)
		{
			errmsg = EqDesktopToolBox.functionMessageToString(fhd.getFunctionMsgManager().getOtherMessages().getMessages(),"'");
			%>
			<jsp:forward page="../jsp/welcome.jsp">
				<jsp:param name="errmsg" value="<%=errmsg%>"/>
				<jsp:param name="logoff" value="true"/>
			</jsp:forward>
			<%
		}

		// if ok, but webfacing, then redirect to the webfacing page
		if (fhd.isLegacyOption())
		{
			// If using CAS authentication and we require the user's iSeries password
			// forward to the getPassword page
			if (!EquationFunctionContext.getContext().isEquationAuthentication()
				&& !EquationFunctionContext.getContext().useCasCommonProfileForWebFacing()
				&& equationLogin.getPasswordType() != EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN)
			{

				%>
				<jsp:forward page="/equation/jsp/getPassword.jsp">
					<jsp:param name="mode" value="<%=mode%>"/>
					<jsp:param name="unit" value="<%=unit%>"/>
					<jsp:param name="user" value="<%=equationUser.getUserId()%>"/>
					<jsp:param name="option" value="<%=optionId%>"/>
					<jsp:param name="systemName" value="<%=equationLogin.getSystem()%>"/>
					<jsp:param name="context" value="<%=context%>"/>
					<jsp:param name="mainFhName" value="<%=mainFhName%>"/>
				</jsp:forward>
				<%
			}

			// this is triggerred when a legacy function is triggered via the menu 
			if (unit != null && !unit.equals("null"))
			{
				%>									
				<jsp:forward page="/equation/jsp/webfacing.jsp">
					<jsp:param name="mode" value="<%=mode%>"/>
					<jsp:param name="unit" value="<%=unit%>"/>
					<jsp:param name="optionId" value="<%=optionId%>"/>
					<jsp:param name="context" value="<%=context%>"/>
					<jsp:param name="mainFh" value="<%=mainFhName%>"/>
					<jsp:param name="workstationId" value="<%=fhd.getFunctionInfo().getWorkStationId()%>"/>
					<jsp:param name="gpUnit" value="<%=fhd.getGpUnit()%>"/>
					<jsp:param name="gpSystem" value="<%=fhd.getGpSystem()%>"/>	
				</jsp:forward>
				<%
			}
			// this is triggerred when transferring control from Equation service to legacy function 
			else
			{				
				%>
				
				<jsp:forward page="/equation/jsp/webfacing.jsp">
					<jsp:param name="mode" value="*D"/>
					<jsp:param name="unit" value="<%=fhd.getEquationUser().getEquationUnit().getUnitId()%>"/>
					<jsp:param name="optionId" value="<%=fhd.getOptionId()%>"/>
					<jsp:param name="context" value="<%=fhd.getCurrentDrillDownContext()%>"/>
					<jsp:param name="mainFh" value="<%=mainFhName%>"/>
					<jsp:param name="workstationId" value="<%=fhd.getFunctionInfo().getWorkStationId()%>"/>
					<jsp:param name="gpUnit" value="<%=fhd.getGpUnit()%>"/>
					<jsp:param name="gpSystem" value="<%=fhd.getGpSystem()%>"/>					
				</jsp:forward>
				<%
			}
		}
	      
		// current screen set
		ScreenSet screenSet = fhd.getScreenSetHandler().rtvScrnSetCurrent();
		
		// Retrieve the screen in HTML format
		String htmlString = functionHandler.rtvScrnSetHTML();
		
		// not ok, redirect to welcome page
		if (htmlString==null)
		{
			errmsg = EqDesktopToolBox.functionMessageToString(fhd.getFunctionMsgManager().getOtherMessages().getMessages(),"'");
			%>
			<jsp:forward page="../jsp/welcome.jsp">
				<jsp:param name="errmsg" value="<%=errmsg%>"/>
				<jsp:param name="logoff" value="true"/>
			</jsp:forward>
			<%
		}
		
		// if any reference text is not retrieved from HBXPF, don't load service
		if (!functionHandler.checkTextReferences(screenSet))
		{
			errmsg = EqDesktopToolBox.functionMessageToString(fhd.getFunctionMsgManager().getOtherMessages().getMessages(),"'");
			%>
			<jsp:forward page="../jsp/welcome.jsp">
				<jsp:param name="errmsg" value="<%=errmsg%>"/>
				<jsp:param name="logoff" value="true"/>
			</jsp:forward>
			<%
		}
		
		// retrieve login classic details
		int sessionType = EquationCommonContext.SESSION_CLASSIC_DESKTOP;	
		if (equationLogin != null)
		{
			sessionType = equationLogin.getSessionType();
		}		
	
		// retrieve user's language RTL property	
		boolean isLanguageRTL = false;
		if (equationUser != null)
		{
			isLanguageRTL = equationUser.isLanguageRTL();
		}       
    %>

	<HEAD>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<META http-equiv="Page-Enter" CONTENT="RevealTrans(Duration=0)">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
		<TITLE> Function area </TITLE>
		
		<% if (isLanguageRTL)
		{
			%><link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetRTL(request)%>'/>" type="text/css"><%
		} 
		else 
		{
			%><link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetMain(request)%>'/>" type="text/css"><%
		}
		%>
		<link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetUserStyle(request)%>'/>" type="text/css">
		<script language="JavaScript">
			var inpd = '<%=equationUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.INPD)%>';
			var unitDate = 	'<%=equationUnit.getProcessingDateCYYMMDD()%>';
			var screenDate = '<%=equationUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.ZDATE)%>';						
			var sessionType = <%=sessionType%>;
		</script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/constant.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/pvWS.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/msf.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/tabbar.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/buttonbar.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/bottombar.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/mainBody.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/pgminfo.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/ast.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/sel.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/s1b.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/opt.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/cal.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/frq.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/yni.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/list.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/function.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/dt_function.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/dt_event.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/dt_key.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/dt_sel.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/dt_help.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/validvalue.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/userexit_utilities.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/popup.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/windowProperties.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/pvUtils.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/childWindow.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/groupField.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/groupShutter.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/vopt.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/sort.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/predictiveList.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/customSelect.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/spooledfile.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/lrp.js'/>"></script>
		<script type="text/javascript">
			var startLoadTime = printTime("S", false);
			var submitted = false;			
			var isPV = false;
			var RTL = <%=isLanguageRTL%>;
			var RTLOrg = RTL;
			var myglobalvars = null;
			var webFacing = false;
			var eqDriver = 'N';
			var checker = <%=fhd.getSecurityLevel().getCheckerType()%>;
			var requiredChecker = <%=fhd.getSecurityLevel().getRequiredCheckerType()%>;
			var currentUser = '<%=EqDesktopToolBox.getDesktopLoginUser(request)%>';
			var unitId = '<%=fhd.getEquationUser().getEquationUnit().getUnitId()%>';
			var status = '<%=fhd.getFunctionSession().getStatus()%>';
			var isResubmitted = '<%=fhd.getFunctionSession().isResubmitted()%>';
			var jobDetails = '<%=equationUser.getSession().getConnectionWrapper().getJobId()%>';
			var nextAction = null;
			eqInit();
		</script>
		
		<!--[if IE 7]>
		<script type="text/javascript">
			var isIE7 = true;
		</script>
		<![endif]--> 		
		
		<%if (fhd.getNextAction() != null)
		{
		%>
				<script type="text/javascript">
				nextAction = new Object();
				nextAction.actionType = '<%=fhd.getNextAction().getActionType()%>';
				nextAction.optionType = '<%=fhd.getNextAction().getOptionType()%>';
				nextAction.optionId = '<%=fhd.getNextAction().getOptionId()%>';
				nextAction.optionDescription = '<%=fhd.getNextAction().getOptionDescription()%>';
				nextAction.context = '<%=fhd.getNextAction().getContext()%>'; 
				nextAction.control = '<%=fhd.getNextAction().getControl()%>';
				nextAction.sessionId = '<%=fhd.getNextAction().getSessionId()%>';
				nextAction.transactionId = '<%=fhd.getNextAction().getTransactionId()%>';
				nextAction.originalFullUser = '<%=fhd.getNextAction().getOriginalFullUser()%>';
				nextAction.status = '<%=fhd.getNextAction().getStatus()%>';
				nextAction.authorityLevel = '<%=fhd.getNextAction().getAuthorityLevel()%>';
				nextAction.screenSetId = '<%=fhd.getNextAction().getScreenSetId()%>';
				nextAction.screenNumber = '<%=fhd.getNextAction().getScreenNumber()%>';
				</script>
		<%		
				fhd.setNextAction(null);
				session.setAttribute("nextAction", null);
		}
		%>
	</HEAD>
	<body id = "functionBody" 
			class="inputBackground" 
			onload='setupMainBody()' 
			onresize='return dt_onresize(event)' 
			onhelp='return false' 
			onkeyup='return dt_bodyKeyUp(event)' 
			onkeydown='return dt_bodyKeyDown(event)' 
			onfocus='return dt_onfocus(event)'
			onclick='return bodyOnClick(event)'
			onmousedown='return bodySelectionMouseDown(event)'
			onmouseup='return bodySelectionMouseUp(event)'
			onmousemove='return bodySelectionMouseMove(event)'
			onpaste='return triggerPaste(getEventTarget(event))'
			<%=EqDesktopToolBox.getHTMLDirAttribute(isLanguageRTL)%>
			>
		<html:form action="/function">
			<span style="visibility: hidden; display: none" id="functionTitle"><%=screenSet.getLayout().rtvLabel(equationUser)%></span>
			<span style="visibility: hidden; display: none" id='$functionId'><%=screenSet.getFunction().getId()%>&nbsp;</span>
			<span style="visibility: hidden; display: none" id='$functionPluginVer' ><%=EqDesktopToolBox.getFunctionPluginVersion(fhd)%>&nbsp;</span>
			<span style="visibility: hidden; display: none" id='$functionMode' ><%=EqDesktopToolBox.getFunctionMode(fhd, equationUser)%>&nbsp;</span>
			<script type="text/javascript">setPgmName('$functionId');setPgmLevel('$functionPluginVer');setFuncMode('$functionMode'); </script>
			<%=htmlString%>
		</html:form>
		
		<div style="display:none;">
		<%
		List<FunctionMessage> listSecondLevel = new ArrayList<FunctionMessage>();
		listSecondLevel = fhd.getFunctionMsgManager().getFunctionMessages().getMessages();
		if (listSecondLevel.size()>0)
		{
			%>
			<div style="display:none;">
			<ul>
			<%
			for (int i=0; i<listSecondLevel.size(); i++)
			{
				%>
				<li id="secondLevelErrorParameterised"><%=Toolbox.rplSlashWith2Slash(Toolbox.rplQuote(Toolbox.rplSingleQuoteWithSlashSingleQuote(listSecondLevel.get(i).getSecondLevelText())))%></li>
				<%
			}
			%>
			</ul>
			</div>
			<%
		}
		
		// generate the list of messages to be displayed
		FunctionRuntimeToolbox.checkIncompleteMessage(equationUser,screenSet,listMessages);
		if (listMessages.size()>0)
		{
			%>
			<div id="errorHeader" style="display:none;"><hr><p class="errorHeader">Validation Error</p><p class="labelText">You must correct the following error(s) before proceeding:</p>
			<ul>
			<%
			for (int i=0; i<listMessages.size(); i++)
			{
				%>
				<li id="errorParameterised"><%=Toolbox.rplSlashWith2Slash(Toolbox.rplQuote(Toolbox.rplSingleQuoteWithSlashSingleQuote(listMessages.get(i).rtvUnformattedText())))%></li>
				<%
			}
			%>
			</ul>
			</div>
			<%
		}
		%>
		
		<html:errors bundle="equation"/>
		<input type="hidden" id="sel_numrec">
		<input type="hidden" id="sel_numcol">
		</div>
	</BODY>
	
</html:html>
<%
EqTimingTest.printTime("End of function.jsp","");
%>
