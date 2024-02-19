import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
    public static String xmlFilePath = "C:\\Users\\toni1\\OneDrive\\Documentos\\DAM\\2nDAM\\LibreriasJava\\bookings.xml"; // Ruta
                                                                                                                          // del
                                                                                                                          // archivo
                                                                                                                          // XML
    public static Connection conn;

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DataBaseConnection.getConnection();

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n" + "Menú de Reservas");
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
            // Crear una sentencia SQL para eliminar todos los registros de la tabla
            // 'bookings'
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
        String localizador = scanner.next();

        try {
            // Crear una sentencia SQL para seleccionar los datos de la reserva por
            // localizador
            String selectQuery = "SELECT * FROM reserva WHERE location_number = ?";

            // Crear un objeto PreparedStatement
            PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);

            // Establecer el localizador en la consulta SQL
            preparedStatement.setString(1, localizador);

            // Ejecutar la consulta
            ResultSet resultSet = preparedStatement.executeQuery();

            // Verificar si se encontraron resultados
            if (resultSet.next()) {
                // Obtener los datos de la reserva
                String clientName = resultSet.getString("client_name");
                String agencyName = resultSet.getString("agency_name");
                String price = resultSet.getString("price");
                String roomType = resultSet.getString("room_type");
                String hotelName = resultSet.getString("hotel_name");
                String checkIn = resultSet.getString("check_in");
                String roomNights = resultSet.getString("room_nights");

                // Mostrar los datos de la reserva
                System.out.println("Datos de la reserva:");
                System.out.println("Cliente: " + clientName);
                System.out.println("Agencia: " + agencyName);
                System.out.println("Precio: " + price);
                System.out.println("Tipo de habitación: " + roomType);
                System.out.println("Hotel: " + hotelName);
                System.out.println("Check-in: " + checkIn);
                System.out.println("Noches de habitación: " + roomNights);
            } else {
                System.out.println("No se encontró ninguna reserva con el localizador proporcionado.");
            }

            // Cerrar recursos
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    // Método para consultar las reservas de una agencia por su identificador
    public static void consultarReservasPorAgencia() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el identificador de la agencia: ");
        String identificadorAgencia = scanner.next();

        try {
            // Crear una sentencia SQL para seleccionar los datos de las reservas por
            // identificador de agencia
            String selectQuery = "SELECT * FROM reserva WHERE agency = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);
            preparedStatement.setString(1, identificadorAgencia);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Verificar si se encontraron resultados
            if (resultSet.next()) {
                System.out.println("Reservas para la agencia con identificador " + identificadorAgencia + ":");
                do {
                    // Obtener los datos de la reserva
                    String locationNumber = resultSet.getString("location_number");
                    String clientName = resultSet.getString("client");
                    String price = resultSet.getString("price");
                    String roomType = resultSet.getString("room");
                    String hotelName = resultSet.getString("hotel");
                    String checkIn = resultSet.getString("check_in");
                    String roomNights = resultSet.getString("room_nights");

                    // Mostrar los datos de la reserva
                    System.out.println("Localizador: " + locationNumber);
                    System.out.println("Cliente: " + clientName);
                    System.out.println("Precio: " + price);
                    System.out.println("Tipo de habitación: " + roomType);
                    System.out.println("Hotel: " + hotelName);
                    System.out.println("Check-in: " + checkIn);
                    System.out.println("Noches de habitación: " + roomNights);
                    System.out.println("-------------------------");
                } while (resultSet.next());
            } else {
                System.out.println(
                        "No se encontraron reservas para la agencia con identificador " + identificadorAgencia);
            }

            // Cerrar recursos
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    // Método para insertar una reserva en la base de datos
    public static void insertarReserva() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introduce el número de localización:");
            String locationNumber = scanner.nextLine();
            System.out.println("Introduce el nombre del cliente:");
            String clientName = scanner.nextLine();
            System.out.println("Introduce el nombre de la agencia:");
            String agencyName = scanner.nextLine();
            System.out.println("Introduce el precio:");
            String price = scanner.nextLine();
            System.out.println("Introduce el tipo de habitación:");
            String roomType = scanner.nextLine();
            System.out.println("Introduce el nombre del hotel:");
            String hotelName = scanner.nextLine();
            System.out.println("Introduce la fecha de check-in:");
            String checkIn = scanner.nextLine();
            System.out.println("Introduce el número de noches de habitación:");
            String roomNights = scanner.nextLine();

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
            System.out.println("Reserva insertada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar una reserva por su localizador
    public static void eliminarReserva() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el localizador de la reserva a eliminar: ");
        String localizador = scanner.next();
        try {
            // Crear una sentencia SQL para eliminar la reserva por localizador
            String deleteQuery = "DELETE FROM reserva WHERE location_number = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery);
            preparedStatement.setString(1, localizador);

            // Ejecutar la eliminación
            int filasEliminadas = preparedStatement.executeUpdate();

            // Cerrar recursos
            preparedStatement.close();
            System.out.println("Se ha eliminado la reserva con localizador " + localizador + " correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para modificar una reserva por su localizador
    public static void modificarReserva() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el localizador de la reserva a modificar: ");
        String localizador = scanner.next();

        try {
            // Verificar si la reserva existe
            String checkQuery = "SELECT * FROM reserva WHERE location_number = ?";
            PreparedStatement checkStatement = conn.prepareStatement(checkQuery);
            checkStatement.setString(1, localizador);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                // La reserva existe, proceder con la modificación
                System.out.println("Introduce el nuevo precio:");
                String newPrice = scanner.next();

                // Preparar la consulta SQL para la modificación
                String updateQuery = "UPDATE reserva SET price = ? WHERE location_number = ?";
                PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
                updateStatement.setString(1, newPrice);
                updateStatement.setString(2, localizador);

                // Ejecutar la modificación
                updateStatement.executeUpdate();
                updateStatement.close();
                System.out.println("Reserva modificada correctamente.");
            } else {
                System.out.println("No se encontró ninguna reserva con el localizador proporcionado.");
            }

            // Cerrar recursos
            resultSet.close();
            checkStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    // Método para salir del programa
    public static void salir() {
        System.out.println("¡Hasta luego!");
        System.exit(0);
    }
}