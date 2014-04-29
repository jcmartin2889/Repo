<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<html:html>
	<head>
	</head>
		
	<frameset id="spooledFileFrame" rows="0,*"  framespacing="0" border="0" frameborder="no">
		<frame id="spooledFileHeader" name="spooledFileHeader" title="spooledFileHeader" src='<html:rewrite page='/equation/jsp/spooledfileHeader.jsp'/><%=EqDesktopToolBox.getParameterAsURL(request)%>'  marginwidth="0" marginheight="0" frameborder="0" scrolling="yes">
		<frame id="spooledFileData" name="spooledFileData" title="spooledFileData" src=""  marginwidth="0" marginheight="0" frameborder="0" scrolling="yes">
	</frameset>
</html:html>
