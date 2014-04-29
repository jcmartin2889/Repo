package com.misys.equation.function.useraccess;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.function.runtime.FunctionMessages;
import com.misys.equation.function.runtime.UserData;
import com.misys.equation.function.runtime.UserModifyData;

public interface IUserExitFunction
{

	/**
	 * Perform defaulting before the standard validation process
	 * 
	 * @param fieldsetId
	 *            - the field set id
	 * @param userModifyData
	 *            - the User modifiable data
	 */
	public void defaultMode(String fieldsetId, UserModifyData userModifyData);

	/**
	 * Perform validation after the standard validation process
	 * 
	 * @param fieldsetId
	 *            - the field set id
	 * @param userModifyData
	 *            - the User modifiable data
	 * @param returnMessages
	 *            - the list of messages
	 */
	public void validateMode(String fieldsetId, UserModifyData userModifyData, UserExitMessages returnMessages);

	/**
	 * Perform update before the standard update process
	 * 
	 * @param journalHeader
	 *            - the journal header
	 * @param userModifyData
	 *            - the User modifiable data
	 * @param returnMessages
	 *            - the list of messages
	 */
	public void preUpdate(JournalHeader journalHeader, UserModifyData userModifyData, UserExitMessages returnMessages);

	/**
	 * Perform update after the standard update process
	 * 
	 * @param journalHeader
	 *            - the journal header
	 * @param userModifyData
	 *            - the User modifiable data
	 * @param returnMessages
	 *            - the list of messages
	 */
	public void postUpdate(JournalHeader journalHeader, UserModifyData userModifyData, UserExitMessages returnMessages);

	/**
	 * Perform load before the standard retrieval process
	 * 
	 * @param userModifyData
	 *            - the User modifiable data
	 * @param returnMessages
	 *            - the list of messages
	 */
	public void preLoad(UserModifyData userModifyData, UserExitMessages returnMessages);

	/**
	 * Perform load after the standard retrieval process
	 * 
	 * @param userModifyData
	 *            - the User modifiable data
	 * @param returnMessages
	 *            - the list of messages
	 * 
	 * @return the function mode
	 */
	public String postLoad(UserModifyData userModifyData, UserExitMessages returnMessages);

	/**
	 * Perform load after retrieving the default charges for the function
	 * 
	 * @param userData
	 *            - the User Data of the main function
	 * @param userModifyDataEFC
	 *            - the User modifiable EFC data
	 */
	public void postLoadEFC(UserData userData, UserModifyData userModifyDataEFC);

	/**
	 * Perform processing when exiting the function
	 * 
	 * @param userModifyData
	 *            - the User Data
	 * @param returnMessages
	 *            - the list of messages
	 */
	public void onCancel(UserModifyData userModifyData, UserExitMessages returnMessages);

	/**
	 * Perform processing when doing update (outside of commitment control)
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
	 *            - the list of messages
	 */
	public void onFinalUpdate(JournalHeader journalHeader, String apiFieldSetId, UserData userData,
					FunctionMessages functionMessages);

}
