package com.misys.equation.common.dao.beans;

/**
 * GYWPF Data model
 * 
 */
public class BTRecordDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BTRecordDataModel.java 14832 2012-11-05 19:03:33Z williae1 $";

	private static final long serialVersionUID = 1L;
	private final static String RECORD_NAME = "BTPF";

	// list of files
	public final static String INFO_ACCOUNT = "AC";
	public final static String INFO_BRANCH = "BR";
	public final static String INFO_COLLATERAL = "CO";
	public final static String INFO_CUSTOMER = "CU";
	public final static String INFO_DEAL = "DL";
	public final static String INFO_TRANSACTION = "TR";

	// default group
	public final static String DEFAULT_GROUP = "<DFT>";

	/** Parameter file */
	private String parameterFile; // BTEQF

	/** Parameter value */
	private String parameterValue; // BTCFL

	/** Information group */
	private String informationGroup; // BTIGR

	/**
	 * Default constructor
	 */
	public BTRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);

	}

	/**
	 * Construct a new BT record key
	 * 
	 * @param parameteFile
	 *            - this identifies the additional info required (e.g. customer, branch, account, etc)
	 * @param parameterValue
	 *            - this identifies the subgroup with the parameter file (e.g. customer type, branch, account type)
	 */
	public BTRecordDataModel(String parameteFile, String parameterValue)
	{
		super();
		setEqFileName(RECORD_NAME);

		setParameterFile(parameteFile);
		setParameterValue(parameterValue);
	}

	// ---getters and setters----//

	/**
	 * Returns the parameter file (e.g. customer, branch, account, etc)
	 * 
	 * @return the parameter file (e.g. customer, branch, account, etc)
	 */
	public String getParameterFile()
	{
		return parameterFile;
	}

	/**
	 * Set the parameter file (e.g. customer, branch, account, etc)
	 * 
	 * @param parameterFile
	 *            - the parameter file (e.g. customer, branch, account, etc)
	 */
	public void setParameterFile(String parameterFile)
	{
		this.parameterFile = parameterFile;
	}

	/**
	 * Return the parameter value (e.g. customer type, branch mnemonic, account type, etc)
	 * 
	 * @return the parameter value (e.g. customer type, branch mnemonic, account type, etc)
	 */
	public String getParameterValue()
	{
		return parameterValue;
	}

	/**
	 * Set the parameter value (e.g. customer type, branch mnemonic, account type, etc)
	 * 
	 * @param parameterValue
	 *            - the parameter value (e.g. customer type, branch mnemonic, account type, etc)
	 */
	public void setParameterValue(String parameterValue)
	{
		this.parameterValue = parameterValue;
	}

	/**
	 * Return the information group
	 * 
	 * @return the information group
	 */
	public String getInformationGroup()
	{
		return informationGroup;
	}

	/**
	 * Set the information group
	 * 
	 * @param informationGroup
	 *            - the information group
	 */
	public void setInformationGroup(String informationGroup)
	{
		this.informationGroup = informationGroup;
	}

}
