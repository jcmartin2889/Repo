package com.misys.equation.common.messages;

import java.beans.PropertyVetoException;
import java.io.IOException;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.misys.equation.common.utilities.AS400Toolbox;
import com.misys.equation.common.utilities.EquationLogger;

public class MessageService
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MessageService.java 15470 2013-03-08 15:56:33Z whittap1 $";
	private static final EquationLogger LOG = new EquationLogger(MessageService.class);
	private AS400 iSeries = null;

	public MessageService(MessageServiceConfig config)
	{
		iSeries = AS400Toolbox.getAS400(config.getHost(), config.getUserName(), config.getPassword());
	}

	public void sendBreakMessage(String terminal, String message)
	{
		message = message.replaceAll("'", "''");
		CommandCall cmd = new CommandCall(iSeries);
		try
		{
			cmd.run("SNDBRKMSG MSG('" + message + "') TOMSGQ(" + terminal + ")");
		}
		catch (PropertyVetoException e)
		{
			LOG.error("sendBreakMessage(String, String)", e); //$NON-NLS-1$
		}
		catch (ErrorCompletingRequestException e)
		{
			LOG.error("sendBreakMessage(String, String)", e); //$NON-NLS-1$
		}
		catch (InterruptedException e)
		{
			LOG.error("sendBreakMessage(String, String)", e); //$NON-NLS-1$
		}
		catch (AS400SecurityException e)
		{
			LOG.error("sendBreakMessage(String, String)", e); //$NON-NLS-1$
		}
		catch (IOException e)
		{
			LOG.error("sendBreakMessage(String, String)", e); //$NON-NLS-1$
		}
		AS400Message[] messageList = cmd.getMessageList();
		int numberOfMessages = messageList.length;
		for (int i = 0; i < numberOfMessages; i++)
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("sendBreakMessage(String, String) - Message " + i + " = " + messageList[i].getText()); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
	}
}
