package com.misys.equation.function.runtime;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.function.journal.ParentTransactionDetail;

public class FunctionInfo
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionInfo.java 12364 2011-12-01 15:23:04Z perkinj1 $";

	// Unique name of the Function Handler
	private String name;

	// Session Id
	private String sessionId;

	// Default workstation Id
	private String workStationId;

	// In debug mode?
	private boolean debugMode;

	// Session type (e.g. Full desktop, API mode, direct to transaction, etc)
	private int sessionType;

	// Is WebFacing installed?
	private boolean webFacingInstalled;

	// Rollback test
	private boolean rollback;

	// Commitment processing
	private boolean commitProcessing;

	// Generate warning and info on update API
	private boolean generateWarningInfo;

	// Transaction details passed from the parent - valid only if this is a linked transaction
	private ParentTransactionDetail parentTransactionDetail;

	/**
	 * Construct a new function context
	 * 
	 * @param sessionId
	 *            - the session id
	 * @param name
	 *            - name of the Function Handler
	 */
	public FunctionInfo(String sessionId, String name)
	{
		this.sessionId = sessionId;
		this.name = name;
		this.workStationId = "";
		this.debugMode = false;
		this.sessionType = EquationCommonContext.SESSION_API_MODE;
		this.webFacingInstalled = false;
		this.rollback = false;
		this.commitProcessing = true;
		this.parentTransactionDetail = null;
		// TODO: Externalise generate warning info... maybe
		this.generateWarningInfo = false;
	}

	/**
	 * Construct a new function context based on an existing function context
	 * 
	 * @param sessionId
	 *            - the session id
	 * @param name
	 *            - name of the Function Handler
	 */
	public FunctionInfo(String sessionId, String name, FunctionInfo fi)
	{
		this.sessionId = sessionId;
		this.name = name;
		this.workStationId = fi.workStationId;
		this.debugMode = fi.debugMode;
		this.sessionType = fi.sessionType;
		this.webFacingInstalled = fi.webFacingInstalled;
		this.rollback = fi.rollback;
		this.commitProcessing = fi.commitProcessing;
		this.parentTransactionDetail = fi.parentTransactionDetail;
		this.generateWarningInfo = fi.generateWarningInfo;
	}

	/**
	 * Return the session id
	 * 
	 * @return the session id
	 */
	public String getSessionId()
	{
		return sessionId;
	}

	/**
	 * Set the session id
	 * 
	 * @param sessionId
	 *            - the session id
	 */
	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}

	/**
	 * Return the name of the Function Handler
	 * 
	 * @return the name of the Function Handler
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the name of the Function Handler
	 * 
	 * @param name
	 *            - the name of the Function Handler
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Return the workstation id
	 * 
	 * @return the workstation id
	 */
	public String getWorkStationId()
	{
		return workStationId;
	}

	/**
	 * Set the workstation id
	 * 
	 * @param workStationId
	 *            - the workstation id
	 */
	public void setWorkStationId(String workStationId)
	{
		this.workStationId = workStationId;
	}

	/**
	 * Return whether the function is in debug mode
	 * 
	 * @return true if the function is in debug mode
	 */
	public boolean isDebugMode()
	{
		return debugMode;
	}

	/**
	 * Set whether the function is in debug mode
	 * 
	 * @param debugMode
	 *            - whether the function is in debug mode or not
	 */
	public void setDebugMode(boolean debugMode)
	{
		this.debugMode = debugMode;
	}

	/**
	 * Return the session type (refer to Context)
	 * 
	 * @return the session type
	 */
	public int getSessionType()
	{
		return sessionType;
	}

	/**
	 * Set the session type
	 * 
	 * @param sessionType
	 *            - the session type
	 */
	public void setSessionType(int sessionType)
	{
		this.sessionType = sessionType;
	}

	/**
	 * Determine if IBM WebFacing is installed
	 * 
	 * @return true if IBM WebFacing is installed
	 */
	public boolean isWebFacingInstalled()
	{
		return webFacingInstalled;
	}

	/**
	 * Set if IBM WebFacing is installed
	 * 
	 * @param webFacingInstalled
	 *            - true if IBM WebFacing is installed
	 */
	public void setWebFacingInstalled(boolean webFacingInstalled)
	{
		this.webFacingInstalled = webFacingInstalled;
	}

	/**
	 * Return whether the function is forced to rollback
	 * 
	 * @return true if the function is forced to rollback
	 */
	public boolean isRollback()
	{
		return rollback;
	}

	/**
	 * Set whether the function is forced to rollback
	 * 
	 * @param rollback
	 *            - true if the function is forced to rollback
	 */
	public void setRollback(boolean rollback)
	{
		this.rollback = rollback;
	}

	/**
	 * Determine whether commit processing
	 * 
	 * @return true if commit processing
	 */
	public boolean isCommitProcessing()
	{
		return commitProcessing;
	}

	/**
	 * Set commitment processing
	 * 
	 * @param commitProcessing
	 *            - true if commit processing
	 */
	public void setCommitProcessing(boolean commitProcessing)
	{
		this.commitProcessing = commitProcessing;
	}

	/**
	 * Return the parent transaction details
	 * 
	 * @return the parent transaction details
	 */
	public ParentTransactionDetail getParentTransactionDetail()
	{
		return parentTransactionDetail;
	}

	/**
	 * Set the parent transaction details
	 * 
	 * @param parentTransactionDetail
	 *            - the parent transaction details
	 */
	public void setParentTransactionDetail(ParentTransactionDetail parentTransactionDetail)
	{
		this.parentTransactionDetail = parentTransactionDetail;
	}

	/**
	 * Determine whether warning and info messages are generated
	 * 
	 * @return true if warning and info messages are generated
	 */
	public boolean isGenerateWarningInfo()
	{
		return generateWarningInfo;
	}

	/**
	 * Set whether warning and info messages are generated
	 * 
	 * @param generateWarningInfo
	 *            - true if warning and info messages are generated
	 */
	public void setGenerateWarningInfo(boolean generateWarningInfo)
	{
		this.generateWarningInfo = generateWarningInfo;
	}

}
