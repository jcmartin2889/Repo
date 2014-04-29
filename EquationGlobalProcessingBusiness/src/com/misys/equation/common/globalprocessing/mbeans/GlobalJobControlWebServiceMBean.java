package com.misys.equation.common.globalprocessing.mbeans;

import java.util.Map;

import com.misys.equation.common.access.UnitNotAvailableException;
import com.misys.equation.common.globalprocessing.audit.SystemUnit;
import com.misys.equation.common.internal.eapi.core.EQActionErrorException;
import com.misys.equation.common.internal.eapi.core.EQException;

/**
 * Contains Global Job Control Service methods such as starting and stopping various jobs (applicator, unit monitor, transaction,
 * and cleanup).
 * <p>
 * This service will primarily be used by the Management Console through a JMX exposed service (See the EquationGlobalWeb project).
 * The stub interfaces are exposed here so that both client and server components refer to the common source.
 * <p>
 * The method names, parameters, and interface type are heavily annotated for automatic generation of DynamicMBean meta information.
 * 
 * @author berzosa
 */
@Meta(name = "GlobalJobControlWebServiceMBean", desc = "Provides a global job control service layer exposed via JMX")
public interface GlobalJobControlWebServiceMBean
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalJobControlWebServiceMBean.java 10044 2010-11-24 14:46:27Z berzosa $";

	/**
	 * Enumerates the available Data Propagation Job Status values.
	 */
	public enum DataPropagationJobStatus
	{
		RUNNING(false, true), // The job is running
		STARTING(false, false), // The job is initialising
		STOPPING(false, false), // The job is stopping
		STOPPED(true, false), // The job is not running
		SLEEPING(false, true), // The job is sleeping because there was nothing to do
		SUSPENDED(false, true), // The job is suspended
		UNKNOWN(false, false), // The state of the job is not known
		REFRESHING(false, false); // The job status is being refreshed

		// flags to indicate what you can do with a job
		private final boolean startable, stoppable;
		private DataPropagationJobStatus(boolean startable, boolean stoppable)
		{
			this.startable = startable;
			this.stoppable = stoppable;
		}

		public boolean isStartable()
		{
			return startable;
		}

		public boolean isStoppable()
		{
			return stoppable;
		}
	};

	/**
	 * Enumerates the available Data Propagation Job Types.
	 */
	public enum DataPropagationJobType
	{
		UNIT_MONITOR(false), // Unit Monitor
		APPLICATOR(false), // Applicator
		TXN_MONITOR(false), // Propagator
		CLEANUP(true); // Cleanup Job

		private final boolean scheduledCleanupJob;
		private DataPropagationJobType(boolean scheduledCleanupJob)
		{
			this.scheduledCleanupJob = scheduledCleanupJob;
		}

		public boolean isScheduledCleanupJob()
		{
			return scheduledCleanupJob;
		}
	}

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
	 * Starts the monitor job for the given system and unit.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The System ID on which the unit resides
	 * @param unitId
	 *            The Unit ID that the job is working on
	 * @throws UnitNotAvailableException
	 */
	@MBeanOperation("Starts the monitor job for the given system and unit.")
	public void startMonitorJob(@Meta(name = "sessionId", desc = "A valid session identifier") String sessionId, //
					@Meta(name = "systemId", desc = "The System ID on which the unit resides") String systemId, //
					@Meta(name = "unitId", desc = "The Unit ID that the job is working on") String unitId) throws EQException,
					UnitNotAvailableException;

	/**
	 * Stops the monitor job for the given system and unit.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The System ID on which the unit resides
	 * @param unitId
	 *            The Unit ID that the job is working on
	 */
	@MBeanOperation("Stops the monitor job for the given system and unit.")
	public void stopMonitorJob(@Meta(name = "sessionId", desc = "A valid session identifier") String sessionId, //
					@Meta(name = "systemId", desc = "The System ID on which the unit resides") String systemId, //
					@Meta(name = "unitId", desc = "The Unit ID that the job is working on") String unitId) throws EQException;

	/**
	 * Starts the applicator job for the given system and unit.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The System ID on which the unit resides
	 * @param unitId
	 *            The Unit ID that the job is working on
	 * @throws UnitNotAvailableException
	 */
	@MBeanOperation("Starts the applicator job for the given system and unit.")
	public void startApplicatorJob(@Meta(name = "sessionId", desc = "A valid session identifier") String sessionId, //
					@Meta(name = "systemId", desc = "The System ID on which the unit resides") String systemId, //
					@Meta(name = "unitId", desc = "The Unit ID that the job is working on") String unitId) throws EQException,
					UnitNotAvailableException;

	/**
	 * Stops the applicator job for the given system and unit.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The System ID on which the unit resides
	 * @param unitId
	 *            The Unit ID that the job is working on
	 */
	@MBeanOperation("Stops the applicator job for the given system and unit.")
	public void stopApplicatorJob(@Meta(name = "sessionId", desc = "A valid session identifier") String sessionId, //
					@Meta(name = "systemId", desc = "The System ID on which the unit resides") String systemId, //
					@Meta(name = "unitId", desc = "The Unit ID that the job is working on") String unitId) throws EQException;

	/**
	 * Starts the transaction unit monitor job for the given system and unit.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The System ID on which the unit resides
	 * @param unitId
	 *            The Unit ID that the job is working on
	 * @throws UnitNotAvailableException
	 */
	@MBeanOperation("Starts the transaction unit monitor job for the given system and unit.")
	public void startTransactionUnitMonitorJob(@Meta(name = "sessionId", desc = "A valid session identifier") String sessionId, //
					@Meta(name = "systemId", desc = "The System ID on which the unit resides") String systemId, //
					@Meta(name = "unitId", desc = "The Unit ID that the job is working on") String unitId) throws EQException,
					UnitNotAvailableException;

	/**
	 * Stops the transaction unit monitor job for the given system and unit.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The System ID on which the unit resides
	 * @param unitId
	 *            The Unit ID that the job is working on
	 */
	@MBeanOperation("Stops the transaction unit monitor job for the given system and unit.")
	public void stopTransactionUnitMonitorJob(@Meta(name = "sessionId", desc = "A valid session identifier") String sessionId, //
					@Meta(name = "systemId", desc = "The System ID on which the unit resides") String systemId, //
					@Meta(name = "unitId", desc = "The Unit ID that the job is working on") String unitId) throws EQException;

	/**
	 * Starts the cleanup job for the given system and unit.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The System ID on which the unit resides
	 * @param unitId
	 *            The Unit ID that the job is working on
	 * @throws UnitNotAvailableException
	 */
	@MBeanOperation("Starts the cleanup job for the given system and unit.")
	public void startCleanupJob(@Meta(name = "sessionId", desc = "A valid session identifier") String sessionId, //
					@Meta(name = "systemId", desc = "The System ID on which the unit resides") String systemId, //
					@Meta(name = "unitId", desc = "The Unit ID that the job is working on") String unitId) throws EQException,
					UnitNotAvailableException;

	/**
	 * Stops the cleanup job for the given system and unit.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The System ID on which the unit resides
	 * @param unitId
	 *            The Unit ID that the job is working on
	 */
	@MBeanOperation("Stops the cleanup job for the given system and unit.")
	public void stopCleanupJob(@Meta(name = "sessionId", desc = "A valid session identifier") String sessionId, //
					@Meta(name = "systemId", desc = "The System ID on which the unit resides") String systemId, //
					@Meta(name = "unitId", desc = "The Unit ID that the job is working on") String unitId) throws EQException;

	/**
	 * Retrieves the job status for all units for the given job type.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param jobType
	 *            The type of job to retrieve statuses of
	 */
	@MBeanOperation("Retrieves the job status for all units for the given job type.")
	public Map<SystemUnit, DataPropagationJobStatus> getJobStatuses(
					@Meta(name = "sessionId", desc = "A valid session identifier") String sessionId, //
					@Meta(name = "jobType", desc = "The type of job to retrieve statuses of") DataPropagationJobType jobType) //
					throws EQException;

	/**
	 * Retrieves the job status for a particular unit for the given job type.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param jobType
	 *            The type of job to retrieve statuses of
	 * @param systemId
	 *            The system ID on which the job is on
	 * @param systemId
	 *            The unit ID on which the job is on
	 */
	@MBeanOperation("Retrieves the job status for a particular unit for the given job type.")
	public DataPropagationJobStatus getJobStatus(@Meta(name = "sessionId", desc = "A valid session identifier") String sessionId, //
					@Meta(name = "jobType", desc = "The type of job to retrieve statuses of") DataPropagationJobType jobType,//
					@Meta(name = "systemId", desc = "The System ID on which the unit resides") String systemId, //
					@Meta(name = "unitId", desc = "The Unit ID that the job is working on") String unitId) throws EQException;

	/**
	 * Retrieves the remaining number of 'records to process' for the propagator job running on the given unit (GYPF last RRN minus
	 * GAWPF field value).
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The System ID on which the unit resides
	 * @param unitId
	 *            The Unit ID that the job is working on
	 */
	@MBeanOperation("Retrieves the remaining number of 'records to process' for the propagator.")
	public Integer getPropagatorRecordsToProcess(@Meta(name = "sessionId", desc = "A valid session identifier") String sessionId, //
					@Meta(name = "systemId", desc = "The System ID on which the unit resides") String systemId, //
					@Meta(name = "unitId", desc = "The Unit ID that the job is working on") String unitId) throws EQException,
					UnitNotAvailableException;

	/**
	 * Retrieves the remaining number of 'records to apply' for the applicator job running on the given unit (GAUSTS = ' ' - to be
	 * automatically applied).
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The System ID on which the unit resides
	 * @param unitId
	 *            The Unit ID that the job is working on
	 */
	@MBeanOperation("Retrieves the remaining number of 'records to apply' for the applicator.")
	public Integer getApplicatorRecordsToApply(@Meta(name = "sessionId", desc = "A valid session identifier") String sessionId, //
					@Meta(name = "systemId", desc = "The System ID on which the unit resides") String systemId, //
					@Meta(name = "unitId", desc = "The Unit ID that the job is working on") String unitId) throws EQException,
					UnitNotAvailableException;

	/**
	 * Retrieves the remaining number of 'records to copy to the global layer' for the unit monitor job running on the given unit
	 * ((number of KWRKGAUPF records not sent to the global layer).
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param systemId
	 *            The System ID on which the unit resides
	 * @param unitId
	 *            The Unit ID that the job is working on
	 */
	@MBeanOperation("Retrieves the remaining number of 'records to copy to the global layer'.")
	public Integer getUnitMonitorRecordsToCopy(@Meta(name = "sessionId", desc = "A valid session identifier") String sessionId, //
					@Meta(name = "systemId", desc = "The System ID on which the unit resides") String systemId, //
					@Meta(name = "unitId", desc = "The Unit ID that the job is working on") String unitId) throws EQException,
					UnitNotAvailableException;

	/**
	 * Starts a job for the given system and unit.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param jobType
	 *            The type of job to start
	 * @param systemId
	 *            The System ID on which the unit resides
	 * @param unitId
	 *            The Unit ID that the job is working on
	 * @throws UnitNotAvailableException
	 */
	@MBeanOperation("Starts a job for the given system and unit.")
	public void startJob(@Meta(name = "sessionId", desc = "A valid session identifier") String sessionId, //
					@Meta(name = "jobType", desc = "The type of job to start") DataPropagationJobType jobType, //
					@Meta(name = "systemId", desc = "The System ID on which the unit resides") String systemId, //
					@Meta(name = "unitId", desc = "The Unit ID that the job is working on") String unitId) throws EQException,
					UnitNotAvailableException;

	/**
	 * Stops a job for the given system and unit.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param jobType
	 *            The type of job to stop
	 * @param systemId
	 *            The System ID on which the unit resides
	 * @param unitId
	 *            The Unit ID that the job is working on
	 */
	@MBeanOperation("Stops a job for the given system and unit.")
	public void stopJob(@Meta(name = "sessionId", desc = "A valid session identifier") String sessionId, //
					@Meta(name = "jobType", desc = "The type of job to stop") DataPropagationJobType jobType, //
					@Meta(name = "systemId", desc = "The System ID on which the unit resides") String systemId, //
					@Meta(name = "unitId", desc = "The Unit ID that the job is working on") String unitId) throws EQException;

	/**
	 * Clears the cached objects from the server.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 */
	@MBeanOperation("Clears the cached objectss from the server.")
	public void resetCache(@Meta(name = "sessionId", desc = "A valid session identifier") String sessionId) throws EQException;
}
