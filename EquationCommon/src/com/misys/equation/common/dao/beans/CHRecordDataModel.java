package com.misys.equation.common.dao.beans;

/**
 * @author esther.williams
 */
public class CHRecordDataModel extends AbsRecord
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CHRecordDataModel.java 8910 2010-08-26 15:25:20Z MACDONP1 $";
	private static final long serialVersionUID = 1L;
	private final static String RECORD_NAME = "CHPF";

	private String enhancementMnemonic; // CHENM
	private String enhancementDescription; // CHEND
	private String enhancementLibraryName; // CHENL
	private String enhancementInstalledToBase; // CHENB
	private String encryptedEnhancementMnemonic; // CHEEM
	private int installationDate; // CHEID
	private int installationTime; // CHEIT
	private String enhancementLevel; // CHLVL
	private String enhancementUpgrade; // CHUPG
	/**
	 * Construct an empty file
	 * 
	 */
	public CHRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	/**
	 * Construct a model with all fields
	 */
	public CHRecordDataModel(String enhancementMnemonic, String enhancementDescription, String enhancementLibraryName,
					String enhancementInstalledToBase, String encryptedEnhancementMnemonic, int installationDate,
					int installationTime, String enhancementLevel, String enhancementUpgrade)
	{
		super();
		setEqFileName(RECORD_NAME);

		setEnhancementMnemonic(enhancementMnemonic);
		setEnhancementDescription(enhancementDescription);
		setEnhancementLibraryName(enhancementLibraryName);
		setEnhancementInstalledToBase(enhancementInstalledToBase);
		setEncryptedEnhancementMnemonic(encryptedEnhancementMnemonic);
		setInstallationDate(installationDate);
		setInstallationTime(installationTime);
		setEnhancementLevel(enhancementLevel);
		setEnhancementUpgrade(enhancementUpgrade);

	}
	// ---getters and setters----//

	/**
	 * Return the enhancement mnemonic
	 * 
	 * @return the enhancement mnemonic
	 */
	public String getEnhancementMnemonic()
	{
		return enhancementMnemonic;
	}

	/**
	 * Set the enhancement mnemonic
	 * 
	 * @param enhancementMnemonic
	 */
	public void setEnhancementMnemonic(String enhancementMnemonic)
	{
		this.enhancementMnemonic = enhancementMnemonic;
	}

	/**
	 * Return the enhancement description
	 * 
	 * @return the enhancement description
	 */
	public String getEnhancementDescription()
	{
		return enhancementDescription;
	}

	/**
	 * Set the enhancement description
	 * 
	 * @param enhancementDescription
	 */
	public void setEnhancementDescription(String enhancementDescription)
	{
		this.enhancementDescription = enhancementDescription;
	}

	/**
	 * Return the enhancement library name
	 * 
	 * @return the enhancement library name
	 */
	public String getEnhancementLibraryName()
	{
		return enhancementLibraryName;
	}

	/**
	 * Set the enhancement library name
	 * 
	 * @param enhancementLibraryName
	 */
	public void setEnhancementLibraryName(String enhancementLibraryName)
	{
		this.enhancementLibraryName = enhancementLibraryName;
	}

	/**
	 * Return the enhancement installed to base flag
	 * 
	 * @return the enhancement installed to base flag
	 */
	public String getEnhancementInstalledToBase()
	{
		return enhancementInstalledToBase;
	}

	/**
	 * Set the enhancement installed to base flag
	 * 
	 * @param enhancementInstalledToBase
	 */
	public void setEnhancementInstalledToBase(String enhancementInstalledToBase)
	{
		this.enhancementInstalledToBase = enhancementInstalledToBase;
	}

	/**
	 * Return the encrypted enhancement mnemonic
	 * 
	 * @return the encrypted enhancement mnemonic
	 */
	public String getEncryptedEnhancementMnemonic()
	{
		return encryptedEnhancementMnemonic;
	}

	/**
	 * Set the encrypted enhancement mnemonic
	 * 
	 * @param encryptedEnhancementMnemonic
	 */
	public void setEncryptedEnhancementMnemonic(String encryptedEnhancementMnemonic)
	{
		this.encryptedEnhancementMnemonic = encryptedEnhancementMnemonic;
	}

	/**
	 * Return the installation date
	 * 
	 * @return the installation date
	 */
	public int getInstallationDate()
	{
		return installationDate;
	}

	/**
	 * Set the installation date
	 * 
	 * @param installationDate
	 */
	public void setInstallationDate(int installationDate)
	{
		this.installationDate = installationDate;
	}

	/**
	 * Return the installation time
	 * 
	 * @return the installation time
	 */
	public int getInstallationTime()
	{
		return installationTime;
	}

	/**
	 * Set the installation time
	 * 
	 * @param installationTime
	 */
	public void setInstallationTime(int installationTime)
	{
		this.installationTime = installationTime;
	}

	/**
	 * Return the enhancement level
	 * 
	 * @return the enhancement level
	 */
	public String getEnhancementLevel()
	{
		return enhancementLevel;
	}

	/**
	 * Set the enhancement level
	 * 
	 * @param enhancementLevel
	 */
	public void setEnhancementLevel(String enhancementLevel)
	{
		this.enhancementLevel = enhancementLevel;
	}

	/**
	 * Return the enhancement upgrade flag
	 * 
	 * @return the enhancement upgrade flag
	 */
	public String getEnhancementUpgrade()
	{
		return enhancementUpgrade;
	}

	/**
	 * Set the enhancement upgrade flag
	 * 
	 * @param enhancementUpgrade
	 */
	public void setEnhancementUpgrade(String enhancementUpgrade)
	{
		this.enhancementUpgrade = enhancementUpgrade;
	}

}
