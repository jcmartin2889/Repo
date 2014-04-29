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
public abstract class AbstractEQ_CMN_ServiceHandler implements IEQ_CMN_ServiceHandler
{
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractEQ_CMN_ServiceHandler.java 17324 2013-09-23 00:54:19Z williae1 $";
	/**
	 * @deprecated use no-argument constructor!
	 */
	public AbstractEQ_CMN_ServiceHandler(BankFusionEnvironment env)
	{
	}

	public AbstractEQ_CMN_ServiceHandler()
	{
	}

	private Object f_IN_ServiceData = new String();

	private bf.com.misys.eqf.types.header.ServiceRqHeader f_IN_ServiceHeader = new bf.com.misys.eqf.types.header.ServiceRqHeader();
	{
		f_IN_ServiceHeader.setServiceMode(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.UiControlRq var_019_ServiceHeader_uiControlRq = new bf.com.misys.eqf.types.header.UiControlRq();

		var_019_ServiceHeader_uiControlRq.setChildFunctionHandlerId(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_uiControlRq.setCkey(Utils.getINTEGERValue(""));
		var_019_ServiceHeader_uiControlRq.setCurScrn(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_uiControlRq.setKeyLoaded(Utils.getBOOLEANValue(""));
		var_019_ServiceHeader_uiControlRq.setFldValidate(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_uiControlRq.setReason(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_uiControlRq.setLoadFieldSet(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_uiControlRq.setLoadField(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_uiControlRq.setUiMode(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_uiControlRq.setTransactionId(Utils.getSTRINGValue(""));
		f_IN_ServiceHeader.setUiControlRq(var_019_ServiceHeader_uiControlRq);

		bf.com.misys.eqf.types.header.EnquiryRq var_019_ServiceHeader_enquiryRq = new bf.com.misys.eqf.types.header.EnquiryRq();

		var_019_ServiceHeader_enquiryRq.setNumberOfRows(Utils.getINTEGERValue(""));
		var_019_ServiceHeader_enquiryRq.setPagingInformation(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_enquiryRq.setRequestedPage(Utils.getINTEGERValue(""));
		f_IN_ServiceHeader.setEnquiryRq(var_019_ServiceHeader_enquiryRq);
		f_IN_ServiceHeader.addChecksumFilter(0, Utils.getSTRINGValue(""));

		f_IN_ServiceHeader.setReference(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.ExtensionDataRq var_019_ServiceHeader_userExtensionData = new bf.com.misys.eqf.types.header.ExtensionDataRq();

		var_019_ServiceHeader_userExtensionData.setVersion(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_userExtensionData.setServiceLinkageId(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_userExtensionData.setUserExtension(Utils.getJAVA_OBJECTValue(""));
		var_019_ServiceHeader_userExtensionData.setSecondaryServiceExtension(Utils.getJAVA_OBJECTValue(""));
		f_IN_ServiceHeader.setUserExtensionData(var_019_ServiceHeader_userExtensionData);

		f_IN_ServiceHeader.setSupervisor(Utils.getSTRINGValue(""));
		f_IN_ServiceHeader.setOptionId(CommonConstants.EMPTY_STRING);
		bf.com.misys.eqf.types.header.ExtensionDataRq var_019_ServiceHeader_misysExtensionData = new bf.com.misys.eqf.types.header.ExtensionDataRq();

		var_019_ServiceHeader_misysExtensionData.setVersion(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_misysExtensionData.setServiceLinkageId(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_misysExtensionData.setUserExtension(Utils.getJAVA_OBJECTValue(""));
		var_019_ServiceHeader_misysExtensionData.setSecondaryServiceExtension(Utils.getJAVA_OBJECTValue(""));
		f_IN_ServiceHeader.setMisysExtensionData(var_019_ServiceHeader_misysExtensionData);

		bf.com.misys.eqf.types.header.RqHeader var_019_ServiceHeader_rqHeader = new bf.com.misys.eqf.types.header.RqHeader();

		var_019_ServiceHeader_rqHeader.setUserExtension(Utils.getJAVA_OBJECTValue(""));
		var_019_ServiceHeader_rqHeader.setEqMessageDetail(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.Formatting var_019_ServiceHeader_rqHeader_formatting = new bf.com.misys.eqf.types.header.Formatting();

		var_019_ServiceHeader_rqHeader_formatting.setDateFormat(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_formatting.setReturnAllFormats(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_formatting.setAmountFormat(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader.setFormatting(var_019_ServiceHeader_rqHeader_formatting);

		var_019_ServiceHeader_rqHeader.setUserIdType(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader.setDataSourceName(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader.setUnitId(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader.setSessionId(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader.setMessageType(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader.setUserId(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader.setVersion(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader.setMessageId(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader.setSystemId(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.Orig var_019_ServiceHeader_rqHeader_orig = new bf.com.misys.eqf.types.header.Orig();

		var_019_ServiceHeader_rqHeader_orig.setOrigTCPIP(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.BranchKeys var_019_ServiceHeader_rqHeader_orig_origBranch = new bf.com.misys.eqf.types.header.BranchKeys();

		var_019_ServiceHeader_rqHeader_orig_origBranch.setBranchCode(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_orig_origBranch.setBranchMnemonic(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_orig.setOrigBranch(var_019_ServiceHeader_rqHeader_orig_origBranch);

		var_019_ServiceHeader_rqHeader_orig.setOrigWorkstation(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_orig.setAppId(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader.setOrig(var_019_ServiceHeader_rqHeader_orig);

		bf.com.misys.eqf.types.header.Overrides var_019_ServiceHeader_rqHeader_overrides = new bf.com.misys.eqf.types.header.Overrides();

		var_019_ServiceHeader_rqHeader_overrides.setOverrideType(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.EqMessage var_019_ServiceHeader_rqHeader_overrides_overrideExclusion = new bf.com.misys.eqf.types.header.EqMessage();

		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion.setOverridden(Utils.getBOOLEANValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion.setFirstLevelText(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.BranchLimitParameters var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_branchLimit = new bf.com.misys.eqf.types.header.BranchLimitParameters();

		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_branchLimit.setLocalCurrencyAmount(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_branchLimit.setDebitCredit(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.BranchKeys var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_branchLimit_affectedBranch = new bf.com.misys.eqf.types.header.BranchKeys();

		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_branchLimit_affectedBranch.setBranchCode(Utils
						.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_branchLimit_affectedBranch.setBranchMnemonic(Utils
						.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_branchLimit
						.setAffectedBranch(var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_branchLimit_affectedBranch);

		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion
						.setBranchLimit(var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_branchLimit);

		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion.setEqMessageParameter3(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion.setEqMessageParameter2(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion.setEqMessageParameter1(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion.setEqMessageSeverity(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion.setFormattedMessage(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.ServiceStack var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_serviceStack = new bf.com.misys.eqf.types.header.ServiceStack();
		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_serviceStack.addServiceStackEntry(0, Utils.getSTRINGValue(""));

		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion
						.setServiceStack(var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_serviceStack);

		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion.setEqMessageId(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.FieldLocation var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_fieldLocation = new bf.com.misys.eqf.types.header.FieldLocation();

		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_fieldLocation.setSetId(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_fieldLocation.addFieldId(0, Utils.getSTRINGValue(""));

		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_fieldLocation.setRowSequence(Utils.getINTEGERValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_fieldLocation.setFieldSet(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion
						.setFieldLocation(var_019_ServiceHeader_rqHeader_overrides_overrideExclusion_fieldLocation);

		var_019_ServiceHeader_rqHeader_overrides_overrideExclusion.setSecondLevelText(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides
						.addOverrideExclusion(0, var_019_ServiceHeader_rqHeader_overrides_overrideExclusion);

		bf.com.misys.eqf.types.header.EqMessage var_019_ServiceHeader_rqHeader_overrides_overrideSelection = new bf.com.misys.eqf.types.header.EqMessage();

		var_019_ServiceHeader_rqHeader_overrides_overrideSelection.setOverridden(Utils.getBOOLEANValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideSelection.setFirstLevelText(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.BranchLimitParameters var_019_ServiceHeader_rqHeader_overrides_overrideSelection_branchLimit = new bf.com.misys.eqf.types.header.BranchLimitParameters();

		var_019_ServiceHeader_rqHeader_overrides_overrideSelection_branchLimit.setLocalCurrencyAmount(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideSelection_branchLimit.setDebitCredit(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.BranchKeys var_019_ServiceHeader_rqHeader_overrides_overrideSelection_branchLimit_affectedBranch = new bf.com.misys.eqf.types.header.BranchKeys();

		var_019_ServiceHeader_rqHeader_overrides_overrideSelection_branchLimit_affectedBranch.setBranchCode(Utils
						.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideSelection_branchLimit_affectedBranch.setBranchMnemonic(Utils
						.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideSelection_branchLimit
						.setAffectedBranch(var_019_ServiceHeader_rqHeader_overrides_overrideSelection_branchLimit_affectedBranch);

		var_019_ServiceHeader_rqHeader_overrides_overrideSelection
						.setBranchLimit(var_019_ServiceHeader_rqHeader_overrides_overrideSelection_branchLimit);

		var_019_ServiceHeader_rqHeader_overrides_overrideSelection.setEqMessageParameter3(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideSelection.setEqMessageParameter2(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideSelection.setEqMessageParameter1(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideSelection.setEqMessageSeverity(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideSelection.setFormattedMessage(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.ServiceStack var_019_ServiceHeader_rqHeader_overrides_overrideSelection_serviceStack = new bf.com.misys.eqf.types.header.ServiceStack();
		var_019_ServiceHeader_rqHeader_overrides_overrideSelection_serviceStack.addServiceStackEntry(0, Utils.getSTRINGValue(""));

		var_019_ServiceHeader_rqHeader_overrides_overrideSelection
						.setServiceStack(var_019_ServiceHeader_rqHeader_overrides_overrideSelection_serviceStack);

		var_019_ServiceHeader_rqHeader_overrides_overrideSelection.setEqMessageId(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.FieldLocation var_019_ServiceHeader_rqHeader_overrides_overrideSelection_fieldLocation = new bf.com.misys.eqf.types.header.FieldLocation();

		var_019_ServiceHeader_rqHeader_overrides_overrideSelection_fieldLocation.setSetId(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideSelection_fieldLocation.addFieldId(0, Utils.getSTRINGValue(""));

		var_019_ServiceHeader_rqHeader_overrides_overrideSelection_fieldLocation.setRowSequence(Utils.getINTEGERValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideSelection_fieldLocation.setFieldSet(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides_overrideSelection
						.setFieldLocation(var_019_ServiceHeader_rqHeader_overrides_overrideSelection_fieldLocation);

		var_019_ServiceHeader_rqHeader_overrides_overrideSelection.setSecondLevelText(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader_overrides
						.addOverrideSelection(0, var_019_ServiceHeader_rqHeader_overrides_overrideSelection);

		var_019_ServiceHeader_rqHeader.setOverrides(var_019_ServiceHeader_rqHeader_overrides);

		var_019_ServiceHeader_rqHeader.setSessionType(Utils.getSTRINGValue(""));
		var_019_ServiceHeader_rqHeader.addResponseFilter(0, Utils.getSTRINGValue(""));

		f_IN_ServiceHeader.setRqHeader(var_019_ServiceHeader_rqHeader);

		f_IN_ServiceHeader.setChecksum(Utils.getSTRINGValue(""));
	}
	private ArrayList<String> udfBoNames = new ArrayList<String>();
	private HashMap udfStateData = new HashMap();

	private bf.com.misys.eqf.types.header.ServiceRsHeader f_OUT_OutputServiceHeader = new bf.com.misys.eqf.types.header.ServiceRsHeader();
	{
		bf.com.misys.eqf.types.header.EnquiryRs var_020_OutputServiceHeader_enquiryRs = new bf.com.misys.eqf.types.header.EnquiryRs();

		var_020_OutputServiceHeader_enquiryRs.setTotalPages(Utils.getINTEGERValue(""));
		var_020_OutputServiceHeader_enquiryRs.setPagingInformation(Utils.getSTRINGValue(""));
		f_OUT_OutputServiceHeader.setEnquiryRs(var_020_OutputServiceHeader_enquiryRs);

		bf.com.misys.eqf.types.header.UiControlRs var_020_OutputServiceHeader_uiControlRs = new bf.com.misys.eqf.types.header.UiControlRs();

		var_020_OutputServiceHeader_uiControlRs.setNextScrn(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_uiControlRs.setNextProcess(Utils.getINTEGERValue(""));
		f_OUT_OutputServiceHeader.setUiControlRs(var_020_OutputServiceHeader_uiControlRs);

		bf.com.misys.eqf.types.header.CrmMessages var_020_OutputServiceHeader_crmMessages = new bf.com.misys.eqf.types.header.CrmMessages();

		bf.com.misys.eqf.types.header.CrmMessage var_020_OutputServiceHeader_crmMessages_crmMessage = new bf.com.misys.eqf.types.header.CrmMessage();

		var_020_OutputServiceHeader_crmMessages_crmMessage.setCustomerName(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage.setRiskAmount(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage.setLimitCategory(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage.setAvailableAmount(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage.setCountryCode(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage.setEarliestExpiryDate(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage.setCustomerGroup(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage.setCurrency(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage.setLimitAmount(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.EqMessage var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage = new bf.com.misys.eqf.types.header.EqMessage();

		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage.setOverridden(Utils.getBOOLEANValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage.setFirstLevelText(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.BranchLimitParameters var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_branchLimit = new bf.com.misys.eqf.types.header.BranchLimitParameters();

		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_branchLimit.setLocalCurrencyAmount(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_branchLimit.setDebitCredit(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.BranchKeys var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_branchLimit_affectedBranch = new bf.com.misys.eqf.types.header.BranchKeys();

		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_branchLimit_affectedBranch.setBranchCode(Utils
						.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_branchLimit_affectedBranch.setBranchMnemonic(Utils
						.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_branchLimit
						.setAffectedBranch(var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_branchLimit_affectedBranch);

		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage
						.setBranchLimit(var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_branchLimit);

		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage.setEqMessageParameter3(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage.setEqMessageParameter2(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage.setEqMessageParameter1(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage.setEqMessageSeverity(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage.setFormattedMessage(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.ServiceStack var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_serviceStack = new bf.com.misys.eqf.types.header.ServiceStack();
		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_serviceStack.addServiceStackEntry(0, Utils.getSTRINGValue(""));

		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage
						.setServiceStack(var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_serviceStack);

		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage.setEqMessageId(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.FieldLocation var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_fieldLocation = new bf.com.misys.eqf.types.header.FieldLocation();

		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_fieldLocation.setSetId(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_fieldLocation.addFieldId(0, Utils.getSTRINGValue(""));

		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_fieldLocation.setRowSequence(Utils.getINTEGERValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_fieldLocation.setFieldSet(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage
						.setFieldLocation(var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage_fieldLocation);

		var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage.setSecondLevelText(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages_crmMessage
						.setEqMessage(var_020_OutputServiceHeader_crmMessages_crmMessage_eqMessage);

		var_020_OutputServiceHeader_crmMessages_crmMessage.setCustomerLocation(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_crmMessages.addCrmMessage(0, var_020_OutputServiceHeader_crmMessages_crmMessage);

		f_OUT_OutputServiceHeader.setCrmMessages(var_020_OutputServiceHeader_crmMessages);

		bf.com.misys.eqf.types.header.RsHeader var_020_OutputServiceHeader_rsHeader = new bf.com.misys.eqf.types.header.RsHeader();

		var_020_OutputServiceHeader_rsHeader.setVersion(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.MessageStatus var_020_OutputServiceHeader_rsHeader_status = new bf.com.misys.eqf.types.header.MessageStatus();

		var_020_OutputServiceHeader_rsHeader_status.setOverallStatus(CommonConstants.EMPTY_STRING);
		bf.com.misys.eqf.types.header.EqMessage var_020_OutputServiceHeader_rsHeader_status_eqMessages = new bf.com.misys.eqf.types.header.EqMessage();

		var_020_OutputServiceHeader_rsHeader_status_eqMessages.setOverridden(Utils.getBOOLEANValue(""));
		var_020_OutputServiceHeader_rsHeader_status_eqMessages.setFirstLevelText(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.BranchLimitParameters var_020_OutputServiceHeader_rsHeader_status_eqMessages_branchLimit = new bf.com.misys.eqf.types.header.BranchLimitParameters();

		var_020_OutputServiceHeader_rsHeader_status_eqMessages_branchLimit.setLocalCurrencyAmount(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_rsHeader_status_eqMessages_branchLimit.setDebitCredit(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.BranchKeys var_020_OutputServiceHeader_rsHeader_status_eqMessages_branchLimit_affectedBranch = new bf.com.misys.eqf.types.header.BranchKeys();

		var_020_OutputServiceHeader_rsHeader_status_eqMessages_branchLimit_affectedBranch.setBranchCode(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_rsHeader_status_eqMessages_branchLimit_affectedBranch.setBranchMnemonic(Utils
						.getSTRINGValue(""));
		var_020_OutputServiceHeader_rsHeader_status_eqMessages_branchLimit
						.setAffectedBranch(var_020_OutputServiceHeader_rsHeader_status_eqMessages_branchLimit_affectedBranch);

		var_020_OutputServiceHeader_rsHeader_status_eqMessages
						.setBranchLimit(var_020_OutputServiceHeader_rsHeader_status_eqMessages_branchLimit);

		var_020_OutputServiceHeader_rsHeader_status_eqMessages.setEqMessageParameter3(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_rsHeader_status_eqMessages.setEqMessageParameter2(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_rsHeader_status_eqMessages.setEqMessageParameter1(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_rsHeader_status_eqMessages.setEqMessageSeverity(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_rsHeader_status_eqMessages.setFormattedMessage(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.ServiceStack var_020_OutputServiceHeader_rsHeader_status_eqMessages_serviceStack = new bf.com.misys.eqf.types.header.ServiceStack();
		var_020_OutputServiceHeader_rsHeader_status_eqMessages_serviceStack.addServiceStackEntry(0, Utils.getSTRINGValue(""));

		var_020_OutputServiceHeader_rsHeader_status_eqMessages
						.setServiceStack(var_020_OutputServiceHeader_rsHeader_status_eqMessages_serviceStack);

		var_020_OutputServiceHeader_rsHeader_status_eqMessages.setEqMessageId(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.FieldLocation var_020_OutputServiceHeader_rsHeader_status_eqMessages_fieldLocation = new bf.com.misys.eqf.types.header.FieldLocation();

		var_020_OutputServiceHeader_rsHeader_status_eqMessages_fieldLocation.setSetId(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_rsHeader_status_eqMessages_fieldLocation.addFieldId(0, Utils.getSTRINGValue(""));

		var_020_OutputServiceHeader_rsHeader_status_eqMessages_fieldLocation.setRowSequence(Utils.getINTEGERValue(""));
		var_020_OutputServiceHeader_rsHeader_status_eqMessages_fieldLocation.setFieldSet(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_rsHeader_status_eqMessages
						.setFieldLocation(var_020_OutputServiceHeader_rsHeader_status_eqMessages_fieldLocation);

		var_020_OutputServiceHeader_rsHeader_status_eqMessages.setSecondLevelText(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_rsHeader_status.addEqMessages(0, var_020_OutputServiceHeader_rsHeader_status_eqMessages);

		var_020_OutputServiceHeader_rsHeader.setStatus(var_020_OutputServiceHeader_rsHeader_status);

		var_020_OutputServiceHeader_rsHeader.setUserExtension(Utils.getJAVA_OBJECTValue(""));
		var_020_OutputServiceHeader_rsHeader.setBuid(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_rsHeader.setCorrelationId(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_rsHeader.setSessionId(Utils.getSTRINGValue(""));
		f_OUT_OutputServiceHeader.setRsHeader(var_020_OutputServiceHeader_rsHeader);

		bf.com.misys.eqf.types.header.ExtensionDataRs var_020_OutputServiceHeader_userExtensionData = new bf.com.misys.eqf.types.header.ExtensionDataRs();

		var_020_OutputServiceHeader_userExtensionData.setVersion(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_userExtensionData.setUserExtension(Utils.getJAVA_OBJECTValue(""));
		var_020_OutputServiceHeader_userExtensionData.setSecondaryServiceExtension(Utils.getJAVA_OBJECTValue(""));
		f_OUT_OutputServiceHeader.setUserExtensionData(var_020_OutputServiceHeader_userExtensionData);

		bf.com.misys.eqf.types.header.ExtensionDataRs var_020_OutputServiceHeader_misysExtensionData = new bf.com.misys.eqf.types.header.ExtensionDataRs();

		var_020_OutputServiceHeader_misysExtensionData.setVersion(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_misysExtensionData.setUserExtension(Utils.getJAVA_OBJECTValue(""));
		var_020_OutputServiceHeader_misysExtensionData.setSecondaryServiceExtension(Utils.getJAVA_OBJECTValue(""));
		f_OUT_OutputServiceHeader.setMisysExtensionData(var_020_OutputServiceHeader_misysExtensionData);

		f_OUT_OutputServiceHeader.setChecksum(Utils.getSTRINGValue(""));
		bf.com.misys.eqf.types.header.JournalKey var_020_OutputServiceHeader_journalKey = new bf.com.misys.eqf.types.header.JournalKey();

		var_020_OutputServiceHeader_journalKey.setTransactionType(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_journalKey.setDayInMonth(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_journalKey.setProgramRoot(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_journalKey.setTime(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_journalKey.setSequence(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_journalKey.setWorkstationId(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_journalKey.setRecoveryStatus(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_journalKey.setJobNumber(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_journalKey.setCcSequence(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_journalKey.setCreateDate(Utils.getSTRINGValue(""));
		var_020_OutputServiceHeader_journalKey.setCcLinkTime(Utils.getSTRINGValue(""));
		f_OUT_OutputServiceHeader.setJournalKey(var_020_OutputServiceHeader_journalKey);
	}
	private Object f_OUT_OutputServiceData = new String();

	public void process(BankFusionEnvironment env) throws BankFusionException
	{
	}

	public Object getF_IN_ServiceData()
	{
		return f_IN_ServiceData;
	}

	public void setF_IN_ServiceData(Object param)
	{
		f_IN_ServiceData = param;
	}

	public bf.com.misys.eqf.types.header.ServiceRqHeader getF_IN_ServiceHeader()
	{
		return f_IN_ServiceHeader;
	}

	public void setF_IN_ServiceHeader(bf.com.misys.eqf.types.header.ServiceRqHeader param)
	{
		f_IN_ServiceHeader = param;
	}

	public Map getInDataMap()
	{
		Map dataInMap = new HashMap();
		dataInMap.put(IN_ServiceData, f_IN_ServiceData);
		dataInMap.put(IN_ServiceHeader, f_IN_ServiceHeader);
		return dataInMap;
	}

	public bf.com.misys.eqf.types.header.ServiceRsHeader getF_OUT_OutputServiceHeader()
	{
		return f_OUT_OutputServiceHeader;
	}

	public void setF_OUT_OutputServiceHeader(bf.com.misys.eqf.types.header.ServiceRsHeader param)
	{
		f_OUT_OutputServiceHeader = param;
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

	public Object getF_OUT_OutputServiceData()
	{
		return f_OUT_OutputServiceData;
	}

	public void setF_OUT_OutputServiceData(Object param)
	{
		f_OUT_OutputServiceData = param;
	}

	public Map getOutDataMap()
	{
		Map dataOutMap = new HashMap();
		dataOutMap.put(OUT_OutputServiceHeader, f_OUT_OutputServiceHeader);
		dataOutMap.put(CommonConstants.ACTIVITYSTEP_UDF_BONAMES, udfBoNames);
		dataOutMap.put(CommonConstants.ACTIVITYSTEP_UDF_STATE_DATA, udfStateData);
		dataOutMap.put(OUT_OutputServiceData, f_OUT_OutputServiceData);
		return dataOutMap;
	}
}
