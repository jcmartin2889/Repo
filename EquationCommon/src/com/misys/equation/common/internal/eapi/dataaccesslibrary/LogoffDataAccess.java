package com.misys.equation.common.internal.eapi.dataaccesslibrary;

import java.sql.SQLException;

import com.misys.equation.common.internal.eapi.core.EQException;

/**
 * Performs logoff from Equation
 */
public class LogoffDataAccess
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LogoffDataAccess.java 8582 2010-08-11 01:26:18Z esther.williams $";
	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";
	protected static final int PARAM_EQLOGOFF_CALLSTATUS = 1;
	protected static final int PARAM_EQLOGOFF_MESSAGE_ID = 2;
	protected static final int PARAM_EQLOGOFF_MESSAGE_TEXT = 3;
	public static final String storedProcedureCallEQLOGOFF = "CALL EQLOGOFF(?,?,?)";
	private String strCallStatus = "";
	private String strMessageId = "";
	private String strMessageText = "";

	/**
	 * Equation signoff process
	 * 
	 * @param conn
	 *            - the connection
	 * 
	 * @return the return status
	 * 
	 * @throws EQException
	 */
	public int equationSignOff(java.sql.Connection conn) throws EQException
	{
		java.sql.CallableStatement cs = null;
		try
		{
			cs = conn.prepareCall(storedProcedureCallEQLOGOFF);
			cs.registerOutParameter(PARAM_EQLOGOFF_CALLSTATUS, java.sql.Types.CHAR);
			cs.registerOutParameter(PARAM_EQLOGOFF_MESSAGE_ID, java.sql.Types.CHAR);
			cs.registerOutParameter(PARAM_EQLOGOFF_MESSAGE_TEXT, java.sql.Types.CHAR);
			// Execute the call statement
			cs.execute();
			strCallStatus = cs.getString(PARAM_EQLOGOFF_CALLSTATUS).trim();
			strMessageId = cs.getString(PARAM_EQLOGOFF_MESSAGE_ID);
			strMessageText = cs.getString(PARAM_EQLOGOFF_MESSAGE_TEXT);
			return Integer.parseInt(strCallStatus);
		}
		catch (SQLException ex)
		{
			throw new EQException(ex);
		}
	}

	/**
	 * Return the message Id
	 * 
	 * @return the message Id
	 */
	public String getMessageId()
	{
		return strMessageId;
	}

	/**
	 * Return the message text
	 * 
	 * @return the message text
	 */
	public String getMessageText()
	{
		return strMessageText;
	}

}
