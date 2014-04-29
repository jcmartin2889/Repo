/*
 * Created on 24-Apr-2008
 * 
 * TODO To change the template for this generated file go to Window - Preferences - Java - Code Style - Code Templates
 */
package com.misys.equation.common.userexit;

import java.util.List;

import com.misys.equation.common.access.EquationScreenDataStructure;

/**
 * Interface for RPG user exit classes. Classes implement this interface to be called from RPG validation user exit programs
 * 
 * Note that it is intended users should extend the abstract class GenericValidationUserExit instead of implementing this interface
 * directly.
 * 
 * @author weddelc1
 */
public interface UserExit
{
	/**
	 * This method is called before any others to supply the class with the the EquationUserExit instance which encapsulates the
	 * screen data structure
	 * 
	 * @param screenDS
	 *            an EquationScreenDataStructure
	 */
	public void initialize(EquationScreenDataStructure screenDS);

	/**
	 * This method called to invoke Java user exit processing.
	 * 
	 * The author of this implementation of this method should use both the supplied parameters and field values from the Equation
	 * Screen DS object to determine what processing to perform. This may include updating the Equation Screen DS field values and
	 * adding validation messages.
	 * 
	 * @param xfct
	 *            Function Mode (A,M,D or I for initialise)
	 * @param xscrn
	 *            Screen number
	 * @param xmode
	 *            User Exit Mode: D for Default or V for Validate
	 * 
	 *            The implementation of this method can use the getField and setField methods of the userExit object to access the
	 *            screen field DS fields. Note that by comparison to an RPG Exit program, the prompt flag parameter (xprmt) is not
	 *            supplied as Java user exits are never called in prompt mode.
	 * 
	 * @see EquationScreenDataStructure
	 * @see ValidationUserExitMessage
	 */
	public void execute(String xfct, String xscrn, String xmode);

	/**
	 * Exposes the collection of messages to be returned to RPG.
	 * 
	 * This get property method will be called after the execute method.
	 * 
	 * @return A List of ValidateUserExitMessage objects.
	 */
	public List<ValidationUserExitMessage> getMessages();

}