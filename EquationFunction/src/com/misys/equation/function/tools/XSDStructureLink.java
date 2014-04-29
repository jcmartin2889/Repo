package com.misys.equation.function.tools;

import java.util.ArrayList;
import java.util.Hashtable;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.adaptor.FunctionAdaptor;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.XSDStructure;
import com.misys.equation.function.runtime.FunctionBankFusion;

public class XSDStructureLink
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: XSDStructureLink.java 17190 2013-09-03 11:49:59Z Lima12 $";

	private Function function;

	private Hashtable<String, XSDStructure> requestMap;
	private Hashtable<String, XSDStructure> responseMap;

	private ArrayList<XSDStructure> requestClassLoadingHierarchy;
	private ArrayList<XSDStructure> responseClassLoadingHierarchy;

	private FunctionBankFusion functionBankfusion;

	/**
	 * Constructor
	 * 
	 * @param function
	 *            - the Function bean
	 */
	public XSDStructureLink(Function function)
	{
		this.function = function;
		this.requestMap = XSDStructureHelper.getRequestXSDMap(function);
		this.responseMap = XSDStructureHelper.getResponseXSDMap(function);
		this.requestClassLoadingHierarchy = XSDStructureHelper.getRequestClassLoadingHierarchy(function);
		this.responseClassLoadingHierarchy = XSDStructureHelper.getResponseClassLoadingHierarchy(function);
		this.functionBankfusion = new FunctionBankFusion();
	}

	/**
	 * Return the Request path of the field
	 * 
	 * @param id
	 *            - the field id
	 * 
	 * @return the path of the field
	 */
	public ArrayList<XSDStructure> getRequestPath(String id)
	{
		XSDStructure xsd = requestMap.get(id);
		return xsd.rtvParents();

	}

	/**
	 * Return the Response path of the field
	 * 
	 * @param id
	 *            - the field id
	 * 
	 * @return the path of the field
	 */
	public ArrayList<XSDStructure> getResponsePath(String id)
	{
		XSDStructure xsd = responseMap.get(id);
		return xsd.rtvParents();
	}

	/**
	 * Return the Request XSD structure of the id
	 * 
	 * @param id
	 *            - the id
	 * 
	 * @return the Request XSD structure of the id
	 */
	public XSDStructure getRequestXsd(String id)
	{
		return requestMap.get(id);
	}

	/**
	 * Return the Response XSD structure of the id
	 * 
	 * @param id
	 *            - the id
	 * 
	 * @return the Response XSD structure of the id
	 */
	public XSDStructure getResponseXsd(String id)
	{
		return responseMap.get(id);
	}

	/**
	 * Return the request XSD class loading hierarchy
	 * 
	 * @return the request XSD class loading hierarchy
	 */
	public ArrayList<XSDStructure> getRequestClassLoadingHierarchy()
	{
		return requestClassLoadingHierarchy;
	}

	/**
	 * Return the response XSD class loading hierarchy
	 * 
	 * @return the response XSD class loading hierarchy
	 */
	public ArrayList<XSDStructure> getResponseClassLoadingHierarchy()
	{
		return responseClassLoadingHierarchy;
	}

	/**
	 * Return the complex type instance
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param fullClassName
	 *            - the full package name of the class to be loaded
	 * @param functionAdaptor
	 *            - When this is specified, then it will load the complex type from the database. Otherwise, load it using BF (if BF
	 *            is installed)
	 * 
	 * @return the complex type instance
	 */
	public Object getBankFusionDataType(EquationStandardSession session, String fullClassName, FunctionAdaptor functionAdaptor)
					throws EQException
	{
		// Function adaptor specified
		if (functionAdaptor != null)
		{
			return functionAdaptor.getBankFusionDataType(session, "", fullClassName);
		}

		// BF installed
		else if (EquationCommonContext.isBankFusionInstalled())
		{
			return functionBankfusion.getBankFusionDataType(null, fullClassName);
		}

		return null;
	}

	/**
	 * Return the complex type instance
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param fullClassName
	 *            - the full package name of the class to be loaded
	 * @param functionAdaptor
	 *            - When this is specified, then it will load the complex type from the database. Otherwise, load it using BF (if BF
	 *            is installed)
	 * 
	 * @return the complex type instance
	 */
	public Class<Object> getBFComplexType(EquationStandardSession session, String fullClassName, FunctionAdaptor functionAdaptor)
					throws EQException
	{
		// Function adaptor specified
		if (functionAdaptor != null)
		{
			return functionAdaptor.getBFComplexTypeClass(session, "", fullClassName);
		}

		// BF installed
		else if (EquationCommonContext.isBankFusionInstalled())
		{
			return functionBankfusion.getBFComplexTypeClass(null, fullClassName);
		}

		return null;
	}

	/**
	 * Return the complex type instance
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param webServiceName
	 *            - the web service name
	 * @param functionAdaptor
	 *            - When this is specified, then it will load the complex type from the database. Otherwise, load it using BF (if BF
	 *            is installed)
	 * 
	 * @return the complex type instance
	 */
	public Object getWebServiceDataType(EquationStandardSession session, String webServiceName, FunctionAdaptor functionAdaptor)
					throws EQException
	{
		String fullClassName = ComplexTypeToolbox.getComplexTypePackageEnhancedXsd(function, webServiceName);
		return getBankFusionDataType(session, fullClassName, functionAdaptor);
	}

	/**
	 * Load the response classes (enhanced XSD)
	 * 
	 * @param session
	 *            - the Equation standard session
	 * 
	 * @Return the main response instance
	 */
	public Object loadResponseClasses(EquationStandardSession session, FunctionAdaptor functionAdaptor) throws EQException
	{
		return loadClasses(session, getResponseClassLoadingHierarchy(), functionAdaptor);
	}

	/**
	 * Load all request classes (enhanced XSD)
	 * 
	 * @param session
	 *            - the Equation standard session
	 * 
	 * @Return the main request instance
	 */
	public Object loadRequestClasses(EquationStandardSession session, FunctionAdaptor functionAdaptor) throws EQException
	{
		return loadClasses(session, getRequestClassLoadingHierarchy(), functionAdaptor);
	}

	/**
	 * Load all classes
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param xsdList
	 *            - the class loading hierarchy. This will load each of the classes in this list.
	 * 
	 * @Return the main class instance
	 */
	protected Object loadClasses(EquationStandardSession session, ArrayList<XSDStructure> xsdList, FunctionAdaptor functionAdaptor)
					throws EQException
	{
		// retrieve the CTS bean
		Function ctsBean = XMLToolbox.getXMLToolbox().getCtsBean(session.getUnitId());

		String fullClassName = null;
		String complexType = null;
		for (XSDStructure xsd : xsdList)
		{
			complexType = xsd.rtvRuntimeComplexType();

			// Loading using BF, then use CTS package name
			if (xsd.chkComplexType())
			{
				fullClassName = ComplexTypeToolbox.getComplexTypePackageEnhancedXsd(ctsBean, complexType);
			}

			// Loading using FunctionAdaptor
			else
			{
				fullClassName = ComplexTypeToolbox.getComplexTypePackageEnhancedXsd(function, complexType);
			}

			getBFComplexType(session, fullClassName, functionAdaptor);
		}

		Object bf_functionData = getBankFusionDataType(session, fullClassName, functionAdaptor);

		return bf_functionData;
	}

}
