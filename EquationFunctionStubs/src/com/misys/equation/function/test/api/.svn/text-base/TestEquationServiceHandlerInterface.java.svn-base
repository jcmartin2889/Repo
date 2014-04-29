package com.misys.equation.function.test.api;

import bf.com.misys.eqf.types.header.Orig;
import bf.com.misys.eqf.types.header.Overrides;
import bf.com.misys.eqf.types.header.RqHeader;
import bf.com.misys.eqf.types.header.ServiceRqHeader;
import bf.com.misys.eqf.types.header.ServiceRsHeader;

import com.misys.equation.bf.EquationServiceHandler;
import com.misys.equation.common.internal.eapi.core.EQException;

// Via Save and Restore
public class TestEquationServiceHandlerInterface
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestComplexTypeLoading.java 8517 2010-08-06 13:18:06Z lima12 $";

	private String realUser = "esthertest";

	public TestEquationServiceHandlerInterface()
	{
		try
		{
			System.setProperty("disable.bankfusion.features", "true");
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters) throws EQException
	{
		TestEquationServiceHandlerInterface test = new TestEquationServiceHandlerInterface();
		test.test();
	}

	private void test() throws EQException
	{
		ServiceRqHeader serviceRqHeader = new ServiceRqHeader();

		Orig orig = new Orig();
		orig.setAppId("appId");

		Overrides overrides = new Overrides();
		// Generate warning messages
		overrides.setOverrideType("N");

		RqHeader rqHeader = new RqHeader();
		rqHeader.setSessionId("1");
		rqHeader.setUserId(realUser);
		rqHeader.setOrig(orig);
		rqHeader.setOverrides(overrides);

		serviceRqHeader.setRqHeader(rqHeader);
		serviceRqHeader.setOptionId("YE1");
		serviceRqHeader.setServiceMode("M");

		EquationServiceHandler equationServiceHandler = new EquationServiceHandler();

		System.out.println("---------Applied Txn 1 Start ------");
		Object inputServiceData = equationServiceHandler.getServiceDataClass("YE1");
		equationServiceHandler.setFieldValue(inputServiceData, "HCI_HRC_holdCode", "ABC");
		equationServiceHandler.setFieldValue(inputServiceData, "HCI_HRD_holdDescription", "description1");
		// TODO try setting non-string data. More helper methods required in EquationServiceHandler
		// equationServiceHandler.setFieldValue(inputServiceData, "HCI_DED_defaultExpiryDate", "0");

		equationServiceHandler.process(serviceRqHeader, inputServiceData);

		ServiceRsHeader serviceRsHeader = equationServiceHandler.getServiceRsHeader();
		Object outputServiceData = equationServiceHandler.getOutputServiceData();

		// TODO create good toPrint methods for the headers
		System.out.println("ServiceRsHeader " + serviceRsHeader.toString());
		System.out.println("OutputServiceData " + outputServiceData.toString());
		System.out.println("---------Applied Txn 1 End ------");

		System.out.println("---------Applied Txn 2 Start - Reuse Session ------");
		// Transaction 2 using same session identifier - make sure we don't have to redo too much
		Object inputServiceData2 = equationServiceHandler.getServiceDataClass("YE1");
		equationServiceHandler.setFieldValue(inputServiceData2, "HCI_HRC_holdCode", "ABC");
		equationServiceHandler.setFieldValue(inputServiceData2, "HCI_HRD_holdDescription", "description2");
		// TODO try setting non-string data. More helper methods required in EquationServiceHandler
		// equationServiceHandler.setFieldValue(inputServiceData, "HCI_DED_defaultExpiryDate", "0");

		equationServiceHandler.process(serviceRqHeader, inputServiceData2);

		serviceRsHeader = equationServiceHandler.getServiceRsHeader();
		outputServiceData = equationServiceHandler.getOutputServiceData();

		// TODO create good toPrint methods for the headers
		System.out.println("ServiceRsHeader " + serviceRsHeader.toString());
		System.out.println("OutputServiceData " + outputServiceData.toString());
		System.out.println("---------Applied Txn 2 End ------");

		System.out.println("---------Applied Txn 3 Start - Different User ------");
		// Transaction 3 using different session identifier
		rqHeader.setUserId("");
		Object inputServiceData3 = equationServiceHandler.getServiceDataClass("YE1");
		equationServiceHandler.setFieldValue(inputServiceData3, "HCI_HRC_holdCode", "ABC");
		equationServiceHandler.setFieldValue(inputServiceData3, "HCI_HRD_holdDescription", "description3");
		// TODO try setting non-string data. More helper methods required in EquationServiceHandler
		// equationServiceHandler.setFieldValue(inputServiceData, "HCI_DED_defaultExpiryDate", "0");

		equationServiceHandler.process(serviceRqHeader, inputServiceData3);

		serviceRsHeader = equationServiceHandler.getServiceRsHeader();
		outputServiceData = equationServiceHandler.getOutputServiceData();

		// TODO create good toPrint methods for the headers
		System.out.println("ServiceRsHeader " + serviceRsHeader.toString());
		System.out.println("OutputServiceData " + outputServiceData.toString());
		System.out.println("---------Applied Txn 3 End ------");

		System.out.println("---------Applied Txn 4 Start - Null User ------");
		// Transaction 3 using different session identifier
		rqHeader.setUserId(null);
		Object inputServiceData4 = equationServiceHandler.getServiceDataClass("YE1");
		equationServiceHandler.setFieldValue(inputServiceData4, "HCI_HRC_holdCode", "ABC");
		equationServiceHandler.setFieldValue(inputServiceData4, "HCI_HRD_holdDescription", "description4");
		// TODO try setting non-string data. More helper methods required in EquationServiceHandler
		// equationServiceHandler.setFieldValue(inputServiceData, "HCI_DED_defaultExpiryDate", "0");

		equationServiceHandler.process(serviceRqHeader, inputServiceData4);

		serviceRsHeader = equationServiceHandler.getServiceRsHeader();
		outputServiceData = equationServiceHandler.getOutputServiceData();

		// TODO create good toPrint methods for the headers
		System.out.println("ServiceRsHeader " + serviceRsHeader.toString());
		System.out.println("OutputServiceData " + outputServiceData.toString());
		System.out.println("---------Applied Txn 4 End ------");

		System.out.println("Done");

	}

}
