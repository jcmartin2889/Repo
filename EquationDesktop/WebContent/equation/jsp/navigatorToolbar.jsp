<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<html:html>
	<%
	EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
	EquationUnit equationUnit = (EquationUnit)request.getSession().getAttribute("equationUnit");
	%>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META name="GENERATOR" content="IBM Software Development Platform">
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
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type='text/JavaScript' src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type='text/JavaScript' src="<html:rewrite page='/equation/scripts/maxmin.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javascript">
			var RTL = <%=equationUser.isLanguageRTL()%>;
			eqInit();			
		</script>
		<script type="text/javascript">
			registerMaximizedChangedListener('navigationToolbar');
			function toggleFrame(frame)
			{
				// get to the frameset
				var p = parent;
				while (p && !p.toggleFrame)
				{
					p = p.parent;
				}
				if (p!= null)
				{
					p.toggleFrame(frame);
				}
				document.selection.clear();	
			}
		</script>
	</head>
	<body class="navigatorBody" 
	      <%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> 
	>
		<table class="buttonbar" cellpadding="0" cellspacing="0" height="100%">
				<tr>
					<td id="naviTitle" class="buttonBarTitle" align="center">
						<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000011")%>
					</td>
					<td class="buttonBarFiller" align="right" width="100%"></td>
					<td align="center" id="tdb_maximize_restore" class="button" height=18>
						<a href="javascript:restore_maximize('b1');" 
						   onmouseover="javascript:setWindowStatus('maximize_restore');return true;" 
						   onmouseout="window.status='';" id="b1">
						   <img src="<html:rewrite page='/equation/images/maximize.gif'/>" 
								alt=<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLMAXI")%>
								title=<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLMAXI")%>
								border="0"
								id="maximize_restore">
						</a>
					</td>
				</tr>
		</table> 
		<script type="text/javascript" >			
			if (RTL) setFieldRTL("naviTitle");
		</script>
	</body>
</html:html>