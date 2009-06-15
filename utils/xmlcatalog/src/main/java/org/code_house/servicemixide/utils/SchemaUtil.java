package org.code_house.servicemixide.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class SchemaUtil {

    private static final DocumentBuilderFactory FACTORY = DocumentBuilderFactory
        .newInstance();

    /**
     * We don't have to create instances of this class.
     */
    private SchemaUtil() {
    }

    public final static Document getDocument(String fileName) {
        return getDocument(new File(fileName));
    }

    /**
     * @param file
     * @return
     */
    public static Document getDocument(File file) {
        try {
            DocumentBuilder builder = FACTORY.newDocumentBuilder();
            return builder.parse(file);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Error configuring parser", e);
        } catch (SAXException e) {
            throw new RuntimeException("Error, can not parse file", e);
        } catch (IOException e) {
            throw new RuntimeException("Error, can not read file", e);
        }
    }

    public final static String getTargetNamespace(String fileName) {
        return getTargetNamespace(new File(fileName));
    }

    public final static String getTargetNamespace(File file) {
        return getTargetNamespace(getDocument(file));
    }

    public final static String getTargetNamespace(Document doc) {
        String attr = doc.getDocumentElement().getAttribute("targetNamespace");
        return attr;
    }

}
