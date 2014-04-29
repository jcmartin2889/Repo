package com.misys.equation.common.globalprocessing.mbeans;

import java.util.List;
import java.util.Map;

import com.misys.equation.common.access.UnitNotAvailableException;
import com.misys.equation.common.dao.beans.QD1RecordDataModel;
import com.misys.equation.common.dao.beans.QZRecordDataModel;
import com.misys.equation.common.globalprocessing.systemoptions.SystemOptionUpdateTransaction;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;

/**
 * Contains service methods for unit options management.
 * 
 * @author berzosa
 */
public interface UnitOptionsService
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UnitOptionsService.java 9962 2010-11-18 17:31:39Z MACDONP1 $";

	/**
	 * Get all unit system option groups.
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @param systemId
	 *            The system where the unit belongs
	 * @param unitId
	 *            The unit to authorised option IDs in
	 * @return - {@link List} of QD1 records
	 * @throws EQException
	 *             If an error occurs retrieving the unit system option groups
	 */
	@MBeanOperation("Get all unit system option groups")
	public List<QD1RecordDataModel> getAllUnitSystemOptionGroups(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier,
					@Meta(name = "systemId", desc = "The system where the unit belongs") String systemId,
					@Meta(name = "unitId", desc = "The unit to authorised option IDs in") String unitId) throws EQException,
					UnitNotAvailableException;

	/**
	 * Retrieve all the maintainable system options in an Equation unit
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @param systemId
	 *            The system where the unit belongs
	 * @param unitId
	 *            The unit to authorised option IDs in
	 * @return the {@link List} of maintainable QZ records retrieved from the Equation unit
	 * @throws EQException
	 *             If an error occurs retrieving the unit system option groups
	 */
	@MBeanOperation("Retrieve all the maintainable system options in an Equation unit")
	public List<QZRecordDataModel> getMaintainableSysOptsInUnit(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier,
					@Meta(name = "systemId", desc = "The system where the unit belongs") String systemId,
					@Meta(name = "unitId", desc = "The unit to authorised option IDs in") String unitId) throws EQException,
					UnitNotAvailableException;

	/**
	 * Retrieve the maintainable system option in an Equation unit according to system option field name
	 * 
	 * @param sessionIdentifier
	 *            A valid session identifier
	 * @param systemId
	 *            The system where the unit belongs
	 * @param unitId
	 *            The unit to authorised option IDs in
	 * @param sysOptFieldName
	 *            The system option field for selecting the system option record
	 * @return the {@link List} of maintainable QZ records retrieved from the Equation unit
	 * @throws EQException
	 *             If an error occurs retrieving the unit system option groups
	 */
	@MBeanOperation("Retrieve all the maintainable system options in an Equation unit")
	public List<QZRecordDataModel> getMaintainableSysOptsInUnit(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionIdentifier,
					@Meta(name = "systemId", desc = "The system where the unit belongs") String systemId,
					@Meta(name = "unitId", desc = "The unit to authorised option IDs in") String unitId,
					@Meta(name = "sysOptFieldName", desc = "The unit to authorised option IDs in") String sysOptFieldName)
					throws EQException, UnitNotAvailableException;

	/**
	 * Retrieve the system option via QZ file.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The system on which the unit resides
	 * @param unitId
	 *            The unit on which to get the system option value of
	 * @param filedName
	 *            The Field Name
	 * @return The system option value
	 * @throws EQException
	 *             If any errors occur getting the system option value
	 */
	@MBeanOperation("Retrieve the system option via QZ file")
	public String getSystemOption(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The system on which the unit resides") String systemId,
					@Meta(name = "unitId", desc = "The unit on which to get the system option value of") String unitId,
					@Meta(name = "fieldName", desc = "The Field Name") String filedName) throws EQException,
					UnitNotAvailableException;

	/**
	 * Sets the system option value.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The system on which the unit resides
	 * @param unitId
	 *            The unit on which to set the system option value of
	 * @param systemOptionType
	 *            The system option type
	 * @param systemOptionGroup
	 *            The system option group to which the option belongs
	 * @param sysOptName
	 *            The system option name to set
	 * @param value
	 *            The value to set the option t
	 * @throws EQException
	 *             If any errors occur setting the system option value
	 */
	@MBeanOperation("Sets the system option value.")
	public EQMessage setSystemOption(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The system on which the unit resides") String systemId,
					@Meta(name = "unitId", desc = "The unit on which to set the system option value of") String unitId,
					@Meta(name = "systemOptionType", desc = "The system option type") String systemOptionType,
					@Meta(name = "systemOptionGroup", desc = "The system option group to which the option belongs") String systemOptionGroup,
					@Meta(name = "sysOptName", desc = "The system option name to set") String sysOptName,
					@Meta(name = "value", desc = "The value to set the option to") String value) throws EQException,
					UnitNotAvailableException;

	/**
	 * Sets the system option value of related system options.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The system on which the unit resides
	 * @param unitId
	 *            The unit on which to set the system option value of
	 * @param systemOptionType
	 *            The system option type
	 * @param systemOptionGroup
	 *            The system option group to which the option belongs
	 * @param sysOptName
	 *            The system option name to set
	 * @param value
	 *            The value to set the option t
	 * @throws EQException
	 *             If any errors occur setting the system option value
	 */
	@MBeanOperation("Sets the system option value of related system options.")
	public SystemOptionUpdateTransaction setRelatedSystemOptions(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The system on which the unit resides") String systemId,
					@Meta(name = "unitId", desc = "The unit on which to set the system option value of") String unitId,
					@Meta(name = "systemOptionType", desc = "The system option type") String systemOptionType,
					@Meta(name = "systemOptionGroup", desc = "The system option group to which the option belongs") String systemOptionGroup,
					@Meta(name = "sysOptName", desc = "The selected system option name to set") String sysOptName,
					@Meta(name = "relatedSysOptEnteredValues", desc = "The value to set the option to") Map<String, String> relatedSysOptEnteredValues,
					@Meta(name = "systemOptionsInDisplayOrder", desc = "The value to set the option to") String[] systemOptionsInDisplayOrder)
					throws EQException, UnitNotAvailableException;

	/**
	 * Retrieves the related fields.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The system on which the unit resides
	 * @param unitId
	 *            The unit on which to get the system option value of
	 * @param relFieldName
	 *            The field name to check for related fields
	 * @throws EQException
	 *             If any errors occur getting the related fields
	 */
	@MBeanOperation("Retrieves the related fields.")
	public List<QZRecordDataModel> checkRelatedFields(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The system on which the unit resides") String systemId,
					@Meta(name = "unitId", desc = "The unit on which to get the system option value of") String unitId,
					@Meta(name = "relFieldName", desc = "The field name to check for related fields") String relFieldName)
					throws EQException, UnitNotAvailableException;

	/**
	 * Retrieve the maintainable related system options in an Equation unit
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The system on which the unit resides
	 * @param unitId
	 *            The unit on which to get the system option value of
	 * @param relatedSysOpt
	 *            The field name that specifies the owning system option of related fields
	 * @return the {@link List} of maintainable QZ records retrieved from the Equation unit
	 * @throws EQException
	 *             If any errors occur getting the related fields
	 */
	@MBeanOperation("Retrieve the maintainable related system options in an Equation unit")
	public List<QZRecordDataModel> getMaintainableRelatedSysOpts(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The system on which the unit resides") String systemId,
					@Meta(name = "unitId", desc = "The unit on which to get the system option value of") String unitId,
					@Meta(name = "relatedSysOpt", desc = "The field name that specifies the owning system option of related fields") String relatedSysOpt)
					throws EQException, UnitNotAvailableException;
}
