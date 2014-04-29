package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class NER40R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: NER40R.java 11324 2011-06-29 11:21:04Z GOLDSMC1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "GB1*";
		invalidCCN = "IBAN00000000000000000000";
	}
}
