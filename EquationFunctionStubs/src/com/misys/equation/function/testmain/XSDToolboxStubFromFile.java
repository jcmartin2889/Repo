package com.misys.equation.function.testmain;

import java.io.File;
import java.io.FileInputStream;

import org.w3c.dom.Document;

import com.misys.equation.function.beans.Function;
import com.misys.equation.function.tools.XMLToolbox;
import com.misys.equation.function.tools.XSDToolbox;

public class XSDToolboxStubFromFile
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: XSDToolboxStub.java 12212 2011-11-01 15:45:23Z lima12 $";

	public XSDToolboxStubFromFile()
	{
	}

	public static void main(String[] inputParameters)
	{
		XSDToolboxStubFromFile test = new XSDToolboxStubFromFile();
		test.test();
	}

	public void test()
	{
		try
		{
			// C:\Eclipse_workspaces\SVN\runtime-EclipseApplication\EquationDesign\services\XXX\XXX.eqf
			File file = new File("C:\\Eclipse_workspaces\\SVN\\runtime-EclipseApplication\\EquationDesign\\services\\XXX\\XXX.eqf");
			FileInputStream initialInputStream = new FileInputStream(file);
			Function service = XMLToolbox.getXMLToolbox().getFunctionBean(initialInputStream);
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
