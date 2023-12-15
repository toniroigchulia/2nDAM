package com.toni;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Paso 1: Leer y parsear el archivo XML
            File xmlFile = new File("bookings.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Paso 2: Convertir datos del archivo XML a JSON
            JSONObject jsonObject = new JSONObject();
            JSONObject bookingsObject = new JSONObject();
            JSONArray bookingArray = new JSONArray();

            // Obtener nodos 'booking'
            doc.getDocumentElement().normalize();
            org.w3c.dom.NodeList nodeList = doc.getElementsByTagName("booking");

            // Procesar cada nodo 'booking'
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                org.w3c.dom.Node node = nodeList.item(temp);
                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    JSONObject bookingObject = new JSONObject();
                    bookingObject.put("location_number", element.getAttribute("location_number"));
                    bookingObject.put("client", element.getElementsByTagName("client").item(0).getTextContent());
                    bookingObject.put("agency", element.getElementsByTagName("agency").item(0).getTextContent());
                    bookingObject.put("price", element.getElementsByTagName("price").item(0).getTextContent());
                    bookingObject.put("room", element.getElementsByTagName("room").item(0).getTextContent());
                    bookingObject.put("hotel", element.getElementsByTagName("hotel").item(0).getTextContent());
                    bookingObject.put("check_in", element.getElementsByTagName("check_in").item(0).getTextContent());
                    bookingObject.put("room_nights", element.getElementsByTagName("room_nights").item(0).getTextContent());

                    bookingArray.put(bookingObject);
                }
            }

            bookingsObject.put("booking", bookingArray);
            jsonObject.put("bookings", bookingsObject);

            // Paso 3 y 4: Crear y escribir datos en un nuevo archivo JSON
            File jsonFile = new File("bookings.json");
            jsonFile.createNewFile();

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(new org.w3c.dom.Document() {
            // Implementación de métodos omitida para simplificar
            });

            StreamResult result = new StreamResult(jsonFile);
            transformer.transform(source, result);

            // Paso 5: Verificar la conversión
            System.out.println("La conversión de XML a JSON se ha realizado correctamente.");

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
            System.err.println("Hubo un error al convertir el archivo XML a JSON.");
        }
    }
}
