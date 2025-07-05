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
        List<Reserva> reservas = reservaDao.obtenerReservas();
        request.setAttribute("reservas", reservas);
        request.getRequestDispatcher("jsp/reservas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombreCliente = request.getParameter("nombreCliente");
        String fechaStr = request.getParameter("fecha");
        String numeroMesaStr = request.getParameter("numeroMesa");
        String estado = request.getParameter("estado");

        // Validaciones de entrada
        if (nombreCliente == null || nombreCliente.trim().isEmpty()) {
            response.sendRedirect("jsp/reservas.jsp?error=nombre_vacio");
            return;
        }

        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            response.sendRedirect("jsp/reservas.jsp?error=fecha_vacia");
            return;
        }

        Date fecha;
        try {
            fecha = Date.valueOf(fechaStr);
        } catch (IllegalArgumentException e) {
            response.sendRedirect("jsp/reservas.jsp?error=fecha_invalida");
            return;
        }

        // ✅ Validar el formato de `hora` antes de convertirlo a `Time`
        String horaStr = request.getParameter("hora");
        System.out.println("Hora recibida: " + horaStr);  // Depuración

        Time hora = null;
        if (horaStr != null && horaStr.matches("\\d{2}:\\d{2}(:\\d{2})?")) {
            // Si el formato es HH:MM, agregar :00 para los segundos
            if (horaStr.matches("\\d{2}:\\d{2}")) {
                horaStr = horaStr + ":00";
            }
            hora = Time.valueOf(horaStr);
        } else {
            System.out.println("Error: formato de hora incorrecto.");
            response.sendRedirect("jsp/reservas.jsp?error=formato_hora");
            return;
        }

        int numeroMesa;
        try {
            numeroMesa = Integer.parseInt(numeroMesaStr);
            if (numeroMesa <= 0) {
                response.sendRedirect("jsp/reservas.jsp?error=numero_mesa_invalido");
                return;
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("jsp/reservas.jsp?error=numero_mesa_invalido");
            return;
        }

        if (estado == null || estado.trim().isEmpty()) {
            response.sendRedirect("jsp/reservas.jsp?error=estado_vacio");
            return;
        }

        Reserva nuevaReserva = new Reserva(nombreCliente, fecha, hora, numeroMesa, estado);
        boolean resultado = reservaDao.crearReserva(nuevaReserva);

        if (resultado) {
            response.sendRedirect("reservas");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al crear la reserva.");
        }
    }
}