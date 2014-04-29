package com.misys.equation.function.tools;

import java.util.List;
import java.util.Map;

import javax.el.ExpressionFactory;

import org.apache.el.ExpressionFactoryImpl;

import bf.com.misys.eqf.types.header.RqHeader;
import bf.com.misys.eqf.types.header.ServiceRqHeader;
import bf.com.misys.eqf.types.header.StartLRPProcessRsHeader;

import com.misys.equation.bankfusion.lrp.engine.ITaskEngine;
import com.misys.equation.bankfusion.lrp.engine.TaskEngineToolbox;
import com.misys.equation.bankfusion.tools.LRPToolbox;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationDataStructureData;
import com.misys.equation.common.access.EquationLogin;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.dao.beans.C8RecordDataModel;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.datastructure.EqDS_DSGYCTR;
import com.misys.equation.common.datastructure.EqDS_DSGYDET;
import com.misys.equation.common.datastructure.EqDS_DSHH03;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.utilities.CRMLimitCheckData;
import com.misys.equation.common.utilities.EqDataToolbox;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.EquationParameters;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.adaptor.AttributesAdaptor;
import com.misys.equation.function.adaptor.FieldAdaptor;
import com.misys.equation.function.adaptor.FunctionAdaptor;
import com.misys.equation.function.adaptor.ValueAdaptor;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayButtonLink;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.DisplayItemList;
import com.misys.equation.function.beans.DisplayLabel;
import com.misys.equation.function.beans.Element;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.FieldDefinition;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionControlData;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.IDisplayItem;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.InputGroup;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.beans.LoadFieldSetStatus;
import com.misys.equation.function.beans.Mapping;
import com.misys.equation.function.beans.RepeatingDataManager;
import com.misys.equation.function.beans.ReplacementToken;
import com.misys.equation.function.beans.TextBean;
import com.misys.equation.function.beans.WorkField;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.function.el.ELContextImpl;
import com.misys.equation.function.el.ELRuntime;
import com.misys.equation.function.el.EquationFunctionMapperImpl;
import com.misys.equation.function.journal.JournalFile;
import com.misys.equation.function.journal.JournalRecord;
import com.misys.equation.function.journal.ParentTransactionDetail;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.runtime.ConversionRules;
import com.misys.equation.function.runtime.EquationStandardService;
import com.misys.equation.function.runtime.FunctionCommonData;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionHandlerData;
import com.misys.equation.function.runtime.FunctionInfo;
import com.misys.equation.function.runtime.FunctionKeys;
import com.misys.equation.function.runtime.FunctionMessage;
import com.misys.equation.function.runtime.FunctionMessages;
import com.misys.equation.function.runtime.FunctionMsgManager;
import com.misys.equation.function.runtime.LoadFieldSetStatusHandler;
import com.misys.equation.function.runtime.ScreenSet;
import com.misys.equation.function.runtime.ScreenSetHandler;
import com.misys.equation.function.runtime.ScreenSetRepeatFields;

