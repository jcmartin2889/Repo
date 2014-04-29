package com.misys.equation.common.files.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Generic base class for accessors
 * 
 * @author perkinj1
 */
public class GenericAccessor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GenericAccessor.java 6893 2010-04-12 04:00:00Z macdonp1 $";
	PreparedStatement ps;
	Connection connection;
	ResultSet rs;
	/**
	 * Ensure that SQL resources are closed
	 */
	@Override
	public void finalize()
	{
		try
		{
			if (rs != null)
			{
				rs.close();
			}
		}
		catch (SQLException e)
		{
			// Throw an unchecked exception
			throw new RuntimeException(e);
		}
		try
		{
			if (ps != null)
			{
				ps.close();
			}
		}
		catch (SQLException e)
		{
			// Throw an unchecked exception
			throw new RuntimeException(e);
		}
	}

	/**
	 * Closes database resources
	 */
	public void close()
	{
		finalize();
	}

	/**
	 * Helper to convert a String from a resultset to a char
	 * 
	 * @param rs
	 * @param columnIndex
	 * @return char
	 */
	public static char getResultSetChar(ResultSet rs, int columnIndex)
	{
		String temp = null;
		try
		{
			temp = rs.getString(columnIndex);
		}
		catch (SQLException e)
		{
			// Throw an unchecked exception
			throw new RuntimeException(e);
		}

		return (temp != null && temp.length() > 0) ? temp.charAt(0) : ' ';
	}

	/**
	 * Helper to convert a char to a String for SQL. TODO: Move into a base class
	 * 
	 * @param ch
	 * @return String containing the supplied char
	 */
	public static String charToString(char ch)
	{
		char[] array = new char[] { ch };
		return new String(array);
	}

}
