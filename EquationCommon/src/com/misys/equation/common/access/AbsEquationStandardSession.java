package com.misys.equation.common.access;

import java.beans.PropertyVetoException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.naming.NamingException;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400JDBCConnection;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.BidiStringType;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.MessageFile;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.QSYSObjectPathName;
import com.misys.equation.common.dao.beans.HBRecordDataModel;
import com.misys.equation.common.datastructure.EqDS_DSJOBE;
import com.misys.equation.common.datastructure.EqDS_SVJOB4;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.internal.eapi.core.EQSessionProperties;
import com.misys.equation.common.internal.eapi.core.EQXAException;
import com.misys.equation.common.language.LanguageResources;
import com.misys.equation.common.utilities.CRMLimitCheckData;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EqJobWatcher;
import com.misys.equation.common.utilities.EqTimingTest;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;

public abstract class AbsEquationStandardSession implements EquationStandardSession
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbsEquationStandardSession.java 17621 2013-11-27 02:50:04Z williae1 $";

	/** Logger for this class */
	private static final EquationLogger LOG = new EquationLogger(AbsEquationStandardSession.class);

	protected abstract Statement getEqStatement(EQStatementType ccApiCaller) throws EQException;

	protected abstract EquationStandardTransaction callX56HMR(EquationStandardTransaction transaction, String mode)
					throws EQException;

	protected abstract EQMessage updateGH(String mode, String workStation, String userId, int jobNumber, String sessionId,
					String optionId, String program, String cc, String app, String logUser, String tcpIp) throws EQException;

	private static final int PARAM_EQTXM_CMD = 1;
	private static final int PARAM_EQTXM_CALLSTATUS = 2;
	private static final int PARAM_EQTXM_MESSAGE_ID = 3;
	private static final int PARAM_EQTXM_MESSAGE_TEXT = 4;
	private static final String CMD_COMMIT = "COMMIT    ";
	private static final String CMD_END_TRANS = "USERBNDOFF";
	private static final String CMD_RESET_JOB = "RESETJOB  ";
	private static final String CMD_ROLLBACK = "ROLLBACK  ";
	private static final String CMD_START_TRANS = "USERBNDON ";
	private static final int PATH_LIBRARY = 1;
	private static final int PATH_FULL = 2;
	private static final String[] VALID_KSM_PREFIXES = { "DSM", "KSM", "USM" };

	public static final String GZREC_SUCCESS = "R";
	public static final String GZREC_WARNING = "W";
	public static final String GZREC_TRANS_FWD_TELLER = "S";
	public static final String GZREC_FAILURE = "F";

	protected enum EQStatementType
	{
		CC_API_CALLER, TRANSACTION_API_CALLER, ENQUIRY_API_CALLER, VALIDATE_API_CALLER, MULTIPLE_VALIDATE_API_CALLER, SUPERVISOR_CALLER, GH_UPDATE_CALLER, RETRIEVE_DATAAREA, RETRIEVE_OBJECT_PATH, CRM_LIMIT_API_CALLED, USER_INTERBRANCH_AUTH, PV_METADATA_API_CALLER, UTW58R, BASIC_TRANSACTION_API_CALLER, GET_OBJECT_LIBRARY_CALLER, QUICK_COMMAND, ENHANCEMENT_CHECKER_CALLER, SET_DATAAREA
	};

	// A command that will execute quickly.
	// This command will be executed when the connection is validated
	protected static final String CMD_DUMMY_QUICK = "CHKOBJ OBJ(QSYS/QSYS) OBJTYPE(*LIB)";

	private static final String UTW58R_MODE_TOKEN = "TKN";
	private static final int USER_TOKEN_LENGTH = 32;

	/** This is the connection related to the current session. */
	protected EquationConnectionWrapper connectionWrapper;

	protected final Map<String, EquationDataStructure> equationDataStructures = new HashMap<String, EquationDataStructure>();
	protected final List<EquationStandardTransaction> equationTransactions = new ArrayList<EquationStandardTransaction>();

	// Consider impact on memory footprint before adding Statements as instance variables.
	private CallableStatement equationValidateAPICaller;
	private CallableStatement equationEnquiryAPICaller;
	private CallableStatement getObjectLibraryCaller;
	private CallableStatement equationCRMLimitAPICalled;
	private CallableStatement equationPVMetaDataAPICaller;
	private CallableStatement equationCCAPICaller;
	private PreparedStatement quickCommand;

	protected CallableStatement equationTransactionAPICaller;
	protected CallableStatement equationGHUpdateCaller;

	protected String userId;
	protected String branchId;

	private final String systemId;
	private final String unitId;
	private final int ccsid;
	private final String language;
	private final EQSessionProperties properties;
	private String ksmmsgfPath;
	private String sessionIdentifier;
	private final Lock sessionLock = new ReentrantLock();
	private ArrayList<String> breakMessages = new ArrayList<String>();

	protected final EquationUnit unit;

	/**
	 * Construct an Equation Standard Session using the connection from the user
	 * 
	 * @param user
	 *            - the Equation user
	 */
	protected AbsEquationStandardSession(EquationUser user, EquationConnectionWrapper connectionWrapper) throws EQException
	{
		this.unit = user.getEquationUnit();
		this.connectionWrapper = connectionWrapper;
		this.branchId = user.getInputBranch();
		this.systemId = user.getEquationUnit().getSystem().getSystemId();
		this.unitId = unit.getUnitId();
		this.userId = user.getUserId();
		this.language = user.getLanguageId();
		this.properties = unit.getEquationSessionPool().getSessionProperties();
		this.ccsid = connectionWrapper.getCCSID();
		commonInitialisation();
	}

	/**
	 * Construct an Equation Standard Session using the connection from the unit's Equation session pool
	 * 
	 * @param unit
	 *            - the Equation unit
	 */
	protected AbsEquationStandardSession(EquationUnit unit) throws EQException
	{
		Connection connection = unit.getEquationSessionPool().getConnection(unit.getPoolUser().getUserId());
		this.connectionWrapper = new EquationConnectionWrapper(connection, unit.getEquationSessionPool(), false, false);
		this.branchId = unit.getPoolUser().getInputBranch();
		this.systemId = unit.getSystem().getSystemId();
		this.unitId = unit.getUnitId();
		this.userId = unit.getPoolUser().getUserId();
		this.language = unit.getPoolUser().getLanguageId();
		this.properties = unit.getEquationSessionPool().getSessionProperties();
		this.ccsid = connectionWrapper.getCCSID();
		this.unit = unit;
		commonInitialisation();
	}
	/**
	 * Common initialisation for the constructors
	 */
	private void commonInitialisation() throws EQException
	{
		// Retrieve the library where KSM is located
		if (unit.isDevelopmentUnit())
		{
			ksmmsgfPath = getObjectPath("KSMMSGF", "*MSGF", "*LIBL", PATH_FULL);
		}

		// check if already been cache
		else
		{
			String language = this.language.trim();
			ksmmsgfPath = unit.getKsmMessageFilePath(language);
			if (ksmmsgfPath == null)
			{
				ksmmsgfPath = getObjectPath("KSMMSGF", "*MSGF", "*LIBL", PATH_FULL);
				unit.addksmMessageFilePath(language, ksmmsgfPath);
			}
		}
	}

	/**
	 * Return the AS400 connection
	 */
	public Connection getConnection()
	{
		Connection connection = connectionWrapper.getConnection();
		return connection;
	}

	/**
	 * Close the Statement instance variables
	 */
	public void closeStatements()
	{
		// Close Callable Statements
		SQLToolbox.close(equationCCAPICaller);
		SQLToolbox.close(equationTransactionAPICaller);
		SQLToolbox.close(equationEnquiryAPICaller);
		SQLToolbox.close(equationValidateAPICaller);
		SQLToolbox.close(equationGHUpdateCaller);
		SQLToolbox.close(equationCRMLimitAPICalled);
		SQLToolbox.close(equationPVMetaDataAPICaller);
		SQLToolbox.close(getObjectLibraryCaller);
		SQLToolbox.close(quickCommand);
	}

	/**
	 * This method will initialise the transaction.
	 * <ul>
	 * <li>The <code>EQTXM</code> stored procedure will be called to initialise the transaction in RPG back-end</li>
	 * </ul>
	 * 
	 * @return true if the transaction was initialised successfully.<br>
	 *         Otherwise false will be returned or an <code>EQException</code> will be thrown.
	 * @throws EQException
	 *             if there is an <code>EQException</code> will be thrown.
	 */
	public boolean startEquationTransaction() throws EQException
	{
		LOG.debug("startEquationTransaction - entry");
		try
		{
			callCCAPI(CMD_START_TRANS);
			return true;
		}
		catch (SQLException sQLException)
		{
			LOG.error(sQLException);
			throw new EQException(sQLException);
		}
		finally
		{
			LOG.debug("startEquationTransaction - exit");
		}
	}

	/**
	 * This method will end the transaction and clear the transaction list.
	 * <ul>
	 * <li>The <code>EQTXM</code> stored procedure will be called to end the initialise the transaction in RPG back-end</li>
	 * </ul>
	 * 
	 * @return true if the transaction was ended successfully.<br>
	 *         Otherwise false will be returned or an <code>EQException</code> will be thrown.
	 * @throws EQException
	 *             if there is an <code>EQException</code> will be thrown.
	 */
	public boolean endEquationTransaction() throws EQException
	{
		LOG.debug("endEquationTransaction - entry");
		try
		{
			// Clear the transactions collections
			callCCAPI(CMD_END_TRANS);
			equationTransactions.clear();
			return true;
		}
		catch (SQLException sqle)
		{
			LOG.error(sqle);
			throw new EQException("EquationStandardSession - endEquationTransaction failed: " + Toolbox.getExceptionMessage(sqle),
							sqle);
		}
		finally
		{
			LOG.debug("endEquationTransaction - exit");
		}
	}

	/**
	 * This method will roll-back all associated transactions.
	 * 
	 * @return true if the transactions were roll-backed successfully.<br>
	 *         Otherwise false will be returned or an <code>EQException</code> will be thrown.
	 * @throws EQException
	 *             if there is an <code>EQException</code> will be thrown.
	 */
	public boolean rollbackTransactions() throws EQException
	{
		try
		{
			LOG.warn("rollbackTransactions - executing");
			rollback();
			return true;
		}
		catch (Exception e)
		{
			// If exception is an EQXAException then don't create a new exception.
			if (e instanceof EQXAException)
			{
				throw (EQXAException) e;
			}
			else
			{
				LOG.error(e);
				if (e instanceof EQException)
				{
					throw (EQException) e;
				}
				else
				{
					throw new EQException("rollbackTransactions failed: " + Toolbox.getExceptionMessage(e), e);
				}
			}
		}
	}

	/**
	 * This method will commit the transaction/s associated to the current session.<br>
	 * It will be done only if the transaction/s was in initialised using <code>startEquationTransaction()</code>.
	 * <ul>
	 * <li>The session's database connection will be committed</li>
	 * <li>The <code>EQTXM</code> store procedure will called to fire the transaction/s in the RPG back-end</li>
	 * </ul>
	 * 
	 * @return true if the session was committed, in other case false will be returned.
	 * 
	 * @throws EQException
	 *             if the transaction level set in the properties is different to EQ transaction a <code>EQException</code> will be
	 *             thrown.
	 */
	public boolean commitTransactions() throws EQException
	{
		try
		{
			LOG.debug("commitTransaction - executing");
			commit();
			return true;
		}
		catch (SQLException sqle)
		{
			throw new EQException("EquationStandardSession - commitTransactions failed: " + Toolbox.getExceptionMessage(sqle), sqle);
		}
	}

	/**
	 * This method will commit the transaction associated to the current session.<br>
	 * It will be done only if the transaction was in initialised using <code>startEquationTransaction()</code>.
	 * <ul>
	 * <li>The session's database connection will be committed</li>
	 * <li>The <code>EQTXM</code> store procedure will called to fire the transaction in the RPG back-end</li>
	 * </ul>
	 * 
	 * @return true if the session was committed, in other case false will be returned.
	 * 
	 * @throws EQException
	 *             if the transaction level set in the properties is different to EQ transaction a <code>EQException</code> will be
	 *             thrown.
	 */
	public boolean commitTransaction() throws EQException
	{
		return commitTransactions();
	}

	/**
	 * Add an <code>EquationStandardTransaction</code> to the session
	 * 
	 * @param transaction
	 *            - the transaction to add
	 */
	public EquationStandardTransaction addEquationTransaction(EquationStandardTransaction transaction)
	{
		equationTransactions.add(transaction);
		return transaction;
	}

	/**
	 * Remove an <code>EquationStandardTransaction</code> from the session
	 * 
	 * @param transaction
	 *            - the transaction to remove
	 * @return true if this transaction was removed
	 */
	public boolean removeEquationTransaction(EquationStandardTransaction transaction)
	{
		// Take the transaction out of the collection
		return (equationTransactions.remove(transaction));
	}

	/**
	 * This method will commit the transaction associated to the current session.<br>
	 * <ul>
	 * <li>The session's database connection will be committed</li>
	 * <li>The <code>EQTXM</code> store procedure will called to fire the transaction in the RPG back-end</li>
	 * </ul>
	 * 
	 * @throws SQLException
	 *             if there is an error a <code>SQLException</code> will be thrown.
	 * @throws EQException
	 *             if the transaction level set in the properties is different to EQ transaction a <code>EQException</code> will be
	 *             thrown.
	 */
	private void commit() throws SQLException, EQException
	{
		// we assume that Equation will set USRBNDOFF
		callCCAPI(CMD_COMMIT);
		connectionWrapper.commit();
	}

	/**
	 * Commitment control - rollback transaction.
	 * 
	 * @throws SQLException
	 * @throws EQException
	 * @throws IllegalStateException
	 * @throws NamingException
	 */
	private void rollback() throws SQLException, EQException, IllegalStateException, NamingException
	{
		// Rollback the Equation activation group work
		callCCAPI(CMD_ROLLBACK);

		// TODO: Investigate why EQSessionProperties.TRANSACTION_ISOLATION_DEFAULT_AND_EQUATION not working
		// Rollback any JDBC connection work we may have done
		connectionWrapper.rollback();
	}

	/**
	 * Implement the call to the CC API stored procedure returns an exception if the call fails, null otherwise
	 * 
	 * @param equationConnection
	 * @param cmd
	 * @return
	 * @throws SQLException
	 * @throws EQException
	 */
	private String callCCAPI(String cmd) throws SQLException, EQException
	{
		if (equationCCAPICaller == null)
		{
			equationCCAPICaller = (CallableStatement) getEqStatement(EQStatementType.CC_API_CALLER);
		}
		equationCCAPICaller.setString(PARAM_EQTXM_CMD, cmd);
		equationCCAPICaller.registerOutParameter(PARAM_EQTXM_CALLSTATUS, java.sql.Types.CHAR);
		equationCCAPICaller.registerOutParameter(PARAM_EQTXM_MESSAGE_ID, java.sql.Types.CHAR);
		equationCCAPICaller.registerOutParameter(PARAM_EQTXM_MESSAGE_TEXT, java.sql.Types.CHAR);
		// Execute the call statement
		equationCCAPICaller.execute();
		String strCallStatus = equationCCAPICaller.getString(PARAM_EQTXM_CALLSTATUS).trim();
		String strMessageId = null;
		String strMessageText = null;
		strMessageText = equationCCAPICaller.getString(PARAM_EQTXM_MESSAGE_TEXT);
		if (!strCallStatus.equals("0"))
		{
			strMessageId = equationCCAPICaller.getString(PARAM_EQTXM_MESSAGE_ID);
			String msg = cmd + " failed. " + strMessageId + " " + strMessageText;
			throw new EQException(msg);
		}
		return strMessageText;
	}

	/**
	 * Apply a specific transaction in validate mode
	 * 
	 * @param transaction
	 *            - the transaction to apply
	 */
	public EquationStandardTransaction validateEquationTransaction(EquationStandardTransaction transaction) throws EQException
	{
		transaction = callX56HMR(transaction, IEquationStandardObject.FCT_VAL);
		return transaction;
	}

	/**
	 * Apply a specific transaction in retrieve mode
	 * 
	 * @param transaction
	 *            - the transaction to apply
	 */
	public EquationStandardTransaction retrieveEquationTransaction(EquationStandardTransaction transaction) throws EQException
	{
		transaction = callX56HMR(transaction, IEquationStandardObject.FCT_RTV);
		return transaction;
	}

	/**
	 * Apply a specific transaction
	 * 
	 * @param transaction
	 *            - the transaction to apply
	 */
	public EquationStandardTransaction applyEquationTransaction(EquationStandardTransaction transaction) throws EQException
	{
		transaction = callX56HMR(transaction, "");
		return transaction;
	}

	/**
	 * Return an EqJobWatcher related to the Job used for processing SQL statements
	 * 
	 * @return an EqJobWatcher
	 */
	public EqJobWatcher getConnectionJobWatcher() throws EQException
	{
		String jobId = connectionWrapper.getJobId();
		String jobName = jobId.substring(0, 10);
		String jobUser = jobId.substring(10, 20);
		String jobNumber = jobId.substring(20, 26);
		EqJobWatcher jobWatcher = new EqJobWatcher(unit, jobName, jobUser, jobNumber);
		return jobWatcher;
	}

	/**
	 * Execute lightweight command to ensure connection is valid
	 * 
	 * @return true if system ok or false if an exception was thrown
	 */
	public boolean isConnectionValid()
	{
		try
		{
			if (quickCommand == null)
			{
				quickCommand = (PreparedStatement) getEqStatement(EQStatementType.QUICK_COMMAND);
			}
			quickCommand.execute();
			return true;
		}
		catch (SQLException e)
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug(e);
			}
			return false;
		}
		catch (EQException e)
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug(e);
			}
			return false;
		}
	}

	/**
	 * @return the session identifier
	 */
	public String getSessionIdentifier()
	{
		return sessionIdentifier;
	}

	/**
	 * Set the session identifier
	 * 
	 * @param sessionIdentifier
	 */
	public void setSessionIdentifier(String sessionIdentifier)
	{
		this.sessionIdentifier = sessionIdentifier;
	}

	/**
	 * @return the absolute path to the KSMMSGF
	 */
	public String getKsmmsgfPath()
	{
		return ksmmsgfPath;
	}

	/**
	 * This method will be executed by the VM and will check if the current instance still holds resources.<br>
	 * Given that the current instance is no longer used all resources have to be closed.<br>
	 * 
	 * Before an object is garbage collected, the runtime system calls its finalize() method. The intent is for finalize() to
	 * release system resources such as open files or open sockets before getting collected.
	 */
	@Override
	protected void finalize() throws Throwable
	{
		try
		{
			// close resources.
			closeConnection();
		}
		finally
		{
			super.finalize();
		}
	}

	/**
	 * This method will close the current connection if that one was not closed before.<br>
	 * When connection is closed it means that this one will be returned to the pool.
	 */
	public void closeConnection()
	{
		if (connectionWrapper != null && (!connectionWrapper.isClosed() && (!connectionWrapper.isXA())))
		{
			try
			{
				closeStatements();
				Connection connection = connectionWrapper.getConnection();
				unit.getEquationSessionPool().returnConnnection(connectionWrapper);
				if (connection instanceof AS400JDBCConnection)
				{
					AS400JDBCConnection as400JDBC = (AS400JDBCConnection) connection;
					as400JDBC.getSystem().disconnectAllServices();
				}
				connectionWrapper = null;
			}
			catch (EQException eqException)
			{
				LOG.error("There was an error trying to close the connection.", eqException);
			}
		}
	}

	/**
	 * Reset the connection for next XA transaction.
	 * <P>
	 * Reset of the connection should only be necessary for XA connections.
	 * <P>
	 * 
	 * @throws SQLException
	 *             if a SQL error occurs.
	 * @throws EQException
	 *             if called in autoCommit mode, or if startTrans has not been called previously.
	 */
	public void reset() throws SQLException, EQException
	{
		if (properties.getTransactionIsolationLevel() != EQSessionProperties.TRANSACTION_ISOLATION_XA)
		{
			throw new EQException("EQSession: resetJob is only appropriate for XA connections");
		}
		callCCAPI(CMD_RESET_JOB);
	}

	/**
	 * @return the ccsid of the session
	 */
	public int getCcsid()
	{
		return ccsid;
	}

	/**
	 * @return the unit
	 */
	public EquationUnit getUnit()
	{
		return unit;
	}

	/**
	 * @return the <code>EquationConnectionWrapper</code> object
	 */
	public EquationConnectionWrapper getConnectionWrapper()
	{
		return connectionWrapper;
	}

	/**
	 * This method will return an <code>LinkedHashMap</code> that contains all KSM messages.
	 * 
	 * @return array that contains all KSM messages.
	 */
	public Map<String, String> getKsmmsgs() throws EQException
	{
		return getKsmmsgs(99999, "<=");
	}

	/**
	 * This method will return an <code>LinkedHashMap</code> that contains all KSM messages with the specified severity
	 * 
	 * @param severity
	 *            - the severity
	 * @param comparator
	 *            - comparison operator <=, ==, >=
	 * 
	 * @return array that contains all KSM messages with the specified severity
	 */
	public Map<String, String> getKsmmsgs(int severity, String comparator) throws EQException
	{
		Map<String, String> messages = null;
		AS400 as400 = null;
		try
		{
			as400 = unit.getEquationSystem().getAS400();
			MessageFile messageFile = new MessageFile(as400, ksmmsgfPath);
			AS400Message msg = messageFile.getMessage(MessageFile.FIRST);
			messages = new LinkedHashMap<String, String>();
			while (msg != null)
			{
				boolean add;
				add = comparator.equals("<=") && msg.getSeverity() <= severity;
				if (!add)
				{
					add = comparator.equals("==") && msg.getSeverity() == severity;
				}
				if (!add)
				{
					add = comparator.equals(">=") && msg.getSeverity() >= severity;
				}
				if (add)
				{
					String currentMessage = Toolbox.removeControlCharacter(msg.getText());
					messages.put(msg.getID(), currentMessage);
				}
				msg = messageFile.getMessage(MessageFile.NEXT);
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		finally
		{
			if (as400 != null)
			{
				unit.getEquationSystem().returnAS400(as400);
			}
		}
		return messages;
	}

	/**
	 * Return the data structure for a file
	 * 
	 * @param formatName
	 *            - the file name
	 * @return the data structure of the file
	 * 
	 * @throws EQException
	 */
	public EquationDataStructure getEquationDataStructure(String formatName) throws EQException
	{
		if (equationDataStructures.containsKey(formatName))
		{
			return equationDataStructures.get(formatName);
		}
		else
		{
			EquationDataStructure equationDataStructure = new EquationDataStructure(formatName, this);
			equationDataStructures.put(formatName, equationDataStructure);
			return equationDataStructure;
		}
	}

	/**
	 * Remove from data structure cache
	 * 
	 * @param formatName
	 *            - the format name to be removed
	 * 
	 * @return the deleted data structure
	 */
	public EquationDataStructure removeEquationDataStructure(String formatName)
	{
		return equationDataStructures.remove(formatName);
	}

	/**
	 * @return the branch mnemonic for the session defaulted from the user or the session pool depending on the constructor, this
	 *         value can be over-riden using the setter method
	 */
	public String getBranchId()
	{
		return branchId;
	}

	/**
	 * @return the system name
	 */
	public String getSystemId()
	{
		return systemId;
	}

	/**
	 * @return the 3 character unit identifier
	 */
	public String getUnitId()
	{
		return unitId;
	}

	/**
	 * @return the user identifier
	 */
	public String getUserId()
	{
		return userId;
	}
	/**
	 * Set the user id. This method should only be used when using pooled connections.
	 * 
	 * @param userId
	 *            - the user id
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	/**
	 * @return the Equation user identifier
	 */
	public String getEquationUserId()
	{
		return Toolbox.trim(userId, 4);
	}

	/**
	 * @param branchId
	 *            to be used when calling the APIs
	 */
	public void setBranchId(String branchId)
	{
		this.branchId = branchId;
	}

	/**
	 * This method will commit the transaction associated to the current session.<br>
	 * <ul>
	 * <li>The session's database connection will be committed</li>
	 * 
	 * @throws SQLException
	 *             , EQException
	 */
	public void connectionCommit() throws SQLException, EQException
	{
		connectionWrapper.commit();
	}

	/**
	 * This method will rollback the transaction associated to the current session.<br>
	 * <ul>
	 * <li>The session's database connection will be rolled back.</li>
	 * 
	 * @throws SQLException
	 *             , EQException, IllegalStateException, NamingException
	 */
	public void connectionRollback() throws SQLException, EQException, IllegalStateException, NamingException
	{
		try
		{
			connectionWrapper.rollback();
		}
		catch (Exception e)
		{
			throw new RuntimeException("EquationStandardSession: connectionRollback: " + Toolbox.getExceptionMessage(e), e);
		}
	}

	/**
	 * Retrieve the user's DAJOBCTLE
	 * 
	 * @return the DAJOBCTLE data structure data
	 * 
	 * @throws EQException
	 */
	public EquationDataStructureData getDajobctle() throws EQException
	{
		byte[] data = callUTW52R("DAJOBCTLE");

		EquationDataStructureData dsData = new EquationDataStructureData(new EqDS_DSJOBE());
		dsData.setBytes(data);

		// as of now, return null as errors in the program will be ignored
		return dsData;
	}

	/**
	 * Retrieve the user's DAWSIDCTXT
	 * 
	 * @return the DAWSIDCTXT data structure data
	 * 
	 * @throws EQException
	 */
	public EquationDataStructureData getDawsidctxt() throws EQException
	{
		byte[] data = callUTW52R("DAWSIDCTXT");

		EquationDataStructureData dsData = new EquationDataStructureData(new EqDS_DSJOBE());
		dsData.setBytes(data);

		// as of now, return null as errors in the program will be ignored
		return dsData;
	}

	/**
	 * Retrieve the user's DAWSIDCTX2
	 * 
	 * @return the DAWSIDCTX2 data structure data
	 * 
	 * @throws EQException
	 */
	public EquationDataStructureData getDawsidctx2() throws EQException
	{
		byte[] data = callUTW52R("DAJOBCTLE");

		EquationDataStructureData dsData = new EquationDataStructureData(new EqDS_DSJOBE());
		dsData.setBytes(data);

		// as of now, return null as errors in the program will be ignored
		return dsData;
	}

	/**
	 * Retrieve the job's SVJOB4
	 * 
	 * @return the SVJOB4 data structure data
	 * 
	 * @throws EQException
	 */
	public EquationDataStructureData getSVJOB4EX() throws EQException
	{
		byte[] data = callUTW52R("SVJOB4EX");

		EquationDataStructureData dsData = new EquationDataStructureData(new EqDS_SVJOB4());
		dsData.setBytes(data);

		// as of now, return null as errors in the program will be ignored
		return dsData;
	}

	/**
	 * Retrieve the object path
	 * 
	 * @param objName
	 *            - the object name
	 * @param objType
	 *            - the type of object
	 * @param objLib
	 *            - the object library
	 * @param type
	 *            - full path or library name
	 * 
	 * @return the returned object library or the object path
	 * 
	 * @throws EQException
	 */
	public String getObjectPath(String objName, String objType, String objLib, int type) throws EQException
	{
		if (objLib.trim().equals("*LIBL"))
		{
			return callGetLib(objName, objType, type);
		}
		else
		{
			return callUTW53C(objName, objType, objLib, type);
		}
	}

	/**
	 * Retrieve the object path
	 * 
	 * @param objName
	 *            - the object name
	 * @param objType
	 *            - the object type
	 * @param objLib
	 *            - the object library
	 * @param retVal
	 *            - required return value 1-object library 2-object path
	 * 
	 * @return the returned object library or the object path
	 * 
	 * @throws EQException
	 */
	private String callUTW53C(String objName, String objType, String objLib, int retVal) throws EQException
	{
		CallableStatement equationRetrieveObjectPath = null;
		try
		{
			equationRetrieveObjectPath = (CallableStatement) getEqStatement(EQStatementType.RETRIEVE_OBJECT_PATH);
			// Register output parameters
			equationRetrieveObjectPath.registerOutParameter(4, Types.CHAR);
			equationRetrieveObjectPath.registerOutParameter(5, Types.CHAR);

			// Set the parameters
			equationRetrieveObjectPath.setString(1, objName);
			equationRetrieveObjectPath.setString(2, objType);
			equationRetrieveObjectPath.setString(3, objLib);

			// Call the API
			equationRetrieveObjectPath.execute();

			// Retrieve the returned values
			if (retVal == PATH_LIBRARY)
			{
				return equationRetrieveObjectPath.getString(4).trim();
			}
			else
			{
				return equationRetrieveObjectPath.getString(5).trim();
			}
		}
		catch (SQLException e)
		{
			throw new EQException(LanguageResources.getFormattedString("EquationStandardSession.ErrorCallingProgram", "UTW53C")
							+ ": " + Toolbox.getExceptionMessage(e));
		}
		finally
		{
			SQLToolbox.close(equationRetrieveObjectPath);
		}
	}

	/**
	 * Retrieve the object path
	 * 
	 * @param objName
	 *            - the object name
	 * @param objType
	 *            - the object type
	 * @param retVal
	 *            - required return value 1-object library 2-object path
	 * 
	 * @return the returned object library
	 * 
	 * @throws EQException
	 */
	private String callGetLib(String objName, String objType, int retVal) throws EQException
	{
		try
		{
			if (getObjectLibraryCaller == null)
			{
				getObjectLibraryCaller = (CallableStatement) getEqStatement(EQStatementType.GET_OBJECT_LIBRARY_CALLER);
			}
			// Register output parameters
			getObjectLibraryCaller.registerOutParameter(1, Types.VARCHAR);
			getObjectLibraryCaller.registerOutParameter(2, Types.VARCHAR);
			getObjectLibraryCaller.registerOutParameter(3, Types.BINARY);

			// Set the parameters
			getObjectLibraryCaller.setString(1, objName);
			getObjectLibraryCaller.setString(2, objType);

			// Call the API
			getObjectLibraryCaller.execute();
			byte[] libBytes = getObjectLibraryCaller.getBytes(3);
			String libString = Toolbox.convertAS400TextIntoCCSID(libBytes, 10, ccsid).trim();
			QSYSObjectPathName path = new QSYSObjectPathName(libString, objName, objType.replace("*", ""));
			// Retrieve the returned values
			if (retVal == PATH_LIBRARY)
			{
				return libString;
			}
			else
			{
				return path.getPath();
			}
		}
		catch (SQLException e)
		{
			throw new EQException(LanguageResources.getFormattedString("EquationStandardSession.ErrorCallingProgram", "GETLIB")
							+ ": " + Toolbox.getExceptionMessage(e));
		}
	}

	/**
	 * Check whether an iSeries object exists
	 * 
	 * @param objectName
	 *            - the object name
	 * @param objectType
	 *            - the type of object
	 * @param library
	 *            - the library (can be "*LIBL")
	 * 
	 * @return a boolean indicating whether the object exists
	 * 
	 * @throws EQException
	 */
	public boolean doesObjectExist(String objectName, String objectType, String library) throws EQException
	{
		return callUTW53C(objectName, objectType, library, PATH_FULL).length() > 0;
	}

	/**
	 * Determine if user has inter-branch authority
	 * 
	 * @param userId
	 *            - the user Id
	 * @param defBranchNo
	 *            - the user's default branch number
	 * 
	 * @return true if user has inter-branch authority
	 * 
	 * @throws EQException
	 */
	public boolean isUserInterBranchAllowed(String userId, String defBranchNo) throws EQException
	{
		return callUTW57R(userId, defBranchNo);
	}

	/**
	 * Perform CRM limit check
	 * 
	 * @param dshh03
	 *            - the DSHH03 data
	 * 
	 * @return the CRM limit check data
	 * 
	 * @throws EQException
	 */
	public CRMLimitCheckData checkCRMLimit(EquationDataStructureData dshh03) throws EQException
	{
		return callUTW54R(dshh03);
	}

	/**
	 * Apply the transactions currently assigned to the session
	 * 
	 * @return true if successful otherwise false
	 * @throws EQException
	 */
	public boolean applyTransactions() throws EQException
	{
		EquationConnectionWrapper originalConnectionWrapper = null;
		try
		{
			// It is assumed that Transaction Management will never start here.
			// The calling application needs to implement Transaction Management.

			// Save the current connection and create and XA connection
			if (unit.getXaDataSource() != null && !connectionWrapper.isXA())
			{
				originalConnectionWrapper = connectionWrapper;

				// Change the connection to an XA connection if in XA mode
				EquationLogin login = EquationCommonContext.getContext().getEquationLogin(getSessionIdentifier());
				// Login user may not be the same as the real Equation user.
				EquationConnectionWrapper xaConnectionWrapper = unit.getEquationSessionPool().getXAConnection(login.getUserId(),
								login.getPassword(), false, true, userId);
				connectionWrapper = xaConnectionWrapper;
			}

			// Apply equation transactions
			startEquationTransaction();
			applyEquationTransactions();
			endEquationTransaction();
		}
		finally
		{
			// Restore the earlier saved connection (not XA)
			if (originalConnectionWrapper != null)
			{
				if (connectionWrapper.isTransactionConnection())
				{
					connectionWrapper.cleanupXAConnection();
				}
				connectionWrapper = originalConnectionWrapper;
			}
		}

		// Return success!
		return true;
	}

	/**
	 * Call the UTW52R to retrieve a data area
	 * 
	 * @param name
	 *            - the data area name
	 * 
	 * @return the content of the data area
	 */
	public byte[] callUTW52R(String name) throws EQException
	{
		CallableStatement equationRetrieveDataArea = null;
		try
		{
			equationRetrieveDataArea = (CallableStatement) getEqStatement(EQStatementType.RETRIEVE_DATAAREA);
			// Register output parameters
			equationRetrieveDataArea.registerOutParameter(2, Types.VARCHAR);

			// Set the data area name
			equationRetrieveDataArea.setString(1, name);

			// Call the API
			equationRetrieveDataArea.execute();

			// Retrieve the returned values
			byte[] value = equationRetrieveDataArea.getBytes(2);
			return value;
		}
		catch (SQLException e)
		{
			throw new EQException(LanguageResources.getFormattedString("EquationStandardSession.ErrorCallingProgram", "UTW52R")
							+ ": " + Toolbox.getExceptionMessage(e));
		}
		finally
		{
			SQLToolbox.close(equationRetrieveDataArea);
		}
	}

	/**
	 * Apply the transactions currently on the session
	 */
	protected boolean applyEquationTransactions() throws EQException
	{
		boolean returnVal = true;
		// Have a bash...
		for (EquationStandardTransaction transaction : equationTransactions)
		{
			transaction = callX56HMR(transaction, "");

			if (!transaction.getValid())
			{
				// Rollback what we've done
				endEquationTransaction();
				rollbackTransactions();

				// Set value and break out of loop here to prevent
				// ConcurrentModificationException of equationTransactions
				returnVal = false;
				break;
			}
		}
		// Clear the transactions
		equationTransactions.clear();
		// Return success!
		return returnVal;
	}

	/**
	 * Call CRM limit checking
	 * 
	 * @param dshh03
	 *            - the Equation data structure data whose data structure is EqDS_DSHH03
	 * 
	 * @return the CRM data
	 * 
	 * @throws EQException
	 */
	private CRMLimitCheckData callUTW54R(EquationDataStructureData dshh03) throws EQException
	{
		try
		{
			if (equationCRMLimitAPICalled == null)
			{
				equationCRMLimitAPICalled = (CallableStatement) getEqStatement(EQStatementType.CRM_LIMIT_API_CALLED);
			}
			// Register output parameters
			equationCRMLimitAPICalled.registerOutParameter(1, Types.VARCHAR);
			equationCRMLimitAPICalled.registerOutParameter(2, Types.VARCHAR);
			equationCRMLimitAPICalled.registerOutParameter(3, Types.CHAR);

			// Set the PV program name
			equationCRMLimitAPICalled.setBytes(1, dshh03.getBytes());
			equationCRMLimitAPICalled.setBytes(2, new byte[1]);
			equationCRMLimitAPICalled.setString(3, "");

			// Call the API
			equationCRMLimitAPICalled.execute();

			// Retrieve the details
			byte[] byteDSHH03 = equationCRMLimitAPICalled.getBytes(1);
			byte[] byteCRM = equationCRMLimitAPICalled.getBytes(2);
			String dsepms = equationCRMLimitAPICalled.getString(3);

			CRMLimitCheckData crmLimitCheckData = new CRMLimitCheckData(byteDSHH03, byteCRM, dsepms);
			return crmLimitCheckData;

		}
		catch (SQLException e)
		{
			throw new EQException(LanguageResources.getFormattedString("EquationStandardSession.ErrorCallingProgram", "UTW54R")
							+ ": " + Toolbox.getExceptionMessage(e));
		}
	}

	/**
	 * Validate the password
	 * 
	 * @param userName
	 *            - the user name
	 * @param password
	 *            - the user password
	 * @param tranType
	 *            - the transaction type
	 * 
	 * @return an error message if user name/password and any validation are not valid
	 * 
	 * @throws EQException
	 */
	public String supervisorPassword(String userName, String password, String tranType) throws EQException
	{
		return callUTW21R(userName, password, tranType);
	}

	/**
	 * Call the UTW21R to validate supervisor password
	 * 
	 * @param parm1
	 *            - supervisor id
	 * @param parm2
	 *            - password
	 * @param tranType
	 *            - the transaction type
	 * 
	 * @return the password
	 */
	private String callUTW21R(String parm1, String parm2, String tranType) throws EQException
	{
		CallableStatement equationSupervisorCaller = null;
		try
		{
			equationSupervisorCaller = (CallableStatement) getEqStatement(EQStatementType.SUPERVISOR_CALLER);
			// Register all parameters
			equationSupervisorCaller.registerOutParameter(1, Types.CHAR);
			equationSupervisorCaller.registerOutParameter(2, Types.CHAR);
			equationSupervisorCaller.registerOutParameter(3, Types.CHAR);
			equationSupervisorCaller.registerOutParameter(4, Types.CHAR);

			// Set the parameters
			equationSupervisorCaller.setString(1, Toolbox.pad(parm1, 10));
			equationSupervisorCaller.setString(2, Toolbox.pad(parm2, 10));
			equationSupervisorCaller.setString(3, "");
			equationSupervisorCaller.setString(4, tranType);

			// Call the API
			equationSupervisorCaller.execute();

			// Retrieve the returned values
			return equationSupervisorCaller.getString(3);
		}
		catch (SQLException e)
		{
			throw new EQException(LanguageResources.getFormattedString("EquationStandardSession.ErrorCallingProgram", "UTW21R")
							+ ": " + Toolbox.getExceptionMessage(e));
		}
		finally
		{
			SQLToolbox.close(equationSupervisorCaller);
		}
	}

	/**
	 * Call an Equation standard Enquiry
	 * 
	 * @param enquiry
	 *            - the Equation standard enquiry
	 * 
	 * @throws EQException
	 * 
	 */
	public EquationStandardEnquiry executeEnquiry(EquationStandardEnquiry enquiry) throws EQException
	{
		String beforeGzDsStr = "";

		try
		{
			// print timing test
			EqTimingTest.printStartTime("EquationStandardSession.executeEnquiry()", enquiry.getAPIName());

			byte[] blankByte = " ".getBytes();
			// enquiry.setBegin(" ");
			enquiry.setBegin("Y");
			enquiry.setNoreq(0);
			enquiry.setNoret(0);
			enquiry.setEqnln("  ");
			enquiry.setErcod("  ");
			enquiry.setErprm("  ");
			enquiry.setFlen(0);
			enquiry.setRlen(0);

			// setup enquiry API caller
			if (equationEnquiryAPICaller == null)
			{
				equationEnquiryAPICaller = (CallableStatement) getEqStatement(EQStatementType.ENQUIRY_API_CALLER);
			}

			// Set the H46 parameter variables in the H46 SQL procedure
			equationEnquiryAPICaller.setString(1, Toolbox.pad(enquiry.getAPIName(), 10));
			equationEnquiryAPICaller.setBytes(2, enquiry.getHzDSData().getBytes());
			equationEnquiryAPICaller.setBytes(3, blankByte);
			equationEnquiryAPICaller.setString(4, Toolbox.pad(enquiry.getBegin(), 1));
			equationEnquiryAPICaller.setInt(5, enquiry.getNoreq());
			equationEnquiryAPICaller.setInt(6, enquiry.getNoret());
			equationEnquiryAPICaller.setString(7, Toolbox.pad(enquiry.getEqnln(), 2));
			equationEnquiryAPICaller.setString(8, Toolbox.pad(enquiry.getErcod(), 2));
			equationEnquiryAPICaller.setString(9, Toolbox.pad(enquiry.getErprm(), 10));
			equationEnquiryAPICaller.setInt(10, enquiry.getFlen());
			equationEnquiryAPICaller.setInt(11, enquiry.getRlen());
			// We want to be able to process all the returned parameters
			equationEnquiryAPICaller.registerOutParameter(1, Types.CHAR);
			equationEnquiryAPICaller.registerOutParameter(2, Types.CHAR);
			equationEnquiryAPICaller.registerOutParameter(3, Types.CHAR);
			equationEnquiryAPICaller.registerOutParameter(4, Types.CHAR);
			equationEnquiryAPICaller.registerOutParameter(5, Types.DECIMAL);
			equationEnquiryAPICaller.registerOutParameter(6, Types.DECIMAL);
			equationEnquiryAPICaller.registerOutParameter(7, Types.CHAR);
			equationEnquiryAPICaller.registerOutParameter(8, Types.CHAR);
			equationEnquiryAPICaller.registerOutParameter(9, Types.CHAR);
			equationEnquiryAPICaller.registerOutParameter(10, Types.DECIMAL);
			equationEnquiryAPICaller.registerOutParameter(11, Types.DECIMAL);

			// Call the API
			beforeGzDsStr = enquiry.toString();
			equationEnquiryAPICaller.execute();
			enquiry.getHzDSData().setBytes(equationEnquiryAPICaller.getBytes(2));
			enquiry.setBegin(equationEnquiryAPICaller.getString(4));
			enquiry.setNoreq(equationEnquiryAPICaller.getBigDecimal(5).intValue());
			enquiry.setNoret(equationEnquiryAPICaller.getBigDecimal(6).intValue());
			enquiry.setEqnln(equationEnquiryAPICaller.getString(7));
			enquiry.setErcod(equationEnquiryAPICaller.getString(8));
			enquiry.setErprm(equationEnquiryAPICaller.getString(9));
			enquiry.setFlen(equationEnquiryAPICaller.getBigDecimal(10).intValue());
			enquiry.setRlen(equationEnquiryAPICaller.getBigDecimal(11).intValue());

			// Set the returned values
			if (equationEnquiryAPICaller.getString(8).equals("  "))
			{
				enquiry.setValid(true);

				// print the transaction data
				LOG.debug(LanguageResources.getFormattedString("EquationStandardSession.EnquiryAPIBefore", enquiry.getAPIName())
								+ "\n" + beforeGzDsStr + "\n");
				LOG.debug(LanguageResources.getFormattedString("EquationStandardSession.EnquiryAPIAfter", enquiry.getAPIName())
								+ "\n" + enquiry);
			}
			else
			{
				if (!equationEnquiryAPICaller.getString(9).trim().equals(""))
				{
					enquiry.setErrorList(getMessages(equationEnquiryAPICaller.getString(9)));
				}
				else
				{
					enquiry.setErrorList(new ArrayList<EQMessage>());
				}
				enquiry.setErrorList(getMessages("KSM4014" + Toolbox.pad(enquiry.getErcod(), 10)
								+ Toolbox.pad(enquiry.getErprm(), 10)));
				enquiry.setValid(false);

				// print the transaction data
				LOG.error(LanguageResources.getFormattedString("EquationStandardSession.EnquiryAPIBefore", enquiry.getAPIName())
								+ "\n" + beforeGzDsStr + "\n");
				LOG.error(LanguageResources.getFormattedString("EquationStandardSession.EnquiryAPIFailure", enquiry.getAPIName())
								+ "\n" + enquiry);
			}
		}
		catch (SQLException e)
		{
			// print the transaction data
			LOG.error(LanguageResources.getFormattedString("EquationStandardSession.EnquiryAPIBefore", enquiry.getAPIName()) + "\n"
							+ beforeGzDsStr + "\n");
			LOG.error(LanguageResources.getFormattedString("EquationStandardSession.EnquiryAPIFailure", enquiry.getAPIName())
							+ "\n" + enquiry);
			LOG.error(e);
			throw new EQException("EquationStandardSession: executeEnquiry Failed: " + Toolbox.getExceptionMessage(e), e);
		}

		// print timing test
		EqTimingTest.printTime("EquationStandardSession.executeEnquiry()", enquiry.getAPIName());
		return enquiry;
	}

	/**
	 * Call the UTR00R to determine whether enhancement is installed
	 * 
	 * @param enhancement
	 *            - the enhancement to be check
	 * 
	 * @return true if enhancement is installed
	 * 
	 * @throws EQException
	 */
	public boolean callUTR00R(String enhancement) throws EQException
	{
		CallableStatement equationEnhancementCheckerCaller = null;
		try
		{
			equationEnhancementCheckerCaller = (CallableStatement) getEqStatement(EQStatementType.ENHANCEMENT_CHECKER_CALLER);
			// Register output parameters
			equationEnhancementCheckerCaller.registerOutParameter(2, Types.CHAR);

			// Set the PV program name
			equationEnhancementCheckerCaller.setString(1, Toolbox.trim(enhancement, 4));

			// Call the API
			equationEnhancementCheckerCaller.execute();

			// Retrieve the details
			String enh = equationEnhancementCheckerCaller.getString(2);

			return enh.equals("Y");
		}
		catch (SQLException e)
		{
			throw new EQException(LanguageResources.getFormattedString("EquationUnit.ErrorCallingProgram", "UTR00R") + ": "
							+ Toolbox.getExceptionMessage(e));
		}
		finally
		{
			SQLToolbox.close(equationEnhancementCheckerCaller);
		}
	}

	/**
	 * Call an Equation Standard List Enquiry until it retrieves all the details
	 * 
	 * @param enquiry
	 *            - the Equation standard list enquiry
	 * 
	 * @throws EQException
	 * 
	 */
	public EquationStandardListEnquiry executeListEnquiry(EquationStandardListEnquiry enquiry) throws EQException
	{
		return executeListEnquiry(enquiry, 0);
	}

	/**
	 * Determine if user has inter-branch authority
	 * 
	 * @param userId
	 *            - the user Id
	 * @param defBranchNo
	 *            - the user's default branch number
	 * 
	 * @return the true if user has inter-branch authority
	 * 
	 * @throws EQException
	 */
	private boolean callUTW57R(String userId, String defBranchNo) throws EQException
	{
		CallableStatement equationUserInterBranchAuth = null;
		try
		{
			equationUserInterBranchAuth = (CallableStatement) getEqStatement(EQStatementType.USER_INTERBRANCH_AUTH);
			// Register output parameters
			equationUserInterBranchAuth.registerOutParameter(3, Types.CHAR);

			// Set the parameters
			equationUserInterBranchAuth.setString(1, userId);
			equationUserInterBranchAuth.setString(2, defBranchNo);

			// Call the API
			equationUserInterBranchAuth.execute();

			// Retrieve the returned values
			return equationUserInterBranchAuth.getString(3).equals(EqDataType.OFF);
		}
		catch (SQLException e)
		{
			throw new EQException(LanguageResources.getFormattedString("EquationStandardSession.ErrorCallingProgram", "UTW57R")
							+ ": " + Toolbox.getExceptionMessage(e));
		}
		finally
		{
			SQLToolbox.close(equationUserInterBranchAuth);
		}
	}

	/**
	 * Call an Equation Standard List Enquiry until it retrieves all the details or limit has been reached
	 * 
	 * @param enquiry
	 *            - the Equation standard list enquiry
	 * @param limitBytes
	 *            - the maximum bytes to be retrieved
	 * 
	 * @throws EQException
	 * 
	 */
	public EquationStandardListEnquiry executeListEnquiry(EquationStandardListEnquiry enquiry, long limitBytes) throws EQException
	{
		long limit = 0;

		// print timing test
		EqTimingTest.printStartTime("EquationStandardSession.executeListEnquiry()", enquiry.getAPIName());

		// For I31DER set mode to 0
		if (enquiry.getAPIName().equals("I31DER"))
		{
			enquiry.setFieldValue("HZMODE", "0");
		}

		enquiry = executeIncrementalListEnquiry(enquiry);
		while (!enquiry.isComplete())
		{
			limit += enquiry.getRecordLength();
			if (limitBytes != 0 && limit > limitBytes)
			{
				break;
			}

			// For I31DER set mode to 4
			if (enquiry.getAPIName().equals("I31DER"))
			{
				enquiry.setFieldValue("HZMODE", "4");
			}

			enquiry = executeIncrementalListEnquiry(enquiry);
		}

		// print timing test
		EqTimingTest.printTime("EquationStandardSession.executeListEnquiry()", enquiry.getAPIName());
		return enquiry;
	}

	/**
	 * Call an Equation Standard List Enquiry once
	 * 
	 * @param enquiry
	 *            - the Equation standard list enquiry
	 * 
	 * @throws EQException
	 * 
	 */
	public EquationStandardListEnquiry executeIncrementalListEnquiry(EquationStandardListEnquiry enquiry) throws EQException
	{
		try
		{
			// print timing test
			EqTimingTest.printStartTime("EquationStandardSession.executeIncrementalListEnquiry()", enquiry.getAPIName());

			if (!enquiry.isInitialised())
			{
				// enquiry.setBegin(" ");
				enquiry.setBegin("Y");
				enquiry.setNoreq(0);
				enquiry.chkNoreq();
				enquiry.setNoret(0);
				enquiry.setEqnln("  ");
				enquiry.setErcod("  ");
				enquiry.setErprm("  ");
				enquiry.setFlen(0);
				enquiry.setRlen(0);
				enquiry.setInitialised(true);
			}
			else if (enquiry.getBegin().equals("Y"))
			{
				enquiry.setBegin(" ");
			}
			if (!enquiry.isComplete())
			{
				if (equationEnquiryAPICaller == null)
				{
					equationEnquiryAPICaller = (CallableStatement) getEqStatement(EQStatementType.ENQUIRY_API_CALLER);
				}
				// Set the H46 parameter variables in the H46 SQL procedure
				equationEnquiryAPICaller.setString(1, Toolbox.pad(enquiry.getAPIName(), 10));
				equationEnquiryAPICaller.setBytes(2, enquiry.getHzDSData().getBytes());
				equationEnquiryAPICaller.setBytes(3, enquiry.gethzListTemplateBytes());
				equationEnquiryAPICaller.setString(4, Toolbox.pad(enquiry.getBegin(), 1));
				equationEnquiryAPICaller.setInt(5, enquiry.getNoreq());
				equationEnquiryAPICaller.setInt(6, enquiry.getNoret());
				equationEnquiryAPICaller.setString(7, Toolbox.pad(enquiry.getEqnln(), 2));
				equationEnquiryAPICaller.setString(8, Toolbox.pad(enquiry.getErcod(), 2));
				equationEnquiryAPICaller.setString(9, Toolbox.pad(enquiry.getErprm(), 10));
				equationEnquiryAPICaller.setInt(10, enquiry.getFlen());
				equationEnquiryAPICaller.setInt(11, enquiry.getRlen());
				// We want to be able to process all the returned parameters
				equationEnquiryAPICaller.registerOutParameter(1, Types.CHAR);
				equationEnquiryAPICaller.registerOutParameter(2, Types.CHAR);
				equationEnquiryAPICaller.registerOutParameter(3, Types.CHAR);
				equationEnquiryAPICaller.registerOutParameter(4, Types.CHAR);
				equationEnquiryAPICaller.registerOutParameter(5, Types.DECIMAL);
				equationEnquiryAPICaller.registerOutParameter(6, Types.DECIMAL);
				equationEnquiryAPICaller.registerOutParameter(7, Types.CHAR);
				equationEnquiryAPICaller.registerOutParameter(8, Types.CHAR);
				equationEnquiryAPICaller.registerOutParameter(9, Types.CHAR);
				equationEnquiryAPICaller.registerOutParameter(10, Types.DECIMAL);
				equationEnquiryAPICaller.registerOutParameter(11, Types.DECIMAL);
				// Call the API
				equationEnquiryAPICaller.execute();
				// Set the returned values
				if (equationEnquiryAPICaller.getString(8).equals("  "))
				{
					enquiry.getHzDSData().setBytes(equationEnquiryAPICaller.getBytes(2));
					enquiry.setBegin(equationEnquiryAPICaller.getString(4));
					enquiry.setNoreq(equationEnquiryAPICaller.getBigDecimal(5).intValue());
					enquiry.setNoret(equationEnquiryAPICaller.getBigDecimal(6).intValue());
					enquiry.setEqnln(equationEnquiryAPICaller.getString(7));
					enquiry.setErcod(equationEnquiryAPICaller.getString(8));
					enquiry.setErprm(equationEnquiryAPICaller.getString(9));
					enquiry.setFlen(equationEnquiryAPICaller.getBigDecimal(10).intValue());
					enquiry.setRlen(equationEnquiryAPICaller.getBigDecimal(11).intValue());
					// loop through the returned data for the repeating format
					ByteArrayInputStream dsairByteArray = new ByteArrayInputStream(equationEnquiryAPICaller.getBytes(3));
					for (int i = 0; i < enquiry.getNoret(); i++)
					{
						byte[] row = new byte[enquiry.getRlen()];
						dsairByteArray.read(row, 0, enquiry.getRlen());
						enquiry.addhzListRcdFmt(row);
					}
					enquiry.setValid(true);
				}
				else
				{
					enquiry.setErcod(equationEnquiryAPICaller.getString(8));
					enquiry.setErprm(equationEnquiryAPICaller.getString(9));
					if (!equationEnquiryAPICaller.getString(9).trim().equals(""))
					{
						enquiry.setErrorList(getMessages(equationEnquiryAPICaller.getString(9)));
					}
					else
					{
						enquiry.setErrorList(new ArrayList<EQMessage>());
					}
					enquiry.getErrorList().add(getMessage("KSM4014" + Toolbox.pad(enquiry.getErcod(), 10)));

					enquiry.setValid(false);
					enquiry.setComplete(true);
				}
				if (enquiry.getBegin().equals("L"))
				{
					enquiry.setComplete(true);
				}
			}
		}
		catch (SQLException e)
		{
			LOG.error("executeIncrementalListEnquiry() error: " + enquiry.getAPIName());
			LOG.error(e);
			throw new EQException("EquationStandardSession: executeIncrementalListEnquiry Failed: "
							+ Toolbox.getExceptionMessage(e), e);
		}

		return enquiry;
	}

	/**
	 * Perform validation
	 * 
	 * @param validation
	 *            - the EquationStandardValidation
	 */
	public EquationStandardValidation executeValidate(EquationStandardValidation validation) throws EQException
	{
		try
		{
			// print timing test
			EqTimingTest.printStartTime("EquationStandardSession.executeValidate()", validation.getService());

			if (equationValidateAPICaller == null)
			{
				equationValidateAPICaller = (CallableStatement) getEqStatement(EQStatementType.VALIDATE_API_CALLER);
			}
			// Register the output
			equationValidateAPICaller.registerOutParameter(1, Types.VARCHAR);
			equationValidateAPICaller.registerOutParameter(2, Types.VARCHAR);
			equationValidateAPICaller.registerOutParameter(3, Types.VARCHAR);
			equationValidateAPICaller.registerOutParameter(4, Types.VARCHAR);
			equationValidateAPICaller.registerOutParameter(5, Types.VARCHAR);
			equationValidateAPICaller.registerOutParameter(6, Types.VARCHAR);
			equationValidateAPICaller.registerOutParameter(7, Types.VARCHAR);
			equationValidateAPICaller.registerOutParameter(8, Types.VARBINARY);
			equationValidateAPICaller.setString(1, validation.getDecode());
			equationValidateAPICaller.setString(2, validation.getService());
			equationValidateAPICaller.setString(3, validation.getDataInput());
			equationValidateAPICaller.setString(4, validation.getBlankAllowed());
			equationValidateAPICaller.setString(5, validation.getNewCode());
			equationValidateAPICaller.setString(6, validation.getSecurity());
			equationValidateAPICaller.setString(7, "");

			// the output must always be the last
			int indexOutput = 8;
			equationValidateAPICaller.setBytes(indexOutput, new byte[1]);

			// Execute the call
			equationValidateAPICaller.execute();

			// Process output data
			validation.setDataOutput(equationValidateAPICaller.getBytes(indexOutput));

			// Get the error message
			if (!validation.getValid())
			{
				validation.setErrorList(getMessages(validation.getError()));
			}

		}
		catch (SQLException e)
		{
			LOG.error("executeValidate() error: " + validation.getId());
			LOG.error(e);
			throw new RuntimeException("EquationStandardSession: executeValidate Failed: " + Toolbox.getExceptionMessage(e), e);
		}

		// print timing test
		EqTimingTest.printTime("EquationStandardSession.executeValidate()", validation.getService());
		return validation;
	}

	/**
	 * Retrieve list validation
	 * 
	 * @param validation
	 *            - the EquationStandardValidation
	 * 
	 * @throws EQException
	 * 
	 */
	public EquationStandardListValidation executeListValidate(EquationUser eqUser, EquationStandardListValidation validation)
					throws EQException
	{
		// print timing test
		EqTimingTest.printStartTime("EquationStandardSession.executeListValidate()", validation.getService());

		EquationDataList eqDataList = new EquationDataList();

		if (validation.getEquationPVData() == null)
		{
			eqDataList.initialise(eqUser, validation.getService(), validation.getDecode(), validation.getSecurity(), validation
							.getDataInput(), validation.getStartKey(), validation.getDirection(), validation.getMaxResults() + 1);
		}
		else
		{
			eqDataList.initialise(eqUser, validation.getService(), validation.getDecode(), validation.getSecurity(), validation
							.getEquationPVData(), validation.getStartKey(), validation.getDirection(),
							validation.getMaxResults() + 1);
		}

		validation.clearData();
		validation.addData(eqDataList.getDataList().split(EqDataType.GLOBAL_DELIMETER), eqUser.getSession().getCcsid());

		// print timing test
		EqTimingTest.printTime("EquationStandardSession.executeListValidate()", validation.getService());

		return validation;
	}

	/**
	 * Retrieve list validation
	 * 
	 * @param validation
	 *            - the EquationStandardValidation
	 * 
	 * @throws EQException
	 * 
	 */
	public EquationStandardListValidation executeNextListValidate(EquationUser eqUser, EquationStandardListValidation validation)
					throws EQException
	{
		if (validation.getDirection() == 1)
		{
			validation.setStartKey(validation.getLastKey());
		}
		else
		{
			validation.setStartKey(validation.getFirstKey());
		}
		return executeListValidate(eqUser, validation);
	}

	/**
	 * Perform validation
	 * 
	 * @param validation
	 *            - the EquationStandardValidation
	 * 
	 * @throws EQException
	 */
	public EquationStandardMultipleValidation executeMultipleValidate(EquationStandardMultipleValidation validation)
					throws EQException
	{
		CallableStatement equationMultipleValidateAPICaller = null;
		try
		{
			// print timing test
			EqTimingTest.printStartTime("EquationStandardSession.executeMultipleValidate()", "");

			equationMultipleValidateAPICaller = (CallableStatement) getEqStatement(EQStatementType.MULTIPLE_VALIDATE_API_CALLER);
			// Register the output
			equationMultipleValidateAPICaller.registerOutParameter(2, Types.VARBINARY);

			// Set the values
			equationMultipleValidateAPICaller.setString(1, validation.getData());
			// equationMultipleValidateAPICaller.setBytes(2, new byte[1]);

			// Execute the call
			equationMultipleValidateAPICaller.execute();

			// Process output data
			validation.setData(equationMultipleValidateAPICaller.getBytes(2), ccsid);
		}
		catch (SQLException e)
		{
			LOG.error(e);
			throw new RuntimeException(
							"EquationStandardSession: executeMultipleValidate Failed: " + Toolbox.getExceptionMessage(e), e);
		}
		finally
		{
			SQLToolbox.close(equationMultipleValidateAPICaller);
		}

		// print timing test
		EqTimingTest.printTime("EquationStandardSession.executeMultipleValidate()", "");
		return validation;
	}

	/**
	 * Validate an Equation table
	 * 
	 * @param table
	 *            - the Equation table
	 */
	public EquationStandardTable validateEquationTable(EquationStandardTable table)
	{
		return table;
	}

	/**
	 * Retrieve an Equation table
	 * 
	 * @param table
	 *            - the Equation table
	 * 
	 * @throws EQException
	 */
	public EquationStandardTable retrieveEquationTable(EquationStandardTable table) throws EQException
	{
		Statement statement = null;
		ResultSet resultSet = null;
		String sqlString = "";

		// Execute the query
		try
		{
			// print timing test
			EqTimingTest.printStartTime("EquationStandardSession.retrieveEquationTable()", table.getTableName());

			// build the sql
			String beforeTable = table.toString();
			sqlString = SQLToolbox.getSelectFromTable(table);

			// First up need to get the info for whether the key is unique
			statement = getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(sqlString.toString());
			if (resultSet.next())
			{
				table.getTableData().setResultSet(resultSet);
				table.setValid(true);
				table.setExists(true);
				table.setBeforeImage(table.getBytes());
			}
			else
			{
				table.setErrorList(getMessages("KSM7367" + table.getTableName()));
				table.setExists(false);
				table.setValid(false);
			}

			// log
			if (table.getValid())
			{
				LOG.debug(sqlString);
				LOG.debug(LanguageResources.getFormattedString("EquationStandardSession.TableBefore", table.getId()) + "\n"
								+ beforeTable + "\n");
				LOG.debug(LanguageResources.getFormattedString("EquationStandardSession.TableAfter", table.getId()) + "\n" + table);
			}
			else
			{
				LOG.error(sqlString);
				LOG.error(LanguageResources.getFormattedString("EquationStandardSession.TableBefore", table.getId()) + "\n"
								+ beforeTable + "\n");
				LOG.error(LanguageResources.getFormattedString("EquationStandardSession.TableFailure", table.getId()) + "\n"
								+ table);
			}
		}
		catch (SQLException e)
		{
			LOG.error("retrieveEquationTable() error: " + table.getTableName() + " - " + sqlString);
			LOG.error(e);
			throw new EQException(e);
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(statement);

			// print timing test
			EqTimingTest.printTime("EquationStandardSession.retrieveEquationTable()", table.getTableName());
		}
		return table;
	}

	/**
	 * Validate details from a PV created via the PV editor
	 * 
	 * @param validation
	 *            - the Equation PV Editor validation
	 * 
	 * @throws EQException
	 */
	public EquationPVEditorValidation executeEquationPVEditorValidation(EquationPVEditorValidation validation) throws EQException
	{
		Statement statement = null;
		ResultSet resultSet = null;
		String sqlString = "";

		// Execute the query
		try
		{
			// print timing test
			EqTimingTest.printStartTime("EquationStandardSession.retrieveEquationPVEditorValidation()", validation.getId());

			// build the sql
			String beforeTable = validation.toString();
			sqlString = validation.rtvSql();

			// First up need to get the info for whether the key is unique
			statement = getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(sqlString);

			// Retrieve ZP text
			EquationPVMetaData pvMetaData = unit.getPVMetaData(validation.getPvId());
			EquationPVDecodeMetaData pvDecodeMetaData = pvMetaData.getDecodeMetaData(validation.getDecode());
			HBRecordDataModel hbRecord = unit.getRecords().getHBRecord(language, "QR",
							Toolbox.getFirstWord(pvDecodeMetaData.getZpParam()));
			String description = validation.getPvId();
			if (hbRecord != null)
			{
				description = hbRecord.rtvCodeDescription(ccsid);
			}

			// found
			if (resultSet.next())
			{
				// must be new, then error
				if (validation.getNewField().equals(EqDataType.YES))
				{
					validation.setErrorList(getMessages("KSM2018" + description));
					validation.setValid(false);
					validation.setExists(true);
				}
				// existing, then load details
				else
				{
					validation.getQueryData().setResultSet(resultSet);
					validation.setValid(true);
					validation.setExists(true);
					validation.setBeforeImage(validation.getBytes());
				}
			}

			// not found
			else
			{
				// must be existing, then error
				if (validation.getNewField().equals(EqDataType.NO))
				{
					validation.setErrorList(getMessages("KSM2010" + description));
					validation.setExists(false);
					validation.setValid(false);
				}
				// not existing
				else
				{
					validation.setValid(true);
					validation.setExists(false);
				}
			}
			// log
			if (validation.getValid())
			{
				LOG.debug(sqlString);
				LOG.debug(LanguageResources.getFormattedString("EquationStandardSession.PVEditorBefore", validation.getId()) + "\n"
								+ beforeTable + "\n");
				LOG.debug(LanguageResources.getFormattedString("EquationStandardSession.PVEditorAfter", validation.getId()) + "\n"
								+ validation);
			}
			else
			{
				LOG.error(sqlString);
				LOG.error(LanguageResources.getFormattedString("EquationStandardSession.PVEditorBefore", validation.getId()) + "\n"
								+ beforeTable + "\n");
				LOG.error(LanguageResources.getFormattedString("EquationStandardSession.PVEditorFailure", validation.getId())
								+ "\n" + validation);
			}
		}
		catch (SQLException e)
		{
			LOG.error("retrieveEquationTable() error: " + validation.getId() + " - " + sqlString);
			LOG.error(e);
			throw new EQException(e);
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(statement);

			// print timing test
			EqTimingTest.printTime("EquationStandardSession.retrieveEquationPVEditorValidation()", validation.getId());
		}
		return validation;
	}

	/**
	 * Performs retrieval processing for a list database table until it retrieves all the details
	 * 
	 * @param table
	 *            the EquationStandardListTable to retrieve data for
	 * 
	 * @throws EQException
	 */
	public void retrieveEquationListTable(EquationStandardListTable table) throws EQException
	{
		retrieveEquationListTable(table, 0);
	}

	/**
	 * Performs retrieval processing for a list database table until it retrieves all the details or limit has been reached
	 * 
	 * @param table
	 *            the EquationStandardListTable to retrieve data for
	 * @param limitBytes
	 *            - the maximum bytes to be retrieved
	 * 
	 * @throws EQException
	 */
	public void retrieveEquationListTable(EquationStandardListTable table, long limitBytes) throws EQException
	{
		// set the keys and retrieve
		EquationDataStructureRepeatingData tableData = table.getTableData();
		Statement statement = null;
		ResultSet resultSet = null;
		String sqlString = "";

		// Execute the query
		try
		{
			// print timing test
			EqTimingTest.printStartTime("EquationStandardSession.retrieveEquationListTable()", table.getTableName());

			// build the sql
			sqlString = SQLToolbox.getSelectFromTable(table);

			// First up need to get the info for whether the key is unique
			statement = getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(sqlString.toString());
			tableData.clear();
			long limit = 0;
			table.setComplete(true);
			while (resultSet.next())
			{
				if (limitBytes != 0 && limit > limitBytes)
				{
					table.setComplete(false);
					break;
				}
				limit += table.getRecordLength();

				tableData.addRow();
				tableData.setResultSet(resultSet);
				table.setComplete(true);
				table.setValid(true);
				table.setExists(true);
				table.setBeforeImage(table.getBytes());
			}
			// print timing test
			EqTimingTest.printTime("EquationStandardSession.retrieveEquationListTable()", table.getTableName());
		}
		catch (SQLException e)
		{
			LOG.error("retrieveEquationListTable() error: " + table.getTableName() + " - " + sqlString);
			LOG.error(e);
			throw new EQException(e);
		}
		finally
		{
			// Once all records processed...
			SQLToolbox.close(resultSet);
			SQLToolbox.close(statement);
		}
	}

	/**
	 * Performs retrieval processing for a list database table until it retrieves all the details
	 * 
	 * @param query
	 *            - the EquationStandardQueryList
	 * 
	 * @throws EQException
	 */
	public void retrieveEquationQueryList(EquationStandardQueryList query) throws EQException
	{
		retrieveEquationQueryList(query, 0);
	}

	/**
	 * Performs retrieval processing for a list query until it retrieves all the details or limit has been reached
	 * 
	 * @param query
	 *            - the EquationStandardQueryList
	 * @param limitBytes
	 *            - the maximum bytes to be retrieved
	 * 
	 * @throws EQException
	 */
	public void retrieveEquationQueryList(EquationStandardQueryList query, long limitBytes) throws EQException
	{
		// Execute the query
		PreparedStatement ps = null;
		try
		{
			// print timing test
			EqTimingTest.printStartTime("EquationStandardSession.retrieveEquationQueryList()", query.getSql());

			Connection con = getConnection();
			ps = con.prepareStatement(query.getSql());

			// Parameter fields are currently assumed to be key fields
			ParameterMetaData meta = ps.getParameterMetaData();
			for (int i = 1; i <= meta.getParameterCount(); i++)
			{
				String name = EquationStandardQueryList.PARM_PREFIX + i;
				ps.setString(i, query.getKeys().get(name));
			}

			ResultSet rs = ps.executeQuery();
			query.setRowSet(SQLToolbox.populate(rs, limitBytes, query));
		}
		catch (SQLException e)
		{
			LOG.error("retrieveEquationQueryList() error: " + query.getSql());
			LOG.error(e);
			throw new EQException(e);
		}
		finally
		{
			SQLToolbox.close(ps);

			// print timing test
			EqTimingTest.printTime("EquationStandardSession.retrieveEquationQueryList()", query.getSql());
		}
	}

	/**
	 * Performs retrieval processing for a query
	 * 
	 * @param query
	 *            - the EquationStandardQueryList
	 * 
	 * @throws EQException
	 */
	public void retrieveEquationQuery(EquationStandardQuery query) throws EQException
	{
		// Execute the query
		PreparedStatement ps = null;
		try
		{
			// print timing test
			EqTimingTest.printStartTime("EquationStandardSession.retrieveEquationQuery()", query.getSql());
			String beforeQuery = query.toString();
			String sqlString = query.rtvSql();

			Connection con = getConnection();
			ps = con.prepareStatement(sqlString);
			ResultSet resultSet = ps.executeQuery();

			// found?
			if (resultSet.next())
			{
				query.getQueryData().setResultSet(resultSet);
				query.setValid(true);
				query.setExists(true);
				query.setBeforeImage(query.getBytes());
			}

			// not found
			else
			{
				query.setErrorList(getMessages("KSM7367" + query.getSql()));
				query.setExists(false);
				query.setValid(false);
			}

			// log
			if (query.getValid())
			{
				LOG.debug(sqlString);
				LOG.debug(LanguageResources.getFormattedString("EquationStandardSession.QueryBefore", query.getId()) + "\n"
								+ beforeQuery + "\n");
				LOG.debug(LanguageResources.getFormattedString("EquationStandardSession.QueryAfter", query.getId()) + "\n" + query);
			}
			else
			{
				LOG.error(sqlString);
				LOG.error(LanguageResources.getFormattedString("EquationStandardSession.QueryBefore", query.getId()) + "\n"
								+ beforeQuery + "\n");
				LOG.error(LanguageResources.getFormattedString("EquationStandardSession.QueryFailure", query.getId()) + "\n"
								+ query);
			}
		}
		catch (SQLException e)
		{
			LOG.error("retrieveEquationQuery() error: " + query.getSql());
			LOG.error(e);
			throw new EQException(e);
		}
		finally
		{
			SQLToolbox.close(ps);

			// print timing test
			EqTimingTest.printTime("EquationStandardSession.retrieveEquationQuery()", query.getSql());
		}
	}

	/**
	 * Add/maintain/delete the table
	 * 
	 * @param table
	 *            - the Equation standard table
	 * 
	 * @return the Equation standard table
	 */
	public EquationStandardTable applyEquationTable(EquationStandardTable table) throws EQException
	{
		Statement statement = null;
		String sqlString = "";

		try
		{
			// print timing test
			EqTimingTest.printStartTime("EquationStandardSession.applyEquationTable()", table.getTableName());

			// Determine the mode
			if (table.getMode().equals(IEquationStandardObject.FCT_ADD))
			{
				// table must not exist
				if (table.exists())
				{
					table.setErrorList(getMessages("KSM2020"));
					table.setValid(false);
				}
				else
				{
					sqlString = SQLToolbox.getInsertFromTable(table);
				}
			}
			else
			{
				// table should exist
				if (!table.exists())
				{
					table.setErrorList(getMessages("KSM2022"));
					table.setValid(false);
				}
				else
				{
					if (table.getMode().equals(IEquationStandardObject.FCT_DEL))
					{
						sqlString = SQLToolbox.getDeleteFromTable(table);
					}
					else
					{
						sqlString = SQLToolbox.getUpdateFromTable(table);
					}
				}
			}

			// Do a quick intermediate update check to make sure the before image is still valid
			if (table.getValid())
			{
				intermediateUpdateCheck(table);
			}

			// Execute the statement
			String beforeTable = table.toString();
			String globalLib = EquationCommonContext.getConfigProperty("eq.GlobalLibraryName").trim();

			if (globalLib.length() != 0 && sqlString.contains(globalLib))
			{
				sqlString = sqlString.replace(globalLib + "/", "");
				statement = connectionWrapper.getGlobalConnection().createStatement();

			}
			else
			{
				statement = getConnection().createStatement();
			}

			if (table.getValid())
			{
				statement.executeUpdate(sqlString);

				LOG.debug(sqlString);
				LOG.debug(LanguageResources.getFormattedString("EquationStandardSession.X56HMRBefore", table.getId()) + "\n"
								+ beforeTable + "\n");
				LOG
								.debug(LanguageResources.getFormattedString("EquationStandardSession.X56HMRAfter", table.getId())
												+ "\n" + table);
			}
			else
			{
				LOG.error(sqlString);
				LOG.error(LanguageResources.getFormattedString("EquationStandardSession.X56HMRBefore", table.getId()) + "\n"
								+ beforeTable + "\n");
				LOG.error(LanguageResources.getFormattedString("EquationStandardSession.X56HMRFailure", table.getId()) + "\n"
								+ table);
			}
		}
		catch (SQLException e)
		{
			LOG.error("applyEquationTable() error: " + table.getTableName() + " - " + sqlString);
			LOG.error(e);
			throw new EQException(e);
		}
		finally
		{
			SQLToolbox.close(statement);
		}

		// print timing test
		EqTimingTest.printTime("EquationStandardSession.applyEquationTable()", table.getTableName());

		return table;
	}

	/**
	 * Execute report
	 * 
	 * @param report
	 *            - the Equation standard report
	 */
	public EquationStandardReport executeReport(EquationStandardReport report)
	{
		Statement statement = null;
		String command = "";
		try
		{
			// print timing test
			EqTimingTest.printStartTime("EquationStandardSession.executeReport()", report.getReport());

			statement = getConnection().createStatement();
			command = "EQRRPTAPI " + "RPTMNM(" + Toolbox.getFirstWord(report.getReport()) + ") " + "SEL('" + report.getSelection()
							+ "') ";
			String query = SQLToolbox.getQcmdexcString2(command);
			statement.execute(query);

			// print timing test
			EqTimingTest.printTime("EquationStandardSession.executeReport()", report.getReport());
		}
		catch (SQLException e)
		{
			LOG.error("executeReport() error: " + command);
			LOG.error(e);
			throw new RuntimeException("EquationStandardSession: executeValidate Failed: " + Toolbox.getExceptionMessage(e), e);
		}
		finally
		{
			SQLToolbox.close(statement);
		}
		return report;
	}

	/**
	 * Perform intermediate update check before updating/maintaining the table to ensure that the table has not been changed by
	 * someone else
	 * 
	 * @param table
	 *            - the Equation standard table
	 */
	private void intermediateUpdateCheck(EquationStandardTable table) throws EQException
	{
		// print timing test
		EqTimingTest.printStartTime("EquationStandardSession.intermediateUpdateCheck()", table.getTableName());

		// Do a quick intermediate update check...
		EquationStandardTable reloadTable = new EquationStandardTable(table.getTableName(), table.getIndexName(), table.getKeys(),
						this);
		String[] keyNames = table.getKeys().split(":");
		for (String keyName : keyNames)
		{
			reloadTable.setFieldValue(keyName, table.getFieldValue(keyName));
		}
		retrieveEquationTable(reloadTable);

		byte[] reloadImage = reloadTable.getBytes();
		byte[] beforeImage = table.getBeforeImage();

		// Someone has already added the table
		if (!table.exists() && reloadTable.exists())
		{
			table.setErrorList(getMessages("KSM2020"));
			table.setValid(false);
		}

		// Some has already deleted the table
		else if (table.exists() && !reloadTable.exists())
		{
			table.setErrorList(getMessages("KSM2022"));
			table.setValid(false);
		}

		// Both not existing, then ok
		else if (!table.exists() && !reloadTable.exists())
		{
		}

		// Both existing, then ensure records are the same
		else if (!Arrays.equals(reloadImage, beforeImage))
		{
			table.setErrorList(getMessages("KSM2011"));
			table.setValid(false);

			LOG.error(LanguageResources.getFormattedString("EquationStandardSession.IntermediateCurrent", table.getId()) + "\n"
							+ reloadTable + "\n");
			LOG.error(LanguageResources.getFormattedString("EquationStandardSession.IntermediateBefore", table.getId()) + "\n"
							+ table + "\n" + getMessages("KSM2011"));
		}

		// print timing test
		EqTimingTest.printTime("EquationStandardSession.intermediateUpdateCheck()", table.getTableName());
	}

	/**
	 * Convert a list of messages in the format of DSEPMS into list of EQMessage
	 * 
	 * @param messageText
	 *            - the list of messages in DSEPMS format
	 * 
	 * @return the list of EQMessage
	 * 
	 * @throws EQException
	 */
	public List<EQMessage> getMessages(String messageText) throws EQException
	{
		List<EQMessage> messages = new ArrayList<EQMessage>();
		messageText = Toolbox.pad(messageText, 740);

		for (int i = 0; i < 20; i++)
		{
			String prefix = messageText.substring(i * 37, (i * 37) + 3);
			if (Arrays.binarySearch(VALID_KSM_PREFIXES, prefix) >= 0)
			{
				String dsepms = messageText.substring(i * 37, (i * 37) + 37);
				EQMessage eqMessage = getMessage(dsepms);
				messages.add(eqMessage);
			}
		}
		return messages;
	}

	/**
	 * Convert a message in the format of DSEPMS into EQMessage
	 * 
	 * @param messageText
	 *            - the message in DSEPMS format
	 * 
	 * @return the message in EQMessage format
	 * 
	 * @throws EQException
	 */
	public EQMessage getMessage(String messageText) throws EQException
	{
		EQMessage eqMessage = null;
		AS400 as400 = null;
		try
		{
			as400 = unit.getEquationSystem().getAS400();
			messageText = Toolbox.pad(messageText, 37);
			String msgId = messageText.substring(0, 7);
			MessageFile messageFile = new MessageFile(as400, ksmmsgfPath);
			int bidiStringType = (this.ccsid == 420) ? BidiStringType.ST5 : BidiStringType.DEFAULT;
			AS400Message message = messageFile.getMessage(msgId, new byte[0], bidiStringType, MessageFile.CCSID_OF_JOB, ccsid);
			String sev = Integer.toString(message.getSeverity());
			String description = message.getText();
			eqMessage = new EQMessage(msgId, sev, description, messageText);
		}
		catch (AS400SecurityException e)
		{
			LOG.error(e);
			throw new EQException("EquationStandardSession: getMessage Failed: " + Toolbox.getExceptionMessage(e), e);
		}
		catch (ErrorCompletingRequestException e)
		{
			LOG.error(e);
			throw new EQException("EquationStandardSession: getMessage Failed: " + Toolbox.getExceptionMessage(e), e);
		}
		catch (PropertyVetoException e)
		{
			LOG.error(e);
			throw new EQException("EquationStandardSession: getMessage Failed: " + Toolbox.getExceptionMessage(e), e);
		}
		catch (InterruptedException e)
		{
			LOG.error(e);
			throw new EQException("EquationStandardSession: getMessage Failed: " + Toolbox.getExceptionMessage(e), e);
		}
		catch (ObjectDoesNotExistException e)
		{
			LOG.error(e);
			throw new EQException("EquationStandardSession: getMessage Failed: " + Toolbox.getExceptionMessage(e), e);
		}
		catch (IOException e)
		{
			LOG.error(e);
			throw new EQException("EquationStandardSession: getMessage Failed: " + Toolbox.getExceptionMessage(e), e);
		}
		finally
		{
			if (as400 != null)
			{
				unit.getEquationSystem().returnAS400(as400);
			}
		}
		return eqMessage;
	}

	/**
	 * Call the UTW19R to retrieve the PV meta data
	 * 
	 * @param pvName
	 *            - PV program name
	 * 
	 * @return the PV meta data
	 */
	public EquationPVMetaData callUTW19R(String pvName) throws EQException
	{
		EquationPVMetaData pvMetaData = null;
		try
		{
			if (equationPVMetaDataAPICaller == null)
			{
				equationPVMetaDataAPICaller = (CallableStatement) getEqStatement(EQStatementType.PV_METADATA_API_CALLER);
			}
			// Register all parameters
			equationPVMetaDataAPICaller.registerOutParameter(2, Types.VARCHAR);
			equationPVMetaDataAPICaller.registerOutParameter(3, Types.VARCHAR);
			equationPVMetaDataAPICaller.registerOutParameter(4, Types.VARCHAR);
			equationPVMetaDataAPICaller.registerOutParameter(5, Types.VARCHAR);
			equationPVMetaDataAPICaller.registerOutParameter(6, Types.VARCHAR);
			equationPVMetaDataAPICaller.registerOutParameter(7, Types.VARCHAR);
			equationPVMetaDataAPICaller.registerOutParameter(8, Types.VARCHAR);
			equationPVMetaDataAPICaller.registerOutParameter(9, Types.VARCHAR);
			equationPVMetaDataAPICaller.registerOutParameter(10, Types.VARCHAR);
			equationPVMetaDataAPICaller.registerOutParameter(11, Types.VARCHAR);
			equationPVMetaDataAPICaller.registerOutParameter(12, Types.VARCHAR);
			equationPVMetaDataAPICaller.registerOutParameter(13, Types.VARCHAR);
			equationPVMetaDataAPICaller.registerOutParameter(14, Types.VARCHAR);
			equationPVMetaDataAPICaller.registerOutParameter(15, Types.VARCHAR);
			equationPVMetaDataAPICaller.registerOutParameter(16, Types.VARCHAR);
			equationPVMetaDataAPICaller.registerOutParameter(17, Types.VARCHAR);
			equationPVMetaDataAPICaller.registerOutParameter(18, Types.VARCHAR);
			equationPVMetaDataAPICaller.registerOutParameter(19, Types.VARCHAR);

			// Set the PV program name
			equationPVMetaDataAPICaller.setString(1, Toolbox.pad(pvName, 10));

			// Call the API
			equationPVMetaDataAPICaller.execute();

			// Retrieve the returned values
			String names = equationPVMetaDataAPICaller.getString(2);
			String namesd = equationPVMetaDataAPICaller.getString(3);
			String types = equationPVMetaDataAPICaller.getString(4);
			String lengths = equationPVMetaDataAPICaller.getString(5);
			String indexs = equationPVMetaDataAPICaller.getString(6);
			String decs = equationPVMetaDataAPICaller.getString(7);
			String usages = equationPVMetaDataAPICaller.getString(8);
			String header = equationPVMetaDataAPICaller.getString(9);
			String dbs = equationPVMetaDataAPICaller.getString(10);
			String ival = equationPVMetaDataAPICaller.getString(11);
			String dval = equationPVMetaDataAPICaller.getString(12);
			String oval = equationPVMetaDataAPICaller.getString(13);
			String decodes = equationPVMetaDataAPICaller.getString(14);
			String decodesd = equationPVMetaDataAPICaller.getString(15);
			String zpparm = equationPVMetaDataAPICaller.getString(16);
			String primf = equationPVMetaDataAPICaller.getString(17);
			String keys = equationPVMetaDataAPICaller.getString(18);
			String pvd = equationPVMetaDataAPICaller.getString(19);

			// create the pv meta data
			pvMetaData = new EquationPVMetaData(pvName, names, namesd, types, lengths, indexs, usages, decs, header, dbs, ival,
							dval, oval, decodes, decodesd, zpparm, primf, keys, pvd);
			pvMetaData.setPvSource(EquationPVMetaData.PV_SOURCE_UTW19R);
		}
		catch (SQLException e)
		{
			throw new EQException(LanguageResources.getFormattedString("EquationUnit.ErrorCallingProgram", "UTW19R") + ": "
							+ Toolbox.getExceptionMessage(e));
		}
		return pvMetaData;
	}

	/**
	 * Call UTV99R to reset the language property of the unit
	 * 
	 * @throws EQException
	 */
	protected void callUTV99R() throws EQException
	{
		Statement generateStatement = null;
		try
		{
			String generateSQLString = "call utv99r";
			generateStatement = connectionWrapper.getConnection().createStatement();
			String query = SQLToolbox.getQcmdexcString(generateSQLString);
			generateStatement.execute(query);
		}
		catch (SQLException e)
		{
			throw new EQException(LanguageResources.getFormattedString("EquationUnit.ErrorCallingProgram", "UTV99R") + ": "
							+ Toolbox.getExceptionMessage(e));
		}
		finally
		{
			SQLToolbox.close(generateStatement);
		}
	}

	/**
	 * Call UTW01R to generate the language property of the unit
	 * 
	 * @throws EQException
	 */
	public void callUTW01R() throws EQException
	{
		Statement generateStatement = null;
		try
		{
			String generateSQLString = "call utw01r";
			generateStatement = connectionWrapper.getConnection().createStatement();
			String query = SQLToolbox.getQcmdexcString(generateSQLString);
			generateStatement.execute(query);
		}
		catch (SQLException e)
		{
			throw new EQException(LanguageResources.getFormattedString("EquationUnit.ErrorCallingProgram", "UTW01R") + ": "
							+ Toolbox.getExceptionMessage(e));
		}
		finally
		{
			SQLToolbox.close(generateStatement);
		}
	}

	/**
	 * Get user token
	 * 
	 * @param userId
	 *            - the user id
	 * @param password
	 *            - the user password
	 * @param tokenType
	 *            - the token type
	 * 
	 * @return the user token
	 * 
	 * @throws EQException
	 */
	public byte[] getUserToken(String userId, String password, String tokenType) throws EQException
	{
		String inputStr = Toolbox.fixLengthChar(userId, 10) + Toolbox.fixLengthChar(password, 30)
						+ Toolbox.fixLengthChar(Toolbox.padAtFront(String.valueOf(password.length()), "0", 3), 3)
						+ Toolbox.fixLengthChar(Toolbox.padAtFront(String.valueOf(ccsid), "0", 3), 3)
						+ Toolbox.fixLengthChar(tokenType, 1);
		byte[] input = Toolbox.convertTextIntoAS400BytesCCSID(inputStr, inputStr.length(), ccsid, BidiStringType.DEFAULT);
		byte[] output = callUTW58R(UTW58R_MODE_TOKEN, input);

		// output is less than USER_TOKEN_LENGTH bytes
		if (output.length < USER_TOKEN_LENGTH)
		{
			return output;
		}

		// only the first 32 characters are valid
		byte[] token = new byte[USER_TOKEN_LENGTH];
		System.arraycopy(output, 0, token, 0, USER_TOKEN_LENGTH);

		return token;
	}

	/**
	 * Call UTW58R
	 * 
	 * @param mode
	 *            - the mode
	 * @param input
	 *            - the input (in bytes)
	 * 
	 * @return the output in byte format
	 * 
	 * @throws EQException
	 */
	private byte[] callUTW58R(String mode, byte[] input) throws EQException
	{
		CallableStatement equationUTW58R = null;
		try
		{
			equationUTW58R = (CallableStatement) getEqStatement(EQStatementType.UTW58R);
			// Register output parameters
			equationUTW58R.registerOutParameter(3, Types.VARCHAR);

			// Set the PV program name
			equationUTW58R.setString(1, Toolbox.trim(mode, 3));
			equationUTW58R.setBytes(2, input);

			// Call the API
			equationUTW58R.execute();

			return equationUTW58R.getBytes(3);
		}
		catch (SQLException e)
		{
			throw new EQException(LanguageResources.getFormattedString("EquationUnit.ErrorCallingProgram", "UTW58R") + ": "
							+ Toolbox.getExceptionMessage(e));
		}
		finally
		{
			SQLToolbox.close(equationUTW58R);
		}
	}

	/**
	 * Update the current GH record
	 * 
	 * @param mode
	 *            - Add or Maintain or Delete
	 * @param optionId
	 *            - option Id
	 * @param program
	 *            - program name
	 * @param cc
	 *            - cc flag?
	 * @param app
	 *            - application
	 * @param tcpIp
	 *            - tcp ip address
	 * 
	 * @return the error message (if any)
	 * 
	 * @throws EQException
	 */
	public EQMessage updateGH(String mode, String optionId, String program, String cc, String app, String tcpIp) throws EQException
	{
		String workStation = connectionWrapper.getJobWorkstationId();
		String jobUserId = connectionWrapper.getJobUser();
		int jobNumber = connectionWrapper.getJobNumber();
		String logonUser = getUserId();
		String sessionId = sessionIdentifier;
		return updateGH(mode, workStation, jobUserId, jobNumber, sessionId, optionId, program, cc, app, logonUser, tcpIp);
	}

	/**
	 * Return the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append("JobId = " + getConnectionWrapper().getJobId() + "\n");
		buffer.append("User  = " + userId + "\n");
		buffer.append("System = " + systemId + "\n");
		buffer.append("Unit  = " + unitId + "\n");
		buffer.append("Ccsid = " + ccsid + "\n");

		return buffer.toString();
	}

	/**
	 * Return the session lock
	 * 
	 * @return the session lock
	 */
	public Lock getSessionLock()
	{
		return sessionLock;
	}
	/**
	 * Return the outstanding break messages
	 * 
	 * @return the outstanding break messages
	 */
	public ArrayList<String> getBreakMessages()
	{
		return breakMessages;
	}
	/**
	 * Set the outstanding break messages
	 * 
	 * @param breakMessages
	 */
	public void setBreakMessages(ArrayList<String> breakMessages)
	{
		this.breakMessages = breakMessages;
	}
}