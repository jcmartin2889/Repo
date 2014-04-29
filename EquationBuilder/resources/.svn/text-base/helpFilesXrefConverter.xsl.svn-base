<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output version="1.0" indent="no" method="text" omit-xml-declaration="yes" encoding="UTF-8"/>
	<xsl:template match="head"></xsl:template>
	<xsl:template match="title"></xsl:template>
	<xsl:template match="h1"></xsl:template>
	<xsl:template match="a">
	<xsl:text>&#xa;</xsl:text>
	<xsl:value-of select="replace(lower-case(normalize-space(text())), ' ', '_')" />
	<xsl:text>=</xsl:text>
	<xsl:value-of select="@href" />
	</xsl:template>
</xsl:stylesheet>