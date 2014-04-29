package com.misys.equation.common.consolidation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IDao;
import com.misys.equation.common.dao.IGMLRecordDao;
import com.misys.equation.common.dao.beans.GMLDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;

public class GMLDaoConsolidator extends DaoConsolidator
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GMLDaoConsolidator.java 10168 2010-12-18 16:55:12Z WRIGHTP1 $";

	/**
	 * Constructor
	 * 
	 * @param session
	 *            EquationStandardSession
	 * @throws EQException
	 */
	public GMLDaoConsolidator(EquationStandardSession session) throws EQException
	{
		super(session, DaoFactory.GML_RECORD_DAO_NAME);
	}

	public Collection<GMLDataModel> getOvernightPositions(final String enqCurrency)
	{
		List<GMLDataModel> coll = new ArrayList<GMLDataModel>();

		int i = 0;
		for (IDao iDao : daos)
		{
			IGMLRecordDao dao = (IGMLRecordDao) iDao;
			coll.addAll(dao.getOvernightPositions(enqCurrency));
			i++;
		}

		return coll;
	}

	public Collection<GMLDataModel> getPositions(final String enqCurrency, final String enqEndDate)
	{
		List<GMLDataModel> coll = new ArrayList<GMLDataModel>();

		int i = 0;
		for (IDao iDao : daos)
		{
			IGMLRecordDao dao = (IGMLRecordDao) iDao;
			coll.addAll(dao.getPositions(enqCurrency, enqEndDate));
			i++;
		}

		return coll;
	}
}