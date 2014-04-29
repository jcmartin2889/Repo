package com.misys.equation.common.consolidation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IDao;
import com.misys.equation.common.dao.IGMGRecordDao;
import com.misys.equation.common.dao.beans.GMGDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;

public class GMGDaoConsolidator extends DaoConsolidator
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GMGDaoConsolidator.java 10168 2010-12-18 16:55:12Z WRIGHTP1 $";

	/**
	 * Constructor
	 * 
	 * @param session
	 *            EquationStandardSession
	 * @throws EQException
	 */
	public GMGDaoConsolidator(EquationStandardSession session) throws EQException
	{
		super(session, DaoFactory.GMG_RECORD_DAO_NAME);
	}

	public List<GMGDataModel> getOvernightPosition(final String currency)
	{
		List<GMGDataModel> coll = new ArrayList<GMGDataModel>();

		int i = 0;
		for (IDao iDao : daos)
		{
			IGMGRecordDao dao = (IGMGRecordDao) iDao;
			coll.addAll(dao.getOvernightPosition(currency));
			i++;
		}

		return coll;
	}

	public Collection<GMGDataModel> getPositions(final String currency)
	{
		List<GMGDataModel> coll = new ArrayList<GMGDataModel>();

		int i = 0;
		for (IDao iDao : daos)
		{
			IGMGRecordDao dao = (IGMGRecordDao) iDao;
			coll.addAll(dao.getPositions(sessions.get(i).getSystemId(), sessions.get(i).getUnitId(), currency));
			i++;
		}

		return coll;
	}
}
