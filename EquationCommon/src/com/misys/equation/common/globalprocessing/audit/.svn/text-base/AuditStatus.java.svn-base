package com.misys.equation.common.globalprocessing.audit;

/**
 * Audit status types used primarily in the "Audit Header" record but also reused by the "Applicator Data Record".
 * 
 * @author berzosa
 */
public enum AuditStatus
{
	AUTO(" "), // "Blank=To be automatically applied
	MANUAL("M"), // To be manually applied
	APPLIED("A"), // Applied successfully
	FAILED("F"), // Failed
	WARNING("W"), // Warning
	CCSID_ERRORS("C"), // Applied with CCSID errors

	SET_TO_AUTO("S"), // Set to Auto
	DELETED("D"), // Deleted
	RETRYING("R"), // Retrying
	ACKED("K"), // Acknowledged
	RESET("X"); // Reset

	private final String value;

	AuditStatus(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}
}
