<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<html:html>
	<%
	EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
	%>
	
	<head>
		<META http-equiv="content-type" content="charset=UTF-8">		
		<META http-equiv="Content-Style-Type" content="text/css">
		<%			
			// retrieve user's language RTL property	
			boolean isLanguageRTL = false;
			if (equationUser != null)
			{
				isLanguageRTL = equationUser.isLanguageRTL();
			}
			if (isLanguageRTL) 
			{
				%><link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetRTL(request)%>'/>" type="text/css"><%
			} 
			else 
			{
				%><link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetMain(request)%>'/>" type="text/css"><%
			}
		%>
		<link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetUserStyle(request)%>'/>" type="text/css">
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/constant.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type='text/JavaScript' src="<html:rewrite page='/equation/scripts/maxmin.js'/>"></script>		
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/buttonbar.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/mainBody.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/error.js'/>"></script>
		<script type="text/javascript">
			var RTL = <%=isLanguageRTL%>;
			eqInit();
		
			if (getFrameMainFrame()!=null)
			{
				registerMaximizedChangedListener('input');
			}
			
			function toggleFrame()
			{
				// get to the frameset
				var p = parent;
				
				// no container, then no action
				if (getFrameMainFrame() == null)
				{
					return;
				}
				
				while (p && !p.toggleFrame)
				{
					p = p.parent;
				}
				
				if (p!= null)
				{
					p.toggleFrame('');
				}
				document.selection.clear;	
			}
			
			function on_unload(e)
			{
				// explorer?
				if (window.event)
				{
					e = window.event;
				}
				
				if (e.clientX <= 0 || e.clientY <= 0)
				{
					logoff_Processing();
					
					// now disable the autologout of the blank frame
					blankFrame = getFrameBlankFrame();
					if (blankFrame != null)
					{
						try
						{
							blankFrame.setAutoLogout(false);
						}
						catch(e)
						{
						}
					}
				}
			}

			function on_load(e)
			{
				// UXP session
				if (isUXP())
				{
					// Start error monitoring
					setTimeout(function ()
						{
							// start monitor of error when page has not been loaded 
							var frame = getFrameCurrent();
							if (frame.eqDriver != 'Y' && frame.eqDriver != 'N')
							{
								startLoadingTransaction();
							}
			   			}
		   				,100);


					// Remove the unload event.  In UXP, it will rely on BF logoff
					// callback mechanism to do the log-off
	   				document.body.onunload = null;
				}
			}
			
		</script>		  
	</head>
	<body class="bottombarframe" 
				onunload="return on_unload(event)" 
			    onload='on_load(event)'
			    <%=EqDesktopToolBox.getHTMLDirAttribute(isLanguageRTL)%> 
	>
		<div id="EqButtonbarDiv" class="ux_form_title">
		<table id="EqButtonbar" class="buttonbar" cellpadding="0" cellspacing="0">
		</table>
		</div>
	</body>
</html:html>