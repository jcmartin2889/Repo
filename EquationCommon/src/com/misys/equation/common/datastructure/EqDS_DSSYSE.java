package com.misys.equation.common.datastructure;

import com.misys.equation.common.access.EquationDataStructure;
import com.misys.equation.common.utilities.Toolbox;

public class EqDS_DSSYSE extends EquationDataStructure
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqDS_DSSYSE.java 12207 2011-11-01 15:22:56Z lima12 $";

	private static final long serialVersionUID = 1L;

	// Field names
	public final static String CCUS = "$CCUS";
	public final static String CCLC = "$CCLC";
	public final static String SOVD = "$SOVD";
	public final static String SOPD = "$SOPD";
	public final static String BOVD = "$BOVD";
	public final static String BOPD = "$BOPD";
	public final static String BOC = "$BOC";
	public final static String CSDT = "$CSDT";
	public final static String CDLP = "$CDLP";
	public final static String NDLP = "$NDLP";
	public final static String BCCYA = "$BCCYA";
	public final static String BCCYN = "$BCCYN";
	public final static String DATFM = "$DATFM";
	public final static String DECPT = "$DECPT";
	public final static String INTPT = "$INTPT";
	public final static String SGNCP = "$SGNCP";
	public final static String SGNCN = "$SGNCN";
	public final static String SLSEP = "$SLSEP";
	public final static String BCKV = "$BCKV";
	public final static String FWDV = "$FWDV";
	public final static String DOG = "$DOG";
	public final static String DNBD = "$DNBD";
	public final static String LMCCY = "$LMCCY";
	public final static String LMSCG = "$LMSCG";
	public final static String LMSCN = "$LMSCN";
	public final static String SSCGS = "$SSCGS";
	public final static String SSCGE = "$SSCGE";
	public final static String SSCNS = "$SSCNS";
	public final static String SSCNE = "$SSCNE";
	public final static String CNEUR = "$CNEUR";
	public final static String DSPZL = "$DSPZL";
	public final static String SWADT = "$SWADT";
	public final static String IRT = "$IRT";
	public final static String LCHCG = "$LCHCG";
	public final static String LCHCN = "$LCHCN";
	public final static String ECNAI = "$ECNAI";
	public final static String ECNAL = "$ECNAL";
	public final static String EFX = "$EFX";
	public final static String EGUAR = "$EGUAR";
	public final static String DSPNA = "$DSPNA";
	public final static String SUPCG = "$SUPCG";
	public final static String SUPCN = "$SUPCN";
	public final static String SUPGR = "$SUPGR";
	public final static String SUPCS = "$SUPCS";
	public final static String XDDV = "$XDDV";
	public final static String XRPM = "$XRPM";
	public final static String XPRS = "$XPRS";
	public final static String XSPA = "$XSPA";
	public final static String CDFTL = "$CDFTL";
	public final static String SFXT = "$SFXT";
	public final static String SMMP = "$SMMP";
	public final static String ESE = "$ESE";
	public final static String ESPEI = "$ESPEI";
	public final static String ESMCP = "$ESMCP";
	public final static String ESDTL = "$ESDTL";
	public final static String DSTLF = "$DSTLF";
	public final static String DACPT = "$DACPT";
	public final static String JTOKN = "$JTOKN";
	public final static String PDUSR = "$PDUSR";
	public final static String ZLDATE = "ZLDATE";
	public final static String PDATE = "$PDATE";
	public final static String PDOC = "$PDOC";
	public final static String TDATE = "$TDATE";
	public final static String TDOC = "$TDOC";
	public final static String YDATE = "$YDATE";
	public final static String YDOC = "$YDOC";
	public final static String DDIC = "$DDIC";
	public final static String LWMSK = "$LWMSK";
	public final static String DAYFT = "$DAYFT";
	public final static String LCDCD = "$LCDCD";
	public final static String LCCY = "$LCCY";
	public final static String PPMS = "$PPMS";
	public final static String PPMSC = "$PPMSC";
	public final static String NPMS = "$NPMS";
	public final static String NPMSC = "$NPMSC";
	public final static String PYRS = "$PYRS";
	public final static String PYRSC = "$PYRSC";
	public final static String NYRS = "$NYRS";
	public final static String NYRSC = "$NYRSC";
	public final static String RLPLD = "$RLPLD";
	public final static String CLOFF = "$CLOFF";
	public final static String RLOBE = "$RLOBE";
	public final static String BNKLN = "$BNKLN";
	public final static String RLACS = "$RLACS";
	public final static String RLRET = "$RLRET";
	public final static String PCHRO = "$PCHRO";
	public final static String PCHRM = "$PCHRM";
	public final static String ZUDATE = "ZUDATE";
	public final static String MTRP = "$MTRP";
	public final static String BDOM = "$BDOM";
	public final static String MIR = "$MIR";
	public final static String ECT = "$ECT";
	public final static String ERT = "$ERT";
	public final static String SBDTE = "$SBDTE";
	public final static String SFDTE = "$SFDTE";
	public final static String SINP = "$SINP";
	public final static String LBDM = "$LBDM";
	public final static String CLS = "$CLS";
	public final static String XFA = "$XFA";
	public final static String XFB = "$XFB";
	public final static String XFC = "$XFC";
	public final static String XFD = "$XFD";
	public final static String XFE = "$XFE";
	public final static String XFF = "$XFF";
	public final static String MSRRT = "$MSRRT";
	public final static String CDR = "$CDR";
	public final static String CDF = "$CDF";
	public final static String RPEN = "$RPEN";
	public final static String EDLC = "$EDLC";
	public final static String EDBC = "$EDBC";
	public final static String FMLC = "$FMLC";
	public final static String FXLM = "$FXLM";
	public final static String MMLM = "$MMLM";
	public final static String MLF = "$MLF";
	public final static String IDA = "$IDA";
	public final static String MDR = "$MDR";
	public final static String MDIR = "$MDIR";
	public final static String SLB = "$SLB";
	public final static String SPME = "$SPME";
	public final static String SDLC = "$SDLC";
	public final static String PPSD = "$PPSD";
	public final static String PYSM = "$PYSM";
	public final static String ADU = "$ADU";
	public final static String RDA = "$RDA";
	public final static String LRC = "$LRC";
	public final static String SDOC = "$SDOC";
	public final static String XEA = "$XEA";
	public final static String DFDT = "$DFDT";
	public final static String BPWD = "$BPWD";
	public final static String PRSEQ = "$PRSEQ";
	public final static String LCRI = "$LCRI";
	public final static String BCRI = "$BCRI";
	public final static String CBM = "$CBM";
	public final static String CBRP = "$CBRP";
	public final static String CERP = "$CERP";
	public final static String DEIM = "$DEIM";
	public final static String LNMA = "$LNMA";
	public final static String EANL = "$EANL";
	public final static String EANE = "$EANE";
	public final static String EANI = "$EANI";
	public final static String CSLDB = "$CSLDB";
	public final static String CSETF = "$CSETF";
	public final static String ICHG = "$ICHG";
	public final static String CPRP = "$CPRP";
	public final static String LPWD = "$LPWD";
	public final static String PDH = "$PDH";
	public final static String PDLR = "$PDLR";
	public final static String PDO = "$PDO";
	public final static String CNO = "$CNO";
	public final static String RCRS = "$RCRS";
	public final static String MCRS = "$MCRS";
	public final static String AHCF = "$AHCF";
	public final static String CMCF = "$CMCF";
	public final static String AGCN = "$AGCN";
	public final static String RCCN = "$RCCN";
	public final static String APS = "$APS";
	public final static String PSLIB = "$PSLIB";
	public final static String AFSM = "$AFSM";
	public final static String AISM = "$AISM";
	public final static String DPSR = "$DPSR";
	public final static String SPSR = "$SPSR";
	public final static String PDSZL = "$PDSZL";
	public final static String PSPRI = "$PSPRI";
	public final static String CCRP = "$CCRP";
	public final static String CLAUT = "$CLAUT";
	public final static String FXRV = "$FXRV";
	public final static String EXRSZ = "$EXRSZ";
	public final static String TII = "$TII";
	public final static String ALALP = "$ALALP";
	public final static String ALPCD = "$ALPCD";
	public final static String RLFRS = "$RLFRS";
	public final static String DAUT = "$DAUT";
	public final static String DLM = "$DLM";
	public final static String DUM1 = "$DUM1";
	public final static String DUM2 = "$DUM2";
	public final static String DUM3 = "$DUM3";

	/**
	 * Constructor to create the DSSYSE data structure
	 */
	public EqDS_DSSYSE()
	{
		super("DSSYSE", Toolbox.DEF_CCSID);

		// create the DS
		createCharacterField(CCUS, "Cashier Charges Customer", 1 - 1, 6);
		createCharacterField(CCLC, "Cashier Charges Customer Location", 7 - 1, 3);
		createCharacterField(SOVD, "S/o Non-business day value date - reserved", 10 - 1, 1);
		createCharacterField(SOPD, "S/o Non-business day processind date - reserved", 11 - 1, 1);
		createCharacterField(BOVD, "B/o Non-business day value date", 12 - 1, 1);
		createCharacterField(BOPD, "B/o Non-business daye processing date", 13 - 1, 1);
		createCharacterField(BOC, "B/o confirmations", 14 - 1, 1);
		createCharacterField(CSDT, "Compensate swap deal type", 15 - 1, 3);
		createCharacterField(CDLP, "Compensation group deal type", 18 - 1, 3);
		createCharacterField(NDLP, "Netting group deal type", 21 - 1, 3);
		createCharacterField(BCCYA, "Base currency", 24 - 1, 3);
		createZonedField(BCCYN, "Base currency code", 27 - 1, 3, 0);
		createCharacterField(DUM2, "Dummy 2", 30 - 1, 3);
		createCharacterField(DATFM, "User date format", 33 - 1, 3);
		createCharacterField(DECPT, "Decimal seperators", 36 - 1, 1);
		createCharacterField(INTPT, "Integer seperators", 37 - 1, 1);
		createCharacterField(SGNCP, "Credit mnemonic", 38 - 1, 2);
		createCharacterField(SGNCN, "Debit mnemonic", 40 - 1, 2);
		createCharacterField(SLSEP, "Selection seperator", 42 - 1, 1);
		createZonedField(BCKV, "Max no of days back value", 43 - 1, 3, 0);
		createZonedField(FWDV, "Max no of days forward value", 46 - 1, 3, 0);
		createZonedField(DOG, "Days of grace", 49 - 1, 2, 0);
		createZonedField(DNBD, "Days to next business day", 51 - 1, 2, 0);
		createCharacterField(LMCCY, "Default limit currency", 53 - 1, 3);
		createCharacterField(LMSCG, "Default limit structure for customer/group", 56 - 1, 5);
		createCharacterField(LMSCN, "Default limit structure for country", 61 - 1, 5);
		createZonedField(SSCGS, "Starting SS reference for customer/group", 66 - 1, 3, 0);
		createZonedField(SSCGE, "Ending SS reference for customer/group", 69 - 1, 3, 0);
		createZonedField(SSCNS, "Starting SS reference for country", 72 - 1, 3, 0);
		createZonedField(SSCNE, "Ending SS reference for country", 75 - 1, 3, 0);
		createCharacterField(CNEUR, "Country exposures updated in real-time (Y/N)", 78 - 1, 1);
		createCharacterField(DSPZL, "Display zero lines (Y/N)", 79 - 1, 1);
		createCharacterField(SWADT, "SWIFT address type", 80 - 1, 1);
		createCharacterField(IRT, "Use rate table for today (1) or value date (2)", 81 - 1, 1);
		createCharacterField(LCHCG, "Check customer/group limits", 82 - 1, 1);
		createCharacterField(LCHCN, "Check country limits", 83 - 1, 1);
		createCharacterField(ECNAI, "Record exposure against internal risk country", 84 - 1, 1);
		createCharacterField(ECNAL, "Record exposure against primary debtor country", 85 - 1, 1);
		createCharacterField(EFX, "Include same day FX deals in exposure", 86 - 1, 1);
		createCharacterField(EGUAR, "Record guarantees given against single category", 87 - 1, 1);
		createCharacterField(DSPNA, "Display negative availability as zero", 88 - 1, 1);
		createCharacterField(SUPCG, "Suppress all customer/group limit checks", 89 - 1, 1);
		createCharacterField(SUPCN, "Suppress all country limit checks", 90 - 1, 1);
		createCharacterField(SUPGR, "Suppress display of availability plus g'tees", 91 - 1, 1);
		createCharacterField(SUPCS, "Suppress cashier system limit checking", 92 - 1, 1);
		createCharacterField(XDDV, "Exchange deal date validation mode", 93 - 1, 1);
		createCharacterField(XRPM, "Exchange deal forward revaluation posting mode", 94 - 1, 1);
		createCharacterField(XPRS, "Exchange deal P&L realised on spot date", 95 - 1, 1);
		createCharacterField(XSPA, "Exchange spot position accounting required", 96 - 1, 1);
		createCharacterField(CDFTL, "Create default customer limits (Y, N, A)", 97 - 1, 1);
		createCharacterField(SFXT, "Suppress FX trading limits (Y,N)", 98 - 1, 1);
		createCharacterField(SMMP, "Suppress MM placement limits (Y,N)", 99 - 1, 1);
		createCharacterField(ESE, "Enable Enhanced Security (Y/N)", 100 - 1, 1);
		createZonedField(ESPEI, "Enhanced Security Password Expiry Inetrval", 101 - 1, 3, 0);
		createZonedField(ESMCP, "Minimum number of Characters for Password", 104 - 1, 1, 0);
		createZonedField(ESDTL, "Default Time Limit for Authorisation request", 105 - 1, 3, 0);
		createCharacterField(DSTLF, "Default Settlement Formula   ", 108 - 1, 1);
		createCharacterField(DACPT, "Default access point         ", 109 - 1, 10);
		createCharacterField(JTOKN, "Journal entry token for stop/suspend", 119 - 1, 2);
		createZonedField(PDUSR, "Process date user format", 121 - 1, 7, 0);
		createCharacterField(ZLDATE, "Date in report format", 128 - 1, 7);
		createZonedField(PDATE, "Process date in nYMD format", 135 - 1, 7, 0);
		createZonedField(PDOC, "Process date in DoC format", 142 - 1, 5, 0);
		createZonedField(TDATE, "Next processing date nYMD format", 147 - 1, 7, 0);
		createZonedField(TDOC, "Next processing date DoC format", 154 - 1, 5, 0);
		createZonedField(YDATE, "Last processing date nYMD format", 159 - 1, 7, 0);
		createZonedField(YDOC, "Last processing date DoC format", 166 - 1, 5, 0);
		createCharacterField(DDIC, "Local currency dictionary codes for month", 171 - 1, 36);
		createZonedField(LWMSK, "Local currency week day mask", 207 - 1, 7, 0);
		createZonedField(DAYFT, "Local currency day of fortnight", 214 - 1, 2, 0);
		createCharacterField(LCDCD, "Local currency code (Alpha)", 216 - 1, 3);
		createZonedField(LCCY, "Local currency numeric", 219 - 1, 3, 0);
		createZonedField(PPMS, "Profit period start date nYMD format", 222 - 1, 7, 0);
		createZonedField(PPMSC, "Profit period start date DoC format", 229 - 1, 5, 0);
		createZonedField(NPMS, "Next profit period start date nYMD format", 234 - 1, 7, 0);
		createZonedField(NPMSC, "Next profit period start date DoC format", 241 - 1, 5, 0);
		createZonedField(PYRS, "Profit year start date nYMD format", 246 - 1, 7, 0);
		createZonedField(PYRSC, "Profit year start date DoC format", 253 - 1, 5, 0);
		createZonedField(NYRS, "Next profit year start date nYMD format", 258 - 1, 7, 0);
		createZonedField(NYRSC, "Next profit year start date DoC format", 265 - 1, 5, 0);
		createCharacterField(RLPLD, "P&L Daily", 270 - 1, 1);
		createZonedField(CLOFF, "Chief local office", 271 - 1, 4, 0);
		createCharacterField(RLOBE, "Off Balance", 275 - 1, 1);
		createCharacterField(BNKLN, "Banks Local Name", 276 - 1, 20);
		createCharacterField(RLACS, "Auto Customer Status", 296 - 1, 1);
		createZonedField(RLRET, "Retention period", 297 - 1, 3, 0);
		createCharacterField(PCHRO, "Prompt single substitution character", 300 - 1, 1);
		createCharacterField(PCHRM, "Prompt multiple substitution character", 301 - 1, 1);
		createCharacterField(ZUDATE, "Screen date for FX/MM system", 302 - 1, 11);
		createZonedField(MTRP, "Default posting retention", 313 - 1, 2, 0);
		createZonedField(BDOM, "Business day of month", 315 - 1, 2, 0);
		createZonedField(MIR, "Minimum interest retention", 317 - 1, 2, 0);
		createZonedField(ECT, "Exchange rate conversion tolerance", 319 - 1, 4, 0);
		createZonedField(ERT, "Exchange rate tolerance", 323 - 1, 2, 0);
		createCharacterField(DUM3, "Dummy 3", 325 - 1, 1);
		createZonedField(SBDTE, "System backvalue date in nYMD format", 326 - 1, 7, 0);
		createZonedField(SFDTE, "System forward date in nYMD format", 333 - 1, 7, 0);
		createCharacterField(SINP, "System subfile selection character", 340 - 1, 1);
		createCharacterField(LBDM, "Last business day of month (Y/N)", 341 - 1, 1);
		createZonedField(CLS, "No of postings for overflow statement", 342 - 1, 2, 0);
		createCharacterField(XFA, "Field blank before maintenance (hex FA)", 344 - 1, 1);
		createCharacterField(XFB, "Multi item transaction (report together)", 345 - 1, 1);
		createCharacterField(XFC, "Before/after image separator (hex FC)", 346 - 1, 1);
		createCharacterField(XFD, "Multi item trans (not reported together)", 347 - 1, 1);
		createCharacterField(XFE, "Mark end of data block (hex FE)", 348 - 1, 1);
		createCharacterField(XFF, "Mark end of transaction (hex FF)", 349 - 1, 1);
		createZonedField(MSRRT, "MS Security price tolerance", 350 - 1, 11, 7);
		createZonedField(CDR, "Cancelled deal retention", 361 - 1, 2, 0);
		createCharacterField(CDF, "Calendar days for statement frequency", 363 - 1, 1);
		createCharacterField(RPEN, "Rollover at penalty rate", 364 - 1, 1);
		createZonedField(EDLC, "Edit code for local currency", 365 - 1, 1, 0);
		createZonedField(EDBC, "Edit code for base currency", 366 - 1, 1, 0);
		createCharacterField(FMLC, "FX/MM limit checking (Y/N)", 367 - 1, 1);
		createCharacterField(FXLM, "FX limit currency", 368 - 1, 3);
		createCharacterField(MMLM, "MM limit currency", 371 - 1, 3);
		createCharacterField(MLF, "Multi-language user interface (Y/N)", 374 - 1, 1);
		createZonedField(IDA, "Days in advance interest advised", 375 - 1, 2, 0);
		createZonedField(MDR, "Default deal retention", 377 - 1, 2, 0);
		createZonedField(MDIR, "Minimum deal interest retention", 379 - 1, 2, 0);
		createCharacterField(SLB, "Statistics on ledger balance (Y/N)", 381 - 1, 1);
		createCharacterField(SPME, "Statistics on prft/cal./bth month end (1/2/3)", 382 - 1, 1);
		createZonedField(SDLC, "Spot date local run day calendar", 383 - 1, 7, 0);
		createZonedField(PPSD, "Profit period start day", 390 - 1, 2, 0);
		createZonedField(PYSM, "Profit year start month", 392 - 1, 2, 0);
		createCharacterField(ADU, "Deal authorisation by different user (Y/N)", 394 - 1, 1);
		createZonedField(RDA, "Days in advance of rollover advised", 395 - 1, 2, 0);
		createCharacterField(LRC, "Local currency residence code", 397 - 1, 2);
		createZonedField(SDOC, "Spot date in day of century format (run day cal)", 399 - 1, 5, 0);
		createCharacterField(XEA, "Mark end of transaction (hex FF)", 404 - 1, 1);
		createCharacterField(DFDT, "Deal authorisation default date", 405 - 1, 1);
		createZonedField(BPWD, "Base currency edit code to the power of 10", 406 - 1, 5, 0);
		createZonedField(PRSEQ, "Pay/receive sequence number", 411 - 1, 3, 0);
		createCharacterField(LCRI, "Local currency rounding indicator", 414 - 1, 1);
		createCharacterField(BCRI, "Base currency rounding indicator", 415 - 1, 1);
		createCharacterField(CBM, "Cheque book management active", 416 - 1, 1);
		createCharacterField(CBRP, "Cheque book retention period", 417 - 1, 4);
		createCharacterField(CERP, "Cheque exceptions retention period", 421 - 1, 4);
		createZonedField(DEIM, "Draft Expiry in Months", 425 - 1, 2, 0);
		createCharacterField(LNMA, "Arabic language code", 427 - 1, 2);
		createZonedField(EANL, "Length of External Account Number", 429 - 1, 2, 0);
		createCharacterField(EANE, "Edit Pattern for External Account Number", 431 - 1, 25);
		createCharacterField(EANI, "Input in External Account Number Format (Y/N)", 456 - 1, 1);
		createCharacterField(CSLDB, "Cashier System - Local Database (Y/N)", 457 - 1, 1);
		createCharacterField(CSETF, "Cashier System - Enable Trickle Feed (Y/N)", 458 - 1, 1);
		createCharacterField(ICHG, "Include charges in APR", 459 - 1, 1);
		createCharacterField(CPRP, "Clean Payments retention period", 460 - 1, 4);
		createZonedField(LPWD, "Local currency edit code to the power of 10", 464 - 1, 4, 0);
		createCharacterField(PDH, "P/D House Cheque Value Date Mode", 468 - 1, 1);
		createCharacterField(PDLR, "P/D Local/Remittance Cheque Value Date Mode", 469 - 1, 1);
		createCharacterField(PDO, "P/D Outport Cheque Value Date Mode", 470 - 1, 1);
		createCharacterField(CNO, "Customer mnemonic or number", 471 - 1, 1);
		createCharacterField(RCRS, "Recalc constant repayment schedules", 472 - 1, 1);
		createCharacterField(DUM1, "", 473 - 1, 1);
		createCharacterField(MCRS, "Months for constant repayment schedule review", 474 - 1, 2);
		createCharacterField(AHCF, "Print ad-hoc netting group confirmations", 476 - 1, 1);
		createCharacterField(CMCF, "Print compensation group confirmations", 477 - 1, 1);
		createCharacterField(AGCN, "System Generated Customer Numbers", 478 - 1, 1);
		createCharacterField(RCCN, "Re use Closed Customer Numbers", 479 - 1, 1);
		createCharacterField(APS, "Accumulate profitability statistics", 480 - 1, 1);
		createCharacterField(PSLIB, "Profitability statistics library exists", 481 - 1, 1);
		createCharacterField(AFSM, "Accumulate FX profitability stats monthly", 482 - 1, 1);
		createCharacterField(AISM, "Accumulate interest profitability stats monthly", 483 - 1, 1);
		createCharacterField(DPSR, "Detailed profitability statistics retention", 484 - 1, 2);
		createCharacterField(SPSR, "Summary profitability statistics retention", 486 - 1, 2);
		createCharacterField(PDSZL, "Display 0 lines in Profitability enquiries", 488 - 1, 1);
		createCharacterField(PSPRI, "Profitability stats archive/backup priority", 489 - 1, 2);
		createCharacterField(CCRP, "Closed customer retention period", 491 - 1, 4);
		createCharacterField(CLAUT, "Credit limit authorisation required", 495 - 1, 1);
		createCharacterField(FXRV, "Revalue O/N FX positions using spot rate", 496 - 1, 1);
		createCharacterField(EXRSZ, "FX rate field size: integers, decimal places", 497 - 1, 3);
		createCharacterField(TII, "TI mode", 500 - 1, 1);
		createCharacterField(ALALP, "Allow Alpha Customer Numbers                ", 501 - 1, 1);
		createCharacterField(ALPCD, "Alpha customer number check digit processing", 502 - 1, 1);
		createCharacterField(RLFRS, "Retail Lending no. of future repayments on stmt", 503 - 1, 2);
		createCharacterField(DAUT, "Re-authorise deal maintenance flag", 505 - 1, 1);
		createZonedField(DLM, "Date last maintained", 506 - 1, 7, 0);

		// final setup
		initialDefaultValue();
	}
}
