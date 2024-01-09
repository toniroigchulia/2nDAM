import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
    public static String xmlFilePath = "C:\\Users\\toni1\\OneDrive\\Documentos\\DAM\\2nDAM\\LibreriasJava\\bookings.xml"; // Ruta del archivo XML
    public static Connection conn;
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DataBaseConnection.getConnection();
        
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n"+"Menú de Reservas");
            System.out.println("1) Insertar les reserves a la base de dades");
            System.out.println("2) Suprimir totes les dades de les reserves");
            System.out.println("3) Consultar les dades d'una reserva");
            System.out.println("4) Consulta de reserves per agència");
            System.out.println("5) Insertar una reserva");
            System.out.println("6) Eliminar una reserva");
            System.out.println("7) Modificar una reserva");
            System.out.println("8) Sortir");
            System.out.print("Escull una opció: ");
            opcion = scanner.nextInt();
            System.out.println(" ");

            switch (opcion) {
                case 1:
                    insertarReservasBaseDatos();
                    break;
                case 2:
                    eliminarTodasReservas();
                    break;
                case 3:
                    consultarReservaPorLocalizador();
                    break;
                case 4:
                    consultarReservasPorAgencia();
                    break;
                case 5:
                    insertarReserva();
                    break;
                case 6:
                    eliminarReserva();
                    break;
                case 7:
                    modificarReserva();
                    break;
                case 8:
                    salir();
                    break;
                default:
                    System.out.println("Opció no vàlida. Si us plau, selecciona una opció vàlida.");
                    break;
            }
        } while (opcion != 8);

        scanner.close();
    }

    public static void insertarReservasBaseDatos() {
        try {
            // Crear el parser para el archivo XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Analizar el archivo XML
            Document document = (Document) builder.parse(new File(xmlFilePath));
            document.getDocumentElement().normalize();

            // Obtener la lista de elementos 'booking'
            NodeList bookingList = document.getElementsByTagName("booking");

            // Recorrer cada elemento 'booking' y realizar la inserción en la base de datos
            for (int i = 0; i < bookingList.getLength(); i++) {
                Element booking = (Element) bookingList.item(i);

                // Obtener los datos del elemento 'booking'
                String locationNumber = booking.getAttribute("location_number");
                String clientName = booking.getElementsByTagName("client").item(0).getTextContent();
                String agencyName = booking.getElementsByTagName("agency").item(0).getTextContent();
                String price = booking.getElementsByTagName("price").item(0).getTextContent();
                String roomType = booking.getElementsByTagName("room").item(0).getTextContent();
                String hotelName = booking.getElementsByTagName("hotel").item(0).getTextContent();
                String checkIn = booking.getElementsByTagName("check_in").item(0).getTextContent();
                String roomNights = booking.getElementsByTagName("room_nights").item(0).getTextContent();

                // Preparar la consulta SQL para la inserción
                String insertQuery = "INSERT INTO reserva (location_number, client, agency, price, room, hotel, check_in, room_nights) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

                // Establecer los valores en la consulta SQL
                preparedStatement.setString(1, locationNumber);
                preparedStatement.setString(2, clientName);
                preparedStatement.setString(3, agencyName);
                preparedStatement.setString(4, price);
                preparedStatement.setString(5, roomType);
                preparedStatement.setString(6, hotelName);
                preparedStatement.setString(7, checkIn);
                preparedStatement.setString(8, roomNights);

                // Ejecutar la inserción
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }

            System.out.println("Inserción completada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar todas las reservas de la base de datos
    public static void eliminarTodasReservas() {
        try {
            // Crear una sentencia SQL para eliminar todos los registros de la tabla 'bookings'
            String deleteQuery = "DELETE FROM reserva";

            // Crear un objeto Statement
            Statement statement = conn.createStatement();

            // Ejecutar la sentencia SQL para eliminar todas las reservas
            int filasEliminadas = statement.executeUpdate(deleteQuery);

            // Cerrar recursos
            statement.close();
            System.out.println("Se han eliminado " + filasEliminadas + " reservas correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar los datos de una reserva por su localizador
    public static void consultarReservaPorLocalizador() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el localizador de la reserva: ");
        String localitzador = scanner.next();
        // Lógica para buscar y mostrar los datos de la reserva con el localizador dado
        System.out.println("Método para consultar una reserva por localizador");
        scanner.close();
    }

    // Método para consultar las reservas de una agencia por su identificador
    public static void consultarReservasPorAgencia() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el identificador de la agencia: ");
        String identificadorAgencia = scanner.next();
        // Lógica para mostrar todas las reservas de la agencia con el identificador
        // dado
        System.out.println("Método para consultar reservas por agencia");
        scanner.close();
    }

    // Método para insertar una reserva en la base de datos
    public static void insertarReserva() {
        // Lógica para solicitar los datos y añadir una reserva a la base de datos
        System.out.println("Método para insertar una reserva en la base de datos");
    }

    // Método para eliminar una reserva por su localizador
    public static void eliminarReserva() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el localizador de la reserva a eliminar: ");
        String localitzador = scanner.next();
        // Lógica para eliminar la reserva con el localizador dado de la base de datos
        System.out.println("Método para eliminar una reserva por localizador");
        scanner.close();
    }

    // Método para modificar una reserva por su localizador
    public static void modificarReserva() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el localizador de la reserva a modificar: ");
        String localitzador = scanner.next();
        // Lógica para modificar la reserva con el localizador dado en la base de datos
        System.out.println("Método para modificar una reserva por localizador");
        scanner.close();
    }

    // Método para salir del programa
    public static void salir() {
        System.out.println("¡Hasta luego!");
        System.exit(0);
    }
}