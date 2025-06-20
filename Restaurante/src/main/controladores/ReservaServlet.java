package main.controladores;

import main.dao.ReservaDao;
import main.modelo.Reserva;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/reservas")
public class ReservaServlet extends HttpServlet {
    private final ReservaDao reservaDao = new ReservaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Reserva> reservas = reservaDao.listarReservas();
        request.setAttribute("reservas", reservas);
        request.getRequestDispatcher("jsp/reservas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombreCliente = request.getParameter("nombreCliente");
        Date fecha = Date.valueOf(request.getParameter("fecha"));

        // ✅ Validar el formato de `hora` antes de convertirlo a `Time`
        String horaStr = request.getParameter("hora");
        System.out.println("Hora recibida: " + horaStr);  // Depuración

        Time hora = null;
        if (horaStr != null && horaStr.matches("\\d{2}:\\d{2}:\\d{2}")) {
            hora = Time.valueOf(horaStr);
        } else {
            System.out.println("Error: formato de hora incorrecto.");
            response.sendRedirect("jsp/reserva.jsp?error=formato_hora");
            return;
        }

        int numeroMesa = Integer.parseInt(request.getParameter("numeroMesa"));
        String estado = request.getParameter("estado");

        Reserva nuevaReserva = new Reserva(nombreCliente, fecha, hora, numeroMesa, estado);
        boolean resultado = reservaDao.insertarReserva(nuevaReserva);

        if (resultado) {
            response.sendRedirect("reservas");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al crear la reserva.");
        }
    }
}