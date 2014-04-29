package com.misys.equation.function.beans;

import java.io.StringReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.el.ELException;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import org.apache.el.ExpressionFactoryImpl;
import org.apache.el.parser.ELParser;
import org.apache.el.parser.Token;

import com.misys.equation.common.dao.beans.GAERecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQRuntimeException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.valid.MessageContainer;
import com.misys.equation.function.el.ELContextImpl;
import com.misys.equation.function.el.ELRuntime;
import com.misys.equation.function.journal.JournalFile;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.useraccess.UserAccessFields;
import com.misys.equation.problems.AbsMessageContainer;
import com.misys.equation.problems.Message;
import com.misys.equation.problems.ProblemLocation;

/**
 * A class containing static methods for use when validating the bean/xml information
 * 
 */
public class ValidationHelper
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ValidationHelper.java 11965 2011-09-30 09:54:43Z rpatrici $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(ValidationHelper.class);

	private static Set<String> sqlReservedKeywords = new HashSet<String>();

	/** EL Factory */
	private static ExpressionFactory elFactory = new ExpressionFactoryImpl();

	/** EL Context */
	private static ELContextImpl elContext = new ELContextImpl();

	public enum BooleanELType
	{
		VALIDATION_EXPRESSION, MANDATORY_EXPRESSION, VISIBLE_EXPRESSION, PROTECTED_EXPRESSION, EXECUTE_EXPRESSION, IGNORELINK_EXPRESSION, COLUMN_VISIBLE_EXPRESSION, DISPLAY_GROUP_VISIBLE_EXPRESSION, DISPLAY_LABEL_VISIBLE_EXPRESSION, DISPLAY_ATTRIBUTESSET_FINISHENABLED_EXPRESSION, POST_UPDATE_MICROFLOW_EXPRESSION,
	}

	private static final String[][] booleanELMessages = new String[][] {
					{ "Language.ELValidationMustBeEntered", "Language.ELValidationParseError",
									"Language.ELValidationFieldDoesNotExist", "Language.ELValidationNotABoolean" },
					{ "Language.ELMandatoryMustBeEntered", "Language.ELMandatoryParseError",
									"Language.ELMandatoryFieldDoesNotExist", "Language.ELMandatoryNotABoolean" },
					{ "Language.ELVisibleMustBeEntered", "Language.ELVisibleParseError", "Language.ELVisibleFieldDoesNotExist",
									"Language.ELVisibleNotABoolean" },
					{ "Language.ELProtectedMustBeEntered", "Language.ELProtectedParseError",
									"Language.ELProtectedFieldDoesNotExist", "Language.ELProtectedNotABoolean" },
					{ "Language.ELExecutionMustBeEntered", "Language.ELExecutionParseError",
									"Language.ELExecutionFieldDoesNotExist", "Language.ELExecutionNotABoolean" },
					{ "Language.ELIgnoreLinkMustBeEntered", "Language.ELIgnoreLinkParseError",
									"Language.ELIgnoreLinkFieldDoesNotExist", "Language.ELIgnoreLinkNotABoolean" },
					{ "Language.ELColumnVisibleMustBeEntered", "Language.ELColumnVisibleParseError",
									"Language.ELColumnVisibleFieldDoesNotExist", "Language.ELColumnVisibleNotABoolean" },
					{ "Language.ELDisplayGroupVisibleMustBeEntered", "Language.ELDisplayGroupVisibleParseError",
									"Language.ELDisplayGroupVisibleFieldDoesNotExist", "Language.ELDisplayGroupVisibleNotABoolean" },
					{ "Language.ELDisplayLabelVisibleMustBeEntered", "Language.ELDisplayLabelVisibleParseError",
									"Language.ELDisplayLabelVisibleFieldDoesNotExist", "Language.ELDisplayLabelVisibleNotABoolean" },
					{ "Language.ELAttributesSetFinishButtonEnabledMustBeEntered",
									"Language.ELAttributesSetFinishButtonEnabledParseError",
									"Language.ELAttributesSetFinishButtonEnabledDoesNotExist",
									"Language.ELAttributesSetFinishButtonEnabledNotABoolean" },
					{ "Language.ELPostUpdateMicroflowMustBeEntered", "Language.ELPostUpdateMicroflowParseError",
									"Language.ELPostUpdateMicroflowDoesNotExist", "Language.ELPostUpdateMicroflowNotABoolean" } };

	public enum StringELType
	{
		DISPLAYSTYLE_FIELDLABEL, DISPLAYSTYLE_FIELDVALUE, DISPLAYSTYLE_FIELDDESC, DISPLAYSTYLE_FIELDROW, DISPLAYSTYLE_LABEL, DISPLAYSTYLE_SUBPARM, DISPLAYSTYLE_GROUPODD, DISPLAYSTYLE_GROUPEVEN, DISPLAYSTYLE_TABLEHEADER, DISPLAYSTYLE_TABLEDATA, DISPLAYSTYLE_TABLEFOOTER, DISPLAYSTYLE_GRANDTOTAL, DISPLAYSTYLE_TOTALLABEL, DISPLAYSTYLE_SUBTOTALLABEL, DISPLAYSTYLE_EDITCODE, AMOUNTREPLACEMENT, COMMAND_AND_PARMS_EXPRESS, LABEL_SCRIPT_EXPRESSION
	}

	private static final String[][] stringELMessages = new String[][] {
					{ "Language.ELFieldLabelMustBeEntered", "Language.ELFieldLabelParseError",
									"Language.ELFieldLabelFieldDoesNotExist", "Language.ELFieldLabelNotAString" },
					{ "Language.ELFieldValueMustBeEntered", "Language.ELFieldValueParseError",
									"Language.ELFieldValueFieldDoesNotExist", "Language.ELFieldValueNotAString" },
					{ "Language.ELFieldDescMustBeEntered", "Language.ELFieldDescParseError",
									"Language.ELFieldDescFieldDoesNotExist", "Language.ELFieldDescNotAString" },
					{ "Language.ELFieldRowMustBeEntered", "Language.ELFieldRowParseError", "Language.ELFieldRowFieldDoesNotExist",
									"Language.ELFieldRowNotAString" },
					{ "Language.ELLabelMustBeEntered", "Language.ELLabelParseError", "Language.ELLabelFieldDoesNotExist",
									"Language.ELLabelNotAString" },
					{ "Language.ELSubParmMustBeEntered", "Language.ELSubParmParseError", "Language.ELSubParmFieldDoesNotExist",
									"Language.ELSubParmNotAString" },
					{ "Language.ELGroupOddMustBeEntered", "Language.ELGroupOddParseError", "Language.ELGroupOddFieldDoesNotExist",
									"Language.ELGroupOddNotAString" },
					{ "Language.ELGroupEvenMustBeEntered", "Language.ELGroupEvenParseError",
									"Language.ELGroupEvenFieldDoesNotExist", "Language.ELGroupEvenNotAString" },

					{ "Language.ELTableHeaderMustBeEntered", "Language.ELTableHeaderParseError",
									"Language.ELTableHeaderFieldDoesNotExist", "Language.ELTableHeaderNotAString" },

					{ "Language.ELTableDataMustBeEntered", "Language.ELTableDataParseError",
									"Language.ELTableDataFieldDoesNotExist", "Language.ELTableDataNotAString" },

					{ "Language.ELTableFooterMustBeEntered", "Language.ELTableFooterParseError",
									"Language.ELTableFooterFieldDoesNotExist", "Language.ELTableFooterNotAString" },

					{ "Language.ELGrandTotalMustBeEntered", "Language.ELGrandTotalParseError",
									"Language.ELGrandTotalFieldDoesNotExist", "Language.ELGrandTotalNotAString" },

					{ "Language.ELTotalLabelMustBeEntered", "Language.ELTotalLabelParseError",
									"Language.ELTotalLabelFieldDoesNotExist", "Language.ELTotalLabelNotAString" },

					{ "Language.ELSubtotalLabelMustBeEntered", "Language.ELSubtotalLabelParseError",
									"Language.ELSubtotalLabelFieldDoesNotExist", "Language.ELSubtotalLabelNotAString" },

					{ "Language.ELEditCodeMustBeEntered", "Language.ELEditCodeParseError", "Language.ELEditCodeFieldDoesNotExist",
									"Language.ELEditCodeNotAString" },

					{ "Language.ELAmountReplacementMustBeEntered", "Language.ELAmountReplacementParseError",
									"Language.ELAmountReplacementFieldDoesNotExist", "Language.ELAmountReplacementNotAString" },

					{ "Language.ELFieldCommandExpressionMustBeEntered", "Language.ELFieldCommandExpressionParseError",
									"Language.ELFieldCommandExpressionFieldDoesNotExist",
									"Language.ELFieldCommandExpressionNotAString" },

					{ "Language.ELFieldLabelScriptExpressionParseError", "Language.ELFieldLabelScriptExpressionParseError",
									"Language.ELFieldLabelScriptExpressionFieldDoesNotExist",
									"Language.ELFieldLabelScriptExpressionNotAString" } };

	static
	{
		/**
		 * Note that this list contains keywords that are longer than 10 characters, which would be invalid for ID fields anyway.
		 * These have been retained for completeness
		 */
		final String[] arraySqlReservedKeywords = { "ACTION", "ACTIVATE", "ADD", "ALIAS", "ALL", "ALLOCATE", "ALLOW", "ALTER",
						"AND", "ANY", "APPEND", "AS", "ASC", "ASENSITIVE", "AT", "ATTRIBUTES", "AUTHORIZATION", "BEGIN", "BETWEEN",
						"BINARY", "BIND", "BIT", "BUFFERPOOL", "BY", "CACHE", "CALL", "CALLED", "CARDINALITY", "CASE", "CAST",
						"CCSID", "CHAR", "CHARACTER", "CHECK", "CLIENT", "CLOSE", "CLUSTER", "COLLECT", "COLLECTION", "COLUMN",
						"COMMENT", "COMMIT", "COMPACT", "COMPRESS", "CONCAT", "CONDITION", "CONNECT", "CONNECTION", "CONSTRAINT",
						" CONTAINS", "CONTINUE", "COPY", "COUNT", "COUNT_BIG", "CREATE", "CROSS", "CUBE", "CURRENT",
						"CURRENT_DATE", "CURRENT_PATH", "CURRENT_SCHEMA", "CURRENT_SERVER", "CURRENT_TIME", "CURRENT_TIMESTAMP",
						"CURRENT_TIMEZONE", "CURRENT_USER", "CURSOR", "CYCLE", "DATA", "DATABASE", "DATAPARTITIONNAME",
						"DATAPARTITIONNUM", "DATE", "DAY", "DAYS", "DBINFO", "DBPARTITIONNAME", "DBPARTITIONNUM", "DB2GENERAL",
						"DB2GENRL", "DB2SQL", "DEACTIVATE", "DEALLOCATE", "DECLARE", "DEFAULT", "DEFAULTS", "DEFER", "DEFINE",
						"DEFINITION", "DELETE", "DENSERANK", "DENSE_RANK", "DESC", "DESCRIBE", "DESCRIPTOR", "DETERMINISTIC",
						"DIAGNOSTICS", "DISABLE", " DISALLOW", "DISCONNECT", "DISTINCT", "DO", "DOUBLE", "DROP", "DYNAMIC", "EACH",
						"ELSE", "ELSEIF", "ENABLE", "ENCRYPTION", "END", "ENDING", "END-EXEC (COBOL only)", "ENFORCED", "ESCAPE",
						"EVERY", "EXCEPT", "EXCEPTION", "EXCLUDING", "EXCLUSIVE", "EXECUTE", "EXISTS", "EXIT", "EXTEND",
						"EXTERNAL", "EXTRACT", "FENCED", "FETCH", "FILE", "FINAL", "FOR", "FOREIGN", "FREE", "FREEPAGE", "FROM",
						"FULL", "FUNCTION", "GBPCACHE", "GENERAL", "GENERATED", "GET", "GLOBAL", "GO", "GOTO", "GRANT", "GRAPHIC",
						"GROUP", " HANDLER", "HASH", "HASHED_VALUE", "HAVING", "HINT", "HOLD", "HOUR", "HOURS", "IDENTITY", "IF",
						"IMMEDIATE", "IMPLICITLY", "IN", "INCLUDE", "INCLUDING", "INCLUSIVE", "INCREMENT", "INDEX", "INDEXBP",
						"INDICATOR", "INF", "INFINITY", "INHERIT", "INNER", "INOUT", "INSENSITIVE", "INSERT", "INTEGRITY",
						"INTERSECT", "INTO", "IS", "ISOLATION", "ITERATE", "JAVA", "JOIN", "KEY", "LABEL", "LANGUAGE", "LATERAL",
						"LEAVE", "LEFT", "LEVEL2", "LIKE", "LINKTYPE", "LOCAL", "LOCALDATE", "LOCALTIME", "LOCALTIMESTAMP", "LOCK",
						"LOCKSIZE", "LOG", "LOGGED", "LONG", "LOOP", "MAINTAINED", "MATERIALIZED", "MAXVALUE", "MICROSECOND",
						"MICROSECONDS", "MINPCTUSED", "MINUTE", "MINUTES", "MINVALUE", "MIXED", "MODE", "MODIFIES", "MONTH",
						"MONTHS", "NAN", "NATIONAL", "NCHAR", "NCLOB", "NEW", "NEW_TABLE", "NEXTVAL", "NO", "NOCACHE", "NOCYCLE",
						"NODENAME", "NODENUMBER", "NOMAXVALUE", "NOMINVALUE", "NONE", "NOORDER", "NORMALIZED", "NOT", "NULL",
						"NULLS", "NVARCHAR", "OBID", "OF ", "OLD", "OLD_TABLE", "ON", " OPEN", "OPTIMIZE", "OPTION", "OR", "ORDER",
						"ORGANIZE", "OUT", "OUTER", "OVER", "OVERRIDING", "PACKAGE", "PADDED", "PAGE", "PAGESIZE", "PARAMETER",
						"PART", "PARTITION", "PARTITIONED", "PARTITIONING", "PARTITIONS", "PASSWORD", "PATH", "PCTFREE",
						"PIECESIZE", "PLAN", "POSITION", "PREPARE", "PREVVAL", "PRIMARY", "PRIQTY", "PRIVILEGES", "PROCEDURE",
						"PROGRAM", "QUERY", "RANGE", "RANK", "RCDFMT", "READ", "READS", "RECOVERY", "REFERENCES", "REFERENCING",
						"REFRESH", "RELEASE", "RENAME", " REPEAT", "RESET", "RESIGNAL", "RESTART", "RESULT", "RETURN", "RETURNS",
						"REVOKE", "RID", "RIGHT", "ROLLBACK", "ROLLUP", "ROUTINE", "ROW", "ROWNUMBER", "ROW_NUMBER", "ROWS", "RRN",
						"RUN", "SAVEPOINT", "SBCS", "SCHEMA", "SCRATCHPAD", "SCROLL", "SEARCH", "SECOND", "SECONDS", "SECQTY",
						"SELECT", "SENSITIVE", "SEQUENCE", "SESSION", "SESSION_USER", "SET", "SIGNAL", "SIMPLE", "SKIP", "SNAN",
						"SOME", "SOURCE", "SPECIFIC", "SQL", "SQLID", "STACKED", "START", " STARTING", "STATEMENT", "STATIC",
						"STOGROUP", "SUBSTRING", "SUMMARY", "SYNONYM", "SYSTEM_USER", "TABLE", "TABLESPACE", "TABLESPACES", "THEN",
						"TIME", "TIMESTAMP", "TO", "TRANSACTION", "TRIGGER", "TRIM", "TYPE", "UNDO", "UNION", "UNIQUE", "UNTIL",
						"UPDATE", "USAGE", "USER", "USING", "VALUE", "VALUES", "VARIABLE", "VARIANT", "VCAT", "VERSION", "VIEW",
						"VOLATILE", "WHEN", "WHERE", "WHILE", "WITH", "WITHOUT", "WRITE", "YEAR", "YEARS", "YES" };

		for (String keyword : arraySqlReservedKeywords)
		{
			sqlReservedKeywords.add(keyword);
		}
	}
	/**
	 * Private constructor to prevent instance construction
	 */
	private ValidationHelper()
	{
	}

	/**
	 * Common validation for Id fields.
	 * 
	 * Note that this method performs syntax validation on the ID, but cannot check for duplicate IDs.
	 * 
	 * @param id
	 * @return String - null if successfully validated, otherwise containing the error message text
	 */
	public static String validateIdSyntax(String id)
	{
		String result = null;

		// First character must be A-Z
		char[] chars = id.toCharArray();
		if (chars.length == 0)
		{
			result = "Id must be entered";
		}
		else if (chars.length > 10)
		{
			result = "ID cannot be longer than 10 characters";
		}
		else
		{
			char ch = chars[0];
			if ((ch < 'A' || ch > 'Z'))
			{
				result = "First character of ID must be A-Z";
			}
			else
			{
				for (int index = 1; index < id.length(); index++)
				{
					ch = chars[index];
					if ((ch < 'A' || ch > 'Z') && (ch < '0' || ch > '9'))
					{
						result = "ID can only contain A-Z or 0-9";
					}
				}
			}
			// If still OK so far, check for DB2 SQL Reserved keywords
			if (result == null)
			{
				if (sqlReservedKeywords.contains(id))
				{
					result = "ID cannot be a SQL Keyword";
				}
			}
		}
		return result;
	}

	/**
	 * Validates the Id of an InputField
	 * <p>
	 * There are various considerations for an InputField Id. As the field Id is used for the GZX journal file column name, it must
	 * be a valid DB2 column name. These must start with A-Z and the rest of the characters can also be either 0-9 or underscore.
	 * Note that although it is technically permissible to include characters such as £, $, @ or # in a DB2 column name, this is
	 * discouraged as these have different code points in different character sets.
	 * <p>
	 * In addition, because these IDs are also used when referencing fields in a an EL script, they must also be legal EL
	 * identifiers. However, no further restrictions are required beyond the DB2 restrictions.
	 * <p>
	 * Note that this method performs syntax validation on the Id, but does not check for duplicate Ids.
	 * 
	 * @param id
	 *            The InputField Id to validate
	 * @param messageContainer
	 *            a MessageContainer to add messages to
	 * @param problemLocation
	 *            A <code>ProblemLocation</code> instance
	 * @return String - message
	 */
	public static String validateInputFieldIdSyntax(String id, MessageContainer messageContainer, ProblemLocation problemLocation)
	{
		String message = null;
		char[] chars = id.toCharArray();
		if (chars.length == 0)
		{
			message = "Language.FieldIdBlank";
			messageContainer.addErrorMessageId(message, problemLocation);
		}
		else if (chars.length > 10)
		{
			message = "Language.FieldIdTooLong";
			messageContainer.addErrorMessageId(message, id, problemLocation);
		}
		else
		{
			if (chars.length > 0)
			{
				char ch = chars[0];
				// First character can only be A-Z
				if (ch < 'A' || ch > 'Z')
				{
					message = "Language.InvalidFirstCharacterOfFieldId";
					messageContainer.addErrorMessageId(message, id, problemLocation);
					return message;
				}
			}

			// For the rest of characters in the id, digits and the underscore character are also valid:
			for (int index = 1; index < id.length(); index++)
			{
				char ch = chars[index];
				if ((ch < 'A' || ch > 'Z') && (ch < '0' || ch > '9') && ch != '_')
				{
					message = "Language.FieldIdInvalidCharacter";
					messageContainer.addErrorMessageId(message, id, problemLocation);
					return message;

				}
			}
			// Note that there are no problems with creating fields with DB2 SQL reserved keywords
		}
		return message;
	}

	/**
	 * Validates the Id of a WorkField
	 * <p>
	 * By contrast with InputFields, WorkField Ids are not used as DB2 column names as WorkFields are not journalled. However, they
	 * must be valid EL script identifiers. To avoid clashes with EL script identifiers, ids must start with either A-Z or #. For
	 * the rest of the characters A-Z, 0-9, # or _ will be allowed.
	 * <p>
	 * Note that this method performs syntax validation on the Id, but does not check for duplicate Ids.
	 * 
	 * @param id
	 *            The WorkField Id to validate
	 * @param messageContainer
	 *            a MessageContainer to add messages to
	 * @param problemLocation
	 *            A ProblemLocation instance
	 * @return String - message
	 */
	public static String validateWorkFieldIdSyntax(String id, MessageContainer messageContainer, ProblemLocation problemLocation)
	{
		String message = null;
		char[] chars = id.toCharArray();
		if (chars.length == 0)
		{
			message = "Language.FieldIdBlank";
			messageContainer.addErrorMessageId(message, problemLocation);
		}
		else if (chars.length > 10)
		{
			message = "Language.FieldIdTooLong";
			messageContainer.addErrorMessageId(message, id, problemLocation);
		}
		else
		{
			if (chars.length > 0)
			{
				char ch = chars[0];
				// First character can only be A-Z or #
				if ((ch < 'A' || ch > 'Z') && ch != '#')
				{
					message = "Language.InvalidFirstCharacterOfWorkFieldId";
					messageContainer.addErrorMessageId(message, id, problemLocation);
					return message;
				}
			}

			for (int index = 0; index < id.length(); index++)
			{
				char ch = chars[index];
				if ((ch < 'A' || ch > 'Z') && (ch < '0' || ch > '9') && ch != '#' && ch != '_')
				{
					message = "Language.FieldIdInvalidCharacter";
					messageContainer.addErrorMessageId(message, id, problemLocation);
					return message;
				}
			}
		}
		return message;
	}

	/**
	 * Validate for Latin alphanumerics
	 * <p>
	 * Allows A-Z, a-z, 0-9 and blanks
	 * 
	 * @param text
	 *            The String to validate
	 * @return boolean (true if valid, false if invalid characters found)
	 */
	public static boolean validateAlphanumerics(String text)
	{
		String valid = "!?/'<> -_";
		for (char ch : text.toCharArray())
		{
			if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') || valid.indexOf(ch) != -1)
			{
				// Valid character (continue to next)
			}
			else
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Validates an option id (for a new service definition)
	 * 
	 * Note that this method performs syntax validation on the ID, but cannot check for duplicate IDs.
	 * 
	 * @param option
	 *            The option to validate
	 * @return String - null if successfully validated, otherwise containing the error message text
	 */
	public static String validateOptionSyntax(String option)
	{
		String result = null;
		char[] chars = option.toCharArray();

		if (chars.length > 3)
		{
			result = LanguageResources.getString("Language.OptionCannotBeLongerThan3Characters");
		}
		else
		{
			for (char ch : chars)
			{
				if ((ch < 'A' || ch > 'Z') && (ch < '0' || ch > '9'))
				{
					result = LanguageResources.getString("Language.InvalidCharactersInOption");
				}
			}
		}
		return result;
	}

	/**
	 * Validates that the supplied text is a valid, non-negative integer:
	 * 
	 * @param text
	 *            A string to validate (must be already trimmed)
	 * @return boolean - true if valid
	 */
	public static boolean validateNumericInteger(String text)
	{
		boolean result = true;
		if (text.length() == 0)
		{
			result = true;
		}
		else
		{
			try
			{
				BigInteger temp = new BigInteger(text);
				if (BigInteger.ZERO.compareTo(temp) > 0)
				{
					// Don't allow negatives
					result = false;
				}
			}
			catch (NumberFormatException e)
			{
				result = false;
			}
		}
		return result;
	}

	/**
	 * Returns a supplied text is a valid, non-negative integer:
	 * 
	 * This also caters for blank strings (which will result in a value of 0 being returned)
	 * 
	 * @param text
	 *            A string to parse (must be already trimmed)
	 * @return boolean - true if valid
	 */
	public static int getSafeIntegerValue(String text)
	{
		int result = -1;
		if (text.length() == 0)
		{
			result = 0;
		}
		else
		{
			try
			{
				result = Integer.parseInt(text);
				if (result < 0)
				{
					result = -1;
				}
			}
			catch (NumberFormatException e)
			{
				result = -1;
			}
		}
		return result;
	}

	/**
	 * Validate a EL expression that should return a boolean
	 * 
	 * @param expression
	 * @param element
	 *            (used to obtain Function to validate fields)
	 * @param messageContainer
	 * @param type
	 * @param problemLocation
	 */
	public static void validateBooleanELExpression(String expression, Element element, MessageContainer messageContainer,
					BooleanELType type, ProblemLocation problemLocation)
	{
		String[] params = new String[] { element.rtvBareId() };
		validateBooleanELExpression(expression, element, messageContainer, type, problemLocation, params);
	}

	/**
	 * Validate a EL expression that should return a boolean
	 * 
	 * @param expression
	 * @param element
	 *            (only used to obtain Function to validate fields)
	 * @param messageContainer
	 * @param type
	 * @param problemLocation
	 * @param params
	 *            String array containing parameters for the error message
	 */
	public static void validateBooleanELExpression(String expression, Element element, AbsMessageContainer messageContainer,
					BooleanELType type, ProblemLocation problemLocation, String[] params)
	{
		String[] messageIds = booleanELMessages[type.ordinal()];

		// Validate for empty:
		if (expression == null || expression.trim().length() == 0)
		{
			messageContainer.addErrorMessageId(messageIds[0], params, problemLocation);
			return;
		}

		String fullExpression = "${" + expression + "}";
		ELParser parser = new ELParser(new StringReader(fullExpression));
		Token token = parser.getNextToken();
		Token previousToken = null;
		Function service = getService(element);

		while (!token.image.equals(""))
		{
			// Bind identifier tokens to service fields, but avoid binding
			// identifiers that follow a namespace (i.e. a function name)
			if (token.kind == ELParser.IDENTIFIER && (previousToken == null || previousToken.kind != ELParser.NAMESPACE))
			{
				// Bind to a string containing a numeric value to avoid
				// numeric parse errors.
				String bindingString = getBindingString(service, validateSuffix(token.image.toUpperCase()));
				if (bindingString == null)
				{
					String message = LanguageResources.getFormattedString(messageIds[2], addStringToArray(params, token.image));
					messageContainer.addMessage(message, Message.SEVERITY_ERROR, problemLocation);
					return;
				}
				elContext.bind(token.image, bindingString);
			}
			previousToken = token;
			token = parser.getNextToken();
		}
		try
		{
			// The createValueExpression method throws a ParseException...
			ValueExpression valueExpression = elFactory.createValueExpression(elContext, fullExpression, Object.class);
			Object objectValue = valueExpression.getValue(elContext);
			if (!(objectValue instanceof Boolean))
			{
				messageContainer.addErrorMessageId(messageIds[3], params, problemLocation);
			}
		}
		catch (ELException e)
		{
			// report a generic parse error
			messageContainer.addErrorMessageId(messageIds[1], params, problemLocation);
		}
		catch (NumberFormatException e)
		{
			String numberFormatMessage = LanguageResources.getFormattedString(messageIds[1], params) + " "
							+ LanguageResources.getString("Language.DueToANumericExpression");
			messageContainer.addMessage(numberFormatMessage, Message.SEVERITY_ERROR, problemLocation);
		}
		catch (Throwable e)
		{
			if (e.getMessage().equals("java.lang.NullPointerException"))
			{
				messageContainer.addMessage(LanguageResources.getString("Language.InvalidScriptExpression"),
								Message.SEVERITY_ERROR, problemLocation);
			}
			else
			{
				messageContainer.addMessage(e.getMessage(), Message.SEVERITY_ERROR, problemLocation);
			}
		}
	}

	/**
	 * Adds a string to the array
	 * 
	 * @param array
	 * @param string
	 * @return A String []
	 */
	private static String[] addStringToArray(String[] array, String string)
	{
		List<String> result = new ArrayList<String>(array.length + 1);
		Collections.addAll(result, array);
		Collections.addAll(result, string);
		return result.toArray(new String[] {});
	}

	/**
	 * Validate a EL expression that should return a string
	 * 
	 * @param expression
	 * @param element
	 *            (used to obtain Function to validate fields)
	 * @param messageContainer
	 * @param type
	 * @param problemLocation
	 * @param service
	 *            - the Function where the field belongs (if null then auto-detect the service)
	 */
	public static void validateStringELExpression(String expression, Element element, MessageContainer messageContainer,
					StringELType type, ProblemLocation problemLocation, Function service)
	{
		String[] messageIds = stringELMessages[type.ordinal()];

		// Validate for empty:
		if (expression == null || expression.trim().length() == 0)
		{
			messageContainer.addErrorMessageId(messageIds[0], element.rtvBareId(), problemLocation);
			return;
		}

		String fullExpression = "${" + expression + "}";
		ELParser parser = new ELParser(new StringReader(fullExpression));
		Token token = parser.getNextToken();
		Token previousToken = null;

		// service not specified, then determine the service
		if (service == null)
		{
			service = getService(element);
		}

		while (!token.image.equals(""))
		{
			// Bind identifier tokens to service fields, but avoid binding
			// identifiers that follow a namespace (i.e. a function name)
			if (token.kind == ELParser.IDENTIFIER && (previousToken == null || previousToken.kind != ELParser.NAMESPACE))
			{
				// Bind to a string containing a numeric value to avoid
				// numeric parse errors.
				String bindingString = getBindingString(service, validateSuffix(token.image.toUpperCase()));
				if (bindingString == null)
				{
					String message = LanguageResources.getFormattedString(messageIds[2], new String[] { element.rtvBareId(),
									token.image });
					messageContainer.addMessage(message, Message.SEVERITY_ERROR, problemLocation);
					return;
				}
				elContext.bind(token.image, bindingString);
			}
			previousToken = token;
			token = parser.getNextToken();
		}
		try
		{
			// The createValueExpression method throws a ParseException...
			ValueExpression valueExpression = elFactory.createValueExpression(elContext, fullExpression, Object.class);
			Object objectValue = valueExpression.getValue(elContext);
			if (!(objectValue instanceof String))
			{
				messageContainer.addErrorMessageId(messageIds[3], element.rtvBareId(), problemLocation);
			}
		}
		catch (ELException e)
		{
			// report a generic parse error
			messageContainer.addErrorMessageId(messageIds[1], element.rtvBareId(), problemLocation);
		}
		catch (NumberFormatException e)
		{
			String numberFormatMessage = LanguageResources.getFormattedString(messageIds[1], element.getId()) + " "
							+ LanguageResources.getString("Language.DueToANumericExpression");
			messageContainer.addMessage(numberFormatMessage, Message.SEVERITY_ERROR, problemLocation);
		}
		catch (Throwable e)
		{
			if (e.getMessage().equals("java.lang.NullPointerException"))
			{
				messageContainer.addMessage(LanguageResources.getString("Language.InvalidScriptExpression"),
								Message.SEVERITY_ERROR, problemLocation);
			}
			else
			{
				messageContainer.addMessage(e.getMessage(), Message.SEVERITY_ERROR, problemLocation);
			}
		}

	}

	/**
	 * Validates a EL expression used in mappings, which should return a String
	 * 
	 * @param expression
	 *            The EL script to execute
	 * @param mappingType
	 *            "Load", "Update" or "Validate"
	 * @param field
	 *            The field being mapped to. Used to obtain the function definition
	 * @param subFieldType
	 *            subfield type string
	 * @param messageContainer
	 *            a MessageContainer to add messages to
	 * @param problemLocation
	 *            A ProblemLocation instance
	 */
	public static void validateELMappingExpression(String expression, String mappingType, Field field, String subFieldType,
					MessageContainer messageContainer, ProblemLocation problemLocation)
	{
		String subFieldText = subFieldType.length() > 0 ? " (" + subFieldType + ")" : "";

		// Validate for null/empty:
		if (expression == null || expression.trim().length() == 0)
		{
			String message = LanguageResources.getFormattedString("Language.ELMappingScriptFieldMustBeEntered", new String[] {
							mappingType, field.getId(), subFieldText });
			messageContainer.addMessage(message, Message.SEVERITY_ERROR, problemLocation);
			return;
		}

		String fullExpression = "${" + expression + "}";
		ELParser parser = new ELParser(new StringReader(fullExpression));
		Token token = parser.getNextToken();
		Token previousToken = null;
		Function service = getService(field);
		while (!token.image.equals(""))
		{
			// Bind identifier tokens to service fields, but avoid binding
			// identifiers that follow a namespace (i.e. a function name)
			if (token.kind == ELParser.IDENTIFIER && (previousToken == null || previousToken.kind != ELParser.NAMESPACE))
			{
				// Bind to a string containing a numeric value to avoid
				// numeric parse errors.
				String bindingString = getBindingString(service, validateSuffix(token.image.toUpperCase()));
				if (bindingString == null)
				{
					String message = LanguageResources.getFormattedString("Language.ELMappingScriptFieldDoesNotExist",
									new String[] { mappingType, field.getId(), subFieldText, token.image });
					messageContainer.addMessage(message, Message.SEVERITY_ERROR, problemLocation);
					return;
				}
				elContext.bind(token.image, bindingString);
			}
			previousToken = token;
			token = parser.getNextToken();
		}
		try
		{
			// The createValueExpression method throws a ParseException...
			ValueExpression valueExpression = elFactory.createValueExpression(elContext, fullExpression, Object.class);
			Object objectValue = valueExpression.getValue(elContext);
			// A returnable value is required (String, Long or Double):
			if (!(objectValue instanceof String || objectValue instanceof Long || objectValue instanceof Double))
			{
				messageContainer.addErrorMessageId("Language.ELMappingScriptNotAStringOrNumeric", new String[] { mappingType,
								field.getId(), subFieldText }, problemLocation);
			}
		}
		catch (ELException e)
		{
			// report a generic parse error
			messageContainer.addErrorMessageId("Language.ELMappingScriptParseError", new String[] { mappingType, field.getId(),
							subFieldText }, problemLocation);
			// TODO: Add processing to handle exceptions according to the nested exception.
			// For example, a ParseException contains too much information to include, but
			// an IndexOutOfBoundsException (e.g. thrown by a user defined string function)
			// could be output to provide more information
		}
		catch (NumberFormatException e)
		{
			String numberFormatMessage = LanguageResources.getFormattedString("Language.ELMappingScriptParseError", new String[] {
							mappingType, field.getId(), subFieldText })
							+ " " + LanguageResources.getString("Language.DueToANumericExpression");
			messageContainer.addMessage(numberFormatMessage, Message.SEVERITY_ERROR, problemLocation);
		}
		catch (Throwable e)
		{
			if (e.getMessage().equals("java.lang.NullPointerException"))
			{
				messageContainer.addMessage(LanguageResources.getString("Language.InvalidScriptExpression"),
								Message.SEVERITY_ERROR, problemLocation);
			}
			else
			{
				messageContainer.addMessage(e.getMessage(), Message.SEVERITY_ERROR, problemLocation);
			}
		}

	}

	/**
	 * Validates a EL expression used in Context mappings, which should return a String
	 * 
	 * @param expression
	 *            The EL script to execute
	 * @param service
	 *            The service definition
	 * @param contextItem
	 *            The name of the context item (for building messages)
	 * @param messageContainer
	 *            a MessageContainer to add messages to
	 * @param problemLocation
	 *            A ProblemLocation instance
	 */
	public static void validateContextMappingELExpression(String expression, Function service, String contextItem,
					MessageContainer messageContainer, ProblemLocation problemLocation)
	{
		// This method is required in order to return different error messages from the
		// other, similar methods
		// Validate for null/empty:
		if (expression == null || expression.trim().length() == 0)
		{
			String message = LanguageResources.getFormattedString("Language.ELContextMappingScriptMustBeEntered", contextItem);
			messageContainer.addMessage(message, Message.SEVERITY_ERROR, problemLocation);
			return;
		}

		String fullExpression = "${" + expression + "}";
		ELParser parser = new ELParser(new StringReader(fullExpression));
		Token token = parser.getNextToken();
		Token previousToken = null;

		while (!token.image.equals(""))
		{
			// Bind identifier tokens to service fields, but avoid binding
			// identifiers that follow a namespace (i.e. a function name)
			if (token.kind == ELParser.IDENTIFIER && (previousToken == null || previousToken.kind != ELParser.NAMESPACE))
			{
				// Bind to a string containing a numeric value to avoid
				// numeric parse errors.
				String bindingString = getBindingString(service, validateSuffix(token.image.toUpperCase()));
				if (bindingString == null)
				{
					String message = LanguageResources.getFormattedString("Language.ELContextMappingScriptFieldDoesNotExist",
									new String[] { contextItem, token.image });
					messageContainer.addMessage(message, Message.SEVERITY_ERROR, problemLocation);
					return;
				}
				elContext.bind(token.image, bindingString);
			}
			previousToken = token;
			token = parser.getNextToken();
		}
		try
		{
			// The createValueExpression method throws a ParseException...
			ValueExpression valueExpression = elFactory.createValueExpression(elContext, fullExpression, Object.class);
			Object objectValue = valueExpression.getValue(elContext);
			// A returnable value is required (String, Long or Double):
			if (!(objectValue instanceof String))
			{
				messageContainer.addErrorMessageId("Language.ELContextMappingScriptNotAString", contextItem, problemLocation);
			}
		}
		catch (ELException e)
		{
			// report a generic parse error
			messageContainer.addErrorMessageId("Language.ELContextMappingScriptParseError", contextItem, problemLocation);
			// TODO: Add processing to handle exceptions according to the nested exception.
			// For example, a ParseException contains too much information to include, but
			// an IndexOutOfBoundsException (e.g. thrown by a user defined string function)
			// could be output to provide more information
		}
		catch (NumberFormatException e)
		{
			String numberFormatMessage = LanguageResources.getFormattedString("Language.ELContextMappingScriptParseError",
							contextItem)
							+ " " + LanguageResources.getString("Language.DueToANumericExpression");
			messageContainer.addMessage(numberFormatMessage, Message.SEVERITY_ERROR, problemLocation);
		}
		catch (Throwable e)
		{
			if (e.getMessage().equals("java.lang.NullPointerException"))
			{
				messageContainer.addMessage(LanguageResources.getString("Language.InvalidScriptExpression"),
								Message.SEVERITY_ERROR, problemLocation);
			}
			else
			{
				messageContainer.addMessage(e.getMessage(), Message.SEVERITY_ERROR, problemLocation);
			}
		}

	}

	/**
	 * Validates a EL expression used in Context mappings, which should return a String
	 * <p>
	 * Note that this does not check for an empty expression; as this expression is optional, this method should only be called if
	 * the expression is not blank
	 * 
	 * @param expression
	 * @param workField
	 *            The workField definition
	 * @param messageContainer
	 * @param problemLocation
	 */
	public static void validateFieldsetInitialisationELExpression(String expression, WorkField workField,
					MessageContainer messageContainer, ProblemLocation problemLocation)
	{
		Function service = getService(workField);

		String fullExpression = "${" + expression + "}";
		ELParser parser = new ELParser(new StringReader(fullExpression));
		Token token = parser.getNextToken();
		Token previousToken = null;

		while (!token.image.equals(""))
		{
			// Bind identifier tokens to service fields, but avoid binding
			// identifiers that follow a namespace (i.e. a function name)
			if (token.kind == ELParser.IDENTIFIER && (previousToken == null || previousToken.kind != ELParser.NAMESPACE))
			{
				// Bind to a string containing a numeric value to avoid
				// numeric parse errors.
				String bindingString = getBindingString(service, validateSuffix(token.image.toUpperCase()));
				if (bindingString == null)
				{
					String message = LanguageResources.getFormattedString(
									"Language.ELFieldSetInitialisationScriptFieldDoesNotExist", new String[] { workField.getId(),
													token.image });
					messageContainer.addMessage(message, Message.SEVERITY_ERROR, problemLocation);
					return;
				}
				elContext.bind(token.image, bindingString);
			}
			previousToken = token;
			token = parser.getNextToken();
		}
		try
		{
			// The createValueExpression method throws a ParseException...
			ValueExpression valueExpression = elFactory.createValueExpression(elContext, fullExpression, Object.class);
			Object objectValue = valueExpression.getValue(elContext);
			// A returnable value is required (String):
			if (!(objectValue == null || objectValue instanceof String || objectValue instanceof Long || objectValue instanceof Double))
			{
				messageContainer.addErrorMessageId("Language.ELFieldSetInitialisationScriptNotAStringOrNumeric", workField.getId(),
								problemLocation);
			}
		}
		catch (ELException e)
		{
			// report a generic parse error
			messageContainer.addErrorMessageId("Language.ELFieldSetInitialisationScriptParseError", workField.getId(),
							problemLocation);
			// TODO: Add processing to handle exceptions according to the nested exception.
			// For example, a ParseException contains too much information to include, but
			// an IndexOutOfBoundsException (e.g. thrown by a user defined string function)
			// could be output to provide more information
		}
		catch (NumberFormatException e)
		{
			String numberFormatMessage = LanguageResources.getFormattedString("Language.ELFieldSetInitialisationScriptParseError",
							workField.getId())
							+ " " + LanguageResources.getString("Language.DueToANumericExpression");
			messageContainer.addMessage(numberFormatMessage, Message.SEVERITY_ERROR, problemLocation);
		}
		catch (Throwable e)
		{
			if (e.getMessage().equals("java.lang.NullPointerException"))
			{
				messageContainer.addMessage(LanguageResources.getString("Language.InvalidScriptExpression"),
								Message.SEVERITY_ERROR, problemLocation);
			}
			else
			{
				messageContainer.addMessage(e.getMessage(), Message.SEVERITY_ERROR, problemLocation);
			}
		}

	}

	/**
	 * Determines the function to which this element belongs
	 * 
	 * @param element
	 * 
	 * @return The <code>Function</code> to which this element belongs
	 */
	private static Function getService(Element element)
	{
		// Short-cut processing if the element is in fact a Function
		if (element instanceof Function)
		{
			return (Function) element;
		}

		Function function = null;
		FieldSet fieldSet = null;

		// First, find the associated service:
		if (element instanceof Field)
		{
			if (element instanceof PVField)
			{
				fieldSet = ((PVField) element).rtvParentFieldSet();
			}
			else
			{
				Element parent = element.rtvParent();
				if (parent instanceof InputFieldSet || parent instanceof APIFieldSet)
				{
					fieldSet = (FieldSet) parent;
				}
				else if (parent instanceof Function)
				{
					function = (Function) parent;
				}
			}
		}
		else if (element instanceof FieldSet)
		{
			fieldSet = (FieldSet) element;
		}

		if (fieldSet != null)
		{
			if (fieldSet.getFunction() != null)
			{
				function = fieldSet.getFunction();
			}
			else if (fieldSet.rtvParent() instanceof Function)
			{
				function = (Function) fieldSet.rtvParent();
			}
			else if (fieldSet.rtvParent() instanceof InputField)
			{
				InputField inputField = (InputField) fieldSet.rtvParent();
				if (inputField.rtvParentFieldSet() instanceof InputFieldSet)
				{
					InputFieldSet inputFieldSet = (InputFieldSet) inputField.rtvParentFieldSet();
					function = inputFieldSet.getFunction();
				}
			}
			else if (fieldSet.rtvParent() instanceof InputFieldSet)
			{
				// This is a Load APIFieldset
				InputFieldSet inputFieldSet = (InputFieldSet) fieldSet.rtvParentFieldSet();
				function = inputFieldSet.getFunction();
			}
		}

		// If a Display Group get the function
		if (element instanceof DisplayGroup)
		{
			DisplayAttributesSet set = ((DisplayGroup) element).rtvParentSet();
			if (set != null)
			{
				Layout layout = set.rtvLayout();
				function = layout.service();
			}
		}

		// If a Display Label get the function
		if (element instanceof DisplayLabel)
		{
			DisplayAttributesSet set = ((DisplayLabel) element).rtvParentSet();
			if (set != null)
			{
				Layout layout = set.rtvLayout();
				function = layout.service();
			}
		}

		// If a DisplayAttributesSet, get the function
		if (element instanceof DisplayAttributesSet)
		{
			DisplayAttributesSet set = (DisplayAttributesSet) element;
			Layout layout = set.rtvLayout();
			function = layout.service();
		}

		// If a Display Button/Link get the function
		if (element instanceof DisplayButtonLink)
		{
			DisplayAttributesSet set = ((DisplayButtonLink) element).rtvParentSet();
			if (set != null)
			{
				Layout layout = set.rtvLayout();
				function = layout.service();
			}
		}

		if (function == null)
		{
			throw new EQRuntimeException("Failed to obtain Service for element [" + element.getClass().getSimpleName()
							+ "] with id [" + element.getId() + "]");
		}

		// Not known. Default to 'allowed'
		return function;
	}
	/**
	 * Validates a regular expression by attempting to parse it
	 * 
	 * @param messageContainer
	 *            a MessageContainer to add messages to
	 * @param problemLocation
	 *            A ProblemLocation instance
	 * @param inputField
	 *            The InputField to which the Regular Expression belongs
	 * @param regEx
	 *            String containing the regular expression
	 * 
	 * @return an integer indicating the location within the String of a error due to a ParseException. -1 will be returned if the
	 *         parse was successful, or if another problem occurred
	 */
	public static int validateRegex(MessageContainer messageContainer, ProblemLocation problemLocation, InputField inputField,
					String regEx)
	{
		// By default, we assume that it's valid unless we determine otherwise.
		int result = -1;
		try
		{
			// We validate the regex by checking whether we get a ParseException.
			Pattern.compile(regEx);
		}
		catch (PatternSyntaxException pe)
		{
			result = pe.getIndex();

			// An off-by-one bug in the xerces regex parser will sometimes return a location for the parseError that
			// is off the end of the string. If this is the case, then we want to highlight the last character.
			if (result >= regEx.length())
			{
				result = regEx.length() - 1;
			}

			if (LOG.isDebugEnabled())
			{
				LOG.debug("Parse Error location: " + result); //$NON-NLS-1$
			}
			messageContainer.addErrorMessageId("Language.RegExPatternSyntaxException", new String[] { pe.getDescription(),
							inputField.getId() }, problemLocation);
		}
		catch (RuntimeException re)
		{
			// Another bug in the parser will sometimes throw a RuntimeException instead of a ParseException.
			// When we get a RuntimeException, we aren't provided with the additional information we need to highlight
			// the parse error. So, we have to use our own error message
			LOG.error("Parse Error", re); //$NON-NLS-1$
			messageContainer.addErrorMessageId("Language.RegExRuntimeException", new String[] { re.getClass().getName(),
							inputField.getId() }, problemLocation);
		}
		return result;
	}

	/**
	 * Validates that the Journal File buffer length will not exceed the maximum permitted length of
	 * {@link JournalFile#MAX_JOURNAL_BUFFER_SIZE}.
	 * <p>
	 * This is to ensure that EQ4 services will be callable via all the drivers that use this buffer size.
	 * 
	 * @param service
	 *            the service to validate
	 * @param messageContainer
	 *            a MessageContainer to add messages to
	 * @param problemLocation
	 *            A <code>ProblemLocation</code> instance
	 */
	public static void validateServiceJournalFileBufferSize(Function service, MessageContainer messageContainer,
					ProblemLocation problemLocation)
	{
		// Accumulate total journal file buffer size
		int fileBufferSize = JournalFile.STANDARD_JOURNAL_FIELD_SIZE;
		for (InputFieldSet inputFieldSet : service.getInputFieldSets())
		{
			for (InputField inputField : inputFieldSet.getInputFields())
			{
				// Accumulate field size...
				int fieldBufferSize = getFieldBufferSize(inputField);
				if (fieldBufferSize == -1)
				{
					// Can't accumulate an accurate total now:
					return;
				}
				else
				{
					fileBufferSize += fieldBufferSize;
				}
			}
		}

		// Validate the record buffer size against the maximum permitted
		if (fileBufferSize > JournalFile.MAX_JOURNAL_BUFFER_SIZE)
		{
			messageContainer.addErrorMessageId("Language.MaximumJournalFileLengthExceeded", new String[] {
							Integer.toString(fileBufferSize), Integer.toString(JournalFile.MAX_JOURNAL_BUFFER_SIZE) },
							problemLocation);
		}
	}
	/**
	 * Determines the file buffer length of the field
	 * <p>
	 * This method is used when calculating the total buffer length for a service, to validate that the maximum size has not been
	 * exceeded. As only InputFields from the service (not WorkFields) are included on the Journal file, only InputFields can be
	 * passed to this method.
	 * <p>
	 * Note that this method assumes a single byte CCSID, not Unicode, and the caller must also take into account the size (
	 * {@link JournalFile#STANDARD_JOURNAL_FIELD_SIZE}) of the standard journal fields that are included on the file automatically.
	 * 
	 * @param inputField
	 *            an InputField
	 * @return an int containing the buffer size of the field. This will be -1 if an error occurred (e.g. invalid characters in the
	 *         size attribute). In this case the total buffer size cannot be determined accurately.
	 */
	private static int getFieldBufferSize(InputField inputField)
	{
		int result = -1;
		String dataType = inputField.getDataType();
		try
		{
			int size = Integer.parseInt(inputField.getSize());
			if (EqDataType.TYPE_PACKED.equals(dataType))
			{
				int temp = size + 1;
				result = temp / 2;
				if (temp % 2 == 1)
				{
					result++;
				}
			}
			else
			{
				// For all other data types, the defined size will be the buffer size
				result = size;
			}
		}
		catch (NumberFormatException e)
		{
			// Return -1 to indicate an invalid field size
		}
		return result;
	}
	/**
	 * Validates that the GAEPF key length will not exceed the maximum permitted length of {@link GAERecordDataModel#MAX_KEY_SIZE}.
	 * <p>
	 * This is to ensure that EQ4 services will be can be stored on the GAEPF file without data truncation.
	 * 
	 * @param service
	 *            the service to validate
	 * @param messageContainer
	 *            a MessageContainer to add messages to
	 * @param problemLocation
	 *            A <code>ProblemLocation</code> instance
	 */
	public static void validateServiceKeySize(Function service, MessageContainer messageContainer, ProblemLocation problemLocation)
	{
		int keySize = 0;
		// Accumulate total key size
		for (InputFieldSet inputFieldSet : service.getInputFieldSets())
		{
			for (InputField inputField : inputFieldSet.getInputFields())
			{
				if (inputField.isKey())
				{
					// Accumulate field size plus one for the separating colon
					int fieldSize = inputField.getId().length() + 1;

					keySize += fieldSize;

				}
			}
		}
		// Subtract one from final length as there is no colon after the last field in the key string
		keySize += -1;
		// Validate the record buffer size against the maximum permitted
		if (keySize > GAERecordDataModel.MAX_KEY_SIZE)
		{
			messageContainer.addErrorMessageId("Language.MaximumKeyLengthExceeded", new String[] { Integer.toString(keySize),
							Integer.toString(GAERecordDataModel.MAX_KEY_SIZE) }, problemLocation);
		}
	}

	public static synchronized void resetElContext()
	{
		elContext = new ELContextImpl();
	}

	/**
	 * Dummy String for EL binding.
	 * 
	 * @param service
	 *            the service to validate
	 * @param fieldName
	 *            the name of the field
	 */
	private static String getBindingString(Function service, String fieldName)
	{
		if (UserAccessFields.isFieldExists(fieldName))
		{
			return Toolbox.pad("1", "1", UserAccessFields.getMaximumLengthSC(fieldName));
		}

		if (service == null)
		{
			return "";
		}
		else if (!service.containsField(fieldName, true))
		{
			return null;
		}
		else
		{
			WorkField field = (WorkField) (service.rtvInputField(fieldName, true));
			String sizeString = field.getSize();
			int sizeInt = Integer.parseInt(sizeString);
			String type = field.getDataType();

			if (type.equals(EqDataType.TYPE_CHAR) || type.equals(EqDataType.TYPE_BOOLEAN))
			{
				return Toolbox.pad("1", "1", sizeInt);
			}
			else if (type.equals(EqDataType.TYPE_PACKED) || type.equals(EqDataType.TYPE_ZONED) || type.equals(EqDataType.TYPE_DATE))
			{
				if (sizeInt > 10)
				{
					return Toolbox.pad("1", "1", 10);
				}
				else
				{
					return Toolbox.pad("1", "1", sizeInt);
				}
			}
		}
		return "";
	}

	/**
	 * Validate suffix of the field
	 * 
	 * @param fieldName
	 *            the field to validate
	 */
	private static String validateSuffix(String fieldName)
	{
		if (fieldName.lastIndexOf(ELRuntime.SUFFIX_CONNECTOR) > 0)
		{
			int index = fieldName.lastIndexOf(ELRuntime.SUFFIX_CONNECTOR);
			String type = fieldName.substring(index);

			if (type.equals(ELRuntime.DATABASE_SFX) || type.equals(ELRuntime.REAL_SFX) || type.equals(ELRuntime.OUTPUT_SFX)
							|| type.equals(ELRuntime.INPUT_SFX))
			{
				return ELRuntime.getBareId(fieldName);
			}
		}
		return fieldName;
	}
}
