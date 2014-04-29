package com.misys.equation.function.linkage;

import java.util.Map;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;

public class ServiceLinkageInputFieldSet extends AbstractServiceLinkage
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ServiceLinkageInputFieldSet.java 14806 2012-11-05 11:58:11Z williae1 $";
	// the linked function
	protected Function linkedFunction;

	// the list of input fields in the service
	protected Map<String, InputField> inputFieldsMap;

	// the list of load apis in the service
	protected Map<String, APIFieldSet> loadAPIsMap;

	/**
	 * Constructor
	 * 
	 * @param linkedFunction
	 *            - the linked function
	 * @param inputFieldsMap
	 *            - the list of fields in other input field sets
	 * @param loadAPIsMap
	 *            - the list of load APIs in other field sets
	 */
	public ServiceLinkageInputFieldSet(Function linkedFunction, Map<String, InputField> inputFieldsMap,
					Map<String, APIFieldSet> loadAPIsMap) throws EQException
	{
		this.linkedFunction = linkedFunction;
		this.inputFieldsMap = inputFieldsMap;
		this.loadAPIsMap = loadAPIsMap;
	}

	/**
	 * Link the source input field into the target linked function
	 * 
	 * @param secondaryFunction
	 *            - the secondary function
	 * @param secondaryInputFieldSet
	 *            - the secondary input field set to be added to the linked function
	 * 
	 * @return the target input field set
	 */
	protected InputFieldSet link(Function secondaryFunction, InputFieldSet secondaryInputFieldSet) throws EQException
	{
		// Add the input field set to the linked function if needed
		InputFieldSet targetInputFieldSet = addInputFieldSet(secondaryInputFieldSet);

		// Add the fields
		addInputFields(targetInputFieldSet, secondaryInputFieldSet, secondaryFunction);

		// Add the load APIs
		addLoadAPIs(targetInputFieldSet, secondaryInputFieldSet, secondaryFunction);

		// No input field?
		if (targetInputFieldSet.getInputFields().size() == 0)
		{
			linkedFunction.removeInputFieldSet(targetInputFieldSet);
		}

		// Return the input field set
		return targetInputFieldSet;
	}

	/**
	 * Add the input field set to the linked function if not existing
	 * 
	 * @param inputFieldSet
	 *            - the input field set
	 * 
	 * @return the input field set added (or the input field set from the linked function if already existing)
	 */
	protected InputFieldSet addInputFieldSet(InputFieldSet inputFieldSet) throws EQException
	{
		// Determine if the field set already exists in the primary field set
		int index = linkedFunction.inputFieldSetKeys().indexOf(inputFieldSet.getId());

		// Retrieve the input field set from the linked function, if existing
		InputFieldSet newInputFieldSet = null;
		if (index >= 0)
		{
			newInputFieldSet = linkedFunction.getInputFieldSets().get(index);
		}

		// Otherwise, create a replica but remove all fields
		else
		{
			newInputFieldSet = new InputFieldSet(inputFieldSet);
			newInputFieldSet.fieldKeys().clear();
			newInputFieldSet.getInputFields().clear();
			newInputFieldSet.getLoadAPIs().clear();
			linkedFunction.addInputFieldSet(newInputFieldSet);
		}

		return newInputFieldSet;
	}

	/**
	 * Add the input fields from the source to the target
	 * 
	 * @param targetInputFieldSet
	 *            - the target input field set
	 * @param sourceInputFieldSet
	 *            - the source input field set
	 * @param sourceFunction
	 *            - the function of the source input field set
	 */
	protected void addInputFields(InputFieldSet targetInputFieldSet, InputFieldSet sourceInputFieldSet, Function sourceFunction)
					throws EQException
	{
		// Re-add unique fields
		for (InputField sourceInputField : sourceInputFieldSet.getInputFields())
		{
			addInputField(targetInputFieldSet, sourceInputField, sourceFunction);
		}
	}

	/**
	 * Add an input field to the target input field set
	 * 
	 * @param targetInputFieldSet
	 *            - the target input field set
	 * @param sourceInputFieldSet
	 *            - the source input field set
	 * @param sourceFunction
	 *            - the function of the source input field set
	 */
	protected void addInputField(InputFieldSet targetInputFieldSet, InputField sourceInputField, Function sourceFunction)
					throws EQException
	{
		// Field already existing?
		InputField targetInputField = inputFieldsMap.get(sourceInputField.getId());

		if (targetInputField != null)
		{
			ServiceLinkageInputField serviceLinkageInputField = new ServiceLinkageInputField(targetInputField);
			serviceLinkageInputField.link(sourceInputField, sourceFunction.getId());
			messageContainer.addMessages(serviceLinkageInputField.getMessageContainer());
		}

		// Field not existing, then include it
		else
		{
			targetInputFieldSet.addInputField(sourceInputField);
			inputFieldsMap.put(sourceInputField.getId(), sourceInputField);
		}
	}

	/**
	 * Add all load APIs
	 * 
	 * @param targetInputFieldSet
	 *            - the target input field set
	 * @param sourceInputFieldSet
	 *            - the source input field set
	 * @param sourceFunction
	 *            - the function of the source input field set
	 */
	public void addLoadAPIs(InputFieldSet targetInputFieldSet, InputFieldSet sourceInputFieldSet, Function sourceFunction)
					throws EQException
	{
		for (APIFieldSet loadAPI : sourceInputFieldSet.getLoadAPIs())
		{

			// existing, then error
			if (loadAPIsMap.containsKey(loadAPI.getId()))
			{
				addMessage("Language.LinkageDuplidateLoadAPIError", loadAPI.getId(), sourceFunction.getId(), "", "", "");
			}

			// add it
			else
			{
				APIFieldSet apiFieldSet = new APIFieldSet(loadAPI);
				apiFieldSet.setDetermineMode(false);
				targetInputFieldSet.addLoadAPI(apiFieldSet);
				loadAPIsMap.put(apiFieldSet.getId(), apiFieldSet);
			}
		}
	}

}
