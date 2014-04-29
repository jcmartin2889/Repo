package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class OMR03R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OMR03R.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "NBEFR01JAN00LONDSWF1003         PP000SWAP  STD           30,573,169-100010100000000003057316R010100SW                   ";
		invalidCCN = "BOBX";
	}
}
