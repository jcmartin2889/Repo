<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.misys.equation.common.access.EquationLogin"%>
<%@page import="com.misys.equation.common.access.EquationUser"%>
<%@page import="com.misys.equation.common.access.EquationUnit"%>
<%@page import="com.misys.equation.common.globalprocessing.GlobalProcessingToolBox"%>
<%@page import="java.util.List"%>
<%@page import="com.misys.equation.common.search.results.SearchType"%>
<%@page import="com.misys.equation.function.tools.XMLToolbox"%>
<%@page import="com.misys.equation.function.tools.XMLToolbox"%>
<%@page import="com.misys.equation.common.access.EquationStandardSession"%>
<%@page import="com.misys.equation.common.datastructure.EqDS_DSWSID"%>
<%@page import="com.misys.equation.common.datastructure.EqDS_DSWSI2"%>
<%@page import="com.misys.equation.function.tools.HTMLToolbox"%>
<%@page import="com.misys.equation.common.access.EquationSystem"%>
<%@page import="com.misys.equation.common.dao.beans.GPX1RecordDataModel"%>
<%@page import="com.misys.equation.common.dao.beans.AbsRecord"%>
<%@page import="com.misys.equation.common.search.results.PropertyType"%>
<%@page import="com.misys.equation.function.tools.FunctionRuntimeToolbox"%>
<%@page import="com.misys.equation.function.runtime.FunctionHandlerTable"%>
<%@page import="com.misys.equation.function.runtime.FunctionHandler"%>
<%@page import="com.misys.equation.function.runtime.FunctionHandlerData"%>
<%@page import="com.misys.equation.common.access.EquationCommonContext"%>
<%@page import="com.misys.equation.ui.tools.EqDesktopToolBox"%>

<%
	EquationLogin eqLogin = (EquationLogin)request.getSession().getAttribute("equationLogin");
	EquationUser eqUser = (EquationUser)request.getSession().getAttribute("equationUser");
	EquationUnit eqUnit = (EquationUnit)request.getSession().getAttribute("equationUnit");
	EquationSystem equationSystem = eqUnit.getSystem();
