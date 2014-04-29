package com.misys.equation.common.globalprocessing.mbeans;

import java.util.List;
import java.util.Map;

import com.ibm.as400.access.ObjectDescription;
import com.misys.equation.common.access.UnitNotAvailableException;
import com.misys.equation.common.dao.beans.CARecordDataModel;
import com.misys.equation.common.dao.beans.EQSRecordDataModel;
import com.misys.equation.common.dao.beans.GPX1RecordDataModel;
import com.misys.equation.common.globalprocessing.BranchDetails;
import com.misys.equation.common.internal.eapi.core.EQException;

/**
 * Contains service methods for session management.
 * 
 * @author berzosa
 */
public interface GroupService
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GroupService.java 9962 2010-11-18 17:31:39Z MACDONP1 $";

	/**
	 * Loads the GPX records for the group that the user's home system unit belongs to.
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * 
	 * @return The GPX records for the group that the user's home system unit belongs to
	 * @throws EQException
	 *             If an error occurs retrieving the groups
	 */
	@MBeanOperation("Loads the GPX records for the group that the user's home system unit belongs to.")
	public List<GPX1RecordDataModel> loadHomeSystemGroupUnits(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier) throws EQException;

	/**
	 * Returns the GPX records (linked units) for all the members of the given group mnemonic.
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @param groupId
	 *            The group mnemonic of the group to load
	 * @return The GPX records for the group with the specified mnemonic
	 * @throws EQException
	 *             If an error occurs retrieving the groups
	 */
	@MBeanOperation("Loads the GPX records for the group with the specified mnemonic.")
	public List<GPX1RecordDataModel> loadAllGroupUnits(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier,
					@Meta(name = "groupMnemonic", desc = "The group mnemnonic") String groupId) throws EQException;

	/**
	 * Loads the branches defined in the given unit.
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @param systemId
	 *            The System ID within which the unit is defined
	 * @unitId The Unit ID to load the branches of
	 * @return The branches defined in the given unit.
	 * @throws EQException
	 *             If an error occurs retrieving the branch members of the given unit.
	 * @throws UnitNotAvailableException
	 */
	@MBeanOperation("Loads the branches defined in the given unit.")
	public List<CARecordDataModel> loadBranchMembers(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId) throws EQException, UnitNotAvailableException;

	/**
	 * Retrieves the branch status.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param branchNumber
	 *            the branch number
	 * @return the branch status give the branch number
	 * @throws EQException
	 *             If an error occurs
	 * @throws UnitNotAvailableException
	 */
	@MBeanOperation("Retrieves the branch status.")
	public List<BranchDetails> getBranchesWithStatus(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId) throws EQException, UnitNotAvailableException;

	/**
	 * Loads the branches and it's details defined in the given unit.
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @param systemId
	 *            The System ID within which the unit is defined
	 * @unitId The Unit ID to load the branches of
	 * @return The branches defined in the given unit.
	 * @throws EQException
	 *             If an error occurs retrieving the branch members of the given unit.
	 * @throws UnitNotAvailableException
	 */
	@MBeanOperation("Loads the branches and it's details defined in the given unit.")
	public List<BranchDetails> loadBranchDetails(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId) throws EQException, UnitNotAvailableException;

	/**
	 * Returns a a map of system and EQS authorisations given the session ID.
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @return List&lt;EQSRecordDataModel&gt; The list of authorised units the user is authorised to.
	 * @throws EQException
	 *             If an error occurs retrieving the user's authorised units.
	 */
	@MBeanOperation("Returns a map of system and EQS authorisations given the session ID.")
	public Map<String, List<EQSRecordDataModel>> getEQSAuthorisationInGroup(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionId) throws EQException;

	/**
	 * Returns a list of unit authorisations for the currently logged-in user given the session ID.
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @return List&lt;EQSRecordDataModel&gt; The list of authorised units the user is authorised to.
	 * @throws EQException
	 *             If an error occurs retrieving the user's authorised units.
	 */
	@MBeanOperation("Returns a list of unit authorisations for the currently logged-in user given the session ID.")
	public List<EQSRecordDataModel> getAllAuthorisedUnits(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier) throws EQException;

	/**
	 * Returns the global group ID based on the last three letters of the global group library (e.g., 'MNG' for global group library
	 * 'KGRPMNG')
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @return The global group mnemonic that this system operates on.
	 * @throws EQException
	 *             If an error occurs retrieving the global library group ID.
	 */
	@MBeanOperation("Get a list of all Groups and units within each.")
	public String getGlobalGroupId(@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier)
					throws EQException;

	/**
	 * Returns the global library group description. This is the text description of the global library (e.g., KGRPMNG) on the
	 * iSeries machine using an {@link ObjectDescription} .
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @return The global group text description of the global group library
	 * @throws EQException
	 *             If an error occurs retrieving the global library group description.
	 */
	@MBeanOperation("Returns the global library group description")
	public String getGlobalGroupDescription(@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier)
					throws EQException;

	/**
	 * Updates (or inserts) the group and associated linked units.
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @param newMembers
	 *            The associated linked units
	 * @throws EQException
	 *             If an error occurs updating the global library group members.
	 */
	@MBeanOperation("Updates the group and associated linked units.")
	public void insertOrUpdateGroup(@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier,
					@Meta(name = "newMembers", desc = "The associated linked units") List<GPX1RecordDataModel> newMembers)
					throws EQException;

	/**
	 * Updates the global library's text description and returns the updated description, truncated if necessary.
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @param description
	 *            The new global library description to set
	 * @return The new description, truncated if necessary.
	 * @throws EQException
	 *             If an error occurs updating the global library group description.
	 */
	@MBeanOperation("Updates the global library's text description.")
	public String setGlobalGroupDescription(@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier,
					@Meta(name = "description", desc = "The new global library description to set.") String description)
					throws EQException;
}
