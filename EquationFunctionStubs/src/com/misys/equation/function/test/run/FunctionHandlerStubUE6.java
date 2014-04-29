package com.misys.equation.function.test.run;

import java.util.List;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionMessage;

/**
 * Test Stub for the UE6 service
 * <p>
 * This stub tests Java Exit processing. This tests the 'business' processing. The UI exits (Layout and Display Attributes) need to
 * be tested interactively using the Desktop.
 */
public class FunctionHandlerStubUE6 extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStubUE6.java 6793 2010-03-31 12:10:20Z deroset $";

	final String JOURNAL_APPLICATION_CODE = "UE";
	final String JOURNAL_IDENTITY = "Account  number";
	final String JOURNAL_SHORT_NAME = "Test short name";
	final String JOURNAL_REFERENCE = "12345678901234567";
	final String JOURNAL_INPUT_REFERENCE = "Input  reference";

	FunctionHandler functionHandler = null;

	public FunctionHandlerStubUE6()
	{
	}

	@Override
	public void setUp()
	{

		try
		{
			super.setUp();
			System.out.println("------------------------------- 1");
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");

		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Tests successful retrieval of data from the ABE enquiry<br/>
	 * This also sets up G5PF journal details, which need to be manually checked
	 */
	public void testStubUE6_001()
	{
		try
		{
			// Following assertion will fail if not authorised
			assertTrue(functionHandler.doNewTransaction("UE6", ""));
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			functionData.chgFieldDbValue("BBB_HRC", "XXX");

			List<FunctionMessage> messages = validateFunction(functionHandler);
			assertEquals(0, messages.size());

			// int loadResult = functionHandler.loadKeyFieldSet("PRIMARY", "");
			int loadResult = functionHandler.applyRetrieveTransaction();
			assertEquals(-1, loadResult);

			// The hold code API field should have been set to 'TS6', and then mapped back:
			assertEquals("TS6", functionData.rtvFieldDbValue("AAA_HRC"));

			// VALID1 DB value should be 'VALIDATE (PRIMITIVE) JAVA'
			assertEquals("VALIDATE (PRIMITIVE) JAVA", functionData.rtvFieldDbValue("VALID1"));
			// VALID1 Output value should be 'VALIDATE (OUTPUT) JAVA'
			assertEquals("VALIDATE (OUTPUT) JAVA", functionData.rtvFieldOutputValue("VALID1"));
			// Tests that the Validate assignment of WorkFields
			assertEquals("VALIDATE (WORKFIELD) JAVA", functionData.rtvFieldDbValue("WORK_VALID"));

			// WORK_LOAD1 should have been assigned (by the Java code) to the value in WORK_INIT,
			// which in turn should have been initialised to INIT
			assertEquals("INIT", functionData.rtvFieldDbValue("WORK_LOAD1"));

			functionHandler.applyTransaction();
			Toolbox.printList(functionHandler.print());

			messages = functionHandler.rtvFunctionMessages().getMessages();
			FunctionToolboxStub.printMessages(messages);
			assertEquals(0, messages.size());

			// retrieve journal header.
			JournalHeader journalHeader = functionHandler.getFhd().getJournalHeader();
			assertNotNull(journalHeader);
			System.out.println("Journal Header [" + journalHeader + "]");

			// TODO: Validate the details have been correctly updated
			System.out.println("The GYPF record must be checked for the actual values.");

		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Test for conditional module processing (Load, Update and PV)<br/>
	 * The first test has checked that no operations occur when the Java exit returns false. Now need to 'switch on' module
	 * execution.
	 * <p>
	 * This method uses the "TSX" hold reason code
	 */
	public void testStubUE6_002()
	{
		try
		{
			// Following assertion will fail if not authorised
			assertTrue(functionHandler.doNewTransaction("UE6", ""));
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			functionData.chgFieldDbValue("BBB_HRC", "#';"); // Invalid
			functionData.chgFieldDbValue("PV_COND", "Y");

			List<FunctionMessage> messages = validateFunction(functionHandler);
			assertTrue(containsMessage(functionHandler, "KSM2039Hold Code", "BBB_HRC"));
			// Now change the conditional of the PV module call:
			functionData.chgFieldDbValue("PV_COND", "");
			messages = validateFunction(functionHandler);
			// There should be no error messages, because the PV module is not now executed
			assertEquals(0, messages.size());

			// Switch validation and load processing back on so that both modules are executed
			functionData.chgFieldDbValue("PV_COND", "Y");
			functionData.chgFieldDbValue("LOAD_COND", "Y");
			functionData.chgFieldDbValue("BBB_HRC", "TSX"); // A valid code

			// int loadResult = functionHandler.loadKeyFieldSet("PRIMARY", "");
			int loadResult = functionHandler.applyRetrieveTransaction();
			assertEquals(-1, loadResult);

			// Toggle the value of the description (and save for later checking). Note use of upper case to avoid comparison issues
			// if the input field has UpperCase set on.
			String newBBB_Description = "BBB_HRD DESCRIPTION 1".equals(functionData.rtvFieldDbValue("UPD_DESC")) ? "BBB_HRD DESCRIPTION 2"
							: "BBB_HRD DESCRIPTION 1";

			// Switch on the update API module
			functionData.chgFieldDbValue("UPD_COND", "Y");
			// The Java Update exit for the BBB_HRD field will actually use the UPD_DESC value
			functionData.chgFieldDbValue("UPD_DESC", newBBB_Description);

			functionHandler.applyTransaction();
			Toolbox.printList(functionHandler.print());

			messages = functionHandler.rtvFunctionMessages().getMessages();
			FunctionToolboxStub.printMessages(messages);
			assertEquals(0, messages.size());

			// Now run transaction again to see if the update was applied:
			// Following assertion will fail if not authorised
			assertTrue(functionHandler.doNewTransaction("UE6", ""));
			functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			functionData.chgFieldDbValue("LOAD_COND", "Y");
			functionData.chgFieldDbValue("BBB_HRC", "TSX"); // A valid code

			// loadResult = functionHandler.loadKeyFieldSet("PRIMARY", "");
			loadResult = functionHandler.applyRetrieveTransaction();
			assertEquals(-1, loadResult);

			assertEquals(newBBB_Description, functionData.rtvFieldDbValue("BBB_HRD"));

		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Wraps the call to validate to avoid calling code having to catch EQException
	 */
	private List<FunctionMessage> validateFunction(FunctionHandler functionHandler)
	{
		try
		{
			functionHandler.validate();
		}
		catch (EQException e)
		{
			throw new RuntimeException(e);
		}

		return functionHandler.rtvFunctionMessages().getMessages();
	}

	private boolean containsMessage(FunctionHandler functionHandler, String dsepmsText, String fieldId)
	{
		boolean result = false;
		List<FunctionMessage> messages = functionHandler.rtvFunctionMessages().getMessages();
		for (FunctionMessage message : messages)
		{
			if (fieldId == null || fieldId.equals(message.getFieldName()))
			{
				EQMessage eqMessage = message.getEqMessage();

				if (eqMessage.getDsepms().equals(dsepmsText))
				{
					// Found a match...

					result = true;
				}
			}
		}
		return result;
	}
}
