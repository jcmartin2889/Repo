package com.misys.equation.common.dao.beans;

import com.misys.equation.common.utilities.Toolbox;

/**
 * GDRecord data-model.
 * 
 * @author deroset
 * 
 */
public class GDRecordDataModel extends AbsRecord
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GDRecordDataModel.java 4546 2009-08-27 23:01:54Z esther.williams $";
	private static final long serialVersionUID = 1L;
	private final static String RECORD_NAME = "GDPF";
	private String userId = ""; // GDUSID
	private String optionId = ""; // GDOID
	/**
	 * Construct an empty file
	 * 
	 */
	public GDRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}
	/**
	 * Construct a GD record with default values
	 * 
	 * @param optionId
	 *            - option id
	 * @param programName
	 *            - program name
	 * @param programTitle
	 *            - program title
	 * @param application
	 *            - application
	 */
	public GDRecordDataModel(String userId, String optionId)
	{
		super();
		setEqFileName(RECORD_NAME);

		setUserId(userId);
		setOptionId(optionId);

	}

	// ---getters and setters----//

	/**
	 * Return the Equation user identification (only first 4 characters of an iSeries profile)
	 * 
	 * @return the Equation user identification
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * Set the Equation user identification (only first 4 characters of an iSeries profile)
	 * 
	 * @param Equation
	 *            user identification - the Equation user identification
	 */
	public void setUserId(String userId)
	{
		this.userId = Toolbox.trim(userId, 4);
	}

	/**
	 * Return the option id
	 * 
	 * @return the option id
	 */
	public String getOptionId()
	{
		return optionId;
	}

	/**
	 * Set the option id
	 * 
	 * @param optionId
	 *            - the option id
	 */
	public void setOptionId(String optionId)
	{
		this.optionId = Toolbox.trim(optionId, 3);
	}

}
