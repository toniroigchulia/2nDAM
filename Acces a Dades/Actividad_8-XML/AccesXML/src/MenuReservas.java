import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MenuReservas {
    private static int contadorNumeroUbicacion = 1;
    private static int contadorIdCliente = 1;
    private static int contadorIdAgencia = 1;
    private static int contadorIdTipoHabitacion = 1;
    private static int contadorIdHotel = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n" + "Menú de Reservas:");
            System.out.println("1. Consultar reserva");
            System.out.println("2. Añadir reserva");
            System.out.println("3. Eliminar reserva");
            System.out.println("4. Modificar reserva");
            System.out.println("5. Salir y guardar en un nuevo archivo");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    consultarReserva();
                    break;
                case 2:
                    añadirReserva();
                    break;
                case 3:
                    eliminarReserva();
                    break;
                case 4:
                    modificarReserva();
                    break;
                case 5:
                    salir = true;
                    System.out.println("Terminando Programa");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }
        }

        scanner.close();
    }

    private static void consultarReserva() {
        System.out.print("Ingrese el número de ubicación: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int numeroUbicacion = scanner.nextInt();
            System.out.println("\n");

            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new File(
                        "C:\\Users\\toni1\\OneDrive\\Documentos\\DAM\\2nDAM\\Acces a Dades\\Actividad_8-XML\\AccesXML\\bookings.xml"));

                NodeList bookingList = document.getElementsByTagName("booking");

                for (int i = 0; i < bookingList.getLength(); i++) {
                    Node bookingNode = bookingList.item(i);

                    if (bookingNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element bookingElement = (Element) bookingNode;

                        int locationNumber = Integer.parseInt(bookingElement.getAttribute("location_number"));

                        if (locationNumber == numeroUbicacion) {
                            String clientName = bookingElement.getElementsByTagName("client").item(0).getTextContent();
                            String agencyName = bookingElement.getElementsByTagName("agency").item(0).getTextContent();
                            String priceStr = bookingElement.getElementsByTagName("price").item(0).getTextContent();
                            priceStr = priceStr.replace(",", ".");
                            double price = Double.parseDouble(priceStr);
                            String roomType = bookingElement.getElementsByTagName("room").item(0).getTextContent();
                            String hotelName = bookingElement.getElementsByTagName("hotel").item(0).getTextContent();
                            String checkInDateStr = bookingElement.getElementsByTagName("check_in").item(0)
                                    .getTextContent();
                            int roomNights = Integer
                                    .parseInt(bookingElement.getElementsByTagName("room_nights").item(0)
                                            .getTextContent());

                            // Formatear la fecha
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date checkInDate = dateFormat.parse(checkInDateStr);

                            // Mostrar los datos
                            System.out.println("Reserva encontrada:");
                            System.out.println("Cliente: " + clientName);
                            System.out.println("Agencia: " + agencyName);
                            System.out.println("Precio: " + price);
                            System.out.println("Tipo de habitación: " + roomType);
                            System.out.println("Hotel: " + hotelName);
                            System.out.println("Fecha de check-in: " + checkInDate);
                            System.out.println("Número de noches: " + roomNights);

                            return;
                        }
                    }
                }

                System.out.println("La reserva con el número de ubicación " + numeroUbicacion + " no existe.");

            } catch (ParserConfigurationException | SAXException | IOException | ParseException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Entrada no válida. Debe ingresar un número.");
        }
    }

    private static void añadirReserva() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(
                    "C:\\Users\\toni1\\OneDrive\\Documentos\\DAM\\2nDAM\\Acces a Dades\\Actividad_8-XML\\AccesXML\\bookings.xml"));

            Element nuevaReserva = document.createElement("booking");
            nuevaReserva.setAttribute("location_number", obtenerNumeroUbicacion());

            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el nombre del cliente: ");
            String nombreCliente = scanner.nextLine();
            System.out.print("Ingrese el nombre de la agencia: ");
            String nombreAgencia = scanner.nextLine();
            System.out.print("Ingrese el precio: ");
            double precio = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Ingrese el tipo de habitación: ");
            String tipoHabitacion = scanner.nextLine();
            System.out.print("Ingrese el nombre del hotel: ");
            String nombreHotel = scanner.nextLine();
            System.out.print("Ingrese la fecha de check-in (dd/MM/yyyy): ");
            String fechaCheckIn = scanner.nextLine();
            System.out.print("Ingrese el número de noches: ");
            int numNoches = scanner.nextInt();

            Element cliente = document.createElement("client");
            cliente.setAttribute("id_client", obtenerNuevoIdCliente());
            cliente.appendChild(document.createTextNode(nombreCliente));

            Element agencia = document.createElement("agency");
            agencia.setAttribute("id_agency", obtenerNuevoIdAgencia());
            agencia.appendChild(document.createTextNode(nombreAgencia));

            Element precioElement = document.createElement("price");
            precioElement.appendChild(document.createTextNode(Double.toString(precio)));

            Element tipoHabitacionElement = document.createElement("room");
            tipoHabitacionElement.setAttribute("id_type", obtenerNuevoIdTipoHabitacion());
            tipoHabitacionElement.appendChild(document.createTextNode(tipoHabitacion));

            Element hotel = document.createElement("hotel");
            hotel.setAttribute("id_hotel", obtenerNuevoIdHotel());
            hotel.appendChild(document.createTextNode(nombreHotel));

            Element checkIn = document.createElement("check_in");
            checkIn.appendChild(document.createTextNode(fechaCheckIn));

            Element roomNights = document.createElement("room_nights");
            roomNights.appendChild(document.createTextNode(Integer.toString(numNoches)));

            nuevaReserva.appendChild(cliente);
            nuevaReserva.appendChild(agencia);
            nuevaReserva.appendChild(precioElement);
            nuevaReserva.appendChild(tipoHabitacionElement);
            nuevaReserva.appendChild(hotel);
            nuevaReserva.appendChild(checkIn);
            nuevaReserva.appendChild(roomNights);

            document.getDocumentElement().appendChild(nuevaReserva);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(
                    "C:\\Users\\toni1\\OneDrive\\Documentos\\DAM\\2nDAM\\Acces a Dades\\Actividad_8-XML\\AccesXML\\bookings.xml"));
            transformer.transform(source, result);

            System.out.println("Reserva añadida correctamente.");

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void eliminarReserva() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el número de ubicación de la reserva que desea eliminar: ");
            int numeroUbicacion = scanner.nextInt();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(
                    "C:\\Users\\toni1\\OneDrive\\Documentos\\DAM\\2nDAM\\Acces a Dades\\Actividad_8-XML\\AccesXML\\bookings.xml"));

            NodeList bookingList = document.getElementsByTagName("booking");

            for (int i = 0; i < bookingList.getLength(); i++) {
                Node bookingNode = bookingList.item(i);

                if (bookingNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element bookingElement = (Element) bookingNode;

                    int locationNumber = Integer.parseInt(bookingElement.getAttribute("location_number"));

                    if (locationNumber == numeroUbicacion) {
                        bookingElement.getParentNode().removeChild(bookingElement);

                        // Guardar el documento actualizado en el archivo
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource source = new DOMSource(document);
                        StreamResult result = new StreamResult(new File(
                                "C:\\Users\\toni1\\OneDrive\\Documentos\\DAM\\2nDAM\\Acces a Dades\\Actividad_8-XML\\AccesXML\\bookings.xml"));
                        transformer.transform(source, result);

                        System.out.println("Reserva eliminada correctamente.");
                        return;
                    }
                }
            }

            // Si llegamos aquí, no se encontró la reserva
            System.out.println("La reserva con el número de ubicación " + numeroUbicacion + " no existe.");

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void modificarReserva() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el número de ubicación de la reserva que desea modificar: ");
            int numeroUbicacion = scanner.nextInt();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(
                    "C:\\Users\\toni1\\OneDrive\\Documentos\\DAM\\2nDAM\\Acces a Dades\\Actividad_8-XML\\AccesXML\\bookings.xml"));

            NodeList bookingList = document.getElementsByTagName("booking");

            for (int i = 0; i < bookingList.getLength(); i++) {
                Node bookingNode = bookingList.item(i);

                if (bookingNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element bookingElement = (Element) bookingNode;

                    int locationNumber = Integer.parseInt(bookingElement.getAttribute("location_number"));

                    if (locationNumber == numeroUbicacion) {
                        System.out.println("Ingrese los nuevos datos para la reserva:");

                        System.out.print("Nombre del cliente: ");
                        String nuevoNombreCliente = scanner.next();
                        bookingElement.getElementsByTagName("client").item(0).setTextContent(nuevoNombreCliente);
            
                        System.out.print("Nombre de la agencia: ");
                        String nuevaAgencia = scanner.next();
                        bookingElement.getElementsByTagName("agency").item(0).setTextContent(nuevaAgencia);
            
                        System.out.print("Precio: ");
                        double nuevoPrecio = scanner.nextDouble();
                        bookingElement.getElementsByTagName("price").item(0).setTextContent(String.valueOf(nuevoPrecio));
            
                        System.out.print("Tipo de habitación: ");
                        String nuevoTipoHabitacion = scanner.next();
                        bookingElement.getElementsByTagName("room").item(0).setTextContent(nuevoTipoHabitacion);
            
                        System.out.print("Nombre del hotel: ");
                        String nuevoNombreHotel = scanner.next();
                        bookingElement.getElementsByTagName("hotel").item(0).setTextContent(nuevoNombreHotel);
            
                        System.out.print("Fecha de check-in (dd/MM/yyyy): ");
                        String nuevaFechaCheckIn = scanner.next();
                        bookingElement.getElementsByTagName("check_in").item(0).setTextContent(nuevaFechaCheckIn);
            
                        System.out.print("Número de noches: ");
                        int nuevaNumNoches = scanner.nextInt();
                        bookingElement.getElementsByTagName("room_nights").item(0).setTextContent(String.valueOf(nuevaNumNoches));

                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource source = new DOMSource(document);
                        StreamResult result = new StreamResult(new File(
                                "C:\\Users\\toni1\\OneDrive\\Documentos\\DAM\\2nDAM\\Acces a Dades\\Actividad_8-XML\\AccesXML\\bookings.xml"));
                        transformer.transform(source, result);

                        System.out.println("Reserva modificada correctamente.");
                        return;
                    }
                }
            }

            // Si llegamos aquí, no se encontró la reserva
            System.out.println("La reserva con el número de ubicación " + numeroUbicacion + " no existe.");

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    // METODOS PARA NUMEROS DE LA RESERVA
    private static String obtenerNumeroUbicacion() {
        String nuevoNumeroUbicacion = "Ubicacion" + contadorNumeroUbicacion;
        contadorNumeroUbicacion++;
        return nuevoNumeroUbicacion;
    }

    private static String obtenerNuevoIdCliente() {
        String nuevoIdCliente = "Cliente" + contadorIdCliente;
        contadorIdCliente++;
        return nuevoIdCliente;
    }

    private static String obtenerNuevoIdAgencia() {
        String nuevoIdAgencia = "Agencia" + contadorIdAgencia;
        contadorIdAgencia++;
        return nuevoIdAgencia;
    }

    private static String obtenerNuevoIdTipoHabitacion() {
        String nuevoIdTipoHabitacion = "TipoHabitacion" + contadorIdTipoHabitacion;
        contadorIdTipoHabitacion++;
        return nuevoIdTipoHabitacion;
    }

    private static String obtenerNuevoIdHotel() {
        String nuevoIdHotel = "Hotel" + contadorIdHotel;
        contadorIdHotel++;
        return nuevoIdHotel;
    }
}