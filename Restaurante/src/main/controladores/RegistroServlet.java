package main.controladores;

import main.dao.UsuarioDAO;
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
        String dni = request.getParameter("dni");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        int codigo = usuarioDAO.insertarUsuario(username, password, rol, dni, telefono, correo);

        if (codigo == 0) {
            response.sendRedirect("registro.jsp?success=true");
        } else if (codigo == 1) {
            response.sendRedirect("registro.jsp?error=dni");
        } else if (codigo == 2) {
            response.sendRedirect("registro.jsp?error=telefono");
        } else if (codigo == 3) {
            response.sendRedirect("registro.jsp?error=correo");
        } else {
            response.sendRedirect("registro.jsp?error=true");
        }
    }
}