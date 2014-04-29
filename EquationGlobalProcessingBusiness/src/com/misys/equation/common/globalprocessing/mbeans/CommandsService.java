package com.misys.equation.common.globalprocessing.mbeans;

import java.util.List;

import com.misys.equation.common.access.UnitNotAvailableException;
import com.misys.equation.common.internal.eapi.core.EQException;

public interface CommandsService
{
	/**
	 * Returns all CMD records (commands)
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @return
	 * @throws EQException
	 *             Error encountered
	 */
	@MBeanOperation(value = "Gets and returns all command records")
	public List<V45Command> getAllCommands(//
					@Meta(name = "sessionId", desc = "Session Identifier") String sessionId)//
					throws EQException;

	/**
	 * Returns all CMD records (commands) with 'AD' (Administrative) Category
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @return
	 * @throws EQException
	 *             Error encountered
	 */
	@MBeanOperation(value = "Gets and returns all command records")
	public List<V45Command> getAdministrativeCommands(//
					@Meta(name = "sessionId", desc = "Session Identifier") String sessionId)//
					throws EQException;

	/**
	 * Returns all CMD records (commands) with 'IN' (Installation) Category
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @return
	 * @throws EQException
	 *             Error encountered
	 */
	@MBeanOperation(value = "Gets and returns all command records")
	public List<V45Command> getInstallationCommands(//
					@Meta(name = "sessionId", desc = "Session Identifier") String sessionId)//
					throws EQException;

	/**
	 * Returns all CMD records (commands) with 'SE' (Security) Category
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @return
	 * @throws EQException
	 *             Error encountered
	 */
	@MBeanOperation(value = "Gets and returns all command records")
	public List<V45Command> getSecurityCommands(//
					@Meta(name = "sessionId", desc = "Session Identifier") String sessionId)//
					throws EQException;

	/**
	 * Validates if the command can be run against the unit by checking on the conditions of the command.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param cmdRecord
	 *            CMD Record for command
	 * @param systemId
	 *            System Id
	 * @param unitId
	 *            Unit Id
	 * @return
	 * @throws EQException
	 *             Error encountered
	 * @throws UnitNotAvailableException
	 */
	@MBeanOperation(value = "Validates if command can be run on unit")
	public boolean isValidCommandForUnit(//
					@Meta(name = "sessionId", desc = "Session Identifier") String sessionId, //
					@Meta(name = "cmdRecord", desc = "CMDRecordDataModel of command") V45Command command, //
					@Meta(name = "systemId", desc = "system id") String systemId, //
					@Meta(name = "unitId", desc = "unit id") String unitId) //
					throws EQException, UnitNotAvailableException;

}
