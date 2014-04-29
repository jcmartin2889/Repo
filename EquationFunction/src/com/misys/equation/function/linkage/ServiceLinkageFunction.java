package com.misys.equation.function.linkage;

import java.util.List;
import java.util.Map;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.WorkField;
import com.misys.equation.function.tools.MappingList;
import com.misys.equation.function.tools.XMLToolbox;

public class ServiceLinkageFunction extends AbstractServiceLinkage
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ServiceLinkageFunction.java 14806 2012-11-05 11:58:11Z williae1 $";
	// The linked function
	protected Function linkedFunction;

	// List of work fields in the linked function
	protected Map<String, WorkField> workFieldsMap;

	// List of update APIs in the linked function
	protected Map<String, APIFieldSet> updateAPIsMap;

	// List of load APIs in the linked function
	protected Map<String, APIFieldSet> loadAPIsMap;

	// List of input fields in the linked function
	protected Map<String, InputField> inputFieldsMap;

	/**
	 * Constructor
	 * 
	 * @param primaryFunction
	 *            - the primary function
	 */
	public ServiceLinkageFunction(Function primaryFunction) throws EQException
	{
		this.linkedFunction = XMLToolbox.getXMLToolbox().duplicateFunction(primaryFunction);
	}

	/**
	 * Return the linked function
	 * 
	 * @return the linked function
	 */
	public Function getlinkedFunction()
	{
		return linkedFunction;
	}

	/**
	 * Link the services
	 * 
	 * @param secondaryFunctions
	 *            - the secondary functions
	 * 
	 * @throws EQException
	 */
	protected Function link(Function... secondaryFunctions) throws EQException
	{
		// retrieve all fields
		workFieldsMap = linkedFunction.rtvWorkFields();
		updateAPIsMap = linkedFunction.rtvUpdateAPIs();
		loadAPIsMap = linkedFunction.rtvLoadAPIs();
		inputFieldsMap = linkedFunction.rtvInputFields();

		// clear all messages
		messageContainer.clear();

		// add alll the secondary functions
		for (Function secondaryFunction : secondaryFunctions)
		{
			addFunction(secondaryFunction);
		}

		return linkedFunction;
	}

	/**
	 * Append the secondary function to the linked service
	 * 
	 * @param secondaryFunction
	 *            - the secondary function
	 */
	protected void addFunction(Function secondaryFunction) throws EQException
	{
		// Validate the order
		isFieldSetInOrder(secondaryFunction);

		// Add the input field sets
		addInputFieldSets(secondaryFunction, secondaryFunction.getInputFieldSets());

		// Add the work fields
		addWorkFields(secondaryFunction, secondaryFunction.getWorkFields());

		// Add the update APIs
		addUpdateAPIs(secondaryFunction, secondaryFunction.getUpdateAPIs());

		// Add all the other mappings
		addOtherMappings(secondaryFunction);
	}

	/**
	 * Append the list of input fields sets to the link service
	 * 
	 * @param secondaryFunction
	 *            - the secondary function
	 * @param secondaryInputFieldSets
	 *            - the list of input field sets
	 */
	protected void addInputFieldSets(Function secondaryFunction, List<InputFieldSet> secondaryInputFieldSets) throws EQException
	{
		for (InputFieldSet secondaryInputFieldSet : secondaryInputFieldSets)
		{
			addInputFieldSet(secondaryFunction, secondaryInputFieldSet);
		}
	}

	/**
	 * Append the input field set to the link service
	 * 
	 * @param secondaryFunction
	 *            - the secondary function
	 * @param secondaryInputFieldSet
	 *            - the secondary input field set to be added
	 */
	protected void addInputFieldSet(Function secondaryFunction, InputFieldSet secondaryInputFieldSet) throws EQException
	{
		ServiceLinkageInputFieldSet linkage = new ServiceLinkageInputFieldSet(linkedFunction, inputFieldsMap, loadAPIsMap);
		linkage.link(secondaryFunction, secondaryInputFieldSet);
		messageContainer.addMessages(linkage.getMessageContainer());
	}

	/**
	 * Append the list of work fields to the link service
	 * 
	 * @param secondaryFunction
	 *            - the secondary function
	 * @param workFields
	 *            - the list of work field
	 */
	protected void addWorkFields(Function secondaryFunction, List<WorkField> workFields)
	{
		// add each work field
		for (WorkField workField : workFields)
		{
			addWorkField(secondaryFunction, workField);
		}
	}

	/**
	 * Append the work field to the link service
	 * 
	 * @param secondaryFunction
	 *            - the secondary function
	 * @param workField
	 *            - the work field to be added
	 */
	protected void addWorkField(Function secondaryFunction, WorkField workField)
	{
		// Get the id
		String id = workField.getId();

		// Work field exists, then do not add anymore, but validate in case field definition conflict
		if (workFieldsMap.containsKey(id))
		{
			ServiceLinkageWorkField serviceLinkageWorkField = new ServiceLinkageWorkField(workFieldsMap.get(id));
			serviceLinkageWorkField.link(workField, secondaryFunction.getId());
			messageContainer.addMessages(serviceLinkageWorkField.getMessageContainer());
		}

		// Work field does not exist, then add it
		else
		{
			linkedFunction.getWorkFields().add(workField);
			workFieldsMap.put(id, workField);
		}
	}

	/**
	 * Append the list of update APIs to the link service
	 * 
	 * @param secondaryFunction
	 *            - the secondary function
	 * @param updateAPIs
	 *            - the list of update APIs
	 */
	protected void addUpdateAPIs(Function secondaryFunction, List<APIFieldSet> updateAPIs)
	{
		// Add each work field
		for (APIFieldSet updateAPI : updateAPIs)
		{
			// Ignore predefined field set
			String apiId = updateAPI.getId();
			boolean preDefined = apiId.equals(Function.EFC_FIELDSET) || apiId.equals(Function.GY_FIELDSET)
							|| apiId.equals(Function.G5_FIELDSET) || apiId.startsWith(Function.CRM_FIELDSET);

			if (!preDefined)
			{
				addUpdateAPI(secondaryFunction, updateAPI);
			}
		}
	}

	/**
	 * Append the update API to the link service
	 * 
	 * @param secondaryFunction
	 *            - the secondary function
	 * @param updateAPI
	 *            - the update API
	 */
	protected void addUpdateAPI(Function secondaryFunction, APIFieldSet updateAPI)
	{
		// Get the id
		String id = updateAPI.getId();

		// Update API exists?
		if (updateAPIsMap.containsKey(id))
		{
			addMessage("Language.LinkageDuplicateUpdateAPIError", id, secondaryFunction.getId(), "", "", "");
		}

		// Update API does not exist
		else
		{
			linkedFunction.getUpdateAPIs().add(updateAPI);
			updateAPIsMap.put(id, updateAPI);
		}
	}

	/**
	 * Append the other mappings
	 * 
	 * @param secondaryFunction
	 *            - the secondary function
	 */
	protected void addOtherMappings(Function secondaryFunction)
	{
		((MappingList) linkedFunction.getLoadMappings()).addAll((MappingList) secondaryFunction.getLoadMappings());
		((MappingList) linkedFunction.getUpdateMappings()).addAll((MappingList) secondaryFunction.getUpdateMappings());
		((MappingList) linkedFunction.getValidateMappings()).addAll((MappingList) secondaryFunction.getValidateMappings());
	}

	/**
	 * Determine if the order of the screen in the secondary matches the order in the primary
	 * 
	 * @param secondary
	 *            - the secondary function
	 */
	public void isFieldSetInOrder(Function secondary)
	{
		int previousIndex = -1;
		for (InputFieldSet inputFieldSet : secondary.getInputFieldSets())
		{
			// search for the field set in the primary field set
			String key = inputFieldSet.getId();
			InputFieldSet primaryFieldSet = null;
			try
			{
				primaryFieldSet = linkedFunction.getInputFieldSet(key);
			}
			catch (EQException e)
			{
				primaryFieldSet = null;
			}

			// existing, then determine the index
			if (primaryFieldSet != null)
			{
				int index = linkedFunction.getInputFieldSets().indexOf(primaryFieldSet);
				if (previousIndex > index)
				{
					addMessage("Language.LinkageInputFieldSetWrongOrderError", secondary.getId(), "", "", "", "");
				}

				// set the new index
				previousIndex = index;
			}
		}
	}

}
