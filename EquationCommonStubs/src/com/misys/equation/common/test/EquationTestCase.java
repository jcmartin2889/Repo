package com.misys.equation.common.test;

import junit.framework.TestCase;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationData;
import com.misys.equation.common.access.EquationDataList;
import com.misys.equation.common.access.EquationPVMetaData;
import com.misys.equation.common.access.EquationStandardEnquiry;
import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.common.utilities.EqDataToolbox;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.EquationPVMetaDataHelper;
import com.misys.equation.common.utilities.Toolbox;

/**
 * @author weddelc1
 */
public class EquationTestCase extends TestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationTestCase.java 9442 2010-10-12 09:42:28Z MACDONP1 $";
	protected final static String WORKSTATIONID = "JUNT";
	protected static EquationStandardSession session;
	protected static EquationUnit unit;
	protected static EquationUser user;

	/**
	 * Logger for this class
	 */
	private static final EquationLogger LOG = new EquationLogger(TestEnvironment.class);

	/**
	 * Sets up the initial conditions for the test
	 * <p>
	 * This method is executed automatically by JUnit
	 */
	@Override
	protected void setUp() throws Exception
	{
		TestEnvironment te = TestEnvironment.getTestEnvironment();

		if (EquationCommonContext.getContext().checkIfGPIsInstalled(te.getSessionId()))
		{
			EquationCommonContext.getContext().loadEquationConfigurationPropertiesBean();
			EquationCommonContext.getContext().runSystemStatusManagerMonitor();
		}
		if (session == null)
		{
			unit = te.getEquationUnit();
			user = te.getEquationUser();
			session = user.getSession();
		}

		super.setUp();
	}
	/**
	 * Tidies up after the test has finished.
	 * <p>
	 * This method is executed automatically by JUnit. you should implement your house keeping in this method
	 */
	@Override
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	/**
	 * Tests whether the standard transaction works
	 * 
	 * @param transaction
	 *            - the Equation Standard Transaction
	 */
	protected void assertTestStandardTransaction(EquationStandardTransaction transaction, boolean expectedResult) throws Exception
	{
		System.out.println("JobId = " + session.getConnectionWrapper().getJobId() + "\n");
		session.addEquationTransaction(transaction);
		session.applyTransactions();
		session.commitTransactions();
		System.out.println(transaction);
		System.out.println(transaction.getWarningList());
		String errorMessage = "\r\n" + "Errors: " + transaction.getErrorList() + "\r\n";
		assertEquals(errorMessage, expectedResult, transaction.getValid());
	}

	/**
	 * Tests validate mode
	 * 
	 * @param transaction
	 *            - the Equation Standard Transaction
	 * @param expectedResult
	 *            - true if the transaction is expected to be successfully applied
	 * 
	 * @throws Exception
	 */
	protected void assertTestValidateStandardTransaction(EquationStandardTransaction transaction, boolean expectedResult)
					throws Exception
	{
		System.out.println("JobId = " + session.getConnectionWrapper().getJobId() + "\n");
		session.validateEquationTransaction(transaction);
		System.out.println(transaction);
		System.out.println(transaction.getWarningList());
		String errorMessage = "\r\n" + "Errors: " + transaction.getErrorList() + "\r\n";
		assertEquals(errorMessage, expectedResult, transaction.getValid());
	}

	/**
	 * Tests retrieval mode
	 * 
	 * @param transaction
	 *            - the Equation Standard Transaction
	 * @param expectedResult
	 *            - true if the transaction is expected to be successfully applied
	 * 
	 * @throws Exception
	 */
	protected void assertTestRetrievalStandardTransaction(EquationStandardTransaction transaction, boolean expectedResult)
					throws Exception
	{
		System.out.println("JobId = " + session.getConnectionWrapper().getJobId() + "\n");
		session.retrieveEquationTransaction(transaction);
		System.out.println(transaction);
		System.out.println(transaction.getWarningList());
		String errorMessage = "\r\n" + "Errors: " + transaction.getErrorList() + "\r\n";
		assertEquals(errorMessage, expectedResult, transaction.getValid());
	}

	/**
	 * Tests whether the standard enquiry works
	 * 
	 * @param transaction
	 *            - the Equation Standard Enquiry
	 * @param expectedResult
	 *            - true if the transaction is expected to be successfully applied
	 */
	protected void assertTestStandardEnquiry(EquationStandardEnquiry enquiry, boolean expectedResult) throws Exception
	{
		enquiry = session.executeEnquiry(enquiry);
		System.out.println(enquiry);
		String errorMessage = "\r\n" + "Error code: " + enquiry.getErcod() + "\r\n" + "Error parm: " + enquiry.getErprm() + "\r\n";
		assertEquals(errorMessage, expectedResult, enquiry.getValid());
	}

	/**
	 * Tests whether the standard list enquiry works
	 * 
	 * @param enquiry
	 *            - the Equation Standard List Enquiry
	 * @param expectedResult
	 *            - true if the transaction is expected to be successfully applied
	 */
	protected void assertTestStandardListEnquiry(EquationStandardListEnquiry enquiry, boolean expectedResult) throws Exception
	{
		enquiry = session.executeListEnquiry(enquiry);
		System.out.println(enquiry);
		String errorMessage = "\r\n" + "Error code: " + enquiry.getErcod() + "\r\n" + "Error parm: " + enquiry.getErprm() + "\r\n";
		assertEquals(errorMessage, expectedResult, enquiry.getValid());
	}

	protected void assertTestPVMetaData(String pvName, boolean expectedResult) throws Exception
	{
		EquationPVMetaData pvmetadata = unit.getPVMetaData(pvName);

		// xml
		EqBeanFactory eqBeanFactory = EqBeanFactory.getEqBeanFactory();
		String xml = eqBeanFactory.serialiseBeanAsXML(pvmetadata);
		System.out.println(xml);
		// finish
		System.out.println(pvmetadata);
	}

	/**
	 * Tests whether the standard list enquiry works
	 * 
	 * @param enquiry
	 *            - the Equation Standard List Transaction
	 * @param expectedResult
	 *            - true if the transaction is expected to be successfully applied
	 */
	protected void assertTestStandardIncrementalListEnquiry(EquationStandardListEnquiry enquiry, boolean expectedResult)
					throws Exception
	{
		enquiry = session.executeIncrementalListEnquiry(enquiry);
		if (enquiry.isComplete())
		{
			System.out.println(enquiry);
		}
		String errorMessage = "\r\n" + "Error code: " + enquiry.getErcod() + "\r\n" + "Error parm: " + enquiry.getErprm() + "\r\n";
		assertEquals(errorMessage, true, enquiry.getValid());
	}

	/**
	 * Return the Equation Standard Transaction
	 * 
	 * @param identifier
	 *            - session identifier
	 * 
	 * @return the Equation Standard Transaction
	 * 
	 * @throws Exception
	 */
	protected EquationStandardTransaction getEquationStandardTransaction(String identifier) throws Exception
	{
		return new EquationStandardTransaction(identifier, session);
	}

	protected EquationPVMetaData getEquationPVMetaData(String pvId) throws Exception
	{
		return unit.getPVMetaData(pvId);
	}

	protected EquationData getEQData(String pvId, String ccn, String decode, String newCode)
	{
		StringBuffer dsccn = new StringBuffer(Toolbox.pad(ccn, EquationPVMetaDataHelper.LEN_CCN));
		return EqDataToolbox.callEqData(session, pvId, decode, dsccn.toString(), " ", newCode);
	}

	protected EquationData getEQData(String pvId, String ccn, String decode, String newCode, String blankAllowed)
	{
		StringBuffer dsccn = new StringBuffer(Toolbox.pad(ccn, EquationPVMetaDataHelper.LEN_CCN));
		return EqDataToolbox.callEqData(session, pvId, decode, dsccn.toString(), blankAllowed, newCode);
	}

	protected EquationDataList getEQDataList(String pvName, String query, String decode) throws EQException
	{
		EquationDataList dataList = new EquationDataList();
		dataList.initialise(user, pvName, decode, query, "", 1, 10);
		return dataList;
	}

	/**
	 * Return the Equation Standard Transaction
	 * 
	 * @param identifier
	 *            - session identifier
	 * @gzName - GZ Journal name
	 * @gsName - GS Journal name
	 * @offSet - GS offset in DSAIM
	 * 
	 * @return the Equation Standard Transaction
	 * 
	 * @throws Exception
	 */
	protected EquationStandardTransaction getEquationStandardTransaction(String identifier, String gzName, String gsName, int offSet)
					throws Exception
	{
		return new EquationStandardTransaction(identifier, session, gzName, gsName, offSet);
	}

	/**
	 * Return the Equation Standard Transaction that uses GS record
	 * 
	 * @param identifier
	 *            - session identifier
	 * @param gsOffset
	 *            - GS offset within the DSAIM
	 * 
	 * @return the Equation Standard Transaction that uses GS record
	 * 
	 * @throws Exception
	 */
	protected EquationStandardTransaction getEquationStandardTransaction(String identifier, int gsOffset) throws Exception
	{
		return new EquationStandardTransaction(identifier, session, gsOffset);
	}

	/**
	 * Return the Equation Standard Enquiry
	 * 
	 * @param identifier
	 *            - session identifier
	 * 
	 * @return the Equation Standard Enquiry
	 * 
	 * @throws Exception
	 */
	protected EquationStandardEnquiry getEquationStandardEnquiry(String identifier) throws Exception
	{
		return new EquationStandardEnquiry(identifier, session);
	}

	/**
	 * Return the Equation Standard Enquiry
	 * 
	 * @param identifier
	 *            - session identifier
	 * @param fixedFormat
	 *            - fixed data format file (e.g. 'HZD203')
	 * 
	 * @return the Equation Standard Enquiry
	 * 
	 * @throws Exception
	 */
	protected EquationStandardEnquiry getEquationStandardEnquiry(String identifier, String fixedFormat) throws Exception
	{
		return new EquationStandardEnquiry(identifier, fixedFormat, session);
	}

	/**
	 * Return the Equation Standard List Enquiry
	 * 
	 * @param identifier
	 *            - session identifier
	 * 
	 * @return the Equation Standard List Enquiry
	 * 
	 * @throws Exception
	 */
	protected EquationStandardListEnquiry getEquationStandardListEnquiry(String identifier) throws Exception
	{
		return new EquationStandardListEnquiry(identifier, session);
	}

	/**
	 * Return the Equation Standard List Enquiry
	 * 
	 * @param identifier
	 *            - enquriy identifier
	 * @param fixedFormat
	 *            - fixed data format file (e.g. 'HZU051')
	 * @param listFormat
	 *            - list data format file (e.g. 'HZU052')
	 * 
	 * 
	 * @return the Equation Standard List Enquiry
	 * 
	 * @throws Exception
	 */
	protected EquationStandardListEnquiry getEquationStandardListEnquiry(String identifier, String fixedFormat, String listFormat)
					throws Exception
	{
		return new EquationStandardListEnquiry(identifier, fixedFormat, listFormat, session);
	}
	/**
	 * Apply transaction
	 * 
	 * @param transaction
	 *            - transaction
	 * 
	 * @throws Exception
	 */
	public boolean applyTransaction(EquationStandardTransaction transaction, boolean expectedResult) throws Exception
	{
		assertTestStandardTransaction(transaction, expectedResult);
		return (transaction.getValid() == expectedResult);
	}

	/**
	 * Apply transaction
	 * 
	 * @param transaction
	 *            - transaction
	 * 
	 * @throws Exception
	 */
	public boolean applyTransaction(EquationStandardEnquiry transaction, boolean expectedResult) throws Exception
	{
		assertTestStandardEnquiry(transaction, expectedResult);
		return (transaction.getValid() == expectedResult);
	}

	/**
	 * Apply transaction
	 * 
	 * @param transaction
	 *            - transaction
	 * 
	 * @throws Exception
	 */
	public boolean applyTransaction(EquationStandardListEnquiry transaction, boolean expectedResult) throws Exception
	{
		assertTestStandardListEnquiry(transaction, expectedResult);
		return (transaction.getValid() == expectedResult);
	}

	/**
	 * Retrieve transaction
	 * 
	 * @param transaction
	 *            - transaction
	 * 
	 * @throws Exception
	 */
	public boolean applyRetrieval(EquationStandardTransaction transaction, boolean expectedResult) throws Exception
	{
		assertTestRetrievalStandardTransaction(transaction, expectedResult);
		return (transaction.getValid() == expectedResult);
	}

	/**
	 * Validate transaction
	 * 
	 * @param transaction
	 *            - transaction
	 * 
	 * @throws Exception
	 */
	public boolean applyValidate(EquationStandardTransaction transaction, boolean expectedResult) throws Exception
	{
		assertTestValidateStandardTransaction(transaction, expectedResult);
		return (transaction.getValid() == expectedResult);
	}
}
