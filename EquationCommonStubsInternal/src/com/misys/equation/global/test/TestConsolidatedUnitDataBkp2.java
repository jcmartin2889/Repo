package com.misys.equation.global.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Hashtable;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.Toolbox;

public class TestConsolidatedUnitDataBkp2
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestConsolidatedUnitDataBkp2.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";

	private static final String[] systemIds = { "SLOUGH1", "SLOUGH1", "SLOUGH1" };
	private static final String[] userIds = { "EQUIADMIN", "EQUIADMIN", "EQUIADMIN" };
	private static final String[] passwords = { "EQUIADMIN", "EQUIADMIN", "EQUIADMIN" };
	private static final String[] unitIds = { "AL9", "EQY", "EQX" };
	private static final String sql = "SELECT SCAB,SCAN,SCAS,NEEAN,NEIBAN,SCSHN,SCACT,SCCCY FROM SC10LF LEFT JOIN NE10LF ON (SCAB=NEAB AND SCAN=NEAN AND SCAS=NEAS) ORDER BY SCAB,SCAN,SCAS";
	private static final int keyLength = 13;
	private Hashtable<String, EquationSystem> systems = new Hashtable<String, EquationSystem>();
	private EquationStandardSession[] sessions = new EquationStandardSession[unitIds.length];
	private Statement[] statements = new Statement[unitIds.length];
	private ResultSet[] resultSets = new ResultSet[unitIds.length];
	public TestConsolidatedUnitDataBkp2()
	{
		try
		{
			// loop the units and initialise...
			for (int i = 0; i < unitIds.length; i++)
			{
				EquationSystem system = getEqSystem(systemIds[i], userIds[i], passwords[i]);
				EquationUnit unit = system.getUnit(unitIds[i]);
				sessions[i] = unit.getEquationSessionPool().getEquationStandardSession();
				statements[i] = sessions[i].getConnection().createStatement();
				resultSets[i] = statements[i].executeQuery(sql);
			}
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
			TestConsolidatedUnitDataBkp2 test = new TestConsolidatedUnitDataBkp2();
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
			String[] keys = new String[unitIds.length];
			initKeys(keys, keyLength);
			getNextKeys(resultSets, keys, keyLength);
			int count = 0;

			// While the last element of the sorted array has non blanks
			while (!keys[unitIds.length - 1].substring(0, keyLength).trim().equals(""))
			{
				// find the first non blank entry
				String key = "";
				int resulSetIdx = -1;
				for (int i = 0; i < keys.length; i++)
				{
					key = keys[i].substring(0, keyLength);
					resulSetIdx = Integer.parseInt(keys[i].substring(keyLength));
					if (!key.trim().equals(""))
					{
						// we have what we want so clear the current key ready for insertion and bug out...
						keys[i] = Toolbox.pad("", keyLength) + resulSetIdx;
						break;
					}
					else
					{
						key = "";
					}
				}
				count++;
				System.out.println(count + " " + unitIds[resulSetIdx] + " " + key);
				getNextKeys(resultSets, keys, keyLength);
			}
			// tidy up
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public String getNextKey(ResultSet resultSet, int keyLength) throws SQLException
	{
		String key = Toolbox.pad("", keyLength);
		if (resultSet.next())
		{
			key = resultSet.getString("SCAB") + resultSet.getString("SCAN") + resultSet.getString("SCAS");
		}
		return key;
	}
	public void getNextKeys(ResultSet[] resultSets, String[] keys, int keyLength) throws SQLException
	{
		for (int i = 0; i < keys.length; i++)
		{
			if (keys[i].substring(0, keyLength).trim().equals(""))
			{
				int resulSetIdx = Integer.parseInt(keys[i].substring(keyLength));
				keys[i] = getNextKey(resultSets[resulSetIdx], keyLength) + resulSetIdx;
			}
		}
		// sort em out...
		Arrays.sort(keys);
	}
	public void initKeys(String[] keys, int keyLength)
	{
		for (int i = 0; i < keys.length; i++)
		{
			keys[i] = Toolbox.pad(keys[i], keyLength) + i;
		}
	}
	public EquationSystem getEqSystem(String systemId, String userId, String password) throws EQException
	{
		EquationSystem system = null;
		if (!systems.containsKey(systemId))
		{
			system = new EquationSystem(systemId, userId, password);
			systems.put(systemId, system);
		}
		else
		{
			system = systems.get(systemId);
		}
		return system;
	}
}
