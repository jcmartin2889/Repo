package com.misys.equation.common.globalprocessing.mbeans;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.misys.equation.common.access.UnitNotAvailableException;
import com.misys.equation.common.globalprocessing.audit.PropData;
import com.misys.equation.common.globalprocessing.audit.SystemUnit;
import com.misys.equation.common.internal.eapi.core.EQException;

/**
 * Contains service methods for ad hoc exports.
 * 
 * @author berzosa
 */
public interface AdHocExportService
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AdHocExportService.java 9962 2010-11-18 17:31:39Z MACDONP1 $";

	/**
	 * Exports the following parameters via SAPJ and creates audit log entries.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param setId
	 *            The set id if any
	 * @param ruleId
	 *            The rule id
	 * @param conditions
	 *            The conditions (where clause) string
	 * @param apiIdentifier
	 *            The API Reference Identifier
	 * @param propagationHeaders
	 *            The list of PropData audit header beans
	 * @param fieldsToInclude
	 *            The list of APIRecordDataModel records
	 * @param unitToCopy
	 *            The unit to export from (unit to copy).
	 * @param isLinkedCustomer
	 *            If the rule is a linked-customer rule.
	 * @throws EQException
	 *             If an error occurs
	 * @return Error messages by reference key and target unit
	 */
	@MBeanOperation("Exports the following parameters to an audit log.")
	public Map<String, Map<PropData, String>> export(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "setId", desc = "The set id if any") String setId,
					@Meta(name = "ruleId", desc = "The rule id") String ruleId,
					@Meta(name = "conditions", desc = "The conditions (where clause) string") String conditions,
					@Meta(name = "apiIdentifier", desc = "The API Reference Identifier") String apiIdentifier,
					@Meta(name = "propagationHeaders", desc = "The list of GlobalAuditPropagationHeader headers") List<PropData> propagationHeaders,
					@Meta(name = "fieldsToInclude", desc = "The list of APIRecordDataModel records") Collection<String> fieldsToInclude,
					@Meta(name = "unitToCopy", desc = "The unit to generate the propagation data from") SystemUnit unitToCopy,
					@Meta(name = "isLinkedCustomer", desc = "If this is a linked customer rule ad hoc export") boolean isLinkedCustomer)
					throws EQException, UnitNotAvailableException;

	/**
	 * Returns the keys for the specified API that match the given conditions when queried on a unit.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param apiIdentifier
	 *            The API Reference Identifier
	 * @param conditions
	 *            The conditions (where clause) string
	 * @param unitToCopy
	 *            The unit to export from (unit to copy).
	 * @throws EQException
	 *             If an error occurs
	 * @return Error messages by reference key and target unit
	 */
	@MBeanOperation("Returns the keys for the specified API that match the given conditions when queried on a unit.")
	public List<String> generateInputKeys(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "apiIdentifier", desc = "The API Reference Identifier") String apiIdentifier,
					@Meta(name = "conditions", desc = "The conditions (where clause) string") String conditions,
					@Meta(name = "unitToCopy", desc = "The unit to generate the propagation data from") SystemUnit unitToCopy)
					throws EQException, UnitNotAvailableException;

	/**
	 * Validates the rule conditions for a monitor-type rule by executing a full SQL statement based on the given conditions.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The System ID to test the conditions on
	 * @param unitId
	 *            The Unit ID to test the conditions on
	 * @param apiIdentifier
	 *            The API Reference Identifier
	 * @param conditions
	 *            The conditions (where clause) string
	 * @throws EQException
	 *             If an error occurs
	 * @return Error messages by reference key and target unit
	 */
	@MBeanOperation("Validates the rule conditions for a monitor-type rule by executing a full SQL statement based on the given conditions.")
	public void checkMonitorRuleConditions(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID to test the conditions on") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID to test the conditions on") String unitID,
					@Meta(name = "apiIdentifier", desc = "The API Reference Identifier") String apiIdentifier,
					@Meta(name = "conditions", desc = "The conditions (where clause) string") String conditions)
					throws EQException, UnitNotAvailableException;
}
