package com.misys.equation.function.linkage;

import java.util.HashMap;
import java.util.Map;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.function.beans.Element;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.beans.LinkService;
import com.misys.equation.function.tools.XMLToolbox;
import com.misys.equation.problems.Message;

public class ServiceLinkage extends AbstractServiceLinkage
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ServiceLinkage.java 14806 2012-11-05 11:58:11Z williae1 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(ServiceLinkage.class);

	// the link service
	protected LinkService linkService;

	// list of function ids corresponding to the secondary ids
	protected String[] secondaryFunctionIds;

	protected Function primaryFunction;
	protected Layout primaryLayout;
	protected Function[] secondaryFunctions;
	protected Layout[] secondaryLayouts;

	// linked function
	protected Function linkedFunction;
	protected Layout linkedLayout;

	/**
	 * Constructor given the link service
	 * 
	 * @param linkService
	 *            - the link service
	 */
	public ServiceLinkage(LinkService linkService)
	{
		this.linkService = linkService;
	}

	/**
	 * Return the linked function
	 * 
	 * @return the linked function
	 */
	public Function getLinkedFunction()
	{
		return linkedFunction;
	}

	/**
	 * Return the linked layout
	 * 
	 * @return the linked layout
	 */
	public Layout getLinkedLayout()
	{
		return linkedLayout;
	}

	/**
	 * Return the link service
	 * 
	 * @return the link service
	 */
	public LinkService getLinkService()
	{
		return linkService;
	}

	/**
	 * Retrieve the linked function
	 * 
	 * @param session
	 *            - the session
	 * @param forceCheck
	 *            - true to retrieve the services from the database
	 * 
	 * @return the linked function if it is valid
	 * 
	 * @throws EQException
	 */
	public Function rtvLinkedFunction(EquationStandardSession session, boolean forceCheck) throws EQException
	{
		// clear messages
		messageContainer.clear();

		// Get the layout
		primaryLayout = getLayout(session, linkService.getPrimaryId(), "Language.LinkagedPrimaryServiceLayoutNotFoundError",
						forceCheck);
		if (primaryLayout == null)
		{
			return null;
		}

		// Get the function
		primaryFunction = getFunctionFromLayout(session, primaryLayout, "Language.LinkagedPrimaryServiceFunctionNotFoundError",
						forceCheck);
		if (primaryFunction == null)
		{
			return null;
		}

		// Get the secondary functions
		secondaryFunctions = getSecondaryFunctions(session, forceCheck);
		if (secondaryFunctions == null)
		{
			return null;
		}

		// Link the services together
		ServiceLinkageFunction serviceLinkageFunction = new ServiceLinkageFunction(primaryFunction);
		serviceLinkageFunction.link(secondaryFunctions);
		messageContainer = serviceLinkageFunction.getMessageContainer();

		// Print message
		printMessages();

		// When error exist, remove non-errors
		if (messageContainer.hasErrorMessages())
		{
			messageContainer.cleanUp(Message.SEVERITY_ERROR);
			return null;
		}

		// generate the linked function
		linkedFunction = serviceLinkageFunction.getlinkedFunction();
		updateDetails(linkedFunction);

		// return the linked function
		return serviceLinkageFunction.getlinkedFunction();
	}

	/**
	 * Retrieve the linked layout
	 * 
	 * @param session
	 *            - the session
	 * @param forceCheck
	 *            - true to retrieve the services from the database
	 * 
	 * @return the linked layout if it is valid
	 * 
	 * @throws EQException
	 */
	public Layout rtvLinkedLayout(EquationStandardSession session, boolean forceCheck) throws EQException
	{
		// clear messages
		messageContainer.clear();

		// Check if there are duplicate services
		if (isDuplicateServices())
		{
			return null;
		}

		// Determine if services are always loaded from the database
		boolean development = session.getUnit().isDevelopmentUnit() || forceCheck;

		// Get the layout
		primaryLayout = getLayout(session, linkService.getPrimaryId(), "Language.LinkagedPrimaryServiceLayoutNotFoundError",
						development);
		if (primaryLayout == null)
		{
			return null;
		}

		// Get the secondary functions
		secondaryLayouts = getSecondaryLayouts(session, development);
		if (secondaryLayouts == null)
		{
			return null;
		}

		// Link the services together
		ServiceLinkageLayout serviceLinkageLayout = new ServiceLinkageLayout(primaryLayout);
		serviceLinkageLayout.link(secondaryLayouts);
		messageContainer = serviceLinkageLayout.getMessageContainer();

		// Print message
		printMessages();

		// When error exist, remove non-errors
		if (messageContainer.hasErrorMessages())
		{
			messageContainer.cleanUp(Message.SEVERITY_ERROR);
			return null;
		}

		// generate the linked layout
		linkedLayout = serviceLinkageLayout.getlinkedLayout();
		updateDetails(linkedLayout);

		// return the linked layout
		return serviceLinkageLayout.getlinkedLayout();
	}

	/**
	 * Retrieve the function given the layout
	 * 
	 * @param session
	 *            - the session
	 * @param layout
	 *            - the layout
	 * @param errorCode
	 *            - the error code
	 * @param forceCheck
	 *            - true to retrieve the services from the database
	 * 
	 * @return the function
	 * 
	 * @throws EQException
	 */
	protected Function getFunctionFromLayout(EquationStandardSession session, Layout layout, String errorCode, boolean forceCheck)
					throws EQException
	{
		Function function = XMLToolbox.getXMLToolbox().getFunction(session, layout.getServiceId(), forceCheck);
		if (function == null)
		{
			addMessage(errorCode, layout.getServiceId(), layout.getId(), "", "", "");
			return null;
		}

		return function;
	}

	/**
	 * Retrieve the layout given the layout
	 * 
	 * @param session
	 *            - the session
	 * @param layout
	 *            - the layout
	 * @param errorCode
	 *            - the error code
	 * @param forceCheck
	 *            - true to retrieve the services from the database
	 * 
	 * @return the function
	 * 
	 * @throws EQException
	 */
	protected Layout getLayout(EquationStandardSession session, String id, String errorCode, boolean forceCheck) throws EQException
	{
		Layout layout = XMLToolbox.getXMLToolbox().getLayout(session, id, forceCheck, true);
		if (layout == null)
		{
			addMessage(errorCode, id, "", "", "", "");
			return null;
		}
		return layout;
	}

	/**
	 * Get the secondary functions
	 * 
	 * @param session
	 *            - the session
	 * @param forceCheck
	 *            - true to retrieve the services from the database
	 * 
	 * @return the secondary functions
	 * 
	 * @throws EQException
	 */
	protected Function[] getSecondaryFunctions(EquationStandardSession session, boolean forceCheck) throws EQException
	{
		// No secondary functions defined?
		if (linkService.getSecondaryIds().size() == 0)
		{
			addMessage("Language.LinkagedSecondaryServiceNotDefinedError", "", "", "", "", "");
			return null;
		}

		// Create the array
		int length = linkService.getSecondaryIds().size();
		Function[] secondaryFunctions = new Function[length];
		secondaryFunctionIds = new String[length];

		for (int i = 0; i < length; i++)
		{
			String secondaryServiceId = linkService.getSecondaryIds().get(i);

			Layout secondaryLayout = getLayout(session, secondaryServiceId, "Language.LinkagedSecondaryServiceLayoutNotFoundError",
							forceCheck);
			if (secondaryLayout == null)
			{
				return null;
			}

			Function secondaryFunction = getFunctionFromLayout(session, secondaryLayout,
							"Language.LinkagedSecondaryServiceFunctionNotFoundError", forceCheck);
			if (secondaryFunction == null)
			{
				return null;
			}
			secondaryFunctions[i] = secondaryFunction;
			secondaryFunctionIds[i] = secondaryFunction.getId();
		}
		return secondaryFunctions;
	}

	/**
	 * Check if there are duplicate services
	 * 
	 * @return true if there are duplicate services
	 */
	protected boolean isDuplicateServices()
	{
		boolean duplicate = false;

		// Initialise the map containing the primary id
		Map<String, String> map = new HashMap<String, String>();
		map.put(linkService.getPrimaryId(), linkService.getPrimaryId());

		for (String secondaryId : linkService.getSecondaryIds())
		{
			if (map.containsKey(secondaryId))
			{
				addMessage("Language.LinkageDuplicateServicesError", secondaryId, "", "", "", "");
				duplicate = true;
			}
			else
			{
				map.put(secondaryId, secondaryId);
			}
		}

		return duplicate;

	}

	/**
	 * Get the secondary layouts
	 * 
	 * @param session
	 *            - the session
	 * @param forceCheck
	 *            - true to retrieve the services from the database
	 * 
	 * @return the secondary layouts
	 * 
	 * @throws EQException
	 */
	protected Layout[] getSecondaryLayouts(EquationStandardSession session, boolean forceCheck) throws EQException
	{
		// No secondary functions defined?
		if (linkService.getSecondaryIds().size() == 0)
		{
			addMessage("Language.LinkagedSecondaryServiceNotDefinedError", "", "", "", "", "");
			return null;
		}

		// Create the array
		int length = linkService.getSecondaryIds().size();
		Layout[] secondaryLayouts = new Layout[length];

		for (int i = 0; i < length; i++)
		{
			String secondaryServiceId = linkService.getSecondaryIds().get(i);

			Layout secondaryLayout = getLayout(session, secondaryServiceId, "Language.LinkagedSecondaryServiceLayoutNotFoundError",
							forceCheck);
			if (secondaryLayout == null)
			{
				return null;
			}

			secondaryLayouts[i] = secondaryLayout;
		}
		return secondaryLayouts;
	}

	/**
	 * Print message
	 */
	protected void printMessages()
	{
		for (Message message : messageContainer)
		{
			if (message.getSeverity() == Message.SEVERITY_ERROR)
			{
				LOG.error(message.getText());
			}
			else if (message.getSeverity() == Message.SEVERITY_WARNING)
			{
				LOG.warn(message.getText());
			}
			else if (message.getSeverity() == Message.SEVERITY_INFO)
			{
				LOG.warn(message.getText());
			}
		}
	}

	/**
	 * Return the list of secondary function ids. This is valid only after executing rtvLinkedFunction()
	 * 
	 * @return the list of secondary function ids
	 */
	public String[] getSecondayFunctionIds()
	{
		return secondaryFunctionIds;
	}

	/**
	 * Return the list of secondary function. This is valid only after executing rtvLinkedFunction()
	 * 
	 * @return the list of secondary function
	 */
	public Function[] getSecondaryFunctions()
	{
		return secondaryFunctions;
	}

	/**
	 * Return the list of secondary layouts. This is valid only after executing rtvLinkedLayout()
	 * 
	 * @return the list of secondary layouts
	 */
	public Layout[] getSecondaryLayouts()
	{
		return secondaryLayouts;
	}

	/**
	 * Return the primary function
	 * 
	 * @return the primary function
	 */
	public Function getPrimaryFunction()
	{
		return primaryFunction;
	}

	/**
	 * Return the primary layout
	 * 
	 * @return the primary layout
	 */
	public Layout getPrimaryLayout()
	{
		return primaryLayout;
	}

	/**
	 * Determine if the linked service uses this option as a layout or as a function
	 * 
	 * @param optionId
	 *            - the option id
	 * 
	 * @return true if the option id is part of the linked service
	 */
	public boolean isUseFunctionOrLayout(String optionId)
	{
		optionId = optionId.trim();
		if (isUseFunction(optionId))
		{
			return true;
		}

		return isUseLayout(optionId);
	}

	/**
	 * Determine if the linked service uses this option id as a layout
	 * 
	 * @param optionId
	 *            - the option id
	 * @return true if the linked service uses this option id as a layout
	 */
	protected boolean isUseLayout(String optionId)
	{
		// is in the layout?
		for (String id : linkService.getSecondaryIds())
		{
			if (id.equals(optionId))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Determine if the linked service uses this option id as a function
	 * 
	 * @param optionId
	 *            - the option id
	 * @return true if the linked service uses this option id as a function
	 */
	protected boolean isUseFunction(String optionId)
	{
		// is in the layout?
		for (String id : secondaryFunctionIds)
		{
			if (id.equals(optionId))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Update details of the linked function based on the link service
	 * 
	 * @param function
	 *            - the function
	 */
	protected void updateDetails(Function function)
	{
		updateDetails((Element) function);
	}

	/**
	 * Update details of the linked layout based on the link service
	 * 
	 * @param layout
	 *            - the layout
	 */
	protected void updateDetails(Layout layout)
	{
		updateDetails((Element) layout);
	}

	/**
	 * Update details of the element based on the link service
	 * 
	 * @param element
	 *            - the element
	 */
	protected void updateDetails(Element element)
	{
		// update the label
		if (linkService.getLabel() != null && linkService.getLabel().trim().length() > 0)
		{
			element.setLabel(linkService.getLabel());
			element.setLabelType(Element.TEXT_VALUE_TEXT);
		}

		// update the description
		if (linkService.getDescription() != null && linkService.getDescription().trim().length() > 0)
		{
			element.setDescription(linkService.getDescription());
			element.setDescriptionType(Element.TEXT_VALUE_TEXT);
		}
	}
	/**
	 * @return a <code>boolean</code> indicating if Desktop UI is not allow in Production
	 */
	public boolean isNoDesktopInProduction()
	{
		return linkService.isNoDesktopInProduction();
	}
}
