package com.misys.equation.function.test.beans;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.exolab.castor.builder.SourceGenerator;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.utilities.EquationLogger;

public class FunctionSchemaStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionSchemaStub.java 9942 2010-11-17 16:56:54Z lima12 $";

	/** Logger for this class */
	private static final EquationLogger LOG = new EquationLogger(FunctionSchemaStub.class);

	EquationStandardSession session;
	public FunctionSchemaStub()
	{
		try
		{
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public static void main(String[] inputParameters)
	{
		FunctionSchemaStub test = new FunctionSchemaStub();
		test.test();

	}

	private void test()
	{
		try
		{
			// load the properties
			File propertiesFile = new File("E:\\IBM\\workspace\\EquationFunctionStubs\\schema\\castorbuilder.properties");
			Reader propertiesFileReader = new FileReader(propertiesFile);
			Properties properties = new Properties();
			// properties.load(propertiesFileReader);

			File f = new File("E:\\IBM\\workspace\\EquationFunctionStubs\\schema\\Schemy.xsd");
			Reader r = new FileReader(f);
			SourceGenerator sourceGenerator = new SourceGenerator();
			sourceGenerator.setDefaultProperties(properties);
			sourceGenerator.setDestDir("E:\\IBM\\workspace\\EquationFunctionStubs\\gensrc");
			sourceGenerator.setDescriptorCreation(false);
			sourceGenerator.setGenerateImportedSchemas(true);
			sourceGenerator.setCreateMarshalMethods(false);
			sourceGenerator.generateSource(r, "com.misys.equation.function");
		}
		catch (IOException e)
		{
			LOG.error(e);
		}
	}
}
