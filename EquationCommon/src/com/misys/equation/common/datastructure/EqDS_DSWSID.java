package com.misys.equation.common.datastructure;

import com.misys.equation.common.access.EquationDataStructure;
import com.misys.equation.common.utilities.Toolbox;

public class EqDS_DSWSID extends EquationDataStructure
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqDS_DSWSID.java 8758 2010-08-19 20:12:59Z MACDONP1 $";

	private static final long serialVersionUID = 1L;

	// Field names
	public final static String CUS = "$CUS";
	public final static String CLC = "$CLC";
	public final static String CCY = "$CCY";
	public final static String NST = "$NST";
	public final static String BRNM = "$BRNM";
	public final static String CCCY = "$CCCY";
	public final static String DLP = "$DLP";
	public final static String DLR = "$DLR";
	public final static String GRP = "$GRP";
	public final static String DTE = "$DTE";
	public final static String SDTE = "$SDTE";
	public final static String EDTE = "$EDTE";
	public final static String LDR = "$LDR";
	public final static String LNI = "$LNI";
	public final static String CBBN = "$CBBN";
	public final static String CBNO = "$CBNO";
	public final static String CSFX = "$CSFX";
	public final static String DNR = "$DNR";
	public final static String SOR = "$SOR";
	public final static String SRF = "$SRF";
	public final static String FSN = "$FSN";
	public final static String LSR = "$LSR";
	public final static String SAMT = "$SAMT";
	public final static String BTC = "$BTC";
	public final static String RIN = "$RIN";
	public final static String PYP = "$PYP";
	public final static String RRT = "$RRT";
	public final static String PYT = "$PYT";
	public final static String CTP = "$CTP";
	public final static String USR = "$USR";
	public final static String SET = "$SET";
	public final static String CPR = "$CPR";
	public final static String CPE = "$CPE";
	public final static String LC = "$LC";
	public final static String PRTP = "$PRTP";
	public final static String CHG = "$CHG";
	public final static String XM = "$XM";
	public final static String RBR = "$RBR";
	public final static String SFX2 = "$SFX2";
	public final static String ATP = "$ATP";
	public final static String NIF = "$NIF";
	public final static String CST = "$CST";
	public final static String MRL = "$MRL";
	public final static String DRG = "$DRG";
	public final static String APP = "$APP";
	public final static String CMR = "$CMR";
	public final static String AMT = "$AMT";
	public final static String FRQ = "$FRQ";
	public final static String PNM = "$PNM";
	public final static String OPD = "$OPD";
	public final static String LSTR = "$LSTR";
	public final static String CNA = "$CNA";
	public final static String RMNM = "$RMNM";
	public final static String RID = "$RID";
	public final static String MNM2 = "$MNM2";
	public final static String HRN1 = "$HRN1";
	public final static String HRN2 = "$HRN2";
	public final static String DREF = "$DREF";
	public final static String SMN = "$SMN";
	public final static String CITM = "$CITM";
	public final static String CIGR = "$CIGR";
	public final static String NAR = "$NAR";
	public final static String EAN = "$EAN";
	public final static String REF = "$REF";
	public final static String DPI = "$DPI";
	public final static String CTNO = "$CTNO";
	public final static String SAMT1 = "$SAMT1";
	public final static String PSTR = "$PSTR";
	public final static String PCSDT = "$PCSDT";
	public final static String PCEDT = "$PCEDT";
	public final static String ACOFR = "$ACOFR";
	public final static String RGN1 = "$RGN1";
	public final static String RGL1 = "$RGL1";
	public final static String BOT = "$BOT";

	/**
	 * Constructor to create the DSWSID data structure
	 */
	public EqDS_DSWSID()
	{
		super("DSWSID", Toolbox.DEF_CCSID);

		// create the DS
		createCharacterField("$CUS", "Customer name", 1 - 1, 6);
		createCharacterField("$CLC", "Customer location", 7 - 1, 3);
		createCharacterField("$CCY", "Currency", 10 - 1, 3);
		createCharacterField("$NST", "Nostro name", 13 - 1, 6);
		createCharacterField("$BRNM", "Branch mnemonic", 19 - 1, 4);
		createCharacterField("$CCCY", "Converted to currency", 23 - 1, 3);
		createCharacterField("$DLP", "Deal prefix", 26 - 1, 3);
		createCharacterField("$DLR", "Deal reference", 29 - 1, 13);
		createCharacterField("$GRP", "Group", 42 - 1, 6);
		createCharacterField("$DTE", "Date (user format)", 48 - 1, 6);
		createCharacterField("$SDTE", "Start date (user format)", 54 - 1, 6);
		createCharacterField("$EDTE", "End date (user format)", 60 - 1, 6);
		createCharacterField("$LDR", "Loan drawdown", 66 - 1, 3);
		createCharacterField("$LNI", "Loan id", 69 - 1, 3);
		createCharacterField("$CBBN", "Branch number", 72 - 1, 4);
		createCharacterField("$CBNO", "A/c basic number", 76 - 1, 6);
		createCharacterField("$CSFX", "A/c suffix", 82 - 1, 3);
		createCharacterField("$DNR", "Diary note reference", 85 - 1, 5);
		createCharacterField("$SOR", "Standing order reference", 90 - 1, 5);
		createCharacterField("$SRF", "Stop reference", 95 - 1, 5);
		createCharacterField("$FSN", "First serial number", 100 - 1, 16);
		createCharacterField("$LSR", "Last serial number", 116 - 1, 16);
		createCharacterField("$SAMT", "Stop amount", 132 - 1, 15);
		createCharacterField("$BTC", "Batch id", 147 - 1, 5);
		// createCharacterField("$RDN", "", 147-1, 148);
		// createCharacterField("$RRF", "", 149-1, 150);
		// createCharacterField("$RML", "", 151-1, 151);
		createCharacterField("$RIN", "Item number", 152 - 1, 5);
		createCharacterField("$PYP", "Payment purpose", 157 - 1, 2);
		createCharacterField("$RRT", "Retail rate type", 159 - 1, 3);
		createCharacterField("$PYT", "Payment type", 162 - 1, 3);
		createCharacterField("$CTP", "Customer type", 165 - 1, 2);
		createCharacterField("$USR", "User", 167 - 1, 4);
		createCharacterField("$SET", "Set id", 171 - 1, 10);
		createCharacterField("$CPR", "Payment reference", 181 - 1, 16);
		createCharacterField("$CPE", "External reference", 197 - 1, 16);
		createCharacterField("$LC", "Limit category", 213 - 1, 5);
		createCharacterField("$PRTP", "Profit type", 218 - 1, 1);
		createCharacterField("$CHG", "Charge code", 219 - 1, 2);
		createCharacterField("$XM", "Transfer method", 221 - 1, 2);
		createCharacterField("$RBR", "Batch branch number", 223 - 1, 4);
		createCharacterField("$SFX2", "Second account suffix type", 227 - 1, 3);
		createCharacterField("$ATP", "Account type", 230 - 1, 2);
		createCharacterField("$NIF", "NIF/DNI spanish id number", 232 - 1, 9);
		createCharacterField("$CST", "CST Number", 241 - 1, 4);
		createCharacterField("$MRL", "MRL Number", 245 - 1, 4);
		createCharacterField("$DRG", "Date of creation (Hacianda/DGTE)", 249 - 1, 7);
		createCharacterField("$APP", "Application code", 256 - 1, 2);
		createCharacterField("$CMR", "Commitment reference", 258 - 1, 13);
		createCharacterField("$AMT", "Repayment amount", 271 - 1, 20);
		createCharacterField("$FRQ", "Frequency code", 291 - 1, 3);
		createCharacterField("$PNM", "Number of payments", 294 - 1, 3);
		createCharacterField("$OPD", "Option Id", 297 - 1, 3);
		createCharacterField("$LSTR", "Limit structure reference", 300 - 1, 5);
		createCharacterField("$CNA", "Country", 305 - 1, 2);
		createCharacterField("$RMNM", "Report mnemonic", 307 - 1, 10);
		createCharacterField("$RID", "Report definition", 317 - 1, 10);
		createCharacterField("$MNM2", "Prototype report mnemonic", 327 - 1, 10);
		createCharacterField("$HRN1", "", 337 - 1, 3);
		createCharacterField("$HRN2", "", 340 - 1, 3);
		createCharacterField("$DREF", "", 343 - 1, 12);
		createCharacterField("$SMN", "Security mnemonic", 355 - 1, 16);
		createCharacterField("$CITM", "Item name", 371 - 1, 4);
		createCharacterField("$CIGR", "Group name", 375 - 1, 5);
		createCharacterField("$NAR", "Narrative", 380 - 1, 35);
		createCharacterField("$EAN", "External Account Number", 415 - 1, 20);
		createCharacterField("$REF", "Portfolio reference number", 435 - 1, 16);
		createCharacterField("$DPI", "Depot id", 451 - 1, 3);
		createCharacterField("$CTNO", "Certificate number", 454 - 1, 10);
		createCharacterField("$SAMT1", "Stop amount", 464 - 1, 17);
		createCharacterField("$PSTR", "Profit structure reference", 481 - 1, 5);
		createCharacterField("$PCSDT", "Compare periods start date", 486 - 1, 6);
		createCharacterField("$PCEDT", "Compare period end date", 492 - 1, 6);
		createCharacterField("$ACOFR", "Account officer", 498 - 1, 3);
		createCharacterField("$RGN1", "Reporting Group name", 501 - 1, 6);
		createCharacterField("$RGL1", "Reporting Group location", 507 - 1, 3);
		createCharacterField("$BOT", "Balance Order Type", 510 - 1, 2);

		// final setup
		initialDefaultValue();
	}
}
