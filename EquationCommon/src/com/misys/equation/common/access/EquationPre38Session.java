package com.misys.equation.common.access;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.language.LanguageResources;
import com.misys.equation.common.utilities.EqTimingTest;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;

/** {@inheritDoc} */
public class EquationPre38Session extends AbsEquationStandardSession
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationPre38Session.java 15717 2013-04-30 15:46:31Z whittap1 $";

	private static final EquationLogger LOG = new EquationLogger(EquationPre38Session.class);

	/** {@inheritDoc} */
	public EquationPre38Session(EquationUser user, EquationConnectionWrapper connectionWrapper) throws EQException
	{
		super(user, connectionWrapper);
	}

	/** {@inheritDoc} */
	public EquationPre38Session(EquationUnit unit) throws EQException
	{
		super(unit);
	}

	/**
	 * Create instance of CallableStatement based on the argument provided.
	 * 
	 * @throws EQException
	 */
	@Override
	protected Statement getEqStatement(EQStatementType type) throws EQException
	{
		Statement s = null;
		try
		{
			switch (type)
			{
				// Get us the basic Transaction API caller
				case BASIC_TRANSACTION_API_CALLER:
					s = getConnection().prepareCall("{ CALL H56HIRPRC (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
					break;
				// TODO added for EQ342 compatibility - need to check resolution for future as program does not exist
				// Get us the PV caller
				case VALIDATE_API_CALLER:
					s = getConnection().prepareCall("{ CALL UTW06RPRC (?, ?, ?, ?, ?, ?, ?, ?) }");
					break;
				// Get us the Retrieve data area API
				case RETRIEVE_DATAAREA:
					s = getConnection().prepareCall("{ CALL UTW52RPRC (?, ?) }");
					break;
				// Get us the Enquiry API caller
				case ENQUIRY_API_CALLER:
					s = getConnection().prepareCall("{ CALL X46HMRPRC (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
					break;
				// Get us the PV Meta data caller
				case PV_METADATA_API_CALLER:
					s = getConnection().prepareCall("{ CALL UTW19RPRC (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
					break;
			}

			switch (type)
			{
				// Get us a simple command to determine if the system is valid
				case GET_OBJECT_LIBRARY_CALLER:
					s = getConnection().prepareCall("{ CALL " + unit.getSystem().getBaseLibrary() + "/GETLIBPRC (?, ?, ?) }");
					break;
				// Get us a simple command to determine if the system is valid
				case QUICK_COMMAND:
					s = getConnection().prepareStatement(SQLToolbox.getQcmdexcString(CMD_DUMMY_QUICK));
					break;
				// Get us the enhancement check caller UTR00R
				case ENHANCEMENT_CHECKER_CALLER:
					s = getConnection().prepareCall("{ CALL UTR00RPRC (?, ?) }");
					break;
			}
		}
		catch (SQLException sqle)
		{
			throw new EQException(sqle);
		}

		return s;
	}

	/**
	 * Apply a specific transaction in validate mode
	 * 
	 * @param transaction
	 *            - the transaction to apply
	 */
	@Override
	public EquationStandardTransaction validateEquationTransaction(EquationStandardTransaction transaction) throws EQException
	{
		transaction = callH56HMR(transaction, IEquationStandardObject.FCT_VAL);
		return transaction;
	}

	/**
	 * Apply a specific transaction in retrieve mode
	 * 
	 * @param transaction
	 *            - the transaction to apply
	 */
	@Override
	public EquationStandardTransaction retrieveEquationTransaction(EquationStandardTransaction transaction) throws EQException
	{
		transaction = callH56HMR(transaction, "R");
		return transaction;
	}

	/**
	 * Apply a specific transaction
	 * 
	 * @param transaction
	 *            - the transaction to apply
	 */
	@Override
	public EquationStandardTransaction applyEquationTransaction(EquationStandardTransaction transaction) throws EQException
	{
		transaction = callH56HMR(transaction, "");
		return transaction;
	}

	/**
	 * Apply the transactions currently on the session
	 */
	@Override
	protected boolean applyEquationTransactions() throws EQException
	{
		boolean returnVal = true;
		// Have a bash...
		for (EquationStandardTransaction transaction : equationTransactions)
		{
			transaction = callH56HMR(transaction, "");

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
	 * Call X56HMR to execute an <code>EquationStandardTransaction</code>
	 * 
	 * @param transaction
	 *            - the transaction to execute
	 * @param mode
	 *            - the mode to call X56 in
	 */
	@Override
	protected EquationStandardTransaction callX56HMR(EquationStandardTransaction transaction, String mode) throws EQException
	{
		String rpgm = "";
		String wsid = "";
		String usid = "";
		String suid = "";
		String spwd = "";
		String jtt = "";
		byte[] gza = null;
		byte[] gzb = null;
		byte[] dsgyctr = null;
		byte[] dsgydet = null;
		String rrec = "";
		String aoe = "";
		String aow = "";
		byte[] crm = null;
		String aext = "";
		String arec = "";
		String beforeGzDsStr = "";
		try
		{
			// print timing test
			EqTimingTest.printStartTime("Equation4Session.callX56HMR()", transaction.getAPIName());

			// Set the H56 parameter variables
			byte[] blankByte = { 0x40 };
			rpgm = transaction.getAPIName();
			usid = getEquationUserId();
			wsid = transaction.getWorkStationId(); // workstation id
			jtt = transaction.getMode(); // transaction type (A, M, ...)
			gza = transaction.getBytes();

			// before image
			gzb = transaction.getBeforeImage();
			if (gzb == null)
			{
				gzb = blankByte;
			}

			// gy control
			if (transaction.getDsgyCtr() == null)
			{
				dsgyctr = blankByte;
			}
			else
			{
				dsgyctr = transaction.getDsgyCtr().getBytes();
			}

			// gy details
			if (transaction.getDsgyDet() == null)
			{
				dsgydet = blankByte;
			}
			else
			{
				dsgydet = transaction.getDsgyDet().getBytes();
			}

			rrec = "";
			if (mode.equals(IEquationStandardObject.FCT_VAL))
			{
				rrec = mode; // Validate - see EQ Journal File definition manual
			}
			if (mode.equals(IEquationStandardObject.FCT_RTV))
			{
				jtt = mode; // Retrieval - see EQ Journal File definition manual
			}
			if (transaction.getAPIName().startsWith("K61A") && mode.equals(IEquationStandardObject.FCT_RTV)
							&& transaction.getByteCP() != 0x20)
			{
				jtt = "R"; // Clean Payments retrieval mode
			}
			// output parameters
			aoe = ""; // Error message
			aow = ""; // Warning messages
			suid = "";
			spwd = "";
			crm = blankByte;
			aext = transaction.getAext();
			arec = transaction.getArec();

			if (equationTransactionAPICaller == null)
			{
				equationTransactionAPICaller = (CallableStatement) getEqStatement(EQStatementType.TRANSACTION_API_CALLER);
			}
			// Set the X56 parameter variables in the X56 SQL procedure
			equationTransactionAPICaller.setString(1, Toolbox.pad(rpgm, 10));
			equationTransactionAPICaller.setString(2, Toolbox.pad(branchId, 4));
			equationTransactionAPICaller.setString(3, Toolbox.pad(wsid, 4));
			equationTransactionAPICaller.setString(4, Toolbox.pad(usid, 4));
			equationTransactionAPICaller.setString(5, Toolbox.pad(suid, 4));
			equationTransactionAPICaller.setString(6, Toolbox.pad(spwd, 10));
			equationTransactionAPICaller.setString(7, Toolbox.pad(jtt, 1));
			equationTransactionAPICaller.setBytes(8, gza);
			equationTransactionAPICaller.setBytes(9, gzb);
			equationTransactionAPICaller.setString(10, Toolbox.pad(rrec, 1));
			equationTransactionAPICaller.setString(11, Toolbox.pad(aoe, 740));
			equationTransactionAPICaller.setString(12, Toolbox.pad(aow, 740));
			equationTransactionAPICaller.setBytes(13, crm);
			equationTransactionAPICaller.setString(14, aext);
			equationTransactionAPICaller.setString(15, arec);
			equationTransactionAPICaller.setString(16, "");
			equationTransactionAPICaller.setString(17, "");
			equationTransactionAPICaller.setString(18, "");
			equationTransactionAPICaller.setString(19, "");
			equationTransactionAPICaller.setBytes(20, dsgyctr);
			equationTransactionAPICaller.setBytes(21, dsgydet);
			equationTransactionAPICaller.setBytes(22, blankByte);

			// We want to be able to process all the returned parameters
			equationTransactionAPICaller.registerOutParameter(1, Types.CHAR);
			equationTransactionAPICaller.registerOutParameter(2, Types.CHAR);
			equationTransactionAPICaller.registerOutParameter(3, Types.CHAR);
			equationTransactionAPICaller.registerOutParameter(4, Types.CHAR);
			equationTransactionAPICaller.registerOutParameter(5, Types.CHAR);
			equationTransactionAPICaller.registerOutParameter(6, Types.CHAR);
			equationTransactionAPICaller.registerOutParameter(7, Types.CHAR);
			equationTransactionAPICaller.registerOutParameter(8, Types.VARCHAR);
			equationTransactionAPICaller.registerOutParameter(9, Types.VARCHAR);
			equationTransactionAPICaller.registerOutParameter(10, Types.CHAR);
			equationTransactionAPICaller.registerOutParameter(11, Types.VARCHAR);
			equationTransactionAPICaller.registerOutParameter(12, Types.VARCHAR);
			equationTransactionAPICaller.registerOutParameter(13, Types.VARCHAR);
			equationTransactionAPICaller.registerOutParameter(14, Types.CHAR);
			equationTransactionAPICaller.registerOutParameter(15, Types.CHAR);
			equationTransactionAPICaller.registerOutParameter(16, Types.CHAR);
			equationTransactionAPICaller.registerOutParameter(17, Types.CHAR);
			equationTransactionAPICaller.registerOutParameter(18, Types.CHAR);
			equationTransactionAPICaller.registerOutParameter(19, Types.CHAR);
			equationTransactionAPICaller.registerOutParameter(20, Types.VARCHAR);
			equationTransactionAPICaller.registerOutParameter(21, Types.VARCHAR);
			equationTransactionAPICaller.registerOutParameter(22, Types.VARCHAR);

			// store string
			beforeGzDsStr = transaction.toString();

			// Call the API
			equationTransactionAPICaller.execute();

			// Set the returned values
			rpgm = equationTransactionAPICaller.getString(1);
			branchId = equationTransactionAPICaller.getString(2);
			wsid = equationTransactionAPICaller.getString(3);
			usid = equationTransactionAPICaller.getString(4);
			suid = equationTransactionAPICaller.getString(5);
			spwd = equationTransactionAPICaller.getString(6);
			jtt = equationTransactionAPICaller.getString(7);
			gza = equationTransactionAPICaller.getBytes(8);
			gzb = equationTransactionAPICaller.getBytes(9);
			rrec = equationTransactionAPICaller.getString(10);
			aoe = equationTransactionAPICaller.getString(11);
			aow = equationTransactionAPICaller.getString(12);
			crm = equationTransactionAPICaller.getBytes(13);
			aext = equationTransactionAPICaller.getString(14);
			arec = equationTransactionAPICaller.getString(15);
			dsgyctr = equationTransactionAPICaller.getBytes(20);
			dsgydet = equationTransactionAPICaller.getBytes(21);
			byte[] dsJrnKey = equationTransactionAPICaller.getBytes(22);

			if (transaction.getDsgyCtr() != null)
			{
				transaction.getDsgyCtr().setBytes(dsgyctr);
			}
			if (transaction.getDsgyDet() != null)
			{
				transaction.getDsgyDet().setBytes(dsgydet);
			}
			transaction.getDsJrnKey().setBytes(dsJrnKey);
		}
		catch (SQLException e)
		{
			LOG.error(e);
			LOG.error("Failed Execution of X56HMR:" + "RPGM=" + rpgm + ";WSID=" + wsid + ";USID=" + usid + ";SUID=" + suid
							+ ";SPWD=" + spwd.length() + ";AEXT=" + aext + ";AREC=" + arec);

			LOG.error(LanguageResources.getFormattedString("Equation4Session.X56HMRBefore", transaction.getAPIName()) + "\n"
							+ beforeGzDsStr + "\n");
			LOG.error(LanguageResources.getFormattedString("Equation4Session.X56HMRFailure", transaction.getAPIName()) + "\n"
							+ transaction + "\n" + getMessages(aoe));

			throw new EQException("Equation4Session - callX56HMR() failed: " + Toolbox.getExceptionMessage(e), e);
		}

		// API successfully applied? Note: W=Warnings (e.g. H15ARR)
		if (rrec.equals("R") || rrec.equals("W"))
		{
			transaction.setGzBytes(gza);
			transaction.setValid(true);
			transaction.setWarningList(getMessages(aow));
			transaction.setCrmData(crm);

			// GS defined?
			int offset = transaction.getGsOffset();
			if (offset > 0)
			{
				byte[] gs = new byte[gza.length - offset + 1];
				System.arraycopy(gza, offset - 1, gs, 0, gza.length - offset + 1);
				transaction.setGsBytes(gs);
			}

			// print the transaction data
			LOG.debug(LanguageResources.getFormattedString("Equation4Session.X56HMRBefore", transaction.getAPIName()) + "\n"
							+ beforeGzDsStr + "\n");
			LOG.debug(LanguageResources.getFormattedString("Equation4Session.X56HMRAfter", transaction.getAPIName()) + "\n"
							+ transaction + "\n" + getMessages(aoe));
		}
		else
		{
			if (aoe.trim().length() == 0)
			{
				aoe = "KSM7364" + transaction.getAPIName();
			}
			transaction.setErrorList(getMessages(aoe));
			transaction.setCrmData(crm);

			// Set to invalid transaction
			transaction.setValid(false);

			LOG.error(LanguageResources.getFormattedString("Equation4Session.X56HMRBefore", transaction.getAPIName()) + "\n"
							+ beforeGzDsStr + "\n");
			LOG.error(LanguageResources.getFormattedString("Equation4Session.X56HMRFailure", transaction.getAPIName()) + "\n"
							+ transaction + "\n" + getMessages(aoe));
		}

		// print timing test
		EqTimingTest.printTime("Equation4Session.callX56HMR()", transaction.getAPIName());
		return transaction;
	}

	/**
	 * Call H56HMR to execute an <codE>EquationStandardTransaction</code>
	 * 
	 * @param the
	 *            transaction to execute
	 * @param the
	 *            mode to call H56 in
	 */
	protected EquationStandardTransaction callH56HMR(EquationStandardTransaction transaction, String mode) throws EQException
	{
		// H56 entry plist declaration
		String rpgm = "";
		String wsid = "";
		String usid = "";
		String jtt = "";
		byte[] gza = null;
		String rrec = "";
		String rmes7 = "";
		String aow = "";
		String etk = "";
		int etn;
		String aext = "";
		String arec = "";
		// work fields
		String beforeGzDsStr = "";
		CallableStatement equationBasicTransactionAPICaller = null;

		try
		{
			// print timing test
			EqTimingTest.printStartTime("Equation4Session.callH56HMR()", transaction.getAPIName());

			// Set the H56 parameter variables
			rpgm = transaction.getAPIName();
			usid = userId.substring(0, 4);
			wsid = transaction.getWorkStationId(); // workstation id
			jtt = transaction.getMode(); // transaction type (A, M, ...)
			gza = transaction.getBytes();
			etn = 0;

			rrec = "";

			if (mode.equals(IEquationStandardObject.FCT_VAL))
			{
				rrec = mode; // Validate - see EQ Journal File definition manual
			}
			if ((mode.equals(IEquationStandardObject.FCT_RTV)) || mode.equals("R"))
			{
				jtt = mode; // Retrieval - see EQ Journal File definition manual
			}
			if (transaction.getAPIName().startsWith("K61A") && mode.equals(IEquationStandardObject.FCT_RTV)
							&& transaction.getByteCP() != 0x20)
			{
				jtt = "R"; // Clean Payments retrieval mode
			}
			// output parameters
			rmes7 = ""; // Error message
			aow = ""; // Warning messages
			aext = transaction.getAext();
			arec = transaction.getArec();

			equationBasicTransactionAPICaller = (CallableStatement) getEqStatement(EQStatementType.BASIC_TRANSACTION_API_CALLER);
			// Set the X56 parameter variables in the X56 SQL procedure
			equationBasicTransactionAPICaller.setString(1, Toolbox.trim(rpgm, 10));
			equationBasicTransactionAPICaller.setString(2, Toolbox.trim(branchId, 4));
			equationBasicTransactionAPICaller.setString(3, Toolbox.trim(usid, 4));
			equationBasicTransactionAPICaller.setString(4, Toolbox.trim(wsid, 4));
			equationBasicTransactionAPICaller.setString(5, Toolbox.trim(jtt, 1));
			equationBasicTransactionAPICaller.setBytes(6, gza); // DSAIM?
			equationBasicTransactionAPICaller.setString(7, Toolbox.trim(rrec, 1));
			equationBasicTransactionAPICaller.setString(8, rmes7);
			equationBasicTransactionAPICaller.setString(9, aow);
			equationBasicTransactionAPICaller.setString(10, etk);
			equationBasicTransactionAPICaller.setInt(11, etn);

			// We want to be able to process all the returned parameters
			equationBasicTransactionAPICaller.registerOutParameter(1, Types.CHAR);
			equationBasicTransactionAPICaller.registerOutParameter(2, Types.CHAR);
			equationBasicTransactionAPICaller.registerOutParameter(3, Types.CHAR);
			equationBasicTransactionAPICaller.registerOutParameter(4, Types.CHAR);
			equationBasicTransactionAPICaller.registerOutParameter(5, Types.CHAR);
			equationBasicTransactionAPICaller.registerOutParameter(6, Types.VARCHAR);
			equationBasicTransactionAPICaller.registerOutParameter(7, Types.CHAR);
			equationBasicTransactionAPICaller.registerOutParameter(8, Types.VARCHAR);
			equationBasicTransactionAPICaller.registerOutParameter(9, Types.VARCHAR);
			equationBasicTransactionAPICaller.registerOutParameter(10, Types.CHAR);
			equationBasicTransactionAPICaller.registerOutParameter(11, Types.INTEGER);

			// store string
			beforeGzDsStr = transaction.toString();

			// Call the API
			equationBasicTransactionAPICaller.execute();

			// Set the returned values
			rpgm = equationBasicTransactionAPICaller.getString(1);
			branchId = equationBasicTransactionAPICaller.getString(2);
			usid = equationBasicTransactionAPICaller.getString(3);
			wsid = equationBasicTransactionAPICaller.getString(4);
			jtt = equationBasicTransactionAPICaller.getString(5);
			gza = equationBasicTransactionAPICaller.getBytes(6);
			rrec = equationBasicTransactionAPICaller.getString(7);
			rmes7 = equationBasicTransactionAPICaller.getString(8);
			aow = equationBasicTransactionAPICaller.getString(9);
		}
		catch (SQLException e)
		{
			LOG.error(e);
			LOG.error("Failed Execution of H56HMR:" + "RPGM=" + rpgm + ";WSID=" + wsid + ";USID=" + usid + ";AEXT=" + aext
							+ ";AREC=" + arec);

			LOG.error(LanguageResources.getFormattedString("Equation4Session.H56HMRBefore", transaction.getAPIName()) + "\n"
							+ beforeGzDsStr + "\n");
			LOG.error(LanguageResources.getFormattedString("Equation4Session.H56HMRFailure", transaction.getAPIName()) + "\n"
							+ transaction + "\n" + getMessages(rmes7));

			throw new EQException("Equation4Session - callH56HMR() failed: " + Toolbox.getExceptionMessage(e), e);
		}
		finally
		{
			SQLToolbox.close(equationBasicTransactionAPICaller);
		}

		// API successfully applied? Note: W=Warnings (e.g. H15ARR)
		if (rrec.equals("R") || rrec.equals("W"))
		{
			transaction.setGzBytes(gza);
			transaction.setValid(true);
			transaction.setWarningList(getMessages(aow));

			// GS defined?
			int offset = transaction.getGsOffset();
			if (offset > 0)
			{
				byte[] gs = new byte[gza.length - offset + 1];
				System.arraycopy(gza, offset - 1, gs, 0, gza.length - offset + 1);
				transaction.setGsBytes(gs);
			}

			// print the transaction data
			LOG.debug(LanguageResources.getFormattedString("Equation4Session.H56HMRBefore", transaction.getAPIName()) + "\n"
							+ beforeGzDsStr + "\n");
			LOG.debug(LanguageResources.getFormattedString("Equation4Session.H56HMRAfter", transaction.getAPIName()) + "\n"
							+ transaction + "\n" + getMessages(rmes7));
		}
		else
		{
			if (rmes7.trim().length() == 0)
			{
				rmes7 = "KSM7364" + transaction.getAPIName();
			}
			transaction.setErrorList(getMessages(rmes7));

			// Set to invalid transaction
			transaction.setValid(false);

			LOG.error(LanguageResources.getFormattedString("Equation4Session.H56HMRBefore", transaction.getAPIName()) + "\n"
							+ beforeGzDsStr + "\n");
			LOG.error(LanguageResources.getFormattedString("Equation4Session.H56HMRFailure", transaction.getAPIName()) + "\n"
							+ transaction + "\n" + getMessages(rmes7));
		}

		// print timing test
		EqTimingTest.printTime("Equation4Session.callH56HMR()", transaction.getAPIName());
		return transaction;
	}

	/*
	 * @Override protected EQMessage updateGH(String mode, String workStation, String userId, int jobNumber, String sessionId,
	 * String optionId, String program, String cc, String app, String logUser, String tcpIp) throws EQException { // No GH Updating
	 * at levels of BFEQ prior to EQ4, return empty message and continue return new EQMessage(); }
	 */

	/**
	 * Call the SGH02R to update the GH record
	 * 
	 * @param mode
	 *            - Add or Maintain or Delete
	 * @param workStation
	 *            - workstation Id
	 * @param userId
	 *            - user Id
	 * @param jobNumber
	 *            - job number
	 * @param sessionId
	 *            - session Id
	 * @param optionId
	 *            - option Id
	 * @param program
	 *            - program name
	 * @param cc
	 *            - cc flag?
	 * @param app
	 *            - application
	 * @param logUser
	 *            - user
	 * @param tcpIp
	 *            - tcp ip address
	 * 
	 * @return the error message (if any)
	 * 
	 * @throws EQException
	 */
	protected EQMessage updateGH(String mode, String workStation, String userId, int jobNumber, String sessionId, String optionId,
					String program, String cc, String app, String logUser, String tcpIp) throws EQException
	{
		// remove connection from the GH file (only available at EQ4)
		if (unit.getUnitVersion().equals(EquationUnit.VERSION_EQ4))
		{
			callSGH02R(mode, workStation, userId, jobNumber, sessionId, optionId, program, cc, app, logUser, tcpIp);
		}
		// as of now, return null as errors in the program will be ignored
		return null;
	}

	/**
	 * Call the SGH02R to update the GH record
	 * 
	 * @param mode
	 *            - Add or Maintain or Delete
	 * @param workStation
	 *            - workstation Id
	 * @param userId
	 *            - user Id
	 * @param jobNumber
	 *            - job number
	 * @param sessionId
	 *            - session Id
	 * @param optionId
	 *            - option Id
	 * @param program
	 *            - program name
	 * @param cc
	 *            - cc flag?
	 * @param app
	 *            - application
	 * @param logUser
	 *            - user
	 * @param tcpIp
	 *            - tcp ip address
	 * 
	 * @return the error message (if any)
	 * 
	 * @throws EQException
	 */
	private String callSGH02R(String mode, String workStation, String userId, int jobNumber, String sessionId, String optionId,
					String program, String cc, String app, String logUser, String tcpIp) throws EQException
	{
		try
		{
			if (equationGHUpdateCaller == null)
			{
				equationGHUpdateCaller = (CallableStatement) getEqStatement(EQStatementType.GH_UPDATE_CALLER);
			}
			// Register all parameters
			equationGHUpdateCaller.registerOutParameter(2, Types.CHAR);

			// Set the parameters
			equationGHUpdateCaller.setString(1, Toolbox.trim(mode, 1));
			equationGHUpdateCaller.setString(3, Toolbox.trim(workStation, 10));
			equationGHUpdateCaller.setString(4, Toolbox.trim(userId, 10));
			equationGHUpdateCaller.setInt(5, jobNumber);
			equationGHUpdateCaller.setString(6, Toolbox.trim(sessionId, 50));
			equationGHUpdateCaller.setString(7, Toolbox.trim(optionId, 3));
			equationGHUpdateCaller.setString(8, Toolbox.trim(program, 10));
			equationGHUpdateCaller.setString(9, Toolbox.trim(cc, 1));
			equationGHUpdateCaller.setString(10, Toolbox.trim(app, 10));
			equationGHUpdateCaller.setString(11, Toolbox.trim(logUser, 10));
			equationGHUpdateCaller.setString(12, Toolbox.trim(tcpIp, 15));

			// Call the API
			equationGHUpdateCaller.execute();

			// Retrieve the returned values
			String dsepms = equationGHUpdateCaller.getString(2);

			return dsepms;
		}
		catch (SQLException e)
		{
			throw new EQException(LanguageResources.getFormattedString("Equation4Session.ErrorCallingProgram", "SGH02R") + ": "
							+ Toolbox.getExceptionMessage(e));
		}
	}
	@Override
	public boolean isWEYPFBdtaInstalled() throws SQLException
	{
		// file won't exist at this level so always return false
		return false;
	}
}