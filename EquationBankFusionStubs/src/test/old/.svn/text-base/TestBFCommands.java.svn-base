package test.old;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class TestBFCommands
{
	public TestBFCommands()
	{
	}
	public static void main(String args[])
	{
		// Get us a helper that will create the BF call environment
		BFCommandHelper bfCommandHelper = BFCommandHelper.getBFCommandHelper();
		// Have a bash at logging on and printing the user
		Map<?, ?> user = bfCommandHelper.logOn("retail", "retail");
		bfCommandHelper.printBankFusionMap(user);
		//
		// // Get the menus and print 'em out...
		// ArrayList menus = bfCommandHelper.getMenuList("retail");
		// bfCommandHelper.printBankFusionArrayList(menus);
		//
		// // Get the BPs and print 'em out...
		// Object[] businessProcesses = bfCommandHelper.getBusinessProcessList("retail");
		// bfCommandHelper.printBankFusionMetaData(businessProcesses);
		// have a go at calling a business process
		Hashtable<String, String> mfParms = new Hashtable<String, String>();
		// mfParms.put("loanReference","LONDCOAOFFSET001");
		// mfParms.put("accountId","1000D00001002");
		// mfParms.put("offsetRate","13.0");
		mfParms.put("innyStartStep", "jack");
		// HashMap bp = bfCommandHelper.executeBusinessProcess("retail","OffsetMortgageMF",mfParms);
		HashMap<?, ?> bp = bfCommandHelper.executeBusinessProcess("retail", "StubMicroflow", mfParms);
		bfCommandHelper.printBankFusionMap(bp);
		// logoff
		bfCommandHelper.logOff();
	}
}
