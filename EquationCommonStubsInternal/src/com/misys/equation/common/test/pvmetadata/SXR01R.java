package com.misys.equation.common.test.pvmetadata;

import com.misys.equation.common.access.EquationPVMetaData;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.EqBeanFactory;

public class SXR01R
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SXR01R.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EquationStandardSession session;
	public SXR01R()
	{
		try
		{
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
		SXR01R test = new SXR01R();
		test.test();
	}

	public void test()
	{
		// Have a bash...
		try
		{
			EquationPVMetaData pvmetadata = session.getUnit().getPVMetaData(this.getClass().getSimpleName());

			// xml
			EqBeanFactory eqBeanFactory = EqBeanFactory.getEqBeanFactory();
			String xml = eqBeanFactory.serialiseBeanAsXML(pvmetadata);
			System.out.println(xml);

			// finish
			System.out.println(pvmetadata);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
