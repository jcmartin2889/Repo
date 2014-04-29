package com.misys.equation.common.additionalinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.misys.equation.common.access.EquationRecords;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.beans.BDRecordDataModel;
import com.misys.equation.common.dao.beans.BFRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;

public class EquationAdditionalInfoGroup
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationAdditionalInfoGroup.java 14832 2012-11-05 19:03:33Z williae1 $";
	private String informationGroup;
	private List<BDRecordDataModel> items;

	/**
	 * Constructor
	 * 
	 * @param parameterFile
	 *            - the parameter file
	 * @param parameterValue
	 *            - the parameter value
	 */
	public EquationAdditionalInfoGroup(String informationGroup)
	{
		this.informationGroup = informationGroup;
	}

	/**
	 * Retrieve the items under the group
	 * 
	 * @param session
	 *            - the Equation standard session
	 */
	public void refresh(EquationStandardSession session) throws EQException
	{
		Map<String, BFRecordDataModel> bfRecords = EquationAdditionalInfoToolbox.getBFRecords(session, informationGroup);

		// initialise the list
		this.items = new ArrayList<BDRecordDataModel>(bfRecords.size());
		for (int i = 0; i < bfRecords.size(); i++)
		{
			this.items.add(null);
		}

		// get all the records
		EquationRecords equationRecords = session.getUnit().getRecords();
		for (String key : bfRecords.keySet())
		{
			BFRecordDataModel bfRecord = bfRecords.get(key);

			BDRecordDataModel bdRecord = equationRecords.getBDRecord(bfRecord.getItemName());
			int sequenceNumber = bfRecord.getItemGroupNumber() - 1;

			items.set(sequenceNumber, bdRecord);
		}
	}

	/**
	 * Return the item in the specified index (zero-based)
	 * 
	 * @param index
	 *            - the index
	 * 
	 * @return the item in the specified index
	 */
	public BDRecordDataModel getItem(int index)
	{
		if (index < 0)
		{
			return null;
		}
		else if (index < items.size())
		{
			return items.get(index);
		}
		else
		{
			return null;
		}
	}

	/**
	 * Return the number of items in the group
	 * 
	 * @return the number of items in the group
	 */
	public int itemCount()
	{
		return items.size();
	}

	/**
	 * Return the item name in the specified index (zero-based)
	 * 
	 * @param index
	 *            - the index
	 * 
	 * @return the item name in the specified index
	 */
	public String getItemName(int index)
	{
		BDRecordDataModel bdRecord = getItem(index);
		if (bdRecord != null)
		{
			return bdRecord.getItemName();
		}
		else
		{
			return "";
		}
	}

	/**
	 * Return the item description in the specified index (zero-based)
	 * 
	 * @param index
	 *            - the index
	 * 
	 * @return the item description in the specified index
	 */
	public String getItemDescription(int index)
	{
		BDRecordDataModel bdRecord = getItem(index);
		if (bdRecord != null)
		{
			return bdRecord.getItemDescription();
		}
		else
		{
			return "";
		}
	}

}
