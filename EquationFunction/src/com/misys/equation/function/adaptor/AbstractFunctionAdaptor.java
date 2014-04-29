package com.misys.equation.function.adaptor;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.function.runtime.FunctionMessages;
import com.misys.equation.function.runtime.UserData;
import com.misys.equation.function.runtime.UserModifyData;
import com.misys.equation.function.useraccess.UserExitESFProcessDetail;

public abstract class AbstractFunctionAdaptor extends AbstractAdaptor implements IFunctionAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractFunctionAdaptor.java 12346 2011-11-18 21:48:19Z perkinj1 $";

	private MethodCallStatus methodCallStatus = new MethodCallStatus();

	/**
	 * Return whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the
	 * user has overridden the methods or not
	 * 
	 * @return true if default method of the abstract adaptor has been executed
	 */
	public MethodCallStatus getMethodCallStatus()
	{
		return methodCallStatus;
	}

	/**
	 * Perform defaulting before the standard validation process
	 * 
	 * <pre>
	 * To access the Function Data, use the following methods of the Function Data class:
	 * 
	 * rtvFieldDbValue(fieldname) 
	 * - to retrieve the primitive value of the field
	 * 
	 * rtvFieldInputValue(fieldname) 
	 * - to retrieve the input value of the field
	 * 
	 * rtvFieldOutputValue(fieldname) 
	 * - to retrieve the output value of the field
	 * 
	 * chgFieldDbValue(fieldname) 
	 * - to change the primitive value of the field.  Changing the primitive value will also 
	 *   update the input value
	 * 
	 * chgFieldInputValue(fieldname) 
	 * - to change the input value of the field
	 * 
	 * chgFieldOutputValue(fieldname) 
	 * - to change the output value of the field
	 * </pre>
	 * 
	 * @param curScreen
	 *            - the current screen
	 * @param userModifyData
	 *            - the user modifiable data
	 */
	public void defaultMode(int curScreen, UserModifyData userModifyData)
	{
		methodCallStatus.setAbstractDefaultMethodExecute(true);
	}

	/**
	 * Perform validation after the standard validation process
	 * 
	 * @param curScreen
	 *            - the current screen
	 * @param userModifyData
	 *            - the user modifiable data
	 */
	public void validateMode(int curScreen, UserModifyData userModifyData)
	{
		methodCallStatus.setAbstractValidateMethodExecute(true);
	}

	/**
	 * Perform update before the standard update process
	 * 
	 * @param userData
	 *            - the User Data
	 */
	public void preUpdate(JournalHeader journalHeader, UserData userData)
	{
		methodCallStatus.setAbstractPreUpdateMethodExecute(true);
	}

	/**
	 * Perform update after the standard update process
	 * 
	 * @param userData
	 *            - the User Data
	 */
	public void postUpdate(JournalHeader journalHeader, UserData userData)
	{
		methodCallStatus.setAbstractPostUpdateMethodExecute(true);
	}

	/**
	 * Perform load before the standard retrieval process
	 * 
	 * @param userModifyData
	 *            - the User Data
	 */
	public void preLoad(UserModifyData userModifyData)
	{
		methodCallStatus.setAbstractPreLoadMethodExecute(true);
	}

	/**
	 * Perform load after the standard retrieval process
	 * 
	 * @param userModifyData
	 *            - the User Data
	 * 
	 * @return the function mode
	 */
	public String postLoad(UserModifyData userModifyData)
	{
		methodCallStatus.setAbstractPostLoadMethodExecute(true);
		return null;
	}

	/**
	 * Perform load after retrieving the default charges for the function
	 * 
	 * @param userData
	 *            - the User Data of the main function
	 * @param userDataEFC
	 *            - the EFC user data
	 */
	public void postLoadEFC(UserData userData, UserModifyData userDataEFC)
	{
		methodCallStatus.setAbstractPostLoadEFCMethodExecute(true);
	}

	/**
	 * Perform load after the standard load process
	 * 
	 * @param userModifyData
	 *            - the user modifiable data
	 */
	public void onCancel(UserModifyData userModifyData)
	{
		methodCallStatus.setAbstractOnCancelMethodExecute(true);
	}

	/**
	 * Perform load after the standard load process
	 * 
	 * @param journalHeader
	 *            - the journal header. NULL if update is not successful and the apiFieldSetId determines the last update it was
	 *            trying to do
	 * @param apiFieldSetId
	 *            - the last API field set id it is trying to processed. This infomation can be combined with the journal header to
	 *            or failure. If this is "asi" and journal header is null, then it means it failed in processing update api "asi"<br>
	 * @param userData
	 *            - the User Data
	 * @param functionMessages
	 *            - the list of function messages
	 */
	public void onFinalUpdate(JournalHeader journalHeader, String apiFieldSetId, UserData userData,
					FunctionMessages functionMessages)
	{
		methodCallStatus.setAbstractOnFinalUpdateMethodExecute(true);
	}

	/**
	 * Return the LRP template process detail
	 * 
	 * @param userData
	 *            - the User Data of the main function
	 * 
	 * @return UserExitESFProcessDetail - ESF process detail
	 */
	public UserExitESFProcessDetail getLRPTemplateProcessDetail(UserData userData)
	{
		methodCallStatus.setAbstractGetLRPTemplateProcessDetailMethodExecute(true);
		return null;
	}

}
