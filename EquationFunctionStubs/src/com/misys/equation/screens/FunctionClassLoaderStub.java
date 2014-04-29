package com.misys.equation.screens;

import junit.framework.TestCase;

import com.misys.equation.common.access.EquationFieldDefinition;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.function.adaptor.AttributesAdaptor;
import com.misys.equation.function.adaptor.FieldAdaptor;
import com.misys.equation.function.adaptor.FunctionAdaptor;
import com.misys.equation.function.adaptor.IAttributesAdaptor;
import com.misys.equation.function.adaptor.IFieldAdaptor;
import com.misys.equation.function.adaptor.IFunctionAdaptor;
import com.misys.equation.function.adaptor.ILayoutAdaptor;
import com.misys.equation.function.adaptor.IValueAdaptor;
import com.misys.equation.function.adaptor.LayoutAdaptor;
import com.misys.equation.function.adaptor.ValueAdaptor;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionAdaptorHandler;
import com.misys.equation.function.runtime.LayoutAdaptorHandler;
import com.misys.equation.function.runtime.UserData;

// CLASS LOADER
public class FunctionClassLoaderStub extends TestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionClassLoaderStub.java 4727 2009-09-15 13:12:18Z esther.williams $";

	EquationStandardSession session;
	EquationUnit unit;

	@Override
	public void setUp()
	{
		try
		{
			session = TestEnvironment.getTestEnvironment().getStandardSession();
			unit = TestEnvironment.getTestEnvironment().getEquationUnit();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public void test()
	{
		// Have a bash...
		try
		{
			// Set up a dummy Function Data
			EquationFieldDefinition fieldDef = new EquationFieldDefinition();
			fieldDef.setFieldDataTypeString("A");
			fieldDef.setFieldLength(10);
			FunctionData functionData = new FunctionData();
			functionData.insertFieldData("XMandatory", fieldDef, "MAND", false);
			functionData.insertFieldData("Protected", fieldDef, "true", false);
			functionData.insertFieldData("Visible", fieldDef, "true", false);
			functionData.insertFieldData("XValid", fieldDef, "ERR", false);

			// User data
			UserData userData = new UserData(null, functionData);

			// Option
			String optionId = "ALZ";

			// Function Adaptor Handler
			FunctionAdaptorHandler functionAdaptorHandler = new FunctionAdaptorHandler();

			// Layout Adaptor Handler
			LayoutAdaptorHandler layoutAdaptorHandler = new LayoutAdaptorHandler();

			// functionAdaptorHandler.getClassLoader().forceReloadClass(session, optionId);

			// Function Adaptor
			FunctionAdaptor functionAdaptor = functionAdaptorHandler.getFunctionAdaptor(session, optionId);
			IFunctionAdaptor ifua = functionAdaptor.getFunctionAdaptorImpl();
			System.out.println("Function Level");
			// System.out.println("- validateMode = " + ifua.validateMode(0, userData));
			// TODO: validateMode and defaultMode currently return void

			// Function Adaptor
			LayoutAdaptor layoutAdaptor = layoutAdaptorHandler.getLayoutAdaptor(session, optionId);
			ILayoutAdaptor ila = layoutAdaptor.getLayoutAdaptorImpl();
			System.out.println("Layout Level");
			System.out.println("- nextscreen = " + ila.nextScreen(0, userData));

			doField(functionAdaptor, layoutAdaptor, "XMandatory", userData);
			doField(functionAdaptor, layoutAdaptor, "XMandatory2", userData);
			doField(functionAdaptor, layoutAdaptor, "XVisible", userData);
			doField(functionAdaptor, layoutAdaptor, "XVisible2", userData);
			doField(functionAdaptor, layoutAdaptor, "XProtected", userData);
			doField(functionAdaptor, layoutAdaptor, "XProtected2", userData);
			doField(functionAdaptor, layoutAdaptor, "XValid", userData);

			// Value Adaptor
			ValueAdaptor valueAdaptor = functionAdaptor.getValueAdaptor(session, "XAMT", "GWV30R_$NDPAM", "2");
			IValueAdaptor vfa = valueAdaptor.getValueAdaptorImpl();
			System.out.println("Value Level: £NDPAM");
			System.out.println("- Get value= " + vfa.getValue("A", new UserData(null, functionData)));

			// Value Adaptor
			valueAdaptor = functionAdaptor.getValueAdaptor(session, "XAMT", "GWV30R_$NODIG", "2");
			vfa = valueAdaptor.getValueAdaptorImpl();
			System.out.println("Value Level: £NODIG");
			System.out.println("- Get value= " + vfa.getValue("B", new UserData(null, functionData)));

			System.out.println("Done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void doField(FunctionAdaptor functionAdaptor, LayoutAdaptor layoutAdaptor, String fieldName, UserData userData)
					throws EQException
	{
		FieldAdaptor fieldAdaptor = functionAdaptor.getFieldAdaptor(session, fieldName, "1");
		IFieldAdaptor ifa = fieldAdaptor.getFieldAdaptorImpl();
		AttributesAdaptor attributesAdaptor = layoutAdaptor.getAttributesAdaptor(session, fieldName);
		IAttributesAdaptor iaa = attributesAdaptor.getAttributesAdaptorImpl();

		System.out.println("InputField Level: " + fieldName);
		System.out.println("- Is mandatory = " + ifa.isMandatory(userData));
		System.out.println("DisplayAttributes Level: " + fieldName);
		if (iaa != null)
		{
			System.out.println("- Is protected = " + iaa.isProtected(userData));
			System.out.println("- Is visible = " + iaa.isVisible(userData));
		}

	}
}
