package com.misys.equation.function.test.option;

import java.util.Hashtable;

import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IGAERecordDao;
import com.misys.equation.common.dao.beans.GAERecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.RepeatingDetails;

/**
 * A test class for non-business testing.
 * <p>
 * This class generates the service XML for service UE2. This service tests the following areas:
 * <ul>
 * <li>Basic load and update API behaviour (using option HCI)</li>
 * <li>Mapping to Journal Header fields via the GY APIFieldSet, from WorkFields</li>
 * </ul>
 * Note this class does not generate a layout as there are no specific layout settings.
 */
public class UE2 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UE2.java 8739 2010-08-19 10:59:04Z lima12 $";

	public UE2()
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
		UE2 test = new UE2();
		test.test();
	}

	private DisplayFieldSetWrapper addPrimary(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Primary Input Field Set",
						"Primary Input Field Set Description");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();

		GAERecordDataModel gaeRecord = getGAERecord(GAERecordDataModel.TYPE_FIXED_INPUT_API, "HCI");
		inputFieldSet.addAPI(session, gaeRecord, "HCI", true, true, true, true, true, "", false, false, new RepeatingDetails(),
						false, "", null, "");

		return fieldSetWrapper;
	}

	private void addWorkFields(FunctionGenerator fg)
	{
		// These are for mapping to the journal header fields
		FunctionToolbox.addWorkField(fg.getFunction(), "WORK_APP", "Journal Application Code", "", "A", "2", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "WORK_WHO", "Journal WHO field", "", "A", "15", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "WORK_SHN", "Journal short name", "", "A", "15", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "WORK_JREF", "Journal reference", "", "A", "17", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "WORK_IREF", "Journal Input reference", "", "A", "16", "");

		// No need to explicitly control API mode now.
		// FunctionToolbox.addWorkField(fg.getFunction(), "WORK_MODE", "Mode for HCI Update", "", "A", "1", "");
	}

	private void addMappings(FunctionGenerator fg) throws EQException
	{
		// Add mappings from the work fields to the Journal Header fields
		fg.addUpdateMapping("", "WORK_APP", "GY", "GYAPP");
		fg.addUpdateMapping("", "WORK_WHO", "GY", "GYWHO");
		fg.addUpdateMapping("", "WORK_SHN", "GY", "GYSHN");
		fg.addUpdateMapping("", "WORK_JREF", "GY", "GYJREF");
		fg.addUpdateMapping("", "WORK_IREF", "GY", "GYIREF");

		// Don't add update mapping;
		// fg.addUpdateMapping("", "WORK_MODE", "HCI", "MODE");
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
		// Have a bash...
		try
		{
			FunctionGenerator fg = new FunctionGenerator("UE2", "EQ4 Test Service 2",
							"Test Service 2 - testing load, validate and update API generation", "com.bigbank.services",
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
