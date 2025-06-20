package main.controladores;

import main.dao.UsuarioDAO;
import main.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        boolean registrado = usuarioDAO.insertarUsuario(username, password, rol);

        if (registrado) {
            response.sendRedirect("registro.jsp?success=true");
        } else {
            response.sendRedirect("registro.jsp?error=true");
        }
    }
}