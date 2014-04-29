package com.misys.equation.function.test.XSD;

/* ********************************************************************************
 * Copyright (c) 2002,2004 Trapedza Financial Systems Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of Trapedza Financial Systems Ltd. Use is subject to license terms.
 * 
 * ********************************************************************************
 * 
 * 
 * $Id: ComplexTypeCodeGenerator.java,v 1.10 2008/11/21 06:02:34 vimalp Exp $
 * 
 * $Log: ComplexTypeCodeGenerator.java,v $ Revision 1.10 2008/11/21 06:02:34 vimalp Added support for Schema type to java class
 * mapping and fixed the missing code in ver 1.9 from 1.8
 * 
 * Revision 1.9 2008/11/05 14:19:38 romans US229 : UI : Code generation fixes : Updated messages
 * 
 * Revision 1.7 2008/10/14 09:46:41 sophiar As part of Gaps of User Story-745-2 : Complex data type definition. Ref Bug 12971
 * 
 * Revision 1.6 2008/10/10 12:52:20 vimalp Added a parameter to generate wrapper type for primitives
 * 
 * Revision 1.5 2008/10/10 10:09:36 prasanthk BugID: 13079 Summary: Erroneous code generation for complex type containing simple
 * elements with occurrence constraints and default value properties Fix:
 * 
 * Erroneous code generation for complex type containing simple elements with occurrence constraints and default value properties
 * 
 * Reviewed by : Jyoti Prakash
 * 
 * Revision 1.4 2008/09/30 12:52:37 sophiar As part of User Story-745-14 : Changes for handling platform attributes
 * 
 * Revision 1.3 2008/09/24 11:35:19 vimalp Changed as part of implementing code generation for Complex attributes (XSD)
 * 
 * Revision 1.2 2008/09/22 09:58:32 nileshk code refactoring
 * 
 * Revision 1.1 2008/09/22 09:10:44 nileshk As part of User Story-745-2 : Code Generation for Complex Type Attributes
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.exolab.castor.builder.SourceGenerator;

import com.misys.equation.common.utilities.EquationLogger;

/**
 * This class is for code generation for Complex Types.
 * 
 * @author nileshk
 * 
 */
public class ComplexTypeBeanGenerator
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FileProcessor.java 7606 2010-06-01 17:04:23Z MACDONP1 $";
	/**
	 * <code>cvsRevision</code> = $Revision: 1.10 $
	 */
	public static final String cvsRevision = "$Revision: 1.10 $";

	/** Logger instance */
	private static final EquationLogger logger = new EquationLogger(ComplexTypeBeanGenerator.class);

	/**
	 * Castor property value for Java Class mapping for Schema types
	 */
	private static final String TYPE = "type";
	/**
	 * Castor property for Java Class mapping for Schema type/element
	 */
	private static final String JAVA_CLASS_MAPPING = "org.exolab.castor.builder.javaclassmapping";

	/**
	 * Castor property for reporting name conflict strategy
	 */
	private static final String NAME_CONFLICT_STRATEGY = "informViaLog";

	public static void main(String[] args) throws Exception
	{
		ComplexTypeBeanGenerator codeGenerator = new ComplexTypeBeanGenerator();
		String projectName = "C:/Eclipse_workspaces/SVN/3-1_Branch/ZGenerated/";
		String destinationDirectory = projectName + "src";
		String xsdLocation = "C:/Eclipse_workspaces/SVN/3-1_Branch/EquationBundleData/DBLoader/EQ_MSG_MessageHeader.xsd";
		String packageName = "bf.com.misys.eqf.types.header";
		HashMap<String, String> nspackages = new HashMap<String, String>();
		nspackages.put("http://www.misys.bf/com/misys/equation/bankfusion/service/cmn",
						"bf.com.misys.equation.bankfusion.service.cmn");
		codeGenerator.generateCode(destinationDirectory, xsdLocation, packageName, nspackages);
	}

	public ComplexTypeBeanGenerator()
	{
		super();

	}
	/**
	 * Method to generate code by invoking JaxB APIs.
	 * 
	 * @param xsdLocation
	 * @param packageName
	 * @throws Exception
	 * @throws Throwable
	 */
	public String generateCode(String destinationDirectory, String xsdLocation, String packageName,
					HashMap<String, String> nspackages) throws Exception
	{
		if (logger.isDebugEnabled())
			logger.debug("Generating code for complex type");
		String error = "";

		SourceGenerator srcGen = new SourceGenerator();
		try
		{
			Properties prop = new Properties();
			prop.put(JAVA_CLASS_MAPPING, TYPE);
			srcGen.setDefaultProperties(prop);
			srcGen.setCreateMarshalMethods(true);
			srcGen.setDescriptorCreation(true);
			srcGen.setGenerateImportedSchemas(true);
			srcGen.setDestDir(destinationDirectory);
			srcGen.setSuppressNonFatalWarnings(true);
			srcGen.setPrimitiveWrapper(true);
			srcGen.setFailOnFirstError(true);
			srcGen.setNameConflictStrategy(NAME_CONFLICT_STRATEGY);
			Set<String> namespaceSet = nspackages.keySet();
			Iterator<String> namespaceIterator = namespaceSet.iterator();
			while (namespaceIterator.hasNext())
			{
				String namespace = namespaceIterator.next();
				srcGen.setNamespacePackageMapping(namespace, nspackages.get(namespace));
			}
			srcGen.generateSource(xsdLocation, packageName);

		}
		catch (Exception e)
		{
			error = "Error generating attribute code :" + e.getMessage();
			logger.error(e.getMessage());

			throw e;

		}
		return error;
	}

}
