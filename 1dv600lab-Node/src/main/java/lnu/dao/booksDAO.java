// Use this file to write and read the xml file.
// http://www.journaldev.com/1234/jaxb-tutorial-example-to-convert-object-to-xml-and-xml-to-object
// javax.xml.bind is added as a part of the sdk from java7 and forward.
package lnu.dao;

import lnu.models.Catalog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class booksDAO {
    private static final String FILE_NAME = "src/main/java/lnu/resources/books.xml";

    // Method to import XML to java
    public Catalog BooksFromFile() {


        try {
            JAXBContext context = JAXBContext.newInstance(Catalog.class);
            Unmarshaller un = context.createUnmarshaller();
            lnu.models.Catalog catalog = (lnu.models.Catalog) un.unmarshal(new File("books.xml"));

            return catalog;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to export java to XML
    public void BooksToFile(Catalog listOfBooks) {
        try {
            JAXBContext context = JAXBContext.newInstance(Catalog.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Write to System.out for debugging
            //m.marshal(books, System.out);

            // Write to File
            m.marshal(listOfBooks, new File(FILE_NAME));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


}