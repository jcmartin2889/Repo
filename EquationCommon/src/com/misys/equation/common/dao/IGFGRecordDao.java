package com.misys.equation.common.dao;

import java.util.Collection;

import com.misys.equation.common.dao.beans.GFGDataModel;

public interface IGFGRecordDao extends IDao
{
	public Collection<GFGDataModel> getAggregatePosition(final String enqCurrency);
	public Collection<GFGDataModel> getStartOfDayMaturities(final String system, final String unit, final String enqCurrency);
	public Collection<GFGDataModel> getFxTotal(final String system, final String unit, final String enqCurrency);
}
