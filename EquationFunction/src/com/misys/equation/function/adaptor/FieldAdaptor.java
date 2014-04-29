package com.misys.equation.function.adaptor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQRuntimeException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.FunctionClassLoader;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.WorkField;
import com.misys.equation.function.el.ELRuntime;

public class FieldAdaptor extends Adaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FieldAdaptor.java 11846 2011-09-26 05:13:50Z capilid1 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(FieldAdaptor.class);

	private final IFunctionAdaptor functionAdaptorImpl;
	private Class<IFieldAdaptor> fieldAdaptorClass;
	private IFieldAdaptor fieldAdaptorImpl;

	/**
	 * Construct a Field Adaptor
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
	 * @param type
	 *            - the specific GAZTYP type for this class
	 */
	public FieldAdaptor(EquationStandardSession session, FunctionClassLoader classLoader, IFunctionAdaptor functionAdaptorImpl,
					String optionId, String fieldId, String type, FunctionData functionData) throws EQException
	{
		this.userData = null;
		this.functionAdaptorImpl = functionAdaptorImpl;

		if (functionAdaptorImpl != null)
		{
			this.fieldAdaptorClass = getClass(session, classLoader, optionId, fieldId, type);
		}

		if (this.fieldAdaptorClass != null)
		{
			this.fieldAdaptorImpl = getInstance(functionAdaptorImpl, fieldAdaptorClass);
			fieldAdaptorImpl.getReturnMessages().setFunctionData(functionData);
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
	public Class<IFieldAdaptor> getFieldAdaptorClass()
	{
		return fieldAdaptorClass;
	}

	/**
	 * Return the field adaptor implementation
	 * 
	 * @return the field adaptor implementation
	 */
	public IFieldAdaptor getFieldAdaptorImpl()
	{
		return fieldAdaptorImpl;
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
	 * @param type
	 *            - the specific GAZTYP type for this class
	 * 
	 * @return the class definition for the option
	 */
	@SuppressWarnings("unchecked")
	public Class<IFieldAdaptor> getClass(EquationStandardSession session, FunctionClassLoader classLoader, String optionId,
					String fieldId, String type) throws EQException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("getInstance - loading definition of class for option [" + optionId + "], field [" + fieldId + "]");
		}
		Class<IFieldAdaptor> c = classLoader.loadClass(session, optionId, fieldId, "", type);
		return c;
	}

	/**
	 * Return an instance of the specified inner class of an outer class
	 * 
	 * @param functionAdaptor
	 *            - the Function adaptor implementation
	 * @param fieldClass
	 *            - the field adaptor implementation class
	 * 
	 * @return an instance of the class
	 */
	public IFieldAdaptor getInstance(IFunctionAdaptor functionAdaptor, Class<IFieldAdaptor> fieldClass)
	{
		try
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("getInstance - creating a new instance of field inner class [" + fieldClass.getName() + "]");
			}
			Constructor<IFieldAdaptor> constructor = fieldClass.getConstructor(functionAdaptor.getClass());
			IFieldAdaptor instance = constructor.newInstance(functionAdaptor);
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
			Method method = fieldAdaptorClass.getDeclaredMethod(methodName, FunctionData.class);
			Object retVal = method.invoke(fieldAdaptorImpl, userData);
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
	 * Determine whether the field is mandatory or not
	 * 
	 * @param field
	 *            - the field
	 * 
	 * @return true if mandatory
	 */
	public boolean isMandatory(InputField field)
	{
		String mandatory = field.getMandatory();
		if (InputField.MANDATORY_NO.equals(mandatory))
		{
			LOG.debug("isMandatory for InputField [" + field.getId() + "] returning false (NO)");
			return false;
		}
		else if (InputField.MANDATORY_YES.equals(mandatory))
		{
			LOG.debug("isMandatory for InputField [" + field.getId() + "] returning true (YES)");
			return true;
		}
		else if (InputField.MANDATORY_SCRIPT.equals(mandatory))
		{
			boolean scriptResult = ELRuntime.runBooleanScript(field.getMandatoryExpression(), functionData, field.getId(),
							"mandatory", false, ELRuntime.REALTYPE_VALUE);
			LOG.debug("isMandatory for InputField [" + field.getId() + "] returning returning [" + scriptResult + "] (SCRIPT)");
			return scriptResult;
		}
		else if (InputField.MANDATORY_CODE.equals(mandatory))
		{
			if (fieldAdaptorImpl == null)
			{
				LOG.error("isMandatory for field [" + field.getId() + "] returning false(because CODE class not loaded)");
				return false;
			}
			else
			{
				boolean codeResult = fieldAdaptorImpl.isMandatory(userData);
				LOG.debug("isMandatory for InputField [" + field.getId() + "] returning [" + codeResult + "] (SCRIPT)");
				return codeResult;
			}
		}
		else
		{
			throw new EQRuntimeException("InputField [" + field.getId() + "] getMandatory contains invalid value [" + mandatory
							+ "]");
		}
	}

	/**
	 * Determine the initial value type
	 * 
	 * @param field
	 *            - the field
	 * 
	 * @return status
	 */
	public String initialValue(WorkField field, FunctionData functionData)
	{
		String status = field.getInitialValueConstantType();
		if (WorkField.VALUE_CONSTANT_TEXT.equals(status))
		{
			LOG.debug("initialValue for field [" + field.getId() + "] returning true (TEXT)");
			return field.getInitialValue();
		}
		else if (WorkField.VALUE_CONSTANT_SCRIPT.equals(status))
		{
			String scriptResult = ELRuntime.runStringScript(field.getInitialValue(), functionData, field.getId(), "Initial Value",
							"", ELRuntime.REALTYPE_VALUE);
			LOG.debug("initialValue for field [" + field.getId() + "] returning returning [" + scriptResult + "] (SCRIPT)");
			return scriptResult;
		}
		else if (WorkField.VALUE_CONSTANT_CODE.equals(status))
		{
			if (fieldAdaptorImpl == null)
			{
				LOG.error("initialValue for field [" + field.getId() + "] returning false(because CODE class not loaded)");
				return "";
			}
			else
			{
				String codeResult = fieldAdaptorImpl.initialValue(userData);
				LOG.debug("initialValue for field [" + field.getId() + "] returning [" + codeResult + "] (CODE)");
				return codeResult;
			}
		}
		else
		{
			throw new EQRuntimeException("InputField [" + field.getId() + "] initialValue contains invalid value [" + status + "]");
		}
	}

	/**
	 * Determine the default value type
	 * 
	 * @param field
	 *            - the field
	 * 
	 * @return status
	 */
	public String defaultValue(WorkField field)
	{
		String status = field.getDefaultValueType();
		if (WorkField.VALUE_CONSTANT_TEXT.equals(status))
		{
			LOG.debug("defaultValue for field [" + field.getId() + "] returning TEXT value");
			return field.getDefaultValue();
		}
		else if (WorkField.VALUE_CONSTANT_SCRIPT.equals(status))
		{
			String scriptResult = ELRuntime.runStringScript(field.getDefaultValue(), functionData, field.getId(), "Default Value",
							"", ELRuntime.REALTYPE_VALUE);
			LOG.debug("defaultValue for field [" + field.getId() + "] returning returning [" + scriptResult + "] (SCRIPT)");
			return scriptResult;
		}
		else if (WorkField.VALUE_CONSTANT_CODE.equals(status))
		{
			if (fieldAdaptorImpl == null)
			{
				LOG.error("defaultValue for field [" + field.getId() + "] returning false(because CODE class not loaded)");
				return "";
			}
			else
			{
				String codeResult = fieldAdaptorImpl.defaultValue(userData);
				LOG.debug("defaultValue for field [" + field.getId() + "] returning [" + codeResult + "] (CODE)");
				return codeResult;
			}
		}
		else
		{
			throw new EQRuntimeException("InputField [" + field.getId() + "] defaultValue contains invalid value [" + status + "]");
		}
	}

	/**
	 * Determine whether the field is valid or not
	 * 
	 * @param field
	 *            - the field
	 * 
	 * @return true if valid
	 */
	public boolean isValid(InputField field)
	{
		if (fieldAdaptorImpl == null)
		{
			return true;
		}
		else
		{
			fieldAdaptorImpl.clearMessages();
			return fieldAdaptorImpl.isValid(userData);
		}
	}

	/**
	 * Call the user exit procedure to set up the primitive value
	 * 
	 * @param field
	 *            - the field
	 * 
	 * @return the primitive value of the field as setup by the user exit procedure
	 */
	public String setPrimitiveValue(InputField field)
	{
		String newStr = null;
		if (fieldAdaptorImpl != null)
		{
			newStr = fieldAdaptorImpl.getPrimitiveValue(userData);
			if (newStr != null)
			{
				functionData.chgFieldDbValue(field.getId(), newStr);
			}
		}
		return newStr;
	}

	/**
	 * Call the user exit procedure to set up the output value
	 * 
	 * @param field
	 *            - the field
	 * 
	 * @return the output value of the field as setup by the user exit procedure
	 */
	public String setOutputValue(InputField field)
	{
		String newStr = null;
		if (fieldAdaptorImpl != null)
		{
			newStr = fieldAdaptorImpl.getOutputValue(userData);
			if (newStr != null)
			{
				functionData.chgFieldOutputValue(field.getId(), newStr);
			}
		}
		return newStr;
	}

	/**
	 * Call the user exit procedure to set up the input value
	 * 
	 * @param field
	 *            - the field
	 * 
	 * @return the input value of the field as setup by the user exit procedure
	 */
	public String setInputValue(InputField field)
	{
		String newStr = null;
		if (fieldAdaptorImpl != null)
		{
			newStr = fieldAdaptorImpl.getInputValue(userData);
			if (newStr != null)
			{
				functionData.chgFieldInputValue(field.getId(), newStr);
			}
		}
		return newStr;
	}

}
