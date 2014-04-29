package com.misys.equation.common.globalprocessing.audit;

public interface IGlobalAuditingManager
{
	/**
	 * This method will audit the login action.
	 */
	public void auditLogin();

	/**
	 * This method will audit the logoff action.
	 */
	public void auditLogoff();

}
