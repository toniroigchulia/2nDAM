import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Path("/bookings")
public class Peticiones {


    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document document;


    private ArrayList<Booking> bookings;

    @GET
    @Path("/get_reservas")
    @Produces("text/plain")
    public String getReservas() {
        initReservas();
        String reservaString = "";
        for (Booking booking : bookings) {
            reservaString += booking.toString() + "\n";
        }
        return reservaString;
    }

    @POST
    @Path("/post_reservas")
    @Consumes(MediaType.APPLICATION_JSON)
    public String postReservas(Booking newBooking) {
        initReservas();
        try {
            addReserva(newBooking);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        bookings.add(newBooking);
        return "Insertado!";
    }

    @GET
    @Path("/{i}")
    @Produces("text/plain")
    public String getReservaI(@PathParam("i") int i) {
        initReservas();

        if (i >= 0 && i < bookings.size()) {
            String reservaString = bookings.get(i).toString();
            return reservaString;
        } else {
            return "Índice fuera de rango";
        }
    }

    @PUT
    @Path("/update/{i}")
    @Produces("text/plain")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateReservaI(@PathParam("i") int i, Booking newBooking) {
        initReservas();
        Booking booking = bookings.get(i);
        newBooking.setLocationId(booking.getLocationId());
        bookings.set(i, newBooking);
        try {
            editReserva(newBooking);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return newBooking.toString() + " Updated!";
    }

    @DELETE
    @Path("/delete/{i}")
    @Produces("text/plain")
    public String deleteReservaI(@PathParam("i") int i) {
        initReservas();
        Booking booking = bookings.get(i);
        bookings.remove(i);
        try {
            deleteReserva(booking.getLocationId());
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return booking.toString() + " Deleted!";
    }

    public void editReserva(Booking editedBooking) throws TransformerException {
        String elementoAModificar = "booking";
        String atributoAModificar = "location_number";
        String valorDelAtributoAModificar = editedBooking.getLocationId() + "";

        Element root = document.getDocumentElement();

        for (Node child = root.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) child;
                if (elemento.getNodeName().equals(elementoAModificar) && elemento.getAttribute(atributoAModificar).equals(valorDelAtributoAModificar)) {
                    elemento.getElementsByTagName("client").item(0).setTextContent(editedBooking.getClient());
                    elemento.getElementsByTagName("agency").item(0).setTextContent(editedBooking.getAgency());
                    elemento.getElementsByTagName("price").item(0).setTextContent(editedBooking.getPrice());
                    elemento.getElementsByTagName("hotel").item(0).setTextContent(editedBooking.getHotel());
                    elemento.getElementsByTagName("check_in").item(0).setTextContent(editedBooking.getCheck_in());
                    elemento.getElementsByTagName("room_nights").item(0).setTextContent(editedBooking.getRoom_nights());
                    elemento.getElementsByTagName("room").item(0).setTextContent(editedBooking.getRoom_type());

                    javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
                    javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
                    javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(document);
                    javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(new java.io.File("tu_archivo.xml"));
                    transformer.transform(source, result);
                    break; 
                }
            }
        }
    }

    public void deleteReserva(int locationId) throws TransformerConfigurationException, TransformerException {
        String elementoAEliminar = "booking";
        String atributoAElininar = "location_number";
        String valorDelAtributoAEliminar = locationId + "";

        Element root = document.getDocumentElement();
        Node nodoAEliminar = null;

        for (Node child = root.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) child;
                if (elemento.getNodeName().equals(elementoAEliminar) && elemento.getAttribute(atributoAElininar).equals(valorDelAtributoAEliminar)) {
                    nodoAEliminar = child;
                    break;
                }
            }
        }

        if (nodoAEliminar != null) {
            root.removeChild(nodoAEliminar);
        } else {
            System.out.println("El elemento a eliminar no se encontró en el archivo XML.");
        }

        javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
        javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
        javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(document);
        javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(new java.io.File("tu_archivo.xml"));
        transformer.transform(source, result);
    }

    public void initReservas() {
        if (bookings == null) {
            bookings = new ArrayList<>();
        }
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new File("bookings.xml"));
            leerReservas();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public void addReserva(Booking reserva) throws TransformerException {
        String lastLocationNumber = reserva.getLocationId() + "";

        Element nuevoElemento = document.createElement("booking");
        nuevoElemento.setAttribute("location_number", Integer.toString(Integer.parseInt(lastLocationNumber)));

        Element cliente = document.createElement("client");
        cliente.setAttribute("id_client", "10");
        Text nombreCliente = document.createTextNode(reserva.getClient());
        cliente.appendChild(nombreCliente);

        Element agencia = document.createElement("agency");
        agencia.setAttribute("id_agency", "10");
        Text nombreAgencia = document.createTextNode(reserva.getAgency());
        agencia.appendChild(nombreAgencia);

        Element price = document.createElement("price");
        Text textPrice = document.createTextNode(reserva.getPrice());
        price.appendChild(textPrice);

        Element room = document.createElement("room");
        room.setAttribute("id_type", "10");
        Text roomType = document.createTextNode(reserva.getRoom_type());
        room.appendChild(roomType);

        Element hotel = document.createElement("hotel");
        hotel.setAttribute("id_hotel", "10");
        Text textHotel = document.createTextNode(reserva.getHotel());
        hotel.appendChild(textHotel);

        Element checkin = document.createElement("check_in");
        Text textCheckIn = document.createTextNode(reserva.getCheck_in());
        checkin.appendChild(textCheckIn);

        Element roomN = document.createElement("room_nights");
        Text textRoomN = document.createTextNode(reserva.getRoom_nights());
        roomN.appendChild(textRoomN);

        nuevoElemento.appendChild(cliente);
        nuevoElemento.appendChild(agencia);
        nuevoElemento.appendChild(price);
        nuevoElemento.appendChild(room);
        nuevoElemento.appendChild(hotel);
        nuevoElemento.appendChild(checkin);
        nuevoElemento.appendChild(roomN);

        document.getDocumentElement().appendChild(nuevoElemento);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("bookings.xml"));
        transformer.transform(source, result);
    }

    public void leerReservas() {
        Element root = document.getDocumentElement();
        NodeList nodelist = root.getChildNodes();
        for (int i = 0; i < nodelist.getLength(); i++) {
            Node node = nodelist.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                int idClient = Integer.parseInt(element.getAttribute("location_number"));
                String cliente = element.getElementsByTagName("client").item(0).getTextContent();
                String agencia = element.getElementsByTagName("agency").item(0).getTextContent();
                String price = element.getElementsByTagName("price").item(0).getTextContent();
                String roomType = element.getElementsByTagName("room").item(0).getTextContent();
                String hotel = element.getElementsByTagName("hotel").item(0).getTextContent();
                String checkIn = element.getElementsByTagName("check_in").item(0).getTextContent();
                String roomNights = (element.getElementsByTagName("room_nights").item(0).getTextContent());
                Booking b = new Booking(idClient, cliente.trim(), agencia.trim(), price.trim(), roomType.trim(), hotel.trim(), checkIn.trim(), roomNights.trim());
                bookings.add(b);
            }
        }
    }

}
