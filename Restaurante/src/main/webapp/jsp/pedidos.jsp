<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 22/05/2025
  Time: 05:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="main.modelo.Pedido" %>
<%@ page import="main.modelo.Plato" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Pedidos - Sabores del Alma</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
    <script src="<%= request.getContextPath() %>/js/pedidos.js" defer></script>
</head>
<body class="bg-light">
<div class="container mt-4">
    <header class="text-center mb-4">
        <h1 class="display-4">Pedidos - Sabores del Alma</h1>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.jsp">Inicio</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item"><a class="nav-link" href="menu.jsp">Menú</a></li>
                        <li class="nav-item"><a class="nav-link" href="reservas.jsp">Reservas</a></li>
                        <li class="nav-item"><a class="nav-link active" href="pedidos.jsp">Pedidos</a></li>
                        <li class="nav-item"><a class="nav-link" href="login.jsp">Admin</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>

    <section class="pedido">
        <h2 class="text-center">Realiza tu pedido</h2>

        <!-- Lista de Platos -->
        <div class="row">
            <%
    List<Plato> platos = (List<Plato>) request.getAttribute("platos");
    if (platos != null) {
        for (Plato plato : platos) {
%>
    <div class="col-md-4">
        <div class="card text-center p-3 shadow-sm">
            <h3><%= plato.getNombre() %></h3>
            <p>S/. <%= plato.getPrecio() %></p>
            <button class="btn btn-success" onclick="agregarPedido('<%= plato.getNombre() %>', <%= plato.getPrecio() %>)">Agregar</button>
        </div>
    </div>
<%
        }
    }
%>
        </div>

        <h3 class="mt-4">Pedido actual:</h3>
        <ul id="lista-pedido" class="list-group"></ul>
        <p class="mt-2"><strong>Total: S/. <span id="total">0</span></strong></p>
        <button class="btn btn-primary mt-2" onclick="enviarPedido()">Enviar Pedido</button>
        <p id="mensaje-envio" class="confirmacion mt-3"></p>
    </section>

    <footer class="text-center mt-4">
        <p>&copy; 2025 Sabores del Alma</p>
    </footer>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>