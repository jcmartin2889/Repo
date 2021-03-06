package com.misys.equation.common.globalprocessing.mbeans;

import java.util.List;

import com.misys.equation.common.access.UnitNotAvailableException;
import com.misys.equation.common.internal.eapi.core.EQActionErrorException;
import com.misys.equation.common.internal.eapi.core.EQConnectionException;
import com.misys.equation.common.internal.eapi.core.EQException;

/**
 * Contains service methods for session management.
 * 
 * @author berzosa
 */
public interface SessionManagementService
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SessionManagementService.java 10052 2010-11-25 15:47:19Z berzosa $";

	/**
	 * Gets a session ID by logging in to the given system and unit using the credentials supplied.
	 * 
	 * @param system
	 *            The system to log in to
	 * @param unit
	 *            The unit mnemonic of the unit to log-in to
	 * @param user
	 *            The user name to use for logging in
	 * @param password
	 *            The password to use for logging in
	 * @return A session ID that can be used for accessing other session-enabled functions; an empty string if login fails due to
	 *         invalid credentials.
	 * @throws EQException
	 *             If any error occurs logging-in.
	 * @throws EQActionErrorException
	 *             If a problem with the login credentials is detected.
	 */
	@MBeanOperation("Gets a session ID by logging in to the given system and unit using the credentials supplied.")
	public String getSession(@Meta(name = "system", desc = "The system to connect to") String system,
					@Meta(name = "unit", desc = "The unit to log-in to") String unit,
					@Meta(name = "user", desc = "The username to login with") String user,
					@Meta(name = "password", desc = "The password to login with") String password) throws EQException,
					EQActionErrorException;

	/**
	 * Determine whether the session is still valid.
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @return true if the session is still valid, false otherwise.
	 */
	@MBeanOperation("Determine whether the session has not timed-out.")
	public boolean isSessionAlive(@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier);

	/**
	 * Checks if the currently logged-in user is authorised to the given option under the given unit.
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @param system
	 *            The system where the unit belongs
	 * @param unit
	 *            the unit to check authorisation within
	 * @param option
	 *            The Option to check authorisation for within the given unit
	 * @return true if the user is authorised to the option, false otherwise
	 */
	@MBeanOperation("Checks if the currently logged-in user is authorised to the given option under the given unit.")
	public boolean checkUserAuthorisation(@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier,
					@Meta(name = "system", desc = "The system to check authorisation on") String system,
					@Meta(name = "unit", desc = "The unit to check authorisation on") String unit,
					@Meta(name = "optionCode", desc = "The optoin code to check authorisation for") String optionCode)
					throws EQException, UnitNotAvailableException;

	/**
	 * Returns the job id of the home session.
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @return The job id of the home session.
	 * @throws EQException
	 *             if any errors occur retrieving the home session job id
	 */
	@MBeanOperation("Returns the job id of the home session.")
	public String getHomeSessionJobId(@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier)
					throws EQException;
	/**
	 * Returns the job locks for the given user on the given system / unit.
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @param systemId
	 *            The system where the unit belongs
	 * @param unitId
	 *            The unit to check job locks in
	 * @param user
	 *            The user to check locks of
	 * @throws EQException
	 *             if any errors occur retrieving the job locsk
	 * @throws UnitNotAvailableException
	 */
	@MBeanOperation("Returns the job locks for the given user on the given system / unit.")
	public List<String> getJobLocks(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The system where the unit belongs") String systemId,
					@Meta(name = "unitId", desc = "The unit to check job locks in") String unitId,
					@Meta(name = "userId", desc = "The user to check locks of") String userId) throws EQException,
					UnitNotAvailableException;

	/**
	 * Logs the given session ID out of the system.
	 * 
	 * @param sessionIdentifier
	 *            The session identifier to log out of the system.
	 * @throws EQActionErrorException
	 *             If a problem logging the session out.
	 */
	@MBeanOperation("Logs the given session ID out of the system.")
	public void logOffSession(@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier)
					throws EQException;

	/**
	 * Writes an Audit Log record into GAUPF for the given option, key value, and key description.
	 * <p>
	 * An example, the System options requires that: <em>
	 * A new record will be written to the Global Audit File (GAUPF � defined in project 1) for each option updated, as follows
	 * <ul>
	 * <li>User � initiating user
	 * <li>Time last maintained � new timestamp
	 * <li>Option identifier � the Global system options option identifier 
	 * <li>Option identifier name � description of option identifier
	 * <li>Key value � <Global system option value>
	 * <li>Key value description � <Global system option code>
	 * </ul>
	 * </em> In this case, the client needs to invoke: <code>
	 * GlobalDbToolbox.writeAudit(Option.GLOBAL_SYS_OPTS, "[option value]", "[option code]");
	 * </code>
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @param option
	 *            The Option being audited
	 * @param keyValue
	 *            The key value (refer to FSD for corresponding module)
	 * @param keyDescription
	 *            The Key Description (refer to FSD for corresponding module)
	 * @throws EQConnectionException
	 *             If an error occurs retrieving the session
	 */
	@MBeanOperation("Writes an Audit Log record into GAUPF for the given option, key value, and key description.")
	public void writeAudit(@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier,
					@Meta(name = "option", desc = "The option identifier") String option,
					@Meta(name = "keyValue", desc = "The key value") String keyValue,
					@Meta(name = "keyDescription", desc = "The key description") String keyDescription) throws EQException;

	/**
	 * Returns the description of the user.
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @param username
	 *            The username to get the description of
	 * @return The user's description
	 * @throws EQException
	 *             If any error occurs getting the user description
	 */
	@MBeanOperation("Returns the description of the user")
	public String getUserDescription(@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier,
					@Meta(name = "username", desc = "The username to get the description of") String userName) throws EQException;

}