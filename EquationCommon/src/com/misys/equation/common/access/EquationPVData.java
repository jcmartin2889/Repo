package com.misys.equation.common.access;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.Iterator;

import com.misys.equation.common.utilities.DsccnToolbox;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.Toolbox;

/**
 * This class provides methods for setting Equation prompt/validate fields before the prompt/validate is executed and for getting
 * fields after the prompt/validate is executed.
 */
public class EquationPVData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationPVData.java 15063 2012-12-18 15:15:25Z williae1 $";

	private final Hashtable<String, String> pvDSData;
	private final EquationPVMetaData pvMetaData;
	private final int ccsid;

	/**
	 * Construct a default Equation PV Data
	 * 
	 * @param pvMetaData
	 *            - the PV meta data
	 * @param ccsid
	 *            - the CCSID of the data
	 * @equation.external
	 * 
	 */
	public EquationPVData(EquationPVMetaData pvMetaData, int ccsid)
	{
		this.pvMetaData = pvMetaData;
		this.pvDSData = pvMetaData.generateHashTable();
		this.ccsid = ccsid;
	}

	/**
	 * Return the PV meta data
	 * 
	 * @return the PV meta data
	 */
	public EquationPVMetaData getPvMetaData()
	{
		return pvMetaData;
	}

	/**
	 * Return the CCSID of the data
	 * 
	 * @return the CCSID of the data
	 */
	public int getCcsid()
	{
		return ccsid;
	}

	/**
	 * Return the data in DSCCN format
	 */
	public String getDataDsccn()
	{
		return parseFieldsIntoDSCCN("N");
	}

	/**
	 * Set the output data in DSCCN format
	 * 
	 * @param dataOutput
	 *            - the output data in DSCCN format
	 */
	public void setDataDsccn(String dataDsccn)
	{
		// set the data structure if defined
		parseDSCCNIntoFields(dataDsccn);
	}

	/**
	 * Set the output data in DSCCN format with cross-reference to the actual AS400 bytes
	 * 
	 * @param dataOutput
	 *            - the output data in DSCCN format (in bytes)
	 */
	public void setDataDsccn(byte[] dataBytes)
	{
		// set the data structure if defined
		parseDSCCNIntoFields(dataBytes);
	}

	/**
	 * Set the field value of a PV field
	 * 
	 * @param fieldName
	 *            - PV field name
	 * @param fieldValue
	 *            - field value
	 * @equation.external
	 */
	public void setFieldValue(String fieldName, String fieldValue)
	{
		pvDSData.put(fieldName, fieldValue);
	}

	/**
	 * Return the field value of a PV field
	 * 
	 * @param fieldName
	 *            - PV field name
	 * 
	 * @return the field value
	 * @equation.external
	 */
	public String getFieldValue(String fieldName)
	{
		String value = pvDSData.get(fieldName);
		if (value == null)
		{
			return "";
		}
		else
		{
			return value;
		}
	}

	/**
	 * Parse the data in DSCCN format into the Equation Data Structure Data
	 * 
	 * @param dsccn
	 *            - the data in DSCCN format
	 */
	public void parseDSCCNIntoFields(String dsccn)
	{
		// check whether the communication array contains only the prompt character '*' and blank
		boolean dsscnContainsPromptAndBlankOnly = containsPromptAndBlankOnly(dsccn);

		for (int i = 0; i < pvMetaData.rtvNumberOfFields(); i++)
		{
			EquationPVFieldMetaData pvFieldMetaData = pvMetaData.rtvFieldMetaData(i);
			String pvField = pvFieldMetaData.getId();
			String pvValue = Toolbox.trimr(pvMetaData.rtvDataFromField(i, dsccn));

			// if DSSCN contains blank and prompt characters only, set the pvValue to a single prompt character to avoid a pvDSData
			// element field from having more than one '*' which happens when there are overlapping fields in the corresponding P/V
			// data structure (e.g. DSR37_B) that in turn causes P/V data list not to be retrieved successfully
			if (dsscnContainsPromptAndBlankOnly == true)
			{
				pvValue = DsccnToolbox.PROMPT;
			}

			setFieldValue(pvField, pvValue);
		}
	}
	/**
	 * Parse the data in DSCCN format into the Equation Data Structure Data
	 * 
	 * @param dsccn
	 *            - the data in DSCCN format
	 */
	public void parseDSCCNIntoFields(byte[] dsccn)
	{
		for (EquationPVFieldMetaData pvFieldMetaData : pvMetaData.getFieldMetaData())
		{
			String pvField = pvFieldMetaData.getId();
			String pvValue = "";

			// retrieve the relevant bytes
			int fieldIndex = pvFieldMetaData.getIndex();
			int fieldLength = pvFieldMetaData.getLength();
			if (pvFieldMetaData.isPacked())
			{
				fieldLength = pvFieldMetaData.getPackedLength();
			}

			byte[] dataBytes = new byte[fieldLength];

			pvValue = null;
			if (dsccn.length >= fieldIndex + fieldLength)
			{
				System.arraycopy(dsccn, pvFieldMetaData.getIndex(), dataBytes, 0, fieldLength);
			}
			else if (dsccn.length > fieldIndex)
			{
				System.arraycopy(dsccn, pvFieldMetaData.getIndex(), dataBytes, 0, dsccn.length - fieldIndex);
				for (int j = dsccn.length - fieldIndex; j < dataBytes.length; j++)
				{
					dataBytes[j] = 64; // set everything else to blank
				}
			}
			else
			{
				pvValue = "";
			}

			if (pvValue == null)
			{
				if (pvFieldMetaData.isNumericField())
				{
					BigDecimal bd;
					try
					{
						if (pvFieldMetaData.isPacked())
						{
							bd = Toolbox.convertAS400Packed(dataBytes, pvFieldMetaData.getLength(), pvFieldMetaData.getDecimal());
						}
						else
						{
							bd = Toolbox.convertAS400Zoned(dataBytes, fieldLength, pvFieldMetaData.getDecimal());
						}
						pvValue = bd.toPlainString();
					}
					catch (Exception e)
					{
						pvValue = Toolbox.convertAS400TextIntoCCSID(dataBytes, fieldLength, ccsid);
					}
				}
				else
				{
					pvValue = Toolbox.convertAS400TextIntoCCSID(dataBytes, fieldLength, ccsid);
				}
			}

			// set the field
			pvValue = Toolbox.trimr(pvValue);
			setFieldValue(pvField, pvValue);
		}
	}

	/**
	 * Convert the Equation Data Structure Data into DSCCN format
	 * 
	 * @param prompt
	 *            - prompt mode?
	 * 
	 * @return the data in DSCCN format
	 */
	public String parseFieldsIntoDSCCN(String prompt)
	{
		StringBuffer buffer = DsccnToolbox.getBuffer();

		for (int i = 0; i < pvMetaData.rtvNumberOfFields(); i++)
		{
			String pvField = pvMetaData.rtvFieldMetaData(i).getId();
			String pvValue = getFieldValue(pvField);

			// prompt mode, then convert into *
			if (pvValue.equals("") && prompt.equals("Y"))
			{
				pvValue = DsccnToolbox.PROMPT;
			}

			// if the current value in DSCCN is blank or *, overwrite it with the new one
			// this is needed to avoid overwriting details in DSCCN when there are
			// overlapping fields
			String curValue = pvMetaData.rtvDataFromField(i, Toolbox.trimr(buffer.toString()));
			if (curValue.trim().equals("*") || curValue.trim().equals(""))
			{
				boolean asChar = pvValue.equals(DsccnToolbox.PROMPT) || pvValue.equals("");
				DsccnToolbox.setupDSCCNFromField(buffer, pvField, pvMetaData, pvValue, asChar);
			}
		}

		return Toolbox.trimr(buffer.toString());
	}

	/**
	 * Convert the Equation Data Structure Data into DSCCN AS400 byte format
	 * 
	 * @param prompt
	 *            - prompt mode?
	 * 
	 * @return the data in DSCCN format
	 */
	public byte[] parseFieldsIntoDSCCNBytes(String prompt)
	{
		byte[] buffer = DsccnToolbox.getBufferBytes();

		for (EquationPVFieldMetaData fmd : pvMetaData.getFieldMetaData())
		{
			String pvField = fmd.getId();
			String pvValue = getFieldValue(pvField);

			// prompt mode, then convert into *
			if (pvValue.equals("") && prompt.equals("Y"))
			{
				pvValue = "*";
			}

			// if the current value in DSCCN is blank or *, overwrite it with the new one
			// this is needed to avoid overwriting details in DSCCN when there are
			// overlapping fields
			byte[] curValueByte = new byte[fmd.getLength()];
			System.arraycopy(buffer, fmd.getIndex(), curValueByte, 0, fmd.getLength());
			String curValue;
			if (Toolbox.isBlank(curValueByte))
			{
				curValue = "";
			}
			else
			{
				curValue = Toolbox.convertAS400TextIntoCCSID(curValueByte, fmd.getLength(), ccsid).trim();
			}

			if (curValue.equals("*") || curValue.equals(""))
			{
				DsccnToolbox.setupDSCCNFromField(buffer, pvField, pvMetaData, pvValue, ccsid);
			}
		}

		return buffer;
	}

	/**
	 * Return the String representation
	 * 
	 * @param the
	 *            String representation
	 */
	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();

		for (EquationPVFieldMetaData fmd : pvMetaData.getFieldMetaData())
		{
			String fieldName = fmd.getId();
			String fieldValue = pvDSData.get(fieldName);
			buffer.append(fieldName + " = " + fieldValue);
			buffer.append("\n");
		}

		return buffer.toString();
	}

	/**
	 * Return the value of the default OVAL field
	 * 
	 * @return the value of the default OVAL field
	 */
	public String rtvDefaultOVal()
	{
		if (pvMetaData.getPvOvals().size() > 0)
		{
			String value = getFieldValue(pvMetaData.getPvOvals().get(0));
			return value;
		}
		else
		{
			return "";
		}
	}

	/**
	 * Return the value of the default DVAL field
	 * 
	 * @return the value of the default DVAL field
	 */
	public String rtvDefaultDVal()
	{
		// Database value
		if (pvMetaData.getPvDvals().size() > 0)
		{
			String value = getFieldValue(pvMetaData.getPvDvals().get(0));
			return value;
		}
		else
		{
			return null;
		}
	}

	/**
	 * Initialise the Equation PV Data from a Equation Data Structure Data
	 * 
	 * @param eqDsData
	 *            - Equation Data Structure Data
	 * 
	 * @return true if all fields in the eqDsData is also in the Equation PV Data
	 */
	public boolean initialiseFromDS(EquationDataStructureData eqDsData)
	{
		boolean allField = true;
		EquationDataStructure eqDs = eqDsData.getEqDS();
		Iterator<String> iter = pvDSData.keySet().iterator();
		while (iter.hasNext())
		{
			String fieldName = iter.next();

			// field exists in the DS, then set it
			if (eqDs.containsField(fieldName))
			{
				EquationPVFieldMetaData fmd = pvMetaData.rtvFieldMetaData(fieldName);
				String fieldValue = eqDsData.getFieldValue(fieldName);

				// for numeric, right align
				if (fmd.isNumericField())
				{
					// if there is a minus sign, then remove it and add it after the padding
					boolean minusSign = fieldValue.indexOf(EqDataType.MINUS_SIGN) >= 0;
					int offset = 0;
					if (minusSign)
					{
						fieldValue = fieldValue.replaceFirst(EqDataType.MINUS_SIGN, "");
						offset = 1;
					}

					fieldValue = Toolbox.padAtFront(fieldValue, "0", fmd.getLength() - offset);

					if (minusSign)
					{
						fieldValue = EqDataType.MINUS_SIGN + fieldValue;
					}
				}

				// set the data
				pvDSData.put(fieldName, fieldValue);
			}

			// field does not exist, then simply ignore it
			else
			{
				allField = false;
			}
		}

		return allField;
	}

	/**
	 * Convert the Equation Data Structure Data into DSCCN format
	 * 
	 * @param prompt
	 *            - prompt mode?
	 * 
	 * @return the data in DSCCN format
	 */
	public String parseFieldsIntoDSCCNOutput(String prompt)
	{
		StringBuffer buffer = DsccnToolbox.getBuffer();

		for (int i = 0; i < pvMetaData.rtvNumberOfFields(); i++)
		{
			String pvField = pvMetaData.rtvFieldMetaData(i).getId();
			String pvValue = getFieldValue(pvField);

			// prompt mode, then convert into *
			if (pvValue.equals("") && prompt.equals("Y"))
			{
				pvValue = DsccnToolbox.PROMPT;
			}

			// if the current value in DSCCN is blank or *, overwrite it with the new one
			// this is needed to avoid overwriting details in DSCCN when there are
			// overlapping fields
			String curValue = pvMetaData.rtvDataFromField(i, Toolbox.trimr(buffer.toString()));
			if (curValue.equals("*") || curValue.equals(""))
			{
				DsccnToolbox.setupDSCCNFromField(buffer, pvField, pvMetaData, pvValue, true);
			}
		}

		return Toolbox.trimr(buffer.toString());
	}

	/**
	 * Check if the data contains only the prompt character '*' and blanks.
	 * 
	 * @param dsccn
	 *            - the data in DSCCN format
	 * @return <code> true </code> if the data contains only prompt character '*' and blanks, else <code> false </code>
	 */
	private boolean containsPromptAndBlankOnly(String dsccn)
	{
		boolean result = true;
		for (int i = 0; i < dsccn.length(); i++)
		{
			if (dsccn.charAt(i) != '*' && dsccn.charAt(i) != ' ')
			{
				result = false;
				break;
			}
		}
		return result;
	}
}
