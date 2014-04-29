package com.misys.equation.function.beans;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.valid.MessageContainer;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.misys.equation.problems.ProblemLocation;

/**
 * This class represents all the UI attributes of a field
 * 
 */
public class DisplayAttributes extends Element implements IDisplayItem
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DisplayAttributes.java 17504 2013-11-04 13:00:43Z williae1 $";

	/** Suppresses label output */
	public static final int FIELD_LABEL_NONE = 0;
	/** Positions the label above the field */
	public static final int FIELD_LABEL_ABOVE = 1;
	/** Positions the label below the field */
	public static final int FIELD_LABEL_BELOW = 2;
	/** Positions the label to the left of the field */
	public static final int FIELD_LABEL_LEFT = 3;
	/** Positions the label to the right of the field */
	public static final int FIELD_LABEL_RIGHT = 4;

	/** Indicates the field is not visible */
	public static final String VISIBLE_NO = "0";
	/** Indicates the field is visible (default) */
	public static final String VISIBLE_YES = "1";
	/** Indicates that there is an EL Script to determine visibility */
	public static final String VISIBLE_SCRIPT = "2";
	/** Indicates that there is Java exit code to determine visibility */
	public static final String VISIBLE_CODE = "3";

	/** Indicates the field is not protected (default). */
	public static final String PROTECTED_NO = "0";
	/** Indicates the field is protected */
	public static final String PROTECTED_YES = "1";
	/** Indicates that there is an EL Script to determine protection */
	public static final String PROTECTED_SCRIPT = "2";
	/** Indicates that there is Java exit code to determine protection */
	public static final String PROTECTED_CODE = "3";

	/** Indicates the field is not formatted with a formatting code */
	public static final String EDIT_CODE_NONE = "";
	/** Indicates the field is formatted as a date */
	public static final String EDIT_CODE_DATE = "1";
	/** Indicates the field is formatted as an amount */
	public static final String EDIT_CODE_AMOUNT = "1";
	/** Indicates the field is formatted as a rate */
	public static final String EDIT_CODE_RATE = "2";
	/** Indicates the field is formatted as an amount */
	public static final String EDIT_CODE_AMOUNT_WITH_REPLACE = "3";
	/** Indicates that there is no edit code parameter */
	public static final String EDIT_PARAMETER_NONE = "1";
	/** Indicates that there is an EL Script to determine the edit code parameter */
	public static final String EDIT_PARAMETER_SCRIPT = "2";
	/** Indicates that there is Java exit code to determine the edit code parameter */
	public static final String EDIT_PARAMETER_CODE = "3";

	/*
	 * Display field attributes
	 */
	private String visible;
	private String protectedStatus;
	private String visibleExpression;
	private String protectedExpression;
	private String maskType;
	private String mask;
	private String widget;
	private boolean hideValidateWidget;
	private String prompt;
	private int labelPosition;
	private boolean keepWithPrevious;
	private boolean highlightWithPrevious;
	private String maskTextOwner;

	private String linkedFuncId;
	private String linkedFuncContext;

	/** This is the script that will be executed when a field loses focus on the screen */
	private String onBlurEventScript;

	/** Indicates whether the field is right-to-left oriented */
	private boolean rtl;

	/** Name of the CSS style */
	private String displayStyleLabel;
	private String displayStyleDescription;
	private String displayStyleValue;
	private String displayStyleRow;

	/**
	 * Indicates whether the field 'description' should be shown instead of the value. This is particularly useful for read-only
	 * fields such as enquiry results
	 */
	private boolean showDescriptionAsValue;

	/** Display as right-aligned */
	private boolean showAsRightAligned;

	/** Determine whether this repeating field (part of list) is displayed in the table */
	private String inGridVisible;
	private String inGridVisibleExpression;

	/** Appear on new line (for repeating fields only with keepWithPrevious */
	private boolean keepWithPreviousInNewLine;

	/** Repeating fields only */
	private boolean displayAsUrl;

	/** Label script - allow user to generate the label at runtime */
	private String labelScriptRunTime;

	/**
	 * Editing code<br>
	 * 
	 * For amounts:<br>
	 * EDIT_AMOUNT_DEFAULT - the amount is edited as standard amount formatting. The number of decimal places of the field must be
	 * 0, otherwise, the number of decimal places defined on the field will be used
	 * 
	 * For dates:<br>
	 * EDIT_DATE_DD_MM_YYYY - the date is formatted as DD MM YYYY or MM DD YYYY or YYYY MM DD depending on the user date format
	 */
	private String editCode;

	/**
	 * Editing code parameter expression <br>
	 * 
	 * For amounts:<br>
	 * EDIT_AMOUNT_DEFAULT - this returns the currency name or the number of decimal places
	 * 
	 * For dates:<br>
	 * EDIT_DATE_DD_MM_YYYY - not used
	 * 
	 */
	private String editCodeParameter;

	/**
	 * This determines whether to use script or Java to retrieve the edit code parameter
	 */
	private String editCodeParameterStatus;

	/**
	 * This contains the list of replacement String if the value of the String being edited matches the one on the list
	 * 
	 * The value of the search String must correponds to the type of the input field (e.g. if it is numeric field, then the<Br>
	 * search string must also be a valid numeric.
	 * 
	 * Pre-defined entries:<br>
	 * *HIVAL - identifies the highest possible value for the field<br>
	 * *LOVAL - identifies the lowest possible value for the field<br>
	 */
	private List<ReplacementToken> editCodeParameterReplacements = new ArrayList<ReplacementToken>();

	// This determine whether predictive prompting is available
	private boolean allowPredictivePrompt;

	/**
	 * Construct a new, empty instance
	 */
	public DisplayAttributes()
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
	public DisplayAttributes(String id, String label, String description)
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
	public DisplayAttributes(Element element)
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
	public DisplayAttributes(DisplayAttributes field)
	{
		super(field);
		visible = field.getVisible();
		protectedStatus = field.getProtected();
		mask = field.getMask();
		widget = field.getWidget();
		hideValidateWidget = field.isHideValidateWidget();
		prompt = field.getPrompt();
		labelPosition = field.getLabelPosition();
		keepWithPrevious = field.isKeepWithPrevious();
		highlightWithPrevious = field.isHighlightWithPrevious();
		visibleExpression = field.getVisibleExpression();
		protectedExpression = field.getProtectedExpression();
		onBlurEventScript = field.getOnBlurEventScript();
		linkedFuncId = field.getLinkedFuncId();
		linkedFuncContext = field.getLinkedFuncContext();
		maskType = field.getMaskType();
		rtl = field.isRtl();
		showDescriptionAsValue = field.isShowDescriptionAsValue();
		displayStyleLabel = field.getDisplayStyleLabel();
		displayStyleDescription = field.getDisplayStyleDescription();
		displayStyleValue = field.getDisplayStyleValue();
		displayStyleRow = field.getDisplayStyleRow();
		showAsRightAligned = field.isShowAsRightAligned();
		inGridVisible = field.getInGridVisible();
		inGridVisibleExpression = field.inGridVisibleExpression;
		editCode = field.editCode;
		editCodeParameter = field.editCodeParameter;
		editCodeParameterStatus = field.getEditCodeParameterStatus();
		keepWithPreviousInNewLine = field.keepWithPreviousInNewLine;
		displayAsUrl = field.displayAsUrl;
		labelScriptRunTime = field.labelScriptRunTime;
		allowPredictivePrompt = field.allowPredictivePrompt;
		editCodeParameterReplacements = field.editCodeParameterReplacements;
		maskTextOwner = field.getMaskTextOwner();
	}

	/**
	 * Common field initialization called by non-copy Constructors.
	 * <p>
	 * When new member variables are added, initialisation code must be added to both this method and the copy constructor.
	 */
	private void commonInitialization()
	{
		visible = VISIBLE_YES;
		protectedStatus = PROTECTED_NO;
		mask = ""; //$NON-NLS-1$	
		widget = ""; //$NON-NLS-1$
		hideValidateWidget = false;
		prompt = ""; //$NON-NLS-1$		
		labelPosition = FIELD_LABEL_LEFT;
		keepWithPrevious = false;
		highlightWithPrevious = true;
		visibleExpression = "";
		protectedExpression = "";
		onBlurEventScript = "";
		linkedFuncId = "";
		linkedFuncContext = "";
		maskType = TEXT_VALUE_TEXT;
		rtl = false;
		showDescriptionAsValue = false;
		displayStyleLabel = "";
		displayStyleDescription = "";
		displayStyleValue = "";
		displayStyleRow = "";
		showAsRightAligned = false;
		inGridVisible = VISIBLE_YES;
		inGridVisibleExpression = "";
		editCode = "";
		editCodeParameter = "";
		editCodeParameterStatus = EDIT_PARAMETER_NONE;
		keepWithPreviousInNewLine = false;
		displayAsUrl = false;
		labelScriptRunTime = "";
		allowPredictivePrompt = false;
		maskTextOwner = "";
	}

	/**
	 * Gets the field visible status
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
	 * Sets how the visibility of the field will be determined at run time.
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

	public String getVisibleExpression()
	{
		return visibleExpression;
	}

	public void setVisibleExpression(String visibleExpression)
	{
		this.visibleExpression = visibleExpression;
	}

	/**
	 * Return the mask of the field
	 * 
	 * @return the mask of the field
	 */
	public String getMask()
	{
		return mask;
	}

	/**
	 * Set the mask of the field
	 * 
	 * @param mask
	 *            - mask of the field
	 */
	public void setMask(String mask)
	{
		this.mask = mask;
	}

	/**
	 * Return the widget of the field
	 * 
	 * @return the widget of the field
	 */
	public String getWidget()
	{
		return widget;
	}

	/**
	 * Set the widget of the field
	 * 
	 * @param widget
	 *            - widget name to be assigned to the field
	 */
	public void setWidget(String widget)
	{
		this.widget = widget;
	}
	/**
	 * Getter method of hideValidateWidget property
	 * 
	 * @return boolean
	 */
	public boolean isHideValidateWidget()
	{
		return hideValidateWidget;
	}
	/**
	 * Setter method of hideValidateWidget property
	 * 
	 * @param hideValidateWidget
	 */
	public void setHideValidateWidget(boolean hideValidateWidget)
	{
		this.hideValidateWidget = hideValidateWidget;
	}

	/**
	 * Return the prompt module of the field
	 * 
	 * @return the prompt module of the field
	 */
	public String getPrompt()
	{
		return prompt;
	}

	/**
	 * Set the prompt module of the field
	 * 
	 * @param prompt
	 *            - prompt module to be assigned to the field
	 */
	public void setPrompt(String prompt)
	{
		this.prompt = prompt;
	}

	/**
	 * Gets the field protected status
	 * <p>
	 * 
	 * @return an <code>String</code> indicating how the protection of the field will be determined at runtime. This will be either
	 *         null, or one of the following constants: PROTECTED_NO, PROTECTED_YES, PROTECTED_SCRIPT or PROTECTED_CODE.
	 */
	public String getProtected()
	{
		return protectedStatus;
	}

	/**
	 * Sets how the protection of the field will be determined at run time.
	 * 
	 * @param protectedStatus
	 *            a <code>String</code> indicating how the protection is determined. Must be one of PROTECTED_YES, PROTECTED_NO,
	 *            PROTECTED_SCRIPT or PROTECTED_CODE.
	 */
	public void setProtected(String protectedStatus)
	{
		this.protectedStatus = protectedStatus;
	}

	public String getProtectedExpression()
	{
		return protectedExpression;
	}

	public void setProtectedExpression(String protectedExpression)
	{
		this.protectedExpression = protectedExpression;
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

	public void setLabelPosition(int labelPosition)
	{
		this.labelPosition = labelPosition;
	}

	/**
	 * Determine whether this field is displayed alongside the previous field in the layout, instead of a new line
	 * 
	 * @return true - if this field is displayed alongside the previous field
	 */
	public boolean isKeepWithPrevious()
	{
		return keepWithPrevious;
	}

	/**
	 * Set whether this field is displayed alongside the previous field in the layout, instead of a new line
	 * 
	 * @param groupField
	 *            - true if this field is displayed alongside the previous field in the layout, instead of a new line
	 */
	public void setKeepWithPrevious(boolean groupField)
	{
		this.keepWithPrevious = groupField;
	}

	/**
	 * Getter method of highlightWithPrevious Property
	 * 
	 * @return boolean
	 */
	public boolean isHighlightWithPrevious()
	{
		return highlightWithPrevious;
	}

	/**
	 * Setter method of highlightWithPrevious property
	 * 
	 * @param highlightWithPrevious
	 */
	public void setHighlightWithPrevious(boolean highlightWithPrevious)
	{
		this.highlightWithPrevious = highlightWithPrevious;
	}

	/**
	 * Return the on-blur event script
	 * 
	 * @return the on-blur event script
	 */
	public String getOnBlurEventScript()
	{
		return onBlurEventScript;
	}

	/**
	 * Set the on-blur event script
	 * 
	 * @param onBlurEventScript
	 *            - the on-blur event script
	 */
	public void setOnBlurEventScript(String onBlurEventScript)
	{
		this.onBlurEventScript = onBlurEventScript;
	}

	/**
	 * Return the parent of this field.
	 * <p>
	 * This is deliberately not called getAttributesSet otherwise the bean serialization would fail with a cyclical reference
	 * 
	 * @return the parent of this field
	 */
	public DisplayAttributesSet attributesSet()
	{
		return getParentSet(this);
	}

	private DisplayAttributesSet getParentSet(Element element)
	{
		Element parent = element.rtvParent();
		if (parent instanceof DisplayAttributesSet)
		{
			return (DisplayAttributesSet) parent;
		}
		else
		{
			return getParentSet(parent);
		}
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
		ProblemLocation problemLocation = new ProblemLocation(DisplayAttributes.class.getSimpleName(), getId());

		Layout layoutBean = null;
		Function serviceBean = null;
		DisplayAttributesSet set = attributesSet();
		if (attributesSet() == null)
		{
			messageContainer.addErrorMessageId("Language.DisplayAttributesHasNullParentSet", problemLocation); //$NON-NLS-1$
		}
		else
		{
			// Validate that Predictive prompt is only enabled if a Prompt module is selected
			if (allowPredictivePrompt && prompt.trim().length() == 0)
			{
				messageContainer.addErrorMessageId("Language.PredictivePromptRequiresPromptModule", getId(), problemLocation);
			}

			layoutBean = set.rtvLayout();
			if (layoutBean == null)
			{
				messageContainer.addErrorMessageId("Language.DisplayAttributesSetHasNullParentLayout", problemLocation); //$NON-NLS-1$
			}
			else
			{
				serviceBean = layoutBean.service();
				if (serviceBean == null)
				{
					messageContainer.addErrorMessageId("Language.DisplayAttributesSetHasNullParentLayout", problemLocation); //$NON-NLS-1$
				}
				else
				{
					int index = serviceBean.inputFieldSetKeys().indexOf(set.getId());
					if (index == -1)
					{
						messageContainer.addErrorMessageId(
										"Language.CorrespondingInputFieldSetNotFound", set.getId(), problemLocation); //$NON-NLS-1$
					}
					else
					{
						InputFieldSet inputFieldSet = serviceBean.getInputFieldSets().get(index);
						// We can check to see if the actual field exists for this attributes
						if (!inputFieldSet.containsField(getId()))
						{
							messageContainer.addErrorMessageId("Language.CorrespondingInputFieldNotFound", new String[] { getId(),
											inputFieldSet.getId() }, problemLocation);
						}
						else
						{

							InputField inputField;
							try
							{
								inputField = inputFieldSet.getInputField(getId());
								// Validate the Visible EL expression:
								if (VISIBLE_SCRIPT.equals(visible))
								{
									ValidationHelper.validateBooleanELExpression(visibleExpression, inputField, messageContainer,
													ValidationHelper.BooleanELType.VISIBLE_EXPRESSION, problemLocation);
								}
								// Validate the Protected EL expression:
								if (PROTECTED_SCRIPT.equals(protectedStatus))
								{
									ValidationHelper.validateBooleanELExpression(protectedExpression, inputField, messageContainer,
													ValidationHelper.BooleanELType.PROTECTED_EXPRESSION, problemLocation);
								}

								// Style
								if (displayStyleLabel.trim().length() > 0)
								{
									ValidationHelper.validateStringELExpression(displayStyleLabel, inputField, messageContainer,
													ValidationHelper.StringELType.DISPLAYSTYLE_FIELDLABEL, problemLocation, null);
								}
								if (displayStyleValue.trim().length() > 0)
								{
									ValidationHelper.validateStringELExpression(displayStyleValue, inputField, messageContainer,
													ValidationHelper.StringELType.DISPLAYSTYLE_FIELDVALUE, problemLocation, null);
								}
								if (displayStyleDescription.trim().length() > 0)
								{
									ValidationHelper.validateStringELExpression(displayStyleDescription, inputField,
													messageContainer, ValidationHelper.StringELType.DISPLAYSTYLE_FIELDDESC,
													problemLocation, null);
								}
								if (displayStyleRow.trim().length() > 0)
								{
									ValidationHelper.validateStringELExpression(displayStyleRow, inputField, messageContainer,
													ValidationHelper.StringELType.DISPLAYSTYLE_FIELDROW, problemLocation, null);
								}
								// Validate the Column Visible (repeating fields) EL expression:
								if (VISIBLE_SCRIPT.equals(inGridVisible))
								{
									ValidationHelper.validateBooleanELExpression(inGridVisibleExpression, inputField,
													messageContainer, ValidationHelper.BooleanELType.COLUMN_VISIBLE_EXPRESSION,
													problemLocation);
								}
								// Display with Previous for column fields
								if (keepWithPreviousInNewLine && !keepWithPrevious)
								{
									messageContainer.addErrorMessageId("Language.DisplayWithPreviousInLineNotValid",
													problemLocation);
								}
								// Display as URL for column fields should have show description as value. The URL will be the input
								// value and the label that is shown will be the output value
								if (displayAsUrl && !showDescriptionAsValue)
								{
									messageContainer.addErrorMessageId("Language.DisplayAsUrlNotValid", problemLocation);
								}
								validateEditCodeParmScript(messageContainer, inputField, problemLocation);
							}
							catch (EQException e)
							{
								// Should never get here as check for field existence before attempting to
								// get it.
								throw new RuntimeException(e);
							}
						}
					}
				}
			}
		}

		return !messageContainer.hasErrorMessages();
	}
	/**
	 * Validate edit code parameter script
	 * <p>
	 * This method validates the formatting fields and their interdependancies
	 * 
	 * @param messageContainer
	 * @param inputField
	 * @param problemLocation
	 */
	private void validateEditCodeParmScript(MessageContainer messageContainer, InputField inputField,
					ProblemLocation problemLocation)
	{
		int decimals = Toolbox.parseInt(inputField.getDecimals(), 0);

		// If amount edit code specified, but the parameter script is not specified, show error
		if (EqDataType.isNumeric(inputField.getDataType()))
		{
			if (!EDIT_PARAMETER_SCRIPT.equals(editCodeParameterStatus) && !EDIT_PARAMETER_CODE.equals(editCodeParameterStatus))
			{
				// amount editing
				if ((editCode.equals(EqDataType.EDIT_AMOUNT_DEFAULT) || editCode.equals(EqDataType.EDIT_AMOUNT_WITH_REPLACE))
								&& decimals == 0 && editCodeParameter.length() == 0)
				{
					messageContainer.addErrorMessageId("Language.EditCodeParmRequired", inputField.getId(), problemLocation);
				}
			}
		}

		// If SCRIPT:
		if (editCodeParameterStatus.equals(EDIT_PARAMETER_SCRIPT))
		{
			if (EqDataType.isNumeric(inputField.getDataType()))
			{
				// Numeric and the editing is Amount
				if ((editCode.equals(EqDataType.EDIT_AMOUNT_DEFAULT) || editCode.equals(EqDataType.EDIT_AMOUNT_WITH_REPLACE)))
				{
					if (decimals > 0)
					{
						// For numeric field with non-zero decimals, the specified
						// decimals are used; so we shouldn't specify here
						messageContainer.addErrorMessageId("Language.EditCodeScriptInvalidForNonAmounts", inputField.getId(),
										problemLocation);
					}
					else
					{
						if (editCodeParameter.length() == 0)
						{
							// Need the edit code script
							messageContainer.addErrorMessageId("Language.ELFormattingScriptMustBeEntered", inputField.getId(),
											problemLocation);
						}
						else
						{
							// Validate the actual script itself
							ValidationHelper.validateStringELExpression(editCodeParameter, inputField, messageContainer,
											ValidationHelper.StringELType.DISPLAYSTYLE_EDITCODE, problemLocation, null);
						}
					}
				}
				else
				{
					// Numeric type, but the edit code is not amount
					messageContainer.addErrorMessageId("Language.EditCodeScriptInvalidForDataType", inputField.getId(),
									problemLocation);
				}
			}
			else
			{
				// Not numeric type, so shouldn't have a script
				messageContainer.addErrorMessageId("Language.EditCodeScriptInvalid", inputField.getId(), problemLocation);
			}
		}
		// TODO: This method doesn't take into account the Java user exit option

		if (editCode.equals(EDIT_CODE_AMOUNT_WITH_REPLACE))
		{
			for (ReplacementToken token : editCodeParameterReplacements)
			{
				// validate search string
				if (token.getSearch().trim().length() == 0)
				{
					messageContainer.addErrorMessageId("Language.AmountSearchTextMustBeEntered", inputField.getId(),
									problemLocation);
				}

				// validate replacement string
				if (token.getReplacement().trim().length() == 0)
				{
					messageContainer.addErrorMessageId("Language.ELAmountReplacementMustBeEntered", inputField.getId(),
									problemLocation);
				}
				else
				{
					ValidationHelper.validateStringELExpression(token.getReplacement(), inputField, messageContainer,
									ValidationHelper.StringELType.AMOUNTREPLACEMENT, problemLocation, null);
				}
			}
		}
	}

	/**
	 * Determines the parent group id.
	 * <p>
	 * Currently this only works for DisplayAttributes in a second-level group
	 * 
	 * @return a String containing the ID of the parent group (or null).
	 */
	public String getGroupId()
	{
		String result = null;
		Element parent = rtvParent();
		if (parent instanceof DisplayGroup)
		{
			Element grandParent = ((DisplayGroup) parent).rtvParent();
			if (grandParent instanceof DisplayGroup)
			{
				result = grandParent.getId();
			}
		}

		return result;
	}

	/**
	 * Return the linked function id
	 * 
	 * @return the linked function id
	 */
	public String getLinkedFuncId()
	{
		return linkedFuncId;
	}

	/**
	 * Set the linked function id
	 * 
	 * @param linkedFuncId
	 *            - the linked function id
	 */
	public void setLinkedFuncId(String linkedFuncId)
	{
		this.linkedFuncId = linkedFuncId;
	}

	/**
	 * Return the linked function context to be passed to the linked function
	 * 
	 * @return the linked function context to be passed to the linked function
	 */
	public String getLinkedFuncContext()
	{
		return linkedFuncContext;
	}

	/**
	 * Set the linked function context to be passed to the linked function
	 * 
	 * @param linkedFuncContext
	 *            - the linked function context to be passed to the linked function
	 */
	public void setLinkedFuncContext(String linkedFuncContext)
	{
		this.linkedFuncContext = linkedFuncContext;
	}

	/**
	 * Indicates whether the mask attribute is literal text or is a code to retrieve multi-language text
	 * <p>
	 * 
	 * @return either <code>Element.TEXT_VALUE_TEXT</code> to indicate that the label is fixed text, or the
	 *         <code>Element.TEXT_VALUE_CODE</code> constant to indicate that the label contains a code.
	 */
	public String getMaskType()
	{
		return maskType;
	}

	/**
	 * Sets whether the label attribute is literal text or is a code to retrieve multi-language text
	 * 
	 * @param maskType
	 *            a <code>String</code>, which should be either <code>Element.TEXT_VALUE_CODE</code> or
	 *            <code>Element.TEXT_VALUE_TEXT</code>
	 */
	public void setMaskType(String maskType)
	{
		this.maskType = maskType;
	}

	/**
	 * Return the mask in user's language
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * 
	 * @return the mask in user's language
	 */
	public String rtvMask(EquationUser eqUser)
	{
		return FunctionRuntimeToolbox.getHBXText(eqUser, getMaskTextOwner(), TextBean.TYPE_MASK, getMask(), getMaskType(),
						FunctionRuntimeToolbox.getServiceBaseLanguage(this, getMaskType()));
	}
	/**
	 * Set the mask text owner
	 * 
	 * @param maskTextOwner
	 *            the mask text owner to be set
	 */
	public void setMaskTextOwner(String maskTextOwner)
	{
		this.maskTextOwner = maskTextOwner;
	}

	/**
	 * Get the mask text owner
	 * 
	 * @return the text owner for the mask
	 */
	public String getMaskTextOwner()
	{
		return maskTextOwner;
	}

	/**
	 * Determine if the field is right-to-left oriented
	 * 
	 * @return true if the field is right-to-left oriented
	 */
	public boolean isRtl()
	{
		return rtl;
	}

	/**
	 * Set if the field must be right to left oriented
	 * 
	 * @param rtl
	 *            - true if the field is right-to-left oriented
	 */
	public void setRtl(boolean rtl)
	{
		this.rtl = rtl;
	}

	/**
	 * Determine the description for this field should be shown instead of the value
	 * 
	 * @return true if showing description instead of the value
	 */
	public boolean isShowDescriptionAsValue()
	{
		return showDescriptionAsValue;
	}
	/**
	 * Determine whether the field should have spooled file script added
	 * 
	 * @return true if field should have spooled file script added
	 */
	public boolean addSpooledFileScript()
	{
		return getId().startsWith("A_SPOOLJS");
	}
	/**
	 * Sets whether the description for this field should be shown instead of the value
	 * 
	 * @param showDescriptionAsValue
	 *            whether the description for this field should be shown instead of the value
	 */
	public void setShowDescriptionAsValue(boolean showDescriptionAsValue)
	{
		this.showDescriptionAsValue = showDescriptionAsValue;
	}

	/**
	 * Return the display style of the label
	 * 
	 * @return the display style of the label
	 */
	public String getDisplayStyleLabel()
	{
		return displayStyleLabel;
	}

	/**
	 * Set the display style of the label
	 * 
	 * @param displayStyleLabel
	 *            - the display style of the label
	 */
	public void setDisplayStyleLabel(String displayStyleLabel)
	{
		this.displayStyleLabel = displayStyleLabel;
	}

	/**
	 * Return the display style of the description
	 * 
	 * @return the display style of the description
	 */
	public String getDisplayStyleDescription()
	{
		return displayStyleDescription;
	}

	/**
	 * Set the display style of the description
	 * 
	 * @param displayStyleDescription
	 *            - the display style of the description
	 */
	public void setDisplayStyleDescription(String displayStyleDescription)
	{
		this.displayStyleDescription = displayStyleDescription;
	}

	/**
	 * Return the display style of the field
	 * 
	 * @return the display style of the field
	 */
	public String getDisplayStyleValue()
	{
		return displayStyleValue;
	}

	/**
	 * Set the display style of the field
	 * 
	 * @param displayStyleField
	 *            - the display style of the field
	 */
	public void setDisplayStyleValue(String displayStyleField)
	{
		this.displayStyleValue = displayStyleField;
	}

	/**
	 * Return the display style of the row
	 * 
	 * @return the display style of the row
	 */
	public String getDisplayStyleRow()
	{
		return displayStyleRow;
	}

	/**
	 * Set the display style of the row
	 * 
	 * @param displayStyleRow
	 *            - the display style of the row
	 */
	public void setDisplayStyleRow(String displayStyleRow)
	{
		this.displayStyleRow = displayStyleRow;
	}

	/**
	 * Display the field as right aligned
	 * 
	 * @return true if field is displayed as right-aligned
	 */
	public boolean isShowAsRightAligned()
	{
		return showAsRightAligned;
	}

	/**
	 * Set the field as right aligned
	 * 
	 * @param showAsRightAligned
	 *            - true if the field is displayed as right-aligned
	 */
	public void setShowAsRightAligned(boolean showAsRightAligned)
	{
		this.showAsRightAligned = showAsRightAligned;
	}

	/**
	 * Determine whether the repeating field is displayed in the table or not
	 * 
	 * @return an <code>String</code> indicating how the visibility of the field will be determined at runtime. This will be one of
	 *         the following constants: VISIBLE_YES, VISIBLE_NO, VISIBLE_SCRIPT or VISIBLE_CODE.
	 */
	public String getInGridVisible()
	{
		return inGridVisible;
	}

	/**
	 * Set the visibility of the repeating field in the table
	 * 
	 * @param inGridVisible
	 *            - in grid visibility status
	 */
	public void setInGridVisible(String inGridVisible)
	{
		this.inGridVisible = inGridVisible;
	}

	/**
	 * Return the in-grid visible expression
	 * 
	 * @return the in-grid visible expression
	 */
	public String getInGridVisibleExpression()
	{
		return inGridVisibleExpression;
	}

	/**
	 * Set the in-grid visible expression
	 * 
	 * @param inGridVisibleExpression
	 *            - the in-grid visible expression
	 */
	public void setInGridVisibleExpression(String inGridVisibleExpression)
	{
		this.inGridVisibleExpression = inGridVisibleExpression;
	}

	/**
	 * Return the edit code
	 * 
	 * @return the edit code
	 */
	public String getEditCode()
	{
		return editCode;
	}

	/**
	 * Set the edit code
	 * 
	 * @param editCode
	 *            - the edit code
	 */
	public void setEditCode(String editCode)
	{
		this.editCode = editCode;
	}

	/**
	 * Return the parameter for the edit code. The content will depend on the edit code
	 * 
	 * @return the parameter for the edit code
	 */
	public String getEditCodeParameter()
	{
		return editCodeParameter;
	}

	/**
	 * Gets the edit code parameter status
	 * <p>
	 * 
	 * @return an <code>String</code> indicating how the edit code parameter will be determined at runtime. This will be either
	 *         null, or one of the following constants: EDIT_PARAMETER_NONE, EDIT_PARAMETER_SCRIPT or EDIT_PARAMETER_CODE.
	 */
	public String getEditCodeParameterStatus()
	{
		return editCodeParameterStatus;
	}

	/**
	 * Sets how the edit code parameter will be determined at run time.
	 * 
	 * @param editCodeParameterStatus
	 *            a <code>String</code> indicating how the edit code parameter is determined. Must be one of EDIT_PARAMETER_NONE,
	 *            EDIT_PARAMETER_SCRIPT or EDIT_PARAMETER_CODE.
	 */
	public void setEditCodeParameterStatus(String editCodeParameterStatus)
	{
		this.editCodeParameterStatus = editCodeParameterStatus;
	}

	/**
	 * Set a parameter for the edit code
	 * 
	 * @param editCodeParameter
	 *            - a parameter for the edit code
	 */
	public void setEditCodeParameter(String editCodeParameter)
	{
		this.editCodeParameter = editCodeParameter;
	}

	/**
	 * Return the list of replacement token for editing
	 * 
	 * @return the list of replacement token for editing
	 */
	public List<ReplacementToken> getEditCodeParameterReplacements()
	{
		return editCodeParameterReplacements;
	}

	/**
	 * Set the list of replacement token for editing
	 * 
	 * @param editCodeParameterReplacements
	 *            - the list of replacement token for editing
	 */
	public void setEditCodeParameterReplacements(ArrayList<ReplacementToken> editCodeParameterReplacements)
	{
		this.editCodeParameterReplacements = editCodeParameterReplacements;
	}

	/**
	 * Add a replacement parameter to the list
	 * 
	 * @param editCodeParameterReplacement
	 *            - the replacement parameter
	 */
	public void addEditCodeParameterReplacement(ReplacementToken editCodeParameterReplacement)
	{
		this.editCodeParameterReplacements.add(editCodeParameterReplacement);
	}

	/**
	 * Determine if the repeating field should be on a next line if it is have keepWithPrevious
	 * 
	 * @return true if the repeating field should be on a next line if it is have keepWithPrevious
	 */
	public boolean isKeepWithPreviousInNewLine()
	{
		return keepWithPreviousInNewLine;
	}

	/**
	 * Set whether the repeating field should be on a next line if it is have keepWithPrevious
	 * 
	 * @param keepWithPreviousInNewLine
	 *            - true if the repeating field should be on a next line if it is have keepWithPrevious
	 */
	public void setKeepWithPreviousInNewLine(boolean keepWithPreviousInNewLine)
	{
		this.keepWithPreviousInNewLine = keepWithPreviousInNewLine;
	}
	/**
	 * Determine if the repeating field should be shown as a URL
	 * 
	 * @return true if the repeating field should be shown as a URL
	 */
	public boolean isDisplayAsUrl()
	{
		return displayAsUrl;
	}

	/**
	 * Set whether the repeating field should be shown as a URL
	 * 
	 * @param displayAsUrl
	 *            - true if the repeating field should be shown as a URL
	 */
	public void setDisplayAsUrl(boolean displayAsUrl)
	{
		this.displayAsUrl = displayAsUrl;
	}
	/**
	 * Return the script to generate the label at run time
	 * 
	 * @return the script to generate the label at run time
	 */
	public String getLabelScriptRunTime()
	{
		return labelScriptRunTime;
	}

	/**
	 * Set the script to generate the label at run time
	 * 
	 * @param labelScriptRunTime
	 *            - the script to generate the label at run time
	 */
	public void setLabelScriptRunTime(String labelScriptRunTime)
	{
		this.labelScriptRunTime = labelScriptRunTime;
	}

	/**
	 * Determine whether predictive prompting is enabled
	 * 
	 * @return true if predictive prompting is enabled
	 */
	public boolean isAllowPredictivePrompt()
	{
		return allowPredictivePrompt;
	}

	/**
	 * Set whether predictive prompting is enabled
	 * 
	 * @param allowPredictivePrompt
	 *            - true if predictive prompting is enabled
	 */
	public void setAllowPredictivePrompt(boolean allowPredictivePrompt)
	{
		this.allowPredictivePrompt = allowPredictivePrompt;
	}

	/**
	 * Remove a linked function
	 * 
	 * @param index
	 *            - index of the linked function to be removed
	 */
	public void removeEditParameterReplacements(int index)
	{
		editCodeParameterReplacements.remove(index);
	}

	/**
	 * Move the edit code parameter up
	 * 
	 * @param index
	 *            - the index of the edit code parameter to move up
	 */
	public void moveEditParameterReplacementsUp(int index)
	{
		if (index > 0)
		{
			ReplacementToken above = editCodeParameterReplacements.get(index - 1);
			editCodeParameterReplacements.remove(index - 1);
			editCodeParameterReplacements.add(index, above);
		}
	}

	/**
	 * Move the edit code parameter down
	 * 
	 * @param index
	 *            - the index of the edit code parameter to move down
	 */
	public void moveEditParameterReplacementsDown(int index)
	{
		if (index < editCodeParameterReplacements.size())
		{
			ReplacementToken below = editCodeParameterReplacements.get(index + 1);
			editCodeParameterReplacements.remove(index + 1);
			editCodeParameterReplacements.add(index, below);
		}
	}

}
