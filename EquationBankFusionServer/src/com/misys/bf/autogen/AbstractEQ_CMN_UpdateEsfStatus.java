package com.misys.bf.autogen;

import java.util.ArrayList;
import com.trapedza.bankfusion.microflow.ActivityStep;
import java.util.Map;
import java.util.List;
import com.trapedza.bankfusion.core.BankFusionException;
import java.util.HashMap;
import com.trapedza.bankfusion.core.DataType;
import com.trapedza.bankfusion.utils.Utils;
import com.trapedza.bankfusion.core.CommonConstants;
import java.util.Iterator;
import com.trapedza.bankfusion.servercommon.commands.BankFusionEnvironment;
import com.trapedza.bankfusion.core.ExtensionPointHelper;
import bf.com.misys.bankfusion.attributes.UserDefinedFields;

/**
 * 
 * DO NOT CHANGE MANUALLY - THIS IS AUTOMATICALLY GENERATED CODE.<br>
 * This will be overwritten by any subsequent code-generation.
 * 
 */
public abstract class AbstractEQ_CMN_UpdateEsfStatus implements IEQ_CMN_UpdateEsfStatus
{
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractEQ_CMN_UpdateEsfStatus.java 17324 2013-09-23 00:54:19Z williae1 $";
	/**
	 * @deprecated use no-argument constructor!
	 */
	public AbstractEQ_CMN_UpdateEsfStatus(BankFusionEnvironment env)
	{
	}

	public AbstractEQ_CMN_UpdateEsfStatus()
	{
	}

	private bf.com.misys.eqf.types.header.UpdateEsfStatusRqHeader f_IN_input = new bf.com.misys.eqf.types.header.UpdateEsfStatusRqHeader();
	{
		f_IN_input.setSystemId(Utils.getSTRINGValue(""));
		f_IN_input.setUserId(Utils.getSTRINGValue(""));
		f_IN_input.setAction(CommonConstants.EMPTY_STRING);
		f_IN_input.setReason(Utils.getSTRINGValue(""));
		f_IN_input.setUnitId(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.EsfKey var_019_input_esfKey = new bf.com.misys.eqf.types.header.EsfKey();

		var_019_input_esfKey.setUserId(Utils.getSTRINGValue(""));
		var_019_input_esfKey.setOptionId(CommonConstants.EMPTY_STRING);
		var_019_input_esfKey.setSessionId(Utils.getSTRINGValue(""));
		var_019_input_esfKey.setTransactionId(Utils.getSTRINGValue(""));
		f_IN_input.setEsfKey(var_019_input_esfKey);
	}
	private ArrayList<String> udfBoNames = new ArrayList<String>();
	private HashMap udfStateData = new HashMap();

	private bf.com.misys.eqf.types.header.UpdateEsfStatusRsHeader f_OUT_output = new bf.com.misys.eqf.types.header.UpdateEsfStatusRsHeader();
	{
		f_OUT_output.setErrorMessage(Utils.getSTRINGValue(""));
		f_OUT_output.setErrorCode(Utils.getSTRINGValue(""));
	}

	public void process(BankFusionEnvironment env) throws BankFusionException
	{
	}

	public bf.com.misys.eqf.types.header.UpdateEsfStatusRqHeader getF_IN_input()
	{
		return f_IN_input;
	}

	public void setF_IN_input(bf.com.misys.eqf.types.header.UpdateEsfStatusRqHeader param)
	{
		f_IN_input = param;
	}

	public Map getInDataMap()
	{
		Map dataInMap = new HashMap();
		dataInMap.put(IN_input, f_IN_input);
		return dataInMap;
	}

	public bf.com.misys.eqf.types.header.UpdateEsfStatusRsHeader getF_OUT_output()
	{
		return f_OUT_output;
	}

	public void setF_OUT_output(bf.com.misys.eqf.types.header.UpdateEsfStatusRsHeader param)
	{
		f_OUT_output = param;
	}

	public void setUDFData(String boName, UserDefinedFields fields)
	{
		if (!udfBoNames.contains(boName.toUpperCase()))
		{
			udfBoNames.add(boName.toUpperCase());
		}
		String udfKey = boName.toUpperCase() + CommonConstants.CUSTOM_PROP;
		udfStateData.put(udfKey, fields);
	}

	public Map getOutDataMap()
	{
		Map dataOutMap = new HashMap();
		dataOutMap.put(OUT_output, f_OUT_output);
		dataOutMap.put(CommonConstants.ACTIVITYSTEP_UDF_BONAMES, udfBoNames);
		dataOutMap.put(CommonConstants.ACTIVITYSTEP_UDF_STATE_DATA, udfStateData);
		return dataOutMap;
	}
}
