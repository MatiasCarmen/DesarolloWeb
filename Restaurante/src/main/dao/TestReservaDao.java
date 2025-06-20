package main.dao;

import main.modelo.Reserva;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class TestReservaDao {
    public static void main(String[] args) {
        ReservaDao reservaDAO = new ReservaDao();

        // PRUEBA DE INSERCIÓN
        Reserva nuevaReserva = new Reserva("Carlos Pérez", Date.valueOf("2025-05-25"), Time.valueOf("19:30:00"), 5, "Pendiente");
        boolean resultadoInsercion = reservaDAO.crearReserva(nuevaReserva);
        System.out.println("¿Reserva insertada?: " + resultadoInsercion);

        // PRUEBA DE LISTADO
        List<Reserva> reservas = reservaDAO.obtenerReservas();
        for (Reserva reserva : reservas) {
            System.out.println("Reserva: " + reserva.getId() + " - " +
                    reserva.getNombreCliente() + " - " +
                    reserva.getFecha() + " - " +
                    reserva.getHora());
        }
    }
}