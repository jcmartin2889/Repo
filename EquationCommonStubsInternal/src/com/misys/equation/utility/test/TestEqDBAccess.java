package com.misys.equation.utility.test;

import java.sql.ResultSet;
import java.sql.Statement;

import com.misys.equation.common.test.EquationTestCase;

public class TestEqDBAccess extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestEqDBAccess.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	public void test()
	{
		try
		{
			Statement equationStatement = session.getConnection().createStatement();
			ResultSet equationExternalAccountNumbers = equationStatement.executeQuery("SELECT * FROM NEPF");
			while (equationExternalAccountNumbers.next())
			{
				System.out.println(equationExternalAccountNumbers.getString("NEEAN"));
			}
			equationStatement.close();
			Statement equationStatement2 = session.getConnection().createStatement();
			ResultSet equationExternalAccountNumbers2 = equationStatement2.executeQuery("SELECT * FROM GZG011");
			while (equationExternalAccountNumbers2.next())
			{
				System.out.println(equationExternalAccountNumbers2.getString("GZCPNC"));
			}
			equationStatement2.close();
			session.getConnection().close();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
