package com.misys.equation.common.dao.beans;

/**
 * KAP Record data-model.
 * 
 * @author deroset
 * 
 */
public class KAPRecordDataModel extends AbsRecord
{

	private final static String RECORD_NAME = "KAPSEC1LF";

	private String userId;
	private String unitMnemonic;
	private String system;
	private String initialProgram;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1258388076837l;

	/**
	 * Default constructor
	 */
	public KAPRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);

	}

	public KAPRecordDataModel(String userId, String unitMnemonic, String system)
	{
		this.userId = userId;
		this.unitMnemonic = unitMnemonic;
		this.system = system;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getUserId()
	{
		return this.userId;
	}
	public void setUserId(String parameter)
	{
		this.userId = parameter;
	}
	public String getUnitMnemonic()
	{
		return this.unitMnemonic;
	}
	public void setUnitMnemonic(String parameter)
	{
		this.unitMnemonic = parameter;
	}
	public String getSystem()
	{
		return this.system;
	}
	public void setSystem(String parameter)
	{
		this.system = parameter;
	}
	public String getInitialProgram()
	{
		return this.initialProgram;
	}
	public void setInitialProgram(String parameter)
	{
		this.initialProgram = parameter;
	}

}
