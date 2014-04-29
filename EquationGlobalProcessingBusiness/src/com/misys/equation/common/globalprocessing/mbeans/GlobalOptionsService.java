package com.misys.equation.common.globalprocessing.mbeans;

import java.util.List;

import com.misys.equation.common.dao.beans.GPMRecordDataManager;
import com.misys.equation.common.dao.beans.GPMRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;

/**
 * Contains service methods for global options management.
 * 
 * @author berzosa
 */
public interface GlobalOptionsService
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalOptionsService.java 9210 2010-09-17 15:31:21Z deroset $";

	/**
	 * Get all global system options.
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @return {@link List} of GPM records
	 * @throws EQException
	 *             If an error occurs retrieving the global options
	 */
	@MBeanOperation("Loads the GPX records for the group that the user's home system unit belongs to.")
	public List<GPMRecordDataModel> getAllGlobalSystemOptions(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier) throws EQException;

	/**
	 * Returns the value of the global system option id.
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @param optionId
	 *            The option ID
	 * @return The value of the global system option ID
	 * @throws EQException
	 *             If an error occurs retrieving the global options
	 */
	@MBeanOperation("Returns the value of the global system option id.")
	public GPMRecordDataManager getSystemOptionValue(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier,
					@Meta(name = "optionId", desc = "The option id") String optionId) throws EQException;

	/**
	 * Sets the value of the global system option id.
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @param optionId
	 *            The option ID
	 * @param optionValue
	 *            The option value to set the option to
	 * @throws EQException
	 *             If an error occurs retrieving the global options
	 */
	@MBeanOperation("Sets the value of the global system option id.")
	public void setSystemOptionValue(@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier,
					@Meta(name = "optionId", desc = "The option id") String optionId,
					@Meta(name = "optionValue", desc = "The option value") String newValue) throws EQException;
}
