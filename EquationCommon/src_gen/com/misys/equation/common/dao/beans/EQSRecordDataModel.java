package com.misys.equation.common.dao.beans;

/**
 * EQS Record data-model.
 * 
 * @author deroset
 * 
 */
public class EQSRecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "EQSEC";

	private String userId;
	private String authorisedUnit;
	private String unitServer;
	private String initialMenu;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1273647273883l;

	/**
	 * Default constructor
	 */
	public EQSRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public EQSRecordDataModel(String userId, String authorisedUnit, String unitServer)
	{
		this.userId = userId;
		this.authorisedUnit = authorisedUnit;
		this.unitServer = unitServer;
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
	public String getAuthorisedUnit()
	{
		return this.authorisedUnit;
	}
	public void setAuthorisedUnit(String parameter)
	{
		this.authorisedUnit = parameter;
	}
	public String getUnitServer()
	{
		return this.unitServer;
	}
	public void setUnitServer(String parameter)
	{
		this.unitServer = parameter;
	}
	public String getInitialMenu()
	{
		return this.initialMenu;
	}
	public void setInitialMenu(String parameter)
	{
		this.initialMenu = parameter;
	}
}