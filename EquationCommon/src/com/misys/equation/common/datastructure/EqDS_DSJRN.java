package com.misys.equation.common.datastructure;

import com.misys.equation.common.access.EquationDataStructure;
import com.misys.equation.common.utilities.Toolbox;

public class EqDS_DSJRN extends EquationDataStructure
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqDS_DSJRN.java 10425 2011-02-03 13:20:07Z lima12 $";

	private static final long serialVersionUID = 1L;

	// Field names
	public final static String BJRN = "BJRN";
	public final static String NIR = "NIR";
	public final static String NIRL = "NIRL";
	public final static String NIRLA = "NIRLA";
	public final static String NIJ = "NIJ";
	public final static String NIJL = "NIJL";
	public final static String NIJLA = "NIJLA";
	public final static String NIJON = "NIJON";
	public final static String NISWP = "NISWP";
	public final static String NIRRP = "NIRRP";
	public final static String NICONT = "NICONT";
	public final static String NBR = "NBR";
	public final static String NBRL = "NBRL";
	public final static String NBRLA = "NBRLA";
	public final static String NBJ = "NBJ";
	public final static String NBJL = "NBJL";
	public final static String NBJLA = "NBJLA";
	public final static String NBJON = "NBJON";
	public final static String TFSR = "TFSR";
	public final static String TFSRL = "TFSRL";
	public final static String TFSRLA = "TFSRLA";
	public final static String TFSJ = "TFSJ";
	public final static String TFSJL = "TFSJL";
	public final static String TFSJLA = "TFSJLA";
	public final static String TFSRE = "TFSRE";
	public final static String TFSRLE = "TFSRLE";
	public final static String TFSRLAE = "TFSRLAE";
	public final static String TFSJE = "TFSJE";
	public final static String TFSJLE = "TFSJLE";
	public final static String TFSJLAE = "TFSJLAE";
	public final static String TFSOR = "TFSOR";

	/**
	 * Constructor to create the DSHH02 data structure
	 */
	public EqDS_DSJRN()
	{
		super("DSJRN", Toolbox.DEF_CCSID);

		// create the DS
		createCharacterField(BJRN, "Set up journaling for Interfaces at End of Day", 1 - 1, 1);
		createCharacterField(NIR, "Normal input receiver", 2 - 1, 10);
		createCharacterField(NIRL, "Normal input receiver library", 12 - 1, 10);
		createCharacterField(NIRLA, "Normal input receiver library ASP", 22 - 1, 2);
		createCharacterField(NIJ, "Normal input journal", 24 - 1, 10);
		createCharacterField(NIJL, "Normal input journal library", 34 - 1, 10);
		createCharacterField(NIJLA, "Normal input journal library ASP", 44 - 1, 2);
		createCharacterField(NIJON, "Normal input IBM Journaling Active Flag", 46 - 1, 1);
		createCharacterField(NISWP, "Swap receivers at End of Day", 47 - 1, 1);
		createCharacterField(NIRRP, "Receiver retention period", 48 - 1, 1);
		createCharacterField(NICONT, "Continuous journaling", 49 - 1, 1);
		createCharacterField(NBR, "Non-business hours receiver", 50 - 1, 10);
		createCharacterField(NBRL, "Non-business hours receiver library", 60 - 1, 10);
		createCharacterField(NBRLA, "Non-business hours receiver library ASP", 70 - 1, 2);
		createCharacterField(NBJ, "Non-business hours journal", 72 - 1, 10);
		createCharacterField(NBJL, "Non-business hours journal library", 82 - 1, 10);
		createCharacterField(NBJLA, "Non-business hours journal library ASP", 92 - 1, 2);
		createCharacterField(NBJON, "Non-business hours IBM Journaling Active Flag", 94 - 1, 1);
		createCharacterField(TFSR, "24*7 receiver", 95 - 1, 10);
		createCharacterField(TFSRL, "24*7 receiver library", 105 - 1, 10);
		createCharacterField(TFSRLA, "24*7 receiver library ASP", 115 - 1, 2);
		createCharacterField(TFSJ, "24*7 journal", 117 - 1, 10);
		createCharacterField(TFSJL, "24*7 journal library", 127 - 1, 10);
		createCharacterField(TFSJLA, "24*7 journal library ASP", 137 - 1, 2);
		createCharacterField(TFSRE, "24*7 receiver (end of day unit)", 139 - 1, 10);
		createCharacterField(TFSRLE, "24*7 receiver library (end of day unit)", 149 - 1, 10);
		createCharacterField(TFSRLAE, "24*7 receiver library ASP (end of day unit)", 159 - 1, 2);
		createCharacterField(TFSJE, "24*7 journal (end of day unit)", 161 - 1, 10);
		createCharacterField(TFSJLE, "24*7 journal library (end of day unit)", 171 - 1, 10);
		createCharacterField(TFSJLAE, "24*7 journal library ASP (end of day unit)", 181 - 1, 2);
		createCharacterField("DUM1", "Dummy", 183 - 1, 1);
		createCharacterField(TFSOR, "24*7 object journal receiver", 184 - 1, 10);

		// final setup
		initialDefaultValue();
	}

}
