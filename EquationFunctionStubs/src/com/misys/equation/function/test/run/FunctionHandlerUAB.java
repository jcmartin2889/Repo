package com.misys.equation.function.test.run;

import bf.com.misys.eqf.types.header.TaskBasicDetails;
import bf.com.misys.eqf.types.header.TaskRqHeader;
import bf.com.misys.eqf.types.header.TaskRsHeader;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;

// Via API
public class FunctionHandlerUAB extends FunctionHandlerStubTestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerUAB.java 14808 2012-11-05 11:58:49Z williae1 $";
	public FunctionHandlerUAB()
	{
		try
		{
			setUp();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters)
	{
		FunctionHandlerUAB test = new FunctionHandlerUAB();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler2 = null;
		try
		{
			// Maintain
			System.out.println("--------------------------- UAB");
			functionHandler2 = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler2.doNewTransaction("UAB", "");

			FunctionData functionData2 = functionHandler2.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			TaskRqHeader header = new TaskRqHeader();
			header.setTaskActionList("list of action");
			header.setBasicDetail(new TaskBasicDetails());
			header.getBasicDetail().setBfeqType("EQD");
			header.getBasicDetail().setOptionId("XXX");
			header.getBasicDetail().setZone("THEZONE");
			functionData2.getOutputExtensionData().setMisysExtensionData(header);

			TaskRsHeader header2 = new TaskRsHeader();
			header2.setReason("Reason is NWO");
			header2.setFunctionMode("X");

			functionData2.getOutputExtensionData().setUserExtensionData(header2);

			// retrieve
			functionHandler2.applyRetrieveTransaction();

			// apply
			functionHandler2.applyTransaction();

			// error?
			FunctionToolboxStub.printMessages(functionHandler2.rtvFunctionMessages().getMessages());

			JournalHeader journalHeader = functionHandler2.getFhd().getJournalHeader();
			if (journalHeader != null)
			{
				System.out.println("Journal 2=" + journalHeader);
			}
			else
			{
				System.out.println("Journal 2=" + "ERROR");
			}

			return (journalHeader != null);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			cleanUp();
		}
	}

}
