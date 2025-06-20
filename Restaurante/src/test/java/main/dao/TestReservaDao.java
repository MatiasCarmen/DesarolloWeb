package main.dao;

import main.modelo.Reserva;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestReservaDao {

    @Test
    void crearYListarReserva() {
        ReservaDao reservaDAO = new ReservaDao();

        Reserva nuevaReserva = new Reserva("Carlos Pérez", Date.valueOf("2025-05-25"),
                Time.valueOf("19:30:00"), 5, "Pendiente");
        boolean resultadoInsercion = reservaDAO.crearReserva(nuevaReserva);
        assertTrue(resultadoInsercion, "La reserva debería insertarse correctamente");

        List<Reserva> reservas = reservaDAO.obtenerReservas();
        assertNotNull(reservas, "La lista de reservas no debería ser nula");
    }
}
