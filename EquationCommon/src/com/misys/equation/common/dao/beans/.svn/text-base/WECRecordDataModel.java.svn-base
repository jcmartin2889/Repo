package com.misys.equation.common.dao.beans;

/**
 * WEC Record data-model.
 * 
 * @author deroset
 * 
 */
public class WECRecordDataModel extends AbsRecord
{		
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WECRecordDataModel.java 13103 2012-03-28 06:23:34Z bernie.terrado $";
	private final static String RECORD_NAME = "WECPF";
	
	private String optionMnemonic;
	private String requiresMakerChecker;
	private String completeByChecker;
	
	public final static String MAKER_CHECKER_REQ = "Y"; // Maker-Checker required
	
	
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1323183018056l;
	
	
	/**
	 * Default constructor
	 */
	public WECRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);		
	}
	
	/**
	 * Construct a WEC record key to retrieve WEC record for desktop
	 * 
	 * @param optionId
	 *            - option id
	 */
	public WECRecordDataModel(String optionId)
	{
		super();
		setEqFileName(RECORD_NAME);

		setOptionMnemonic(optionId);
	}
	
	/**
	 * Retrieves the Equation functions/service mnemonic
	 * @return Equation functions/service mnemonic
	 */
	public String getOptionMnemonic()
	{
		return this.optionMnemonic;
	}
	
	/**
	 * Sets the Equation functions/service mnemonic
	 * @param parameter Equation functions/service mnemonic
	 */
	public void setOptionMnemonic( String parameter)
	{
		this.optionMnemonic= parameter;
	}
	
	/**
	 *Retrieves the Requires Maker Checker field 
	 * @return true if transaction requires Maker Checker processing
	 */
	public String getRequiresMakerChecker()
	{
		return this.requiresMakerChecker;
	}
	
	/**
	 * Sets the Requires Maker Checker field
	 * 
	 * @param parameter Requires Maker Checker field (true or false)
	 */
	public void setRequiresMakerChecker( String parameter)
	{
		this.requiresMakerChecker= parameter;
	}
	
	/**
	 * Retrieves the Complete by Checker field
	 * 
	 * @return true if Complete by Checker 
	 */
	public String getCompleteByChecker()
	{
		return this.completeByChecker;
	}
	
	/**
	 * Sets the Complete by Checker field
	 * 
	 * @param parameter Complete by Checker (true or false)
	 */
	public void setCompleteByChecker( String parameter)
	{
		this.completeByChecker= parameter;
	}
}
