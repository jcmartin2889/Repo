package com.misys.equation.common.datastructure;

import com.misys.equation.common.access.EquationDataStructure;
import com.misys.equation.common.utilities.Toolbox;

public class EqDS_DSWSI2 extends EquationDataStructure
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqDS_DSWSI2.java 8758 2010-08-19 20:12:59Z MACDONP1 $";

	private static final long serialVersionUID = 1L;

	// Field names
	public final static String REF = "$2REF";
	public final static String AB = "$2AB";
	public final static String AN = "$2AN";
	public final static String AS = "$2AS";
	public final static String EAN = "$2EAN";
	public final static String CTR1 = "$2CTR1";
	public final static String CTR2 = "$2CTR2";
	public final static String SRTC = "$2SRTC";
	public final static String REG = "$2REG";
	public final static String CLC = "$2CLC";
	public final static String CLP = "$2CLP";
	public final static String CLR = "$2CLR";
	public final static String GTP = "$2GTP";
	public final static String ALL = "$2ALL";
	public final static String ADLP = "$2ADLP";
	public final static String ADLR = "$2ADLR";
	public final static String ADBN = "$2ADBN";
	public final static String OREF = "$2OREF";
	public final static String WREF = "$2WREF";
	public final static String IID = "$2IID";
	public final static String CTCD = "$2CTCD";
	public final static String DTCD = "$2DTCD";
	public final static String ACST = "$2ACST";
	public final static String CHNG = "$2CHNG";
	public final static String POOL = "$2POOL";
	public final static String PCCY = "$2PCCY";
	public final static String PTER = "$2PTER";
	public final static String PVDT = "$2PVDT";
	public final static String SGP = "$2SGP";
	public final static String SGP1 = "$2SGP1";
	public final static String FLN = "$2FLN";
	public final static String IMN = "$2IMN";
	public final static String MQQ = "$2MQQ";
	public final static String IBAN = "$2IBAN";
	public final static String TREF = "$2TREF";
	public final static String SOR = "$2SOR";
	public final static String SIT = "$2SIT";
	public final static String DNMR = "$2DNMR";
	public final static String STS = "$2STS";
	public final static String CUS = "$2CUS";
	public final static String TRN = "$2TRN";
	public final static String XREF = "$2XREF";
	public final static String MIC = "$2MIC";
	public final static String UII = "$2UII";
	public final static String BRF = "$2BRF";
	public final static String RIN7 = "$RIN7";
	public final static String SNR = "$2SNR";

	/**
	 * Constructor to create the DSHH02 data structure
	 */
	public EqDS_DSWSI2()
	{
		super("DSWSI2", Toolbox.DEF_CCSID);

		// create the DS
		createCharacterField("$2REF", "Account transfer reference", 1 - 1, 5);
		createCharacterField("$2AB", "Account branch", 6 - 1, 4);
		createCharacterField("$2AN", "Account basic", 10 - 1, 6);
		createCharacterField("$2AS", "Account suffix", 16 - 1, 3);
		createCharacterField("$2EAN", "External account number", 19 - 1, 20);
		createCharacterField("$2CTR1", "Charge transaction reference 1", 39 - 1, 3);
		createCharacterField("$2CTR2", "Charge transaction reference 2", 42 - 1, 3);
		createCharacterField("$2SRTC", "Sort code", 45 - 1, 9);
		createCharacterField("$2REG", "Region Code", 54 - 1, 3);
		createCharacterField("$2CLC", "Collateral location Or owning location", 57 - 1, 4);
		createCharacterField("$2CLP", "Collateral type", 61 - 1, 3);
		createCharacterField("$2CLR", "Collateral reference", 64 - 1, 35);
		createCharacterField("$2GTP", "Guarantee type", 99 - 1, 3);
		createCharacterField("$2ALL", "All branches flag", 102 - 1, 1);
		createCharacterField("$2ADLP", "Reservation deal type", 103 - 1, 3);
		createCharacterField("$2ADLR", "Reservation deal reference", 106 - 1, 13);
		createCharacterField("$2ADBN", "Reservation deal branch", 119 - 1, 4);
		createCharacterField("$2OREF", "Originators reference", 123 - 1, 20);
		createCharacterField("$2WREF", "Notice a/c withdrawal reference", 143 - 1, 16);
		createCharacterField("$2IID", "Customer payment instruction ID", 159 - 1, 10);
		createCharacterField("$2CTCD", "Credit transaction code", 169 - 1, 3);
		createCharacterField("$2DTCD", "Debit transaction code", 172 - 1, 3);
		createCharacterField("$2ACST", "Account settlement type", 175 - 1, 3);
		createCharacterField("$2CHNG", "Data changed Y/N flag", 178 - 1, 1);
		createCharacterField("$2POOL", "Pool name", 179 - 1, 3);
		createCharacterField("$2PCCY", "Pool currency", 182 - 1, 3);
		createCharacterField("$2PTER", "Pool term", 185 - 1, 5);
		createCharacterField("$2PVDT", "Pool value date", 190 - 1, 6);
		createCharacterField("$2SGP", "Variable group", 196 - 1, 2);
		createCharacterField("$2SGP1", "Variable sub-group", 198 - 1, 1);
		createCharacterField("$2FLN", "Variable field name", 199 - 1, 6);
		createCharacterField("$2IMN", "Interface mnemonic", 205 - 1, 8);
		createCharacterField("$2MQQ", "MQSeries queue name", 213 - 1, 48);
		createCharacterField("$2IBAN", "International bank a/c number", 261 - 1, 34);
		createCharacterField("$2TREF", "Transfer reference", 295 - 1, 16);
		createCharacterField("$2SOR", "Standing order reference", 311 - 1, 16);
		createCharacterField("$2SIT", "Stock item type", 327 - 1, 4);
		createCharacterField("$2DNMR", "Denomination required Y/N flag", 331 - 1, 1);
		createCharacterField("$2STS", "Status flag", 332 - 1, 1);
		createCharacterField("$2CUS", "Owning customer", 333 - 1, 6);
		createCharacterField("$2TRN", "Transaction type", 339 - 1, 2);
		createCharacterField("$2XREF", "External reference", 341 - 1, 35);
		createCharacterField("$2MIC", "Method of id code", 376 - 1, 4);
		createCharacterField("$2UII", "UII number", 380 - 1, 50);
		createCharacterField("$2BRF", "Batch reference", 430 - 1, 20);
		createCharacterField("$RIN7", "7 long Posting seq. no.", 450 - 1, 7);
		createCharacterField("$2SNR", "Serial number", 457 - 1, 13);

		// final setup
		initialDefaultValue();
	}
}
