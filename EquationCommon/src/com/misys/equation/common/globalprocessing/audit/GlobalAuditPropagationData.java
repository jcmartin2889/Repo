package com.misys.equation.common.globalprocessing.audit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.beans.GAVRecordDataModel;

public class GlobalAuditPropagationData extends GAVRecordDataModel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalAuditPropagationData.java 10472 2011-02-10 15:04:46Z MACDONP1 $";
	/** */
	private static final long serialVersionUID = 289796456624293654L;
	/** */
	private final DateFormat dateFormatter = new SimpleDateFormat("HHmmss");
	/** */
	private static final String EMPTY_STRING = "";

	public GlobalAuditPropagationData(EquationStandardSession session)
	{
		super();
		setSequenceId(0);

		setSourceUnit(session.getUnitId());
		setSourceServerId(session.getSystemId());
		setUserId(session.getUserId());
		setWorkstationId(EMPTY_STRING);
		setSourceUnitCcsid(session.getCcsid());
		setPropagationSetId(EMPTY_STRING);
		setPropagationRuleId(EMPTY_STRING);
		setApiFormat(EMPTY_STRING);
		setConditions(EMPTY_STRING);
		Calendar calendar = Calendar.getInstance();
		setDayInMonth(calendar.get(Calendar.DAY_OF_MONTH));

		int timeHhmmss = Integer.parseInt(dateFormatter.format(calendar.getTime()));
		setTimeHhmmss(timeHhmmss);
		setSequenceNumber(0);
		setApiFormat(EMPTY_STRING);
		setType(EMPTY_STRING);
		setSourceGsImage(null);
		setSourceGzImage(null);
		setPropagationSetId(EMPTY_STRING);
		setPropagationRuleId(EMPTY_STRING);
		setConditions(EMPTY_STRING);
	}
}
