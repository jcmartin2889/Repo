package com.misys.equation.common.datastructure;

import com.misys.equation.common.access.EquationDataStructure;
import com.misys.equation.common.utilities.Toolbox;

public class EqDS_DSHH02Head extends EquationDataStructure
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqDS_DSHH02Head.java 8758 2010-08-19 20:12:59Z MACDONP1 $";

	private static final long serialVersionUID = 1L;

	// Field names
	public final static String HCON = "HCON";
	public final static String HREF = "HREF";
	public final static String HNUM = "HNUM";
	public final static String HCCY = "HCCY";
	public final static String HAMA = "HAMA";
	public final static String DUM1 = "DUM1";
	public final static String DUM2 = "DUM2";

	/**
	 * Constructor to create the DSHH02 data structure
	 */
	public EqDS_DSHH02Head()
	{
		super("DSHH02Head", Toolbox.DEF_CCSID);

		// create the DS
		createCharacterField(HCON, "Error store control", 1 - 1, 1);
		createCharacterField(HREF, "Reference", 2 - 1, 22);
		createCharacterField(HCCY, "Currency", 24 - 1, 3);
		createCharacterField(DUM1, "Dummy spaces 1", 27 - 1, 16);
		createPackedField(HAMA, "Limit amount", 43 - 1, 15, 0);
		createCharacterField(DUM2, "Dummy spaces 2", 51 - 1, 46);
		createPackedField(HNUM, "Number", 97 - 1, 3, 0);

		// final setup
		initialDefaultValue();
	}
}
