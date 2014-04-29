package com.misys.equation.ui.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationLogin;
import com.misys.equation.common.utilities.EqTimingTest;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.ConversionRules;
import com.misys.equation.function.runtime.FunctionBankFusion;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionHandlerData;
import com.misys.equation.function.runtime.FunctionHandlerTable;
import com.misys.equation.function.runtime.FunctionKeys;
import com.misys.equation.function.runtime.FunctionMessage;
import com.misys.equation.function.runtime.FunctionMessages;
import com.misys.equation.function.runtime.ScreenSet;
import com.misys.equation.function.runtime.ScreenSetHandler;
import com.misys.equation.function.runtime.SecurityLevel;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.misys.equation.function.tools.HTMLToolbox;
import com.misys.equation.ui.context.EquationDesktopContext;
import com.misys.equation.ui.tools.EqDesktopToolBox;

public class FunctionForm extends ActionForm
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionForm.java 17382 2013-10-08 08:45:53Z Lima12 $";

	/** Serialization version number */
	private static final long serialVersionUID = 1L;

	/** Static class level logger */
	private static final EquationLogger LOG = new EquationLogger(FunctionForm.class);

	@Override
	@SuppressWarnings("unchecked")
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		EqDesktopToolBox.setupThreadData(request);
		// Print time test
		EqTimingTest.printStartTime("FunctionForm.validate()", "");

		// Initialise list of errors for the strut
		ActionErrors ae = new ActionErrors();

		// Retrieve the function handler
		FunctionHandlerTable funcHandlerTable = null;
		FunctionHandler functionHandler = null;
		FunctionHandlerData fhd = null;

		// Determine name of the function handler
		String name = request.getParameter(HTMLToolbox.OBJ_NAME);

		try
		{
			request.getSession().setAttribute(FunctionRuntimeToolbox.UNIQUE_NAME, name);
			funcHandlerTable = (FunctionHandlerTable) request.getSession().getAttribute(FunctionRuntimeToolbox.NAME);
			if (funcHandlerTable == null)
			{
				ae.add("actionError", new ActionMessage("error.invalid.lostsession", ""));
				return ae;
			}
			functionHandler = funcHandlerTable.getFunctionHandler(name);
			fhd = functionHandler.getFhd();
		}
		catch (Exception e)
		{
			LOG.error("validate", e);
			ae.add("actionError", new ActionMessage("error.parameterised", Toolbox.getExceptionMessage(e)));
			return ae;
		}

		// Session timed-out?
		if (fhd.getEquationUser() == null || fhd.getEquationUser().getSession() == null)
		{
			ae.add("actionError", new ActionMessage("error.invalid.timedout", ""));
			return ae;
		}

		// Retrieve function key pressed
		int ckey = Toolbox.parseInt(request.getParameter(HTMLToolbox.OBJ_FKEY), 0);
		String fldValidate = request.getParameter(HTMLToolbox.OBJ_FLDVAL).trim();
		fhd.getFunctionKeys().setFieldId(fldValidate);

		// supervisor message
		String reason = request.getParameter(HTMLToolbox.OBJ_SUPMSG);
		String referToUserId = request.getParameter(HTMLToolbox.OBJ_REFERTOUSERID);
		fhd.setReason(reason);
		fhd.setReferToUserId(referToUserId);

		// get the screen set handler
		ScreenSetHandler screenSetHandler = fhd.getScreenSetHandler();
		int curScreenSet = screenSetHandler.getCurScreenSet();
		ScreenSet screenSet = screenSetHandler.rtvScrnSetCurrent();

		// load the statuses
		screenSet.getDisplayGroupHandler().loadDisplayGroupFromMap2(request.getParameterMap());
		screenSet.getShutterHandler().loadShutterFromMap2(request.getParameterMap());
		screenSet.getRepeatingGroupStatusHandler().loadRepeatingGroupFromMap2(request.getParameterMap());

		// load the data into the function data
		FunctionData functionDataAft = screenSet.getFunctionData();
		boolean flag = functionDataAft.loadFieldDataFromMap2(request.getParameterMap(), true, fhd.getEquationUser().getSession()
						.getCcsid());

		// any data has been changed, then invalidate the screen and invalidate F24=Override
		if (flag)
		{
			functionHandler.dirtyScreen(screenSet.getScrnNo());
			if (ckey == FunctionKeys.KEY_OVR)
			{
				if (fhd.isMessageOnCancelUserExit())
				{
					ckey = FunctionKeys.KEY_VERIWARN;
				}
				else
				{
					ckey = FunctionKeys.KEY_ENT;
					fhd.getFunctionKeys().setFuncKey(ckey);
				}
			}
		}

		// Validate the page
		ckey = functionHandler.overrideCkey(ckey);
		fhd.getFunctionKeys().setFuncKey(ckey);
		if (ckey != FunctionKeys.KEY_EXIT && ckey != FunctionKeys.KEY_EXIT2 && ckey != FunctionKeys.KEY_EXIT_OFLNE_OVR
						&& ckey != FunctionKeys.KEY_PREV && ckey != FunctionKeys.KEY_UNLOAD)
		{
			// process function key
			try
			{
				EqTimingTest.printStartTime("FunctionForm.validate().validateFkey()", "");
				// If BankFusion is installed then call a dummy BankFusion microflow to wrap the validate process.
				// Call only for the main screenset
				if (EquationCommonContext.isBankFusionInstalled() && curScreenSet == 0)
				{
					ConversionRules conversionRules = FunctionRuntimeToolbox.getConversionRules(null, fhd);
					conversionRules.setDateFormat(ConversionRules.DATE_CYYMMDD);
					conversionRules.setAmountFormat(ConversionRules.AMOUNT_MINOR_CURRENCY);
					FunctionBankFusion functionBankFusion = new FunctionBankFusion();
					functionBankFusion.callDummyValidateMicroflow(screenSet, ckey, fldValidate, request
									.getParameter(HTMLToolbox.OBJ_TRANID), name, conversionRules);
				}
				else
				{
					functionHandler.validateFkey(ckey, fldValidate, request.getParameter(HTMLToolbox.OBJ_TRANID));
				}
				EqTimingTest.printTime("FunctionForm.validate().validateFkey()", "");
			}
			catch (Exception e)
			{
				LOG.error(e);
				ae.add("actionError", new ActionMessage("error.parameterised", Toolbox.getExceptionMessage(e)));
			}

			// any error generated during the validation steps?
			if (fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_NONE && !fhd.getSecurityLevel().isUpdateMessage())
			{
				if (functionHandler.rtvMsgSev() != FunctionMessages.MSG_NONE)
				{
					ae = loadIntoActionErrors(ae, functionHandler.rtvFunctionMessages().getMessages());
				}
			}

			// include messages from other messages
			ae = loadIntoActionErrors(ae, fhd.getFunctionMsgManager().getOtherMessages().getMessages());
		}

		// save the function handler back to the request
		funcHandlerTable.setFunctionHandler(name, functionHandler);
		request.getSession().setAttribute(FunctionRuntimeToolbox.NAME, funcHandlerTable);
		request.getSession().setAttribute(FunctionRuntimeToolbox.UNIQUE_NAME, name);
		request.getSession().setAttribute(FunctionRuntimeToolbox.REFRESH_MAIN_WINDOW, "");

		// update the context in the login bean
		EquationLogin existinglogin = (EquationLogin) request.getSession().getAttribute(EquationDesktopContext.SESSION_LOGIN);
		existinglogin.setContextData1(functionHandler.getFhd().getContextHandler().getDswsid1Str());
		existinglogin.setContextData2(functionHandler.getFhd().getContextHandler().getDswsid2Str());

		// print time test
		EqTimingTest.printTime("FunctionForm.validate()", "");

		// return the error
		return ae;
	}
	/**
	 * Performs initial processing prior to displaying the function
	 * 
	 * @param messages
	 *            - list of messages
	 * 
	 * @return the action errors
	 * 
	 */
	public ActionErrors loadIntoActionErrors(ActionErrors ae, List<FunctionMessage> messages)
	{
		for (int i = 0; i < messages.size(); i++)
		{
			ae.add("actionError", new ActionMessage("error.parameterised", messages.get(i).rtvUnformattedText()));
		}
		return ae;
	}

	/**
	 * Set the character encoding to UTF-8
	 */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		try
		{
			request.setCharacterEncoding("UTF-8");
		}
		catch (Exception e)
		{
		}
	}

}
