package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class MJR10R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MJR10R.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "0543123451002SQC01       000000000000000                                                                                1060930";
		// 12345678901234567890123456789012345678901234567890123456789012345678901234567890
		invalidCCN = "00000000010011           00000000000000                                                                                 0001231";
	}
}
