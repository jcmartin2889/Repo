package com.misys.equation.function.runtime;

import com.ibm.as400.access.BidiStringType;
import com.misys.equation.common.access.EquationDataStructureData;
import com.misys.equation.common.dao.beans.GBRecordDataModel;
import com.misys.equation.common.datastructure.EqDS_DSWSI2;
import com.misys.equation.common.datastructure.EqDS_DSWSID;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.Mapping;
import com.misys.equation.function.beans.WorkField;
import com.misys.equation.function.el.ELRuntime;
import com.misys.equation.function.language.LanguageResources;

public class FunctionContextHandler
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionContextHandler.java 10725 2011-04-05 11:30:54Z REVISD1 $";

	private EquationDataStructureData dswsid1;
	private EquationDataStructureData dswsid2;
	private boolean hasContent = false;
	/**
	 * Construct the context handler
	 */
	public FunctionContextHandler()
	{
		dswsid1 = new EquationDataStructureData(new EqDS_DSWSID());
		dswsid2 = new EquationDataStructureData(new EqDS_DSWSI2());
	}

	/**
	 * Retrieve the context value of the specified field
	 * 
	 * @param fieldName
	 *            - the field name
	 * 
	 * @return the context value
	 */
	public String getFieldValue(String fieldName)
	{
		int i = whichContext(fieldName);
		String fieldValue = "";
		if (i == 1)
		{
			fieldValue = dswsid1.getFieldValue(fieldName);
		}
		else if (i == 2)
		{
			fieldValue = dswsid2.getFieldValue(fieldName);
		}

		// return value
		return Toolbox.trimr(fieldValue);
	}

	/**
	 * Set the context value of the specified field
	 * 
	 * @param fieldName
	 *            - the field name
	 * @param fieldValue
	 *            - the field value
	 */
	public void setFieldValue(String fieldName, String fieldValue)
	{
		int i = whichContext(fieldName);
		if (i == 1)
		{
			dswsid1.setFieldValue(fieldName, fieldValue);
		}
		else if (i == 2)
		{
			dswsid2.setFieldValue(fieldName, fieldValue);
		}
		// Mark as containing content
		hasContent = true;
	}

	/**
	 * Determine which context area the field name belongs to
	 * 
	 * @param fieldName
	 *            - the field name
	 * 
	 * @return 1 (dswsid1) or 2(dswsid2) or 0 (not existing)
	 */
	private int whichContext(String fieldName)
	{
		if (dswsid1.getEqDS().containsField(fieldName))
		{
			return 1;
		}
		else if (dswsid2.getEqDS().containsField(fieldName))
		{
			return 2;
		}
		else
		{
			return 0;
		}
	}

	/**
	 * Load the context into the function upon initial processing
	 * 
	 * @param contextStr
	 *            - context string passed with the option
	 * @param gbRecord
	 *            - GB record of the option
	 * @return true - if at least one field has been changed
	 */
	public boolean loadContext(Function function, FunctionData functionData, String contextStr, GBRecordDataModel gbRecord)
	{
		// GB context string
		if (gbRecord != null)
		{
			functionData.loadFieldDataFromContext(function, gbRecord.getDefEntryData(), true);
		}

		// Context data held in FunctionContext
		boolean contextChanged = loadContextDataAreaToFunctionData(function, functionData);

		// Context string passed
		if (contextStr != null)
		{
			functionData.loadFieldDataFromContext(function, contextStr, true);
		}

		if ((gbRecord != null && !gbRecord.getDefEntryData().equals("")) || contextChanged == true
						|| (contextStr != null && !contextStr.equals("")))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Load the context data into the Function Data
	 * 
	 * @param function
	 *            - the function
	 * @param functionData
	 *            - the function data
	 * @return true - if at least one field has been changed
	 */
	private boolean loadContextDataAreaToFunctionData(Function function, FunctionData functionData)
	{
		boolean changed = false;
		for (int i = 0; i < function.getInputFieldSets().size(); i++)
		{
			InputFieldSet inputFieldSet = function.getInputFieldSets().get(i);
			for (int j = 0; j < inputFieldSet.getInputFields().size(); j++)
			{
				InputField inputField = inputFieldSet.getInputFields().get(j);
				if (inputField.getInitialValueType() == WorkField.INIT_VALUE_CONTEXT)
				{
					String fieldValue = getFieldValue(inputField.getInitialValue());
					if (!fieldValue.equals(""))
					{
						functionData.chgFieldInputValue(inputField.getId(), fieldValue);
						changed = true;
					}
				}
			}
		}

		// for (int i = 0; i < function.getContextMappings().size(); i++)
		// {
		// Mapping mapping = function.getContextMappings().get(i);
		// String fieldValue = getFieldValue(mapping.getTarget()).trim();
		// if (fieldValue.length() > 0)
		// {
		// functionData.chgFieldInputValue(mapping.getSource(), fieldValue);
		// }
		// }

		return changed;
	}

	/**
	 * Save the Function to the context
	 * 
	 * @param function
	 *            - the function
	 * @param functionData
	 *            - the function data
	 */
	public void saveFunctionToContextData(Function function, FunctionData functionData)
	{
		for (int i = 0; i < function.getContextMappings().size(); i++)
		{
			Mapping mapping = function.getContextMappings().get(i);
			String fieldValue = ELRuntime.runStringScript(mapping.getSource(), functionData, mapping.getTarget(), LanguageResources
							.getString("Language.Context"), "", ELRuntime.INPUT_VALUE);
			setFieldValue(mapping.getTarget(), fieldValue);
		}
	}

	/**
	 * Copy the context area of another function context
	 * 
	 * @param functionContext
	 *            - the function context to copy from
	 */
	public void copy(FunctionContextHandler functionContext)
	{
		dswsid1.copy(functionContext.getDswsid1());
		dswsid2.copy(functionContext.getDswsid2());
		hasContent = functionContext.hasContent;
	}

	/**
	 * Reset the context area
	 */
	public void reset()
	{
		dswsid1.reset();
		dswsid2.reset();
		// Mark as NOT containing content
		hasContent = false;
	}

	/**
	 * Return the context data area 1
	 * 
	 * @return the context data area 1
	 */
	private EquationDataStructureData getDswsid1()
	{
		return dswsid1;
	}

	/**
	 * Return the context data area 2
	 * 
	 * @return the context data area 2
	 */
	private EquationDataStructureData getDswsid2()
	{
		return dswsid2;
	}

	/**
	 * Set the context data area
	 * 
	 */
	public void setContext(String context1, String context2)
	{
		if (context1 != null)
		{
			int length = dswsid1.getEqDS().getInitialBytes().length;
			String cstr = Toolbox.pad(Toolbox.trim(context1, length), length);
			byte[] b = Toolbox.convertTextIntoAS400BytesCCSID(cstr, length, Toolbox.DEF_CCSID, BidiStringType.DEFAULT);
			dswsid1.setBytes(b);
			// Mark as containing content
			hasContent = true;
		}

		if (context2 != null)
		{
			int length = dswsid2.getEqDS().getInitialBytes().length;
			String cstr = Toolbox.pad(Toolbox.trim(context2, length), length);
			byte[] b = Toolbox.convertTextIntoAS400BytesCCSID(cstr, length, Toolbox.DEF_CCSID, BidiStringType.DEFAULT);
			dswsid2.setBytes(b);
			// Mark as containing content
			hasContent = true;
		}
	}

	/**
	 * Update the context data based on the list of field values
	 * 
	 * @param fieldValues
	 *            - the list of field values with the specified separator
	 * @param fieldValueDelim
	 *            - separator between field name and field value
	 * @param fieldDelim
	 *            - separator between fields
	 */
	public void updateFields(String fieldValues, String fieldValueDelim, String fieldDelim)
	{
		String[] list = fieldValues.split(fieldDelim);
		updateFields(list, fieldValueDelim);
	}

	/**
	 * Update the context data based on the list of field values
	 * 
	 * @param fieldValues
	 *            - the list of field values
	 * @param fieldValueDelim
	 *            - separator between field name and field value
	 */
	public void updateFields(String[] fieldValues, String fieldValueDelim)
	{
		for (String fieldValue : fieldValues)
		{
			String[] list = fieldValue.split(fieldValueDelim);
			if (list.length >= 2)
			{
				setFieldValue(list[0], list[1]);
			}
		}
	}

	/**
	 * Return the context data area 1 in string format
	 * 
	 * @return the context data area 1 in string format
	 */
	public String getDswsid1Str()
	{
		return Toolbox.convertAS400TextIntoCCSID(dswsid1.getBytes(), dswsid1.getBytes().length, Toolbox.DEF_CCSID);
	}

	/**
	 * Return the context data area 2 in string format
	 * 
	 * @return the context data area 2 in string format
	 */
	public String getDswsid2Str()
	{
		return Toolbox.convertAS400TextIntoCCSID(dswsid2.getBytes(), dswsid2.getBytes().length, Toolbox.DEF_CCSID);
	}
	public boolean hasContent()
	{
		return hasContent;
	}
}
