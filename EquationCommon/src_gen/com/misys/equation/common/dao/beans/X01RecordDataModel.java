package com.misys.equation.common.dao.beans;

/**
 * X01 Record data-model.
 * 
 * @author deroset
 */
public class X01RecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "X010LF";

	private String redundant;
	private int transferDate;
	private String debitAccountBranch;
	private String debitAccountBasic;
	private String debitAccountSuffix;
	private String creditAccountBranch;
	private String creditAccountBasic;
	private String creditAccountSuffix;
	private int posting1Date;
	private String posting1Branch;
	private String posting1BatchId;
	private int posting1Sequence;
	private int posting2Date;
	private String posting2Branch;
	private String posting2BatchId;
	private int posting2Sequence;
	private int posting3Date;
	private String posting3Branch;
	private String posting3BatchId;
	private int posting3Sequence;
	private int posting4Date;
	private String posting4Branch;
	private String posting4BatchId;
	private int posting4Sequence;
	private String fontisTransfer;
	private String standingOrderTransfer;
	private String transferReference;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1284999546573l;

	/**
	 * Default constructor
	 */
	public X01RecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public X01RecordDataModel(int transferDate, String debitAccountBranch, String debitAccountBasic, String debitAccountSuffix,
					String creditAccountBranch, String creditAccountBasic, String creditAccountSuffix, String fontisTransfer,
					String transferReference)
	{
		this.transferDate = transferDate;
		this.debitAccountBranch = debitAccountBranch;
		this.debitAccountBasic = debitAccountBasic;
		this.debitAccountSuffix = debitAccountSuffix;
		this.creditAccountBranch = creditAccountBranch;
		this.creditAccountBasic = creditAccountBasic;
		this.creditAccountSuffix = creditAccountSuffix;
		this.fontisTransfer = fontisTransfer;
		this.transferReference = transferReference;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getRedundant()
	{
		return this.redundant;
	}
	public void setRedundant(String parameter)
	{
		this.redundant = parameter;
	}
	public int getTransferDate()
	{
		return this.transferDate;
	}
	public void setTransferDate(int parameter)
	{
		this.transferDate = parameter;
	}
	public String getDebitAccountBranch()
	{
		return this.debitAccountBranch;
	}
	public void setDebitAccountBranch(String parameter)
	{
		this.debitAccountBranch = parameter;
	}
	public String getDebitAccountBasic()
	{
		return this.debitAccountBasic;
	}
	public void setDebitAccountBasic(String parameter)
	{
		this.debitAccountBasic = parameter;
	}
	public String getDebitAccountSuffix()
	{
		return this.debitAccountSuffix;
	}
	public void setDebitAccountSuffix(String parameter)
	{
		this.debitAccountSuffix = parameter;
	}
	public String getCreditAccountBranch()
	{
		return this.creditAccountBranch;
	}
	public void setCreditAccountBranch(String parameter)
	{
		this.creditAccountBranch = parameter;
	}
	public String getCreditAccountBasic()
	{
		return this.creditAccountBasic;
	}
	public void setCreditAccountBasic(String parameter)
	{
		this.creditAccountBasic = parameter;
	}
	public String getCreditAccountSuffix()
	{
		return this.creditAccountSuffix;
	}
	public void setCreditAccountSuffix(String parameter)
	{
		this.creditAccountSuffix = parameter;
	}
	public int getPosting1Date()
	{
		return this.posting1Date;
	}
	public void setPosting1Date(int parameter)
	{
		this.posting1Date = parameter;
	}
	public String getPosting1Branch()
	{
		return this.posting1Branch;
	}
	public void setPosting1Branch(String parameter)
	{
		this.posting1Branch = parameter;
	}
	public String getPosting1BatchId()
	{
		return this.posting1BatchId;
	}
	public void setPosting1BatchId(String parameter)
	{
		this.posting1BatchId = parameter;
	}
	public int getPosting1Sequence()
	{
		return this.posting1Sequence;
	}
	public void setPosting1Sequence(int parameter)
	{
		this.posting1Sequence = parameter;
	}
	public int getPosting2Date()
	{
		return this.posting2Date;
	}
	public void setPosting2Date(int parameter)
	{
		this.posting2Date = parameter;
	}
	public String getPosting2Branch()
	{
		return this.posting2Branch;
	}
	public void setPosting2Branch(String parameter)
	{
		this.posting2Branch = parameter;
	}
	public String getPosting2BatchId()
	{
		return this.posting2BatchId;
	}
	public void setPosting2BatchId(String parameter)
	{
		this.posting2BatchId = parameter;
	}
	public int getPosting2Sequence()
	{
		return this.posting2Sequence;
	}
	public void setPosting2Sequence(int parameter)
	{
		this.posting2Sequence = parameter;
	}
	public int getPosting3Date()
	{
		return this.posting3Date;
	}
	public void setPosting3Date(int parameter)
	{
		this.posting3Date = parameter;
	}
	public String getPosting3Branch()
	{
		return this.posting3Branch;
	}
	public void setPosting3Branch(String parameter)
	{
		this.posting3Branch = parameter;
	}
	public String getPosting3BatchId()
	{
		return this.posting3BatchId;
	}
	public void setPosting3BatchId(String parameter)
	{
		this.posting3BatchId = parameter;
	}
	public int getPosting3Sequence()
	{
		return this.posting3Sequence;
	}
	public void setPosting3Sequence(int parameter)
	{
		this.posting3Sequence = parameter;
	}
	public int getPosting4Date()
	{
		return this.posting4Date;
	}
	public void setPosting4Date(int parameter)
	{
		this.posting4Date = parameter;
	}
	public String getPosting4Branch()
	{
		return this.posting4Branch;
	}
	public void setPosting4Branch(String parameter)
	{
		this.posting4Branch = parameter;
	}
	public String getPosting4BatchId()
	{
		return this.posting4BatchId;
	}
	public void setPosting4BatchId(String parameter)
	{
		this.posting4BatchId = parameter;
	}
	public int getPosting4Sequence()
	{
		return this.posting4Sequence;
	}
	public void setPosting4Sequence(int parameter)
	{
		this.posting4Sequence = parameter;
	}
	public String getFontisTransfer()
	{
		return this.fontisTransfer;
	}
	public void setFontisTransfer(String parameter)
	{
		this.fontisTransfer = parameter;
	}
	public String getStandingOrderTransfer()
	{
		return this.standingOrderTransfer;
	}
	public void setStandingOrderTransfer(String parameter)
	{
		this.standingOrderTransfer = parameter;
	}
	public String getTransferReference()
	{
		return this.transferReference;
	}
	public void setTransferReference(String parameter)
	{
		this.transferReference = parameter;
	}
}