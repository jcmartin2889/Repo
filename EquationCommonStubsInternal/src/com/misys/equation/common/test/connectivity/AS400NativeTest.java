package com.misys.equation.common.test.connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AS400NativeTest
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AS400NativeTest.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	String user = null;
	String password = null;
	String system = null;
	String library = null;

	Connection connection = null;
	private AS400NativeTest(String system, String library, String user, String password)
	{
		try
		{
			this.system = system;
			this.library = library;
			this.user = user;
			this.password = password;
			if (user.toLowerCase().equals("null"))
			{
				user = null;
			}
			if (password.toLowerCase().equals("null"))
			{
				password = null;
			}
			System.out.println("Using:" + system + "," + library + "," + user + "," + password);

			Class.forName("com.ibm.as400.access.AS400JDBCDriver");
			String url = "jdbc:as400://" + system + "/" + library
							+ ";cursor hold=false;do escape processing=false;blocking enabled=true;block size=32;errors=full";
			connection = DriverManager.getConnection(url, user, password);
			Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = statement.executeQuery("SELECT HOSTID FROM HOSTSYSTEM where HOSTTYPE='Equation'");
			if (resultSet.next())
			{
				System.out.println("Successfully connected to: " + resultSet.getString(1) + " Using:" + system + "," + library
								+ "," + user + "," + password);
			}
			resultSet.close();
			statement.close();
			connection.close();
			System.out.println("FINISHED SUCCESSFULLY");
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		System.out.println("USAGE NOTES:");
		System.out.println("	you must pass: [system,library,user,password] where");
		System.out.println("		when running on the as400 set system to \"localhost\"");
		System.out.println("		set library to the name of the MM database");
		System.out.println("		for a null user set to a string containing the word \"null\"");
		System.out.println("		for a null password set to a string containing the word \"null\"");
		if (args.length != 4)
		{
			System.out.println("YOU DID NOT PASS FOUR PARMS");
		}
		else
		{
			System.out.println("Proceeding with test...");
			AS400NativeTest test = new AS400NativeTest(args[0], args[1], args[2], args[3]);
			test.test();
		}
	}
	private void test()
	{

	}
}
