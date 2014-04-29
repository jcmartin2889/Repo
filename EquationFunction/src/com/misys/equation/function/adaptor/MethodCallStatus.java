package com.misys.equation.function.adaptor;

public class MethodCallStatus
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MethodCallStatus.java 11962 2011-09-30 09:38:25Z rpatrici $";

	// determines whether default method of the abstract adaptor has been executed or not to allow system to determine
	// whether user has overridden the methods or not
	protected boolean abstractDefaultMethodExecute = true;
	protected boolean abstractValidateMethodExecute = true;
	protected boolean abstractPreUpdateMethodExecute = true;
	protected boolean abstractPostUpdateMethodExecute = true;
	protected boolean abstractPreLoadMethodExecute = true;
	protected boolean abstractPostLoadMethodExecute = true;
	protected boolean abstractPostLoadEFCMethodExecute = true;
	protected boolean abstractOnCancelMethodExecute = true;
	protected boolean abstractOnFinalUpdateMethodExecute = true;
	protected boolean abstractGetLRPTemplateProcessDetailMethodExecute = true;

	/**
	 * Return whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the
	 * user has overridden the methods or not
	 * 
	 * @return true if default method of the abstract adaptor has been executed
	 */
	public boolean isAbstractPreLoadMethodExecute()
	{
		return abstractPreLoadMethodExecute;
	}

	/**
	 * Set whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the user
	 * has overridden the methods or not
	 * 
	 * @param abstractPreLoadMethodExecute
	 *            - true if default method has been executed
	 */
	public void setAbstractPreLoadMethodExecute(boolean abstractPreLoadMethodExecute)
	{
		this.abstractPreLoadMethodExecute = abstractPreLoadMethodExecute;
	}

	/**
	 * Return whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the
	 * user has overridden the methods or not
	 * 
	 * @return true if default method of the abstract adaptor has been executed
	 */
	public boolean isAbstractPostLoadMethodExecute()
	{
		return abstractPostLoadMethodExecute;
	}

	/**
	 * Set whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the user
	 * has overridden the methods or not
	 * 
	 * @param abstractPostLoadMethodExecute
	 *            - true if default method has been executed
	 */
	public void setAbstractPostLoadMethodExecute(boolean abstractPostLoadMethodExecute)
	{
		this.abstractPostLoadMethodExecute = abstractPostLoadMethodExecute;
	}

	/**
	 * Return whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the
	 * user has overridden the methods or not
	 * 
	 * @return true if default method of the abstract adaptor has been executed
	 */
	public boolean isAbstractDefaultMethodExecute()
	{
		return abstractDefaultMethodExecute;
	}

	/**
	 * Set whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the user
	 * has overridden the methods or not
	 * 
	 * @param abstractDefaultMethodExecute
	 *            - true if default method has been executed
	 */
	public void setAbstractDefaultMethodExecute(boolean abstractDefaultMethodExecute)
	{
		this.abstractDefaultMethodExecute = abstractDefaultMethodExecute;
	}

	/**
	 * Return whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the
	 * user has overridden the methods or not
	 * 
	 * @return true if default method of the abstract adaptor has been executed
	 */
	public boolean isAbstractValidateMethodExecute()
	{
		return abstractValidateMethodExecute;
	}

	/**
	 * Set whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the user
	 * has overridden the methods or not
	 * 
	 * @param abstractValidateMethodExecute
	 *            - true if default method has been executed
	 */
	public void setAbstractValidateMethodExecute(boolean abstractValidateMethodExecute)
	{
		this.abstractValidateMethodExecute = abstractValidateMethodExecute;
	}

	/**
	 * Return whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the
	 * user has overridden the methods or not
	 * 
	 * @return true if default method of the abstract adaptor has been executed
	 */
	public boolean isAbstractPreUpdateMethodExecute()
	{
		return abstractPreUpdateMethodExecute;
	}

	/**
	 * Set whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the user
	 * has overridden the methods or not
	 * 
	 * @param abstractPreUpdateMethodExecute
	 *            - true if default method has been executed
	 */
	public void setAbstractPreUpdateMethodExecute(boolean abstractPreUpdateMethodExecute)
	{
		this.abstractPreUpdateMethodExecute = abstractPreUpdateMethodExecute;
	}

	/**
	 * Return whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the
	 * user has overridden the methods or not
	 * 
	 * @return true if default method of the abstract adaptor has been executed
	 */
	public boolean isAbstractPostUpdateMethodExecute()
	{
		return abstractPostUpdateMethodExecute;
	}

	/**
	 * Set whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the user
	 * has overridden the methods or not
	 * 
	 * @param abstractPostUpdateMethodExecute
	 *            - true if default method has been executed
	 */
	public void setAbstractPostUpdateMethodExecute(boolean abstractPostUpdateMethodExecute)
	{
		this.abstractPostUpdateMethodExecute = abstractPostUpdateMethodExecute;
	}

	/**
	 * Return whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the
	 * user has overridden the methods or not
	 * 
	 * @return true if default method of the abstract adaptor has been executed
	 */
	public boolean isAbstractPostLoadEFCMethodExecute()
	{
		return abstractPostLoadEFCMethodExecute;
	}

	/**
	 * Set whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the user
	 * has overridden the methods or not
	 * 
	 * @param abstractPostLoadEFCMethodExecute
	 *            - true if default method has been executed
	 */
	public void setAbstractPostLoadEFCMethodExecute(boolean abstractPostLoadEFCMethodExecute)
	{
		this.abstractPostLoadEFCMethodExecute = abstractPostLoadEFCMethodExecute;
	}

	/**
	 * Return whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the
	 * user has overridden the methods or not
	 * 
	 * @return true if default method of the abstract adaptor has been executed
	 */
	public boolean isAbstractOnCancelMethodExecute()
	{
		return abstractOnCancelMethodExecute;
	}

	/**
	 * Set whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the user
	 * has overridden the methods or not
	 * 
	 * @param abstractOnCancelMethodExecute
	 *            - true if default method has been executed
	 */
	public void setAbstractOnCancelMethodExecute(boolean abstractOnCancelMethodExecute)
	{
		this.abstractOnCancelMethodExecute = abstractOnCancelMethodExecute;
	}

	/**
	 * Set whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the user
	 * has overridden the methods or not
	 * 
	 * @param abstractOnFinalUpdateMethodExecute
	 *            - true if default method has been executed
	 */
	public void setAbstractOnFinalUpdateMethodExecute(boolean abstractOnFinalUpdateMethodExecute)
	{
		this.abstractOnFinalUpdateMethodExecute = abstractOnFinalUpdateMethodExecute;
	}

	/**
	 * Return whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the
	 * user has overridden the methods or not
	 * 
	 * @return true if default method of the abstract adaptor has been executed
	 */
	public boolean isAbstractGetLRPTemplateProcessDetailMethodExecute()
	{
		return abstractGetLRPTemplateProcessDetailMethodExecute;
	}

	/**
	 * Set whether the default method of the abstract adaptor has been executed. This will allow the system to determine if the user
	 * has overridden the methods or not
	 * 
	 * @param abstractGetLRPTemplateProcessDetailMethodExecute
	 *            - true if default method has been executed
	 */
	public void setAbstractGetLRPTemplateProcessDetailMethodExecute(boolean abstractGetLRPTemplateProcessDetailMethodExecute)
	{
		this.abstractGetLRPTemplateProcessDetailMethodExecute = abstractGetLRPTemplateProcessDetailMethodExecute;
	}

}
