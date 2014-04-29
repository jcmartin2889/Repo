package com.misys.equation.bankfusion.lrp.engine;

import java.util.HashMap;

import com.misys.equation.common.internal.eapi.core.EQException;

/**
 * The BFTaskEngine is concrete implementation of AbsBFTaskEngine to interact with BankFusion. This class is used when running from
 * inside a microflow - e.g. during update processing.
 * 
 * @author yzobdabu
 */
public class BFTaskEngine extends AbsBFTaskEngine implements ITaskEngine
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BFTaskEngine.java 14401 2012-09-04 11:58:44Z williae1 $";

	/**
	 * Constuctor
	 * 
	 * @param zone
	 *            - the task zone
	 */
	public BFTaskEngine(String zone)
	{
		super(zone);
	}

	/**
	 * Call to microflow
	 * 
	 * @param microflowName
	 *            - the microflow name
	 * @param inputParms
	 *            - the input parameters
	 * 
	 * @return the output parameters
	 */
	public HashMap<?, ?> callMicroflow(String microflowName, HashMap<?, ?> inputParms) throws EQException
	{
		return bfToolbox.callMFExecuter(microflowName, inputParms);
	}

}
