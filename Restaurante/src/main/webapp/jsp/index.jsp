<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Sabores del Alma</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/main/webapp/css/estilos.css">

</head>
<body class="bg-light">
<div class="container mt-4">
    <header class="text-center mb-4">
        <h1 class="display-4">Sabores del Alma</h1>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.jsp">Inicio</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item"><a class="nav-link active" href="index.jsp">Inicio</a></li>
                        <li class="nav-item"><a class="nav-link" href="../menu.jsp">Menú</a></li>
                        <li class="nav-item"><a class="nav-link" href="reservas.jsp">Reservas</a></li>
                        <li class="nav-item"><a class="nav-link" href="../pedidos.jsp">Pedidos</a></li>
                        <li class="nav-item"><a class="nav-link" href="../login.jsp">Admin</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>

    <section class="bienvenida text-center">
        <h2>Bienvenidos a Sabores del Alma</h2>
        <p class="lead">Una experiencia culinaria que mezcla la tradición piurana con lo mejor del Mediterráneo.</p>
        <img src="img/restaurante.webp" class="img-fluid rounded shadow" alt="Restaurante Sabores del Alma">
    </section>

    <footer class="text-center mt-4">
        <p>&copy; 2025 Sabores del Alma | Todos los derechos reservados</p>
    </footer>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>