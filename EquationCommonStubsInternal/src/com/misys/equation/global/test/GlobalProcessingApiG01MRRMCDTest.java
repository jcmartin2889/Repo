package com.misys.equation.global.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.GlobalProcessingApiExecuter;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.EquationLogger;

/**
 * This test is going to test the execution of the H70DER program in a global processing environment. It will execute this API using
 * all predefined global processing sessions.
 * 
 * @author deroset
 * 
 */
public class GlobalProcessingApiG01MRRMCDTest extends TestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalProcessingApiG01MRRMCDTest.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	private final EquationLogger LOG = new EquationLogger(this.getClass());
	private EquationUnit unit;
	protected EquationStandardSession session;
	private GlobalProcessingApiExecuter globalProcessingApiExecuter;
	private HashMap<String, String> apiFields;
	private final String apiName = "G01MRRMCD";
	private final int expectedNumberOfSession = 2;
	private String sessionIdentifier;

	@Override
	public void setUp() throws Exception
	{
		createApiFields();
		setUpTestingEnvironment();
		globalProcessingApiExecuter = new GlobalProcessingApiExecuter(session, IEquationStandardObject.FCT_RTV);
		globalProcessingApiExecuter.setApiFields(apiFields);
		globalProcessingApiExecuter.setApiName(apiName);

	}

	/**
	 * This method set Session and EqUnit.
	 */
	public void setUpTestingEnvironment()
	{
		try
		{
			TestEnvironment.setTestEnvironment();
			unit = TestEnvironment.getTestEnvironment().getEquationUnit();
			session = TestEnvironment.getTestEnvironment().getStandardSession();

		}
		catch (Exception exception)
		{
			if (LOG.isErrorEnabled())
			{
				StringBuilder message = new StringBuilder("There was an error in ");
				message.append(this.getClass().getSimpleName());
				message.append(" :setUpTestingEnvironment() ");
				LOG.error(message.toString(), exception);
			}
		}
	}

	/**
	 * This method will set all G01MRRMCD fields.
	 */
	private void createApiFields()
	{
		apiFields = new HashMap<String, String>();

		// apiFields.put("GZCUS", "BBI"); // Customer mnemonic (6A)
		// apiFields.put("GZCLC", ""); // Customer location (3A)

		// CLDCNO=CP0001
		apiFields.put("GZCPNC", "CP0001"); // Customer number (6A)

	}

	/**
	 * This method will test all predefined global processing sessions.
	 */
	public void testSession()
	{
		List<EquationStandardSession> equationStandardSessions;

		equationStandardSessions = globalProcessingApiExecuter.getEquationStandardSessions();
		assertNotNull(equationStandardSessions);
		assertEquals(equationStandardSessions.size(), expectedNumberOfSession);
	}

	/***
	 * This method will assert the result of the execution. if there is any message error the test will fail.
	 */
	public void testProgramExecution()
	{
		globalProcessingApiExecuter.executeAPI();

		Iterator<String> iterator = globalProcessingApiExecuter.getResults().keySet().iterator();
		String unitId = null;

		EquationStandardTransaction equationStandardTransaction = null;

		while (iterator.hasNext())
		{
			unitId = iterator.next();

			equationStandardTransaction = (EquationStandardTransaction) globalProcessingApiExecuter.getResults().get(unitId);
			assertNotNull(equationStandardTransaction);
			showSomedata(unitId, equationStandardTransaction);
		}
	}

	/**
	 * This method will only display some records.
	 * 
	 * @param unit
	 *            this is the current unit.
	 * @param equationStandardObject
	 *            this is the data container.
	 */
	private void showSomedata(String unit, IEquationStandardObject equationStandardObject)
	{
		System.out.println("Unit: " + unit);

		System.out.println("Name:" + equationStandardObject.getFieldValue("GZCUN"));
		System.out.println("Number:" + equationStandardObject.getFieldValue("GZCPNC"));

		System.out.println("Type:" + equationStandardObject.getFieldValue("GZCTP"));
		System.out.println("Parent country:" + equationStandardObject.getFieldValue("GZCNAP"));
		System.out.println("Residence country:" + equationStandardObject.getFieldValue("GZCNAL"));

	}

}
