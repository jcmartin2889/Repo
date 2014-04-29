package com.misys.equation.utility.test;

import com.misys.equation.common.access.EquationStandardQueryList;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.test.TestEnvironment;

public class EquationStandardQueryListStub
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationStandardQueryListStub.java 11984 2011-09-30 16:17:27Z lima12 $";
	EquationStandardQueryListStub(String sql)
	{
		try
		{
			TestEnvironment env = TestEnvironment.getTestEnvironment();
			EquationStandardSession session = env.getStandardSession();
			EquationStandardQueryList query = new EquationStandardQueryList(sql, session);

			session.retrieveEquationQueryList(query);

			query.moveFirst();
			while (query.next())
			{
				System.out.println(query.getFieldValue("C8CUR") + query.getFieldValue("C8CCY"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		System.out.println("ALL Currency");
		new EquationStandardQueryListStub("select * from c8pf");
		System.out.println("ALL G*");
		new EquationStandardQueryListStub("select * from c8pf where c8ccy like 'G%'");
	}

}
