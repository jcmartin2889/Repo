package com.misys.equation.function.runtime;

public class SecurityLevel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SecurityLevel.java 13462 2012-06-06 08:19:07Z bterrado $";

	// Type of checker
	public final static int CHCKR_NONE = 0; // user inputting the transaction
	public final static int CHCKR_SUPER = 1; // supervisor to override warnings
	public final static int CHCKR_MAKER_CHECKER = 2; // checker of a maker-checker referral
	public final static int CHCKR_MAKER_MAKER = 3; // maker of a maker-checker referral (after the checker has authorised it)

	// Type of person performing this transaction
	private int checkerType;
	private String checkerId;

	// Requires further checking by?
	// .. when this is non-zero, then it means that the transaction needs to be referred to this particular type of checker
	private int requiredCheckerType;
	private String requiredCheckerId;

	// Enquire mode only?
	private boolean enquireMode;

	// Error during update?
	private boolean updateMessage;

	private boolean authorizeAllowed;
	private boolean declineAllowed;
	private boolean referAllowed;
	private boolean updateAllowed;

	/**
	 * Construct the default security level
	 * 
	 * @param checker
	 *            - checker type
	 * @param checkerId
	 *            - checker user id
	 */
	public SecurityLevel(int checker, String checkerId)
	{
		this.checkerType = CHCKR_NONE;
		this.checkerId = checkerId;
		this.requiredCheckerType = CHCKR_NONE;
		this.requiredCheckerId = "";
		this.enquireMode = false;
		this.updateMessage = false;

		// are the initial values fine
		this.authorizeAllowed = false;
		this.declineAllowed = false;
		this.referAllowed = false;
		this.updateAllowed = false;
	}

	/**
	 * Return the checker type
	 * 
	 * @return the checker type
	 */
	public int getCheckerType()
	{
		return checkerType;
	}

	/**
	 * Set the checker type
	 * 
	 * @param checker
	 *            - the checker type
	 */
	public void setCheckerType(int checkerType)
	{
		this.checkerType = checkerType;
	}

	/**
	 * Return the checker id
	 * 
	 * @return the checker id
	 */
	public String getCheckerId()
	{
		return checkerId;
	}

	/**
	 * Set the checker id
	 * 
	 * @param checkerId
	 *            -the checker id
	 */
	public void setCheckerId(String checkerId)
	{
		this.checkerId = checkerId;
	}

	/**
	 * Return the type of checker who needs to check this transaction
	 * 
	 * @return the type of checker who needs to check this transaction
	 */
	public int getRequiredCheckerType()
	{
		return requiredCheckerType;
	}

	/**
	 * Set the type of checker who needs to check this transaction
	 * 
	 * @param requiredChecker
	 *            - the type of checker who needs to check this transaction
	 */
	public void setRequiredCheckerType(int requiredCheckerType)
	{
		this.requiredCheckerType = requiredCheckerType;
	}

	/**
	 * Return the user id who needs to check this transaction
	 * 
	 * @return the user id who needs to check this transaction
	 */
	public String getRequiredCheckerId()
	{
		return requiredCheckerId;
	}

	/**
	 * Set the user id who needs to check this transaction
	 * 
	 * @param requiredCheckerId
	 *            - the user id who needs to check this transaction
	 */
	public void setRequiredCheckerId(String requiredCheckerId)
	{
		this.requiredCheckerId = requiredCheckerId;
	}

	/**
	 * Determine whether the user is only on enquire mode
	 * 
	 * @return true if the user is only on enquire mode
	 */
	public boolean isEnquireMode()
	{
		return enquireMode;
	}

	/**
	 * Set whether the user is only on enquire mode
	 * 
	 * @param enquireMode
	 *            - true if the user is only on enquire mode
	 */
	public void setEnquireMode(boolean enquireMode)
	{
		this.enquireMode = enquireMode;
	}

	/**
	 * Determine whether major severity has been issued during update
	 * 
	 * @return true if major severity has been issued during update
	 */
	public boolean isUpdateMessage()
	{
		return updateMessage;
	}

	/**
	 * Set whether major severity has been issued during update
	 * 
	 * @param updateMessage
	 *            - true if major severity has been issued during update
	 */
	public void setUpdateMessage(boolean updateMessage)
	{
		this.updateMessage = updateMessage;
	}

	/**
	 * Returns whether this transaction cannot be updated anymore
	 * 
	 * @return true, if transaction cannot be updated
	 */
	public boolean chkNoUpdate()
	{
		if (checkerType == SecurityLevel.CHCKR_SUPER || enquireMode || updateMessage)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * Returns whether this transaction cannot be updated anymore
	 * 
	 * @return true, if transaction cannot be updated
	 */
	public boolean chkNoValidate()
	{
		if (checkerType == SecurityLevel.CHCKR_SUPER || updateMessage)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * Returns whether authorization/approval is allowed
	 * 
	 * @return the authorizeAllowed
	 */
	public boolean isAuthorizeAllowed()
	{
		return authorizeAllowed;
	}

	/**
	 * Sets whether authorization/approval is allowed
	 * 
	 * @param authorizeAllowed
	 *            the authorizeAllowed to set
	 */
	public void setAuthorizeAllowed(boolean authorizeAllowed)
	{
		this.authorizeAllowed = authorizeAllowed;
	}

	/**
	 * Return whether declining/rejecting is allowed
	 * 
	 * @return the declineAllowed
	 */
	public boolean isDeclineAllowed()
	{
		return declineAllowed;
	}

	/**
	 * Sets whether declining/rejecting is allowed
	 * 
	 * @param declineAllowed
	 *            the declineAllowed to set
	 */
	public void setDeclineAllowed(boolean declineAllowed)
	{
		this.declineAllowed = declineAllowed;
	}

	/**
	 * Return whether referral is allowed
	 * 
	 * @return true if referral is allowed
	 */
	public boolean isReferAllowed()
	{
		return referAllowed;
	}

	/**
	 * Sets whether referral is allowed
	 * 
	 * @param referAllowed
	 *            - true if referral is allowed
	 */
	public void setReferAllowed(boolean referAllowed)
	{
		this.referAllowed = referAllowed;
	}

	/**
	 * Return whether update is allowed
	 * 
	 * @return whether update is allowed
	 */
	public boolean isUpdateAllowed()
	{
		return updateAllowed;
	}

	/**
	 * Set whether update is allowed
	 * 
	 * @param updateAllowed
	 *            - true if update is allowed
	 */
	public void setUpdateAllowed(boolean updateAllowed)
	{
		this.updateAllowed = updateAllowed;

		// update allowed, then automatically allow authorised
		if (updateAllowed)
		{
			this.authorizeAllowed = true;
		}
	}

}
