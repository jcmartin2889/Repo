package com.misys.equation.function.context;

/**
 * Encapsulates the IBM i and BankFusion credentials that are supplied when starting a session
 * 
 * The user and password field values could be either IBM i or BankFusion credentials, depending on the configuration of the system
 */
public class BFEQCredentials
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BFEQCredentials.java 16593 2013-06-24 15:32:19Z perkinj1 $";

	private String userId;
	private String password;
	private String passwordType;
	private String sessionIdentifier;
	private String userLocator;

	/**
	 * Constructor taking user id and password credentials
	 * 
	 * @param userId
	 *            IBM i or BankFusion user id
	 * @param password
	 *            IBM i or BankFusion password
	 * @param passwordType
	 *            an EquationCommonContext password type
	 * @param sessionIdentifier
	 *            The current sessionIdentifier if a session already exists.
	 */
	public BFEQCredentials(String userId, String password, String passwordType, String sessionIdentifier)
	{
		this.userId = userId;
		this.password = password;
		this.passwordType = passwordType;
		this.sessionIdentifier = sessionIdentifier;
	}
	/**
	 * Constructor taking user id and password credentials
	 * 
	 * @param userId
	 *            IBM i or BankFusion user id
	 * @param password
	 *            IBM i or BankFusion password
	 * @param passwordType
	 *            an EquationCommonContext password type
	 * @param sessionIdentifier
	 *            The current sessionIdentifier if a session already exists.
	 * @param userLocator
	 *            The current user locator if a BankFusion session already exists.
	 */
	public BFEQCredentials(String userId, String password, String passwordType, String sessionIdentifier, String userLocator)
	{
		this.userId = userId;
		this.password = password;
		this.passwordType = passwordType;
		this.sessionIdentifier = sessionIdentifier;
		this.userLocator = userLocator;
	}

	/**
	 * Constructor to be called from ActivityStep entry points
	 * 
	 * @param userId
	 *            BankFusion user Id
	 * @param userLocator
	 *            BankFusion user locator
	 * @param sessionIdentifier
	 *            The current sessionIdentifier if a session already exists.
	 */
	public BFEQCredentials(String userId, String userLocator, String sessionIdentifier)
	{
		this.userId = userId;
		this.userLocator = userLocator;
		this.sessionIdentifier = sessionIdentifier;
	}

	/**
	 * @return the User Id, which may be either the IBM i or BankFusion user id, depending on context.
	 */
	public String getUserId()
	{
		return userId;
	}

	public String getPasswordType()
	{
		return passwordType;
	}
	/**
	 * 
	 * @return the BankFusion user locator
	 */
	public String getUserLocator()
	{
		return userLocator;
	}

	/**
	 * Gets the password. This may be either the IBM i or BankFusion password, depending on the mode
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @return The Equation session id
	 */
	public String getSessionIdentifier()
	{
		return sessionIdentifier;
	}
}
