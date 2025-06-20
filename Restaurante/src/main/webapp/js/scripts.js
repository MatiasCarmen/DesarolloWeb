// scripts.js
document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form[action="reservas"]');
    if (form) {
        form.addEventListener('submit', function (e) {
            // Puedes personalizar esto según el flujo de tu backend
            // Aquí solo mostramos el mensaje después de enviar el formulario
            setTimeout(() => {
                alert("¡Reserva enviada correctamente!");
            }, 350);
        });
    }
});