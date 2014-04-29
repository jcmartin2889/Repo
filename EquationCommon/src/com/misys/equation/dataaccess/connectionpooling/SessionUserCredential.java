package com.misys.equation.dataaccess.connectionpooling;

/**
 * This abstract class represents a user Credential object. This object is used by the connection pool if to determine if the user
 * should have access to the database prior to returning a connection.
 * 
 * Different connection pools might want to extend this class in order to determine if a particular user should be given access.
 * 
 * @author camillen
 * 
 */
public class SessionUserCredential
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SessionUserCredential.java 7607 2010-06-01 17:05:56Z MACDONP1 $";
	// User name
	private final String userName;
	private final String system;
	private final String unit;

	/**
	 * Constructor for the UserCredential
	 * 
	 * @param userName
	 */
	public SessionUserCredential(final String userName, final String system, final String unit)
	{
		super();
		this.userName = userName;
		this.system = system;
		this.unit = unit;
	}

	/**
	 * 
	 * @return
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * 
	 * @return
	 */
	public String getSystem()
	{
		return system;
	}

	/**
	 * 
	 * @return
	 */
	public String getUnit()
	{
		return unit;
	}

}
