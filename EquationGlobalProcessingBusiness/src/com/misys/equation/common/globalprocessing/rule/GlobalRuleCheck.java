package com.misys.equation.common.globalprocessing.rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IAPVRecordDao;
import com.misys.equation.common.dao.ICLCRecordDao;
import com.misys.equation.common.dao.ICLDRecordDaoImp;
import com.misys.equation.common.dao.IGFRecordDao;
import com.misys.equation.common.dao.IGPFRecordDao;
import com.misys.equation.common.dao.IGPRRecordDao;
import com.misys.equation.common.dao.beans.APJRecordDataModel;
import com.misys.equation.common.dao.beans.APVRecordDataModel;
import com.misys.equation.common.dao.beans.CLCRecordDataModel;
import com.misys.equation.common.dao.beans.CLDRecordDataModel;
import com.misys.equation.common.dao.beans.GFRecordDataModel;
import com.misys.equation.common.dao.beans.GPFRecordDataModel;
import com.misys.equation.common.dao.beans.GPRRecordDataModel;
import com.misys.equation.common.dao.beans.GYRecordDataModel;
import com.misys.equation.common.globalprocessing.audit.APVCacheUtil;
import com.misys.equation.common.globalprocessing.audit.GlobalAuditUtils;
import com.misys.equation.common.globalprocessing.audit.PropData;
import com.misys.equation.common.globalprocessing.audit.SystemUnit;
import com.misys.equation.common.globalprocessing.audit.UnitAuditUtils;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;

