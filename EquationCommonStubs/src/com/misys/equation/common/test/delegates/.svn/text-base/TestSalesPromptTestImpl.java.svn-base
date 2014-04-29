package com.misys.equation.common.test.delegates;

import java.util.List;

import com.misys.equation.common.delegates.ISalesPromptProvider;
import com.misys.equation.common.delegates.SalesPromptProviderFactory;

import junit.framework.TestCase;

public class TestSalesPromptTestImpl extends TestCase
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FileProcessor.java 7606 2010-06-01 17:04:23Z MACDONP1 $";
	SalesPromptProviderFactory providerFactory;
	ISalesPromptProvider promptProvider;

	public void setUp() throws Exception
	{
		super.setUp();
		providerFactory = new SalesPromptProviderFactory();
		providerFactory.setSalesPromptProvider();
		promptProvider = providerFactory.getSalesPromptProvider();

	}
	public void testSetRequest()
	{
		promptProvider.setRequest("0000001");
		assertEquals("Request", "0000001", promptProvider.getRequest());
	}
	public void testSetResponse()
	{
		promptProvider.setResponse();
		assertEquals(
						"Response",
						"<salesPromptFullDtls><uniqueID>01</uniqueID><salesPromptStatus>Active</salesPromptStatus><salesPromptDescription>Sales Prompt Test line 1</salesPromptDescription><salesPromptName>Test1</salesPromptName><salesPromptScore>10</salesPromptScore><salesPromptLinkText>http://www.yahoo.co.uk</salesPromptLinkText><salesPromptMessageDesc>Would you like to have test 1?</salesPromptMessageDesc><salesPromptActType>cross-sell test</salesPromptActType><salesPromptCampaignDesc>Cross sell this test</salesPromptCampaignDesc><salesPromptCampaignName>Test 1</salesPromptCampaignName><salesPromptCampaignStartDt>2013-10-10</salesPromptCampaignStartDt><salesCampaignEndDt>2014-10-09</salesCampaignEndDt></salesPromptFullDtls><salesPromptFullDtls><uniqueID>02</uniqueID><salesPromptStatus>Active</salesPromptStatus><salesPromptDescription>Sales Prompt Test line 2</salesPromptDescription><salesPromptName>Test2</salesPromptName><salesPromptScore>11</salesPromptScore><salesPromptLinkText>http://www.google.co.uk</salesPromptLinkText><salesPromptMessageDesc>Would you like to have test 2?</salesPromptMessageDesc><salesPromptActType>cross-sell test</salesPromptActType><salesPromptCampaignDesc>Cross sell this test</salesPromptCampaignDesc><salesPromptCampaignName>Test 2</salesPromptCampaignName><salesPromptCampaignStartDt>2013-10-10</salesPromptCampaignStartDt><salesCampaignEndDt>2014-10-09</salesCampaignEndDt></salesPromptFullDtls><salesPromptFullDtls><uniqueID>03</uniqueID><salesPromptStatus>Active</salesPromptStatus><salesPromptDescription>Sales Prompt Test line 3</salesPromptDescription><salesPromptName>Test3</salesPromptName><salesPromptScore>12</salesPromptScore><salesPromptLinkText>http://www.yell.com</salesPromptLinkText><salesPromptMessageDesc>Would you like to have test 3?</salesPromptMessageDesc><salesPromptActType>cross-sell test</salesPromptActType><salesPromptCampaignDesc>Cross sell this test</salesPromptCampaignDesc><salesPromptCampaignName>Test 3</salesPromptCampaignName><salesPromptCampaignStartDt>2013-10-10</salesPromptCampaignStartDt><salesCampaignEndDt>2014-10-09</salesCampaignEndDt></salesPromptFullDtls>",
						promptProvider.getResponse());
	}
	public void testParseResponse()
	{
		promptProvider.setResponse();
		List<String[]> list = promptProvider.parseResponse();
		String[] record1 = list.get(0);
		assertEquals("Parsing", "01", record1[0]);
		assertEquals("Parsing", "http://www.yahoo.co.uk", record1[1]);
		assertEquals("Parsing", "Cross sell this test", record1[2]);
		assertEquals("Parsing", "Would you like to have test 1?", record1[3]);
		assertEquals("Parsing", "Test 1", record1[4]);
	}
	public void testSendUpdate()
	{
		promptProvider.sendUpdate("01", "EQPTY0000000001", "DontMarket");

	}

}
