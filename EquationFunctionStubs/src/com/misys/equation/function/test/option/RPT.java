package com.misys.equation.function.test.option;

import com.misys.equation.common.datastructure.EqDS_Report;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayLabel;
import com.misys.equation.function.beans.Element;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.TextBean;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;
import com.misys.equation.function.tools.FunctionToolbox;

public class RPT extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RPT.java 11230 2011-06-17 12:25:36Z rpatrici $";

	public RPT()
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
		RPT test = new RPT();
		test.test();
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Record 1 of RPT", "Description 1 of RPT");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// add all the fields in this section ---------------------------------------

		// Report mnemonic
		inputField = FunctionToolbox.getInputField("RPTM", "Report request", "This is the report request", "A", "10", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("JTR01R - asdfsadf");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "JTR01R", "", true, "N"));

		// Report selection 1
		inputField = FunctionToolbox.getInputField("RPTS", "GBL000008", "This is the report selection 1", "A", "50", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabelType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
		displayAttributes.setLabelTextOwner(TextBean.OWNER_MISYS);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Label
		DisplayLabel displayLabel = FunctionToolbox.getDisplayLabel("label1",
						"Label only 1 - the quick brown fox jumps over the lazy dog", "Desc of Label 1");
		FunctionToolbox.addDisplayLabel(attributeSet, displayLabel);

		// Report selection 1
		inputField = FunctionToolbox.getInputField("DUMMY", "GBL900033", "Long labe", "A", "50", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabelType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
		displayAttributes.setLabelTextOwner(TextBean.OWNER_MISYS);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		return fieldSetWrapper;
	}

	private void addLoadAPI(FunctionGenerator fg)
	{
	}

	private void addUpdateAPI(FunctionGenerator fg) throws EQException
	{
		APIFieldSet apiFieldSet = FunctionToolbox.getReportFieldSet("RID", "", "");
		fg.addUpdateAPIFieldSet(apiFieldSet);

		apiFieldSet = FunctionToolbox.getReportFieldSet("RID2", "P64ARPR1", "IOP-FL.");
		fg.addUpdateAPIFieldSet(apiFieldSet);
	}

	private void addMappings(FunctionGenerator fg) throws EQException
	{
		// add update mapping
		fg.addUpdateMapping("PRIMARY", "RPTM", "RID", EqDS_Report.RPT);
		fg.addUpdateMapping("PRIMARY", "RPTS", "RID", EqDS_Report.SEL);
	}

	public void test()
	{
		// Have a bash...
		try
		{
			printStartStatus(this.getClass().getSimpleName());
			FunctionGenerator fg = new FunctionGenerator("RPT", "Charge Detail", "This is the description of Charge Detail",
							"com.misys.equation.screens", "com.misys.equation.screens.layout");
			addRecord1(fg);
			addLoadAPI(fg);
			addUpdateAPI(fg);
			addMappings(fg);

			// Write to DB
			FunctionToolboxStub.writeToDB(unit, fg.getFunctionBean(), fg.getLayoutBean(), null, null);

			// print
			printCompleteStatus(fg, this.getClass().getSimpleName(), printXML);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