%>
<script type="text/JavaScript"  src="<html:rewrite page='/equation/scripts/constant.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/equation/scripts/globalsUXP.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/globals.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/init.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/prototype.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/ws.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/pvWS.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/msf.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/tabbar.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/buttonbar.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/bottombar.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/predictiveList.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/mainBody.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/pgminfo.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/ast.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/sel.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/s1b.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/opt.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/cal.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/frq.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/yni.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/list.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/function.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/dt_function.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/dt_event.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/dt_key.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/dt_sel.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/dt_help.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/validvalue.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/utilities.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/userexit_utilities.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/popup.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/windowProperties.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/pvUtils.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/childWindow.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/groupField.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/groupShutter.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/vopt.js'/>"></script>
<script type="text/JavaScript" src="<html:rewrite page='/equation/scripts/globalPVs.js'/>"></script>
<%			
	// retrieve user's language RTL property	
	boolean isLanguageRTL = false;
	if (eqUser != null)
	{
		isLanguageRTL = eqUser.isLanguageRTL();
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

<script type="text/javascript">
	var submitted = false;
	var isPV = false;
	var RTL = false;
	var RTL = <%=isLanguageRTL%>;
	var sessionType = <%=eqLogin.getSessionType()%>;
	var myglobalvars = null;
	var webFacing = false;
	var eqDriver = 'N';
	var checker = 0;
	var requiredChecker = 0;
	var currentUser = '<%=eqUser.getUserId()%>';
	var unitId = '<%=eqUnit.getUnitId()%>';	

	var customerSearchesCriteriaIndex = 0;
	var accountsSearchesCriteriaIndex = 0;
	var branchesSearchesCriteriaIndex = 0;
	var dealSearchesCriteriaIndex = 0;	
	
	var invalidUnitString ='<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.errors.invalid.unit")%>';

	/************************************************************************************************************************
	 * Define the array containing the DIV names and Comments for customers, accounts, branches, deals 
	 ************************************************************************************************************************/
	// Customers ...
	var customerSearches = new Array();
	customerSearches[0]="SearchCustomersByMnemLocDiv";
	customerSearches[1]="SearchCustomersByCustNoDiv";
	var customerSearchesComments = new Array();
	customerSearchesComments[0] = <%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.customers.comment1")%>
	customerSearchesComments[1] = <%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.customers.comment2")%>
		
	// Accounts ...
	var accountSearches = new Array();
	accountSearches[0]="SearchAccountNoByBranchNoSuffix";
	accountSearches[1]="SearchAccountsByExternalAccntNo";
	accountSearches[2]="SearchAccountsByIBAN";
	var accountSearchComments = new Array();
	accountSearchComments[0] = <%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.accounts.comment1")%>
	accountSearchComments[1] = <%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.accounts.comment2")%>
	accountSearchComments[2] = <%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.accounts.comment3")%>

	// Branches ...
	var branchSearches = new Array();
	branchSearches[0]="SearchBranchByBranchMnemonic";
	branchSearches[1]="SearchBranchByBranchNumber";
	var branchSearchesComments = new Array();
	branchSearchesComments[0] = <%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.branches.comment1")%>
	branchSearchesComments[1] = <%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.branches.comment2")%>

	// Deals ...
	var dealSearches = new Array();
	dealSearches[0] = "SearchDealByBranchNoTypeRef";
	var dealSearchesComments = new Array();
	dealSearchesComments[0] = <%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.deals.comment1")%>

	var arrayOfArrays = new Array();
	arrayOfArrays[0] = customerSearches;
	arrayOfArrays[1] = accountSearches;
	arrayOfArrays[2] = branchSearches;
	arrayOfArrays[3] = dealSearches;

	var arrayOfCommentArrays = new Array();
	arrayOfCommentArrays[0] = customerSearchesComments;
	arrayOfCommentArrays[1] = accountSearchComments;
	arrayOfCommentArrays[2] = branchSearchesComments;
	arrayOfCommentArrays[3] = dealSearchesComments;
	
	eqInit();	

	function getContextString(searchType)
	{		
	    contextString = '';
	    // Determine the type of search that we need to switch too and build the context string ...
	    if(searchType=='SearchCustomers')
	    {
	        contextString = '<%=PropertyType.CUST_NO%>='+$('CUST').value+
	        ',<%=PropertyType.SYSTEM%>='+$('SYSTEM').value+
	        ',<%=PropertyType.UNIT%>='+$('UNIT').value+
	        ',<%=PropertyType.CUST_LOC%>='+$('CUST_LOC').value+
	        ',<%=PropertyType.CUST_FNAME%>='+$('CUST_FNAME').value+<%=eqUser.isLanguageRTL()?"' ' + $('CFULL_NAME').value+":""%>
	        ',<%=PropertyType.CUST_MNEM%>='+$('CUST_MNEM').value;
	    }
	    else if(searchType=='SearchDealByBranchNoTypeRef')
	    {
	        contextString = '<%=PropertyType.DEAL_TYPE%>='+$('DEALTYPE').value+
	        ',<%=PropertyType.SYSTEM%>='+$('SYSTEM').value+
	        ',<%=PropertyType.UNIT%>='+$('UNIT').value+
	        ',<%=PropertyType.DEAL_REF%>='+$('DEALREF').value+
	        ',<%=PropertyType.DL_BRH_MNM%>='+$('BRANCHMNEM').value;
	    }
	    else if (searchType == "SearchBranch")
	    {	    	
	    	if( $('BRANCHNO').value != '')
	    	{
	    		contextString = '<%=PropertyType.BRANCH_NO%>='+$('BRANCHNO').value + ',';
	    	}
	    	
	    	contextString += '<%=PropertyType.SYSTEM%>='+$('SYSTEM').value+
	    	    ',<%=PropertyType.UNIT%>='+$('UNIT').value;
	    
	        if($('BRCHMNEM').value != '')
	        {
	        	contextString += ',<%=PropertyType.B_MNEM%>='+$('BRCHMNEM').value;
	        }
	    }
	    else if (searchType=="SearchAccounts")
	    {
	        contextString = '<%=PropertyType.BRANCH%>='+$('ACCNTBRCH').value+
	        ',<%=PropertyType.SYSTEM%>='+$('SYSTEM').value+
	        ',<%=PropertyType.UNIT%>='+$('UNIT').value+
	        ',<%=PropertyType.SHORT_NAME%>='+$('ACNTSHRTN').value+<%=eqUser.isLanguageRTL()?"' ' + $('ARACSHRTN').value+":""%>
	        ',<%=PropertyType.EX_ACNTNO%>='+$('EXTACCNT').value+
	        ',<%=PropertyType.ACC_IBAN%>='+$('IBAN').value+
	        ',<%=PropertyType.ACCNT_NO%>='+$('ACCNTNO').value+
	        ',<%=PropertyType.ACCNT_SUF%>='+$('ACCNTSFX').value;
	    }
	    return contextString;
	}	
	
	function setGlobalPromptButton(search)
	{			
	   /*************************************************************************************************************************
		* Customers 
		*************************************************************************************************************************/
		if(search == 'SearchCustomersByCustNoDiv')
		{
			<%
			// If the user is arabic then we load the arabic PV
			if(eqUser.isLanguageRTL())
			{				
				%>				
				var header = '<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.globalprompts.customerpopup")%>';
                callCusnPrompt(header);
                <%
			}
			else // we load the normal PV
			{
				%>
				var header = '<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.globalprompts.customerpopup")%>';
				callCusnPrompt(header);
				<%
			}
			%>
		}
		else if(search == 'SearchCustomersByMnemLocDiv')
		{
			<%
			// If the user is arabic then we load the arabic PV
			if(eqUser.isLanguageRTL())
			{
				%>
				var header = '<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.globalprompts.customerpopup")%>';
                callCusmPrompt(header);
                <%
			}
			else // we load the normal PV
			{
				%>
				var header = '<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.globalprompts.customerpopup")%>';
				callCusmPrompt(header);
				<%
			}
			%>
		}
	   /*************************************************************************************************************************
		* Deals
		*************************************************************************************************************************/
		else if(search == 'SearchDealByBranchNoTypeRef')
		{
			var header = '<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.globalprompts.dealpopup")%>';
			callDealPrompt(header);
		}
	   /*************************************************************************************************************************
		* Accounts
		*************************************************************************************************************************/
		else if(search == 'SearchAccountNoByBranchNoSuffix')
		{
			<%
			// If the user is arabic then we load the arabic PV
			if(eqUser.isLanguageRTL())
			{
				%>
				var header ='<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.globalprompts.accountpopup")%>';
                callAcntaPrompt(header);
                <%
			}
			else // we load the normal PV
			{
				%>
				var header ='<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.globalprompts.accountpopup")%>';
				callAcntnPrompt(header);
				<%
			}

			%>
		}
		else if(search=='SearchAccountsByExternalAccntNo')
		{
			<%
			// If the user is arabic then we load the arabic PV
			if(eqUser.isLanguageRTL())
			{
				%>
				var header = '<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.globalprompts.accountpopup")%>';
                callExternalAccountPrompt(header);
            	<%
			}
			else // we load the normal PV
			{
				%>
				var header = '<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.globalprompts.accountpopup")%>';
				callExternalAccountPrompt(header);
				<%
			}
			%>
		}
		else if(search=='SearchAccountsByIBAN')
		{
			<%
			// If the user is arabic then we load the arabic PV
			if(eqUser.isLanguageRTL())
			{
				%>
				var header = '<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.globalprompts.accountpopup")%>';
                callIBANPrompt(header);
                <%
			}
			else // we load the normal PV
			{
				%>
				var header = '<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.globalprompts.accountpopup")%>';
				callIBANPrompt(header);
				<%
			}
			%>
		}
	   /*************************************************************************************************************************
		* Branches
		*************************************************************************************************************************/
		else if(search=='SearchBranchByBranchNumber')
		{
			var header = '<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.globalprompts.branchpopup")%>';
			callBrnchNumberPrompt(header);
		}
		else if(search=='SearchBranchByBranchMnemonic')
		{
			var header = '<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.globalprompts.branchpopup")%>';
			callBranchMnemonicPrompt(header);
		}
		else 
		{
			return
		}		
	}
	
	function switchSearchCriteria(search, incrementCount)
	{
	    var divIndex = 0;
	    var currentIndex = 0;

	    inUnitString = '<b><%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.inunit")%></b>&nbsp;&nbsp;';

	    // First determine the type of search that we need to switch too ...
	    if(search == 'SearchCustomersDiv')
	    {
	    	if(incrementCount)
	    	{	    		
	    		customerSearchesCriteriaIndex++;
	    		if(customerSearchesCriteriaIndex >= customerSearches.length)
                {
                    customerSearchesCriteriaIndex = 0;
                }
	    	}
	    	
	        divIndex = 0;
	        currentIndex = customerSearchesCriteriaIndex; 
	        $('SearchCustomersCommentDiv').innerHTML = arrayOfCommentArrays[divIndex][currentIndex]; 
	    }
	    else if(search == 'SearchAccountsDiv')
	    {
	    	if(incrementCount)
            {	    		
	    		accountsSearchesCriteriaIndex++;
	    		if(accountsSearchesCriteriaIndex >= accountSearches.length)
                {
                    accountsSearchesCriteriaIndex = 0;
                }
            }
	        divIndex = 1;	        
	        currentIndex = accountsSearchesCriteriaIndex;   	             
	        $('SearchAccountsCommentDiv').innerHTML = arrayOfCommentArrays[divIndex][currentIndex]; 
	    }
	    else if(search == 'SearchBranchesDiv')
	    {
	    	if(incrementCount)
            {	 	    		
	    		branchesSearchesCriteriaIndex++;
	    		if(branchesSearchesCriteriaIndex >= branchSearches.length)
                {
                    branchesSearchesCriteriaIndex = 0;
                }
            }
	    	
	        divIndex = 2;
	        currentIndex = branchesSearchesCriteriaIndex; 
	        $('SearchBranchesCommentDiv').innerHTML = arrayOfCommentArrays[divIndex][currentIndex]; 
	    }
	    else if(search == 'SearchDealsDiv')
	    {
	        divIndex = 3;	        
	        currentIndex = dealSearchesCriteriaIndex;	                   
	        $('SearchDealsCommentDiv').innerHTML = arrayOfCommentArrays[divIndex][currentIndex]; 
	    }
	    else
	    {
	        return;
	    }
    
	    for (var i = 0; i < arrayOfArrays[divIndex].length; i++ )
	    {
	        if(i == currentIndex)
	        {
	            showdiv(arrayOfArrays[divIndex][i]);
	        }
	        else
	        {
	            hidediv(arrayOfArrays[divIndex][i]);
	        }   
	    }
	}

	/*
	 * This method records the selection. Once that is done we need to check for the current unit and see if the unit has changed
	 * or not.
	 */
	function selectionMade(search, contextString)
	{		
	    // First we check for if we have an open transaction ...
	    if(!checkForOpenTransaction())
	    {
	        // If we do show error and return ...
	        return;
	    }
	    
	    // Check also if a unit has been selected ... 
	    if($('UNIT').value == '' && $('SYSTEM').value == '' 
	    		|| ($('UNIT').value == 'undefined' && $('SYSTEM').value == '*'))
	    {
	        // If not we cannot do much. Need to know the unit we are in ...
	        errorAlert(20,invalidUnitString);
	        return
	    }

	    var divIndex = 0;
	    var currentIndex = 0;
	    
	    // If the context String is empty, build it (depending on the searchType, see getContextString())
	    if(contextString == '' && search != 'UnitOnly')
	    {
	        contextString = getContextString(search);
	    }

	    system = $('SYSTEM').value;
        unitDropDown = $('UNITDRPDWN');
        unit = $('UNIT').value;
        currentUnit = '<%=eqUnit.getUnitId()%>'; 
        currentSystem = '<%=eqUnit.getSystem().getSystemId()%>';
        
	    if(contextString == '' && search == 'UnitOnly'  
	       && unit == currentUnit && system == currentSystem)
	    {
	    	return;
	    }
	    
	    // Now we need to forward to the unit if it changed ...
	    if(unit == currentUnit && system == currentSystem)
	    {
	        // Set the context area ...
	        setCurrentContext(contextString,'<%=eqUnit.getUnitId()%>'); 
	    }   
	    else
	    {
	        // Switch Unit if the user confirms ..
	        if(confirm('<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"currentcontext.dialog.confirmmessage")%>'))
	        {
        	   // Set the context area ...
               setCurrentContext(contextString,'<%=eqUnit.getUnitId()%>');
	        }
	        else
	        {
	            return;
	        }
	    }
	    // Finally we re-sent the content for the recent search tree. This will ensure that it is re-loaded the next time
	    // the recent search shutter is opened. 
	    $('eqRecentSearches').innerHTML = '';
	    
	    //Refresh the recent searches
	    getNaviInfoFromServDir('getRecentSearches', 'SearchDiv', 'eqRecentSearches', true); 
	}
</script>


<BR/>

<!-- Define Search Unit/System -->
<TABLE border="0"  cellpadding="1" cellspacing="0" id="UNITtable">
    <TR>
        <TD>
            <INPUT type="hidden" id="SYSTEM" name="SYSTEM" maxlength="10" onchange="unitDropDownChanged();"/>
            <INPUT type="hidden" id="UNIT" name="UNIT" maxlength="10" onchange="unitDropDownChanged();"/>
        </TD>
        <TD nowrap valign="middle">
            <SPAN class="labelTextBold" id="SEARCHIN$$$Label"><%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.inunit")%>&nbsp;</SPAN>
        </TD>
        <TD nowrap valign="middle">            
            <SELECT name="UNITDRPDWN" id="UNITDRPDWN" onchange="unitDropDownManuallyChanged();">
                <OPTION value="*" SELECTED><%=EquationCommonContext.getContext().getLanguageResource(eqUser,"globalprocessing.allunits")%></option>
            </SELECT>   
           
            <script type="text/JavaScript">
               var selectEl = $('UNITDRPDWN');
                <%
                List<String> unitList = GlobalProcessingToolBox.getListOfUnitsAndDescriptions(eqUser);
                for(String record : unitList)
                {
                    %>
                    appendOption(selectEl,'<%=record.trim()%>','<%=record.trim()%>');
                    <%
                }
                %>
            </script>
         </TD>   
         <TD nowrap width="25px" align="right">
            <a href="#" onclick="JavaScript:selectionMade('UnitOnly', '')"><img src="../images/go.gif" alt="Go to Unit" border="0"></img></a>
         </TD>    
    </TR>   
</table>
<BR> 
<!-- HTML defining the search type -->
<TABLE border="0" cellpadding="1" cellspacing="0" id="SEARCHFORtable">
	<TR id="SEARCHFORrow2">

		<TD nowrap valign="middle">
			<SPAN class="labelTextBold" id="SEARCHFOR$$$Label"><%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.searchfor")%>&nbsp;</SPAN>
		</TD>
		<TD nowrap valign="middle">
			<SELECT name="SEARCHFOR" id="SEARCHFOR" onchange="showHideSearch(this.options[this.selectedIndex].value);">
				<%
					// if authorisied to searches
					if (eqUser.checkAuthorisedOption("DGS",false))
					{
						%>
						<option value="Branches"><%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.searchtypes.branches")%></option>
		                <option value="Customers"><%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.searchtypes.customer")%></option>
		                <option value="Accounts"><%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.searchtypes.accounts")%></option>
		                <option value="Deals"><%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.searchtypes.deals")%></option>
						<% 
					}
				%>
			</SELECT>
		</TD>		
	</TR>
</TABLE>

<BR/>

<!-- Create the DIV containing the 'Accounts Search '  -->
<DIV class="GroupClass" id="SearchAccountsDiv">
 	<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
     	<TR class="rowSpacer">
            <TD class="labelText">
            </TD>
            <TD colspan="2" nowrap>
            <br/>
            <DIV id="SearchAccountsCommentDiv">
            </DIV>
			<br/>
			</TD>
      	</TR>
     	<TR>
            <TD class="labelText">
            </TD>
            <TD colspan="2">
                    <TABLE border="0" cellpadding="1" cellspacing="0">
                    <TR>
                        <TD nowrap>
                           <input type="hidden" name="ACNTSHRTN" id="ACNTSHRTN" onchange="JavaScript:selectionMade('SearchAccounts', '')"></input>
                        	<DIV id="SearchAccountNoByBranchNoSuffix">
	                        	<INPUT class="wf_LTOR wf_ul wf_hi wf_default wf_field" id=ACCNTBRCH
	                            maxlength="4" name="ACCNTBRCH"
	                            onblur="JavaScript:return inputFieldBlur(event);"
	                            onfocus="JavaScript:return inputFieldFocus(event);"
	                            onkeydown="JavaScript:return inputFieldKeyDown(event);"
	                            size="4" title="<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.inputfield.title.accountbranch")%>">
	                        	&nbsp;&nbsp;
	                        	<INPUT class="wf_LTOR wf_ul wf_hi wf_default wf_field" id="ACCNTNO"
	                            maxlength="6" name="ACCNTNO"
	                            onblur="JavaScript:return inputFieldBlur(event);"
	                            onfocus="JavaScript:return inputFieldFocus(event);"
	                            onkeydown="JavaScript:return inputFieldKeyDown(event);"
	                            size="6" title="<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.inputfield.title.accountnumber")%>">
	                        	&nbsp;&nbsp;
	                        	<INPUT class="wf_LTOR wf_ul wf_hi wf_default wf_field" id="ACCNTSFX"
	                            maxlength="3" name="ACCNTSFX"
	                            onblur="JavaScript:return inputFieldBlur(event);"
	                            onfocus="JavaScript:return inputFieldFocus(event);"
	                            onkeydown="JavaScript:return inputFieldKeyDown(event);"
	                            size="3" title="<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.inputfield.title.accountsuffix")%>">
	                            <script type="text/javascript">
	                        		setGlobalPromptButton('SearchAccountNoByBranchNoSuffix');
	                        	</script>
                        	</DIV>	
                        	<DIV id="SearchAccountsByExternalAccntNo">
	                        	<INPUT class="wf_LTOR wf_ul wf_hi wf_default wf_field" id="EXTACCNT"
	                            maxlength="20" name="EXTACCNT"
	                            onblur="JavaScript:return inputFieldBlur(event);"
	                            onfocus="JavaScript:return inputFieldFocus(event);"
	                            onkeydown="JavaScript:return inputFieldKeyDown(event);"
	                            size="20" title="<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.inputfield.title.externalaccountnumber")%>">
	                            <input type="hidden" name="EACCNTBRCH" id="EACCNTBRCH"></input>
	                            <input type="hidden" name="EACCNTNO" id="EACCNTNO"></input>
	                            <input type="hidden" name="EACCNTSFX" id="EACCNTSFX"></input>
	                            <script type="text/javascript">
	                        		setGlobalPromptButton('SearchAccountsByExternalAccntNo');
	                        	</script>
                        	</DIV>	
                        	
                        	<DIV id="SearchAccountsByIBAN">
	                        	<INPUT class="wf_LTOR wf_ul wf_hi wf_default wf_field" id="IBAN"
	                            maxlength="34" name="IBAN"
	                            onblur="JavaScript:return inputFieldBlur(event);"
	                            onfocus="JavaScript:return inputFieldFocus(event);"
	                            onkeydown="JavaScript:return inputFieldKeyDown(event);"
	                            size="34" title="<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.inputfield.title.iban")%>">
	                            <input type="hidden" name="IACCNTBRCH" id="IACCNTBRCH"></input>
	                            <input type="hidden" name="IACCNTNO" id="IACCNTNO"></input>
	                            <input type="hidden" name="IACCNTSFX" id="IACCNTSFX"></input>
	                            <script type="text/javascript">
	                        		setGlobalPromptButton('SearchAccountsByIBAN');
	                        	</script>
                        	</DIV>	
                        </TD>
                        <TD nowrap width="25px" align="right">
                        	<a href="#" onclick="JavaScript:selectionMade('SearchAccounts', '')"><img src="../images/go.gif" alt="Go to Unit" border="0"></img></a>
                        </TD>
                        <TD nowrap width="25px" align="right">
                        	<a href="#" onclick="JavaScript:switchSearchCriteria('SearchAccountsDiv', true)"><img src="../images/toggle.gif" alt="Switch Search Criteria" border="0"></img></a>
                        </TD>
                	</TR>
                </TABLE>
            </TD>
       	</TR>
        <TR class="rowSpacer">
        	<TD colspan="3" nowrap><SPAN></SPAN></TD>
        </TR>  
	</TABLE>
