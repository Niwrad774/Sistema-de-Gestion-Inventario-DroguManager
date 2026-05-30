const API_BASE = '/api';

export const loginUsuario = async (email, password) => {
  const response = await fetch(`${API_BASE}/usuarios/login`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, password })
  });
  if (!response.ok) throw new Error('Credenciales incorrectas');
  return response.json();
};

export const registrarUsuario = async (nombre, email, password, rol) => {
  const response = await fetch(`${API_BASE}/usuarios/registro`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ nombre, email, password, rol })
  });
  if (!response.ok) {
    let errorText = 'Error al registrar usuario';
    try {
      const errJson = await response.json();
      errorText = errJson.message || errorText;
    } catch (e) {
      errorText = await response.text();
    }
    throw new Error(errorText);
  }
  return response.json();
};

export const obtenerProductos = async () => {
  const response = await fetch(`${API_BASE}/productos`);
  if (!response.ok) throw new Error('Error al obtener los productos');
  return response.json();
};

export const listarFacturas = async () => {
  const response = await fetch(`${API_BASE}/facturas`);
  if (!response.ok) throw new Error('Error al listar facturas');
  return response.json();
};

export const obtenerFactura = async (facturaId) => {
  const response = await fetch(`${API_BASE}/facturas/${facturaId}`);
  if (!response.ok) throw new Error('Factura no encontrada');
  return response.json();
};

export const obtenerFacturaPorPedido = async (pedidoId) => {
  const response = await fetch(`${API_BASE}/facturas/pedido/${pedidoId}`);
  if (!response.ok) throw new Error('Factura no encontrada para el pedido');
  return response.json();
};

export const generarFactura = async (pedidoId) => {
  const response = await fetch(`${API_BASE}/facturas/generar/${pedidoId}`, {
    method: 'POST'
  });
  if (!response.ok) throw new Error('No se pudo generar la factura');
  return response.json();
};
