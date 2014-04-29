package com.misys.equation.function.test.option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IGAERecordDao;
import com.misys.equation.common.dao.beans.GAERecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.PVField;
import com.misys.equation.function.beans.PVFieldSet;
import com.misys.equation.function.beans.WorkField;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.MappingToolbox;
import com.misys.equation.function.tools.RepeatingDetails;

/**
 * A test class for non-business testing.
 * <p>
 * This class generates the service XML for service UE3. This service tests the following areas:
 * <ul>
 * <li>Basic load and update API behaviour (using option HCI)</li>
 * <li>Mapping to Journal Header fields via the GY APIFieldSet, from WorkFields</li>
 * </ul>
 * Note this class does not generate a layout as there are no specific layout settings.
 */
public class UE3 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UE3.java 9442 2010-10-12 09:42:28Z MACDONP1 $";

	public UE3()
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
		UE3 test = new UE3();
		test.test();
	}

	private DisplayFieldSetWrapper addPrimary(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Primary Input Field Set",
						"Primary Input Field Set Description");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();

		GAERecordDataModel gaeRecord = getGAERecord(GAERecordDataModel.TYPE_FIXED_INPUT_API, "ANC");
		inputFieldSet.addAPI(session, gaeRecord, "ANC", true, true, false, false, false, "", false, false, new RepeatingDetails(),
						false, "", null, "");

		// Remove all ANC fields apart from the following;
		String[] required = new String[] { "CUS", "CLC", "CUN", "CPNC", "DAS", "CTP", "ACO", "CNAP", "CNAR", "CNAL", "GRP", "BRNM",
						"LNM", "CRB1", "CRB2" };

		List<String> requiredList = new ArrayList<String>(Arrays.asList(required));
		List<InputField> toRemove = new ArrayList<InputField>();
		// First create a list of fields that are not in the above list:
		for (InputField inputField : inputFieldSet.getInputFields())
		{
			if (inputField.getId().startsWith("ANC_"))
			{
				String bareName = inputField.getId().substring(4);
				if (!requiredList.contains(bareName))
				{
					toRemove.add(inputField);
				}
			}
		}
		// Now loop though the list of unwanted fields and remove them:
		for (InputField inputField : toRemove)
		{
			inputFieldSet.removeField(inputField);
		}

		// Add a Load API for a database table:
		inputFieldSet.addTable(session, "CA02LF", "BBN", "Description !!!", false, false, false, "", false, false, false);

		InputField crb1 = inputFieldSet.getInputField("ANC_CRB1");
		PVFieldSet pvFieldSet = crb1.getPvFieldSet("D4R47R");
		PVField pvField = pvFieldSet.getPVField("$D4DTE");
		// Assign the date via EL script:
		pvField.setLoadAssignment(Field.ASSIGNMENT_SCRIPT);
		pvField.setLoadScript("''"); // Assign an empty string
		// Note that the actual rate key field will be assigned by mapping...

		InputField crb2 = inputFieldSet.getInputField("ANC_CRB2");
		pvFieldSet = crb2.getPvFieldSet("D4R47R");
		pvField = pvFieldSet.getPVField("$D4DTE");
		// Assign the date via Java code:
		pvField.setLoadAssignment(Field.ASSIGNMENT_CODE);

		return fieldSetWrapper;
	}

	private void addWorkFields(FunctionGenerator fg)
	{
		FunctionToolbox.addWorkField(fg.getFunction(), "WORK_APP", "Journal Application Code", "", "A", "2", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "WORK_WHO", "Journal WHO field", "", "A", "15", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "WORK_SHN", "Journal short name", "", "A", "15", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "WORK_JREF", "Journal reference", "", "A", "17", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "WORK_IREF", "Journal Input reference", "", "A", "16", "");

		WorkField workField = FunctionToolbox.addWorkField(fg.getFunction(), "WORK_BBN", "WORK Branch number", "", "A", "4", "");
		workField.setInitialValue("0132");
	}

	private void addMappings(FunctionGenerator fg) throws EQException
	{
		// Add mappings from the work fields to the Journal Header fields
		fg.addUpdateMapping("", "WORK_APP", "GY", "GYAPP");
		fg.addUpdateMapping("", "WORK_WHO", "GY", "GYWHO");
		fg.addUpdateMapping("", "WORK_SHN", "GY", "GYSHN");
		fg.addUpdateMapping("", "WORK_JREF", "GY", "GYJREF");
		fg.addUpdateMapping("", "WORK_IREF", "GY", "GYIREF");

		// Add a load mapping from the WORK_BBN field to the BBN key field of the CA02LF table
		fg.addLoadMappingToLoad("", "WORK_BBN", "PRIMARY", "BBN", "CABBN");
		// Add a mapping from the branch mnemonic field of the table to the branch mnemonic Input Field
		fg.addLoadMappingFromLoad("PRIMARY", "BBN", "CABRNM", "PRIMARY", "ANC_BRNM", MappingToolbox.TYPE_PRIMITIVE);

		// Add validate mapping to the base rate code field of the D4R47R
		fg.addValidateMappingToPV("PRIMARY", "ANC_CRB1", "PRIMARY", "ANC_CRB1", "D4R47R", "$D4BRR");
		fg.addValidateMappingToPV("PRIMARY", "ANC_CRB2", "PRIMARY", "ANC_CRB2", "D4R47R", "$D4BRR");

		// Add a description mapping
		fg.addValidateMappingFromPV("PRIMARY", "ANC_CRB1", "D4R47R", "$D4DSC", "PRIMARY", "ANC_CRB1", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "ANC_CRB2", "D4R47R", "$D4DSC", "PRIMARY", "ANC_CRB2", MappingToolbox.TYPE_PRIMITIVE);

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

	private void test()
	{
		try
		{
			FunctionGenerator fg = new FunctionGenerator("UE3", "EQ4 Test Service 3",
							"Test Service 3 - testing assignments (mappings, script and java", "com.bigbank.services",
							"com.bigbank.services.ui");
			fg.getFunction().addReservedFieldSet(Function.GY_FIELDSET, "Journal Details");

			fg.getFunction().setAllowedMaint(true);
			fg.getFunction().setAllowedDel(true);

			addPrimary(fg);
			addWorkFields(fg);
			addMappings(fg);

			// System Testing should test Batch External Input and Recovery
			// processing by applying as a service, not individual APIs
			fg.getFunction().setApplyDuringExtInput(true);
			fg.getFunction().setApplyDuringRecovery(true);

			// Write to DB
			// FunctionToolboxStub.writeToDB(unit, fg, true);
			System.out.println(fg.getService());

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
