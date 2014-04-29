package com.misys.equation.common.dao.beans;

/**
 * GYWPF Data model
 * 
 */
public class BFRecordDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BFRecordDataModel.java 14832 2012-11-05 19:03:33Z williae1 $";

	private static final long serialVersionUID = 1L;
	private final static String RECORD_NAME = "BFPF";

	private String equationFile; // BFEQF
	private String informationGroup; // BFCIGR
	private int itemGroupNumber; // BFINIG
	private String itemName; // BFCITM

	/**
	 * Default constructor
	 */
	public BFRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);

	}

	/**
	 * Construct a new GYW record key
	 * 
	 * @param informationGroup
	 *            - the information group
	 * @param itemName
	 *            - the item name
	 */
	public BFRecordDataModel(String informationGroup, String itemName)
	{
		super();
		setEqFileName(RECORD_NAME);

		setInformationGroup(informationGroup);
		setItemName(itemName);
	}

	// ---getters and setters----//

	/**
	 * Return the Equation file
	 */
	public String getEquationFile()
	{
		return equationFile;
	}

	/**
	 * Set the Equation file
	 * 
	 * @param equationFile
	 *            - the Equation file
	 */
	public void setEquationFile(String equationFile)
	{
		this.equationFile = equationFile;
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

	/**
	 * Return the item group number
	 * 
	 * @return the item group number
	 */
	public int getItemGroupNumber()
	{
		return itemGroupNumber;
	}

	/**
	 * Set the item group name
	 * 
	 * @param itemGroupNumber
	 *            - the item group name
	 */
	public void setItemGroupNumber(int itemGroupNumber)
	{
		this.itemGroupNumber = itemGroupNumber;
	}

	/**
	 * Return the item name
	 * 
	 * @return the item name
	 */
	public String getItemName()
	{
		return itemName;
	}

	/**
	 * Set the item name
	 * 
	 * @param itemName
	 *            - the item name
	 */
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

}