package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class LTR01R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LTR01R.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "CITYCDDY2C2101      YOUNG GRPITL       P                               1000104PT1000104PR000900";
		invalidCCN = "CITYCDDY2C2101      YOUNG GRPITL       P                               1000104PT1000104PR000901";
	}
}