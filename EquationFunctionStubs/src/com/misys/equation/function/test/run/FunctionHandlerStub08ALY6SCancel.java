package com.misys.equation.function.test.run;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.dao.beans.WERecordDataModel;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionSession;
import com.misys.equation.function.runtime.ScreenSetHandler;
import com.misys.equation.function.tools.SupervisorToolbox;

/**
 * FunctionHandler Stub 1
 * 
 * This is how to use the Function Handler with validate and update processing done separately
 * 
 */
public class FunctionHandlerStub08ALY6SCancel extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub08ALY6SCancel.java 7610 2010-06-01 17:10:41Z MACDONP1 $";

	public FunctionHandlerStub08ALY6SCancel()
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

	public FunctionHandlerStub08ALY6SCancel(EquationUnit unit, EquationUser user, EquationStandardSession session)
	{
		FunctionHandlerStubTestCase.unit = unit;
		FunctionHandlerStubTestCase.user = user;
		FunctionHandlerStubTestCase.session = session;
	}

	public static void main(String[] inputParameters)
	{
		FunctionHandlerStub08ALY6SCancel test = new FunctionHandlerStub08ALY6SCancel();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler3 = null;
		try
		{
			// Cancel XX4
			System.out.println("------------------------------- 3");
			functionHandler3 = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler3.doNewTransaction("ALY", "");
			FunctionData functionData3 = functionHandler3.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			functionData3.chgFieldInputValue("AB", "0132");
			functionData3.chgFieldInputValue("AN", "891250");
			functionData3.chgFieldInputValue("AS", "380");
			functionData3.chgFieldInputValue("HLD", "XX4");
			functionData3.chgFieldInputValue("BRNM", "BRNM");
			functionData3.chgFieldInputValue("DLP", "DLP");
			functionData3.chgFieldInputValue("DLR", "DLR");
			functionData3.chgFieldDbValue("HLD2", "SS4");
			functionHandler3.applyRetrieveTransaction();
			// functionHandler3.validate(0);
			// functionHandler3.loadKeyFieldSet("PRIMARY", "HLD");

			// save it
			functionHandler3.save(WERecordDataModel.STAT_WIP, "STUB08ALY6SCancel");

			// NOW RESTORE IT
			FunctionHandler fh = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			FunctionSession fs = functionHandler3.getFhd().getFunctionSession();
			fh.restore(fs.getOptionId(), fs.getSessionId(), fs.getUserId(), fs.getTransactionId(), null,
							ScreenSetHandler.SCREENSET_DEFAULT);
			functionData3 = fh.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			functionData3.chgFieldInputValue("CUS", "CUSSS");
			functionData3.chgFieldInputValue("CLC", "CLC");
			functionData3.chgFieldInputValue("DOS", "DOS-ORG-SYS");
			functionData3.chgFieldInputValue("DSC", "XX4 Description");
			functionData3.chgFieldInputValue("HLD2", "SS4");
			functionData3.chgFieldInputValue("DSC2", "SS4 Description");
			fh.applyTransactionDelete();
			FunctionToolboxStub.printMessages(fh.rtvFunctionMessages().getMessages());

			JournalHeader journalHeader = fh.getFhd().getJournalHeader();
			if (journalHeader != null)
			{
				System.out.println("Journal 3=" + journalHeader);
			}
			else
			{
				System.out.println("Journal 3=" + "ERROR");
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
			if (functionHandler3 != null)
			{
				if (functionHandler3.getFhd().getFunctionSession() != null)
				{
					SupervisorToolbox.removeSession(functionHandler3.getFhd().getFunctionSession(), functionHandler3.getFhd()
									.getEquationUser().getEquationUnit());
				}
			}
			cleanUp();
		}
	}
	public void testStub08ALY6SCancel()
	{
		FunctionHandlerStub08ALY6SCancel stub = new FunctionHandlerStub08ALY6SCancel();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
