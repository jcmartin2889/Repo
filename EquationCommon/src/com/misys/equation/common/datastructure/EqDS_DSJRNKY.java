package com.misys.equation.common.datastructure;

import com.misys.equation.common.access.EquationDataStructure;
import com.misys.equation.common.utilities.Toolbox;

public class EqDS_DSJRNKY extends EquationDataStructure
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqDS_DSJRNKY.java 8758 2010-08-19 20:12:59Z MACDONP1 $";

	private static final long serialVersionUID = 1L;

	// Field names
	public final static String GYWSID = "@GYWSID";
	public final static String GYDIM = "@GYDIM";
	public final static String GYTIM = "@GYTIM";
	public final static String GYSEQ = "@GYSEQ";
	public final static String GYFRO = "@GYFRO";
	public final static String GYJTT = "@GYJTT";
	public final static String GYCDT = "@GYCDT";
	public final static String GYCLTM = "@GYCLTM";
	public final static String GYCSEQ = "@GYCSEQ";
	public final static String GYJOB = "@GYJOB";
	public final static String GYREC = "@GYREC";

	/**
	 * Constructor to create the DSGYCTR data structure
	 */
	public EqDS_DSJRNKY()
	{
		super("DSJRNKY", Toolbox.DEF_CCSID);

		// create the DS
		createCharacterField(GYWSID, "", 1 - 1, 4);
		createCharacterField(GYDIM, "", 5 - 1, 2);
		createCharacterField(GYTIM, "", 7 - 1, 6);
		createCharacterField(GYSEQ, "", 13 - 1, 7);
		createCharacterField(GYFRO, "", 20 - 1, 4);
		createCharacterField(GYJTT, "", 24 - 1, 1);
		createCharacterField(GYCDT, "", 25 - 1, 7);
		createCharacterField(GYCLTM, "", 32 - 1, 6);
		createCharacterField(GYCSEQ, "", 38 - 1, 7);
		createCharacterField(GYJOB, "", 45 - 1, 6);
		createCharacterField(GYREC, "", 51 - 1, 1);

		// final setup
		initialDefaultValue();
	}

}
