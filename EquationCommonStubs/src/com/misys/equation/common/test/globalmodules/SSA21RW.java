package com.misys.equation.common.test.globalmodules;

import com.misys.equation.common.test.EquationTestCaseGlobalModuleMetaData;
import com.misys.equation.common.utilities.Toolbox;

public class SSA21RW extends EquationTestCaseGlobalModuleMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SSA21RW.java 11511 2011-07-26 12:25:21Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		// 250,000 amount - see WRKTBL QSYS/QEBCDIC on iSeries for pre-conversion hex values required
		String amount = Toolbox.cvtHexToStr("000000000226000C"); // hex value of 000000000250000+ before EBCDIC
		// conversion
		String seqNum5 = Toolbox.cvtHexToStr("000125"); // 00016C in EBCDIC
		String seqNum7 = Toolbox.cvtHexToStr("00000125"); // 0000016C in EBCDIC
		String numPostingEntries = Toolbox.cvtHexToStr("00001C");
		String mode = "V";
		validCCN = "                 0132100000001LONDBLOS "
						+ seqNum5
						+ "1000105CITYNARRATIVE       "
						+ amount
						+ "  510USDAABBBCCC"
						+ numPostingEntries
						+ "Narrative details line 1----------xNarrative details line 2----------xNarrative details line 3----------xNarrative details line 4----------xNYN                                                                       NUSD"
						+ amount
						+ "                         "
						+ seqNum7
						+ "                                                                                                                                                                 "
						+ mode;

		invalidCCN = "                 0132100000001LONDBLOS "
						+ seqNum5
						+ "1000105CITYNARRATIVE       "
						+ amount
						+ "  51XUSDAABBBCCC"
						+ numPostingEntries
						+ "Narrative details line 1----------xNarrative details line 2----------xNarrative details line 3----------xNarrative details line 4----------xNNN                                                                       NUSD"
						+ amount
						+ "                         "
						+ seqNum7
						+ "                                                                                                                                                                 "
						+ mode;
	}

}
