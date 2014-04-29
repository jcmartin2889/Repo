package com.misys.equation.function.test.option;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.tools.FunctionToolbox;

public class VV1 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: VV1.java 4735 2009-09-15 16:58:25Z lima12 $";
	public static InputField addField(EquationStandardSession session, InputFieldSet inputFieldSet,
					DisplayAttributesSet attributeSet, String fieldName, String fieldLabel, String type, int length, String pvName,
					String decode, boolean blankAllowed, String newCode) throws EQException
	{
		InputField inputField = FunctionToolbox.getInputField(fieldName, fieldLabel, fieldLabel, type, String.valueOf(length), "");
		DisplayAttributes displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, pvName, decode, blankAllowed, newCode));

		return inputField;
	}

	public static InputField addPromptField(EquationStandardSession session, InputFieldSet inputFieldSet,
					DisplayAttributesSet attributeSet, String fieldName, String fieldLabel, String type, int length, String pvName,
					String decode, boolean blankAllowed, String newCode) throws EQException
	{
		InputField inputField = FunctionToolbox.getInputField(fieldName, fieldLabel, fieldLabel, type, String.valueOf(length), "");
		DisplayAttributes displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt(pvName);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, pvName, decode, blankAllowed, newCode));

		return inputField;
	}
}
