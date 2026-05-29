import { createContext, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  // Inicialización síncrona: previene que el usuario sea null por una fracción de segundo al recargar (F5)
  const [usuario, setUsuario] = useState(() => {
    const storedUser = sessionStorage.getItem('usuario');
    return storedUser ? JSON.parse(storedUser) : null;
  });
  const navigate = useNavigate();

  // El useEffect ya no es necesario aquí porque el estado inicial se encarga de restaurar la sesión

  const login = (userData) => {
    setUsuario(userData);
    sessionStorage.setItem('usuario', JSON.stringify(userData));
    // Redirigir según el rol
    if (userData.rol === 'COMPRADOR') navigate('/cliente/inicio');
    else if (userData.rol === 'GESTOR_PEDIDOS') navigate('/gestor/inicio');
    else if (userData.rol === 'ADMIN_INVENTARIO') navigate('/admin/agregar-producto');
    else navigate('/');
  };

  const logout = () => {
    setUsuario(null);
    sessionStorage.removeItem('usuario');
    navigate('/');
  };

  return (
    <AuthContext.Provider value={{ usuario, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
