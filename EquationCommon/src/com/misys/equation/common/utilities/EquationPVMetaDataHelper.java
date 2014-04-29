package com.misys.equation.common.utilities;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import com.misys.equation.common.access.EquationPVDecodeMetaData;
import com.misys.equation.common.access.EquationPVFieldMetaData;

/**
 * @author Alex Lim
 */
public class EquationPVMetaDataHelper
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationPVMetaDataHelper.java 9963 2010-11-18 17:48:14Z MACDONP1 $";

	public final static String SEC_ENQ = "G";
	public final static String SEC_UPD = "H";
	public final static String SEC_TRAN = "T";

	public final static String TYPE_ALPHA = "A";
	public final static String TYPE_DDMMYY = "E"; // Reformat cyymmdd to DDMMYY
	public final static String TYPE_ZONED = "S";
	public final static String TYPE_PACKED = "P";
	public final static String TYPE_TIMESTAMP = "Z";
	public final static String TYPE_HIDALPHA = "V"; // Hidden alpha field
	public final static String TYPE_HIDZONED = "U"; // Hidden numeric field

	public final static int LEN_CCN = 4096;
	public final static int LEN_DSEPMS = 37;

	public final static int LEN_NAME = 10;
	public final static int LEN_NAMED = 50;
	public final static int LEN_TYPE = 1;
	public final static int LEN_LENGTH = 10;
	public final static int LEN_INDEX = 10;
	public final static int LEN_DEC = 10;
	public final static int LEN_USE = 1;
	public final static int LEN_HEADER = 50;
	public final static int LEN_DB = 10;
	public final static int LEN_OUTPUT = 10;
	public final static int LEN_DECODE = 1;
	public final static int LEN_DECODED = 50;
	public final static int LEN_ZPPARMD = 10;
	public final static int LEN_PFILE = 10;
	public final static int LEN_KEY = 10;

	private static final String singleQuote = "'";

	private final String pvName;

	// Field information
	private final List<String> fieldNames;
	private final List<String> fieldDescs;
	private final List<String> fieldTypes;
	private final List<String> fieldUsages;
	private final List<String> fieldLengths;
	private final List<String> fieldIndexs;
	private final List<String> fieldDecimals;
	private final List<String> fieldHeaders;
	private final List<String> fieldDbs;
	private List<Boolean> fieldComposites;

	// Data information from the PV
	private final List<String> pvIvals;

	// Decode information
	private final List<String> dcdNames;
	private final List<String> dcdDescs;
	private final List<String> dcdZpParms;
	private final List<String> dcdPFiles;
	private final Hashtable<String, List<String>> dcdPfKeys;
	private Hashtable<String, List<String>> dcdPvKeys;
	private Hashtable<String, Integer> dcdIndex;
	private Hashtable<String, Integer> dcdLength;

	// Database cross reference to DSCCN
	private Hashtable<String, String> databaseXref;
	// DSCCN length will be set by the last character in the dsccn structure
	private int dsccnLength;

	/**
	 * Construct an PV meta data
	 * 
	 * @param pvName
	 *            - PV program name
	 * @param fieldNames
	 *            - field names
	 * @param fieldDescs
	 *            - field descriptions
	 * @param fieldTypes
	 *            - field types
	 * @param fieldUsages
	 *            - field usage
	 * @param fieldLengths
	 *            - field lengths
	 * @param fieldDecimals
	 *            - number of decimal places
	 * @param decodes
	 *            - decode characters
	 * @param decodeDescs
	 *            - decode description
	 * @param zpParms
	 *            - ZP parameters
	 * @param pFiles
	 *            - primary files
	 */
	public EquationPVMetaDataHelper(String pvName, String fieldNames, String fieldDescs, String fieldTypes, String fieldLengths,
					String fieldIndexs, String fieldUsages, String fieldDecimals, String fieldHeaders, String fieldDbs,
					String decodes, String decodeDescs, String zpParms, String pFiles, String keys, String pvIvals)
	{
		this.pvName = pvName;
		this.pvIvals = Toolbox.loadString(Toolbox.trimr(pvIvals), EquationPVMetaDataHelper.LEN_NAME);
		// field information
		this.fieldNames = Toolbox.loadString(Toolbox.trimr(fieldNames), LEN_NAME);
		this.fieldDescs = Toolbox.loadString(Toolbox.trimr(fieldDescs), LEN_NAMED);
		this.fieldTypes = Toolbox.loadString(Toolbox.trimr(fieldTypes), LEN_TYPE);
		this.fieldUsages = Toolbox.loadString(Toolbox.trimr(fieldUsages), LEN_USE);
		this.fieldLengths = Toolbox.loadString(Toolbox.trimr(fieldLengths), LEN_LENGTH);
		this.fieldIndexs = Toolbox.loadString(Toolbox.trimr(fieldIndexs), LEN_INDEX);
		this.fieldDecimals = Toolbox.loadString(Toolbox.trimr(fieldDecimals), LEN_DEC);
		this.fieldHeaders = Toolbox.loadString(Toolbox.trimr(fieldHeaders), LEN_HEADER);
		this.fieldDbs = Toolbox.loadString(Toolbox.trimr(fieldDbs), LEN_DB);

		// make sure the list of database fields is the same length as the list of field names
		for (int i = this.fieldDbs.size(); i < this.fieldNames.size(); i++)
		{
			this.fieldDbs.add("");
		}

		// setup the datbase field - field name mapping
		setupDatabaseXref();

		// determine whether field is a composite field (made up of sub-fields
		setupCompositeStatus();

		// data information
		// this.pvDvals = Toolbox.loadString(Toolbox.trimr(pvDval), LEN_NAME);
		// this.pvOvals = Toolbox.loadString(Toolbox.trimr(pvOval), LEN_NAME);

		// decode information
		this.dcdNames = Toolbox.loadString(Toolbox.trimr(decodes), LEN_DECODE, LEN_DECODE);
		this.dcdDescs = Toolbox.loadString(Toolbox.trimr(decodeDescs), LEN_DECODED);
		this.dcdZpParms = Toolbox.loadString(Toolbox.trimr(zpParms), LEN_ZPPARMD);
		this.dcdPFiles = Toolbox.loadString(Toolbox.trimr(pFiles), LEN_PFILE);
		this.dcdPfKeys = parseDecodePrimKey(Toolbox.loadStringWithSubstituion(Toolbox.trimr(keys), LEN_KEY));
		setupPVKeys();
		setupDSCCNLength();
	}

	/**
	 * Return the pv name
	 * 
	 * @return the pv name
	 */
	public String getPvName()
	{
		return pvName;
	}

	/**
	 * Return the list of field names
	 * 
	 * @return the list of field names
	 */
	public List<String> getFieldNames()
	{
		return fieldNames;
	}

	/**
	 * Return the list of field names description
	 * 
	 * @return the list of field description
	 */
	public List<String> getFieldDescs()
	{
		return fieldDescs;
	}

	/**
	 * Return the list of decodes
	 * 
	 * @return the list of decodes
	 */
	public List<String> getDcdNames()
	{
		return dcdNames;
	}

	/**
	 * Return the DSCCN length - position of last character in DSCCN structure
	 * 
	 * @return the DSCCN length
	 */
	public int getDSCCNLength()
	{
		return dsccnLength;
	}
	/**
	 * Return the field information using field name
	 * 
	 * @param fieldName
	 *            - field name
	 * 
	 * @return all the field information
	 */
	public EquationPVFieldMetaData getFieldMetaData(String fieldName)
	{
		// locate the field name in the list of field names
		int index = fieldNames.indexOf(fieldName);

		// not found
		if (index == -1)
		{
			return null;
		}

		// retrieve information
		return (getFieldMetaData(index));
	}

	/**
	 * Return the DSCCN field name related to the DB field
	 * 
	 * @return the DSCCN field name related to the DB field
	 */
	public String getDSCCNName(String dbName)
	{
		return databaseXref.get(dbName);
	}
	/**
	 * Return the field information using index
	 * 
	 * @param index
	 *            - index to the array
	 * 
	 * @return all the field information
	 */
	public EquationPVFieldMetaData getFieldMetaData(int index)
	{
		// retrieve the field name
		Object obj = Toolbox.getElement(fieldNames, index);

		// not found
		if (obj == null)
		{
			return null;
		}

		// field information
		String fieldName = ((String) obj).trim();
		String fieldDesc = Toolbox.getStringElement(fieldDescs, index).trim();
		String fieldType = Toolbox.getStringElement(fieldTypes, index).trim();
		String fieldUsage = Toolbox.getStringElement(fieldUsages, index).trim();
		int fieldLength = Toolbox.getIntElement(fieldLengths, index, 0);
		int fieldIndex = Toolbox.getIntElement(fieldIndexs, index, 0) - 1; // reduce by one as index start at 0
		int fieldDecimal = Toolbox.getIntElement(fieldDecimals, index, 0);
		String fieldHeader = Toolbox.getStringElement(fieldHeaders, index);
		String fieldDb = Toolbox.getStringElement(fieldDbs, index).trim();
		Boolean composite = fieldComposites.get(index);

		// for packed fields, the length is in bytes, calculate actual number of digits
		if (fieldType.equals(EquationPVMetaDataHelper.TYPE_PACKED))
		{
			fieldLength = (fieldLength * 2) - 1;
		}

		// create the field meta data
		EquationPVFieldMetaData fieldMetaData = new EquationPVFieldMetaData(pvName, fieldName, fieldDesc, fieldType, fieldUsage,
						fieldLength, fieldIndex, fieldDecimal, fieldHeader, fieldDb, composite);

		return fieldMetaData;
	}

	/**
	 * Return the decode information using decode
	 * 
	 * @param index
	 *            - index to the array
	 * 
	 * @return all the field information
	 */
	public EquationPVDecodeMetaData getDecodeMetaData(int index)
	{
		// retrieve the decode
		Object obj = Toolbox.getElement(dcdNames, index);

		// not found
		if (obj == null)
		{
			return null;
		}

		// decode information
		String decode = (String) obj;
		String decodeD = Toolbox.getStringElement(dcdDescs, index).trim();
		String zp = Toolbox.getStringElement(dcdZpParms, index).trim();
		String pfile = Toolbox.getStringElement(dcdPFiles, index).trim();
		List<String> keyFields = dcdPfKeys.get(decode);
		List<String> pvFields = dcdPvKeys.get(decode);
		Integer startIndex = dcdIndex.get(decode);
		Integer length = dcdLength.get(decode);

		// default these decode information from the default value
		if (zp.equals(""))
		{
			zp = Toolbox.getStringElement(dcdZpParms, 0).trim();
		}
		if (pfile.equals(""))
		{
			pfile = Toolbox.getStringElement(dcdPFiles, 0).trim();
		}
		if (keyFields == null)
		{
			keyFields = dcdPfKeys.get("");
			pvFields = dcdPvKeys.get("");
			startIndex = dcdIndex.get("");
			length = dcdLength.get("");
		}

		// not set?
		if (startIndex == null)
		{
			startIndex = 0;
		}
		if (length == null)
		{
			length = 0;
		}
		if (keyFields == null)
		{
			keyFields = new ArrayList<String>();
			pvFields = new ArrayList<String>();
		}

		// create the decode meta data
		EquationPVDecodeMetaData decodeMetaData = new EquationPVDecodeMetaData(pvName, decode, decodeD, zp, pfile, keyFields,
						pvFields, startIndex, length);
		setDecodeDSCCNInputFields(decodeMetaData);
		setKeyFields(decodeMetaData);
		return decodeMetaData;
	}

	/**
	 * Return a hashtable of the string
	 * 
	 * @parm keys - array of string delimeted by *NEXT <decode char>
	 * 
	 * @return a hashtable
	 */
	private Hashtable<String, List<String>> parseDecodePrimKey(List<String> keys)
	{
		Hashtable<String, List<String>> hashtable = new Hashtable<String, List<String>>();

		// loop through the array, the array is delimited by *NEXT <decode char>
		String decode = "";
		List<String> fieldKeys = new ArrayList<String>();
		String fieldKey;

		for (int i = 0; i < keys.size(); i++)
		{
			// get the key field
			fieldKey = keys.get(i).trim();

			// delimeter?
			if (fieldKey.length() > 5 && fieldKey.substring(0, 5).equals("*NEXT"))
			{
				hashtable.put(decode, fieldKeys);
				decode = fieldKey.substring(6, 7);
				fieldKeys = new ArrayList<String>();
			}
			else
			{
				// Old PV Keys had blank fillers. These are no longer needed.
				// The keys must now only contain DB fields.
				if (fieldDbs.contains(fieldKey))
				{
					fieldKeys.add(fieldKey);
				}

				// Validate module which is identified if PF is blank
				else if (dcdPFiles.size() == 0)
				{
					fieldKeys.add(fieldKey);

				}
			}
		}

		// add this
		if (fieldKeys.size() > 0)
		{
			hashtable.put(decode, fieldKeys);
		}

		return hashtable;
	}

	/**
	 * Setup the database field cross reference to the DSCCN field
	 */
	private void setupDatabaseXref()
	{
		databaseXref = new Hashtable<String, String>();

		// loop through the array, the array is delimited by *NEXT <decode char>
		for (int i = 0; i < this.fieldDbs.size(); i++)
		{
			String dbField = fieldDbs.get(i).trim();
			if (!dbField.equals(""))
			{
				databaseXref.put(dbField, fieldNames.get(i));
			}
		}
	}

	/**
	 * Setup the composite String value
	 * 
	 */
	private void setupCompositeStatus()
	{
		this.fieldComposites = new ArrayList<Boolean>();

		// Read all the fields
		for (int i = 0; i < fieldIndexs.size(); i++)
		{
			Boolean compositeFlag = Boolean.FALSE;

			// field is defined, then automatically not composite, otherwise check other fields if there is at least one field whose
			// start/length position is within this field
			if (fieldDbs.get(i).equals(""))
			{
				// Initialise
				int primaryStartPosition = new Integer(fieldIndexs.get(i));
				int primaryEndPosition = primaryStartPosition + new Integer(fieldLengths.get(i));

				// Read all the secondary fields
				for (int j = 0; j < fieldIndexs.size(); j++)
				{
					if (!fieldNames.get(i).equals(fieldNames.get(j)))
					{
						int secondaryStartPosition = new Integer(fieldIndexs.get(j));
						int secondaryEndPosition = secondaryStartPosition + new Integer(fieldLengths.get(j));

						if (primaryStartPosition <= secondaryStartPosition && primaryEndPosition >= secondaryEndPosition)
						{
							compositeFlag = Boolean.TRUE;
							break;
						}
					}
				}
			}

			// add composite flag to the list
			this.fieldComposites.add(compositeFlag);
		}
	}

	/**
	 * Setup the corresponding PV fields for the DB fields
	 * 
	 */
	private void setupPVKeys()
	{
		dcdPvKeys = new Hashtable<String, List<String>>();
		dcdIndex = new Hashtable<String, Integer>();
		dcdLength = new Hashtable<String, Integer>();

		// loop through all the decodes
		Enumeration<String> iter = dcdPfKeys.keys();
		while (iter.hasMoreElements())
		{
			String decode = iter.nextElement();
			List<String> keyFields = dcdPfKeys.get(decode);
			List<String> pvFields = new ArrayList<String>();
			int index = Integer.MAX_VALUE;
			int length = 0;
			int fieldEnd = 0;

			// loop through all the key fields and retrieve the equivalent PV fields
			for (int i = 0; i < keyFields.size(); i++)
			{
				String pvField = databaseXref.get(keyFields.get(i));

				// database field (determine starting index and length)
				if (pvField != null)
				{
					EquationPVFieldMetaData fmd = getFieldMetaData(pvField);
					fieldEnd = fmd.getIndex() + fmd.getLength();

					if (fieldEnd > length)
					{
						length = fieldEnd;
					}
					if (index > fmd.getIndex())
					{
						index = fmd.getIndex();
					}

					// add to the list
					pvFields.add(pvField);
				}
				else if (dcdPFiles.size() == 0)
				{
					// is it a PV field?
					EquationPVFieldMetaData fmd = getFieldMetaData(keyFields.get(i));
					if (fmd != null)
					{
						pvField = keyFields.get(i);
						length += fmd.getLength();
						if (index > fmd.getIndex())
						{
							index = fmd.getIndex();
						}
					}
					// constant
					else
					{
						int l = EqDataListHelper.getLength(keyFields.get(i));
						pvField = String.valueOf(l);
						length += l;
					}

					// add to the list
					pvFields.add(pvField);
				}
			}

			// add to the hashtable
			dcdPvKeys.put(decode, pvFields);
			dcdIndex.put(decode, index);
			dcdLength.put(decode, length);
		}
	}

	/**
	 * Setup the DSCCN length by calculating the last field in the structure
	 * 
	 */
	private void setupDSCCNLength()
	{
		// loop through all the fields
		Iterator<String> iter = getFieldNames().iterator();
		int length = 0;
		while (iter.hasNext())
		{
			String fieldName = iter.next();
			EquationPVFieldMetaData fmd = getFieldMetaData(fieldName);
			int fieldEnd = fmd.getIndex() + fmd.getLength();
			if (fieldEnd > length)
			{
				length = fieldEnd;
			}
		}
		dsccnLength = length;
	}
	/**
	 * Setup the DSCCN input fields. These are primarily DB key fields. Constants may also be included.
	 * 
	 */
	private void setDecodeDSCCNInputFields(EquationPVDecodeMetaData decodeMetaData)
	{
		List<String> dsccnPositions = new ArrayList<String>();
		// Initialise DSCCN Positions Array
		for (int i = 0; i < dsccnLength; i++)
		{
			dsccnPositions.add(i, "");
		}

		List<String> keyfields = decodeMetaData.getKeyFields();

		// For each DSCCN position determine what key field will set the data
		// Iterate over all the DSCCN fields
		EquationPVFieldMetaData fmd = null;
		String dbField = null;
		String ival = null;
		for (int i = 0; i < getFieldNames().size(); i++)
		{
			fmd = getFieldMetaData(i);
			dbField = fmd.getDb();
			// Determine if DSCCN field has a related IVAL - this should be rare
			// IVAL will only be used for constants
			if (pvIvals.size() > i)
			{
				ival = pvIvals.get(i);
			}
			else
			{
				ival = null;
			}
			if (ival != null && !ival.equals(""))
			{
				for (int j = 0; j < fmd.getLength(); j++)
				{
					dsccnPositions.set(fmd.getIndex() + j, ival);
				}
			}
			else if (!dbField.equals("") && keyfields.contains(dbField))
			{
				for (int j = 0; j < fmd.getLength(); j++)
				{
					dsccnPositions.set(fmd.getIndex() + j, dbField);
				}
			}
		}

		// Summarise the previous information by checking for change of fields
		List<String> dsccnInputFieldArray = new ArrayList<String>();
		String lastKey = null;
		int lastKeyCount = 0;
		for (int i = 0; i < dsccnPositions.size(); i++)
		{
			if (i == 0)
			{
				lastKey = dsccnPositions.get(i);
			}
			if (!dsccnPositions.get(i).equals(lastKey))
			{
				if (lastKey.equals(""))
				{
					StringBuilder key = new StringBuilder(singleQuote);
					for (int j = 0; j < lastKeyCount; j++)
					{
						key.append(' ');
					}
					key.append(singleQuote);
					dsccnInputFieldArray.add(key.toString());
				}
				else
				{
					lastKey = Toolbox.constantSubstitution(lastKey);
					dsccnInputFieldArray.add(lastKey);
				}
				lastKey = dsccnPositions.get(i);
				lastKeyCount = 1;
			}
			else
			{
				lastKeyCount++;
			}
		}
		if (lastKey != null)
		{
			if (!lastKey.equals(""))
			{
				dsccnInputFieldArray.add(lastKey);
			}
		}
		decodeMetaData.setDSCCNInputFields(dsccnInputFieldArray);
	}
	/**
	 * Setup the DSCCN input fields segregated between Table Columns and Constants.
	 * 
	 */
	private void setKeyFields(EquationPVDecodeMetaData decodeMetaData)
	{
		List<String> keyFieldsAll = decodeMetaData.getKeyFields();
		List<EquationPVFieldMetaData> keyFieldsConstants = new ArrayList<EquationPVFieldMetaData>();
		List<EquationPVFieldMetaData> keyFieldsDBValue = new ArrayList<EquationPVFieldMetaData>();

		// loop through all the key fields and segregate DSCCN set from DB and Constants
		String keyField;
		String dsccnFieldName;
		EquationPVFieldMetaData fmd;

		for (int i = 0; i < keyFieldsAll.size(); i++)
		{
			keyField = keyFieldsAll.get(i).trim();

			// determine if the key field is a database field
			dsccnFieldName = getDSCCNName(keyField);
			if (dsccnFieldName != null)
			{
				// get the field meta-data
				fmd = getFieldMetaData(dsccnFieldName);
				if (!fmd.getType().equals(EquationPVMetaDataHelper.TYPE_HIDALPHA)
								&& !fmd.getType().equals(EquationPVMetaDataHelper.TYPE_HIDZONED))
				{
					keyFieldsDBValue.add(fmd);
				}
				else
				{
					keyFieldsConstants.add(fmd);
				}
			}
		}
		decodeMetaData.setDbKeyFields(keyFieldsDBValue);
		decodeMetaData.setConstantsKeyFields(keyFieldsConstants);
	}
}