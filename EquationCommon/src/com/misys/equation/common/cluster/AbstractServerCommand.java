package com.misys.equation.common.cluster;

import com.misys.equation.common.access.EquationCommonContext;

/**
 * The base class for any command classes
 */
public abstract class AbstractServerCommand
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractServerCommand.java 17823 2014-01-30 09:23:00Z perkinj1 $";

	public static final String PARAM_SYSTEM = "SYSTEM";
	public static final String PARAM_UNIT = "UNIT";

	String command = null;

	/**
	 * Publishes the command to the other nodes in the cluster
	 * 
	 * This will have no effect if clustering is not configured.
	 * 
	 * @param command
	 *            The Command instance
	 */
	protected void distribute(CommandData command)
	{
		if (EquationCommonContext.getContext().getClusterService() != null)
		{
			EquationCommonContext.getContext().getClusterService().publishCommand(command);
		}
	}

	/**
	 * Called on the remote nodes to apply the command.
	 * 
	 * @param commandData
	 *            The Command instance
	 */
	public abstract void perform(CommandData commandData);
}