</DIV>

<!-- Create the DIV containing the 'Customers Search '  -->
<DIV class="GroupClass" id="SearchCustomersDiv">
    <TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
        <TR class="rowSpacer">
            <TD class="labelText"></TD>
            <TD colspan="2" nowrap>
                <br/>
                <DIV id="SearchCustomersCommentDiv"></DIV>	
                <br/>            
			</TD>			
      	</TR>
        <TR>
            <TD class="labelText">
            </TD>
            <TD colspan="2" nowrap>
            		<input type="hidden" name="CUST_FNAME" id="CUST_FNAME" onchange="JavaScript:selectionMade('SearchCustomers', '')"/>
            		<input type="hidden" name="CFULL_NAME" id="CFULL_NAME"/>
            		<input type="hidden" name="ASHRT_NAME" id="ASHRT_NAME"/>
                    <TABLE border="0" cellpadding="1" cellspacing="0">
                    <TR>
                        <TD nowrap>
                        	<DIV id="SearchCustomersByMnemLocDiv">
	                        	<INPUT class="wf_LTOR wf_ul wf_hi wf_default wf_field" id="CUST_MNEM"
	                            maxlength="6" name="CUST_MNEM"
	                            onblur="JavaScript:return inputFieldBlur(event);"
	                            onfocus="JavaScript:return inputFieldFocus(event);"
	                            onkeydown="JavaScript:return inputFieldKeyDown(event);"
	                            size="5" title="<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.inputfield.title.customermnemonic")%>">
	                            &nbsp;&nbsp;
	                        	<INPUT class="wf_LTOR wf_ul wf_hi wf_default wf_field" id="CUST_LOC"
	                            maxlength="6" name="CUST_LOC"
	                            onblur="JavaScript:return inputFieldBlur(event);"
	                            onfocus="JavaScript:return inputFieldFocus(event);"
	                            onkeydown="JavaScript:return inputFieldKeyDown(event);"
	                            size="5" title="<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.inputfield.title.customerlocation")%>">
	                            &nbsp;&nbsp;
	                            <script type="text/javascript">
	                        		setGlobalPromptButton('SearchCustomersByMnemLocDiv');
	                        	</script>
                        	</DIV>
                        	<DIV id="SearchCustomersByCustNoDiv">
                                <INPUT class="wf_LTOR wf_ul wf_hi wf_default wf_field" id="CUST"
                                maxlength="6" name="CUST"
                                onblur="JavaScript:return inputFieldBlur(event);"
                                onfocus="JavaScript:return inputFieldFocus(event);"
                                onkeydown="JavaScript:return inputFieldKeyDown(event);"
                                size="5" title="<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.inputfield.title.customernumber")%>">
                                &nbsp;&nbsp;
                                <script type="text/javascript">
                                    setGlobalPromptButton('SearchCustomersByCustNoDiv');
                                </script>                               
                            </DIV>
                        </TD>
                        <TD nowrap width="25px" align="right">
                        	<a href="#" onclick="JavaScript:selectionMade('SearchCustomers', '')"><img src="../images/go.gif" alt="Go to Unit" border="0"></img></a>
                        </TD>     
                        <TD nowrap width="25px" align="right">
                            <a href="#" onclick="JavaScript:switchSearchCriteria('SearchCustomersDiv', true)">
                                <img src="../images/toggle.gif" alt="Switch Search Criteria" border="0"></img>
                            </a>
                        </TD>                   
                	</TR>
                </TABLE>
            </TD>
       	</TR>
        <TR class="rowSpacer">
        	<TD colspan="3" nowrap><SPAN></SPAN></TD>
        </TR>
     	
	</TABLE>
