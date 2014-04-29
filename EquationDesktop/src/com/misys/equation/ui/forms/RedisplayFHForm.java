package com.misys.equation.ui.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RedisplayFHForm extends ActionForm
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RedisplayFHForm.java 10599 2011-03-03 13:53:08Z MACDONP1 $";

	/** Serial UID */
	private static final long serialVersionUID = -3779677384843241588L;

	// name of the function handler;
	private String name;
	private String removechild;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getRemovechild()
	{
		return removechild;
	}

	public void setRemovechild(String removechild)
	{
		this.removechild = removechild;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		ActionErrors ae = new ActionErrors();
		return ae;
	}
}
