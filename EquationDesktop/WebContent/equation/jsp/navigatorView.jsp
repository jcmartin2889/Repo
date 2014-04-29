<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.misys.equation.ui.beans.EqMenu"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<%@page import="com.misys.equation.ui.helpers.EqNavigator"%>
<%@page import="com.misys.equation.ui.actions.SwitchToHomeUnitAction"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>
<%@page import="com.misys.equation.function.context.EquationFunctionContext"%>
<html:html>
    <%
    EquationLogin equationLogin = (EquationLogin)request.getSession().getAttribute("equationLogin");
    EquationUser equationUser = (EquationUser)request.getSession().getAttribute("equationUser");
    EquationUnit equationUnit = (EquationUnit)request.getSession().getAttribute("equationUnit");
    EqNavigator eqNavigator = (EqNavigator)request.getSession().getAttribute("eqNavigator");
    Boolean isAGPSession= (Boolean)request.getSession().getAttribute("isAGPSession");
    Boolean canSearch = equationUser.checkAuthorisedOption("DGS",false);
    %>
    <HEAD>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EDGE" />
        <title> <%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000011")%></title>
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
        
		<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/constant.js'/>"></script>
		<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/eqWS.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/workLoadWS.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/tree.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/function.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/msf.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/tabbar.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/spooledfile.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/session.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/popup.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/dt_function.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/dt_key.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/navigator.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/buttonbar.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/predictiveList.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/globalprocessingconsole.js'/>"></script>
        <script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/lrp.js'/>"></script>
        <script type="text/javascript">        
            var numNaviBar = 0;
            var RTL = <%=equationUser.isLanguageRTL()%>;
            var RTLOrg = RTL;
            eqInit();       
            var unitId = '<%=equationLogin.getUnitId()%>';  
            var mode = '*D';
            var webFacUser = '<%=equationLogin.getUserId()%>';
        </script>
    </head>
    <%
    final String dataArea1 = (String)request.getSession().getAttribute(SwitchToHomeUnitAction.DATAAREA_KEY);
    final String dataArea2 = (String)request.getSession().getAttribute(SwitchToHomeUnitAction.DATAAREA_KEY2);
    final String optionInput = (String)request.getSession().getAttribute("optionInput"); 
    if((dataArea1 != null && dataArea1.trim().length() > 0) || (dataArea2 != null && dataArea2.trim().length() > 0))
    {
        if(optionInput !=null && !optionInput.equals(""))
        {
           %>   
             <body id="navi"
             	   class="navi" 
             	   onresize='navi_resize()' 
             	   onkeyup='return bodyKeyUp(event)' 
             	   onkeydown='return bodyKeyDown(event)' 
             	   onload="JavaScript:getNaviInfoFromServDir('getCurrentContext','ContextDiv','eqContext');switchMenu('ContextDiv','getCurrentContext','eqContext');routeToOption('D*','<%=equationLogin.getUnitId()%>','<%=optionInput%>','');"
             	   <%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> 
             	   >
           <%       
            request.getSession().removeAttribute("optionInput"); 
        }
        else
        {
        %>
            <body id="navi"
             	class="navi"  
            	onresize='navi_resize()' 
            	onkeyup='return bodyKeyUp(event)' 
            	onkeydown='return bodyKeyDown(event)' 
            	onload="JavaScript:switchMenu('ContextDiv','getCurrentContext','eqContext');"
            	<%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> 
            	>
        <%          
        }
    }
    else
    {
        if(optionInput !=null && !optionInput.equals(""))
        {
           %>
          <body id="navi"
             	class="navi"  
          		onresize='navi_resize()' 
          		onkeyup='return bodyKeyUp(event)' 
          		onkeydown='return bodyKeyDown(event)' 
          		onload="JavaScript:routeToOption('D*','<%=equationLogin.getUnitId()%>','<%=optionInput%>','');"
          		<%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> 
          		>
        <%      
            request.getSession().removeAttribute("optionInput");
        }
        else
        {
        %>
        <body id="navi"
            class="navi"  
        	onresize='navi_resize()'
        	<%=EqDesktopToolBox.getHTMLDirAttribute(equationUser.isLanguageRTL())%> 
        	>
        <%          
        }
    }
    %>  
        <%
            /*  Menus */
            EqMenu eqMenu = eqNavigator.getEqMenu();        
        %>
        <table id="SearchDivHeader" class="navigationbar" cellpadding="0" cellspacing="0" height="100%" width="100%" ondblclick="JavaScript:switchMenu('SearchDiv');" style="display:none;">
            <tr>
                <td id="SearchDivTitle" class="labelText" align="left">
                    <%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLSEARCH")%>
                </td>               
                <td align="right" width="100%"></td>
       
                <td id="SearchDivUnitStatus" align="center" class="button" style="display:none;" height="18">
                    <a href="JavaScript:routeToOption2('*D',unitId,'EUS','')" tabIndex='-1'>
                   		<img src="../images/view.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLSEARCH") +" "+ EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLUNIT")%>" border="0">
               		</a>
                </td>
                
                <td id="SearchDivCollapse" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:collapseTree('eqRecentSearchesLink');">
                       <img src="../images/collapseall.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000035")%>" border="0">
                    </a>
                </td>
       
                <td id="SearchDivExpand" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:expandTree('eqRecentSearchesLink');">
                       <img src="../images/expandall.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000036")%>" border="0">
                    </a>
                </td>
       
                <td id="SearchDivReset" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:resetCurrentContext();">
                       <img src="../images/delete.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL900074")%>" border="0">
                    </a>
                </td>
       
                <td id="SearchDivProgressBar" align="center" class="navigationButtonSelected" style="display:none;">
                    <img src="../images/EqSpin.gif" border="0">
                </td>           
       
                <td id="SearchDivRefresh" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:getNaviInfoFromServDir('getRecentSearches','SearchDiv','eqRecentSearches',true);">
                       <img src="../images/refresh.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLREFR")%>" border="0">
                    </a>
                </td>
              
                <td id="SearchDivSwitch" align="center" class="button" height="18">
                    <a href= "JavaScript:switchMenu('SearchDiv','getRecentSearches','eqRecentSearches',true);" id="SearchDivAnchor">
                       <img id="SearchDivSwitchImg" src="../images/down.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000021")%>" border="0">
                    </a>
                </td>
            </tr>
        </table>        
        <div style="display:none; overflow:scroll; width:100%;" id="SearchDiv"> 
            <table id="optionSearchResults" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                        <td id="tdsearchOptionLabel">
                        <%
                        if (isAGPSession.booleanValue())
                        {
                            %>
                            <%@ include file="globalprocessingconsole.jsp" %>
                            <%
                        }
                        %>

                        <table  id="ContextTable">
                            <tr>
                                <td>
                                    <ul id="eqContext">
                                    </ul>
                                </td>
                            </tr>
                        </table>
                        <table id="OutputTable">
                            <tr>
                                <td class="labelText">
                                    <%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000086")%>
                                    <input type="text" class="inputText" style="TEXT-TRANSFORM:uppercase" id="outputRecentFilterText" name="outputRecentFilterText" 
                                        onkeydown="navi_onkeydown(event,'outputRecentFilterText','eqRecentSearches','eqRecentSearchesFilterOutput','SearchDivProgressBar')" 
                                        onkeyup="navi_onkeyup(event,'outputRecentFilterText','eqRecentSearches','eqRecentSearchesFilterOutput','SearchDivProgressBar')" > 
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <ul id="eqRecentSearchesFilterOutput">
                                    </ul>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <ul id="eqRecentSearchesLink">
                                        <li title="EC1"><%=EquationCommonContext.getContext().getLanguageResource(equationUser,"search.tree.title")%>
                                            <ul id="eqRecentSearches">
                                            </ul>
                                        </li>
                                    </ul>
                                </td>
                            </tr>
                        </table>
                        </td>
                    </tr>
            </table>
        </div>
            <!-- Format the searches tree -->
            <script type="text/JavaScript" >
                if (RTL)
                {
                    setFieldRTL('SearchDivTitle');
                }
                if ((<%=isAGPSession.booleanValue()%>) && (<%=canSearch.booleanValue()%>))
                {
	                numNaviBar = numNaviBar + 1;
                }
                x= formatTree($("eqRecentSearchesLink"));
          </script>

        <script type="text/javascript">
            // condition display of the search shutter on the desktop and the unit being Global Processing enabled.
            // Default is to not display the shutter.           
            if ((<%=isAGPSession.booleanValue()%>) && (<%=canSearch.booleanValue()%>))
            {
                $("SearchDivHeader").style.display = "";
            }
            else
            {
                $("SearchDivHeader").style.display = "none";
            }
        </script>
        
        <% 
        // Change password is allowed only if non-CAS
        if (!EquationCommonContext.isCASAuthentication())
        {
        %>
     	<table id="AccountMDivHeader" class="navigationbar" cellpadding="0" cellspacing="0" height="100%" width="100%" ondblclick="JavaScript:switchMenu('AccountMDiv');">
            <tr>
                <td id="AccountMDivTitle" class="labelText" align="left">
                    <%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLACTMNT")%>
                </td>               
                <td align="right" width="100%"></td>
                <td id="AccountMaintenanceDivProgressBar" align="center" class="button" style="display:none;">
                    <img src="../images/EqSpin.gif" border="0">
                </td>           
                <td id="SystemMenuDivSwitch" align="center" class="button" height="18">
                    <a href= "JavaScript:switchMenu('AccountMDiv');" id="AccountMDivAnchor">
                       <img id="AccountMDivSwitchImg" src="../images/down.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000021")%>" border="0">
                    </a>
                </td>
            </tr>
        </table>
        <div style="display: none; overflow:scroll; width:100%;" id="AccountMDiv">
            <table id="AccountMTable">
            	<tr>
                    <td>
                        <ul id="AccountMList">
                            <li>
                                <a href="JavaScript:changePassword();">
                                <%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLCHGPAS")%>
                                </a>
                            </li>
                        </ul>
                    </td>
                </tr>
            </table>
             <!-- Format the recent menu tree -->
            <script type="text/JavaScript" >
                if (RTL) 
                {
                	setFieldRTL('AccountMDivTitle');
                }
                numNaviBar = numNaviBar + 1;
                x= formatTree($("AccountMList"));
            </script>
        </div>
        <% 
        }
        %>
        
        <table id="RecentDivHeader" class="navigationbar" cellpadding="0" cellspacing="0" height="100%" width="100%" ondblclick="JavaScript:switchMenu('RecentDiv','getRecentOptionHTML','eqRecentOptions');">
            <tr>
                <td id="RecentDivTitle" class="labelText" align="left">
                    <%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLRECENT")%>
                </td>               
                <td align="right" width="100%"></td>
                <td id="RecentDivProgressBar" align="center" class="button" style="display:none;">
                    <img src="../images/EqSpin.gif" border="0">
                </td>           
                <td id="RecentDivRefresh" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:getNaviInfoFromServDir('getRecentOptionHTML','RecentDiv','eqRecentOptions');">
                       <img src="../images/refresh.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLREFR")%>" border="0">
                    </a>
                </td>
                <td id="RecentDivSwitch" align="center" class="button" height="18">
                    <a href= "JavaScript:switchMenu('RecentDiv','getRecentOptionHTML','eqRecentOptions');" id="RecentDivAnchor">
                       <img id="RecentDivSwitchImg" src="../images/down.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000021")%>" border="0">
                    </a>
                </td>
            </tr>
        </table>
        <div style="display:none; overflow:scroll; width:100%;" id="RecentDiv">
            <table  id="RecentTable">
                <tr>
                    <td>
                        <ul id="eqRecentOptions">
                        </ul>
                    </td>
                </tr>
            </table>
            <!-- Format the recent menu tree -->
            <script type="text/JavaScript" >
                if (RTL) 
                {
                	setFieldRTL('RecentDivTitle');
                }
                numNaviBar = numNaviBar + 1;
                x= formatTree($("eqRecentOptions"));
            </script>
        </div>
        
        <%/*  Prime Menu */ %>
        <table id="PrimeMenuDivHeader" style="display:none;" class="navigationbar" cellpadding="0" cellspacing="0" height="100%" width="100%" ondblclick="JavaScript:switchMenu('PrimeMenuDiv');">
            <tr>
                <td id="PrimeMenuDivTitle" class="labelText" align="left">
                    <%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000003")%>
                </td>
                <td align="right" width="100%"></td>
                <td id="PrimeMenuDivCollapse" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:collapseTree('equationUserMenu');">
                       <img src="../images/collapseall.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000035")%>" border="0">
                    </a>
                </td>
                <td id="PrimeMenuDivExpand" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:expandTree('equationUserMenu');">
                       <img src="../images/expandall.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000036")%>" border="0">
                    </a>
                </td>
                <td id="PrimeMenuDivSwitch" align="center" class="button" height="18">
                    <a href= "JavaScript:switchMenu('PrimeMenuDiv');" id="PrimeMenuDivAnchor">
                       <img id="PrimeMenuDivSwitchImg" src="../images/down.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000021")%>" border="0">
                    </a>
                </td>
            </tr>
        </table>
        <div style="display: none; overflow:scroll; width:100%;" id="PrimeMenuDiv">
            <table id="PrimeMenuTable">
                <tr>
                    <td>
                        <ul id="equationUserMenu">
                            <%=eqMenu.getUserMenuHTML()%>
                        </ul>
                    </td>
                </tr>
            </table>
            <!-- Format the prime menu tree -->
            <script type="text/JavaScript" >            
                if (RTL)
                {
                	setFieldRTL('PrimeMenuDivTitle');
                }
                numNaviBar = numNaviBar + 1;
                x = formatTree($("equationUserMenu"));
                if(x == 0)
                {
                    $("PrimeMenuDivHeader").style.display = "";
                }
                else
                {
                    $("PrimeMenuDivHeader").style.display = "none";
                }
            </script>
        </div>

        <% /*  All Menus */ %>

        <table id="AllMenuDivHeader" class="navigationbar" cellpadding="0" cellspacing="0" height="100%" width="100%" ondblclick="JavaScript:switchMenu('AllMenuDiv','getFullMenuHTML','eqFullMenu');">
            <tr>
                <td id="AllMenuDivTitle" class="labelText" align="left">
                    <%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000004")%>
                </td>
                <td align="right" width="100%"></td>
                <td id="AllMenuDivProgressBar" align="center" class="button" style="display:none;">
                    <img src="../images/EqSpin.gif" border="0">
                </td>           
                <td id="AllMenuDivCollapse" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:collapseTree('eqFullMenu');">
                       <img src="../images/collapseall.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000035")%>" border="0">
                    </a>
                </td>
                <td id="AllMenuDivExpand" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:expandTree('eqFullMenu');">
                       <img src="../images/expandall.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000036")%>" border="0">
                    </a>
                </td>
                <td id="AllMenuDivSwitch" align="center" class="button" height="18">
                    <a href= "JavaScript:switchMenu('AllMenuDiv','getFullMenuHTML','eqFullMenu');" id="AllMenuDivAnchor">
                       <img id="AllMenuDivSwitchImg" src="../images/down.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000021")%>" border="0">
                    </a>
                </td>
            </tr>
        </table>
        <div style="display: none; overflow:scroll; width:100%;" id="AllMenuDiv">
            <table id="AllMenuTable">
                <tr>
                    <td>
                        <ul id="eqFullMenu">
                        </ul>
                    </td>
                </tr>
            </table>
            <!-- Format the full menu tree -->
            <script type="text/JavaScript" >            
                if (RTL)
                {
                	setFieldRTL('AllMenuDivTitle');
                }
                numNaviBar = numNaviBar + 1;
                x= formatTree($("eqFullMenu"));
            </script>
        </div>

        <% /*  System Menu */ %>

        <table id="SystemMenuDivHeader" style="display:none;" class="navigationbar" cellpadding="0" cellspacing="0" height="100%" width="100%" ondblclick="JavaScript:switchMenu('SystemMenuDiv');">
            <tr>
                <td id="SystemMenuDivTitle" class="labelText" align="left">
                    <%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000005")%>
                </td>
                <td align="right" width="100%"></td>
                <td id="SystemMenuDivCollapse" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:collapseTree('eqControlMenu');">
                       <img src="../images/collapseall.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000035")%>" border="0">
                    </a>
                </td>
                <td id="SystemMenuDivExpand" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:expandTree('eqControlMenu');">
                       <img src="../images/expandall.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000036")%>" border="0">
                    </a>
                </td>
                <td id="SystemMenuDivSwitch" align="center" class="button" height="18">
                    <a href= "JavaScript:switchMenu('SystemMenuDiv');" id="SystemMenuDivAnchor">
                       <img id="SystemMenuDivSwitchImg" src="../images/down.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000021")%>" border="0">
                    </a>
                </td>
            </tr>
        </table>
        <div style="display: none; overflow:scroll; width:100%;" id="SystemMenuDiv">
            <table id="SystemMenuTable">
                <tr>
                    <td>
                        <ul id="eqControlMenu">
                            <%=eqMenu.getControlMenuHTML()%>
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td>
                        <ul id="systemLinks">
	                        <%if(eqMenu.isAuthSuspED()){%>
                            <li>
                                <a id="killPoolsLink" href= "JavaScript:destroyPools('destroyPools');">
                                    <script type="text/javascript">
                                        $('killPoolsLink').innerHTML=getLanguageLabel("GBL000072");
                                        $('killPoolsLink').title=getLanguageLabel("GBL000072");
                                    </script>
                                </a>
                            </li>
    	                    <%}%>
    	                    <%if(eqMenu.isAuthRefreshCache()){%>
                            <li>
                                <a id="refreshCacheLink" href= "JavaScript:refreshDesktopCache();">
                                    <script type="text/javascript">
                                        $('refreshCacheLink').innerHTML=getLanguageLabel("GBL000381");
                                        $('refreshCacheLink').title=getLanguageLabel("GBL000381");
                                    </script>
                                </a>
                            </li>
	                        <%}%>
                        </ul>
                    </td>
                </tr>
            </table>
            <!-- Format the system menu tree -->
            <script type="text/JavaScript" >
                if (RTL) 
                {
                	setFieldRTL('SystemMenuDivTitle');
                }
                numNaviBar = numNaviBar + 1;
                x= formatTree($("eqControlMenu"));
                z= formatTree($("systemLinks"));
                if(x==0 || z==0)
                {
                    $("SystemMenuDivHeader").style.display = "";
                }
                else
                {
                    $("SystemMenuDivHeader").style.display = "none";
                }
            </script>
        </div>

		<% /*  User's spooled file*/ %>
        <table id="OutputDivHeader" class="navigationbar" cellpadding="0" cellspacing="0" height="100%" width="100%" ondblclick="JavaScript:switchMenu('OutputDiv','getUserSpoolFilesHTML','eqOutput');">
            <tr>
                <td id="OutputDivTitle" class="labelText" align="left">
                    <%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000063")%>
                </td>
                <td align="right" width="100%"></td>
                <td id="OutputDivProgressBar" align="center" class="button" style="display:none;">
                    <img src="../images/EqSpin.gif" border="0">
                </td>           
                <td id="OutputDivRefresh" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:getNaviInfoFromServDir('getUserSpoolFilesHTML','OutputDiv','eqOutput');">
                       <img src="../images/refresh.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLREFR")%>" border="0">
                    </a>
                </td>
                <td id="OutputDivCollapse" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:collapseTree('eqOutput');">
                       <img src="../images/collapseall.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000035")%>" border="0">
                    </a>
                </td>
                <td id="OutputDivExpand" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:expandTree('eqOutput');">
                       <img src="../images/expandall.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000036")%>" border="0">
                    </a>
                </td>
                <td id="OutputDivSwitch" align="center" class="button" height="18">
                    <a href= "JavaScript:switchMenu('OutputDiv','getUserSpoolFilesHTML','eqOutput');" id="OutputDivAnchor">
                       <img id="OutputDivSwitchImg" src="../images/down.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000021")%>" border="0">
                    </a>
                </td>
            </tr>
        </table>
        <div style="display: none; overflow:scroll; width:100%;" id="OutputDiv">
        <table id="OutputTable">
            <tr>
                <td class="labelText">
                    <%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000086")%>
                    <input type="text" class="inputText" style="TEXT-TRANSFORM:uppercase" id="outputFilterText" name="outputFilterText" 
                        onkeydown="navi_onkeydown(event,'outputFilterText','eqOutput','eqFilterOutput','OutputDivProgressBar')" 
                        onkeyup="navi_onkeyup(event,'outputFilterText','eqOutput','eqFilterOutput','OutputDivProgressBar')" > 
                </td>
            </tr>
            <tr>
                <td>
                    <ul id="eqFilterOutput">
                    </ul>
                </td>
            </tr>
            <tr>
                <td>
                    <ul id="eqOutput">
                    </ul>
                </td>
            </tr>
        </table>
        </div>
        <script type="text/JavaScript" >
            if (RTL) 
            {
            	setFieldRTL('OutputDivTitle');
            }
            numNaviBar = numNaviBar + 1;
        </script>

        <% /*  Units spooled files */ %>
        
        <% if (eqMenu.isAuthUnitSpool()) {  %>

        <table id="UnitSpoolDivHeader" class="navigationbar" cellpadding="0" cellspacing="0" height="100%" width="100%" ondblclick="JavaScript:switchMenu('UnitSpoolDiv','getUnitSpoolFilesHTML','eqUnitSpool');">
            <tr>
                <td id="UnitSpoolDivTitle" class="labelText" align="left">
                    <%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000064")%>
                </td>
                <td align="right" width="100%"></td>
                <td id="UnitSpoolDivProgressBar" align="center" class="button" style="display:none;">
                    <img src="../images/EqSpin.gif" border="0">
                </td>           
                <td id="UnitSpoolDivRefresh" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:getNaviInfoFromServDir('getUnitSpoolFilesHTML','UnitSpoolDiv','eqUnitSpool');">
                       <img src="../images/refresh.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLREFR")%>" border="0">
                    </a>
                </td>
                <td id="UnitSpoolDivCollapse" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:collapseTree('eqUnitSpool');">
                       <img src="../images/collapseall.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000035")%>" border="0">
                    </a>
                </td>
                <td id="UnitSpoolDivExpand" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:expandTree('eqUnitSpool');">
                       <img src="../images/expandall.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000036")%>" border="0">
                    </a>
                </td>
                <td id="UnitSpoolDivSwitch" align="center" class="button" height="18">
                    <a href= "JavaScript:switchMenu('UnitSpoolDiv','getUnitSpoolFilesHTML','eqUnitSpool');" id="UnitSpoolDivAnchor">
                       <img id="UnitSpoolDivSwitchImg" src="../images/down.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000021")%>" border="0">
                    </a>
                </td>
            </tr>
        </table>
        <div style="display: none; overflow:scroll; width:100%;" id="UnitSpoolDiv">
        <table id="UnitSpoolTable">
            <tr>
                <td class="labelText">
                    <%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000086")%>
                    <input type="text" class="inputText" style="TEXT-TRANSFORM:uppercase" id="unitSpoolFilterText" name="unitSpoolFilterText" 
                        onkeydown="navi_onkeydown(event,'unitSpoolFilterText','eqUnitSpool','eqFilterUnitSpool','UnitSpoolDivProgressBar')" 
                        onkeyup="navi_onkeyup(event,'unitSpoolFilterText','eqUnitSpool','eqFilterUnitSpool','UnitSpoolDivProgressBar')" > 
                </td>
            </tr>
            <tr>
                <td>
                    <ul id="eqFilterUnitSpool">
                    </ul>
                </td>
            </tr>
            <tr>
                <td>
                    <ul id="eqUnitSpool">
                    </ul>
                </td>
            </tr>
        </table>
        </div>
        <script type="text/JavaScript" >
            if (RTL) 
            {
            	setFieldRTL('UnitSpoolDivTitle');
            }
            numNaviBar = numNaviBar + 1;
        </script>
        <% }  %>

        
		<% /*  Any spooled files */ %>
		
		<% if (eqMenu.isAuthUnitSpool()) {  %>

		<table id="AnySpoolDivHeader" class="navigationbar" cellpadding="0" cellspacing="0" height="100%" width="100%" ondblclick="javascript:switchMenu('AnySpoolDiv',null,'eqAnySpool'); anySpoolFileShutterOpen(anySpoolLibraryText,anySpoolOutputQueueCombo,anySpoolText,'AnySpoolDivProgressBar');">
			<tr>
				<td id="AnySpoolDivTitle" class="labelText" align="left">
					<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000312")%>
				</td>
				<td align="right" width="100%"></td>
				<td id="AnySpoolDivProgressBar" align="center" class="button" style="display:none;">
					<img src="../images/EqSpin.gif" border="0">
				</td>			
				<td id="AnySpoolDivRefresh" align="center" class="button" style="display:none;" height="18">
					<a href= "Javascript:anySpoolFileOutputQueue_refresh(anySpoolFileCurrentDisplay, 'AnySpoolDiv','getAnySpoolFilesHTML','eqAnySpool','anySpoolFilterText','eqAnySpool','eqFilterAnySpool','AnySpoolDivProgressBar');">
					   <img src="../images/refresh.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLREFR")%>" border="0">
					</a>
				</td>
				<td id="AnySpoolDivCollapse" align="center" class="button" style="display:none;" height="18">
					<a href= "Javascript:collapseTree('eqAnySpool');">
					   <img src="../images/collapseall.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000035")%>" border="0">
					</a>
				</td>
				<td id="AnySpoolDivExpand" align="center" class="button" style="display:none;" height="18">
					<a href= "Javascript:expandTree('eqAnySpool');">
					   <img src="../images/expandall.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000036")%>" border="0">
					</a>
				</td>
				<td id="AnySpoolDivSwitch" align="center" class="button" height="18">
					<a href= "Javascript:switchMenu('AnySpoolDiv',null,'eqAnySpool'); anySpoolFileShutterOpen(anySpoolLibraryText,anySpoolOutputQueueCombo,anySpoolText,'AnySpoolDivProgressBar');" id="AnySpoolDivAnchor">
					   <img id="AnySpoolDivSwitchImg" src="../images/down.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000021")%>" border="0">
					</a>
				</td>
			</tr>
		</table>
		<div style="display: none; overflow:scroll; width:100%;" id="AnySpoolDiv">
		<table id="AnySpoolTable">
			<tr>
				<td class="labelText">
					<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000314")%>
				</td>
				<td>
					<input type="text" class="inputText" style="TEXT-TRANSFORM:uppercase" id="anySpoolLibraryText" name="anySpoolLibraryText"
						value = "QGPL" title = "<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL900070")%>"
						onkeydown="anySpoolFileLibrary_keydown(anySpoolLibraryText, anySpoolOutputQueueCombo, event, 'AnySpoolDivProgressBar')" 
						onblur="anySpoolFileLibrary_blur(anySpoolLibraryText, anySpoolOutputQueueCombo, anySpoolText, event, 'AnySpoolDivProgressBar')" > 
				</td>
				<td width = "100%">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td class="labelText">
					<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000313")%>
				</td>
				<td>
					<table class="navigatorFldGroup">
					<tr>
						<td>
						<select class="inputText" id="anySpoolOutputQueueCombo" name="anySpoolOutputQueueCombo" 
							onchange="anySpoolOutputQueueCombo_onchange(anySpoolLibraryText, anySpoolOutputQueueCombo, event,'AnySpoolDiv','getAnySpoolFilesHTML','eqAnySpool','anySpoolFilterText','eqAnySpool','eqFilterAnySpool','AnySpoolDivProgressBar')" 
							title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL900071")%>" ></select>
						</td>
					</tr>	 
					<tr>
						<td>
						<span class="labelText">
							<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLOR2")%>
						</span>
					</tr>	 
					<tr>
						<td>
						<input type="text" class="inputText " style="TEXT-TRANSFORM:uppercase" id="anySpoolText" name="anySpoolText"
							onkeydown="anySpoolFileOutputQueue_keydown(anySpoolLibraryText, anySpoolText, event,'AnySpoolDiv','getAnySpoolFilesHTML','eqAnySpool','anySpoolFilterText','eqAnySpool','eqFilterAnySpool','AnySpoolDivProgressBar')"
							title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL900072")%>" >
						</td>
					</tr>	 
					</table> 
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td class="labelText">
					<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000086")%>
				</td>
				<td>
					<input type="text" class="inputText" style="TEXT-TRANSFORM:uppercase" id="anySpoolFilterText" name="anySpoolFilterText" 
						onkeydown="anySpoolFileFilter_keydown(anySpoolFileCurrentDisplay, event,'anySpoolFilterText','eqAnySpool','eqFilterAnySpool','AnySpoolDivProgressBar')" 
						onkeyup="anySpoolFileFilter_keyup(anySpoolFileCurrentDisplay, event,'anySpoolFilterText','eqAnySpool','eqFilterAnySpool','AnySpoolDivProgressBar')" > 
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td class="labelTextBold" colspan="3">
					<span id="anySpoolFileCurrentDisplay">
						<%=EqDesktopToolBox.getInitialOutputQueue(EquationFunctionContext.getContext().getLoginUserBySessionId(request.getSession().getId()))%>
					</span>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<ul id="eqFilterAnySpool">
					</ul>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<ul id="eqAnySpool">
					</ul>
				</td>
			</tr>
		</table>
		</div>
		<script type="text/Javascript" >
			if (RTL) setFieldRTL('AnySpoolDivTitle');
			numNaviBar = numNaviBar + 1;
		</script>
		<% }  %>

        <% /*  User's work load */ %>
        
        <table id="WorkLoadDivHeader" class="navigationbar" cellpadding="0" cellspacing="0" height="100%" width="100%" ondblclick="JavaScript:switchMenu('WorkLoadDiv','getWorkLoadHTML','eqWorkLoad'); ">
            <tr>
                <td id="WorkLoadDivTitle" class="labelText" align="left">
                    <%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000078")%>
                </td>
                <td align="right" width="100%"></td>
                <td id="WorkLoadDivProgressBar" align="center" class="button" style="display:none;">
                    <img src="../images/EqSpin.gif" border="0">
                </td>           
                <td id="WorkLoadDivRefresh" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:getNaviInfoFromServDir('getWorkLoadHTML','WorkLoadDiv','eqWorkLoad');">
                       <img src="../images/refresh.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBLREFR")%>" border="0">
                    </a>
                </td>
                <td id="WorkLoadDivCollapse" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:collapseTree('eqWorkLoad');">
                       <img src="../images/collapseall.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000035")%>" border="0">
                    </a>
                </td>
                <td id="WorkLoadDivExpand" align="center" class="button" style="display:none;" height="18">
                    <a href= "JavaScript:expandTree('eqWorkLoad');">
                       <img src="../images/expandall.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000036")%>" border="0">
                    </a>
                </td>
                <td id="WorkLoadDivSwitch" align="center" class="button" height="18">
                    <a href= "JavaScript:switchMenu('WorkLoadDiv','getWorkLoadHTML','eqWorkLoad');" id="WorkLoadDivAnchor">
                       <img id="WorkLoadDivSwitchImg" src="../images/down.gif" title="<%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000021")%>" border="0">
                    </a>
                </td>
            </tr>
        </table>
        <div style="display: none; overflow:scroll; width:100%;" id="WorkLoadDiv">
        <table id="WorkLoadTable">
            <tr>
                <td class="labelText">
                    <%=EquationCommonContext.getContext().getLanguageResource(equationUser,"GBL000086")%>
                    <input type="text" class="inputText" style="TEXT-TRANSFORM:uppercase" id="workLoadFilterText" name="workLoadFilterText" 
                        onkeydown="navi_onkeydown(event,'workLoadFilterText','eqWorkLoad','eqFilterWorkLoad','WorkLoadDivProgressBar')" 
                        onkeyup="navi_onkeyup(event,'workLoadFilterText','eqWorkLoad','eqFilterWorkLoad','WorkLoadDivProgressBar')" > 
                </td>
            </tr>
            <tr>
                <td>
                    <ul id="eqFilterWorkLoad">
                    </ul>
                </td>
            </tr>
            <tr>
                <td>
                    <ul id="eqWorkLoad">
                    </ul>
                </td>
            </tr>
        </table>
        </div>
        <script type="text/JavaScript" >
            if (RTL)
            {
            	setFieldRTL('WorkLoadDivTitle');
            }
            numNaviBar = numNaviBar + 1;
        </script>
        
        <script>
        // Display the change password page or not?
        function changePassword()
        {
    		// get current frame
    		var currentFrame = getFrameCurrent();

    		// driver screen?
    		if (currentFrame.eqDriver == 'Y')
    		{
    			currentFrame.location.replace("passwordChange.jsp");
    		}
    		else
    		{
				errorAlert(20,getLanguageLabel("GBL900053"));
    		}
    		return;
        }            
        </script>
        
    </body>
</html:html>