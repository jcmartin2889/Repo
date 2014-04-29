package com.misys.equation.common.access;

/**
 * An interface used to notify the Function layer.
 * 
 * EquationFunctionContext will set this into the EquationCommonContext
 * 
 * This mechanism allows unit removal to be initiated in the Common layer
 */
public interface IFunctionLayerCallback
{
	public void removeAllUsersOfUnit(String systemId, String unitId);
}