</DIV>


<!-- Create the DIV containing the 'Branch Search '  -->
<DIV class="GroupClass" id="SearchBranchesDiv">
	<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
     	<TR class="rowSpacer">
            <TD class="labelText"></TD>
            <TD colspan="2" nowrap>
                <br/>
                <DIV id="SearchBranchesCommentDiv"></DIV>
			    <br/>
			</TD>			
      	</TR>
        <TR>
            <TD class="labelText">
            </TD>
            <TD colspan="2" nowrap>
                    <TABLE border="0" cellpadding="1" cellspacing="0">
                    <TR>
                        <TD nowrap>                       	
                        	<DIV id="SearchBranchByBranchMnemonic">	                        	
	                        	<INPUT class="wf_LTOR wf_ul wf_hi wf_default wf_field" id="BRCHMNEM"
	                            onchange="JavaScript:selectionMade('SearchBranch', '')"	                            
	                            maxlength="4" name="BRCHMNEM"
	                            onblur="JavaScript:return inputFieldBlur(event);"
	                            onfocus="JavaScript:return inputFieldFocus(event);"
	                            onkeydown="JavaScript:return inputFieldKeyDown(event);"
	                            size="8" title="<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.branches.branchmnemonic")%>">
	                            <script type="text/javascript">
	                        		setGlobalPromptButton('SearchBranchByBranchMnemonic');
	                        	</script>
                        	</DIV>
                        	<DIV id="SearchBranchByBranchNumber">
                                <INPUT class="wf_LTOR wf_ul wf_hi wf_default wf_field" id="BRANCHNO"
                                maxlength="4" name="BRANCH"
                                onblur="JavaScript:return inputFieldBlur(event);"
                                onfocus="JavaScript:return inputFieldFocus(event);"
                                onkeydown="JavaScript:return inputFieldKeyDown(event);"
                                size="8" title="<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.branches.branchnumber")%>">
                                <script type="text/javascript">
                                    setGlobalPromptButton('SearchBranchByBranchNumber');
                                </script>
                            </DIV>  
                        </TD>
                        <TD nowrap width="25px" align="right">
                        	<a href="#" onclick="JavaScript:selectionMade('SearchBranch', '')"><img src="../images/go.gif" alt="Go to Unit" border="0"></img></a>
                        </TD>  
                        <TD nowrap width="25px" align="right">
                            <a href="#" onclick="JavaScript:switchSearchCriteria('SearchBranchesDiv', true)"><img src="../images/toggle.gif" alt="Switch Search Criteria" border="0"></img></a>
                        </TD>                      
                	</TR>
                </TABLE>
            </TD>
       	</TR>
        <TR class="rowSpacer">
        	<TD colspan="3" nowrap><SPAN></SPAN></TD>
        </TR>     	
	</TABLE>
