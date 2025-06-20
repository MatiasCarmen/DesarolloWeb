package main.controladores;

import main.dao.MenuDAO;
import main.modelo.Plato;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    private final MenuDAO menuDAO = new MenuDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Plato> platos = menuDAO.listarPlatos();
        request.setAttribute("platos", platos);
        request.getRequestDispatcher("jsp/menu.jsp").forward(request, response);
    }
}