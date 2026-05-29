import { useContext } from 'react';
import { Navigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';

const ProtectedRoute = ({ children, allowedRoles }) => {
  const { usuario } = useContext(AuthContext);

  if (!usuario) {
    return <Navigate to="/" replace />;
  }

  if (allowedRoles && !allowedRoles.includes(usuario.rol)) {
    // Si está logueado pero no tiene el rol correcto, enviarlo a su inicio respectivo
    if (usuario.rol === 'COMPRADOR') return <Navigate to="/cliente/inicio" replace />;
    if (usuario.rol === 'GESTOR_PEDIDOS') return <Navigate to="/gestor/inicio" replace />;
    if (usuario.rol === 'ADMIN_INVENTARIO') return <Navigate to="/admin/agregar-producto" replace />;
  }

  return children;
};

export default ProtectedRoute;
