package com.misys.equation.function.testmain;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.comparator.ElementComparator;
import com.misys.equation.function.comparator.ElementComparatorToolbox;
import com.misys.equation.function.comparator.ElementDifference;
import com.misys.equation.function.comparator.SynchroniseServiceToolbox;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionInfo;

public class SynchroniseServiceStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SynchroniseServiceStub.java 9097 2010-09-10 10:15:41Z lima12 $";
	EquationStandardSession session;
	EquationUnit unit;
	EquationUser user;

	public SynchroniseServiceStub()
	{
		try
		{
			unit = TestEnvironment.getTestEnvironment().getEquationUnit();
			user = TestEnvironment.getTestEnvironment().getEquationUser();
			session = TestEnvironment.getTestEnvironment().getStandardSession();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters)
	{
		SynchroniseServiceStub test = new SynchroniseServiceStub();
		test.test();
	}

	private boolean test()
	{
		try
		{
			FunctionHandler fh = new FunctionHandler(user, new FunctionInfo("resync", ""));
			fh.doNewTransaction("TS7", "");

			Function function = fh.getFhd().getScreenSetHandler().rtvScreenSetMain().getFunction();

			// create another copy
			String xml = EqBeanFactory.getEqBeanFactory().serialiseBeanAsXML(function);
			Function function2 = (Function) EqBeanFactory.getEqBeanFactory().deserialiseXMLAsBean(xml, Function.class);

			// ResyncServiceToolbox.updatePVFieldSet(session, function2);
			// ResyncServiceToolbox.updateLoadAPIFieldSet(session, function2);
			SynchroniseServiceToolbox.updateUpdateAPIFieldSet(session, function2);

			// compare
			ElementDifference ed = ElementComparatorToolbox.compare(function2, function, "Function", ElementComparator.COPY_NONE);
			System.out.println(ed);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return false;
	}
}
