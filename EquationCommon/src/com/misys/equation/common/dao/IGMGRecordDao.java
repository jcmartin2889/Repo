package com.misys.equation.common.dao;

import java.util.Collection;

import com.misys.equation.common.dao.beans.GMGDataModel;

public interface IGMGRecordDao extends IDao
{
	public Collection<GMGDataModel> getOvernightPosition(final String enqCurrency);
	public Collection<GMGDataModel> getPositions(final String system, final String unit, final String enqCurrency);
}
