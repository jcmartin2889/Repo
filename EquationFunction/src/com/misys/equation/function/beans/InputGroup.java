package com.misys.equation.function.beans;

import com.misys.equation.function.beans.valid.MessageContainer;

/**
 * Represents a Group of InputItems.
 * <p>
 * An InputGroup is used to anchor repeating fields
 */
public class InputGroup extends Element
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: InputGroup.java 17190 2013-09-03 11:49:59Z Lima12 $";

	/** Default label/description */
	public static final String DEFAULT_LABEL_VALUE = "Repeating data";

	/**
	 * Construct a new empty InputGroup
	 */
	public InputGroup()
	{
		super();
	}

	/**
	 * Construct a new InputGroup with the specified Id and the default label
	 * 
	 * @param id
	 */
	public InputGroup(String id)
	{
		super();
		this.setId(id);
		this.setLabel(DEFAULT_LABEL_VALUE);
	}

	/**
	 * Construct a new DisplayAttributesSet with the supplied details
	 * 
	 * @param id
	 *            - identifies the set
	 * @param label
	 *            - label of the set
	 * @param description
	 *            - description of the set
	 */
	public InputGroup(String id, String label, String description)
	{
		super(id, label, description);
	}

	/**
	 * Construct a new DisplayAttributesSet, copying the id, label and description from the supplied Element
	 * 
	 * @param element
	 *            - An Element
	 */
	public InputGroup(Element element)
	{
		super(element);
	}

	/**
	 * Called to validate the bean
	 * 
	 * @param messageContainer
	 *            A MessageContainer to add messages to
	 * @param subSet
	 *            A String which may indicate a subset of validation. May be null
	 * @param includeChildren
	 *            Whether any children of this bean should also be validated.
	 * 
	 * @return boolean (not used)
	 */
	public boolean validateBean(MessageContainer messageContainer, String subSet, boolean includeChildren)
	{
		return false;
	}

	/**
	 * Set the label to default label (DEFAULT LABEL + ID)
	 * 
	 * @return the new label
	 */
	public String defaultLabelBaseOnId()
	{
		setLabel(DEFAULT_LABEL_VALUE + " " + getId());
		return getLabel();
	}
}
