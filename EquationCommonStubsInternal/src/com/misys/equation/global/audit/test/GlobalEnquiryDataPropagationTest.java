package com.misys.equation.global.audit.test;

import junit.framework.TestCase;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.globalprocessing.audit.EnquiryGlobalDataPropagation;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.global.test.TestEnvironmentGlobal;

public class GlobalEnquiryDataPropagationTest extends TestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalEnquiryDataPropagationTest.java 15058 2012-12-18 15:09:12Z williae1 $";
	private final EquationLogger LOG = new EquationLogger(this.getClass());
	protected EquationStandardSession session;
	private EnquiryGlobalDataPropagation enquiryGlobalDataPropagation;

	@Override
	public void setUp() throws Exception
	{
		setUpTestingEnvironment();
	}

	/**
	 * This method set Session and EqUnit.
	 */
	public void setUpTestingEnvironment()
	{
		try
		{
			TestEnvironmentGlobal.setTestEnvironment();
			session = TestEnvironmentGlobal.getTestEnvironment().getStandardSession();
			enquiryGlobalDataPropagation = new EnquiryGlobalDataPropagation(session);

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
	 * This method will test all predefined global processing sessions.
	 */
	public void testSession()
	{
		enquiryGlobalDataPropagation.setCurrentSequence("59");
		enquiryGlobalDataPropagation.getGAARecordDataModel();

	}
}