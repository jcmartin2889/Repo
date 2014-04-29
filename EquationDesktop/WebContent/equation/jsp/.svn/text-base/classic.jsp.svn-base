<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html:html>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<title>Equation User Interface</title>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
		<script type="text/javascript">
			var RTL = false;
			eqInit();
			setCookie('languageId','GB');
			setCookie('helpURL','');
			setCookie('optionID', '');
		</script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/language.js'/>"></script>
	</head>	
	<frameset id="inputFrameset" rows="26,*"  framespacing="0" border="0" frameborder="no" >
		<frame id="buttonBarFrame" name="buttonBarFrame" title="buttonBarFrame" src="<html:rewrite page='/equation/jsp/buttonbar.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" noresize="noresize" scrolling="no">
		<frameset rows="*,0" framespacing="3" framespacing="0" border="0" frameborder="no">
			<frame id="screenFrame" name="screenFrame" title="ScreenFrame" src="<html:rewrite page='/equation/jsp/eqwfWrap.jsp'/>" marginwidth="0" marginheight="0" frameborder="0" scrolling="yes">
			<frame id="blankFrame" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" noresize="noresize">
		</frameset>
	</frameset>
</html:html>
