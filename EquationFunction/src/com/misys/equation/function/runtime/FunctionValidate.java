package com.misys.equation.function.runtime;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import com.ibm.as400.access.BidiStringType;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationPVData;
import com.misys.equation.common.access.EquationPVDecodeMetaData;
import com.misys.equation.common.access.EquationPVFieldMetaData;
import com.misys.equation.common.access.EquationPVMetaData;
import com.misys.equation.common.access.EquationStandardMultipleValidation;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardValidation;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.globalprocessing.SystemStatusManager;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EqTimingTest;
import com.misys.equation.common.utilities.EquationAPICache;
import com.misys.equation.common.utilities.EquationAPICacheHandler;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.adaptor.FieldAdaptor;
import com.misys.equation.function.adaptor.FieldSetAdaptor;
import com.misys.equation.function.adaptor.FunctionAdaptor;
import com.misys.equation.function.adaptor.InputFieldSetAdaptor;
import com.misys.equation.function.adaptor.LayoutAdaptor;
import com.misys.equation.function.adaptor.ValueAdaptor;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.FieldData;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.Mapping;
import com.misys.equation.function.beans.PVField;
import com.misys.equation.function.beans.PVFieldSet;
import com.misys.equation.function.beans.RelatedFields;
import com.misys.equation.function.beans.RepeatingDataManager;
import com.misys.equation.function.beans.RepeatingFieldData;
import com.misys.equation.function.beans.ReplacementToken;
import com.misys.equation.function.beans.ValidationExpression;
import com.misys.equation.function.beans.WorkField;
import com.misys.equation.function.el.ELRuntime;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.tools.AdaptorToolbox;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.FunctionValidateMultiplePVHelper;
import com.misys.equation.function.tools.FunctionValidatePVSpecific;
import com.misys.equation.function.tools.LinkedFunctionsToolbox;
import com.misys.equation.function.tools.MappingToolbox;
import com.misys.equation.function.tools.ReturnDataMFUserExit;
import com.misys.equation.function.useraccess.UserExitMessage;

/**
 * This class represent a Function Validator
 */
