package com.misys.equation.function.adaptor;

import java.lang.reflect.Constructor;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQRuntimeException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.FunctionClassLoader;
import com.misys.equation.function.beans.DisplayLabel;
import com.misys.equation.function.el.ELRuntime;

/**
 * This class manages the runtime interaction with the user exit code for DisplayLabel
 * <p>
 * The class encapsulates the visible state checking, and will call the appropriate user exit class method implementation if
 * appropriate.
 */
public class DisplayLabelAdaptor extends Adaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DisplayLabelAdaptor.java 10552 2011-02-25 11:17:20Z capilid1 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(DisplayLabelAdaptor.class);

	private ILayoutAdaptor layoutAdaptorImpl;
	private Class<IDisplayLabelAdaptor> displayLabelAdaptorClass;
	private IDisplayLabelAdaptor displayLabelAdaptorImpl;

	/**
	 * Construct a Label Adaptor
	 * 
	 * @param layoutAdaptorImpl
	 *            - an instance of the outer class
	 * @param fieldId
	 *            - the field name
	 */
	@SuppressWarnings("unchecked")
	public DisplayLabelAdaptor(EquationStandardSession session, FunctionClassLoader classLoader, ILayoutAdaptor layoutAdaptorImpl,
					String optionId, String fieldId, LayoutAdaptor layoutAdaptor) throws EQException
	{
		this.userData = null;
		this.layoutAdaptorImpl = layoutAdaptorImpl;

		if (layoutAdaptorImpl != null)
		{
			this.displayLabelAdaptorClass = layoutAdaptor.getClass(session, classLoader, optionId, fieldId, "",
							GAZRecordDataModel.TYP_LABEL_ATTRIBUTES);
		}

		if (this.displayLabelAdaptorClass != null)
		{
			this.displayLabelAdaptorImpl = getInstance(layoutAdaptorImpl, displayLabelAdaptorClass);
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
	 * Return the display label adaptor class
	 * 
	 * @return the field adaptor class
	 */
	public Class<IDisplayLabelAdaptor> getDisplayLabelAdaptorClass()
	{
		return displayLabelAdaptorClass;
	}

	/**
	 * Return the attributes adaptor implementation
	 * 
	 * @return the attributes adaptor implementation
	 */
	public IDisplayLabelAdaptor getDisplayLabelAdaptorImpl()
	{
		return displayLabelAdaptorImpl;
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
	private IDisplayLabelAdaptor getInstance(ILayoutAdaptor layoutAdaptor, Class<IDisplayLabelAdaptor> innerClass)
	{
		try
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("getInstance - creating a new instance of inner class [" + innerClass.getName() + "]");
			}
			Constructor<IDisplayLabelAdaptor> constructor = innerClass.getConstructor(layoutAdaptor.getClass());
			IDisplayLabelAdaptor instance = constructor.newInstance(layoutAdaptor);
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
	 * @param attributes
	 *            - DisplayLabel for the field. These specify whether the field is definitely visible or not, or whether this should
	 *            be determined by calling the isVisible method on the Java user exit
	 * 
	 * @return true if visible
	 */
	public boolean isVisible(DisplayLabel displayLabel)
	{
		if (DisplayLabel.VISIBLE_NO.equals(displayLabel.getVisible()))
		{
			LOG.debug("isVisible for DisplayLabel [" + displayLabel.getId() + "] returning false (NO)");
			return false;
		}
		else if (DisplayLabel.VISIBLE_YES.equals(displayLabel.getVisible()))
		{
			LOG.debug("isVisible for DisplayLabel [" + displayLabel.getId() + "] returning true (YES)");
			return true;
		}
		else if (DisplayLabel.VISIBLE_SCRIPT.equals(displayLabel.getVisible()))
		{
			boolean scriptResult = ELRuntime.runBooleanScript(displayLabel.getVisibleExpression(), functionData, displayLabel
							.getId(), "visible", true, ELRuntime.REALTYPE_VALUE);
			LOG.debug("isVisible for DisplayLabel [" + displayLabel.getId() + "] returning returning [" + scriptResult
							+ "] (SCRIPT)");
			return scriptResult;
		}
		else if (DisplayLabel.VISIBLE_CODE.equals(displayLabel.getVisible()))
		{
			if (displayLabelAdaptorImpl == null)
			{
				LOG.error("isVisible for DisplayLabel [" + displayLabel.getId()
								+ "] returning false(because CODE class not loaded)");
				return false;
			}
			else
			{
				boolean codeResult = displayLabelAdaptorImpl.isVisible(userData);
				LOG.debug("isVisible for DisplayGroup [" + displayLabel.getId() + "] returning [" + codeResult + "] (SCRIPT)");
				return codeResult;
			}
		}

		else
		{
			throw new EQRuntimeException("DisplayLabel [" + displayLabel.getId() + "] visibleStatus contains invalid value ["
							+ displayLabel.getVisible() + "]");
		}
	}
}
