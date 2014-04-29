package com.misys.equation.bankfusion.lrp.engine;

import java.util.HashMap;

import com.misys.equation.bankfusion.tools.LRPToolbox;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.trapedza.bankfusion.servercommon.core.BankFusionThreadLocal;

/**
 * This toolbox provides functionalities to BF task engines
 * 
 * @author yzobdabu
 * 
 */
public class TaskEngineToolbox
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TaskEngineToolbox.java 14401 2012-09-04 11:58:44Z williae1 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(TaskEngineToolbox.class);

	/** Equation ESF process address */
	private String esfDefaultProcessService = null;

	/** Equation Desktop Task type */
	public static final String BFEQ_TYPE = "EQD";

	/** Custom property name for the Equation desktop task type */
	public static final String BFEQ_TYPE_CP = "TYPE";

	/** Custom property name of task type - data entry, authorisation, esf */
	public static final String BFEQ_TASK_TYPE_CP = "TASKCUSTOMPROP1";

	/** Custom property name of the zone */
	public static final String BFEQ_TASK_ZONE_CP = "TASKCUSTOMPROP2";

	/** Custom property name of the option id */
	public static final String BFEQ_TASK_OID_CP = "TASKCUSTOMPROP3";

	/** Task type data entry */
	public static final String TASK_TYPE_DATA_ENTRY = "ENTRY";

	/** Task type authorisation */
	public static final String TASK_TYPE_AUTH = "AUTH";

	/** Task type ESF */
	public static final String TASK_TYPE_ESF = "ESF";

	/** Task action authorisation */
	public static final String TASK_ACTION_AUTH = "AUTH";

	/** Task action decline */
	public static final String TASK_ACTION_DECL = "DECL";

	/** Task action refer */
	public static final String TASK_ACTION_REFER = "REFER";

	/** Task action update */
	public static final String TASK_ACTION_UPDATE = "UPD";

	/** Task action review - for ESF */
	public static final String TASK_ACTION_REVIEW = "REVIEW";

	/** Task action authorised first warning - for ESF */
	public static final String TASK_ACTION_ONE = "ONE";

	/** Task action cancel authorisation - for ESF */
	public static final String TASK_ACTION_CANCEL = "CANCEL";

	/** Claim state */
	public static final String CLAIMED_STATE = "CLAIMED";

	/** Ready state */
	public static final String READY_STATE = "READY";

	/**
	 * Private constructor for the TaskEngineToolbox
	 */
	private TaskEngineToolbox()
	{
	}

	/**
	 * Static holder for the TaskEngineToolbox
	 * 
	 */
	private static class TaskEngineToolboxHolder
	{
		private static TaskEngineToolbox INSTANCE = new TaskEngineToolbox();
	}

	/**
	 * Returns the TaskEngineToolbox instance
	 * 
	 * @return the TaskEngineToolbox instance
	 */
	public static TaskEngineToolbox getInstance()
	{
		return TaskEngineToolboxHolder.INSTANCE;
	}

	/**
	 * Returns the LRP server location
	 * 
	 * @return the LRP server location
	 */
	public String getLRPServer() throws EQException
	{
		return "";
	}

	/**
	 * Returns the WPS Human Task Manager Web Service address
	 * 
	 * @return the WPS Human Task Manager Web Service address
	 */
	public String getHtmJaxWsService() throws EQException
	{
		return "";
	}

	/**
	 * Returns the BF Task Service address
	 * 
	 * @return the BF Task Service address
	 */
	public String getBfTaskService() throws EQException
	{
		return "";
	}

	/**
	 * Returns the BF Human Task Wrapper Service address
	 * 
	 * @return the BF Human Task Wrapper Service address
	 */
	public String getBfHumanTaskWrapperService() throws EQException
	{
		return "";
	}

	/**
	 * Returns the default ESF process address
	 * 
	 * @return the default ESF process address
	 */
	public String getEqDefaultEsfProcess() throws EQException
	{
		if (esfDefaultProcessService == null)
		{
			esfDefaultProcessService = EquationCommonContext.getConfigProperty("eq.lrp.ESFDefaultProcessService");

			if (esfDefaultProcessService == null)
			{
				throw new EQException("Failed to load eq.lrp.ESFDefaultProcessService from equationConfiguration.properties");
			}
		}
		return esfDefaultProcessService;
	}

	/**
	 * This method sets common input parameters when calling BF/WPS microflows
	 * 
	 * @param hashMap
	 */
	public void getWpsCommonInputParameters(HashMap<String, Object> hashMap) throws EQException
	{
	}

	/**
	 * Return the task list query with filtering
	 * 
	 * @param zone
	 *            - the zone filter
	 * @param filterTaskType
	 *            - the task type filter
	 * @param filterOID
	 *            - the option id filter
	 * @return the task list query with filtering
	 */
	public String getTaskListQuery(String zone, String filterTaskType, String filterOID)
	{
		// Equation type only
		StringBuilder query = new StringBuilder();
		query.append(BFEQ_TYPE_CP);
		query.append("=");
		query.append("'");
		query.append(BFEQ_TYPE);
		query.append("'");
		query.append(" ");

		// Zone
		if (zone != null)
		{
			query.append(" AND ");
			query.append(BFEQ_TASK_ZONE_CP);
			query.append("=");
			query.append("'");
			query.append(zone);
			query.append("'");
			query.append(" ");
		}

		// task type?
		if (filterTaskType != null)
		{
			query.append(" AND ");
			query.append(BFEQ_TASK_TYPE_CP);
			query.append("=");
			query.append("'");
			query.append(filterTaskType);
			query.append("'");
			query.append(" ");
		}

		// option Id?
		if (filterOID != null)
		{
			query.append(" AND ");
			query.append(BFEQ_TASK_OID_CP);
			query.append("=");
			query.append("'");
			query.append(filterOID);
			query.append("'");
			query.append(" ");
		}

		return query.toString();
	}

	/**
	 * Return the default task engine
	 * 
	 * @param sessionIdentifier
	 *            - the session id
	 * @param userLocator
	 *            - the user locator
	 * 
	 * @return the task engine
	 */
	public ITaskEngine getTaskEngine(String sessionIdentifier, String userLocator)
	{
		EquationUser equationUser = EquationCommonContext.getContext().getEquationUser(sessionIdentifier);
		EquationUnit equationUnit = equationUser.getEquationUnit();
		String zone = LRPToolbox.getTaskZone(equationUnit.getSystem().getSystemId(), equationUnit.getUnitId());

		ITaskEngine taskEngine = null;

		if (BankFusionThreadLocal.getBankFusionEnvironment() == null)
		{
			taskEngine = new BFMFTaskEngine(userLocator, zone);
		}
		else
		{
			taskEngine = new BFTaskEngine(zone);
		}

		return taskEngine;
	}

}
