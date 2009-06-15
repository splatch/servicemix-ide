package org.code_house.servicemixide.utils;

import java.io.File;

import javax.xml.bind.JAXBException;

import oasis.names.tc.entity.xmlns.xml.catalog.Catalog;

/**
 * Writes catalog items.
 * 
 * @author Łukasz Dywicki luke@codehouse.org
 * 
 *         $Id$
 */
public class CatalogWriter extends JAXBCatalogTool {

    /**
     * Place where definition will be saved.
     */
    private File destination;

    public CatalogWriter(File saveTo) {
        this.destination = saveTo;
    }

    public void save(Catalog catalog) throws JAXBException {
        getMarshaller().marshal(catalog, destination);
    }
}
