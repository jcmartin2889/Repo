package com.misys.equation.screens;

import java.io.File;
import java.sql.Connection;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.tools.AdaptorToolbox;
import com.misys.equation.function.tools.XMLToolbox;

// CLASS LOADER
public class ClassToFile
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ClassToFile.java 12212 2011-11-01 15:45:23Z lima12 $";
	EquationStandardSession session;
	EquationUnit unit;

	public ClassToFile()
	{
		try
		{
			session = TestEnvironment.getTestEnvironment().getStandardSession();
			unit = TestEnvironment.getTestEnvironment().getEquationUnit();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters)
	{
		ClassToFile test = new ClassToFile();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		try
		{
			Connection connection = session.getConnection();

			final String hardCodedPath = "G:\\workspace\\EquationFunctionStubs\\bin\\";

			String pathString = null;
			String filLibrary = unit.getKFILLibrary();

			Function function = XMLToolbox.getXMLToolbox().getFunction(session, "ALZ", true);
			pathString = hardCodedPath + File.separator + function.getPackageName() + File.separator + function.getId();
			AdaptorToolbox.writeFunctionAdaptor(session, filLibrary, function, new File(pathString), new File(pathString));

			function = XMLToolbox.getXMLToolbox().getFunction(session, "CRM", true);
			pathString = hardCodedPath + File.separator + function.getPackageName() + File.separator + function.getId();
			AdaptorToolbox.writeFunctionAdaptor(session, filLibrary, function, new File(pathString), new File(pathString));

			function = XMLToolbox.getXMLToolbox().getFunction(session, "AC1", true);
			pathString = hardCodedPath + File.separator + function.getPackageName() + File.separator + function.getId();
			AdaptorToolbox.writeFunctionAdaptor(session, filLibrary, function, new File(pathString), new File(pathString));

			function = XMLToolbox.getXMLToolbox().getFunction(session, "AC2", true);
			pathString = hardCodedPath + File.separator + function.getPackageName() + File.separator + function.getId();
			AdaptorToolbox.writeFunctionAdaptor(session, filLibrary, function, new File(pathString), new File(pathString));

			function = XMLToolbox.getXMLToolbox().getFunction(session, "AC3", true);
			pathString = hardCodedPath + File.separator + function.getPackageName() + File.separator + function.getId();
			AdaptorToolbox.writeFunctionAdaptor(session, filLibrary, function, new File(pathString), new File(pathString));

			function = XMLToolbox.getXMLToolbox().getFunction(session, "HCX", true);
			pathString = hardCodedPath + File.separator + function.getPackageName() + File.separator + "XHCX";
			AdaptorToolbox.writeFunctionAdaptor(session, filLibrary, function, new File(pathString), new File(pathString));

			connection.commit();
			System.out.println("Done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
