package com.misys.equation.function.runtime;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.LinkedFunction;
import com.misys.equation.function.tools.LinkedFunctionsToolbox;

public class ScreenSetSupervisor extends ScreenSet
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ScreenSetSupervisor.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	// this store the original messages as restored from a session
	private FunctionMessages orgFuncMessages = null;

	/**
	 * Construct a Function Screen AC1
	 * 
	 * @param id
	 *            - screen set id
	 * @param functionHandlerData
	 *            the Function Handler Data
	 * 
	 * @throws EQException
	 */
	public ScreenSetSupervisor(int id, FunctionHandlerData fhd, String optionId) throws EQException
	{
		super(id, fhd, optionId);
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
		functionMessages = orgFuncMessages;
		boolean selectionMade = false;
		boolean error = false;

		// loop all the screens
		for (int i = fromScrnNo; i <= toScrnNo; i++)
		{
			// retrieve the field set
			InputFieldSet fieldSet = function.getInputFieldSets().get(scrnNo);

			// List of repeating groups that already been validated
			Hashtable<String, String> listRepeatingGroups = new Hashtable<String, String>();

			// Syntax Validation - loop through all the fields
			for (InputField field : fieldSet.getInputFields())
			{
				if (Field.isRepeating(field))
				{
					// validate list
					if (functionData.getRepeatingDataManager(field.getRepeatingGroup()) != null
									&& listRepeatingGroups.get(field.getRepeatingGroup()) == null)
					{
						FunctionValidate functionValidate = new FunctionValidate(fhd);
						functionValidate.validateSelectionOption(toScrnNo, fieldSet, field.getRepeatingGroup());

						// any error?
						if (functionValidate.getFunctionMessages().getMsgSev() != FunctionMessages.MSG_NONE)
						{
							error = true;
							functionMessages = functionValidate.getFunctionMessages();
						}
						else if (functionValidate.isSelectionExists())
						{
							selectionMade = true;
						}
					}
				}
			}
		}

		// selection made?
		if (selectionMade && !error)
		{
			processSelectionOption();
		}

		return functionMessages.getMsgSev();
	}

	/**
	 * Return true if the screenset wants to do some validation
	 * 
	 * @param fromScrnNo
	 *            - from screen number
	 * @param toScrnNo
	 *            - to screen number
	 * 
	 * @return true if the screenset wants to do some validation
	 */
	@Override
	protected boolean performValidateDuringNoUpdate(int fromScrnNo, int toScrnNo)
	{
		return true;
	}

	/**
	 * Perform selection option processing on the given list
	 * 
	 * @param repeatingGroupId
	 *            - the repeating group id
	 * @param linkedFunctions
	 *            - the linked functions for the repeating group
	 * 
	 * @return true if a selection option has been processed
	 * 
	 * @throws EQException
	 */
	@Override
	protected boolean processSelectionOption(String repeatingGroupId, List<LinkedFunction> linkedFunctions) throws EQException
	{
		// for supervisor, override the linked function with the default linked function
		LinkedFunctionsToolbox linkedFunctionsTool = new LinkedFunctionsToolbox(linkedFunctions);
		List<LinkedFunction> defaultLinkFunction = linkedFunctionsTool.getLinkedFunctionSupervisor();
		if (defaultLinkFunction.size() == 0)
		{
			return false;
		}

		return super.processSelectionOption(repeatingGroupId, defaultLinkFunction);
	}

	/**
	 * Called to do any processing prior to displaying this screen set upon entry <br>
	 * from previous screen set(e.g. paging down)
	 * 
	 * @param sourceScreenSetId
	 *            - screen set Id of the current screen set
	 * 
	 * @return SCRN_PREV - if the previous screen set should be displayed<br>
	 *         SCRN_NEXT - if the next screen set should be displayed<br>
	 *         SCRN_CUR - if this screen set should be displayed
	 */
	@Override
	protected int onEntryScreenSetFromPrev(int sourceScreenSetId) throws EQException
	{
		processSelectionOption();
		return SCRN_CUR;
	}

	/**
	 * Called when a session is restored by supervisor
	 * 
	 */
	@Override
	protected void restoreSupervisor()
	{
		// backup the original message
		orgFuncMessages = new FunctionMessages();
		orgFuncMessages.insertMessages(functionMessages);
	}

}
