package com.misys.equation.function.test.datasource;

import bf.com.misys.eqf.types.header.Orig;
import bf.com.misys.eqf.types.header.Overrides;
import bf.com.misys.eqf.types.header.RqHeader;
import bf.com.misys.eqf.types.header.ServiceRqHeader;
import bf.com.misys.eqf.types.header.ServiceRsHeader;

import com.misys.equation.bf.EquationServiceHandler;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.internal.eapi.core.EQException;

// Via Save and Restore
public class TestDataSourceInterface
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestComplexTypeLoading.java 8517 2010-08-06 13:18:06Z lima12 $";

	String dataSourceName = "EQ-SLOUGH1-EW9-ESTHER";
	public TestDataSourceInterface()
	{
		try
		{
			// TODO move the creation of the pool into EquationServiceHandler probably based on equationConfiguration.properties
			System.setProperty("disable.bankfusion.features", "true");

			EquationCommonContext.getContext().initialiseUnitEnvironment("SLOUGH1", "EW9", "CASADMIN", "CASADMIN",
							EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN);
			EquationCommonContext.getContext().getEquationSystem("SLOUGH1").getUnit("EW9").initialisePool("CASADMIN", "CASADMIN",
							false, dataSourceName);

		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters) throws EQException
	{
		TestDataSourceInterface test = new TestDataSourceInterface();
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
		rqHeader.setOrig(orig);
		rqHeader.setOverrides(overrides);

		serviceRqHeader.setRqHeader(rqHeader);
		serviceRqHeader.setOptionId("GMA");
		serviceRqHeader.setServiceMode("M");

		EquationServiceHandler equationServiceHandler = new EquationServiceHandler(dataSourceName);

		Object inputServiceData = equationServiceHandler.getServiceDataClass("GMA");
		equationServiceHandler.setFieldValue(inputServiceData, "HCI_HRC_holdCode", "XXX");
		equationServiceHandler.setFieldValue(inputServiceData, "HCI_HRD_holdDescription", "description");
		// TODO try setting non-string data. More helper methods required in EquationServiceHandler
		// equationServiceHandler.setFieldValue(inputServiceData, "HCI_DED_defaultExpiryDate", "0");

		equationServiceHandler.process(serviceRqHeader, inputServiceData);

		ServiceRsHeader serviceRsHeader = equationServiceHandler.getServiceRsHeader();
		Object outputServiceData = equationServiceHandler.getOutputServiceData();

		// TODO create good toPrint methods for the headers
		System.out.println("---------Applied Txn ------");

		System.out.println("ServiceRsHeader " + serviceRsHeader.toString());
		System.out.println("OutputServiceData " + outputServiceData.toString());

		System.out.println("Done");

	}

}