public class FunctionValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionValidate.java 17613 2013-11-19 18:05:21Z whittap1 $";

	// Logger
	private static final EquationLogger LOG = new EquationLogger(FunctionValidate.class);

	private FunctionHandlerData fhd;
	private ScreenSet screenSet;
	private FunctionData functionData;
	private FunctionMessages functionMessages;
	private EquationStandardSession homeEquationStandardSession;
	private EquationStandardSession currentEquationStandardSession;
	private FunctionMsgManager functionMsgManager;
	private EquationUser eqUser;

	private FunctionAdaptor functionAdaptor;
	private LayoutAdaptor layoutAdaptor;
	private boolean defaultValues;
	private boolean haltOnError;
	private boolean inputMappingExist;
	private boolean applicationValidate;
	private FunctionDebugInfo functionDebugInfo;

	private FunctionValidatePVSpecific validatePVSpecific; // PV specific processing
	private FunctionValidateMultiplePVHelper validateMultiplePVHelper; // For multiple PV processing

	private boolean repeatingGroupVerticalValidation; // vertical validation for repeating group

	// Determines whether to validate all fields (true) or selected fields only (false)
	// e.g. for Enquiry services, then there is nothing to validate
	// e.g. for repeating group, no need to validate as fields are read-only
	public static final int VAL_FIELD_AUTO = 0;
	public static final int VAL_FIELD_YES = 1;
	public static final int VAL_FIELD_NO = 2;
	private int validateAllFixedFields;
	private int validateAllRepeatingFields;

	private int rowId; // the current row being validated
	private String rowIdString; // the current row being validated, which is added as a second level text
	private boolean selectionExists; // set to true if selection option has been specified during the validation
	private boolean selectionExistsError; // set to true if selection option error

	private boolean revalidation; // set to TRUE when doing revalidation due to user exit changes

	private boolean loadMode; // determine whether during load mode stage (loading of keys)

	/**
	 * Construct a new Function Validate given the Function Handler
	 */
	public FunctionValidate(FunctionHandlerData functionHandlerData)
	{
		setupFromFunctionHandler(functionHandlerData);
		commonInitialisation();
	}

	/**
	 * Construct a new Function Validate given the Function Handler and the Function Data details to validate
	 */
	public FunctionValidate(FunctionHandlerData functionHandlerData, ScreenSet screenSet, FunctionData functionData)
	{
		setupFromFunctionHandler(functionHandlerData);
		this.screenSet = screenSet;
		this.functionData = functionData;
		commonInitialisation();
	}

	/**
	 * Setup properties based on function handler
	 * 
	 * @param functionHandler
	 *            - the function handler
	 */
	private void setupFromFunctionHandler(FunctionHandlerData functionHandlerData)
	{
		this.fhd = functionHandlerData;
		this.screenSet = functionHandlerData.getScreenSetHandler().rtvScrnSetCurrent();
		this.homeEquationStandardSession = functionHandlerData.getEquationUser().getSessionForNonUpdate();
		this.functionMsgManager = functionHandlerData.getFunctionMsgManager();
		this.functionData = screenSet.getFunctionData();
		this.eqUser = functionHandlerData.getEquationUser();
	}

	/**
	 * Common initialisation
	 */
	private void commonInitialisation()
	{
		this.functionMessages = new FunctionMessages();
		this.haltOnError = true;
		this.defaultValues = true;
		this.applicationValidate = true;
		this.functionDebugInfo = new FunctionDebugInfo();
		this.rowId = 0;
		this.rowIdString = "";
		this.selectionExists = false;
		this.selectionExistsError = false;
		this.validatePVSpecific = new FunctionValidatePVSpecific(eqUser, functionData);
		this.repeatingGroupVerticalValidation = true;
		this.validateAllFixedFields = VAL_FIELD_YES;
		this.validateAllRepeatingFields = VAL_FIELD_YES;
		this.validateMultiplePVHelper = null;
		this.revalidation = false;
		this.loadMode = false;
	}

	/**
	 * Returns the list of messages
	 * 
	 * @return the list of messages
	 */
	public FunctionMessages getFunctionMessages()
	{
		return functionMessages;
	}

	/**
	 * Determine if the validate module ignores any errors when validating multiple screens
	 * 
	 * @return true - if errors are ignored
	 */
	public boolean isHaltOnError()
	{
		return haltOnError;
	}

	/**
	 * Set whether to bypass error
	 * 
	 * @param haltOnError
	 *            true - ignore error
	 * 
	 */
	public void setHaltOnError(boolean haltOnError)
	{
		this.haltOnError = haltOnError;
	}

	/**
	 * Determine whether the validate module should invoke processing that will change the input values
	 * 
	 * @return true - if it should invoke processing that will change the input values
	 */
	public boolean isDefaultValues()
	{
		return defaultValues;
	}

	/**
	 * Set whether the validate module should invoke processing that will change the input values
	 * 
	 * @param defaultValues
	 *            - true, if the validate module should invoke processing that will change the input values
	 */
	public void setDefaultValues(boolean defaultValues)
	{
		this.defaultValues = defaultValues;
	}

	/**
	 * Determine whether the validate module should invoke application validation
	 * 
	 * @return true - if the validate module should invoke application validation
	 */
	public boolean isApplicationValidate()
	{
		return applicationValidate;
	}

	/**
	 * Set whether the validate module should invoke application validation
	 * 
	 * @param applicationValidate
	 *            - true if the validate module should invoke application validation
	 */
	public void setApplicationValidate(boolean applicationValidate)
	{
		this.applicationValidate = applicationValidate;
	}

	/**
	 * Determine if selection option has been specified during the validation pass
	 * 
	 * @return true if selection option has been specified during the validation pass
	 */
	public boolean isSelectionExists()
	{
		return selectionExists;
	}

	/**
	 * Determine if error with selection option
	 * 
	 * @return true if error with selection option
	 */
	public boolean isSelectionExistsError()
	{
		return selectionExistsError;
	}

	/**
	 * Determine whether validating all fixed fields
	 * 
	 * @param validateAllFixedFields
	 *            - true if validating all fixed fields
	 */
	public void setValidateAllFixedFields(int validateAllFixedFields)
	{
		this.validateAllFixedFields = validateAllFixedFields;
	}

	/**
	 * Determine whether validating all repeating fields
	 * 
	 * @param validateAllRepeatingFields
	 *            - true if validating all repeating fields
	 */
	public void setValidateAllRepeatingFields(int validateAllRepeatingFields)
	{
		this.validateAllRepeatingFields = validateAllRepeatingFields;
	}

	/**
	 * Set whether validation is happening during loading of keys
	 * 
	 * @param loadMode
	 *            - true, if validation is happening during loading of keys
	 */
	public void setLoadMode(boolean loadMode)
	{
		this.loadMode = loadMode;
	}

	/**
	 * Performs validation on all screens
	 * 
	 * @return the highest message severity
	 * 
	 * @throws EQException
	 */
	public int validate() throws EQException
	{
		return (validate(0, screenSet.getFunction().getInputFieldSets().size() - 1));
	}

	/**
	 * Performs validation on the specified screen
	 * 
	 * @return the highest message severity
	 * @throws EQException
	 */
	public int validate(int scrnNo) throws EQException
	{
		return (validate(scrnNo, scrnNo));
	}

	/**
	 * Performs validation on the specified field name
	 * 
	 * @return the highest message severity
	 * @throws EQException
	 */
	public int validate(int scrnNo, String fieldSetId, String fieldName) throws EQException
	{
		// debug info
		functionDebugInfo.printStartMethod("FunctionValidate: validate(1)");
		functionDebugInfo.printSyntaxValidation(scrnNo);
		functionDebugInfo.printField(fieldName);

		// reset message severity
		functionMessages.clearMessages();

		// setup the function/layout adaptor
		functionAdaptor = screenSet.getFunctionAdaptor();
		layoutAdaptor = screenSet.getLayoutAdaptor();

		// retrieve the field set
		InputFieldSet fieldSet = screenSet.getFunction().getInputFieldSets().get(scrnNo);
		DisplayAttributesSet displayAttributesSet = screenSet.getLayout().getDisplayAttributesSets().get(scrnNo);

		// retrieve the field
		InputField inputField = screenSet.getFunction().rtvFieldFromInputFieldSet(fieldSetId,
						FunctionRuntimeToolbox.getRootFieldNameForRepeatingField(fieldName));
		DisplayAttributes displayAttribute = FunctionRuntimeToolbox.getDisplayAttributes(displayAttributesSet.getDisplayItems(),
						inputField.getId());

		// is this a repeating field?
		if (Field.isRepeating(inputField))
		{
			functionData.getRepeatingDataManager(inputField.getRepeatingGroup()).setRow(fieldName);
		}

		// syntax validation
		int msgSev = syntaxValidate(scrnNo, fieldSet, inputField, displayAttribute);

		// application validation
		if (msgSev < FunctionMessages.MSG_ERROR)
		{
			msgSev = applicationValidate(scrnNo, fieldSet, inputField);
		}

		// Log all messages
		if (!functionMessages.chkNoMessage())
		{
			LOG.debug(LanguageResources.getString("FunctionValidate.ReturnedMessageFromValidate") + " : " + functionMessages);
		}

		// returns the message severity
		functionDebugInfo.printEndMethod("FunctionValidate: validate(1)");
		return msgSev;
	}

	/**
	 * Performs validation from the specified screen up to the specified screen
	 * 
	 * @return the message severity
	 * 
	 * @throws EQException
	 * 
	 */
	public int validate(int fromScrnNo, int toScrnNo) throws EQException
	{
		// debug info
		functionDebugInfo.printStartMethod("FunctionValidate: validate(2)");
		EqTimingTest.printStartTime("FunctionValidate.validate()", "");

		// reset message severity
		functionMessages.clearMessages();

		// setup the function/layout adaptor
		functionAdaptor = screenSet.getFunctionAdaptor();
		layoutAdaptor = screenSet.getLayoutAdaptor();

		// no selection
		selectionExists = false;
		selectionExistsError = false;

		for (int i = fromScrnNo; i <= toScrnNo; i++)
		{
			// user exit - default mode
			if (defaultValues)
			{
				defaultUserExit(i);
			}

			// validate the screen
			EqTimingTest.printStartTime("FunctionValidate.validate().screenValidate()", "screen=" + i);
			int msgSev = screenValidate(i);
			EqTimingTest.printTime("FunctionValidate.validate().screenValidate()", "screen=" + i);

			// user exit - validate mode
			if (applicationValidate && msgSev < FunctionMessages.MSG_ERROR)
			{
				validateUserExit(i);
			}

			// any major severity, then stop validation
			if (haltOnError && msgSev == FunctionMessages.MSG_ERROR)
			{
				break;
			}

			// initialise details for the next screen
			if (defaultValues && i < screenSet.getMaxScrnNo() - 1)
			{
				screenSet.fieldSetInitialisationScriptProcessing(i + 1);
			}

		}

		// Remove messages with lower severity
		int msgSev = functionMessages.getMsgSev();
		functionData.clearMessages(FunctionData.CLEAR_MSG_ALL, msgSev);

		// Print information
		functionDebugInfo.printEndMethod("FunctionValidate: validate(2)");
		EqTimingTest.printTime("FunctionValidate.validate()", "");

		// Log all messages
		if (!functionMessages.chkNoMessage())
		{
			LOG.debug(LanguageResources.getString("FunctionValidate.ReturnedMessageFromValidate") + " : " + functionMessages);
		}

		// return message severity
		return (msgSev);
	}

	/**
	 * Performs validation on the specified screen
	 * 
	 * @param scrnNo
	 *            - screen number
	 * 
	 * @return the highest message severity
	 * 
	 * @throws EQException
	 */
	private int screenValidate(int scrnNo) throws EQException
	{
		// retrieve the field set
		InputFieldSet fieldSet = screenSet.getFunction().getInputFieldSets().get(scrnNo);

		// syntax validate it
		functionDebugInfo.printSyntaxValidation(scrnNo);
		validateFieldSet(true, scrnNo, fieldSet);

		// application validate it
		if (applicationValidate && functionMessages.getMsgSev() < FunctionMessages.MSG_ERROR)
		{
			functionDebugInfo.printApplicationValidation(scrnNo);
			validateFieldSet(false, scrnNo, fieldSet);
		}

		// returns the message severity
		return functionMessages.getMsgSev();
	}

	/**
	 * Performs validation on the specified field set
	 * 
	 * @param syntax
	 *            - syntax or application validation
	 * @param scrnNo
	 *            - current screen number
	 * @param inputFieldSet
	 *            - the current field set
	 * 
	 * @throws EQException
	 */
	private void validateFieldSet(boolean syntax, int scrnNo, InputFieldSet inputFieldSet) throws EQException
	{
		// Determine if key is open?
		boolean detailOpen = !screenSet.getLoadFieldSetStatusHandler().isKeyOpen(inputFieldSet.getId());

		// List of repeating groups that already been validated
		Hashtable<String, String> listRepeatingGroups = new Hashtable<String, String>();

		// Syntax Validation - loop through all the fields
		for (InputField inputField : inputFieldSet.getInputFields())
		{
			boolean validate = false;

			// is the field displayed?
			if ((inputField.isKey() && !detailOpen) || (!inputField.isKey() && detailOpen))
			{
				validate = true;
			}

			// validate it
			if (validate)
			{
				if (Field.isRepeating(inputField))
				{
					// validate list
					if (functionData.getRepeatingDataManager(inputField.getRepeatingGroup()) != null
									&& listRepeatingGroups.get(inputField.getRepeatingGroup()) == null)
					{
						int backupValidateAllRepeatingFields = validateAllRepeatingFields;
						try
						{
							listRepeatingGroups.put(inputField.getRepeatingGroup(), "");
							if (repeatingGroupVerticalValidation)
							{
								validateRepeatingGroupVertical(syntax, scrnNo, inputFieldSet, inputField.getRepeatingGroup());
							}
							else
							{
								validateRepeatingGroup(syntax, scrnNo, inputFieldSet, inputField.getRepeatingGroup());
							}
						}
						finally
						{
							validateAllRepeatingFields = backupValidateAllRepeatingFields;
							validateMultiplePVHelper = null;
							rowId = 0;
							rowIdString = "";
						}
					}
				}
				else
				{
					// debug info
					functionDebugInfo.printField(inputField.getId());

					if (syntax)
					{
						DisplayAttributesSet displayAttributesSet = screenSet.getLayout().getDisplayAttributesSets().get(scrnNo);
						DisplayAttributes displayAttribute = FunctionRuntimeToolbox.getDisplayAttributes(displayAttributesSet
										.getDisplayItems(), inputField.getId());
						syntaxValidate(scrnNo, inputFieldSet, inputField, displayAttribute);
					}
					else
					{
						applicationValidate(scrnNo, inputFieldSet, inputField);
					}
				}
			}
		}

		// After calling all the Validate modules, now process all EL Script and Java
		// user exit assignments of InputField values (both to the Primitive form
		// and the Output form of the field)
		if (defaultValues && syntax)
		{
			// FunctionRuntimeToolbox.processInputFieldSetValidateAssignments(fieldSet, functionData, functionAdaptor,
			// equationStandardSession, fhd);
			FunctionRuntimeToolbox.processWorkFieldsValidateAssignments(screenSet.getFunction(), functionData, functionAdaptor,
							homeEquationStandardSession, fhd);
		}
	}

	/**
	 * Perform validation on the specified repeating group
	 * 
	 * @param syntax
	 *            - syntax or application validation
	 * @param scrnNo
	 *            - current screen number
	 * @param inputFieldSet
	 *            - the current field set
	 * @param repeatingGroup
	 *            - the repeating group id
	 * 
	 * @throws EQException
	 */
	private void validateRepeatingGroup(boolean syntax, int scrnNo, InputFieldSet inputFieldSet, String repeatingGroup)
					throws EQException
	{
		// List of repeating fields
		LinkedList<InputField> listRepeatingFields = null;

		// Linked function exists?
		LinkedFunctionsToolbox linkedFunctionsToolbox = null;

		// Loop through all rows
		int rowCount = 0;
		RepeatingDataManager dataManager = functionData.getRepeatingDataManager(repeatingGroup);
		dataManager.moveFirst();
		while (dataManager.next())
		{
			// create the list once only
			if (listRepeatingFields == null)
			{
				listRepeatingFields = new LinkedList<InputField>();
				for (InputField field : inputFieldSet.getInputFields())
				{
					if (field.getRepeatingGroup().equals(repeatingGroup))
					{
						listRepeatingFields.add(field);
					}
				}

				// is linked function exists, then initialise the linked functions
				DisplayGroup displayGroup = screenSet.getLayout().getDisplayAttributesSets().get(scrnNo).rtvRepeatingGroup(
								repeatingGroup);
				if (displayGroup != null && displayGroup.getLinkedFunctions().size() > 0)
				{
					linkedFunctionsToolbox = new LinkedFunctionsToolbox(displayGroup.getLinkedFunctions());
				}

				// in place editing?
				if (validateAllRepeatingFields == VAL_FIELD_AUTO
								&& displayGroup.getEditMode().equals(DisplayGroup.EDIT_MODE_IN_PLACE))
				{
					validateAllRepeatingFields = VAL_FIELD_YES;
				}
			}

			// row count;
			rowCount++;
			rowId = rowCount;
			rowIdString = getRowIdString(repeatingGroup, rowCount);

			// linked function exists, then validate the selection option
			if (syntax && linkedFunctionsToolbox != null)
			{
				validateSelectionOption(dataManager.rtvSelectionOptionId(), linkedFunctionsToolbox, scrnNo);
			}

			// Syntax Validation - loop through all the fields
			for (InputField inputField : listRepeatingFields)
			{
				functionDebugInfo.printField(inputField.getId() + rowIdString);

				if (syntax)
				{
					DisplayAttributesSet displayAttributesSet = screenSet.getLayout().getDisplayAttributesSets().get(scrnNo);
					DisplayAttributes displayAttribute = FunctionRuntimeToolbox.getDisplayAttributes(displayAttributesSet
									.getDisplayItems(), inputField.getId());
					syntaxValidate(scrnNo, inputFieldSet, inputField, displayAttribute);
				}
				else
				{
					applicationValidate(scrnNo, inputFieldSet, inputField);
				}
			}
		}
	}

	/**
	 * Perform validation on the specified repeating group vertically (e.g. validates all data in one column before next)
	 * 
	 * @param syntax
	 *            - syntax or application validation
	 * @param scrnNo
	 *            - current screen number
	 * @param fieldSet
	 *            - the current field set
	 * @param repeatingGroup
	 *            - the repeating group id
	 * 
	 * @throws EQException
	 */
	private void validateRepeatingGroupVertical(boolean syntax, int scrnNo, InputFieldSet fieldSet, String repeatingGroup)
					throws EQException
	{
		// Get all repeating fields for the group
		LinkedList<InputField> listRepeatingFields = new LinkedList<InputField>();
		for (InputField field : fieldSet.getInputFields())
		{
			if (field.getRepeatingGroup().equals(repeatingGroup))
			{
				listRepeatingFields.add(field);
			}
		}

		// is linked function exists, then initialise the linked functions
		LinkedFunctionsToolbox linkedFunctionsToolbox = null;
		DisplayGroup displayGroup = screenSet.getLayout().getDisplayAttributesSets().get(scrnNo).rtvRepeatingGroup(repeatingGroup);
		if (displayGroup != null && displayGroup.getLinkedFunctions().size() > 0)
		{
			linkedFunctionsToolbox = new LinkedFunctionsToolbox(displayGroup.getLinkedFunctions());
		}

		// in place editing?
		if (validateAllRepeatingFields == VAL_FIELD_AUTO && displayGroup.getEditMode().equals(DisplayGroup.EDIT_MODE_IN_PLACE))
		{
			validateAllRepeatingFields = VAL_FIELD_YES;
		}

		// data manager
		RepeatingDataManager dataManager = functionData.getRepeatingDataManager(repeatingGroup);

		// validate the selection option if exists
		if (syntax && linkedFunctionsToolbox != null)
		{
			int rowCount = 0;
			dataManager.moveFirst();
			while (dataManager.next())
			{
				rowCount++;
				rowId = rowCount;
				rowIdString = getRowIdString(repeatingGroup, rowCount);
				validateSelectionOption(dataManager.rtvSelectionOptionId(), linkedFunctionsToolbox, scrnNo);
			}
		}

		// validate on per field
		for (InputField inputField : listRepeatingFields)
		{
			// syntax validation is driven by the PV modules
			if (syntax)
			{
				processMultiplePVProcessing(dataManager, scrnNo, fieldSet, inputField);
			}

			// application validation
			else
			{
				int rowCount = 0;
				dataManager.moveFirst();
				while (dataManager.next())
				{
					rowCount++;
					rowId = rowCount;
					rowIdString = getRowIdString(repeatingGroup, rowCount);
					functionDebugInfo.printField(inputField.getId() + rowIdString);
					applicationValidate(scrnNo, fieldSet, inputField);
				}
			}
		}
	}

	/**
	 * Validate the selection option for a given repeating group
	 * 
	 * @param scrnNo
	 *            - current screen number
	 * @param fieldSet
	 *            - the current field set
	 * @param repeatingGroup
	 *            - the repeating group id
	 */
	public void validateSelectionOption(int scrnNo, InputFieldSet fieldSet, String repeatingGroup) throws EQException
	{
		// Linked function exists?
		LinkedFunctionsToolbox linkedFunctionsToolbox = null;

		// Loop through all rows
		int rowCount = 0;
		RepeatingDataManager dataManager = functionData.getRepeatingDataManager(repeatingGroup);
		dataManager.moveFirst();
		while (dataManager.next())
		{
			// create the list once only
			if (linkedFunctionsToolbox == null)
			{
				// is linked function exists, then initialise the linked functions
				DisplayGroup group = screenSet.getLayout().getDisplayAttributesSets().get(screenSet.getScrnNo()).rtvRepeatingGroup(
								repeatingGroup);
				if (group != null && group.getLinkedFunctions().size() > 0)
				{
					linkedFunctionsToolbox = new LinkedFunctionsToolbox(group.getLinkedFunctions());

					// supervisor, then override with default linked function
					if (fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_SUPER)
					{
						linkedFunctionsToolbox = new LinkedFunctionsToolbox(linkedFunctionsToolbox.getLinkedFunctionSupervisor());
					}
				}

				if (linkedFunctionsToolbox == null)
				{
					return;
				}
			}

			// row count;
			rowCount++;
			rowId = rowCount;
			rowIdString = getRowIdString(repeatingGroup, rowCount);

			// linked function exists, then validate the selection option
			validateSelectionOption(dataManager.rtvSelectionOptionId(), linkedFunctionsToolbox, scrnNo);
		}
	}

	/**
	 * Validate the selection option
	 * 
	 * @param selectionOptionId
	 *            - the selection option Id
	 * @param linkedFunctionsToolbox
	 *            - the list of valid linked functions
	 * @param scrnNo
	 *            - the current screen number
	 * 
	 */
	private void validateSelectionOption(String selectionOptionId, LinkedFunctionsToolbox linkedFunctionsToolbox, int scrnNo)
					throws EQException
	{
		FieldData fieldData = functionData.rtvFieldData(selectionOptionId);
		fieldData.clearMessages();
		String selectionOption = fieldData.getInputValue().trim();
		if (selectionOption.length() > 0)
		{
			selectionExists = true;

			boolean ignore = linkedFunctionsToolbox.validateLinkedFunction(functionData, eqUser, fhd.getFunctionInfo()
							.getSessionType(), fhd.getFunctionInfo().isWebFacingInstalled());
			if (ignore)
			{
				if (linkedFunctionsToolbox.isValidatedValidSelection(selectionOption))
				{
					fieldData.setDbValue(selectionOption);
				}
				else
				{
					selectionExistsError = true;
					functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo,
									fieldData.getFieldName(), rowId, fieldData, "KSM2042" + Toolbox.pad(selectionOption, 10)
													+ linkedFunctionsToolbox.getValidatedValidSelectionOptionsListing(), "",
									rowIdString, FunctionMessages.MSG_NONE);
				}
			}
			else
			{
				if (linkedFunctionsToolbox.isValidSelection(selectionOption))
				{
					fieldData.setDbValue(selectionOption);
				}
				else
				{
					selectionExistsError = true;
					functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo,
									fieldData.getFieldName(), rowId, fieldData, "KSM2042" + Toolbox.pad(selectionOption, 10)
													+ linkedFunctionsToolbox.getValidSelectionOptionsListing(), "", rowIdString,
									FunctionMessages.MSG_NONE);
				}
			}
		}

	}

	/**
	 * Performs syntax validation on the field<br>
	 * 
	 * @param scrnNo
	 *            - screen number
	 * @param fieldSet
	 *            - the display input field set
	 * @param field
	 *            - the input field
	 * 
	 * @return the message severity for this field checking
	 * 
	 * @throws EQException
	 */
	private int syntaxValidate(int scrnNo, InputFieldSet fieldSet, InputField field, DisplayAttributes displayAttribute)
					throws EQException
	{
		// Field name
		String fieldName = field.getId();

		// Field data
		FieldData fieldData = functionData.rtvFieldData(fieldName);
		if (fieldData == null)
		{
			functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), 0, field.getId(),
							rowId, null, "KSM7369" + fieldName, "", rowIdString, FunctionMessages.MSG_NONE);
			return functionMessages.getMsgSev();
		}

		// does it need to validate this?
		if (ignoreValidationField(fieldData))
		{
			functionMessages.insertMessages(fieldData.getFunctionMessages());
			return fieldData.getMsgSev();
		}

		// Retrieve the field label
		String fieldLabel = FunctionRuntimeToolbox.getLabel(eqUser, field, displayAttribute).replaceFirst(
						EqDataType.CHARACTER_RETURN, " ");

		// Field adaptor
		FieldAdaptor fieldAdaptor = functionAdaptor.getFieldAdaptor(homeEquationStandardSession, field.getId(),
						GAZRecordDataModel.TYP_FIELD);

		// Initial validation
		int msgSev = initialSyntaxValidation(scrnNo, fieldSet, field, fieldData, fieldAdaptor, fieldLabel);

		if (validateMultiplePVHelper != null)
		{
			return msgSev;
		}

		// Field value
		String inputValue = Toolbox.trimr(fieldData.getInputValue());

		// If no errors yet, then perform P/V module processing
		if (msgSev < FunctionMessages.MSG_ERROR)
		{
			msgSev = processPVModules(scrnNo, field, fieldData, fieldAdaptor, inputValue, fieldLabel);
		}

		// Process the validate EL script / java code
		FunctionRuntimeToolbox.processInputFieldValidateAssignments(field, functionData, functionAdaptor,
						homeEquationStandardSession, fhd);

		// If no errors yet, then perform Service composer validation
		if (msgSev < FunctionMessages.MSG_ERROR)
		{
			msgSev = serviceComposerValidate(scrnNo, field, fieldData, fieldAdaptor, fieldLabel);
		}

		// For numeric fields, ensure it defaults to 0 if blank
		if (msgSev < FunctionMessages.MSG_ERROR && EqDataType.isBasedNumeric(field.getDataType())
						&& fieldData.getDbValue().trim().length() == 0)
		{
			fieldData.setDbValue(EqDataType.ZEROES);
		}

		// return message severity
		return msgSev;
	}

	/**
	 * Initial syntax validation
	 * 
	 * @param scrnNo
	 *            - screen number
	 * @param fieldSet
	 *            - the display input field set
	 * @param field
	 *            - the input field
	 * @param fieldData
	 *            - the field data
	 * @param fieldAdaptor
	 *            - the field adaptor
	 * @param fieldLabel
	 *            - the field label
	 * 
	 * @return the message severity
	 * 
	 * @throws EQException
	 */
	private int initialSyntaxValidation(int scrnNo, InputFieldSet fieldSet, InputField field, FieldData fieldData,
					FieldAdaptor fieldAdaptor, String fieldLabel) throws EQException
	{
		// message severity
		int msgSev = FunctionMessages.MSG_NONE;

		// Field value
		String inputValue = Toolbox.trimr(fieldData.getInputValue());
		String fieldName = field.getId();

		// Clear error on the field
		if (!revalidation)
		{
			fieldData.clearMessages();
		}

		// Field is blank and default value is set
		if (inputValue.equals("")
						&& (field.getDefaultValue().length() > 0 || field.getDefaultValueType().equals(
										WorkField.VALUE_CONSTANT_CODE)))
		{
			inputValue = fieldAdaptor.defaultValue(field);

			if (field.getDefaultValueAs().equals(InputField.VALUE_DATABASE))
			{
				functionData.chgFieldDbValue(fieldName, inputValue);
			}
			else
			{
				functionData.chgFieldInputValue(fieldName, inputValue);
			}
		}

		// Field is still blank, then perform screen set defaulting routine
		if (inputValue.equals(""))
		{
			inputValue = screenSet.fieldDefaultValue(fieldSet, field);
			functionData.chgFieldDbValue(fieldName, inputValue);
		}

		// Field is mandatory
		boolean mandatory = fieldAdaptor.isMandatory(field);
		mandatory = screenSet.fieldMandatory(fieldSet, field, mandatory);
		if (mandatory && inputValue.equals(""))
		{
			String str = "";
			if (InputField.MANDATORY_SCRIPT.equals(field.getMandatory()))
			{
				str = LanguageResources.getFormattedString("FunctionValidate.ReturnedMessageFromUserExitFieldMandatory", field
								.getId());
			}
			else
			{
				str = LanguageResources.getFormattedString("FunctionValidate.ReturnedMessageFromUserExitFieldServComp", field
								.getId());
			}
			FunctionMessage fm = functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet
							.getId(), scrnNo, fieldName, rowId, fieldData, "KSM2003" + fieldLabel, "", rowIdString + str,
							FunctionMessages.MSG_NONE);
			if (fm != null)
			{
				msgSev = fm.rtvMsgSev();
			}
		}

		// return message severity
		return msgSev;
	}

	/**
	 * Determine whether it needs to validate the field or not
	 * 
	 * @param fieldData
	 *            - the field data
	 * 
	 * @return true if the field does not need to be validated
	 */
	private boolean ignoreValidationField(FieldData fieldData)
	{
		// for repeating fields
		if (fieldData instanceof RepeatingFieldData)
		{
			return (validateAllRepeatingFields != VAL_FIELD_YES);
		}

		// for fixed fields
		else
		{
			return (validateAllFixedFields != VAL_FIELD_YES);
		}
	}

	/**
	 * Performs syntax validation on the field
	 * 
	 * @param scrnNo
	 *            - screen number
	 * @param fieldSet
	 *            - the display input field set
	 * @param field
	 *            - the field to validate
	 * 
	 * @return the message severity for this field checking
	 * 
	 * @throws EQException
	 */
	private int applicationValidate(int scrnNo, InputFieldSet fieldSet, InputField field) throws EQException
	{
		// Field details
		String fieldName = field.getId();
		FieldData fieldData = functionData.rtvFieldData(fieldName);
		FieldAdaptor fieldAdaptor = functionAdaptor.getFieldAdaptor(homeEquationStandardSession, field.getId(),
						GAZRecordDataModel.TYP_FIELD);

		DisplayAttributesSet displayAttributesSet = screenSet.getLayout().getDisplayAttributesSets().get(scrnNo);
		DisplayAttributes displayAttribute = FunctionRuntimeToolbox.getDisplayAttributes(displayAttributesSet.getDisplayItems(),
						field.getId());

		// Retrieve the field label
		String fieldLabel = FunctionRuntimeToolbox.getLabel(eqUser, field, displayAttribute).replaceFirst(
						EqDataType.CHARACTER_RETURN, " ");

		// Field valid?
		if (!fieldAdaptor.isValid(field))
		{
			functionMsgManager
							.generateMessages(
											homeEquationStandardSession,
											functionMessages,
											screenSet.getId(),
											scrnNo,
											field.getId(),
											rowId,
											fieldData,
											fieldAdaptor.getFieldAdaptorImpl().getReturnMessages().getReturnMessages(),
											"",
											rowIdString
															+ LanguageResources
																			.getFormattedString(
																							"FunctionValidate.ReturnedMessageFromUserExitFieldValid",
																							field.getId()), functionData,
											FunctionMessages.MSG_NONE);
		}

		// validation expression
		if (field.getValidationExpressions().size() > 0)
		{
			for (ValidationExpression validationExpression : field.getValidationExpressions())
			{
				Boolean isValid = (Boolean) ELRuntime.runBooleanScript(validationExpression.getExpression(), functionData, field
								.getId(), "isValid", false, ELRuntime.REALTYPE_VALUE);

				// error running the expression?
				if (isValid == null)
				{
					functionMsgManager
									.generateMessage(
													homeEquationStandardSession,
													functionMessages,
													screenSet.getId(),
													scrnNo,
													fieldName,
													rowId,
													fieldData,
													Toolbox.getDSEPMS("KSM7373", fieldLabel, "", validationExpression
																	.getExpression()),
													"",
													rowIdString
																	+ LanguageResources
																					.getFormattedString(
																									"FunctionValidate.ReturnedMessageFromUserExitExpressionValidation",
																									fieldName + " " + fieldLabel),
													FunctionMessages.MSG_NONE);

					isValid = false;
				}

				// Interpret true as the error condition being met, so show the error.
				if (isValid.booleanValue())
				{
					String messageId = null;
					if (validationExpression.getErrorMessageId().length() > 0)
					{
						messageId = Toolbox.pad(validationExpression.getErrorMessageId(), 7);
						messageId += Toolbox.pad(FunctionRuntimeToolbox.processSubstitutionParmExpression(validationExpression
										.getSubstitutionParmOne(), fieldName, functionData), 10);
						messageId += Toolbox.pad(FunctionRuntimeToolbox.processSubstitutionParmExpression(validationExpression
										.getSubstitutionParmTwo(), fieldName, functionData), 10);
						messageId += Toolbox.pad(FunctionRuntimeToolbox.processSubstitutionParmExpression(validationExpression
										.getSubstitutionParmThree(), fieldName, functionData), 10);
					}
					else
					{
						messageId = FunctionRuntimeToolbox.getErrorMsgId();
						if (validationExpression.getErrorText().length() > 0)
						{
							messageId += Toolbox.pad(FunctionRuntimeToolbox.processSubstitutionParmExpression(validationExpression
											.getSubstitutionParmOne(), fieldName, functionData), 10);
							messageId += Toolbox.pad(FunctionRuntimeToolbox.processSubstitutionParmExpression(validationExpression
											.getSubstitutionParmTwo(), fieldName, functionData), 10);
							messageId += Toolbox.pad(FunctionRuntimeToolbox.processSubstitutionParmExpression(validationExpression
											.getSubstitutionParmThree(), fieldName, functionData), 10);
							EQMessage workMsg = new EQMessage(FunctionRuntimeToolbox.getErrorMsgId(), "00", validationExpression
											.getErrorText(), messageId);
							messageId = FunctionRuntimeToolbox.getErrorMsgId() + workMsg.getUnFormattedText();
						}
						else
						{
							// Default behaviour is to append the EL expression to the message
							messageId += validationExpression.getExpression();
						}
					}

					functionMsgManager
									.generateMessage(
													homeEquationStandardSession,
													functionMessages,
													screenSet.getId(),
													scrnNo,
													fieldName,
													rowId,
													fieldData,
													messageId,
													"",
													rowIdString
																	+ LanguageResources
																					.getFormattedString(
																									"FunctionValidate.ReturnedMessageFromUserExitExpressionValidation",
																									fieldName + " " + fieldLabel),
													FunctionMessages.MSG_NONE);
				}
			}
		}

		return fieldData.getMsgSev();
	}

	/**
	 * Perform validation defined in the service composer
	 * 
	 * @param scrnNo
	 *            - screen number
	 * @param field
	 *            - field
	 * @param fieldData
	 *            - field data
	 * @param fieldAdaptor
	 *            - field adaptor
	 * @param fieldLabel
	 *            - field label
	 * 
	 * @return the message severity of the field
	 * 
	 * @throws EQException
	 */
	private int serviceComposerValidate(int scrnNo, InputField field, FieldData fieldData, FieldAdaptor fieldAdaptor,
					String fieldLabel) throws EQException
	{
		// Field name
		String fieldName = field.getId();

		// database value (in String)
		String dbValue = fieldData.getDbValue();
		double dbValueNum = 0;

		// database value (in number)
		boolean validNumber = true;
		if (field.getDataType().equals(EqDataType.TYPE_CHAR))
		{
			dbValueNum = dbValue.length();
		}
		else if (field.getDataType().equals(EqDataType.TYPE_PACKED) || field.getDataType().equals(EqDataType.TYPE_ZONED))
		{
			try
			{
				if (dbValue.length() > 0)
				{
					// is negative sign at the end, then put it to the front
					if (dbValue.charAt(dbValue.length() - 1) == EqDataType.MINUS_SIGNC)
					{
						dbValue = EqDataType.MINUS_SIGN + dbValue.subSequence(0, dbValue.length() - 1);
					}

					dbValueNum = Double.parseDouble(dbValue); // ensure valid number
				}
			}
			catch (NumberFormatException e)
			{
				// invalid number
				validNumber = false;
				functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo,
								fieldName, rowId, fieldData, "KSM2152", "", rowIdString
												+ LanguageResources.getFormattedString(
																"FunctionValidate.ReturnedMessageFromUserExitFieldServComp", field
																				.getId()), FunctionMessages.MSG_NONE);
			}
		}
		else if (field.getDataType().equals(EqDataType.TYPE_DATE))
		{
			try
			{
				// ensure valid number
				if (dbValue.trim().length() > 0)
				{
					dbValueNum = Integer.parseInt(dbValue);

					// validate that it is a valid date format
					if (!EqDataType.isValidDate(dbValue))
					{
						throw new NumberFormatException();
					}
				}
			}
			catch (NumberFormatException e)
			{
				// invalid number
				validNumber = false;
				functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo,
								fieldName, rowId, fieldData, "KSM2151", "", rowIdString
												+ LanguageResources.getFormattedString(
																"FunctionValidate.ReturnedMessageFromUserExitFieldServComp", field
																				.getId()), FunctionMessages.MSG_NONE);
			}
		}

		// minimum length or value
		if (validNumber && field.getMinLength().trim().length() > 0)
		{
			double minLength = Toolbox.parseDouble(field.getMinLength(), 0);
			if (field.getDataType().equals(EqDataType.TYPE_CHAR) || field.getDataType().equals(EqDataType.TYPE_PACKED)
							|| field.getDataType().equals(EqDataType.TYPE_ZONED))
			{
				if (dbValueNum < minLength)
				{
					functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo,
									fieldName, rowId, fieldData, Toolbox.getDSEPMS("KSM7349", fieldLabel, "", String
													.valueOf(minLength)), "", rowIdString
													+ LanguageResources.getFormattedString(
																	"FunctionValidate.ReturnedMessageFromUserExitFieldServComp",
																	fieldName + " " + fieldLabel), FunctionMessages.MSG_NONE);
				}
			}
		}

		// maximum length or value
		if (validNumber && field.getMaxLength().trim().length() > 0)
		{
			double maxLength = Toolbox.parseDouble(field.getMaxLength(), 0);
			if (field.getDataType().equals(EqDataType.TYPE_CHAR) || field.getDataType().equals(EqDataType.TYPE_PACKED)
							|| field.getDataType().equals(EqDataType.TYPE_ZONED))
			{
				if (dbValueNum > maxLength)
				{
					functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo,
									fieldName, rowId, fieldData, Toolbox.getDSEPMS("KSM7350", fieldLabel, "", String
													.valueOf(maxLength)), "", rowIdString
													+ LanguageResources.getFormattedString(
																	"FunctionValidate.ReturnedMessageFromUserExitFieldServComp",
																	fieldName + " " + fieldLabel), FunctionMessages.MSG_NONE);
				}
			}
		}

		// compare the length in actual bytes (e.g. DBCS takes 2 bytes)
		if (field.getDataType().equals(EqDataType.TYPE_CHAR) || field.getDataType().equals(EqDataType.TYPE_BOOLEAN))
		{
			if (!Toolbox.isValidAS400TextLength(dbValue, fieldData.getFieldLength(), homeEquationStandardSession.getCcsid()))
			{
				functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo,
								fieldName, rowId, fieldData, Toolbox.getDSEPMS("KSM7353", fieldLabel, "", String.valueOf(fieldData
												.getFieldLength())), "", rowIdString
												+ LanguageResources.getFormattedString(
																"FunctionValidate.ReturnedMessageFromUserExitFieldServComp",
																fieldName + " " + fieldLabel), FunctionMessages.MSG_NONE);
			}
			if (!Toolbox.isValidAS400Text(dbValue, fieldData.getFieldLength(), homeEquationStandardSession.getCcsid()))
			{
				functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo,
								fieldName, rowId, fieldData, Toolbox.getDSEPMS("KSM7386", fieldLabel, "", ""), "", rowIdString
												+ LanguageResources.getFormattedString(
																"FunctionValidate.ReturnedMessageFromUserExitFieldServComp",
																fieldName + " " + fieldLabel), FunctionMessages.MSG_NONE);
			}
		}
		else if (validNumber && field.getDataType().equals(EqDataType.TYPE_PACKED))
		{
			if (!Toolbox.isValidAS400PackedLength(dbValueNum, fieldData.getFieldLength(), fieldData.getFieldDecimal()))
			{
				functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo,
								fieldName, rowId, fieldData, Toolbox.getDSEPMS("KSM7353", fieldLabel, "", ""), "", rowIdString
												+ LanguageResources.getFormattedString(
																"FunctionValidate.ReturnedMessageFromUserExitFieldServComp",
																fieldName + " " + fieldLabel), FunctionMessages.MSG_NONE);
			}
		}
		else if (validNumber && field.getDataType().equals(EqDataType.TYPE_ZONED)
						|| field.getDataType().equals(EqDataType.TYPE_DATE))
		{
			if (!Toolbox.isValidAS400ZonedLength(dbValueNum, fieldData.getFieldLength(), fieldData.getFieldDecimal()))
			{
				functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo,
								fieldName, rowId, fieldData, Toolbox.getDSEPMS("KSM7353", fieldLabel, "", ""), "", rowIdString
												+ LanguageResources.getFormattedString(
																"FunctionValidate.ReturnedMessageFromUserExitFieldServComp",
																fieldName + " " + fieldLabel), FunctionMessages.MSG_NONE);
			}
		}

		// regular expression
		String regExp = field.rtvRegExp(eqUser).trim();
		if (regExp.length() > 0)
		{
			if (!dbValue.matches(regExp))
			{
				String regExpMsg = "";
				int regMsgSev = field.getRegExpSev();
				if (field.getRegExpMsg().length() == 0)
				{
					regExpMsg = Toolbox.getDSEPMS("KSM7351", fieldLabel, "", regExp);
				}
				else if (regMsgSev == FunctionMessages.MSG_NONE)
				{
					regExpMsg = field.getRegExpMsg();
				}
				else
				{
					regExpMsg = FunctionRuntimeToolbox.getDSEPMS(regMsgSev, field.getRegExpMsg());
				}

				if (regExpMsg.length() > 0)
				{
					functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo,
									fieldName, rowId, fieldData, regExpMsg, "", rowIdString
													+ LanguageResources.getFormattedString(
																	"FunctionValidate.ReturnedMessageFromUserExitFieldServComp",
																	fieldName + " " + fieldLabel), FunctionMessages.MSG_NONE);
				}
			}
		}

		// valid values
		if (dbValue.trim().length() > 0)
		{
			String validValueStr = field.rtvValidValues(eqUser);
			if (validValueStr.length() > 0)
			{
				String[] list = validValueStr.split(Toolbox.CONTEXT_DELIMETER);
				boolean valid = false;
				for (String element : list)
				{
					if (field.getDataType().equals(EqDataType.TYPE_CHAR))
					{
						if (dbValue.equals(Toolbox.trimr(element)))
						{
							valid = true;
							break;
						}
					}
					else if (field.getDataType().equals(EqDataType.TYPE_PACKED)
									|| field.getDataType().equals(EqDataType.TYPE_ZONED))
					{
						double value = Toolbox.parseDouble(element, Double.MIN_VALUE);
						if (value != Double.MIN_VALUE && value == dbValueNum)
						{
							valid = true;
							break;
						}
					}

				}
				if (!valid)
				{
					functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo,
									fieldName, rowId, fieldData, Toolbox.getDSEPMS("KSM7352", fieldLabel, "", validValueStr), "",
									rowIdString
													+ LanguageResources.getFormattedString(
																	"FunctionValidate.ReturnedMessageFromUserExitFieldServComp",
																	fieldName + " " + fieldLabel), FunctionMessages.MSG_NONE);
				}
			}
		}

		// user exit
		if (defaultValues)
		{
			fieldAdaptor.setOutputValue(field);
			fieldAdaptor.setInputValue(field);
			fieldAdaptor.setPrimitiveValue(field);
		}

		// editing code
		if (fieldData.getEditCode().length() > 0 && fieldData.getMsgSev() < FunctionMessages.MSG_ERROR)
		{
			List<ReplacementToken> replacementTokens = null;
			if (fieldData.getEditCode().equals(EqDataType.EDIT_AMOUNT_WITH_REPLACE))
			{
				DisplayAttributes displayAttributes = screenSet.getLayout().getDisplayAttributesSets().get(scrnNo)
								.rtvDisplayAttribute(fieldName);
				replacementTokens = displayAttributes.getEditCodeParameterReplacements();
			}

			String outputValue = FunctionRuntimeToolbox.editEquationData(eqUser, functionData, fieldData.getDbValue(), fieldData
							.getFieldName(), fieldData.getFieldType(), fieldData.getFieldLength(), fieldData.getFieldDecimal(),
							fieldData.getEditCode(), fieldData.getEditCodeParameter(), layoutAdaptor.getAttributesAdaptor(
											homeEquationStandardSession, fieldName), replacementTokens);
			fieldData.setOutputValue(outputValue);
		}

		// load mode
		if (loadMode && !field.isKey())
		{
			processDuringLoadMode(scrnNo, field, fieldData, fieldAdaptor);
		}

		// return message severity
		return fieldData.getMsgSev();
	}

	/**
	 * Perform processing for the P/V module
	 * 
	 * @param scrnNO
	 *            - the current screen number
	 * @param field
	 *            - field to validate
	 * @param fieldData
	 *            - field data of the field
	 * @param inputValue
	 *            - input value of the field
	 * @param fieldLabel
	 *            - field label
	 * 
	 * @return the message severity of the field
	 * 
	 * @throws EQException
	 */
	private int processPVModules(int scrnNo, InputField field, FieldData fieldData, FieldAdaptor fieldAdaptor, String inputValue,
					String fieldLabel) throws EQException
	{
		EquationStandardSession session = homeEquationStandardSession;
		boolean pvExecuted = false;

		// loop through all the PV field sets
		for (PVFieldSet pVFieldSet : field.getPvFieldSets())
		{
			FieldSetAdaptor fieldSetAdaptor = functionAdaptor.getFieldSetAdaptor(session, pVFieldSet);
			if (fieldSetAdaptor.shouldExecute(pVFieldSet))
			{
				String currentUnit = functionData.rtvFieldInputValue("CURUNT");
				String currentSystem = functionData.rtvFieldInputValue("CURSYS");
				// Set the current unit for global processing
				boolean setSessionResult = setCurrentSession();

				if (!setSessionResult)
				{
					functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(),
									screenSet.scrnNo, "", rowId, fieldData, "KSM8011" + currentUnit + "/" + currentSystem, "",
									rowIdString, FunctionMessages.MSG_NONE);
					break;
				}

				// PV executed
				pvExecuted = true;

				// PV module
				if (isProperPVFromUTW19R(pVFieldSet))
				{
					executePVModule(scrnNo, field, fieldData, pVFieldSet, fieldLabel);
				}

				// API disguised as PV module
				else
				{
					executeAPIModule(scrnNo, field, fieldData, pVFieldSet, fieldLabel);
				}
			}
		}

		// no p/v module executed
		if (!pvExecuted)
		{
			fieldData.setDbValue(functionData.cvtInputIntoDb(fieldData, inputValue));
		}

		return fieldData.getMsgSev();
	}

	/**
	 * Execute a PV module
	 * 
	 * @param scrnNo
	 *            - the current screen number
	 * @param field
	 *            - field to validate
	 * @param fieldData
	 *            - field data of the field
	 * @param pvFieldSet
	 *            - the PV field set
	 * @param fieldLabel
	 *            - field label
	 * 
	 * @throws EQException
	 */
	private void executePVModule(int scrnNo, InputField field, FieldData fieldData, PVFieldSet pvFieldSet, String fieldLabel)
					throws EQException
	{
		// setup the PV name
		String pvModule = Toolbox.getFirstWord(pvFieldSet.getId());

		// setup dsccn
		inputMappingExist = false;
		EquationPVData equationPVData = setupDSCCN(currentEquationStandardSession, pvModule, field, pvFieldSet);

		// Only proceed if there are any fields to process
		if (equationPVData.getPvMetaData().rtvNumberOfFields() <= 0)
		{
			return;
		}

		// validate the field
		EquationStandardValidation equationStandardValidation = new EquationStandardValidation(pvFieldSet.getDecode(), pvModule,
						equationPVData, Toolbox.cvtBooleanToYN(pvFieldSet.isBlankAllowed()), pvFieldSet.getNewField());
		equationStandardValidation.setSecurity(pvFieldSet.getSecurity());

		// is the PV cache?
		EquationAPICache equationPVResult = null;
		EquationAPICacheHandler equationPVResultHandler = currentEquationStandardSession.getUnit().getApiCacheHandler();
		if (pvFieldSet.isCache())
		{
			equationPVResult = equationPVResultHandler.getFromCache(eqUser.getLanguageId(), equationStandardValidation);
		}

		// execute the PV
		if (equationPVResult == null)
		{
			currentEquationStandardSession.executeValidate(equationStandardValidation);

			if (pvFieldSet.isCache())
			{
				equationPVResultHandler.addAPICache(eqUser.getLanguageId(), equationStandardValidation);
			}
		}

		// process
		processEquationStandardValidation(field, fieldData, pvFieldSet, fieldLabel, scrnNo, equationStandardValidation);
	}

	/**
	 * Process the result of Equation standard validation
	 * 
	 * @param session
	 *            - the Equation Standard Session
	 * @param field
	 *            - field to validate
	 * @param fieldData
	 *            - field data of the field
	 * @param pvFieldSet
	 *            - the PV field set
	 * @param fieldLabel
	 *            - field label
	 * @param scrnNo
	 *            - the current screen number
	 * @param equationStandardValidation
	 *            - the Equation Standard Validation
	 * 
	 * @return the message severity
	 * 
	 * @throws EQException
	 */
	private int processEquationStandardValidation(InputField field, FieldData fieldData, PVFieldSet pvFieldSet, String fieldLabel,
					int scrnNo, EquationStandardValidation equationStandardValidation) throws EQException
	{
		int msev = FunctionMessages.MSG_NONE;
		RelatedFields relFields = null;
		if (pvFieldSet != null)
		{
			relFields = new RelatedFields(field.getId(), pvFieldSet.getRelatedFields());
		}
		else
		{
			relFields = new RelatedFields(field.getId(), "");
		}
		// setup the PV name
		String pvModule = Toolbox.getFirstWord(pvFieldSet.getId());

		// pop it out if interested
		if (LOG.isDebugEnabled())
		{
			LOG.debug(field.getId() + "PVDATA: " + equationStandardValidation.getDataOutput());
		}

		// any message returned?
		if (!equationStandardValidation.getValid())
		{
			String secondLevelText = LanguageResources.getFormattedString("FunctionValidate.ReturnedMessageFromPV", new String[] {
							pvModule, field.getId() + " " + fieldLabel });
			LOG.debug(secondLevelText + " : " + equationStandardValidation.getError());
			FunctionMessage fm = functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet
							.getId(), scrnNo, relFields.getRelatedFields(), rowId, fieldData,
							equationStandardValidation.getError(), "", rowIdString + secondLevelText, pvFieldSet
											.getIgnoreMessages());
			if (fm != null)
			{
				msev = fm.rtvMsgSev();

				// is this the special UTR71R?
				if (pvModule.equals("UTR71R"))
				{
					fm.setAmount(equationStandardValidation.getEquationPVData().getFieldValue("@69CAM"));
					fm.setBranch(equationStandardValidation.getEquationPVData().getFieldValue("@69ABR"));
					fm.setDebitCredit(equationStandardValidation.getEquationPVData().getFieldValue("@69DR"));
				}
			}
		}

		// not a major severity, then retrieve all the details returned by the p/v
		if (msev < FunctionMessages.MSG_ERROR)
		{
			processDSCCN(field, equationStandardValidation.getEquationPVData(), pvFieldSet.getDecode());
		}

		// return message severity
		return msev;
	}

	/**
	 * Execute an API module
	 * 
	 * @param scrnNO
	 *            - the current screen number
	 * @param inputField
	 *            - field to validate
	 * @param fieldData
	 *            - field data of the field
	 * @param origPVFieldSet
	 *            - the PV field set
	 * @param fieldLabel
	 *            - field label
	 * 
	 * @throws EQException
	 */
	private void executeAPIModule(int scrnNo, InputField inputField, FieldData fieldData, PVFieldSet origPVFieldSet,
					String fieldLabel) throws EQException
	{
		// save original field set
		PVFieldSet pvFieldSet = origPVFieldSet;
		RelatedFields relFields = null;
		if (pvFieldSet != null)
		{
			relFields = new RelatedFields(inputField.getId(), pvFieldSet.getRelatedFields());
		}
		else
		{
			relFields = new RelatedFields(inputField.getId(), "");
		}
		// if the transaction type is PV, then it means this is a PV created from the PV editor.
		// as of now, the only validation is whether the record exists or not exists depending on the newField flag in the PV
		// fieldset. In order to do validation, treat this PV as a database table
		if (pvFieldSet.getType().trim().length() == 0)
		{
			// if root has not been set up, then do not validate, as the root should contain the database file name
			if (pvFieldSet.getRoot().trim().length() == 0)
			{
				return;
			}

			// create a new copy of the field set in order to convert this to a database table
			pvFieldSet = new PVFieldSet(origPVFieldSet);
			pvFieldSet.setType(IEquationStandardObject.TYPE_PVEDITOR);
		}

		// generate the transaction
		FunctionTransactions functionTransaction = new FunctionTransactions(fhd, IEquationStandardObject.FCT_RTV);
		functionTransaction.setJournalHeader(new JournalHeader());
		IEquationStandardObject itransaction = functionTransaction.rtvTransaction(currentEquationStandardSession, FunctionToolbox
						.cvtPVToAPIFieldSet(pvFieldSet));

		// assume blank
		boolean blank = true;

		// loop through the mappings
		String path = MappingToolbox.getFullPVFieldSetPath(inputField.rtvPath(), pvFieldSet.getId());
		for (Mapping mapping : screenSet.getFunction().getValidateMappings())
		{
			// mapping exists for the pv field?
			if (mapping.getTarget().indexOf(path) >= 0)
			{
				String mapToStr = MappingToolbox.getField(mapping.getTarget());
				String mapFromStr = MappingToolbox.getField(mapping.getSource());

				// retrieve the value
				FieldData mapFromStrData = functionData.rtvFieldData(mapFromStr);
				if (mapFromStrData != null)
				{
					String value = mapFromStrData.getInputValue();
					itransaction.setFieldValue(mapToStr, value);
					if (blank && value.trim().length() != 0)
					{
						blank = false;
					}
				}
				else
				{
					functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), 0,
									relFields.getRelatedFields(), rowId, fieldData, "KSM7369" + mapFromStr, "", rowIdString + "",
									FunctionMessages.MSG_NONE);
				}
			}
		}

		// loop through all the pv fields for the script or java user exits
		for (PVField pvField : pvFieldSet.getPVFields())
		{
			String newValue = null;
			String pvFieldName = pvField.getId();
			if (Field.ASSIGNMENT_SCRIPT.equals(pvField.getValidateAssignment()))
			{
				newValue = ELRuntime.runStringScript(pvField.getValidateScript(), functionData, pvFieldSet.getId() + " "
								+ pvField.getId(), LanguageResources.getString("Language.PVFieldAssignment"), null,
								ELRuntime.INPUT_VALUE);
			}
			else if (Field.ASSIGNMENT_CODE.equals(pvField.getValidateAssignment()))
			{
				String curValue = itransaction.getFieldValue(pvFieldName);
				ValueAdaptor valueAdaptor = functionAdaptor.getValueAdaptor(homeEquationStandardSession, inputField.getId(),
								AdaptorToolbox.getPVNameField(pvFieldSet.getId(), pvFieldName), GAZRecordDataModel.TYP_VALUE);
				newValue = valueAdaptor.getValue(curValue);
			}

			// check the value
			if (newValue != null)
			{
				if (newValue.trim().length() > 0)
				{
					itransaction.setFieldValue(pvFieldName, newValue);
					if (blank && newValue.trim().length() != 0)
					{
						blank = false;
					}
				}
			}
		}

		// blank allowed?
		if (!pvFieldSet.isBlankAllowed() && blank)
		{
			DisplayAttributesSet displayAttributesSet = screenSet.getLayout().getDisplayAttributesSets().get(scrnNo);
			DisplayAttributes displayAttribute = FunctionRuntimeToolbox.getDisplayAttributes(
							displayAttributesSet.getDisplayItems(), inputField.getId());
			String apiFieldLabel = FunctionRuntimeToolbox.getLabel(eqUser, inputField, displayAttribute).replaceAll(
							EqDataType.CHARACTER_RETURN, " ");
			functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), screenSet
							.getScrnNo(), relFields.getRelatedFields(), rowId, fieldData, "KSM2003" + apiFieldLabel, "",
							rowIdString
											+ LanguageResources.getFormattedString("FunctionValidate.ReturnedMessageFromPV",
															new String[] { itransaction.getId(),
																			inputField.getId() + " " + fieldLabel }),
							FunctionMessages.MSG_NONE);
			return;
		}

		// run it
		FunctionEquationStandardObjectHelper.applyTransaction(currentEquationStandardSession, itransaction, LOG, 0);

		// search for predefined KSM for key not found
		EQMessage eqMessage = FunctionRuntimeToolbox.getKeyNotFoundMsg(itransaction.getMessages());

		// PV
		if (pvFieldSet.getType().equals(IEquationStandardObject.TYPE_PVEDITOR))
		{
			if (!itransaction.getValid())
			{
				functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), screenSet
								.getScrnNo(), relFields.getRelatedFields(), rowId, fieldData, itransaction.getMessages().get(0)
								.getDsepms(), "", rowIdString
								+ LanguageResources.getFormattedString("FunctionValidate.ReturnedMessageFromPV", new String[] {
												itransaction.getId(), inputField.getId() + " " + fieldLabel }),
								FunctionMessages.MSG_NONE);
				return;
			}
		}

		// key not existing, but should be existing
		else if (eqMessage != null)
		{
			if (pvFieldSet.getNewField().equals(EqDataType.NO))
			{
				functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), screenSet
								.getScrnNo(), relFields.getRelatedFields(), rowId, fieldData, eqMessage.getDsepms(), "",
								rowIdString
												+ LanguageResources.getFormattedString("FunctionValidate.ReturnedMessageFromPV",
																new String[] { itransaction.getId(),
																				inputField.getId() + " " + fieldLabel }),
								FunctionMessages.MSG_NONE);
				return;
			}
		}

		// key exist, but should not be existing
		else if (pvFieldSet.getNewField().equals(EqDataType.YES))
		{
			String dsepms = "KSM7383";
			functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), screenSet.getId(),
							relFields.getRelatedFields(), rowId, fieldData, dsepms, "", rowIdString
											+ LanguageResources.getFormattedString("FunctionValidate.ReturnedMessageFromPV",
															new String[] { itransaction.getId(),
																			inputField.getId() + " " + fieldLabel }),
							FunctionMessages.MSG_NONE);
			return;
		}

		// process the error messages
		List<EQMessage> eqMessages = itransaction.getMessages();
		for (int j = 0; j < eqMessages.size(); j++)
		{
			if (!FunctionRuntimeToolbox.isIgnoreKsmAtLoad(eqMessages.get(j).getMessageID()))
			{
				functionMsgManager.generateMessage(functionMessages, screenSet.getId(), scrnNo, relFields.getRelatedFields(),
								rowId, fieldData, eqMessages.get(j), "", rowIdString
												+ LanguageResources.getFormattedString("FunctionValidate.ReturnedMessageFromPV",
																new String[] { itransaction.getId(),
																				inputField.getId() + " " + fieldLabel }),
								pvFieldSet.getIgnoreMessages());
			}
		}

		// load it back to the function data via mappings
		if (functionMessages.getMsgSev() < FunctionMessages.MSG_ERROR)
		{
			path = MappingToolbox.getFullPVFieldSetPath(inputField.rtvPath(), pvFieldSet.getId());
			for (Mapping mapping : screenSet.getFunction().getValidateMappings())
			{
				if (mapping.getSource().indexOf(path) >= 0)
				{
					String mapFromStr = MappingToolbox.getField(mapping.getSource());
					String mapToStr = MappingToolbox.getField(mapping.getTarget());
					String value = itransaction.getFieldValue(mapFromStr);

					FieldData mapToStrData = functionData.rtvFieldData(mapToStr);
					if (mapToStrData == null)
					{
						functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), 0,
										relFields.getRelatedFields(), rowId, null, "KSM7369" + mapFromStr, "", rowIdString + "",
										FunctionMessages.MSG_NONE);
					}

					else
					{
						// mapped to a primitive value?
						if (MappingToolbox.isMappedToPrimitive(mapping.getTarget()))
						{
							// for numeric, make sure to have some transformation to convert the decimal/integer separator
							if (EqDataType.isNumeric(mapToStrData.getFieldType()))
							{
								String engValue = functionData.cvtInputIntoDb(mapToStrData, value);
								mapToStrData.setDbValue(engValue);
							}
							else
							{
								mapToStrData.setDbValue(value);
							}

							// set also the input type if this belongs to another field
							if (!inputField.getId().equals(mapToStr))
							{
								mapToStrData.setInputValue(value);
							}
						}
						else if (MappingToolbox.isMappedToInput(mapping.getTarget()))
						{
							mapToStrData.setInputValue(value);
						}
						else if (MappingToolbox.isMappedToOutput(mapping.getTarget()))
						{
							mapToStrData.setOutputValue(value);
						}
						else if (MappingToolbox.isMappedToWorkField(mapping.getTarget()))
						{
							mapToStrData.setDbValue(value);
							mapToStrData.setInputValue(value);
						}
					}
				}
			}
		}
	}

	/**
	 * Setup the DSCCN to pass to the PV module
	 * 
	 * @param session
	 *            - the Equation Standard Session
	 * @param pvName
	 *            - the PV name
	 * @param field
	 *            - the InputField being validated
	 * @param pvFieldSet
	 *            The <code>PVFieldSet</code> that represents the PV module to be processed
	 * 
	 * 
	 * @return the Equation PV Data
	 */
	private EquationPVData setupDSCCN(EquationStandardSession session, String pvName, InputField field, PVFieldSet pvFieldSet)
					throws EQException
	{
		// list of locked fields
		ArrayList<String> lockedFieldList = new ArrayList<String>();

		// construct the initial data
		EquationPVData equationPVData = new EquationPVData(session.getUnit().getPVMetaData(pvName), session.getCcsid());

		// retrieve the meta data
		EquationPVMetaData pvMetaData = equationPVData.getPvMetaData();

		// Only proceed if we have any fields to process
		if (pvMetaData.rtvNumberOfFields() > 0)
		{
			// retrieve the mappings
			Function function = screenSet.getFunction();

			// non-locked and non-blank value?
			boolean blank = true;
			int mapIntoFld = 0;

			// loop through the mappings
			String path = MappingToolbox.getFullPVFieldSetPath(field.rtvPath(), pvMetaData.getId());
			for (Mapping mapping : function.getValidateMappings())
			{
				// mapping exists for the pv field?
				if (mapping.getTarget().indexOf(path) >= 0)
				{
					inputMappingExist = true;
					mapIntoFld++;
					String mapToStr = MappingToolbox.getField(mapping.getTarget());
					String mapFromStr = MappingToolbox.getField(mapping.getSource());

					// does the PV field exist?
					EquationPVFieldMetaData fmd = pvMetaData.rtvFieldMetaData(mapToStr);
					if (fmd == null)
					{
						functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), 0,
										field.getId(), rowId, null, "KSM7368" + mapToStr, "", rowIdString + "",
										FunctionMessages.MSG_NONE);
					}

					else
					{
						// retrieve the value
						FieldData fieldData = functionData.rtvFieldData(mapFromStr);
						if (fieldData != null)
						{
							String value = fieldData.getInputValue();

							// If field is flagged as right-to-left, reverse the data so it is in display format as the data is
							// passed as a string to UTW06R stored procedure which will reverse the data automatically
							// Contextual RTL conversion (BidiStringType.ST11) seems to give the best result where there is mixed
							// Arabic and Latin characters
							if (fieldData.chkRTL())
							{
								value = Toolbox.convertTextRTLForDisplay(value, fieldData.getFieldLength(), session.getCcsid(),
												BidiStringType.ST11);
							}

							value = validatePVSpecific.specialInputPVField(field, pvMetaData.getId(), fmd.getId(), value, value,
											fmd.getLength(), fmd.getDecimal());
							equationPVData.setFieldValue(mapToStr, value);

							// data to be validated. Locked fields are considered blank as these are defaulted by the system
							if (blank && !fieldData.isLocked() && value.trim().length() > 0)
							{
								blank = false;
							}

							// list of locked pv fields
							if (fieldData.isLocked())
							{
								lockedFieldList.add(mapToStr);
							}
						}
						else
						{
							functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), 0,
											field.getId(), rowId, null, "KSM7369" + mapFromStr, "", rowIdString + "",
											FunctionMessages.MSG_NONE);
						}
					}
				}
			}

			// no mapping defined, then use the default mapping
			if (!inputMappingExist)
			{
				EquationPVDecodeMetaData decodeMetaData = pvMetaData.getDecodeMetaData(pvFieldSet.getDecode());
				if (decodeMetaData != null)
				{
					List<String> defaultInputValue = decodeMetaData.getPvFields();
					String fieldValue = functionData.rtvFieldInputValue(field.getId());
					for (String pvField : defaultInputValue)
					{
						String fieldValue2 = pvMetaData.rtvDataFromField(pvField, fieldValue);
						if (fieldValue2 != null)
						{
							EquationPVFieldMetaData fmd = pvMetaData.rtvFieldMetaData(pvField);
							fieldValue2 = validatePVSpecific.specialInputPVField(field, pvMetaData.getId(), pvField, fieldValue,
											fieldValue2, fmd.getLength(), fmd.getDecimal());
							equationPVData.setFieldValue(pvField, fieldValue2);
						}
					}
				}
				else
				{
					functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), 0, field
									.getId(), rowId, null,
									"KSM7372" + Toolbox.pad(pvFieldSet.getDecode(), 10) + pvMetaData.getId(), "", rowIdString + "",
									FunctionMessages.MSG_NONE);
				}
			}

			// loop through all the pv fields for the script or java user exits
			for (PVField pvField : pvFieldSet.getPVFields())
			{
				String newValue = null;
				String pvFieldName = pvField.getId();
				if (Field.ASSIGNMENT_SCRIPT.equals(pvField.getValidateAssignment()))
				{
					newValue = ELRuntime.runStringScript(pvField.getValidateScript(), functionData, pvFieldSet.getId() + " "
									+ pvField.getId(), LanguageResources.getString("Language.PVFieldAssignment"), null,
									ELRuntime.INPUT_VALUE);
				}
				else if (Field.ASSIGNMENT_CODE.equals(pvField.getValidateAssignment()))
				{
					String curValue = equationPVData.getFieldValue(pvFieldName);
					ValueAdaptor valueAdaptor = functionAdaptor.getValueAdaptor(homeEquationStandardSession, field.getId(),
									AdaptorToolbox.getPVNameField(pvName, pvFieldName), GAZRecordDataModel.TYP_VALUE);
					newValue = valueAdaptor.getValue(curValue);
				}

				// check the value
				if (newValue != null)
				{
					if (newValue.trim().length() > 0 && !lockedFieldList.contains(pvFieldName))
					{
						EquationPVFieldMetaData fmd = equationPVData.getPvMetaData().rtvFieldMetaData(pvFieldName);
						newValue = validatePVSpecific.specialInputPVField(field, pvMetaData.getId(), pvFieldName, newValue,
										newValue, fmd.getLength(), fmd.getDecimal());
						equationPVData.setFieldValue(pvFieldName, newValue);
						blank = false;
					}
				}
			}

			// Perform last setting up of the details
			validatePVSpecific.specialInputPVPgm(field, equationPVData);

			// Blank and more than one map into fields, then blank everything. This is support "locking" field where it is
			// automatically default by the system. Example will be the account comprising the AB AN AS. If no inter-branch
			// authority, then AB is always defaulting to the user's branch. If AN and AS have not been specified, the system
			// cannot validate it, otherwise it will issue an error, hence, blank out the AB part again.
			// BUT if there is only one data map into it (e.g. branch mnemonic field), then the system should validate
			// if (blank && mapIntoFld > 1)
			// {
			// equationPVData.parseDSCCNIntoFields("");
			// }

		}
		// return the PV data
		return equationPVData;
	}

	/**
	 * Populate the fields based on the mapping from the validate module back to the field
	 * 
	 * @param field
	 *            - the field for which the validation is being carried out
	 * @param eqData
	 *            - the DSCCN
	 * @param decode
	 *            - the decode
	 * 
	 */
	private void processDSCCN(InputField field, EquationPVData equationPVData, String decode) throws EQException
	{
		// retrieve the meta data
		EquationPVMetaData pvMetaData = equationPVData.getPvMetaData();
		String pvName = pvMetaData.getId();

		// retrieve the mappings
		Function function = screenSet.getFunction();

		// loop through the mappings
		String path = MappingToolbox.getFullPVFieldSetPath(field.rtvPath(), pvMetaData.getId());
		boolean mappingExist = false;

		for (Mapping mapping : function.getValidateMappings())
		{
			if (mapping.getSource().indexOf(path) >= 0)
			{
				String mapFromStr = MappingToolbox.getField(mapping.getSource());
				String mapToStr = MappingToolbox.getField(mapping.getTarget());
				String value = equationPVData.getFieldValue(mapFromStr);
				value = validatePVSpecific.specialOutputPVField(pvMetaData.getId(), mapFromStr, value);

				// does the PV field exist?
				EquationPVFieldMetaData fmd = pvMetaData.rtvFieldMetaData(mapFromStr);
				if (fmd == null)
				{
					functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), 0, field
									.getId(), rowId, null, "KSM7368" + mapFromStr, "", rowIdString + "", FunctionMessages.MSG_NONE);
				}

				FieldData fieldData = functionData.rtvFieldData(mapToStr);
				if (fieldData == null)
				{
					functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), 0, field
									.getId(), rowId, null, "KSM7369" + mapFromStr, "", rowIdString + "", FunctionMessages.MSG_NONE);
				}

				// PV field must exist
				if (fmd != null && fieldData != null)
				{
					// mapped to a primitive value?
					if (MappingToolbox.isMappedToPrimitive(mapping.getTarget()))
					{
						// for numeric, make sure to have some transformation to convert the decimal/integer separator
						if (EqDataType.isNumeric(fieldData.getFieldType()) && validatePVSpecific.isInputValue(pvName, fmd.getId()))
						{
							String engValue = functionData.cvtInputIntoDb(fieldData, value);
							fieldData.setDbValue(engValue);
						}
						else
						{
							fieldData.setDbValue(value);
						}

						// set also the input type if this belongs to another field
						if (!field.getId().equals(mapToStr))
						{
							fieldData.setInputValue(value);
						}
					}
					else if (MappingToolbox.isMappedToInput(mapping.getTarget()))
					{
						fieldData.setInputValue(value);
					}
					else if (MappingToolbox.isMappedToOutput(mapping.getTarget()))
					{
						fieldData.setOutputValue(value);
					}
					else if (MappingToolbox.isMappedToWorkField(mapping.getTarget()))
					{
						fieldData.setDbValue(value);
						fieldData.setInputValue(value);
					}
				}
				mappingExist = true;
			}
		}

		// no mapping defined, then use the default mapping
		if (!inputMappingExist && !mappingExist)
		{
			// Field data
			FieldData fieldData = functionData.rtvFieldData(field.getId());
			fieldData.setOutputValue(equationPVData.rtvDefaultOVal());
			String db = equationPVData.rtvDefaultDVal();
			if (db == null)
			{
				// for numeric, make sure to have some transformation to convert the decimal/integer separator
				if (EqDataType.isNumeric(fieldData.getFieldType()))
				{
					String engValue = functionData.cvtInputIntoDb(fieldData, fieldData.getInputValue());
					fieldData.setDbValue(engValue);
				}
				else
				{
					fieldData.setDbValue(fieldData.getInputValue());
				}

				String engValue = functionData.cvtInputIntoDb(fieldData, fieldData.getInputValue());
				fieldData.setDbValue(engValue);
			}
			else
			{
				fieldData.setDbValue(db);
			}
		}
	}

	/**
	 * Call default user exit
	 * 
	 * @param scrnNo
	 *            - the screen number
	 * 
	 * @throws EQException
	 */
	private void defaultUserExit(int scrnNo) throws EQException
	{
		boolean changed = false;
		InputFieldSet inputFieldSet = screenSet.getFunction().getInputFieldSets().get(scrnNo);

		// Java user exit on field set level
		try
		{
			EqTimingTest.printStartTime("FunctionValidate.defaultUserExit", "Java");
			InputFieldSetAdaptor inputFieldSetAdaptor = functionAdaptor.getInputFieldSetAdaptor(homeEquationStandardSession,
							inputFieldSet);
			changed = inputFieldSetAdaptor.defaultMode();
		}
		catch (Exception e)
		{
			LOG.error(e);
			functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo, "", rowId,
							null, "KSM7375" + inputFieldSet.getId(), "", rowIdString + Toolbox.getExceptionMessage(e),
							FunctionMessages.MSG_NONE);
		}
		finally
		{
			EqTimingTest.printTime("FunctionValidate.defaultUserExit", "Java");
		}

		// JAVA user exit
		try
		{
			EqTimingTest.printStartTime("FunctionValidate.defaultUserExit", "Java");
			boolean changed2 = functionAdaptor.defaultMode(scrnNo);
			changed = changed || changed2;
		}
		catch (Exception e)
		{
			LOG.error(e);
			functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo, "", rowId,
							null, "KSM7375" + inputFieldSet.getId(), "", rowIdString + Toolbox.getExceptionMessage(e),
							FunctionMessages.MSG_NONE);
		}
		finally
		{
			EqTimingTest.printTime("FunctionValidate.defaultUserExit", "Java");
		}

		// Bankfusion user exit
		if (EquationCommonContext.isBankFusionInstalled())
		{
			try
			{
				EqTimingTest.printTime("FunctionValidate.defaultUserExit", "BankFusion");
				FunctionBankFusionLinkService functionBankFusionLinkService = new FunctionBankFusionLinkService();
				boolean changed2 = functionBankFusionLinkService.callDefaultUserExitLinkService(screenSet, scrnNo);

				if (functionBankFusionLinkService.isBFNotInstalledError())
				{
					String secondLevelText = LanguageResources.getString("FunctionUpdate.ReturnedMessageFromMicroflowDefaultMode");
					functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo,
									"", rowId, null, "KSM7340" + LanguageResources.getString("Language.BankNotInstalled"), "",
									rowIdString + secondLevelText, FunctionMessages.MSG_NONE);
				}
				else
				{
					changed = changed || changed2;
				}
			}
			catch (Exception e)
			{
				LOG.error(e);
				functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo, "",
								rowId, null, "KSM7375" + inputFieldSet.getId(), "", rowIdString + Toolbox.getExceptionMessage(e),
								FunctionMessages.MSG_NONE);
			}
			finally
			{
				EqTimingTest.printTime("FunctionValidate.defaultUserExit", "BankFusion");
			}
		}

		// data has been changed, then revalidate previous screens
		if (changed)
		{
			for (int j = 0; j < scrnNo; j++)
			{
				screenValidate(j);
			}
		}
	}

	/**
	 * Call validate user exit
	 * 
	 * @param scrnNo
	 *            - the screen number
	 * 
	 * @throws EQException
	 */
	private void validateUserExit(int scrnNo) throws EQException
	{
		boolean changed = false;
		InputFieldSet inputFieldSet = screenSet.getFunction().getInputFieldSets().get(scrnNo);

		// Java user exit on field set level
		try
		{
			EqTimingTest.printStartTime("FunctionValidate.validateUserExit", "Java");
			InputFieldSetAdaptor inputFieldSetAdaptor = functionAdaptor.getInputFieldSetAdaptor(homeEquationStandardSession,
							inputFieldSet);
			changed = inputFieldSetAdaptor.validateMode();

			// generate messages returned by the user exit
			if (inputFieldSetAdaptor.getInputFieldSetAdaptorImpl() != null)
			{
				List<UserExitMessage> messages = functionAdaptor.getFunctionAdaptorImpl().getReturnMessages().getReturnMessages();
				if (messages.size() > 0)
				{
					String secondLevelText = LanguageResources.getFormattedString(
									"FunctionValidate.ReturnedMessageFromUserExitValidateMode", String.valueOf(scrnNo));
					LOG.debug(secondLevelText + " : " + functionAdaptor.getFunctionAdaptorImpl().getReturnMessages());
					functionMsgManager.generateMessages(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo,
									"", rowId, null, messages, "", rowIdString + secondLevelText, functionData,
									FunctionMessages.MSG_NONE);
				}
			}

		}
		catch (Exception e)
		{
			LOG.error(e);
			functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo, "", rowId,
							null, "KSM7376" + inputFieldSet.getId(), "", rowIdString + Toolbox.getExceptionMessage(e),
							FunctionMessages.MSG_NONE);
		}
		finally
		{
			EqTimingTest.printTime("FunctionValidate.validateUserExit", "Java");
		}

		// JAVA user exit
		try
		{
			EqTimingTest.printStartTime("FunctionValidate.validateUserExit", "Java");
			boolean changed2 = changed = functionAdaptor.validateMode(scrnNo);
			changed = changed || changed2;

			// generate messages returned by the user exit
			if (functionAdaptor.getFunctionAdaptorImpl() != null)
			{
				List<UserExitMessage> messages = functionAdaptor.getFunctionAdaptorImpl().getReturnMessages().getReturnMessages();
				if (messages.size() > 0)
				{
					String secondLevelText = LanguageResources.getFormattedString(
									"FunctionValidate.ReturnedMessageFromUserExitValidateMode", String.valueOf(scrnNo));
					LOG.debug(secondLevelText + " : " + functionAdaptor.getFunctionAdaptorImpl().getReturnMessages());
					functionMsgManager.generateMessages(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo,
									"", rowId, null, messages, "", rowIdString + secondLevelText, functionData,
									FunctionMessages.MSG_NONE);
				}
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo, "", rowId,
							null, "KSM7376" + inputFieldSet.getId(), "", rowIdString + Toolbox.getExceptionMessage(e),
							FunctionMessages.MSG_NONE);
		}
		finally
		{
			EqTimingTest.printTime("FunctionValidate.validateUserExit", "Java");
		}

		// Bankfusion user exit
		ReturnDataMFUserExit returnData = null;
		if (EquationCommonContext.isBankFusionInstalled())
		{
			try
			{
				EqTimingTest.printStartTime("FunctionValidate.validateUserExit", "BankFusion");
				FunctionBankFusionLinkService functionBankFusionLinkService = new FunctionBankFusionLinkService();
				returnData = functionBankFusionLinkService.callValidateUserExitLinkService(screenSet, scrnNo);

				if (functionBankFusionLinkService.isBFNotInstalledError())
				{
					String secondLevelText = LanguageResources.getString("FunctionUpdate.ReturnedMessageFromMicroflowValidateMode");
					functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo,
									"", rowId, null, "KSM7340" + LanguageResources.getString("Language.BankNotInstalled"), "",
									rowIdString + secondLevelText, FunctionMessages.MSG_NONE);
				}
				else
				{
					if (!changed)
					{
						changed = returnData.isChanged();
					}
				}
			}
			catch (Exception e)
			{
				LOG.error(e);
				functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo, "",
								rowId, null, "KSM7376" + inputFieldSet.getId(), "", rowIdString + Toolbox.getExceptionMessage(e),
								FunctionMessages.MSG_NONE);
			}
			finally
			{
				EqTimingTest.printTime("FunctionValidate.validateUserExit", "BankFusion");
			}
		}
		// generate messages returned by the user exit
		if (returnData != null && returnData.getMessages() != null && returnData.getMessages().size() > 0)
		{
			String secondLevelText = LanguageResources.getFormattedString(
							"FunctionValidate.ReturnedMessageFromMicroflowValidateMode", String.valueOf(scrnNo));
			LOG.debug(secondLevelText + " : " + returnData);
			functionMsgManager.generateMessages(homeEquationStandardSession, functionMessages, screenSet.getId(), scrnNo, "",
							rowId, null, returnData.getMessages(), "", rowIdString + secondLevelText, functionData,
							FunctionMessages.MSG_NONE);
		}

		// data has been changed, then revalidate previous screens
		if (changed)
		{
			revalidation = true;
			try
			{
				for (int j = 0; j <= scrnNo; j++)
				{
					screenValidate(j);
				}
			}
			finally
			{
				revalidation = false;
			}
		}
	}

	/**
	 * For a repeating group, consolidate all PV into a single call to the iSeries
	 * 
	 * @param dataManager
	 *            - the repeating data manager
	 * @param scrnNo
	 *            - screen number
	 * @param fieldSet
	 *            - the display input field set
	 * @param inputField
	 *            - the field to validate
	 * 
	 * @throws EQException
	 */
	private void processMultiplePVProcessing(RepeatingDataManager dataManager, int scrnNo, InputFieldSet fieldSet,
					InputField inputField) throws EQException
	{
		String fieldName = inputField.getId();
		boolean[] pvExecuted = new boolean[dataManager.totalRows()];

		FieldAdaptor fieldAdaptor = functionAdaptor.getFieldAdaptor(homeEquationStandardSession, inputField.getId(),
						GAZRecordDataModel.TYP_FIELD);

		// Retrieve the field label
		DisplayAttributesSet displayAttributesSet = screenSet.getLayout().getDisplayAttributesSets().get(scrnNo);
		DisplayAttributes displayAttribute = FunctionRuntimeToolbox.getDisplayAttributes(displayAttributesSet.getDisplayItems(),
						inputField.getId());
		String fieldLabel = FunctionRuntimeToolbox.getLabel(eqUser, inputField, displayAttribute).replaceFirst(
						EqDataType.CHARACTER_RETURN, " ");

		// Syntax validate the entire column first
		syntaxValidateColumn(dataManager, scrnNo, fieldSet, inputField, displayAttribute);

		// Loop through all the PV field sets
		for (PVFieldSet pVFieldSet : inputField.getPvFieldSets())
		{
			validateMultiplePVHelper = new FunctionValidateMultiplePVHelper();
			String pvModule = Toolbox.getFirstWord(pVFieldSet.getId());

			// Are there any fields?
			if (homeEquationStandardSession.getUnit().getPVMetaData(pvModule).rtvNumberOfFields() > 0)
			{
				int rowCount = 0;
				dataManager.moveFirst();
				while (dataManager.next())
				{
					rowCount++;
					rowId = rowCount;
					rowIdString = getRowIdString(dataManager.getId(), rowCount);
					functionDebugInfo.printField(inputField.getId() + rowIdString);

					// Retrieve field data
					FieldData fieldData = functionData.rtvFieldData(fieldName);

					// Does it need to validate this?
					if (ignoreValidationField(fieldData) || fieldData.getMsgSev() >= FunctionMessages.MSG_ERROR)
					{
						pvExecuted[rowCount - 1] = true;
					}
					else
					{
						// Validate it
						FieldSetAdaptor fieldSetAdaptor = functionAdaptor.getFieldSetAdaptor(homeEquationStandardSession,
										pVFieldSet);
						if (fieldSetAdaptor.shouldExecute(pVFieldSet))
						{
							String currentUnit = functionData.rtvFieldInputValue("CURUNT");
							String currentSystem = functionData.rtvFieldInputValue("CURSYS");
							// Set the current unit for global processing
							boolean setSessionResult = setCurrentSession();

							if (!setSessionResult)
							{
								functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages,
												screenSet.getId(), screenSet.scrnNo, "", rowId, fieldData, "KSM8011" + currentUnit
																+ "/" + currentSystem, "", rowIdString, FunctionMessages.MSG_NONE);
								break;
							}
							// PV executed
							pvExecuted[rowCount - 1] = true;

							// PV module
							if (isProperPVFromUTW19R(pVFieldSet))
							{
								executeMultiplePVModule(dataManager, scrnNo, inputField, fieldData, pVFieldSet, pvModule,
												fieldLabel);
							}

							// API disguised as PV module
							else
							{
								executeMultiplePVModuleNow(dataManager, scrnNo, inputField, pVFieldSet, pvModule, fieldLabel);
								executeAPIModule(scrnNo, inputField, fieldData, pVFieldSet, fieldLabel);
							}
						}
					}
				}

				String currentUnit = functionData.rtvFieldInputValue("CURUNT");
				String currentSystem = functionData.rtvFieldInputValue("CURSYS");
				// Set the current unit for global processing
				boolean setSessionResult = setCurrentSession();

				if (!setSessionResult)
				{
					functionMsgManager.generateMessage(homeEquationStandardSession, functionMessages, screenSet.getId(),
									screenSet.scrnNo, "", rowId, null, "KSM8011" + currentUnit + "/" + currentSystem, "",
									rowIdString, FunctionMessages.MSG_NONE);
					break;
				}
				// execute
				executeMultiplePVModuleNow(dataManager, scrnNo, inputField, pVFieldSet, pvModule, fieldLabel);
			}
		}

		// Final processing
		int rowCount = 0;
		dataManager.moveFirst();
		while (dataManager.next())
		{
			rowId = rowCount + 1;
			rowIdString = getRowIdString(dataManager.getId(), rowId);

			// retrieve field data
			FieldData fieldData = functionData.rtvFieldData(fieldName);

			// has any PV been executed?
			if (!pvExecuted[rowCount])
			{
				dataManager.setDbValue(fieldName, functionData.cvtInputIntoDb(fieldData, dataManager.getInputValue(fieldName)));
			}

			// Process the validate EL script / java code
			FunctionRuntimeToolbox.processInputFieldValidateAssignments(inputField, functionData, functionAdaptor,
							homeEquationStandardSession, fhd);

			// If no errors yet, then perform Service composer validation
			if (fieldData.getMsgSev() <= FunctionMessages.MSG_ERROR)
			{
				serviceComposerValidate(scrnNo, inputField, fieldData, fieldAdaptor, fieldLabel);
			}

			// next element
			rowCount++;
		}
	}

	/**
	 * Performs syntax validation on the field<br>
	 * 
	 * @param dataManager
	 *            - the repeating data manager
	 * @param scrnNo
	 *            - screen number
	 * @param inputFieldSet
	 *            - the input field set
	 * @param inputField
	 *            - the input field
	 * @param displayAttributes
	 *            - the display attributes
	 * 
	 * @return the message severity for this field checking
	 * 
	 * @throws EQException
	 */
	private void syntaxValidateColumn(RepeatingDataManager dataManager, int scrnNo, InputFieldSet inputFieldSet,
					InputField inputField, DisplayAttributes displayAttributes) throws EQException
	{
		validateMultiplePVHelper = new FunctionValidateMultiplePVHelper();
		dataManager.moveFirst();
		int rowCount = 0;
		while (dataManager.next())
		{
			rowCount++;
			rowId = rowCount;
			rowIdString = getRowIdString(dataManager.getId(), rowCount);
			syntaxValidate(scrnNo, inputFieldSet, inputField, displayAttributes);
		}
	}

	/**
	 * Execute a PV module
	 * 
	 * @param dataManager
	 *            - the repeating data manager
	 * @param scrnNo
	 *            - the current screen number
	 * @param inputField
	 *            - field to validate
	 * @param fieldData
	 *            - field data of the field
	 * @param pvFieldSet
	 *            - the PV field set
	 * @param fieldLabel
	 *            - field label
	 * 
	 * @throws EQException
	 */
	private void executeMultiplePVModule(RepeatingDataManager dataManager, int scrnNo, InputField inputField, FieldData fieldData,
					PVFieldSet pvFieldSet, String pvModule, String fieldLabel) throws EQException
	{
		// setup dsccn
		inputMappingExist = false;
		EquationPVData equationPVData = setupDSCCN(currentEquationStandardSession, pvModule, inputField, pvFieldSet);

		// validate the field
		EquationStandardValidation equationStandardValidation = new EquationStandardValidation(pvFieldSet.getDecode(), pvModule,
						equationPVData, Toolbox.cvtBooleanToYN(pvFieldSet.isBlankAllowed()), pvFieldSet.getNewField());
		equationStandardValidation.setSecurity(pvFieldSet.getSecurity());

		// is the PV cache?
		EquationAPICache equationPVResult = null;
		EquationAPICacheHandler equationPVResultHandler = currentEquationStandardSession.getUnit().getApiCacheHandler();
		if (pvFieldSet.isCache())
		{
			equationPVResult = equationPVResultHandler.getFromCache(eqUser.getLanguageId(), equationStandardValidation);
		}

		// add to the list
		if (equationPVResult == null)
		{
			// max out at the end?
			if (validateMultiplePVHelper.isExceedBuffer(equationStandardValidation.getEquationPVData().getPvMetaData()
							.rtvDSCCNLength(equationStandardValidation.getDataInput().length())))
			{
				int saveRowId = rowId; // Save rowId/rowIdString so it can continue from the correct point
				String saveRowIdString = rowIdString;
				executeMultiplePVModuleNow(dataManager, scrnNo, inputField, pvFieldSet, pvModule, fieldLabel);
				rowId = saveRowId; // Restore previous rowId and rowIdString
				rowIdString = saveRowIdString;
			}

			validateMultiplePVHelper.add(rowId, rowIdString, inputMappingExist, equationStandardValidation);
		}
		// process
		else
		{
			processEquationStandardValidation(inputField, fieldData, pvFieldSet, fieldLabel, scrnNo, equationStandardValidation);
		}
	}

	/**
	 * Execute the consolidated PV in a single call
	 * 
	 * @param dataManager
	 *            - the repeating data manager
	 * @param scrnNo
	 *            - the current screen number
	 * @param inputField
	 *            - field to validate
	 * @param pvFieldSet
	 *            - the PV field set
	 * @param fieldLabel
	 *            - field label
	 * 
	 * @throws EQException
	 */
	private void executeMultiplePVModuleNow(RepeatingDataManager dataManager, int scrnNo, InputField inputField,
					PVFieldSet pvFieldSet, String pvModule, String fieldLabel) throws EQException
	{
		// backup current row index
		int rowIndex = dataManager.currentRowNumber();

		// execute it
		EquationStandardMultipleValidation equationStandardMultipleValidation = validateMultiplePVHelper
						.getEquationStandardMultipleValidation();
		if (equationStandardMultipleValidation.getEquationStandardValidations().size() <= 0)
		{
			return;
		}

		currentEquationStandardSession.executeMultipleValidate(equationStandardMultipleValidation);

		// process each
		EquationStandardValidation equationStandardValidation;
		int length = validateMultiplePVHelper.getRowIndexes().size();
		for (int i = 0; i < length; i++)
		{
			rowId = validateMultiplePVHelper.getRowIndexes().get(i);
			rowIdString = validateMultiplePVHelper.getRowIdStrings().get(i);
			inputMappingExist = validateMultiplePVHelper.getInputMappingExists().get(i);
			equationStandardValidation = equationStandardMultipleValidation.getEquationStandardValidations().get(i);

			dataManager.setRow(rowId - 1);
			FieldData fieldData = functionData.rtvFieldData(inputField.getId());
			processEquationStandardValidation(inputField, fieldData, pvFieldSet, fieldLabel, scrnNo, equationStandardValidation);
		}

		// clear the list
		validateMultiplePVHelper.clear();

		// reposition
		dataManager.setRow(rowIndex);
	}

	/**
	 * @return true if the method was able to set a current session based on function data, otherwise false
	 */
	private boolean setCurrentSession()
	{
		boolean setSuccess = true;
		// Need to see if the current unit in which to execute the PV is anything but the default...
		String currentUnit = functionData.rtvFieldInputValue("CURUNT");
		String currentSystem = functionData.rtvFieldInputValue("CURSYS");

		// Use the "home" session if target unit is the same or not specified
		if (currentUnit == null
						|| currentUnit.trim().equals("")
						|| currentSystem == null
						|| currentSystem.trim().equals("")
						|| (currentUnit.equals(homeEquationStandardSession.getUnitId()) && currentSystem
										.equals(homeEquationStandardSession.getSystemId())))
		{
			currentEquationStandardSession = homeEquationStandardSession;
		}
		else
		{
			if (SystemStatusManager.getInstance().isUnitAvailable(currentSystem, currentUnit))
			{
				currentEquationStandardSession = homeEquationStandardSession;
				setSuccess = true;
			}

			// Should use a session dedicated to the user in the unit specified
			EquationUser user = EquationCommonContext.getContext().getEquationUser(currentSystem, currentUnit,
							homeEquationStandardSession.getSessionIdentifier());
			if (user == null)
			{
				setSuccess = false;
			}
			else
			{
				currentEquationStandardSession = user.getSession();
			}

			// Need to reset the current unit so that next time it will be reset
			functionData.chgFieldInputValue("CURUNT", "");
			functionData.chgFieldInputValue("CURSYS", "");
		}
		return setSuccess;
	}
	/**
	 * Return the row id in String format for identifying the row with error
	 * 
	 * @param id
	 *            - the repeating data manager id
	 * @param rowCount
	 *            - the row count
	 * 
	 * @return the string
	 */
	private String getRowIdString(String id, int rowCount)
	{
		return "[" + id + " " + LanguageResources.getString("Language.Row") + " "
						+ Toolbox.leftZeroPad(rowCount, RepeatingDataManager.LIST_INDEX_LEN) + "] ";
	}

	/**
	 * Determine whether the PV field set is a proper PV module from UTW19R
	 * 
	 * @param pvFieldSet
	 *            - the pv field set
	 * 
	 * @return true if the PV field set is a proper PV module from UTW19R
	 */
	private boolean isProperPVFromUTW19R(PVFieldSet pvFieldSet) throws EQException
	{
		// transaction type must be blank
		if (pvFieldSet.getType().trim().length() == 0)
		{
			String pvModule = Toolbox.getFirstWord(pvFieldSet.getId());
			EquationPVMetaData equationPvMetaData = currentEquationStandardSession.getUnit().getPVMetaData(pvModule);
			return equationPvMetaData.getPvSource() == EquationPVMetaData.PV_SOURCE_UTW19R;
		}

		return false;
	}

	/**
	 * Processing of field during load mode
	 * 
	 * @param scrnNo
	 *            - screen number
	 * @param field
	 *            - field
	 * @param fieldData
	 *            - field data
	 * @param fieldAdaptor
	 *            - field adaptor
	 */
	private void processDuringLoadMode(int scrnNo, InputField field, FieldData fieldData, FieldAdaptor fieldAdaptor)
					throws EQException
	{
		// for numeric, update the input value with the output value (for non-zero only)
		if (EqDataType.isNumeric(fieldData.getFieldType()))
		{
			String dbValue = fieldData.getDbValue();
			if (Toolbox.parseDouble(dbValue, 0) != 0)
			{
				// if edit code is with replacement, then re-edit without using the replacement values
				if (fieldData.getEditCode().equals(EqDataType.EDIT_AMOUNT_WITH_REPLACE))
				{
					String fieldType = fieldData.getFieldType();
					int fieldLength = fieldData.getFieldLength();
					int fieldDecimal = fieldData.getFieldDecimal();
					String editedAmount = FunctionRuntimeToolbox.editEquationData(eqUser, functionData, dbValue, field.getId(),
									fieldType, fieldLength, fieldDecimal, "1", "", null, null);
					fieldData.setInputValue(editedAmount);
				}
				else
				{
					String outputValue = fieldData.getOutputValue();
					String testNumeric = EqDataType.cvtNumericToDb(outputValue, functionData.getIntegerSeparator(), functionData
									.getDecimalSeparator());
					try
					{
						Double.parseDouble(testNumeric);
						fieldData.setInputValue(outputValue);
					}
					catch (NumberFormatException e)
					{
					}
				}
			}
		}
	}
}