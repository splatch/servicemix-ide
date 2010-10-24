<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" encoding="UTF-8" indent="yes" />
    <xsl:strip-space elements="*[local-name(.) = 'archetype-catalog']"/>

    <xsl:param name="groupId" />
    <xsl:param name="version" />

    <xsl:template match="*|@*">
        <xsl:call-template name="copy" />
    </xsl:template>

    <xsl:template match="//*[local-name(.) = 'archetype']">
        <xsl:choose>
            <xsl:when test="*[local-name(.) = 'groupId'] = $groupId and starts-with(*[local-name(.) = 'version'], $version)">
                <xsl:call-template name="copy" />
            </xsl:when>
            <!--
            <xsl:otherwise>
                <xsl:comment>
                    Refuse:
                        <xsl:value-of select="*[local-name(.) = 'groupId']" />!=<xsl:value-of select="$groupId" /> (<xsl:value-of select="string(*[local-name(.) = 'groupId'] = $groupId)" />), 
                        <xsl:value-of select="*[local-name(.) = 'version']" />!=<xsl:value-of select="$version" /> (<xsl:value-of select="string(starts-with(*[local-name(.) = 'version'], $version))" />),
                </xsl:comment>
            </xsl:otherwise>
            -->
        </xsl:choose>
    </xsl:template>

    <xsl:template name="copy">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>
    </xsl:template>

</xsl:stylesheet>