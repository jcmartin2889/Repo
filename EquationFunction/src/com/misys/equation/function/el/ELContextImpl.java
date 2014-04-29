package com.misys.equation.function.el;

import javax.el.BeanELResolver;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.FunctionMapper;
import javax.el.VariableMapper;

import org.apache.el.ValueExpressionLiteral;

public class ELContextImpl extends ELContext
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ELContextImpl.java 6793 2010-03-31 12:10:20Z deroset $";
	private final BeanELResolver resolver = new BeanELResolver();
	private final EquationFunctionMapperImpl functionMapper = new EquationFunctionMapperImpl();
	private final VariableMapper variableMapper = new VariableMapperImpl();

	@Override
	public ELResolver getELResolver()
	{
		return resolver;
	}

	@Override
	public FunctionMapper getFunctionMapper()
	{
		return functionMapper;
	}
	@Override
	public VariableMapper getVariableMapper()
	{
		return variableMapper;
	}

	public void bind(String variable, Object obj)
	{
		variableMapper.setVariable(variable, new ValueExpressionLiteral(obj, Object.class));
	}
}
