package com.misys.equation.function.testmain;

import com.misys.equation.function.beans.APIField;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.PVField;
import com.misys.equation.function.beans.PVFieldSet;
import com.misys.equation.function.comparator.ElementComparatorToolbox;
import com.misys.equation.function.comparator.ElementDifference;
import com.misys.equation.function.comparator.FieldSetComparator;
import com.misys.equation.function.comparator.FunctionComparator;
import com.misys.equation.function.comparator.InputFieldComparator;
import com.misys.equation.function.comparator.InputFieldSetComparator;

// Via Save and Restore
public class ElementComparatorStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ElementComparatorStub.java 9097 2010-09-10 10:15:41Z lima12 $";

	public ElementComparatorStub()
	{
		try
		{
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters)
	{
		ElementComparatorStub test = new ElementComparatorStub();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		try
		{
			// ---------------------
			System.out.println("-------------- API Field Set");
			APIFieldSet apiFieldset1 = new APIFieldSet("HCX", "label", "desc");
			apiFieldset1.setAlwaysApplyInExtInput(true);
			apiFieldset1.setRepeatingGroup("GRP");
			apiFieldset1.addAPIField(new APIField("HCX_A", "label", "desc"));
			apiFieldset1.addAPIField(new APIField("HCX_B", "label", "desc"));

			APIFieldSet apiFieldset2 = new APIFieldSet("HCX", "label2", "desc2");
			apiFieldset2.setAlwaysApplyInExtInput(false);
			apiFieldset2.setRepeatingGroup("GR2");
			apiFieldset2.addAPIField(new APIField("HCX_A", "labelxx", "descxx"));
			apiFieldset2.addAPIField(new APIField("HCX_Z", "label", "desc"));

			FieldSetComparator apiFieldSetcomparator = new FieldSetComparator();
			ElementDifference ed = apiFieldSetcomparator.compare(apiFieldset1, apiFieldset2,
							ElementComparatorToolbox.TYPE_UPDATEAPI);
			System.out.println(ed);

			// ---------------------
			System.out.println("-------------- PV Field Set");
			PVFieldSet pvFieldset1 = new PVFieldSet("GWV30R", "label", "desc", "N", "N", true);
			pvFieldset1.addPVField(new PVField("fld1a", "label", "desc"));
			pvFieldset1.addPVField(new PVField("fld1b", "label", "desc"));

			PVFieldSet pvFieldset2 = new PVFieldSet("GWV30R", "label2", "desc2", "A", "X", false);
			pvFieldset2.addPVField(new PVField("fld1b", "labelxx", "descxx"));
			pvFieldset2.addPVField(new PVField("fld1z", "label", "desc"));

			FieldSetComparator pvFieldSetcomparator = new FieldSetComparator();
			ed = pvFieldSetcomparator.compare(pvFieldset1, pvFieldset2, ElementComparatorToolbox.TYPE_PVFIELDSET);
			System.out.println(ed);

			// ---------------------
			System.out.println("-------------- Input Field");
			InputField inputField1 = new InputField("FLD1", "label", "desc");
			inputField1.setKey(true);
			inputField1.setUpdateAssignment("2");
			inputField1.setUpdateScript("script2");
			inputField1.addPvFieldSet(pvFieldset1);
			inputField1.addPvFieldSet(new PVFieldSet("newpvfieldset", "description", "decode", "newField", false));

			InputField inputField2 = new InputField("FLD1", "label1", "desc2");
			inputField2.setKey(false);
			inputField2.addPvFieldSet(new PVFieldSet("deletedpvfieldset", "description", "decode", "newField", false));
			inputField2.addPvFieldSet(pvFieldset2);

			InputFieldComparator inputFieldComparator = new InputFieldComparator();
			ed = inputFieldComparator.compare(inputField1, inputField2, ElementComparatorToolbox.TYPE_INPUTFIELD);
			System.out.println(ed);

			// ---------------------
			System.out.println("-------------- Input Field Set");
			InputFieldSet inputFieldSet1 = new InputFieldSet("PRIMARY", "label", "desc");
			inputFieldSet1.setDefaultMicroflow(true);
			inputFieldSet1.addInputField(inputField1);
			inputFieldSet1.addLoadAPI(new APIFieldSet("HCX_B", "label", "desc"));
			inputFieldSet1.addLoadAPI(apiFieldset1);

			InputFieldSet inputFieldSet2 = new InputFieldSet("PRIMARY", "label1", "desc2");
			inputFieldSet2.setDefaultMicroflow(false);
			inputFieldSet2.addInputField(inputField2);
			inputFieldSet2.addLoadAPI(apiFieldset2);
			inputFieldSet2.addLoadAPI(new APIFieldSet("HCX_Z", "label", "desc"));

			InputFieldSetComparator inputFieldSetComparator = new InputFieldSetComparator();
			ed = inputFieldSetComparator.compare(inputFieldSet1, inputFieldSet2, ElementComparatorToolbox.TYPE_INPUTFIELDSET);
			System.out.println(ed);

			// ---------------------
			System.out.println("-------------- Function");
			Function function1 = new Function("func", "label", "desc");
			function1.setAllowedAdd(true);
			function1.addInputFieldSet(inputFieldSet1);
			function1.addUpdateAPI(new APIFieldSet("HCX_B", "label", "desc"));
			function1.addUpdateAPI(apiFieldset1);

			Function function2 = new Function("func", "label2", "desc3");
			function1.setAllowedAdd(false);
			function2.addInputFieldSet(inputFieldSet2);
			function2.addUpdateAPI(new APIFieldSet("HCX_B", "label", "desc"));
			function2.addUpdateAPI(apiFieldset2);

			FunctionComparator functionComparator = new FunctionComparator();
			ed = functionComparator.compare(function1, function2, "FUNC");
			System.out.println(ed);

			System.out.println("done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
