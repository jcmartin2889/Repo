package com.misys.equation.function.beans;

import com.misys.equation.function.beans.valid.IValidation;
import com.misys.equation.function.beans.valid.MessageContainer;
import com.misys.equation.problems.ProblemLocation;

/**
 * This class represents an validation EL expression
 * <p>
 * As well as the validation expression text, it is possible to specific a different message id from the standard KSM7351 message,
 * or alternative text to be appended to the message instead of the actual expression
 * 
 */
public class ValidationExpression extends Element implements IValidation
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ValidationExpression.java 6793 2010-03-31 12:10:20Z deroset $";

	/** The expression language script (without the enclosing ${ }) */
	private String expression;

	/** The Id of an error message to be shown if the expression returns true. Mutually exclusive with the errorText */
	private String errorMessageId;

	/** Literal error text to be shown if the expression returns true. Mutually exclusive with the errorMessageId */
	private String errorText;

	/** The first substitution parameter (&1). Can be either literal text or an expression. */
	private String substitutionParmOne;

	/** The second substitution parameter (&2). Can be either literal text or an expression. */
	private String substitutionParmTwo;

	/** The third substitution parameter (&3). Can be either literal text or an expression. */
	private String substitutionParmThree;

	/**
	 * Default constructor for bean deserialisation
	 */
	public ValidationExpression()
	{
		super();
		expression = "";
		errorMessageId = "";
		errorText = "";
		substitutionParmOne = "";
		substitutionParmTwo = "";
		substitutionParmThree = "";
	}

	/**
	 * Construct a new layout with the given label, id and description
	 * 
	 * @param option
	 *            - the id of the option
	 * @param optionTitle
	 *            - the label of the option
	 * @param optionDescription
	 *            - the description of the option
	 */
	public ValidationExpression(String option, String optionTitle, String optionDescription)
	{
		super(option, optionTitle, optionDescription);
		expression = "";
		errorMessageId = "";
		errorText = "";
		substitutionParmOne = "";
		substitutionParmTwo = "";
		substitutionParmThree = "";
	}

	/**
	 * 
	 * @param copyFrom
	 *            - Another ValidationExpression object to copy from
	 */
	public void copyFrom(ValidationExpression copyFrom)
	{
		setId(copyFrom.getId());
		setLabel(copyFrom.getLabel());
		setDescription(copyFrom.getDescription());
		expression = copyFrom.getExpression();
		errorMessageId = copyFrom.getErrorMessageId();
		errorText = copyFrom.getErrorText();
		substitutionParmOne = copyFrom.getSubstitutionParmOne();
		substitutionParmTwo = copyFrom.getSubstitutionParmTwo();
		substitutionParmThree = copyFrom.getSubstitutionParmThree();
	}

	public boolean validateBean(MessageContainer messageContainer, String subSet, boolean includeChildren)
	{
		ProblemLocation problemLocation = new ProblemLocation(ValidationExpression.class.getSimpleName(), getId());
		// Can only specify either an error message Id or literal text:
		if (errorMessageId.length() > 0 && errorText.length() > 0)
		{
			messageContainer.addErrorMessageId("Language.CannotSpecifyBothIdAndLiteral", problemLocation);
		}

		// The expression cannot be blank
		if (expression.trim().length() == 0)
		{
			messageContainer.addErrorMessageId("Language.ExpressionMustBeEntered", problemLocation);
		}
		else
		{
			if (this.rtvParent() instanceof InputField)
			{
				ValidationHelper.validateBooleanELExpression(expression, this.rtvParent(), messageContainer,
								ValidationHelper.BooleanELType.VALIDATION_EXPRESSION, problemLocation);
				if (substitutionParmOne.length() > 0)
				{
					ValidationHelper.validateStringELExpression(substitutionParmOne, this.rtvParent(), messageContainer,
									ValidationHelper.StringELType.DISPLAYSTYLE_SUBPARM, problemLocation, null);
				}
				if (substitutionParmTwo.length() > 0)
				{
					ValidationHelper.validateStringELExpression(substitutionParmTwo, this.rtvParent(), messageContainer,
									ValidationHelper.StringELType.DISPLAYSTYLE_SUBPARM, problemLocation, null);
				}
				if (substitutionParmThree.length() > 0)
				{
					ValidationHelper.validateStringELExpression(substitutionParmThree, this.rtvParent(), messageContainer,
									ValidationHelper.StringELType.DISPLAYSTYLE_SUBPARM, problemLocation, null);
				}
			}
		}
		return false;
	}

	public String getExpression()
	{
		return expression;
	}

	public void setExpression(String expression)
	{
		this.expression = expression;
	}

	public String getErrorMessageId()
	{
		return errorMessageId;
	}

	public void setErrorMessageId(String errorMessageId)
	{
		this.errorMessageId = errorMessageId;
	}

	public String getErrorText()
	{
		return errorText;
	}

	public void setErrorText(String errorText)
	{
		this.errorText = errorText;
	}

	public void setSubstitutionParmOne(String substitutionParmOne)
	{
		this.substitutionParmOne = substitutionParmOne;
	}

	public String getSubstitutionParmOne()
	{
		return substitutionParmOne;
	}

	public void setSubstitutionParmTwo(String substitutionParmTwo)
	{
		this.substitutionParmTwo = substitutionParmTwo;
	}

	public String getSubstitutionParmTwo()
	{
		return substitutionParmTwo;
	}

	public void setSubstitutionParmThree(String substitutionParmThree)
	{
		this.substitutionParmThree = substitutionParmThree;
	}

	public String getSubstitutionParmThree()
	{
		return substitutionParmThree;
	}
}