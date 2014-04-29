package com.misys.equation.common.globalprocessing.audit;

import com.misys.equation.common.utilities.Toolbox;

/**
 * Holds result of SAPJ10R call
 */
public class SAPJ10RDS
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SAPJ10RDS.java 9141 2010-09-14 11:33:32Z berzosa $";

	private String internalApi; // internal api e.g. RRT
	private String apiFile;// api file e.g. GZK491
	private byte[] apiData; // DSAIM - data structure for api details
	private String excludedFields; // DSFLD - fields to be excluded
	private String mode; // default to A
	private String fromUnit; // FUNT (default to 3 blanks)
	private String keyIn; // KEYIN (default to 20 blanks)
	private boolean recordFound; // DBREC
	private boolean valid; // (default to false)
	private String errorMessage; // DSPEPMS
	private int dsaimLength;

	public SAPJ10RDS(String internalApi, String apiFile, byte[] apiData, String excludedFields, String mode, String fromUnit,
					String keyIn, boolean recordFound, boolean valid, String errorMessage)
	{

		this.internalApi = internalApi;
		this.apiFile = apiFile;
		this.apiData = apiData;
		this.excludedFields = excludedFields;
		this.mode = mode;
		this.fromUnit = fromUnit;
		this.keyIn = keyIn;
		this.recordFound = recordFound;
		this.valid = valid;
		this.errorMessage = errorMessage;
	}

	public SAPJ10RDS()
	{
		this.internalApi = null;
		this.apiFile = null;
		this.apiData = null;
		this.excludedFields = null;
		this.mode = "A";
		this.fromUnit = Toolbox.pad("", 3); // FUNT (default to 3 blanks)
		this.keyIn = Toolbox.pad("", 20); // default to 20 blanks
		this.recordFound = false;
		this.valid = false; // default to false;
		this.errorMessage = null;

	}

	public String getInternalApi()
	{
		return internalApi;
	}
	public void setInternalApi(String internalApi)
	{
		this.internalApi = internalApi;
	}
	public String getApiFile()
	{
		return apiFile;
	}
	public void setApiFile(String apiFile)
	{
		this.apiFile = apiFile;
	}
	public byte[] getApiData()
	{
		return apiData;
	}
	public void setApiData(byte[] apiData)
	{
		this.apiData = apiData;
	}
	public String getExcludedFields()
	{
		return excludedFields;
	}
	public void setExcludedFields(String excludedFields)
	{
		this.excludedFields = excludedFields;
	}
	public String getMode()
	{
		return mode;
	}
	public void setMode(String mode)
	{
		this.mode = mode;
	}
	public String getFromUnit()
	{
		return fromUnit;
	}
	public void setFromUnit(String fromUnit)
	{
		this.fromUnit = fromUnit;
	}
	public String getKeyIn()
	{
		return keyIn;
	}
	public void setKeyIn(String keyIn)
	{
		this.keyIn = keyIn;
	}
	public boolean isRecordFound()
	{
		return recordFound;
	}
	public void setRecordFound(boolean recordFound)
	{
		this.recordFound = recordFound;
	}
	public boolean isValid()
	{
		return valid;
	}
	public void setValid(boolean valid)
	{
		this.valid = valid;
	}
	public String getErrorMessage()
	{
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString()
	{
		return "SAPJ10RDS " //
						+ ("[errorMessage=" + errorMessage) //
						+ (", image=" + (apiData != null ? Toolbox.convertAS400TextIntoCCSID(apiData, apiData.length, 37).trim()
										: "null")) //
						+ (", mode=" + mode) //
						+ (", recordFound=" + recordFound) //
						+ (", valid=" + valid + "]");
	}

	public int getDsaimLength()
	{
		return dsaimLength;
	}

	public void setDsaimLength(int dsaimLength)
	{
		this.dsaimLength = dsaimLength;
	}

}
