<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" encoding="UTF-8" indent="yes" />

    <xsl:param name="location" />

    <xsl:template match="*|@*">
        <xsl:call-template name="copy" />
    </xsl:template>

    <xsl:template match="//*[local-name(.) = 'locations']">
        <locations>
            <xsl:apply-templates />

            <!-- Append directory with generated p2 metadata to locations -->
            <location type="Directory">
                <xsl:attribute name="path">
                    <xsl:value-of select="$location" />
                </xsl:attribute>
            </location>
        </locations>
    </xsl:template>

    <xsl:template name="copy">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>
    </xsl:template>

</xsl:stylesheet>