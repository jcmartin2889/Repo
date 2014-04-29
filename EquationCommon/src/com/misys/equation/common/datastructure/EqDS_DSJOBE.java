package com.misys.equation.common.datastructure;

import com.misys.equation.common.access.EquationDataStructure;
import com.misys.equation.common.utilities.Toolbox;

/**
 * This class represents the DSJOBE data structure
 */
public class EqDS_DSJOBE extends EquationDataStructure
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqDS_DSJOBE.java 15079 2012-12-18 18:57:46Z williae1 $";

	private static final long serialVersionUID = 1L;

	// Field names
	public final static String WSID = "$WSID";
	public final static String USID = "$USID";
	public final static String JOBNO = "$JOBNO";
	public final static String MENU = "$MENU";
	public final static String MAIN = "$MAIN";
	public final static String LNG = "$LNG";
	public final static String DBRNM = "$DBRNM";
	public final static String DBBN = "$DBBN";
	public final static String UBID = "$UBID";
	public final static String MENT = "$MENT";
	public final static String MDEL = "$MDEL";
	public final static String MMNT = "$MMNT";
	public final static String MENQ = "$MENQ";
	public final static String MPMT = "$MPMT";
	public final static String DUM1 = "$DUM1";
	public final static String ZDATE = "$ZDATE";
	public final static String ANF = "$ANF";
	public final static String OPN = "$OPN";
	public final static String OVRD = "$OVRD";
	public final static String DECST = "$DECST";
	public final static String INTST = "$INTST";
	public final static String YAB = "$YAB";
	public final static String NAB = "$NAB";
	public final static String BOTH = "$BOTH";
	public final static String AUTH = "$AUTH";
	public final static String MAINT = "$MAINT";
	public final static String DEL = "$DEL";
	public final static String AAI = "$AAI";
	public final static String DDAY = "$DDAY";
	public final static String DWEEK = "$DWEEK";
	public final static String DMNTH = "$DMNTH";
	public final static String DYEAR = "$DYEAR";
	public final static String DSPOT = "$DSPOT";
	public final static String DTOM = "$DTOM";
	public final static String DNEXT = "$DNEXT";
	public final static String DTODY = "$DTODY";
	public final static String PAY = "$PAY";
	public final static String REC = "$REC";
	public final static String JAN = "$JAN";
	public final static String INPD = "$INPD";
	public final static String OID = "$OID";
	public final static String ETK = "$ETK";
	public final static String ETN = "$ETN";
	public final static String DMMDF = "$DMMDF";
	public final static String YACST = "$YACST";
	public final static String YRTL = "$YRTL";
	public final static String RPFMT = "$RPFMT";
	public final static String RDFMT = "$RDFMT";
	public final static String DSEP = "$DSEP";
	public final static String CSJOB = "$CSJOB";
	public final static String CSBBN = "$CSBBN";
	public final static String LVAUT = "$LVAUT";
	public final static String SUID4 = "$SUID4";
	public final static String LTIM = "$LTIM";
	public final static String LSET = "$LSET";
	public final static String CSFWD = "$CSFWD";
	public final static String GFOIJ = "$GFOIJ";
	public final static String CSTRN = "$CSTRN";
	public final static String BAEXT = "$BAEXT";
	public final static String LOTRN = "$LOTRN";
	public final static String SEPWD = "$SEPWD";
	public final static String TCPIP = "$TCPIP";
	public final static String LOGONAPPL = "$LOGONAPPL";
	public final static String JOBSLEEPSATEOD = "$JOBSLEEPSATEOD";
	public final static String STARTCC = "$STARTCC";
	public final static String JOBCCDEF = "$JOBCCDEF";
	public final static String DFTGRPCMTLINK = "$DFTGRPCMTLINK";
	public final static String CONNECTIONCMT = "$CONNECTIONCMT";
	public final static String XACMT = "$XACMT";
	public final static String LOGONREQ = "$LOGONREQ";
	public final static String LOGONUNIT = "$LOGONUNIT";
	public final static String EQACTGRP = "$EQACTGRP";
	public final static String ORGCURUSER = "$ORGCURUSER";
	public final static String UNITAVAILABLE = "$UNITAVAILABLE";
	public final static String JOBRESETREQ = "$JOBRESETREQ";
	public final static String LOGLIBL = "$LOGLIBL";
	public final static String FOID1 = "$FOID1";
	public final static String CSPGMMSGQ = "$CSPGMMSGQ";
	public final static String WIPADDR = "$WIPADDR";
	public final static String EQDTP = "$EQDTP";

	/**
	 * Constructor to create the DSJOBE data structure
	 * 
	 * @equation.external
	 */
	public EqDS_DSJOBE()
	{
		super("DSJOBE", Toolbox.DEF_CCSID);

		// create the DS
		createCharacterField(WSID, "Workstation identity", 1 - 1, 10);
		createCharacterField(USID, "User identity", 11 - 1, 10);
		createCharacterField(JOBNO, "Job number", 21 - 1, 6);
		createCharacterField(MENU, "Menu program called from", 27 - 1, 3);
		createCharacterField(MAIN, "Initial program entry menu", 30 - 1, 3);
		createCharacterField(LNG, "Users language code", 33 - 1, 2);
		createCharacterField(DBRNM, "Default branch mnemonic for this user", 35 - 1, 4);
		createCharacterField(DBBN, "Default branch number for this user", 39 - 1, 4);
		createCharacterField(UBID, "Input batch ID for this user", 43 - 1, 5);
		createCharacterField(MENT, "Entry mode text", 48 - 1, 7);
		createCharacterField(MDEL, "Delete mode text", 55 - 1, 7);
		createCharacterField(MMNT, "Maint mode text", 62 - 1, 7);
		createCharacterField(MENQ, "Enquiry mode text", 69 - 1, 7);
		createCharacterField(MPMT, "Prompt mode text", 76 - 1, 7);
		createCharacterField(DUM1, "Not used", 83 - 1, 21); // Space left for 3 more modes
		createCharacterField(ZDATE, "Screen date (dd mon yyyy format)", 104 - 1, 11);
		createCharacterField(ANF, "Not found message text", 115 - 1, 15);
		createCharacterField(OPN, "OPEN date text", 130 - 1, 7);
		createCharacterField(OVRD, "F24=Override", 137 - 1, 16);
		createCharacterField(DECST, "Decimal separator", 153 - 1, 1);
		createCharacterField(INTST, "Integer separator", 154 - 1, 1);
		createCharacterField(YAB, "YES abbreviation", 155 - 1, 1);
		createCharacterField(NAB, "NO abbreviation", 156 - 1, 1);
		createCharacterField(BOTH, "BOTH abbreviation", 157 - 1, 1);
		createCharacterField(AUTH, "Authorise character", 158 - 1, 1);
		createCharacterField(MAINT, "Maintain character", 159 - 1, 1);
		createCharacterField(DEL, "Delete character", 160 - 1, 1);
		createCharacterField(AAI, "Amount abbreviations", 161 - 1, 8);
		createCharacterField(DDAY, "Day abbreviation", 169 - 1, 1);
		createCharacterField(DWEEK, "Week abbreviation", 170 - 1, 1);
		createCharacterField(DMNTH, "Month abbreviation", 171 - 1, 1);
		createCharacterField(DYEAR, "Year abbreviation", 172 - 1, 1);
		createCharacterField(DSPOT, "Spot abbreviation", 173 - 1, 1);
		createCharacterField(DTOM, "Tomorrow abbreviation", 174 - 1, 1);
		createCharacterField(DNEXT, "Next abbreviation", 175 - 1, 1);
		createCharacterField(DTODY, "Today abbreviation", 176 - 1, 1);
		createCharacterField(PAY, "Pay settlement abbreviation", 177 - 1, 1);
		createCharacterField(REC, "Receive settlement abbreviation", 178 - 1, 1);
		createCharacterField(JAN, "Month mnemonics", 179 - 1, 36);
		createCharacterField(INPD, "Input date format", 215 - 1, 1);
		createCharacterField(OID, "Menu option id (GB)", 216 - 1, 3);
		createCharacterField(ETK, "Enigma timestamp", 219 - 1, 4);
		createPackedField(ETN, "Enigma timestamp sequence number", 223 - 1, 7, 0);
		createCharacterField(DMMDF, "MM/CL start date abbreviation", 227 - 1, 1);
		createCharacterField(YACST, "Call Accounting set in update module?", 228 - 1, 1);
		createCharacterField(YRTL, "Is user's language right-to-left", 229 - 1, 1);
		createCharacterField(RPFMT, "Report/enquiry date format P/S", 230 - 1, 1);
		createCharacterField(RDFMT, "Report/enquiry date format D/M/Y", 231 - 1, 1);
		createCharacterField(DSEP, "Date separator", 232 - 1, 1);
		createCharacterField(CSJOB, "Called via Cashier System?", 233 - 1, 1);
		createCharacterField(CSBBN, "Cashier System branch number", 234 - 1, 4);
		createCharacterField(LVAUT, "Limit violation authority", 238 - 1, 1);
		createCharacterField(SUID4, "Supervisor user identity", 239 - 1, 4);
		createCharacterField(LTIM, "Link time", 243 - 1, 6);
		createCharacterField(LSET, "Link set", 249 - 1, 1);
		createCharacterField(CSFWD, "Called via Cashier System Forwarding?", 250 - 1, 1);
		createCharacterField(GFOIJ, "Is this the GFOI interface job?", 251 - 1, 1);
		createCharacterField(CSTRN, "Called via Cashier System V7 or V8?", 252 - 1, 1);
		createCharacterField(BAEXT, "Called from batch external input?", 253 - 1, 1);
		createCharacterField(LOTRN, "Called from Loan Origination?", 254 - 1, 1);
		createCharacterField(SEPWD, "Encrypted supervisor password", 255 - 1, 10);
		createCharacterField(TCPIP, "Tcpip address", 265 - 1, 15);
		createCharacterField(LOGONAPPL, "Calling application, determined at EQLOGON", 280 - 1, 10);
		createCharacterField(JOBSLEEPSATEOD, "Job remains inactive during EOD? 0/1", 290 - 1, 1);
		createCharacterField(STARTCC, "Commitment Control should be started? 0/1", 291 - 1, 1);
		createCharacterField(JOBCCDEF, "Commitment Definition is JOB scoped? 0/1", 292 - 1, 1);
		createCharacterField(DFTGRPCMTLINK, "Default activition group commit triggers 'Equation' activition group commit? 0/1",
						293 - 1, 1);
		createCharacterField(CONNECTIONCMT, "Connnection commit triggers 'Equation' activition group commit? 0/1", 294 - 1, 1);
		createCharacterField(XACMT, "XA commit triggers 'Equation' activition group commit? 0/1", 295 - 1, 1);
		createCharacterField(LOGONREQ, "EQLOGON process required? 0/1", 296 - 1, 1);
		createCharacterField(LOGONUNIT, "Original unit used for the logon process", 297 - 1, 3);
		createCharacterField(EQACTGRP, "EQUATION activation group being used? 0/1", 300 - 1, 1);
		createCharacterField(ORGCURUSER, "Original current user of the job", 301 - 1, 10);
		createCharacterField(UNITAVAILABLE, "Unit is available? 0/1", 311 - 1, 1);
		createCharacterField(JOBRESETREQ, "Job needs to be reset? 0/1", 312 - 1, 1);
		createCharacterField(LOGLIBL, "Logon library list mode", 313 - 1, 1);
		createCharacterField(FOID1, "First option ID", 314 - 1, 3);
		createCharacterField(CSPGMMSGQ, "Call Stack Program Message Queue", 317 - 1, 10);
		createCharacterField(WIPADDR, "Workstation IP address", 327 - 1, 15);
		createCharacterField(EQDTP, "Equation desktop function", 342 - 1, 1);

		// final setup
		initialDefaultValue();
	}
}
