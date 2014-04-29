<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.utilities.Toolbox"%>

<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationStandardSession"%>
<%@page import="java.util.List"%>
<html:html>
	<%
	EquationLogin equationLogin = (EquationLogin) request.getSession().getAttribute("equationLogin");
	EquationUser equationUser = (EquationUser) request.getSession().getAttribute("equationUser");
 	%>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<title>Webfacing frame loader</title>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
   </head>
   
   <%
		String mode = request.getParameter("mode");
		String unit = request.getParameter("unit");
		String optionId = request.getParameter("optionId");
		String context = request.getParameter("context");
		String workstationId = request.getParameter("workstationId");
		String mainFhName = request.getParameter("mainFh");
		String gpUnit = request.getParameter("gpUnit");
		String gpSystem = request.getParameter("gpSystem");
		String originalUnit = equationLogin.getHomeUnit();
		String originalSystem = equationLogin.getHomeSystem();
	
		if(gpUnit != null && gpSystem != null)
		{ 
			if(!gpUnit.equals("") && !gpUnit.equals("null") && !gpSystem.equals("") && !gpSystem.equals("null"))
			{
				unit = gpUnit;
				List<EquationStandardSession> gpSessions = EquationCommonContext.getContext().getGlobalProcessingEquationStandardSessions(equationLogin.getSessionId());
				for(EquationStandardSession csession : gpSessions)
				{					
					if(csession.getUnitId().equals(gpUnit) && csession.getSystemId().equals(gpSystem))
					{
						EquationLogin login = new EquationLogin(equationLogin.getUserId(), equationLogin.getPassword(),gpUnit, gpSystem, equationLogin.getIpAddress(), csession.getSessionIdentifier());
						request.getSession().setAttribute("equationLogin", login );
						%>
						<script>					
							var gpUnit = '<%=gpUnit%>';
							var gpSystem = '<%=gpSystem%>';
							var toolbarFrame = getFrameToolbar();
							toolbarFrame.document.getElementById('welcomeTitle').innerText = '<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"globalprocessing.homeunit")%>' + ": " + '<%=originalSystem%>' +  "/" + '<%=originalUnit%>' + " - " + '<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"globalprocessing.nowviewing")%>' + ": "+ gpSystem + "/" + gpUnit;
						</script>
						<% 						 
					}
				}				
			}
		}
		// this field determines whether this WebFacing is invoked via drill down from an Equation service
		// so that upon exiting this WebFacing, we can redisplay the Equation service instead of the welcome page
		if (mainFhName == null)
		{
			mainFhName = "";
		}
		
		String dswsid1 = Toolbox.pad(equationLogin.getContextData1(),512);
		String dswsid2 = Toolbox.pad(equationLogin.getContextData2(),512);
   %>
   
   <script>
		var eqDriver = "X";
		var mainFhName = '<%=mainFhName%>';
		var gpUnit = '<%=gpUnit%>';
		var gpSystem = '<%=gpSystem%>';
		var originalSystem = '<%=originalSystem%>';
		// retrieve the webfacing frame
		var webfacingFrame = getFrameWebfacing();
		
		// load for the first time				
		if (webfacingFrame.eqDriver == "X" || webfacingFrame.eqDriver == null || (gpUnit != "null" && gpUnit != "" && gpSystem != "null" && gpSystem != originalSystem))
		{			
			webfacingFrame.location = '/' + getWebAppName() + "/equation/jsp/eqwfLogin.jsp?mode=" + '<%=mode%>' + "&unit=" + '<%=unit%>' + "&optionId=" + '<%=optionId%>' + "&context=" + '<%=context%>'+ "&workstationId=" + '<%=workstationId%>';
		}
		
		if (webfacingFrame.eqDriver == "Y")
		{
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLEMOD').value='<%=mode%>';							
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLUNIT').value='<%=unit%>';										
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLOPT').value='<%=optionId%>';										
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLCONT').value='<%=context%>';	
												
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLDS11').value='<%=dswsid1.substring(0,64)%>';										
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLDS12').value='<%=dswsid1.substring(64,128)%>';										
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLDS13').value='<%=dswsid1.substring(128,192)%>';										
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLDS14').value='<%=dswsid1.substring(192,256)%>';										
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLDS15').value='<%=dswsid1.substring(256,320)%>';										
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLDS16').value='<%=dswsid1.substring(320,384)%>';										
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLDS17').value='<%=dswsid1.substring(384,448)%>';										
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLDS18').value='<%=dswsid1.substring(448,512)%>';
													
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLDS21').value='<%=dswsid2.substring(0,64)%>';										
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLDS22').value='<%=dswsid2.substring(64,128)%>';										
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLDS23').value='<%=dswsid2.substring(128,192)%>';										
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLDS24').value='<%=dswsid2.substring(192,256)%>';										
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLDS25').value='<%=dswsid2.substring(256,320)%>';										
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLDS26').value='<%=dswsid2.substring(320,384)%>';										
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLDS27').value='<%=dswsid2.substring(384,448)%>';										
			webfacingFrame.document.getElementById('l1_W97HMDA$ZLDS28').value='<%=dswsid2.substring(448,512)%>';
													
			setTimeout( 
					function() 
					{
						webfacingFrame.validateAndSubmit('ENTER',webfacingFrame);										
					}
					, 50);
		}

   </script>
	<body>

	<%
		// load a default page on this frame
		if (!mainFhName.equals("EXCLUDED"))
		{
			%>
			<html:form action="/redisplayFH">
				<input id="name" name="name" value="<%=mainFhName%>" type="hidden" >
				<input id="removechild" name="removechild" value="Y" type="hidden" >
			</html:form>
			<html:errors bundle="equation"/>
		
			<script>
			function submit()
			{
				var form = document.getElementsByTagName('form')[0];
				form.submit();
			}
			function getName()
			{
				return "<%=mainFhName%>";
			}
			</script>
			<%
		}
	%>
	</body>
</html:html>