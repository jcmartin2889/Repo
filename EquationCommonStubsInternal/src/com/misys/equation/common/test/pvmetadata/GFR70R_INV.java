package com.misys.equation.common.test.pvmetadata;

import com.misys.equation.common.access.EquationDataList;
import com.misys.equation.common.access.EquationPVData;
import com.misys.equation.common.access.EquationPVMetaData;
import com.misys.equation.common.access.EquationStandardListValidation;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.test.TestEnvironment;

public class GFR70R_INV
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GFR70R_INV.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";

	private EquationStandardSession session;
	private EquationUser user;
	private static final String PVNAME = "GFR70R";

	public GFR70R_INV()
	{
		try
		{
			session = TestEnvironment.getTestEnvironment().getStandardSession();
			user = TestEnvironment.getTestEnvironment().getEquationUser();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters)
	{
		GFR70R_INV test = new GFR70R_INV();
		test.test();
	}

	public void test()
	{
		// Have a bash...
		try
		{
			// Get the meta-data for the PV
			EquationPVMetaData pvmetadata = session.getUnit().getPVMetaData(PVNAME);

			getSingleResult(pvmetadata);

			// Call the PV
			getDataList(pvmetadata);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void getSingleResult(EquationPVMetaData pvmetadata) throws EQException
	{
		EquationDataList help = new EquationDataList();
		EquationPVData data = new EquationPVData(pvmetadata, session.getCcsid());

		// Initialise the CCN
		String ccn = data.parseFieldsIntoDSCCN("Y");
		data.parseDSCCNIntoFields(ccn);

		// Set up a field
		data.setFieldValue("$GFCUS", "ATLANT");
		data.setFieldValue("$GFCLC", "IND");

		// Initialise
		help.initialise(user, PVNAME, " ", data, "", 1, 100);

		System.out.println("Get single record result:");
		// Execute
		String data2 = help.getDataList();
		System.out.println(data2);
	}

	private void getDataList(EquationPVMetaData pvmetadata) throws EQException
	{
		System.out.println("\nRetrieve PV Data");

		String ccn = "ATLANT";

		EquationPVData equationPVData = new EquationPVData(pvmetadata, session.getCcsid());
		equationPVData.setDataDsccn(ccn);

		EquationStandardListValidation listValidation = new EquationStandardListValidation("", PVNAME, equationPVData, "N", "N",
						"", "", 1, 10);
		session.executeListValidate(user, listValidation);
		System.out.println(listValidation);
	}
}
