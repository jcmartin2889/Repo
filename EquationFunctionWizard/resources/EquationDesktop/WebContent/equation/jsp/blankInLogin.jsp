<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html:html>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
		<title>blank jsp</title>
   		<script>
			autologout = true;
			function on_unload(e)
			{
				if (autologout)
				{
					autologout = false;
					logoffDesktopDuringLogin();
				}
			}
	
			function setAutoLogout(logout)
			{
				autologout = logout;
			}
		</script>
		
   </head>
	<body onunload="return on_unload(event)" >
	</body>

</html:html>