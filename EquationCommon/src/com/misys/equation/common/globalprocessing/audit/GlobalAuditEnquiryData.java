package com.misys.equation.common.globalprocessing.audit;

import java.sql.Timestamp;
import java.util.Date;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.beans.GAURecordDataModel;

/**
 * @author deroset
 */
public class GlobalAuditEnquiryData extends GAURecordDataModel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalAuditEnquiryData.java 11293 2011-06-24 14:10:00Z hempensp $";

	/** Serial UID */
	private static final long serialVersionUID = 4527032712831382804L;

	private transient EquationStandardSession session;
	private AuditType auditType = null;

	public GlobalAuditEnquiryData(EquationStandardSession session)
	{
		this.session = session;

		setSequenceId(0);
		setSessionId("");
		setUser("");
		setSource("");
		setSourceUnit("");
		setSourceServer("");
		setProcessingDate(0);
		setUnitDefaultBranch("");
		setOptionId("");
		setReferenceDetailsType("");
		setReferenceDetails("");
		setStatus("");
		setAcknowledgedStatus("");
		setToUnit("");
		setToServer("");
		setPropDataSequenceNumber(0);
		setUnitDataSequenceNumber(0);
		setGlobalLayerStatus("");
	}

	public void setValues(AuditType auditType)
	{
		setAuditType(auditType);

		if (session.getSessionIdentifier().contains(":"))
		{
			setSessionId(session.getSessionIdentifier().split(":")[2]);
		}
		else
		{
			setSessionId(session.getSessionIdentifier());
		}
		setUser(session.getUserId());
		setSource(this.auditType.getValue());
		setSourceUnit(session.getUnitId());

		setSourceServer(session.getSystemId());
		setProcessingDate(session.getUnit().getProcessingDateCYYMMDD());
		setUnitDefaultBranch(session.getBranchId());
		setOptionId();
		setAuditTimestamp(new Timestamp(new Date().getTime()));
	}

	/**
	 * this method will set the option id.
	 */
	private void setOptionId()
	{
		String valueToBeSet = "";

		if (auditType.name().equals(AuditType.LOGON.name()))
		{
			valueToBeSet = "LON";
		}
		else
		{
			valueToBeSet = "LOF";
		}
		setOptionId(valueToBeSet);
	}

	public AuditType getAuditType()
	{
		return auditType;
	}

	public void setAuditType(AuditType auditType)
	{
		this.auditType = auditType;
	}

	/**
	 * @return the session
	 */
	public EquationStandardSession getSession()
	{
		return session;
	}

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(EquationStandardSession session)
	{
		this.session = session;
	}
}
