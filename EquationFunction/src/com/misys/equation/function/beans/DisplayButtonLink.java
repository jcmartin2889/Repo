package com.misys.equation.function.beans;

import com.misys.equation.function.beans.valid.MessageContainer;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.problems.Message;
import com.misys.equation.problems.ProblemLocation;

/**
 * This class represents all Button or Link properties
 * 
 */
public class DisplayButtonLink extends Element implements IDisplayItem
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DisplayButtonLink.java 12396 2011-12-12 01:55:51Z williae1 $";

	/** The prefix that is automatically prepended to all DisplayButtonLink IDs to distinguish them from other IDs */
	public static final String BUTTON_LINK_ID_PREFIX = "-";

	/** Display as button */
	public static final int DISP_BUTTON = 1;
	/** Display as hyperlink */
	public static final int DISP_LINK = 2;
	/** Display as image */
	public static final int DISP_IMAGE = 3;

	/** Action when link or button is clicked is a URL */
	public static final int ACTION_URL = 1;
	/** Action when link or button is clicked is a Window's command */
	public static final int ACTION_COMMAND = 2;

	/** Action is generated via script or expression, "0" and "1" are reserved for Yes and No but currently not used */
	public static final String COMMAND_SCRIPT = "2";
	/** Action is generated via Java code */
	public static final String COMMAND_CODE = "3";

	/*
	 * Display field attributes
	 */
	private int labelPosition;
	private String labelScriptRuntime;
	private boolean keepWithPrevious;
	private String visible;
	private String visibleExpression;
	private String protectedStatus;
	private String protectedExpression;
	private String displayStyleLabel;
	private int displayAs;
	private int action;
	private String command;
	private String commandExpression;

	/**
	 * Construct a new, empty instance
	 */
	public DisplayButtonLink()
	{
		super();
		commonInitialization();
	}

	/**
	 * Construct a new instance using the given details
	 * 
	 * @param id
	 *            - field id
	 * @param label
	 *            - field label
	 * @param description
	 *            - field description
	 */
	public DisplayButtonLink(String id, String label, String description)
	{
		super(id, label, description);
		commonInitialization();
	}

	/**
	 * Construct a new empty field using the id, label and description of the supplied Element (typically an InputField)
	 * 
	 * @param element
	 *            - the element to copy from
	 */
	public DisplayButtonLink(Element element)
	{
		super(element);
		commonInitialization();
	}

	/**
	 * Construct a new empty field using the id, label and description of the supplied Element (typically an InputField) *
	 * 
	 * @param field
	 *            - the field to copy from
	 */
	public DisplayButtonLink(DisplayButtonLink field)
	{
		super(field);
		labelPosition = field.getLabelPosition();
		labelScriptRuntime = field.getLabelScriptRuntime();
		keepWithPrevious = field.isKeepWithPrevious();
		visible = field.getVisible();
		visibleExpression = field.getVisibleExpression();
		protectedStatus = field.getProtected();
		protectedExpression = field.getProtectedExpression();
		displayStyleLabel = field.getDisplayStyleLabel();
		displayAs = field.getDisplayAs();
		action = field.getAction();
		command = field.getCommand();
		commandExpression = field.getCommandExpression();
	}

	/**
	 * Common field initialization called by non-copy Constructors.
	 * <p>
	 * When new member variables are added, initialisation code must be added to both this method and the copy constructor.
	 */
	private void commonInitialization()
	{
		labelPosition = DisplayAttributes.FIELD_LABEL_LEFT;
		labelScriptRuntime = "";
		keepWithPrevious = false;
		visible = DisplayAttributes.VISIBLE_YES;
		visibleExpression = "";
		protectedStatus = DisplayAttributes.PROTECTED_NO;
		protectedExpression = "";
		displayStyleLabel = "";
		displayAs = DISP_BUTTON;
		action = ACTION_URL;
		command = COMMAND_SCRIPT;
		commandExpression = "";
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
		ProblemLocation problemLocation = new ProblemLocation(DisplayButtonLink.class.getSimpleName(), getId());

		DisplayAttributesSet set = rtvParentSet();
		Function service = null;
		if (set != null)
		{
			Layout layout = set.rtvLayout();
			service = layout.service();
		}

		if (service != null)
		{
			// Label and Label Script are both provided
			if (getLabel().trim().length() > 0 && !getLabel().trim().equals(Element.DEFAULT_TEXT_VALUE)
							&& getLabelScriptRuntime().trim().length() > 0)
			{
				messageContainer.addMessage(LanguageResources.getString("Language.LabelScriptLabelTextExclusive"),
								Message.SEVERITY_ERROR, problemLocation);
				return true;
			}

			// Label Script expression
			if (getLabelScriptRuntime().trim().length() > 0)
			{
				ValidationHelper.validateStringELExpression(getLabelScriptRuntime(), this, messageContainer,
								ValidationHelper.StringELType.LABEL_SCRIPT_EXPRESSION, problemLocation, null);
			}

			// Visible EL expression
			if (!messageContainer.hasErrorMessages() && DisplayAttributes.VISIBLE_SCRIPT.equals(getVisible()))
			{
				ValidationHelper.validateBooleanELExpression(getVisibleExpression(), this, messageContainer,
								ValidationHelper.BooleanELType.VISIBLE_EXPRESSION, problemLocation);
			}
			// Protected EL expression:
			if (!messageContainer.hasErrorMessages() && DisplayAttributes.PROTECTED_SCRIPT.equals(getProtected()))
			{
				ValidationHelper.validateBooleanELExpression(getProtectedExpression(), this, messageContainer,
								ValidationHelper.BooleanELType.PROTECTED_EXPRESSION, problemLocation);
			}

			// Style
			if (!messageContainer.hasErrorMessages() && getDisplayStyleLabel().trim().length() > 0)
			{
				ValidationHelper.validateStringELExpression(getDisplayStyleLabel(), this, messageContainer,
								ValidationHelper.StringELType.DISPLAYSTYLE_FIELDLABEL, problemLocation, null);
			}

			// Command and parameter expression
			if (!messageContainer.hasErrorMessages() && DisplayButtonLink.COMMAND_SCRIPT.equals(getCommand()))
			{
				ValidationHelper.validateStringELExpression(getCommandExpression(), this, messageContainer,
								ValidationHelper.StringELType.COMMAND_AND_PARMS_EXPRESS, problemLocation, null);
			}
		}

		return !messageContainer.hasErrorMessages();
	}

	/**
	 * Return the DisplayAttributesSet that contains this DisplayGroup
	 * 
	 * @return the DisplayAttributesSet (may be null for a new DisplayGroup)
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
	 * @see DisplayAttributes#FIELD_LABEL_LEFT
	 * 
	 * @return the label position. The default is FIELD_LABEL_LEFT
	 */
	public int getLabelPosition()
	{
		return labelPosition;
	}

	/**
	 * @param labelPosition
	 *            the label position to set
	 */
	public void setLabelPosition(int labelPosition)
	{
		this.labelPosition = labelPosition;
	}

	/**
	 * @param labelScriptRuntime
	 *            the label script to set
	 */
	public void setLabelScriptRuntime(String labelScriptRuntime)
	{
		this.labelScriptRuntime = labelScriptRuntime;
	}

	/**
	 * @return labelScriptRuntime
	 */
	public String getLabelScriptRuntime()
	{
		return labelScriptRuntime;
	}

	/**
	 * @return the keepWithPrevious
	 */
	public boolean isKeepWithPrevious()
	{
		return keepWithPrevious;
	}

	/**
	 * @param keepWithPrevious
	 *            the keepWithPrevious to set
	 */
	public void setKeepWithPrevious(boolean keepWithPrevious)
	{
		this.keepWithPrevious = keepWithPrevious;
	}

	/**
	 * @return the visibility
	 */
	public String getVisible()
	{
		return visible;
	}

	/**
	 * @param visible
	 *            the visibility to set
	 */
	public void setVisible(String visible)
	{
		this.visible = visible;
	}

	/**
	 * @return the visibleExpression
	 */
	public String getVisibleExpression()
	{
		return visibleExpression;
	}

	/**
	 * @param visibleExpression
	 *            the visible expression to set
	 */
	public void setVisibleExpression(String visibleExpression)
	{
		this.visibleExpression = visibleExpression;
	}

	/**
	 * @return the protect
	 */
	public String getProtected()
	{
		return protectedStatus;
	}

	/**
	 * @param protectedStatus
	 *            the protect to set
	 */
	public void setProtected(String protectedStatus)
	{
		this.protectedStatus = protectedStatus;
	}

	/**
	 * @return the protectedExpression
	 */
	public String getProtectedExpression()
	{
		return protectedExpression;
	}

	/**
	 * @param protectedExpression
	 *            the protected expression to set
	 */
	public void setProtectedExpression(String protectedExpression)
	{
		this.protectedExpression = protectedExpression;
	}

	/**
	 * @return the displayStyleLabel
	 */
	public String getDisplayStyleLabel()
	{
		return displayStyleLabel;
	}

	/**
	 * @param displayStyleLabel
	 *            the displayStyle for the Label to set
	 */
	public void setDisplayStyleLabel(String displayStyleLabel)
	{
		this.displayStyleLabel = displayStyleLabel;
	}

	/**
	 * @return the displayAs
	 */
	public int getDisplayAs()
	{
		return displayAs;
	}

	/**
	 * @param displayAs
	 *            the displayAs to set
	 */
	public void setDisplayAs(int displayAs)
	{
		this.displayAs = displayAs;
	}

	/**
	 * @return the action
	 */
	public int getAction()
	{
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(int action)
	{
		this.action = action;
	}

	/**
	 * @return the command
	 */
	public String getCommand()
	{
		return command;
	}

	/**
	 * @param command
	 *            the command to set
	 */
	public void setCommand(String command)
	{
		this.command = command;
	}

	/**
	 * @return the commandExpression
	 */
	public String getCommandExpression()
	{
		return commandExpression;
	}

	/**
	 * @param commandExpression
	 *            the commandExpression to set
	 */
	public void setCommandExpression(String commandExpression)
	{
		this.commandExpression = commandExpression;
	}

}
