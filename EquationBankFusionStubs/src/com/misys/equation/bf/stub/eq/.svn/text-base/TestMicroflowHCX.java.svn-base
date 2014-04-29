package com.misys.equation.bf.stub.eq;

import bf.com.misys.eqf.types.header.ServiceRqHeader;
import bf.com.misys.equation.bankfusion.service.cmn.EQ_CMN_addMaintainHoldCodeHCX_SRV;

import com.misys.equation.bf.TestMicroflowEQ;

/**
 * Equation Service HCX
 */
public class TestMicroflowHCX extends TestMicroflowEQ
{
	public static final String CVS_REVISION = "$Revision$"; // NON-NLS-$1

	static
	{
		com.trapedza.bankfusion.utils.Tracer.register(CVS_REVISION);
	}

	/**
	 * Main
	 * 
	 * @param arguments
	 *            - arguments
	 */
	public static void main(String arguments[])
	{
		try
		{
			new TestMicroflowHCX().start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Test data
	 */
	public void test()
	{
		// Set details
		String optionId = "HCX";
		String microFlowName = "EQ_CMN_addMaintainHoldCodeHCX_SRV";

		// Set Service Header
		ServiceRqHeader serviceHeader = getServiceRqHeader(optionId);

		// Set Service Data
		EQ_CMN_addMaintainHoldCodeHCX_SRV bf_functionData = new EQ_CMN_addMaintainHoldCodeHCX_SRV();
		bf_functionData.setHCI_HRC_holdCode("BFX");
		bf_functionData.setHCI_HRD_holdDescription("Test invoke from Java world 2");

		// Execute
		executeEquationService(microFlowName, serviceHeader, bf_functionData);

		// assume success
		assertEquals(true, true);
	}

}
