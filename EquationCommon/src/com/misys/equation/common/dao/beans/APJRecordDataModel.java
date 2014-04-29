package com.misys.equation.common.dao.beans;

/**
 * APJ Record data-model.
 * 
 * @author deroset
 * 
 */
public class APJRecordDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: APJRecordDataModel.java 10420 2011-02-03 12:22:26Z MACDONP1 $";
	private final static String RECORD_NAME = "APJ10LF";

	private String apiReference;
	private String apiFileName;
	private String equationApiLevel;
	private String apiFieldSequence;
	private String apiFieldName;
	private String apiFieldDescripton;
	private String apiFieldType;
	private String apiFieldStart;
	private String apiFieldEnd;
	private String apiFieldLength;
	private String apiFieldIntegers;
	private String apiFieldFractions;
	private String dbFieldName;
	private String dbFieldDescription;
	private String dbFieldType;
	private String dbFieldStart;
	private String dbFieldEnd;
	private String dbFieldLength;
	private String dbFieldIntegers;
	private String dbFieldFractions;
	private String dbFileName;
	private String retrievalInstance;
	private String controlType;
	private String subControlType;
	private String conversionProgram;
	private String mapParameter;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1273209982588l;

	/**
	 * Default constructor
	 */
	public APJRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public APJRecordDataModel(String apiReference, String apiFileName, String apiFieldSequence)
	{
		this.apiReference = apiReference;
		this.apiFileName = apiFileName;
		this.apiFieldSequence = apiFieldSequence;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getApiReference()
	{
		return this.apiReference;
	}
	public void setApiReference(String parameter)
	{
		this.apiReference = parameter;
	}
	public String getApiFileName()
	{
		return this.apiFileName;
	}
	public void setApiFileName(String parameter)
	{
		this.apiFileName = parameter;
	}
	public String getEquationApiLevel()
	{
		return this.equationApiLevel;
	}
	public void setEquationApiLevel(String parameter)
	{
		this.equationApiLevel = parameter;
	}
	public String getApiFieldSequence()
	{
		return this.apiFieldSequence;
	}
	public void setApiFieldSequence(String parameter)
	{
		this.apiFieldSequence = parameter;
	}
	public String getApiFieldName()
	{
		return this.apiFieldName;
	}
	public void setApiFieldName(String parameter)
	{
		this.apiFieldName = parameter;
	}
	public String getApiFieldDescripton()
	{
		return this.apiFieldDescripton;
	}
	public void setApiFieldDescripton(String parameter)
	{
		this.apiFieldDescripton = parameter;
	}
	public String getApiFieldType()
	{
		return this.apiFieldType;
	}
	public void setApiFieldType(String parameter)
	{
		this.apiFieldType = parameter;
	}
	public String getApiFieldStart()
	{
		return this.apiFieldStart;
	}
	public void setApiFieldStart(String parameter)
	{
		this.apiFieldStart = parameter;
	}
	public String getApiFieldEnd()
	{
		return this.apiFieldEnd;
	}
	public void setApiFieldEnd(String parameter)
	{
		this.apiFieldEnd = parameter;
	}
	public String getApiFieldLength()
	{
		return this.apiFieldLength;
	}
	public void setApiFieldLength(String parameter)
	{
		this.apiFieldLength = parameter;
	}
	public String getApiFieldIntegers()
	{
		return this.apiFieldIntegers;
	}
	public void setApiFieldIntegers(String parameter)
	{
		this.apiFieldIntegers = parameter;
	}
	public String getApiFieldFractions()
	{
		return this.apiFieldFractions;
	}
	public void setApiFieldFractions(String parameter)
	{
		this.apiFieldFractions = parameter;
	}
	public String getDbFieldName()
	{
		return this.dbFieldName;
	}
	public void setDbFieldName(String parameter)
	{
		this.dbFieldName = parameter;
	}
	public String getDbFieldDescription()
	{
		return this.dbFieldDescription;
	}
	public void setDbFieldDescription(String parameter)
	{
		this.dbFieldDescription = parameter;
	}
	public String getDbFieldType()
	{
		return this.dbFieldType;
	}
	public void setDbFieldType(String parameter)
	{
		this.dbFieldType = parameter;
	}
	public String getDbFieldStart()
	{
		return this.dbFieldStart;
	}
	public void setDbFieldStart(String parameter)
	{
		this.dbFieldStart = parameter;
	}
	public String getDbFieldEnd()
	{
		return this.dbFieldEnd;
	}
	public void setDbFieldEnd(String parameter)
	{
		this.dbFieldEnd = parameter;
	}
	public String getDbFieldLength()
	{
		return this.dbFieldLength;
	}
	public void setDbFieldLength(String parameter)
	{
		this.dbFieldLength = parameter;
	}
	public String getDbFieldIntegers()
	{
		return this.dbFieldIntegers;
	}
	public void setDbFieldIntegers(String parameter)
	{
		this.dbFieldIntegers = parameter;
	}
	public String getDbFieldFractions()
	{
		return this.dbFieldFractions;
	}
	public void setDbFieldFractions(String parameter)
	{
		this.dbFieldFractions = parameter;
	}
	public String getDbFileName()
	{
		return this.dbFileName;
	}
	public void setDbFileName(String parameter)
	{
		this.dbFileName = parameter;
	}
	public String getRetrievalInstance()
	{
		return this.retrievalInstance;
	}
	public void setRetrievalInstance(String parameter)
	{
		this.retrievalInstance = parameter;
	}
	public String getControlType()
	{
		return this.controlType;
	}
	public void setControlType(String parameter)
	{
		this.controlType = parameter;
	}
	public String getSubControlType()
	{
		return this.subControlType;
	}
	public void setSubControlType(String parameter)
	{
		this.subControlType = parameter;
	}
	public String getConversionProgram()
	{
		return this.conversionProgram;
	}
	public void setConversionProgram(String parameter)
	{
		this.conversionProgram = parameter;
	}
	public String getMapParameter()
	{
		return this.mapParameter;
	}
	public void setMapParameter(String parameter)
	{
		this.mapParameter = parameter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apiFieldDescripton == null) ? 0 : apiFieldDescripton.hashCode());
		result = prime * result + ((apiFieldEnd == null) ? 0 : apiFieldEnd.hashCode());
		result = prime * result + ((apiFieldFractions == null) ? 0 : apiFieldFractions.hashCode());
		result = prime * result + ((apiFieldIntegers == null) ? 0 : apiFieldIntegers.hashCode());
		result = prime * result + ((apiFieldLength == null) ? 0 : apiFieldLength.hashCode());
		result = prime * result + ((apiFieldName == null) ? 0 : apiFieldName.hashCode());
		result = prime * result + ((apiFieldSequence == null) ? 0 : apiFieldSequence.hashCode());
		result = prime * result + ((apiFieldStart == null) ? 0 : apiFieldStart.hashCode());
		result = prime * result + ((apiFieldType == null) ? 0 : apiFieldType.hashCode());
		result = prime * result + ((apiFileName == null) ? 0 : apiFileName.hashCode());
		result = prime * result + ((apiReference == null) ? 0 : apiReference.hashCode());
		result = prime * result + ((controlType == null) ? 0 : controlType.hashCode());
		result = prime * result + ((conversionProgram == null) ? 0 : conversionProgram.hashCode());
		result = prime * result + ((dbFieldDescription == null) ? 0 : dbFieldDescription.hashCode());
		result = prime * result + ((dbFieldEnd == null) ? 0 : dbFieldEnd.hashCode());
		result = prime * result + ((dbFieldFractions == null) ? 0 : dbFieldFractions.hashCode());
		result = prime * result + ((dbFieldIntegers == null) ? 0 : dbFieldIntegers.hashCode());
		result = prime * result + ((dbFieldLength == null) ? 0 : dbFieldLength.hashCode());
		result = prime * result + ((dbFieldName == null) ? 0 : dbFieldName.hashCode());
		result = prime * result + ((dbFieldStart == null) ? 0 : dbFieldStart.hashCode());
		result = prime * result + ((dbFieldType == null) ? 0 : dbFieldType.hashCode());
		result = prime * result + ((dbFileName == null) ? 0 : dbFileName.hashCode());
		result = prime * result + ((equationApiLevel == null) ? 0 : equationApiLevel.hashCode());
		result = prime * result + ((mapParameter == null) ? 0 : mapParameter.hashCode());
		result = prime * result + ((retrievalInstance == null) ? 0 : retrievalInstance.hashCode());
		result = prime * result + ((subControlType == null) ? 0 : subControlType.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		APJRecordDataModel other = (APJRecordDataModel) obj;
		if (apiFieldDescripton == null)
		{
			if (other.apiFieldDescripton != null)
			{
				return false;
			}
		}
		else if (!apiFieldDescripton.equals(other.apiFieldDescripton))
		{
			return false;
		}
		if (apiFieldEnd == null)
		{
			if (other.apiFieldEnd != null)
			{
				return false;
			}
		}
		else if (!apiFieldEnd.equals(other.apiFieldEnd))
		{
			return false;
		}
		if (apiFieldFractions == null)
		{
			if (other.apiFieldFractions != null)
			{
				return false;
			}
		}
		else if (!apiFieldFractions.equals(other.apiFieldFractions))
		{
			return false;
		}
		if (apiFieldIntegers == null)
		{
			if (other.apiFieldIntegers != null)
			{
				return false;
			}
		}
		else if (!apiFieldIntegers.equals(other.apiFieldIntegers))
		{
			return false;
		}
		if (apiFieldLength == null)
		{
			if (other.apiFieldLength != null)
			{
				return false;
			}
		}
		else if (!apiFieldLength.equals(other.apiFieldLength))
		{
			return false;
		}
		if (apiFieldName == null)
		{
			if (other.apiFieldName != null)
			{
				return false;
			}
		}
		else if (!apiFieldName.equals(other.apiFieldName))
		{
			return false;
		}
		if (apiFieldSequence == null)
		{
			if (other.apiFieldSequence != null)
			{
				return false;
			}
		}
		else if (!apiFieldSequence.equals(other.apiFieldSequence))
		{
			return false;
		}
		if (apiFieldStart == null)
		{
			if (other.apiFieldStart != null)
			{
				return false;
			}
		}
		else if (!apiFieldStart.equals(other.apiFieldStart))
		{
			return false;
		}
		if (apiFieldType == null)
		{
			if (other.apiFieldType != null)
			{
				return false;
			}
		}
		else if (!apiFieldType.equals(other.apiFieldType))
		{
			return false;
		}
		if (apiFileName == null)
		{
			if (other.apiFileName != null)
			{
				return false;
			}
		}
		else if (!apiFileName.equals(other.apiFileName))
		{
			return false;
		}
		if (apiReference == null)
		{
			if (other.apiReference != null)
			{
				return false;
			}
		}
		else if (!apiReference.equals(other.apiReference))
		{
			return false;
		}
		if (controlType == null)
		{
			if (other.controlType != null)
			{
				return false;
			}
		}
		else if (!controlType.equals(other.controlType))
		{
			return false;
		}
		if (conversionProgram == null)
		{
			if (other.conversionProgram != null)
			{
				return false;
			}
		}
		else if (!conversionProgram.equals(other.conversionProgram))
		{
			return false;
		}
		if (dbFieldDescription == null)
		{
			if (other.dbFieldDescription != null)
			{
				return false;
			}
		}
		else if (!dbFieldDescription.equals(other.dbFieldDescription))
		{
			return false;
		}
		if (dbFieldEnd == null)
		{
			if (other.dbFieldEnd != null)
			{
				return false;
			}
		}
		else if (!dbFieldEnd.equals(other.dbFieldEnd))
		{
			return false;
		}
		if (dbFieldFractions == null)
		{
			if (other.dbFieldFractions != null)
			{
				return false;
			}
		}
		else if (!dbFieldFractions.equals(other.dbFieldFractions))
		{
			return false;
		}
		if (dbFieldIntegers == null)
		{
			if (other.dbFieldIntegers != null)
			{
				return false;
			}
		}
		else if (!dbFieldIntegers.equals(other.dbFieldIntegers))
		{
			return false;
		}
		if (dbFieldLength == null)
		{
			if (other.dbFieldLength != null)
			{
				return false;
			}
		}
		else if (!dbFieldLength.equals(other.dbFieldLength))
		{
			return false;
		}
		if (dbFieldName == null)
		{
			if (other.dbFieldName != null)
			{
				return false;
			}
		}
		else if (!dbFieldName.equals(other.dbFieldName))
		{
			return false;
		}
		if (dbFieldStart == null)
		{
			if (other.dbFieldStart != null)
			{
				return false;
			}
		}
		else if (!dbFieldStart.equals(other.dbFieldStart))
		{
			return false;
		}
		if (dbFieldType == null)
		{
			if (other.dbFieldType != null)
			{
				return false;
			}
		}
		else if (!dbFieldType.equals(other.dbFieldType))
		{
			return false;
		}
		if (dbFileName == null)
		{
			if (other.dbFileName != null)
			{
				return false;
			}
		}
		else if (!dbFileName.equals(other.dbFileName))
		{
			return false;
		}
		if (equationApiLevel == null)
		{
			if (other.equationApiLevel != null)
			{
				return false;
			}
		}
		else if (!equationApiLevel.equals(other.equationApiLevel))
		{
			return false;
		}
		if (mapParameter == null)
		{
			if (other.mapParameter != null)
			{
				return false;
			}
		}
		else if (!mapParameter.equals(other.mapParameter))
		{
			return false;
		}
		if (retrievalInstance == null)
		{
			if (other.retrievalInstance != null)
			{
				return false;
			}
		}
		else if (!retrievalInstance.equals(other.retrievalInstance))
		{
			return false;
		}
		if (subControlType == null)
		{
			if (other.subControlType != null)
			{
				return false;
			}
		}
		else if (!subControlType.equals(other.subControlType))
		{
			return false;
		}
		return true;
	}

	/**
	 * Returns a string representation of this model which can be used by PatternFilter (see rule editor field pages).
	 */
	@Override
	public String toString()
	{
		if (apiFieldName == null)
		{
			return "<None>".intern();
		}

		return (apiFieldName + " " + apiFieldSequence + " " + apiFieldDescripton).trim();
	}
}
