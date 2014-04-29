package com.misys.equation.common.dao.beans;

import com.misys.equation.common.utilities.Toolbox;

/**
 * OC2Record data-model.
 * 
 * @author deroset
 * 
 */
public class OC2RecordDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OC2RecordDataModel.java 4546 2009-08-27 23:01:54Z esther.williams $";
	private static final long serialVersionUID = 1L;
	private final static String RECORD_NAME = "OCPF";
	private String userId; // OCUSID
	private byte[] password; // OCPWD
	private int pwdDate; // OCPWDM

	/**
	 * Construct an empty file
	 * 
	 */
	public OC2RecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	/**
	 * Construct a new OC password
	 * 
	 * @param userId
	 *            - user Id
	 */
	public OC2RecordDataModel(String userId)
	{
		super();
		setEqFileName(RECORD_NAME);
		this.userId = Toolbox.trim(userId, 4);
	}

	// ---getters and setters----//

	/**
	 * Return the option id
	 * 
	 * @return the option id
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * Return the password
	 * 
	 * @return the password
	 */
	public byte[] getPassword()
	{
		return password;
	}

	/**
	 * Return the password expiry date
	 * 
	 * @return the password expiry date
	 */
	public int getPwdDate()
	{
		return pwdDate;
	}

	/**
	 * Set the user id
	 * 
	 * @param userId
	 *            - the user id
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * Set the password
	 * 
	 * @param password
	 *            - the password
	 */
	public void setPassword(byte[] password)
	{
		this.password = password;
	}

	/**
	 * Set the password expiry date
	 * 
	 * @param pwdDate
	 *            - the password expiry date
	 */
	public void setPwdDate(int pwdDate)
	{
		this.pwdDate = pwdDate;
	}
}
