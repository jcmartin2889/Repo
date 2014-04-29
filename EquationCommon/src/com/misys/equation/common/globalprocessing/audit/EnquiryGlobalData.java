package com.misys.equation.common.globalprocessing.audit;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IGAURecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAURecordDataModel;

public class EnquiryGlobalData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EnquiryGlobalData.java 10420 2011-02-03 12:22:26Z MACDONP1 $";
	private final DaoFactory daoFactory = new DaoFactory();
	private IGAURecordDao gAURecordDao = null;
	private String currentUser = null;
	private List<GAURecordDataModel> gAURecordDataModels;

	public EnquiryGlobalData(EquationStandardSession session)
	{
		initialization(session);
	}

	private void initialization(EquationStandardSession session)
	{
		gAURecordDao = daoFactory.getGAUDao(session, new GAURecordDataModel());
	}

	public String getCurrentUser()
	{
		return currentUser;
	}

	public void setCurrentUser(String currentUser)
	{
		this.currentUser = currentUser;
	}

	public List<GAURecordDataModel> getGAURecordDataModelByUser()
	{
		List<AbsRecord> result;

		if (gAURecordDataModels == null)
		{
			StringBuilder whereStatement = new StringBuilder(" GAUUSID = '").append(currentUser).append("'");
			whereStatement.append(" AND (GAUSO = '1' OR GAUSO = '2' OR GAUSO = '7')");
			result = gAURecordDao.getRecordBy(whereStatement.toString());
			gAURecordDataModels = castResults(result);
		}
		return gAURecordDataModels;
	}

	private List<GAURecordDataModel> castResults(List<AbsRecord> result)
	{
		List<GAURecordDataModel> gAURecordDataModels = null;
		gAURecordDataModels = new ArrayList<GAURecordDataModel>();
		GAURecordDataModel gAURecordDataModel = null;

		for (AbsRecord absRecord : result)
		{
			gAURecordDataModel = (GAURecordDataModel) absRecord;
			gAURecordDataModels.add(gAURecordDataModel);
		}
		return gAURecordDataModels;
	}
}
