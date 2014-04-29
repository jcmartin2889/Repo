<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>

<html:html>
    <HEAD>
        <TITLE>
        </TITLE>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <META http-equiv="Content-Style-Type" content="text/css">
        <link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetMain(request)%>'/>" type="text/css">
        <link rel="stylesheet" href="<html:rewrite page='<%=EqDesktopToolBox.getStyleSheetUserStyle(request)%>'/>" type="text/css">
    </HEAD>
    <body>
        <form>
            <table id="confirmationTable" width="300px">
				<tr id="confirmationRowHeader">
					<td>
						<span id="confirmationTitle" class="modalHeaderText"></span>
					</td>
				</tr>
                <tr height="142" class="widgetBody">
	                <td width ="90%" >
	                    <span id="submittedLabel" > </span>
	                </td>
                </tr>
                <tr height="20" id="confirmationRowFooter">
                    <td  class="modalButtons" width ="90%" valign="middle">
                        <input type="button" id="okButton" class="inputButton" value="" onClick="window.returnValue=true;window.close();" >
                    </td>
                </tr>
            </table>
        </form>
        <script type="text/javascript">
	        var labels = window.dialogArguments;
            document.title = labels.makerchecker;
            document.getElementById('confirmationTitle').innerHTML = labels.makerchecker;
            document.getElementById('submittedLabel').innerHTML = labels.submitted;
            document.getElementById('okButton').value = labels.ok;
        </script>
    </body>   
</html:html>