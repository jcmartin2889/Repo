package com.misys.equation.function.runtime;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.utilities.EqDataToolbox;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;

public class ScreenSetAC3 extends ScreenSet
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ScreenSetAC3.java 14803 2012-11-05 11:57:09Z williae1 $";

	// Log
	private static final EquationLogger LOG = new EquationLogger(ScreenSetAC3.class);

	public final static String OPTION = "AC3";
	public final static String DESKTOP_GZFLAG = "U";

	private int sourceIndex;
	private FunctionData sourceData;

	/**
	 * Construct a Function Screen AC3
	 * 
	 * @param id
	 *            - screen set id
	 * @param functionHandlerData
	 *            the Function Handler Data
	 * 
	 * @throws EQException
	 */
	public ScreenSetAC3(int id, FunctionHandlerData fhd) throws EQException
	{
		super(id, fhd, OPTION);
	}

	/**
	 * On exit, remove this from the list again
	 * 
	 * @param sourceScreenSetId
	 *            - screen set Id of the current screen set
	 * 
	 * @return SCRN_PREV to display the previous function<br>
	 * 
	 */
	@Override
	protected int onExitScreenSetToPrev(int sourceScreenSetId)
	{
		// supervisor mode?
		if (securityLevel.getCheckerType() == SecurityLevel.CHCKR_SUPER)
		{
			return SCRN_PREV;
		}

		// remove this from the list of functions
		screenSetHandler.getScreenSets().remove(id);

		return SCRN_PREV;
	}

	/**
	 * On exit, move all the value back to the ACG function
	 * 
	 * @param sourceScreenSetId
	 *            - screen set Id of the current screen set
	 * 
	 * @return SCRN_PREV to display the previous function
	 * 
	 */
	@Override
	protected int onExitScreenSetToNext(int sourceScreenSetId)
	{
		// supervisor mode?
		if (securityLevel.getCheckerType() == SecurityLevel.CHCKR_SUPER)
		{
			return SCRN_PREV;
		}

		// move the function details
		outData();
		screenSetHandler.getScreenSets().remove(id);

		return SCRN_PREV;
	}

	/**
	 * Transfer the data from the source (identified by the index) into this function
	 * 
	 * @param sourceData
	 *            - the source Function data
	 * @param index
	 *            - the source index
	 * 
	 * @return true
	 */
	protected boolean inData(FunctionData sourceData, int index)
	{
		this.sourceIndex = index;
		this.sourceData = sourceData;
		String is = "_" + sourceIndex;

		// from the HZ details (fix details)
		functionData.chgFieldValues("BRNM", sourceData.rtvFieldData("HZBRNM"));
		functionData.chgFieldValues("DLP", sourceData.rtvFieldData("HZDLP"));
		functionData.chgFieldValues("DLR", sourceData.rtvFieldData("HZDLR"));
		functionData.chgFieldValues("AB", sourceData.rtvFieldData("HZAB"));
		functionData.chgFieldValues("AN", sourceData.rtvFieldData("HZAN"));
		functionData.chgFieldValues("AS", sourceData.rtvFieldData("HZAS"));
		functionData.chgFieldValues("CMR", sourceData.rtvFieldData("HZCMR"));
		functionData.chgFieldValues("CHGS", sourceData.rtvFieldData("HZCHDS"));
		functionData.chgFieldValues("EVNM", sourceData.rtvFieldData("HZEVNT"));
		functionData.chgFieldValues("REF", sourceData.rtvFieldData("REF"));
		functionData.chgFieldValues("ATP", sourceData.rtvFieldData("HZATP"));

		// from the HZ details (repeating details)
		functionData.chgFieldValues("COAD", sourceData.rtvFieldData("COAD" + is));

		// key details
		functionData.chgFieldValues("TREF", sourceData.rtvFieldData("TREF"));
		functionData.chgFieldValues("CAB", sourceData.rtvFieldData("CAB"));
		functionData.chgFieldValues("CAN", sourceData.rtvFieldData("CAN"));
		functionData.chgFieldValues("CAS", sourceData.rtvFieldData("CAS"));
		functionData.chgFieldValues("DDTD", sourceData.rtvFieldData("DDTD"));
		functionData.chgFieldValues("AREF", sourceData.rtvFieldData("AREF"));
		functionData.chgFieldValues("ESQN", sourceData.rtvFieldData("ESQN"));
		functionData.chgFieldValues("EESQN", sourceData.rtvFieldData("EESQN"));
		functionData.chgFieldValues("TCCY", sourceData.rtvFieldData("TCCY"));
		functionData.chgFieldValues("TAMT", sourceData.rtvFieldData("TAMT"));

		// from the GS details
		functionData.chgFieldValues("ECNM", sourceData.rtvFieldData("GSECNM" + is));
		functionData.chgFieldValues("CHGC", sourceData.rtvFieldData("GSCHGC" + is));
		functionData.chgFieldValues("AMT", sourceData.rtvFieldData("GSCHA" + is));
		functionData.chgFieldValues("PBRR", sourceData.rtvFieldData("GSBRR" + is));
		functionData.chgFieldValues("PRAT", sourceData.rtvFieldData("GSPAMT" + is));
		functionData.chgFieldValues("CAMT", sourceData.rtvFieldData("GSCAOD" + is));
		functionData.chgFieldValues("MINC", sourceData.rtvFieldData("GSMIND" + is));
		functionData.chgFieldValues("MAXC", sourceData.rtvFieldData("GSMAXD" + is));
		functionData.chgFieldValues("WAMT", sourceData.rtvFieldData("GSWAMD" + is));
		functionData.chgFieldValues("CNR1", sourceData.rtvFieldData("GSNA1" + is));
		functionData.chgFieldValues("CNR2", sourceData.rtvFieldData("GSNA2" + is));
		functionData.chgFieldValues("CNR3", sourceData.rtvFieldData("GSNA3" + is));
		functionData.chgFieldValues("CNR4", sourceData.rtvFieldData("GSNA4" + is));
		functionData.chgFieldValues("CCY", sourceData.rtvFieldData("GSCCYC" + is));
		functionData.chgFieldValues("SDTE", sourceData.rtvFieldData("GSSDT" + is));
		functionData.chgFieldValues("EDTE", sourceData.rtvFieldData("GSEND" + is));
		functionData.chgFieldValues("FRQ", sourceData.rtvFieldData("GSFRQ" + is));
		functionData.chgFieldValues("FAB", sourceData.rtvFieldData("GSFAB" + is));
		functionData.chgFieldValues("FAN", sourceData.rtvFieldData("GSFAN" + is));
		functionData.chgFieldValues("FAS", sourceData.rtvFieldData("GSFAS" + is));
		functionData.chgFieldValues("CPI", sourceData.rtvFieldData("GSCAP" + is));
		functionData.chgFieldValues("FORC", sourceData.rtvFieldData("GSFTF" + is));
		functionData.chgFieldValues("PNR1", sourceData.rtvFieldData("GSNP1" + is));
		functionData.chgFieldValues("PNR2", sourceData.rtvFieldData("GSNP2" + is));
		functionData.chgFieldValues("PNR3", sourceData.rtvFieldData("GSNP3" + is));
		functionData.chgFieldValues("PNR4", sourceData.rtvFieldData("GSNP4" + is));
		functionData.chgFieldValues("SRC", sourceData.rtvFieldData("GSSRC" + is));
		functionData.chgFieldValues("UC1", sourceData.rtvFieldData("GSUC1" + is));
		functionData.chgFieldValues("UC2", sourceData.rtvFieldData("GSUC2" + is));
		functionData.chgFieldValues("TAX", sourceData.rtvFieldData("GSTAX" + is));
		functionData.chgFieldValues("EXCL", sourceData.rtvFieldData("GSEXCL" + is));
		functionData.chgFieldValues("CCAL", sourceData.rtvFieldData("GSCCAL" + is));
		functionData.chgFieldValues("REGF", sourceData.rtvFieldData("GSREGF" + is));

		functionData.chgFieldValues("CALA", sourceData.rtvFieldData("CALA" + is));
		functionData.chgFieldValues("CHGA", sourceData.rtvFieldData("CHGA" + is));

		// fix values
		functionData.chgFieldDbValue("DIGIT", "15");
		functionData.chgFieldDbValue("DECI", "0");

		// default posting narrative
		if (functionData.rtvFieldInputValue("PNR1").trim().length() == 0)
		{
			functionData.chgFieldDbValue("PNR1", functionData.rtvFieldOutputValue("CHGC"));
		}

		return true;
	}

	/**
	 * Transfer the data from this function back to the source
	 * 
	 * @param targetData
	 *            - the target Function data
	 * 
	 * @return true
	 */
	protected boolean outData()
	{
		String is = "_" + sourceIndex;

		// maintainable fields only
		sourceData.chgFieldValues("GSCHA" + is, functionData.rtvFieldData("AMT"));
		sourceData.chgFieldValues("GSBRR" + is, functionData.rtvFieldData("PBRR"));
		sourceData.chgFieldValues("GSPAMT" + is, functionData.rtvFieldData("PRAT"));
		sourceData.chgFieldValues("GSWAMD" + is, functionData.rtvFieldData("WAMT"));
		sourceData.chgFieldValues("GSNA1" + is, functionData.rtvFieldData("CNR1"));
		sourceData.chgFieldValues("GSNA2" + is, functionData.rtvFieldData("CNR2"));
		sourceData.chgFieldValues("GSNA3" + is, functionData.rtvFieldData("CNR3"));
		sourceData.chgFieldValues("GSNA4" + is, functionData.rtvFieldData("CNR4"));
		sourceData.chgFieldValues("GSSDT" + is, functionData.rtvFieldData("SDTE"));
		sourceData.chgFieldValues("GSEND" + is, functionData.rtvFieldData("EDTE"));
		sourceData.chgFieldValues("GSFRQ" + is, functionData.rtvFieldData("FRQ"));
		sourceData.chgFieldValues("GSFAB" + is, functionData.rtvFieldData("FAB"));
		sourceData.chgFieldValues("GSFAN" + is, functionData.rtvFieldData("FAN"));
		sourceData.chgFieldValues("GSFAS" + is, functionData.rtvFieldData("FAS"));
		sourceData.chgFieldValues("GSCAP" + is, functionData.rtvFieldData("CPI"));
		sourceData.chgFieldValues("GSFTF" + is, functionData.rtvFieldData("FORC"));
		sourceData.chgFieldValues("GSNP1" + is, functionData.rtvFieldData("PNR1"));
		sourceData.chgFieldValues("GSNP2" + is, functionData.rtvFieldData("PNR2"));
		sourceData.chgFieldValues("GSNP3" + is, functionData.rtvFieldData("PNR3"));
		sourceData.chgFieldValues("GSNP4" + is, functionData.rtvFieldData("PNR4"));
		sourceData.chgFieldValues("GSSRC" + is, functionData.rtvFieldData("SRC"));
		sourceData.chgFieldValues("GSUC1" + is, functionData.rtvFieldData("UC1"));
		sourceData.chgFieldValues("GSUC2" + is, functionData.rtvFieldData("UC2"));
		sourceData.chgFieldValues("GSTAX" + is, functionData.rtvFieldData("TAX"));

		// display in the subfile
		sourceData.chgFieldValues("CALA" + is, functionData.rtvFieldData("CALA"));
		sourceData.chgFieldValues("CHGA" + is, functionData.rtvFieldData("CHGA"));

		// control status
		sourceData.chgFieldDbValue("ADDS", "2");
		sourceData.chgFieldDbValue("MSGSTS" + is, String.valueOf(functionMessages.getMsgSev()));

		return true;
	}

	/**
	 * Performs validation on the specified screens
	 * 
	 * @param fromScrnNo
	 *            - from screen number
	 * @param toScrnNo
	 *            - to screen number
	 * 
	 * @return the message severity
	 * 
	 * @throws EQException
	 */
	@Override
	public int validate(int fromScrnNo, int toScrnNo) throws EQException
	{
		// create the validator
		FunctionValidate funcValidate = new FunctionValidate(fhd);

		// validate it
		int msgSev = funcValidate.validate(fromScrnNo, toScrnNo);

		// function messages
		functionMessages = funcValidate.getFunctionMessages();

		// if no major severity, then perform further validation via API
		EquationStandardTransaction transaction = null;
		if (msgSev < FunctionMessages.MSG_ERROR)
		{
			if (toScrnNo == maxScrnNo - 1)
			{
				transaction = validateDetail(DESKTOP_GZFLAG);
			}
		}

		// calculated fields
		msgSev = functionMessages.getMsgSev();
		if (msgSev < FunctionMessages.MSG_ERROR)
		{
			long gscha = Long.parseLong(transaction.getGSField("GSCHA"));
			long gswamd = Long.parseLong(transaction.getGSField("GSWAMD"));

			functionData.chgFieldDbValue("CHGA", transaction.getGSField("GSCHA"));
			functionData.chgFieldDbValue("CALA", String.valueOf(gscha + gswamd));

			// edit calculated charge amount
			functionData.chgFieldOutputValue("CALA", EqDataToolbox.editAmount(equationStandardSession, "", functionData
							.rtvFieldDbValue("CALA"), functionData.rtvFieldDbValue("CCY"), 0, 15));

			// edit charge amount
			functionData.chgFieldOutputValue("CHGA", EqDataToolbox.editAmount(equationStandardSession, "", functionData
							.rtvFieldInputValue("CHGA"), functionData.rtvFieldDbValue("CCY"), 0, 15));
		}

		// try to parse the error messages and try to highlight affected fields
		for (FunctionMessage fm : functionMessages.getMessages())
		{
			// No date found that corresponds to frequency
			String ksmId = fm.getEqMessage().getMessageID();
			if (ksmId.equals("KSM2161"))
			{
				functionData.rtvFieldData("FRQ").getFunctionMessages().insertMessage(fm.getScreenSetId(), fm.getScrnNo(),
								fm.getFieldName(), fm.getSequence(), fm.getEqMessage(), fm.getFirstLevelText(),
								fm.getSecondLevelText());
				functionData.rtvFieldData("SDTE").getFunctionMessages().insertMessage(fm.getScreenSetId(), fm.getScrnNo(),
								fm.getFieldName(), fm.getSequence(), fm.getEqMessage(), fm.getFirstLevelText(),
								fm.getSecondLevelText());
				functionData.rtvFieldData("EDTE").getFunctionMessages().insertMessage(fm.getScreenSetId(), fm.getScrnNo(),
								fm.getFieldName(), fm.getSequence(), fm.getEqMessage(), fm.getFirstLevelText(),
								fm.getSecondLevelText());
			}
			// Start date and end date are the same for
			else if (ksmId.equals("KSM3571"))
			{
				functionData.rtvFieldData("SDTE").getFunctionMessages().insertMessage(fm.getScreenSetId(), fm.getScrnNo(),
								fm.getFieldName(), fm.getSequence(), fm.getEqMessage(), fm.getFirstLevelText(),
								fm.getSecondLevelText());
				functionData.rtvFieldData("EDTE").getFunctionMessages().insertMessage(fm.getScreenSetId(), fm.getScrnNo(),
								fm.getFieldName(), fm.getSequence(), fm.getEqMessage(), fm.getFirstLevelText(),
								fm.getSecondLevelText());
			}
			// No available balance / Debit Balance not allowed / Funding currency differs from charge currency
			else if (ksmId.equals("KSM0140") || ksmId.equals("KSM5417") || ksmId.equals("KSM3451"))
			{
				functionData.rtvFieldData("FAB").getFunctionMessages().insertMessage(fm.getScreenSetId(), fm.getScrnNo(),
								fm.getFieldName(), fm.getSequence(), fm.getEqMessage(), fm.getFirstLevelText(),
								fm.getSecondLevelText());
			}
			// Transaction is not eligible for queuing
			else if (ksmId.equals("KSM2647"))
			{
				functionData.rtvFieldData("FORC").getFunctionMessages().insertMessage(fm.getScreenSetId(), fm.getScrnNo(),
								fm.getFieldName(), fm.getSequence(), fm.getEqMessage(), fm.getFirstLevelText(),
								fm.getSecondLevelText());
			}
			// Percentage charge
			else if (ksmId.equals("KSM2857"))
			{
				functionData.rtvFieldData("PRAT").getFunctionMessages().insertMessage(fm.getScreenSetId(), fm.getScrnNo(),
								fm.getFieldName(), fm.getSequence(), fm.getEqMessage(), fm.getFirstLevelText(),
								fm.getSecondLevelText());
			}
			// Waived amount
			else if (ksmId.equals("KSM2853"))
			{
				functionData.rtvFieldData("WAMT").getFunctionMessages().insertMessage(fm.getScreenSetId(), fm.getScrnNo(),
								fm.getFieldName(), fm.getSequence(), fm.getEqMessage(), fm.getFirstLevelText(),
								fm.getSecondLevelText());
			}
		}

		return msgSev;
	}
	/**
	 * Validate details via calling the C62ARR
	 * 
	 * @param mode
	 *            - the transaction mode to call C62ARR
	 * 
	 * @return the Equation standard transaction
	 * 
	 * @throws EQException
	 */
	private EquationStandardTransaction validateDetail(String mode) throws EQException
	{
		EquationStandardTransaction transaction = new EquationStandardTransaction(getACGApiName(), equationStandardSession, 1000);
		loadDetailsToTransaction(transaction);
		transaction.setFieldValue("GZFLAG", mode);
		eqUser.getSession().validateEquationTransaction(transaction);
		processReturnedMessage(transaction);

		// highest severity message
		return transaction;
	}

	/**
	 * Load the function data into the transaction
	 * 
	 * @param transaction
	 *            - the Equation standard transaction for ACG
	 */
	private void loadDetailsToTransaction(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZFLAG", "T"); // Linked ACG
		transaction.setFieldValue("GZDLP", functionData.rtvFieldDbValue("DLP"));
		transaction.setFieldValue("GZDLR", functionData.rtvFieldDbValue("DLR"));
		transaction.setFieldValue("GZDBRM", functionData.rtvFieldDbValue("BRNM"));
		transaction.setFieldValue("GZCMR", functionData.rtvFieldDbValue("CMR"));
		transaction.setFieldValue("GZAB", functionData.rtvFieldDbValue("AB"));
		transaction.setFieldValue("GZAN", functionData.rtvFieldDbValue("AN"));
		transaction.setFieldValue("GZAS", functionData.rtvFieldDbValue("AS"));
		transaction.setFieldValue("GZEVNM", functionData.rtvFieldDbValue("EVNM"));
		transaction.setFieldValue("GZTREF", functionData.rtvFieldDbValue("TREF"));
		transaction.setFieldValue("GZCAB", functionData.rtvFieldDbValue("CAB"));
		transaction.setFieldValue("GZCAN", functionData.rtvFieldDbValue("CAN"));
		transaction.setFieldValue("GZCAS", functionData.rtvFieldDbValue("CAS"));
		transaction.setFieldValue("GZDDTD", functionData.rtvFieldDbValue("DDTD"));
		transaction.setFieldValue("GZAREF", functionData.rtvFieldDbValue("AREF"));
		transaction.setFieldValue("GZSQN", functionData.rtvFieldDbValue("ESQN"));
		transaction.setFieldValue("GZESQN", functionData.rtvFieldDbValue("EESQN"));
		transaction.setFieldValue("GZCCYA", functionData.rtvFieldDbValue("TCCY"));
		transaction.setFieldValue("GZCHA", functionData.rtvFieldDbValue("TAMT"));
		transaction.setFieldValue("GZATP", functionData.rtvFieldDbValue("ATP"));

		transaction.setFieldValue("GZDCHG", "N");
		transaction.setFieldValue("GZVAL", "");
		transaction.setFieldValue("GZUSER", fhd.getEquationUser().getInputBranch());
		transaction.setGSFieldValue("GSECNM", functionData.rtvFieldDbValue("ECNM"));
		transaction.setGSFieldValue("GSCHGC", "");
		transaction.setGSFieldValue("GSCHA", String.valueOf(Long.parseLong(functionData.rtvFieldDbValue("AMT"))
						- Long.parseLong(functionData.rtvFieldDbValue("WAMT"))));
		transaction.setGSFieldValue("GSBRR", functionData.rtvFieldDbValue("PBRR"));
		transaction.setGSFieldValue("GSPAMT", functionData.rtvFieldDbValue("PRAT"));
		transaction.setGSFieldValue("GSSDT", functionData.rtvFieldDbValue("SDTE"));
		transaction.setGSFieldValue("GSEND", functionData.rtvFieldDbValue("EDTE"));
		transaction.setGSFieldValue("GSFRQ", functionData.rtvFieldDbValue("FRQ"));
		transaction.setGSFieldValue("GSNA1", functionData.rtvFieldDbValue("CNR1"));
		transaction.setGSFieldValue("GSNA2", functionData.rtvFieldDbValue("CNR2"));
		transaction.setGSFieldValue("GSNA3", functionData.rtvFieldDbValue("CNR3"));
		transaction.setGSFieldValue("GSNA4", functionData.rtvFieldDbValue("CNR4"));
		transaction.setGSFieldValue("GSWAMD", functionData.rtvFieldDbValue("WAMT"));
		transaction.setGSFieldValue("GSMIND", "");
		transaction.setGSFieldValue("GSMAXD", "");
		transaction.setGSFieldValue("GSFAB", functionData.rtvFieldDbValue("FAB"));
		transaction.setGSFieldValue("GSFAN", functionData.rtvFieldDbValue("FAN"));
		transaction.setGSFieldValue("GSFAS", functionData.rtvFieldDbValue("FAS"));
		transaction.setGSFieldValue("GSCAP", functionData.rtvFieldDbValue("CPI"));
		transaction.setGSFieldValue("GSNP1", functionData.rtvFieldDbValue("PNR1"));
		transaction.setGSFieldValue("GSNP2", functionData.rtvFieldDbValue("PNR2"));
		transaction.setGSFieldValue("GSNP3", functionData.rtvFieldDbValue("PNR3"));
		transaction.setGSFieldValue("GSNP4", functionData.rtvFieldDbValue("PNR4"));
		transaction.setGSFieldValue("GSSRC", functionData.rtvFieldDbValue("SRC"));
		transaction.setGSFieldValue("GSUC1", functionData.rtvFieldDbValue("UC1"));
		transaction.setGSFieldValue("GSUC2", functionData.rtvFieldDbValue("UC2"));
		transaction.setGSFieldValue("GSTAX", "");
		transaction.setGSFieldValue("GSFTF", functionData.rtvFieldDbValue("FORC"));
		transaction.setGSFieldValue("GSEXCL", functionData.rtvFieldDbValue("EXCL"));
		transaction.setGSFieldValue("GSCAOD", functionData.rtvFieldDbValue("CAMT"));
		transaction.setGSFieldValue("GSCCYC", functionData.rtvFieldDbValue("CCY"));
	}

	/**
	 * Process the messages returned by the transaction
	 * 
	 * @param transaction
	 *            - the transaction
	 * 
	 * @throws EQException
	 */
	private void processReturnedMessage(EquationStandardTransaction transaction) throws EQException
	{
		// assume it will not be copied
		boolean copy = false;

		// any errors?
		List<EQMessage> eqMessages = transaction.getErrorList();
		copy = (eqMessages.size() > 0 && functionMessages.getMsgSev() <= FunctionMessages.MSG_ERROR);

		// any warnings?
		if (!copy && functionMessages.getMsgSev() < FunctionMessages.MSG_ERROR)
		{
			eqMessages = transaction.getWarningList();
			copy = (eqMessages.size() > 0);
		}

		// copy the messages?
		if (copy)
		{
			for (int j = 0; j < eqMessages.size(); j++)
			{
				functionMsgManager.generateMessage(equationStandardSession, functionMessages, id, scrnNo, "", sourceIndex, null,
								eqMessages.get(j).getDsepms(), "", LanguageResources
												.getString("ScreenSetAC3.ReturnedMessageFromACGAPI"), FunctionMessages.MSG_NONE);
			}
		}

	}

	/**
	 * Performs update on the specified screen
	 * 
	 * @param journalHeader
	 *            - the Journal Header
	 * @param functionTransactions
	 *            - the list of transactions already executed prior to this update
	 * @param session
	 *            - the Equation standard session
	 * 
	 * @return true - this screen has already handled the updated processing<br>
	 *         false - let the standard process perform update
	 * 
	 * @throws EQException
	 */
	@Override
	public boolean update(JournalHeader journalHeader, FunctionTransactions functionTransactions, EquationStandardSession session)
					throws EQException
	{
		EquationStandardTransaction transaction = new EquationStandardTransaction(getACGApiName(), session, 1000);
		loadDetailsToTransaction(transaction);
		transaction.setWorkStationId(journalHeader.getWorkstationID());
		transaction.setDsgyCtr(FunctionRuntimeToolbox.getGYCtr(journalHeader));
		transaction.setDsgyDet(FunctionRuntimeToolbox.getGYDet(journalHeader));
		transaction.setArec(EqDataType.YES);
		transaction.setAext(EqDataType.YES);

		// Print the function data
		LOG.debug(LanguageResources.getString("FunctionTransactions.Debug.FunctionData.Before") + "\n" + functionData.toString()
						+ "\n");

		session.applyEquationTransaction(transaction);
		functionMessages.clearMessages();
		processReturnedMessage(transaction);
		functionTransactions.addTransaction("AC3" + id, transaction);

		return true;
	}

	/**
	 * Generate the function key for the screen
	 * 
	 * @return the function keys
	 * 
	 */
	@Override
	protected FunctionKeys generateFkeys() throws EQException
	{
		super.generateFkeys();
		functionKeys.deleteKey(FunctionKeys.KEY_SAVE);
		functionKeys.deleteKey(FunctionKeys.KEY_SVTMPL);
		functionKeys.deleteKey(FunctionKeys.KEY_CHARGE);
		functionKeys.deleteKey(FunctionKeys.KEY_DEL);
		functionKeys.deleteKey(FunctionKeys.KEY_PRINT);
		functionKeys.deleteKey(FunctionKeys.KEY_EXCEL);
		return functionKeys;
	}

	/**
	 * Perform ScreenSet specific field visibility routine
	 * 
	 * @param inputFieldSet
	 *            - the input field set where the field belongs to
	 * @param inputField
	 *            - the input field
	 * @param displayAttributesSet
	 *            - the display attributes set
	 * @param displayAttributes
	 *            - the display attributes
	 * @param currentStatus
	 *            - the current status of the field - protected (true) / non-protected (false)
	 * 
	 * @return true if field must be visible
	 */
	@Override
	public boolean fieldVisiblity(InputFieldSet inputFieldSet, InputField inputField, DisplayAttributesSet displayAttributeSet,
					DisplayAttributes displayAttributes, boolean currentStatus)
	{
		// determine whether fix or percentage charge
		String chargeCalc = functionData.rtvFieldDbValue("CCAL");
		String regular = functionData.rtvFieldDbValue("REGF");
		String loan = functionData.rtvFieldDbValue("DLR");

		// get the field name
		String fieldName = inputField.getId();

		// fix charge
		if (chargeCalc.equals("1"))
		{
			if (fieldName.equals("PBRR") || fieldName.equals("PRAT") || fieldName.equals("CAMT") || fieldName.equals("MINC")
							|| fieldName.equals("MAXC") || fieldName.equals("CALA"))
			{
				return false;
			}
		}

		// percentage charge
		else if (chargeCalc.equals("2"))
		{
			if (fieldName.equals("AMT"))
			{
				return false;
			}
		}

		// non-regular charge?
		if (!regular.equals("Y"))
		{
			if (fieldName.equals("FRQ"))
			{
				return false;
			}
		}

		// non-loan
		if (loan.equals(""))
		{
			if (fieldName.equals("CPI"))
			{
				return false;
			}
		}

		return currentStatus;
	}

	/**
	 * Perform ScreenSet specific field visibility routine
	 * 
	 * @param inputFieldSet
	 *            - the input field set where the field belongs to
	 * @param inputField
	 *            - the input field
	 * @param displayAttributesSet
	 *            - the display attributes set
	 * @param displayAttributes
	 *            - the display attributes
	 * @param currentStatus
	 *            - the current status of the field - mandatory (true) / optional (false)
	 * 
	 * @return true if field must be visible
	 */
	@Override
	public boolean fieldMandatory(InputFieldSet inputFieldSet, InputField inputField, boolean currentStatus)
	{
		// determine whether fix or percentage charge
		String regular = functionData.rtvFieldDbValue("REGF");

		// get the field name
		String fieldName = inputField.getId();

		// non-regular charge?
		if (regular.equals("Y"))
		{
			if (fieldName.equals("FRQ"))
			{
				return true;
			}
		}

		return currentStatus;
	}

	/**
	 * Perform ScreenSet specific field defaulting routine. This is only executed only if the field remains blank
	 * 
	 * @param inputFieldSet
	 *            - the input field set where the field belongs to
	 * @param inputField
	 *            - the input field
	 * 
	 * @return true if field must be protected
	 */
	@Override
	public String fieldDefaultValue(InputFieldSet inputFieldSet, InputField inputField)
	{
		// posting narrative 1
		if (inputField.getId().equals("PNR1"))
		{
			return functionData.rtvFieldOutputValue("CHGC");
		}

		return "";
	}

	/**
	 * Return the Add event charge API program
	 * 
	 * @return the Add event charge API program
	 */
	private String getACGApiName()
	{
		return "C62ARR" + "ACG";
	}

	/**
	 * Performs printing on the specified screen
	 * 
	 * @returns the list of lines
	 * 
	 * @throws EQException
	 */
	@Override
	protected List<String> print() throws EQException
	{
		List<String> lines = new ArrayList<String>();

		// Set up the function printer
		FunctionPrinter functionPrinter = new FunctionPrinter(fhd);
		functionPrinter.setPrintHeader(true);
		functionPrinter.setPrintBlankLine(true);
		functionPrinter.setPrintHiddenFields(false);

		// Read all the screens the print it
		int n = fhd.getScreenSetHandler().getCurScreenSet();
		for (int i = 0; i < 1; i++)
		{
			fhd.getScreenSetHandler().setCurScreenSet(i);
			functionPrinter.print(this);
			lines.addAll(functionPrinter.getLines());
			lines.add("");
			lines.add("");
		}
		fhd.getScreenSetHandler().setCurScreenSet(n);

		return lines;
	}
}
