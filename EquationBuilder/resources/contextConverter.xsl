<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8"
		indent="yes" />
	<xsl:template match="/">
		<contexts>
			<xsl:apply-templates select="html/body/table/tr/td/span" />
		</contexts>
	</xsl:template>
	<xsl:template match="a">
		<context>
			<xsl:attribute name="id">
			<xsl:value-of
				select="replace(lower-case(normalize-space(text())), ' ', '_')" /></xsl:attribute>
			<description>
				<xsl:value-of select="normalize-space(text())" />
			</description>
			<topic>
				<xsl:attribute name="label"><xsl:value-of
					select="normalize-space(text())" /></xsl:attribute>
				<xsl:attribute name="href">html/<xsl:value-of select="@href" /></xsl:attribute>
			</topic>
		</context>
	</xsl:template>
</xsl:stylesheet>