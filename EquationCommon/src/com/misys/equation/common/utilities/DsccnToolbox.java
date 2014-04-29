package com.misys.equation.common.utilities;

import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.BidiStringType;
import com.misys.equation.common.access.EquationPVFieldMetaData;
import com.misys.equation.common.access.EquationPVMetaData;

public class DsccnToolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DsccnToolbox.java 10671 2011-03-24 13:38:24Z lima12 $";

	private static final String BUFFER = Toolbox.pad("", EquationPVMetaDataHelper.LEN_CCN);
	public static final int CCSID_GB = 37;
	public static final String PROMPT = "*";

	/**
	 * Return the default buffer in String
	 * 
	 * @return the default buffer in String
	 */
	public static StringBuffer getBuffer()
	{
		return new StringBuffer(BUFFER);
	}

	/**
	 * Return the default buffer in String
	 * 
	 * @return the default buffer in bytes
	 */
	public static byte[] getBufferBytes()
	{
		return new byte[EquationPVMetaDataHelper.LEN_CCN];
	}

	/**
	 * Populate the buffer at location pvFieldName with the specified value of the fieldName
	 * 
	 * @param buffer
	 *            - the DSCCN buffer
	 * @param pvFieldName
	 *            - the PV field name
	 * @param pv
	 *            - the pv
	 * @param inputValue
	 *            - the value
	 * @param asChar
	 *            - treat field as character
	 */
	public static void setupDSCCNFromField(StringBuffer buffer, String pvFieldName, EquationPVMetaData pv, String inputValue,
					boolean asChar)
	{
		EquationPVFieldMetaData fmd = pv.rtvFieldMetaData(pvFieldName);

		if (!asChar && fmd.isNumericField())
		{
			// inputValue = Toolbox.pad(inputValue, fmd.getLength());
			// inputValue = Toolbox.trimAtFront(inputValue, fmd.getLength());

			byte[] inputValueByte = new byte[fmd.getLength()];
			if (fmd.isPacked())
			{
				double inputValueDouble = Toolbox.parseDouble(inputValue, 0);
				inputValueByte = Toolbox.convertNumberIntoAS400PackedBytes(inputValueDouble, fmd.getLength(), fmd.getDecimal());
				inputValue = Toolbox.convertAS400TextIntoCCSID(inputValueByte, fmd.getPackedLength(), CCSID_GB);
				buffer.replace(fmd.getIndex(), fmd.getIndex() + fmd.getPackedLength(), inputValue);
			}
			else if (fmd.isZoned())
			{
				double inputValueDouble = Toolbox.parseDouble(inputValue, 0);
				inputValueByte = Toolbox.convertNumberIntoAS400ZonedBytes(inputValueDouble, fmd.getLength(), fmd.getDecimal());
				inputValue = Toolbox.convertAS400TextIntoCCSID(inputValueByte, fmd.getLength(), CCSID_GB);
				buffer.replace(fmd.getIndex(), fmd.getIndex() + fmd.getLength(), inputValue);
			}
			else
			{
				inputValue = Toolbox.convertAS400TextIntoCCSID(inputValueByte, fmd.getLength(), CCSID_GB);
				buffer.replace(fmd.getIndex(), fmd.getIndex() + fmd.getLength(), inputValue);
			}
		}
		else
		{
			inputValue = Toolbox.pad(inputValue, fmd.getLength());
			inputValue = Toolbox.trim(inputValue, fmd.getLength());
			buffer.replace(fmd.getIndex(), fmd.getIndex() + fmd.getLength(), inputValue);
		}

	}

	/**
	 * Populate the buffer at location pvFieldName with the specified value of the fieldName
	 * 
	 * @param buffer
	 *            - the DSCCN buffer
	 * @param pvFieldName
	 *            - the PV field name
	 * @param pv
	 *            - the pv
	 * @param inputValue
	 *            - the value
	 */
	public static void setupDSCCNFromField(byte[] buffer, String pvFieldName, EquationPVMetaData pv, String inputValue, int ccsid)
	{
		EquationPVFieldMetaData fmd = pv.rtvFieldMetaData(pvFieldName);

		byte[] inputValueByte = new byte[fmd.getLength()];
		if (fmd.isPacked())
		{
			double inputValueDouble = Toolbox.parseDouble(inputValue, 0);
			inputValueByte = Toolbox.convertNumberIntoAS400PackedBytes(inputValueDouble, fmd.getLength(), fmd.getDecimal());
			// copy to the buffer
			System.arraycopy(inputValueByte, 0, buffer, fmd.getIndex(), fmd.getPackedLength());
		}
		else if (fmd.isZoned())
		{
			double inputValueDouble = Toolbox.parseDouble(inputValue, 0);
			inputValueByte = Toolbox.convertNumberIntoAS400ZonedBytes(inputValueDouble, fmd.getLength(), fmd.getDecimal());
			// copy to the buffer
			System.arraycopy(inputValueByte, 0, buffer, fmd.getIndex(), fmd.getLength());
		}
		else
		{
			inputValueByte = Toolbox.convertTextIntoAS400BytesCCSID(inputValue, fmd.getLength(), ccsid, BidiStringType.DEFAULT);
			// copy to the buffer
			System.arraycopy(inputValueByte, 0, buffer, fmd.getIndex(), fmd.getLength());
		}
	}

	/**
	 * Setup the DSCCN given the bytes and the CCSID of the bytes
	 * 
	 * @param byteData
	 *            - the data in bytes
	 * @param pvMetaData
	 *            - the PV meta data
	 * @param totalLength
	 *            - the relevant length in byteData starting from the offset
	 * @param offset
	 *            - the starting data within the byteData
	 * @param ccsid
	 *            - the CCSID of the data
	 * @param forDisplay
	 *            - determine if the result is meant for display. For RTL, conversion is needed in order to properly display Arabic
	 *            text retrieve from iSeries
	 * 
	 * @return the string equivalent
	 */
	public static String setupDSCCNFromBytes(byte[] byteData, EquationPVMetaData pvMetaData, int totalLength, int offset,
					int ccsid, boolean forDisplay)
	{
		StringBuffer buffer = getBuffer();
		int reallyLastIndex = 0;

		for (EquationPVFieldMetaData fmd : pvMetaData.getFieldMetaData())
		{
			// determine last index to copy
			int length = fmd.getLength();
			int lastIndex = fmd.getIndex() + length;
			if (totalLength < lastIndex)
			{
				length = totalLength - fmd.getIndex();
			}

			// determine the last index copied
			if (reallyLastIndex < lastIndex)
			{
				reallyLastIndex = lastIndex;
			}

			// no more data
			if (length <= 0)
			{
				break;
			}

			// copy relevant bytes
			byte[] pvByteData = new byte[length];
			System.arraycopy(byteData, offset + fmd.getIndex(), pvByteData, 0, length);

			// convert
			AS400Text text = new AS400Text(pvByteData.length, ccsid);
			String objText = (String) text.toObject(pvByteData);

			// do we need need for display?
			if (forDisplay && EqDataType.isAlpha(fmd.getType()))
			{
				objText = Toolbox.convertTextRTLForDisplay(objText, objText.length(), ccsid, 0);
			}

			// append to the buffer
			setupDSCCNFromField(buffer, fmd.getId(), pvMetaData, objText, false);
		}

		// are there any thing left in the data?
		if (reallyLastIndex < totalLength)
		{
			int len = totalLength - reallyLastIndex;
			byte[] pvByteData = new byte[len];
			System.arraycopy(byteData, offset + reallyLastIndex, pvByteData, 0, len);
			AS400Text text = new AS400Text(len, ccsid);
			String objText = (String) text.toObject(pvByteData);

			// replace the buffer
			buffer.replace(reallyLastIndex, reallyLastIndex + len, objText);
		}

		return Toolbox.trimr(buffer.toString());
	}

	/**
	 * Setup the data output (DSEPMS + DATA) based on the bytes
	 * 
	 * @param byteData
	 *            - the data in bytes
	 * @param pvMetaData
	 *            - the Equation PV Meta data
	 * @param ccsid
	 *            - the CCSID of the data
	 * @param forDisplay
	 *            - determine if the result is meant for display. For RTL, conversion is needed in order to properly display Arabic
	 *            text retrieve from iSeries
	 * 
	 * @return the data output
	 */
	public static String setupErrorDSCCNFromBytes(byte[] byteData, EquationPVMetaData pvMetaData, int ccsid, boolean forDisplay)
	{
		// Get the error message
		byte[] errorData = new byte[EquationPVMetaDataHelper.LEN_DSEPMS];
		System.arraycopy(byteData, 0, errorData, 0, EquationPVMetaDataHelper.LEN_DSEPMS);
		AS400Text text = new AS400Text(EquationPVMetaDataHelper.LEN_DSEPMS, ccsid);
		String errorText = (String) text.toObject(errorData);

		// Get the data
		String dataText = DsccnToolbox.setupDSCCNFromBytes(byteData, pvMetaData, byteData.length
						- EquationPVMetaDataHelper.LEN_DSEPMS, EquationPVMetaDataHelper.LEN_DSEPMS, ccsid, forDisplay);

		return errorText + dataText;
	}

	/**
	 * Setup the DSCCN given the value and positions
	 * 
	 * @param buffer
	 *            - the buffer
	 * @param value
	 *            - the value to be put into the buffer
	 * @param startIndex
	 *            - start index in the buffer
	 * @param length
	 *            - maximum length
	 * @param right
	 *            - right justified?
	 */
	public static void setupDSCCN(StringBuffer buffer, String value, int startIndex, int length, boolean right)
	{
		// right justified?
		String s = value;
		if (right && s.length() < length)
		{
			s = Toolbox.shiftRight(value, ' ', length);
		}

		// ensure that the value has the correct length
		int l = s.length();
		if (l > length)
		{
			l = length;
		}

		// copy to the buffer
		buffer.replace(startIndex - 1, startIndex - 1 + l, s.substring(0, l));
	}

	/**
	 * Retrieve the substring specified by the start and end index
	 * 
	 * @param dataOutput
	 *            - the output string
	 * @param startIndex
	 *            - the start index
	 * @param endIndex
	 *            - the end index
	 * 
	 * @return the string specified by the start/end index (or blank if invalid)
	 */
	public static String getData(String dataOutput, int startIndex, int endIndex)
	{
		// start index is greater than the length
		if (startIndex > dataOutput.length())
		{
			return "";
		}

		// end index is greater than the length
		if (endIndex > dataOutput.length())
		{
			endIndex = dataOutput.length();
		}

		return dataOutput.substring(startIndex, endIndex);
	}

}
