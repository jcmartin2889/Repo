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
public interface IEQ_CMN_SearchHandler extends com.trapedza.bankfusion.servercommon.steps.refimpl.Processable
{
	public static final String IN_SearchData = "SearchData";
	public static final String IN_SearchHeader = "SearchHeader";
	public static final String OUT_OutputSearchData = "OutputSearchData";
	public static final String OUT_OutputSearchHeader = "OutputSearchHeader";

	public void process(BankFusionEnvironment env) throws BankFusionException;

	public Object getF_IN_SearchData();

	public void setF_IN_SearchData(Object param);

	public bf.com.misys.eqf.types.header.SearchRqHeader getF_IN_SearchHeader();

	public void setF_IN_SearchHeader(bf.com.misys.eqf.types.header.SearchRqHeader param);

	public Map getInDataMap();

	public Object getF_OUT_OutputSearchData();

	public void setF_OUT_OutputSearchData(Object param);

	public bf.com.misys.eqf.types.header.SearchRsHeader getF_OUT_OutputSearchHeader();

	public void setF_OUT_OutputSearchHeader(bf.com.misys.eqf.types.header.SearchRsHeader param);

	public Map getOutDataMap();
}