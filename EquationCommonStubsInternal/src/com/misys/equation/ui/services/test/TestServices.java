package com.misys.equation.ui.services.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;

public class TestServices
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestServices.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	
	/** Logger for this class */
	private static final EquationLogger LOG = new EquationLogger(TestServices.class);

	String inputBrnmStr = "LOND";
	// fields required for ASI (debit), change according to your environment
	String debitAccountBranch = "0543";
	String debitAccountBasicNumber = "012826";
	String debitAccountSuffix = "002";
	String debitCurrency = "GBP";
	String debitTransactionCode = "010";
	// fields required for ASI (credit), change according to your environment
	String creditAccountBranch = "0543";
	String creditAccountBasicNumber = "012826";
	String creditAccountSuffix = "001";
	String creditCurrency = "GBP";
	String creditTransactionCode = "510";

	String amount = "10000";
	String valueDate = "1000601";
	String reference = "MISSLI";
	EquationStandardSession session;
	public static void main(String[] args)
	{
		TestServices test = new TestServices();
		test.test();
	}
	/**
	 * @param args
	 */
	public void test()
	{
		URL endpointURL = null;
		ServiceDirectorySoapBindingStub stub = null;
		try
		{
			endpointURL = new URL("http://localhost:9080/EquationDesktop/services/ServiceDirectory");
		}
		catch (MalformedURLException e)
		{
			LOG.error(e);
		}
		try
		{
			stub = new ServiceDirectorySoapBindingStub(endpointURL, null);
		}
		catch (AxisFault e)
		{
			LOG.error(e);
		}
		try
		{
			String sessionId = stub.getEqSession("SLOUGH1", "EQ4", "EQUIADMIN", "EQUIADMIN", null, "localhost",
							EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN);
			String whatever = stub.getJobId(sessionId);
			System.out.println(sessionId + ":" + whatever);
			session = TestEnvironment.getTestEnvironment().getStandardSession();
			EquationStandardTransaction debitAddSundryItem = new EquationStandardTransaction("H15ARRASI", session);
			// Set the transaction type
			debitAddSundryItem.setMode("A");
			// Set the fields required for the sundry item
			debitAddSundryItem.setFieldValue("GZBRNM", inputBrnmStr);
			debitAddSundryItem.setFieldValue("GZAB", debitAccountBranch);
			debitAddSundryItem.setFieldValue("GZAN", debitAccountBasicNumber);
			debitAddSundryItem.setFieldValue("GZAS", debitAccountSuffix);
			debitAddSundryItem.setFieldValue("GZCCY", debitCurrency);
			debitAddSundryItem.setFieldValue("GZAMA", amount);
			debitAddSundryItem.setFieldValue("GZTCD", debitTransactionCode);
			debitAddSundryItem.setFieldValue("GZNPE", "1");
			debitAddSundryItem.setFieldValue("GZVFR", valueDate);
			debitAddSundryItem.setFieldValue("GZPBR", "SM@T");
			debitAddSundryItem.setFieldValue("GZDRF", reference);
			String data = Toolbox.cvtBytesToHexString(debitAddSundryItem.getBytes());
			String x = stub.applyTransactionData(sessionId, data, "A", "A", "ASI", "");
			System.out.println(x);
		}
		catch (RemoteException e)
		{
			LOG.error(e);
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}
}
