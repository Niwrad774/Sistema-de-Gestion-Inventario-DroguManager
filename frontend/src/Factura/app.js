const API_BASE = 'http://localhost:8080/api/facturas';

// Captura de elementos del HTML
const pedidoIdInput = document.getElementById('pedidoIdInput');
const btnBuscar = document.getElementById('btnBuscar');
const btnGenerar = document.getElementById('btnGenerar');
const errorContainer = document.getElementById('errorContainer');
const errorMessage = document.getElementById('errorMessage');
const facturaContainer = document.getElementById('facturaContainer');

// Escuchadores de eventos (Clicks)
btnBuscar.addEventListener('click', () => realizarPeticion('buscar'));
btnGenerar.addEventListener('click', () => realizarPeticion('generar'));

// Función principal para conectar con el Backend
async function realizarPeticion(accion) {
    const pedidoId = pedidoIdInput.value;
    if (!pedidoId) {
        alert('Por favor, ingresa un ID de Pedido');
        return;
    }

    // Resetear estados visuales
    ocultarErrores();
    facturaContainer.classList.add('hidden');
    
    // Feedback visual de carga
    const botonOriginalText = accion === 'generar' ? btnGenerar.innerText : btnBuscar.innerText;
    if (accion === 'generar') btnGenerar.innerText = 'Procesando...';
    
    try {
        let response;
        if (accion === 'generar') {
            // POST /api/facturas/generar/{pedidoId}
            response = await fetch(`${API_BASE}/generar/${pedidoId}`, { method: 'POST' });
        } else {
            // GET /api/facturas/pedido/{pedidoId}
            response = await fetch(`${API_BASE}/pedido/${pedidoId}`);
        }

        if (response.status === 404) throw new Error('No se encontró información para el ID solicitado.');
        if (!response.ok) throw new Error('Ocurrió un error en el servidor al procesar la solicitud.');

        const facturaDto = await response.json();
        pitarFacturaEnPantalla(facturaDto);

    } catch (error) {
        mostrarError(error.message);
    } finally {
        // Restaurar texto del botón
        if (accion === 'generar') btnGenerar.innerText = botonOriginalText;
    }
}

// Función para mapear el FacturaResponseDto al HTML
function pitarFacturaEnPantalla(factura) {
    document.getElementById('facturaId').innerText = factura.facturaId;
    document.getElementById('facturaPedidoId').innerText = factura.pedidoId;
    document.getElementById('facturaClienteId').innerText = factura.clienteId;
    document.getElementById('facturaFecha').innerText = new Date(factura.fechaEmision).toLocaleString();
    document.getElementById('facturaTotal').innerText = factura.totalFacturado.toFixed(2);
    document.getElementById('facturaRuta').innerText = factura.rutaArchivo;

    // Control del Badge de Estado
    const estadoBadge = document.getElementById('facturaEstado');
    estadoBadge.innerText = factura.estadoPedido;
    if (factura.estadoPedido === 'COMPLETADO') {
        estadoBadge.className = "px-3 py-1 rounded-full text-xs font-bold bg-green-100 text-green-800";
    } else {
        estadoBadge.className = "px-3 py-1 rounded-full text-xs font-bold bg-yellow-100 text-yellow-800";
    }

    // Renderizar la lista de ítems en la tabla
    const tablaBody = document.getElementById('tablaItemsBody');
    tablaBody.innerHTML = ''; // Limpiar filas anteriores

    factura.itemsPedido.forEach(item => {
        const fila = document.createElement('tr');
        fila.className = "border-b";
        fila.innerHTML = `
            <td class="p-2 font-medium">${item.nombreProducto} <span class="text-xs text-gray-400">(ID: ${item.productoId})</span></td>
            <td class="p-2 text-center">${item.cantidad}</td>
            <td class="p-2 text-right">$${item.precioUnitario.toFixed(2)}</td>
            <td class="p-2 text-right font-semibold">$${item.subtotal.toFixed(2)}</td>
        `;
        tablaBody.appendChild(fila);
    });

    // Mostrar el contenedor de la factura quitando la clase 'hidden'
    facturaContainer.classList.remove('hidden');
}

function mostrarError(mensaje) {
    errorMessage.innerText = mensaje;
    errorContainer.classList.remove('hidden');
}

function ocultarErrores() {
    errorContainer.classList.add('hidden');
}