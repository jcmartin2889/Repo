package com.misys.equation.common.dao.beans;

import com.misys.equation.common.utilities.Toolbox;

/**
 * Class of user data-model.
 * 
 * @author deroset
 * 
 */
public class WFRecordDataModel extends AbsRecord
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WFRecordDataModel.java 15470 2013-03-08 15:56:33Z whittap1 $";
	private static final long serialVersionUID = 1L;
	private final static String RECORD_NAME = "WFPF";

	private String classOfUser = ""; // WFCOA
	private String description = ""; // WFCOAD
	private String authorisationLevel = ""; // WFAGXL

	/**
	 * Default constructor
	 */
	public WFRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);

	}

	/**
	 * Construct a new record with the class of user key field
	 * 
	 * @param classOfUser
	 *            - Class of user
	 */
	public WFRecordDataModel(String classOfUser)
	{
		super();
		setEqFileName(RECORD_NAME);

		setClassOfUser(classOfUser);
	}

	/**
	 * Return the keys
	 * 
	 * @return the keys
	 */
	public String getKey()
	{
		return classOfUser;
	}

	// ---getters and setters----//

	/**
	 * Return the class of user
	 * 
	 * @return the class of user
	 */
	public String getClassOfUser()
	{
		return classOfUser;
	}

	/**
	 * Set the class of user
	 * 
	 * @param classOfUser
	 *            - the class of user
	 */
	public void setClassOfUser(String classOfUser)
	{
		this.classOfUser = Toolbox.trim(classOfUser, 10);
	}

	/**
	 * Return the description
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Set the description
	 * 
	 * @param description
	 *            - the description
	 */
	public void setDescription(String description)
	{
		this.description = Toolbox.trim(description, 35);
	}

	/**
	 * Return the authorisation level
	 * 
	 * @return the authorisation level
	 */
	public String getAuthorisationLevel()
	{
		return authorisationLevel;
	}

	/**
	 * Set the authorisation level
	 * 
	 * @param authorisationLevel
	 *            - the authorisation level
	 */
	public void setAuthorisationLevel(String authorisationLevel)
	{
		this.authorisationLevel = Toolbox.trim(authorisationLevel, 2);
	}
}