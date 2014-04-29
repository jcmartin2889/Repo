package com.misys.equation.global.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.access.EquationUnit;

public class TestConsolidatedUnitDataBkp1
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestConsolidatedUnitDataBkp1.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";

	EquationSystem systemA;
	EquationUnit unitA;
	EquationStandardSession sessionA;
	String systemIdA = "SLOUGH1";
	String userIdA = "EQUIADMIN";
	String passwordA = "EQUIADMIN";
	String unitIdA = "AL9";
	EquationSystem systemB;
	EquationUnit unitB;
	EquationStandardSession sessionB;
	String systemIdB = "SLOUGH1";
	String userIdB = "EQUIADMIN";
	String passwordB = "EQUIADMIN";
	String unitIdB = "EQX";
	public TestConsolidatedUnitDataBkp1()
	{
		try
		{
			systemA = new EquationSystem(systemIdA, userIdA, passwordA);
			systemB = new EquationSystem(systemIdB, userIdB, passwordB);
			unitA = systemA.getUnit(unitIdA);
			unitB = systemB.getUnit(unitIdB);
			sessionA = unitA.getEquationSessionPool().getEquationStandardSession();
			sessionB = unitB.getEquationSessionPool().getEquationStandardSession();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		try
		{
			TestConsolidatedUnitDataBkp1 test = new TestConsolidatedUnitDataBkp1();
			test.test();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public void test()
	{
		try
		{
			// Build the statements...
			String sql = "SELECT SCAB,SCAN,SCAS,NEEAN,NEIBAN,SCSHN,SCACT,SCCCY FROM SC10LF LEFT JOIN NE10LF ON (SCAB=NEAB AND SCAN=NEAN AND SCAS=NEAS) ORDER BY SCAB,SCAN,SCAS";
			Statement statementA = sessionA.getConnection().createStatement();
			ResultSet resultSetA = statementA.executeQuery(sql);
			Statement statementB = sessionB.getConnection().createStatement();
			ResultSet resultSetB = statementB.executeQuery(sql);

			String keyA = getNextKey(resultSetA);
			String keyB = getNextKey(resultSetB);
			int count = 0;

			while (!keyA.equals("") || !keyB.equals(""))
			{
				if (keyB.equals(""))
				{
					count++;
					System.out.println(count + " A " + keyA);
					keyA = getNextKey(resultSetA);
				}
				else if (keyA.equals(""))
				{
					count++;
					System.out.println(count + " B " + keyB);
					keyB = getNextKey(resultSetB);
				}
				if (keyA.compareTo(keyB) < 0)
				{
					if (!keyA.equals(""))
					{
						count++;
						System.out.println(count + " A " + keyA);
						keyA = getNextKey(resultSetA);
					}
				}
				else
				{
					if (!keyB.equals(""))
					{
						count++;
						System.out.println(count + " B " + keyB);
						keyB = getNextKey(resultSetB);
					}
				}
			}
			// tidy up
			resultSetA.close();
			statementA.close();
			resultSetB.close();
			statementB.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public String getNextKey(ResultSet resultSet) throws SQLException
	{
		String key = "";
		if (resultSet.next())
		{
			key = resultSet.getString("SCAB") + resultSet.getString("SCAN") + resultSet.getString("SCAS");
		}
		return key;
	}
	public void getKeys(ResultSet[] resultSets, String[] keys, int keyLength) throws SQLException
	{
		for (int i = 0; i < keys.length; i++)
		{
			int resulSetIdx = Integer.parseInt(keys[i].substring(keyLength));
			keys[i] = getNextKey(resultSets[resulSetIdx]) + resulSetIdx;
		}
	}
	public void initKeys(String[] keys, int keyLength)
	{
		for (int i = 0; i < keys.length; i++)
		{
			StringBuffer sb = new StringBuffer(keyLength);
			for (int j = 0; j < keyLength; j++)
			{
				sb.append(" ");
			}
			keys[i] = sb.toString() + i;
		}
	}
}
