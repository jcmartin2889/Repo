<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8"
		indent="yes" />
	<xsl:template match="/">
		<toc label="BankFusion Equation Service Composer">
			<xsl:apply-templates select="toc" />
		</toc>
	</xsl:template>
	<xsl:template match="toc">
		<xsl:apply-templates select="topic" />
	</xsl:template>
	<xsl:template match="topic[@href]">
		<topic>
			<xsl:attribute name="label"><xsl:value-of select="@label" />
			</xsl:attribute>
			<xsl:attribute name="href">html/<xsl:value-of select="@href" />
			</xsl:attribute>
			<xsl:apply-templates select="topic" />
		</topic>
	</xsl:template>
</xsl:stylesheet>
		