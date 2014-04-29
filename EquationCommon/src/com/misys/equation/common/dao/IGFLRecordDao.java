package com.misys.equation.common.dao;

import java.util.Collection;

import com.misys.equation.common.dao.beans.GFLDataModel;

public interface IGFLRecordDao extends IDao
{
	public Collection<GFLDataModel> getOvernightPositions(final String enqCurrency);
	public Collection<GFLDataModel> getStartOfDayMaturityPositions(final String enqCurrency, String enqEndDate);
	public Collection<GFLDataModel> getPositions(final String enqCurrency, String enqEndDate);
}
