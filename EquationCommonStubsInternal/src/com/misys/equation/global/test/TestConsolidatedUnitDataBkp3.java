package com.misys.equation.global.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Hashtable;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.UserSpace;
import com.misys.equation.common.access.EquationDataStructure;
import com.misys.equation.common.access.EquationDataStructureData;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.Toolbox;

public class TestConsolidatedUnitDataBkp3
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestConsolidatedUnitDataBkp3.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";

	private static final String[] systemIds = { "MNG", "SLOUGH1", "SLOUGH1" };
	private static final String[] userIds = { "EQUIADMIN", "EQUIADMIN", "EQUIADMIN" };
	private static final String[] passwords = { "EQUIADMIN", "EQUIADMIN", "EQUIADMIN" };
	private static final String[] unitIds = { "EQX", "EQY", "AL9*l" };
	private static final String sql = "SELECT SCAB,SCAN,SCAS,NEEAN,SCSHN,SCACT,SCCCY FROM SC10LF LEFT JOIN NE10LF ON (SCAB=NEAB AND SCAN=NEAN AND SCAS=NEAS) WHERE SCAN LIKE '012%' ORDER BY SCAB,SCAN,SCAS";
	private static final int keyLength = 13;
	private final EquationDataStructure[] dataStructures = new EquationDataStructure[unitIds.length];
	private final EquationDataStructureData[] dataStructureDatas = new EquationDataStructureData[unitIds.length];
	private final String[] keys = new String[unitIds.length];
	private final Hashtable<String, EquationSystem> systems = new Hashtable<String, EquationSystem>();
	private final EquationStandardSession[] sessions = new EquationStandardSession[unitIds.length];
	private UserSpace userSpace;
	private final Statement[] statements = new Statement[unitIds.length];
	private final ResultSet[] resultSets = new ResultSet[unitIds.length];
	public TestConsolidatedUnitDataBkp3()
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
				dataStructures[i] = new EquationDataStructure("FRED", resultSets[i], unit.getCcsid());
			}
			// Create a nice little user space to store all the gubbins in (assume prime unit is first entry)...
			AS400 as400 = sessions[0].getUnit().getEquationSystem().getAS400();
			userSpace = new UserSpace(as400, "/QSYS.LIB/KWRK" + unitIds[0] + ".LIB/PMTDTA.USRSPC");
			userSpace.create(4096, true, "", (new byte[] { 0x00 })[0], "Equation Prompt Data", "*ALL");
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
			TestConsolidatedUnitDataBkp3 test = new TestConsolidatedUnitDataBkp3();
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
			initRecord(keys, keyLength);
			getNextRecords(keys, keyLength);
			int count = 0;
			int offset = 0;
			Toolbox.printTime("Start:");

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
				byte[] data = dataStructureDatas[resulSetIdx].getBytes();
				userSpace.write(data, offset);
				offset = offset + data.length;
				// System.out.println(count + " " + unitIds[resulSetIdx] + " " + key);
				// System.out.println(dataStructureDatas[resulSetIdx]);
				getNextRecords(keys, keyLength);
			}
			Toolbox.printTime("Finished processing : " + count + " records at: ");
			// tidy up
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public String getNextRecord(int i, int keyLength) throws SQLException, EQException
	{
		String key = Toolbox.pad("", keyLength);
		if (resultSets[i].next())
		{
			key = resultSets[i].getString("SCAB") + resultSets[i].getString("SCAN") + resultSets[i].getString("SCAS");
			dataStructureDatas[i] = new EquationDataStructureData(dataStructures[i], resultSets[i]);
		}
		else
		{
			dataStructureDatas[i] = new EquationDataStructureData(dataStructures[i]);
		}
		return key;
	}
	public void getNextRecords(String[] keys, int keyLength) throws SQLException, EQException
	{
		for (int i = 0; i < keys.length; i++)
		{
			if (keys[i].substring(0, keyLength).trim().equals(""))
			{
				int resulSetIdx = Integer.parseInt(keys[i].substring(keyLength));
				keys[i] = getNextRecord(resulSetIdx, keyLength) + resulSetIdx;
			}
		}
		// sort em out...
		Arrays.sort(keys);
	}
	public void initRecord(String[] keys, int keyLength)
	{
		for (int i = 0; i < keys.length; i++)
		{
			keys[i] = Toolbox.pad(keys[i], keyLength) + i;
			dataStructureDatas[i] = new EquationDataStructureData(dataStructures[i]);
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
