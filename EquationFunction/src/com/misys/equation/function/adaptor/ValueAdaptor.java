package com.misys.equation.function.adaptor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.FunctionClassLoader;
import com.misys.equation.function.beans.FunctionData;

public class ValueAdaptor extends Adaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ValueAdaptor.java 8065 2010-07-05 16:03:25Z lima12 $";

	private IFunctionAdaptor functionAdaptorImpl;
	private Class<IValueAdaptor> valueAdaptorClass;
	private IValueAdaptor valueAdaptorImpl;

	/**
	 * Return the root class name of the PV field
	 * 
	 * @param field
	 *            - the field where the PV module is attached to
	 * @param pvField
	 *            - the pv field
	 * 
	 * @return the root class name of the pv field
	 */
	public static String getPVFieldClassName(String field, String pvField)
	{
		// remove the first £ or $ of the field name
		if (pvField.charAt(0) == '£' || pvField.charAt(0) == '$')
		{
			// pvField = pvField.substring(1, pvField.length());
		}

		return field + "_" + pvField;
	}

	/**
	 * Construct a Value Adaptor
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param classLoader
	 *            - the Function class loader
	 * @param functionAdaptorImpl
	 *            - an instance of the Equation service
	 * @param option
	 *            id - the option id of the Equation service
	 * @param fieldId
	 *            - the field name
	 * @param pvId
	 *            - the PV field
	 * @param type
	 *            the specific GAZTYP type for this class
	 */
	public ValueAdaptor(EquationStandardSession session, FunctionClassLoader classLoader, IFunctionAdaptor functionAdaptorImpl,
					String optionId, String fieldId, String pvId, String type) throws EQException
	{
		this.userData = null;
		this.functionAdaptorImpl = functionAdaptorImpl;

		if (functionAdaptorImpl != null)
		{
			this.valueAdaptorClass = getClass(session, classLoader, optionId, fieldId, pvId, type);
		}

		if (this.valueAdaptorClass != null)
		{
			this.valueAdaptorImpl = getInstance(functionAdaptorImpl, valueAdaptorClass);
		}
	}

	/**
	 * Return the function adaptor implementation
	 * 
	 * @return the function adaptor implementation
	 */
	public IFunctionAdaptor getFunctionAdaptorImpl()
	{
		return functionAdaptorImpl;
	}

	/**
	 * Return the field adaptor class
	 * 
	 * @return the field adaptor class
	 */
	public Class<IValueAdaptor> getValueAdaptorClass()
	{
		return valueAdaptorClass;
	}

	/**
	 * Return the field adaptor implementation
	 * 
	 * @return the field adaptor implementation
	 */
	public IValueAdaptor getValueAdaptorImpl()
	{
		return valueAdaptorImpl;
	}

	/**
	 * Return the (inner) class definition of the outer class
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param classLoader
	 *            - the Function class loader
	 * @param functionAdaptorImpl
	 *            - an instance of the Equation service
	 * @param option
	 *            id - the option id of the Equation service
	 * @param fieldId
	 *            - the field name
	 * @param pvId
	 *            - the PV field
	 * @param type
	 *            the specific GAZTYP type for this class
	 * 
	 * @return the class definition for the option
	 */
	@SuppressWarnings("unchecked")
	public Class<IValueAdaptor> getClass(EquationStandardSession session, FunctionClassLoader classLoader, String optionId,
					String fieldId, String pvId, String type) throws EQException
	{
		Class<IValueAdaptor> c = classLoader.loadClass(session, optionId, fieldId, pvId, type);
		return c;
	}

	/**
	 * Return an instance of the specified inner class of an outer class
	 * 
	 * @param functionAdaptor
	 *            - the Function adaptor implementation
	 * @param valueClass
	 *            - the value adaptor implementation class
	 * 
	 * @return an instance of the class
	 */
	public IValueAdaptor getInstance(IFunctionAdaptor functionAdaptor, Class<IValueAdaptor> valueClass)
	{
		try
		{
			Constructor<IValueAdaptor> constructor = valueClass.getConstructor(functionAdaptor.getClass());
			IValueAdaptor instance = constructor.newInstance(functionAdaptor);
			return instance;
		}
		catch (NoSuchMethodException e)
		{
			return null;
		}
		catch (InvocationTargetException e)
		{
			return null;
		}
		catch (InstantiationException e)
		{
			return null;
		}
		catch (IllegalAccessException e)
		{
			return null;
		}
	}

	/**
	 * This will execute the specified method. This method accepts one parameter (Function Data) <br>
	 * and returns something
	 * 
	 * @param methodName
	 *            - the method name
	 * @param defaultValue
	 *            - the default value when exception occurs while executing the method
	 * 
	 * @return the output of the method
	 */
	public Object executeDefaultMethod(String methodName, Object defaultValue)
	{
		try
		{
			Method method = valueAdaptorClass.getDeclaredMethod(methodName, FunctionData.class);
			Object retVal = method.invoke(valueAdaptorImpl, userData);
			return retVal;
		}
		catch (NoSuchMethodException e)
		{
			return defaultValue;
		}
		catch (InvocationTargetException e)
		{
			return defaultValue;
		}
		catch (IllegalAccessException e)
		{
			return defaultValue;
		}
	}

	/**
	 * Call the user exit procedure to set up the value
	 * 
	 * @param curValue
	 *            - the current value of the field
	 * 
	 * @return the value of the field as setup by the user exit procedure
	 */
	public String getValue(String curValue)
	{
		String newStr = null;
		if (valueAdaptorImpl != null)
		{
			newStr = valueAdaptorImpl.getValue(curValue, userData);
		}

		return newStr;
	}
}
