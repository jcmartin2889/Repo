package com.misys.equation.common.access;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods for setting Equation prompt fields before the prompt is executed and for getting fields after the
 * prompt is executed - returning list data is supported.
 */
public class EquationStandardListValidation extends EquationStandardValidation
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationStandardListValidation.java 15068 2012-12-18 15:19:19Z williae1 $";

	private static final long serialVersionUID = 1L;

	private final List<String> dataOutputList;
	private final List<EquationPVData> equationPVDataList;

	private String startKey;
	private final int direction;
	private final int maxResults;

	// helper for the next execution
	private String firstKey;
	private String lastKey;
	private boolean moreRecords;

	/**
	 * Construct a new prompt and validate module that allows access to individual PV fields
	 * 
	 * @param decode
	 *            - decode of the P/V module
	 * @param service
	 *            - P/V program name
	 * @param equationPVData
	 *            - input data in Equation PV Data format
	 * @param blankAllowed
	 *            - Y - if blank is allowed <br>
	 *            N - if blank is not allowed
	 * @param newCode
	 *            - Y - if the key must not be existing <br>
	 *            N - if the key must be existing <br>
	 *            blank - the key may or may not be existing
	 * @param startKey
	 *            - if the direction is 1, then this is the starting key <br>
	 *            if the direction is -1, then this is a last key <br>
	 * @param direction
	 *            - (1) page down (-1) page up
	 * @param maxResults
	 *            - maximum number of data to return
	 */
	public EquationStandardListValidation(String decode, String service, EquationPVData equationPVData, String blankAllowed,
					String newCode, String security, String startKey, int direction, int maxResults)
	{
		super(decode, service, equationPVData, blankAllowed, newCode);
		setSecurity(security);
		this.startKey = startKey;
		this.direction = direction;
		this.maxResults = maxResults;
		this.firstKey = "";
		this.lastKey = "";

		this.equationPVData = equationPVData;
		setDataInput(equationPVData.parseFieldsIntoDSCCN("Y"));
		equationPVData.setDataDsccn(getDataInput());

		this.dataOutputList = new ArrayList<String>();
		this.equationPVDataList = new ArrayList<EquationPVData>();
	}

	/**
	 * Construct a new prompt and validate module that allows access to data via DSCNN format
	 * 
	 * @param decode
	 *            - decode of the P/V module
	 * @param service
	 *            - P/V program name
	 * @param dataInput
	 *            - input data in DSCCN format
	 * @param blankAllowed
	 *            - Y - if blank is allowed <br>
	 *            N - if blank is not allowed
	 * @param newCode
	 *            - Y - if the key must not be existing <br>
	 *            N - if the key must be existing <br>
	 *            blank - the key may or may not be existing
	 * @param startKey
	 *            - if the direction is 1, then this is the starting key <br>
	 *            if the direction is -1, then this is a last key <br>
	 * @param direction
	 *            - (1) page down (-1) page up
	 * @param maxResults
	 *            - maximum number of data to return
	 */
	public EquationStandardListValidation(String decode, String service, String dataInput, String blankAllowed, String newCode,
					String security, String startKey, int direction, int maxResults)
	{
		super(decode, service, dataInput, blankAllowed, newCode);
		setSecurity(security);
		this.startKey = startKey;
		this.direction = direction;
		this.maxResults = maxResults;
		this.firstKey = "";
		this.lastKey = "";

		this.dataOutputList = new ArrayList<String>();
		this.equationPVDataList = null;
	}

	/**
	 * Return the list of output data in DSCCN format
	 * 
	 * @return the list of output data in DSCCN format
	 */
	public List<String> getDataOutputList()
	{
		return dataOutputList;
	}

	/**
	 * Return the list of output data in Equation PV Data format
	 * 
	 * @return the list of output data in Equation PV Data format
	 * @equation.external
	 */
	public List<EquationPVData> getEquationPVDataList()
	{
		return equationPVDataList;
	}

	/**
	 * Return the reference start key
	 * 
	 * @return the reference start key
	 */
	public String getStartKey()
	{
		return startKey;
	}

	/**
	 * Set the reference start key
	 * 
	 * @param startKey
	 *            - the reference start key
	 */
	public void setStartKey(String startKey)
	{
		this.startKey = startKey;
	}

	/**
	 * Return the search direction
	 * 
	 * @return the search direction
	 */
	public int getDirection()
	{
		return direction;
	}

	/**
	 * Return the maximum number of record to be retrieved
	 * 
	 * @return the maximum number of record to be retrieved
	 */
	public int getMaxResults()
	{
		return maxResults;
	}

	/**
	 * Return true if there are more records to retrieve (in the same direction as the previous execution)
	 * 
	 * @return true if there are more records to retrieve
	 * @equation.external
	 */
	public boolean isMoreRecords()
	{
		return moreRecords;
	}

	/**
	 * Set whether there are more records or not
	 * 
	 * @param moreRecords
	 *            - true if there are more records or not
	 */
	public void setMoreRecords(boolean moreRecords)
	{
		this.moreRecords = moreRecords;
	}

	/**
	 * Return the first key of the current list
	 * 
	 * @return the first key of the current list
	 */
	public String getFirstKey()
	{
		return firstKey;
	}

	/**
	 * Set the first key of the current list
	 * 
	 * @param firstKey
	 *            - the first key of the current list
	 */
	public void setFirstKey(String firstKey)
	{
		this.firstKey = firstKey;
	}

	/**
	 * Return the last key of the current list
	 * 
	 * @return the last key of the current list
	 */
	public String getLastKey()
	{
		return lastKey;
	}

	/**
	 * Set the last key of the current list
	 * 
	 * @param lastKey
	 *            - the last key of the current list
	 */
	public void setLastKey(String lastKey)
	{
		this.lastKey = lastKey;
	}

	/**
	 * Clear the data list
	 * 
	 */
	public void clearData()
	{
		this.dataOutputList.clear();

		if (equationPVDataList != null)
		{
			this.equationPVDataList.clear();
		}
	}

	/**
	 * Add record to the list
	 * 
	 * @param dataOutput
	 *            - data in DSCCN format
	 * @param ccsid
	 *            - the CCSID of the data output
	 */
	public void addData(String dataOutput, int ccsid)
	{
		this.dataOutputList.add(dataOutput);

		if (equationPVDataList != null)
		{
			EquationPVData equationPVDataOutput = new EquationPVData(equationPVData.getPvMetaData(), ccsid);
			equationPVDataOutput.setDataDsccn(dataOutput);
			equationPVDataList.add(equationPVDataOutput);
		}
	}

	/**
	 * Add a list of data array
	 * 
	 * @param dataList
	 *            - the list of data array
	 * @param ccsid
	 *            - the CCSID of the data output
	 */
	public void addData(String[] dataList, int ccsid)
	{
		// determine the number of records
		int nResult = maxResults;
		if (dataList.length <= maxResults)
		{
			nResult = dataList.length;
			moreRecords = false;
		}
		else
		{
			moreRecords = true;
		}

		// add all the records
		for (int i = 0; i < nResult; i++)
		{
			addData(dataList[i], ccsid);
		}

		// set the first and last index
		firstKey = getDataOutputList().get(0);
		lastKey = getDataOutputList().get(nResult - 1);
	}

	/**
	 * Return the String representation
	 */
	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < dataOutputList.size(); i++)
		{
			buffer.append(dataOutputList.get(i));
			buffer.append("\n");
		}
		return buffer.toString();
	}

}
