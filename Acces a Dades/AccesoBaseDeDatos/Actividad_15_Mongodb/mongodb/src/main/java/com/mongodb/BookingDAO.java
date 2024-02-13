package com.mongodb;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    private final MongoCollection<Document> collection;

    public BookingDAO(MongoDatabase database) {
        this.collection = database.getCollection("bookings");
    }

    public void inserirReserva(Booking reserva) {
        Document reservaDocument = new Document()
                .append("location_number", reserva.getLocationNumber())
                .append("client", reserva.getClient())
                .append("agency", reserva.getAgency())
                .append("price", reserva.getPrice())
                .append("room", reserva.getRoom())
                .append("hotel", reserva.getHotel())
                .append("check_in", reserva.getCheckIn())
                .append("room_nights", reserva.getRoomNights());

        collection.insertOne(reservaDocument);
    }

    public List<Booking> mostrarReserves() {
        List<Booking> reserves = new ArrayList<>();
        for (Document doc : collection.find()) {
            reserves.add(documentToBooking(doc));
        }
        return reserves;
    }

    public void actualitzarPreuReserva(int locationNumber, double nouPreu) {
        collection.updateOne(Filters.eq("location_number", locationNumber),
                Updates.set("price", nouPreu));
    }

    public void eliminarReserva(int locationNumber) {
        collection.deleteOne(Filters.eq("location_number", locationNumber));
    }

    private Booking documentToBooking(Document doc) {
        return new Booking(
                doc.getInteger("location_number"),
                doc.getString("client"),
                doc.getString("agency"),
                doc.getDouble("price"),
                doc.getString("room"),
                doc.getString("hotel"),
                doc.getString("check_in"),
                doc.getInteger("room_nights")
        );
    }
}