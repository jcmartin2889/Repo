package com.misys.equation.function.test.option;

import java.util.Hashtable;

import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IGAERecordDao;
import com.misys.equation.common.dao.beans.GAERecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.FieldSet;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
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
 * This class generates the service XML for service UE5. Test coverage includes:
 * <ul>
 * <li>Enquiry processing (based on option ABE)</li>
 * <li>Mapping to Enquiry Journal Header fields via the G5 APIFieldSet, from WorkFields</li>
 * <li>Adding a PV to retrieve the code description for the account type</li>
 * </ul>
 * Note this class does not generate a layout as there are no specific layout settings.
 */
public class UE5 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UE5.java 8739 2010-08-19 10:59:04Z lima12 $";

	public UE5()
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
		UE5 test = new UE5();
		test.test();
	}

	private DisplayFieldSetWrapper addPrimary(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Primary Input Field Set",
						"Primary Input Field Set Description");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();

		GAERecordDataModel gaeRecord = getGAERecord(GAERecordDataModel.TYPE_FIXED_ENQUIRY_API, "ABE");
		inputFieldSet.addAPI(session, gaeRecord, "ABE", true, false, true, true, true, "", false, false, new RepeatingDetails(),
						false, "", null, "");

		InputField hzab = inputFieldSet.getInputField("ABE_HZAB");
		if (!hzab.isKey())
		{
			throw new RuntimeException("GAE key information for function ABE is missing/incorrect");
		}
		PVFieldSet gwr76r = FunctionToolbox.getPVFieldSet(session, "GWR76R", "", false, "N");
		gwr76r.setLabel("Accounts");
		gwr76r.setDescription("Accounts");

		// By adding an executed PV which does not map back out to the input fields, this
		// seems to blank out the field values
		gwr76r.setExecuteMode(FieldSet.EXECUTE_NEVER);
		FunctionToolbox.addPVFieldSet(hzab, gwr76r);

		// Account type
		InputField hzact = inputFieldSet.getInputField("ABE_HZACT");
		PVFieldSet c5r55r = FunctionToolbox.getPVFieldSet(session, "C5R55R", "", false, "N");
		c5r55r.getPVField("$C5ATP").setKey(true);
		c5r55r.setLabel("Account Types");
		c5r55r.setDescription("Account Types");

		FunctionToolbox.addPVFieldSet(hzact, c5r55r);

		return fieldSetWrapper;
	}

	private void addWorkFields(FunctionGenerator fg)
	{
		FunctionToolbox.addWorkField(fg.getFunction(), "WORK_APP", "Journal Application Code", "", "A", "2", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "WORK_WHO", "Journal WHO field", "", "A", "15", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "WORK_SHN", "Journal short name", "", "A", "15", "");
		WorkField workField = FunctionToolbox.addWorkField(fg.getFunction(), "WORK_JREF", "Journal reference", "", "A", "17", "");
		workField.setInitialValue("Journal Reference");

		workField = FunctionToolbox.addWorkField(fg.getFunction(), "WORK_N01", "Narrative line 1", "", "A", "50", "");
		workField.setInitialValue("Enquiry Journal Narrative line 1");
		workField = FunctionToolbox.addWorkField(fg.getFunction(), "WORK_N02", "Narrative line 2", "", "A", "50", "");
		workField.setInitialValue("Enquiry Journal Narrative line 2");
		workField = FunctionToolbox.addWorkField(fg.getFunction(), "WORK_N03", "Narrative line 3", "", "A", "50", "");
		workField.setInitialValue("Enquiry Journal Narrative line 3");
		workField = FunctionToolbox.addWorkField(fg.getFunction(), "WORK_N04", "Narrative line 4", "", "A", "50", "");
		workField.setInitialValue("Enquiry Journal Narrative line 4");
		workField = FunctionToolbox.addWorkField(fg.getFunction(), "WORK_N05", "Narrative line 5", "", "A", "50", "");
		workField.setInitialValue("Enquiry Journal Narrative line 5");
		workField = FunctionToolbox.addWorkField(fg.getFunction(), "WORK_N06", "Narrative line 6", "", "A", "50", "");
		workField.setInitialValue("Enquiry Journal Narrative line 6");
		workField = FunctionToolbox.addWorkField(fg.getFunction(), "WORK_N07", "Narrative line 7", "", "A", "50", "");
		workField.setInitialValue("Enquiry Journal Narrative line 7");
		workField = FunctionToolbox.addWorkField(fg.getFunction(), "WORK_N08", "Narrative line 8", "", "A", "50", "");
		workField.setInitialValue("Enquiry Journal Narrative line 8");
		workField = FunctionToolbox.addWorkField(fg.getFunction(), "WORK_N09", "Narrative line 9", "", "A", "50", "");
		workField.setInitialValue("Enquiry Journal Narrative line 9");
		workField = FunctionToolbox.addWorkField(fg.getFunction(), "WORK_N10", "Narrative line 10", "", "A", "50", "");
		workField.setInitialValue("Enquiry Journal Narrative line 10");
	}

	private void addMappings(FunctionGenerator fg) throws EQException
	{
		// Add mappings from the work fields to the Journal Header fields
		fg.addUpdateMapping("", "WORK_APP", "G5", "G5APP");
		fg.addUpdateMapping("", "WORK_WHO", "G5", "G5WHO");
		fg.addUpdateMapping("", "WORK_SHN", "G5", "G5SHN");
		fg.addUpdateMapping("", "WORK_JREF", "G5", "G5JREF");

		fg.addUpdateMapping("", "WORK_N01", "G5", "G5N01");
		fg.addUpdateMapping("", "WORK_N02", "G5", "G5N02");
		fg.addUpdateMapping("", "WORK_N03", "G5", "G5N03");
		fg.addUpdateMapping("", "WORK_N04", "G5", "G5N04");
		fg.addUpdateMapping("", "WORK_N05", "G5", "G5N05");
		fg.addUpdateMapping("", "WORK_N06", "G5", "G5N06");
		fg.addUpdateMapping("", "WORK_N07", "G5", "G5N07");
		fg.addUpdateMapping("", "WORK_N08", "G5", "G5N08");
		fg.addUpdateMapping("", "WORK_N09", "G5", "G5N09");
		fg.addUpdateMapping("", "WORK_N10", "G5", "G5N010");

		// Add mappings for the PV for the account key fields:
		fg.addValidateMappingToPV("PRIMARY", "ABE_HZAB", "PRIMARY", "ABE_HZAB", "GWR76R", "$R76AB");
		fg.addValidateMappingToPV("PRIMARY", "ABE_HZAN", "PRIMARY", "ABE_HZAB", "GWR76R", "$R76AN");
		fg.addValidateMappingToPV("PRIMARY", "ABE_HZAS", "PRIMARY", "ABE_HZAB", "GWR76R", "$R76AS");

		// Add mappings for the C5R55R PV module on the ABE_HZACT field:
		fg.addValidateMappingToPV("PRIMARY", "ABE_HZACT", "PRIMARY", "ABE_HZACT", "C5R55R", "$C5ATP");
		fg.addValidateMappingFromPV("PRIMARY", "ABE_HZACT", "C5R55R", "$C5ATP", "PRIMARY", "ABE_HZACT",
						MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "ABE_HZACT", "C5R55R", "$C5ATD", "PRIMARY", "ABE_HZACT", MappingToolbox.TYPE_OUTPUT);

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
			FunctionGenerator fg = new FunctionGenerator("UE5", "EQ4 Test Service 5", "Test Service 5 - Enquiry testing (ABE)",
							"com.bigbank.services", "com.bigbank.services.ui");
			fg.getFunction().addReservedFieldSet(Function.G5_FIELDSET, "Journal Details - Enquiry");

			fg.getFunction().setAllowedEnq(true);
			fg.getFunction().setAllowedAdd(false);

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
