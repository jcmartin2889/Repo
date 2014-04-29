package com.misys.equation.common.consolidation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IDao;
import com.misys.equation.common.dao.IFPGRecordDao;
import com.misys.equation.common.dao.beans.FPGDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;

public class FPGDaoConsolidator extends DaoConsolidator
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FPGDaoConsolidator.java 10168 2010-12-18 16:55:12Z WRIGHTP1 $";

	/**
	 * Constructor
	 * 
	 * @param session
	 *            EquationStandardSession
	 * @throws EQException
	 */
	public FPGDaoConsolidator(EquationStandardSession session) throws EQException
	{
		super(session, DaoFactory.FPG_RECORD_DAO_NAME);
	}

	public Collection<FPGDataModel> getFxTotal(final String currency, final String date)
	{
		List<FPGDataModel> coll = new ArrayList<FPGDataModel>();

		int i = 0;
		for (IDao iDao : daos)
		{
			IFPGRecordDao dao = (IFPGRecordDao) iDao;
			coll.addAll(dao.getFxTotal(sessions.get(i).getSystemId(), sessions.get(i).getUnitId(), currency, date));
			i++;
		}

		return coll;
	}
}