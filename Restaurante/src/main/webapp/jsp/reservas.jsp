<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="main.modelo.Reserva" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Reservas - Sabores del Alma</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

    <script src="../scripts.js" defer></script>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/main/webapp/css/estilos.css">
</head>
<body class="bg-body-secondary">
<div class="container mt-4 mb-5">
    <!-- Encabezado -->
    <header class="text-center mb-4">
        <h1 class="display-3 fw-bold text-primary mb-2">Sabores del Alma <i class="bi bi-emoji-sunglasses"></i></h1>
        <p class="lead text-secondary">¡Reserva tu mesa y disfruta de la mejor gastronomía!</p>
        <!-- NavBar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow rounded mb-4">
            <div class="container-fluid">
                <a class="navbar-brand" href="#"><i class="bi bi-house-door-fill"></i> Inicio</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item"><a class="nav-link" href="../menu.jsp"><i class="bi bi-journal"></i> Menú</a></li>
                        <li class="nav-item"><a class="nav-link active" href="reservas.jsp"><i class="bi bi-calendar-check"></i> Reservas</a></li>
                        <li class="nav-item"><a class="nav-link" href="../pedidos.jsp"><i class="bi bi-basket2"></i> Pedidos</a></li>
                        <li class="nav-item"><a class="nav-link" href="../login.jsp"><i class="bi bi-gear-fill"></i> Admin</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>

    <!-- Sección Principal -->
    <section class="reserva bg-white p-4 rounded shadow-sm">
        <h2 class="text-center mb-4 text-primary">Reservas actuales</h2>
        <% String mensajeExito = (String) request.getAttribute("mensajeExito"); %>
        <% if (mensajeExito != null) { %>
            <div class="alert alert-success text-center fw-bold"><%= mensajeExito %></div>
        <% } %>

        <!-- Tabla de Reservas -->
        <div class="table-responsive">
            <table class="table table-striped table-hover table-bordered align-middle shadow-sm">
                <thead class="table-primary text-center">
                <tr>
                    <th>ID</th>
                    <th>Cliente</th>
                    <th>Fecha</th>
                    <th>Hora</th>
                    <th>Mesa</th>
                    <th>Estado</th>
                </tr>
                </thead>
                <tbody class="text-center">
                <%
                    List<Reserva> reservas = (List<Reserva>) request.getAttribute("reservas");
                    if (reservas != null) {
                        for (Reserva reserva : reservas) {
                %>
                <tr>
                    <td><%= reserva.getId() %></td>
                    <td><i class="bi bi-person-circle me-1"></i><%= reserva.getNombreCliente() %></td>
                    <td><i class="bi bi-calendar-event me-1"></i><%= reserva.getFecha() %></td>
                    <td><i class="bi bi-clock me-1"></i><%= reserva.getHora() %></td>
                    <td><i class="bi bi-people me-1"></i><%= reserva.getNumeroMesa() %></td>
                    <td>
                        <% String estado = reserva.getEstado(); %>
                        <span class="badge
                            <%= "Confirmada".equals(estado) ? "bg-success" :
                                "Cancelada".equals(estado) ? "bg-danger" : "bg-warning text-dark" %>">
                            <%= estado %>
                        </span>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>

        <!-- Formulario para Nuevas Reservas -->
        <div class="card shadow-lg mt-5 border-primary">
            <div class="card-body bg-light">
                <h3 class="card-title text-center text-primary mb-4">Nueva Reserva <i class="bi bi-plus-circle"></i></h3>
                <form action="reservas" method="post" class="row g-3">
                    <div class="col-md-6">
                        <label class="form-label"><i class="bi bi-person-fill me-1"></i> Nombre completo:</label>
                        <input type="text" class="form-control" name="nombreCliente" required>
                    </div>
                    <div class="col-md-3">
                        <label class="form-label"><i class="bi bi-calendar2-date me-1"></i> Fecha:</label>
                        <input type="date" class="form-control" name="fecha" required>
                    </div>
                    <div class="col-md-3">
                        <label class="form-label"><i class="bi bi-clock-fill me-1"></i> Hora:</label>
                        <input type="time" class="form-control" name="hora" required>
                    </div>
                    <div class="col-md-4">
                        <label class="form-label"><i class="bi bi-people-fill me-1"></i> Número de personas:</label>
                        <input type="number" min="1" class="form-control" name="numeroMesa" required>
                    </div>
                    <div class="col-md-4">
                        <label class="form-label"><i class="bi bi-list-task me-1"></i> Estado:</label>
                        <select class="form-select" name="estado">
                            <option value="Pendiente">Pendiente</option>
                            <option value="Confirmada">Confirmada</option>
                            <option value="Cancelada">Cancelada</option>
                        </select>
                    </div>
                    <div class="col-12 text-center mt-3">
                        <button type="submit" class="btn btn-success btn-lg">
                            <i class="bi bi-check2-circle me-1"></i> Reservar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer class="text-center mt-5 py-4 bg-primary text-white rounded shadow-sm">
        <p class="mb-0">&copy; 2025 Sabores del Alma | ¡Gracias por elegirnos!</p>
    </footer>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>