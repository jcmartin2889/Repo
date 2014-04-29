/**
 * Copyright and all other intellectual property rights in this software, in any form, is vested in Misys International Banking
 * Systems Ltd ("Misys") or a related company.
 * 
 * This software may not be copied, amended, compiled, translated, or developed; or sold, leased, hired, rented, or disclosed to any
 * third party without the prior written consent of Misys.
 * 
 * Copyright Misys International Banking Systems Ltd, 1975 and later
 */

package com.misys.equation.common.sample;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;

/**
 * A sample for illustration for calling an iSeries program from Java. Stored procedures are used.
 */
public class EquationProgramCallSample
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationProgramCallSample.java 15460 2013-03-08 13:11:48Z williae1 $";

	public static void main(String[] args) throws Exception
	{
		EquationProgramCallSample programCallSample = new EquationProgramCallSample();
		programCallSample.process();
	}
	public boolean process()
	{
		// Program UTS13C does checks to see if the object exists. There are 4 parameters: object, library, type and an Y/N exists
		// flag.
		// Program calls can be surround by transaction control processing to start, end and commit transactions (see
		// EquationStandardTransactionSample)
		boolean continueProcessing = true;
		try
		{
			// Get a connection to the iSeries. A QZDASONIT job is created and a session identifier is returned.
			EquationStandardSession session = EquationContextSample.getContext().getSampleSession();
			// Create the stored procedure if it does not exist
			// In a real situation stored procedures should be created in startup processing
			createUTS13CStoredProcedure(session);
			// First program call - object exists
			boolean exists = callUTS13CStoredProcedure(session, "CHPF", session.getUnit().getKFILLibrary(), "*FILE");
			System.out.println("Object CHPF exists = " + exists);
			// Second program call - object does not exist
			exists = callUTS13CStoredProcedure(session, "CHPFX", session.getUnit().getKFILLibrary(), "*FILE");
			System.out.println("Object CHPFX exists = " + exists);
			// Drop the stored procedure if it exists.
			// In a real situation the drop of stored procedure would not be used
			dropUTS13CStoredProcedure(session);
			// Log off the session
			EquationCommonContext.getContext().logOffSession(session.getSessionIdentifier());
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return continueProcessing;
	}
	/**
	 * Create stored procedure to call program UTS13C (which does a check to see if the object exists)
	 * 
	 * @param session
	 *            - the Equation Session
	 * @throws Exception
	 */
	private static void createUTS13CStoredProcedure(EquationStandardSession session) throws Exception
	{
		boolean procedureExists = EquationSQLToolboxSample
						.procedureExists(session, session.getUnit().getKLIBLibrary(), "UTS13CPRC");
		if (!procedureExists)
		{
			String unitId = session.getUnitId();
			String createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/UTS13CPRC " + "( " + "IN OBJECT CHAR(10), "
							+ "IN LIBRARY CHAR(10), " + "IN TYPE CHAR(7), " + "OUT EXISTS CHAR(1)) " + "LANGUAGE CL "
							+ "NOT DETERMINISTIC " + "NO SQL " + "EXTERNAL NAME UTS13C " + "PARAMETER STYLE GENERAL";
			Connection connection = session.getConnectionWrapper().getConnection();
			Statement createProcedureStatement = null;
			try
			{
				createProcedureStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
				createProcedureStatement.execute(createSQLObject);
				session.connectionCommit();
			}
			catch (Exception e)
			{
				throw e;
			}
			finally
			{
				SQLToolbox.close(createProcedureStatement);
			}
		}
	}
	/**
	 * Drop stored procedure for program UTS13C
	 * 
	 * @param session
	 *            - the Equation Session
	 * @throws Exception
	 */
	private static void dropUTS13CStoredProcedure(EquationStandardSession session) throws Exception
	{
		boolean procedureExists = EquationSQLToolboxSample
						.procedureExists(session, session.getUnit().getKLIBLibrary(), "UTS13CPRC");
		if (procedureExists)
		{
			String unitId = session.getUnitId();
			String dropSQLObject = "DROP SPECIFIC PROCEDURE KLIB" + unitId.trim() + "/UTS13CPRC";
			Connection connection = session.getConnectionWrapper().getConnection();
			Statement dropProcedureStatement = null;
			try
			{
				dropProcedureStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
				dropProcedureStatement.execute(dropSQLObject);
				session.connectionCommit();
			}
			catch (Exception e)
			{
				throw e;
			}
			finally
			{
				SQLToolbox.close(dropProcedureStatement);
			}
		}
	}
	/**
	 * Call UTS13C stored procedure to determine if an object exists
	 * 
	 * @param session
	 * @param object
	 * @param library
	 * @param type
	 * @return true if the object exists
	 * @throws EQException
	 * @throws SQLException
	 */
	public boolean callUTS13CStoredProcedure(EquationStandardSession session, String object, String library, String type)
					throws EQException, SQLException
	{
		boolean exists = false;
		CallableStatement chkobj = null;
		try
		{
			// Get the chkobj API
			chkobj = session.getConnectionWrapper().getConnection().prepareCall("{ CALL UTS13CPRC (?, ?, ?, ?) }");
			// Set the parameter variables in the SQL procedure
			chkobj.setString(1, Toolbox.pad(object, 10));
			chkobj.setString(2, Toolbox.pad(library, 10));
			chkobj.setString(3, Toolbox.pad(type, 7));
			// Register all parameters
			chkobj.registerOutParameter(4, Types.CHAR);
			// Call the API
			chkobj.execute();
			// Check the return parameters
			String existsString = chkobj.getString(4).trim();
			if (existsString.equals("Y"))
			{
				exists = true;
			}
			else
			{
				exists = false;
			}
		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			SQLToolbox.close(chkobj);
		}
		return exists;
	}
}
