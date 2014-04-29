package com.misys.equation.common.dao.beans;

import com.misys.equation.common.search.results.ISearchResult;

public abstract class SearchResultDataModel extends AbsRecord
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SearchResultDataModel.java 6928 2010-04-14 12:04:49Z MACDONP1 $";
	/**
	 * Unique Serial UID
	 */
	private static final long serialVersionUID = -7550490617170542545L;

	public abstract ISearchResult populateFromDataModel(AbsRecord dataModel, final String unit, final String system);

}
