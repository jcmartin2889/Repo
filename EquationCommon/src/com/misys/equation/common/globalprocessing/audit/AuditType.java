package com.misys.equation.common.globalprocessing.audit;

/**
 * Audit Header is the main audit table and is used by Propagation, Applicator, Ad-hoc, Desktop, Mgmt Console, EOD propagation and
 * Audit Enquiry.
 * 
 * @author deroset
 * 
 */
public enum AuditType
{

	ADHOC("1"), PROPAGATION("2"), DESKTOPINPUT("3"), DESKTOPENQUIRY("4"), MGMCONSOLEFUNCTION("5"), MGMCONSOLEENQUIRY("6"), EODPROP(
					"7"), LOGON("8"), LOGOFF("9");

	private final String value;

	AuditType(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}

}