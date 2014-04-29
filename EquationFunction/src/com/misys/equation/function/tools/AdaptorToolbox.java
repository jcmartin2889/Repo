package com.misys.equation.function.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IGAZRecordDao;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQRuntimeException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.FileUtils;
import com.misys.equation.function.adaptor.AdaptorKeyFields;
import com.misys.equation.function.beans.APIField;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayButtonLink;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.DisplayItemList;
import com.misys.equation.function.beans.DisplayLabel;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.FieldSet;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.IDisplayItem;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.beans.PVField;
import com.misys.equation.function.beans.PVFieldSet;
import com.misys.equation.function.beans.WorkField;
import com.misys.equation.function.runtime.UserExitDetails;

public class AdaptorToolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AdaptorToolbox.java 17190 2013-09-03 11:49:59Z Lima12 $";
	/**
	 * Logger for this class
	 */
	private static final EquationLogger LOG = new EquationLogger(AdaptorToolbox.class);
	public final static String FIELD_SFX = "_FieldAdaptor";
	public final static String VALUE_SFX = "_ValueAdaptor";
	public final static String UPDATE_SFX = "_UpdateValueAdaptor";
	public final static String LOAD_SFX = "_LoadValueAdaptor";

	/** A value to indicate that this is not a valid assignment */
	public final static int INVALID_ASSIGNMENT = 0;
	/** Indicates a Load assignment */
	public final static int LOAD_ASSIGNMENT = 1;
	/** Indicates a Validate assignment */
	public final static int VALIDATE_ASSIGNMENT = 2;
	/** Indicates an Update assignment */
	public final static int UPDATE_ASSIGNMENT = 3;

	/** Display Attributes class name suffix */
	public final static String ATTRIBUTES_SFX = "_AttributesAdaptor";

	/** Display Attributes class name suffix */
	public final static String DISPLAYGROUP_SFX = "_DisplayGroupAdaptor";

	/** Display Label class name suffix */
	public final static String DISPLAYLABEL_SFX = "_DisplayLabelAdaptor";

	/** Display Attributes Set class name suffix */
	public final static String DISPLAYATTRIBUTESSET_SFX = "_AttributesSetAdaptor";

	/** Display Button/Link class name suffix */
	public final static String DISPLAYBUTTONLINK_SFX = "_DisplayButtonLinkAdaptor";

	/** Name of the superclass that Fieldset inner classes extend */
	public static final String FIELDSET_ADAPTOR_SUPERCLASS_NAME = "AbstractFieldSetAdaptor";

	/** Name of the superclass that InputFieldset inner classes extend */
	public static final String INPUTFIELDSET_ADAPTOR_SUPERCLASS_NAME = "AbstractInputFieldSetAdaptor";

	/** Name of the superclass that Field inner classes extend */
	public static final String FIELD_ADAPTOR_SUPERCLASS_NAME = "AbstractFieldAdaptor";

	/** Name of the superclass that (Display) Attributes inner classes extend */
	public static final String ATTRIBUTES_ADAPTOR_SUPERCLASS_NAME = "AbstractAttributesAdaptor";

	/** Name of the superclass that (Display) Attributes inner classes extend */
	public static final String DISPLAYGROUP_ADAPTOR_SUPERCLASS_NAME = "AbstractDisplayGroupAdaptor";

	/** Name of the superclass that Label inner classes extend */
	public static final String DISPLAYLABEL_ADAPTOR_SUPERCLASS_NAME = "AbstractDisplayLabelAdaptor";

	/** Name of the superclass that Attributes set inner classes extend */
	public static final String DISPLAYATTRIBUTESSET_ADAPTOR_SUPERCLASS_NAME = "AbstractAttributesSetAdaptor";

	/** Name of the superclass that Button/Link inner classes extend */
	public static final String DISPLAYBUTTONLINK_ADAPTOR_SUPERCLASS_NAME = "AbstractDisplayButtonLinkAdaptor";

	/** Name of the superclass that Value (Load/Validate/Update assignment) inner classes extend */
	public static final String VALUE_ADAPTOR_SUPERCLASS_NAME = "AbstractValueAdaptor";

	/** Service "defaultMode" method name */
	public static final String DEFAULT_MODE_METHOD_NAME = "defaultMode";
	/** Service "validateMode" method name */
	public static final String VALIDATE_MODE_METHOD_NAME = "validateMode";

	/** InputFieldSet "initialiseMode" method name */
	public static final String INITIALISE_MODE_METHOD_NAME = "initialiseMode";
	/** InputFieldSet "defaultMode" method name */
	public static final String DEFAULT_MODE_METHOD_NAME_FIELD_SET = "defaultModeFS";
	/** InputFieldSet "validateMode" method name */
	public static final String VALIDATE_MODE_METHOD_NAME_FIELD_SET = "validateModeFS";

	/** Service "initialValue" method name */
	public static final String INITIAL_VALUE_METHOD_NAME = "initialValue";

	/** Service "defaultValue" method name */
	public static final String DEFAULT_VALUE_METHOD_NAME = "defaultValue";

	/** FieldSet "shouldExecuteModule" method name */
	public static final String SHOULDEXECUTEMODULE_METHOD_NAME = "shouldExecuteModule";

	/** Field "isMandatory" method name */
	public static final String IS_MANDATORY_METHOD_NAME = "isMandatory";
	/** Field "isValid" method name */
	public static final String IS_VALID_METHOD_NAME = "isValid";

	/** DisplayAttributes "isProtected" method name */
	public static final String IS_PROTECTED_METHOD_NAME = "isProtected";
	/** DisplayAttributes "isVisible" method name */
	public static final String IS_VISIBLE_METHOD_NAME = "isVisible";
	/** DisplayAttributes "getEditingParameter" method name */
	public static final String GET_EDITING_PARAMETER_METHOD_NAME = "getEditingParameter";
	/** DisplayAttributes "isInGridVisible" method name */
	public static final String IS_IN_GRID_VISIBLE_METHOD_NAME = "isInGridVisible";
	/** DisplayGroup "isDisplayGroupVisible" method name */
	public static final String IS_DISPLAY_GROUP_VISIBLE_METHOD_NAME = "isVisible";
	/** DisplayLabel "isDisplayLabelVisible" method name */
	public static final String IS_DISPLAY_LABEL_VISIBLE_METHOD_NAME = "isVisible";
	/** DisplayAttributesSet "isFinishButtonEnabled" method name */
	public static final String IS_FINISH_BUTTON_ENABLED_METHOD_NAME = "isFinishButtonEnabled";
	/** DisplayButtonLink "isProtected" method name */
	public static final String IS_DISPLAY_BUTTON_VISIBLE_METHOD_NAME = "isVisible";
	/** DisplayButtonLink "isProtected" method name */
	public static final String IS_DISPLAY_BUTTON_PROTECTED_METHOD_NAME = "isProtected";
	/** DisplayButtonLink "getCommandParameter" method name */
	public static final String IS_DISPLAY_BUTTOM_CMD_PARAM_METHOD_NAME = "getCommandParameter";

	/** Layout "nextScreen" method name */
	public static final String NEXT_SCREEN_METHOD_NAME = "nextScreen";
	/** Layout "prevScreen" method name */
	public static final String PREV_SCREEN_METHOD_NAME = "prevScreen";

	/** Value "getValue" method name */
	public static final String GET_VALUE_METHOD_NAME = "getValue";

	/** "postLoad" method name */
	public static final String POST_LOAD_METHOD_NAME = "postLoad";
	/** "postUpdateEFC" method name */
	public static final String POST_LOAD_EFC_METHOD_NAME = "postLoadEFC";
	/** "preUpdate" method name */
	public static final String PRE_UPDATE_METHOD_NAME = "preUpdate";
	/** "postUpdate" method name */
	public static final String POST_UPDATE_METHOD_NAME = "postUpdate";

	/** Repeating Group "getTopColumnDetails" method name */
	public static final String GET_TOP_COLUMN_DETAILS_METHOD_NAME = "getTopColumnDetails";
	/** Repeating Group "getBottomColumnDetails" method name */
	public static final String GET_BOTTOM_COLUMN_DETAILS_METHOD_NAME = "getBottomColumnDetails";
	/** Repeating Group "getAboveRowColumnDetails" method name */
	public static final String GET_ABOVE_ROW_COLUMN_DETAILS_METHOD_NAME = "getAboveRowColumnDetails";
	/** Repeating Group "getBelowRowColumnDetails" method name */
	public static final String GET_BELOW_ROW_COLUMN_DETAILS_METHOD_NAME = "getBelowRowColumnDetails";

	/** Method name for retrieving LRP Template Process Detail **/
	public static final String GET_LRP_TEMPLATE_PROCESS_DETAIL = "getLRPTemplateProcessDetail";

	private static DaoFactory daoFactory = new DaoFactory();

	/**
	 * Returns the class name of a layout adaptor class given the option id, field id, and the type
	 * 
	 * @param packageName
	 *            name of the Java package
	 * @param mainClassName
	 *            - the class name
	 * @param fieldId
	 *            - the field Id
	 * @param type
	 *            - the type
	 * 
	 * @return the qualified class name
	 */
	private static String getLayoutClassName(String packageName, String mainClassName, String fieldId, String type)
	{
		StringBuffer name = new StringBuffer(packageName);
		if (name.length() != 0)
		{
			name.append('.');
		}
		name.append(mainClassName);
		// field id is defined?
		if (!fieldId.equals(""))
		{
			name.append("$");
			name.append(fieldId);
		}

		// type is defined?
		if (type.equals(""))
		{
		}
		else if (type.equals(GAZRecordDataModel.TYP_ATTRIBUTES))
		{
			name.append(ATTRIBUTES_SFX);
		}
		else if (type.equals(GAZRecordDataModel.TYP_GROUP_ATTRIBUTES))
		{
			name.append(DISPLAYGROUP_SFX);
		}
		else if (type.equals(GAZRecordDataModel.TYP_LABEL_ATTRIBUTES))
		{
			name.append(DISPLAYLABEL_SFX);
		}
		else if (type.equals(GAZRecordDataModel.TYP_ATTRIBUTESSET_ATTRIBUTES))
		{
			name.append(DISPLAYATTRIBUTESSET_SFX);
		}
		else if (type.equals(GAZRecordDataModel.TYP_BUTTONLINK_ATTRIBUTES))
		{
			name.append(DISPLAYBUTTONLINK_SFX);
		}
		return name.toString();
	}

	/**
	 * Write the adaptor classes to the file
	 * 
	 * @param unit
	 *            - the Equation unit
	 * @param function
	 *            - the Function
	 * @param javaBinaryFile
	 *            - the main (outer) class to deploy
	 * 
	 * @throws EQException
	 */
	public static void writeFunctionAdaptor(EquationStandardSession session, String filLibrary, Function function,
					File javaBinaryFile, File javaSourceFile) throws EQException
	{
		try
		{

			// Delete all existing service definition records:
			GAZRecordDataModel gazRecord = new GAZRecordDataModel();
			gazRecord.setLibrary(filLibrary);
			IGAZRecordDao dao = daoFactory.getGAZDao(session, gazRecord);
			dao.deleteRecordByOptionAndType(function.getId(), GAZRecordDataModel.TYP_SERVICE);
			dao.deleteRecordByOptionAndType(function.getId(), GAZRecordDataModel.TYP_LOAD);
			dao.deleteRecordByOptionAndType(function.getId(), GAZRecordDataModel.TYP_VALUE);
			dao.deleteRecordByOptionAndType(function.getId(), GAZRecordDataModel.TYP_UPDATE);
			dao.deleteRecordByOptionAndType(function.getId(), GAZRecordDataModel.TYP_APIFIELDSET);
			dao.deleteRecordByOptionAndType(function.getId(), GAZRecordDataModel.TYP_PVFIELDSET);
			dao.deleteRecordByOptionAndType(function.getId(), GAZRecordDataModel.TYP_FIELD);
			dao.deleteRecordByOptionAndType(function.getId(), GAZRecordDataModel.TYP_INPUTFIELDSET);

			/** For Import Wizard Related changes **/
			dao.deleteRecordByOptionAndType(function.getId(), GAZRecordDataModel.TYP_SERVICE_SRC);

			if (javaBinaryFile.exists())
			{

				String outerClassName = javaBinaryFile.getName().replace(".class", "");
				writeClassToDB(session, filLibrary, javaBinaryFile, function.getId(), "", "", GAZRecordDataModel.TYP_SERVICE,
								function.getPackageName(), outerClassName);

				writeClassToDB(session, filLibrary, javaSourceFile, function.getId(), "", "", GAZRecordDataModel.TYP_SERVICE_SRC,
								function.getPackageName(), outerClassName);

				// All other class files will be in the same folder:
				String classFolderPath = javaBinaryFile.getParentFile().getAbsolutePath() + File.separator;

				File file = null;
				String innerClassName = null;

				// get all the field sets of the function
				for (InputFieldSet inputFieldSet : function.getInputFieldSets())
				{
					// check if the field class adaptor exists
					// if it does, then write it to the file
					innerClassName = outerClassName + "$" + getInnerClassName(inputFieldSet);
					file = new File(classFolderPath + innerClassName.replace(".", File.separator) + ".class");
					if (file.exists())
					{
						writeClassToDB(session, filLibrary, file, function.getId(), inputFieldSet.getId(), "",
										GAZRecordDataModel.TYP_INPUTFIELDSET, function.getPackageName(), innerClassName);
					}

					// get all the fields in the field set
					for (InputField field : inputFieldSet.getInputFields())
					{
						// check if the field class adaptor exists
						// if it does, then write it to the file
						innerClassName = outerClassName + "$" + getInnerClassName(field);
						file = new File(classFolderPath + innerClassName.replace(".", File.separator) + ".class");
						if (file.exists())
						{
							writeClassToDB(session, filLibrary, file, function.getId(), field.getId(), "",
											GAZRecordDataModel.TYP_FIELD, function.getPackageName(), innerClassName);
						}

						// An input field may also have a Validate (Primitive) value adaptor:
						innerClassName = outerClassName + "$"
										+ getInnerClassName(field, VALIDATE_ASSIGNMENT, MappingToolbox.PRIMITIVE);
						file = new File(classFolderPath + innerClassName.replace(".", File.separator) + ".class");
						if (file.exists())
						{
							writeClassToDB(session, filLibrary, file, function.getId(), getGAZKeyFields(field, VALIDATE_ASSIGNMENT,
											MappingToolbox.PRIMITIVE), function.getPackageName(), innerClassName);
						}

						// An input field may also have a Validate (Output) value adaptor:
						innerClassName = outerClassName + "$"
										+ getInnerClassName(field, VALIDATE_ASSIGNMENT, MappingToolbox.OUTPUT);
						file = new File(classFolderPath + innerClassName.replace(".", File.separator) + ".class");
						if (file.exists())
						{
							writeClassToDB(session, filLibrary, file, function.getId(), getGAZKeyFields(field, VALIDATE_ASSIGNMENT,
											MappingToolbox.OUTPUT), function.getPackageName(), innerClassName);
						}

						// An input field may also have a Load value adaptor:
						innerClassName = outerClassName + "$" + getInnerClassName(field, LOAD_ASSIGNMENT, "");
						file = new File(classFolderPath + innerClassName.replace(".", File.separator) + ".class");
						if (file.exists())
						{
							writeClassToDB(session, filLibrary, file, function.getId(),
											getGAZKeyFields(field, LOAD_ASSIGNMENT, ""), function.getPackageName(), innerClassName);
						}

						// get all the PV attached to the field
						for (PVFieldSet pvFieldSet : field.getPvFieldSets())
						{
							// Process the PVFieldSet itself:
							innerClassName = outerClassName + "$" + getInnerClassName(pvFieldSet);
							file = new File(classFolderPath + innerClassName.replace(".", File.separator) + ".class");
							if (file.exists())
							{
								writeClassToDB(session, filLibrary, file, function.getId(), getGAZKeyFields(pvFieldSet), function
												.getPackageName(), innerClassName);
							}

							// Then process all the fields of the PV:
							for (PVField pvField : pvFieldSet.getPVFields())
							{
								innerClassName = outerClassName + "$" + getInnerClassName(pvField, VALIDATE_ASSIGNMENT, "");
								file = new File(classFolderPath + innerClassName.replace(".", File.separator) + ".class");
								if (file.exists())
								{
									writeClassToDB(session, filLibrary, file, function.getId(), field.getId(), getPVNameField(
													pvFieldSet.getId(), pvField.getId()), GAZRecordDataModel.TYP_VALUE, function
													.getPackageName(), innerClassName);
								}
							}
						}
					}
				}

				// process all the work fields
				for (WorkField workField : function.getWorkFields())
				{
					// Note that Workfields do not have field level adaptors, as the exit points on the
					// Field adaptor are only relevant to InputFields (e.g. Mandatory), but WorkFields
					// do still have value adaptors:

					// An work field may have a Validate value adaptor:
					innerClassName = outerClassName + "$"
									+ getInnerClassName(workField, VALIDATE_ASSIGNMENT, MappingToolbox.OUTPUT);
					file = new File(classFolderPath + innerClassName.replace(".", File.separator) + ".class");
					if (file.exists())
					{
						writeClassToDB(session, filLibrary, file, function.getId(), getGAZKeyFields(workField, VALIDATE_ASSIGNMENT,
										""), function.getPackageName(), innerClassName);
					}

					// An work field may also have a Load value adaptor:
					innerClassName = outerClassName + "$" + getInnerClassName(workField, LOAD_ASSIGNMENT, "");
					file = new File(classFolderPath + innerClassName.replace(".", File.separator) + ".class");
					if (file.exists())
					{
						writeClassToDB(session, filLibrary, file, function.getId(),
										getGAZKeyFields(workField, LOAD_ASSIGNMENT, ""), function.getPackageName(), innerClassName);
					}
				}

				// get all the update API field sets
				for (APIFieldSet apiFieldSet : function.getUpdateAPIs())
				{
					// Process the Update API fieldset itself:
					innerClassName = outerClassName + "$" + getInnerClassName(apiFieldSet);
					file = new File(classFolderPath + innerClassName.replace(".", File.separator) + ".class");
					if (file.exists())
					{
						writeClassToDB(session, filLibrary, file, function.getId(), getGAZKeyFields(apiFieldSet), function
										.getPackageName(), innerClassName);
					}

					for (APIField apiField : apiFieldSet.getAPIFields())
					{
						innerClassName = outerClassName + "$" + getInnerClassName(apiField, UPDATE_ASSIGNMENT, "");
						file = new File(classFolderPath + innerClassName.replace(".", File.separator) + ".class");
						if (file.exists())
						{
							writeClassToDB(session, filLibrary, file, function.getId(), apiFieldSet.getId(), apiField.getId(),
											GAZRecordDataModel.TYP_UPDATE, function.getPackageName(), innerClassName);
						}
					}
				}

				// get all the load field sets
				for (InputFieldSet inputFieldSet : function.getInputFieldSets())
				{
					for (APIFieldSet apiFieldSet : inputFieldSet.getLoadAPIs())
					{
						// Process the Load API fieldset itself:
						innerClassName = outerClassName + "$" + getInnerClassName(apiFieldSet);
						file = new File(classFolderPath + innerClassName.replace(".", File.separator) + ".class");
						if (file.exists())
						{
							writeClassToDB(session, filLibrary, file, function.getId(), getGAZKeyFields(apiFieldSet), function
											.getPackageName(), innerClassName);
						}

						for (APIField apiField : apiFieldSet.getAPIFields())
						{
							innerClassName = outerClassName + "$" + getInnerClassName(apiField, LOAD_ASSIGNMENT, "");
							file = new File(classFolderPath + innerClassName.replace(".", File.separator) + ".class");
							if (file.exists())
							{
								writeClassToDB(session, filLibrary, file, function.getId(), apiFieldSet.getId(), apiField.getId(),
												GAZRecordDataModel.TYP_LOAD, function.getPackageName(), innerClassName);
							}
						}
					}
				}
			}

		}
		catch (Exception e)
		{

			throw new EQException("AdaptorToolbox - writeFunctionAdaptor() - Failed", e);
		}

	}

	/**
	 * Write the adaptor classes for a layout to the iSeries database file
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param filLibrary
	 *            - the library to update
	 * @param layout
	 *            - the service layout
	 * @param javaBinaryFile
	 *            - the location of the JAVA user exit
	 * 
	 * @throws EQException
	 */
	public static void writeLayoutAdaptor(EquationStandardSession session, String filLibrary, Layout layout, File javaBinaryFile,
					File javaSourceFile) throws Exception
	{

		// Delete all existing service definition records:
		GAZRecordDataModel gazRecord = new GAZRecordDataModel();
		gazRecord.setLibrary(filLibrary);
		IGAZRecordDao dao = daoFactory.getGAZDao(session, gazRecord);
		dao.deleteRecordByOptionAndType(layout.getId(), GAZRecordDataModel.TYP_LAYOUT);
		dao.deleteRecordByOptionAndType(layout.getId(), GAZRecordDataModel.TYP_BUTTONLINK_ATTRIBUTES);
		dao.deleteRecordByOptionAndType(layout.getId(), GAZRecordDataModel.TYP_ATTRIBUTES);
		dao.deleteRecordByOptionAndType(layout.getId(), GAZRecordDataModel.TYP_GROUP_ATTRIBUTES);
		dao.deleteRecordByOptionAndType(layout.getId(), GAZRecordDataModel.TYP_LABEL_ATTRIBUTES);
		dao.deleteRecordByOptionAndType(layout.getId(), GAZRecordDataModel.TYP_ATTRIBUTESSET_ATTRIBUTES);

		/** For Import Wizard Related changes **/
		dao.deleteRecordByOptionAndType(layout.getId(), GAZRecordDataModel.TYP_LAYOUT_SRC);
		// Get the base folder (i.e. the bin folder)
		File baseFolder = getBaseFolder(javaBinaryFile.getParentFile(), layout.getPackageName());

		String rootPath = baseFolder.getAbsolutePath() + File.separator;

		String mainClass = javaBinaryFile.getName().replace(".class", "");

		// write the main class
		String qualifiedClassName = getLayoutClassName(layout.getPackageName(), mainClass, "", "");
		File file = new File(rootPath + qualifiedClassName.replace(".", File.separator) + ".class");
		if (file.exists())
		{
			writeClassToDB(session, filLibrary, file, layout.getId(), "", "", GAZRecordDataModel.TYP_LAYOUT, "", qualifiedClassName);
			writeClassToDB(session, filLibrary, javaSourceFile, layout.getId(), "", "", GAZRecordDataModel.TYP_LAYOUT_SRC, "",
							qualifiedClassName);

			// get all the display attributes sets of the function
			for (int i = 0; i < layout.getDisplayAttributesSets().size(); i++)
			{
				// get all the display attributes in the set
				DisplayAttributesSet attributesSet = layout.getDisplayAttributesSets().get(i);
				writeDisplayItem(session, filLibrary, layout, rootPath, mainClass, "", attributesSet.getDisplayItems());

				String attributesSetQualifiedClassName = getLayoutClassName(layout.getPackageName(), mainClass, attributesSet
								.getId(), GAZRecordDataModel.TYP_ATTRIBUTESSET_ATTRIBUTES);
				file = new File(rootPath + attributesSetQualifiedClassName.replace(".", File.separator) + ".class");

				if (file.exists())
				{
					writeClassToDB(session, filLibrary, file, layout.getId(), attributesSet.getId(), "",
									GAZRecordDataModel.TYP_ATTRIBUTESSET_ATTRIBUTES, "", attributesSetQualifiedClassName);
				}
			}
		}
	}

	/**
	 * Write the adaptor for the list of items
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param filLibrary
	 *            - the library to update
	 * @param layout
	 *            - the service layout
	 * @param rootPath
	 *            - the root path
	 * @param mainClass
	 *            - the main (outer) class
	 * @param displayItems
	 *            - the list of display items
	 */
	private static void writeDisplayItem(EquationStandardSession session, String filLibrary, Layout layout, String rootPath,
					String mainClass, String parentGroup, DisplayItemList displayItems) throws Exception
	{
		for (IDisplayItem displayItem : displayItems)
		{
			// Display attribute fields
			if (displayItem instanceof DisplayAttributes)
			{
				// check if the field class adaptor exists
				// if it does, then write it to the file
				DisplayAttributes attributes = (DisplayAttributes) displayItem;
				String qualifiedClassName = getLayoutClassName(layout.getPackageName(), mainClass, attributes.getId(),
								GAZRecordDataModel.TYP_ATTRIBUTES);
				File file = new File(rootPath + qualifiedClassName.replace(".", File.separator) + ".class");
				if (file.exists())
				{
					writeClassToDB(session, filLibrary, file, layout.getId(), attributes.getId(), "",
									GAZRecordDataModel.TYP_ATTRIBUTES, "", qualifiedClassName);
				}
			}

			// Display label
			else if (displayItem instanceof DisplayLabel)
			{
				// check if the field class adaptor exists
				// if it does, then write it to the file
				DisplayLabel displayLabel = (DisplayLabel) displayItem;
				String qualifiedClassName = getLayoutClassName(layout.getPackageName(), mainClass, displayLabel.rtvBareId(),
								GAZRecordDataModel.TYP_LABEL_ATTRIBUTES);
				File file = new File(rootPath + qualifiedClassName.replace(".", File.separator) + ".class");
				if (file.exists())
				{
					writeClassToDB(session, filLibrary, file, layout.getId(), displayLabel.rtvBareId(), "",
									GAZRecordDataModel.TYP_LABEL_ATTRIBUTES, "", qualifiedClassName);
				}
			}

			// Display group
			else if (displayItem instanceof DisplayGroup)
			{
				// display group
				DisplayGroup displayGroup = (DisplayGroup) displayItem;

				// determine the class name - group Id + subgroup Id. This Id must exclude the
				// + prefix for Groups.
				String className = "";
				String group = "";
				String subgroup = "";
				if (parentGroup.length() == 0)
				{
					className = displayGroup.rtvBareId();
					group = displayGroup.rtvBareId();
					subgroup = "";
				}
				else
				{
					className = parentGroup + "_" + displayGroup.rtvBareId();
					group = parentGroup;
					subgroup = displayGroup.rtvBareId();
				}
				className = fixNameForJava(className);

				// get the file
				String qualifiedClassName = getLayoutClassName(layout.getPackageName(), mainClass, className,
								GAZRecordDataModel.TYP_GROUP_ATTRIBUTES);
				File file = new File(rootPath + qualifiedClassName.replace(".", File.separator) + ".class");
				if (file.exists())
				{
					writeClassToDB(session, filLibrary, file, layout.getId(), group, subgroup,
									GAZRecordDataModel.TYP_GROUP_ATTRIBUTES, "", qualifiedClassName);
				}

				writeDisplayItem(session, filLibrary, layout, rootPath, mainClass, displayGroup.rtvBareId(), displayGroup
								.getDisplayItems());
				writeDisplayItem(session, filLibrary, layout, rootPath, mainClass, displayGroup.rtvBareId(), displayGroup
								.getDisplayItems());
			}
			// Display button link
			else if (displayItem instanceof DisplayButtonLink)
			{
				// check if the field class adaptor exists
				// if it does, then write it to the file
				DisplayButtonLink displayButtonLink = (DisplayButtonLink) displayItem;
				String qualifiedClassName = getLayoutClassName(layout.getPackageName(), mainClass, displayButtonLink.rtvBareId(),
								GAZRecordDataModel.TYP_BUTTONLINK_ATTRIBUTES);
				File file = new File(rootPath + qualifiedClassName.replace(".", File.separator) + ".class");
				if (file.exists())
				{
					writeClassToDB(session, filLibrary, file, layout.getId(), displayButtonLink.rtvBareId(), "",
									GAZRecordDataModel.TYP_BUTTONLINK_ATTRIBUTES, "", qualifiedClassName);
				}
			}
		}
	}

	/**
	 * Uses the packageName parts to step back up the folders to find the root folder (i.e. the bin folder)
	 * 
	 * @param folder
	 * @param packageName
	 * @return IFile (null if a mismatch occurs)
	 */
	private static File getBaseFolder(final File folder, final String packageName)
	{
		File result = new File(folder, "");

		String[] parts = packageName.split("\\.");
		for (int index = parts.length; index > 0; index--)
		{
			if (result.getName().equals(parts[index - 1]))
			{
				result = result.getParentFile();
			}
			else
			{
				// This check against an empty string is required to handle the case when the package name is an empty string
				if (!parts[index - 1].equals(""))
				{
					result = null;
				}
				break;
			}
		}
		return result;
	}
	/**
	 * Write the content of a file into the database
	 * 
	 * @param session
	 *            - the EquationStandardSession
	 * @param filLibrary
	 *            - the Unit KFIL library
	 * @param file
	 *            - the file
	 * @param functionId
	 *            - the function Id
	 * @param fieldId
	 *            - the field Id
	 * @param pvId
	 *            - The PV module name (or blank)
	 * @param type
	 *            A <code>String</code> containing the GAZTYP field type
	 * @param packageName
	 *            A <code>String</code> containing the Java package name.
	 * @param className
	 *            A <code>String</code> containing the outer or inner class name.
	 * 
	 * @return true - if the record has been added (or false if updated)
	 * 
	 * @throws EQException
	 */
	public static boolean writeClassToDB(EquationStandardSession session, String filLibrary, File file, String functionId,
					String fieldId, String pvId, String type, String packageName, String className) throws EQException
	{
		boolean result = false;
		FileInputStream inputStream = null;
		try
		{
			inputStream = new FileInputStream(file);
			GAZRecordDataModel gazRecord = new GAZRecordDataModel(functionId, fieldId, pvId, type);
			gazRecord.setLibrary(filLibrary);
			IGAZRecordDao dao = daoFactory.getGAZDao(session, gazRecord);

			// read
			int length = inputStream.available();
			byte[] classByte = new byte[length];
			inputStream.read(classByte);

			// write it
			String qualifiedClassName;
			if (packageName == null || packageName.length() == 0)
			{
				qualifiedClassName = className;
			}
			else
			{
				qualifiedClassName = packageName + "." + className;
			}

			gazRecord.setClassName(qualifiedClassName);
			gazRecord.setClassByte(classByte);

			dao.insertOrUpdateRecord();

			result = dao.checkIfThisGAZRecordIsInTheDB();
		}
		catch (IOException e)
		{
			LOG.error(e);
		}
		finally
		{
			FileUtils.close(inputStream);
		}
		return result;
	}
	/**
	 * Write the content of a file into the database
	 * 
	 * @param session
	 *            - the EquationStandardSession
	 * @param file
	 *            - the file
	 * @param functionId
	 *            - the function Id
	 * @param fieldId
	 *            - the field Id
	 * 
	 * @return true - if the record has been added (or false if updated)
	 * 
	 * @throws Exception
	 */
	private static boolean writeClassToDB(EquationStandardSession session, String filLibrary, File file, String functionId,
					AdaptorKeyFields keyFields, String packageName, String className) throws Exception
	{

		return writeClassToDB(session, filLibrary, file, functionId, keyFields.getGazfld(), keyFields.getGazpvn(), keyFields
						.getGaztyp(), packageName, className);
	}

	/**
	 * Return the pv name and pv field
	 * 
	 * @param pvName
	 *            - the pv name
	 * @param pvField
	 *            - the pv field
	 * 
	 * @return the concatenated pv name and pv field
	 */
	public static String getPVNameField(String pvName, String pvField)
	{
		return pvName + "_" + pvField;
	}

	/**
	 * Convert invalid characters to X so that it becomes a valid Java identifier
	 * 
	 * @param name
	 *            - the field name
	 * 
	 * @return the converted name
	 */
	public static String fixNameForJava(String name)
	{
		return name.replaceAll("@", "X").replaceAll("\\+", "X");
	}
	/**
	 * Determines the GAZPF key fields relevant for a FieldSet.
	 * 
	 * @param fieldSet
	 *            either an <code>APIFieldSet</code> or a <code>PVFieldSet</code>
	 * @return a <code>AdaptorKeyFields</code> containing the key fields
	 */
	public static AdaptorKeyFields getGAZKeyFields(FieldSet fieldSet)
	{
		AdaptorKeyFields result = new AdaptorKeyFields();
		if (fieldSet instanceof APIFieldSet)
		{
			result.setGaztyp(GAZRecordDataModel.TYP_APIFIELDSET);
			// Load API fieldsets have a parent InputFieldSet:
			if (fieldSet.rtvParentFieldSet() instanceof InputFieldSet)
			{
				result.setGazfld(fieldSet.rtvParentFieldSet().getId());
				result.setGazpvn(fieldSet.getId());
			}
			else
			{
				// Otherwise, it's an Update API Fieldset - only set gazfld:
				result.setGazfld(fieldSet.getId());
			}
		}
		else if (fieldSet instanceof PVFieldSet)
		{
			result.setGaztyp(GAZRecordDataModel.TYP_PVFIELDSET);
			// Could be a Load PV fieldset, or a Validate PV fieldset (owned by an input field)
			if (fieldSet.rtvParent() instanceof InputField)
			{
				// Validate
				result.setGazfld(fieldSet.rtvParentField().getId());
				result.setGazpvn(fieldSet.getId());
			}
			else
			{
				// Load PV
				result.setGazfld(fieldSet.getId());
			}
		}
		else
		{
			throw new EQRuntimeException("Unhandled FieldSet subclass type [" + fieldSet.getClass().getSimpleName() + "]");
		}
		return result;
	}

	/**
	 * Determines the GAZPF key fields relevant for a InputFieldSet.
	 * 
	 * @param inputFieldSet
	 *            the input field set
	 * @return a <code>AdaptorKeyFields</code> containing the key fields
	 */
	public static AdaptorKeyFields getGAZKeyFields(InputFieldSet inputFieldSet)
	{
		AdaptorKeyFields result = new AdaptorKeyFields();
		if (inputFieldSet instanceof InputFieldSet)
		{
			result.setGaztyp(GAZRecordDataModel.TYP_INPUTFIELDSET);
			result.setGazfld(inputFieldSet.getId());
		}
		else
		{
			throw new EQRuntimeException("Unhandled FieldSet subclass type [" + inputFieldSet.getClass().getSimpleName() + "]");
		}
		return result;
	}

	/**
	 * Determines the GAZPF key fields relevant for a Field Value adaptor.
	 * <p>
	 * This method determines the set of key fields for this inner class. This is called at deploy (export) time to ensure that only
	 * valid inner classes are exported.
	 * <p>
	 * 
	 * @param field
	 *            This will be an APIField, InputField, PVField or WorkField, depending on the context
	 * @param assignmentType
	 *            an <code>int</code>, one of LOAD_ASSIGNMENT, UPDATE_ASSIGNMENT or VALIDATE_ASSIGNMENT
	 * @param subfieldType
	 *            for Validate assignments to InputFields, specify either MappingToolbox.PRIMITIVE or MappingToolbox.OUTPUT
	 *            constants. Otherwise supply an empty String.
	 * 
	 * @return a <code>AdaptorKeyFields</code> containing the GAZPF key fields for this exit point
	 */
	public static AdaptorKeyFields getGAZKeyFields(Field field, int assignmentType, String subfieldType)
	{
		AdaptorKeyFields result = new AdaptorKeyFields();
		// All six types share the same type:

		switch (assignmentType)
		{
			case LOAD_ASSIGNMENT:
				// Could be to either a Load API, Load PV or to an WorkField (including InputField)
				if (field instanceof WorkField)
				{
					// Just set up FLD:
					result.setGazfld(field.getId());
				}
				else if (field instanceof PVField)
				{
					// A Load PV field; leave field name blank, just set PV (PV module and Field)
					result.setGazpvn(getPVNameField(field.rtvParentFieldSet().getId(), field.getId()));
				}
				else if (field instanceof APIField)
				{
					// Leave field name blank, just set PV
					result.setGazfld(field.getId());
					result.setGazpvn("Load API"); // A hard coded value
				}
				else
				{
					throw new EQRuntimeException("Invalid field type for Load assignments");
				}
				result.setGaztyp(GAZRecordDataModel.TYP_LOAD);
				break;
			case UPDATE_ASSIGNMENT:
				if (field instanceof APIField)
				{
					result.setGazfld(field.getId());
				}
				else
				{
					throw new EQRuntimeException("Update assignment must be to an APIField");
				}
				result.setGaztyp(GAZRecordDataModel.TYP_UPDATE);
				break;
			case VALIDATE_ASSIGNMENT:
				if (MappingToolbox.PRIMITIVE.equals(subfieldType))
				{
					InputField inputField = (InputField) field;
					result.setGazfld(inputField.getId() + "_" + MappingToolbox.PRIMITIVE);
				}
				else if (MappingToolbox.OUTPUT.equals(subfieldType))
				{
					InputField inputField = (InputField) field;
					result.setGazfld(inputField.getId() + "_" + MappingToolbox.OUTPUT);
				}
				else if ("".equals(subfieldType))
				{
					// Must be a PV field or Workfield
					if (field instanceof PVField)
					{
						PVField pvField = (PVField) field;
						PVFieldSet pvFieldSet = (PVFieldSet) pvField.rtvParentFieldSet();
						// The GAZFLD vlaue will be the parent InputField name:
						result.setGazfld(pvFieldSet.rtvParentFieldSet().getId());
						result.setGazpvn(pvFieldSet.getId() + "_" + pvField.getId());
					}
					else
					{
						// Assume workfield:
						WorkField workField = (WorkField) field;
						result.setGazfld(workField.getId());
					}
				}
				else
				{
					throw new EQRuntimeException("Unhandled FieldSet subclass type [" + field.getClass().getSimpleName() + "]");
				}
				result.setGaztyp(GAZRecordDataModel.TYP_VALUE);
				break;

		}
		return result;
	}

	/**
	 * Gets the inner class name for a FieldSet.
	 * 
	 * @param fieldSet
	 *            either an <code>APIFieldSet</code> or a <code>PVFieldSet</code>
	 * @return a <code>String</code> containing the innerclass name
	 */
	public static String getInnerClassName(FieldSet fieldSet)
	{
		String result;
		if (fieldSet instanceof APIFieldSet)
		{
			// Load API fieldsets have a parent InputFieldSet:
			if (fieldSet.rtvParentFieldSet() instanceof InputFieldSet)
			{
				// Should be <InputFieldSetId>__<APIFieldSetId>__LoadFieldSet
				result = fieldSet.rtvParentFieldSet().getId() + "__" + fieldSet.getId() + "__LoadFieldSet";
			}
			else
			{
				// Otherwise, it's an Update API Fieldset
				// Should be <APIFieldSetId>__UpdateFieldSet
				result = fieldSet.getId() + "__UpdateFieldSet";
			}
		}
		else if (fieldSet instanceof PVFieldSet)
		{
			// Could be a Load PV fieldset, or a Validate PV fieldset (owned by an input field)
			if (fieldSet.rtvParent() instanceof InputField)
			{
				// Validate (owned by an InputField):
				// Format is <InputFieldId>__<PVFieldsetId>__PVFieldSet
				result = fieldSet.rtvParentField().getId() + "__" + fieldSet.getId() + "__PVFieldSet";
			}
			else
			{
				// Load PV
				// Format is <PVFieldSetID>__PVFieldSet
				result = fieldSet.getId() + "__PVFieldSet";
			}
		}
		else
		{
			throw new EQRuntimeException("Unhandled FieldSet subclass type [" + fieldSet.getClass().getSimpleName() + "]");
		}
		return result;
	}

	/**
	 * Gets the inner class name for a Field.
	 * <p>
	 * This handles the basic field adaptor class name generation. Note that this does not cater for Value Adaptors
	 * 
	 * @param field
	 *            a <code>InputField</code>
	 * @return a <code>String</code> containing the inner class name
	 */
	public static String getInnerClassName(Field field)
	{
		String result;
		if (field instanceof InputField)
		{
			// Format is <FieldID>_FieldAdaptor
			result = field.getId() + FIELD_SFX; //$NON-NLS-1$
		}
		else
		{
			throw new EQRuntimeException("Unhandled Field subclass type [" + field.getClass().getSimpleName() + "]");
		}
		return result;
	}

	/**
	 * Gets the inner class name for a DisplayAttributes.
	 * 
	 * @param displayAttributes
	 *            a <code>DisplayAttributes</code>
	 * @return a <code>String</code> containing the inner-class name
	 */
	public static String getInnerClassName(DisplayAttributes displayAttributes)
	{
		// Format is <DisplayAttributesID>_AttributesAdaptor
		return displayAttributes.getId() + "_AttributesAdaptor"; //$NON-NLS-1$
	}

	/**
	 * Gets the inner class name for a DisplayAttributes.
	 * 
	 * @param displayAttributes
	 *            a <code>DisplayAttributes</code>
	 * @return a <code>String</code> containing the inner-class name
	 */
	public static String getInnerClassName(DisplayGroup displayGroup)
	{
		return displayGroup.getId().substring(DisplayGroup.GROUP_ID_PREFIX.length()) + "_DisplayGroupAdaptor";
	}

	/**
	 * Gets the inner class name for a DisplayLabel.
	 * 
	 * @param displayLabel
	 *            a <code>DisplayLabel</code>
	 * @return a <code>String</code> containing the inner-class name
	 */
	public static String getInnerClassName(DisplayLabel displayLabel)
	{
		return displayLabel.getId().substring(DisplayLabel.LABEL_ID_PREFIX.length()) + "_DisplayLabelAdaptor"; //$NON-NLS-1$
	}

	/**
	 * Gets the inner class name for DisplayAttributesSet.
	 * 
	 * @param displayAttributesSet
	 *            a <code>DisplayAttributesSet</code>
	 * @return a <code>String</code> containing the inner-class name
	 */

	public static String getInnerClassName(DisplayAttributesSet displayAttributesSet)
	{
		return displayAttributesSet.getId() + "_AttributesSetAdaptor"; //$NON-NLS-1$
	}

	/**
	 * Gets the inner class name for InputFieldSet.
	 * 
	 * @param inputFieldSet
	 *            an <code>InputFieldSet</code>
	 * @return a <code>String</code> containing the inner-class name
	 */

	public static String getInnerClassName(InputFieldSet inputFieldSet)
	{
		return inputFieldSet.getId() + "_InputFieldSetAdaptor"; //$NON-NLS-1$
	}

	/**
	 * Gets the inner class name for a DisplayButtonLink.
	 * 
	 * @param displayButtonLink
	 *            a <code>displayButtonLink</code>
	 * @return a <code>String</code> containing the inner-class name
	 */
	public static String getInnerClassName(DisplayButtonLink displayButtonLink)
	{
		return displayButtonLink.getId().substring(DisplayButtonLink.BUTTON_LINK_ID_PREFIX.length()) + DISPLAYBUTTONLINK_SFX; //$NON-NLS-1$
	}

	/**
	 * Gets the inner class name for a Field's Value Adaptor
	 * 
	 * @param field
	 *            a <code>Field</code>
	 * @param assignmentType
	 *            an <code>int</code> indicating the type of assignment (Load, Update or Validate)
	 * @param subfieldType
	 *            A String containing the subfield type the assignment is mapping to. An empty string should be used if no subtype.
	 * @return a <code>String</code> containing the inner-class name
	 */
	public static String getInnerClassName(Field field, int assignmentType, String subfieldType)
	{
		String result = null;
		switch (assignmentType)
		{
			case LOAD_ASSIGNMENT:
				if (field instanceof PVField)
				{
					// Load PVs don't have a parent field, so just two parts:
					PVField pvField = (PVField) field;
					PVFieldSet pvFieldSet = (PVFieldSet) pvField.rtvParentFieldSet();
					result = pvFieldSet.getId() + "_" + fixNameForJava(pvField.getId()) + "_LoadPVValueAdaptor"; //$NON-NLS-1$
				}
				else if (field instanceof APIField)
				{
					APIField apiField = (APIField) field;
					result = apiField.rtvParentFieldSet().getId() + "_" + fixNameForJava(field.getId()) + LOAD_SFX; //$NON-NLS-1$
				}
				else if (field instanceof WorkField)
				{
					// WorkFields and InputFields can use a single part (field name)
					WorkField workField = (WorkField) field;
					result = workField.getId() + LOAD_SFX; //$NON-NLS-1$
				}
				break;
			case UPDATE_ASSIGNMENT:
				// Update assignments assign the value of an API field:
				if (field instanceof APIField)
				{
					APIField apiField = (APIField) field;
					result = apiField.rtvParentFieldSet().getId() + "_" + field.getId() + UPDATE_SFX; //$NON-NLS-1$
				}
				break;
			case VALIDATE_ASSIGNMENT:
				// Validate assignments can be to a PV field, or back to an InputField/WorkField
				if (field instanceof PVField)
				{
					PVField pvField = (PVField) field;
					PVFieldSet pvFieldSet = (PVFieldSet) pvField.rtvParentFieldSet();
					Field parentField = pvFieldSet.rtvParentField();
					result = parentField.getId() + "_" + pvFieldSet.getId() + "_" + fixNameForJava(pvField.getId()) + VALUE_SFX; //$NON-NLS-1$
				}
				else if (field instanceof WorkField)
				{
					if (field instanceof InputField)
					{
						// Can be either the Primitive or Output form of the field
						InputField inputField = (InputField) field;
						result = inputField.getId() + "_" + subfieldType + VALUE_SFX; //$NON-NLS-1$
					}
					else
					{
						// Work fields don't use subtype
						WorkField workField = (WorkField) field;
						result = workField.getId() + VALUE_SFX; //$NON-NLS-1$
					}
				}
				break;
			default:
				throw new RuntimeException("Unhandled assignmentType [" + assignmentType + "]");
		}
		return result;
	}

	public static UserExitDetails getUserExitMethodDetails(FieldSet fieldSet, String methodName)
	{
		UserExitDetails result = new UserExitDetails();
		if (SHOULDEXECUTEMODULE_METHOD_NAME.equals(methodName))
		{
			result.setSuperClassName(FIELDSET_ADAPTOR_SUPERCLASS_NAME);
			result.setInnerClassName(getInnerClassName(fieldSet));
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "UserData" });
			result.setMethodParameterNames(new String[] { "userdata" });
			result.setMethodReturnType("boolean");
		}
		else
		{
			throw new EQRuntimeException("Unknown method name [" + methodName + "]");
		}
		return result;
	}

	public static UserExitDetails getUserExitMethodDetails(WorkField field, String methodName)
	{
		UserExitDetails result = new UserExitDetails();
		if (IS_MANDATORY_METHOD_NAME.equals(methodName) || IS_VALID_METHOD_NAME.equals(methodName))
		{
			result.setSuperClassName(FIELD_ADAPTOR_SUPERCLASS_NAME);
			result.setInnerClassName(getInnerClassName(field));
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "UserData" });
			result.setMethodParameterNames(new String[] { "data" });
			result.setMethodReturnType("boolean");
		}
		else if (INITIAL_VALUE_METHOD_NAME.equals(methodName) || DEFAULT_VALUE_METHOD_NAME.equals(methodName))
		{
			result.setSuperClassName(FIELD_ADAPTOR_SUPERCLASS_NAME);
			result.setInnerClassName(getInnerClassName(field));
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "UserData" });
			result.setMethodParameterNames(new String[] { "userData" });
			result.setMethodReturnType("String");
		}
		else
		{
			throw new EQRuntimeException("Unknown method name [" + methodName + "]");
		}
		return result;
	}

	/**
	 * @param displayAttributes
	 *            <code>DisplayAttributes</code>
	 * @param methodName
	 *            Name of the method for which details are required. Must be one of {@link #IS_PROTECTED_METHOD_NAME}, either
	 *            <code>IS_PROTECTED_METHOD_NAME</code>, <code>IS_VISIBLE_METHOD_NAME</code> or
	 *            <code>IS_IN_GRID_VISIBLE_METHOD_NAME</code>
	 * @return <code>UserExitDetails</code> containing all the information required to create/check for a Java User Exit
	 *         inner-class/method
	 */
	public static UserExitDetails getUserExitMethodDetails(DisplayAttributes displayAttributes, String methodName)
	{
		UserExitDetails result = new UserExitDetails();
		if (IS_PROTECTED_METHOD_NAME.equals(methodName) || IS_VISIBLE_METHOD_NAME.equals(methodName)
						|| IS_IN_GRID_VISIBLE_METHOD_NAME.equals(methodName))
		{
			result.setSuperClassName(ATTRIBUTES_ADAPTOR_SUPERCLASS_NAME);
			result.setInnerClassName(getInnerClassName(displayAttributes));
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "UserData" });
			result.setMethodParameterNames(new String[] { "data" });
			result.setMethodReturnType("boolean");
		}
		else if (GET_EDITING_PARAMETER_METHOD_NAME.equals(methodName))
		{
			result.setSuperClassName(ATTRIBUTES_ADAPTOR_SUPERCLASS_NAME);
			result.setInnerClassName(getInnerClassName(displayAttributes));
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "UserData" });
			result.setMethodParameterNames(new String[] { "data" });
			result.setMethodReturnType("String");
		}
		else
		{
			throw new EQRuntimeException("Unknown method name [" + methodName + "] for DisplayAttributes");
		}
		return result;
	}

	/**
	 * @param displayGroup
	 *            <code>DisplayGroup</code>
	 * @param methodName
	 *            Name of the method for which details are required. Must be one of {@link #IS_PROTECTED_METHOD_NAME}, either
	 *            <code>IS_PROTECTED_METHOD_NAME</code> or <code>IS_VISIBLE_METHOD_NAME</code>or
	 *            <code>IS_DISPLAY_GROUP_VISIBLE_METHOD_NAME</code>
	 * @return <code>UserExitDetails</code> containing all the information required to create/check for a Java User Exit
	 *         inner-class/method
	 */
	public static UserExitDetails getUserExitMethodDetails(DisplayGroup displayGroup, String methodName)
	{
		UserExitDetails result = new UserExitDetails();
		if (GET_TOP_COLUMN_DETAILS_METHOD_NAME.equals(methodName) || GET_BOTTOM_COLUMN_DETAILS_METHOD_NAME.equals(methodName)
						|| GET_ABOVE_ROW_COLUMN_DETAILS_METHOD_NAME.equals(methodName)
						|| GET_BELOW_ROW_COLUMN_DETAILS_METHOD_NAME.equals(methodName))
		{
			result.setSuperClassName(DISPLAYGROUP_ADAPTOR_SUPERCLASS_NAME);
			result.setInnerClassName(getInnerClassName(displayGroup));
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "UserData", "String", "int" });
			result.setMethodParameterNames(new String[] { "userData", "fieldName", "counter" });
			result.setMethodReturnType("UserExitListColumnData");
		}
		else if (IS_DISPLAY_GROUP_VISIBLE_METHOD_NAME.equals(methodName))
		{
			result.setSuperClassName(DISPLAYGROUP_ADAPTOR_SUPERCLASS_NAME);
			result.setInnerClassName(getInnerClassName(displayGroup));
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "UserData" });
			result.setMethodParameterNames(new String[] { "userData" });
			result.setMethodReturnType("boolean");
		}
		else
		{
			throw new EQRuntimeException("Unknown method name [" + methodName + "] for DisplayGroup");
		}
		return result;
	}

	/**
	 * Gets details for a field assignment user exit (Value adaptor)
	 * 
	 * @param field
	 *            <code>Field</code>
	 * @param assignmentType
	 *            a <code>int</code> indicating the type of assignment (Load, Update or Validate). The constants
	 *            <code>LOAD_ASSIGNMENT</code>, <code>UPDATE_ASSIGNMENT</code> or <code>VALIDATE_ASSIGNMENT</code> should be used
	 * @param subfieldType
	 *            a String indicating the subfield type for the mapping. Must be either <code>SUBFIELD_PRIMITIVE</code>,
	 *            <code>SUBFIELD_OUTPUT</code> or an empty string.
	 * @param methodName
	 *            Name of the method for which details are required. Must be <code>GET_VALUE_METHOD_NAME</code>
	 * @return <code>UserExitDetails</code> containing all the information required to create/check for a Java User Exit
	 *         inner-class/method
	 */
	public static UserExitDetails getUserExitMethodDetails(Field field, int assignmentType, String subfieldType, String methodName)
	{
		UserExitDetails result = new UserExitDetails();
		if (GET_VALUE_METHOD_NAME.equals(methodName))
		{
			result.setSuperClassName(VALUE_ADAPTOR_SUPERCLASS_NAME);
			result.setInnerClassName(getInnerClassName(field, assignmentType, subfieldType));
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "String", "UserData" });
			result.setMethodParameterNames(new String[] { "curValue", "userdata" });
			result.setMethodReturnType("String");
		}
		else
		{
			throw new EQRuntimeException("Unknown method name [" + methodName + "] for DisplayAttributes");
		}
		return result;
	}

	/**
	 * Gets Java exit method details for Function level exit points
	 * <p>
	 * Note that although this returns a <code>UserExitDetails</code>, the properties relevant to determining the inner class
	 * details will be left blank
	 * 
	 * @param service
	 *            the <code>Function</code>
	 * @param methodName
	 * @return
	 */
	public static UserExitDetails getUserExitMethodDetails(Function service, String methodName)
	{
		UserExitDetails result = new UserExitDetails();
		if (methodName.equals(POST_LOAD_METHOD_NAME))
		{
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "UserModifyData" });
			result.setMethodParameterNames(new String[] { "userModifyData" });
			result.setMethodReturnType("String");
		}
		else if (methodName.equals(POST_LOAD_EFC_METHOD_NAME))
		{
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "UserData", "UserModifyData" });
			result.setMethodParameterNames(new String[] { "userData", "userDataEFC" });
			result.setMethodReturnType("void");
		}
		else if (methodName.equals(POST_UPDATE_METHOD_NAME) || methodName.equals(PRE_UPDATE_METHOD_NAME))
		{
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "JournalHeader", "UserData" });
			result.setMethodParameterNames(new String[] { "journalHeader", "userData" });
			result.setMethodReturnType("void");
		}
		else if (methodName.equals(GET_LRP_TEMPLATE_PROCESS_DETAIL))
		{
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "UserData" });
			result.setMethodParameterNames(new String[] { "userData" });
			result.setMethodReturnType("UserExitESFProcessDetail");
		}
		else
		{
			throw new EQRuntimeException("Unknown method name [" + methodName + "]");
		}
		return result;
	}

	/**
	 * @param displayLabel
	 *            <code>DisplayLabel</code>
	 * @param methodName
	 *            Name of the method for which details are required. Must be <code>IS_VISIBLE_METHOD_NAME</code>
	 * @return <code>UserExitDetails</code> containing all the information required to create/check for a Java User Exit
	 *         inner-class/method
	 */
	public static UserExitDetails getUserExitMethodDetails(DisplayLabel displayLabel, String methodName)
	{
		UserExitDetails result = new UserExitDetails();
		if (IS_DISPLAY_LABEL_VISIBLE_METHOD_NAME.equals(methodName))
		{
			result.setSuperClassName(DISPLAYLABEL_ADAPTOR_SUPERCLASS_NAME);
			result.setInnerClassName(getInnerClassName(displayLabel));
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "UserData" });
			result.setMethodParameterNames(new String[] { "userData" });
			result.setMethodReturnType("boolean");
		}
		else
		{
			throw new EQRuntimeException("Unknown method name [" + methodName + "] for DisplayLabel");
		}
		return result;
	}

	/**
	 * @param displayAttributesSet
	 *            <code>DisplayAttributesSet</code>
	 * @param methodName
	 *            Name of the method for which details are required. Must be <code>IS_FINISH_BUTTON_ENABLED_METHOD_NAME</code>
	 * @return <code>UserExitDetails</code> containing all the information required to create/check for a Java User Exit
	 *         inner-class/method
	 */
	public static UserExitDetails getUserExitMethodDetails(DisplayAttributesSet displayAttributesSet, String methodName)
	{
		UserExitDetails result = new UserExitDetails();
		if (IS_FINISH_BUTTON_ENABLED_METHOD_NAME.equals(methodName))
		{
			result.setSuperClassName(DISPLAYATTRIBUTESSET_ADAPTOR_SUPERCLASS_NAME);
			result.setInnerClassName(getInnerClassName(displayAttributesSet));
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "UserData" });
			result.setMethodParameterNames(new String[] { "userData" });
			result.setMethodReturnType("boolean");
		}
		else
		{
			throw new EQRuntimeException("Unknown method name [" + methodName + "] for DisplayAttributesSet");
		}
		return result;
	}

	/**
	 * @param inputFieldSet
	 *            <code>InputFieldSet</code>
	 * @param methodName
	 *            Name of the method for which details are required. Must be <code>DEFAULT_MODE_METHOD_NAME</code>,
	 *            <code>INITIALISE_MODE_METHOD_NAME</code>, or <code>VALIDATE_MODE_METHOD_NAME</code>.
	 * @return <code>UserExitDetails</code> containing all the information required to create/check for a Java User Exit
	 *         inner-class/method
	 */

	public static UserExitDetails getUserExitMethodDetails(InputFieldSet inputFieldSet, String methodName)
	{
		UserExitDetails result = new UserExitDetails();
		if (DEFAULT_MODE_METHOD_NAME.equals(methodName) || VALIDATE_MODE_METHOD_NAME.equals(methodName))
		{
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "int", "UserModifyData" });
			result.setMethodParameterNames(new String[] { "curScreen", "userModifyData" });
			result.setMethodReturnType("void");
		}

		else if (DEFAULT_MODE_METHOD_NAME_FIELD_SET.equals(methodName))
		{
			result.setSuperClassName(INPUTFIELDSET_ADAPTOR_SUPERCLASS_NAME);
			result.setInnerClassName(getInnerClassName(inputFieldSet));
			result.setMethodName(DEFAULT_MODE_METHOD_NAME);
			result.setMethodParameterTypes(new String[] { "UserModifyData" });
			result.setMethodParameterNames(new String[] { "userModifyData" });
			result.setMethodReturnType("void");
		}

		else if (VALIDATE_MODE_METHOD_NAME_FIELD_SET.equals(methodName))
		{
			result.setSuperClassName(INPUTFIELDSET_ADAPTOR_SUPERCLASS_NAME);
			result.setInnerClassName(getInnerClassName(inputFieldSet));
			result.setMethodName(VALIDATE_MODE_METHOD_NAME);
			result.setMethodParameterTypes(new String[] { "UserModifyData" });
			result.setMethodParameterNames(new String[] { "userModifyData" });
			result.setMethodReturnType("void");
		}

		else if (INITIALISE_MODE_METHOD_NAME.equals(methodName))
		{
			result.setSuperClassName(INPUTFIELDSET_ADAPTOR_SUPERCLASS_NAME);
			result.setInnerClassName(getInnerClassName(inputFieldSet));
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "UserModifyData" });
			result.setMethodParameterNames(new String[] { "userModifyData" });
			result.setMethodReturnType("void");
		}
		else
		{
			throw new EQRuntimeException("Unknown method name [" + methodName + "] for InputFieldSet");
		}
		return result;
	}

	/**
	 * @param displayButtonLink
	 *            <code>DisplayButtonLink</code>
	 * @param methodName
	 *            Name of the method for which details are required. Must be <code>IS_VISIBLE_METHOD_NAME</code>
	 * @return <code>UserExitDetails</code> containing all the information required to create/check for a Java User Exit
	 *         inner-class/method
	 */
	public static UserExitDetails getUserExitMethodDetails(DisplayButtonLink displayButtonLink, String methodName)
	{
		UserExitDetails result = new UserExitDetails();
		if (IS_DISPLAY_BUTTON_VISIBLE_METHOD_NAME.equals(methodName))
		{
			result.setSuperClassName(DISPLAYBUTTONLINK_ADAPTOR_SUPERCLASS_NAME);
			result.setInnerClassName(getInnerClassName(displayButtonLink));
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "UserData" });
			result.setMethodParameterNames(new String[] { "userData" });
			result.setMethodReturnType("boolean");
		}
		else if (IS_DISPLAY_BUTTON_PROTECTED_METHOD_NAME.equals(methodName))
		{
			result.setSuperClassName(DISPLAYBUTTONLINK_ADAPTOR_SUPERCLASS_NAME);
			result.setInnerClassName(getInnerClassName(displayButtonLink));
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "UserData" });
			result.setMethodParameterNames(new String[] { "userData" });
			result.setMethodReturnType("boolean");
		}
		else if (IS_DISPLAY_BUTTOM_CMD_PARAM_METHOD_NAME.equals(methodName))
		{
			result.setSuperClassName(DISPLAYBUTTONLINK_ADAPTOR_SUPERCLASS_NAME);
			result.setInnerClassName(getInnerClassName(displayButtonLink));
			result.setMethodName(methodName);
			result.setMethodParameterTypes(new String[] { "UserData" });
			result.setMethodParameterNames(new String[] { "userData" });
			result.setMethodReturnType("String");
		}
		else
		{
			throw new EQRuntimeException("Unknown method name [" + methodName + "] for DisplayLabel");
		}
		return result;
	}

}
