package com.misys.equation.function.el;

import java.util.HashMap;
import java.util.Map;

import javax.el.ValueExpression;
import javax.el.VariableMapper;

public class VariableMapperImpl extends VariableMapper
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: VariableMapperImpl.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private final Map<String, ValueExpression> variables = new HashMap<String, ValueExpression>();
	@Override
	public ValueExpression resolveVariable(String s)
	{
		return variables.get(s);
	}
	@Override
	public ValueExpression setVariable(String s, ValueExpression valueExpression)
	{
		return (variables.put(s, valueExpression));
	}
}
