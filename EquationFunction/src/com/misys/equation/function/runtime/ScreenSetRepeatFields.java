package com.misys.equation.function.runtime;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.FieldData;
import com.misys.equation.function.beans.FunctionControlData;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.RepeatingDataManager;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.misys.equation.function.tools.LinkedFunctionsToolbox;

public class ScreenSetRepeatFields extends ScreenSet
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ScreenSetRepeatFields.java 14803 2012-11-05 11:57:09Z williae1 $";

	private int sourceIndex;
	private FunctionData sourceData;
	private final String repeatingGroupId;
	private final int parentScreenSet;
	private final String operation;
	private DisplayGroup repeatingDisplayGroup;

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
	public ScreenSetRepeatFields(int id, FunctionHandlerData fhd, int parentScreenSet, String parentOption,
					String repeatingGroupId, String operation) throws EQException
	{
		super(id, fhd, parentOption);
		this.repeatingGroupId = repeatingGroupId;
		this.parentScreenSet = parentScreenSet;
		this.operation = operation;

		// determine function mode
		if (operation.equals(LinkedFunctionsToolbox.LINKED_DEL))
		{
			mode = IEquationStandardObject.FCT_DEL;
		}
		else if (operation.equals(LinkedFunctionsToolbox.LINKED_DSP))
		{
			mode = IEquationStandardObject.FCT_ENQ;
		}
		else if (operation.equals(LinkedFunctionsToolbox.LINKED_EDIT))
		{
			mode = IEquationStandardObject.FCT_MNT;
		}

		// get the actual repeating group
		repeatingDisplayGroup = null;
		for (DisplayAttributesSet displayAttributesSet : layout.getDisplayAttributesSets())
		{
			repeatingDisplayGroup = displayAttributesSet.rtvRepeatingGroup(repeatingGroupId);
			if (repeatingDisplayGroup != null)
			{
				break;
			}
		}

		// regenerate a new service / layout containing only the repeating group
		FunctionRuntimeToolbox.prepareForEditRepeatingData(this, repeatingGroupId);
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
	public int onExitScreenSetToPrev(int sourceScreenSetId)
	{
		// inform parent that this is the last processed row
		fhd.setLastRepeatingGroup(repeatingGroupId);
		fhd.setLastRepeatingRow(sourceIndex + 1);

		// supervisor mode?
		if (securityLevel.getCheckerType() == SecurityLevel.CHCKR_SUPER)
		{
			outData();
			fhd.getScreenSetHandler().setLastScreenSetViewed(parentScreenSet);
		}

		// remove this from the list of functions
		screenSetHandler.getScreenSets().remove(id);

		// drill up processing, and determine whether to display the parent or another drill down
		screenSetHandler.setCurScreenSet(parentScreenSet);
		fhd.getFunctionHandler().drillUpProcessing();
		if (screenSetHandler.getScreenSets().size() >= (id + 1))
		{
			return id;
		}

		return parentScreenSet;
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
	public int onExitScreenSetToNext(int sourceScreenSetId)
	{
		// inform parent that this is the last processed row
		fhd.setLastRepeatingGroup(repeatingGroupId);
		fhd.setLastRepeatingRow(sourceIndex + 1);

		// move the function details
		outData();
		screenSetHandler.getScreenSets().remove(id);

		// drill up processing, and determine whether to display the parent or another drill down
		screenSetHandler.setCurScreenSet(parentScreenSet);
		fhd.getFunctionHandler().drillUpProcessing();
		if (screenSetHandler.getScreenSets().size() > id)
		{
			return id;
		}

		return parentScreenSet;
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
	public boolean inData(FunctionData sourceData, int index)
	{
		this.sourceData = sourceData;
		this.sourceIndex = index;

		// set something on the function data to signify that this is a repeating group
		FunctionControlData functionControlData = functionData.getFunctionControlData();
		functionControlData.addData(FunctionControlData.REPEATING_GROUP_OPER, operation);
		functionControlData.addData(FunctionControlData.REPEATING_GROUP_ROW, String.valueOf(index));

		// copy all the data non-repeating field
		functionData.copyFixedData(sourceData);

		// display the selected record?
		if (!operation.equals(LinkedFunctionsToolbox.LINKED_ADD) && !operation.equals(LinkedFunctionsToolbox.LINKED_INS))
		{
			// copy specific row from the source
			sourceData.getRepeatingDataManager(repeatingGroupId).copyRepeatingDataTo(functionData, sourceIndex);
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
	public boolean outData()
	{
		// get the repeating manager from the parent
		RepeatingDataManager repeatingDataManager = sourceData.getRepeatingDataManager(repeatingGroupId);

		// display only?
		if (operation.equals(LinkedFunctionsToolbox.LINKED_DSP))
		{
		}

		// edit?
		else if (operation.equals(LinkedFunctionsToolbox.LINKED_EDIT))
		{
			repeatingDataManager.saveRepeatingData(functionData, sourceIndex);
		}

		// add?
		else if (operation.equals(LinkedFunctionsToolbox.LINKED_ADD))
		{
			repeatingDataManager.addRow();
			repeatingDataManager.saveRepeatingData(functionData, repeatingDataManager.currentRowNumber());
			fhd.setLastRepeatingRow(repeatingDataManager.currentRowNumber() + 1);
		}

		// insert?
		else if (operation.equals(LinkedFunctionsToolbox.LINKED_INS))
		{
			repeatingDataManager.addRow(sourceIndex);
			repeatingDataManager.saveRepeatingData(functionData, repeatingDataManager.currentRowNumber());
			fhd.setLastRepeatingRow(repeatingDataManager.currentRowNumber() + 1);
		}

		// delete?
		else if (operation.equals(LinkedFunctionsToolbox.LINKED_DEL))
		{
			repeatingDataManager.deleteRow(sourceIndex);
			fhd.setLastRepeatingRow(1);
		}

		return true;
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
		functionKeys.deleteKey(FunctionKeys.KEY_SVTMPL);
		functionKeys.deleteKey(FunctionKeys.KEY_CHARGE);
		functionKeys.deleteKey(FunctionKeys.KEY_DEL);
		functionKeys.deleteKey(FunctionKeys.KEY_PRINT);
		functionKeys.deleteKey(FunctionKeys.KEY_EXCEL);

		// delete mode?
		if (operation.equals(LinkedFunctionsToolbox.LINKED_DEL))
		{
			functionKeys.deleteKey(FunctionKeys.KEY_ENT);
			functionKeys.addKey(FunctionKeys.KEY_DEL, "Delete");
		}

		return functionKeys;
	}

	/**
	 * Perform ScreenSet specific field protected routine
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
	 * @return true if field must be protected
	 */
	@Override
	public boolean fieldProtected(InputFieldSet inputFieldSet, InputField inputField, DisplayAttributesSet displayAttributesSet,
					DisplayAttributes displayAttributes, boolean currentStatus)
	{
		if (operation.equals(LinkedFunctionsToolbox.LINKED_DEL))
		{
			return true;
		}
		else if (operation.equals(LinkedFunctionsToolbox.LINKED_DSP))
		{
			return true;
		}
		else if (operation.equals(LinkedFunctionsToolbox.LINKED_EDIT))
		{
			if (repeatingDisplayGroup.chkRepeatingFieldKey(displayAttributes.getId()))
			{
				return true;
			}
		}

		return currentStatus;
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
		// do standard validation
		super.validate(fromScrnNo, toScrnNo);

		// additional validation
		int rowIndex = sourceIndex;
		if (operation.equals(LinkedFunctionsToolbox.LINKED_ADD) || operation.equals(LinkedFunctionsToolbox.LINKED_INS))
		{
			rowIndex = -1;
		}

		// construct the list of values
		if (repeatingDisplayGroup.getKeys().length() > 0)
		{
			String contextValue = LinkedFunctionsToolbox.getContextValue(repeatingDisplayGroup.getKeys(), functionData, false,
							Toolbox.CONTEXT_DELIMETER);
			// get the repeating manager from the parent
			RepeatingDataManager repeatingDataManager = sourceData.getRepeatingDataManager(repeatingGroupId);

			// check if duplicate
			int duplicateIndex = repeatingDataManager.chkUniqueKey(contextValue, repeatingDisplayGroup.getKeys(), rowIndex);

			if (duplicateIndex >= 0)
			{
				FunctionMessage fm = functionMsgManager.generateMessage(equationStandardSession, 0, fromScrnNo, "", 0, null,
								"KSM0007" + LanguageResources.getString("Language.KeyExistsInRow"), "", LanguageResources
												.getString("ScreenSetRepeatFields.ReturnedMessageFromKeyValidation"),
								FunctionMessages.MSG_NONE);
				// add the message to each of the context fields for highlighting
				if (fm != null)
				{
					String[] list = repeatingDisplayGroup.getKeys().split(Toolbox.CONTEXT_DELIMETER);
					for (String element : list)
					{
						FieldData fieldData = functionData.rtvFieldData(element);
						fieldData.getFunctionMessages().insertMessage(fm.getScreenSetId(), fm.getScrnNo(), fm.getFieldName(),
										fm.getSequence(), fm.getEqMessage(), fm.getFirstLevelText(), fm.getSecondLevelText());
					}
				}
			}
		}

		return functionMessages.getMsgSev();
	}

}
