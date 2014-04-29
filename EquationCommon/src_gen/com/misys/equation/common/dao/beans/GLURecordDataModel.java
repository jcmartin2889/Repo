package com.misys.equation.common.dao.beans;

/**
 * GLU Record data-model.
 * 
 * @author deroset
 * 
 */
public class GLURecordDataModel extends AbsRecord
{

	private final static String RECORD_NAME = "GLU10LF";

	private String unitMnemonic;
	private String systemID;
	private String unitDescription;
	private String equationLevel;
	private String desktopSupported;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1258127204731l;

	/**
	 * Default constructor
	 */
	public GLURecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);

	}

	public GLURecordDataModel(String unitMnemonic, String systemID)
	{
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getUnitMnemonic()
	{
		return this.unitMnemonic;
	}
	public void setUnitMnemonic(String parameter)
	{
		this.unitMnemonic = parameter;
	}
	public String getSystemID()
	{
		return this.systemID;
	}
	public void setSystemID(String parameter)
	{
		this.systemID = parameter;
	}
	public String getUnitDescription()
	{
		return this.unitDescription;
	}
	public void setUnitDescription(String parameter)
	{
		this.unitDescription = parameter;
	}
	public String getEquationLevel()
	{
		return this.equationLevel;
	}
	public void setEquationLevel(String parameter)
	{
		this.equationLevel = parameter;
	}
	public String getDesktopSupported()
	{
		return this.desktopSupported;
	}
	public void setDesktopSupported(String parameter)
	{
		this.desktopSupported = parameter;
	}

}
