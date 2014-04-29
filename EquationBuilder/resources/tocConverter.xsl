<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8"
		indent="yes" />
	<xsl:template match="/">
		<toc label="Equation Service Composer">
			<xsl:apply-templates select="html/body/table/tr/td/span" />
		</toc>
	</xsl:template>
	<xsl:template match="span" name="innerspan">
		<xsl:apply-templates select="a" />
	</xsl:template>
	<xsl:template match="html/body/table/tr/td/span">
		<xsl:apply-templates select="child::*[local-name(.) = 'a']" />
	</xsl:template>
	<xsl:template match="a">
		<xsl:variable name="node" select="following-sibling::*" />
		<xsl:variable name="count"
			select="following-sibling::span[1]/following-sibling::*" />
		<topic>
			<xsl:attribute name="label"><xsl:value-of
				select="normalize-space(text())" /></xsl:attribute>
			<xsl:attribute name="href">html/<xsl:value-of select="@href" /></xsl:attribute>
			<!--xsl:if test="not(empty($count))"-->
			<xsl:variable name="testnodes" select="$node except $count" />
			<xsl:choose>
				<xsl:when test="$testnodes[local-name() = 'a']" />
				<xsl:otherwise>
					<xsl:apply-templates select="following-sibling::span[1]" />
				</xsl:otherwise>
			</xsl:choose>
			<!--/xsl:if-->
		</topic>
	</xsl:template>
</xsl:stylesheet>