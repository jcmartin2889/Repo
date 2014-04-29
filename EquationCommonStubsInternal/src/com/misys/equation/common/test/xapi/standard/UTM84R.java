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

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.test.TestEnvironment;

// *************************************************************************************************
/**
 * 
 * 
 */
// *************************************************************************************************
public class UTM84R
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UTM84R.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EquationStandardSession session;
	// Input branch mnemonic
	String service = "UTM84R";
	String parmLnm = "";
	String parmKcd = "GK";
	String parmKsn = "AA";
	String parmLtr = "";
	String parmFlg = "";
	int parmLen = 0;
	String parmRtl = "Y";
	String inputBrnmStr = "0000";
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private UTM84R()
	{
		try
		{
			session = TestEnvironment.getTestEnvironment().getStandardSession();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public static void main(String[] inputParameters)
	{
		UTM84R test = new UTM84R();
		test.test();
	}
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	public void test()
	{
		// Have a bash...
		try
		{
			// make sure we have the procedure for calling PVs...
			String dataItemFunctionSqlString = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = ? AND SPECIFIC_NAME = '"
							+ service + "PRC'";
			PreparedStatement dataItemFunctionStatement = session.getConnection().prepareStatement(dataItemFunctionSqlString,
							ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			dataItemFunctionStatement.setString(1, session.getUnit().getKLIBLibrary());
			ResultSet dataItemFunctionRS = dataItemFunctionStatement.executeQuery();
			// ...
			if (dataItemFunctionRS.next())
			{
				if (dataItemFunctionRS.getInt(1) == 0)
				{
					// Create the required SQL procedures
					String createProcedure = "CREATE PROCEDURE KLIB" + session.getUnitId() + "/" + service + "PRC " + "( "
									+ "INOUT U81LNM CHAR(2) , " + "INOUT U81KCD CHAR(3) , " + "INOUT U81KSN CHAR(10) , "
									+ "INOUT U81LTR CHAR(35) , " + "INOUT U81FLG CHAR(1) , " + "INOUT U81LEN DEC(3) , "
									+ "INOUT U81RTL CHAR(1) " + ") " + "LANGUAGE RPGLE " + "NOT DETERMINISTIC " + "NO SQL "
									+ "EXTERNAL NAME " + service + " " + "PARAMETER STYLE GENERAL";
					try
					{
						Statement createProcedureStatement = session.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY,
										ResultSet.CONCUR_READ_ONLY);
						createProcedureStatement.execute(createProcedure);
						createProcedureStatement.close();
					}
					catch (Exception e)
					{
						e.printStackTrace();
						System.out.println("Failed to create PV Procedure");
					}
				}
			}
			// Clear the decks so we are ready to start with XA
			session.connectionCommit();
			// Set the parameter variables in the SQL procedure
			CallableStatement equationCaller = session.getConnection().prepareCall(
							"{ CALL " + service + "PRC (?, ?, ?, ?, ?, ?, ?) }");
			// Register the output
			equationCaller.registerOutParameter(1, Types.CHAR);
			equationCaller.registerOutParameter(2, Types.CHAR);
			equationCaller.registerOutParameter(3, Types.CHAR);
			equationCaller.registerOutParameter(4, Types.CHAR);
			equationCaller.registerOutParameter(5, Types.CHAR);
			equationCaller.registerOutParameter(6, Types.INTEGER);
			equationCaller.registerOutParameter(7, Types.CHAR);
			equationCaller.setString(1, parmLnm);
			equationCaller.setString(2, parmKcd);
			equationCaller.setString(3, parmKsn);
			equationCaller.setString(4, parmLtr);
			equationCaller.setString(5, parmFlg);
			equationCaller.setInt(6, parmLen);
			equationCaller.setString(7, parmRtl);
			// Execute the call
			equationCaller.execute();
			// Get the returned value
			parmLnm = equationCaller.getString(1);
			parmKcd = equationCaller.getString(2);
			parmKsn = equationCaller.getString(3);
			parmLtr = equationCaller.getString(4);
			parmFlg = equationCaller.getString(5);
			parmLen = equationCaller.getInt(6);
			parmRtl = equationCaller.getString(7);
			// display
			System.out.println("lnm = " + parmLnm);
			System.out.println("kcd = " + parmKcd);
			System.out.println("ksn = " + parmKsn);
			System.out.println("ltr = " + parmLtr);
			System.out.println("flg = " + parmFlg);
			System.out.println("len = " + parmLen);
			System.out.println("rtl = " + parmRtl);
			// Close the statement
			equationCaller.close();
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
}
