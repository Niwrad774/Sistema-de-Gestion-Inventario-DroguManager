import { useContext } from 'react';
import { AuthContext } from '../context/AuthContext';
import { useNavigate } from 'react-router-dom';

const Header = () => {
  const { usuario, logout } = useContext(AuthContext);
  const navigate = useNavigate();

  if (!usuario) return null; // No mostrar header si no está logueado

  const handleInicioClick = () => {
    if (usuario.rol === 'COMPRADOR') navigate('/cliente/inicio');
    else if (usuario.rol === 'GESTOR_PEDIDOS') navigate('/gestor/inicio');
    else if (usuario.rol === 'ADMIN_INVENTARIO') navigate('/admin/facturas');
  };

  return (
    <header className="header">
      <div className="header-brand">
        <span>💊 DroguManager</span>
      </div>
      <nav className="header-nav">
        {usuario.rol === 'COMPRADOR' && (
          <>
            <span className="header-link" onClick={handleInicioClick}>Inicio</span>
            <span className="header-link" onClick={() => navigate('/cliente/pedidos')}>Pedidos</span>
            <span className="header-link" onClick={() => navigate('/cliente/carrito')} title="Carrito">🛒</span>
            <span className="header-link" onClick={() => navigate('/cliente/perfil')} title="Perfil">👤</span>
          </>
        )}
        
        {usuario.rol === 'GESTOR_PEDIDOS' && (
          <>
            <span className="header-link" onClick={handleInicioClick}>Inicio</span>
            <span className="header-link" onClick={() => navigate('/gestor/facturas')}>Facturas</span>
          </>
        )}

        {usuario.rol === 'ADMIN_INVENTARIO' && (
          <>
            <span className="header-link" onClick={handleInicioClick}>Inicio</span>
            <div className="dropdown">
              <span className="header-link" style={{ display: 'flex', alignItems: 'center', gap: '0.25rem' }}>
                Inventario <span style={{ fontSize: '0.7em' }}>▼</span>
              </span>
              <div className="dropdown-content">
                <span onClick={() => navigate('/admin/agregar-producto')}>Agregar producto al inventario</span>
              </div>
            </div>
          </>
        )}

        <button className="btn btn-primary" style={{ backgroundColor: '#115E59', padding: '0.5rem 1rem' }} onClick={logout}>
          Salir
        </button>
      </nav>
    </header>
  );
};

export default Header;
