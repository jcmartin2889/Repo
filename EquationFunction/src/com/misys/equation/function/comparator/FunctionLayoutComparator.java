package com.misys.equation.function.comparator;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.DisplayItemList;
import com.misys.equation.function.beans.Element;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.IDisplayItem;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.Layout;

public class FunctionLayoutComparator extends ElementComparator
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionLayoutComparator.java 14893 2012-11-08 13:14:58Z williae1 $";

	/**
	 * Constructor
	 */
	public FunctionLayoutComparator()
	{
	}

	/**
	 * Compare the Layout bean against its Function bean
	 * 
	 * @param layout
	 *            - the Layout bean
	 * @param function
	 *            - the Function bean
	 * 
	 * @return the list of different fields in the journal and in the function
	 * 
	 * @throws EQException
	 */
	public ElementDifference compare(Layout layout, Function function) throws EQException
	{
		ElementDifference properties = new ElementDifference(layout.getId(), "");
		compareFieldSets(properties, layout, function);

		// return the differences
		return properties;
	}

	/**
	 * Compare field set
	 * 
	 * @param properties
	 *            - the list of differences
	 * @param layout
	 *            - the Layout bean
	 * @param function
	 *            - the Function bean
	 */
	protected void compareFieldSets(ElementDifference properties, Layout layout, Function function)
	{
		// determine missing field sets
		compareNewFieldSets(properties, layout, function);

		// Make sure field sets matches
		for (DisplayAttributesSet displayAttributesSet : layout.getDisplayAttributesSets())
		{
			String id = displayAttributesSet.getId();
			ElementDifference elementDifference = new ElementDifference(id, "");

			// Field set in both Layout and Function
			if (function.containsInputFieldSet(id))
			{
				try
				{
					// compare the list of fields within the fieldset
					InputFieldSet inputFieldSet = function.getInputFieldSet(id);
					compareNewFields(elementDifference, displayAttributesSet, displayAttributesSet.getDisplayItems(), inputFieldSet);
					compareFields(elementDifference, displayAttributesSet, displayAttributesSet.getDisplayItems(), inputFieldSet);
				}
				catch (EQException e)
				{
					// do nothing, as this should not happen
				}
			}

			// Field set in Layout but not in Function
			else
			{
				elementDifference.addPropertyDifference(new PropertyDifference("", ElementComparatorToolbox.TEXT_MISSING,
								ElementComparatorToolbox.TEXT_NEW, PropertyDifference.FLAG_DELETED));
			}

			// any difference?
			if (elementDifference.isDiffer())
			{
				properties.addSubElementDifference(id, elementDifference);
			}

		}
	}

	/**
	 * Determine new field set in Function
	 * 
	 * @param properties
	 *            - the list of differences
	 * @param layout
	 *            - the Layout bean
	 * @param function
	 *            - the Function bean
	 */
	protected void compareNewFieldSets(ElementDifference properties, Layout layout, Function function)
	{
		// Determine new field set in Function (which does not exist in Layout)
		for (InputFieldSet inputFieldSet : function.getInputFieldSets())
		{
			String id = inputFieldSet.getId();
			DisplayAttributesSet displayAttributesSet = layout.getDisplayAttributesSet(id);
			if (displayAttributesSet == null)
			{
				ElementDifference elementDifference = new ElementDifference(id, "");
				elementDifference.addPropertyDifference(new PropertyDifference("", ElementComparatorToolbox.TEXT_NEW,
								ElementComparatorToolbox.TEXT_MISSING, PropertyDifference.FLAG_INSERTED));
				properties.addSubElementDifference(id, elementDifference);
			}
		}
	}

	/**
	 * Compare the fields in a field set
	 * 
	 * @param layout
	 *            - the Layout bean
	 * @param function
	 *            - the Function bean
	 */
	protected void compareFields(ElementDifference properties, DisplayAttributesSet displayAttributesSet,
					DisplayItemList displayItems, InputFieldSet inputFieldSet)
	{
		// Determine new field set in Function (which does not exist in Layout)
		for (IDisplayItem displayItem : displayItems)
		{
			if (displayItem instanceof DisplayAttributes)
			{
				DisplayAttributes displayAttribute = (DisplayAttributes) displayItem;
				String fieldName = displayAttribute.getId();
				ElementDifference elementDifference = new ElementDifference(fieldName, "");

				// Field in both Layout and Function
				if (inputFieldSet.containsField(fieldName))
				{
					try
					{
						InputField inputField = inputFieldSet.getInputField(fieldName);
						elementDifference = compare(displayAttribute, inputField);
					}
					catch (EQException e)
					{
					}
				}

				// Field in Layout, but not in Function
				else
				{
					elementDifference.addPropertyDifference(new PropertyDifference("", ElementComparatorToolbox.TEXT_MISSING,
									ElementComparatorToolbox.TEXT_NEW, PropertyDifference.FLAG_DELETED));
				}

				// any difference?
				if (elementDifference.isDiffer())
				{
					properties.addSubElementDifference(fieldName, elementDifference);
				}
			}

			else if (displayItem instanceof DisplayGroup)
			{
				compareFields(properties, displayAttributesSet, ((DisplayGroup) displayItem).getDisplayItems(), inputFieldSet);
			}
		}
	}

	/**
	 * Compare the fields in a field set
	 * 
	 * @param layout
	 *            - the Layout bean
	 * @param function
	 *            - the Function bean
	 */
	protected void compareNewFields(ElementDifference properties, DisplayAttributesSet displayAttributesSet,
					DisplayItemList displayItems, InputFieldSet inputFieldSet)
	{
		for (InputField inputField : inputFieldSet.getInputFields())
		{
			String fieldName = inputField.getId();

			DisplayAttributes displayAttribute = displayAttributesSet.rtvDisplayAttributeFromDisplayGroup(displayItems, fieldName);
			if (displayAttribute == null)
			{
				ElementDifference elementDifference = new ElementDifference(fieldName, "");
				elementDifference.addPropertyDifference(new PropertyDifference("", ElementComparatorToolbox.TEXT_NEW,
								ElementComparatorToolbox.TEXT_MISSING, PropertyDifference.FLAG_INSERTED));
				properties.addSubElementDifference(fieldName, elementDifference);
			}
		}
	}

	/**
	 * Compare the fields in the Layout and the the Function
	 * 
	 * @param displayAttribute
	 *            - the field in the layout
	 * @param inputField
	 *            - the field in the function
	 * 
	 * @return the differences
	 */
	protected ElementDifference compare(DisplayAttributes displayAttribute, InputField inputField)
	{
		ElementDifference properties = new ElementDifference(inputField.getId(), "");

		// Input field is a key field, then make sure it is in the proper group in the layout
		Element parent = displayAttribute.rtvParent();
		if (inputField.isKey())
		{
			boolean problem = true;
			if (parent instanceof DisplayGroup)
			{
				problem = !parent.getId().equals(DisplayGroup.FULL_KEY_GROUP_ID);
			}
			if (problem)
			{
				Element newParent = parent.rtvParent();
				if (newParent != null)
				{
					problem = !newParent.getId().equals(DisplayGroup.FULL_KEY_GROUP_ID);
				}
			}
			if (problem)
			{

				PropertyDifference pd = new PropertyDifference("Key", "Key", "Non-Key", PropertyDifference.FLAG_CHANGED);
				properties.addPropertyDifference(pd);
			}
		}
		else
		{
			boolean problem = false;
			if (parent instanceof DisplayGroup)
			{
				problem = parent.getId().equals(DisplayGroup.FULL_KEY_GROUP_ID);
			}
			if (problem)
			{
				PropertyDifference pd = new PropertyDifference("Key", "Non-Key", "Key", PropertyDifference.FLAG_CHANGED);
				properties.addPropertyDifference(pd);
			}
		}

		// Input field is a repeating field, then make sure it is in the proper group in the layout
		if (InputField.isRepeating(inputField))
		{
			boolean problem = false;
			if (parent instanceof DisplayGroup)
			{
				DisplayGroup parentGroup = (DisplayGroup) parent;
				if (!parentGroup.getId().equals(DisplayGroup.GROUP_ID_PREFIX + inputField.getRepeatingGroup()))
				{
					PropertyDifference pd = new PropertyDifference("Input group", inputField.getRepeatingGroup(), parentGroup
									.getId(), PropertyDifference.FLAG_CHANGED);
					properties.addPropertyDifference(pd);
				}
			}
			else
			{
				PropertyDifference pd = new PropertyDifference("Input group", inputField.getRepeatingGroup(), "",
								PropertyDifference.FLAG_CHANGED);
				properties.addPropertyDifference(pd);
			}
		}

		return properties;
	}

}
