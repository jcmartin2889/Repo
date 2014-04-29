package com.misys.equation.common.test.connectivity;

import java.sql.Connection;

import junit.framework.TestCase;

import org.springframework.util.Assert;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.EquationLogger;

public class EquationPoolConnectionTest extends TestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationPoolConnectionTest.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";

	/** Logger for this class */
	private static final EquationLogger LOG = new EquationLogger(EquationPoolConnectionTest.class);

	/**
	 * This test is going to evaluate the database connection.
	 */
	public void testEquationPoolConnection()
	{
		Connection connection = TestEnvironment.getTestEnvironment().getStandardSession().getConnection();

		try
		{
			Assert.isTrue(connection.isClosed());
		}
		catch (Exception exception)
		{
			// TODO: handle exception
		}
		Assert.notNull(connection);
	}

	/**
	 * This test is check we can still access the KSMMSGF.
	 */
	public void testKSMs()
	{

		EquationStandardSession session;
		try
		{
			session = TestEnvironment.getTestEnvironment().getEquationUnit().getEquationSessionPool().getEquationStandardSession();
			EQMessage message = session.getMessage("KSM1233");
			System.out.println(message.getFormattedMessage());
		}
		catch (EQException e)
		{
			LOG.error(e);
		}
	}
}