<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output version="1.0" indent="no" method="text" omit-xml-declaration="yes" encoding="UTF-8"/>
	<xsl:template match="/">
	<xsl:apply-templates select="toc/topic" />
	</xsl:template>
	<xsl:template match="topic[@href]">
	<xsl:text>&#xa;</xsl:text>
	<xsl:value-of select="replace(lower-case(normalize-space(@label)), ' ', '_')" />
	<xsl:text>=</xsl:text>
	<xsl:value-of select="@href" />
	<xsl:apply-templates select="topic" />
	</xsl:template>
    <xsl:template match="topic[not(@href)]">
    	<xsl:apply-templates select="topic" />
 	</xsl:template>   
</xsl:stylesheet>