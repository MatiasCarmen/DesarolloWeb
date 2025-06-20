// pedidos.js
// Gestor de pedidos para la página pedidos.jsp

let pedidoActual = [];

function agregarPedido(nombre, precio) {
    pedidoActual.push({ nombre, precio });
    actualizarVista();
}

function eliminarItem(index) {
    pedidoActual.splice(index, 1);
    actualizarVista();
}

function actualizarVista() {
    const lista = document.getElementById('lista-pedido');
    const totalElem = document.getElementById('total');
    lista.innerHTML = '';

    let total = 0;
    pedidoActual.forEach((item, idx) => {
        total += item.precio;
        const li = document.createElement('li');
        li.className = 'list-group-item d-flex justify-content-between align-items-center';
        li.textContent = `${item.nombre} - S/. ${item.precio.toFixed(2)}`;

        const btn = document.createElement('button');
        btn.className = 'btn btn-sm btn-danger ms-2';
        btn.textContent = 'Eliminar';
        btn.onclick = () => eliminarItem(idx);

        li.appendChild(btn);
        lista.appendChild(li);
    });

    totalElem.textContent = total.toFixed(2);
}

function enviarPedido() {
    const mensaje = document.getElementById('mensaje-envio');
    if (pedidoActual.length === 0) {
        mensaje.textContent = 'Agrega productos antes de enviar el pedido.';
        return;
    }

    mensaje.textContent = 'Procesando pedido...';
    setTimeout(() => {
        mensaje.textContent = '¡Pedido enviado correctamente!';
        pedidoActual = [];
        actualizarVista();
    }, 350);
}
