import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class App {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            BookingHandler handler = new BookingHandler();
            saxParser.parse(new File("C:\\Users\\toni1\\OneDrive\\Documentos\\DAM\\2nDAM\\Acces a Dades\\Actividad-9-SAXS\\SAXS\\lib\\bookings.xml"), handler);

            Vector<Booking> bookings = handler.getBookings();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Introduce el número de ubicación:");
            String inputLocation = scanner.nextLine();

            for (Booking booking : bookings) {
                if (booking.getLocationNumber().equalsIgnoreCase(inputLocation)) {
                    System.out.println("Información de la reserva:");
                    System.out.println("Número de ubicación: " + booking.getLocationNumber());
                    System.out.println("Cliente: " + booking.getClientName());
                    System.out.println("Agencia: " + booking.getAgencyId());
                    System.out.println("Precio: " + booking.getPrice());
                    System.out.println("Tipo de habitación: " + booking.getRoomIdType());
                    System.out.println("Hotel: " + booking.getHotelName());
                    System.out.println("Check-in: " + booking.getCheckIn());
                    System.out.println("Noches de habitación: " + booking.getRoomNights());
                    return;
                }
            }
            System.out.println("Reserva no encontrada para el número de ubicación ingresado.");

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
