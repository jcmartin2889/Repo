package com.misys.equation.function.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IGAZRecordDao;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.FileUtils;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.context.EquationFunctionContext;

public class ComplexTypeToolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AdaptorToolbox.java 16799 2013-07-22 01:13:58Z williae1 $";
	/**
	 * Logger for this class
	 */
	private static final EquationLogger LOG = new EquationLogger(ComplexTypeToolbox.class);

	// Package name hardcoded to those used in BFEQ so that beans come out the same
	private static final String BEAN_PACKAGE_NAME = "bf.com.misys.equation.bankfusion.service";
	private static final String CTS_BEAN_PACKAGE_NAME = "bf.com.misys.equation.bankfusion.complextypes";

	// the old XSDToolbox JAVA_PACKAGE - used to delete old java files
	private static final String OLD_JAVA_PACKAGE = "bf.com.misys.eq4.service";

	// all BF complex type starts with "bf."
	public static final String BF_COMPLEX_TYPE_PREFIX = "bf.";
	private static final String BEAN_DESCRIPTORS_NAME_SUFFIX = ".descriptors";

	private static DaoFactory daoFactory = new DaoFactory();

	/**
	 * Delete any existing Bankfusion Castor generated classes for the specified service
	 * 
	 * @param session
	 *            An EquationStandardSession
	 * @param filLibrary
	 *            The library to deploy to
	 * @param optionId
	 *            The Id of the service for which to delete all existing BankFusion classes
	 * 
	 * @throws EQException
	 */
	private static void deleteTypeClasses(EquationStandardSession session, String filLibrary, String optionId) throws EQException
	{
		try
		{
			// Delete all existing service definition records:
			GAZRecordDataModel gazRecord = new GAZRecordDataModel();
			gazRecord.setLibrary(filLibrary);
			IGAZRecordDao dao = daoFactory.getGAZDao(session, gazRecord);
			// All Complex Type classes
			dao.deleteRecordByOptionAndType(optionId, GAZRecordDataModel.TYP_BFTYPE);
			dao.deleteRecordByOptionAndType(optionId, GAZRecordDataModel.TYP_BFTYPE_SRC);
			// And all Complex Type descriptor classes
			dao.deleteRecordByOptionAndType(optionId, GAZRecordDataModel.TYP_BFTYPEDESCRIPTOR);
			dao.deleteRecordByOptionAndType(optionId, GAZRecordDataModel.TYP_BFTYPEDESCRIPTOR_SRC);
		}
		catch (Exception e)
		{
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
	}

	/**
	 * Write Complex Type Classes using collections of source and binary files
	 * 
	 * @param session
	 * @param targetFilLibrary
	 * @param function
	 * @param javaSource
	 * @param javaBinary
	 * @param javaSourceDescriptor
	 * @param javaBinaryDescriptor
	 * @throws EQException
	 */
	public static void writeComplexTypeClasses(EquationStandardSession session, String targetFilLibrary, Function function,
					Map<String, File> javaSource, Map<String, File> javaBinary, Map<String, File> javaSourceDescriptor,
					Map<String, File> javaBinaryDescriptor) throws EQException
	{
		String packageName = null;
		String fullClassName = null;

		// Delete all existing service definition records:
		ComplexTypeToolbox.deleteTypeClasses(session, targetFilLibrary, function.getId());
		// Write Java source
		packageName = ComplexTypeToolbox.getComplexTypePackage(function);
		Iterator<String> iterator = javaSource.keySet().iterator();
		File file = null;
		while (iterator.hasNext())
		{
			fullClassName = iterator.next();
			file = javaSource.get(fullClassName);
			ComplexTypeToolbox.writeComplexTypeClassWithInnerFiles(session, targetFilLibrary, function.getId(), file, false,
							GAZRecordDataModel.TYP_BFTYPE_SRC, packageName, fullClassName);

		}
		// Write Java binary
		packageName = ComplexTypeToolbox.getComplexTypePackage(function);
		iterator = javaBinary.keySet().iterator();
		while (iterator.hasNext())
		{
			fullClassName = iterator.next();
			file = javaBinary.get(fullClassName);
			ComplexTypeToolbox.writeComplexTypeClassWithInnerFiles(session, targetFilLibrary, function.getId(), file, true,
							GAZRecordDataModel.TYP_BFTYPE, packageName, fullClassName);

		}
		// Write Java descriptor source
		packageName = ComplexTypeToolbox.getComplexTypeDescriptorPackage(function);
		iterator = javaSourceDescriptor.keySet().iterator();
		while (iterator.hasNext())
		{
			fullClassName = iterator.next();
			file = javaSourceDescriptor.get(fullClassName);
			ComplexTypeToolbox.writeComplexTypeClassWithInnerFiles(session, targetFilLibrary, function.getId(), file, false,
							GAZRecordDataModel.TYP_BFTYPEDESCRIPTOR_SRC, packageName, fullClassName);

		}
		// Write Java descriptor binary
		packageName = ComplexTypeToolbox.getComplexTypeDescriptorPackage(function);
		iterator = javaBinaryDescriptor.keySet().iterator();
		while (iterator.hasNext())
		{
			fullClassName = iterator.next();
			file = javaBinaryDescriptor.get(fullClassName);
			ComplexTypeToolbox.writeComplexTypeClassWithInnerFiles(session, targetFilLibrary, function.getId(), file, true,
							GAZRecordDataModel.TYP_BFTYPEDESCRIPTOR, packageName, fullClassName);

		}
	}
	/**
	 * Write Complex Type Class. If a binary file look for inner class files.
	 * 
	 * @param session
	 * @param filLibrary
	 * @param id
	 * @param file
	 * @param isBinary
	 * @param gazpfType
	 * @param packageName
	 * @param fullClassName
	 * @throws EQException
	 */
	private static void writeComplexTypeClassWithInnerFiles(EquationStandardSession session, String filLibrary, String id,
					File file, boolean isBinary, String gazpfType, String packageName, String fullClassName) throws EQException
	{
		try
		{

			if (file.exists())
			{
				writeClassToDB(session, filLibrary, file, id, "", fullClassName, gazpfType, null, fullClassName);
				if (isBinary)
				{
					String innerClass = null;
					// Now need to loop through the subclasses...
					int pos = file.getName().indexOf("class");
					String className = file.getName().substring(0, pos - 1) + "$";
					File[] packageBinaryFiles = file.getParentFile().listFiles();
					for (File binaryFile : packageBinaryFiles)
					{

						if (binaryFile.getName().startsWith(className))
						{
							innerClass = packageName + "." + binaryFile.getName();
							String sequence = binaryFile.getName().substring(binaryFile.getName().indexOf("$") + 1,
											binaryFile.getName().indexOf(".class"));
							writeClassToDB(session, filLibrary, file, id, sequence, innerClass, gazpfType, null, innerClass);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			throw new EQException("AdaptorToolbox - writeBankFusionTypeClass() - Failed", e);
		}
	}

	/**
	 * Write the content of a file into the database
	 * 
	 * @param session
	 *            - the EquationStandardSession
	 * @param file
	 *            - the file
	 * @param functionId
	 *            - the function Id
	 * @param fieldId
	 *            - the field Id
	 * @param pvId
	 *            - The PV module name (or blank)
	 * @param type
	 *            A <code>String</code> containing the GAZTYP field type
	 * @param packageName
	 *            A <code>String</code> containing the Java package name.
	 * @param className
	 *            A <code>String</code> containing the outer or inner class name.
	 * 
	 * @return true - if the record has been added (or false if updated)
	 * 
	 * @throws EQException
	 */
	private static boolean writeClassToDB(EquationStandardSession session, String filLibrary, File file, String functionId,
					String fieldId, String pvId, String type, String packageName, String className) throws EQException
	{
		boolean result = false;
		FileInputStream inputStream = null;
		try
		{
			inputStream = new FileInputStream(file);
			GAZRecordDataModel gazRecord = new GAZRecordDataModel(functionId, fieldId, pvId, type);
			gazRecord.setLibrary(filLibrary);
			IGAZRecordDao dao = daoFactory.getGAZDao(session, gazRecord);

			// read
			int length = inputStream.available();
			byte[] classByte = new byte[length];
			inputStream.read(classByte);

			// write it
			String qualifiedClassName;
			if (packageName == null || packageName.length() == 0)
			{
				qualifiedClassName = className;
			}
			else
			{
				qualifiedClassName = packageName + "." + className;
			}

			gazRecord.setClassName(qualifiedClassName);
			gazRecord.setClassByte(classByte);

			dao.insertOrUpdateRecord();

			result = dao.checkIfThisGAZRecordIsInTheDB();
		}
		catch (IOException e)
		{
			LOG.error(e);
		}
		finally
		{
			FileUtils.close(inputStream);
		}
		return result;
	}

	/**
	 * return the Complex Type package
	 * 
	 * @param function
	 * @return Complex Type package for Enhanced XSD
	 */
	public static String getComplexTypePackage(Function function)
	{
		if (function.chkXSDGeneric())
		{
			if (function.getId().equals(EquationFunctionContext.MISYS_REFERENCE_SERVICE_ID))
			{
				return CTS_BEAN_PACKAGE_NAME;
			}
			else
			{
				return BEAN_PACKAGE_NAME + '.' + function.getModuleId().toLowerCase() + "." + function.getId().toLowerCase() + "."
								+ function.rtvXsdMajorVersion();
			}
		}
		else
		{
			return BEAN_PACKAGE_NAME + '.' + function.getModuleId().toLowerCase();
		}
	}
	/**
	 * return the Complex Type package depending on xsdGeneric parameter - Only use this method when trying to delete all possible
	 * Java bean classes!
	 * 
	 * @param function
	 * @param xsdGeneric
	 *            - is the package for xsdGeneric
	 * @return Complex Type package for Enhanced XSD
	 */
	public static String getComplexTypePackageForDelete(Function function, boolean xsdGeneric)
	{
		if (xsdGeneric)
		{
			if (function.getId().equals(EquationFunctionContext.MISYS_REFERENCE_SERVICE_ID))
			{
				return CTS_BEAN_PACKAGE_NAME;
			}
			else
			{
				return BEAN_PACKAGE_NAME + '.' + function.getModuleId().toLowerCase() + "." + function.getId().toLowerCase() + "."
								+ function.rtvXsdMajorVersion();
			}
		}
		else
		{
			return BEAN_PACKAGE_NAME + '.' + function.getModuleId().toLowerCase();
		}
	}
	/**
	 * return the Complex Type package for Enhanced XSD
	 * 
	 * @param function
	 *            - the function bean
	 * @param groupId
	 *            - the group id
	 * 
	 * @return Complex Type package for Enhanced XSD
	 */
	public static String getComplexTypePackageEnhancedXsd(Function function, String groupId)
	{
		return getComplexTypePackage(function) + "." + groupId;
	}

	/**
	 * return the Complex Type package for older services
	 * 
	 * @param function
	 * @return Complex Type package for older services
	 */
	public static String getComplexTypePackageOldXsd(Function function)
	{
		return OLD_JAVA_PACKAGE + "." + function.getId().toLowerCase();
	}

	/**
	 * return the Complex Type descriptor package
	 * 
	 * @param function
	 * @return Complex Type descriptor package
	 */
	public static String getComplexTypeDescriptorPackage(Function function)
	{
		return getComplexTypePackage(function) + ComplexTypeToolbox.BEAN_DESCRIPTORS_NAME_SUFFIX;
	}
	/**
	 * return the Complex Type descriptor package depending on xsdGeneric parameter - Only use this method when trying to delete all
	 * possible Java bean classes!
	 * 
	 * @param function
	 * @param xsdGeneric
	 *            - is the package for xsdGeneric
	 * @return Complex Type descriptor package
	 */
	public static String getComplexTypeDescriptorPackageForDelete(Function function, boolean xsdGeneric)
	{
		return getComplexTypePackageForDelete(function, xsdGeneric) + ComplexTypeToolbox.BEAN_DESCRIPTORS_NAME_SUFFIX;
	}
	/**
	 * return the Complex Type descriptor package for older services
	 * 
	 * @param function
	 * @return Complex Type descriptor package for older services
	 */
	public static String getComplexTypeDescriptorPackageOldXsd(Function function)
	{
		return getComplexTypePackageOldXsd(function) + ComplexTypeToolbox.BEAN_DESCRIPTORS_NAME_SUFFIX;
	}

}
