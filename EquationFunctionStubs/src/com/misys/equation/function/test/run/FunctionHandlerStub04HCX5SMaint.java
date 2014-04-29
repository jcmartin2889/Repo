package com.misys.equation.function.test.run;

import com.misys.equation.common.dao.beans.WERecordDataModel;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionSession;
import com.misys.equation.function.runtime.ScreenSetHandler;
import com.misys.equation.function.tools.SupervisorToolbox;

// Via API
public class FunctionHandlerStub04HCX5SMaint extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub04HCX5SMaint.java 6793 2010-03-31 12:10:20Z deroset $";

	public FunctionHandlerStub04HCX5SMaint()
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
		FunctionHandlerStub04HCX5SMaint test = new FunctionHandlerStub04HCX5SMaint();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			// Add XX4
			System.out.println("--------------------------- 1");
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler.doNewTransaction("HCX", "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			functionData.chgFieldInputValue("HLD", "XX4");
			functionHandler.applyRetrieveTransaction();

			// save it
			functionHandler.save(WERecordDataModel.STAT_WIP, "STUB04HCX4Save");

			// NOW RESTORE IT
			FunctionHandler fh = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			FunctionSession fs = functionHandler.getFhd().getFunctionSession();
			fh.restore(fs.getOptionId(), fs.getSessionId(), fs.getUserId(), fs.getTransactionId(), null,
							ScreenSetHandler.SCREENSET_DEFAULT);
			fh.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData().chgFieldInputValue("DSC",
							"XX4 Maintain Save Restore");
			fh.applyTransaction();
			FunctionToolboxStub.printMessages(fh.rtvFunctionMessages().getMessages());

			// retrieve journal header
			JournalHeader journalHeader1 = fh.getFhd().getJournalHeader();
			if (journalHeader1 != null)
			{
				System.out.println("Journal 1=" + journalHeader1);
			}
			else
			{
				System.out.println("Journal 1=" + "ERROR");
			}

			return (journalHeader1 != null);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (functionHandler != null)
			{
				if (functionHandler.getFhd().getFunctionSession() != null)
				{
					SupervisorToolbox.removeSession(functionHandler.getFhd().getFunctionSession(), functionHandler.getFhd()
									.getEquationUser().getEquationUnit());
				}
			}
			cleanUp();
		}
	}

	public void testStub04HCX5SMaint_001()
	{
		FunctionHandlerStub04HCX5SMaint stub = new FunctionHandlerStub04HCX5SMaint();
		boolean success = stub.test();
		assertEquals(true, success);

	}

}
