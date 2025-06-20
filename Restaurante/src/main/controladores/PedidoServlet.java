package main.controladores;

import main.dao.PedidoDAO;
import main.modelo.Pedido;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/pedidos")
public class PedidoServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(PedidoServlet.class.getName());
    private final PedidoDAO pedidoDAO = new PedidoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pedido> pedidos = pedidoDAO.listarPedidos();
        request.setAttribute("pedidos", pedidos);
        request.getRequestDispatcher("webapp/jsp/pedidos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cliente = request.getParameter("cliente");
        String fechaStr = request.getParameter("fecha");
        String estado = request.getParameter("estado");

        // Validaciones de los datos
        if (cliente == null || cliente.trim().isEmpty() || fechaStr == null || estado == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos inválidos: asegúrate de llenar todos los campos.");
            return;
        }

        Date fecha;
        try {
            fecha = Date.valueOf(fechaStr);
            if (fecha.before(new Date(System.currentTimeMillis()))) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "La fecha no puede ser anterior a hoy.");
                return;
            }
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de fecha inválido.");
            return;
        }

        Pedido nuevoPedido = new Pedido(cliente, fecha, estado);
        boolean resultado = pedidoDAO.insertarPedido(nuevoPedido);

        if (resultado) {
            response.sendRedirect("pedidos");
        } else {
            LOGGER.log(Level.SEVERE, "Error al registrar el pedido.");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al registrar el pedido.");
        }
    }
}