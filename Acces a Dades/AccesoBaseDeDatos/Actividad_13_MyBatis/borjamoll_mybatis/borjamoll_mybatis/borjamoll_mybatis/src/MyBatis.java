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


public class MyBatis {
    public static void main(String[] args) {
        BBDDManager bbdd = new BBDDManager();
        BBDDSesion sesion = new BBDDSesion();
        sesion.initSession();
        sesion.setRequiereCommit();

        BBDDManager.sqlMapper.getConfiguration().addMapper(BookingsMapper.class);
        BookingsMapper bookingMapper = sesion.getSession().getMapper(BookingsMapper.class);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n" + "Menú Principal:");
            System.out.println("1. Carregar les dades d'un fitxer xml");
            System.out.println("2. Eliminar totes les dades de la base de dades");
            System.out.println("3. Afegeix una reserva");
            System.out.println("4. Eliminar una reserva");
            System.out.println("5. Modificar una reserva");
            System.out.println("0. Sortir");

            System.out.print("Selecciona una opció: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    carregarDadesXML(bookingMapper);
                    break;
                case 2:
                    eliminarTotesDades(bookingMapper);
                    break;
                case 3:
                    afegirReserva(bookingMapper);
                    break;
                case 4:
                    eliminarReserva(bookingMapper);
                    break;
                case 5:
                    modificarReserva(bookingMapper);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opció no vàlida.");
            }

            sesion.closeSession();
        }
    }

    // Cargar datos
    private static void carregarDadesXML(BookingsMapper mapper) {
        System.out.print("Introdueix la ruta del fitxer XML: ");
        String rutaFitxerXML = new Scanner(System.in).nextLine();

        parseXmlFile(rutaFitxerXML, mapper);

        System.out.println("Dades carregades amb èxit des de l'arxiu XML.");
    }

    // Eliminar datos
    private static void eliminarTotesDades(BookingsMapper mapper) {
        mapper.eliminarTodasReservas();
        System.out.println("Totes les dades de reserves eliminades de la base de dades.");
    }

    // Añadir reservas
    private static void afegirReserva(BookingsMapper mapper) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Introdueix les dades de la reserva:");
        System.out.print("Número de localització: ");
        int locationNumber = scanner.nextInt();
        System.out.print("ID del client: ");
        int clientId = scanner.nextInt();
        System.out.print("Nom del client: ");
        String clientName = scanner.next();
        System.out.print("ID de l'agència: ");
        int agencyId = scanner.nextInt();
        System.out.print("Nom de l'agència: ");
        String agencyName = scanner.next();
        System.out.print("Preu: ");
        BigDecimal price = scanner.nextBigDecimal();
        System.out.print("ID del tipus d'habitació: ");
        int roomTypeId = scanner.nextInt();
        System.out.print("Nom de l'hotel: ");
        String hotelName = scanner.next();
        System.out.print("Data d'entrada (dd/MM/yyyy): ");
        String checkInStr = scanner.next();
        LocalDate checkIn = LocalDate.parse(checkInStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Nits de l'habitació: ");
        int roomNights = scanner.nextInt();
    
        BookingsBean reserva = new BookingsBean(locationNumber, clientId, clientName, agencyId, agencyName, price, roomTypeId, hotelName, checkIn, roomNights);
    
        mapper.agregarReserva(reserva);
        System.out.println("Reserva afegida amb èxit a la base de dades.");
    }

    // Eliminar reserva
    private static void eliminarReserva(BookingsMapper mapper) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Introdueix l'identificador de la reserva a eliminar: ");
        int idReserva = scanner.nextInt();
    
        mapper.eliminarReservaPorId(idReserva);
        System.out.println("Reserva eliminada amb èxit de la base de dades.");
    }

    // Modificar reserva
    private static void modificarReserva(BookingsMapper mapper) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Introdueix l'identificador de la reserva a modificar: ");
        int idReserva = scanner.nextInt();
    
        BookingsBean reservaActual = mapper.obtenerReservaPorId(idReserva);
    
        System.out.println("Detalls de la reserva actual:");
        System.out.println(reservaActual.toString());
    
        System.out.println("Introdueix els nous detalls de la reserva:");
    
        System.out.print("Número de localització: ");
        int locationNumber = scanner.nextInt();
        System.out.print("ID del client: ");
        int clientId = scanner.nextInt();
        System.out.print("Nom del client: ");
        String clientName = scanner.next();
        System.out.print("ID de l'agència: ");
        int agencyId = scanner.nextInt();
        System.out.print("Nom de l'agència: ");
        String agencyName = scanner.next();
        System.out.print("Preu: ");
        BigDecimal price = scanner.nextBigDecimal();
        System.out.print("ID del tipus d'habitació: ");
        int roomTypeId = scanner.nextInt();
        System.out.print("Nom de l'hotel: ");
        String hotelName = scanner.next();
        System.out.print("Data d'entrada (dd/MM/yyyy): ");
        String checkInStr = scanner.next();
        LocalDate checkIn = LocalDate.parse(checkInStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Nits de l'habitació: ");
        int roomNights = scanner.nextInt();
    
        reservaActual.setLocationNumber(locationNumber);
        reservaActual.setClientId(clientId);
        reservaActual.setClientName(clientName);
        reservaActual.setAgencyId(agencyId);
        reservaActual.setAgencyName(agencyName);
        reservaActual.setPrice(price);
        reservaActual.setRoomTypeId(roomTypeId);
        reservaActual.setHotelName(hotelName);
        reservaActual.setCheckIn(checkIn);
        reservaActual.setRoomNights(roomNights);
    
        mapper.actualizarReserva(reservaActual);
        System.out.println("Reserva modificada amb èxit a la base de dades.");
    }
    
    // Cargar Xml a la base de datos
    private static void parseXmlFile(String rutaArchivoXML, BookingsMapper mapper) {
        try {
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

                    mapper.agregarReserva(new BookingsBean(locationNumber, clientId, clientName, agencyId, agencyName, price, roomTypeId, hotelName, checkIn, roomNights));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}