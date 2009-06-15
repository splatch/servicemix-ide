package org.code_house.servicemixide.maven;


import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBException;

import oasis.names.tc.entity.xmlns.xml.catalog.Catalog;
import oasis.names.tc.entity.xmlns.xml.catalog.Public;
import oasis.names.tc.entity.xmlns.xml.catalog.RewriteSystem;
import oasis.names.tc.entity.xmlns.xml.catalog.RewriteURI;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.regexp.RE;
import org.code_house.servicemixide.utils.CatalogWriter;
import org.code_house.servicemixide.utils.SchemaUtil;
import org.codehaus.plexus.util.DirectoryScanner;

/**
 * Mojo who is running catalog generator.
 *
 * @goal generate
 * @execute phase=generate-sources
 *
 * @author Łukasz Dywicki luke@codehouse.org
 *
 * $Id$
 */
public class CatalogGeneratorMojo extends AbstractMojo {

    /**
     * Patter for file names who contains informations about schema version:
     * It should match:
     * schema-1.0.xsd
     * schema-1,1.dtd
     */
    private static final String NAME_VERSION_PATTERN = "-[0-9\\.,]\\.\\w+";

    /**
     * Object for matching schemas and versions.
     */
    private static final RE NAME_VERSION = new RE(NAME_VERSION_PATTERN);

    /**
     * Directory where plugin should search schema definitions.
     * 
     * @parameter expression="${catalog.source}" default-value="${basedir}/schema/"
     */
    private File source;

    /**
     * File where definitions should be saved?
     * 
     * @parameter expression="${catalog.file}" default-value="${catalog.source}/catalog.xml"
     */
    private File file;

    /**
     * Default inclusion patterns.
     * 
     * @parameter
     */
    private String[] includes = new String[] {"*.xsd", "*.dtd"};

    /**
     * Default exclusion patterns.
     * 
     * @parameter
     */
    private String[] excludes;

    /**
     * Information about rewrites. See {@link http://www.oasis-open.org/committees/entity/spec-2001-08-06.html#s.rewrite}
     * for more details.
     * 
     * @parameter
     */
    private RewriteURI[] rewritesUri = new RewriteURI[0];

    /**
     * Information about rewrites. See {@link http://www.oasis-open.org/committees/entity/spec-2001-08-06.html#s.rewrite}
     * for more details.
     * 
     * @parameter
     */
    private RewriteSystem[] rewritesSystem = new RewriteSystem[0];

    /**
     * Override generated files?
     * 
     * @parameter expression="${catalog.override}" default-value="true"
     */
    private boolean override;

    public void execute() throws MojoExecutionException, MojoFailureException {
        if (!source.isDirectory()) {
            throw new MojoExecutionException("Source parameter should point to existing directory."
                + " Given directory " + source.getAbsolutePath() + " is incorrect");
        }
        if (file.exists() && !override) {
            getLog().warn("Override catalog definitions");
            file.delete();
        }

        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setBasedir(source);
        scanner.setIncludes(includes);
        scanner.setExcludes(excludes);
        scanner.scan();

        Catalog catalog = new Catalog();
        rewriteTo(catalog.getRewriteSystems(), rewritesSystem);
        rewriteTo(catalog.getRewriteURIs(), rewritesUri);

        String[] schemas = scanner.getIncludedFiles();
        if (schemas.length == 0) {
            getLog().warn("No schemas found. XML Catalog will be not created");
            return;
        }

        for (String schema : schemas) {
            String targetNs = SchemaUtil.getTargetNamespace(new File(source,
                schema));

            if (containsVersion(schema)) {
                getLog().debug(schema + " contains version in name. "
                    + "Public and system entries will be generated");
                catalog.getSystems().add(createSystem(targetNs, schema));
            }
            catalog.getPublics().add(createPublic(targetNs, schema));
        }

        CatalogWriter catalogWriter = new CatalogWriter(file);
        try {
            catalogWriter.save(catalog);
        } catch (JAXBException e) {
            getLog().error(e);
            throw new MojoFailureException("Error saving xml catalog to " + file);
        }
    }

    private oasis.names.tc.entity.xmlns.xml.catalog.System createSystem(String targetNs, String schema) {
        oasis.names.tc.entity.xmlns.xml.catalog.System systemEntry = new oasis.names.tc.entity.xmlns.xml.catalog.System();
        systemEntry.setSystemId(schema);
        systemEntry.setUri(schema);
        return systemEntry;
    }

    private Public createPublic(String targetNs, String schema) {
        Public publicEntry = new Public();
        publicEntry.setPublicId(targetNs);
        publicEntry.setUri(schema);
        return publicEntry;
    }

    private boolean containsVersion(String fileName) {
        return NAME_VERSION.match(fileName);
    }

    private <T> void rewriteTo(List<T> catalogElements, T[] elements) {
        for (T rewrite : elements) {
            catalogElements.add(rewrite);
        }
    }
}
