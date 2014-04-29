package com.misys.equation.function.adaptor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.FunctionClassLoader;
import com.misys.equation.function.beans.FieldSet;
import com.misys.equation.function.el.ELRuntime;
import com.misys.equation.function.tools.AdaptorToolbox;

/**
 * This class manages the runtime retrieval of values that might depend on Java user exit code for a FieldSet
 * <p>
 * The shouldExecute method should be called by the runtime to determine if the API or PV represented by this FieldSet should be
 * executed. The shouldExecute method encapsulates the checking of simple Always/Never values and also performs the runtime
 * processing for EL Script or Java User Exit implementations.
 * 
 * @see IFieldSetAdaptor
 * @see AbstractFieldSetAdaptor
 */
public class FieldSetAdaptor extends Adaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FieldSetAdaptor.java 13251 2012-04-19 08:16:27Z jonathan.perkins $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(FieldSetAdaptor.class);

	private IFunctionAdaptor functionAdaptorImpl;

	private Class<IFieldSetAdaptor> fieldSetAdaptorClass;
	private IFieldSetAdaptor fieldSetAdaptorImpl;

	/**
	 * Construct a FieldSet Adaptor
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param classLoader
	 *            - the Function class loader
	 * @param functionAdaptorImpl
	 *            - an instance of the Equation service
	 * @param optionId
	 *            - the option id of the Equation service
	 * @param fieldSet
	 *            - the Field Set
	 */
	public FieldSetAdaptor(EquationStandardSession session, FunctionClassLoader classLoader, IFunctionAdaptor functionAdaptorImpl,
					String optionId, FieldSet fieldSet) throws EQException
	{
		this.functionAdaptorImpl = functionAdaptorImpl;

		AdaptorKeyFields keyFields = AdaptorToolbox.getGAZKeyFields(fieldSet);

		if (functionAdaptorImpl != null)
		{
			this.fieldSetAdaptorClass = getClass(session, classLoader, optionId, keyFields.getGazfld(), keyFields.getGazpvn(),
							keyFields.getGaztyp());
		}

		if (this.fieldSetAdaptorClass != null)
		{
			this.fieldSetAdaptorImpl = getInstance(functionAdaptorImpl, fieldSetAdaptorClass);
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
	public Class<IFieldSetAdaptor> getFieldSetAdaptorClass()
	{
		return fieldSetAdaptorClass;
	}

	/**
	 * Return the fieldset adaptor implementation
	 * 
	 * @return the fieldset adaptor implementation
	 */
	public IFieldSetAdaptor getFieldSetAdaptorImpl()
	{
		return fieldSetAdaptorImpl;
	}

	/**
	 * Return the (inner) class definition of the outer class
	 * <p>
	 * This method ensures that the returned class definition is an instance of <code>IFieldSetAdaptor</code>
	 * 
	 * @param session
	 *            The <code>EquationStandardSession</code> passed to the constructor
	 * @param classLoader
	 *            The <code>FunctionClassLoader</code> passed to the constructor
	 * @param fieldId
	 *            The field name
	 * @param pvName
	 *            the PV name
	 * @param type
	 *            the specific GAZTYP type for this class
	 * 
	 * @return the class definition for the option
	 */
	@SuppressWarnings("unchecked")
	private Class<IFieldSetAdaptor> getClass(EquationStandardSession session, FunctionClassLoader classLoader, String optionId,
					String fieldId, String pvName, String type) throws EQException
	{
		if (LOG.isInfoEnabled())
		{
			LOG.debug("getInstance - loading definition of class for option [" + optionId + "], field [" + fieldId + "]");
		}
		Class<IFieldSetAdaptor> c = classLoader.loadClass(session, optionId, fieldId, pvName, type);
		return c;
	}

	/**
	 * Return an instance of the specified inner class of an outer class
	 * 
	 * @param functionAdaptor
	 *            - the Function adaptor implementation
	 * @param fieldClass
	 *            - the fieldset adaptor implementation class
	 * 
	 * @return an instance of the class
	 */
	public IFieldSetAdaptor getInstance(IFunctionAdaptor functionAdaptor, Class<IFieldSetAdaptor> fieldClass)
	{
		try
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("getInstance - creating a new instance of field inner class [" + fieldClass.getName() + "]");
			}
			Constructor<IFieldSetAdaptor> constructor = fieldClass.getConstructor(functionAdaptor.getClass());
			IFieldSetAdaptor instance = constructor.newInstance(functionAdaptor);
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
	 * Determines whether to execute the API or PV module that this FieldSet represents
	 * 
	 * @param fieldSet
	 *            - the <code>FieldSet</code> instance to check
	 * 
	 * @return true if the module should be executed
	 */
	public boolean shouldExecute(FieldSet fieldSet)
	{
		switch (fieldSet.getExecuteMode())
		{
			case FieldSet.EXECUTE_ALWAYS:
				LOG.debug("shouldExecute for fieldset (module) [" + fieldSet.getId() + "] returning true (ALWAYS)");
				return true;
			case FieldSet.EXECUTE_NEVER:
				LOG.debug("shouldExecute for fieldset (module) [" + fieldSet.getId() + "] returning false (NEVER)");
				return false;
			case FieldSet.EXECUTE_SCRIPT:
				boolean scriptResult = ELRuntime.runBooleanScript(fieldSet.getExecuteScript(), functionData, fieldSet.getId(),
								"shouldExecute", true, ELRuntime.REALTYPE_VALUE);
				LOG.debug("shouldExecute for fieldset (module) [" + fieldSet.getId() + "]  returning [" + scriptResult
								+ "] (SCRIPT)");
				return scriptResult;
			case FieldSet.EXECUTE_CODE:
			{
				if (fieldSetAdaptorImpl == null)
				{
					LOG.error("shouldExecute for fieldset (module) [" + fieldSet.getId()
									+ "] returning true (because CODE class not loaded)");
					return true;
				}
				else
				{
					boolean javaResult = fieldSetAdaptorImpl.shouldExecuteModule(userData);
					LOG.debug("shouldExecute for fieldset (module) [" + fieldSet.getId() + "]  returning [" + javaResult
									+ "] (CODE)");
					return javaResult;
				}
			}
			default:
				LOG.error("Invalid getExecuteMode contains invalid value [" + fieldSet.getExecuteMode()
								+ "], defaulting to executing the module");
				return true;
		}
	}
}