public class FunctionRuntimeToolbox
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionRuntimeToolbox.java 17356 2013-09-25 14:37:20Z Lima12 $";

	// Log
	protected final static EquationLogger LOG = new EquationLogger(FunctionRuntimeToolbox.class);

	// Function name
	public static final String NAME = "FunctionHandler";
	public static final String UNIQUE_NAME = "Name";
	public static final String NO_LOADOPTION = "NOLOAD";
	public static final String REFRESH_MAIN_WINDOW = "refreshMainWindow";
	public static final String NEXT_ACTION = "nextAction";

	// Next processing
	public static final int PROC_REDISPLAY_SCREEN = 0;
	public static final int PROC_EXIT_FUNCTION = 1;
	public static final int PROC_SCREEN1 = 2;
	public static final int PROC_LET_SYSTEM_DECIDE = 3;
	public static final int PROC_EXIT_DESKTOP = 4;
	public static final int PROC_REFRESH_MAIN_WINDOW = 5;

	// Mode for writing journal
	public static final String FCT_JRN = "J";
	public static final String FCT_ENQ = "E";

	// Initialisation mode
	public static final int INIT_DESK = 1; // initialisation of a brand new function
	public static final int INIT_SAVE = 2; // initialisation of a saved transaction
	public static final int INIT_DESK_LINK = 3; // initialisation of a brand new transaction (linked - e.g. next request, etc)

	// Expression validation
	private static ExpressionFactory factory = new ExpressionFactoryImpl();
	private static ELContextImpl elContext = new ELContextImpl();
	private static FunctionData functionData;

	/** Success */
	public static final String OVERALL_MESSAGE_SUCCESS = "S";

	/** Error */
	public static final String OVERALL_MESSAGE_ERROR = "E";

	/** Success with info messages */
	public static final String OVERALL_MESSAGE_SUCCESSINFO = "I";

	/** Warning */
	public static final String OVERALL_MESSAGE_WARNING = "W";

	/** Failure */
	public static final String OVERALL_MESSAGE_FAILURE = "F";

	/** Default suffix for bank-defined service function user exit */
	public static String USER_EXIT_FUNCTION_SUFFIX = "UserExit";

	/** Default suffix for bank-defined service layout user exit */
	public static String USER_EXIT_LAYOUT_SUFFIX = "LayoutUserExit";

	/** Default suffix for Misys-defined service function user exit */
	public static String MISYS_USER_EXIT_FUNCTION_SUFFIX = "MisysUserExit";

	/** Default suffix for Misys-defined service layout user exit */
	public static String MISYS_USER_EXIT_LAYOUT_SUFFIX = "MisysLayoutUserExit";

	/**
	 * Retrieve the Equation standard session from the pool
	 * 
	 * @param eqUnit
	 *            - the Equation unit
	 * 
	 * @return an Equation standard session from the pool
	 */
	public static EquationStandardSession rtvEquationSessionFromPool(EquationUnit eqUnit) throws EQException
	{
		return eqUnit.getEquationSessionPool().getEquationStandardSession();
	}
	/**
	 * Retrieve the Equation standard session from the pool
	 * 
	 * @param eqUnit
	 *            - the Equation unit
	 * @param dataSourceName
	 *            - dataSourceName (e.g. EQ-SYSTEM-UNM-XXXXX)
	 * 
	 * @return an Equation standard session from the pool
	 */
	public static EquationStandardSession rtvEquationSessionFromPool(EquationUnit eqUnit, String dataSourceName) throws EQException
	{
		// TODO Remove unit and construct here from data source name?
		return eqUnit.getEquationSessionPool(dataSourceName).getEquationStandardSession();
	}
	/**
	 * Return the Equation standard session back to the pool
	 * 
	 * @param eqUnit
	 *            - the Equation Unit
	 * @param sessionFromPool
	 *            - the Equation Standard Session from the Equation Session Pool
	 */
	public static void returnEquationSessionToPool(EquationUnit eqUnit, EquationStandardSession sessionFromPool)
	{
		if (sessionFromPool != null)
		{
			try
			{
				eqUnit.getEquationSessionPool().returnEquationStandardSession(sessionFromPool);
			}
			catch (Exception e)
			{
				LOG.error(e);
			}
		}
	}
	/**
	 * Return the Equation standard session back to the pool
	 * 
	 * @param dataSourceName
	 *            - dataSourceName (e.g. EQ-SYSTEM-UNM-XXXXX)
	 * @param sessionFromPool
	 *            - the Equation Standard Session from the Equation Session Pool
	 */
	public static void returnEquationSessionToPool(String dataSourceName, EquationStandardSession sessionFromPool)
	{

		if (sessionFromPool != null)
		{
			try
			{
				EquationUnit unit = sessionFromPool.getUnit();
				unit.getEquationSessionPool(dataSourceName).returnEquationStandardSession(sessionFromPool);

			}
			catch (Exception e)
			{
				LOG.error(e);
			}
		}
	}

	/**
	 * Generate other message
	 * 
	 * @param functionMsgManager
	 *            - the Function Message Manager
	 * 
	 * @param equationStandardSession
	 *            - the Equation standard session
	 * @param curScreenSet
	 *            - the current screen set
	 * @param scrnNo
	 *            - the current screen
	 * @param methodName
	 *            - method that generate the message
	 * @param e
	 *            - Exception
	 */
	public static void generateOtherMessages(FunctionMsgManager functionMsgManager,
					EquationStandardSession equationStandardSession, int curScreenSet, int scrnNo, String methodName, Exception e)
	{
		try
		{
			LOG.error(methodName, e);
			functionMsgManager.insertOtherMessage(equationStandardSession, curScreenSet, scrnNo, "", 0, null, "KSM7340"
							+ Toolbox.getExceptionMessage(e), "", "");
		}
		catch (Exception e2)
		{
			LOG.error(methodName, e2);
			functionMsgManager.insertOtherMessage(curScreenSet, scrnNo, "KSM7340", EQMessage.SEVERITY_ERROR, "KSM7340"
							+ Toolbox.getExceptionMessage(e), "", "");
		}
	}

	/**
	 * Determine if the user is authorised to the transaction
	 * 
	 * @param optionId
	 *            - the option Id
	 */
	public static String chkAuthorised(EquationStandardSession equationStandardSession, String optionId, String userId)
	{
		return EqDataToolbox.validateOption(equationStandardSession, "V", "N", "N", optionId, userId);
	}

	/**
	 * Determine if the user is authorised to the transaction
	 * 
	 * @param optionId
	 *            - the option Id
	 */
	public static String chkOptionExist(EquationStandardSession equationStandardSession, String optionId, String userId)
	{
		return EqDataToolbox.validateOption(equationStandardSession, "", "N", "N", optionId, userId);
	}

	/**
	 * Update the option to the GH file
	 * 
	 * @param optionId
	 *            - the option Id
	 * 
	 * @return true if successfully maintained
	 */
	public static void updateOptionInGH(EquationStandardSession equationStandardSession, String optionId, String userId,
					String sessionId)
	{
		try
		{
			equationStandardSession.updateGH("O", optionId, "", "", "", "");
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}

	/**
	 * Perform CRM limit checking
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param screenSet
	 *            - the screen set to check
	 * 
	 * @return the CRM limit data
	 * 
	 * @throws EQException
	 */
	public static CRMLimitCheckData performCRMLimitCheck(EquationStandardSession session, ScreenSet screenSet) throws EQException
	{
		// setup the details
		Function function = screenSet.getFunction();
		FunctionAdaptor functionAdaptor = screenSet.getFunctionAdaptor();

		// Before proceeding we need to ensure we have CRM defined for the function
		if (!function.containsAPIFieldSet(Function.CRM_FIELDSET))
		{
			return null;
		}
		// go get the CRM API field set
		APIFieldSet apiFieldSet = function.getUpdateAPI(Function.CRM_FIELDSET);

		// retrieve the journal details
		FunctionCommonData mapData = new FunctionCommonData(session, function, apiFieldSet, screenSet.getFunctionData(),
						functionAdaptor, screenSet.getFhd());

		// no data
		if (!mapData.isDataExist())
		{
			return null;
		}

		// construct the data structure
		EquationDataStructureData dshh03 = new EquationDataStructureData(new EqDS_DSHH03());
		dshh03.setFieldValue(EqDS_DSHH03.HHAB, mapData.getFieldValue(EqDS_DSHH03.HHAB));
		dshh03.setFieldValue(EqDS_DSHH03.HHAN, mapData.getFieldValue(EqDS_DSHH03.HHAN));
		dshh03.setFieldValue(EqDS_DSHH03.HHAS, mapData.getFieldValue(EqDS_DSHH03.HHAS));
		dshh03.setFieldValue(EqDS_DSHH03.HHBRN, mapData.getFieldValue(EqDS_DSHH03.HHBRN));
		dshh03.setFieldValue(EqDS_DSHH03.HHDLP, mapData.getFieldValue(EqDS_DSHH03.HHDLP));
		dshh03.setFieldValue(EqDS_DSHH03.HHDLR, mapData.getFieldValue(EqDS_DSHH03.HHDLR));
		dshh03.setFieldValue(EqDS_DSHH03.HHMVT, mapData.getFieldValue(EqDS_DSHH03.HHMVT));
		dshh03.setFieldValue(EqDS_DSHH03.HHCMR, mapData.getFieldValue(EqDS_DSHH03.HHCMR));
		dshh03.setFieldValue(EqDS_DSHH03.HHCUS, mapData.getFieldValue(EqDS_DSHH03.HHCUS));
		dshh03.setFieldValue(EqDS_DSHH03.HHCLC, mapData.getFieldValue(EqDS_DSHH03.HHCLC));
		dshh03.setFieldValue(EqDS_DSHH03.HHATP, mapData.getFieldValue(EqDS_DSHH03.HHATP));
		dshh03.setFieldValue(EqDS_DSHH03.HHCY1, mapData.getFieldValue(EqDS_DSHH03.HHCY1));
		dshh03.setFieldValue(EqDS_DSHH03.HHCY2, mapData.getFieldValue(EqDS_DSHH03.HHCY2));
		dshh03.setFieldValue(EqDS_DSHH03.HHSRC, mapData.getFieldValue(EqDS_DSHH03.HHSRC));
		dshh03.setFieldValue(EqDS_DSHH03.HHUC1, mapData.getFieldValue(EqDS_DSHH03.HHUC1));
		dshh03.setFieldValue(EqDS_DSHH03.HHUC2, mapData.getFieldValue(EqDS_DSHH03.HHUC2));
		dshh03.setFieldValue(EqDS_DSHH03.HHSDT, mapData.getFieldValue(EqDS_DSHH03.HHSDT));
		dshh03.setFieldValue(EqDS_DSHH03.HHMDT, mapData.getFieldValue(EqDS_DSHH03.HHMDT));
		dshh03.setFieldValue(EqDS_DSHH03.HHDRF, mapData.getFieldValue(EqDS_DSHH03.HHDRF));
		dshh03.setFieldValue(EqDS_DSHH03.HHAMC, mapData.getFieldValue(EqDS_DSHH03.HHAMC));
		dshh03.setFieldValue(EqDS_DSHH03.HHODC, mapData.getFieldValue(EqDS_DSHH03.HHODC));
		dshh03.setFieldValue(EqDS_DSHH03.HHFCT, mapData.getFieldValue(EqDS_DSHH03.HHFCT));
		dshh03.setFieldValue(EqDS_DSHH03.HHAB2, mapData.getFieldValue(EqDS_DSHH03.HHAB2));
		dshh03.setFieldValue(EqDS_DSHH03.HHAN2, mapData.getFieldValue(EqDS_DSHH03.HHAN2));
		dshh03.setFieldValue(EqDS_DSHH03.HHAS2, mapData.getFieldValue(EqDS_DSHH03.HHAS2));
		dshh03.setFieldValue(EqDS_DSHH03.HHCU2, mapData.getFieldValue(EqDS_DSHH03.HHCU2));
		dshh03.setFieldValue(EqDS_DSHH03.HHCL2, mapData.getFieldValue(EqDS_DSHH03.HHCL2));
		dshh03.setFieldValue(EqDS_DSHH03.HHDR2, mapData.getFieldValue(EqDS_DSHH03.HHDR2));
		dshh03.setFieldValue(EqDS_DSHH03.HHAMP, mapData.getFieldValue(EqDS_DSHH03.HHAMP));
		dshh03.setFieldValue(EqDS_DSHH03.HHODP, mapData.getFieldValue(EqDS_DSHH03.HHODP));
		dshh03.setFieldValue(EqDS_DSHH03.HHDSP, mapData.getFieldValue(EqDS_DSHH03.HHDSP));
		dshh03.setFieldValue(EqDS_DSHH03.HHCAC, mapData.getFieldValue(EqDS_DSHH03.HHCAC));
		dshh03.setFieldValue(EqDS_DSHH03.HHCAP, mapData.getFieldValue(EqDS_DSHH03.HHCAP));
		dshh03.setFieldValue(EqDS_DSHH03.HHMCH, mapData.getFieldValue(EqDS_DSHH03.HHMCH));
		dshh03.setFieldValue(EqDS_DSHH03.HHKEY, mapData.getFieldValue(EqDS_DSHH03.HHKEY));

		dshh03.setFieldValue(EqDS_DSHH03.HHDSP, "A");

		return session.checkCRMLimit(dshh03);
	}

	/**
	 * Check and return error message if user has violated limit check
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param screenSet
	 *            - the screen set to check
	 * 
	 * @return the error message
	 */
	public static String isCRMLimitCheckError(EquationStandardSession session, ScreenSet screenSet) throws EQException
	{
		CRMLimitCheckData crmLimitCheckData = performCRMLimitCheck(session, screenSet);
		if (crmLimitCheckData != null)
		{
			return crmLimitCheckData.getDsepms().trim();
		}
		return "";
	}

	/**
	 * Return the value identified by the initial value type
	 * 
	 * @param initialValueType
	 *            - the initial value type
	 * @param initialValue
	 *            - the initial value
	 * @param fhd
	 *            - the Function Handler Data
	 * 
	 * 
	 * @return the initial value
	 */
	public static String rtvInitialValue(WorkField field, int initialValueType, String initialValue, FunctionHandlerData fhd,
					FunctionData functionData) throws EQException
	{
		EquationUser eqUser = fhd.getEquationUser();
		EquationStandardSession session = eqUser.getSession();

		// constant
		if (initialValueType == InputField.INIT_VALUE_CONSTANT)
		{
			FieldAdaptor fieldAdaptor = fhd.getFunctionAdaptorHandler().getFunctionAdaptor(session, fhd.getOptionId())
							.getFieldAdaptor(session, field.getId(), GAZRecordDataModel.TYP_FIELD);
			fieldAdaptor.setFunctionData(fhd, functionData);
			return fieldAdaptor.initialValue(field, functionData);
		}

		// system option
		else if (initialValueType == InputField.INIT_VALUE_MAJOR_PROC || initialValueType == InputField.INIT_VALUE_SYS_VAR
						|| initialValueType == InputField.INIT_VALUE_CST)
		{
			// trim off the label if the " - " delimeter is present
			if (initialValue.indexOf(Toolbox.TEXT_DELIMITER) > 0)
			{
				initialValue = initialValue.substring(0, initialValue.indexOf(Toolbox.TEXT_DELIMITER));
			}
			if (session != null)
			{
				EquationUnit eqUnit = session.getUnit();
				String value = eqUnit.getSystemOption(session, "KFIL", initialValue, eqUnit.isDevelopmentUnit());

				// data area not found, try KLIB
				if (value == null)
				{
					value = eqUnit.getSystemOption(session, "KLIB", initialValue, eqUnit.isDevelopmentUnit());
				}

				// data area not found, try KLIB
				if (value == null)
				{
					throw new EQException(LanguageResources.getFormattedString("FunctionRuntimeToolbox.DataAreaNotFound",
									new String[] { initialValue, field.getId() }));
				}

				// system option not found
				else if (value.length() == 0)
				{
					throw new EQException(LanguageResources.getFormattedString("FunctionRuntimeToolbox.SystemOptionNotFound",
									new String[] { initialValue, field.getId() }));
				}

				return value;

			}
			return "";
		}

		// enhancement check
		else if (initialValueType == InputField.INIT_VALUE_ENH)
		{
			return Toolbox.cvtBooleanToYN(session.getUnit().isEnhancementInstalled(initialValue));
		}

		// from dajobctle
		else if (initialValueType == InputField.INIT_VALUE_DAJOB)
		{
			return eqUser.getDsjobctle().getFieldValue(initialValue);
		}

		// from context
		else if (initialValueType == InputField.INIT_VALUE_CONTEXT)
		{
			return fhd.getContextHandler().getFieldValue(initialValue);
		}

		// from parameter
		else if (initialValueType == InputField.INIT_VALUE_REFERENCE)
		{
			return fhd.getEquationUser().getHBXText(TextBean.OWNER_MISYS, initialValue, TextBean.TYPE_DESCRIPTION,
							EquationUser.DEF_LANG, true);
		}

		// from dasysctl
		else if (initialValueType == InputField.INIT_VALUE_DASYS)
		{
			return fhd.getEquationUser().getEquationUnit().getSystemOption(initialValue);
		}

		// unknown
		else
		{
			return "";
		}
	}

	/**
	 * Write the journal details
	 * 
	 * @param session
	 *            - Equation standard session
	 * @param journalHeader
	 *            - journal header
	 * @param function
	 *            - function
	 * @param functionData
	 *            - function data
	 * 
	 * @return true if successfully written journal details
	 * 
	 * @throws EQException
	 */
	public static EquationStandardTransaction writeJournalTransaction(EquationStandardSession session, JournalHeader journalHeader,
					Function function, FunctionData functionData, FunctionData functionDataBefore, FunctionAdaptor functionAdaptor,
					boolean journalIt, FunctionHandlerData fhd) throws EQException
	{
		// Setup the new transaction for writing the GY (we write the GY via W90FRR)
		EquationStandardTransaction transaction = new EquationStandardTransaction(EquationStandardTransaction.EDF_PGM
						+ journalHeader.getOption(), session, EquationStandardTransaction.EDF_JRNF, "", 0);

		// Setup the transaction
		transaction.setWorkStationId(journalHeader.getWorkstationID());
		transaction.setMode(FCT_JRN);
		transaction.setAext(journalHeader.getAext());
		transaction.setArec(journalHeader.getArec());
		transaction.setDsgyCtr(FunctionRuntimeToolbox.getGYCtr(journalHeader));
		transaction.setDsgyDet(FunctionRuntimeToolbox.getGYDet(journalHeader));

		// Set the EFC stuff
		if (function.containsAPIFieldSet(Function.EFC_FIELDSET))
		{
			// retrieve the EFC details
			FunctionCommonData mapData = new FunctionCommonData(session, function, function.getUpdateAPI(Function.EFC_FIELDSET),
							functionData, functionAdaptor, fhd);

			// Setup the fields
			transaction.setFieldValue("GZDAB", mapData.getFieldValue(FunctionCommonData.EFC_EDAB));
			transaction.setFieldValue("GZDAN", mapData.getFieldValue(FunctionCommonData.EFC_EDAN));
			transaction.setFieldValue("GZDAS", mapData.getFieldValue(FunctionCommonData.EFC_EDAS));
			transaction.setFieldValue("GZCAB", mapData.getFieldValue(FunctionCommonData.EFC_ECAB));
			transaction.setFieldValue("GZCAN", mapData.getFieldValue(FunctionCommonData.EFC_ECAN));
			transaction.setFieldValue("GZCAS", mapData.getFieldValue(FunctionCommonData.EFC_ECAS));
			transaction.setFieldValue("GZBRNM", mapData.getFieldValue(FunctionCommonData.EFC_EBRNM));
			transaction.setFieldValue("GZDLP", mapData.getFieldValue(FunctionCommonData.EFC_EDLP));
			transaction.setFieldValue("GZDLR", mapData.getFieldValue(FunctionCommonData.EFC_EDLR));
			transaction.setFieldValue("GZCMR", mapData.getFieldValue(FunctionCommonData.EFC_ECMR));
			transaction.setFieldValue("GZTREF", mapData.getFieldValue(FunctionCommonData.EFC_ETREF));
			transaction.setFieldValue("GZTDTE", mapData.getFieldValue(FunctionCommonData.EFC_ETDTE));
			transaction.setFieldValue("GZXREF", mapData.getFieldValue(FunctionCommonData.EFC_EXREF));
			transaction.setFieldValue("GZSQN", mapData.getFieldValue(FunctionCommonData.EFC_ESQN));
			transaction.setFieldValue("GZTCCY", mapData.getFieldValue(FunctionCommonData.EFC_EDCCY));
			transaction.setFieldValue("GZTAMT", mapData.getFieldValue(FunctionCommonData.EFC_EDAMT));
			transaction.setFieldValue("GZBTID", "");
			transaction.setFieldValue("GZIUID", journalHeader.getUser());
			transaction.setFieldValue("GZAUID", functionData.rtvFieldDbValue("")); // TODO
			transaction.setFieldValue("GZCOMM", functionData.rtvFieldDbValue(""));
			transaction.setFieldValue("GZCUS", "");
			transaction.setFieldValue("GZCLC", "");
			transaction.setFieldValue("GZPCHG", "");
		}

		// Set the remaining standard required for the GY API
		transaction.setFieldValue("GZOID", journalHeader.getOption());
		transaction.setFieldValue("GZIMG2", journalHeader.getFunctionMode());
		transaction.setFieldValue("GZAPP", journalHeader.getApplication());
		transaction.setFieldValue("GZWHO", journalHeader.getIdentity());
		transaction.setFieldValue("GZSHN", journalHeader.getIdentityShortName());
		transaction.setFieldValue("GZJREF", journalHeader.getJournalRef());
		transaction.setFieldValue("GZIREF", journalHeader.getInputRef());
		transaction.setFieldValue("GZAPID", journalHeader.getAppId());
		transaction.setFieldValue("GZJRN", Toolbox.cvtBooleanToYN(journalIt));

		// Warning messages
		int size = journalHeader.getWarningMessages().size();
		if (size >= 1)
		{
			transaction.setFieldValue("GZMES1", journalHeader.getWarningMessages().get(0)); // Message 1 (37A)
			transaction.setFieldValue("GZESP1", journalHeader.getAuthorisors().get(0)); // Authorisor 1
		}
		if (size >= 2)
		{
			transaction.setFieldValue("GZMES2", journalHeader.getWarningMessages().get(1)); // Message 2 (37A)
			transaction.setFieldValue("GZESP2", journalHeader.getAuthorisors().get(1)); // Authorisor 2
		}
		if (size >= 3)
		{
			transaction.setFieldValue("GZMES3", journalHeader.getWarningMessages().get(2)); // Message 3 (37A)
			transaction.setFieldValue("GZESP3", journalHeader.getAuthorisors().get(2)); // Authorisor 3
		}
		if (size >= 4)
		{
			transaction.setFieldValue("GZMES4", journalHeader.getWarningMessages().get(3)); // Message 4 (37A)
			transaction.setFieldValue("GZESP4", journalHeader.getAuthorisors().get(3)); // Authorisor 4
		}
		if (size >= 5)
		{
			transaction.setFieldValue("GZMES5", journalHeader.getWarningMessages().get(4)); // Message 5 (37A)
			transaction.setFieldValue("GZESP5", journalHeader.getAuthorisors().get(4)); // Authorisor 5
		}
		if (size >= 6)
		{
			transaction.setFieldValue("GZMES6", journalHeader.getWarningMessages().get(5)); // Message 6 (37A)
			transaction.setFieldValue("GZESP6", journalHeader.getAuthorisors().get(5)); // Authorisor 6
		}

		// Apply it
		session.applyEquationTransaction(transaction);

		// Remove all the warnings
		transaction.getWarningList().clear();

		// If successful, then write the GZ record
		if (transaction.getValid())
		{
			// Retrieve the details
			EquationDataStructureData eqDsDta = transaction.getGzDSData();
			EquationDataStructureData eqDsJrnKey = transaction.getDsJrnKey();

			// Setup the journal header key
			journalHeader.setWorkstationID(eqDsDta.getFieldValue("GZWSID"));
			journalHeader.setJrnDay(Integer.parseInt(eqDsDta.getFieldValue("GZDIM")));
			journalHeader.setJrnTime(Integer.parseInt(eqDsDta.getFieldValue("GZTIM")));
			journalHeader.setJrnSequence(Integer.parseInt(eqDsDta.getFieldValue("GZSEQ")));
			journalHeader.setCcLinkSeq(Toolbox.parseInt(eqDsJrnKey.getFieldValue("@GYCSEQ"), 0));
			journalHeader.setCcLinkTime(Toolbox.parseInt(eqDsJrnKey.getFieldValue("@GYCLTM"), 0));
			journalHeader.setCreateDate(Toolbox.parseInt(eqDsJrnKey.getFieldValue("@GYCDT"), 0));
			journalHeader.setJobNumber(Toolbox.parseInt(eqDsJrnKey.getFieldValue("@GYJOB"), 0));
			journalHeader.setRecovStat(eqDsJrnKey.getFieldValue("@GYREC"));

			// Setup the journal record
			JournalRecord journalRecord = new JournalRecord(function);

			// Link service?
			ScreenSet mainScreenSet = fhd.getScreenSetHandler().rtvScreenSetMain();
			if (mainScreenSet.isLinkService())
			{
				journalRecord.setEqFileName(JournalFile.getJournalName(fhd.getOptionId()));
			}

			journalRecord.chgJournalKey(journalHeader);
			journalRecord.setEfc(functionData.rtvFieldInputValue(FunctionData.FLD_EFC));
			if (fhd.getEquationUser().getEquationUnit().hasMakerCheckerEnhancement())
			{
				journalRecord.setSuppMakerChecker(functionData.rtvFieldInputValue(FunctionData.FLD_SUPP_MKR_CHKR));
				journalRecord.setStatMakerChecker(functionData.rtvFieldInputValue(FunctionData.FLD_STAT_MKR_CHKR));
			}

			// Write the GZ - before image
			if (functionDataBefore != null && !journalHeader.getFunctionMode().equals(IEquationStandardObject.FCT_ADD))
			{
				try
				{
					journalRecord.markBeforeImg();
					journalRecord.update(session, functionDataBefore);
				}
				catch (Exception e)
				{
					throw new EQException(LanguageResources.getString("FunctionRuntimeToolbox.JournalBeforeImage") + " "
									+ Toolbox.getExceptionMessage(e));
				}
			}

			// Write the GZ - after image
			if (!journalHeader.getFunctionMode().equals(IEquationStandardObject.FCT_DEL))
			{
				try
				{
					journalRecord.markAfterImg();
					journalRecord.update(session, functionData);
				}
				catch (Exception e)
				{
					throw new EQException(LanguageResources.getString("FunctionRuntimeToolbox.JournalAfterImage") + " "
									+ Toolbox.getExceptionMessage(e));
				}
			}
		}

		// Write the user data
		if (!functionData.getInputExtensionData().isEmpty())
		{
			functionData.getInputExtensionData().saveUserExtensionData(session, journalHeader.getWorkstationID(),
							journalHeader.getJrnDay(), journalHeader.getJrnTime(), journalHeader.getJrnSequence(),
							journalHeader.getFunctionMode());
		}

		// transaction successful?
		return transaction;
	}

	/**
	 * Write the journal details
	 * 
	 * @param session
	 *            - Equation standard session
	 * @param journalHeader
	 *            - journal header
	 * @param function
	 *            - function
	 * @param functionData
	 *            - function data
	 * 
	 * @return true if successfully written journal details
	 * 
	 * @throws EQException
	 */
	public static EquationStandardTransaction writeJournalEnquiry(EquationStandardSession session, JournalHeader journalHeader,
					Function function, FunctionData functionData, FunctionAdaptor functionAdaptor, FunctionHandlerData fhd)
					throws EQException
	{
		// Setup the new transaction for writing the GY (we write the GY via W90FRR)
		EquationStandardTransaction transaction = new EquationStandardTransaction(EquationStandardTransaction.EDF_PGM
						+ journalHeader.getOption(), session, EquationStandardTransaction.EDF_JRNF, "", 0);

		// Setup the transaction
		transaction.setWorkStationId(journalHeader.getWorkstationID());
		transaction.setMode(FCT_ENQ);

		// Set the G5 details
		if (function.containsAPIFieldSet(Function.G5_FIELDSET))
		{
			// retrieve the G5 details
			FunctionCommonData mapData = new FunctionCommonData(session, function, function.getUpdateAPI(Function.G5_FIELDSET),
							functionData, functionAdaptor, fhd);
			transaction.setFieldValue("GZAPP", mapData.getFieldValue(FunctionCommonData.G5_G5APP));
			transaction.setFieldValue("GZWHO", mapData.getFieldValue(FunctionCommonData.G5_G5WHO));
			transaction.setFieldValue("GZSHN", mapData.getFieldValue(FunctionCommonData.G5_G5SHN));
			transaction.setFieldValue("GZJREF", mapData.getFieldValue(FunctionCommonData.G5_G5JREF));
			transaction.setFieldValue("GZEEAN", mapData.getFieldValue(FunctionCommonData.G5_G5EEAN));
			transaction.setFieldValue("GZN01", mapData.getFieldValue(FunctionCommonData.G5_G5N01));
			transaction.setFieldValue("GZN02", mapData.getFieldValue(FunctionCommonData.G5_G5N02));
			transaction.setFieldValue("GZN03", mapData.getFieldValue(FunctionCommonData.G5_G5N03));
			transaction.setFieldValue("GZN04", mapData.getFieldValue(FunctionCommonData.G5_G5N04));
			transaction.setFieldValue("GZN05", mapData.getFieldValue(FunctionCommonData.G5_G5N05));
			transaction.setFieldValue("GZN06", mapData.getFieldValue(FunctionCommonData.G5_G5N06));
			transaction.setFieldValue("GZN07", mapData.getFieldValue(FunctionCommonData.G5_G5N07));
			transaction.setFieldValue("GZN08", mapData.getFieldValue(FunctionCommonData.G5_G5N08));
			transaction.setFieldValue("GZN09", mapData.getFieldValue(FunctionCommonData.G5_G5N09));
			transaction.setFieldValue("GZN010", mapData.getFieldValue(FunctionCommonData.G5_G5N010));
		}

		// Set the remaining standard required for the G5

		// Warning messages
		int size = journalHeader.getWarningMessages().size();
		if (size >= 1)
		{
			transaction.setFieldValue("GZMES1", journalHeader.getWarningMessages().get(0)); // Message 1 (37A)
		}
		if (size >= 2)
		{
			transaction.setFieldValue("GZMES2", journalHeader.getWarningMessages().get(1)); // Message 2 (37A)
		}
		if (size >= 3)
		{
			transaction.setFieldValue("GZMES3", journalHeader.getWarningMessages().get(2)); // Message 3 (37A)
		}
		if (size >= 4)
		{
			transaction.setFieldValue("GZMES4", journalHeader.getWarningMessages().get(3)); // Message 4 (37A)
		}
		if (size >= 5)
		{
			transaction.setFieldValue("GZMES5", journalHeader.getWarningMessages().get(4)); // Message 5 (37A)
		}
		if (size >= 6)
		{
			transaction.setFieldValue("GZMES6", journalHeader.getWarningMessages().get(5)); // Message 6 (37A)
		}

		// Apply it
		session.applyEquationTransaction(transaction);

		// If successful, then write the GZ record
		if (transaction.getValid())
		{
			// Retrieve the details
			EquationDataStructureData eqDsDta = transaction.getGzDSData();

			// Setup the journal header key
			journalHeader.setWorkstationID(eqDsDta.getFieldValue("GZWSID"));
			journalHeader.setJrnDay(Integer.parseInt(eqDsDta.getFieldValue("GZDIM")));
			journalHeader.setJrnTime(Integer.parseInt(eqDsDta.getFieldValue("GZTIM")));
			journalHeader.setJrnSequence(Integer.parseInt(eqDsDta.getFieldValue("GZSEQ")));
		}

		// transaction successful?
		return transaction;
	}

	/**
	 * Write the journal details
	 * 
	 * @param fct
	 *            - function mode
	 * @param workStationId
	 *            - workstation Id
	 * @param screenSetMain
	 *            - main screen set
	 * @param eqUser
	 *            - the Equation user
	 * @param warningMessages
	 *            - list of warning messages
	 * 
	 * @return the journal header
	 * 
	 * @throws EQException
	 */
	public static JournalHeader setupJournal(String fct, String workStationId, ScreenSet screenSetMain, EquationUser eqUser,
					FunctionMessages warningMessages) throws EQException
	{
		Function function = screenSetMain.getFunction();

		// Setup and write the journal header
		JournalHeader journalHeader = new JournalHeader();
		journalHeader.chgJournalKey();
		journalHeader.setWorkstationID(workStationId);
		journalHeader.setProgramRoot(EquationStandardTransaction.EDF_PGM.substring(0, 4));
		journalHeader.setFunctionMode(fct);
		journalHeader.setBranch(eqUser.getInputBranch());
		journalHeader.setOption(screenSetMain.getOptionId());

		// link service, then use the link service option
		if (screenSetMain.isLinkService())
		{
			journalHeader.setOption(screenSetMain.getFhd().getOptionId());
		}

		// Make Equation user UPPERCASE
		journalHeader.setUser(eqUser.getUserId().toUpperCase());
		journalHeader.setWarningMessages(warningMessages.rtvDsepms());
		journalHeader.setAuthorisors(warningMessages.rtvAuthorisor());
		journalHeader.setAext(Toolbox.cvtBooleanToYN(function.isApplyDuringExtInput()));
		journalHeader.setArec(Toolbox.cvtBooleanToYN(function.isApplyDuringRecovery()));
		journalHeader.setAppId("DESKTOP");

		// is there EqLogin?
		EquationLogin eqLogin = EquationCommonContext.getContext().getEquationLogin(
						screenSetMain.getFhd().getFunctionInfo().getSessionId());
		if (eqLogin != null)
		{
			journalHeader.setTcpIp(eqLogin.getIpAddress());
		}

		// Set up the additional journal header details from the mappings
		if (function.containsAPIFieldSet(Function.GY_FIELDSET))
		{
			// get the update API for the GY
			APIFieldSet apiFieldSet = function.getUpdateAPI(Function.GY_FIELDSET);

			// retrieve the journal details
			FunctionCommonData mapData = new FunctionCommonData(eqUser.getSession(), function, apiFieldSet, screenSetMain
							.getFunctionData(), screenSetMain.getFunctionAdaptor(), screenSetMain.getFhd());
			journalHeader.setApplication(mapData.getFieldValue(FunctionCommonData.GY_GYAPP));
			journalHeader.setIdentity(mapData.getFieldValue(FunctionCommonData.GY_GYWHO));
			journalHeader.setIdentityShortName(mapData.getFieldValue(FunctionCommonData.GY_GYSHN));
			journalHeader.setJournalRef(mapData.getFieldValue(FunctionCommonData.GY_GYJREF));
			journalHeader.setInputRef(mapData.getFieldValue(FunctionCommonData.GY_GYIREF));
			// Make input user and authorising user UPPERCASE
			journalHeader.setInputUserId(mapData.getFieldValue(FunctionCommonData.GY_GYAUID).toUpperCase());
			journalHeader.setAuthorisingId(mapData.getFieldValue(FunctionCommonData.GY_GYSUID).toUpperCase());
		}
		return journalHeader;
	}

	/**
	 * Return the text in user's language from properties
	 * 
	 * @param langId
	 *            - the user's language
	 * @param text
	 *            - the label
	 * @param type
	 *            - the label type
	 */
	public static String getText(String langId, String text, String type)
	{
		if (type.equals(Element.TEXT_VALUE_REFERENCE))
		{
			return LanguageResources.getStringInUserLanguage(langId, text);
		}

		return text;
	}

	/**
	 * Return the text in user's language from HB file
	 * 
	 * @param eqUser
	 *            - the EquationUser object
	 * @param textOwner
	 *            - the text owner
	 * 
	 * @param textType
	 *            - the text type (i.e. "LAB", "DSC","MSK", "REG" or "VLD")
	 * @param key
	 *            - the key
	 * @param type
	 *            - if text, reference text or reusable text
	 */
	public static String getHBXText(EquationUser eqUser, String textOwner, String textType, String key, String type,
					String serviceBaseLanguage)
	{
		String returnText = "";

		// If default text (no matter what type) then return an empty string.
		if (key.trim().equals(Element.DEFAULT_TEXT_VALUE))
		{
			returnText = "";
			return returnText;
		}

		// retrieve from hbx text
		if (type.equals(Element.TEXT_VALUE_REFERENCE) || type.equals(Element.TEXT_VALUE_REUSABLE_REFERENCE) && !key.equals(""))
		{
			if ((textOwner == null || textOwner.trim().length() == 0) && type.equals(Element.TEXT_VALUE_REUSABLE_REFERENCE))
			{
				textOwner = TextBean.OWNER_MISYS;
			}

			returnText = Toolbox.trimr(eqUser.getHBXText(textOwner, key, textType, serviceBaseLanguage, false));

			// if the text is not found
			if (returnText == null || returnText.length() == 0)
			{
				if (type.equals(Element.TEXT_VALUE_REUSABLE_REFERENCE) && !TextBean.TYPE_LABEL.equals(textType)
								&& TextBean.OWNER_MISYS.equals(textOwner))
				{
					// search for the GBL keys using Type LAB
					returnText = Toolbox.trimr(eqUser.getHBXText(textOwner, key, TextBean.TYPE_LABEL, serviceBaseLanguage, true));

				}
			}
		}

		// other type
		else
		{
			// if this is a simple text, return the key, i.e. text, passed to this method
			returnText = key;
		}

		return returnText;
	}
	/**
	 * Create a Function Data given the journal record
	 * 
	 * @param function
	 *            - the function
	 * @param journalRecord
	 *            - journal record
	 * @param fhd
	 *            - the Function Handler data
	 * 
	 * @return an equivalent function data
	 */
	public static FunctionData initialiseFunctionData(Function function, JournalRecord journalRecord, FunctionHandlerData fhd)
					throws EQException
	{
		FunctionData functionData = new FunctionData(function, fhd);
		functionData.loadFieldDataFromDS(journalRecord.getJrnData(), false);
		return functionData;
	}

	/**
	 * Create a Function Data given the journal record in bytes
	 * 
	 * @param function
	 *            - the function
	 * @param gzData
	 *            - journal record in bytes
	 * @param fhd
	 *            - the Function Handler data
	 * 
	 * @return an equivalent function data
	 * 
	 * @throws EQException
	 */
	public static FunctionData initialiseFunctionData(Function function, EquationDataStructureData eqDsDta, FunctionHandlerData fhd)
					throws EQException
	{

		// Create the function data
		FunctionData functionData = new FunctionData(function, fhd);
		functionData.loadFieldDataFromDS(eqDsDta, false);

		// Return the function data
		return functionData;
	}

	/**
	 * Initialise a journal record
	 * 
	 * @param function
	 *            - the function
	 * @param workStation
	 *            - work station
	 * @param jrnDay
	 *            - journal day
	 * @param jrnTime
	 *            - journal time
	 * @param jrnSequence
	 *            - journal sequence
	 * @param fct
	 *            - function mode
	 * @param library
	 *            - library where the journal is located
	 * @param optionId
	 *            - if not null, then this will overwrite the journal file
	 * 
	 * @return the journal record
	 */
	public static JournalRecord initialiseJournalRecord(Function function, String workStation, int jrnDay, int jrnTime,
					int jrnSequence, String image, String fct, String library, String optionId)
	{
		JournalRecord journalRecord = new JournalRecord(function);
		journalRecord.setWorkstationID(workStation);
		journalRecord.setJrnDay(jrnDay);
		journalRecord.setJrnTime(jrnTime);
		journalRecord.setJrnSequence(jrnSequence);
		journalRecord.setImage(image);
		journalRecord.setLibrary(library);

		// overwrite option id?
		if (optionId != null)
		{
			journalRecord.setEqFileName(JournalFile.getJournalName(optionId));
		}

		return journalRecord;
	}

	/**
	 * Return the result of the transaction in one string
	 * 
	 * The format will be:
	 * <p>
	 * - severity (2 characters)
	 * <p>
	 * - messages (37 characters) each
	 * 
	 * @return the list of messages
	 */
	public static String rtvAsString(FunctionMessages functionMessages)
	{
		StringBuffer str = new StringBuffer();

		// severity
		int msgSev = functionMessages.getMsgSev();
		if (msgSev != FunctionMessages.MSG_NONE)
		{
			str.append(Toolbox.shiftRight(String.valueOf(functionMessages.getMsgSev()), '0', 2));
		}
		else
		{
			str.append("  ");
		}

		// list of messages
		List<FunctionMessage> fm = functionMessages.getMessages();
		for (int i = 0; i < fm.size(); i++)
		{
			EQMessage eqMessage = fm.get(i).getEqMessage();
			str.append(Toolbox.pad(eqMessage.getDsepms37(), 37));
		}

		return str.toString();
	}

	/**
	 * Return the GY override control
	 * 
	 * @param journalHeader
	 *            - the journal header
	 * 
	 * @return the GY override control
	 */
	public static EquationDataStructureData getGYCtr(JournalHeader journalHeader)
	{
		EquationDataStructureData dsGYCtr = new EquationDataStructureData(new EqDS_DSGYCTR());
		dsGYCtr.setFieldValue(EqDS_DSGYCTR.XGYOVR, EqDataType.YES);
		return dsGYCtr;
	}

	/**
	 * Return the GY override details
	 * 
	 * @param journalHeader
	 *            - the journal header
	 * 
	 * @return the GY override details
	 */
	public static EquationDataStructureData getGYDet(JournalHeader journalHeader)
	{
		EquationDataStructureData dsGYDet = new EquationDataStructureData(new EqDS_DSGYDET());
		dsGYDet.setFieldValue(EqDS_DSGYDET.XGYAPID, journalHeader.getAppId());
		dsGYDet.setFieldValue(EqDS_DSGYDET.XGYAUID, journalHeader.getInputUserId());
		dsGYDet.setFieldValue(EqDS_DSGYDET.XGYIREF, journalHeader.getInputRef());
		dsGYDet.setFieldValue(EqDS_DSGYDET.XGYSUID, journalHeader.getAuthorisingId());
		dsGYDet.setFieldValue(EqDS_DSGYDET.XGYTCP, journalHeader.getTcpIp());
		dsGYDet.setFieldValue(EqDS_DSGYDET.XGYAEXT, journalHeader.getAext());
		dsGYDet.setFieldValue(EqDS_DSGYDET.XGYAREC, journalHeader.getArec());
		return dsGYDet;
	}

	/**
	 * Process all EL Script and Java user exit assignments of all the fields in an Input Field Set
	 * 
	 * @param inputFieldSet
	 *            - the input field set
	 */
	public static void processInputFieldSetValidateAssignments(InputFieldSet inputFieldSet, FunctionData functionData,
					FunctionAdaptor functionAdaptor, EquationStandardSession session, FunctionHandlerData fhd)
	{
		try
		{
			for (InputField inputField : inputFieldSet.getInputFields())
			{
				processInputFieldValidateAssignments(inputField, functionData, functionAdaptor, session, fhd);
			}
		}
		catch (EQException e)
		{
			LOG.error("processInputFieldValidateAssignments");
		}
	}

	/**
	 * Process all EL Script and Java user exit assignments of all the work fields
	 * 
	 * @param inputFieldSet
	 *            - the input field set
	 */
	public static void processWorkFieldsValidateAssignments(Function function, FunctionData functionData,
					FunctionAdaptor functionAdaptor, EquationStandardSession session, FunctionHandlerData fhd)
	{
		try
		{
			for (WorkField workField : function.getWorkFields())
			{
				processWorkFieldValidateAssignments(workField, functionData, functionAdaptor, session, fhd);
			}
		}
		catch (EQException e)
		{
			LOG.error("processInputFieldValidateAssignments", e);
		}
	}

	/**
	 * Process all EL Script and Java user exit assignments of all the fields in all Input Field Sets
	 * 
	 * @param inputFieldSet
	 *            - the input field set
	 */
	public static void processFunctionLoadAssignments(Function function, FunctionData functionData,
					FunctionAdaptor functionAdaptor, EquationStandardSession session, FunctionHandlerData fhd)
	{
		try
		{
			for (InputFieldSet inputFieldSet : function.getInputFieldSets())
			{
				// First process all fields in repeating data groups
				for (InputGroup inputGroup : inputFieldSet.getInputGroups())
				{
					for (RepeatingDataManager repeatingDataManager : functionData.getRepeatingDataManagers())
					{
						// Match an input group with a repeating data manager
						if (inputGroup.getId().equals(repeatingDataManager.getId()))
						{
							repeatingDataManager.moveFirst();
							// Loop through all rows:
							while (repeatingDataManager.next())
							{
								// Process all fields in the repeating data
								for (Map.Entry<String, FieldDefinition> entry : repeatingDataManager.getFieldDefinitions()
												.entrySet())
								{
									if (inputFieldSet.containsField(entry.getKey()))
									{
										InputField inputField = inputFieldSet.getInputField(entry.getKey());
										String dbValue = processUpdateLoadFieldAssignments(inputField.getId(), "", inputField
														.getLoadPrimitiveAssignment(), inputField.getLoadPrimitiveScript(),
														functionData, functionAdaptor, session, fhd, GAZRecordDataModel.TYP_LOAD);
										if (dbValue != null)
										{
											functionData.chgFieldDbValue(inputField.getId(), dbValue);
										}

									}
								}
							}
							break;
						}
					}
				}
				// Next process all non-repeating fields
				for (InputField inputField : inputFieldSet.getInputFields())
				{
					if (!Field.isRepeating(inputField))
					{
						String dbValue = processUpdateLoadFieldAssignments(inputField.getId(), "", inputField
										.getLoadPrimitiveAssignment(), inputField.getLoadPrimitiveScript(), functionData,
										functionAdaptor, session, fhd, GAZRecordDataModel.TYP_LOAD);
						if (dbValue != null)
						{
							functionData.chgFieldDbValue(inputField.getId(), dbValue);
						}
					}
				}
			}

			for (WorkField workField : function.getWorkFields())
			{
				String dbValue = processUpdateLoadFieldAssignments(workField.getId(), "", workField.getLoadAssignment(), workField
								.getLoadScript(), functionData, functionAdaptor, session, fhd, GAZRecordDataModel.TYP_LOAD);
				if (dbValue != null)
				{
					functionData.chgFieldDbValue(workField.getId(), dbValue);
				}
			}
		}
		catch (EQException e)
		{
			LOG.error("processInputFieldValidateAssignments", e);
		}
	}

	/**
	 * Process all EL Script and Java user exit assignments of an Input Field (both to the Primitive form and the Output form of the
	 * field)
	 * 
	 * @param inputField
	 *            - the input field
	 * @param functionData
	 *            - the Function DAta
	 * @param functionAdaptor
	 *            - the Function Adaptor
	 * @param session
	 *            - the Equation Standard Session
	 * @param fhd
	 *            - the Function Handler data
	 */
	public static void processWorkFieldValidateAssignments(Field inputField, FunctionData functionData,
					FunctionAdaptor functionAdaptor, EquationStandardSession session, FunctionHandlerData fhd) throws EQException
	{
		// Primitive form of the field:
		if (Field.ASSIGNMENT_SCRIPT.equals(inputField.getValidateAssignment()))
		{
			// Mapping script expression specified
			String dbValue = ELRuntime.runStringScript(inputField.getValidateScript(), functionData, inputField.getId(),
							LanguageResources.getString("Language.Assignment"), null, ELRuntime.DB_VALUE);
			if (dbValue != null)
			{
				functionData.chgFieldDbValue(inputField.getId(), dbValue);
				LOG.debug("Setting primitive form of [" + inputField.getId() + "] to [" + dbValue + "] from Script");
			}
		}
		else if (Field.ASSIGNMENT_CODE.equals(inputField.getValidateAssignment()))
		{
			String curValue = functionData.rtvFieldDbValue(inputField.getId());
			ValueAdaptor valueAdaptor = functionAdaptor.getValueAdaptor(session, AdaptorToolbox.getGAZKeyFields(inputField,
							AdaptorToolbox.VALIDATE_ASSIGNMENT, ""));
			valueAdaptor.setFunctionData(fhd, functionData);
			String newValue = valueAdaptor.getValue(curValue);
			if (newValue != null)
			{
				functionData.chgFieldDbValue(inputField.getId(), newValue);
				LOG.debug("Setting primitive form of [" + inputField.getId() + "] to [" + newValue + "] from Java");
			}
		}
	}

	/**
	 * Process all EL Script and Java user exit assignments of an Input Field (both to the Primitive form and the Output form of the
	 * field)
	 * 
	 * @param inputField
	 *            - the input field
	 * @param functionData
	 *            - the Function DAta
	 * @param functionAdaptor
	 *            - the Function Adaptor
	 * @param session
	 *            - the Equation Standard Session
	 * @param fhd
	 *            - the Function Handler data
	 */
	public static void processInputFieldValidateAssignments(Field inputField, FunctionData functionData,
					FunctionAdaptor functionAdaptor, EquationStandardSession session, FunctionHandlerData fhd) throws EQException
	{
		// Primitive form of the field:
		if (Field.ASSIGNMENT_SCRIPT.equals(inputField.getValidatePrimitiveAssignment()))
		{
			// Mapping script expression specified
			String dbValue = ELRuntime.runStringScript(inputField.getValidatePrimitiveScript(), functionData, inputField.getId(),
							LanguageResources.getString("Language.PrimitiveAssignment"), null, ELRuntime.DB_VALUE);
			if (dbValue != null)
			{
				functionData.chgFieldDbValue(inputField.getId(), dbValue);
				LOG.debug("Setting primitive form of [" + inputField.getId() + "] to [" + dbValue + "] from Script");
			}
		}
		else if (Field.ASSIGNMENT_CODE.equals(inputField.getValidatePrimitiveAssignment()))
		{
			String curValue = functionData.rtvFieldDbValue(inputField.getId());
			ValueAdaptor valueAdaptor = functionAdaptor.getValueAdaptor(session, AdaptorToolbox.getGAZKeyFields(inputField,
							AdaptorToolbox.VALIDATE_ASSIGNMENT, MappingToolbox.PRIMITIVE));
			valueAdaptor.setFunctionData(fhd, functionData);
			String newValue = valueAdaptor.getValue(curValue);
			if (newValue != null)
			{
				functionData.chgFieldDbValue(inputField.getId(), newValue);
				LOG.debug("Setting primitive form of [" + inputField.getId() + "] to [" + newValue + "] from Java");
			}
		}

		// Output form of the field:
		if (Field.ASSIGNMENT_SCRIPT.equals(inputField.getValidateOutputAssignment()))
		{
			// Mapping script expression specified
			String outputValue = ELRuntime.runStringScript(inputField.getValidateOutputScript(), functionData, inputField.getId(),
							"output assignment", null, ELRuntime.DB_VALUE);
			if (outputValue != null)
			{
				functionData.chgFieldOutputValue(inputField.getId(), outputValue);
				LOG.debug("Setting output form of [" + inputField.getId() + "] to [" + outputValue + "] from Script");
			}
		}
		else if (Field.ASSIGNMENT_CODE.equals(inputField.getValidateOutputAssignment()))
		{
			String curValue = functionData.rtvFieldOutputValue(inputField.getId());
			ValueAdaptor valueAdaptor = functionAdaptor.getValueAdaptor(session, AdaptorToolbox.getGAZKeyFields(inputField,
							AdaptorToolbox.VALIDATE_ASSIGNMENT, MappingToolbox.OUTPUT));
			valueAdaptor.setFunctionData(fhd, functionData);
			String newValue = valueAdaptor.getValue(curValue);
			if (newValue != null)
			{
				functionData.chgFieldOutputValue(inputField.getId(), newValue);
				LOG.debug("Setting output form of [" + inputField.getId() + "] to [" + newValue + "] from Java");
			}
		}
	}

	/**
	 * Process all EL Script and Java user exit assignments of an Update/Load fields's value adaptor
	 * 
	 * @param fieldSetId
	 *            - the field set id
	 * @param fieldName
	 *            - the field name
	 * @param assignment
	 *            - the assignment to determine whether from script or Java
	 * @param script
	 *            - the script code to execute
	 * @param functionData
	 *            - the Function DAta
	 * @param functionAdaptor
	 *            - the Function Adaptor
	 * @param session
	 *            - the Equation Standard Session
	 * @param fhd
	 *            - the Function Handler data
	 * @param adaptor
	 *            - the adaptor type
	 */
	public static String processUpdateLoadFieldAssignments(String fieldSetId, String fieldName, String assignment, String script,
					FunctionData functionData, FunctionAdaptor functionAdaptor, EquationStandardSession session,
					FunctionHandlerData fhd, String adaptor) throws EQException
	{
		String dbValue = null;

		if (Field.ASSIGNMENT_SCRIPT.equals(assignment))
		{
			dbValue = ELRuntime.runStringScript(script, functionData, fieldSetId + " " + fieldName, LanguageResources
							.getString("Language.UpdateLoadAssignment"), null, ELRuntime.DB_VALUE);
			LOG.debug("Setting field [" + fieldSetId + "-" + fieldName + "] to [" + dbValue + "] from Script");
		}

		else if (Field.ASSIGNMENT_CODE.equals(assignment))
		{
			dbValue = "";
			ValueAdaptor valueAdaptor = functionAdaptor.getValueAdaptor(session, fieldSetId, fieldName, adaptor);
			String newStr = valueAdaptor.getValue(dbValue);
			if (newStr != null)
			{
				dbValue = newStr;
				LOG.debug("Setting field [" + fieldSetId + "-" + fieldName + "] to [" + dbValue + "] from Java");
			}
		}

		// return the new value
		return dbValue;
	}

	/**
	 * Process substitution parameter expression for a KSM message (may be simple text)
	 * 
	 * @param expression
	 *            - the expression language statement
	 * 
	 * @return the parameter value (max 10 chars)
	 */
	public static String processSubstitutionParmExpression(String expression, String id, FunctionData functionData)
	{
		String result = ELRuntime.runStringScript(expression, functionData, id, LanguageResources.getString("Language.Assignment"),
						"", ELRuntime.INPUT_VALUE);

		return Toolbox.trim(result, 10);
	}

	/**
	 * Setup default details of the transaction based on JournalHeader
	 * 
	 * @param journalHeader
	 *            - the journal header of the main transaction
	 * @param transaction
	 *            - the transaction
	 */
	public static void setupTransaction(EquationStandardTransaction transaction, JournalHeader journalHeader,
					boolean applyDuringRecovery, boolean applyDuringExtInp)
	{
		transaction.setWorkStationId(journalHeader.getWorkstationID());

		// Determine if this transaction is applied during external input and recovery
		transaction.setArec(Toolbox.cvtBooleanToYN(journalHeader.getArec().equals(EqDataType.NO)));
		transaction.setAext(Toolbox.cvtBooleanToYN(journalHeader.getAext().equals(EqDataType.NO)));

		// Should it always recover this transaction even if main transaction is already being recovered?
		if (applyDuringRecovery && journalHeader.getArec().equals(EqDataType.YES))
		{
			transaction.setArec(EqDataType.YES);
		}

		// Should it always external input this transaction even if main transaction is already being externally input?
		if (applyDuringExtInp && journalHeader.getAext().equals(EqDataType.YES))
		{
			transaction.setAext(EqDataType.YES);
		}

		// GY override control/detail
		transaction.setDsgyCtr(FunctionRuntimeToolbox.getGYCtr(journalHeader));
		transaction.setDsgyDet(FunctionRuntimeToolbox.getGYDet(journalHeader));

		EquationDataStructureData dsgyDet = transaction.getDsgyDet();
		dsgyDet.setFieldValue(EqDS_DSGYDET.XGYAEXT, transaction.getAext());
		dsgyDet.setFieldValue(EqDS_DSGYDET.XGYAREC, transaction.getArec());
	}

	/**
	 * Setup default details of the transaction based on JournalHeader
	 * 
	 * @param journalHeader
	 *            - the journal header of the main transaction
	 * @param transaction
	 *            - the transaction
	 */
	public static void setupTransaction(EquationStandardService transaction, JournalHeader journalHeader,
					boolean applyDuringRecovery, boolean applyDuringExtInp)
	{
		ParentTransactionDetail parentTransactionDetail = transaction.getFh().getFhd().getFunctionInfo()
						.getParentTransactionDetail();

		if (parentTransactionDetail != null)
		{
			// Determine if this transaction is applied during external input and recovery
			parentTransactionDetail.setApplyDuringExternalInput(journalHeader.getArec().equals(EqDataType.NO));
			parentTransactionDetail.setApplyDuringRecovery(journalHeader.getAext().equals(EqDataType.NO));

			// Should it always recover this transaction even if main transaction is already being recovered?
			if (applyDuringRecovery && journalHeader.getArec().equals(EqDataType.YES))
			{
				parentTransactionDetail.setApplyDuringRecovery(true);
			}

			// Should it always external input this transaction even if main transaction is already being externally input?
			if (applyDuringExtInp && journalHeader.getAext().equals(EqDataType.YES))
			{
				parentTransactionDetail.setApplyDuringExternalInput(true);
			}
		}
	}

	/**
	 * Return the primary field set status
	 * 
	 * @param fhd
	 *            - the function handler data
	 * 
	 * @return the primary field set status
	 */
	public static LoadFieldSetStatus getPrimaryFieldSetStatus(FunctionHandlerData fhd)
	{
		ScreenSet screenSet = fhd.getScreenSetHandler().rtvScreenSetMain();
		String inputFieldSetId = screenSet.getMainInputFieldSet().getId();
		LoadFieldSetStatusHandler loadFieldSetStatusHandler = screenSet.getLoadFieldSetStatusHandler();
		LoadFieldSetStatus status = loadFieldSetStatusHandler.getFieldSetStatus(inputFieldSetId);
		return status;
	}

	/**
	 * Determine if keys are populated
	 * 
	 * @param fhd
	 *            - the function handler data
	 * 
	 * @return true if all keys are populated
	 */
	public static boolean isKeysPopulated(ScreenSet screenSet) throws EQException
	{
		FunctionData functionData = screenSet.getFunctionData();
		InputFieldSet inputFieldSet = screenSet.getFunction().getInputFieldSets().get(0);
		FunctionAdaptor functionAdaptor = screenSet.getFunctionAdaptor();
		boolean keyExists = false;
		boolean nonblanks = false;
		for (int i = 0; i < inputFieldSet.getInputFields().size(); i++)
		{
			InputField inputField = inputFieldSet.getInputFields().get(i);

			// key field and blank, then not valid
			if (inputField.isKey())
			{
				// field set adaptor
				FieldAdaptor fieldAdaptor = functionAdaptor.getFieldAdaptor(screenSet.getFhd().getEquationUser().getSession(),
								inputField.getId(), GAZRecordDataModel.TYP_FIELD);

				// there is a key
				keyExists = true;

				// mandatory field and blank, then keys have not be loaded
				if (fieldAdaptor.isMandatory(inputField)
								&& functionData.rtvFieldInputValue(inputField.getId()).trim().length() == 0)
				{
					return false;
				}

				// non-blanks?
				else if (functionData.rtvFieldInputValue(inputField.getId()).trim().length() != 0)
				{
					nonblanks = true;
				}
			}
		}

		// if key exists and non-blanks, then all key fields are ok
		return keyExists && nonblanks;
	}

	/**
	 * Validates the Transaction Id
	 * 
	 * @param id
	 *            - the transaction id to be validated
	 */
	public static boolean validateTransactionIdSyntax(String id)
	{
		char[] chars = id.toCharArray();

		// For the rest of characters the following are valid
		for (int index = 0; index < id.length(); index++)
		{
			char ch = chars[index];
			if ((ch < 'A' || ch > 'Z') && (ch < 'a' || ch > 'z') && (ch < '0' || ch > '9') && ch != '_' && ch != '.' && ch != ':'
							&& ch != '-' && ch != ' ')
			{
				return false;
			}
		}

		// valid
		return true;
	}

	/**
	 * Return the default error message id
	 * 
	 * @return the default error message id
	 */
	public static String getErrorMsgId()
	{
		return "KSM7340";
	}

	/**
	 * Return the default warning message id
	 * 
	 * @return the default warning message id
	 */
	public static String getWarningMsgId()
	{
		return "KSM1999";
	}

	/**
	 * Return the default info message id
	 * 
	 * @return the default info message id
	 */
	public static String getInfoMsgId()
	{
		return "KSM7348";
	}

	/**
	 * Construct DSEPMS given the message and message severity
	 * 
	 * @param msgSev
	 *            - the message severity
	 * @param msg
	 *            - the message
	 * @return the message in DSEPMS format
	 */
	public static String getDSEPMS(int msgSev, String msg)
	{
		String dsepms = "";
		if (msgSev == FunctionMessages.MSG_ERROR)
		{
			dsepms = getErrorMsgId() + msg;
		}
		else if (msgSev == FunctionMessages.MSG_WARN)
		{
			dsepms = getWarningMsgId() + msg;
		}
		else if (msgSev == FunctionMessages.MSG_INFO)
		{
			dsepms = getInfoMsgId() + msg;
		}

		return dsepms;
	}

	/**
	 * Determine whether the system should ignore the KSM during load processing
	 * 
	 * @param ksmId
	 *            - the KSM id
	 * 
	 * @return true if KSM should be ignored
	 */
	public static boolean isIgnoreKsmAtLoad(String ksmId)
	{
		if (ksmId.equals("KSM2010") || ksmId.equals("KSM7367"))
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * This analyses the list of messages returned by Load APIs to determine whether the key exists or not.<br>
	 * This uses isIgnoreKsmAtLoad to determine the list of KSMs that determines key existence.
	 * 
	 * @param messages
	 *            - the list of messages
	 * 
	 * @return the message that triggers the not found message
	 */
	public static EQMessage getKeyNotFoundMsg(List<EQMessage> messages)
	{
		for (int i = 0; i < messages.size(); i++)
		{
			if (isIgnoreKsmAtLoad(messages.get(i).getMessageID()))
			{
				return messages.get(i);
			}
		}

		// key exists
		return null;
	}

	/**
	 * Search and return the display attribute of the field from the list
	 * 
	 * @param displayItems
	 *            - list of display items
	 * @param fieldName
	 *            - the field name
	 * 
	 * @return the display attribute of the id
	 */
	public static DisplayAttributes getDisplayAttributes(DisplayItemList displayItems, String fieldName)
	{
		for (int i = 0; i < displayItems.size(); i++)
		{
			if (displayItems.get(i) instanceof DisplayAttributes)
			{
				DisplayAttributes displayAttributes = (DisplayAttributes) displayItems.get(i);
				if (displayAttributes.getId().equals(fieldName))
				{
					return displayAttributes;
				}
			}

			else if (displayItems.get(i) instanceof DisplayGroup)
			{
				DisplayGroup displayGroup = (DisplayGroup) displayItems.get(i);
				DisplayAttributes displayAttributes = getDisplayAttributes(displayGroup.getDisplayItems(), fieldName);
				if (displayAttributes != null)
				{
					return displayAttributes;
				}
			}
		}

		// not found
		return null;
	}

	/**
	 * Return the label of an input field based on the input field and display attributes
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param inputField
	 *            - the input field
	 * @param displayAttributes
	 *            - the display attribute of the field
	 * 
	 * @return the field label in user's language
	 */
	public static String getLabel(EquationUser eqUser, InputField inputField, DisplayAttributes displayAttribute)
	{
		String fieldLabel = "";
		if (displayAttribute != null)
		{
			fieldLabel = displayAttribute.rtvLabel(eqUser);
		}

		// Blank, then retrieve from the field. If all else fails, then the id
		if (fieldLabel.trim().length() == 0)
		{
			fieldLabel = inputField.rtvLabel(eqUser);
			if (fieldLabel.trim().length() == 0)
			{
				fieldLabel = inputField.getId();
			}
		}

		// return the label
		return fieldLabel;
	}

	/**
	 * Return the label of a display attribute fields based solely on display attributes
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param inputField
	 *            - the input field
	 * @param displayAttributesSet
	 *            - the display attribute field set where the field is suppose to belong to
	 * 
	 * @return the field label in user's language
	 */
	public static String getLabel(EquationUser eqUser, DisplayAttributes displayAttributes, FunctionData functionData)
	{
		String fieldLabel = "";
		fieldLabel = displayAttributes.rtvLabel(eqUser);

		if (displayAttributes.getLabelScriptRunTime().trim().length() > 0)
		{
			String labelRuntime = ELRuntime.runStringScript(displayAttributes.getLabelScriptRunTime(), functionData,
							displayAttributes.getId(), LanguageResources.getString("FunctionRuntimeToolbox.LabelRunTime"),
							fieldLabel, ELRuntime.DB_VALUE);
			if (labelRuntime.trim().length() > 0)
			{
				fieldLabel = labelRuntime;
			}
		}

		// return the label
		return fieldLabel;
	}

	/**
	 * Return the label of the button or link field
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param displayElement
	 *            - the display element where the field is suppose to belong to
	 * @param functionData
	 *            - the Function Data
	 * 
	 * @return the field label in user's language
	 */
	public static String getLabel(EquationUser eqUser, Element displayElement, FunctionData functionData)
	{
		String fieldLabel = "";
		fieldLabel = displayElement.rtvLabel(eqUser);

		if (displayElement instanceof DisplayButtonLink)
		{
			DisplayButtonLink displayButtonLink = (DisplayButtonLink) displayElement;
			if (displayButtonLink.getLabelScriptRuntime().trim().length() > 0)
			{
				String labelRuntime = ELRuntime.runStringScript(displayButtonLink.getLabelScriptRuntime(), functionData,
								displayElement.getId(), LanguageResources.getString("FunctionRuntimeToolbox.LabelRunTime"),
								fieldLabel, ELRuntime.DB_VALUE);
				if (labelRuntime.trim().length() > 0)
				{
					fieldLabel = labelRuntime;
				}
			}
		}

		// return the label
		return fieldLabel;
	}

	/**
	 * Return the root field name of the given field name
	 * 
	 * @param fieldName
	 *            - the field name (e.g. A_HZAB$00001)
	 * 
	 * @return the root field name (e.g. A_HZAB)
	 */
	public static String getRootFieldNameForRepeatingField(String fieldName)
	{
		int index = fieldName.indexOf(RepeatingDataManager.INDEX_DELIMITER);
		int indexLast = fieldName.lastIndexOf(RepeatingDataManager.INDEX_DELIMITER);
		if (index >= 0 && index == indexLast)
		{
			// ensure this is the input field
			if (index == fieldName.length() - RepeatingDataManager.LIST_INDEX_LEN - 1)
			{
				return fieldName.substring(0, index);
			}
			else
			{
				return fieldName;
			}
		}
		else
		{
			return fieldName;
		}
	}

	/**
	 * Return the row index of a field name if this is a repeating field name
	 * 
	 * @param fieldName
	 *            - the field name (e.g. A_HZAB$00001)
	 * 
	 * @return the row number (e.g. 1), 0 if not a repeating field
	 */
	public static int isRepeatingFieldName(String fieldName)
	{
		int index = fieldName.indexOf(RepeatingDataManager.INDEX_DELIMITER);
		int indexLast = fieldName.lastIndexOf(RepeatingDataManager.INDEX_DELIMITER);
		if (index >= 0 && index == indexLast)
		{
			// get the index
			int row = Toolbox.parseInt(fieldName.substring(index + RepeatingDataManager.INDEX_DELIMITER.length()), 0);
			return row;
		}
		else
		{
			return 0;
		}
	}

	/**
	 * Copy the element details
	 * 
	 * @param sourceElement
	 *            - source element to copy from
	 * @param targetElement
	 *            - target element to copy to
	 * 
	 * @return the target element
	 */
	public static Element copyElement(Element sourceElement, Element targetElement)
	{
		targetElement.setId(sourceElement.getId());
		targetElement.setLabel(sourceElement.getLabel());
		targetElement.setDescription(sourceElement.getDescription());
		targetElement.setLabelType(sourceElement.getLabelType());
		targetElement.setDescriptionType(sourceElement.getDescriptionType());
		return targetElement;
	}

	/**
	 * Add the fields of a repeating group to the Function/Layout
	 * 
	 * @param repeatingGroupId
	 *            - the repeating group id
	 * @param functionMain
	 *            - the Function to copy from
	 * @param layoutMain
	 *            - the Layout to copy from
	 * @param newFunction
	 *            - the Function to copy to
	 * @param newLayout
	 *            - the Layout to copy to
	 * 
	 * @return true if able to copy the repeating group
	 */
	public static boolean copyRepeatingFields(String repeatingGroupId, Function functionMain, Layout layoutMain,
					Function newFunction, Layout newLayout) throws EQException
	{
		boolean copied = false;
		DisplayAttributesSet newDisplayFieldSet = null;
		InputFieldSet newInputFieldSet = null;

		// loop through the layout looking for the specified group
		for (DisplayAttributesSet fieldSet : layoutMain.getDisplayAttributesSets())
		{
			DisplayGroup displayGroup = fieldSet.rtvRepeatingGroup(repeatingGroupId);

			if (displayGroup != null)
			{
				// add the primary field set
				newDisplayFieldSet = (DisplayAttributesSet) FunctionRuntimeToolbox.copyElement(displayGroup,
								new DisplayAttributesSet());
				newDisplayFieldSet.setId(Function.PRIMARY_ID);
				newLayout.addDisplayAttributesSet(newDisplayFieldSet);

				newInputFieldSet = (InputFieldSet) FunctionRuntimeToolbox.copyElement(displayGroup, new InputFieldSet());
				newInputFieldSet.setId(Function.PRIMARY_ID);
				newFunction.addInputFieldSet(newInputFieldSet);

				for (IDisplayItem repeatingField : displayGroup.getDisplayItems())
				{
					IDisplayItem newElement = duplicateDisplayItem(repeatingField);
					newDisplayFieldSet.addItem(newElement); // force dump when NULL as must be a valid type
				}
				break;
			}
		}

		// loop through the function looking for all the fields in the group
		// TODO: is this how we determine the group??????????
		for (InputFieldSet fieldSet : functionMain.getInputFieldSets())
		{
			for (InputField field : fieldSet.getInputFields())
			{
				// repeating field, then add it
				if (newDisplayFieldSet.getDisplayItems().get(field.getId()) != null)
				{
					InputField inputField = new InputField(field);
					inputField.setRepeatingGroup("");
					newInputFieldSet.addInputField(inputField);
					copied = true;
				}

				// otherwise, add as work field
				else
				{
					WorkField workField = new WorkField(field);
					newFunction.addWorkField(workField);
				}
			}
		}

		// add all work fields as well
		for (WorkField workField : functionMain.getWorkFields())
		{
			WorkField copyWorkField = new WorkField(workField);
			newFunction.addWorkField(copyWorkField);
		}

		// copied something?
		return copied;
	}

	/**
	 * Copy the validate mapping from one function to another, including renaming the mapping if the field is in another field set /
	 * work field
	 * 
	 * @param newFunction
	 *            - the new function
	 * @param functionMain
	 *            - the function to copy from
	 * 
	 * @return true if successful
	 */
	public static boolean copyValidateMappings(Function newFunction, Function functionMain)
	{
		// remap the validating mapping
		// 1. If this is not a repeating field, then mapped as a work field
		// 2. If this is a repeating field, then field set must be primary
		// 3. If one of the mapping is not a repeating field, then remove it
		for (Mapping mapping : functionMain.getValidateMappings())
		{
			String field1 = MappingToolbox.getInputField(mapping.getSource());
			String field2 = MappingToolbox.getInputField(mapping.getTarget());
			Element element1 = newFunction.rtvInputField(field1, true);
			Element element2 = newFunction.rtvInputField(field2, true);

			if (element1 instanceof InputField || element2 instanceof InputField)
			{
				String newSourceFieldSet = (element1 instanceof InputField) ? Function.PRIMARY_ID : "";
				String newTargetFieldSet = (element2 instanceof InputField) ? Function.PRIMARY_ID : "";

				String newSource = renameValidateMapping(newSourceFieldSet, field1, field1, mapping.getSource());
				String newTarget = renameValidateMapping(newTargetFieldSet, field2, field2, mapping.getTarget());
				Mapping newMapping = new Mapping(newSource, newTarget);
				newFunction.addValidateMapping(newMapping);
			}
		}

		return true;
	}

	/**
	 * Rename an input field validate mapping into a new input field name
	 * 
	 * @param newFieldSetName
	 *            - the new field set name
	 * @param newFieldName
	 *            - the new field name
	 * @param oldFieldName
	 *            - the old field name
	 * @param fullMapping
	 *            - the full mapping
	 * 
	 * @return the new mapping using the new field set and field name
	 */
	public static String renameValidateMapping(String newFieldSetName, String newFieldName, String oldFieldName, String fullMapping)
	{
		String inputFieldPath = MappingToolbox.getInputFieldPath(oldFieldName);
		int index = fullMapping.indexOf(inputFieldPath);
		if (index == -1)
		{
			return fullMapping;
		}

		String newMapping = MappingToolbox.getFullInputFieldPath(newFieldSetName, newFieldName)
						+ fullMapping.substring(index + inputFieldPath.length());
		return newMapping;
	}

	/**
	 * Duplicate a display item
	 * 
	 * @param item
	 *            - the item to be duplicated
	 * 
	 * @return a duplicate copy
	 */
	public static IDisplayItem duplicateDisplayItem(IDisplayItem item)
	{
		if (item instanceof DisplayGroup)
		{
			return new DisplayGroup((DisplayGroup) item);
		}
		else if (item instanceof DisplayLabel)
		{
			return new DisplayLabel((DisplayLabel) item);
		}
		else if (item instanceof DisplayAttributes)
		{
			return new DisplayAttributes((DisplayAttributes) item);
		}
		else
		{
			return null;
		}
	}

	/**
	 * Duplicate a display item
	 * 
	 * @param item
	 *            - the item to be duplicated
	 * 
	 * @return a duplicate copy
	 */
	public static IDisplayItem hideDisplayItem(IDisplayItem item)
	{
		if (item instanceof DisplayGroup)
		{
			((DisplayGroup) item).setVisible(DisplayAttributes.VISIBLE_NO);
		}
		else if (item instanceof DisplayAttributes)
		{
			((DisplayAttributes) item).setVisible(DisplayAttributes.VISIBLE_NO);
		}

		return item;
	}

	/**
	 * Add a drill down child function
	 * 
	 * @param parentFunctionHandler
	 *            - the parent function handler
	 * @param optionId
	 *            - the option id of the child
	 * @param context
	 *            - the context string
	 * @param repeatingGroupId
	 *            - the repeating group id
	 * @param rowIndex
	 *            - the current row (zero based)
	 * 
	 * @return true if child successfully created
	 */
	public static boolean addDrillDownChild(FunctionHandler parentFunctionHandler, String optionId, String context,
					String repeatingGroupId, int rowIndex) throws EQException
	{
		// parent
		FunctionHandlerData parentFhd = parentFunctionHandler.getFhd();
		ScreenSet screenSet = parentFhd.getScreenSetHandler().rtvScrnSetCurrent();

		// setup the drill down child
		FunctionHandler fh = new FunctionHandler(parentFhd.getEquationUser(), new FunctionInfo(parentFhd.getFunctionInfo()
						.getSessionId(), parentFhd.getFunctionInfo().getName(), parentFhd.getFunctionInfo()));
		FunctionHandlerData fhd = fh.getFhd();
		fhd.setParentFunctionHandler(parentFunctionHandler);
		fhd.getContextHandler().copy(parentFhd.getContextHandler());
		fhd.getContextHandler().saveFunctionToContextData(screenSet.getFunction(), screenSet.getFunctionData());

		// if parent is a popup, then all child will also be a popup
		fhd.getFunctionInfo().setSessionType(EquationCommonContext.SESSION_CHILD_DRILLDOWN_SAMEWINDOW);
		if (EquationCommonContext.isChildDesktopSessionPopup(parentFhd.getFunctionInfo().getSessionType()))
		{
			fhd.getFunctionInfo().setSessionType(EquationCommonContext.SESSION_CHILD_DRILLDOWN);
		}

		fhd.setCurrentRepeatingGroup(repeatingGroupId);
		fhd.setCurrentRepeatingRow(rowIndex + 1);

		boolean ok = fh.doNewTransaction(optionId, context);

		// set the child of the parent
		parentFhd.setChildFunctionHandler(fh);
		parentFhd.setLastRepeatingGroup(repeatingGroupId);
		parentFhd.setLastRepeatingRow(rowIndex + 1);

		// child successfully created?
		if (ok)
		{
			parentFhd.getFunctionMsgManager().getFunctionMessages().insertMessages(
							fh.getFhd().getFunctionMsgManager().getFunctionMessages());

			if (fhd.isLegacyOption())
			{
				fhd.setCurrentDrillDownContext(context);
			}
		}

		// success?
		return ok;
	}

	/**
	 * Add an special operation drill down child function (e.g. add, delete, edit records)
	 * 
	 * @param parentFunctionHandler
	 *            - the parent function handler
	 * @param operation
	 * @param optionId
	 *            - the option id of the child
	 * @param groupId
	 *            - the repeating group id
	 * @param rowIndex
	 * 
	 * @return true if child successfully created
	 * @throws EQException
	 */
	private static boolean addDrillDownChild(FunctionHandler parentFunctionHandler, String operation, String repeatingGroupId,
					int rowIndex) throws EQException
	{
		// parent
		FunctionHandlerData parentFhd = parentFunctionHandler.getFhd();

		// setup the drill down child
		FunctionHandler fh = new FunctionHandler(parentFhd.getEquationUser(), new FunctionInfo(parentFhd.getFunctionInfo()
						.getSessionId(), parentFhd.getFunctionInfo().getName(), parentFhd.getFunctionInfo()));
		fh.getFhd().setParentFunctionHandler(parentFunctionHandler);
		fh.getFhd().getContextHandler().copy(parentFhd.getContextHandler());
		fh.getFhd().getFunctionInfo().setSessionType(EquationCommonContext.SESSION_CHILD_DRILLDOWN_SAMEWINDOW_EDIT);

		// setup this child
		// fh.getFhd().setup(parentFhd.getOptionId(), groupId); <-- commented out as setup() is made private

		// copy the content of the row
		if (!operation.equals(LinkedFunctionsToolbox.LINKED_ADD) && !operation.equals(LinkedFunctionsToolbox.LINKED_INS))
		{
			FunctionData parentFunctionData = parentFhd.getScreenSetHandler().rtvScreenSetMain().getFunctionData();
			FunctionData childFunctionData = fh.getFhd().getScreenSetHandler().rtvScreenSetMain().getFunctionData();

			// copy all the data including non-repeating field
			childFunctionData.copyFixedData(parentFunctionData);

			// copy specific row from the parent to the child
			// TODO: Was this just copying one row in one RepeatingDataManager?
			// parentFunctionData.getRepeatingDataManager().copyRepeatingDataTo(childFunctionData, rowIndex);
		}

		// set the child of the parent
		parentFhd.setChildFunctionHandler(fh);
		parentFhd.setLastRepeatingGroup(repeatingGroupId);
		parentFhd.setLastRepeatingRow(rowIndex + 1);

		// copy the messages back to the parent
		parentFhd.getFunctionMsgManager().getFunctionMessages().insertMessages(
						fh.getFhd().getFunctionMsgManager().getFunctionMessages());

		// success?
		return true;
	}

	/**
	 * Add a new screen set to drill down to a linked function (e.g. add, delete, edit records)
	 * 
	 * @param parentFunctionHandler
	 *            - the parent function handler
	 * @param operation
	 *            - edit, add or delete (see LinkedFunctionToolbox)
	 * @param repeatingGroupId
	 *            - the repeating group id
	 * @param rowIndex
	 *            - the current row
	 * 
	 * @return true if child successfully created
	 */
	public static boolean addDrillDownScreenSet(FunctionHandler parentFunctionHandler, String operation, String repeatingGroupId,
					int rowIndex) throws EQException
	{
		// parent
		FunctionHandlerData parentFhd = parentFunctionHandler.getFhd();
		ScreenSetHandler screenSetHandler = parentFhd.getScreenSetHandler();

		// create the screen set
		ScreenSetRepeatFields screenSet = new ScreenSetRepeatFields(0, parentFhd, screenSetHandler.getCurScreenSet(), parentFhd
						.getOptionId(), repeatingGroupId, operation);

		// copy the content of the row
		screenSet.inData(screenSetHandler.rtvScrnSetCurrent().getFunctionData(), rowIndex);

		// add and reposition to this screen
		screenSetHandler.addScreenSet(screenSet);
		screenSetHandler.setCurScreenSet(screenSet.getId());

		// success?
		return true;
	}

	/**
	 * Reformat the function and layout to prepare the function for editing the repeating group. <br>
	 * This creates a new function and layout containing only the repeating group. All other fields are made into a work field
	 * 
	 * @param screenSet
	 *            - the screen set which must contain repeating fields
	 * @param repeatingGroupId
	 *            - the repeating group id
	 * 
	 * @return true if successful
	 * 
	 * @throws EQException
	 */
	public static boolean prepareForEditRepeatingData(ScreenSet screenSet, String repeatingGroupId) throws EQException
	{
		// create a new layout based on the main layout
		Layout layoutMain = screenSet.getLayout();
		Layout newLayout = (Layout) FunctionRuntimeToolbox.copyElement(layoutMain, new Layout());

		// create a new function based on the main function
		Function functionMain = screenSet.getFunction();
		Function newFunction = (Function) FunctionRuntimeToolbox.copyElement(functionMain, new Function());

		// copy it
		boolean copied = FunctionRuntimeToolbox.copyRepeatingFields(repeatingGroupId, functionMain, layoutMain, newFunction,
						newLayout);
		FunctionRuntimeToolbox.copyValidateMappings(newFunction, functionMain);

		// now re-setup
		screenSet.resetup(newFunction, newLayout);

		// set something on the function data to signify that this is a repeating group
		FunctionControlData functionControlData = screenSet.getFunctionData().getFunctionControlData();
		functionControlData.addData(FunctionControlData.REPEATING_GROUP, repeatingGroupId);

		return copied;
	}

	/**
	 * Edit a data in db format
	 * 
	 * @param equationUser
	 *            - the Equation User
	 * @param functionData
	 *            - the Function Data
	 * @param dbValue
	 *            - the string to edit. Must be in DB format
	 * @param fieldType
	 *            - the field type
	 * @param fieldDecimal
	 *            - the number of decimal places
	 * @param editCode
	 *            - the edit code
	 * @param editCodeParameter
	 *            - the edit code parameter
	 * 
	 * @return the edited input
	 */
	public static String editEquationData(EquationUser equationUser, FunctionData functionData, String dbValue, String fieldName,
					String fieldType, int fieldLength, int fieldDecimal, String editCode, String editCodeParameter,
					AttributesAdaptor attributeAdaptor, List<ReplacementToken> replacementTokens) throws EQException
	{
		String outputValue = "";

		// numeric
		if (EqDataType.isNumeric(fieldType))
		{
			int decimal = fieldDecimal;
			if (fieldDecimal == 0)
			{
				// no editing parameter
				if (editCodeParameter.equals(EqDataType.GLOBAL_DELIMETER))
				{
					decimal = 0;
				}

				// script or java
				else
				{
					String currency = "";
					// script
					if (editCodeParameter.length() > 0)
					{
						currency = ELRuntime.runStringScript(editCodeParameter, functionData, fieldName, LanguageResources
										.getString("Language.EditCodeParameter"), "", ELRuntime.DB_VALUE);
					}

					// java code
					else if (attributeAdaptor != null)
					{
						currency = attributeAdaptor.getEditingParameter();
					}

					// is the currency a number?
					try
					{
						decimal = Integer.parseInt(currency);
					}
					catch (NumberFormatException e)
					{
						// retrieve the currency details
						C8RecordDataModel c8Record = equationUser.getEquationUnit().getRecords().getC8Record(currency);
						if (c8Record != null)
						{
							decimal = Toolbox.parseInt(c8Record.getEditField(), 0);
						}
						else
						{
							decimal = 0;
						}
					}

					// incorrect decimal?
					if (decimal < 0)
					{
						decimal = EqDataType.DEFAULT_DECIMAL;
					}
				}
			}

			if (editCode.equals(EqDataType.EDIT_AMOUNT_DEFAULT))
			{
				outputValue = EqDataType.formatEquationAmount(dbValue, fieldLength, decimal, functionData.getIntegerSeparator(),
								functionData.getDecimalSeparator());
			}
			else if (editCode.equals(EqDataType.EDIT_RATE_DEFAULT))
			{
				outputValue = EqDataType.formatNumber(dbValue, fieldLength, decimal, functionData.getIntegerSeparator(),
								functionData.getDecimalSeparator());
			}
			else if (editCode.equals(EqDataType.EDIT_AMOUNT_WITH_REPLACE))
			{
				outputValue = formatEquationAmountWithReplacement(dbValue, fieldLength, decimal,
								functionData.getIntegerSeparator(), functionData.getDecimalSeparator(), replacementTokens,
								functionData);
			}
		}

		// date
		else if (EqDataType.isDate(fieldType))
		{
			outputValue = EqDataType.formatEquationDate(dbValue, functionData.getDateInputFormat(), functionData.getOpenDateFull(),
							equationUser.getSystemDictionary(EquationParameters.HA_SDJAN));
		}

		return outputValue;
	}

	/**
	 * Format an Equation amount <br>
	 * - edit *HIVAL as blank <br>
	 * 
	 * @param eqAmount
	 *            - the amount in Equation amount format
	 * @param length
	 *            - the field length
	 * @param decimal
	 *            - the number of decimal places
	 * @param foreignIntegerSeparator
	 *            - the integer separator in user language
	 * @param foreignDecimalSeparator
	 *            - the decimal separator in user language
	 * 
	 * @return the formatted amount
	 */
	public static String formatEquationAmountWithReplacement(String eqAmount, int length, int decimal,
					String foreignIntegerSeparator, String foreignDecimalSeparator, List<ReplacementToken> replacementTokens,
					FunctionData functionData)
	{
		// is hival or loval?
		String searchString = eqAmount;
		if (EqDataType.isHival(eqAmount, length))
		{
			searchString = EqDataType.MAXIMUM_AMOUNT;
		}
		else if (EqDataType.isLoval(eqAmount, length))
		{
			searchString = EqDataType.MINIMUM_AMOUNT;
		}

		// search for it
		if (replacementTokens != null)
		{
			String replacementString = ReplacementToken.rtvReplacement(replacementTokens, EqDataType.TYPE_ZONED, searchString,
							functionData);
			if (replacementString != null)
			{
				return replacementString;
			}
		}

		return EqDataType.formatEquationAmount(eqAmount, length, decimal, foreignIntegerSeparator, foreignDecimalSeparator);
	}

	/**
	 * Check for incomplete HTML generation
	 * 
	 * @param equationUser
	 *            - the Equation user
	 * @param screenSet
	 *            - the screen set
	 * @param listMessages
	 *            - the repository to add the message
	 * 
	 * @return true if any message has been issued
	 */
	public static boolean checkIncompleteMessage(EquationUser equationUser, ScreenSet screenSet, List<FunctionMessage> listMessages)
	{
		try
		{
			boolean msg = false;

			// html incomplete?
			if (screenSet.isHtmlGenerationIncomplete())
			{
				EQMessage eqMessage = equationUser.getSession().getMessage(
								"KSM7348" + LanguageResources.getString("Language.TooManyField"));
				FunctionMessage fm = new FunctionMessage(0, 0, "", 0, eqMessage, "", "");
				listMessages.add(fm);
				msg = true;
			}

			// load api not loaded due to exceeding limit per api
			for (String loadAPI : screenSet.getLoadAPIIncomplete())
			{
				EQMessage eqMessage = equationUser.getSession().getMessage(
								"KSM7348"
												+ LanguageResources.getFormattedString("Language.LoadAPIExceedLimit",
																new String[] { loadAPI }));
				FunctionMessage fm = new FunctionMessage(0, 0, "", 0, eqMessage, "", "");
				listMessages.add(fm);
				msg = true;
			}

			// load api not loaded due to exceeding limit for total load api
			for (String loadAPI : screenSet.getLoadAPI2Incomplete())
			{
				EQMessage eqMessage = equationUser.getSession().getMessage(
								"KSM7348"
												+ LanguageResources.getFormattedString("Language.LoadAPIExceedLimitAll",
																new String[] { loadAPI }));
				FunctionMessage fm = new FunctionMessage(0, 0, "", 0, eqMessage, "", "");
				listMessages.add(fm);
				msg = true;
			}

			return msg;
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
		return false;
	}

	/**
	 * Determine whether the option is allowed at runtime taking into consideration the session type and user authorisation
	 * 
	 * @param sessionType
	 *            - the session type
	 * @param equationUser
	 *            - the Equation user
	 * @param option
	 *            - the option id
	 * @param isWebFacing
	 *            - webFacing installed?
	 * 
	 * @return true if the option is allowed
	 */
	public static boolean isAllowLinkFunction(int sessionType, EquationUser equationUser, String option, boolean isWebFacing)
	{
		boolean allow = equationUser.checkAuthorisedOption(option, isWebFacing);

		// legacy function cannot be run on popup
		if (allow && EquationCommonContext.isChildDesktopSessionPopup(sessionType))
		{
			try
			{
				allow = !equationUser.getEquationUnit().isLegacyOption(option);
			}
			catch (Exception e)
			{
				LOG.error(e);
				return false;
			}
		}

		return allow;
	}

	/**
	 * Return the label of a display group fields based solely on display group
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param inputField
	 *            - the input field
	 * @param displayGroupSet
	 *            - the display group field set where the field is suppose to belong to
	 * 
	 * @return the field label in user's language
	 */
	public static String getLabel(EquationUser eqUser, DisplayGroup displayGroup, FunctionData functionData)
	{
		String fieldLabel = "";
		fieldLabel = displayGroup.rtvLabel(eqUser);

		if (displayGroup.getLabelScriptRunTime().trim().length() > 0)
		{
			String labelRuntime = ELRuntime.runStringScript(displayGroup.getLabelScriptRunTime(), functionData, displayGroup
							.getId(), LanguageResources.getString("FunctionRuntimeToolbox.LabelRunTime"), fieldLabel,
							ELRuntime.DB_VALUE);
			if (labelRuntime.trim().length() > 0)
			{
				fieldLabel = labelRuntime;
			}
		}

		// return the label
		return fieldLabel;
	}

	/**
	 * Retrieves the base language of the service or layout
	 * 
	 * @param elem
	 * @param labelType
	 * @return the base language
	 */
	public static String getServiceBaseLanguage(Element elem, String labelType)
	{
		String serviceBaseLanguage = null;
		if (labelType.equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE)
						|| labelType.equalsIgnoreCase(Element.TEXT_VALUE_REUSABLE_REFERENCE))
		{
			Element element = elem;

			while (true)
			{
				if (element instanceof Layout)
				{
					serviceBaseLanguage = ((Layout) element).getBaseLanguage();
					break;
				}

				else if (element instanceof Function)
				{
					serviceBaseLanguage = ((Function) element).getBaseLanguage();
					break;
				}

				else
				{
					element = element.rtvParent();
				}

				if (element == null)
				{
					break;
				}
			}
		}

		if (serviceBaseLanguage == null || serviceBaseLanguage.trim().length() == 0)
		{
			serviceBaseLanguage = EquationUser.DEF_LANG;
		}
		return serviceBaseLanguage;

	}

	/**
	 * Returns the encrypted password
	 * 
	 * See EquationFunctionMapperImpl.getPassword(String,String,String)
	 * 
	 * @param userId
	 *            - the user id
	 * @param login
	 *            - the Equation login
	 * 
	 * @return the encrypted password
	 */
	public static String getPassword(String userId, EquationLogin login)
	{
		EquationFunctionMapperImpl mapper = (EquationFunctionMapperImpl) elContext.getFunctionMapper();
		return mapper.getPassword(userId, login.getPassword(), login.getPasswordType());
	}

	/**
	 * Convert the message severity
	 * 
	 * @param msgSev
	 *            - the message severity
	 * 
	 * @return the equivalent message in the overall message status
	 */
	public static String cvtOverallStatus(int msgSev)
	{
		if (msgSev == FunctionMessages.MSG_INFO)
		{
			return OVERALL_MESSAGE_SUCCESSINFO;
		}
		else if (msgSev == FunctionMessages.MSG_WARN)
		{
			return OVERALL_MESSAGE_WARNING;
		}
		else if (msgSev == FunctionMessages.MSG_ERROR)
		{
			return OVERALL_MESSAGE_ERROR;
		}
		else
		{
			return OVERALL_MESSAGE_SUCCESS;
		}
	}

	/**
	 * Convert function key to task action
	 * 
	 * @param ckey
	 *            - the function key
	 */
	public static String cvtFKeytoTaskAction(int ckey)
	{
		if (ckey == FunctionKeys.KEY_AUTHA)
		{
			return TaskEngineToolbox.TASK_ACTION_AUTH;
		}
		else if (ckey == FunctionKeys.KEY_REFER)
		{
			return TaskEngineToolbox.TASK_ACTION_REFER;
		}
		else if (ckey == FunctionKeys.KEY_DECL)
		{
			return TaskEngineToolbox.TASK_ACTION_DECL;
		}
		else if (ckey == FunctionKeys.KEY_DEL)
		{
			return TaskEngineToolbox.TASK_ACTION_AUTH;
		}
		else
		{
			return TaskEngineToolbox.TASK_ACTION_UPDATE;
		}
	}

	/**
	 * Start an LRP process
	 * 
	 * @param screenSet
	 *            - the ScreenSet whose data is the payload
	 * @param screenSetCRM
	 *            - the ScreenSet for CRM
	 * @param screenSetEFC
	 *            - the ScreenSet for EFC
	 * @param serviceName
	 *            - the JUDDI service
	 * @param operationName
	 *            - the operation name
	 * 
	 * @return the output
	 * 
	 * @throws EQException
	 */
	public static StartLRPProcessRsHeader startLRPProcess(ScreenSet screenSet, ScreenSet screenSetCRM, ScreenSet screenSetEFC,
					String serviceName, String operationName) throws EQException
	{
		// task engine
		String sessionIdentifer = screenSet.getFhd().getFunctionInfo().getSessionId();
		ITaskEngine taskEngine = EquationFunctionContext.getContext().getTaskEngine(sessionIdentifer);

		// convert the data into BF complex type
		Object payload = screenSet.cvtDataToBfData();
		Object payloadCRM = null;
		Object payloadEFC = null;

		if (screenSetCRM != null)
		{
			payloadCRM = screenSetCRM.cvtDataToBfData();
		}

		if (screenSetEFC != null)
		{
			payloadEFC = screenSetEFC.cvtDataToBfData();
		}

		// start the process
		StartLRPProcessRsHeader output = LRPToolbox.startLRPProcess(taskEngine, serviceName, operationName, payload, payloadCRM,
						payloadEFC);

		// return the output
		return output;
	}

	/**
	 * Return the linkage id
	 * 
	 * @param screenSet
	 *            - the screen set
	 * @return the linkage id
	 */
	public static String getLinkageServiceId(ScreenSet screenSet)
	{
		if (screenSet.isLinkService())
		{
			return screenSet.getFhd().getOptionId();
		}
		else
		{
			return null;
		}
	}

	/**
	 * Return the conversion rules based on the service header and an initialised fhd
	 * 
	 * @param serviceRqHeader
	 *            - the Service Request
	 * @param fhd
	 *            - the Function handler data
	 */
	public static ConversionRules getConversionRules(ServiceRqHeader serviceRqHeader, FunctionHandlerData fhd)
	{
		EquationUser equationUser = fhd.getEquationUser();
		ConversionRules conversionRules = new ConversionRules(equationUser.getEquationUnit());

		// Enhanced XSD?
		ScreenSet mainScreenSet = fhd.getScreenSetHandler().rtvScreenSetMain();

		// Nothing?
		if (mainScreenSet == null)
		{
			return conversionRules;
		}

		// Set the function
		Function function = mainScreenSet.getFunction();

		// Set default formatting
		if (function.chkXSDGeneric())
		{
			conversionRules.setDateFormat(ConversionRules.DATE_YYYY_MM_DD);
			conversionRules.setAmountFormat(ConversionRules.AMOUNT_MAJOR_CURRENCY);
		}
		else
		{
			conversionRules.setDateFormat(ConversionRules.DATE_CYYMMDD);
			conversionRules.setAmountFormat(ConversionRules.AMOUNT_MINOR_CURRENCY);
		}

		// Determine the date and amount format
		if (serviceRqHeader != null)
		{
			RqHeader rqHeader = serviceRqHeader.getRqHeader();

			if (rqHeader.getFormatting() != null && rqHeader.getFormatting().getDateFormat() != null
							&& rqHeader.getFormatting().getDateFormat().trim().length() > 0)
			{
				conversionRules.setDateFormat(rqHeader.getFormatting().getDateFormat().trim());
			}

			if (rqHeader.getFormatting() != null && rqHeader.getFormatting().getAmountFormat() != null
							&& rqHeader.getFormatting().getAmountFormat().trim().length() > 0)
			{
				conversionRules.setAmountFormat(rqHeader.getFormatting().getAmountFormat().trim());
			}
		}

		// Enhanced XSD
		if (function.chkXSDGeneric())
		{
			FunctionAdaptor functionAdaptor = null;
			EquationStandardSession session = equationUser.getSession();

			if (!EquationCommonContext.isBankFusionInstalled())
			{
				functionAdaptor = mainScreenSet.getFunctionAdaptor();
			}

			XSDStructureLink link = XMLToolbox.getXMLToolbox().getXSDStructureLink(session.getUnitId(), function.getId());
			conversionRules.setEnhancedXSD(link, session, functionAdaptor);
		}

		// Non-enhanced XSD
		else
		{
			conversionRules.setSession(equationUser.getSession());
		}

		return conversionRules;
	}

	/**
	 * Load default response if there is error
	 * 
	 * @param function
	 *            - the Function bean
	 * @param optionId
	 *            - the option Id (if function bean is null)
	 * @param conversionRules
	 *            - the conversion rule
	 * @param functionHandler
	 *            - the Function handler
	 * 
	 * @return the default response data
	 * 
	 * @throws EQException
	 */
	public static Object loadDefaultResponse(Function function, String optionId, ConversionRules conversionRules,
					FunctionHandler functionHandler, Object defaultValue) throws EQException
	{
		// function has not been set, then try to load from database
		if (function == null)
		{
			try
			{
				FunctionHandlerData fhd = functionHandler.getFhd();
				EquationStandardSession session = fhd.getEquationUser().getSession();
				function = XMLToolbox.getXMLToolbox().getFunction(session, optionId, false);

				if (function == null)
				{
					return defaultValue;
				}

				if (conversionRules == null)
				{
					conversionRules = new ConversionRules(fhd.getEquationUser().getEquationUnit());
					XSDStructureLink link = XMLToolbox.getXMLToolbox().getXSDStructureLink(session.getUnitId(), optionId);
					conversionRules.setEnhancedXSD(link, session, fhd.getFunctionAdaptorHandler().getFunctionAdaptor(session,
									optionId));
				}
			}
			catch (Exception e)
			{
				return defaultValue;
			}
		}

		// Not using enhanced XSD, then just return the default value
		if (!function.chkXSDGeneric())
		{
			return defaultValue;
		}

		// Setup the conversion rules
		if (conversionRules == null)
		{
			conversionRules = FunctionRuntimeToolbox.getConversionRules(null, functionHandler.getFhd());
		}

		// load the object
		Object outputServiceData = conversionRules.loadResponseClasses();
		return outputServiceData;
	}

	/**
	 * Return the conversion rules for the secondary service
	 * 
	 * @param conversionRules
	 *            - the conversion rules for the primary service
	 * @param secondaryFunction
	 *            - the secondary function
	 * @param secondaryFunctionAdaptor
	 *            - the secondary function adaptor
	 * 
	 * @return the conversion rules for the secondary service
	 * 
	 * @throws EQException
	 */
	public static ConversionRules getConversionRulesForLinkService(ConversionRules conversionRules, Function secondaryFunction,
					FunctionAdaptor secondaryFunctionAdaptor)
	{
		EquationStandardSession session = conversionRules.getSession();
		String secondaryFunctionId = secondaryFunction.getId();

		// BF, then secondary function adaptor must not be set
		if (EquationCommonContext.isBankFusionInstalled())
		{
			secondaryFunctionAdaptor = null;
		}

		XSDStructureLink secondaryXSDLink = XMLToolbox.getXMLToolbox()
						.getXSDStructureLink(session.getUnitId(), secondaryFunctionId);

		ConversionRules conversionRulesForLink = new ConversionRules(conversionRules);
		conversionRulesForLink.setEnhancedXSD(secondaryXSDLink, session, secondaryFunctionAdaptor);
		return conversionRulesForLink;
	}

	/**
	 * Change the messages to point to a specified field
	 * 
	 * @param messages
	 *            - the list of messages
	 * @param fieldName
	 *            - the new field name
	 * @param index
	 *            - the new index (if field name is in a row)
	 */
	public static void changeFieldName(FunctionMessages messages, String fieldName, int index)
	{
		for (FunctionMessage message : messages.getMessages())
		{
			message.setFieldName(fieldName);
			message.setSequence(index);
		}
	}

}