package com.misys.equation.function.beans;

import com.misys.equation.function.beans.valid.MessageContainer;
import com.misys.equation.problems.ProblemLocation;

/**
 * This class represents a label to be displayed in the Equation Desktop.
 * <p>
 * Labels are defined in the Equation Desktop Editor, to allow the user to create fixed text.<br>
 * Note that empty (blank) labels are legal and can be used to provide spacing.
 */
public class DisplayLabel extends Element implements IDisplayItem
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DisplayLabel.java 10552 2011-02-25 11:17:20Z capilid1 $";

	/** The prefix that is automatically prepended to all DisplayLabel IDs to distinguish them from other IDs */
	public static final String LABEL_ID_PREFIX = "-";

	/** Indicates the field is not visible */
	public static final String VISIBLE_NO = "0";
	/** Indicates the field is visible (default) */
	public static final String VISIBLE_YES = "1";
	/** Indicates that there is an EL Script to determine visibility */
	public static final String VISIBLE_SCRIPT = "2";
	/** Indicates that there is Java exit code to determine visibility */
	public static final String VISIBLE_CODE = "3";

	private String visible;
	private String visibleExpression;

	/** Name of the CSS style */
	private String displayStyle;

	/**
	 * Construct a new, empty instance
	 */
	public DisplayLabel()
	{
		super();
		commonInitialisation();
	}

	/**
	 * Construct a new instance using the given details
	 * 
	 * @param id
	 *            identifier
	 * @param label
	 *            label
	 * @param description
	 *            description (not relevant to this class)
	 */
	public DisplayLabel(String id, String label, String description)
	{
		super(id, label, description);
		commonInitialisation();
	}

	/**
	 * Construct a new instance from another display label
	 * 
	 * @param id
	 *            identifier
	 * @param label
	 *            label
	 * @param description
	 *            description (not relevant to this class)
	 */
	public DisplayLabel(DisplayLabel displayLabel)
	{
		super(displayLabel);
		displayStyle = displayLabel.displayStyle;
		this.visible = displayLabel.visible;
	}
	/**
	 * Common field initialisation called by non-copy Constructors.
	 * <p>
	 * When new member variables are added, initialisation code must be added to both this method and the copy constructor.
	 */
	private void commonInitialisation()
	{
		displayStyle = "";
		this.visible = DisplayLabel.VISIBLE_YES;
	}
	/**
	 * Return the display style of the field
	 * 
	 * @return the display style of the field
	 */
	public String getDisplayStyle()
	{
		return displayStyle;
	}

	/**
	 * Set the display style of the field
	 * 
	 * @param displayStyle
	 *            - the display style of the field
	 */
	public void setDisplayStyle(String displayStyle)
	{
		this.displayStyle = displayStyle;
	}

	/**
	 * Validate the bean
	 * 
	 * @param messageContainer
	 * @param includeChildren
	 * 
	 * @return
	 */
	public boolean validateBean(MessageContainer messageContainer, String subSet, boolean includeChildren)
	{
		ProblemLocation problemLocation = new ProblemLocation(DisplayLabel.class.getSimpleName(), getId());
		// Validate the Label Visible EL expression:
		if (DisplayLabel.VISIBLE_SCRIPT.equals(visible))
		{
			ValidationHelper.validateBooleanELExpression(visibleExpression, this, messageContainer,
							ValidationHelper.BooleanELType.DISPLAY_LABEL_VISIBLE_EXPRESSION, problemLocation);
		}

		return !messageContainer.hasErrorMessages();
	}

	/**
	 * Return the DisplayAttributesSet that contains this DisplayLabel
	 * 
	 * @return the DisplayAttributesSet (may be null for a new DisplayLabel)
	 */
	public DisplayAttributesSet rtvParentSet()
	{
		Element parent = rtvParent();
		while (parent != null && !(parent instanceof DisplayAttributesSet))
		{
			parent = parent.rtvParent();
		}
		return parent == null ? null : (DisplayAttributesSet) parent;
	}

	/**
	 * Gets the label visible status
	 * <p>
	 * 
	 * @return an <code>String</code> indicating how the visibility of the field will be determined at runtime. This will be one of
	 *         the following constants: VISIBLE_YES, VISIBLE_NO, VISIBLE_SCRIPT or VISIBLE_CODE.
	 */
	public String getVisible()
	{
		return visible;
	}

	/**
	 * Sets how the visibility of the label will be determined at run time.
	 * <p>
	 * 
	 * @param visible
	 *            an <code>int</code> indicating how the visibility is determined. Must be one of VISIBLE_STRING_YES,
	 *            VISIBLE_STRING_NO, VISIBLE_STRING_SCRIPT or VISIBLE_STRING_CODE.
	 */
	public void setVisible(String visible)
	{
		this.visible = visible;
	}

	/**
	 * Return the visible expression
	 * 
	 * @return the visible expression
	 */
	public String getVisibleExpression()
	{
		return visibleExpression;
	}

	/**
	 * Set the visible expression
	 * 
	 * @param visibleExpression
	 *            - the visible expression
	 */
	public void setVisibleExpression(String visibleExpression)
	{
		this.visibleExpression = visibleExpression;
	}

}
