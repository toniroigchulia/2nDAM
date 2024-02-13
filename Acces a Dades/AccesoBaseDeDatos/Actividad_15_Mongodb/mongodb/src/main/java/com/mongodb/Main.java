package com.mongodb;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MongoDBConnector connector = new MongoDBConnector();
        BookingDAO bookingDAO = new BookingDAO(connector.getDatabase());

        // Exemple d'inserció de reserva
        Booking novaReserva = new Booking(123, "Client1", "Agència1", 200.0, "Suite", "HotelA", "2024-02-06", 3);
        bookingDAO.inserirReserva(novaReserva);
        System.out.println("Reserva inserida: " + novaReserva);

        // Exemple de mostra de totes les reserves
        System.out.println("\nTotes les reserves:");
        List<Booking> reserves = bookingDAO.mostrarReserves();
        for (Booking reserva : reserves) {
            System.out.println(reserva);
        }

        // Exemple d'actualització de preu de reserva
        int locationNumber = 123;
        double nouPreu = 250.0;
        bookingDAO.actualitzarPreuReserva(locationNumber, nouPreu);
        System.out.println("\nPreu de reserva actualitzat amb èxit.");

        // Exemple d'eliminació de reserva
        int reservaAEliminar = 123;
        bookingDAO.eliminarReserva(reservaAEliminar);
        System.out.println("\nReserva eliminada amb èxit.");

        connector.closeConnection();
    }
}