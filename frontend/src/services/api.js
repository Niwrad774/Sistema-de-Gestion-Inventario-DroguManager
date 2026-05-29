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
