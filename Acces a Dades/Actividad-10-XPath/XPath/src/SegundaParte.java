import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class SegundaParte {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("bookings.xml"));

            Document newDoc = createNewDocument(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(newDoc);
            StreamResult result = new StreamResult(new File("new_bookings.xml"));
            transformer.transform(source, result);

            System.out.println("El nuevo archivo XML 'new_bookings.xml' ha sido creado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Document createNewDocument(Document doc) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document newDoc = builder.newDocument();

            Element information = newDoc.createElement("information");
            newDoc.appendChild(information);

            createSection(doc, newDoc, information, "client", "clients", "id_client");
            createSection(doc, newDoc, information, "agency", "agencies", "id_agency");
            createSection(doc, newDoc, information, "room", "rooms", "id_type");
            createSection(doc, newDoc, information, "hotel", "hotels", "id_hotel");

            return newDoc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void createSection(Document doc, Document newDoc, Element information, String tag,
                                      String sectionTag, String attribute) {
        NodeList nodeList = doc.getElementsByTagName(tag);
        Element section = newDoc.createElement(sectionTag);
        information.appendChild(section);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            String id = element.getAttribute(attribute);
            String name = element.getTextContent();

            Element newElement = newDoc.createElement(tag);
            newElement.setAttribute(attribute, id);
            newElement.appendChild(newDoc.createTextNode(name));
            section.appendChild(newElement);
        }
    }
}
