<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<html:html>
	<HEAD>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
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
		
		<title>blank jsp</title>
	   <script type="text/javascript">
			var eqDriver = "X";
	   </script>
   </HEAD>
	<body>
	<br></br>
	<span class="labelText" id="spnText"></span>
	<img src="../images/EqSpin.gif" border="0">
	<script>
		var RTL = <%=rtl%>;
		var frame = window.top.frames['spooledFileHeader'];
		document.getElementById('spnText').innerText = frame.getLanguageLabel("GBL900087");

		// right-to-left?
		if (RTL)
		{
			document.body.dir="rtl";
		}
		
	</script>
	</body>
</html:html>