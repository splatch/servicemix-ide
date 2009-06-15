package org.code_house.servicemixide.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import oasis.names.tc.entity.xmlns.xml.catalog.ObjectFactory;

/**
 * Base class for reader/writer with JAXB releated methods.
 *
 * @author Łukasz Dywicki luke@codehouse.org
 *
 * $Id$
 */
public abstract class JAXBCatalogTool {

    /**
     * Vendor specific property name.
     */
    private static final String SUN_XML_BIND_HEADERS = "com.sun.xml.bind.xmlHeaders";

    /**
     * Header value - it points to oasis catalog.dtd.
     */
    private static final Object HEADER_VALUE = "<!DOCTYPE catalog "
        + "PUBLIC \"-//OASIS/DTD Entity Resolution XML Catalog V1.0//EN\" "
        + "\"http://www.oasis-open.org/committees/entity/release/1.0/catalog.dtd\">\n";

    /**
     * Static context created only one time.
     */
    private static JAXBContext context;

    /**
     * Gets JAXB context.
     * 
     * @return Context for working with oasis catalog.
     * @throws JAXBException When context creation fails.
     */
    protected final JAXBContext getContext() throws JAXBException {
        if (context == null) {
            context = JAXBContext.newInstance(ObjectFactory.class);
        }
        return context;
    }

    /**
     * Creates marshaller.
     * 
     * @return JAXB marshaller.
     * @throws JAXBException Caller have to handle this exception.
     */
    protected final Marshaller getMarshaller() throws JAXBException {
        Marshaller marshaller = getContext().createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
        marshaller.setProperty(SUN_XML_BIND_HEADERS, HEADER_VALUE);
        return marshaller;
    }

    /**
     * Creates new unmarshaller.
     * 
     * @return JAXB unmarshaller.
     * @throws JAXBException Caller have to handle this exception
     */
    protected final Unmarshaller getUnmarshaller() throws JAXBException {
        return getContext().createUnmarshaller();
    }
}
