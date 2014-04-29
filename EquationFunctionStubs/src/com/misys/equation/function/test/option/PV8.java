package com.misys.equation.function.test.option;

import java.util.Calendar;

import com.misys.equation.common.dao.beans.GAXRecordDataModel;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.DisplayFieldWrapper;
import com.misys.equation.function.tools.XMLToolbox;

public class PV8 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: PV8.java 4735 2009-09-15 16:58:25Z lima12 $";

	public PV8()
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
		PV8 test = new PV8();
		test.test();
	}

	private DisplayFieldWrapper getField(String id, String label, int length, String prompt, String decode)
	{
		DisplayFieldWrapper wrapper = new DisplayFieldWrapper();
		wrapper.setId(id);
		wrapper.setLabel(label);
		wrapper.setDescription(label);
		// Business layer attributes
		InputField inputField = wrapper.getInputField();
		inputField.setDataType("A");
		inputField.setSize(String.valueOf(length));
		inputField.setInitialValue("");
		inputField.setMaxLength("");
		inputField.setMinLength("0");
		inputField.setDecimals("0");
		inputField.setLocked("");
		inputField.setMandatory(InputField.MANDATORY_NO);
		inputField.setRegExp("");
		inputField.setType("");
		inputField.setUpperCase(true);
		inputField.setValidValues("");

		// UI layer attributes
		DisplayAttributes displayAttributes = wrapper.getDisplayAttributes();
		displayAttributes.setKeepWithPrevious(false);
		displayAttributes.setLabelPosition(0);
		displayAttributes.setMask("");
		displayAttributes.setPrompt(prompt);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_NO);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_YES);
		displayAttributes.setWidget("");

		return wrapper;
	}

	private DisplayFieldSetWrapper getRecord1() throws Exception
	{
		InputFieldSet inputFieldSet;

		// Record 1
		DisplayFieldSetWrapper setWrapper = new DisplayFieldSetWrapper();
		setWrapper.setId("REC1");
		setWrapper.setLabel("PV Testing");
		setWrapper.setDescription("This is the PV testing");
		inputFieldSet = setWrapper.getInputFieldSet();
		inputFieldSet.setValidateUserExit("");

		DisplayAttributesSet displayAttributesSet = setWrapper.getDisplayAttributesSet();
		displayAttributesSet.setNextScreenUserExit("");

		// All the fields for record 1
		setWrapper.addDisplayFieldWrapper(getField("BRNM", "Branch", 4, "OSR33R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("AB", "Account branch", 4, "R4R01R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("AB", "Account branch", 4, "WZR10R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("AB", "Account branch", 4, "R7R01R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("BRNM", "Branch", 4, "OSR34R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("BRNM", "Branch", 4, "OSR01R", ""));
		setWrapper.addDisplayFieldWrapper(getField("SQN", "MRL", 4, "KAR02R", ""));
		setWrapper.addDisplayFieldWrapper(getField("GRP", "Group", 6, "TAR52R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("BRNM", "Branch", 4, "OSR38R", ""));
		setWrapper.addDisplayFieldWrapper(getField("TSET", "Reference", 10, "P6R01R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("RGN1", "Group", 6, "WMR10R", "")); raiza
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("AVC", "Code", 3, "WGR01R", "")); raiza
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("BRNM", "Branch", 4, "OMR02R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("LSTR", "Structure", 5, "HGR10R", ""));
		setWrapper.addDisplayFieldWrapper(getField("CAB", "Branch", 4, "JYR01R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("FLN", "Option", 6, "QZR20R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("BRM", "Branch", 4, "AASR10R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("GRP", "User group", 3, "ZXXR01R", ""));
		setWrapper.addDisplayFieldWrapper(getField("CLP", "Type", 3, "HYR40R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("CMR", "Reference", 13, "LCR01R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("CLP", "type", 3, "HYR60R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("CLO", "collateral loc", 4, "HZR10R", ""));
		setWrapper.addDisplayFieldWrapper(getField("ACT", "A/c type", 2, "QMR10R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("BRNM", "branch", 4, "WCR01R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("USID", "user", 4, "N7R01R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("RRT", "Type", 3, "N3R01R", ""));
		setWrapper.addDisplayFieldWrapper(getField("CZC", "Zone", 2, "JWR02R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("LNM", "Lang", 2, "HVR30R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("JWCZC", "Zone", 2, "JWR01R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("PRC", "Period code", 4, "JRR01R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("KEY", "Key", 6, "DFR01R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("BKC", "Broker", 2, "CBR21R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("CTP", "Type", 2, "C4R54R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("ACD", "Code", 2, "C6R53R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("PYT", "Type", 3, "N4R01R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("KEY", "Key", 9, "RJR20R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("ANMD", "Mnemonic", 6, "DHR01R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("SPN", "Code", 3, "DRR01R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("CBTC", "Book type", 3, "DSR01R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("NANC", "Code", 5, "D8R01R", ""));
		setWrapper.addDisplayFieldWrapper(getField("TRC", "Code", 4, "D9R01R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("SAC", "Code", 2, "C3R12R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("ATP", "A/CType", 2, "C5R55R", ""));
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("ING", "Code", 2, "FSR01R", ""));
		// All the fields for record 2
		// setWrapper.addDisplayFieldWrapper(getFieldWrapper("SITS", "Cust", 4, "AAAR10R", ""));
		return setWrapper;
	}

	private void test()
	{
		// Have a bash...
		try
		{
			// Create the FUNCTION
			Function function = new Function();
			function.setId("PV8");
			function.setLabel("Prompt and Validate Test Stub");
			function.setDescription("Prompt and Validate Test Stub");

			// Records
			DisplayFieldSetWrapper setWrapper = getRecord1();
			function.addInputFieldSet(setWrapper.getInputFieldSet());

			// Use the set display attributes created previously (i.e. to specify prompt modules)
			Layout layout = new Layout(function, setWrapper.getDisplayAttributesSet());

			// Print the XML
			EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
			String serviceXML = beanFactory.serialiseBeanAsXML(function);
			System.out.println(serviceXML);

			String layoutXML = beanFactory.serialiseBeanAsXML(layout);
			System.out.println(layoutXML);

			// Write to GAXPF
			XMLToolbox.getXMLToolbox().writeDefinitionXMLtoDB(session, unit.getKFILLibrary(), GAXRecordDataModel.SERVICE_CODE,
							function.getId(), Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT), serviceXML);

			// Write to GBXPF
			XMLToolbox.getXMLToolbox().writeDefinitionXMLtoDB(session, unit.getKFILLibrary(), GAXRecordDataModel.LAYOUT_CODE,
							layout.getId(), Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT), layoutXML);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}