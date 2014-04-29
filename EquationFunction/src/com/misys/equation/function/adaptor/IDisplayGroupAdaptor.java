package com.misys.equation.function.adaptor;

import com.misys.equation.function.runtime.UserData;
import com.misys.equation.function.useraccess.UserExitListColumnData;

/**
 * Interface of methods applicable to DisplayAttributes exit processing.
 * <p>
 * This currently allows user control over a field's visible and protected states
 * <p>
 * This is used at runtime by the DisplayGroupAdaptor which uses this interface to interact with the user exit code. The
 * AbstractDisplayGroupAdaptor implements this interface, and is in turn intended to be extended by the user code.
 * 
 * @see DisplayGroupAdaptor
 * @see AbstractDisplayGroupAdaptor
 */
public interface IDisplayGroupAdaptor
{
	/**
	 * Determine whether the group is visible or not
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return true if visible
	 */
	public boolean isVisible(UserData userData);

	/**
	 * Return an additional top row details for a repeating group
	 * 
	 * @param userData
	 *            - the User Data
	 * @param fieldName
	 *            - the field name
	 * @param counter
	 *            - not currently used
	 * 
	 * @return A UserExitListColumnData object, with columns populated as required
	 */
	public UserExitListColumnData getTopColumnDetails(UserData userData, String fieldName, int counter);

	/**
	 * Return an additional bottom row details for a repeating group
	 * 
	 * @param userData
	 *            - the User Data
	 * @param fieldName
	 *            - the field name
	 * @param counter
	 *            - not currently used
	 * 
	 * @return A UserExitListColumnData object, with columns populated as required
	 */
	public UserExitListColumnData getBottomColumnDetails(UserData userData, String fieldName, int counter);

	/**
	 * Return an additional (above) row details for a particular row in a repeating group
	 * 
	 * @param userData
	 *            - the User Data
	 * @param fieldName
	 *            - the field name
	 * @param counter
	 *            - not currently used
	 * 
	 * @return A UserExitListColumnData object, with columns populated as required
	 */
	public UserExitListColumnData getAboveRowColumnDetails(UserData userData, String fieldName, int counter);

	/**
	 * Return an additional bottom row details for a repeating group
	 * 
	 * @param userData
	 *            - the User Data
	 * @param fieldName
	 *            - the field name
	 * @param counter
	 *            - not currently used
	 * 
	 * @return A UserExitListColumnData object, with columns populated as required
	 */
	public UserExitListColumnData getBelowRowColumnDetails(UserData userData, String fieldName, int counter);

}
