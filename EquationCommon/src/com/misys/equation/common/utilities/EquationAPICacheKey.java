package com.misys.equation.common.utilities;

public class EquationAPICacheKey
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationAPICacheKey.java 7607 2010-06-01 17:05:56Z MACDONP1 $";
	private String language;
	private String pvName;
	private String decode;
	private String key;
	private final String blankAllowed;
	private final String newCode;
	private final String security;

	private String generatedResultKey;

	/**
	 * Construct a PV result key
	 * 
	 * @param language
	 *            - the language
	 * @param pvName
	 *            - the pv Name
	 * @param decode
	 *            - the decode
	 * @param blankAllowed
	 *            - blank allowed?
	 * @param newCode
	 *            - new code?
	 * @param security
	 *            - security
	 * @param key
	 *            - the key
	 */
	public EquationAPICacheKey(String language, String pvName, String decode, String blankAllowed, String newCode, String security,
					String key)
	{
		this.language = language;
		this.pvName = pvName;
		this.decode = decode;
		this.key = key;
		this.blankAllowed = blankAllowed;
		this.newCode = newCode;
		this.security = security;
	}

	/**
	 * Generate the PV result key
	 * 
	 * @return the generated PV result key
	 */
	private String internal_generateResultKey()
	{
		return Toolbox.pad(language, 2) + Toolbox.pad(pvName, 10) + Toolbox.pad(decode, 1) + Toolbox.pad(blankAllowed, 1)
						+ Toolbox.pad(newCode, 1) + Toolbox.pad(security, 1) + key;
	}

	/**
	 * Return the generated
	 * 
	 * @return the generated PV result key
	 */
	public String generateResultKey()
	{
		generatedResultKey = internal_generateResultKey();
		return generatedResultKey;
	}

	/**
	 * Return the user's language
	 * 
	 * @return the user's language
	 */
	public String getLanguage()
	{
		return language;
	}

	/**
	 * Set the user's language
	 * 
	 * @param language
	 *            - the user's language
	 */
	public void setLanguage(String language)
	{
		this.language = language;
	}

	/**
	 * Return the PV name
	 * 
	 * @return the PV name
	 */
	public String getPvName()
	{
		return pvName;
	}

	/**
	 * Set the PV name
	 * 
	 * @param pvName
	 *            - the PV name
	 */
	public void setPvName(String pvName)
	{
		this.pvName = pvName;
	}

	/**
	 * Return the decode
	 * 
	 * @return the decode
	 */
	public String getDecode()
	{
		return decode;
	}

	/**
	 * Set the decode
	 * 
	 * @param decode
	 *            - the decode
	 */
	public void setDecode(String decode)
	{
		this.decode = decode;
	}

	/**
	 * Return the key
	 * 
	 * @return the key
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * Set the key
	 * 
	 * @param key
	 *            - the key
	 */
	public void setKey(String key)
	{
		this.key = key;
	}

	/**
	 * Return the String representation (generated result key)
	 * 
	 * @return the String representation (generated result key)
	 */
	@Override
	public String toString()
	{
		if (generatedResultKey != null)
		{
			return generatedResultKey;
		}
		return "";
	}

}
