package com.misys.equation.common.test.pv.list;

import com.misys.equation.common.access.EquationPVData;
import com.misys.equation.common.access.EquationPVMetaData;
import com.misys.equation.common.access.EquationStandardListValidation;
import com.misys.equation.common.test.EquationTestCase;

public class GBR39R extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GBR39R.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public void test()
	{
		// WITH RS AS (
		// SELECT UTW06RFNC(' ','CTR56R', CTTCD,'N','N') AS DTA,
		// CTTCD AS DTAKEY FROM CT01LF )
		// SELECT * FROM RS
		// WHERE DTAKEY>'' AND SUBSTR(RS.DTA,129,3)<>'KSM' AND UPPER(RS.DTA)
		// LIKE UPPER('%%')
		// ORDER BY DTAKEY ASC

		// Have a bash...
		try
		{
			// Starting a new PV
			String service = "GBR39R";
			String decode = "";
			String query = "*   ";
			String start = "";
			int direction = 1;
			int maxResults = 10;

			// Setup the data
			EquationPVMetaData pvMetaData = unit.getPVMetaData(service);
			EquationPVData equationPVData = new EquationPVData(pvMetaData, session.getCcsid());
			equationPVData.setDataDsccn(query);

			// Execute
			EquationStandardListValidation listValidation = new EquationStandardListValidation(decode, service, equationPVData,
							"Y", "", " ", start, direction, maxResults);
			session.executeListValidate(user, listValidation);
			System.out.println("PVDATA: " + listValidation);

			// Setup the data
			System.out.println("Check ALZLIMA");
			pvMetaData = unit.getPVMetaData(service);
			decode = "V";
			query = "ALZLIMA";
			equationPVData = new EquationPVData(pvMetaData, session.getCcsid());
			equationPVData.setDataDsccn(query);

			// Execute
			listValidation = new EquationStandardListValidation(decode, service, equationPVData, "Y", "", " ", start, direction,
							maxResults);
			session.executeListValidate(user, listValidation);
			System.out.println("PVDATA: " + listValidation);

			System.out.println("done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
