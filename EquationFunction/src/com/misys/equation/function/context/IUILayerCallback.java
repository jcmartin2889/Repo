package com.misys.equation.function.context;

/**
 * An interface used to notify the UI (EquationDesktop) layer
 * 
 * The EquationDesktopContext will set this into the EquationFunctionContext
 * 
 * This mechanism allows unit removal to be initiated in the Common layer
 */
public interface IUILayerCallback
{
	public void removeAllUsersOfUnit(String systemId, String unitId);
}
