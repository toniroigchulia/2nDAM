import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


/**
 * Practica MyBatis
 */
public class MyBatis {
    /*
     * Ejecucion del proceso
     */
    public static void main(String[] args) {
        // Inicializamos conexion
        BBDDManager bbdd = new BBDDManager();
        BBDDSesion sesion = new BBDDSesion();
        sesion.initSession();
        sesion.setRequiereCommit();

        // Cargamos mapper
        BBDDManager.sqlMapper.getConfiguration().addMapper(BookingsMapper.class);
        BookingsMapper bookingMapper = sesion.getSession().getMapper(BookingsMapper.class);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n" + "Menú Principal:");
            System.out.println("1. Carregar les dades d'un fitxer xml");
            System.out.println("2. Descarregar les dades a un fitxer csv");
            System.out.println("3. Eliminar totes les dades de la base de dades");
            System.out.println("4. Afegeix una reserva");
            System.out.println("5. Eliminar una reserva");
            System.out.println("6. Modificar una reserva");
            System.out.println("0. Sortir");

            System.out.print("Selecciona una opció: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    carregarDadesXML(bookingMapper);
                    break;
                case 2:
                    descarregarDadesCSV();
                    break;
                case 3:
                    eliminarTotesDades();
                    break;
                case 4:
                    afegirReserva();
                    break;
                case 5:
                    eliminarReserva();
                    break;
                case 6:
                    modificarReserva();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opció no vàlida.");
            }

            // Cerramos sesion
            sesion.closeSession();
        }
    }

    private static void carregarDadesXML(BookingsMapper mapper) {

        System.out.print("Introdueix la ruta del fitxer XML: ");
        String rutaFitxerXML = new Scanner(System.in).nextLine();

        parseXmlFile(rutaFitxerXML, mapper);

        System.out.println("Dades carregades amb èxit des de l'arxiu XML.");
    }

    private static void descarregarDadesCSV() {
        // Lógica para descargar datos desde la base de datos a un archivo CSV
        System.out.println("Descarregant les dades a l'arxiu CSV...");
    }

    private static void eliminarTotesDades() {
        // Lógica para eliminar todas las datos de la base de datos
        System.out.println("Eliminant totes les dades de la base de dades...");
    }

    private static void afegirReserva() {
        // Lógica para añadir una reserva a la base de datos
        System.out.println("Afegint una nova reserva a la base de dades...");
    }

    private static void eliminarReserva() {
        // Lógica para eliminar una reserva de la base de datos
        System.out.println("Eliminant una reserva de la base de dades...");
    }

    private static void modificarReserva() {
        // Lógica para modificar una reserva en la base de datos
        System.out.println("Modificant una reserva a la base de dades...");
    }
    
    private static void parseXmlFile(String rutaArchivoXML, BookingsMapper mapper) {
        try {
            // Aquí verifica si la cadena es una ruta válida
            File xmlFile = new File("C:\\Users\\toni1\\OneDrive\\Documentos\\DAM\\2nDAM\\2nDAM\\Acces a Dades\\AccesoBaseDeDatos\\Actividad_13_MyBatis\\borjamoll_mybatis\\borjamoll_mybatis\\borjamoll_mybatis\\src\\bookings.xml");
            if (!xmlFile.exists()) {
                System.out.println("El archivo XML no existe en la ruta proporcionada.");
                return;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList bookingList = doc.getElementsByTagName("booking");

            for (int i = 0; i < bookingList.getLength(); i++) {
                Node bookingNode = bookingList.item(i);

                if (bookingNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element bookingElement = (Element) bookingNode;

                    int locationNumber = Integer.parseInt(bookingElement.getAttribute("location_number"));
                    int clientId = Integer.parseInt(bookingElement.getElementsByTagName("client").item(0).getAttributes().getNamedItem("id_client").getTextContent());
                    String clientName = bookingElement.getElementsByTagName("client").item(0).getTextContent();
                    int agencyId = Integer.parseInt(bookingElement.getElementsByTagName("agency").item(0).getAttributes().getNamedItem("id_agency").getTextContent());
                    String agencyName = bookingElement.getElementsByTagName("agency").item(0).getTextContent();
                    BigDecimal price = new BigDecimal(bookingElement.getElementsByTagName("price").item(0).getTextContent().replace(",", "."));
                    int roomTypeId = Integer.parseInt(bookingElement.getElementsByTagName("room").item(0).getAttributes().getNamedItem("id_type").getTextContent());
                    String hotelName = bookingElement.getElementsByTagName("hotel").item(0).getTextContent();
                    LocalDate checkIn = LocalDate.parse(bookingElement.getElementsByTagName("check_in").item(0).getTextContent(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    int roomNights = Integer.parseInt(bookingElement.getElementsByTagName("room_nights").item(0).getTextContent());

                    // Ahora puedes llamar al método del mapper con estos valores
                    mapper.carregarDadesXML(new BookingsBean(locationNumber, clientId, clientName, agencyId, agencyName, price, roomTypeId, hotelName, checkIn, roomNights));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}