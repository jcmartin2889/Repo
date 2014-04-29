package com.misys.equation.function.test.option;

import java.io.File;
import java.util.ArrayList;

import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationPVMetaDataHelper;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.DisplayLabel;
import com.misys.equation.function.beans.Element;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.FieldSet;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.PVFieldSet;
import com.misys.equation.function.beans.ReplacementToken;
import com.misys.equation.function.beans.TextBean;
import com.misys.equation.function.beans.WorkField;
import com.misys.equation.function.runtime.FunctionMessages;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.MappingToolbox;

public class ALZ2 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ALZ2.java 11921 2011-09-27 03:33:50Z ESTHER.WILLIAMS $";

	boolean cache = true;

	public ALZ2()
	{
		try
		{
			setUp();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters)
	{
		ALZ2 test = new ALZ2();
		test.test();
	}

	private ArrayList<DisplayFieldSetWrapper> getSubFieldSet1(DisplayFieldSetWrapper root) throws EQException
	{
		ArrayList<DisplayFieldSetWrapper> setWrappers = new ArrayList<DisplayFieldSetWrapper>();
		// DisplayFieldSetWrapper setWrapper;
		// DisplayInputField field;
		//
		// // first sub input field set
		// record = new DisplayFieldSetWrapper();
		// record.setDefaultUserExit("");
		// record.setId("REC11");
		// record.setLabel("REC11");
		// record.setDescription("REC11");
		// record.setNextScreenUserExit("");
		// record.setValidateUserExit("");
		// record.setPath(root.getPath() + MappingToolbox.getDisplayFieldSetWrapperPath(record.getId()));
		// records.add(record);
		//
		// field = FunctionToolbox.getDisplayInputField("D1FS1", "1FS- 1st field", "1FS - 1st field", "A", "35", "");
		// FunctionToolbox.addDisplayInputField(record, field);
		// field = FunctionToolbox.getDisplayInputField("D1FS2", "1FS- 2nd field", "1FS - 2nd field", "A", "35", "");
		// field.setMandatory(InputField.MANDATORY_YES);
		// FunctionToolbox.addDisplayInputField(record, field);
		//
		// // second sub input field set
		// record = new DisplayFieldSetWrapper();
		// record.setDefaultUserExit("");
		// record.setId("REC12");
		// record.setLabel("REC12");
		// record.setDescription("REC12");
		// record.setNextScreenUserExit("");
		// record.setValidateUserExit("");
		// record.setPath(root.getPath() + MappingToolbox.getDisplayFieldSetWrapperPath(record.getId()));
		// records.add(record);
		//
		// field = FunctionToolbox.getDisplayInputField("D2FS1", "2FS- 1st field (key)", "2FS - 1st field (key)", "A", "35", "");
		// field.setKey(true);
		// FunctionToolbox.addDisplayInputField(record, field);
		// field = FunctionToolbox.getDisplayInputField("D2FS2", "2FS- 2nd field", "2FS - 2nd field", "A", "35", "");
		// FunctionToolbox.addDisplayInputField(record, field);
		// field = FunctionToolbox.getDisplayInputField("D2FS3", "2FS- 3rd field", "2FS - 3rd field", "A", "35", "");
		// FunctionToolbox.addDisplayInputField(record, field);

		// list of records
		return setWrappers;
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Record 1 of ALZ", "Description 1 of ALZ");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		inputFieldSet.setDescription("GBL900052");
		inputFieldSet.setDescriptionType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
		attributeSet.setLabel("GBLPWD");
		attributeSet.setLabelType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
		inputFieldSet.setDescriptionTextOwner(TextBean.OWNER_MISYS);
		attributeSet.setLabelTextOwner(TextBean.OWNER_MISYS);

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;
		PVFieldSet pvFieldSet;

		// add all the fields in this section ---------------------------------------

		// Account branch
		inputField = FunctionToolbox.getInputField("AB", "Account branch x", "This is the account branch", "A", "4", "");
		inputField.setInitialValue("9132");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLinkedFuncId("ALY");
		displayAttributes.setLinkedFuncContext("'XXX':AB:AN:AS");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Account number
		inputField = FunctionToolbox.getInputField("AN", "Account number", "This is the account number", "A", "6", "");
		inputField.setInitialValue("234567");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Account suffix
		inputField = FunctionToolbox.getInputField("AS", "Account suffix", "This is the account suffix", "A", "3", "");
		inputField.setInitialValue("001");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("GWR76R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", true, "N"));
		pvFieldSet.setSecurity(EquationPVMetaDataHelper.SEC_UPD);
		pvFieldSet.setCache(cache);

		// Account
		inputField = FunctionToolbox
						.getInputField(
										"AC",
										"Account",
										"This is the account and it is really really a very long long long description to display with funny characters !$'\\%^&*()_+\" ",
										"A", "13", "");
		inputField.setInitialValue("9132234567001");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("GWR76R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// External account number
		inputField = FunctionToolbox.getInputField("EAN", "External account number", "Description of EAN", "A", "20", "");
		inputField.setInitialValue("1840KBWD870900840");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setMinLength("10");
		inputField.setMaxLength("20");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("GIR33R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GIR33R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Transaction code
		inputField = FunctionToolbox.getInputField("TCD", "Transaction code", "Description of TCD", "A", "3", "");
		inputField.setInitialValue("510");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("CTR56R");
		displayAttributes.setWidget("IDB");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "CTR56R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Branch
		inputField = FunctionToolbox.getInputField("BRNM", "Branch", "Description of branch", "A", "4", "");
		inputField.setFieldContextType(InputField.FLDCTXTYP_BRNM);
		inputField.setInitialValue("LOND");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("CAR73R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "CAR73R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Protected field
		inputField = FunctionToolbox.getInputField("PROTECT", "Protected field", "Description of protected field", "A", "30", "");
		inputField.setInitialValue("LONDProtected field");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setWidget("CAL");
		displayAttributes.setPrompt("CAR73R");
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "CAR73R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Hidden field
		inputField = FunctionToolbox.getInputField("HIDDEN", "Hidden field", "Description of hidden field", "A", "30", "");
		inputField.setInitialValue("LONDHidden field");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setWidget("CAL");
		displayAttributes.setPrompt("CAR73R");
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "CAR73R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Field 1A
		inputField = FunctionToolbox.getInputField("FLD1A", "Label top 1", "Test for label top 1", "A", "30", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_ABOVE);
		displayAttributes.setPrompt("GBR39R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GBR39R", "V", true, "N"));
		pvFieldSet.setCache(cache);

		// Field 1B
		inputField = FunctionToolbox.getInputField("FLD1B", "Label top 2", "Test for label top 2", "A", "30", "");
		inputField.setInitialValueType(InputField.INIT_VALUE_SYS_VAR);
		inputField.setInitialValue("EXEP");
		inputField.setDefaultValue("default value");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_ABOVE);
		displayAttributes.setPrompt("C8R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "", true, "N"));
		pvFieldSet.setExecuteMode(FieldSet.EXECUTE_NEVER);
		pvFieldSet.setCache(cache);

		// Field 2A
		inputField = FunctionToolbox.getInputField("FLD2A", "Label bottom 1", "Test for label bottom 1", "A", "30", "");
		inputField.setInitialValueType(InputField.INIT_VALUE_MAJOR_PROC);
		inputField.setInitialValue("BCY");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_BELOW);
		displayAttributes.setPrompt("C8R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Field 2B
		inputField = FunctionToolbox.getInputField("FLD2B", "Label bottom 2", "Test for label bottom 2", "A", "30", "");
		inputField.setInitialValueType(InputField.INIT_VALUE_CST);
		inputField.setInitialValue("9450");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_BELOW);
		displayAttributes.setPrompt("C8R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "", true, "N"));
		pvFieldSet.setExecuteMode(FieldSet.EXECUTE_NEVER);
		pvFieldSet.setCache(cache);

		// Field 3A
		inputField = FunctionToolbox.getInputField("FLD3A", "Label left 1", "Test for label left 1", "A", "30", "");
		inputField.setFieldContextType(InputField.FLDCTXTYP_BBN);
		inputField.setInitialValueType(InputField.INIT_VALUE_CST);
		inputField.setInitialValue("0017");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_LEFT);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Field 3B
		inputField = FunctionToolbox.getInputField("FLD3B", "Label bottom 2", "Test for label bottom 2", "A", "30", "");
		inputField.setInitialValue("0991231CITY@@CH10000015");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_BELOW);
		// displayAttributes.setPrompt("SAR10R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		// FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "SAR10R", "", true, "N"));
		// pvFieldSet.setCache(cache);
		// pvFieldSet.setAllowPredictivePrompt(true);

		// Field 3C
		inputField = FunctionToolbox.getInputField("FLD3C", "Label top 3", "Test for label top 3", "A", "50", "");
		inputField.setInitialValue("BBB9998120002100  0991231KBSL@@MM 0000118");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_ABOVE);
		// displayAttributes.setPrompt("SAR50R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		// FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "SAR50R", "", true, "N"));
		// pvFieldSet.setCache(cache);
		// pvFieldSet.setAllowPredictivePrompt(true);

		// Label
		DisplayLabel displayLabel = FunctionToolbox.getDisplayLabel("label1",
						"Label only 1 - the quick brown fox jumps over the lazy dog", "Desc of Label 1");
		FunctionToolbox.addDisplayLabel(attributeSet, displayLabel);

		// Label
		displayLabel = FunctionToolbox.getDisplayLabel("label1b",
						"Label only 1!N_L!the quick brown fox!N_L! jumps over!N_L! the lazy dog", "Desc of Label 1");
		FunctionToolbox.addDisplayLabel(attributeSet, displayLabel);

		// Label
		displayLabel = FunctionToolbox.getDisplayLabel("label1a", "", "Desc of Label 1");
		displayLabel.setLabelType(Element.TEXT_VALUE_LINE);
		FunctionToolbox.addDisplayLabel(attributeSet, displayLabel);

		DisplayGroup group = new DisplayGroup("+TA1", "Label of TA1", "Description of TA1");
		group.setLabel("GBLSYS");
		group.setLabelType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
		group.setLabelTextOwner(TextBean.OWNER_MISYS);
		group.setDescription("GBL900053");
		group.setDescriptionType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
		group.setDescriptionTextOwner(TextBean.OWNER_MISYS);
		group.setDefaultOpen(false);
		attributeSet.addItem(group);

		DisplayGroup subGroup = new DisplayGroup("+EQN", "Label of EQN", "Description of EQN");
		subGroup.setLabel("GBLUNIT");
		subGroup.setLabelType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
		subGroup.setLabelTextOwner(TextBean.OWNER_MISYS);
		subGroup.setDescription("GBL900049");
		subGroup.setDescriptionType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
		subGroup.setDescriptionTextOwner(TextBean.OWNER_MISYS);
		subGroup.setVisible(DisplayAttributes.VISIBLE_CODE);
		subGroup.setVisibleExpression("true");
		group.addItem(subGroup);

		// Account branch with toggle
		inputField = FunctionToolbox.getInputField("TAB1", "Account branch 1", "This is the account branch", "A", "4", "");
		inputField.setFieldContextType(InputField.FLDCTXTYP_ACN);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setDisplayStyleRow("mystyle");
		displayAttributes.setOnBlurEventScript("var value = getInputFieldValue(fieldId); " + "if (value.trim() == 'Y') " + "{ "
						+ "	setProtectField('CODCCY'); setInputFieldValue('TAB3','TAB3'); " + "	setProtectField('CODVFR'); " + "}");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);

		// Account number with toggle
		inputField = FunctionToolbox.getInputField("TAN1", "Account number 1", "This is the account number", "A", "6", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);

		// Account suffix with toggle
		inputField = FunctionToolbox.getInputField("TAS1", "Account suffix 1", "This is the account suffix", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("GWR76R");
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// External Account number display group
		subGroup = new DisplayGroup("+ALT", "Label of ALT", "Description of ALT");
		group.addItem(subGroup);

		// External account number with toggle
		inputField = FunctionToolbox.getInputField("TEAN1", "External account number 1", "Description of EAN", "A", "20", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("GIR33R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GIR33R", "", true, "N"));
		pvFieldSet.setCache(cache);

		group = new DisplayGroup("TA2", "Label of TA2", "Description of TA2");
		attributeSet.addItem(group);

		subGroup = new DisplayGroup("EQN", "Label of EQN", "Description of EQN");
		group.addItem(subGroup);

		// Account branch with toggle
		inputField = FunctionToolbox.getInputField("TAB2", "Account branch 2", "This is the account branch", "A", "4", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);

		// Account number with toggle
		inputField = FunctionToolbox.getInputField("TAN2", "Account number 2", "This is the account number", "A", "6", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);

		// Account suffix with toggle
		inputField = FunctionToolbox.getInputField("TAS2", "Account suffix 2", "This is the account suffix", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setPrompt("GWR76R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", true, "N"));
		pvFieldSet.setCache(cache);

		subGroup = new DisplayGroup("ALT", "Label of ALT", "Description of ALT");
		group.addItem(subGroup);

		// Label
		displayLabel = FunctionToolbox.getDisplayLabel("label2", "Label only 2", "Desc of Label 2");
		group.addItem(displayLabel);

		// External account number with toggle
		inputField = FunctionToolbox.getInputField("TEAN2", "External account number 2", "Description of EAN", "A", "20", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("GIR33R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GIR33R", "", true, "N"));
		pvFieldSet.setCache(cache);

		subGroup = new DisplayGroup("IBAN", "Label of IBAN", "Description of IBAN");
		group.addItem(subGroup);

		// IBAN field
		inputField = FunctionToolbox.getInputField("TIBAN2", "IBAN account number 2", "Description of IBAN", "A", "20", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("GIR33R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GIR33R", "", true, "N"));
		pvFieldSet.setCache(cache);

		group = new DisplayGroup("TA3", "Label of TA3", "Description of TA3");
		attributeSet.addItem(group);

		subGroup = new DisplayGroup("EQN", "Label of EQN", "Description of EQN");
		group.addItem(subGroup);

		// Account branch with toggle
		inputField = FunctionToolbox.getInputField("TAB3", "Account branch 3", "This is the account branch", "A", "4", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);

		// Account number with toggle
		inputField = FunctionToolbox.getInputField("TAN3", "Account number 3", "This is the account number", "A", "6", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);

		// Account suffix with toggle
		inputField = FunctionToolbox.getInputField("TAS3", "Account suffix 3", "This is the account suffix", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setPrompt("GWR76R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", true, "N"));
		pvFieldSet.setCache(cache);

		subGroup = new DisplayGroup("ALT", "Label of ALT", "ALT");
		group.addItem(subGroup);

		// External account number with toggle
		inputField = FunctionToolbox.getInputField("TEAN3", "External account number 3", "Description of EAN", "A", "20", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("GIR33R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GIR33R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Yes or no (PROTECTION)
		inputField = FunctionToolbox.getInputField("COD", "Currency or Date", "This is the boolean flag", "B", "1", "");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("CTR56R");
		displayAttributes.setWidget("IDB");
		displayAttributes.setOnBlurEventScript("var value = getInputFieldValue('COD'); " + "if (value == 'Y') " + "{ "
						+ "	setProtectField('CODCCY'); " + "	setProtectField('CODCCY2'); " + "	setProtectField('CODCCY3'); "
						+ "	setUnprotectField('YNOPROT');" + "	setUnprotectField('IDBPROT');" + "	setUnprotectField('FRQPROT');"
						+ "	setUnprotectField('VVPROT');" + "	setUnprotectField('CODVFR'); " + "} " + "else " + "{"
						+ "	setProtectField('CODVFR');" + "	setProtectField('YNOPROT');" + "	setProtectField('IDBPROT');"
						+ "	setProtectField('FRQPROT');" + "	setProtectField('VVPROT');" + "	setUnprotectField('CODCCY');"
						+ "	setUnprotectField('CODCCY2');" + "	setUnprotectField('CODCCY3');" + "}");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "CTR56R", "", true, "N"));
		pvFieldSet.setCache(cache);

		pvFieldSet.setExecuteMode(FieldSet.EXECUTE_NEVER);
		group.addItem(displayAttributes);
		// FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Currency
		inputField = FunctionToolbox.getInputField("CODCCY", "Currency choice", "This is the currency choice", "A", "3", "");
		inputField.setInitialValue("GBP");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("C8R01R");
		displayAttributes.setWidget("EXF");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Currency
		inputField = FunctionToolbox.getInputField("CODCCY2", "Currency choice 2 (server prot)", "This is the currency choice",
						"A", "3", "");
		inputField.setInitialValue("GBP");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setPrompt("C8R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Currency
		inputField = FunctionToolbox.getInputField("CODCCY3", "Currency choice 2 (server hidden)", "This is the currency choice",
						"A", "3", "");
		inputField.setInitialValue("GBP");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		displayAttributes.setPrompt("C8R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Value date
		inputField = FunctionToolbox.getInputField("CODVFR", "Date choice", "This is the date choice", "D", "7", "");
		inputField.setInitialValue("040100");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("CAL");
		displayAttributes.setLinkedFuncId("HCX");
		displayAttributes.setLinkedFuncContext("HLD");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Yes or no (protected)
		inputField = FunctionToolbox.getInputField("YNOPROT", "Yes/No protect default", "This is the boolean flag", "B", "1", "");
		inputField.setInitialValue("Y");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		// displayAttributes.setWidget("YNO");
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// List (protected)
		inputField = FunctionToolbox.getInputField("IDBPROT", "IDB protect default", "This is the boolean flag", "A", "1", "");
		inputField.setInitialValue("1");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("IDB");
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Frequency (protected)
		inputField = FunctionToolbox
						.getInputField("FRQPROT", "Frequency protect default", "This is the boolean flag", "A", "3", "");
		inputField.setInitialValue("V13");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("FRQ");
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Valid value (protected)
		inputField = FunctionToolbox.getInputField("VVPROT", "Valid valuedefault", "This is the boolean flag", "A", "3", "");
		inputField.setInitialValue("105");
		inputField.setValidValues("100:101:102:103:104:105");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("CTR56R");
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "CTR56R", "", true, "N"));
		pvFieldSet.setExecuteMode(FieldSet.EXECUTE_NEVER);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet.setCache(cache);

		// Yes or no (VISIBILITY)
		inputField = FunctionToolbox.getInputField("HCOD", "Currency or Date", "This is the boolean flag", "A", "1", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("YNO");
		displayAttributes.setOnBlurEventScript("var value = getInputFieldValue('HCOD'); " + "if (value == 'Y') " + "{ "
						+ "	setInvisibleField('HCODCCY'); " + "	setInvisibleField('HCODCCY2'); "
						+ "	setInvisibleField('HCODCCY3'); " + "	setVisibleField('HYNOPROT');" + "	setVisibleField('HIDBPROT');"
						+ "	setVisibleField('HFRQPROT');" + "	setVisibleField('HVVPROT');" + "	setVisibleField('HCODVFR'); " + "} "
						+ "else " + "{" + "	setInvisibleField('HCODVFR');" + "	setInvisibleField('HYNOPROT');"
						+ "	setInvisibleField('HIDBPROT');" + "	setInvisibleField('HFRQPROT');" + "	setInvisibleField('HVVPROT');"
						+ "	setVisibleField('HCODCCY');" + "	setVisibleField('HCODCCY2');" + "	setVisibleField('HCODCCY3');" + "}");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Currency
		inputField = FunctionToolbox.getInputField("HCODCCY", "Currency choice", "This is the currency choice", "A", "3", "");
		inputField.setInitialValue("GBP");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("C8R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Currency
		inputField = FunctionToolbox.getInputField("HCODCCY2", "Currency choice 2 (server prot)", "This is the currency choice",
						"A", "3", "");
		inputField.setInitialValue("GBP");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setPrompt("C8R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Currency
		inputField = FunctionToolbox.getInputField("HCODCCY3", "Currency choice 2 (server hidden)", "This is the currency choice",
						"A", "3", "");
		inputField.setInitialValue("GBP");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		displayAttributes.setPrompt("C8R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Value date
		inputField = FunctionToolbox.getInputField("HCODVFR", "Date choice", "This is the date choice", "D", "7", "");
		inputField.setInitialValue("040100");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		displayAttributes.setWidget("CAL");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Yes or no (hidden)
		inputField = FunctionToolbox.getInputField("HYNOPROT", "Yes/No protect default", "This is the boolean flag", "A", "1", "");
		inputField.setInitialValue("Y");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("YNO");
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		displayAttributes.setLinkedFuncId("HC3");
		displayAttributes.setLinkedFuncContext("HLD");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// List (hidden)
		inputField = FunctionToolbox.getInputField("HIDBPROT", "IDB protect default", "This is the boolean flag", "A", "1", "");
		inputField.setInitialValue("1");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("IDB");
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Frequency (hidden)
		inputField = FunctionToolbox.getInputField("HFRQPROT", "Frequency protect default", "This is the boolean flag", "A", "3",
						"");
		inputField.setInitialValue("V13");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		displayAttributes.setWidget("FRQ");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Frequency (hidden)
		inputField = FunctionToolbox.getInputField("HFRQPROT2", "Frequency protect default", "This is the boolean flag", "A", "3",
						"");
		inputField.setInitialValue("V15");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_YES);
		displayAttributes.setWidget("FRQ");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Valid value (hidden)
		inputField = FunctionToolbox.getInputField("HVVPROT", "Valid valuedefault", "This is the boolean flag", "A", "3", "");
		inputField.setInitialValue("101");
		inputField.setValidValues("100:101:102:103:104:105");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("IDB");
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// S1B
		inputField = FunctionToolbox.getInputField("S1B", "S1B", "S1B", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("S1B");
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_YES);
		displayAttributes.setOnBlurEventScript("var value = getInputFieldValue(fieldId); " + "if (value.trim() == '1') " + "{ "
						+ "	setProtectField('SYB'); setInputFieldValue('SYB','Y'); " + "}" + "else " + "{"
						+ "	setUnprotectField('SYB');" + "	setInputFieldValue('SYB','');" + "}");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// SYB
		inputField = FunctionToolbox.getInputField("SYB", "SYB", "SYB", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("SYB");
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_YES);
		displayAttributes.setOnBlurEventScript("var value = getInputFieldValue(fieldId); " + "if (value.trim() == '') " + "{ "
						+ "	setVisibleField('R1B'); " + "}" + "else " + "{" + "	setInvisibleField('R1B');" + "}");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// R1B
		inputField = FunctionToolbox.getInputField("R1B", "R1B", "R1B", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("R1B");
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Numeric
		inputField = FunctionToolbox.getInputField("NUMERIC", "NUMERICB", "NUMERIC", "S", "5", "1");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		displayAttributes.setEditCode(EqDataType.EDIT_AMOUNT_WITH_REPLACE);
		displayAttributes.setEditCodeParameterStatus(DisplayAttributes.EDIT_PARAMETER_SCRIPT);
		displayAttributes.getEditCodeParameterReplacements().add(new ReplacementToken("*HIVAL", "'Maximum value'"));
		displayAttributes.getEditCodeParameterReplacements().add(new ReplacementToken("*LOVAL", "'Minimum value'"));
		displayAttributes.getEditCodeParameterReplacements().add(new ReplacementToken("1", "'One'"));
		displayAttributes.getEditCodeParameterReplacements().add(new ReplacementToken("10", "'Ten'"));
		displayAttributes.getEditCodeParameterReplacements().add(
						new ReplacementToken("20", "fn:parseDouble(NUMERIC)==20? 'twenty' : 'thirty'"));
		displayAttributes.getEditCodeParameterReplacements().add(
						new ReplacementToken("30", "fn:parseDouble(NUMERIC)==20? 'twenty' : 'thirty'"));

		// Float
		inputField = FunctionToolbox.getInputField("FLOAT", "FLOAT 10.55-11.581", "FLOAT", "P", "15", "5");
		inputField.setInitialValue("10.55");
		inputField.setMinLength("10.55");
		inputField.setMaxLength("11.58");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		return fieldSetWrapper;
	}
	private DisplayFieldSetWrapper addRecord2(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("REC2", "Record 2 of ALZ", "Description 2 of ALZ");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// add all the fields in this section ---------------------------------------

		DisplayGroup group = new DisplayGroup("GROUP", "Label of TA1", "Description of TA1");
		attributeSet.addItem(group);

		DisplayGroup subGroupBD = new DisplayGroup("BD", "Basic details", "Description Basic details");
		group.addItem(subGroupBD);

		DisplayGroup subGroupAD = new DisplayGroup("AD", "Additional details", "Description Additional details");
		group.addItem(subGroupAD);

		// Currency
		inputField = FunctionToolbox.getInputField("CCY", "Currency", "This is the currency", "A", "3", "");
		inputField.setInitialValue("GBP");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("C8R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupBD.addItem(displayAttributes);
		PVFieldSet pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "",
						true, "N"));
		pvFieldSet.setCache(cache);

		// Amount
		inputField = FunctionToolbox.getInputField("AMT", "Amount", "This is the amount", "P", "15", "0");
		inputField.setInitialValue("1T");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupBD.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV30R", "", true, "N"));

		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "UTR71R", "", true, "N"));
		pvFieldSet.getPVField("@69LCY").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.getPVField("@69DBR").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.getPVField("@69DR").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.getPVField("@69USR").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.setIgnoreMessages(0);
		pvFieldSet.setCache(cache);

		// Value date
		inputField = FunctionToolbox.getInputField("VFR", "Value date", "This is the value date", "D", "7", "");
		inputField.setInitialValue("040100");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("CAL");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupBD.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Reference
		inputField = FunctionToolbox.getInputField("DRF", "Reference", "This is the reference", "A", "20", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setMaxLength("20");
		inputField.setMinLength("5");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabelScriptRunTime("fn:concat('Reference: ',CCY)");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupBD.addItem(displayAttributes);

		// Narrative 1
		inputField = FunctionToolbox.getInputField("NR1", "Narrative 1", "This is the narrative 1", "A", "35", "");
		inputField.setMaxLength("20");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupBD.addItem(displayAttributes);

		// Narrative 2
		inputField = FunctionToolbox.getInputField("NR2", "Narrative 2", "This is the narrative 2", "A", "35", "");
		inputField.setInitialValueType(InputField.INIT_VALUE_ENH);
		inputField.setInitialValue("K451");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupBD.addItem(displayAttributes);

		// Narrative 3
		inputField = FunctionToolbox.getInputField("NR3", "Narrative 3", "This is the narrative 3", "A", "35", "");
		inputField.setInitialValue("ACMD001");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupBD.addItem(displayAttributes);

		// Narrative 4
		inputField = FunctionToolbox.getInputField("NR4", "Narrative 4", "This is the narrative 4", "A", "35", "");
		inputField.setInitialValue("$PDATE");
		inputField.setInitialValueType(WorkField.INIT_VALUE_DASYS);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupBD.addItem(displayAttributes);

		// Sundry item
		inputField = FunctionToolbox.getInputField("SRC", "Sundry item", "This is the sundry item", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupBD.addItem(displayAttributes);

		// User code 1
		inputField = FunctionToolbox.getInputField("UC1", "User code 1", "This is the user code 1", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("IDB");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupBD.addItem(displayAttributes);

		// User code 2
		inputField = FunctionToolbox.getInputField("UC2", "User code 2", "This is the user code 2", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupBD.addItem(displayAttributes);

		// Number of items
		inputField = FunctionToolbox.getInputField("NPE", "Number of items", "This is the number of items", "P", "5", "0");
		inputField.setInitialValue("1");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setUpperCase(false);
		inputField.setMaxLength("1");
		inputField.setMinLength("1");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupBD.addItem(displayAttributes);

		// Posting reference
		inputField = FunctionToolbox.getInputField("PBR", "Posting reference", "This is the posting reference", "A", "4", "");
		inputField.setInitialValue("EQDE");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setMaxLength("4");
		inputField.setMinLength("4");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupBD.addItem(displayAttributes);

		// Frequency
		inputField = FunctionToolbox.getInputField("FRQ", "Frequency", "This is the frequency", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("FRQ");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupBD.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV13R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Base code
		inputField = FunctionToolbox.getInputField("BRR", "Base code", "This is the base code", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("D4R47R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "D4R47R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Yes or no
		inputField = FunctionToolbox.getInputField("YNI", "Yes or No", "This is the boolean flag", "A", "1", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("YNO");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);

		// Customer type
		inputField = FunctionToolbox.getInputField("CTP", "Customer type", "This is the customer type", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("C4R54R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C4R54R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Analysis code
		inputField = FunctionToolbox.getInputField("C1", "Analysis code", "This is the analysis code", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("GKR40R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GKR40R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Rate 1
		inputField = FunctionToolbox.getInputField("RAT1", "Rate 1", "This is the rate 1", "P", "10", "5");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV28R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Rate 2
		inputField = FunctionToolbox.getInputField("RAT2", "Rate 2", "This is the rate 1", "P", "15", "7");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV28R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Date 1
		inputField = FunctionToolbox.getInputField("DAT1", "Date 1", "This is the date 1", "D", "7", "");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("CAL");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Date 2
		inputField = FunctionToolbox.getInputField("DAT2", "Date 2", "This is the date 2", "D", "7", "");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("CAL");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Date 3
		inputField = FunctionToolbox.getInputField("DAT3", "Date 3", "This is the date 3", "D", "7", "");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("CAL");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Number of digits
		inputField = FunctionToolbox.getInputField("DIGIT", "Number of digits", "This is the number of digits for the amounts",
						"S", "2", "0");
		inputField.setInitialValue("15");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);

		// Number of decimal
		inputField = FunctionToolbox.getInputField("DECI", "Number of decimals", "This is the number of decimals for the amounts",
						"S", "1", "0");
		inputField.setInitialValue("0");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);

		// Deal branch
		inputField = FunctionToolbox.getInputField("DBRN", "Deal branch", "This is the deal branch", "A", "4", "");
		inputField.setInitialValue("ACC1");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);

		// Deal type
		inputField = FunctionToolbox.getInputField("DDLP", "Deal type", "This is the deal type", "A", "3", "");
		inputField.setInitialValue("RDS");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);

		// Deal reference
		inputField = FunctionToolbox.getInputField("DDLR", "Deal reference", "This is the deal reference", "A", "13", "");
		inputField.setInitialValue("133463");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("OSR33R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "OSR33R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Dummy field
		inputField = FunctionToolbox.getInputField("DTMP", "Dummy  field", "This is a dummy field", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);

		// Dummy field 2
		inputField = FunctionToolbox.getInputField("DTMP2", "Dummy  field 2", "This is a dummy field 2", "A", "20", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);

		// Hold code with API as PV
		inputField = FunctionToolbox.getInputField("HLDPV", "Hold code with API as PV", "Hold code with API as PV", "A", "3", "");
		inputField.setInitialValue("HPV");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);
		APIFieldSet apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "HCIPV", "HCI", "J46F", "Add/Maintain Hold Code",
						IEquationStandardObject.FCT_ADD);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.cvtAPIToPVFieldSet(apiFieldSet));
		pvFieldSet.setCache(cache);

		// Hold code description
		inputField = FunctionToolbox.getInputField("DSCPV", "Hold code desc", "Hold code desc", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);

		// Hold code with API as Table PV
		inputField = FunctionToolbox.getInputField("HLDTABPV", "Hold code with API as Table PV", "Hold code with API as Table PV",
						"A", "3", "");
		inputField.setInitialValue("HPV");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);
		apiFieldSet = FunctionToolbox.getTableFieldSet(session, "HCITABPV", "JV10LF", "Hold test", "JVHRC", "JVPF", "", false,
						false);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.cvtAPIToPVFieldSet(apiFieldSet));
		pvFieldSet.setCache(cache);

		// Hold code description
		inputField = FunctionToolbox.getInputField("DSCTABPV", "Hold code desc", "Hold code desc", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);

		// Hold code with API as Equation Service PV
		inputField = FunctionToolbox.getInputField("HLDSRVPV", "Hold code with API as Eq Srv PV",
						"Hold code with API as Eq Srv PV", "A", "3", "");
		inputField.setInitialValue("HPV");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);
		apiFieldSet = FunctionToolbox.getServiceFieldSet(session, "HCISRVPV", "HCX", "Add/Maintain Hold Code",
						IEquationStandardObject.FCT_ADD, false);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.cvtAPIToPVFieldSet(apiFieldSet));
		pvFieldSet.setCache(cache);

		// Hold code description
		inputField = FunctionToolbox.getInputField("DSCSRVPV", "Hold code desc", "Hold code desc", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);

		// Enquiry test
		inputField = FunctionToolbox.getInputField("ADDRACC", "Customer of 0000-000001-001", "Customer of 0000-000001-001", "A",
						"50", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupAD.addItem(displayAttributes);
		apiFieldSet = FunctionToolbox.getEnquiryFieldSet(session, "ENQPV", "ABE", "H69E", "HZH691",
						"Account Basic Details Enquiry", false);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.cvtAPIToPVFieldSet(apiFieldSet));
		pvFieldSet.getPVField("HZAB").setValidateAssignment(Field.ASSIGNMENT_SCRIPT);
		pvFieldSet.getPVField("HZAN").setValidateAssignment(Field.ASSIGNMENT_SCRIPT);
		pvFieldSet.getPVField("HZAS").setValidateAssignment(Field.ASSIGNMENT_SCRIPT);
		pvFieldSet.getPVField("HZAB").setValidateScript("'0000'");
		pvFieldSet.getPVField("HZAN").setValidateScript("'000001'");
		pvFieldSet.getPVField("HZAS").setValidateScript("'001'");
		pvFieldSet.setCache(cache);

		return fieldSetWrapper;
	}
	private DisplayFieldSetWrapper addRecord3(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("REC3", "Record 3 of ALZ", "Description 3 of ALZ");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// add all the fields in this section ---------------------------------------

		// Mandatory script testing 1
		inputField = FunctionToolbox.getInputField("XMandatory", "This is not mandatory", "This is mandatory script testing", "A",
						"4", "");
		inputField.setInitialValue("MAND");
		inputField.setMandatory(InputField.MANDATORY_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Mandatory script testing 2
		inputField = FunctionToolbox.getInputField("XMandatory2", "This is mandatory", "This is mandatory script testing", "A",
						"4", "");
		inputField.setInitialValue("MAND");
		inputField.setMandatory(InputField.MANDATORY_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Visible script testing 1
		inputField = FunctionToolbox.getInputField("XVisible", "This is not visible", "This is visible script testing", "A", "4",
						"");
		inputField.setInitialValue("VISI");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_CODE);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Visible script testing 2
		inputField = FunctionToolbox.getInputField("XVisible2", "This is visible", "This is visible script testing", "A", "4", "");
		inputField.setInitialValue("VISI");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_CODE);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Protected script testing 1
		inputField = FunctionToolbox.getInputField("XProtected", "This is not protected", "This is protected script testing", "A",
						"4", "");
		inputField.setInitialValue("PROT");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_CODE);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Protected script testing 2
		inputField = FunctionToolbox.getInputField("XProtected2", "This is protected", "This is protected script testing", "A",
						"4", "");
		inputField.setInitialValue("PROT");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_CODE);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Valid field checking
		inputField = FunctionToolbox.getInputField("XValid", "Valid field", "This is valid script testing", "A", "4", "");
		inputField.setInitialValue("Valid");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Valid min/max checking
		inputField = FunctionToolbox.getInputField("XValid2", "Valid min/max (100-200)", "This is valid min/max testing", "P", "5",
						"0");
		inputField.setInitialValue("150");
		inputField.setMinLength("100");
		inputField.setMaxLength("200");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Valid number checking
		inputField = FunctionToolbox.getInputField("XValid3", "Valid value (150,160,170))", "This is valid number testing", "S",
						"5", "0");
		inputField.setInitialValue("150");
		inputField.setMinLength("100");
		inputField.setMaxLength("200");
		inputField.setValidValues("00150:00160:00170:100.50");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Regular expression checking
		inputField = FunctionToolbox.getInputField("XReg", "Regular expression (*REGULAR*)", "This is regular expression testing",
						"A", "35", "");
		inputField.setInitialValue("REGULAR");
		inputField.setRegExp(".*REGULAR.*");
		inputField.setRegExpMsg("This is an info");
		inputField.setRegExpSev(FunctionMessages.MSG_INFO);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Default input/output via user exit
		inputField = FunctionToolbox.getInputField("XInput", "Default input/output via user exit",
						"This is default input/output user exit testing", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Default primitive via user exit
		inputField = FunctionToolbox.getInputField("XPrimitive", "Default primitive via user exit",
						"This is default input/output user exit testing", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Default P/V fields
		inputField = FunctionToolbox.getInputField("XAMT", "Default PV field for amount", "This is default P/V user exit testing",
						"P", "15", "0");
		inputField.setUpperCase(false);
		inputField.setDefaultValue("10T");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		PVFieldSet pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV30R", "",
						false, "N"));
		pvFieldSet.getPVField("$NDPAM").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.getPVField("$NODIG").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.setCache(cache);

		// Masking
		inputField = FunctionToolbox.getInputField("XMASK", "This is the masked field",
						"This is default input/output user exit testing", "A", "50", "");
		inputField.setInitialValue("442033205082");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setMask("(+##) 0## #### ####");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		DisplayGroup group = new DisplayGroup("TA4", "TA4", "TA4");
		attributeSet.addItem(group);

		DisplayGroup subGroup = new DisplayGroup("EQN", "EQN", "EQN");
		group.addItem(subGroup);

		// Account branch with toggle
		inputField = FunctionToolbox.getInputField("TAB4", "Account branch 4", "This is the account branch", "A", "4", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);

		// Account number with toggle
		inputField = FunctionToolbox.getInputField("TAN4", "Account number 4", "This is the account number", "A", "6", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);

		// Account suffix with toggle
		inputField = FunctionToolbox.getInputField("TAS4", "Account suffix 4", "This is the account suffix", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setPrompt("GWR76R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", true, "N"));
		pvFieldSet.setCache(cache);

		subGroup = new DisplayGroup("ALT", "ALT", "ALT");
		group.addItem(subGroup);
		// Note, this used to specify system option selection information

		// External account number with toggle
		inputField = FunctionToolbox.getInputField("TEAN4", "External account number 4", "Description of EAN", "A", "20", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("GIR33R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GIR33R", "", true, "N"));
		pvFieldSet.setCache(cache);

		// Hold code
		inputField = FunctionToolbox.getInputField("HLD", "Hold code", "This is hold code", "A", "3", "");
		inputField.setInitialValue("ALZ");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setAllowPredictivePrompt(true);
		displayAttributes.setPrompt("JVR01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "JVR01R", "", true, "N"));
		pvFieldSet.setCache(cache);

		return fieldSetWrapper;
	}

	private void addLoadAPI(FunctionGenerator fg)
	{
		// APIFieldSet apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "HCIID", "HCI", "J46F", "GZJ461",
		// "Add/Maintain/Delete Hold Code");
		// fg.addLoadAPIFieldSet(apiFieldSet, FunctionToolbox.getPVFieldSet(session, "JVR01R", "", false, ""));
	}

	private void addUpdateAPI(FunctionGenerator fg) throws EQException
	{
		APIFieldSet apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "ASIID", "ASI", "H15A", "Add Sundry Item",
						IEquationStandardObject.FCT_ADD);
		apiFieldSet.setAlwaysApplyInExtInput(true);
		fg.addUpdateAPIFieldSet(apiFieldSet);

		apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "LID", "HCI", "J46F", "Add/Maintain/Delete Hold Code",
						IEquationStandardObject.FCT_MNT);
		apiFieldSet.setAlwaysApplyInRecovery(true);
		fg.addUpdateAPIFieldSet(apiFieldSet);

		fg.getFunction().addReservedFieldSet(Function.CRM_FIELDSET, "Description of CRM");
		fg.getFunction().addReservedFieldSet(Function.EFC_FIELDSET, "Description of EFC");
		fg.getFunction().addReservedFieldSet(Function.GY_FIELDSET, "Description of GY");
		fg.getFunction().addReservedFieldSet(Function.G5_FIELDSET, "Description of G5");
	}

	private void addMappings(FunctionGenerator fg) throws EQException
	{
		// add validate mapping
		fg.addValidateMappingToPV("PRIMARY", "AB", "PRIMARY", "AS", "GWR76R", "$R76AB");
		fg.addValidateMappingToPV("PRIMARY", "AN", "PRIMARY", "AS", "GWR76R", "$R76AN");
		fg.addValidateMappingToPV("PRIMARY", "AS", "PRIMARY", "AS", "GWR76R", "$R76AS");
		fg.addValidateMappingFromPV("PRIMARY", "AS", "GWR76R", "$R76AB", "PRIMARY", "AB", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "AS", "GWR76R", "$R76AN", "PRIMARY", "AN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "AS", "GWR76R", "$R76AS", "PRIMARY", "AS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "AS", "GWR76R", "$R76SN", "PRIMARY", "AB", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("PRIMARY", "AS", "GWR76R", "$R76SN", "REC3", "XInput", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("REC2", "CCY", "REC2", "AMT", "GWV30R", "$CCYAM");
		fg.addValidateMappingToPV("REC2", "AMT", "REC2", "AMT", "GWV30R", "$INPAM");
		fg.addValidateMappingToPV("REC2", "DIGIT", "REC2", "AMT", "GWV30R", "$NODIG");
		fg.addValidateMappingToPV("REC2", "DECI", "REC2", "AMT", "GWV30R", "$NDPAM");
		fg.addValidateMappingFromPV("REC2", "AMT", "GWV30R", "$EDTAM", "REC2", "AMT", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("REC2", "AMT", "GWV30R", "$NUMAM", "REC2", "AMT", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("REC2", "AMT", "GWV30R", "$NUMAM", "REC2", "DTMP2", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingToPV("REC2", "DTMP2", "REC2", "AMT", "UTR71R", "@69AMT");
		fg.addValidateMappingToPV("REC2", "CCY", "REC2", "AMT", "UTR71R", "@69CCY");
		fg.addValidateMappingToPV("REC2", "AB", "REC2", "AMT", "UTR71R", "@69ABR");
		fg.addValidateMappingToPV("REC2", "DDLP", "REC2", "DDLR", "OSR33R", "$DLP");
		fg.addValidateMappingToPV("REC2", "DDLR", "REC2", "DDLR", "OSR33R", "$DLR");
		fg.addValidateMappingToPV("REC2", "DBRN", "REC2", "DDLR", "OSR33R", "$BRNM");
		fg.addValidateMappingFromPV("REC2", "DDLR", "OSR33R", "$BRNM", "REC2", "DBRN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("REC2", "DDLR", "OSR33R", "$DLP", "REC2", "DDLP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("REC2", "DDLR", "OSR33R", "$DLP", "REC2", "DDLP", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("REC2", "DDLR", "OSR33R", "$DLR", "REC2", "DDLR", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("REC2", "DDLR", "OSR33R", "$DLR", "REC2", "DDLR", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("REC2", "DDLR", "OSR33R", "$CUS", "REC2", "DTMP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingToPV("REC2", "CCY", "REC3", "XAMT", "GWV30R", "$CCYAM");
		fg.addValidateMappingToPV("REC3", "XAMT", "REC3", "XAMT", "GWV30R", "$INPAM");
		fg.addValidateMappingFromPV("REC3", "XAMT", "GWV30R", "$NUMAM", "REC3", "XAMT", MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("PRIMARY", "TAB1", "PRIMARY", "TAS1", "GWR76R", "$R76AB");
		fg.addValidateMappingToPV("PRIMARY", "TAN1", "PRIMARY", "TAS1", "GWR76R", "$R76AN");
		fg.addValidateMappingToPV("PRIMARY", "TAS1", "PRIMARY", "TAS1", "GWR76R", "$R76AS");
		fg.addValidateMappingFromPV("PRIMARY", "TAS1", "GWR76R", "$R76AB", "PRIMARY", "TAB1", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "TAS1", "GWR76R", "$R76AN", "PRIMARY", "TAN1", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "TAS1", "GWR76R", "$R76AS", "PRIMARY", "TAS1", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "TAS1", "GWR76R", "$R76SN", "PRIMARY", "TAB1", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("PRIMARY", "TAS1", "GWR76R", "$R76EZ", "PRIMARY", "TEAN1", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "TAS1", "GWR76R", "$R76SN", "PRIMARY", "TAS1", MappingToolbox.TYPE_OUTPUT);

		// add validate mapping
		fg.addValidateMappingToPV("REC2", "HLDPV", "REC2", "HLDPV", "HCIPV", "GZHRC");
		fg.addValidateMappingFromPV("REC2", "HLDPV", "HCIPV", "GZHRC", "REC2", "HLDPV", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("REC2", "HLDPV", "HCIPV", "GZHRD", "REC2", "DSCPV", MappingToolbox.TYPE_PRIMITIVE);

		// add validate mapping
		fg.addValidateMappingToPV("REC2", "HLDTABPV", "REC2", "HLDTABPV", "HCITABPV", "JVHRC");
		fg.addValidateMappingFromPV("REC2", "HLDTABPV", "HCITABPV", "JVHRC", "REC2", "HLDTABPV", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("REC2", "HLDTABPV", "HCITABPV", "JVHRD", "REC2", "DSCTABPV", MappingToolbox.TYPE_PRIMITIVE);

		// add validate mapping
		fg.addValidateMappingToPV("REC2", "HLDSRVPV", "REC2", "HLDSRVPV", "HCISRVPV", "HLD");
		fg.addValidateMappingFromPV("REC2", "HLDSRVPV", "HCISRVPV", "HLD", "REC2", "HLDSRVPV", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("REC2", "HLDSRVPV", "HCISRVPV", "DSC", "REC2", "DSCSRVPV", MappingToolbox.TYPE_PRIMITIVE);

		// add validate mapping
		fg.addValidateMappingFromPV("REC2", "ADDRACC", "ENQPV", "HZCUS", "REC2", "ADDRACC", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("REC2", "ADDRACC", "ENQPV", "HZCUS", "REC2", "ADDRACC", MappingToolbox.TYPE_OUTPUT);

		// add update mapping
		fg.addUpdateMapping("PRIMARY", "AB", "ASIID", "GZAB");
		fg.addUpdateMapping("PRIMARY", "AN", "ASIID", "GZAN");
		fg.addUpdateMapping("PRIMARY", "AS", "ASIID", "GZAS");
		fg.addUpdateMapping("PRIMARY", "BRNM", "ASIID", "GZBRNM");
		fg.addUpdateMapping("PRIMARY", "VFR", "ASIID", "GZVFR");
		fg.addUpdateMapping("PRIMARY", "BRNM", "ASIID", "GZBRND");
		fg.addUpdateMapping("PRIMARY", "DRF", "ASIID", "GZDRF");
		fg.addUpdateMapping("PRIMARY", "AMT", "ASIID", "GZAMA");
		fg.addUpdateMapping("PRIMARY", "TCD", "ASIID", "GZTCD");
		fg.addUpdateMapping("REC2", "CCY", "ASIID", "GZCCY");
		fg.addUpdateMapping("REC2", "SRC", "ASIID", "GZSRC");
		fg.addUpdateMapping("REC2", "UC1", "ASIID", "GZUC1");
		fg.addUpdateMapping("REC2", "UC2", "ASIID", "GZUC2");
		fg.addUpdateMapping("REC2", "NPE", "ASIID", "GZNPE");
		fg.addUpdateMapping("REC2", "NR1", "ASIID", "GZNR1");
		fg.addUpdateMapping("REC2", "NR2", "ASIID", "GZNR2");
		fg.addUpdateMapping("REC2", "NR3", "ASIID", "GZNR3");
		fg.addUpdateMapping("REC2", "NR4", "ASIID", "GZNR4");
		fg.addUpdateMapping("REC2", "CCY", "ASIID", "GZTCCY");
		fg.addUpdateMapping("REC2", "AMT", "ASIID", "GZTAMA");
		fg.addUpdateMapping("REC2", "CCY", "ASIID", "GZHCCY");
		fg.addUpdateMapping("REC2", "PBR", "ASIID", "GZPBR");

		// add update mapping
		fg.addUpdateMapping("REC3", "HLD", "LID", "GZHRC");
		fg.addUpdateMapping("PRIMARY", "DRF", "LID", "GZHRD");

		// journal mapping
		fg.addUpdateMapping("REC2", "AC", Function.GY_FIELDSET, "GYWHO");
		fg.addUpdateMapping("PRIMARY", "NR1", Function.GY_FIELDSET, "GYSHN");
		fg.addUpdateMapping("REC2", "DRF", Function.GY_FIELDSET, "GYJREF");
		fg.addUpdateMapping("REC2", "DRF", Function.GY_FIELDSET, "GYIREF");
		fg.addUpdateMapping("REC2", "DRF", Function.GY_FIELDSET, "GYAPID");
		fg.addUpdateMapping("PRIMARY", "TCD", Function.GY_FIELDSET, "GYAPP");
		fg.addUpdateMapping("PRIMARY", "NR2", Function.GY_FIELDSET, "GYAUID");
		fg.addUpdateMapping("PRIMARY", "NR3", Function.GY_FIELDSET, "GYSUID");

		fg.addUpdateMapping("REC2", "AC", Function.G5_FIELDSET, "G5WHO");
		fg.addUpdateMapping("PRIMARY", "NR1", Function.G5_FIELDSET, "G5SHN");
		fg.addUpdateMapping("REC2", "DRF", Function.G5_FIELDSET, "G5JREF");
		fg.addUpdateMapping("PRIMARY", "TCD", Function.G5_FIELDSET, "G5APP");
		fg.addUpdateMapping("PRIMARY", "DRF", Function.G5_FIELDSET, "G5N01");
		fg.addUpdateMapping("PRIMARY", "NR1", Function.G5_FIELDSET, "G5N02");
		fg.addUpdateMapping("PRIMARY", "NR2", Function.G5_FIELDSET, "G5N03");
		fg.addUpdateMapping("PRIMARY", "NR3", Function.G5_FIELDSET, "G5N04");
		fg.addUpdateMapping("PRIMARY", "NR4", Function.G5_FIELDSET, "G5N05");
		fg.addUpdateMapping("PRIMARY", "DRF", Function.G5_FIELDSET, "G5N06");
		fg.addUpdateMapping("PRIMARY", "NR1", Function.G5_FIELDSET, "G5N07");
		fg.addUpdateMapping("PRIMARY", "NR2", Function.G5_FIELDSET, "G5N08");
		fg.addUpdateMapping("PRIMARY", "NR3", Function.G5_FIELDSET, "G5N09");
		fg.addUpdateMapping("PRIMARY", "NR4", Function.G5_FIELDSET, "G5N010");

		// efc mapping
		// fg.addUpdateMapping("PRIMARY", "AB", Function.EFC_FIELDSET, "ENEW");
		fg.addUpdateMapping("PRIMARY", "AB", Function.EFC_FIELDSET, "EDAB"); // debit details
		fg.addUpdateMapping("PRIMARY", "AN", Function.EFC_FIELDSET, "EDAN");
		fg.addUpdateMapping("PRIMARY", "AS", Function.EFC_FIELDSET, "EDAS");
		fg.addUpdateMapping("REC2", "AMT", Function.EFC_FIELDSET, "EDAMT");
		fg.addUpdateMapping("REC2", "CCY", Function.EFC_FIELDSET, "EDCCY");
		fg.addUpdateMapping("PRIMARY", "AB", Function.EFC_FIELDSET, "ECAB"); // credit details
		fg.addUpdateMapping("PRIMARY", "AN", Function.EFC_FIELDSET, "ECAN");
		fg.addUpdateMapping("PRIMARY", "AS", Function.EFC_FIELDSET, "ECAS");
		fg.addUpdateMapping("REC2", "AMT", Function.EFC_FIELDSET, "ECAMT");
		fg.addUpdateMapping("REC2", "CCY", Function.EFC_FIELDSET, "ECCCY");
		// fg.addUpdateMapping("REC2", "DBRN", Function.EFC_FIELDSET, "EBRNM"); // deal details
		// fg.addUpdateMapping("REC2", "DDLP", Function.EFC_FIELDSET, "EDLP");
		// fg.addUpdateMapping("REC2", "DDLR", Function.EFC_FIELDSET, "EDLR");
		// fg.addUpdateMapping("REC2", "NR2", Function.EFC_FIELDSET, "ECMR"); // commitment details
		fg.addUpdateMapping("REC2", "DRF", Function.EFC_FIELDSET, "ETREF"); // common
		fg.addUpdateMapping("REC2", "VFR", Function.EFC_FIELDSET, "ETDTE");
		fg.addUpdateMapping("REC2", "NR1", Function.EFC_FIELDSET, "EXREF");
		fg.addUpdateMapping("REC2", "DIGIT", Function.EFC_FIELDSET, "ESQN");

		// crm mapping
		fg.addUpdateMapping("PRIMARY", "AB", Function.CRM_FIELDSET, "HHAB");
		fg.addUpdateMapping("PRIMARY", "AN", Function.CRM_FIELDSET, "HHAN");
		fg.addUpdateMapping("PRIMARY", "AS", Function.CRM_FIELDSET, "HHAS");
		fg.addUpdateMapping("REC2", "CCY", Function.CRM_FIELDSET, "HHCY1");
		fg.addUpdateMapping("REC2", "AMT", Function.CRM_FIELDSET, "HHAMC");
		fg.addUpdateMapping("REC", "", Function.CRM_FIELDSET, "HHODC");
		fg.addUpdateMapping("REC", "NR3", Function.CRM_FIELDSET, "HHFCT");

		// context mapping
		fg.addContextMapping("AB", "$CBBN");
		fg.addContextMapping("AN", "$CBNO");
		fg.addContextMapping("AS", "$CSFX");
	}

	public void test()
	{
		// Have a bash...
		try
		{
			printStartStatus(this.getClass().getSimpleName());
			FunctionGenerator fg = new FunctionGenerator("ALZ", "Add Sundry Item", "Description of Add Sundry Item",
							"com.misys.equation.screens", "com.misys.equation.screens.layout");
			addRecord1(fg);
			addRecord2(fg);
			addRecord3(fg);
			addLoadAPI(fg);
			addUpdateAPI(fg);
			addMappings(fg);

			fg.getFunction().setApplyDuringExtInput(true);
			fg.getFunction().setApplyDuringRecovery(true);

			fg.getLayoutBean().setCommandKeyOption1("ASI");
			fg.getLayoutBean().setCommandKeyOption1("ALY");
			fg.getLayoutBean().setCommandKeyOption1("HCX");

			File serviceClass = new File(workSpace + "com\\misys\\equation\\screens\\ALZ.class");
			File layoutClass = new File(workSpace + "com\\misys\\equation\\screens\\layout\\ALZ.class");

			// Write to DB
			FunctionToolboxStub.writeToDB(unit, fg.getFunctionBean(), fg.getLayoutBean(), serviceClass, layoutClass);

			// print
			printCompleteStatus(fg, this.getClass().getSimpleName(), printXML);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
