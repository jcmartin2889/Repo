package com.misys.equation.common.datastructure;

import com.misys.equation.common.access.EquationDataStructure;
import com.misys.equation.common.utilities.Toolbox;

public class EqDS_DSGYDET extends EquationDataStructure
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqDS_DSGYDET.java 8758 2010-08-19 20:12:59Z MACDONP1 $";

	private static final long serialVersionUID = 1L;

	// Field names
	public final static String XGYAPID = "@XGYAPID";
	public final static String XGYTCP = "@XGYTCP";
	public final static String XGYIREF = "@XGYIREF";
	public final static String XGYAUID = "@XGYAUID";
	public final static String XGYSUID = "@XGYSUID";
	public final static String XGYAEXT = "@XGYAEXT";
	public final static String XGYAREC = "@XGYAREC";

	/**
	 * Constructor to create the DSGYDET data structure
	 */
	public EqDS_DSGYDET()
	{
		super("DSGYDET", Toolbox.DEF_CCSID);

		// create the DS
		createCharacterField(XGYAPID, "Application Id", 1 - 1, 10);
		createCharacterField(XGYTCP, "TCP/Ip address", 11 - 1, 15);
		createCharacterField(XGYIREF, "Input reference", 26 - 1, 16);
		createCharacterField(XGYAUID, "Inputting user id", 42 - 1, 10);
		createCharacterField(XGYSUID, "Supervisor user", 52 - 1, 10);
		createCharacterField(XGYAEXT, "Apply during external input?", 62 - 1, 1);
		createCharacterField(XGYAREC, "Apply during recovery?", 63 - 1, 1);

		// final setup
		initialDefaultValue();
	}

}
