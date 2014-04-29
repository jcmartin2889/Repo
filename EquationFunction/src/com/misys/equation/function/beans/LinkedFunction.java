package com.misys.equation.function.beans;

public class LinkedFunction
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LinkedFunction.java 10992 2011-05-18 22:03:34Z perkinj1 $";

	/** the selection option */
	private String selectionOption;

	/** the option associated with the selection option */
	private String optionId;

	/** the option description */
	private String optionDescription;

	/** the list of fields to pass to the function */
	private String contextFields;

	/** the expression to determine wheter to ignore the linked function */
	private String ignoreLinkFuncExpression;

	/** global linked function flag - linked function not related to a record */
	private boolean globalLink;

	/** default linked function flag - determine whether this is the default linked function for double click event */
	private boolean defaultLink;

	/** Always display the linked function when it is executed */
	public static final int AUTO_EXECUTE_DSP_ALWAYS = 0;
	/**
	 * Attempt to execute the linked function without displaying it. However, if there are any messages, then the linked function
	 * will be displayed to the user
	 */
	public static final int AUTO_EXECUTE_DSP_MSG = 1;

	/**
	 * apply linked function flag - determine whether the linked function should be executed without user interaction, or whether it
	 * should be shown normally on the screen
	 */
	private int autoExecuteLink;

	/**
	 * Construct an empty linked function
	 */
	public LinkedFunction()
	{
		commonInitialisation();
	}

	/**
	 * Construct a linked function
	 */
	public LinkedFunction(String selectionOption, String optionId, String optionDescription, String contextFields)
	{
		commonInitialisation();
		this.selectionOption = selectionOption;
		this.optionId = optionId;
		this.optionDescription = optionDescription;
		this.contextFields = contextFields;
	}

	/**
	 * Construct a linked function with blank description
	 */
	public LinkedFunction(String selectionOption, String optionId, String contextFields)
	{
		commonInitialisation();
		this.selectionOption = selectionOption;
		this.optionId = optionId;
		this.contextFields = contextFields;
	}

	/**
	 * Initialise properties
	 */
	private void commonInitialisation()
	{
		this.selectionOption = "";
		this.optionId = "";
		this.optionDescription = "";
		this.contextFields = "";
		this.ignoreLinkFuncExpression = "";
		this.globalLink = false;
		this.defaultLink = false;
		this.autoExecuteLink = AUTO_EXECUTE_DSP_ALWAYS;
	}

	/**
	 * 
	 * @param copyFrom
	 *            - Another LinkedFunction object to copy from
	 */
	public void copyFrom(LinkedFunction copyFrom)
	{
		selectionOption = copyFrom.getSelectionOption();
		optionId = copyFrom.getOptionId();
		optionDescription = copyFrom.getOptionDescription();
		contextFields = copyFrom.getContextFields();
		ignoreLinkFuncExpression = copyFrom.ignoreLinkFuncExpression;
		globalLink = copyFrom.globalLink;
		defaultLink = copyFrom.defaultLink;
		autoExecuteLink = copyFrom.autoExecuteLink;
	}

	/**
	 * Return the selection option
	 * 
	 * @return the selection option
	 */
	public String getSelectionOption()
	{
		return selectionOption;
	}

	/**
	 * Set the selection option
	 * 
	 * @param selectionOption
	 *            - the selection option
	 */
	public void setSelectionOption(String selectionOption)
	{
		this.selectionOption = selectionOption;
	}

	/**
	 * Return the option id
	 * 
	 * @return the option id
	 */
	public String getOptionId()
	{
		return optionId;
	}

	/**
	 * Set the option id
	 * 
	 * @param optionId
	 *            - the option id
	 */
	public void setOptionId(String optionId)
	{
		this.optionId = optionId;
	}

	/**
	 * Return the option description
	 * 
	 * @return the option description
	 */
	public String getOptionDescription()
	{
		return optionDescription;
	}

	/**
	 * Set the option description
	 * 
	 * @param optionDescription
	 *            - the option description
	 */
	public void setOptionDescription(String optionDescription)
	{
		this.optionDescription = optionDescription;
	}

	/**
	 * Return the context fields delimited by Toolbox.CONTEXT_DELIMETER
	 * 
	 * @return the context fields
	 */
	public String getContextFields()
	{
		return contextFields;
	}

	/**
	 * Set the context fields
	 * 
	 * @param contextFields
	 *            - the context fields delimited by Toolbox.CONTEXT_DELIMETER
	 */
	public void setContextFields(String contextFields)
	{
		this.contextFields = contextFields;
	}

	/**
	 * Return the expression to determine whether to ignore the linked function
	 * 
	 * @return the expression to determine whether to ignore the linked function
	 */
	public String getIgnoreLinkFuncExpression()
	{
		return ignoreLinkFuncExpression;
	}

	/**
	 * Set the expression to determine whether to ignore the linked function
	 * 
	 * @param ignoreLinkFuncExpression
	 *            - the expression to determine whether to ignore the linked function
	 */
	public void setIgnoreLinkFuncExpression(String ignoreLinkFuncExpression)
	{
		this.ignoreLinkFuncExpression = ignoreLinkFuncExpression;
	}

	/**
	 * Determine whether this link function is a global linked function
	 * 
	 * @return true if this link function is a global linked function
	 */
	public boolean isGlobalLink()
	{
		return globalLink;
	}

	/**
	 * Set whether this link function is a global linked function
	 * 
	 * @param globalLink
	 *            - true if this link function is a global linked function
	 */
	public void setGlobalLink(boolean globalLink)
	{
		this.globalLink = globalLink;
	}

	/**
	 * Determine whether this is the default linked function
	 * 
	 * @return true if this is the default linked function
	 */
	public boolean isDefaultLink()
	{
		return defaultLink;
	}

	/**
	 * Set whether this is the default linked function
	 * 
	 * @param defaultLink
	 *            - true if this is the default linked function
	 */
	public void setDefaultLink(boolean defaultLink)
	{
		this.defaultLink = defaultLink;
	}

	/**
	 * Return the auto execute mode
	 * 
	 * @return the auto execute mode
	 */
	public int getAutoExecuteLink()
	{
		return autoExecuteLink;
	}

	/**
	 * Set the auto execute mode
	 * 
	 * @param autoExecuteLink
	 *            - the auto execute mode
	 */
	public void setAutoExecuteLink(int autoExecuteLink)
	{
		this.autoExecuteLink = autoExecuteLink;
	}
}
