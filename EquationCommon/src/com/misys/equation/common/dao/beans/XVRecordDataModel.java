package com.misys.equation.common.dao.beans;

/**
 * XVRecord data-model.
 * 
 * @author deroset
 * 
 */
public class XVRecordDataModel extends AbsRecord
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: XVRecordDataModel.java 4546 2009-08-27 23:01:54Z esther.williams $";
	private static final long serialVersionUID = 1L;
	private final static String RECORD_NAME = "XVPF";

	private String blankAllowed = "f"; // XVBLN
	private int dateLastMaintainedD; // XVDLMD
	private int dateLastMaintainedM; // XVDLMM
	private int dateLastMaintainedY; // XVDLMY
	private String decode = ""; // XVDCD
	private String fieldName = ""; // XVFLN
	private String lastRecordIndicator = ""; // XVLRI
	private String newCode = ""; // XVNEW
	private String promptAvailable = ""; // XVPAV
	private String sysProgram = ""; // XVPGM
	private String pvModule = ""; // XVPGMV
	private int seqNo; // XVSQN
	private int updateLevelNo; // XVULV

	/**
	 * Construct an empty file
	 * 
	 */
	public XVRecordDataModel()
	{
		super();
	}

	/**
	 * Construct an XV record key
	 * 
	 * @param pfFile
	 *            file name
	 */
	public XVRecordDataModel(String fileName)
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	/**
	 * Create a default XV record for a database file
	 * 
	 * @param fileName
	 *            file name
	 * @param libraryName
	 *            library
	 * @param fileType
	 *            file type
	 */
	public XVRecordDataModel(String sysProgram, String fieldName)
	{
		super();
		setEqFileName(RECORD_NAME);

		setSysProgram(sysProgram);
		setFieldName(fieldName);
	}

	// ---getters and setters----//

	/**
	 * Return the blank allowed flag
	 * 
	 * @return the blank allowed flag
	 */
	public String getBlankAllowed()
	{
		return blankAllowed;
	}

	/**
	 * Return the day last maintained
	 * 
	 * @return the day last maintained
	 */
	public int getDateLastMaintainedD()
	{
		return dateLastMaintainedD;
	}

	/**
	 * Set the month last maintained
	 * 
	 * @return the dateLastMaintainedM - the month last maintained
	 */
	public int getDateLastMaintainedM()
	{
		return dateLastMaintainedM;
	}

	/**
	 * Return the year last maintained
	 * 
	 * @return the dateLastMaintainedY - the year last maintained
	 */
	public int getDateLastMaintainedY()
	{
		return dateLastMaintainedY;
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
	 * Return the field name
	 * 
	 * @return the field name
	 */
	public String getFieldName()
	{
		return fieldName;
	}

	/**
	 * Return the last record indicator
	 * 
	 * @return the last record indicator
	 */
	public String getLastRecordIndicator()
	{
		return lastRecordIndicator;
	}

	/**
	 * Return the new code flag
	 * 
	 * @return the new code flag
	 */
	public String getNewCode()
	{
		return newCode;
	}

	/**
	 * Return the prompt available flag
	 * 
	 * @return the prompt available flag
	 */
	public String getPromptAvailable()
	{
		return promptAvailable;
	}

	/**
	 * Return the system program
	 * 
	 * @return the system program
	 */
	public String getSysProgram()
	{
		return sysProgram;
	}

	/**
	 * Return the PV module
	 * 
	 * @return the PV module
	 */
	public String getPvModule()
	{
		return pvModule;
	}

	/**
	 * Return the sequence number
	 * 
	 * @return the sequence number
	 */
	public int getSeqNo()
	{
		return seqNo;
	}

	/**
	 * Return the update level number
	 * 
	 * @return the update level number
	 */
	public int getUpdateLevelNo()
	{
		return updateLevelNo;
	}

	/**
	 * Set the blank allowed flag
	 * 
	 * @param blankAllowed
	 *            - the blank allowed flag to set
	 */
	public void setBlankAllowed(String blankAllowed)
	{
		this.blankAllowed = blankAllowed;
	}

	/**
	 * Set the day last maintained
	 * 
	 * @param dateLastMaintainedD
	 *            - the day last maintained to set
	 */
	public void setDateLastMaintainedD(int dateLastMaintainedD)
	{
		this.dateLastMaintainedD = dateLastMaintainedD;
	}

	/**
	 * Set the month last maintained
	 * 
	 * @param dateLastMaintainedM
	 *            - the month last maintained to set
	 */
	public void setDateLastMaintainedM(int dateLastMaintainedM)
	{
		this.dateLastMaintainedM = dateLastMaintainedM;
	}

	/**
	 * Set the year last maintained
	 * 
	 * @param dateLastMaintainedY
	 *            - the year last maintained to set
	 */
	public void setDateLastMaintainedY(int dateLastMaintainedY)
	{
		this.dateLastMaintainedY = dateLastMaintainedY;
	}

	/**
	 * Set the decode
	 * 
	 * @param decode
	 *            - the decode to set
	 */
	public void setDecode(String decode)
	{
		this.decode = decode;
	}

	/**
	 * Set the field name
	 * 
	 * @param fieldName
	 *            - the field name to set
	 */
	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	/**
	 * Set the last record indicator
	 * 
	 * @param lastRecordIndicator
	 *            - the last record indicator to set
	 */
	public void setLastRecordIndicator(String lastRecordIndicator)
	{
		this.lastRecordIndicator = lastRecordIndicator;
	}

	/**
	 * Set the new code flag
	 * 
	 * @param newCode
	 *            - the new code flag to set
	 */
	public void setNewCode(String newCode)
	{
		this.newCode = newCode;
	}

	/**
	 * Set the prompt available
	 * 
	 * @param promptAvailable
	 *            - the prompt available to set
	 */
	public void setPromptAvailable(String promptAvailable)
	{
		this.promptAvailable = promptAvailable;
	}

	/**
	 * Set the system program
	 * 
	 * @param sysProgram
	 *            - the system program to set
	 */
	public void setSysProgram(String sysProgram)
	{
		this.sysProgram = sysProgram;
	}

	/**
	 * Set the PV module
	 * 
	 * @param pvModule
	 *            - the PV module to set
	 */
	public void setPvModule(String pvModule)
	{
		this.pvModule = pvModule;
	}

	/**
	 * Set the sequence number
	 * 
	 * @param seqNo
	 *            - the sequence number to set
	 */
	public void setSeqNo(int seqNo)
	{
		this.seqNo = seqNo;
	}

	/**
	 * Set the update level number
	 * 
	 * @param updateLevelNo
	 *            - the update level number to set
	 */
	public void setUpdateLevelNo(int updateLevelNo)
	{
		this.updateLevelNo = updateLevelNo;
	}

}
