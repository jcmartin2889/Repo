package com.misys.equation.function.tools;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.dao.beans.WERecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.LinkedFunction;
import com.misys.equation.function.beans.NextAction;
import com.misys.equation.function.el.ELRuntime;
import com.misys.equation.function.language.LanguageResources;

public class LinkedFunctionsToolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LinkedFunctionsToolbox.java 17007 2013-08-16 13:20:11Z perkinj1 $";

	/** Delimeter for linked function */
	public final static String LINKEDOPTION_DELIMETER = "*";

	/** Special codes */
	public final static String LINKED_EDIT = "+EDIT";
	public final static String LINKED_ADD = "+ADD";
	public final static String LINKED_DEL = "+DEL";
	public final static String LINKED_DSP = "+DSP";
	public final static String LINKED_INS = "+INS";

	/** Referral codes */
	public final static String ACTION_ROUTE_TO_OPTION_2 = "routeToOption2";
	public final static String ACTION_ROUTE_TO_SESSION_2 = "routeToSessionRestore2";
	public final static String ACTION_ROUTE_TO_LRP = "routeToLRP";

	/** The list of linked functions */
	private List<LinkedFunction> linkedFunctions;
	private Hashtable<String, List<String>> contexts;

	/** The equivalent String of the linked functions */
	private String validValueListing;
	private String validSelectionOptionsListing;

	/** The list of validated linked functions for this row */
	private List<LinkedFunction> validatedLinkedFunctions;

	/** The list of global link function */
	private List<LinkedFunction> globalLinkedFunctions;

	/**
	 * Create a linked function toolbox with the given the list of linked functions
	 * 
	 * @param linkedFunctions
	 *            - the linked function string
	 */
	public LinkedFunctionsToolbox(List<LinkedFunction> linkedFunctions)
	{
		validValueListing = null;
		validSelectionOptionsListing = null;
		parse(linkedFunctions);
	}

	/**
	 * Parse the linked function into individual component
	 * 
	 * @param linkedFunctions
	 *            - the linked functions
	 */
	private void parse(List<LinkedFunction> linkedFunctions)
	{
		this.globalLinkedFunctions = new ArrayList<LinkedFunction>();
		this.linkedFunctions = new ArrayList<LinkedFunction>();

		// no linked function?
		if (linkedFunctions.size() == 0)
		{
			contexts = new Hashtable<String, List<String>>();
			return;
		}

		// initialise the list
		contexts = new Hashtable<String, List<String>>();

		for (LinkedFunction linkedFunction : linkedFunctions)
		{
			// global?
			if (linkedFunction.isGlobalLink())
			{
				this.globalLinkedFunctions.add(linkedFunction);
			}

			// non-global
			else
			{
				this.linkedFunctions.add(linkedFunction);
			}

			// contexts
			List<String> listContexts = new ArrayList<String>();
			if (!ContextString.isEmpty(linkedFunction.getContextFields()))
			{
				String[] split = linkedFunction.getContextFields().split(Toolbox.CONTEXT_DELIMETER);
				for (String element : split)
				{
					listContexts.add(element);
				}
			}
			contexts.put(linkedFunction.getSelectionOption(), listContexts);
		}
	}

	/**
	 * Return the index of the selection option
	 * 
	 * @param selectionOption
	 *            - the selection option
	 * 
	 * @return the index within the array
	 */
	private int getIndex(String selectionOption, List<LinkedFunction> linkedFunctions)
	{
		for (int i = 0; i < linkedFunctions.size(); i++)
		{
			LinkedFunction linkedFunction = linkedFunctions.get(i);
			if (linkedFunction.getSelectionOption().equals(selectionOption))
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * Return the list of linked function in the format of Valid Values
	 * 
	 * @return the list of linked function in the format of Valid Values
	 */
	public String getValidValueListing()
	{
		if (validValueListing == null)
		{
			validValueListing = getValidValueListing(linkedFunctions);
		}

		return validValueListing;
	}

	/**
	 * Return the list of validated linked function in the format of Valid Values
	 * 
	 * @return the list of validated linked function in the format of Valid Values
	 */
	public String getValidatedValidValues()
	{
		return getValidValueListing(validatedLinkedFunctions);
	}

	/**
	 * Return the list of linked function in the format of Valid Values
	 * 
	 * @param linkedFunctions
	 *            - the list of link functions
	 * 
	 * @return the list of linked function in the format of Valid Values
	 */
	private String getValidValueListing(List<LinkedFunction> linkedFunctions)
	{
		StringBuilder buffer = new StringBuilder();

		for (LinkedFunction linkedFunction : linkedFunctions)
		{
			buffer.append(linkedFunction.getSelectionOption() + "=" + linkedFunction.getOptionId() + " "
							+ linkedFunction.getOptionDescription() + Toolbox.CONTEXT_DELIMETER);
		}

		if (linkedFunctions.size() > 0)
		{
			buffer.setLength(buffer.length() - 1);
		}

		return buffer.toString();
	}

	/**
	 * Return the list of selection options - delimeted by comma
	 * 
	 * @return the list of selection options
	 */
	public String getValidSelectionOptionsListing()
	{
		if (validSelectionOptionsListing == null)
		{
			validSelectionOptionsListing = getValidSelectionOptionListing(linkedFunctions);
		}

		return validSelectionOptionsListing;
	}

	/**
	 * Return the list of validated selection options - delimeted by comma
	 * 
	 * @return the list of selection options
	 */
	public String getValidatedValidSelectionOptionsListing()
	{
		return getValidSelectionOptionListing(validatedLinkedFunctions);
	}

	/**
	 * Return the list of selection options - delimeted by comma
	 * 
	 * @param linkedFunctions
	 *            - the list of linked functions
	 * 
	 * @return the list of selection options
	 */
	private String getValidSelectionOptionListing(List<LinkedFunction> linkedFunctions)
	{
		StringBuilder buffer = new StringBuilder();

		for (LinkedFunction linkedFunction : linkedFunctions)
		{
			buffer.append(linkedFunction.getSelectionOption() + ", ");
		}

		if (linkedFunctions.size() > 0)
		{
			buffer.setLength(buffer.length() - 2);
		}

		return buffer.toString();
	}

	/**
	 * Return the linked function
	 * 
	 * @param selectionOption
	 *            - the selection option
	 * 
	 * @return the linked function of the specified option
	 */
	public LinkedFunction getLinkedFunction(String selectionOption)
	{
		int index = getIndex(selectionOption, linkedFunctions);
		if (index >= 0)
		{
			LinkedFunction linkedFunction = linkedFunctions.get(index);
			return linkedFunction;
		}
		else
		{
			index = getIndex(selectionOption, globalLinkedFunctions);
			if (index >= 0)
			{
				LinkedFunction linkedFunction = globalLinkedFunctions.get(index);
				return linkedFunction;
			}
			else
			{
				return null;
			}
		}
	}

	/**
	 * Return the equivalent option of the selection option
	 * 
	 * @param selectionOption
	 *            - the selection option
	 * 
	 * @return the equivalent option of the selection option
	 */
	public String getOption(String selectionOption)
	{
		LinkedFunction linkedFunction = getLinkedFunction(selectionOption);
		if (linkedFunction == null)
		{
			return "";
		}
		else
		{
			return linkedFunction.getOptionId();
		}
	}

	/**
	 * Return the field values as specified by the context (e.g. 0000:000001:001 for context string AB:AN:AS)
	 * 
	 * @param selectionOption
	 *            - the selection option
	 * @param functionData
	 *            - the data
	 * @param legacyFunction
	 *            - true if to return context string style for legacy function
	 * 
	 * @return the field values as specified by the context
	 */
	public String getContextString(String selectionOption, FunctionData functionData, boolean legacyFunction)
	{
		List<String> contextFields = contexts.get(selectionOption);
		if (contextFields != null)
		{
			ContextString cs = ContextString.generateContextString(contextFields, functionData);
			if (legacyFunction)
			{
				return cs.getLegacyContextString();
			}
			else
			{
				return cs.getContextString();
			}
		}
		else
		{
			return "";
		}
	}
	/**
	 * Return next action if drill down is to happen using a new page
	 * 
	 * @param unit
	 *            - the unit
	 * @param optionId
	 *            - the option identifier
	 * @param selectionOption
	 *            - the selection option
	 * @param functionData
	 *            - the data
	 * 
	 * @return next action if drill down is to happen using a new page
	 * @throws EQException
	 */
	public NextAction getNextAction(EquationUnit unit, String optionId, String selectionOption, FunctionData functionData)
					throws EQException
	{
		NextAction nextAction = null;
		boolean legacyFunction = unit.isLegacyOption(optionId);

		String actionType = null;
		String control = null;
		String sessionId = null;
		String transactionId = null;
		String originalFullUser = null;
		String status = null;
		String authorityLevel = null;
		int screenSetId = 0;
		int screenNumber = 0;
		String context = "";

		List<String> contextFields = contexts.get(selectionOption);

		if (contextFields != null)
		{
			ContextString cs = ContextString.generateContextString(contextFields, functionData);
			if (legacyFunction)
			{
				context = cs.getLegacyContextString();
			}
			else
			{
				context = cs.getContextString();
			}

			// Determine if this is a special referrals Enquiry
			if (optionId.equals("TWL"))
			{
				// A_USER:A_WEOID:A_WEUID:A_WEUID2:A_WEAUID:A_WEASTS:A_WESID:A_WETID:A_WESSET:A_WESCRN:A_WEAUTH:A_WEJBD
				List<String> fields = cs.getContexts();
				String userId = fields.get(0); // ("A_USER");
				optionId = fields.get(1); // ("A_WEOID");
				String origUser = fields.get(2); // "A_WEUID");
				originalFullUser = fields.get(3); // ("A_WEUID2");
				String superUser = fields.get(4); // ("A_WEAUID");
				status = fields.get(5); // ("A_WEASTS");
				sessionId = fields.get(6); // ("A_WESID");
				transactionId = fields.get(7); // ("A_WETID");
				screenSetId = new Integer(fields.get(8)).intValue(); // ("A_WESSET");
				screenNumber = new Integer(fields.get(9)).intValue(); // ("A_WESCRN");
				authorityLevel = fields.get(10); // ("A_WEAUTH");
				String jobName = fields.get(11); // ("A_WEJBD");
				String type = fields.get(12); // ("A_TYPE");

				legacyFunction = unit.isLegacyOption(optionId);

				// Non-equation desktop function
				if (sessionId.equals(""))
				{
					actionType = ACTION_ROUTE_TO_OPTION_2;
					optionId = "AU";
					context = "";
				}
				// LRP
				else if ("L".equals(type))
				{
					control = "4";
					actionType = ACTION_ROUTE_TO_LRP;
					// TaskId will be in session Id
					// TaskType will be in transaction Id
				}
				// Non-equation desktop equation with maker-checker processing
				// Submitted for checking
				else if (status.equals(WERecordDataModel.MAKER_CHECKER_STAT_AWAIT) && userId.equals(origUser)
								&& !userId.equals(superUser) && !superUser.equals("") && !jobName.equalsIgnoreCase(""))
				{
					actionType = ACTION_ROUTE_TO_OPTION_2;
					optionId = "SBL";
					context = "";
				}
				// Awaiting checking
				else if (status.equals(WERecordDataModel.MAKER_CHECKER_STAT_AWAIT) && !jobName.equalsIgnoreCase(""))
				{
					actionType = ACTION_ROUTE_TO_OPTION_2;
					optionId = "AWL";
					context = "";
				}
				// Approved by checker
				else if (status.equals(WERecordDataModel.MAKER_CHECKER_STAT_APPRVD) && !jobName.equalsIgnoreCase(""))
				{
					actionType = ACTION_ROUTE_TO_OPTION_2;
					optionId = "ARL";
					context = "";
				}
				// Rejected by checker
				else if (status.equals(WERecordDataModel.MAKER_CHECKER_STAT_REJCTD) && !jobName.equalsIgnoreCase(""))
				{
					actionType = ACTION_ROUTE_TO_OPTION_2;
					optionId = "RJL";
					context = "";
				}

				// Non-equation desktop function
				else if (!jobName.equalsIgnoreCase(""))
				{
					actionType = ACTION_ROUTE_TO_OPTION_2;
					optionId = "AU";
					context = "";
				}
				// Control attributes
				// 1 - Restore a work in progress session
				// 2 - Restore a session for the supervisor override
				// 3 - Restore a session that has been authorised/declined by the supervisor
				// 4 - Restore a LRP task

				// Template
				else if (status.equals(WERecordDataModel.STAT_TMPLT))
				{
					actionType = ACTION_ROUTE_TO_SESSION_2;
					control = "1";
				}

				// Referred by another user
				else if (status.equals(WERecordDataModel.STAT_REFUSR))
				{
					actionType = ACTION_ROUTE_TO_SESSION_2;
					control = "1";
				}

				// Desktop function - referred to another user
				else if (userId.equals(origUser) && !userId.equals(superUser) && !superUser.equals(""))
				{
					actionType = ACTION_ROUTE_TO_SESSION_2;
					control = "3";
				}

				// Desktop function - user's transaction
				else if (userId.equals(origUser))
				{
					actionType = ACTION_ROUTE_TO_SESSION_2;
					control = "1";
				}
				// As supervisor
				else
				{
					actionType = ACTION_ROUTE_TO_SESSION_2;
					control = "2";
				}

			}
		}

		String optionType = legacyFunction ? "WF" : "EQ";
		String optionDescription = unit.getOptionDescription(optionId);

		nextAction = new NextAction(actionType, control, optionType, optionId, optionDescription, context, sessionId,
						transactionId, originalFullUser, status, authorityLevel, screenSetId, screenNumber);
		return nextAction;
	}
	/**
	 * Determine if it is a valid selection option
	 * 
	 * @param selectionOption
	 *            - the selection option to be validated
	 * 
	 * @return true if it is a valid selection option
	 */
	public boolean isValidSelection(String selectionOption)
	{
		return (getIndex(selectionOption, linkedFunctions) >= 0);
	}

	/**
	 * Determine if it is a valid validated selection option
	 * 
	 * @param selectionOption
	 *            - the selection option to be validated
	 * 
	 * @return true if it is a valid validated selection option
	 */
	public boolean isValidatedValidSelection(String selectionOption)
	{
		return (getIndex(selectionOption, validatedLinkedFunctions) >= 0);
	}

	/**
	 * Determine whether the selection option is associated with a special linked function
	 * 
	 * @param selectionOption
	 *            - the selection option to be validated
	 * 
	 * @return true if it is a special linked function
	 */
	public boolean isSpecialCode(String selectionOption)
	{
		LinkedFunction linkedFunction = getLinkedFunction(selectionOption);
		if (linkedFunction == null)
		{
			return false;
		}
		else
		{
			return isReservedLinkedFunction(linkedFunction.getOptionId());
		}
	}

	/**
	 * Determine whether the selection option is associated with a linked function that will be auto executed
	 * 
	 * @param selectionOption
	 *            - the selection option to be validated
	 * 
	 * @return true if it is a special linked function
	 */
	public boolean isAutoExecute(String selectionOption)
	{
		LinkedFunction linkedFunction = getLinkedFunction(selectionOption);
		if (linkedFunction == null)
		{
			return false;
		}
		else
		{
			return linkedFunction.getAutoExecuteLink() != LinkedFunction.AUTO_EXECUTE_DSP_ALWAYS;
		}
	}

	/**
	 * Determine whether the linked function is a special one (e.g. edit, add, delete, etc)
	 * 
	 * @param linkedFunction
	 *            - the linked function
	 * 
	 * @return true if it is a special linked function
	 */
	public boolean isReservedLinkedFunction(String linkedFunction)
	{
		if (linkedFunction.equals(LINKED_EDIT) || linkedFunction.equals(LINKED_ADD) || linkedFunction.equals(LINKED_DEL)
						|| linkedFunction.equals(LINKED_DSP) || linkedFunction.equals(LINKED_INS))
		{
			return true;
		}
		return false;
	}

	/**
	 * Return the default linked function for supervisor
	 * 
	 * @return the default linked function for supervisor
	 */
	public String getValidValueLinkedFunctionSupervisor()
	{
		// allow display, only if special code exists on the linked function, otherwise, do not allow supervisor to see details
		for (LinkedFunction linkedFunction : linkedFunctions)
		{
			if (isReservedLinkedFunction(linkedFunction.getOptionId()))
			{
				return "1=" + LinkedFunctionsToolbox.LINKED_DSP;
			}
		}
		return "";
	}

	/**
	 * Return the default linked function for supervisor
	 * 
	 * @return the default linked function for supervisor
	 */
	public List<LinkedFunction> getLinkedFunctionSupervisor()
	{
		List<LinkedFunction> linkedFunctions = new ArrayList<LinkedFunction>();
		// allow display, only if special code exists on the linked function, otherwise, do not allow supervisor to see details
		for (LinkedFunction linkedFunction : linkedFunctions)
		{
			if (isReservedLinkedFunction(linkedFunction.getOptionId()))
			{
				LinkedFunction linkedFunction2 = new LinkedFunction("1", LinkedFunctionsToolbox.LINKED_DSP, "");
				linkedFunctions.add(linkedFunction2);
				return linkedFunctions;
			}
		}
		return linkedFunctions;
	}

	/**
	 * Return the field values as specified by the context (e.g. 0000:000001:001 for context string AB:AN:AS)
	 * 
	 * @param contextFields
	 *            - the list of fields
	 * @param functionData
	 *            - the data
	 * @param inputValue
	 *            - input or db value?
	 * @param delimiter
	 *            - the delimiter between field values
	 * 
	 * @return the field values as specified by the context
	 */
	public static String getContextValue(String contextFields, FunctionData functionData, boolean inputValue, String delimiter)
	{
		// no context fields
		if (contextFields.trim().length() == 0)
		{
			return "";
		}

		String[] list = contextFields.split(Toolbox.CONTEXT_DELIMETER);
		StringBuilder buffer = new StringBuilder();
		for (String fieldName : list)
		{
			String fieldValue = "";
			if (inputValue)
			{
				fieldValue = functionData.rtvFieldInputValue(fieldName);
			}
			else
			{
				fieldValue = functionData.rtvFieldDbValue(fieldName);
			}

			buffer.append(fieldValue);
			buffer.append(delimiter);
		}

		if (buffer.length() > 0)
		{
			buffer.setLength(buffer.length() - delimiter.length());
		}
		return buffer.toString();
	}

	/**
	 * Convert the list of linked functions to String format
	 * 
	 * @param linkedFunctions
	 *            - the list of linked functions
	 * 
	 * @return the linked functions in String format delimeted by LINKEDOPTION_DELIMETER
	 */
	public static String cvtListToString(List<LinkedFunction> linkedFunctions)
	{
		StringBuilder buffer = new StringBuilder();
		for (LinkedFunction linkedFunction : linkedFunctions)
		{
			buffer.append(linkedFunction.getSelectionOption());
			buffer.append(Toolbox.CONTEXT_DELIMETER);
			buffer.append(linkedFunction.getOptionId());
			buffer.append(Toolbox.CONTEXT_DELIMETER);
			buffer.append(linkedFunction.getContextFields());
			buffer.append(LINKEDOPTION_DELIMETER);
		}

		// remove the last LINKEDOPTION_DELIMETER
		if (buffer.length() > 0)
		{
			buffer.setLength(buffer.length() - 1);
		}

		return buffer.toString();
	}

	/**
	 * Return the list of linked function in the format of Valid Values
	 * 
	 * @param functionData
	 *            - the function data
	 * @param eqUser
	 *            - the Equation User
	 * @param sessionType
	 *            - the session type
	 * @param webFacingInstalled
	 *            - true if WebFacing is installed
	 * 
	 * @return true if at least one linked function has been ignored
	 */
	public boolean validateLinkedFunction(FunctionData functionData, EquationUser eqUser, int sessionType,
					boolean webFacingInstalled)
	{
		validatedLinkedFunctions = new ArrayList<LinkedFunction>();
		boolean atLeastOneIgnore = false;
		for (LinkedFunction linkedFunction : linkedFunctions)
		{
			// is user authorised to it?
			boolean ignore = false;

			if (!isSpecialCode(linkedFunction.getSelectionOption())
							&& !FunctionRuntimeToolbox.isAllowLinkFunction(sessionType, eqUser, linkedFunction.getOptionId(),
											webFacingInstalled))
			{
				ignore = true;
			}

			// to be added then run the script validation
			if (!ignore)
			{
				ignore = validateLinkedFunction(functionData, linkedFunction);
			}

			// include or not?
			if (ignore)
			{
				atLeastOneIgnore = true;
			}
			else
			{
				validatedLinkedFunctions.add(linkedFunction);
			}
		}

		// no ignored linked function, then use the default
		if (!atLeastOneIgnore)
		{
			validatedLinkedFunctions = null;
		}
		return atLeastOneIgnore;
	}

	/**
	 * Validate whether the linked function is ignored for this record
	 * 
	 * @param functionData
	 *            - the function data
	 * @param linkedFunction
	 *            - the linked function to be validated
	 * 
	 * @return true if the linked function is ignored
	 */
	public boolean validateLinkedFunction(FunctionData functionData, LinkedFunction linkedFunction)
	{
		boolean ignore = ELRuntime.runBooleanScript(linkedFunction.getIgnoreLinkFuncExpression(), functionData, linkedFunction
						.getSelectionOption(), LanguageResources.getString("Language.LinkedFunctionCheck"), false,
						ELRuntime.DB_VALUE);
		return ignore;
	}

	/**
	 * Return the list of validated linked functions
	 * 
	 * @return the list of validated linked functions
	 */
	public List<LinkedFunction> getValidatedLinkedFunctions()
	{
		return validatedLinkedFunctions;
	}

	/**
	 * Return the list of global linked functions
	 * 
	 * @return the list of global linked functions
	 */
	public List<LinkedFunction> getGlobalLinkedFunctions()
	{
		return globalLinkedFunctions;
	}

	/**
	 * Return the default selection option
	 * 
	 * @return the default selection option
	 */
	public String getDefaultSelectionOption()
	{
		// default linked function
		if (validatedLinkedFunctions == null)
		{
			return getDefaultSelectionOption(linkedFunctions);
		}

		// validated linked function
		else
		{
			return getDefaultSelectionOption(validatedLinkedFunctions);
		}
	}

	/**
	 * Return the default selection option
	 * 
	 * @param linkedFunctions
	 *            - the linked functions
	 * 
	 * @return the default selection option
	 */
	public String getDefaultSelectionOption(List<LinkedFunction> linkedFunctions)
	{
		// only one linked function, then this is the default
		if (linkedFunctions.size() == 1)
		{
			return linkedFunctions.get(0).getSelectionOption();
		}

		// search for the default linked function
		else
		{
			for (LinkedFunction linkedFunction : linkedFunctions)
			{
				if (linkedFunction.isDefaultLink())
				{
					return linkedFunction.getSelectionOption();
				}
			}

			// no default function, then simply return the first element
			if (linkedFunctions.size() > 1)
			{
				return linkedFunctions.get(0).getSelectionOption();
			}
		}

		return "";
	}
	/**
	 * Return true if there is only 1 linked function and selection is '1'
	 * 
	 * @return true if there is only 1 linked function and selection is '1'
	 */
	public boolean predictableLinkedFunction()
	{
		boolean predictableLinkedFuntionSelection = false;
		if (linkedFunctions.size() == 1)
		{
			for (LinkedFunction linkedFunction : linkedFunctions)
			{
				if (linkedFunction.getSelectionOption().equals("1"))
				{
					predictableLinkedFuntionSelection = true;
				}
			}
		}
		return predictableLinkedFuntionSelection;
	}

}
