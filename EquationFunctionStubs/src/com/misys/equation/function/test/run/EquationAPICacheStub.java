package com.misys.equation.function.test.run;

import com.misys.equation.common.access.EquationPVData;
import com.misys.equation.common.access.EquationStandardValidation;
import com.misys.equation.common.utilities.EquationAPICacheHandler;

// Via API
public class EquationAPICacheStub extends FunctionHandlerStubTestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationAPICacheStub.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	public EquationAPICacheStub()
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
		EquationAPICacheStub test = new EquationAPICacheStub();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		try
		{
			EquationStandardValidation equationStandardValidation1 = new EquationStandardValidation("", "C8R01R", "GBP", "", "");
			equationStandardValidation1.setEquationPVData(new EquationPVData(session.getUnit().getPVMetaData("C8R01R"), 37));
			session.executeValidate(equationStandardValidation1);
			String ccyDesc = equationStandardValidation1.getEquationPVData().getFieldValue("C8@CUR");

			EquationStandardValidation equationStandardValidation2 = new EquationStandardValidation("", "C8R01R", "USD", "", "");
			equationStandardValidation2.setEquationPVData(new EquationPVData(session.getUnit().getPVMetaData("C8R01R"), 37));
			EquationStandardValidation equationStandardValidation3 = new EquationStandardValidation("", "C8R01R", "XXX", "", "N");
			equationStandardValidation3.setEquationPVData(new EquationPVData(session.getUnit().getPVMetaData("C8R01R"), 37));
			EquationStandardValidation equationStandardValidation4 = new EquationStandardValidation("", "OSR33R", "ACC1RDS133463",
							"", "");
			equationStandardValidation4.setEquationPVData(new EquationPVData(session.getUnit().getPVMetaData("OSR33R"), 37));

			EquationAPICacheHandler equationAPICacheHandler = new EquationAPICacheHandler();
			equationAPICacheHandler.addAPICache("", equationStandardValidation1);
			equationAPICacheHandler.addAPICache("", equationStandardValidation2);
			equationAPICacheHandler.addAPICache("", equationStandardValidation3);
			equationAPICacheHandler.addAPICache("", equationStandardValidation4);

			equationAPICacheHandler.updateAll(session);

			equationAPICacheHandler.getFromCache("", equationStandardValidation1);
			equationAPICacheHandler.getFromCache("", equationStandardValidation2);
			equationAPICacheHandler.getFromCache("", equationStandardValidation3);
			equationAPICacheHandler.getFromCache("", equationStandardValidation4);

			boolean valid = true;
			System.out.println("1. Currency check");
			System.out.println(ccyDesc);
			System.out.println(equationStandardValidation1.getEquationPVData().getFieldValue("C8@CUR"));
			valid = equationStandardValidation1.getEquationPVData().getFieldValue("C8@CUR").equals(ccyDesc);

			if (valid)
			{
				System.out.println("2. Currency check");
				System.out.println(equationStandardValidation2.getEquationPVData().getFieldValue("C8@CUR"));
				valid = equationStandardValidation2.getEquationPVData().getFieldValue("C8@CUR").indexOf("US DOLLARS") >= 0;
			}

			if (valid)
			{
				System.out.println("3. Error check");
				System.out.println(equationStandardValidation3.getError());
				valid = !equationStandardValidation3.getError().equals("");
			}

			if (valid)
			{
				System.out.println("4. Deal check");
				System.out.println(equationStandardValidation4.getEquationPVData().getFieldValue("$CUS"));
				System.out.println(equationStandardValidation4.getEquationPVData().getFieldValue("$CCY"));
				valid = !equationStandardValidation4.getEquationPVData().getFieldValue("$CUS").equals("");
			}

			if (valid)
			{
				System.out.println("OK");
			}

			return valid;

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

	public void testStub03NoEFC_001()
	{
		EquationAPICacheStub stub = new EquationAPICacheStub();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
