package com.misys.equation.function.test.option;

import java.util.Hashtable;

import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IGAERecordDao;
import com.misys.equation.common.dao.beans.GAERecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.APIField;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.FieldSet;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.IDisplayItem;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.beans.PVField;
import com.misys.equation.function.beans.PVFieldSet;
import com.misys.equation.function.beans.Translation;
import com.misys.equation.function.beans.WorkField;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.MappingToolbox;
import com.misys.equation.function.tools.RepeatingDetails;

/**
 * Non-business System Test service UE6.
 * <p>
 * This service tests the Java user exit processing of both the Service and Layout definitions.
 * <ul>
 * <li>Assignment - Load (Assign API Key field)</li>
 * <li>TODO: Assignment - Load (Assign Input field after calling API)</li>
 * <li>Assignment - Load (Assign Work field after calling API)</li>
 * <li>Assignment - Validate (Assign PV Key field)</li>
 * <li>Assignment - Validate (Assign Input field (Output form) after calling PV)</li>
 * <li>Assignment - Validate (Assign Input field (Primitive form) after calling PV)</li>
 * <li>Assignment - Validate (Assign Work field after calling PV)</li>
 * <li>Assignment - Update (Assign update API field value)</li>
 * <li>Conditional API - Load API/Database</li>
 * <li>Conditional API - Validation PV module</li>
 * <li>Conditional API - Update API/Database</li>
 * <li>Field - Mandatory</li>
 * <li>Field - Valid</li>
 * <li>TODO InputFieldSet - DefaultMode</li>
 * <li>TODO InputFieldSet - ValidateMode</li>
 * <li>TODO InputFieldSet - PreUpdate</li>
 * <li>TODO InputFieldSet - PostUpdate</li>
 * <li>TODO InputFieldSet - PostLoad</li>
 * <li>TODO InputFieldSet - PostLoadEFC</li>
 * <li>Layout - Next Screen</li>
 * <li>Layout - Previous Screen</li>
 * <li>(Display)Field - Protected</li>
 * <li>(Display)Field - Visible</li>
 * </ul>
 */
