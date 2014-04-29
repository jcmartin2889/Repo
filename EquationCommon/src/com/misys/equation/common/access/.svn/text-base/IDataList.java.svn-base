package com.misys.equation.common.access;

import com.misys.equation.common.internal.eapi.core.EQException;

/**
 * Interface used by data lists returned from PVs
 */
public interface IDataList
{
	/**
	 * Construct a data list retrieving the file and key from the PV Meta-Data
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param service
	 *            - p/v program name
	 * @param decode
	 *            - decode character
	 * @param security
	 *            - security mode
	 * @param query
	 *            - this is the DSCCN
	 * @param start
	 *            - if the direction is 1, then this is the starting key <br>
	 *            if the direction is -1, then this is a last key <br>
	 * @param direction
	 *            - (1) page down (-1) page up
	 * @param maxResults
	 *            - maximum number of data to return
	 * 
	 * @throws EQException
	 */
	public void initialise(EquationUser eqUser, String service, String decode, String security, String dsccn, String start,
					int direction, int maxResults) throws EQException;
	/**
	 * Retrieve the data list.
	 * 
	 * @return Returns the dataList.
	 */
	public String getDataList();

	/**
	 * Retrieve the data list. This is normally NULL except if the CCSID supports BIDI. In this case, this will contain the
	 * equivalent text ready for display (converted from iSeries to Java String)
	 * 
	 * @return Returns the dataList.
	 */
	public String getDataListForDisplay();

}
