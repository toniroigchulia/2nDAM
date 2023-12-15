import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.xpath.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeraParte {
    public static void main(String[] args) {
        try {
            File inputFile = new File("bookings.xml"); // Ruta al archivo XML

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();
            Scanner scanner = new Scanner(System.in);

            int choice;
            do {
                System.out.println("Elija una opción:");
                System.out.println("1. Ver información del cliente");
                System.out.println("2. Ver información de la agencia");
                System.out.println("3. Ver precio");
                System.out.println("4. Ver tipo de habitación");
                System.out.println("5. Ver hotel");
                System.out.println("6. Ver fecha de entrada");
                System.out.println("7. Ver número de noches");
                System.out.println("8. Salir");
                System.out.print("Ingrese el número de opción: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        List<String> clientInfo = getXPathValues(doc, xPath, "//booking/client");
                        displayResults("Información del cliente:", clientInfo);
                        break;
                    case 2:
                        List<String> agencyInfo = getXPathValues(doc, xPath, "//booking/agency");
                        displayResults("Información de la agencia:", agencyInfo);
                        break;
                    case 3:
                        List<String> priceInfo = getXPathValues(doc, xPath, "//booking/price");
                        displayResults("Precios:", priceInfo);
                        break;
                    case 4:
                        List<String> roomInfo = getXPathValues(doc, xPath, "//booking/room");
                        displayResults("Tipos de habitación:", roomInfo);
                        break;
                    case 5:
                        List<String> hotelInfo = getXPathValues(doc, xPath, "//booking/hotel");
                        displayResults("Información del hotel:", hotelInfo);
                        break;
                    case 6:
                        List<String> checkInInfo = getXPathValues(doc, xPath, "//booking/check_in");
                        displayResults("Fechas de entrada:", checkInInfo);
                        break;
                    case 7:
                        List<String> roomNightsInfo = getXPathValues(doc, xPath, "//booking/room_nights");
                        displayResults("Número de noches:", roomNightsInfo);
                        break;
                    case 8:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, elija una opción válida.");
                        break;
                }
            } while (choice != 8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> getXPathValues(Document doc, XPath xPath, String expression) throws XPathExpressionException {
        XPathExpression xPathExpr = xPath.compile(expression);
        NodeList nodeList = (NodeList) xPathExpr.evaluate(doc, XPathConstants.NODESET);
        List<String> results = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            results.add(node.getTextContent());
        }

        return results;
    }

    private static void displayResults(String header, List<String> results) {
        if (!results.isEmpty()) {
            System.out.println(header);
            for (String result : results) {
                displayResult(result);
            }
        } else {
            System.out.println("No se encontró información para mostrar.");
        }
    }

    private static void displayResult(String result) {
        System.out.println("- " + result);
    }
}
