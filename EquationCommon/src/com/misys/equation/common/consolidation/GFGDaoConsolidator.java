package com.misys.equation.common.consolidation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IDao;
import com.misys.equation.common.dao.IGFGRecordDao;
import com.misys.equation.common.dao.beans.GFGDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;

public class GFGDaoConsolidator extends DaoConsolidator
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GFGDaoConsolidator.java 10168 2010-12-18 16:55:12Z WRIGHTP1 $";

	/**
	 * Constructor
	 * 
	 * @param session
	 *            EquationStandardSession
	 * @throws EQException
	 */
	public GFGDaoConsolidator(EquationStandardSession session) throws EQException
	{
		super(session, DaoFactory.GFG_RECORD_DAO_NAME);
	}

	public List<GFGDataModel> getAggregatePosition(final String currency)
	{
		List<GFGDataModel> coll = new ArrayList<GFGDataModel>();

		int i = 0;
		for (IDao iDao : daos)
		{
			IGFGRecordDao dao = (IGFGRecordDao) iDao;
			coll.addAll(dao.getAggregatePosition(currency));
			i++;
		}

		return coll;
	}

	public List<GFGDataModel> getStartOfDayMaturities(final String currency)
	{
		List<GFGDataModel> coll = new ArrayList<GFGDataModel>();

		int i = 0;
		for (IDao iDao : daos)
		{
			IGFGRecordDao dao = (IGFGRecordDao) iDao;
			coll.addAll(dao.getStartOfDayMaturities(sessions.get(i).getSystemId(), sessions.get(i).getUnitId(), currency));
			i++;
		}

		return coll;
	}

	public Collection<GFGDataModel> getFxTotal(final String currency)
	{
		List<GFGDataModel> coll = new ArrayList<GFGDataModel>();

		int i = 0;
		for (IDao iDao : daos)
		{
			IGFGRecordDao dao = (IGFGRecordDao) iDao;
			coll.addAll(dao.getFxTotal(sessions.get(i).getSystemId(), sessions.get(i).getUnitId(), currency));
			i++;
		}

		return coll;
	}
}
