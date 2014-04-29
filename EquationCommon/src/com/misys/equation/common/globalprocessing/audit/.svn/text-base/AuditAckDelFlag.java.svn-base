package com.misys.equation.common.globalprocessing.audit;

/**
 * Enumeration of all the possible "Acknowledged / Deleted" codes that can be read from the "Acknowledged/Deleted" field of the
 * audit log header file.
 * <p>
 * This class also serves as a resource bundle key enumeration for getting strings values which represent the different
 * "Acknowledged/Deleted" types.
 * 
 * @author berzosa
 */
public enum AuditAckDelFlag
{
	NOT_ACKED("0"), // Not acknowledged
	ACKED("1"), // Acknowledged(Ignore)
	DELETED("2"); // Deleted

	private final String value;
	private AuditAckDelFlag(String value)
	{
		this.value = value;
	}

	/**
	 * Returns the code that this enum constant represents.
	 * 
	 * @return The code that this enum constant represents.
	 */
	public String getValue()
	{
		return value;
	}
}
