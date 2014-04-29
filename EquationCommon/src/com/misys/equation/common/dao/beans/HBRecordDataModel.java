package com.misys.equation.common.dao.beans;

import com.misys.equation.common.utilities.Toolbox;

/**
 * [ACE]Record data-model.
 * 
 * @author deroset
 * 
 */
public class HBRecordDataModel extends AbsRecord
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HBRecordDataModel.java 7886 2010-06-21 16:29:02Z lima12 $";
	private static final long serialVersionUID = 1L;
	private final static String RECORD_NAME = "HBPF";

	private String languageCode; // HBLNM
	private String filePrefix; // HBCFR
	private String fileKey; // HBCFL
	private String codeDescription; // HBRNM
	private int dateLastMaintained; // HBDLM
	private byte[] byteCodeDescription; // code description in bytes

	/**
	 * Default constructor
	 */
	public HBRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);

	}

	/**
	 * Construct a new HB record
	 * 
	 * @param languageCode
	 *            - the language code
	 * @param filePrefix
	 *            - the file prefix
	 * @param fileKey
	 *            - the file key
	 */
	public HBRecordDataModel(String languageCode, String filePrefix, String fileKey)
	{
		super();
		setEqFileName(RECORD_NAME);
		this.languageCode = languageCode;
		this.filePrefix = filePrefix;
		this.fileKey = fileKey;
	}

	// ---getters and setters----//

	/**
	 * Return the language code
	 */
	public String getLanguageCode()
	{
		return languageCode;
	}

	/**
	 * Set the language code
	 * 
	 * @param languageCode
	 *            - the language code
	 */
	public void setLanguageCode(String languageCode)
	{
		this.languageCode = languageCode;
	}

	/**
	 * Return the file prefix
	 * 
	 * @return the file prefix
	 */
	public String getFilePrefix()
	{
		return filePrefix;
	}

	/**
	 * Set the file prefix
	 * 
	 * @param filePrefix
	 *            - the file prefix
	 */
	public void setFilePrefix(String filePrefix)
	{
		this.filePrefix = filePrefix;
	}

	/**
	 * Return the file key
	 * 
	 * @return the file key
	 */
	public String getFileKey()
	{
		return fileKey;
	}

	/**
	 * Set the file key
	 * 
	 * @param fileKey
	 *            - the file key
	 */
	public void setFileKey(String fileKey)
	{
		this.fileKey = fileKey;
	}

	/**
	 * Return the code description
	 * 
	 * @return the code description
	 */
	public String getCodeDescription()
	{
		return codeDescription;
	}

	/**
	 * Set the code description
	 * 
	 * @param codeDescription
	 *            - the code description
	 */
	public void setCodeDescription(String codeDescription)
	{
		this.codeDescription = codeDescription;
	}

	/**
	 * Return the date last maintained
	 * 
	 * @return the date last maintained
	 */
	public int getDateLastMaintained()
	{
		return dateLastMaintained;
	}

	/**
	 * Set the date last maintained
	 * 
	 * @param dateLastMaintained
	 *            - the date last maintained
	 */
	public void setDateLastMaintained(int dateLastMaintained)
	{
		this.dateLastMaintained = dateLastMaintained;
	}

	/**
	 * Return the code description in bytes
	 * 
	 * @return the code description in bytes
	 */
	public byte[] getByteCodeDescription()
	{
		return byteCodeDescription;
	}

	/**
	 * Set the code description in bytes
	 * 
	 * @param byteCodeDescription
	 *            - the code description in bytes
	 */
	public void setByteCodeDescription(byte[] byteCodeDescription)
	{
		this.byteCodeDescription = byteCodeDescription;
	}

	/**
	 * Return the converted code description from the job's CCSID
	 * 
	 * @param ccsid
	 *            - the CCSID
	 */
	public String rtvCodeDescription(int ccsid)
	{
		if (byteCodeDescription != null)
		{
			return Toolbox.convertAS400TextIntoCCSID(byteCodeDescription, byteCodeDescription.length, ccsid);
		}
		else
		{
			return "";
		}
	}

}
