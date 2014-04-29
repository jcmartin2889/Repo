package com.misys.equation.common.globalcustomers;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.common.internal.eapi.core.EQMessage;

public abstract class GlobalBean
{
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalBean.java 10793 2011-04-14 13:45:53Z whittap1 $";
	private List<EQMessage> messages = new ArrayList<EQMessage>();

	public List<EQMessage> getMessages()
	{
		return messages;
	}

	public void setMessages(List<EQMessage> messages)
	{
		this.messages = messages;
	}
}
