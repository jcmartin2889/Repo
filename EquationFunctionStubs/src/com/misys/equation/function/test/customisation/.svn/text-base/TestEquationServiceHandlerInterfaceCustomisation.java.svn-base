package com.misys.equation.function.test.customisation;

import java.util.Enumeration;
import java.util.HashMap;

import bf.com.misys.eqf.types.header.EqMessage;
import bf.com.misys.eqf.types.header.ExtensionDataRq;
import bf.com.misys.eqf.types.header.Orig;
import bf.com.misys.eqf.types.header.Overrides;
import bf.com.misys.eqf.types.header.RqHeader;
import bf.com.misys.eqf.types.header.ServiceRqHeader;
import bf.com.misys.eqf.types.header.ServiceRsHeader;

import com.misys.equation.bf.EquationServiceHandler;
import com.misys.equation.common.internal.eapi.core.EQException;

// Via Save and Restore
public class TestEquationServiceHandlerInterfaceCustomisation
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestComplexTypeLoading.java 8517 2010-08-06 13:18:06Z lima12 $";

	// NOTE: Setup equationConfiguration.properites to initialise the system and unit.
	private String realUser = "williae1";

	public TestEquationServiceHandlerInterfaceCustomisation()
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
		TestEquationServiceHandlerInterfaceCustomisation test = new TestEquationServiceHandlerInterfaceCustomisation();
		test.testLinkedService();
		test.testTunnelledData();
	}

	private void testLinkedService() throws EQException
	{

		EquationServiceHandler equationServiceHandler = new EquationServiceHandler("SLOUGH1", "EW9", "williae1", "williae1");

		System.out.println("---------Applied Txn 1 Start - Linked Service ------");

		ServiceRqHeader serviceRqHeader = new ServiceRqHeader();

		Orig orig = new Orig();
		orig.setAppId("appId");

		Overrides overrides = new Overrides();
		// Do not stop for warning messages
		overrides.setOverrideType("Y");

		RqHeader rqHeader = new RqHeader();
		rqHeader.setSessionId("1");
		rqHeader.setUserId(realUser);
		rqHeader.setOrig(orig);
		rqHeader.setOverrides(overrides);

		serviceRqHeader.setRqHeader(rqHeader);
		serviceRqHeader.setOptionId("YRT");
		serviceRqHeader.setServiceMode("M");

		Object inputServiceData = equationServiceHandler.getServiceDataClass("YRT");
		equationServiceHandler.setFieldValue(inputServiceData, "RRT_CCY_currency", "GBP");
		equationServiceHandler.setFieldValue(inputServiceData, "RRT_RRT_retailRateType", "CSH");
		equationServiceHandler.setFieldValue(inputServiceData, "RRT_XBT_exchangeRateBuy", "1");
		equationServiceHandler.setFieldValue(inputServiceData, "RRT_MDB_minimumDenominationBuy", ".01");

		Object secondaryServiceData = equationServiceHandler.getServiceDataClass("YRL");
		equationServiceHandler.setFieldValue(secondaryServiceData, "BUYCOMMENT_comment", "BuyComment");
		equationServiceHandler.setFieldValue(secondaryServiceData, "SELCOMMENT_comment", "SellComment");

		ExtensionDataRq extensionDataRq = new ExtensionDataRq();
		extensionDataRq.setServiceLinkageId("YRX");
		extensionDataRq.setSecondaryServiceExtension(secondaryServiceData);
		serviceRqHeader.setMisysExtensionData(extensionDataRq);

		equationServiceHandler.process(serviceRqHeader, inputServiceData);

		ServiceRsHeader serviceRsHeader = equationServiceHandler.getServiceRsHeader();
		Object outputServiceData = equationServiceHandler.getOutputServiceData();

		// TODO create good toPrint methods for the headers
		System.out.println("Status " + serviceRsHeader.getRsHeader().getStatus().getOverallStatus());
		Enumeration<? extends EqMessage> enumeration = serviceRsHeader.getRsHeader().getStatus().enumerateEqMessages();
		while (enumeration.hasMoreElements())
		{
			System.out.println("Messages " + enumeration.nextElement().getFormattedMessage());
		}
		System.out.println("OutputServiceData " + outputServiceData.toString());
		System.out.println("---------Applied Txn 1 End ------");

		System.out.println("Done");

	}
	private void testTunnelledData() throws EQException
	{

		EquationServiceHandler equationServiceHandler = new EquationServiceHandler("SLOUGH1", "EW9", "williae1", "williae1");
		System.out.println("---------Applied Txn 2 Start - Tunnelled Data ------");

		ServiceRqHeader serviceRqHeader = new ServiceRqHeader();

		Orig orig = new Orig();
		orig.setAppId("appId");

		Overrides overrides = new Overrides();
		// Do not stop for warning messages
		overrides.setOverrideType("A");

		RqHeader rqHeader = new RqHeader();
		rqHeader.setSessionId("1");
		rqHeader.setUserId(realUser);
		rqHeader.setOrig(orig);
		rqHeader.setOverrides(overrides);

		serviceRqHeader.setRqHeader(rqHeader);
		serviceRqHeader.setOptionId("YRT");
		serviceRqHeader.setServiceMode("M");

		Object inputServiceData = equationServiceHandler.getServiceDataClass("YRT");
		equationServiceHandler.setFieldValue(inputServiceData, "RRT_CCY_currency", "GBP");
		equationServiceHandler.setFieldValue(inputServiceData, "RRT_RRT_retailRateType", "CSH");
		equationServiceHandler.setFieldValue(inputServiceData, "RRT_XBT_exchangeRateBuy", "1");
		equationServiceHandler.setFieldValue(inputServiceData, "RRT_MDB_minimumDenominationBuy", ".01");

		HashMap tunnelData = new HashMap();
		tunnelData.put("BUYCOMMENT_comment", "TunnelBuyComment");
		tunnelData.put("SELCOMMENT_comment", "TunnelSellComment");

		ExtensionDataRq extensionDataRq = new ExtensionDataRq();
		extensionDataRq.setUserExtension(tunnelData);
		serviceRqHeader.setUserExtensionData(extensionDataRq);

		equationServiceHandler.process(serviceRqHeader, inputServiceData);

		ServiceRsHeader serviceRsHeader = equationServiceHandler.getServiceRsHeader();
		Object outputServiceData = equationServiceHandler.getOutputServiceData();

		// TODO create good toPrint methods for the headers
		System.out.println("Status " + serviceRsHeader.getRsHeader().getStatus().getOverallStatus());
		Enumeration<? extends EqMessage> enumeration = serviceRsHeader.getRsHeader().getStatus().enumerateEqMessages();
		while (enumeration.hasMoreElements())
		{
			System.out.println("Messages " + enumeration.nextElement().getFormattedMessage());
		}

		System.out.println("OutputServiceData " + outputServiceData.toString());
		System.out.println("---------Applied Txn 2 End ------");

		System.out.println("Done");

	}
}
