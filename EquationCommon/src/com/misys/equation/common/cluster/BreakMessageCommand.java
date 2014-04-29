package com.misys.equation.common.cluster;

import com.misys.equation.common.access.EquationCommonContext;

/**
 * A break message command for cluster support
 * 
 * Break messages are added using the SBK (Send Break Message) service
 */
public class BreakMessageCommand extends AbstractServerCommand
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BreakMessageCommand.java 17823 2014-01-30 09:23:00Z perkinj1 $";

	public static final String PARAM_USER = "USER";
	public static final String PARAM_SEVERITY = "SEVERITY";
	public static final String PARAM_MSG = "MSG";

	/**
	 * Adds a break message both on the local node, and distributes the command to the cluster nodes.
	 * 
	 * @param sessionId
	 *            session id
	 * @param user
	 *            user name
	 * @param severity
	 *            message severity
	 * @param message
	 *            message text
	 */
	public void perform(String sessionId, String user, String severity, String message)
	{
		CommandData replicatedCmd = new CommandData();
		replicatedCmd.setCommandId(CommandData.COMMAND_TYPE_BREAKMSG);
		replicatedCmd.getData().put(PARAM_USER, user);
		replicatedCmd.getData().put(PARAM_SEVERITY, severity);
		replicatedCmd.getData().put(PARAM_MSG, message);

		// Perform for the local node. This needs to be passed the sessionId to exclude the user's session:
		EquationCommonContext.getContext().addSessionMessageLocal(sessionId, user, severity, message);
		// Distribute to other nodes. No need to include the session id
		distribute(replicatedCmd);
	}

	/**
	 * Called on a destination node to apply the break message
	 */
	public void perform(CommandData commandData)
	{
		String user = commandData.getData().get(PARAM_USER);
		String severity = commandData.getData().get(PARAM_SEVERITY);
		String message = commandData.getData().get(PARAM_MSG);
		EquationCommonContext.getContext().addSessionMessageLocal(null, user, severity, message);
	}
}
