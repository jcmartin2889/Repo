package com.misys.equation.common.access.securitywrapper;

import java.sql.ResultSet;
import java.util.List;

import com.misys.equation.common.access.EquationStandardSession;

/**
 * This is the BranchSecurityWrapper which filters rows from the branch result sets. Note that the filter methods is implemented
 * generically in the super class SecurityWrapper.
 * 
 * @author camillen
 * 
 */
public class AccountSecurityWrapper extends SecurityWrapper
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AccountSecurityWrapper.java 8910 2010-08-26 15:25:20Z MACDONP1 $";
	private static final String PV_ID = "SSC99RW";

	public AccountSecurityWrapper(final EquationStandardSession session, final ResultSet rs, final List<String> securityFields)
	{
		super(session, rs, PV_ID, securityFields);
	}
}