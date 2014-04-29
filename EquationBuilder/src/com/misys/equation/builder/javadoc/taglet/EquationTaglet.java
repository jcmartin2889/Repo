package com.misys.equation.builder.javadoc.taglet;

import java.util.Map;

import com.sun.tools.doclets.Taglet;

abstract public class EquationTaglet implements Taglet
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationTaglet.java 11334 2011-07-01 10:09:11Z lima12 $";

	public boolean inConstructor()
	{
		return true;
	}

	public boolean inField()
	{
		return true;
	}

	public boolean inMethod()
	{
		return true;
	}

	public boolean inOverview()
	{
		return true;
	}

	public boolean inPackage()
	{
		return true;
	}

	public boolean inType()
	{
		return true;
	}

	public boolean isInlineTag()
	{
		return true;
	}

	/**
	 * Register this Taglet.
	 * 
	 * @param tagletMap
	 *            the map to register this tag to.
	 */
	public static void register(EquationTaglet tag, Map<String, Taglet> tagletMap)
	{
		Taglet t = (Taglet) tagletMap.get(tag.getName());
		if (t != null)
		{
			tagletMap.remove(tag.getName());
		}
		tagletMap.put(tag.getName(), tag);
	}

}
