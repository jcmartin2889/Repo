package com.misys.equation.common.dao.beans;

import com.misys.equation.common.search.results.ISearchResult;
import com.misys.equation.common.search.results.OptionSearchResult;
import com.misys.equation.common.utilities.Toolbox;

/**
 * GBRecord data-model.
 * 
 * @author deroset
 * 
 */
public class GBRecordDataModel extends SearchResultDataModel
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GBRecordDataModel.java 10646 2011-03-18 05:38:23Z capilid1 $";
	private static final long serialVersionUID = 1L;
	private final static String RECORD_NAME = "GBPF";

	private String optionId = ""; // GBOID
	private String programName = ""; // GBFPR
	private String programTitle = ""; // GBONM
	private String mandatoryNextReq = ""; // GBMNR
	private String defEntryData = ""; // GBODT
	private String userFuncKey1 = ""; // GBOID1
	private String userFuncKey2 = ""; // GBOID2
	private String userFuncKey3 = ""; // GBOID3
	private String userFuncKey4 = ""; // GBOID4
	private String pcMnem = ""; // GBMNC
	private String optionIdGA = ""; // GBFID
	private String repeatProcessing = ""; // GBRPP
	private String actionBarMenu = ""; // GBOID9
	private String optionType = ""; // GBOMT
	private String extendedInput = ""; // GBEIA
	private String application = ""; // GBAPP
	private String gbwm1 = ""; // GBWM1

	/**
	 * Construct an empty file
	 * 
	 */
	public GBRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	/**
	 * Construct a GB record key
	 * 
	 * @param optionId
	 *            - option id
	 */
	public GBRecordDataModel(String optionId)
	{
		super();
		setEqFileName(RECORD_NAME);

		setOptionId(optionId);
	}

	/**
	 * Construct a GB record with default values
	 * 
	 * @param optionId
	 *            - option id
	 * @param programName
	 *            - program name
	 * @param programTitle
	 *            - program title
	 * @param application
	 *            - application
	 */
	public GBRecordDataModel(String optionId, String programName, String programTitle, String application, String optionIdGA)
	{
		super();
		setEqFileName(RECORD_NAME);

		setOptionId(optionId);
		setProgramName(programName);
		setProgramTitle(programTitle);
		setApplication(application);
		setOptionIdGA(optionIdGA);
		this.repeatProcessing = "N";
		this.extendedInput = "Y";
	}

	// ---getters and setters----//

	/**
	 * Return the option id
	 * 
	 * @return the option id
	 */
	public String getOptionId()
	{
		return optionId;
	}

	/**
	 * Set the option id
	 * 
	 * @param optionId
	 *            - the option id
	 */
	public void setOptionId(String optionId)
	{
		this.optionId = Toolbox.trim(optionId, 3);
	}

	/**
	 * Return the program name
	 * 
	 * @return the program name
	 */
	public String getProgramName()
	{
		return programName;
	}

	/**
	 * Set the program name
	 * 
	 * @param programName
	 *            - the program name
	 */
	public void setProgramName(String programName)
	{
		this.programName = Toolbox.trim(programName, 6);
	}

	/**
	 * Return the program title
	 * 
	 * @return the program title
	 */
	public String getProgramTitle()
	{
		return programTitle;
	}

	/**
	 * Set the program title
	 * 
	 * @param programTitle
	 *            - the program title
	 */
	public void setProgramTitle(String programTitle)
	{
		this.programTitle = Toolbox.trim(programTitle, 35);
	}

	/**
	 * Return the mandatory next request flag
	 * 
	 * @return the mandatory next request flag
	 */
	public String getMandatoryNextReq()
	{
		return mandatoryNextReq;
	}

	/**
	 * Set the mandatory next request flag
	 * 
	 * @param mandatoryNextReq
	 *            - the mandatory next request flag
	 */
	public void setMandatoryNextReq(String mandatoryNextReq)
	{
		this.mandatoryNextReq = Toolbox.trim(mandatoryNextReq, 3);
	}

	/**
	 * Return the default entry data
	 * 
	 * @return the default entry data
	 */
	public String getDefEntryData()
	{
		return defEntryData;
	}

	/**
	 * Set the default entry data
	 * 
	 * @param defEntryData
	 *            - the default entry data
	 */
	public void setDefEntryData(String defEntryData)
	{
		this.defEntryData = Toolbox.trim(defEntryData, 55);
	}

	/**
	 * Return the user defined function key 1
	 * 
	 * @return the user defined function key 1
	 */
	public String getUserFuncKey1()
	{
		return userFuncKey1;
	}

	/**
	 * Set the user defined function key 1
	 * 
	 * @param userFuncKey1
	 *            - the user defined function key 1
	 */
	public void setUserFuncKey1(String userFuncKey1)
	{
		this.userFuncKey1 = Toolbox.trim(userFuncKey1, 3);
	}

	/**
	 * Return the user defined function key 2
	 * 
	 * @return the user defined function key 2
	 */
	public String getUserFuncKey2()
	{
		return userFuncKey2;
	}

	/**
	 * Set the user defined function key 2
	 * 
	 * @param userFuncKey2
	 *            - the user defined function key 2
	 */
	public void setUserFuncKey2(String userFuncKey2)
	{
		this.userFuncKey2 = Toolbox.trim(userFuncKey2, 3);
	}

	/**
	 * Return the user defined function key 3
	 * 
	 * @return the user defined function key 3
	 */
	public String getUserFuncKey3()
	{
		return userFuncKey3;
	}

	/**
	 * Set the user defined function key 3
	 * 
	 * @param userFuncKey3
	 *            - the user defined function key 3
	 */
	public void setUserFuncKey3(String userFuncKey3)
	{
		this.userFuncKey3 = Toolbox.trim(userFuncKey3, 3);
	}

	/**
	 * Return the user defined function key 4
	 * 
	 * @return the user defined function key 4
	 */
	public String getUserFuncKey4()
	{
		return userFuncKey4;
	}

	/**
	 * Set the user defined function key 4
	 * 
	 * @param userFuncKey4
	 *            - the user defined function key 4
	 */
	public void setUserFuncKey4(String userFuncKey4)
	{
		this.userFuncKey4 = Toolbox.trim(userFuncKey4, 3);
	}

	/**
	 * Return the PC mnemonic
	 * 
	 * @return the PC mnemonic
	 */
	public String getPcMnem()
	{
		return pcMnem;
	}

	/**
	 * Set the PC mnemonic
	 * 
	 * @param pcMnem
	 *            - the PC mnemonic
	 */
	public void setPcMnem(String pcMnem)
	{
		this.pcMnem = Toolbox.trim(pcMnem, 1);
	}

	/**
	 * Return the option ID from GA
	 * 
	 * @return the option ID from GA
	 */
	public String getOptionIdGA()
	{
		return optionIdGA;
	}

	/**
	 * Set the option ID from GA
	 * 
	 * @param optionIdGA
	 *            - the option ID from GA
	 */
	public void setOptionIdGA(String optionIdGA)
	{
		this.optionIdGA = Toolbox.trim(optionIdGA, 3);
	}

	/**
	 * Return the repeat processing flag
	 * 
	 * @return the repeat processing flag
	 */
	public String getRepeatProcessing()
	{
		return repeatProcessing;
	}

	/**
	 * Set the repeat processing flag
	 * 
	 * @param repeatProcessing
	 *            - the repeat processing flag
	 */
	public void setRepeatProcessing(String repeatProcessing)
	{
		this.repeatProcessing = Toolbox.trim(repeatProcessing, 1);
	}

	/**
	 * Return the option type
	 * 
	 * @return the option type
	 */
	public String getOptionType()
	{
		return optionType;
	}

	/**
	 * Set the option type
	 * 
	 * @param optionType
	 *            - the option type
	 */
	public void setOptionType(String optionType)
	{
		this.optionType = Toolbox.trim(optionType, 3);
	}

	/**
	 * Return the extended input flag
	 * 
	 * @return the extended input flag
	 */
	public String getExtendedInput()
	{
		return extendedInput;
	}

	/**
	 * Set the extended input flag
	 * 
	 * @param extendedInput
	 *            - the extended input flag
	 */
	public void setExtendedInput(String extendedInput)
	{
		this.extendedInput = Toolbox.trim(extendedInput, 1);
	}

	/**
	 * Return the action bar menu for PC
	 * 
	 * @return the action bar menu for PC
	 */
	public String getActionBarMenu()
	{
		return actionBarMenu;
	}

	/**
	 * Set the action bar menu for PC
	 * 
	 * @param actionBarMenu
	 *            - the action bar menu for PC
	 */
	public void setActionBarMenu(String actionBarMenu)
	{
		this.actionBarMenu = Toolbox.trim(actionBarMenu, 3);
	}

	/**
	 * Return the application
	 * 
	 * @return the application
	 */
	public String getApplication()
	{
		return application;
	}

	/**
	 * Set the application
	 * 
	 * @param application
	 *            - the application
	 */
	public void setApplication(String application)
	{
		this.application = Toolbox.trim(application, 4);
	}

	public String getGbwm1()
	{
		return gbwm1;
	}

	public void setGbwm1(String gbwm1)
	{
		this.gbwm1 = gbwm1;
	}

	@Override
	public ISearchResult populateFromDataModel(AbsRecord dataModel, final String unit, final String system)
	{
		return new OptionSearchResult(optionId, programTitle, unit, system, null);
	}

	/**
	 * Reload GB details
	 * 
	 * @param gbRecord
	 *            - GB record details
	 */
	public void updateWithThisRecord(GBRecordDataModel gbRecord)
	{
		this.setOptionId(gbRecord.getOptionId());
		this.setProgramName(gbRecord.getProgramName());
		this.setProgramTitle(gbRecord.getProgramTitle());
		this.setMandatoryNextReq(gbRecord.getMandatoryNextReq());
		this.setDefEntryData(gbRecord.getDefEntryData());
		this.setUserFuncKey1(gbRecord.getUserFuncKey1());
		this.setUserFuncKey2(gbRecord.getUserFuncKey2());
		this.setUserFuncKey3(gbRecord.getUserFuncKey3());
		this.setUserFuncKey4(gbRecord.getUserFuncKey4());
		this.setPcMnem(gbRecord.getPcMnem());
		this.setOptionIdGA(gbRecord.getOptionIdGA());
		this.setRepeatProcessing(gbRecord.getRepeatProcessing());
		this.setActionBarMenu(gbRecord.getActionBarMenu());
		this.setOptionType(gbRecord.getOptionType());
		this.setExtendedInput(gbRecord.getExtendedInput());
		this.setApplication(gbRecord.getApplication());
		this.setGbwm1(gbRecord.getGbwm1());
	}
}