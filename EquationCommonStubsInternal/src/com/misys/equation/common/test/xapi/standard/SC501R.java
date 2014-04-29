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
public class SC501R
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SC501R.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EquationStandardSession session;
	// Input branch mnemonic
	String service = "SC501R";
	String £5atp = "CA";
	String £5ccy = "GBP";
	String £5sdte = "       ";
	String £5conf = "B";
	String sc501a = " ";
	byte[] sc501b = { 0x40 };
	String dsepms = "                                   ";
	int £5rtmd;
	String inputBrnmStr = "0000";
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private SC501R()
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
		SC501R test = new SC501R();
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
			sc501a = £5atp + £5ccy + £5sdte + £5conf;
			// ...
			if (dataItemFunctionRS.next())
			{
				if (dataItemFunctionRS.getInt(1) == 0)
				{
					// Create the required SQL procedures
					String createProcedure = "CREATE PROCEDURE KLIB" + session.getUnitId() + "/" + service + "PRC " + "( "
									+ "INOUT SC501A CHAR(13) , " + "INOUT SC501B CHAR(132) FOR BIT DATA, "
									+ "INOUT DSEPMS CHAR(37)" + ") " + "LANGUAGE RPGLE " + "NOT DETERMINISTIC " + "NO SQL "
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
			CallableStatement equationCaller = session.getConnection().prepareCall("{ CALL " + service + "PRC (?, ?, ?) }");
			// Register the output
			equationCaller.registerOutParameter(1, Types.CHAR);
			equationCaller.registerOutParameter(2, Types.CHAR);
			equationCaller.registerOutParameter(3, Types.CHAR);
			equationCaller.setString(1, sc501a);
			equationCaller.setBytes(2, sc501b);
			equationCaller.setString(3, dsepms);
			// Execute the call
			equationCaller.execute();
			// Get the returned value
			sc501a = equationCaller.getString(1);
			sc501b = equationCaller.getBytes(2);
			dsepms = equationCaller.getString(3);
			// £5rtmd = sc501b.substring(21, 32)
			outputByteArray("fred", sc501b);
			System.out.println(sc501b.toString());
			System.out.println(dsepms.toString());
			// Close the statement
			equationCaller.close();
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
	public static void outputByteArray(String s, byte[] bytes)
	{
		// Bung the bytes into a string
		StringBuffer stringBuffer = new StringBuffer();
		String string = "";
		int i = 0;
		for (i = 0; i < bytes.length; i++)
		{
			int bytesInteger = Integer.parseInt(Byte.toString(bytes[i]));
			if (bytesInteger < 0)
			{
				bytesInteger = bytesInteger + 256;
			}
			String byteHex = Integer.toHexString(bytesInteger);
			while (byteHex.length() < 2)
			{
				byteHex = "0" + byteHex;
			}
			// Suppress leading 0x00 values to aid presentation
			if (!byteHex.equals("00"))
			{
				stringBuffer.append(byteHex + " ");
			}
		}
		string = stringBuffer.toString().toUpperCase();
		System.out.println(s + string);
	}
}
