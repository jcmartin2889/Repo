package com.misys.equation.function.adaptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bf.com.misys.eqf.types.header.ServiceRqHeader;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQRuntimeException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.FunctionClassLoader;
import com.misys.equation.function.beans.FieldSet;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.el.ELRuntime;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.runtime.FunctionBankFusion;
import com.misys.equation.function.runtime.FunctionMessages;
import com.misys.equation.function.runtime.UserModifyData;
import com.misys.equation.function.tools.ComplexTypeToolbox;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.XMLToolbox;
import com.misys.equation.function.tools.XSDStructureLink;
import com.misys.equation.function.tools.XSDToolbox;
import com.misys.equation.function.useraccess.UserExitESFProcessDetail;

public class FunctionAdaptor extends Adaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionAdaptor.java 17382 2013-10-08 08:45:53Z Lima12 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(FunctionAdaptor.class);

	protected FunctionClassLoader classLoader;
	protected Class<IFunctionAdaptor> functionAdaptorClass;
	protected IFunctionAdaptor functionAdaptorImpl;
	protected String optionId;

	protected UserExitFunctionAdaptor userExitFunctionAdaptor;
	protected UserExitFunctionAdaptor misysExitFunctionAdaptor;
	protected List<FunctionAdaptor> secondaryFunctionAdaptors;
	protected UserExitFunctionAdaptor linkedUserExitFunctionAdaptor;

	/**
	 * Construct a Function Adaptor
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param optionId
	 *            id - the option id of the Equation service
	 * @param type
	 *            the specific GAZTYP type for this class
	 */
	public FunctionAdaptor(EquationStandardSession session, String optionId, String type) throws EQException
	{
		this.optionId = optionId;
		this.classLoader = session.getUnit().getFunctionUserExitClassLoader();
		this.functionAdaptorClass = getClass(session, classLoader, optionId, type);

		if (functionAdaptorClass != null)
		{
			functionAdaptorImpl = getInstance(functionAdaptorClass);
		}
		else
		{
			// TODO: If the Main class is not loaded, this will
			// subsequently cause a NullPointerException. If the requirement
			// to have a Java class is removed, then this exception should also
			// be removed.

			// Paul M: I've commented this out for the time being because it's breaking the PV test frame
			// throw new RuntimeException("Failed to load main Java user exit class from the GAZPF");
		}
		this.misysExitFunctionAdaptor = new UserExitFunctionAdaptor(session, classLoader, functionAdaptorImpl, optionId, true);
		this.userExitFunctionAdaptor = new UserExitFunctionAdaptor(session, classLoader, functionAdaptorImpl, optionId, false);
	}

	/**
	 * Return the function adaptor class
	 * 
	 * @return the function adaptor class
	 */
	public Class<IFunctionAdaptor> getFunctionAdaptorClass()
	{
		return functionAdaptorClass;
	}

	/**
	 * Return the function adaptor implementation
	 * 
	 * @return the function adaptor implementation
	 */
	public IFunctionAdaptor getFunctionAdaptorImpl()
	{
		return functionAdaptorImpl;
	}

	/**
	 * Return the class definition of a function adaptor
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param classLoader
	 *            - the Function class loader
	 * @param optionId
	 *            id - the option id of the Equation service
	 * @param type
	 *            the specific GAZTYP type for this class
	 * 
	 * @return the class definition for the option
	 */
	@SuppressWarnings("unchecked")
	public Class<IFunctionAdaptor> getClass(EquationStandardSession session, FunctionClassLoader classLoader, String optionId,
					String type) throws EQException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("getInstance - loading definition of class for option [" + optionId + "]");
		}

		Class<IFunctionAdaptor> c = classLoader.loadClass(session, optionId, "", "", type);
		return c;
	}

	/**
	 * Return the class definition of a service. For enhanced XSD, return the request class definition
	 * 
	 * @param session
	 *            - the Equation Standard Session
	 * @param optionId
	 *            - the option Id
	 * 
	 * @return the complex type class of the service
	 */
	@SuppressWarnings("unchecked")
	public Class getBFComplexTypeClass(EquationStandardSession session, String optionId) throws EQException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("getInstance - loading definition of complex type class for option [" + optionId + "]");
		}

		// Load the function
		Function function = XMLToolbox.getXMLToolbox().getFunction(session, optionId, false);

		// Enhanced XSD?
		if (function.chkXSDGeneric())
		{
			XSDStructureLink xsdStructureLink = XMLToolbox.getXMLToolbox().getXSDStructureLink(session.getUnitId(), optionId);
			Object object = xsdStructureLink.loadRequestClasses(session, this);
			return object.getClass();
		}

		// This is the processing for non-enhanced complex type
		String mainComplexTypeClass = FunctionBankFusion.getServiceMicroflowName(function);
		String fullClassName = ComplexTypeToolbox.getComplexTypePackage(function) + mainComplexTypeClass;

		Class<Object> bfType = classLoader.loadClassWithCheckClassPath(session, optionId, "", "", GAZRecordDataModel.TYP_BFTYPE,
						fullClassName);

		// also load the corresponding descriptor classes, it'll save trouble when BF classes try to access it later...
		classLoader.loadClass(session, optionId, "", "", GAZRecordDataModel.TYP_BFTYPEDESCRIPTOR);
		int numberInnerClasses = FunctionToolbox.getNumberInnerClasses(session, optionId, "");
		for (int i = 0; i < numberInnerClasses; i++)
		{
			classLoader.loadClass(session, optionId, i + 1 + "", "", GAZRecordDataModel.TYP_BFTYPEDESCRIPTOR);
		}

		// Also need to load repeating data classes (row classes)
		List<GAZRecordDataModel> repeatingTypeGAZRecords = FunctionToolbox.getOptionListClassTypes(session, optionId);

		// The following will load the Row base class and inner classes first, then
		// the repeating row class definitions. If loaded in a different order,
		// this will cause a NoClassDefFoundError, as the FunctionClassLoader doesn't
		// recursively load required classes, and would require a new DAO getter method
		// based on class name, instead of the four normal key fields.
		for (GAZRecordDataModel repeatingTypeGAZRecord : repeatingTypeGAZRecords)
		{
			if (repeatingTypeGAZRecord.getPvId().endsWith("row"))
			{
				loadClasses(session, repeatingTypeGAZRecord);
			}
		}

		for (GAZRecordDataModel repeatingTypeGAZRecord : repeatingTypeGAZRecords)
		{
			if (!repeatingTypeGAZRecord.getPvId().endsWith("row"))
			{
				Class type = loadClasses(session, repeatingTypeGAZRecord);

				// Make sure bfType refers to the main complex type
				// This is because: previously, the main complex type was loaded above, however, changes in the BFEQ3.1.1,
				// it will not be able to load from above as the GAZPVN has been populated with the class name
				if (bfType == null)
				{
					if (repeatingTypeGAZRecord.getPvId().endsWith(mainComplexTypeClass))
					{
						bfType = type;
					}
				}
			}
		}

		return bfType;
	}

	/**
	 * Loads the set of classes for the supplied GAZ record.
	 * <p>
	 * First the inner classes will be loaded, and then the outer class will be loaded
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param gazRecord
	 *            - the GAZ record of the option
	 * 
	 * @return the class
	 * 
	 * @throws EQException
	 */
	private Class loadClasses(EquationStandardSession session, GAZRecordDataModel gazRecord) throws EQException
	{
		List<GAZRecordDataModel> innerGAZRecords = FunctionToolbox.getOptionInnerClasses(session, gazRecord.getOptionId(),
						gazRecord.getPvId());
		for (GAZRecordDataModel innerGAZRecord : innerGAZRecords)
		{
			classLoader.loadClassWithCheckClassPath(session, innerGAZRecord.getOptionId(), innerGAZRecord.getFieldId(),
							innerGAZRecord.getPvId(), innerGAZRecord.getType(), innerGAZRecord.getPvId());
		}

		// After loading all the inner classes, load the specified class:
		return classLoader.loadClassWithCheckClassPath(session, gazRecord.getOptionId(), gazRecord.getFieldId(), gazRecord
						.getPvId(), gazRecord.getType(), gazRecord.getPvId());
	}

	/**
	 * Return an instance of the specified function adaptor class
	 * 
	 * @param optionId
	 *            - the class definition
	 * 
	 * @return an instance of the class
	 */
	public IFunctionAdaptor getInstance(Class<IFunctionAdaptor> optionId)
	{
		IFunctionAdaptor result = null;
		try
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("getInstance - creating a new instance of class [" + optionId.getName() + "]");
			}

			Object o = optionId.newInstance();
			// Now try to cast to a IFunctionAdaptor
			result = (IFunctionAdaptor) o;
		}
		catch (InstantiationException e)
		{
			// TODO
		}
		catch (IllegalAccessException e)
		{
			// TODO
		}
		return result;
	}

	/**
	 * This will execute a default method. The default method accepts one parameter (User Modifiable Data) <br>
	 * and returns something
	 * 
	 * @param methodName
	 *            - the method name
	 * @param userModifyData
	 *            - the user modifiable data
	 * @param defaultValue
	 *            - the default value to return when any error occurred
	 * 
	 * @return the output of the method
	 */
	public Object executeDefaultMethod(String methodName, UserModifyData userModifyData, Object defaultValue)
	{
		try
		{
			Method method = functionAdaptorClass.getDeclaredMethod(methodName, UserModifyData.class);
			Object retVal = method.invoke(functionAdaptorImpl, userModifyData);
			return retVal;
		}
		catch (NoSuchMethodException e)
		{
			return defaultValue;
		}
		catch (InvocationTargetException e)
		{
			return defaultValue;
		}
		catch (IllegalAccessException e)
		{
			return defaultValue;
		}
	}

	/**
	 * Return the field adaptor implementation of the specified field
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param fieldId
	 *            - the field id
	 * @param type
	 *            the specific GAZTYP type for this class
	 * 
	 * @return the field adaptor implementation
	 */
	public FieldAdaptor getFieldAdaptor(EquationStandardSession session, String fieldId, String type) throws EQException
	{
		FieldAdaptor fieldAdaptor = new FieldAdaptor(session, classLoader, functionAdaptorImpl, optionId, fieldId, type,
						functionData);
		fieldAdaptor.setFunctionData(fhd, functionData);

		// adaptor not loaded, then try loading from the secondary services
		if (secondaryFunctionAdaptors != null && fieldAdaptor.getFieldAdaptorClass() == null)
		{
			for (FunctionAdaptor secondaryFunctionAdaptor : secondaryFunctionAdaptors)
			{
				fieldAdaptor = secondaryFunctionAdaptor.getFieldAdaptor(session, fieldId, type);
				if (fieldAdaptor.getFieldAdaptorClass() != null)
				{
					break;
				}
			}
		}

		return fieldAdaptor;
	}

	/**
	 * Return the value adaptor implementation for the specified set of fields
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param fieldId
	 *            - the field Id
	 * @param pvId
	 *            - the PV Id
	 * @param type
	 *            the specific GAZTYP type for this class
	 * 
	 * @return the value adaptor implementation
	 */
	public ValueAdaptor getValueAdaptor(EquationStandardSession session, String fieldId, String pvId, String type)
					throws EQException
	{
		ValueAdaptor valueAdaptor = new ValueAdaptor(session, classLoader, functionAdaptorImpl, optionId, fieldId, pvId, type);
		valueAdaptor.setFunctionData(fhd, functionData);

		// adaptor not loaded, then try loading from the secondary services
		if (secondaryFunctionAdaptors != null && valueAdaptor.getValueAdaptorClass() == null)
		{
			for (FunctionAdaptor secondaryFunctionAdaptor : secondaryFunctionAdaptors)
			{
				valueAdaptor = secondaryFunctionAdaptor.getValueAdaptor(session, fieldId, pvId, type);
				if (valueAdaptor.getValueAdaptorClass() != null)
				{
					break;
				}
			}
		}

		return valueAdaptor;
	}

	/**
	 * Return the value adaptor implementation for the specified set of fields
	 * <p>
	 * The <code>AdaptorKeyFields</code> instance is expected to be obtained by calling one of the <code>getGAZKeyFields</code>
	 * methods on the <code>AdaptorToolbox</code> class. This ensures that the same GAZPF key field values are used when both
	 * writing and reading the records.
	 * 
	 * @param session
	 *            an <code>EquationStandardSession</code>
	 * @param adaptorKeyFields
	 *            an <code>AdaptorKeyFields</code> that contains the GAZPF key fields
	 * 
	 * @return the value adaptor implementation
	 */
	public ValueAdaptor getValueAdaptor(EquationStandardSession session, AdaptorKeyFields adaptorKeyFields) throws EQException
	{
		return getValueAdaptor(session, adaptorKeyFields.getGazfld(), adaptorKeyFields.getGazpvn(), adaptorKeyFields.getGaztyp());
	}

	/**
	 * Returns a <code>FieldSetAdaptor</code> implementation for the specified fieldset
	 * 
	 * @param session
	 *            - a <code>EquationStandardSession</code>
	 * @param fieldSet
	 *            - the <code>FieldSet</code>, either a <code>APIFieldSet</code> or a <code>PVFieldSet</code>
	 * 
	 * @return the value adaptor implementation
	 */
	public FieldSetAdaptor getFieldSetAdaptor(EquationStandardSession session, FieldSet fieldSet) throws EQException
	{
		FieldSetAdaptor fieldSetAdaptor = new FieldSetAdaptor(session, classLoader, functionAdaptorImpl, optionId, fieldSet);
		fieldSetAdaptor.setFunctionData(fhd, functionData);

		// adaptor not loaded, then try loading from the secondary services
		if (secondaryFunctionAdaptors != null && fieldSetAdaptor.getFieldSetAdaptorClass() == null)
		{
			for (FunctionAdaptor secondaryFunctionAdaptor : secondaryFunctionAdaptors)
			{
				fieldSetAdaptor = secondaryFunctionAdaptor.getFieldSetAdaptor(session, fieldSet);
				if (fieldSetAdaptor.getFieldSetAdaptorClass() != null)
				{
					break;
				}
			}
		}

		return fieldSetAdaptor;
	}

	/**
	 * Returns an <code>InputFieldSetAdaptor</code> implementation for the specified inputfieldset
	 * 
	 * @param session
	 *            - a <code>EquationStandardSession</code>
	 * @param fieldSet
	 *            - the <code>FieldSet</code>
	 * 
	 * @return the value adaptor implementation
	 */
	public InputFieldSetAdaptor getInputFieldSetAdaptor(EquationStandardSession session, InputFieldSet fieldSet) throws EQException
	{
		InputFieldSetAdaptor inputFieldSetAdaptor = new InputFieldSetAdaptor(session, classLoader, functionAdaptorImpl, optionId,
						fieldSet);
		inputFieldSetAdaptor.setFunctionData(fhd, functionData);

		// secondary adaptors?
		if (secondaryFunctionAdaptors != null)
		{
			inputFieldSetAdaptor.setSecondaryFunctionIds(session, secondaryFunctionAdaptors, fieldSet);
		}

		return inputFieldSetAdaptor;
	}

	/**
	 * Call the user exit procedure to perform defaulting of values before the standard validation process
	 * 
	 * @param curScreen
	 *            - the current screen
	 * 
	 * @return true if data has been changed
	 */
	public boolean defaultMode(int curScreen)
	{
		if (functionAdaptorImpl != null)
		{
			String id = fhd.getScreenSetHandler().rtvScrnSetCurrent().getFunction().getInputFieldSets().get(curScreen).getId();
			UserModifyData userModifyData = getUserModifyData();

			// call the service default mode
			functionAdaptorImpl.getMethodCallStatus().setAbstractDefaultMethodExecute(false);
			functionAdaptorImpl.defaultMode(curScreen + 1, userModifyData);

			// call a Misys-defined default user exit
			misysExitFunctionAdaptor.defaultMode(id, userModifyData);
			boolean changed = userModifyData.isChanged();
			// call a bank-defined default user exit
			userExitFunctionAdaptor.defaultMode(id, userModifyData);
			changed = changed || userModifyData.isChanged();

			// call default user exit in the secondary services
			if (secondaryFunctionAdaptors != null)
			{
				for (FunctionAdaptor secondaryFunctionAdaptor : secondaryFunctionAdaptors)
				{
					boolean changed2 = secondaryFunctionAdaptor.defaultMode(curScreen);
					changed = changed || changed2;
				}
				// execute linkage user exit
				linkedUserExitFunctionAdaptor.defaultMode(id, userModifyData);
				changed = changed || userModifyData.isChanged();
			}
			// data changed?
			return changed;
		}
		return false;
	}

	/**
	 * Call the user exit procedure to perform validation after the standard validation process
	 * 
	 * @param curScreen
	 *            - the current screen
	 * 
	 * @return true if data has been changed
	 */
	public boolean validateMode(int curScreen)
	{
		if (functionAdaptorImpl != null)
		{
			functionAdaptorImpl.clearMessages();
			UserModifyData userModifyData = getUserModifyData();

			// call the service validate mode
			functionAdaptorImpl.getMethodCallStatus().setAbstractValidateMethodExecute(false);
			functionAdaptorImpl.getReturnMessages().setFunctionData(functionData);
			functionAdaptorImpl.validateMode(curScreen + 1, userModifyData);

			String id = fhd.getScreenSetHandler().rtvScrnSetCurrent().getFunction().getInputFieldSets().get(curScreen).getId();
			// call a Misys-defined validate user exit
			misysExitFunctionAdaptor.validateMode(id, userModifyData, functionAdaptorImpl.getReturnMessages());
			boolean changed = userModifyData.isChanged();
			// call a bank-defined validate user exit
			userExitFunctionAdaptor.validateMode(id, userModifyData, functionAdaptorImpl.getReturnMessages());
			changed = changed || userModifyData.isChanged();

			// call validate user exit in the secondary services
			if (secondaryFunctionAdaptors != null)
			{
				for (FunctionAdaptor secondaryFunctionAdaptor : secondaryFunctionAdaptors)
				{
					boolean changed2 = secondaryFunctionAdaptor.validateMode(curScreen);
					functionAdaptorImpl.getReturnMessages().addMessages(
									secondaryFunctionAdaptor.getFunctionAdaptorImpl().getReturnMessages().getReturnMessages());
					changed = changed || changed2;
				}
				// execute linkage user exit
				linkedUserExitFunctionAdaptor.validateMode(id, userModifyData, functionAdaptorImpl.getReturnMessages());
				changed = changed || userModifyData.isChanged();
			}

			// data changed?
			return changed;
		}
		return false;
	}

	/**
	 * Call the user exit procedure to perform update before the standard update process
	 * 
	 * @param journalHeader
	 *            - the journal header of the main transaction
	 */
	public void preUpdate(JournalHeader journalHeader)
	{
		if (functionAdaptorImpl != null)
		{
			functionAdaptorImpl.clearMessages();
			functionAdaptorImpl.getMethodCallStatus().setAbstractPreUpdateMethodExecute(false);

			// call the service pre update user exit
			functionAdaptorImpl.preUpdate(journalHeader, userData);

			UserModifyData userModifyData = getUserModifyData();
			// call a Misys-defined pre-update user exit
			misysExitFunctionAdaptor.preUpdate(journalHeader, userModifyData, functionAdaptorImpl.getReturnMessages());
			// call a bank-defined pre-update user exit
			userExitFunctionAdaptor.preUpdate(journalHeader, userModifyData, functionAdaptorImpl.getReturnMessages());

			// call pre-update user exit in the secondary services
			if (secondaryFunctionAdaptors != null)
			{
				for (FunctionAdaptor secondaryFunctionAdaptor : secondaryFunctionAdaptors)
				{
					secondaryFunctionAdaptor.preUpdate(journalHeader);
					functionAdaptorImpl.getReturnMessages().addMessages(
									secondaryFunctionAdaptor.getFunctionAdaptorImpl().getReturnMessages().getReturnMessages());
				}
				// execute linkage user exit
				linkedUserExitFunctionAdaptor.preUpdate(journalHeader, userModifyData, functionAdaptorImpl.getReturnMessages());
			}
		}
	}

	/**
	 * Call the user exit procedure to perform update after the standard update process
	 * 
	 * @param journalHeader
	 *            - the journal header of the main transaction
	 */
	public void postUpdate(JournalHeader journalHeader, Map<String, IEquationStandardObject> transactions)
	{
		if (functionAdaptorImpl != null)
		{
			functionAdaptorImpl.clearMessages();
			functionAdaptorImpl.getMethodCallStatus().setAbstractPostUpdateMethodExecute(false);

			// call the service post update mode
			userData.setTransactions(transactions);
			functionAdaptorImpl.postUpdate(journalHeader, userData);
			userData.setTransactions(null);

			UserModifyData userModifyData = getUserModifyData();
			// call a Misys-defined post-update user exit
			misysExitFunctionAdaptor.postUpdate(journalHeader, userModifyData, functionAdaptorImpl.getReturnMessages());
			// call a bank-defined post-update user exit
			userExitFunctionAdaptor.postUpdate(journalHeader, userModifyData, functionAdaptorImpl.getReturnMessages());

			// call post-update user exit in the secondary services
			if (secondaryFunctionAdaptors != null)
			{
				for (FunctionAdaptor secondaryFunctionAdaptor : secondaryFunctionAdaptors)
				{
					secondaryFunctionAdaptor.postUpdate(journalHeader, transactions);
					functionAdaptorImpl.getReturnMessages().addMessages(
									secondaryFunctionAdaptor.getFunctionAdaptorImpl().getReturnMessages().getReturnMessages());
				}
				// execute linkage user exit
				linkedUserExitFunctionAdaptor.postUpdate(journalHeader, getUserModifyData(), functionAdaptorImpl
								.getReturnMessages());
			}
		}
	}

	/**
	 * Call the user exit procedure to perform load before the standard retrieval process
	 * 
	 */
	public void preLoad()
	{
		if (functionAdaptorImpl != null)
		{
			functionAdaptorImpl.clearMessages();
			UserModifyData userModifyData = getUserModifyData();
			functionAdaptorImpl.getMethodCallStatus().setAbstractPreLoadMethodExecute(false);

			// call the service pre-load user exit
			functionAdaptorImpl.preLoad(userModifyData);

			// call a Misys-defined pre-load user exit
			misysExitFunctionAdaptor.preLoad(userModifyData, functionAdaptorImpl.getReturnMessages());
			// call a bank-defined pre-load user exit
			userExitFunctionAdaptor.preLoad(userModifyData, functionAdaptorImpl.getReturnMessages());

			// call pre-load user exit in the secondary services
			if (secondaryFunctionAdaptors != null)
			{
				for (FunctionAdaptor secondaryFunctionAdaptor : secondaryFunctionAdaptors)
				{
					secondaryFunctionAdaptor.preLoad();
					functionAdaptorImpl.getReturnMessages().addMessages(
									secondaryFunctionAdaptor.getFunctionAdaptorImpl().getReturnMessages().getReturnMessages());
				}
				// execute linkage user exit
				linkedUserExitFunctionAdaptor.preLoad(userModifyData, functionAdaptorImpl.getReturnMessages());
			}
		}
	}

	/**
	 * Call the user exit procedure to perform load after the standard retrieval process
	 * 
	 * @return the function mode
	 */
	public String postLoad(Map<String, IEquationStandardObject> transactions)
	{
		if (functionAdaptorImpl != null)
		{
			functionAdaptorImpl.clearMessages();
			functionAdaptorImpl.getMethodCallStatus().setAbstractPostLoadMethodExecute(false);
			UserModifyData userModifyData = getUserModifyData();

			// call the service post-load user exit
			userModifyData.setTransactions(transactions);
			String mode = functionAdaptorImpl.postLoad(userModifyData);

			// call a Misys-defined post-load user exit
			String mode2 = misysExitFunctionAdaptor.postLoad(userModifyData, functionAdaptorImpl.getReturnMessages());
			// which mode?
			if (mode2 != null)
			{
				mode = mode2;
			}
			// call a bank-defined post-load user exit
			String mode3 = userExitFunctionAdaptor.postLoad(userModifyData, functionAdaptorImpl.getReturnMessages());
			// which mode?
			if (mode3 != null)
			{
				mode = mode3;
			}

			// call post-load user exit in the secondary services
			if (secondaryFunctionAdaptors != null)
			{
				for (FunctionAdaptor secondaryFunctionAdaptor : secondaryFunctionAdaptors)
				{
					secondaryFunctionAdaptor.postLoad(transactions);
					functionAdaptorImpl.getReturnMessages().addMessages(
									secondaryFunctionAdaptor.getFunctionAdaptorImpl().getReturnMessages().getReturnMessages());
				}
				// execute linkage user exit
				String mode4 = linkedUserExitFunctionAdaptor.postLoad(userModifyData, functionAdaptorImpl.getReturnMessages());
				// which mode?
				if (mode4 != null)
				{
					mode = mode4;
				}
			}

			return mode;
		}
		return null;
	}

	/**
	 * Call the user exit procedure to perform initialisation of EFC charges
	 * 
	 * @param functionDataEFC
	 *            - the Function Data for the EFC
	 */
	public void postLoadEFC(FunctionData functionDataEFC)
	{
		if (functionAdaptorImpl != null)
		{
			functionAdaptorImpl.clearMessages();
			functionAdaptorImpl.getMethodCallStatus().setAbstractPostLoadEFCMethodExecute(false);
			UserModifyData userModifyData = getUserModifyData(functionDataEFC);

			// call the service post-load EFC user exit
			functionAdaptorImpl.postLoadEFC(userData, userModifyData);

			// call a Misys-defined post-load user exit
			misysExitFunctionAdaptor.postLoadEFC(userData, userModifyData);
			// call a bank-defined post-load user exit
			userExitFunctionAdaptor.postLoadEFC(userData, userModifyData);

			// call post-load EFC user exit in the secondary services
			if (secondaryFunctionAdaptors != null)
			{
				for (FunctionAdaptor secondaryFunctionAdaptor : secondaryFunctionAdaptors)
				{
					secondaryFunctionAdaptor.postLoadEFC(functionDataEFC);
					functionAdaptorImpl.getReturnMessages().addMessages(
									secondaryFunctionAdaptor.getFunctionAdaptorImpl().getReturnMessages().getReturnMessages());
				}
				// execute linkage user exit
				linkedUserExitFunctionAdaptor.postLoadEFC(userData, userModifyData);
			}
		}
	}

	/**
	 * Determine whether abstract method were executed (true) or user defined method (false)
	 * 
	 * @return true if abstract method were executed
	 */
	public boolean isAbstractPostLoadMethodExecute()
	{
		if (functionAdaptorImpl != null)
		{
			return functionAdaptorImpl.getMethodCallStatus().isAbstractPostLoadMethodExecute();
		}
		return true;
	}

	/**
	 * Call the user exit procedure to perform processing on cancellation of transaction (either F3 / F12)
	 * 
	 */
	public void onCancelUserExit()
	{
		if (functionAdaptorImpl != null)
		{
			functionAdaptorImpl.clearMessages();
			functionAdaptorImpl.getMethodCallStatus().setAbstractOnCancelMethodExecute(false);
			UserModifyData userModifyData = getUserModifyData();

			// call the service on-cancel user exit
			functionAdaptorImpl.onCancel(userModifyData);

			// call a Misys-defined on-cancel user exit
			misysExitFunctionAdaptor.onCancel(userModifyData, functionAdaptorImpl.getReturnMessages());
			// call a bank-defined on-cancel user exit
			userExitFunctionAdaptor.onCancel(userModifyData, functionAdaptorImpl.getReturnMessages());

			// call on-cancel user exit in the secondary services
			if (secondaryFunctionAdaptors != null)
			{
				for (FunctionAdaptor secondaryFunctionAdaptor : secondaryFunctionAdaptors)
				{
					secondaryFunctionAdaptor.onCancelUserExit();
					functionAdaptorImpl.getReturnMessages().addMessages(
									secondaryFunctionAdaptor.getFunctionAdaptorImpl().getReturnMessages().getReturnMessages());
				}
				// execute linkage user exit
				linkedUserExitFunctionAdaptor.onCancel(userModifyData, functionAdaptorImpl.getReturnMessages());
			}
		}
	}

	/**
	 * Call the user exit procedure to perform processing whenever the update processing is called whether successful or not
	 * (outside of commitment control)
	 * 
	 * @param journalHeader
	 *            - the journal header. NULL if update is not successful and the apiFieldSetId determines the last update it was
	 *            trying to do
	 * @param apiFieldSetId
	 *            - the last API field set id it is trying to processed. This infomation can be combined with the journal header to
	 *            or failure. If this is "asi" and journal header is null, then it means it failed in processing update api "asi"<br>
	 * @param functionMessages
	 *            - the list of function messages
	 */
	public void onFinalUpdate(JournalHeader journalHeader, String apiFieldSetId, FunctionMessages functionMessages)
	{
		if (functionAdaptorImpl != null)
		{
			functionAdaptorImpl.clearMessages();
			functionAdaptorImpl.getMethodCallStatus().setAbstractOnFinalUpdateMethodExecute(false);

			// call the service on-final user exit
			functionAdaptorImpl.onFinalUpdate(journalHeader, apiFieldSetId, userData, functionMessages);

			// call a Misys-defined on-final user exit
			misysExitFunctionAdaptor.onFinalUpdate(journalHeader, apiFieldSetId, userData, functionMessages);
			// call a bank-defined on-final user exit
			userExitFunctionAdaptor.onFinalUpdate(journalHeader, apiFieldSetId, userData, functionMessages);

			// call on-final user exit in the secondary services
			if (secondaryFunctionAdaptors != null)
			{
				for (FunctionAdaptor secondaryFunctionAdaptor : secondaryFunctionAdaptors)
				{
					secondaryFunctionAdaptor.onFinalUpdate(journalHeader, apiFieldSetId, functionMessages);
					functionAdaptorImpl.getReturnMessages().addMessages(
									secondaryFunctionAdaptor.getFunctionAdaptorImpl().getReturnMessages().getReturnMessages());
				}
				// execute linkage user exit
				linkedUserExitFunctionAdaptor.onFinalUpdate(journalHeader, apiFieldSetId, userData, functionMessages);
			}
		}
	}

	/**
	 * Call the user exit procedure to determine the LRP web service URL for ESF
	 * 
	 */
	public UserExitESFProcessDetail getLRPTemplateProcessDetail()
	{
		if (functionAdaptorImpl != null)
		{
			functionAdaptorImpl.clearMessages();
			functionAdaptorImpl.getMethodCallStatus().setAbstractGetLRPTemplateProcessDetailMethodExecute(false);
			return functionAdaptorImpl.getLRPTemplateProcessDetail(userData);
		}
		return null;
	}

	/**
	 * Determine whether the function requires post update Microflow
	 * 
	 * @param function
	 *            - the function
	 * 
	 * @return true if post update Microflow is required
	 */
	public boolean isPostUpdateMicroFlow(Function function)
	{
		String postUpdateMicroflow = function.getPostUpdateMicroFlow();

		// for backward compatibility
		if (Function.POST_UPDATE_MICROFLOW_NO.equals(postUpdateMicroflow) && function.getPostUpdateMicroflow())
		{
			LOG.debug("isPostUpdateMicroFlow for service [" + function.getId() + "] returning true (backward compatibility)");
			return true;
		}

		else if (Function.POST_UPDATE_MICROFLOW_NO.equals(postUpdateMicroflow))
		{
			LOG.debug("isPostUpdateMicroFlow for service [" + function.getId() + "] returning false (NO)");
			return false;
		}

		else if (Function.POST_UPDATE_MICROFLOW_YES.equals(postUpdateMicroflow))
		{
			LOG.debug("isPostUpdateMicroFlow for service [" + function.getId() + "] returning true (YES)");
			return true;
		}

		else if (Function.POST_UPDATE_MICROFLOW_SCRIPT.equals(postUpdateMicroflow))
		{
			boolean scriptResult = ELRuntime.runBooleanScript(function.getPostUpdateMicroFlowExpression(), functionData, function
							.getId(), "post update microflow", false, ELRuntime.REALTYPE_VALUE);
			LOG.debug("isPostUpdateMicroFlow for service [" + function.getId() + "] returning [" + scriptResult + "] (SCRIPT)");
			return scriptResult;
		}

		else
		{
			throw new EQRuntimeException("Function [" + function.getId() + "] getPostUpdateMicroFlow contains invalid value ["
							+ postUpdateMicroflow + "]");
		}
	}

	/**
	 * Set the secondary id
	 * 
	 * @param session
	 *            - the Equation session
	 * @param secondaryFunctionIds
	 *            - the secondary ids
	 * @param type
	 *            - type
	 */
	public void setSecondaryFunctionIds(EquationStandardSession session, String[] secondaryFunctionIds, String type)
					throws EQException
	{
		secondaryFunctionAdaptors = new ArrayList<FunctionAdaptor>();
		for (String secondaryFunctionId : secondaryFunctionIds)
		{
			// load the function adaptor
			FunctionAdaptor functionAdaptor = new FunctionAdaptor(session, secondaryFunctionId, type);
			functionAdaptor.setFunctionData(fhd, functionData);
			secondaryFunctionAdaptors.add(functionAdaptor);
		}
		linkedUserExitFunctionAdaptor = new UserExitFunctionAdaptor(session, classLoader, functionAdaptorImpl, fhd.getOptionId(),
						false);
	}

	/**
	 * Set the Function data for this function adaptor and all of the secondary adaptors
	 * 
	 * @param functionData
	 *            - reset the function data
	 */
	public void resetFunctionData(FunctionData functionData) throws EQException
	{
		// reset the function data of the main function adaptor
		setFunctionData(fhd, functionData);

		// reset the function data of the secondary function adaptor
		if (secondaryFunctionAdaptors != null)
		{
			for (FunctionAdaptor secondaryFunctionAdaptor : secondaryFunctionAdaptors)
			{
				secondaryFunctionAdaptor.setFunctionData(fhd, functionData);
			}
		}
	}

	/**
	 * Return the secondary function adaptors
	 * 
	 * @return the secondary function adaptors
	 */
	public List<FunctionAdaptor> getSecondaryFunctionAdaptors()
	{
		return secondaryFunctionAdaptors;
	}

	/**
	 * Return the equivalent complex data type of the Function
	 * 
	 * @param session
	 *            - the Equation Standard Session
	 * @param id
	 *            - the id
	 * @param pvId
	 *            - the pv id
	 * 
	 * @return the complex data type
	 * 
	 * @throws EQException
	 */
	public Object getBankFusionDataType(EquationStandardSession session, String id, String pvId) throws EQException
	{
		try
		{
			Class<Object> bfType = getBFComplexTypeClass(session, id, pvId);

			// complex type not loaded
			if (bfType == null)
			{
				LOG.error(LanguageResources.getFormattedString("FunctionAdaptor.NoComplexType", new String[] { id, pvId }));
				return null;
			}

			Object bf_functionData = bfType.newInstance();
			return bf_functionData;
		}
		catch (Exception e)
		{
			LOG.error(LanguageResources.getFormattedString("FunctionAdaptor.CannotCreateInstance", new String[] { id, pvId }), e);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
	}

	/**
	 * Return the equivalent complex data type of the Function
	 * 
	 * @param session
	 *            - the Equation Standard Session
	 * @param id
	 *            - the id
	 * @param pvId
	 *            - the pv id
	 * 
	 * @return the complex data type
	 * 
	 * @throws EQException
	 */
	public Class<Object> getBFComplexTypeClass(EquationStandardSession session, String id, String pvId) throws EQException
	{
		try
		{
			Class<Object> bfType = classLoader.loadClassWithCheckClassPath(session, optionId, id, pvId,
							GAZRecordDataModel.TYP_BFTYPE, pvId);
			return bfType;
		}
		catch (Exception e)
		{
			LOG.error(LanguageResources.getFormattedString("FunctionAdaptor.NoComplexType", new String[] { id, pvId }), e);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
	}

	/**
	 * Return the request combined type
	 * 
	 * @param service
	 *            - the function
	 * @param serviceHeader
	 *            - the service request header
	 * @param serviceData
	 *            - the data
	 */
	public Object getRequestCombinedType(EquationStandardSession session, Function service, ServiceRqHeader serviceHeader,
					Object serviceData) throws EQException
	{
		String combinedRequestType = ComplexTypeToolbox.getComplexTypePackage(service) + "."
						+ XSDToolbox.getCombinedComplexTypeRequestName(service);
		Object combinedData = getBankFusionDataType(session, "", combinedRequestType);

		if (combinedData != null)
		{
			FunctionBankFusion fb = new FunctionBankFusion();
			fb.setFieldObject(combinedData, "set" + XSDToolbox.SERVICE_HEADER_TAG, serviceHeader);
			fb.setFieldObject(combinedData, "set" + XSDToolbox.SERVICE_DATA_TAG, serviceData);
		}

		return combinedData;
	}

}
