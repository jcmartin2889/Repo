package com.misys.equation.function.runtime;

import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.RepeatingDataManager;

/**
 * Provide a wrapper for the Function Data
 */
public class UserModifyData extends UserData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UserModifyData.java 17341 2013-09-23 01:22:43Z williae1 $";

	// boolean to determine data has been changed
	private boolean changed;

	/**
	 * Construct a new user data
	 * 
	 * @param functionData
	 *            - the function data
	 */
	public UserModifyData(FunctionHandlerData fhd, FunctionData functionData)
	{
		super(fhd, functionData);
		changed = false;
	}

	/**
	 * Change the field input value of the specified field name
	 * 
	 * @param fieldName
	 *            - field name
	 * @param fieldValue
	 *            - field input value
	 * @equation.external
	 */
	public void chgFieldInputValue(String fieldName, String fieldValue)
	{
		String str = functionData.rtvFieldInputValue(fieldName);

		// not the same
		if (!str.equals(fieldValue))
		{
			functionData.chgFieldInputValue(fieldName, fieldValue);
			changed = true;
		}
	}

	/**
	 * Change the field output value of the specified field name
	 * 
	 * @param fieldName
	 *            - field name
	 * @param fieldValue
	 *            - field output value
	 * @equation.external
	 */
	public void chgFieldOutputValue(String fieldName, String fieldValue)
	{
		String str = functionData.rtvFieldOutputValue(fieldName);

		// not the same
		if (!str.equals(fieldValue))
		{
			functionData.chgFieldOutputValue(fieldName, fieldValue);

			// do not marked as changed anymore to avoid triggering another round of validation
			// since output description is not being validated anyway
			// changed = true;
		}
	}

	/**
	 * Change the field database value of the specified field name
	 * 
	 * @param fieldName
	 *            - field name
	 * @param fieldValue
	 *            - field database value
	 * @equation.external
	 */
	public void chgFieldDbValue(String fieldName, String fieldValue)
	{
		String str = functionData.rtvFieldDbValue(fieldName);

		// not the same
		if (!str.equals(fieldValue))
		{
			functionData.chgFieldDbValue(fieldName, fieldValue);
			changed = true;
		}
	}

	/**
	 * Determine whether data has been changed
	 * 
	 * @return true if data has been changed
	 */
	public boolean isChanged()
	{
		return changed;
	}

	/**
	 * Set whether the data has been changed
	 * 
	 * @param changed
	 *            - true if data has been changed
	 */
	protected void setChanged(boolean changed)
	{
		this.changed = changed;
	}

	/**
	 * Return the repeating data manager
	 * 
	 * @param repeatingGroupId
	 *            - the repeating data manager id
	 * 
	 * @return the repeating data manager
	 * @equation.external
	 */
	@Override
	public UserModifyRepeatingDataManager getRepeatingDataManager(String repeatingGroupId)
	{
		RepeatingDataManager dataManager = fhd.getScreenSetHandler().rtvScrnSetCurrent().getFunctionData().getRepeatingDataManager(
						repeatingGroupId);
		if (dataManager == null)
		{
			return null;
		}
		else
		{
			return new UserModifyRepeatingDataManager(this, dataManager);
		}
	}
	/**
	 * Set serialised EquationStandardSQLPagingList XML
	 * 
	 * @param pagingDataXML
	 *            - serialised EquationStandardSQLPagingList XML
	 * @equation.external
	 */
	public void setEnquiryPagingData(String pagingDataXML)
	{
		functionData.putHeaderResponseData("EnquiryPagingData", pagingDataXML);
	}
	/**
	 * Set total page from call to EquationStandardSQLPagingList
	 * 
	 * @param totalPages
	 *            - total page from call to EquationStandardSQLPagingList
	 * @equation.external
	 */
	public void setEnquiryTotalPages(int totalPages)
	{
		functionData.putHeaderResponseData("EnquiryTotalPages", new Integer(totalPages).toString());
	}
}