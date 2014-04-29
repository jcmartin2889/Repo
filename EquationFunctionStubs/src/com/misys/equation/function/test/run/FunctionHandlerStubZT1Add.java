package com.misys.equation.function.test.run;

import java.util.Calendar;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;

// Via API
public class FunctionHandlerStubZT1Add extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStubZT1Add.java 6793 2010-03-31 12:10:20Z deroset $";
	private static final EquationLogger LOG = new EquationLogger(FunctionHandlerStubZT1Add.class);

	public FunctionHandlerStubZT1Add()
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
		FunctionHandlerStubZT1Add test = new FunctionHandlerStubZT1Add();
		test.test();
	}

	public boolean test()
	{
		FunctionHandler functionHandler = null;
		try
		{
			// Function initialisation
			System.out.println("--------------------------- 1");
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler.doNewTransaction("ZT1", "");
			FunctionData functionData = functionHandler.rtvFunctionData();

			// Change this value to add a new customer
			String DATE = Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT).replaceAll(" ", "").replaceAll(":",
							"");
			String customerNumber = DATE.substring(DATE.length() - 9);
			String customerLocation = DATE.substring(DATE.length() - 3);

			if (externalInputTest)
			{
				customerNumber = "150524325";
				customerLocation = "325";
			}

			// Function field values, others should be defaulted as per Desktop runtime
			functionData.chgFieldInputValue("ANC_CUS", customerNumber);
			functionData.chgFieldInputValue("ANC_CLC", customerLocation);
			functionHandler.applyRetrieveTransaction();

			functionData.chgFieldInputValue("ANC_CUN", "Michael Andrew Smith");
			functionData.chgFieldInputValue("ANC_CPNC", customerNumber);
			functionData.chgFieldInputValue("ANC_DAS", "Michael Smith");
			functionData.chgFieldInputValue("ANC_CTP", "EA");
			functionData.chgFieldInputValue("ANC_BRNM", "LOND");
			functionData.chgFieldInputValue("ANC_CRB1", "00");
			functionData.chgFieldInputValue("ANC_CRB2", "00");
			functionData.chgFieldInputValue("ANX_EAD1", "Michael.Smith@test.com");
			functionData.chgFieldInputValue("CAA_NA1", "The White House");
			functionData.chgFieldInputValue("CAA_NA2", "1 High Street");
			functionData.chgFieldInputValue("CAA_NA3", "Madeup Town");
			functionData.chgFieldInputValue("CAA_NA4", "Falseshire");
			functionData.chgFieldInputValue("CAA_NA5", "UK");
			functionData.chgFieldInputValue("MCO_C1R", "BB");

			functionData.chgFieldInputValue("OCA_ACT", "CA");

			functionData.chgFieldInputValue("RDS_DLP", "RTD");
			functionData.chgFieldInputValue("RDS_DLR", customerNumber + "001");
			functionData.chgFieldInputValue("RDS_DLA", "3T");
			functionData.chgFieldInputValue("RDS_SDT", "T");
			functionData.chgFieldInputValue("RDS_MDT", "3Y");
			functionData.chgFieldInputValue("RDS_PRC", "Y1");

			functionData.chgFieldInputValue("ASC_VFR", "T");
			functionData.chgFieldInputValue("ASC_AMA", "2H");
			functionData.chgFieldInputValue("ASC_DRF", customerNumber + "T001");
			functionData.chgFieldInputValue("ASC_TCD", "510");
			functionData.chgFieldInputValue("ASD_TCD", "010");

			functionHandler.applyTransaction();
			Toolbox.printList(functionHandler.print());
			FunctionToolboxStub.printMessages(functionHandler.rtvFunctionMessages().getMessages());

			// retrieve journal header
			JournalHeader journalHeader = functionHandler.getFhd().getJournalHeader();
			System.out.println(customerNumber + " " + customerLocation);
			if (journalHeader != null)
			{
				System.out.println("Journal 1=" + journalHeader);
			}
			else
			{
				System.out.println("Journal 1=" + "ERROR");
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

	public void testStubZT1Add()
	{
		FunctionHandlerStubZT1Add stub = new FunctionHandlerStubZT1Add();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
