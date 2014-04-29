package com.misys.equation.common.dao.beans;

import com.misys.equation.common.utilities.Toolbox;

/**
 * GAXRecord data-model.
 * 
 * @author deroset
 * 
 */
public class GAXRecordDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GAXRecordDataModel.java 14832 2012-11-05 19:03:33Z williae1 $";

	private static final long serialVersionUID = 1L;

	private final static String RECORD_NAME = "GAXPF";

	private String code; // GAXCOD
	private String key; // GAXKEY
	private String timestamp; // GAXTIM
	private String layout; // GAXXML

	/** Code value denoting a Service record */
	public static final String SERVICE_CODE = "S";
	/** Code value denoting a Layout record */
	public static final String DISPLAYGROUP_CODE = "G";
	/** Code value denoting a Layout record */
	public static final String LAYOUT_CODE = "L";
	/** Code value denoting a PV record */
	public static final String PV_CODE = "P";

	/** Code value denoting a Link service record */
	public static final String LINKAGE_CODE = "C";

	/**
	 * Construct an empty file
	 */
	public GAXRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	/**
	 * Construct a new record
	 */
	public GAXRecordDataModel(String code, String key)
	{
		super();
		setEqFileName(RECORD_NAME);
		setCode(code);
		setKey(key);
	}

	// ---getters and setters----//

	/**
	 * Return the record type
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Set the record type
	 * 
	 * @param code
	 *            - the record type
	 */
	public void setCode(String code)
	{
		this.code = Toolbox.trimr(code);
	}

	/**
	 * Return the option Id
	 * 
	 * @return the option Id
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * Set the option Id
	 * 
	 * @param key
	 *            - the option Id
	 */
	public void setKey(String key)
	{
		this.key = Toolbox.trimr(key);
	}

	/**
	 * Return the timestamp
	 * 
	 * @return the timestamp
	 */
	public String getTimestamp()
	{
		return timestamp;
	}

	/**
	 * Set the timestamp
	 * 
	 * @param timestamp
	 *            - the timestamp
	 */
	public void setTimestamp(String timestamp)
	{
		this.timestamp = Toolbox.trimr(timestamp);
	}

	/**
	 * Return the XML details
	 * 
	 * @return the XML details
	 */
	public String getLayout()
	{
		return layout;
	}

	/**
	 * Set the XML details
	 * 
	 * @param layout
	 *            - the XML details
	 */
	public void setLayout(String layout)
	{
		this.layout = Toolbox.trimr(layout);
	}
}