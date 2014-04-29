package com.misys.equation.function.beans;

import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;

/**
 * This class represents a field and contains all the attributes of the field
 * 
 */
public abstract class Element
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: Element.java 17190 2013-09-03 11:49:59Z Lima12 $";
	private final EquationLogger LOG = new EquationLogger(this.getClass());

	/** Default label/description */
	public static final String DEFAULT_TEXT_VALUE = "<none>";
	/** Indicates that a String property contains literal text (to be displayed without translating) */
	public static final String TEXT_VALUE_TEXT = "";
	/** Indicates that a String property contains a reusable reference to retrieve multi-language or looked-up text */
	public static final String TEXT_VALUE_REUSABLE_REFERENCE = "1";
	/** Indicates that a String property contains a reference to retrieve multi-language or looked-up text */
	public static final String TEXT_VALUE_REFERENCE = "2";
	/** Indicates that a String property contains a code to signify line */
	public static final String TEXT_VALUE_LINE = "LINE";
	/** Indicates that a String property contains a code to signify an empty line */
	public static final String TEXT_VALUE_EMPTY_LINE = "EMPTY_LINE";

	private String id;
	private String label;
	private String description;
	private Element parent;
	/** Indicates whether the label contains literal text, or is a code to retrieve the label text */
	private String labelType;
	/** Indicates whether the description contains literal text, or is a code used to retrieve the description text */
	private String descriptionType;

	private String labelTextOwner;
	private String descriptionTextOwner;

	/**
	 * Construct a new empty <code>Element</code>
	 */
	public Element()
	{
		super();
		id = "";
		label = DEFAULT_TEXT_VALUE;
		description = DEFAULT_TEXT_VALUE;
		labelType = TEXT_VALUE_TEXT;
		descriptionType = TEXT_VALUE_TEXT;
		labelTextOwner = "";
		descriptionTextOwner = "";
	}

	/**
	 * Construct a new <code>Element</code>
	 * 
	 * @param id
	 * @param label
	 * @param description
	 */
	public Element(String id, String label, String description)
	{
		super();
		this.id = id.trim();
		this.label = label.trim();
		this.description = description.trim();
		labelType = TEXT_VALUE_TEXT;
		descriptionType = TEXT_VALUE_TEXT;
		labelTextOwner = "";
		descriptionTextOwner = "";
	}

	/**
	 * Construct a new <code>Element</code>
	 * 
	 * @param id
	 * @param label
	 * @param description
	 * @param labelType
	 * @param descriptionType
	 */
	public Element(String id, String label, String description, String labelType, String descriptionType)
	{
		super();
		this.id = id.trim();
		this.label = label.trim();
		this.description = description.trim();
		this.labelType = labelType.trim();
		this.descriptionType = descriptionType.trim();
		labelTextOwner = "";
		descriptionTextOwner = "";
	}

	/**
	 * Copy constructor
	 * 
	 * @param element
	 */
	public Element(Element element)
	{
		if (element.getLabel() == null || element.getLabel().trim().equals(""))
		{
			label = DEFAULT_TEXT_VALUE;
			labelType = TEXT_VALUE_TEXT;
		}
		else
		{
			label = element.getLabel();
			labelType = element.getLabelType();
		}
		if (element.getDescription() == null || element.getDescription().trim().equals(""))
		{
			description = DEFAULT_TEXT_VALUE;
			descriptionType = TEXT_VALUE_TEXT;
		}
		else
		{
			description = element.getDescription();
			descriptionType = element.getDescriptionType();
		}
		id = element.getId();
		if (element.hasParent())
		{
			parent = element.rtvParent();
		}
		if (element.getLabelTextOwner() != null)
		{
			if (!element.getLabelTextOwner().equalsIgnoreCase(""))
			{
				labelTextOwner = element.getLabelTextOwner();
			}
		}
		else
		{
			labelTextOwner = "";
		}

		if (element.getDescriptionTextOwner() != null)
		{
			if (!element.getDescriptionTextOwner().equalsIgnoreCase(""))
			{
				descriptionTextOwner = element.getDescriptionTextOwner();
			}
		}
		else
		{
			descriptionTextOwner = "";
		}

	}
	/**
	 * Returns the label of the <code>Element</code>
	 * 
	 * @return the label of the <code>Element</code>
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * Sets the label of the <code>Element</code>
	 * 
	 * @param label
	 */
	public void setLabel(String label)
	{
		this.label = label;
	}

	/**
	 * Returns the ID of the <code>Element</code>
	 * 
	 * @return the ID of the <code>Element</code>
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Sets the id of the <code>Element</code>
	 * 
	 * @param id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Returns the description of the <code>Element</code>
	 * 
	 * @return the description of the <code>Element</code>
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description of the <code>Element</code>
	 * 
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Retrieves the path of this <code>Element</code>
	 * 
	 * @return a <code>String</code> of the path.
	 */
	public String rtvPath()
	{
		StringBuffer currentPath = new StringBuffer("");
		Element currentParent = this.rtvParent();
		// Note that WorkFields have the Function as their parent, but
		// paths should not include the parent
		while (currentParent != null && !(currentParent instanceof Function))
		{
			currentPath.insert(0, currentParent.getClass().getSimpleName() + "." + currentParent.getId() + ".");
			if (currentParent.hasParent())
			{
				currentParent = currentParent.rtvParent();
			}
			else
			{
				currentPath.append(this.getClass().getSimpleName() + "." + this.getId());
				return currentPath.toString();
			}
		}
		currentPath.append(this.getClass().getSimpleName() + "." + this.getId());
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Path for " + this.getClass().getSimpleName() + " with Id " + this.getId() + " is [" + currentPath.toString()
							+ "]");
		}
		return currentPath.toString();
	}

	/**
	 * Get the parent <code>Element</code>
	 * 
	 * @return the parent of this <code>Element</code>
	 */
	public Element rtvParent()
	{
		return parent;
	}

	/**
	 * Set the parent <code>Element</code>
	 * 
	 * @param the
	 *            new parent <code>Element</code>
	 */
	public void setParent(Element parent)
	{
		this.parent = parent;
	}

	/**
	 * Does the <code>Element</code> have a parent
	 * 
	 * @return true if the parent is not null, otherwise false
	 */
	public boolean hasParent()
	{
		Element parent = this.rtvParent();
		return parent != null;
	}

	/**
	 * Indicates whether the label attribute is literal text or is a code to retrieve multi-language text
	 * 
	 * @return constant <code>TEXT_VALUE_TEXT</code> to indicate that the label is fixed text or either constant
	 *         <code>TEXT_VALUE_REUSABLE_REFERENCE</code> or <code>TEXT_VALUE_REFERENCE</code> to indicate that the label is an
	 *         HBXPF key.
	 */
	public String getLabelType()
	{
		return labelType;
	}

	/**
	 * Sets whether the label attribute is literal text or is a code to retrieve multi-language text
	 * 
	 * @param labelType
	 *            a <code>String</code>, which should be <code>TEXT_VALUE_TEXT</code>, <code>TEXT_VALUE_REUSABLE_REFERENCE</code> or
	 *            <code>TEXT_VALUE_REFERENCE</code>
	 */
	public void setLabelType(String labelType)
	{
		this.labelType = labelType;
	}

	/**
	 * Indicates whether the description is literal text or is a code to retrieve multi-language text
	 * 
	 * @return constant <code>TEXT_VALUE_TEXT</code> to indicate that the description is fixed text or either constant
	 *         <code>TEXT_VALUE_REUSABLE_REFERENCE</code> or <code>TEXT_VALUE_REFERENCE</code> to indicate that the description is
	 *         an HBXPF key.
	 */
	public String getDescriptionType()
	{
		return descriptionType;
	}

	/**
	 * Sets whether the description is literal text or is a code to retrieve multi-language text
	 * 
	 * @param descriptionType
	 *            a <code>String</code>, which should be <code>TEXT_VALUE_TEXT</code>, <code>TEXT_VALUE_REUSABLE_REFERENCE</code> or
	 *            <code>TEXT_VALUE_REFERENCE</code>
	 */
	public void setDescriptionType(String descriptionType)
	{
		this.descriptionType = descriptionType;
	}

	/**
	 * Returns the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		return id + Toolbox.TEXT_DELIMITER + label + Toolbox.TEXT_DELIMITER + description;
	}

	/**
	 * Return the label in user's language
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * 
	 * @return the label in user's language
	 */
	public String rtvLabel(EquationUser eqUser)
	{
		return FunctionRuntimeToolbox.getHBXText(eqUser, getLabelTextOwner(), TextBean.TYPE_LABEL, getLabel(), getLabelType(),
						FunctionRuntimeToolbox.getServiceBaseLanguage(this, getLabelType()));
	}
	/**
	 * Return the description in user's language
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * 
	 * @return the description in user's language
	 */
	public String rtvDescription(EquationUser eqUser)
	{
		return FunctionRuntimeToolbox.getHBXText(eqUser, getDescriptionTextOwner(), TextBean.TYPE_DESCRIPTION, getDescription(),
						getDescriptionType(), FunctionRuntimeToolbox.getServiceBaseLanguage(this, getDescriptionType()));
	}
	/**
	 * Returns the Id, trimmed of any special characters
	 * <p>
	 * This Id is therefore suitable for messages or determining class names
	 * 
	 * @return the Id
	 */
	public String rtvBareId()
	{
		String id = getId();
		if (this instanceof DisplayGroup && id.startsWith(DisplayGroup.GROUP_ID_PREFIX))
		{
			id = id.substring(DisplayGroup.GROUP_ID_PREFIX.length());
		}
		else if (this instanceof DisplayLabel && id.startsWith(DisplayLabel.LABEL_ID_PREFIX))
		{
			id = id.substring(DisplayLabel.LABEL_ID_PREFIX.length());
		}
		else if (this instanceof DisplayButtonLink && id.startsWith(DisplayButtonLink.BUTTON_LINK_ID_PREFIX))
		{
			id = id.substring(DisplayButtonLink.BUTTON_LINK_ID_PREFIX.length());
		}
		return id;
	}

	/**
	 * Get the label text owner
	 * 
	 * @return the text owner for the label
	 */
	public String getLabelTextOwner()
	{
		return labelTextOwner;
	}

	/**
	 * Set the label text owner
	 * 
	 * @param labelTextOwner
	 *            the label text owner to set
	 */
	public void setLabelTextOwner(String labelTextOwner)
	{
		this.labelTextOwner = labelTextOwner;
	}

	/**
	 * Get the description text owner
	 * 
	 * @return the text owner for the description
	 */
	public String getDescriptionTextOwner()
	{
		return descriptionTextOwner;

	}

	/**
	 * Set the description text owner
	 * 
	 * @param descriptionTextOwner
	 *            the description text owner to set
	 */
	public void setDescriptionTextOwner(String descriptionTextOwner)
	{
		this.descriptionTextOwner = descriptionTextOwner;
	}

	/**
	 * Return the label. If contain the default text value, then return empty string
	 * 
	 * @return the label
	 */
	public String rtvLabel()
	{
		if (label.equals(DEFAULT_TEXT_VALUE))
		{
			return "";
		}
		else
		{
			return label;
		}
	}
	/**
	 * Return the id and dash separated label
	 * 
	 * @return the id and dash separated label
	 */
	public String rtvIdAndLabel()
	{
		return getId() + Toolbox.DASH_DELIMITER + rtvLabel();
	}
}
