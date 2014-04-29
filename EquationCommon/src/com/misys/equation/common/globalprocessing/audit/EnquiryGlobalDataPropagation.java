package com.misys.equation.common.globalprocessing.audit;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IGAARecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAARecordDataModel;

public class EnquiryGlobalDataPropagation
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EnquiryGlobalDataPropagation.java 10420 2011-02-03 12:22:26Z MACDONP1 $";
	private final DaoFactory daoFactory = new DaoFactory();
	private IGAARecordDao gAARecordDao = null;
	private String currentSequence = null;
	private List<GAARecordDataModel> gAARecordDataModels;

	public EnquiryGlobalDataPropagation(EquationStandardSession session)
	{
		initialization(session);
	}

	private void initialization(EquationStandardSession session)
	{
		gAARecordDao = daoFactory.getGAADao(session, new GAARecordDataModel());
	}

	public String getCurrentSequence()
	{
		return currentSequence;
	}

	public void setCurrentSequence(String currentSequence)
	{
		this.currentSequence = currentSequence;
	}

	public List<GAARecordDataModel> getGAARecordDataModel()
	{
		List<AbsRecord> result;

		if (gAARecordDataModels == null)
		{
			result = gAARecordDao.getRecordBy(new StringBuilder(" GAASEQ='").append(currentSequence).append("'").toString());
			gAARecordDataModels = castResults(result);
		}
		return gAARecordDataModels;
	}

	private List<GAARecordDataModel> castResults(List<AbsRecord> result)
	{
		List<GAARecordDataModel> gAARecordDataModels = null;
		gAARecordDataModels = new ArrayList<GAARecordDataModel>();
		GAARecordDataModel gAURecordDataModel = null;

		for (AbsRecord absRecord : result)
		{
			gAURecordDataModel = (GAARecordDataModel) absRecord;
			gAARecordDataModels.add(gAURecordDataModel);

		}
		return gAARecordDataModels;
	}
}