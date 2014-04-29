<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<html:html>
	<head>
        <%
        	// right-to-left?
			boolean rtl = request.getParameter("rtl").equals("true");

        	// Set the style sheet
            if (rtl) 
            {
				%><link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetRTL(request)%>'/>" type="text/css"><%
			} 
			else 
			{
				%><link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetMain(request)%>'/>" type="text/css"><%
            }
        %>
        <link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetUserStyle(request)%>'/>" type="text/css">
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/spooledFileHeader.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/spooledFile.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/dt_key.js'/>"></script>
		
		<script>
        	// Retrieve the parameters
        	var RTL = <%=rtl%>;
        	var spoolName   = '<%=request.getParameter("spoolName")%>';
			var jobName     = '<%=request.getParameter("jobName")%>';
			var jobUser     = '<%=request.getParameter("jobUser")%>';
			var jobNumber   = '<%=request.getParameter("jobNumber")%>';
			var spoolNumber = '<%=request.getParameter("spoolNumber")%>';
			var totalPageNo = parseInt('<%=request.getParameter("totalPageNo")%>');
			var pagesize    = parseInt('<%=request.getParameter("pagesize")%>');
			var maxpagesize = parseInt('<%=request.getParameter("maxpagesize")%>');
		</script>
	</head>

	<body class="spooledFileHeaderBody" onload="spooledFileHeader_onLoad()" onresize="spooledFileHeader_onResize()">
		<table id="maintable" name="maintable" border="1" width="100%">
			<tr>
				<td width="20%">
					<div>
						<span id="spnPage" class="labelText"></span> &nbsp;
						<br></br>
						<span class="labelText" id="startingPage"></span> 
						<span class="labelText">/</span>	
						<span class="labelText" id="totalPage"><%=request.getParameter("totalPageNo")%></span>
						&nbsp;
					</div>
				</td>
				
				<td width="80%">
					<div>
						<span id="spnRange" class="labelText"></span>
						<input class="inputText" id='startPageNo' name='startPageNo' type="text" value="1"  size="7"
								onkeydown="startPageNo_onKeyDown()"
								>
							</input>
						<input class="inputText" id='endPageNo' name='endPageNo' type="text" value="1"  size="7"
								onkeydown="endPageNo_onKeyDown()"
								>
							</input>
						<input class="inputButton" id='cmdGoPageSelection' name='cmdGoPageSelection' type="button" 
								onclick="cmdGoPageSelection_onClick()">
							</input>
						<br></br>
						&nbsp;&nbsp; 
						<input class="inputButton" id='cmdGoPageSelectionFirst' name='cmdGoFirst' type="button" onclick="cmdGoPageSelectionFirst_onClick()"></input>
						<input class="inputButton" id='cmdGoPageSelectionPrev' name='cmdGoPrev' type="button" onclick="cmdGoPageSelectionPrev_onClick()"></input>
						<input class="inputButton" id='cmdGoPageSelectionNext' name='cmdGoNext' type="button" onclick="cmdGoPageSelectionNext_onClick()" ></input>
						<input class="inputButton" id='cmdGoPageSelectionLast' name='cmdGoLast' type="button" onclick="cmdGoPageSelectionLast_onClick()"></input>
						<input class="inputButton" id='cmdGoPageSelectionAll' name='cmdGoPageAll' type="button" 
								onclick="cmdGoPageSelectionAll_onClick()">
							</input>
					</div>
				</td>
			</tr>
		</table>
		
		<script>
		document.getElementById('spnPage').innerText = getLanguageLabel("GBL000382");
		document.getElementById('spnRange').innerText = getLanguageLabel("GBL000384");
		
		document.getElementById('cmdGoPageSelection').value = getLanguageLabel("GBLGO");
		document.getElementById('cmdGoPageSelectionAll').value = getLanguageLabel("GBLALL");
		document.getElementById('cmdGoPageSelectionFirst').value = getLanguageLabel("GBLFIRST");
		document.getElementById('cmdGoPageSelectionPrev').value = getLanguageLabel("GBLPREV");
		document.getElementById('cmdGoPageSelectionNext').value = getLanguageLabel("GBLNEXT");
		document.getElementById('cmdGoPageSelectionLast').value = getLanguageLabel("GBLLAST");

		// right-to-left?
		if (RTL)
		{
			document.body.dir="rtl";
		}
		</script>
		
	</body>		

</html:html>
