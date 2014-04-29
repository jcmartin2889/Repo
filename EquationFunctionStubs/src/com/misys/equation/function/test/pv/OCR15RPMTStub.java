package com.misys.equation.function.test.pv;

import com.misys.equation.function.beans.FieldData;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.test.run.FunctionHandlerStubTestCase;
import com.misys.equation.function.test.run.FunctionToolboxStub;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.HTMLToolbox;

/**
 * FunctionHandler Stub 2
 * 
 * This is how to use the Function Handler validation each screen separately
 * 
 */
public class OCR15RPMTStub extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OCR15RPMTStub.java 6793 2010-03-31 12:10:20Z deroset $";

	public OCR15RPMTStub()
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
		OCR15RPMTStub test = new OCR15RPMTStub();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;

		try
		{
			// create the function handler
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");

			// create the function
			functionHandler.doNewTransaction("ALZ", "");

			// Setup the field
			InputField inputField = new InputField();
			inputField.setId("userId");
			FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "OCR15R", "", false, "N"));

			// Setup FunctionData
			FieldData fieldData = new FieldData(inputField.getId(), "A", 10, 0);
			FunctionData functionData = new FunctionData();
			functionData.insertFieldData(inputField.getId(), fieldData, "", false);

			HTMLToolbox htmlToolbox = new HTMLToolbox();
			htmlToolbox.setFunctionData(functionData);
			htmlToolbox.setSession(functionHandler.rtvEquationSession());
			htmlToolbox.setEqUser(user);
			String str = htmlToolbox.getScriptPMT(functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunction(),
							inputField, "OCR15R", "", "");
			System.out.println(str);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	// setPromptButtonNoWF('Supervisor', 'userId', 'OCR15R', '','', '',
	// ['$USID','$UNAM'],['User','Name'],['0','4'],['4','35'],0,4,'$USID,userId,0,4,N,$UNAM,.*,4,35,N,$OID,.*,39,3,N,$LNM,.*,42,2,N,$BRNM,.*,44,4,N,$LVA,.*,48,1,N,$PWD,.*,49,10,N,$UNAM,userIdOutput,4,35,Y,$USID,userIdDB,0,4,Y',['OCUSID','OCUNAM'],5);

}
