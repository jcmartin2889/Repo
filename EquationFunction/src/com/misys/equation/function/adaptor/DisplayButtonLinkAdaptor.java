package com.misys.equation.function.adaptor;

import java.lang.reflect.Constructor;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQRuntimeException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.FunctionClassLoader;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayButtonLink;
import com.misys.equation.function.el.ELRuntime;

/**
 * This class manages the runtime interaction with the user exit code for DisplayButtonLink
 * <p>
 * The class encapsulates the visible and protected state checking, and will call the appropriate user exit class method
 * implementation if appropriate i.e. retrieving the command and parameters
 */
public class DisplayButtonLinkAdaptor extends Adaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DisplayButtonLinkAdaptor.java 11239 2011-06-21 06:40:49Z yzobdabu $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(DisplayButtonLinkAdaptor.class);

	private ILayoutAdaptor layoutAdaptorImpl;
	private Class<IDisplayButtonLinkAdaptor> displayButtonLinkAdaptorClass;
	private IDisplayButtonLinkAdaptor displayButtonLinkAdaptorImpl;

	/**
	 * Construct a Button Link Adaptor
	 * 
	 * @param session
	 *            - the session
	 * @param classLoader
	 *            - the function class loader
	 * @param layoutAdaptorImpl
	 *            - an instance of the outer class
	 * @param optionId
	 *            - the option id
	 * @param fieldId
	 *            - the field name
	 * @param layoutAdaptor
	 *            - the layout adaptor
	 * @throws EQException
	 */
	@SuppressWarnings("unchecked")
	public DisplayButtonLinkAdaptor(EquationStandardSession session, FunctionClassLoader classLoader,
					ILayoutAdaptor layoutAdaptorImpl, String optionId, String fieldId, LayoutAdaptor layoutAdaptor)
					throws EQException
	{
		this.userData = null;
		this.layoutAdaptorImpl = layoutAdaptorImpl;

		if (layoutAdaptorImpl != null)
		{
			this.displayButtonLinkAdaptorClass = layoutAdaptor.getClass(session, classLoader, optionId, fieldId, "",
							GAZRecordDataModel.TYP_BUTTONLINK_ATTRIBUTES);
		}

		if (this.displayButtonLinkAdaptorClass != null)
		{
			this.displayButtonLinkAdaptorImpl = getInstance(layoutAdaptorImpl, displayButtonLinkAdaptorClass);
		}
	}

	/**
	 * Return the layout adaptor implementation
	 * 
	 * @return the layout adaptor implementation
	 */
	public ILayoutAdaptor getLayoutAdaptorImpl()
	{
		return layoutAdaptorImpl;
	}

	/**
	 * Return the display button/link adaptor class
	 * 
	 * @return the field adaptor class
	 */
	public Class<IDisplayButtonLinkAdaptor> getDisplayButtonLinkAdaptorClass()
	{
		return displayButtonLinkAdaptorClass;
	}

	/**
	 * Return the attributes adaptor implementation
	 * 
	 * @return the attributes adaptor implementation
	 */
	public IDisplayButtonLinkAdaptor getDisplayButtonLinkAdaptorImpl()
	{
		return displayButtonLinkAdaptorImpl;
	}

	/**
	 * Return an instance of the specified inner class of an outer class
	 * 
	 * @param layoutAdaptor
	 *            - the outer class instance
	 * @param innerClass
	 *            - the class definition of the inner class
	 * 
	 * @return an instance of the inner class
	 */
	private IDisplayButtonLinkAdaptor getInstance(ILayoutAdaptor layoutAdaptor, Class<IDisplayButtonLinkAdaptor> innerClass)
	{
		try
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("getInstance - creating a new instance of inner class [" + innerClass.getName() + "]");
			}
			Constructor<IDisplayButtonLinkAdaptor> constructor = innerClass.getConstructor(layoutAdaptor.getClass());
			IDisplayButtonLinkAdaptor instance = constructor.newInstance(layoutAdaptor);
			return instance;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Determine whether the field is visible or not
	 * 
	 * @param displayButtonLink
	 *            - the DisplayButtonLink bean. These specify whether the field is definitely visible or not, or whether this should
	 *            be determined by calling the isVisible method on the Java user exit
	 * 
	 * @return true if visible
	 */
	public boolean isVisible(DisplayButtonLink displayButtonLink)
	{
		if (DisplayAttributes.VISIBLE_NO.equals(displayButtonLink.getVisible()))
		{
			LOG.debug("isVisible for DisplayButtonLink [" + displayButtonLink.getId() + "] returning false (NO)");
			return false;
		}
		else if (DisplayAttributes.VISIBLE_YES.equals(displayButtonLink.getVisible()))
		{
			LOG.debug("isVisible for DisplayButtonLink [" + displayButtonLink.getId() + "] returning true (YES)");
			return true;
		}
		else if (DisplayAttributes.VISIBLE_SCRIPT.equals(displayButtonLink.getVisible()))
		{
			boolean scriptResult = ELRuntime.runBooleanScript(displayButtonLink.getVisibleExpression(), functionData,
							displayButtonLink.getId(), "visible", true, ELRuntime.REALTYPE_VALUE);
			LOG.debug("isVisible for DisplayButtonLink [" + displayButtonLink.getId() + "] returning returning [" + scriptResult
							+ "] (SCRIPT)");
			return scriptResult;
		}
		else if (DisplayAttributes.VISIBLE_CODE.equals(displayButtonLink.getVisible()))
		{
			if (displayButtonLinkAdaptorImpl == null)
			{
				LOG.error("isVisible for DisplayButtonLink [" + displayButtonLink.getId()
								+ "] returning false(because CODE class not loaded)");
				return false;
			}
			else
			{
				boolean codeResult = displayButtonLinkAdaptorImpl.isVisible(userData);
				LOG.debug("isVisible for DisplayButtonLink [" + displayButtonLink.getId() + "] returning [" + codeResult
								+ "] (CODE)");
				return codeResult;
			}
		}

		else
		{
			throw new EQRuntimeException("DisplayButtonLink [" + displayButtonLink.getId()
							+ "] visibleStatus contains invalid value [" + displayButtonLink.getVisible() + "]");
		}
	}

	/**
	 * Determine whether the field is protected or not
	 * 
	 * @param displayButtonLink
	 *            - the DisplayButtonLink bean. These specify whether the field is definitely protected or not, or whether this
	 *            should be determined by calling the isProtected method on the Java user exit
	 * 
	 * @return true if protected
	 */
	public boolean isProtected(DisplayButtonLink displayButtonLink)
	{
		if (DisplayAttributes.PROTECTED_NO.equals(displayButtonLink.getProtected()))
		{
			LOG.debug("isProtected for DisplayButtonLink [" + displayButtonLink.getId() + "] returning false (NO)");
			return false;
		}
		else if (DisplayAttributes.PROTECTED_YES.equals(displayButtonLink.getProtected()))
		{
			LOG.debug("isProtected for DisplayButtonLink [" + displayButtonLink.getId() + "] returning true (YES)");
			return true;
		}
		else if (DisplayAttributes.PROTECTED_SCRIPT.equals(displayButtonLink.getProtected()))
		{
			boolean scriptResult = ELRuntime.runBooleanScript(displayButtonLink.getProtectedExpression(), functionData,
							displayButtonLink.getId(), "protected", true, ELRuntime.REALTYPE_VALUE);
			LOG.debug("isProtected for DisplayButtonLink [" + displayButtonLink.getId() + "] returning [" + scriptResult
							+ "] (SCRIPT)");
			return scriptResult;
		}
		else if (DisplayAttributes.PROTECTED_CODE.equals(displayButtonLink.getProtected()))
		{
			if (displayButtonLinkAdaptorImpl == null)
			{
				LOG.error("isProtected for DisplayButtonLink [" + displayButtonLink.getId()
								+ "] returning false(because CODE class not loaded)");
				return false;
			}
			else
			{
				boolean codeResult = displayButtonLinkAdaptorImpl.isProtected(userData);
				LOG.debug("isProtected for DisplayButtonLink [" + displayButtonLink.getId() + "] returning [" + codeResult
								+ "] (CODE)");
				return codeResult;
			}
		}

		else
		{
			throw new EQRuntimeException("DisplayButtonLink [" + displayButtonLink.getId()
							+ "] protectedStatus contains invalid value [" + displayButtonLink.getProtected() + "]");
		}
	}

	/**
	 * This method will retrieve the command and parameters depending on the command flag
	 * 
	 * @param displayButtonLink
	 *            - the DisplayButtonLink
	 * 
	 * @return the command and parameter expression
	 */
	public String getCommandParameter(DisplayButtonLink displayButtonLink)
	{
		String result = null;
		if (DisplayButtonLink.COMMAND_SCRIPT.equals(displayButtonLink.getCommand()))
		{
			result = ELRuntime.runStringScript(displayButtonLink.getCommandExpression(), functionData, displayButtonLink.getId(),
							"commandParameter", "", ELRuntime.DB_VALUE);
			LOG.debug("getCommandParameter for DisplayButtonLink [" + displayButtonLink.getId() + "] returning [" + result
							+ "] (SCRIPT)");
		}
		else if (DisplayButtonLink.COMMAND_CODE.equals(displayButtonLink.getCommand()))
		{
			if (displayButtonLinkAdaptorImpl == null)
			{
				LOG.error("getCommandParameter for DisplayButtonLink [" + displayButtonLink.getId()
								+ "] returning false(because CODE class not loaded)");
				result = "";
			}
			else
			{
				result = displayButtonLinkAdaptorImpl.getCommandParameter(userData);
				LOG.debug("getCommandParameter for DisplayButtonLink [" + displayButtonLink.getId() + "] returning [" + result
								+ "] (CODE)");
			}
		}

		else
		{
			throw new EQRuntimeException("DisplayButtonLink [" + displayButtonLink.getId()
							+ "] protectedStatus contains invalid value [" + displayButtonLink.getProtected() + "]");
		}

		if (result == null)
		{
			result = "";
		}
		return result.trim();
	}
}
