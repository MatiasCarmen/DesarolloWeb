<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 22/05/2025
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro - Sabores del Alma</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/main/webapp/css/estilos.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-4">
            <h2 class="text-center">Registrar Nuevo Usuario</h2>
            <% String err = request.getParameter("error");
               if (err != null) {
                   String msg;
                   switch(err) {
                       case "dni": msg = "El DNI ya está registrado."; break;
                       case "telefono": msg = "El teléfono ya está registrado."; break;
                       case "correo": msg = "El correo ya está registrado."; break;
                       default: msg = "Error al registrar usuario. Inténtalo nuevamente.";
                   }
            %>
            <div class="alert alert-danger"><%= msg %></div>
            <% } %>
            <% if (request.getParameter("success") != null) { %>
            <div class="alert alert-success">Usuario registrado exitosamente.</div>
            <% } %>
            <form action="RegistroServlet" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">Usuario</label>
                    <input type="text" class="form-control" name="username" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" name="password" required>
                </div>
                <div class="mb-3">
                    <label for="dni" class="form-label">DNI</label>
                    <input type="text" class="form-control" name="dni" required>
                </div>
                <div class="mb-3">
                    <label for="telefono" class="form-label">Teléfono</label>
                    <input type="text" class="form-control" name="telefono" required>
                </div>
                <div class="mb-3">
                    <label for="correo" class="form-label">Correo</label>
                    <input type="email" class="form-control" name="correo" required>
                </div>
                <div class="mb-3">
                    <label for="rol" class="form-label">Rol</label>
                    <select class="form-control" name="rol" required>
                        <option value="admin">Admin</option>
                        <option value="empleado">Empleado</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary w-100">Registrar</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>