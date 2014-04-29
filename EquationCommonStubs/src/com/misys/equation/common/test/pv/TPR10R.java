package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class TPR10R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TPR10R.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		// validCCN = "        ATLANTNIR                                            1000103                      Y";
		validCCN = "        ATLANTNIR07JAN00  ATLANTIC INDUSTRIAL NIAROBI        1000107CORP CAD1001231KBSLYYNY";
		invalidCCN = "        ATLANTNIR                                            1000103                      X";
	}
}
