package com.misys.equation.common.access;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import com.ibm.as400.access.AS400Text;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;

/**
 * @author weddelc1
 */
public class EquationConsolidatedTable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationConsolidatedTable.java 10499 2011-02-18 17:02:08Z MACDONP1 $";
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EquationConsolidatedTable.class);
	private EquationDataStructure dataStructure;
	private EquationDataStructureData[] dataStructureDatas;
	private String[] keys;
	private final String[] keyFields;
	private byte[] outputBytes;

	private int keyLength = 0;
	private int resultSetIdx = -1;
	private boolean initialised = false;
	private final int CONSOLIDATED_OFFSET = 33;
	private final String baseSQL;
	private final EquationConsolidator consolidator;
	private final boolean consolidatedQuery;

	public EquationConsolidatedTable(String selectPart, String[] keyFields, String fromPart, String wherePart,
					EquationConsolidator consolidator, int outputBytesSize, boolean consolidatedQuery)
	{
		// Save a handle to the consolidator...
		this.consolidator = consolidator;
		// Inject the unit into the key...
		this.keyFields = keyFields;

		// Over multiple units...
		this.consolidatedQuery = consolidatedQuery;

		// is there WHERE part?
		if (wherePart.trim().equals(""))
		{
			wherePart = " ";
		}
		else
		{
			wherePart = SQLToolbox.WHERE + wherePart;
		}

		// We need to inject the unit to the SQL select...
		String orderByPart = "";
		if (keyFields.length > 0)
		{
			orderByPart = Arrays.toString(this.keyFields);
			orderByPart = "ORDER BY " + orderByPart.substring(1, orderByPart.length() - 1) + " ";
		}
		baseSQL = selectPart + " " + fromPart + " " + wherePart + " " + orderByPart;

		ResultSet resultSet = null;
		Statement statement = null;
		try
		{
			List<EquationStandardSession> sessions = consolidator.getSessions();
			dataStructureDatas = new EquationDataStructureData[sessions.size()];
			keys = new String[sessions.size()];

			if (consolidator.getPvMetaData().isGlobalLibraryPrompt())
			{
				statement = sessions.get(0).getConnectionWrapper().getGlobalConnection().createStatement();
			}
			else
			{
				statement = sessions.get(0).getConnection().createStatement();
			}

			String metaDataSQL = baseSQL.replaceAll("<UNIT>", sessions.get(0).getUnitId());

			// Inject session information into query
			metaDataSQL = SQLToolbox.injectVariables(metaDataSQL, sessions.get(0));

			resultSet = statement.executeQuery(metaDataSQL);

			// Data structure will be the same for all so base on first element...
			dataStructure = new EquationDataStructure("FRED", resultSet, sessions.get(0).getUnit().getCcsid());

			// Quickly loop the key fields to find the length...
			for (String keyField : this.keyFields)
			{
				keyLength = keyLength + dataStructure.getFieldLength(keyField.toUpperCase());
			}

			if (consolidator.getPvMetaData().isGlobalConsolidatedPrompt())
			{
				outputBytes = new byte[outputBytesSize + dataStructure.getRecordByteLength()];
			}
			else
			{
				outputBytes = new byte[dataStructure.getRecordByteLength()];
			}
		}
		catch (Exception e)
		{
			LOG.error("Cannot create EquationConsolidatedTable", e);
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(statement);
		}
	}

	private String getNextRecord(ResultSet resultSets[], int resulSetIdx) throws SQLException, EQException
	{
		String key = Toolbox.pad("", keyLength);
		if (resultSets[resulSetIdx] != null)
		{
			if (resultSets[resulSetIdx].next())
			{
				StringBuilder keyBuilder = new StringBuilder("");
				for (String keyField : keyFields)
				{
					keyBuilder.append(resultSets[resulSetIdx].getString(keyField));
				}
				key = keyBuilder.toString();
				dataStructureDatas[resulSetIdx] = new EquationDataStructureData(dataStructure, resultSets[resulSetIdx]);
			}
		}
		return key;
	}

	private void loadRecords(ResultSet resultSets[]) throws SQLException, EQException
	{
		for (int i = 0; i < keys.length; i++)
		{
			if (keys[i].substring(0, keyLength).trim().equals(""))
			{
				int resulSetIdx = Integer.parseInt(keys[i].substring(keyLength));
				keys[i] = Toolbox.pad(getNextRecord(resultSets, resulSetIdx), keyLength) + resulSetIdx;
			}
		}
		// sort em out...
		Arrays.sort(keys);
	}

	private void initRecords()
	{
		keys = new String[keys.length];
		for (int i = 0; i < keys.length; i++)
		{
			keys[i] = Toolbox.pad(keys[i], keyLength) + i;
			dataStructureDatas[i] = new EquationDataStructureData(dataStructure);
		}
	}

	public EquationDataStructure getDataStructure()
	{
		return dataStructure;
	}

	public EquationDataStructureData[] getDataStructureDatas()
	{
		return dataStructureDatas;
	}

	public String[] getKeys()
	{
		return keys;
	}

	public void setKeys(String[] keys)
	{
		this.keys = keys;
	}

	public String getBaseSQL()
	{
		return baseSQL;
	}

	public int getKeyLength()
	{
		return keyLength;
	}

	public boolean hasNext()
	{
		return !keys[keys.length - 1].substring(0, keyLength).trim().equals("");
	}

	private int nextIndex()
	{
		// find the first non blank entry
		String key = "";
		resultSetIdx = -1;
		for (int i = 0; i < keys.length; i++)
		{
			key = keys[i].substring(0, keyLength);
			resultSetIdx = Integer.parseInt(keys[i].substring(keyLength));
			if (!key.trim().equals(""))
			{
				// we have what we want so clear the current key ready for insertion and bug out...
				keys[i] = Toolbox.pad("", keyLength) + resultSetIdx;
				break;
			}
			else
			{
				key = "";
			}
		}
		return resultSetIdx;
	}

	public boolean next(ResultSet resultSets[]) throws SQLException, EQException
	{
		if (!initialised)
		{
			initRecords();
			initialised = true;
		}
		loadRecords(resultSets);
		if (hasNext())
		{
			nextIndex();
			return true;
		}
		else
		{
			return false;
		}
	}

	public int getCount(ResultSet resultSets[]) throws SQLException, EQException
	{
		int totalRowCount = 0;
		for (ResultSet rs : resultSets)
		{
			try
			{
				// Point to the last row in result set.
				rs.last();
				// Get the row position which is also the number of rows in the ResultSet.
				int rowcount = rs.getRow();
				// Reposition at the beginning of the ResultSet to take up rs.next() call.
				rs.beforeFirst();
				totalRowCount += rowcount;
			}
			catch (Exception e)
			{
				throw new EQException(
								"EquationConsolidatedTable.getCount() failed: Possibly because the result set is not TYPE_SCROLL_SENSITIVE.",
								e);
			}
		}
		next(resultSets);
		return totalRowCount;
	}

	public byte[] getBytes()
	{
		if (resultSetIdx == -1)
		{
			return null;
		}
		else
		{
			return dataStructureDatas[resultSetIdx].getBytes();
		}
	}

	public byte[] getKeyBytes()
	{
		if (resultSetIdx == -1)
		{
			return null;
		}
		else
		{
			byte[] key = new byte[keyLength];
			int offset = 0;
			for (String keyField2 : keyFields)
			{
				byte[] keyField = dataStructureDatas[resultSetIdx].getFieldValueBytes(keyField2);
				System.arraycopy(keyField, 0, key, offset, keyField.length);
				offset += dataStructureDatas[resultSetIdx].getEqDS().getRcdFmt().getFieldDescription(keyField2).getDataType()
								.getByteLength();
			}
			return key;
		}
	}

	public String getKey()
	{
		if (resultSetIdx == -1)
		{
			return null;
		}
		else
		{
			StringBuilder key = new StringBuilder("");
			for (String keyField : keyFields)
			{
				key.append(dataStructureDatas[resultSetIdx].getFieldValue(keyField));
			}
			return key.toString();
		}
	}

	public EquationDataStructureData getDataStrucureData()
	{
		if (resultSetIdx == -1)
		{
			return null;
		}
		else
		{
			return dataStructureDatas[resultSetIdx];
		}
	}

	public int getUnitIndex()
	{
		return resultSetIdx;
	}

	public byte[] getOutputBytes()
	{
		if (resultSetIdx == -1)
		{
			Arrays.fill(outputBytes, (new byte[] { 0x00 })[0]);
		}
		else
		{
			if (consolidator.getPvMetaData().isGlobalConsolidatedPrompt())
			{
				byte[] systemBytes = consolidator.getSystemIdBytes(resultSetIdx);
				byte[] unitIdBytes = consolidator.getUnitIdBytes(resultSetIdx);
				byte[] unitDescriptionBytes = consolidator.getUnitDescriptionBytes(resultSetIdx);
				byte[] dataBytes = dataStructureDatas[resultSetIdx].getBytes();

				if (!consolidator.isSystemNull() && !consolidator.getPvMetaData().isGlobalLibraryPrompt())
				{
					System.arraycopy(systemBytes, 0, outputBytes, 0, 8);
					System.arraycopy(unitIdBytes, 0, outputBytes, 8, 3);
					System.arraycopy(unitDescriptionBytes, 0, outputBytes, 11, 20);
					System.arraycopy(dataBytes, 0, outputBytes, 31, dataStructure.getRecordByteLength());
				}
			}
			else
			{
				byte[] dataBytes = dataStructureDatas[resultSetIdx].getBytes();
				System.arraycopy(dataBytes, 0, outputBytes, 0, dataStructure.getRecordByteLength());
			}
		}
		return outputBytes;
	}

	public String getOutputString(EquationPVMetaData equationPVMetaData)
	{
		String outputString = "";
		if (resultSetIdx != -1)
		{
			// output bytes contains the full information (unit, description, actual data)
			outputBytes = getOutputBytes();

			// get ccsid of the system
			EquationStandardSession session = consolidator.getSession(resultSetIdx);
			int ccsid = session.getCcsid();

			// not PV, then treat as whole string
			if (equationPVMetaData == null)
			{
				AS400Text text = new AS400Text(outputBytes.length, ccsid);
				outputString = (String) text.toObject(outputBytes);
			}

			// PV, then use the Equation PV Meta Data
			// Need to split the output bytes again to remove the header information (e.g. unit, description, etc)
			else
			{
				// header included?
				String headerString = "";

				if (equationPVMetaData.isGlobalLibraryPrompt())
				{
					byte[] headerBytes = new byte[CONSOLIDATED_OFFSET];
					System.arraycopy(outputBytes, 0, headerBytes, 0, CONSOLIDATED_OFFSET);
					AS400Text text = new AS400Text(headerBytes.length, ccsid);
					headerString = (String) text.toObject(headerBytes);
				}

				// data
				EquationPVData equationPVData = new EquationPVData(equationPVMetaData, ccsid);
				equationPVData.initialiseFromDS(dataStructureDatas[resultSetIdx]);

				if (equationPVMetaData.rtvFieldMetaData("UNIT") != null && equationPVMetaData.rtvFieldMetaData("UNITDESC") != null)
				{
					equationPVData.setFieldValue("UNIT", session.getUnitId());
					equationPVData.setFieldValue("UNITDESC", session.getUnit().getDescription());
					outputString = equationPVData.parseFieldsIntoDSCCNOutput(EqDataType.NO);
				}
				else
				{
					outputString = headerString + equationPVData.parseFieldsIntoDSCCNOutput(EqDataType.NO);
				}
			}
		}
		return outputString;
	}

	public String[] getKeyFields()
	{
		return keyFields;
	}

	public void reset()
	{
		initialised = false;
	}

	public boolean isConsolidatedQuery()
	{
		return consolidatedQuery;
	}
}