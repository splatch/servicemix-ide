<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:arch="http://maven.apache.org/plugins/maven-archetype-plugin/archetype-catalog/1.0.0">

    <xsl:output method="xml" encoding="UTF-8" indent="yes" />
    <xsl:strip-space elements="arch:archetypes"/>

    <xsl:param name="groupId" />
    <xsl:param name="version" />

    <xsl:template match="*|@*">
        <xsl:call-template name="copy" />
    </xsl:template>

    <xsl:template match="//arch:archetype">
        <xsl:choose>
            <xsl:when test="arch:groupId = $groupId and starts-with(arch:version, $version)">
                <xsl:call-template name="copy" />
            </xsl:when>
            <!-- Debug condition
            <xsl:otherwise>
                <xsl:comment>
                    Refuse:
                        <xsl:value-of select="arch:groupId" />!=<xsl:value-of select="$groupId" /> (<xsl:value-of select="string(arch:groupId = $groupId)" />), 
                        <xsl:value-of select="arch:version" />!=<xsl:value-of select="$version" /> (<xsl:value-of select="string(starts-with(arch:version, $version))" />),
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