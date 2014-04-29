package com.misys.equation.function.testmain;

import java.io.File;
import java.io.FileInputStream;

import org.w3c.dom.Document;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.tools.XMLToolbox;
import com.misys.equation.function.tools.XSDToolbox;

public class XSDToolboxStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: XSDToolboxStub.java 17212 2013-09-05 14:26:05Z williae1 $";
	EquationStandardSession session;
	EquationUnit unit;

	public XSDToolboxStub()
	{
		try
		{
			// unit = TestEnvironment.getTestEnvironment().getEquationUnit();
			// session = TestEnvironment.getTestEnvironment().getStandardSession();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters)
	{
		XSDToolboxStub test = new XSDToolboxStub();
		test.test();
	}

	public void test()
	{

		try
		{

			File file = new File("C:\\Eclipse_workspaces\\SVN\\BFEQ_GenericServices_3-1\\EquationSamples\\services\\HGW\\HGW.eqf");
			FileInputStream initialInputStream = new FileInputStream(file);
			Function service = XMLToolbox.getXMLToolbox().getFunctionBean(initialInputStream);
			// Function service = XMLToolbox.getXMLToolbox().getFunction(session, "XXX", true);
			Document schemaDocument = XSDToolbox.getFunctionSchemaDocument(service, null, false);
			String schemaString = XSDToolbox.serialiseDocument(schemaDocument);
			System.out.println(schemaString);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
