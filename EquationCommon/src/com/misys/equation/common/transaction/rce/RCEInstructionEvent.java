package com.misys.equation.common.transaction.rce;

public class RCEInstructionEvent implements IRCETransactionEvent
{
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RCEInstructionEvent.java 17435 2013-10-15 14:16:27Z lima12 $";

	private String instructionType;
	private String instructionDate;
	private String repaymentFrequency;
	private String firstRepaymentDate;
	private String numberOfRepayments;
	private String maturityDate;
	private String repaymentAmount;
	private String firstAmount;
	private String firstAmountCanDiffer;

	// public ScheduleInstruction(String internalApi, String apiFile, byte[] apiData, String excludedFields, String mode,
	// String fromUnit, String keyIn, boolean recordFound, boolean valid, String errorMessage)
	// {
	// this.internalApi = internalApi;
	// this.apiFile = apiFile;
	// this.apiData = apiData;
	// this.excludedFields = excludedFields;
	// this.mode = mode;
	// this.fromUnit = fromUnit;
	// this.keyIn = keyIn;
	// this.recordFound = recordFound;
	// this.valid = valid;
	// this.errorMessage = errorMessage;
	// }

	public RCEInstructionEvent()
	{
		this.instructionType = "NI";
		this.instructionDate = "";
		this.repaymentFrequency = "";
		this.firstRepaymentDate = "";
		this.numberOfRepayments = "";
		this.maturityDate = "";
		this.repaymentAmount = "";
		this.firstAmount = "";
		this.firstAmountCanDiffer = "N";
	}

	public String getInstructionType()
	{
		return instructionType;
	}

	public void setInstructionType(String instructionType)
	{
		this.instructionType = instructionType;
	}

	public String getInstructionDate()
	{
		return instructionDate;
	}

	public void setInstructionDate(String instructionDate)
	{
		this.instructionDate = instructionDate;
	}

	public String getRepaymentFrequency()
	{
		return repaymentFrequency;
	}

	public void setRepaymentFrequency(String repaymentFrequency)
	{
		this.repaymentFrequency = repaymentFrequency;
	}

	public String getFirstRepaymentDate()
	{
		return firstRepaymentDate;
	}

	public void setFirstRepaymentDate(String firstRepaymentDate)
	{
		this.firstRepaymentDate = firstRepaymentDate;
	}

	public String getNumberOfRepayments()
	{
		return numberOfRepayments;
	}

	public void setNumberOfRepayments(String numberOfRepayments)
	{
		this.numberOfRepayments = numberOfRepayments;
	}

	public String getMaturityDate()
	{
		return maturityDate;
	}

	public void setMaturityDate(String maturityDate)
	{
		this.maturityDate = maturityDate;
	}

	public String getRepaymentAmount()
	{
		return repaymentAmount;
	}

	public void setRepaymentAmount(String repaymentAmount)
	{
		this.repaymentAmount = repaymentAmount;
	}

	public String getFirstAmount()
	{
		return firstAmount;
	}

	public void setFirstAmount(String firstAmount)
	{
		this.firstAmount = firstAmount;
	}

	public String getFirstAmountCanDiffer()
	{
		return firstAmountCanDiffer;
	}

	public void setFirstAmountCanDiffer(String firstAmountCanDiffer)
	{
		this.firstAmountCanDiffer = firstAmountCanDiffer;
	}
}