</DIV>

<!-- Create the DIV containing the 'Deals Search '  -->
<DIV class="GroupClass" id="SearchDealsDiv">
	<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
     	<TR class="rowSpacer">
            <TD class="labelText"></TD>
            <TD colspan="2" nowrap>
            <br/>
            <DIV id="SearchDealsCommentDiv"></DIV>
			<br/>
			</TD>
      	</TR>
        <TR>
            <TD class="labelText">
            </TD>
            <TD colspan="2" nowrap>
				<TABLE border="0" cellpadding="1" cellspacing="0">
                    <TR>
                        <TD nowrap>
                       	<DIV id="SearchDealByBranchNoTypeRef">
                        	<INPUT class="wf_LTOR wf_ul wf_hi wf_default wf_field" id="BRANCHMNEM"
                            maxlength="4" name="BRANCHMNEM"
                            onblur="JavaScript:return inputFieldBlur(event);"
                            onfocus="JavaScript:return inputFieldFocus(event);"
                            onkeydown="JavaScript:return inputFieldKeyDown(event);"
                            size="4" title="<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.inputfield.title.dealbranch")%>">
                            &nbsp;&nbsp;
                        	<INPUT class="wf_LTOR wf_ul wf_hi wf_default wf_field" id="DEALTYPE"
                            maxlength="3" name="DEALTYPE"
                            onblur="JavaScript:return inputFieldBlur(event);"
                            onfocus="JavaScript:return inputFieldFocus(event);"
                            onkeydown="JavaScript:return inputFieldKeyDown(event);"
                            size="3" title="<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.inputfield.title.dealtype")%>">
                            &nbsp;&nbsp;
                        	<INPUT class="wf_LTOR wf_ul wf_hi wf_default wf_field" id="DEALREF"
                            maxlength="10" name="DEALREF"
                            onblur="JavaScript:return inputFieldBlur(event);"
                            onfocus="JavaScript:return inputFieldFocus(event);"
                            onkeydown="JavaScript:return inputFieldKeyDown(event);"
                            onchange="JavaScript:selectionMade('SearchDealByBranchNoTypeRef', '')"
                            size="10" title="<%=EquationCommonContext.getContext().getLanguageResource(eqUser,"search.inputfield.title.dealref")%>">
                       	</DIV>	
                        </TD>
                        <TD nowrap width="25px" align="right">
							<script type="text/JavaScript">
	                        	setGlobalPromptButton('SearchDealByBranchNoTypeRef');
	                        </script>
                        </TD>
                        <TD nowrap width="25px" align="right">
                            <a href="#" onclick="JavaScript:selectionMade('SearchDealByBranchNoTypeRef', '')"><img src="../images/go.gif" alt="Go to Unit" border="0"></img></a>
                        </TD>
                	</TR>
                </TABLE>
            </TD>
       	</TR>
        <TR class="rowSpacer">
        	<TD colspan="3" nowrap><SPAN></SPAN></TD>
        </TR>     	
	</TABLE>
</DIV>

<script type="text/javascript">
	showHideSearch('Branches');
</script>