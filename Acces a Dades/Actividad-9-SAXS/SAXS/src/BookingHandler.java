import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.Vector;

public class BookingHandler extends DefaultHandler {
    private Vector<Booking> bookings;
    private Booking currentBooking;
    private String currentElement;

    public BookingHandler() {
        bookings = new Vector<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        if (qName.equalsIgnoreCase("booking")) {
            String locationNumber = attributes.getValue("location_number");
            currentBooking = new Booking(locationNumber, "", "", "", "", "", "", "", "", "");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentBooking != null) {
            String value = new String(ch, start, length).trim();
            if (value.length() > 0) {
                switch (currentElement) {
                    case "client":
                        currentBooking.setClientId(currentBooking.getClientId() + value);
                        currentBooking.setClientName(value);
                        break;
                    case "agency":
                        currentBooking.setAgencyId(currentBooking.getAgencyId() + value);
                        break;
                    case "price":
                        currentBooking.setPrice(value);
                        break;
                    case "room":
                        currentBooking.setRoomIdType(currentBooking.getRoomIdType() + value);
                        break;
                    case "hotel":
                        currentBooking.setHotelId(currentBooking.getHotelId() + value);
                        currentBooking.setHotelName(value);
                        break;
                    case "check_in":
                        currentBooking.setCheckIn(value);
                        break;
                    case "room_nights":
                        currentBooking.setRoomNights(value);
                        break;
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("booking")) {
            bookings.add(currentBooking);
            currentBooking = null;
        }
        currentElement = "";
    }

    public Vector<Booking> getBookings() {
        return bookings;
    }
}
