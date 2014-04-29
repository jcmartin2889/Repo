package com.misys.equation.common.globalprocessing.mbeans;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ibm.as400.access.QueuedMessage;
import com.misys.equation.common.access.EquationPVMetaData;
import com.misys.equation.common.access.IDataList;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.access.UnitNotAvailableException;
import com.misys.equation.common.dao.beans.CHRecordDataModel;
import com.misys.equation.common.dao.beans.EQSRecordDataModel;
import com.misys.equation.common.dao.beans.GBRecordDataModel;
import com.misys.equation.common.dao.beans.GCRecordDataModel;
import com.misys.equation.common.dao.beans.GDRecordDataModel;
import com.misys.equation.common.dao.beans.OCRecordDataModel;
import com.misys.equation.common.dao.beans.VP1RecordDataModel;
import com.misys.equation.common.globalprocessing.ServerStatus;
import com.misys.equation.common.globalprocessing.UnitDetails;
import com.misys.equation.common.globalprocessing.UserDetails;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;

/**
 * Contains service methods for user accounts management.
 * 
 * @author berzosa
 */
public interface SystemUnitsService
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SystemUnitsService.java 11293 2011-06-24 14:10:00Z hempensp $";

	/**
	 * Returns the full list of EQSec authorisations records.
	 * 
	 * @return Full list of EQSec authorisations records.
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur loading the EQSEC records
	 */
	@MBeanOperation("Loads the EQSEC authorisation records.")
	public List<EQSRecordDataModel> getEqSecUsers(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId)
					throws EQException;

	/**
	 * Returns the UnitDetails of the given unit on the specified system.
	 * 
	 * @return UnitDetails of the given unit on the specified system.
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur loading the unit details
	 */
	@MBeanOperation("Returns the UnitDetails of the given unit on the specified system.")
	public UnitDetails getUnitDetails(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId) throws EQException;

	/**
	 * Returns the UnitDetails of the given unit on the specified system.
	 * 
	 * @param sessionId
	 * @param systemId
	 * @param unitId
	 * @param getOtherDetails
	 *            -if true, it will retrieve the number of jobs signed on and retrieve for the EOD total time details; otherwise it
	 *            is defaulted to zero
	 * 
	 * @return UnitDetails of the given unit on the specified system.
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur loading the unit details
	 */
	@MBeanOperation("Returns the UnitDetails of the given unit on the specified system.")
	public UnitDetails getUnitDetails(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId,
					@Meta(name = "getOtherDetails", desc = "whether or not to retrieve the other details") boolean getOtherDetails)
					throws EQException;

	/**
	 * Returns the Unit description (KAPUNDES) of the given unit on the specified system.
	 * 
	 * @return Unit description of the given unit on the specified system.
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur loading the unit's description
	 */
	@MBeanOperation("Returns the Unit description (KAPUNDES) of the given unit on the specified system.")
	public String getUnitDescription(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId) throws EQException;

	/**
	 * Returns the Unit phase (KAPPHS) of the given unit on the specified system.
	 * 
	 * @return Unit phase of the given unit on the specified system.
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur loading the unit's current phase
	 */
	@MBeanOperation("Returns the Unit phase (KAPPHS) of the given unit on the specified system.")
	public String getUnitPhase(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId) throws EQException;

	/**
	 * Returns the UserDetails for the given user ID on the specified system.
	 * 
	 * @return UserDetails for the given user ID on the specified system.
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur loading the user details
	 */
	@MBeanOperation("Returns the UserDetails of the given user on the specified system.")
	public UserDetails getUserDetails(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "userId", desc = "The Usert ID") String userId) throws EQException;

	/**
	 * Returns the Enhancement records (CHRecordDataModel) installed on the given unit of the specified system.
	 * 
	 * @return Enhancement records (CHRecordDataModel) installed on the given unit of the specified system.
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur retrieving the unit enhancement records
	 */
	@MBeanOperation("Returns the Enhancement records (CHRecordDataModel) installed on the given unit of the specified system.")
	public List<CHRecordDataModel> getUnitEnhancements(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId) throws EQException, UnitNotAvailableException;

	/**
	 * Returns the Enhancement records (CHRecordDataModel) installed on the given unit of the specified system.
	 * 
	 * @return Enhancement records (CHRecordDataModel) installed on the given unit of the specified system.
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur retrieving the installed enhancements
	 */
	@MBeanOperation("Returns the Enhancement records (CHRecordDataModel) installed on the given unit of the specified system.")
	public Set<String> getInstalledEnhancements(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId,
					@Meta(name = "checkEnhancements", desc = "The list of enhancements to check if installed") Collection<String> checkEnhancements)
					throws EQException, UnitNotAvailableException;

	/**
	 * Returns all enhancement mnemonics installed on the unit by cross referencing with the release (QZREL) records in QZPF (split
	 * by ',', and '+'). This utility method enables the caller to cache the installed enhancement on the unit based on QZPF records
	 * so that repeated calls to 'getInstalledEnhancements()' can be avoided.
	 * 
	 * @param sessionId
	 *            The Session identifier
	 * @param systemId
	 *            The System ID
	 * @param unitId
	 *            The Unit ID
	 */
	@MBeanOperation("Returns all enhancement mnemonics installed on the unit by cross referencing with the release (QZREL) records in QZPF.")
	public Set<String> getInstalledEnhancementsByQZReleases(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId) throws EQException, UnitNotAvailableException;

	/**
	 * Returns the server status (ServerStatus) of given system.
	 * 
	 * @return the {@link ServerStatus} of the given system
	 * @throws EQException
	 *             ,UnitNotAvailableException - If any error occurs retrieving the server status
	 */
	@MBeanOperation("Returns the server status (ServerStatus) of given system.")
	public ServerStatus getServerStatus(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId) throws EQException;

	/**
	 * Calls Q14DER API on the given unit and returns the results.
	 * 
	 * @return API call results
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur executing the API
	 */
	@MBeanOperation("Calls Q14DER API on the given unit and returns the results.")
	public IEquationStandardObject callAPIQ14DER(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId) throws EQException, UnitNotAvailableException;

	/**
	 * Calls Q15DER API on the given unit and returns the results.
	 * 
	 * @return API call results
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur executing the API
	 */
	@MBeanOperation("Calls Q14DER API on the given unit and returns the results.")
	public IEquationStandardObject callAPIQ15DER(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId) throws EQException, UnitNotAvailableException;

	/**
	 * Returns the Authorised Options (GDRecordDataModel) for a given User.
	 * 
	 * @return User Authorised Options (GDRecordDataModel)
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur retrieving the authorised options
	 */
	@MBeanOperation("Returns the Authorised Options (GDRecordDataModel) for a given User.")
	public List<GDRecordDataModel> getAuthorisedOptions(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId,
					@Meta(name = "userId", desc = "User ID") String userId) throws EQException, UnitNotAvailableException;

	/**
	 * Loads User Specific Information
	 * 
	 * @return a User instance loaded from the database
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur loading the user specific information record
	 */
	@MBeanOperation("Loads  User Specific Information")
	public OCRecordDataModel loadUser(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId,
					@Meta(name = "userId", desc = "User ID") String qualifiedUserId) throws EQException, UnitNotAvailableException;

	/**
	 * Retrieves available options (GBPF records)
	 * 
	 * @return The available options
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur retrieving the options
	 */
	@MBeanOperation("Retrieves available options (GBPF records).")
	public List<GBRecordDataModel> getOptions(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId) throws EQException, UnitNotAvailableException;

	/**
	 * Retrieves available menu options (GCPF records)
	 * 
	 * @return The available menu options
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur retrieving the menu options
	 */
	@MBeanOperation("Retrieves available menu options (GCPF records).")
	public List<GCRecordDataModel> getMenuOptions(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId) throws EQException, UnitNotAvailableException;

	/**
	 * Retrieves the P/V meta-data for the given P/V.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The system on which the unit resides
	 * @param unitId
	 *            The unit on which the P/V resides
	 * @param pvName
	 *            The PV name
	 * @return The EquationPVMetaData for the given PV
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur obtaining the P/V meta data.
	 */
	@MBeanOperation("Retrieves the P/V meta-data for the given P/V.")
	public EquationPVMetaData getPVMetaData(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId,
					@Meta(name = "pvName", desc = "The P/V name") String pvName) throws EQException, UnitNotAvailableException;

	/**
	 * Retrieves data from the specified P/V given criteria.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The system on which the unit resides
	 * @param unitId
	 *            The unit on which the P/V resides
	 * @param pvName
	 *            The PV name
	 * @param decode
	 *            The decode value
	 * @param security
	 *            The security value
	 * @param query
	 *            The query
	 * @param start
	 *            The start offset
	 * @param direction
	 *            The direction (-1, +1)
	 * @param maxResults
	 *            The maximum number of results to retur
	 * 
	 * @return An IDataList containing the results of the query
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur obtaining the P/V meta data.
	 */
	@MBeanOperation("Retrieves data from the specified P/V given criteria.")
	public IDataList getEquationDataList(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId,
					@Meta(name = "pvName", desc = "The P/V name") String pvName,
					@Meta(name = "decode", desc = "The decode value") String decode,
					@Meta(name = "security", desc = "The security value") String security,
					@Meta(name = "query", desc = "The query") String query,
					@Meta(name = "start", desc = "The start offset") String start,
					@Meta(name = "direction", desc = "The direction (-1, +1)") int direction,
					@Meta(name = "maxResults", desc = "The maximum number of results to return") int maxResults)
					throws EQException, UnitNotAvailableException;

	/**
	 * Retrieves a message from the home system unit's message queue at the given queue path (see JT400 documentation for details on
	 * path specification)
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The system from which to receive the messages
	 * @param path
	 *            The path to the message queue.
	 * @param messageKey
	 *            The message key, or null if no message key is needed.
	 * @param waitTime
	 *            The number of seconds to wait for the message to arrive in the queue so it can be received.
	 * @param messageAction
	 *            The action to take after the message is received
	 * @param messageType
	 *            The type of message to return.
	 * @return The queued message, or null if the message can not be received
	 * @throws IOException
	 *             If any error occurs retrieving a message.
	 */
	@MBeanOperation("Retrieves a message from the home system unit's message queue at the given queue path.")
	public QueuedMessage receiveFromMessageQueue(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The system from which to receive the messages") String systemId,
					@Meta(name = "path", desc = "The path to the message queue.") String path,
					@Meta(name = "messageKey", desc = "The message key, or null if no message key is needed.") byte[] messageKey,
					@Meta(name = "waitTime", desc = "The number of seconds to wait for the message to arrive in the queue so it can be received.") int waitTime,
					@Meta(name = "messageAction", desc = "The action to take after the message is received") String messageAction,
					@Meta(name = "messageType", desc = "The type of message to return.") String messageType) throws EQException,
					UnitNotAvailableException;

	/**
	 * Sends a reply to a message on the message queue.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The system on which the message originated from
	 * @param path
	 *            The path to the message queue.
	 * @param messageKey
	 *            The message key, or null if no message key is needed.
	 * @param replyText
	 *            The message reply text
	 * @throws IOException
	 *             If any error occurs sending the message reply.
	 */
	@MBeanOperation("Retrieves a message from the home system unit's message queue at the given queue path.")
	public void sendMessageReply(String sessionId, String systemId, String path, byte[] messageKey, String replyText)
					throws EQException, UnitNotAvailableException;

	/**
	 * Calls G22FRR API on the given unit and user.
	 * 
	 * @return API call results
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur executing the API
	 */
	@MBeanOperation("Calls G22FRR API on the given unit and user.")
	public IEquationStandardObject callAPIG22FRR(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId,
					@Meta(name = "mode", desc = "RPG API mode") String mode,
					@Meta(name = "apiParams", desc = "API Parameters") Map<String, String> apiParams) throws EQException,
					UnitNotAvailableException;

	/**
	 * Calls G18FRR API to authorise Options to a User
	 * 
	 * @return API call results
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur executing the API
	 */
	@MBeanOperation("Calls G18FRR API to authorise Options to a User.")
	public IEquationStandardObject callAPIG18FRR(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId,
					@Meta(name = "userId", desc = "User Id") String userId,
					@Meta(name = "optionId", desc = "Option Id") String optionId,
					@Meta(name = "mode", desc = "RPG API mode") String mode) throws EQException, UnitNotAvailableException;

	/**
	 * Returns a list of units that the logged on user is authorised to on the given system (EQSEC).
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The system on which the unit resides
	 * @param userId
	 *            The UserId to get EQSEC authorised units of
	 * @return A list of unit mnemonics that the user is authorised to on the specified system.
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur attempting to retrieve the authorised units
	 */
	@MBeanOperation("Returns a list of units that the logged on user is authorised to on the given system (EQSEC).")
	public List<String> getEqSecAuthorisedUnits(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "userId", desc = "The User ID") String userId) throws EQException, UnitNotAvailableException;

	/**
	 * Retrieves the required job log entries starting from the specified position.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The system on which the unit resides
	 * @param jobName
	 *            The Job Name
	 * @param jobUser
	 *            The Job User
	 * @param jobNumber
	 *            The Job Number
	 * @param pos
	 *            The position to start retrieving messages from
	 * @param maxResults
	 *            The maximum number of log entries to retrieve
	 * @return A List of QueuedMessage instances representing the job log
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur attempting to retrieve the job log
	 */
	@MBeanOperation("Retrieves the required job log entries starting from the specified position.")
	public List<QueuedMessage> getJobLog(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "jobName", desc = "The Job Name") String jobName,
					@Meta(name = "jobUser", desc = "The Job User") String jobUser,
					@Meta(name = "jobNumber", desc = "The Job Number") String jobNumber,
					@Meta(name = "pos", desc = "The position to start retrieving messages from") int pos,
					@Meta(name = "maxResults", desc = "The maximum number of log entries to retrieve") int maxResults)
					throws EQException, UnitNotAvailableException;

	/**
	 * Terminates a job that is currently running in the system.
	 * 
	 * @param sessionId
	 *            The Session identifier"
	 * @param systemId
	 *            The system on which the job is running
	 * @param jobDesc
	 *            The job description
	 * @param userProfile
	 *            The user profile running the job
	 * @param jobId
	 *            The job ID of the job to terminate
	 * @param duration
	 *            The duration (in seconds) to wait before forcefully terminating the job
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur while attempting to terminate the job.
	 */
	@MBeanOperation("Terminates a job that is currently running in the system.")
	public void endJob(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The system on which the job is running") String systemId,
					@Meta(name = " jobDesc", desc = "The job description") String jobDesc,
					@Meta(name = "userProfile", desc = "The user profile running the job") String userProfile,
					@Meta(name = "jobId", desc = "The job ID of the job to terminate") String jobId,
					@Meta(name = "duration", desc = "The duration (in seconds) to wait before forcefully terminating the job") int duration)
					throws EQException, UnitNotAvailableException;

	/**
	 * Executes a system program given the program name and command parameters on the given system unit.
	 * 
	 * @param sessionId
	 *            The Session identifier
	 * @param command
	 *            CMDRecordDataModel of command
	 * @param systemId
	 *            The System ID to run the command on
	 * @param unitId
	 *            The unit ID to run the command on
	 * @param dynamicParms
	 *            The command-specific parameters to execute
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occurred executing the program
	 */
	@MBeanOperation("Executes a system program given the program name and command parameters on the given system unit.")
	public void runSystemCommand(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "commandRecord", desc = "The command record") V45Command command,
					@Meta(name = "systemId", desc = "The System ID to run the command on") String systemId,
					@Meta(name = "unitId", desc = "The unit ID to run the command on") String unitId,
					@Meta(name = "dynamicParms", desc = "The command-specific parameters to execute") String parameters)
					throws EQException, UnitNotAvailableException;

	/**
	 * Returns if an enhancement is switched on or off
	 * 
	 * @param sessionId
	 *            The Session identifier
	 * @param systemId
	 *            The System ID to run the command on
	 * @param unitId
	 *            The unit ID to run the command on
	 * @param enhancement
	 *            Enhancement Id
	 * 
	 * @return true if enhancement is switched on, false otherwise
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occurred determining if the enhancement was installed
	 */
	@MBeanOperation("Returns if an enhancement is switched on or off")
	public boolean isEnhancementOn(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID to run the command on") String systemId,
					@Meta(name = "unitId", desc = "The unit ID to run the command on") String unitId,
					@Meta(name = "enhancement", desc = "The program enhancement Id") String enhancement) throws EQException,
					UnitNotAvailableException;

	/**
	 * Returns the value of the CURRENT_USER job attribute.
	 * 
	 * @param sessionId
	 *            The Session identifier
	 * @param systemId
	 *            The System ID to run the command on
	 * @param jobName
	 *            The job name
	 * @param userName
	 *            The user name of the job
	 * @param jobNumber
	 *            The job number
	 * @return The value of the CURRENT_USER job attribute.
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occurred getting the current job user
	 */
	@MBeanOperation("Returns the value of the CURRENT_USER job attribute.")
	public String getJobCurrentUser(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID to run the command on") String systemId,
					@Meta(name = "jobName", desc = "The job name") String jobName,
					@Meta(name = "userName", desc = "The user name of the job") String userName,
					@Meta(name = "jobNumber", desc = "The job number") String jobNumber) throws EQException,
					UnitNotAvailableException;

	/**
	 * Returns the Volume Based Pricing By Product records (VP1RecordDataModel) on the given unit of the specified system.
	 * 
	 * @param sessionId
	 *            The Session identifier
	 * @param systemId
	 *            The System ID to run the command on
	 * @param unitId
	 *            The unit ID to run the command on
	 * 
	 * @return Volume Based Pricing By Product records (VP1RecordDataModel) on the given unit of the specified system.
	 * 
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur retrieving the unit enhancement records
	 */
	@MBeanOperation("Returns the Enhancement records (VP1RecordDataModel) installed on the given unit of the specified system.")
	public List<VP1RecordDataModel> getUnitVolumeBasedPricingByProduct(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId) throws EQException, UnitNotAvailableException;

	/**
	 * The method will perform a clear-copy of the MPMPF records from the unit to the MPMPF of the global library.
	 * 
	 * @param sessionId
	 *            The Session identifier
	 * @param systemId
	 *            The System ID
	 * @param unitId
	 *            The unit ID to copy MPMPF records from
	 * @throws EQException
	 *             ,UnitNotAvailableException If any error occurs copying the mapping records to the global library
	 */
	@MBeanOperation("The method will perform a clear-copy of the MPPF records from the unit to the MPPF of the global library.")
	public void propagateMappingParameters(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID to copy the records from") String systemId,
					@Meta(name = "unitId", desc = "The unit ID to copy the records from") String unitId) throws EQException,
					UnitNotAvailableException;

	/**
	 * The method will refresh the given unit's APV and APJ records with the global library's version.
	 * 
	 * @param sessionId
	 *            The Session identifier
	 * @param toSystemId
	 *            The System ID to copy the records to
	 * @param toUnitId
	 *            The unit ID to copy the records to
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur copying the records from the global library to the unit
	 */
	@MBeanOperation("The method will refresh the given unit's APV and APJ records with the global library's version.")
	public void distributeAPIDefinitions(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID to copy the records to") String systemId,
					@Meta(name = "unitId", desc = "The unit ID to copy the records to") String unitId) throws EQException,
					UnitNotAvailableException;

	/**
	 * Returns all languages defined in this unit keyed by language mnemonic.
	 * 
	 * @param sessionId
	 *            The Session identifier
	 * @param toSystemId
	 *            The System ID where the unit resides
	 * @param toUnitId
	 *            The unit ID to get the defined languages of
	 * @throws EQException
	 *             If any errors occur retrieving the languages
	 */
	@MBeanOperation("Returns all languages defined in this unit keyed by language mnemonic.")
	public Map<String, String> getLanguages(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID where the unit resides") String systemId,
					@Meta(name = "unitId", desc = "The unit ID to get the defined languages of") String unitId) throws EQException,
					UnitNotAvailableException;

	/**
	 * Parsers and converts a message text into an {@link EQMessage}.
	 * 
	 * @param sessionId
	 *            The Session identifier
	 * @param toSystemId
	 *            The System ID where the unit resides
	 * @param toUnitId
	 *            The unit ID to get the defined languages of
	 * @param messageText
	 *            The message to get convert to an {@link EQMessage}
	 * @throws EQException
	 *             If any errors occur retrieving the languages
	 */
	@MBeanOperation("Parsers and converts a message text into an EQMessage.")
	public EQMessage convertToEQMessage(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID where the unit resides") String systemId,
					@Meta(name = "unitId", desc = "The unit ID where the unit resides") String unitId,
					@Meta(name = "message", desc = "The message to get the EQMEssage of") String messageText) throws EQException,
					UnitNotAvailableException;

	/**
	 * Suspends the unit jobs by logging out all equation users, closing the pool, and removing the unit from the context.
	 * 
	 * @param sessionId
	 *            The Session identifier
	 * @param systemId
	 *            The System ID of the unit to close
	 * @param unitId
	 *            The Unit ID of the unit to close
	 */
	@MBeanOperation("Closes the unit by logging out all equation users, closing the pool, and removing the unit from the context.")
	public void suspendUnitJobs(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID of the unit to close") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID of the unit to close") String unitId) throws EQException;

	/**
	 * Returns the User records (OCRecordDataModel) of the given unit of the specified system.
	 * 
	 * @return User records (OCRecordDataModel) of the given unit of the specified system.
	 * @throws EQException
	 *             ,UnitNotAvailableException If any errors occur retrieving the user records
	 */
	@MBeanOperation("Returns the User records (OCRecordDataModel) of the given unit of the specified system.")
	public List<OCRecordDataModel> getUsers(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId) throws EQException, UnitNotAvailableException;
	/**
	 * Returns the 24*7 data area as a string
	 * 
	 * @return String dtaara
	 * @throws EQException
	 *             If any errors occur retrieving the user records
	 */
	@MBeanOperation("Returns the 24*7 data area as a string.")
	public String get247DataArea(@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId) throws EQException;

	/**
	 * Adds a specified unit to the SystemStatusManager singleton in GlobalWeb
	 * 
	 * *
	 * */
	@MBeanOperation("Adds a specified unit to the SystemStatusManager singleton in GlobalWeb")
	public void addUnitToMonitor(@Meta(name = "unitId", desc = "The Unit ID") String unitId,
					@Meta(name = "systemId", desc = "The System ID") String systemId);

}
