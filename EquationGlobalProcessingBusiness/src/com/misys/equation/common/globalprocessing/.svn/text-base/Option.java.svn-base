package com.misys.equation.common.globalprocessing;

/**
 * Enumeration that defines all known relevant Option IDs used by the GlobalConsole and provides a {@link #getDescription()} method
 * for returning the option name (description of the option identifier).
 * 
 * @author berzosa
 */
public enum Option
{
	// Option identifiers
	LNK_CUST_SYNC_CODES("GSC", "SYNCH_CODE"), // "Linked Customer Synchronisation Codes"
	DEF_DATA_PROP_RULES("GDP", "RULE_ID"), // "Define Data Propagation Rules"
	DEF_DATA_PROP_SETS("GPS", "SET_ID"), // "Define Data Propagation Sets"
	AD_HOC_DATA_EXPORT("GAH", "RULE_ID"), // "Ad Hoc Data Export"
	GLOBAL_SYS_OPTS("GGO", "OPT_CODE"), // "Global System Options"
	UNIT_SYS_OPTS("GUO", "OPT_CODE"), // "Unit System Options"
	DATA_PROP_AUDIT_VIEWER("GAV", "GAUSEQ"), // Data Propagation Audit Viewer
	DB_COMPARISON_CONS("GCO", "KEY"), // "Database Comparison Console"
	MAJ_PROC_CHAR("KPC", "OPT_CODE"), // "Major Processing Characteristics"
	CONT_SYS_TAIL("KCS", "OPT_CODE"), // "Control System Tailoring"
	SYS_VAR("KSV", "OPT_CODE"), // "System Variables"
	MAJ_PROC_CHAR_ENQ("KP1", "OPT_CODE"), // "Major Processing Characteristics Enquiry"
	CONT_SYS_TAIL_ENQ("KC1", "OPT_CODE"), // "Control System Tailoring Enquiry"
	SYS_VAR_ENQ("KS1", "OPT_CODE"), // "System Variables Enquiry"
	DATAPROP_JOB_CONTROL("GJC", "OPT_CODE"), // "Data Propagation Job Control"
	DEFINE_USER_SPECIFIC_INFO("DUS", "OPT_CODE"), // Define User Specific Info
	DEFINE_USER_OPTION_AUTH("DUO", "OPT_CODE"); // Define User Option Authorization

	private final String id;
	private final String defaultRefType;
	private Option(String id, String defaultRefType)
	{
		this.id = id;
		this.defaultRefType = defaultRefType;
	}

	public String getId()
	{
		return id;
	}

	public String getDefaultRefType()
	{
		return defaultRefType;
	}
}
