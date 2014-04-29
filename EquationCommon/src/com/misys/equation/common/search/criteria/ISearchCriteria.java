package com.misys.equation.common.search.criteria;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.search.results.SearchType;

public interface ISearchCriteria
{

	public SearchType getSearchType();

	public String getSearchString();

	public AbsRecord getDataModel();

	public IDao getRecordDao(EquationStandardSession session, DaoFactory daoFactory, AbsRecord record);
}
