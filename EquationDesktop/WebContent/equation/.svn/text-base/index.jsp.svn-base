<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html:html>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<title>Equation User Interface Initiation</title>
		<script type="text/javascript">
			var loginURL = window.location.pathname.substring(0,window.location.pathname.indexOf('index.jsp')) + 'jsp/loginMain.jsp' + getURLParameters(); 
			window.opener = window;
			window.open('','_parent',''); 
			window.open(loginURL,'_blank','status=1,toolbar=0,resizable=1, height=800px, width=1200px left=0px, top=0px ');

			function getURLParameters() 
			{
				var params = new String();
				
				var sURL = window.document.URL.toString();
				
				if (sURL.indexOf("?") > 0)
				{
					params = sURL.substring(sURL.indexOf("?"), sURL.length);
				}
				else
				{
					params = '';					
				}
				return params;
			}
			window.close();

			// this piece is code is copied from global.js
			function getCookieLogin(c_name)
			{
				if (document.cookie.length>0)
				{
					c_start=window.top.document.cookie.indexOf(c_name + "=");
					if (c_start!=-1)
					{ 
						c_start=c_start + c_name.length+1;
						c_end=window.top.document.cookie.indexOf(";",c_start);
						if (c_end==-1) 
						{
							c_end=window.top.document.cookie.length;
						}
						return unescape(window.top.document.cookie.substring(c_start,c_end));
					} 
				}
			}

			// This method is copied from global.js
			function getWindowPositionParameters()
			{
				// position the window on the last known position
				pageLeft = parseInt(getCookieLogin("windowLeft")); 
				pageTop = parseInt(getCookieLogin("windowTop"));
				if (!isNaN(pageLeft) && !isNaN(pageTop) && pageLeft >= 0 && pageTop >= 0)
				{
					return 'left=' + pageLeft + 'px' + ' top=' + pageTop + 'px';
				}
				return '';
			}
			
		</script>
	</HEAD>
</html:html>