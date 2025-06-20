<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 22/05/2025
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="main.modelo.Plato" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Menú - Sabores del Alma</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
</head>
<body class="bg-light">
<div class="container mt-4">
    <header class="text-center mb-4">
        <h1 class="display-4">Menú - Sabores del Alma</h1>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.jsp">Inicio</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item"><a class="nav-link" href="index.jsp">Inicio</a></li>
                        <li class="nav-item"><a class="nav-link active" href="menu.jsp">Menú</a></li>
                        <li class="nav-item"><a class="nav-link" href="reservas.jsp">Reservas</a></li>
                        <li class="nav-item"><a class="nav-link" href="../pedidos.jsp">Pedidos</a></li>
                        <li class="nav-item"><a class="nav-link" href="login.jsp">Admin</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>

    <section class="menu">
        <h2 class="text-center">Nuestro Menú</h2>

        <!-- Lista de Platos desde la Base de Datos -->
        <div class="row">
            <%
                List<Plato> platos = (List<Plato>) request.getAttribute("platos");
                if (platos != null && !platos.isEmpty()) {
                    for (Plato plato : platos) {
            %>
            <div class="col-md-4 mb-4">
                <div class="card text-center p-3 shadow-sm">
                    <!-- Si la imagen no existe, usa una imagen por defecto -->
                    <img src="<%= request.getContextPath() %>/img/<%= plato.getImagen() != null ? plato.getImagen() : "placeholder.jpg" %>"
                         alt="<%= plato.getNombre() %>" class="img-fluid rounded">
                    <h3><%= plato.getNombre() %></h3>
                    <p><%= plato.getDescripcion() %></p>
                    <span class="fw-bold">S/. <%= plato.getPrecio() %></span>
                </div>
            </div>
            <%
                }
            } else {
            %>
            <!-- Mensaje si no hay platos disponibles -->
            <div class="col-12 text-center">
                <p class="text-muted">No hay platos disponibles en este momento.</p>
            </div>
            <% } %>
        </div>
    </section>

    <footer class="text-center mt-4">
        <p>&copy; 2025 Sabores del Alma</p>
    </footer>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>