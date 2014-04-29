package com.misys.equation.common.access;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.misys.equation.common.datastructure.EqDS_DSJOBE;
import com.misys.equation.common.utilities.EqDataToolbox;

/**
 * @author Alex Lim
 */
@SuppressWarnings("serial")
public class EquationPVDecodeMetaData implements Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationPVDecodeMetaData.java 11001 2011-05-19 14:03:00Z MACDONP1 $";

	public final static String DEFAULT_DECODE = " ";
	private String decode;
	private String zpParam;
	private String pFile;
	private String description;
	private String sqlFrom = "";
	private String sqlWhere = "";
	private String sqlSelect = "";

	// list of key field (as specified in the meta data)
	private List<String> keyFields;

	// list of key field (as specified in the meta data)
	private List<String> dsccnInputFields;

	// list of key field that are in the database table
	private List<EquationPVFieldMetaData> dbKeyFields;

	// list of key field that are constants
	private List<EquationPVFieldMetaData> constantsKeyFields;

	// list of equivalent key field mapped to the PV fields. For non-PV fields, the length is specified
	private List<String> pvFields;

	// start index in the DSCCN (excluding any leading constants)
	private int index;

	// total length of the keys (identified by the KEYS_ meta-data structure)
	private int length;

	// cache of ZP parameter description
	private final Map<String, String> zpParamDescs = new Hashtable<String, String>();

	/**
	 * Construct an empty DecodeMetaData
	 * 
	 */
	public EquationPVDecodeMetaData()
	{
		setData("", "", "", "", new ArrayList<String>(), new ArrayList<String>(), 0, 0);
	}

	/**
	 * Construct a Decode Meta Data
	 * 
	 * @param pvName
	 *            - PV name
	 * @param decode
	 *            - decode character
	 * @param description
	 *            - decode description
	 * @param zpParam
	 *            - equivalent ZP parameter
	 * @param pFile
	 *            - equivalent primary file
	 */
	public EquationPVDecodeMetaData(String pvName, String decode, String description, String zpParam, String pFile,
					List<String> keyFields, List<String> pvFields, int index, int length)
	{
		setData(decode, description, zpParam, pFile, keyFields, pvFields, index, length);
	}

	/**
	 * Set the meta data
	 * 
	 * @param decode
	 *            - decode character
	 * @param description
	 *            - decode description
	 * @param zpParam
	 *            - equivalent ZP parameter
	 * @param pFile
	 *            - equivalent primary file
	 */
	private void setData(String decode, String description, String zpParam, String pFile, List<String> keyFields,
					List<String> pvFields, int index, int length)
	{
		this.zpParam = zpParam;
		this.pFile = pFile;
		this.description = description;
		this.decode = decode;
		this.keyFields = keyFields;
		this.pvFields = pvFields;
		this.index = index;
		this.length = length;
	}

	/**
	 * Return the ZP parameter
	 * 
	 * @return ZP parameter
	 */
	public String getZpParam()
	{
		return zpParam;
	}

	/**
	 * Set the ZP parameter
	 * 
	 * @param zpParam
	 *            - ZP Parameter
	 */
	public void setZpParam(String zpParam)
	{
		this.zpParam = zpParam;
	}

	/**
	 * Return the primary file
	 * 
	 * @return primary file
	 */
	public String getpFile()
	{
		return pFile;
	}

	/**
	 * Set the primary file
	 * 
	 * @param pFile
	 *            - primary file
	 */
	public void setpFile(String pFile)
	{
		this.pFile = pFile;
	}

	/**
	 * Return the decode
	 * 
	 * @return decode
	 */
	public String getDecode()
	{
		return decode;
	}

	/**
	 * Set the decode
	 * 
	 * @param decode
	 *            - decode character
	 */
	public void setDecode(String decode)
	{
		this.decode = decode;
	}

	/**
	 * Return the decode description
	 * 
	 * @return decode description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Set the decode description
	 * 
	 * @param description
	 *            - decode description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Return the list of key field names
	 * 
	 * @return the list of key field names
	 */
	public List<String> getKeyFields()
	{
		return keyFields;
	}

	/**
	 * Set the list of key field names
	 * 
	 * @param keyFields
	 *            - the list of key field names
	 */
	public void setKeyFields(List<String> keyFields)
	{
		this.keyFields = keyFields;
	}

	/**
	 * Return the list of DSCCN input field names
	 * 
	 * @return the list of DSCCN input field names
	 */
	public List<String> getDSCCNInputFields()
	{
		return dsccnInputFields;
	}

	/**
	 * Set the list of DSCCN input field names
	 * 
	 * @param dsccnInputFields
	 *            - the list of input field names
	 */
	public void setDSCCNInputFields(List<String> dsccnInputFields)
	{
		this.dsccnInputFields = dsccnInputFields;
	}

	/**
	 * Return the list of DSCCN input fields that relate to Table Columns
	 * 
	 * @return the list of DSCCN input fields that relate to Table Columns
	 */
	public List<EquationPVFieldMetaData> getDbKeyFields()
	{
		return dbKeyFields;
	}

	/**
	 * Set the list of DSCCN input fields that relate to Table Columns
	 * 
	 * @param dbKeyFields
	 *            - the list of input fields that relate to Table Columns
	 */
	public void setDbKeyFields(List<EquationPVFieldMetaData> dbKeyFields)
	{
		this.dbKeyFields = dbKeyFields;
	}
	/**
	 * Return the list of DSCCN input fields that relate to Constants
	 * 
	 * @return the list of DSCCN input fields that relate to Constants
	 */
	public List<EquationPVFieldMetaData> getConstantsKeyFields()
	{
		return constantsKeyFields;
	}

	/**
	 * Set the list of DSCCN input fields that relate to Constants
	 * 
	 * @param constantsKeyFields
	 *            - the list of input fields that relate to Constants
	 */
	public void setConstantsKeyFields(List<EquationPVFieldMetaData> constantsKeyFields)
	{
		this.constantsKeyFields = constantsKeyFields;
	}

	/**
	 * Add a key field - this method is required for bean serialisation - do not delete.
	 * 
	 * @param fieldId
	 *            - the ID of the field to add
	 */
	public void addKeyField(String fieldId)
	{
		keyFields.add(fieldId);
	}

	/**
	 * Return the list of equivalent pv key fields
	 * 
	 * @return the list of equivalent pv key fields
	 */
	public List<String> getPvFields()
	{
		return pvFields;
	}

	/**
	 * Set the list of equivalent pv key fields
	 * 
	 * @param pvFields
	 *            - the list of equivalent pv key fields
	 */
	public void setPvFields(List<String> pvFields)
	{
		this.pvFields = pvFields;
	}

	/**
	 * Add a PV field - this method is required for bean serialisation - do not delete.
	 * 
	 * @param fieldId
	 *            - the ID of the field to add
	 */
	public void addPvField(String fieldId)
	{
		pvFields.add(fieldId);
	}

	/**
	 * Return the starting index of the key in the DSCCN (excluding leading constant)
	 * 
	 * @return the starting index of the key in the DSCCN (excluding leading constant)
	 */
	public int getIndex()
	{
		return index;
	}

	/**
	 * Set the starting index of the key in the DSCCN (excluding leading constant)
	 * 
	 * @param index
	 *            - the starting index of the key in the DSCCN (excluding leading constant)
	 */
	public void setIndex(int index)
	{
		this.index = index;
	}

	/**
	 * Return the length of the key (excluding leading constant)
	 * 
	 * @return the length of the key (excluding leading constant)
	 */
	public int getLength()
	{
		return length;
	}

	/**
	 * Set the length of the key (excluding leading constant)
	 * 
	 * @param length
	 *            - the length of the key (excluding leading constant)
	 */
	public void setLength(int length)
	{
		this.length = length;
	}

	/**
	 * Return the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		StringBuffer string = new StringBuffer("");

		string.append("Decode  = " + getDecode());
		string.append("\n");
		string.append("Desc = " + getDescription());
		string.append("\n");
		string.append("ZP = " + getZpParam());
		string.append("\n");
		string.append("File = " + getpFile());
		string.append("\n");
		string.append("DB Key = " + getKeyFields());
		string.append("\n");
		string.append("PV Key = " + getPvFields());
		string.append("\n");
		string.append("Key index = " + getIndex());
		string.append("\n");
		string.append("Key length = " + getLength());
		string.append("\n");
		string.append("DSCCN Key = " + getDSCCNInputFields());
		string.append("\n");
		string.append("\n");

		return string.toString();
	}

	/**
	 * Return the ZP description
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * 
	 * @return the ZP parameter description
	 */
	public String rtvZpParam(EquationUser eqUser)
	{
		// language and file code
		String language = eqUser.rtvLanguageId();
		String filecode = "QR";

		// is it already in the cache?
		String desc = zpParamDescs.get(language);
		if (desc == null)
		{
			// retrieve from parameter field
			desc = EqDataToolbox.editParameter(eqUser.getSession(), "", language, filecode, zpParam);

			// not found?
			if (desc.equals(language + filecode + zpParam))
			{
				desc = EqDataToolbox.editParameter(eqUser.getSession(), "", "", filecode, zpParam);

				// still not found
				if (desc.equals("" + filecode + zpParam))
				{
					desc = eqUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.ANF);
				}
			}

			zpParamDescs.put(language, desc);
		}

		return desc;
	}

	public String getSqlFrom()
	{
		return sqlFrom;
	}

	public void setSqlFrom(String sqlFrom)
	{
		this.sqlFrom = sqlFrom;
	}

	public String getSqlWhere()
	{
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere)
	{
		this.sqlWhere = sqlWhere;
	}

	public String getSqlSelect()
	{
		return sqlSelect;
	}

	public void setSqlSelect(String sqlSelect)
	{
		this.sqlSelect = sqlSelect;
	}
}
