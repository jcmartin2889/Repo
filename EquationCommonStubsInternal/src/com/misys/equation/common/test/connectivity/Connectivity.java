package com.misys.equation.common.test.connectivity;

import junit.framework.TestCase;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.EquationLogger;

public class Connectivity extends TestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: Connectivity.java 16593 2013-06-24 15:32:19Z perkinj1 $";

	/**
	 * Logger for this class
	 */
	private static final EquationLogger LOG = new EquationLogger(TestEnvironment.class);

	public void testOkayThroughContext()
	{
		try
		{
			String systemId = "SLOUGH1";
			String unitId = "EQQ";
			String userId = "EQUIADMIN";
			String password = "EQUIADMIN";
			String s = EquationCommonContext.getContext().getEqSession(systemId, unitId, userId, password, "FREDDY", "127.0.0.1",
							EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, EquationCommonContext.SESSION_API_MODE, null);
			EquationStandardSession session = EquationCommonContext.getContext().getEquationUser("FREDDY").getSession();
			LOG.info("finished");
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}

	public void testOkayThroughBeans()
	{
		try
		{
			String systemId = "SLOUGH1";
			String unitId = "EQQ";
			String userId = "EQUIADMIN";
			String password = "EQUIADMIN";
			EquationSystem system = new EquationSystem(systemId, userId, password);
			EquationUnit unit = system.getUnit(unitId);
			@SuppressWarnings("unused")
			EquationStandardSession session = unit.getEquationSessionPool().getEquationStandardSession();
			LOG.info("finished");
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}
	public void testNoPooling()
	{
		try
		{
			String systemId = "SLOUGH1";
			String unitId = "EQX";
			String userId = "EQUIADMIN";
			String password = "EQUIADMIN";
			LOG.info("finished");
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}
	@SuppressWarnings("unused")
	public void testInvalidSystem()
	{
		try
		{
			String systemId = "COW1";
			String unitId = "EQX";
			String userId = "EQUIADMIN";
			String password = "EQUIADMIN";
			EquationSystem system = new EquationSystem(systemId, userId, password);
			EquationUnit unit = system.getUnit(unitId);
			EquationStandardSession session = unit.getEquationSessionPool().getEquationStandardSession();
			LOG.info("finished");
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}
	@SuppressWarnings("unused")
	public void testInvalidUnit()
	{
		try
		{
			String systemId = "SLOUGH1";
			String unitId = "XZZ";
			String userId = "EQUIADMIN";
			String password = "EQUIADMIN";
			EquationSystem system = new EquationSystem(systemId, userId, password);
			EquationUnit unit = system.getUnit(unitId);
			EquationStandardSession session = unit.getEquationSessionPool().getEquationStandardSession();
			LOG.info("finished");
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}
	@SuppressWarnings("unused")
	public void testInvalidUser()
	{
		try
		{
			String systemId = "SLOUGH1";
			String unitId = "EQX";
			String userId = "ZOG";
			String password = "EQUIADMIN";
			EquationSystem system = new EquationSystem(systemId, userId, password);
			EquationUnit unit = system.getUnit(unitId);
			EquationStandardSession session = unit.getEquationSessionPool().getEquationStandardSession();
			LOG.info("finished");
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}
	@SuppressWarnings("unused")
	public void testInvalidPassword()
	{
		try
		{
			String systemId = "SLOUGH1";
			String unitId = "EQX";
			String userId = "EQUIADMIN";
			String password = "IFORGOTIT";
			EquationSystem system = new EquationSystem(systemId, userId, password);
			EquationUnit unit = system.getUnit(unitId);
			EquationStandardSession session = unit.getEquationSessionPool().getEquationStandardSession();
			LOG.info("finished");
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}
}
