package com.misys.equation.common.globalprocessing.audit;

public class H56H1RDS
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: H56H1RDS.java 8520 2010-08-09 03:31:46Z barcelr1 $";

	private String functionIdentifier; // RPGM Function identifier
	private String inputBranch; // BRNM Input branch
	private String user; // USID User identifier
	private String workstationId; // WSID Workstation identifier (default to @GDP)
	private String functionType; // JTT Function Type
	private byte[] transactionSerialisation; // DSAIM Transaction serialisation
	private String returnCode; // RREC Return Code (Default to F)
	private String errorMessage; // RMES7 Error Message
	private String warningMessage; // AOW Warning Messages
	private Boolean applyTransactionExt; // AEXT Apply transaction during external input (default to N)
	private Boolean applyTransactionRecovery; // AREC Apply transaction during recovery (default to N)

	public H56H1RDS(String functionIdentifier, String inputBranch, String user, String workstationId, String functionType,
					byte[] transactionSerialisation, String returnCode, String errorMessage, String warningMessage,
					Boolean applyTransactionExt, Boolean applyTransactionRecovery)
	{

		this.functionIdentifier = functionIdentifier;
		this.inputBranch = inputBranch;
		this.user = user;
		this.workstationId = workstationId;
		this.functionType = functionType;
		this.transactionSerialisation = transactionSerialisation;
		this.returnCode = returnCode;
		this.errorMessage = errorMessage;
		this.warningMessage = warningMessage;
		this.applyTransactionExt = applyTransactionExt;
		this.applyTransactionRecovery = applyTransactionRecovery;
	}

	public H56H1RDS()
	{

		this.functionIdentifier = null;
		this.inputBranch = null;
		this.user = null;
		this.workstationId = "@GDP"; // default
		this.functionType = null;
		this.transactionSerialisation = null;
		this.returnCode = "F"; // default
		this.errorMessage = null;
		this.warningMessage = null;
		this.applyTransactionExt = false; // default
		this.applyTransactionRecovery = false; // default

	}

	public String getFunctionIdentifier()
	{
		return functionIdentifier;
	}
	public void setFunctionIdentifier(String functionIdentifier)
	{
		this.functionIdentifier = functionIdentifier;
	}
	public String getInputBranch()
	{
		return inputBranch;
	}
	public void setInputBranch(String inputBranch)
	{
		this.inputBranch = inputBranch;
	}
	public String getUser()
	{
		return user;
	}
	public void setUser(String user)
	{
		this.user = user;
	}
	public String getWorkstationId()
	{
		return workstationId;
	}
	public void setWorkstationId(String workstationId)
	{
		this.workstationId = workstationId;
	}
	public String getFunctionType()
	{
		return functionType;
	}
	public void setFunctionType(String functionType)
	{
		this.functionType = functionType;
	}
	public byte[] getTransactionSerialisation()
	{
		return transactionSerialisation;
	}
	public void setTransactionSerialisation(byte[] transactionSerialisation)
	{
		this.transactionSerialisation = transactionSerialisation;
	}
	public String getReturnCode()
	{
		return returnCode;
	}
	public void setReturnCode(String returnCode)
	{
		this.returnCode = returnCode;
	}
	public String getErrorMessage()
	{
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}
	public String getWarningMessage()
	{
		return warningMessage;
	}
	public void setWarningMessage(String warningMessage)
	{
		this.warningMessage = warningMessage;
	}
	public Boolean getApplyTransactionExt()
	{
		return applyTransactionExt;
	}
	public void setApplyTransactionExt(Boolean applyTransactionExt)
	{
		this.applyTransactionExt = applyTransactionExt;
	}
	public Boolean getApplyTransactionRecovery()
	{
		return applyTransactionRecovery;
	}
	public void setApplyTransactionRecovery(Boolean applyTransactionRecovery)
	{
		this.applyTransactionRecovery = applyTransactionRecovery;
	}

}