package com.misys.equation.common.ant.builder.helpconverter;

public enum CommonDefinitions
{

	TOC_CONVERTER
	{

		@Override
		public String toString()
		{
			return "tocConverter.xsl";
		}
	},

	CONTEXT_CONVERTER
	{

		@Override
		public String toString()
		{
			return "contextConverter.xsl";
		}
	},

	CONTEXTS
	{

		@Override
		public String toString()
		{
			return "contexts.xml";
		}
	},
	HELPFILES_XREF_CONVERTER
	{

		@Override
		public String toString()
		{
			return "helpFilesXrefConverter.xsl";
		}
	},

	HELPFILES_XREF
	{

		@Override
		public String toString()
		{
			return "helpFilesXref.properties";
		}
	},
	TOC
	{

		@Override
		public String toString()
		{
			return "toc.xml";
		}
	},

	TOC_HTM
	{

		@Override
		public String toString()
		{
			return "toc.htm";
		}
	},
	XML_HEAD
	{

		@Override
		public String toString()
		{
			return "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
		}
	},

	OUTPUT_DIRECTORY
	{

		@Override
		public String toString()
		{
			return "html";
		}
	},
	TRANSITIONAL
	{

		@Override
		public String toString()
		{
			return "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd";
		}
	},
	TRANSITIONAL_DTD
	{

		@Override
		public String toString()
		{
			return "xhtml1-transitional.dtd";
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
	RESOURCES
	{

		@Override
		public String toString()
		{
			return "resources";
		}
	}
}
