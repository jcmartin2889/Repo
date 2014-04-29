package com.misys.equation.function.test.helper;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQRuntimeException;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.function.beans.APIField;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.Element;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.beans.Mapping;
import com.misys.equation.function.beans.PVField;
import com.misys.equation.function.beans.TextBean;
import com.misys.equation.function.beans.WorkField;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.MappingToolbox;

public class FunctionGenerator
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionGenerator.java 11934 2011-09-28 10:46:16Z esther.williams $";

	// This can be changed if the base language for the service is not GB
	public static final String BASE_LANG = "GB";

	Function service;
	Layout layout;

	/**
	 * Construct a new Function
	 * 
	 * @param id
	 *            - the function's id
	 * @param label
	 *            - the function's label
	 * @param description
	 *            - the function's description
	 */
	public FunctionGenerator(String id, String label, String description, String packageName, String layoutPackageName)
	{
		service = new Function();
		service.setId(id);
		service.setLabel(label);
		service.setDescription(description);
		service.setPackageName(packageName);
		service.setBaseLanguage(BASE_LANG);

		layout = new Layout();
		layout.setServiceId(id);
		layout.setId(id);
		layout.setLabel(label);
		layout.setDescription(description);
		layout.setPackageName(layoutPackageName);
		layout.setBaseLanguage(BASE_LANG);
	}

	/**
	 * Set the label and description of the service and layout to use parameter
	 */
	public void setToParameter(EquationStandardSession session)
	{
		if (session != null)
		{
			String reusableText = FunctionToolbox.getReuseableText(session, TextBean.OWNER_MISYS, service.getBaseLanguage(),
							service.getLabel(), TextBean.TYPE_LABEL);

			// service label
			if (reusableText != null)
			{
				service.setLabelType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
				service.setLabel(reusableText);
				service.setLabelTextOwner(TextBean.OWNER_MISYS);
			}
			// service description
			reusableText = FunctionToolbox.getReuseableText(session, TextBean.OWNER_MISYS, service.getBaseLanguage(), service
							.getDescription(), TextBean.TYPE_LABEL);
			if (reusableText != null)
			{
				service.setDescriptionType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
				service.setDescription(reusableText);
				service.setDescriptionTextOwner(TextBean.OWNER_MISYS);
			}
			// layout label
			reusableText = FunctionToolbox.getReuseableText(session, TextBean.OWNER_MISYS, service.getBaseLanguage(), layout
							.getLabel(), TextBean.TYPE_LABEL);
			if (reusableText != null)
			{
				layout.setLabelType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
				layout.setLabel(reusableText);
				layout.setLabelTextOwner(TextBean.OWNER_MISYS);
			}
			// layout description
			reusableText = FunctionToolbox.getReuseableText(session, TextBean.OWNER_MISYS, service.getBaseLanguage(), layout
							.getDescription(), TextBean.TYPE_LABEL);
			if (reusableText != null)
			{
				layout.setDescriptionType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
				layout.setDescription(reusableText);
				layout.setDescriptionTextOwner(TextBean.OWNER_MISYS);
			}

		}
		else
		{
			// service label
			service.setLabelTextOwner(TextBean.OWNER_MISYS);
			service.setLabelType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
			// service description
			service.setDescriptionType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
			service.setDescriptionTextOwner(TextBean.OWNER_MISYS);
			// layout label
			layout.setLabelType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
			layout.setLabelTextOwner(TextBean.OWNER_MISYS);
			// layout description
			layout.setDescriptionType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
			layout.setDescriptionTextOwner(TextBean.OWNER_MISYS);
		}

	}
	/**
	 * Return the function id
	 * 
	 * @return the function id
	 */
	public String getId()
	{
		return service.getId();
	}

	/**
	 * Return the serialised version of the service
	 */
	public String getService() throws EQException
	{
		String xml;
		EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
		xml = beanFactory.serialiseBeanAsXML(service);
		return xml;
	}

	/**
	 * Expose the generate Function bean
	 */
	public Function getFunctionBean()
	{
		return service;
	}

	/**
	 * Expose the generate Layout bean
	 */
	public Layout getLayoutBean()
	{
		return layout;
	}

	/**
	 * Return the serialise version of the layout
	 */
	public String getLayout() throws EQException
	{
		String xml;
		EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
		xml = beanFactory.serialiseBeanAsXML(layout);
		return xml;
	}

	/**
	 * Add a display input field set
	 * 
	 * @param id
	 *            - field set id
	 * @param label
	 *            - field set label
	 * @param description
	 *            - field set description
	 * 
	 * @return the field set wrapper
	 * 
	 * @throws EQException
	 */
	public DisplayFieldSetWrapper addFieldSet(String id, String label, String description) throws EQException
	{
		DisplayFieldSetWrapper fieldSetWrapper = new DisplayFieldSetWrapper();
		fieldSetWrapper.setId(id);
		fieldSetWrapper.setLabel(label);
		fieldSetWrapper.setDescription(description);

		// set the mapping path of the field set
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();

		// add the field set to the service and layout
		service.addInputFieldSet(inputFieldSet);
		layout.addDisplayAttributesSet(fieldSetWrapper.getDisplayAttributesSet());

		// return the field set wrapper
		return fieldSetWrapper;
	}

	/**
	 * Add a display input field set
	 * 
	 * @param id
	 *            - field set id
	 * @param label
	 *            - field set label
	 * @param description
	 *            - field set description
	 * 
	 * @return the field set wrapper
	 * 
	 * @throws EQException
	 */
	public DisplayFieldSetWrapper addFieldSetParameter(String id, String label, String description) throws EQException
	{
		DisplayFieldSetWrapper fieldSetWrapper = addFieldSet(id, label, description);

		// input field set
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		// input field set label
		inputFieldSet.setLabelType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
		inputFieldSet.setLabelTextOwner(TextBean.OWNER_MISYS);
		// input field set description
		inputFieldSet.setDescriptionType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
		inputFieldSet.setDescriptionTextOwner(TextBean.OWNER_MISYS);

		DisplayAttributesSet displayAttributesSet = fieldSetWrapper.getDisplayAttributesSet();
		// display attribute set label
		displayAttributesSet.setLabelType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
		displayAttributesSet.setLabelTextOwner(TextBean.OWNER_MISYS);
		// display attribute set description
		displayAttributesSet.setDescriptionType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
		displayAttributesSet.setDescriptionTextOwner(TextBean.OWNER_MISYS);

		// return the field set wrapper
		return fieldSetWrapper;
	}

	/**
	 * Add an update API to the function
	 * 
	 * @param apiFieldSet
	 *            - the update API field set
	 * 
	 * @return the updated API field set
	 * 
	 * @throws EQException
	 */
	public APIFieldSet addUpdateAPIFieldSet(APIFieldSet apiFieldSet) throws EQException
	{
		apiFieldSet.addReservedFields();
		service.addUpdateAPI(apiFieldSet);
		return apiFieldSet;
	}

	/**
	 * Add an load API to the function
	 * 
	 * @param inputFieldSet
	 *            - the input field set
	 * @param loadAPIFieldSet
	 *            - the update API field set
	 * 
	 * @return the load API field set
	 * 
	 * @throws EQException
	 */
	public APIFieldSet addLoadAPIFieldSet(String inputFieldSet, APIFieldSet loadAPIFieldSet) throws EQException
	{
		for (int i = 0; i < service.getInputFieldSets().size(); i++)
		{
			if (service.getInputFieldSets().get(i).getId().equals(inputFieldSet))
			{
				service.getInputFieldSets().get(i).addLoadAPI(loadAPIFieldSet);
			}
		}
		return loadAPIFieldSet;
	}

	/**
	 * Add a validate mapping from a display input field to a PV field
	 * 
	 * @param fromInputFieldSet
	 *            - from display input field set
	 * @param fromInputField
	 *            - from display input field
	 * @param toInputFieldSet
	 *            - to display input field set
	 * @param toInputField
	 *            - to display input field
	 * @param toPvName
	 *            - to PV name
	 * @param toPvField
	 *            - to PV field
	 * 
	 * @throws EQException
	 */
	public void addValidateMappingToPV(String fromInputFieldSet, String fromInputField, String toInputFieldSet,
					String toInputField, String toPvName, String toPvField) throws EQException
	{
		// construct the mapping from
		String mapFrom = MappingToolbox.getFullInputFieldPath(fromInputFieldSet, fromInputField);

		// construct the mapping to
		String mapTo = MappingToolbox.getFullPVFieldPath(toInputFieldSet, toInputField, toPvName, toPvField);

		service.addValidateMapping(mapFrom, mapTo);
	}

	/**
	 * Add a validate mapping from a PV field to a display input field
	 * 
	 * @param fromInputFieldSet
	 *            - from display input field set
	 * @param fromInputField
	 *            - from display input field
	 * @param fromPvName
	 *            - to PV name
	 * @param fromPvField
	 *            - to PV field
	 * @param toInputFieldSet
	 *            - to display input field set
	 * @param toInputField
	 *            - to display input field
	 * @param type
	 *            - identify whether it is the primitive, input or output
	 * 
	 * @throws EQException
	 */

	public void addValidateMappingFromPV(String fromInputFieldSet, String fromInputField, String fromPvName, String fromPvField,
					String toInputFieldSet, String toInputField, int type) throws EQException
	{
		// construct the mapping from
		String mapFrom = MappingToolbox.getFullPVFieldPath(fromInputFieldSet, fromInputField, fromPvName, fromPvField);

		// construct the mapping to
		String mapTo = MappingToolbox.getFullInputFieldPath(toInputFieldSet, toInputField, type);

		service.addValidateMapping(mapFrom, mapTo);
	}

	/**
	 * Add a validate mapping to a PV field via script
	 * 
	 * @param inputFieldSetId
	 *            - input field set id
	 * @param inputFieldId
	 *            - input field id
	 * @param pvName
	 *            - PV name
	 * @param pvFieldId
	 *            - PV field
	 * @param script
	 *            - the script expression
	 * 
	 * @throws EQException
	 */
	public void addValidateMappingPVScript(String inputFieldSetId, String inputFieldId, String pvName, String pvFieldId,
					String script) throws EQException
	{
		PVField pvField = service.getInputFieldSet(inputFieldSetId).getInputField(inputFieldId).getPvFieldSet(pvName).getPVField(
						pvFieldId);
		pvField.setValidateAssignment(Field.ASSIGNMENT_SCRIPT);
		pvField.setValidateScript(script);
	}

	/**
	 * Add a validate mapping to a PV field via code
	 * 
	 * @param inputFieldSetId
	 *            - input field set id
	 * @param inputFieldId
	 *            - input field id
	 * @param pvName
	 *            - PV name
	 * @param pvFieldId
	 *            - PV field
	 * 
	 * @throws EQException
	 */
	public void addValidateMappingPVCode(String inputFieldSetId, String inputFieldId, String pvName, String pvFieldId)
					throws EQException
	{
		PVField pvField = service.getInputFieldSet(inputFieldSetId).getInputField(inputFieldId).getPvFieldSet(pvName).getPVField(
						pvFieldId);
		pvField.setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvField.setValidateScript("");
	}

	/**
	 * Add a validate mapping to a field's primitive value
	 * 
	 * @param inputFieldSetId
	 *            - input field set id
	 * @param inputFieldId
	 *            - input field id
	 * @param script
	 *            - the script expression
	 * 
	 * @throws EQException
	 */
	public void addValidateMappingPrimitiveFieldScript(String inputFieldSetId, String inputFieldId, String script)
					throws EQException
	{
		InputField field = service.getInputFieldSet(inputFieldSetId).getInputField(inputFieldId);
		field.setValidatePrimitiveAssignment(Field.ASSIGNMENT_SCRIPT);
		field.setValidatePrimitiveScript(script);
	}

	/**
	 * Add a validate mapping to a field's primitive value
	 * 
	 * @param inputFieldSetId
	 *            - input field set id
	 * @param inputFieldId
	 *            - input field id
	 * 
	 * @throws EQException
	 */
	public void addValidateMappingPrimitiveFieldCode(String inputFieldSetId, String inputFieldId) throws EQException
	{
		InputField field = service.getInputFieldSet(inputFieldSetId).getInputField(inputFieldId);
		field.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		field.setValidatePrimitiveScript("");
	}

	/**
	 * Add a validate mapping to a field's output value
	 * 
	 * @param inputFieldSetId
	 *            - input field set id
	 * @param inputFieldId
	 *            - input field id
	 * @param script
	 *            - the script expression
	 * 
	 * @throws EQException
	 */
	public void addValidateMappingOutputFieldScript(String inputFieldSetId, String inputFieldId, String script) throws EQException
	{
		InputField field = service.getInputFieldSet(inputFieldSetId).getInputField(inputFieldId);
		field.setValidateOutputAssignment(Field.ASSIGNMENT_SCRIPT);
		field.setValidateOutputScript(script);
	}

	/**
	 * Add a validate mapping to a field's output value
	 * 
	 * @param inputFieldSetId
	 *            - input field set id
	 * @param inputFieldId
	 *            - input field id
	 * 
	 * @throws EQException
	 */
	public void addValidateMappingOutputFieldCode(String inputFieldSetId, String inputFieldId) throws EQException
	{
		InputField field = service.getInputFieldSet(inputFieldSetId).getInputField(inputFieldId);
		field.setValidateOutputAssignment(Field.ASSIGNMENT_CODE);
		field.setValidateOutputScript("");
	}

	/**
	 * Add a validate mapping to a work field
	 * 
	 * @param workFieldId
	 *            - work field id
	 * @param script
	 *            - the script expression
	 * 
	 * @throws EQException
	 */
	public void addValidateMappingWorkFieldScript(String workFieldId, String script) throws EQException
	{
		WorkField field = service.getWorkFields().get(workFieldId);
		field.setValidateAssignment(Field.ASSIGNMENT_SCRIPT);
		field.setValidateScript(script);
	}

	/**
	 * Add a validate mapping to a work field
	 * 
	 * @param workFieldId
	 *            - work field id
	 * 
	 * @throws EQException
	 */
	public void addValidateMappingWorkFieldCode(String workFieldId) throws EQException
	{
		WorkField field = service.getWorkFields().get(workFieldId);
		field.setValidateAssignment(Field.ASSIGNMENT_CODE);
		field.setValidateScript("");
	}

	/**
	 * Add an update mapping from a display input field to the update API field
	 * 
	 * @param fromInputFieldSet
	 *            - from display input field set
	 * @param fromInputField
	 *            - from display input field
	 * @param toAPIFieldSet
	 *            - to API input field set
	 * @param toAPIField
	 *            - to API input field
	 * 
	 * @throws EQException
	 */

	public void addUpdateMapping(String fromInputFieldSet, String fromInputField, String toAPIFieldSet, String toAPIField)
					throws EQException
	{
		// construct the mapping from
		String mapFrom = MappingToolbox.getFullInputFieldPath(fromInputFieldSet, fromInputField);

		// construct the mapping to
		String mapTo = MappingToolbox.getFullUpdateFieldPath(toAPIFieldSet, toAPIField);

		service.addUpdateMapping(mapFrom, mapTo);
	}

	/**
	 * Add a update mapping to a update field via script
	 * 
	 * @param apiFieldSetId
	 *            - the load API field set id
	 * @param apiFieldId
	 *            - the API field id
	 * @param script
	 *            - the script expression
	 * 
	 * @throws EQException
	 */
	public void addUpdateMappingScript(String apiFieldSetId, String apiFieldId, String script) throws EQException
	{
		APIField apiField = service.getUpdateAPI(apiFieldSetId).getAPIField(apiFieldId);
		apiField.setUpdateAssignment(Field.ASSIGNMENT_SCRIPT);
		apiField.setUpdateScript(script);
	}

	/**
	 * Add a update mapping to a update field via code
	 * 
	 * @param apiFieldSetId
	 *            - the load API field set id
	 * @param apiFieldId
	 *            - the API field id
	 * 
	 * @throws EQException
	 */
	public void addUpdateMappingCode(String apiFieldSetId, String apiFieldId) throws EQException
	{
		APIField apiField = service.getUpdateAPI(apiFieldSetId).getAPIField(apiFieldId);
		apiField.setUpdateAssignment(Field.ASSIGNMENT_CODE);
		apiField.setUpdateScript("");
	}

	/**
	 * Removes the mapping to an update API field
	 * 
	 * @param toAPIFieldSet
	 *            - to API input field set
	 * @param toAPIField
	 *            - to API input field
	 */
	public void removeUpdateMapping(String toAPIFieldSet, String toAPIField)
	{
		// construct the mapping to
		String mapTo = MappingToolbox.getFullUpdateFieldPath(toAPIFieldSet, toAPIField);

		try
		{
			service.removeUpdateMapping(mapTo);
		}
		catch (EQException e)
		{
			throw new EQRuntimeException(e);
		}
	}

	/**
	 * Add a load mapping from a display input field to the load API field
	 * 
	 * @param fromInputFieldSet
	 *            - from display input field set
	 * @param fromInputField
	 *            - from display input field
	 * @param toAPIFieldSet
	 *            - to API input field set
	 * @param toAPIField
	 *            - to API input field
	 * 
	 * @throws EQException
	 */
	public void addLoadMappingToLoad(String fromInputFieldSet, String fromInputField, String toInputFieldSet, String toAPIFieldSet,
					String toAPIField) throws EQException
	{
		// construct the mapping from
		String mapFrom = MappingToolbox.getFullInputFieldPath(fromInputFieldSet, fromInputField);

		// construct the mapping to
		String mapTo = MappingToolbox.getFullLoadFieldPath(toInputFieldSet, toAPIFieldSet, toAPIField);

		service.addLoadMapping(mapFrom, mapTo);
	}

	/**
	 * Removes a load mapping to a load API field
	 * 
	 * @param toAPIFieldSet
	 *            - to API input field set
	 * @param toAPIField
	 *            - to API input field
	 */
	public void removeLoadMappingToLoad(String toInputFieldSet, String toAPIFieldSet, String toAPIField)
	{
		// construct the mapping to path
		String mapTo = MappingToolbox.getFullLoadFieldPath(toInputFieldSet, toAPIFieldSet, toAPIField);

		try
		{
			service.removeLoadMapping(mapTo);
		}
		catch (EQException e)
		{
			throw new EQRuntimeException(e);
		}
	}

	/**
	 * Add a load mapping from a display input field to the load API field
	 * 
	 * @param fromInputFieldSet
	 *            - from display input field set
	 * @param fromInputField
	 *            - from display input field
	 * @param toInputFieldSet
	 *            - to input field set
	 * @param toPvFieldSet
	 *            - to pv field set
	 * @param toPvField
	 *            - to pv
	 * 
	 * @throws EQException
	 */
	public void addLoadMappingToPVLoad(String fromInputFieldSet, String fromInputField, String toInputFieldSet,
					String toPvFieldSet, String toPvField) throws EQException
	{
		// construct the mapping from
		String mapFrom = MappingToolbox.getFullInputFieldPath(fromInputFieldSet, fromInputField);

		// construct the mapping to
		String mapTo = MappingToolbox.getPVFieldLoadAPI(toInputFieldSet, toPvFieldSet, toPvField);

		service.addLoadMapping(mapFrom, mapTo);
	}

	/**
	 * Add a load mapping from the load API field to a display input field
	 * 
	 * @param fromAPIFieldSet
	 *            - to API input field set
	 * @param fromAPIField
	 *            - to API input field
	 * @param toInputFieldSet
	 *            - from display input field set
	 * @param toInputField
	 *            - from display input field
	 * @param type
	 *            - identify whether it is the primitive, input or output
	 * 
	 * @throws EQException
	 */

	public void addLoadMappingFromLoad(String fromInputFieldSet, String fromAPIFieldSet, String fromAPIField,
					String toInputFieldSet, String toInputField, int type) throws EQException
	{
		// construct the mapping from
		String mapFrom = MappingToolbox.getFullLoadFieldPath(fromInputFieldSet, fromAPIFieldSet, fromAPIField);

		// construct the mapping to
		String mapTo = MappingToolbox.getFullInputFieldPath(toInputFieldSet, toInputField, type);

		service.addLoadMapping(mapFrom, mapTo);
	}

	/**
	 * Add a load mapping to a load field via script
	 * 
	 * @param apiFieldSetId
	 *            - from display input field set
	 * @param apiFieldId
	 *            - from display input field
	 * @param script
	 *            - the script expression
	 * 
	 * @throws EQException
	 */
	public void addLoadMappingScript(String inputFieldId, String apiFieldSetId, String apiFieldId, String script)
					throws EQException
	{
		APIField apiField = service.getInputFieldSet(inputFieldId).getLoadAPI(apiFieldSetId).getAPIField(apiFieldId);
		apiField.setLoadAssignment(Field.ASSIGNMENT_SCRIPT);
		apiField.setLoadScript(script);
	}

	/**
	 * Add a load mapping to a load field via code
	 * 
	 * @param inputFieldId
	 *            - the input field id
	 * @param apiFieldSetId
	 *            - the load API field set id
	 * @param apiFieldId
	 *            - the API field id
	 * 
	 * @throws EQException
	 */
	public void addLoadMappingCode(String inputFieldId, String apiFieldSetId, String apiFieldId) throws EQException
	{
		APIField apiField = service.getInputFieldSet(inputFieldId).getLoadAPI(apiFieldSetId).getAPIField(apiFieldId);
		apiField.setLoadAssignment(Field.ASSIGNMENT_CODE);
		apiField.setLoadScript("");
	}

	/**
	 * @return String containing the id of the function
	 */
	public String getFunctionId()
	{
		return service.getId();
	}

	/**
	 * Return the function definition
	 * 
	 * @return the function definition
	 */
	public Function getFunction()
	{
		return service;
	}

	/**
	 * Add a context mapping
	 * 
	 * @param field
	 *            - the field name
	 * @param contextField
	 *            - the context field name
	 */
	public void addContextMapping(String field, String contextField)
	{
		Mapping mapping = new Mapping(field, contextField);
		service.getContextMappings().add(mapping);
	}

}
