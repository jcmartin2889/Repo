package com.misys.equation.common.globalprocessing.audit;

import com.misys.equation.common.access.EquationStandardSession;

public class GlobalAuditPropagationHeader extends GlobalAuditEnquiryData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalAuditPropagationHeader.java 8116 2010-07-08 02:47:53Z aranetv1 $";
	/** */
	private static final long serialVersionUID = -69031822685600260L;

	public GlobalAuditPropagationHeader()
	{
		super(null);
	}

	public GlobalAuditPropagationHeader(EquationStandardSession session)
	{
		super(session);
	}

	public GlobalAuditPropagationHeader(EquationStandardSession session, AuditType auditType)
	{
		this(session);
		setValues(auditType);
	}
}
