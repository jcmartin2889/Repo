<!-- 
This JSP is used to generate the link to the global prompter. 
I accepts the following parameters:
- searchType Indicating the Search Type

This page generates the HTML to create a link to call the appropriate global prompter.
This is done by calling the JavaScript method getNewPVListAsWindow().
Depending on the searchType and searchCriteria this JSP generates different links to call the getNewPVListAsWindow() using
different parameters

Below are the different combinations:
SearchCustomersByCustNoDiv
SearchCustomersByMnemLocDiv				>>>>	Customers Search
SearchCustomersByGlobalCustNoDiv

SearchAccountNoByBranchNoSuffix
SearchAccountsByExternalAccntNo			>>>>	Accounts Search
SearchAccountsByIBAN
SearchAccountsByInternalAccount

SearchBranchByBranchNumber				>>>>	Branches Search
SearchBranchByBranchMnemonic

SearchDealByBranchNoTypeRef				>>>>	Deals Search
 -->



<SCRIPT id="BRANCHPMT" type="text/javascript">
	setPromptButtonNoWF('Branches', 'BRANCH', 'GPE', ' ', '','', '', ['CABBN','HVBNM','HVBAD1','HVBAD2','HVBRNM'],['Branch<BR> number','Branch name','Branch address line 1','Branch address line 2','Branch<BR> mnemonic'],['140','432','452','487','424'],['4','20','35','35','4'],0,0,'CABAD1,.*,0,35,N,N,A,N,CABAD2,.*,35,35,N,N,A,N,CABAD3,.*,70,35,N,N,A,N,CABAD4,.*,105,35,N,N,A,N,CABBN,BRANCH,140,4,N,N,A,N,CABRN,.*,144,35,N,N,A,N,CADLM,.*,179,7,N,N,S,N,CAFCR,.*,186,1,N,N,A,N,CAFDR,.*,187,1,N,N,A,N,CATLY,.*,188,16,N,N,A,N,CATPH,.*,204,16,N,N,A,N,CABRNM,.*,220,4,N,N,A,N,CABRSC,.*,224,6,N,N,A,N,CABRAC,.*,230,8,N,N,A,N,CABRAT,.*,238,1,N,N,A,N,CABCSC,.*,239,6,N,N,A,N,CABCAC,.*,245,8,N,N,A,N,CABCAT,.*,253,1,N,N,A,N,CAOUT1,.*,254,6,N,N,A,N,CAOUT2,.*,260,6,N,N,A,N,CAOUT3,.*,266,6,N,N,A,N,CAOUT4,.*,272,6,N,N,A,N,CABAD5,.*,278,35,N,N,A,N,CARLFI,.*,313,1,N,N,A,N,CASWB,.*,314,4,N,N,A,N,CACNAS,.*,318,2,N,N,A,N,CASWL,.*,320,2,N,N,A,N,CASWBR,.*,322,3,N,N,A,N,CASORT,.*,325,9,N,N,A,N,CAREG,.*,334,3,N,N,A,N,CARCBR,.*,337,4,N,N,A,N,CACHAN,.*,341,6,N,N,A,N,CACRC,.*,347,20,N,N,A,N,CACNAR,.*,367,2,N,N,A,N,CATORN,.*,369,12,N,N,A,N,CATORD,.*,381,35,N,N,A,N,CABAK,.*,416,6,N,N,A,N,HVLNM,.*,422,2,N,N,A,N,HVBRNM,.*,424,4,N,N,A,N,HVBBN,.*,428,4,N,N,A,N,HVBNM,.*,432,20,N,N,A,N,HVBAD1,.*,452,35,N,N,A,N,HVBAD2,.*,487,35,N,N,A,N,HVBAD3,.*,522,35,N,N,A,N,HVBAD4,.*,557,35,N,N,A,N,HVBAD5,.*,592,35,N,N,A,N,HVPHN,.*,627,20,N,N,A,N,HVTLX,.*,647,20,N,N,A,N,HVC08,.*,667,8,N,N,A,N,HVFAX,.*,675,20,N,N,A,N,CABBN,BRANCH$$$DB,140,4,Y,N,A,N,CABBN,BRANCH$$$Output,140,4,Y,N,A,N',['CABBN','HVBNM','HVBAD1','HVBAD2','HVBRNM'],[' ',' ',' ',' ',' '],16);
</SCRIPT>