public class GlobalRuleCheck
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalRuleCheck.java 9962 2010-11-18 17:31:39Z MACDONP1 $";

	private EquationStandardSession session = null;

	/** Logger Instance */
	private static final EquationLogger LOG = new EquationLogger(GlobalRuleCheck.class);

	/* Cache APV File names for quick-lookup */
	Collection<String> apvFilesCache;

	/**
	 * Create GlobalRuleCheck
	 * 
	 * @param unitSession
	 */
	public GlobalRuleCheck(EquationStandardSession unitSession)
	{
		session = unitSession;
		apvFilesCache = initialise();
	}

	/**
	 * This checks that the API is in the master set of monitored API’s and also verifies that a monitor enabled rule exists on the
	 * GPRPF for the specified system and unit for the supplied API.
	 * 
	 * @param programRoot
	 * @param fromSystem
	 * @param fromUnit
	 * @param unit
	 * @return
	 * @throws EQException
	 */
	public boolean quickCheckMonitorRule(String programRoot, String fromSystem, String fromUnit) throws EQException
	{
		String apiName = getApiName(programRoot);
		if (!apvFilesCache.contains(apiName))
		{
			return false;
		}

		return true;
	}

	public Boolean quickCheckRule(String apiReference) throws EQException
	{
		// use RulesCacheUtl to determine how many rules are linked to this export type
		final int result = RulesCacheUtil.getInstance().getRulesForExportType(apiReference);
		if (result > 0)
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Rules were found for API Reference: " + apiReference);
			}
			return true;
		}
		else
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Rules were NOT found for API Reference: " + apiReference);
			}
			return false; // no applicable rules found
		}
	}

	/**
	 * This verifies that a monitor enabled rule exists on the GPRPF for the specified system/unit and for the supplied API.
	 * <p>
	 * Returns a Map of valid rule IDs.
	 * 
	 * @param GYrecordset
	 * @param fromSystem
	 * @param fromUnit
	 * @param apv
	 * @return
	 * @throws EQException
	 */
	public Map<String, GPRRecordDataModel> checkMonitorRule(GYRecordDataModel gyRecord, String fromSystem, String fromUnit,
					APVRecordDataModel apv) throws EQException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Executing checkMonitorRule...");
		}

		String apiName = getApiName(gyRecord.getProgramRoot());

		// retrieve rules applicable for this system/unit
		Map<String, GPRRecordDataModel> monitorRules = getApplicableRules(fromSystem, fromUnit, apv.getApiReference());

		// Check if the condition specified ont the rule returns a valid GZxxxx record
		Iterator<Entry<String, GPRRecordDataModel>> iterator = monitorRules.entrySet().iterator();
		while (iterator.hasNext())
		{
			final Entry<String, GPRRecordDataModel> entry = iterator.next();
			final GPRRecordDataModel gprRecord = entry.getValue();

			final String condition = gprRecord.getConditions().trim();
			if (StringUtils.isNotEmpty(condition))
			{
				// validate GZRecord against monitor rule conditions

				String query = createGZQuery(gyRecord, apv, condition);
				int result = UnitAuditUtils.getSQLCountValue(session, query.toString());
				if (result == 0)
				{
					if (LOG.isDebugEnabled())
					{
						LOG.debug("No " + apiName + " record found for " + gprRecord.getExportType() + "-"
										+ gprRecord.getDescription() + ". Removing rule from hash map");
					}

					// rule's condition does not match!
					iterator.remove();
					continue;
				}
			}

			// process GYJTT
			final String journalTransType = gyRecord.getJournalTransType();

			// If GYJTT is A the GPRMOA must be Y.
			if ("A".equalsIgnoreCase(journalTransType) && !"Y".equalsIgnoreCase(gprRecord.getMonitorAdditions()))
			{
				if (LOG.isDebugEnabled())
				{
					LOG.debug("GPRMOA should be set to 'Y' since Journal Transaction Type is 'A'");
					LOG.debug("Removing rule " + gprRecord.getIdentifier() + " - " + gprRecord.getDescription());
				}

				// we're not monitoring "Add" transactions
				iterator.remove();
				continue;

			}

			// If GYJTT is M the GPRMOM must be Y.
			if ("M".equalsIgnoreCase(journalTransType) && !"Y".equalsIgnoreCase(gprRecord.getMonitorMaintenance()))
			{
				if (LOG.isDebugEnabled())
				{
					LOG.debug("GPRMOM should be set to 'Y' since Journal Transaction Type is 'M'");
					LOG.debug("Removing rule" + gprRecord.getIdentifier() + "-" + gprRecord.getDescription());

				}

				// we're not monitoring "Maintain" transactions
				iterator.remove();
				continue;
			}

			// If GYJTT is D the GPRMOD must be Y.
			if ("D".equalsIgnoreCase(journalTransType) && !"Y".equalsIgnoreCase(gprRecord.getMonitorDeletions()))
			{
				if (LOG.isDebugEnabled())
				{
					LOG.debug("GPRMOD should be set to 'Y' since Journal Transaction Type is 'D'");
					LOG.debug("Removing rule" + gprRecord.getIdentifier() + "-" + gprRecord.getDescription());
				}

				// we're not monitoring "Delete" transactions
				iterator.remove();
				continue;
			}
		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug("Returning all valid monitor rules... ");
		}

		return monitorRules;
	}

	/**
	 * This verifies that a monitor rule applies to a destination unit. Linked customers have more complex rules.
	 * 
	 * The Propagation Data PropData’s helper class should contain the ruleid, target unit, target system, auto or manual apply
	 * flag, exclude field hashmap, include field hashmap, customer number and global customer number.
	 * 
	 * @param ruleId
	 * @param gyRecord
	 * @param GZrecordset
	 * @param fromSystem
	 * @param fromUnit
	 * @return - returns a hashmap of PropData helper objects the rule will apply to; contains multiple target units
	 * @throws EQException
	 */
	public HashMap<String, PropData> checkMonitorRulePropagateUnits(String ruleId, GYRecordDataModel gyRecord, ResultSet gzRecord,
					String fromSystem, String fromUnit) throws EQException
	{
		// Create a hashmap of all systems/units (excluding the from system/unit) from the GPXPF table.
		Set<SystemUnit> systemUnits = GlobalAuditUtils.getNonOriginatingSystemUnits(session, ruleId, fromSystem, fromUnit);
		// Set of all terminating system-units from GPUPF
		Set<SystemUnit> gpuUnits = GlobalAuditUtils.getGPUPFUnits(session, ruleId, "T");
		// Get Rule
		GPRRecordDataModel rule = getRule(ruleId);
		// Fields listed on GPFPF
		Set<String> markedFields = getMarkedFields(rule.getIdentifier());
		// (all fields from APJPF) - (marked Fields)
		Set<String> unmarkedFields = getUnmarkedFields(rule.getExportType(), markedFields);
		// if GPRINC=Y --> exclude fields = marked fields
		Set<String> excludeFields = "Y".equals(rule.getIncludeAllFields()) ? markedFields : unmarkedFields;
		Set<String> includeFields = "Y".equals(rule.getIncludeAllFields()) ? unmarkedFields : markedFields;

		// For linked Customers only
		if ("Y".equalsIgnoreCase(rule.getLinkedCustomers()) && "CU".equalsIgnoreCase(rule.getExportGroup()))
		{
			// // by default exclude propagation for fields GZCUS and GZCLS
			// excludeFields.add("GZCUS");
			// excludeFields.add("GZCLC");
			//
			// // safety check
			// includeFields.remove("GZCUS");
			// includeFields.remove("GZCLC");

			// If GPREGRP=’CU’ ...retrieve customer no.
			GFRecordDataModel customer = null;
			try
			{
				String cusMnemonic = gzRecord.getString("GZCUS");
				String cusLocation = gzRecord.getString("GZCLC");
				customer = getCustomer(cusMnemonic, cusLocation);

				if (customer == null)
				{
					// can't generate any data!
					LOG.warn(String.format("Customer not found in GFPF, skipping! [%s]/[%s]", cusMnemonic, cusLocation));
					return new HashMap<String, PropData>();
				}
			}
			catch (SQLException e)
			{
				// can't generate any data!
				LOG.error("Could not retrieve customer", e);
				return new HashMap<String, PropData>();
			}

			return getCustomerPropData(fromSystem, fromUnit, customer, gyRecord, gpuUnits, rule, excludeFields, includeFields);
		}
		else
		{
			// For Normal Customers & Data Rules only
			return getNormalPropData(systemUnits, gpuUnits, rule, excludeFields, includeFields);
		}
	}
	private Set<String> getUnmarkedFields(String apvRef, Set<String> markedFields) throws EQException
	{
		// retrieve all fields
		Set<String> fieldSet = new HashSet<String>();
		List<APJRecordDataModel> fields = APVCacheUtil.getInstance(session).findAPIFields(apvRef);
		for (APJRecordDataModel field : fields)
		{
			fieldSet.add(field.getApiFieldName());
		}

		// removed marked fields
		fieldSet.removeAll(markedFields);
		return fieldSet;
	}

	private HashMap<String, PropData> getNormalPropData(Set<SystemUnit> systemUnits, Set<SystemUnit> gpuUnits,
					GPRRecordDataModel rule, Set<String> excludeFields, Set<String> includeFields)
	{
		// First determine the terminating Units
		Set<SystemUnit> terminatingSystemUnits = new HashSet<SystemUnit>();
		HashMap<String, PropData> propDataHashMap = new HashMap<String, PropData>();

		if ("Y".equalsIgnoreCase(rule.getPropagateToAllUnits()))
		{
			// terminating units == systemUnits - gpuUnits
			terminatingSystemUnits.addAll(systemUnits);
			terminatingSystemUnits.removeAll(gpuUnits);
		}
		else
		{
			terminatingSystemUnits.addAll(gpuUnits);
		}

		for (SystemUnit targetUnit : terminatingSystemUnits)
		{
			PropData propData = new PropData();
			propData.setRuleId(rule.getIdentifier());
			propData.setTargetSystem(targetUnit.getSystem());
			propData.setTargetUnit(targetUnit.getUnit());
			propData.setApplyFlag("A");
			propData.setExcludeFields(excludeFields);
			propData.setIncludeFields(includeFields);

			// add condition
			propData.setCondition(rule.getConditions());

			// add rule if include all fields
			propData.setIncludeAllFields(rule.getIncludeAllFields());

			propDataHashMap.put(targetUnit.toString(), propData);
		}
		return propDataHashMap;
	}

	private HashMap<String, PropData> getCustomerPropData(String fromSystem, String fromUnit, GFRecordDataModel customer,
					GYRecordDataModel gyRecord, Set<SystemUnit> gpuUnits, GPRRecordDataModel rule, Set<String> excludeFields,
					Set<String> includeFields)
	{
		CLDRecordDataModel linkedCustomer = getLinkedCustomer(fromSystem, fromUnit, customer.getCustomerNumber());
		HashMap<String, PropData> propDataHashMap = new HashMap<String, PropData>();
		if (linkedCustomer == null)
		{
			// no linked customer
			LOG.warn(String.format("No linked customer found for %s on %s/%s", customer.getCustomerNumber(), fromSystem, fromUnit));
			return new HashMap<String, PropData>();
		}
		else
		{

			// Create the hashmap of PropData's to propagate to the 'other customers'
			List<CLDRecordDataModel> otherLinkedCustomers = getOtherLinkedCustomers(linkedCustomer.getGlobalCustomerId(),
							linkedCustomer.getSequenceNumber());

			// create Hashmap of PropData
			for (CLDRecordDataModel targetCustomer : otherLinkedCustomers)
			{
				// get syncRecord
				CLCRecordDataModel syncRecord = getSyncRecord(targetCustomer.getSyncID());

				// check that customer API GYFRO matches API Flags on CLCPF synch record.. then get the synch flag
				String synchFlagValue = getSynchFlag(targetCustomer.isMasterFlag(), gyRecord.getProgramRoot(), syncRecord);
				if ("N".equalsIgnoreCase(synchFlagValue))
				{
					continue;
				}

				SystemUnit targetSystemUnit = new SystemUnit(targetCustomer.getSystemName(), targetCustomer.getCustomerOwningUnit());

				if ("Y".equalsIgnoreCase(rule.getPropagateToAllUnits()) && gpuUnits.contains(targetSystemUnit))
				{
					// do not propagate to units which are labelled as excluded
					continue;
				}

				else if ("N".equalsIgnoreCase(rule.getPropagateToAllUnits()) && !gpuUnits.contains(targetSystemUnit))
				{
					// do not propgate to units which are NOT included
					continue;
				}

				PropData propData = new PropData();
				propData.setRuleId(rule.getIdentifier());
				propData.setTargetSystem(targetCustomer.getSystemName());
				propData.setTargetUnit(targetCustomer.getCustomerOwningUnit());
				propData.setApplyFlag("A");
				propData.setExcludeFields(excludeFields);
				propData.setIncludeFields(includeFields);
				propData.setGlobalCustomerNumber(targetCustomer.getGlobalCustomerId());
				propData.setCustomerNumber(targetCustomer.getCustomerNumber());
				propData.setCondition(rule.getConditions());

				// add rule if include all fields
				propData.setIncludeAllFields(rule.getIncludeAllFields());

				// add to hash map
				propDataHashMap.put(targetSystemUnit + "-" + targetCustomer.getCustomerNumber(), propData);
			}

		}
		return propDataHashMap;
	}

	/**
	 * Retreives other linked customers define in the Customer Linkage table.
	 * 
	 * @param globalCustomerId
	 * @param seqId
	 * @return
	 */
	private List<CLDRecordDataModel> getOtherLinkedCustomers(String globalCustomerId, int seqId)
	{
		// Select * from CLDPF where CLDGCID = global customer id & CLDSEQ <> found sequence number
		String whereClause = "CLDGCID='" + globalCustomerId + "' AND CLDSEQ <> " + seqId;
		final ICLDRecordDaoImp dao = new DaoFactory().getCLDRecordDao(session, new CLDRecordDataModel());
		return coerce(dao.getRecordBy(whereClause));
	}

	private String getSynchFlag(boolean masterFlag, String gyfro, CLCRecordDataModel record)
	{
		final String programRoot3Chars = StringUtils.substring(gyfro, 0, 3);
		if (masterFlag)
		{
			if ("G01".equalsIgnoreCase(programRoot3Chars))
			{
				// synchFlag = "CLCMBD";
				return record.getSynchMasterBasicDetails();
			}
			else if ("G09".equalsIgnoreCase(programRoot3Chars))
			{
				// synchFlag = "CLCMOD";
				return record.getSynchMasterOtherDetails();
			}
			else if ("G17".equalsIgnoreCase(programRoot3Chars))
			{
				// synchFlag = "CLCMFF";
				return record.getSynchMasterFreeFormatDetails();
			}
			else if ("H60".equalsIgnoreCase(programRoot3Chars))
			{
				// synchFlag = "CLCMAD";
				return record.getSynchMasterAddressDetails();
			}
			else if ("V30".equalsIgnoreCase(programRoot3Chars))
			{
				// synchFlag = "CLCMED";
				return record.getSynchMasterExtendedDetails();
			}
			else if ("H39".equalsIgnoreCase(programRoot3Chars))
			{
				// synchFlag = "CLCMAC";
				return record.getSynchMasterACDetails();
			}
		}
		else
		{
			if ("G01".equalsIgnoreCase(programRoot3Chars))
			{
				// synchFlag = "CLCNMBD";
				return record.getSynchNonMasterBasicDetails();
			}
			else if ("G09".equalsIgnoreCase(programRoot3Chars))
			{
				// synchFlag = "CLCNMOD";
				return record.getSynchNonMasterOtherDetails();
			}
			else if ("G17".equalsIgnoreCase(programRoot3Chars))
			{
				// synchFlag = "CLCNMFF";
				return record.getSynchNonMasterFreeFormatDetails();
			}
			else if ("H60".equalsIgnoreCase(programRoot3Chars))
			{
				// synchFlag = "CLCNMAD";
				return record.getSynchNonMasterAddressDetails();
			}
			else if ("V30".equalsIgnoreCase(programRoot3Chars))
			{
				// synchFlag = "CLCNMED";
				return record.getSynchNonMasterExtendedDetails();
			}
			else if ("H39".equalsIgnoreCase(programRoot3Chars))
			{
				// synchFlag = "CLCNMAC";
				return record.getSynchNonMasterACDetails();
			}
		}

		// not found .. default to 'N'
		return "N";
	}

	private GFRecordDataModel getCustomer(String cusMnemonic, String cusLocation)
	{
		String whereClause = "GFCUS='" + cusMnemonic + "' AND  GFCLC='" + cusLocation + "'";
		final IGFRecordDao dao = new DaoFactory().getGFRecordDao(session, new GFRecordDataModel());
		List<GFRecordDataModel> records = coerce(dao.getRecordBy(whereClause));
		if (records.size() > 0)
		{
			return records.get(0);
		}
		else
		{
			return null;
		}
	}

	private CLCRecordDataModel getSyncRecord(String syncID)
	{
		String whereClause = "CLCID='" + syncID + "'";
		final ICLCRecordDao dao = new DaoFactory().getCLCRecordDao(session, new CLCRecordDataModel());
		List<CLCRecordDataModel> records = coerce(dao.getRecordBy(whereClause));

		if (records.size() > 0)
		{
			return records.get(0);
		}
		else
		{
			return null;
		}
	}

	// Utilities

	private List<GPFRecordDataModel> getFields(String whereClause)
	{
		final IGPFRecordDao dao = new DaoFactory().getGPFDao(session, new GPFRecordDataModel());
		return coerce(dao.getRecordBy(whereClause));
	}

	private GPRRecordDataModel getRule(String ruleId)
	{
		GPRRecordDataModel rule = new GPRRecordDataModel();

		String whereClause = "GPRRID= '" + ruleId + "'";
		final IGPRRecordDao dao = new DaoFactory().getGPRDao(session, new GPRRecordDataModel());
		List<GPRRecordDataModel> gprRecords = coerce(dao.getRecordBy(whereClause));

		if (gprRecords.size() > 0)
		{
			rule = gprRecords.get(0);
		}

		return rule;
	}

	/**
	 * Converts a generic list from one type to another (assuming the conversion is valid).
	 * 
	 * @param genericList
	 *            A generic List
	 * @return A generics-enabled list of type List&lg;T&gt;
	 */
	@SuppressWarnings("unchecked")
	protected static <T> List<T> coerce(List genericList)
	{
		return genericList;
	}

	/**
	 * Retrieve fields listed on GPFPF based on the ruleId
	 * 
	 * @param ruleid
	 * @return
	 */
	private Set<String> getMarkedFields(String ruleid)
	{
		Set<String> markedFields = new HashSet<String>();

		// Retrieve 'marked fields' to be included/excluded
		String ruleCondition = "GPFRID='" + ruleid + "'";
		List<GPFRecordDataModel> gpfRecords = getFields(ruleCondition);

		// Save include field as a SET ...

		for (GPFRecordDataModel field : gpfRecords)
		{
			markedFields.add(field.getField());
		}

		return markedFields;
	}

	private CLDRecordDataModel getLinkedCustomer(String fromSystem, String fromUnit, String customerNumber)
	{
		String whereClause = "CLDSYS='" + fromSystem + "' AND CLDCOUN='" + fromUnit + "' AND CLDCNO= '" + customerNumber + "'";
		final ICLDRecordDaoImp dao = new DaoFactory().getCLDRecordDao(session, new CLDRecordDataModel());
		List<CLDRecordDataModel> records = coerce(dao.getRecordBy(whereClause));
		if (records.size() > 0)
		{
			return records.get(0);
		}

		// no linked customer
		return null;
	}

	/**
	 * Returns all APV file names defined in APVPF.
	 * 
	 * @return All APV file names defined in APVPF.
	 */
	private Set<String> initialise()
	{
		final Set<String> apvApiFiles = new HashSet<String>();
		final IAPVRecordDao dao = new DaoFactory().getAPVDao(session, new APVRecordDataModel());
		List<APVRecordDataModel> apvRecords = coerce(dao.getRecords());
		for (APVRecordDataModel record : apvRecords)
		{
			apvApiFiles.add(record.getApiFileName());
		}

		return apvApiFiles;
	}

	public static String getApiName(String gyfro)
	{
		if ("H48N".equals(gyfro))
		{
			return "GZVF1P";
		}
		else
		{
			return "GZ" + gyfro.substring(0, 3) + "1";
		}
	}

	private LinkedHashMap<String, GPRRecordDataModel> convertToRuleHashMap(List<GPRRecordDataModel> rules)
	{
		LinkedHashMap<String, GPRRecordDataModel> monitorRules = new LinkedHashMap<String, GPRRecordDataModel>();
		for (GPRRecordDataModel rule : rules)
		{
			monitorRules.put(rule.getIdentifier(), rule);
		}
		return monitorRules;
	}

	private LinkedHashMap<String, GPRRecordDataModel> getApplicableRules(String system, String unit, String apiName)
	{
		StringBuffer whereClause = new StringBuffer();
		whereClause.append(" GPRTYP='M'");
		whereClause.append(" AND GPRMON='Y'");
		whereClause.append(" AND GPRETYP ='" + apiName + "'");
		whereClause.append(" AND (");

		// case1 - GPRMALL='Y' , system-unit should NOT be in GPUPF
		whereClause.append(" (GPRMALL='Y'  AND GPRRID NOT IN");
		whereClause.append(" (SELECT DISTINCT GPURID FROM GPUPF WHERE GPUTYP='O'");
		whereClause.append(" AND GPUSRV='" + system + "' AND GPUUNT='" + unit + "') )");

		// take union
		whereClause.append(" OR ");

		// case2 - GPRMALL='N' , system-unit should be in GPUPF
		whereClause.append(" (GPRMALL='N' AND  GPRRID IN");
		whereClause.append(" (SELECT DISTINCT GPURID FROM GPUPF WHERE GPUTYP='O'");
		whereClause.append(" AND GPUSRV='" + system + "' AND GPUUNT='" + unit + "') )");

		// end
		whereClause.append(" ) ");

		List<GPRRecordDataModel> rules = GlobalAuditUtils.getGPRPFUnits(session, whereClause.toString());
		return convertToRuleHashMap(rules);
	}

	public static String createGZQuery(GYRecordDataModel gyRecord, APVRecordDataModel apv, String ruleCondition)
	{
		if ("H48N".equals(apv.getProgramRoot()))
		{
			return createH48NQuery(gyRecord, apv, apv.getConditions(), ruleCondition);
		}
		else
		{
			return createGZNormalQuery(gyRecord, apv.getConditions(), ruleCondition);
		}
	}

	private static String createH48NQuery(GYRecordDataModel gyRecord, APVRecordDataModel apv, String apvCondition,
					String ruleCondition)
	{
		// check that GZVF1P has a record with the specified condition
		// include VFWSD, VFTIM, VFDIM, VFSEQ
		final StringBuilder query = new StringBuilder();
		query.append(" VFWSD = '" + SQLToolbox.replaceSingleQuote(gyRecord.getWorkstationId()) + "'");
		query.append(" AND VFDIM = " + gyRecord.getDayInMonth());
		query.append(" AND VFTIM = " + gyRecord.getTimeHhmmss());
		query.append(" AND VFSEQ = " + gyRecord.getSequenceNumber());
		if (StringUtils.isNotEmpty(apvCondition))
		{
			query.append(" AND ");
			query.append(apvCondition);
		}

		if (StringUtils.isNotEmpty(ruleCondition))
		{
			// append pseudo fields for this API (if any)
			final String pseudoFields = APVCacheUtil.getPseudoGZVF1PColumns(apv);
			if (StringUtils.isNotEmpty(pseudoFields))
			{
				query.insert(0, "SELECT COUNT(*) FROM (SELECT GZVF1P.*," + pseudoFields + " FROM GZVF1P WHERE ");
				query.append(") RULECHECK WHERE 1=1");
			}
			else
			{
				// we don't need the pseudo fields, so just do a regular "SELECT COUNT(*)" query
				query.insert(0, "SELECT COUNT(*) FROM GZVF1P WHERE ");
			}

			// if rule contains conditions, then we must include pseudo fields (i.e., fields based on VFDATA) as part of the query
			// so that rules can condition on them!
			query.append(" AND ");
			query.append(ruleCondition);
		}
		else
		{
			// we don't need the pseudo fields, so just do a regular "SELECT COUNT(*)" query
			query.insert(0, "SELECT COUNT(*) FROM GZVF1P WHERE ");
		}

		return query.toString();
	}

	private static String createGZNormalQuery(GYRecordDataModel gyRecord, String apvCondition, String ruleCondition)
	{
		String apiName = getApiName(gyRecord.getProgramRoot());
		// check that GZxxx has a record with the specified condition
		// include GZWSID, GZTIM, GZDIM, GZSEQ
		StringBuilder query = new StringBuilder();
		query.append("SELECT COUNT(*) FROM " + apiName + " WHERE ");
		query.append(" GZWSID = '" + SQLToolbox.replaceSingleQuote(gyRecord.getWorkstationId()) + "'");
		query.append(" AND GZDIM = " + gyRecord.getDayInMonth());
		query.append(" AND GZTIM = " + gyRecord.getTimeHhmmss());
		query.append(" AND GZSEQ = " + gyRecord.getSequenceNumber());
		if (StringUtils.isNotEmpty(apvCondition))
		{
			query.append(" AND ");
			query.append(apvCondition);
		}
		if (StringUtils.isNotEmpty(ruleCondition))
		{
			query.append(" AND ");
			query.append(ruleCondition);
		}

		return query.toString();
	}
}
