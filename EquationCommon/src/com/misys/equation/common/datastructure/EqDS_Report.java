package com.misys.equation.common.datastructure;

import com.misys.equation.common.access.EquationDataStructure;
import com.misys.equation.common.language.LanguageResources;
import com.misys.equation.common.utilities.Toolbox;

public class EqDS_Report extends EquationDataStructure
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqDS_Report.java 8758 2010-08-19 20:12:59Z MACDONP1 $";

	private static final long serialVersionUID = 1L;

	// Field names
	public final static String RPT = "RPT";
	public final static String SEL = "SEL";

	/**
	 * Constructor to create the report data structure
	 */
	public EqDS_Report()
	{
		super("DS_REPORT", Toolbox.DEF_CCSID);

		// create the DS
		createCharacterField(RPT, LanguageResources.getString("Language.ReportRequestOverride"), 1 - 1, 10);
		createCharacterField(SEL, LanguageResources.getString("Language.ReportSelection"), 11 - 1, 128);

		// final setup
		initialDefaultValue();
	}

}
