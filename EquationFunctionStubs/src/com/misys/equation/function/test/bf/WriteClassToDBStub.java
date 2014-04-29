package com.misys.equation.function.test.bf;

import java.io.File;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.function.test.option.TestOptionStub;
import com.misys.equation.function.tools.AdaptorToolbox;

// Via Save and Restore
public class WriteClassToDBStub extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WriteClassToDBStub.java 16593 2013-06-24 15:32:19Z perkinj1 $";

	EquationStandardSession session;
	EquationUnit unit;
	EquationUser user;

	public WriteClassToDBStub()
	{
		try
		{
			unit = TestEnvironment.getTestEnvironment().getEquationUnit();
			user = new EquationUser(unit, "EQUIADMIN", "EQUIADMIN", null);
			session = user.getSession();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters)
	{
		WriteClassToDBStub test = new WriteClassToDBStub();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		try
		{
			String fullpath = "";
			File file = null;

			fullpath = workSpace + "com\\misys\\equation\\bankfusion\\attributes\\ATTR_ALZ.class";
			file = new File(fullpath);
			AdaptorToolbox.writeClassToDB(session, unit.getKFILLibrary(), file, "ALZ", "", "", GAZRecordDataModel.TYP_BFTYPE,
							"com.misys.equation.bankfusion.attributes", "ATTR_ALZ");

			System.out.println("Done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
