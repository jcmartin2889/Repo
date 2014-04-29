/*
 * This sample code is provided by Misys for illustrative purposes only.
 * 
 * These examples have not been thoroughly tested under all conditions.
 * 
 * Misys, therefore, cannot guarantee or imply reliability, serviceability, or function of these programs.
 * 
 * All programs contained herein are provided to you "AS IS" without any warranties of any kind. The implied warranties of
 * non-infringement, merchantability and fitness for a particular purpose are expressly disclaimed.
 */
package com.misys.equation.common.test.xapi.standard;

import java.util.List;

import junit.framework.TestCase;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTable;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.EquationLogger;

// *************************************************************************************************
/**
 * 
 * 
 */
// *************************************************************************************************
public class GreenFields extends TestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GreenFields.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";

	protected EquationStandardSession session;
	protected EquationUnit unit;
	protected EquationUser user;
	protected EquationStandardTable table;
	protected final EquationLogger LOG = new EquationLogger(this.getClass());

	public GreenFields()
	{

	}

	@Override
	public void setUp() throws Exception
	{
		try
		{
			unit = TestEnvironment.getTestEnvironment().getEquationUnit();
			user = TestEnvironment.getTestEnvironment().getEquationUser();
			session = user.getSession();
			createEquationStandardTable();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	public void testPRDACC()
	{

		EquationStandardTable equationStandardTable = null;
		// Have a bash...
		try
		{
			insert();
			equationStandardTable = retrieveData();
			assertEquationStandardTable(equationStandardTable);
			table = equationStandardTable;
			update();
			equationStandardTable = retrieveData();
			assertEquationStandardTable(equationStandardTable);
			table = equationStandardTable;
			delete();
			table = retrieveData();
			assertDelete();
		}
		catch (Exception exception)
		{
			rollbackTransactions();

			if (LOG.isErrorEnabled())
			{

				LOG.error("There was a problem trying to perform rollback", exception);
			}
		}
	}

	public void createEquationStandardTable() throws EQException
	{

		table = new EquationStandardTable("PRDACC", "PDRA10LF", "PRDCDE", session);
		table.setFieldValue("PRDCDE", "55");
		table.setFieldValue("PRDDSC", "Product Description");
		table.setFieldValue("PRDACC", "Product account");
		table.setBeforeImage(table.getBytes());
	}

	private void showErrors()
	{
		List<EQMessage> errors = table.getErrorList();

		for (EQMessage message : errors)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(message.toString());
			}
		}
	}

	public void insert()
	{
		try
		{
			table.setMode(IEquationStandardObject.FCT_ADD);
			session.applyEquationTable(table);
			session.commitTransactions();
			table.setMode(IEquationStandardObject.FCT_MNT);
			table = session.retrieveEquationTable(table);

		}
		catch (EQException eQException)
		{
			rollbackTransactions();

			if (LOG.isErrorEnabled())
			{
				LOG.error("There was a problem trying to insert.", eQException);
			}
		}
		showErrors();
	}

	public void assertDelete()
	{
		List<EQMessage> errors = table.getErrorList();

		for (EQMessage message : errors)
		{
			assertEquals(message.toString(), "KSM7367  Record not found in PRDACC");
		}
	}

	public void delete()
	{

		try
		{
			table.setMode(IEquationStandardObject.FCT_DEL);
			session.applyEquationTable(table);
			session.commitTransactions();
		}
		catch (EQException eQException)
		{
			rollbackTransactions();

			if (LOG.isErrorEnabled())
			{

				LOG.error("There was a problem trying to delete.", eQException);
			}
		}
		showErrors();
	}

	public void update()
	{
		try
		{
			table.setMode(IEquationStandardObject.FCT_MNT);
			table.setFieldValue("PRDDSC", "updated");
			table.setFieldValue("PRDACC", "updated");
			session.applyEquationTable(table);
			session.commitTransactions();
		}
		catch (EQException eQException)
		{
			rollbackTransactions();

			if (LOG.isErrorEnabled())
			{

				LOG.error("There was a problem trying to update.", eQException);
			}
		}
		showErrors();
	}

	public void assertEquationStandardTable(EquationStandardTable equationStandardTable)
	{

		assertEquals(table.getTableName(), equationStandardTable.getTableName());
		assertEquals(table.getFieldValue("PRDCDE"), equationStandardTable.getFieldValue("PRDCDE"));
		assertEquals(table.getFieldValue("PRDDSC"), equationStandardTable.getFieldValue("PRDDSC"));
		assertEquals(table.getFieldValue("PRDACC"), equationStandardTable.getFieldValue("PRDACC"));
	}

	public EquationStandardTable retrieveData()
	{
		String mode = null;
		EquationStandardTable equationStandardTable = null;

		try
		{
			table.setMode(mode);
			equationStandardTable = session.retrieveEquationTable(table);

		}
		catch (EQException eQException)
		{
			rollbackTransactions();

			if (LOG.isErrorEnabled())
			{

				LOG.error("There was a problem trying to perform rollback", eQException);
			}
		}
		equationStandardTable.setExists(true);
		showErrors();
		return equationStandardTable;
	}

	public void rollbackTransactions()
	{
		try
		{
			session.rollbackTransactions();
		}
		catch (Exception exception)
		{
			if (LOG.isErrorEnabled())
			{

				LOG.error("There was a problem trying to perform rollback", exception);
			}
		}
	}
}