package com.misys.equation.common.ant.builder.helpText.helpers;

/**
 * 
 * @author deroset This emun contains all fixed text that the helper text process will use.
 * 
 */
public enum CommonDefinitions
{

	PROPERTY_FILE_NAME
	{

		@Override
		public String toString()
		{
			return "helpText.properties";
		}
	},

	FILE_SEPARATOR
	{

		@Override
		public String toString()
		{
			return "\\";
		}
	},
	FUNCTION_ERROR_FILES
	{

		@Override
		public String toString()
		{
			return "functionErrorFiles.log";
		}
	},
	APIHTML_ERROR_FILES
	{

		@Override
		public String toString()
		{
			return "apiHtmlErrorFiles.log";
		}
	},

	APIHtml
	{

		@Override
		public String toString()
		{
			return "APIHtml";
		}
	},
	ALL
	{

		@Override
		public String toString()
		{
			return "*ALL";
		}
	},
	RESOURCES
	{

		@Override
		public String toString()
		{
			return "resources";
		}
	}

}
