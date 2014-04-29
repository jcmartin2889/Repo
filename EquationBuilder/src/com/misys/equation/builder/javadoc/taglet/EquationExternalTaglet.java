package com.misys.equation.builder.javadoc.taglet;

import java.util.Map;

import com.sun.javadoc.Tag;

public class EquationExternalTaglet extends EquationTaglet
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationExternalTaglet.java 11334 2011-07-01 10:09:11Z lima12 $";
	public static final String NAME = "equation.external";

	public boolean inField()
	{
		return false;
	}

	public boolean isInlineTag()
	{
		return false;
	}

	public String getName()
	{
		return NAME;
	}

	public String toString(Tag arg0)
	{
		return "";
	}

	public String toString(Tag[] arg0)
	{
		return "";
	}

	/**
	 * Register this Taglet.
	 * 
	 * @param tagletMap
	 *            the map to register this tag to.
	 */
	public static void register(Map tagletMap)
	{
		register(new EquationExternalTaglet(), tagletMap);
	}

}