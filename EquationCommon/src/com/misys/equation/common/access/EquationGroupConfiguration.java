package com.misys.equation.common.access;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This is a singleton, it holds all the groups and members defined in the Equation configuration properties
 */
public class EquationGroupConfiguration
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationGroupConfiguration.java 11001 2011-05-19 14:03:00Z MACDONP1 $";

	/** The default library */
	private static final String DEFAULT_LIBRARY = "QGPL";

	/** The comma delimiter */
	private static final String DELIMITER_COMMA = ",";

	/** The User Level Queue Property Name */
	public static final String USER_LEVEL_OUTPUT_QUEUE_PROPERTY = "eq.outq.user.";

	/** The Group Level Queue Property Name */
	public static final String GROUP_LEVEL_OUTPUT_QUEUE_PROPERTY = "eq.outq.group.";

	/** The Default Level Queue Property Name */
	public static final String DEFAULT_LEVEL_OUTPUT_QUEUE_PROPERTY = "eq.outq.default";

	private static EquationGroupConfiguration commonEquationGroupConfiguration = null;

	/**
	 * The names of the groups
	 */
	private String[] groupNames = null;

	/**
	 * List of groups and its members
	 * 
	 * The groups are linked according to how they are defined in eq.group.names.
	 */
	private final Map<String, String[]> groupMembers = new LinkedHashMap<String, String[]>(30);

	/**
	 * Private Constructor
	 */
	private EquationGroupConfiguration()
	{
	}

	/**
	 * Get the singleton context
	 */
	public static synchronized EquationGroupConfiguration getContext()
	{
		// Create our one and only instance of this class
		if (commonEquationGroupConfiguration == null)
		{
			commonEquationGroupConfiguration = new EquationGroupConfiguration();
		}
		return commonEquationGroupConfiguration;
	}

	/**
	 * Get the Default Library
	 * 
	 * @return Default Library
	 */
	public String getDefaultLibrary()
	{
		return DEFAULT_LIBRARY;
	}

	/**
	 * Load the Groups and Members from equationConfiguration.properties
	 */
	public synchronized void loadGroupsAndMembersFromProperties()
	{
		String groupNameProperty = EquationCommonContext.getConfigProperty("eq.group.names");

		// Load Groups
		if (groupNameProperty != null)
		{
			groupNames = groupNameProperty.split(DELIMITER_COMMA);
		}

		// Load Group members
		if (groupNames != null)
		{
			String groupName = null;
			String groupMemberNamesProperty = null;
			String[] groupMemberNames = null;

			for (String groupName2 : groupNames)
			{
				groupName = groupName2.trim();

				// If the name is blank, skip it
				if (groupName.length() == 0)
				{
					continue;
				}

				groupMemberNamesProperty = EquationCommonContext.getConfigProperty("eq.group.member." + groupName);

				// If the group is blank or not found, continue to the next group
				if (groupMemberNamesProperty == null || groupMemberNamesProperty.trim().length() == 0)
				{
					continue;
				}

				// Split the users e.g. user1,user2
				groupMemberNames = groupMemberNamesProperty.split(DELIMITER_COMMA);

				// Trim the member names
				for (int memberIndex = 0; memberIndex < groupMemberNames.length; memberIndex++)
				{
					groupMemberNames[memberIndex] = groupMemberNames[memberIndex].trim().toLowerCase();

				}
				Arrays.sort(groupMemberNames);

				// Store the group
				groupMembers.put(groupName, groupMemberNames);
			}
		}
	}

	/**
	 * This method accepts a user Id and returns the group name the user belongs to
	 * 
	 * @param userId
	 * @returns the User Group
	 */
	public String getUserGroup(String userId)
	{
		// Return blank is the user id is not provided
		if (userId == null || userId.trim().length() == 0)
		{
			return "";
		}

		// For each group, check if the user is a member
		String[] members = null;
		for (String group : groupMembers.keySet())
		{
			members = groupMembers.get(group);

			if (Arrays.binarySearch(members, userId.trim().toLowerCase()) >= 0)
			{
				return group;
			}
		}

		// Return blank if the user does not belong to any group
		return "";
	}
}