public class UE6 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UE6.java 14808 2012-11-05 11:58:49Z williae1 $";

	public UE6()
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
		UE6 test = new UE6();
		test.test();
	}

	private DisplayFieldSetWrapper addPrimary(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Primary input field set", "Primary input field set");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		inputFieldSet.setDescription("Primary description");
		attributeSet.setLabel("Primary");

		// input field
		InputField inputField;

		GAERecordDataModel gaeRecord = getGAERecord(GAERecordDataModel.TYPE_FIXED_INPUT_API, "HCI");
		inputFieldSet.addAPI(session, gaeRecord, "AAA", true, true, true, false, false, "", false, false, new RepeatingDetails(),
						false, "", null, "");

		inputField = inputFieldSet.getInputField("AAA_HRC");
		PVFieldSet pvFieldSet = inputField.getPvFieldSet("JVR01R");
		PVField pvField = pvFieldSet.getPVField("$JVHRC");
		pvField.setValidateAssignment(Field.ASSIGNMENT_CODE);

		APIFieldSet loadAPI = inputFieldSet.getLoadAPI("AAA");
		APIField apiField = loadAPI.getAPIField("GZHRC");
		apiField.setLoadAssignment(Field.ASSIGNMENT_CODE);

		// Field LOAD1
		inputField = FunctionToolbox.getInputField("LOAD1", "Assigned by Load", "Assigned by Load Java, should be 'Load Value' ",
						"A", "30", "");
		inputField.setLoadPrimitiveAssignment(Field.ASSIGNMENT_CODE);
		FunctionToolbox.addInputField(inputFieldSet, inputField);

		// Field VALID1
		inputField = FunctionToolbox.getInputField("VALID1", "Assigned by Validate",
						"Assigned by Validate Java (both Primitive and Output)", "A", "35", "");
		inputField.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		inputField.setValidateOutputAssignment(Field.ASSIGNMENT_CODE);
		FunctionToolbox.addInputField(inputFieldSet, inputField);

		// Note that the PV_COND field must be added before the BBB API so that the PV_COND value
		// is changed BEFORE the value is evaluated for the PV condition
		// Field LOAD_COND
		inputField = FunctionToolbox.getInputField("LOAD_COND", "LOAD_COND",
						"Controls whether the conditional API should be loaded. Set to 'Y' to execute", "A", "1", "");
		inputField.setKey(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);

		// Field PV_COND
		inputField = FunctionToolbox.getInputField("PV_COND", "PV_COND",
						"Controls whether the PV should be executed. Set to 'Y' to execute", "A", "1", "");
		inputField.setKey(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);

		// Also add another API for option HCI, this will be used for condition processing validation:
		inputFieldSet.addAPI(session, gaeRecord, "BBB", true, true, true, true, true, "", false, false, new RepeatingDetails(),
						false, "", null, "");
		APIFieldSet apiFieldSet = inputFieldSet.getLoadAPI("BBB");
		apiFieldSet.setExecuteMode(FieldSet.EXECUTE_CODE);

		inputField = inputFieldSet.getInputField("BBB_HRC");
		pvFieldSet = inputField.getPvFieldSet("JVR01R");
		pvFieldSet.setExecuteMode(FieldSet.EXECUTE_CODE);

		// Update API Execution exit
		apiFieldSet = fg.getFunction().getUpdateAPI("BBB");
		apiFieldSet.setExecuteMode(FieldSet.EXECUTE_CODE);

		// The HRD (Hold Description) field will be assigned by Java Update API:
		apiFieldSet.getAPIField("GZHRD").setUpdateAssignment(Field.ASSIGNMENT_CODE);

		return fieldSetWrapper;
	}

	/**
	 * Adds a second screen, with some fields for testing mandatory and validation (field level) exits:
	 * 
	 * @param fg
	 * @return
	 * @throws EQException
	 */
	private DisplayFieldSetWrapper addScreen2(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("SCRN2", "Screen 2 of UE6", "Screen 2 of UE6 Description");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();

		// input field
		InputField inputField;

		// add all the fields in this section ---------------------------------------

		// Mandatory control
		inputField = FunctionToolbox.getInputField("MAND_CTL", "Mandatory control",
						"This determines whether the MAND field is mandatory (set to 'Y')", "A", "1", "");
		inputField.setInitialValue("N");
		FunctionToolbox.addInputField(inputFieldSet, inputField);

		// Mandatory test
		inputField = FunctionToolbox.getInputField("MAND_TST", "Mandatory test",
						"This mandatory state of this field depends on the value of the MAND_CTL field", "A", "30", "");
		inputField.setMandatory(InputField.MANDATORY_CODE);
		FunctionToolbox.addInputField(inputFieldSet, inputField);

		// Validation control
		inputField = FunctionToolbox.getInputField("VALID_TST", "Validation exit", "Enter FAIL to cause KSM0117 to be generated.",
						"A", "4", "");
		FunctionToolbox.addInputField(inputFieldSet, inputField);

		// Next screen
		inputField = FunctionToolbox.getInputField("NEXTSCRN", "Next Screen",
						"Controls the next screen. Set/leave blank for default", "A", "30", "");
		FunctionToolbox.addInputField(inputFieldSet, inputField);

		// UPD_DESC
		inputField = FunctionToolbox
						.getInputField(
										"UPD_DESC",
										"UPD_DESC",
										"Field that contains the description to be applied to the conditional BBB HCI update API by the Java Update User Exit",
										"A", "35", "");
		FunctionToolbox.addInputField(inputFieldSet, inputField);

		return fieldSetWrapper;
	}

	/**
	 * Adds a third screen, with a field to control previous screen user exit code
	 * 
	 * @param fg
	 * @return
	 * @throws EQException
	 */
	private DisplayFieldSetWrapper addScreen3(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("SCRN3", "Screen 3 of UE6", "Screen 3 of UE6 Description");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();

		// input field
		InputField inputField;

		// add all the fields in this section ---------------------------------------

		// Previous screen
		inputField = FunctionToolbox.getInputField("PREVSCRN", "Previous Screen",
						"Controls the previous screen. Set/leave blank for default", "A", "30", "");
		FunctionToolbox.addInputField(inputFieldSet, inputField);

		return fieldSetWrapper;
	}

	/**
	 * Adds a second screen, with some fields for testing mandatory and validation (field level) exits:
	 * 
	 * @param fg
	 * @return
	 * @throws EQException
	 */
	private DisplayFieldSetWrapper addScreen4(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("SCRN4", "Screen 4 of UE6", "Screen 4 of UE6 Description");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		// input field
		InputField inputField;

		// add all the fields in this section ---------------------------------------

		// Visibility control
		inputField = FunctionToolbox.getInputField("VIS_CTL", "Visibility control",
						"This determines whether the TEST field is visible (set to 'N' to hide)", "A", "1", "");
		inputField.setInitialValue("Y");
		FunctionToolbox.addInputField(inputFieldSet, inputField);

		// Protected control
		inputField = FunctionToolbox.getInputField("PROT_CTL", "Protected control",
						"This determines whether the TEST field is protected (set to 'Y' to protect)", "A", "1", "");
		inputField.setInitialValue("N");
		FunctionToolbox.addInputField(inputFieldSet, inputField);

		// Test field (for both visibility and protected state
		inputField = FunctionToolbox.getInputField("TEST1", "Test field",
						"This visibility/protected states of this field depends on the value of the CTL fields", "A", "30", "");
		FunctionToolbox.addInputField(inputFieldSet, inputField);

		// Field UPD_COND
		inputField = FunctionToolbox.getInputField("UPD_COND", "UPD_COND",
						"Controls whether the conditional API should be executed. Set to 'Y' to execute", "A", "1", "");
		FunctionToolbox.addInputField(inputFieldSet, inputField);

		return fieldSetWrapper;
	}

	private void addWorkFields(FunctionGenerator fg) throws EQException
	{
		WorkField workField = FunctionToolbox.addWorkField(fg.getFunction(), "WORK_LOAD1", "WORK_LOAD1",
						"To be assigned by Load Exit Code", "A", "35", "");
		workField.setLoadAssignment(Field.ASSIGNMENT_CODE);

		workField = FunctionToolbox.addWorkField(fg.getFunction(), "WORK_VALID", "WORK_VALID",
						"To be assigned by Validate Exit Code", "A", "35", "");
		workField.setValidateAssignment(Field.ASSIGNMENT_CODE);

		workField = FunctionToolbox.addWorkField(fg.getFunction(), "WORK_INIT", "WORK_INIT", "", "A", "4", "");
		workField.setInitialValue("INIT");
	}

	/**
	 * Remove unwanted mappings
	 * 
	 * @param fg
	 */
	private void removeMappings(FunctionGenerator fg)
	{
		// Remove load mapping to the key GZHRC field for the AAA HCI update API:
		fg.removeLoadMappingToLoad("PRIMARY", "AAA", "GZHRC");

		// Remove update mapping to the GZHRD field for the BBB (conditional) HCI update API.
		// This field is updated by Java code
		fg.removeUpdateMapping("BBB", "GZHRD");
	}

	private void addMappings(FunctionGenerator fg) throws EQException
	{
		// add validate mapping
		fg.addValidateMappingFromPV("PRIMARY", "AAA_HRC", "JVR01R", "$JVHRC", "PRIMARY", "AAA_HRC", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "AAA_HRC", "JVR01R", "$JVDSCS", "PRIMARY", "AAA_HRC", MappingToolbox.TYPE_OUTPUT);

		// add load mapping:
		fg.addLoadMappingFromLoad("PRIMARY", "BBB", "GZHRD", "SCRN2", "UPD_DESC", MappingToolbox.TYPE_PRIMITIVE);
	}

	private void test()
	{
		// Have a bash...
		try
		{
			FunctionGenerator fg = new FunctionGenerator("UE6", "System Test 6 - User Exits",
							"Description of System Test 6 - User Exits", "com.misys.equation.userexits",
							"com.misys.equation.userexits.ui");
			fg.getFunction().setAllowedMaint(true);
			fg.getFunction().setAllowedDel(true);
			fg.getFunction().addReservedFieldSet(Function.GY_FIELDSET, "Journal Details");

			addPrimary(fg);
			addScreen2(fg);
			addScreen3(fg);
			addScreen4(fg);
			addWorkFields(fg);
			removeMappings(fg);
			addMappings(fg);

			// System Testing should test Batch External Input and Recovery
			// processing by applying as a service, not individual APIs
			fg.getFunction().setApplyDuringExtInput(true);
			fg.getFunction().setApplyDuringRecovery(true);

			// Now the services is complete, ensure that the layout is synchronized with the service
			Layout layout = fg.getLayoutBean();
			Translation translation = new Translation(fg.getFunction().rtvTextOwner());
			layout.synchronizeWithService(fg.getFunction(), translation, translation, false, false);

			DisplayAttributesSet screen4 = layout.getDisplayAttributesSet("SCRN4");
			IDisplayItem displayItem = screen4.getDisplayItems().get("TEST1");
			DisplayAttributes test1 = (DisplayAttributes) displayItem;
			test1.setProtected(DisplayAttributes.PROTECTED_CODE);
			test1.setVisible(DisplayAttributes.VISIBLE_CODE);

			// Write to DB
			// FunctionToolboxStub.writeToDB(unit, fg.getFunctionBean(), fg.getLayoutBean());
			FunctionToolboxStub.writeToDB(unit, fg, true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Obtain a GAE record, for use when adding APIs
	 * 
	 * @param type
	 * @param id
	 * @return
	 */
	private GAERecordDataModel getGAERecord(String type, String id)
	{
		GAERecordDataModel result = null;
		DaoFactory daoFactory = new DaoFactory();
		IGAERecordDao gaeRecordDao = daoFactory.getGAEDao(session, new GAERecordDataModel());
		String whereClause = "GAEFID = '" + id + "' AND GAEATYP = '" + type + "'";

		Hashtable<String, GAERecordDataModel> records = gaeRecordDao.getGAERecordKeyedByApiId(whereClause);
		if (records.size() == 1)
		{
			result = records.elements().nextElement();
		}

		return result;
	}

}
