package com.misys.equation.function.adaptor;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.function.runtime.FunctionMessages;
import com.misys.equation.function.runtime.UserData;
import com.misys.equation.function.runtime.UserModifyData;
import com.misys.equation.function.useraccess.UserExitESFProcessDetail;
import com.misys.equation.function.useraccess.UserExitMessages;

public interface IFunctionAdaptor
{
	/**
	 * Return the messages
	 * 
	 * @return the list of messages
	 */
	public UserExitMessages getReturnMessages();

	/**
	 * Clear the messages
	 */
	public void clearMessages();

	/**
	 * Return whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the
	 * user has overridden the methods or not
	 * 
	 * @return true if default method of the abstract adaptor has been executed
	 */
	public MethodCallStatus getMethodCallStatus();

	/**
	 * Perform defaulting before the standard validation process
	 * 
	 * @param curScreen
	 *            - the current screen
	 * @param userModifyData
	 *            - the User modifiable data
	 */
	public void defaultMode(int curScreen, UserModifyData userModifyData);

	/**
	 * Perform validation after the standard validation process
	 * 
	 * @param curScreen
	 *            - the current screen
	 * @param userModifyData
	 *            - the User modifiable data
	 */
	public void validateMode(int curScreen, UserModifyData userModifyData);

	/**
	 * Perform update before the standard update process
	 * 
	 * @param journalHeader
	 *            - the journal header
	 * @param userData
	 *            - the User Data
	 */
	public void preUpdate(JournalHeader journalHeader, UserData userData);

	/**
	 * Perform update after the standard update process
	 * 
	 * @param journalHeader
	 *            - the journal header
	 * @param userData
	 *            - the User Data
	 */
	public void postUpdate(JournalHeader journalHeader, UserData userData);

	/**
	 * Perform load before the standard retrieval process
	 * 
	 * @param userModifyData
	 *            - the User Data
	 */
	public void preLoad(UserModifyData userModifyData);

	/**
	 * Perform load after the standard retrieval process
	 * 
	 * @param userModifyData
	 *            - the User Data
	 * 
	 * @return the function mode
	 */
	public String postLoad(UserModifyData userModifyData);

	/**
	 * Perform load after retrieving the default charges for the function
	 * 
	 * @param userData
	 *            - the User Data of the main function
	 * @param userDataEFC
	 *            - the EFC user data
	 * 
	 * @return the function mode
	 */
	public void postLoadEFC(UserData userData, UserModifyData userDataEFC);

	/**
	 * Perform processing when exiting the function
	 * 
	 * @param userModifyData
	 *            - the User Data
	 */
	public void onCancel(UserModifyData userModifyData);

	/**
	 * Perform processing when doing update (outside of commitment control)
	 * 
	 * @param journalHeader
	 *            - the journal header. NULL if update is not successful and the apiFieldSetId determines the last update it was
	 *            trying to do
	 * @param apiFieldSetId
	 *            - the last API field set id it is trying to processed. This infomation can be combined with the journal header to
	 *            or failure. If this is "asi" and journal header is null, then it means it failed in processing update api "asi"<br>
	 * @param userModifyData
	 *            - the User Data
	 * @param functionMessages
	 *            - the list of function messages
	 */
	public void onFinalUpdate(JournalHeader journalHeader, String apiFieldSetId, UserData userData,
					FunctionMessages functionMessages);

	/**
	 * Return the LRP template process detail
	 * 
	 * @param userData
	 *            - the User Data of the main function
	 * 
	 * @return UserExitESFProcessDetail - ESF process detail
	 */
	public UserExitESFProcessDetail getLRPTemplateProcessDetail(UserData userData);

}
