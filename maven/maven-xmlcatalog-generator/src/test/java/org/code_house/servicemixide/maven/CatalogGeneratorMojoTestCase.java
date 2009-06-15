package org.code_house.servicemixide.maven;

import java.io.File;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;

public class CatalogGeneratorMojoTestCase extends AbstractMojoTestCase {

    private static File SOURCE_BASE;
    private static File TARGET_BASE;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        SOURCE_BASE = new File(CatalogGeneratorMojoTestCase.class
            .getResource("/").toURI());
        TARGET_BASE = new File("target");
    }

    public void testExecution() throws Exception {
//        RewriteURI uri = new RewriteURI();
//        uri.setRewritePrefix("");
//        uri.setUriStartString("");

        File target = new File(TARGET_BASE, "catalog.xml");

        CatalogGeneratorMojo mojo = new CatalogGeneratorMojo();
        setVariableValueToObject(mojo, "source", new File(SOURCE_BASE, "schema"));
        setVariableValueToObject(mojo, "file", target);
//        setVariableValueToObject(mojo, "rewritesUri", new RewriteURI[] {uri});

        mojo.execute();

        assertEquals("Catalog file should be created", true, target.exists());
    }
}
