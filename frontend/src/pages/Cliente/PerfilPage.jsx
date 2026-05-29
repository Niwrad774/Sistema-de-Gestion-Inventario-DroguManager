import { useContext } from 'react';
import { AuthContext } from '../../context/AuthContext';

const PerfilPage = () => {
  const { usuario } = useContext(AuthContext);

  if (!usuario) return null;

  const roleNames = {
    'COMPRADOR': 'Cliente',
    'GESTOR_PEDIDOS': 'Gestor de Pedidos',
    'ADMIN_INVENTARIO': 'Administrador de Inventario'
  };

  return (
    <div style={{ maxWidth: '600px', margin: '0 auto' }}>
      <h2 style={{ color: 'var(--color-primary)', marginBottom: '1.5rem' }}>Mi Perfil</h2>
      
      <div className="card">
        <div style={{ display: 'flex', alignItems: 'center', marginBottom: '2rem', gap: '1.5rem' }}>
          <div style={{ width: '80px', height: '80px', borderRadius: '50%', backgroundColor: 'var(--color-primary)', color: 'white', display: 'flex', alignItems: 'center', justifyContent: 'center', fontSize: '2rem', fontWeight: 'bold' }}>
            {usuario.nombre.charAt(0).toUpperCase()}
          </div>
          <div>
            <h3 style={{ margin: '0 0 0.25rem 0', fontSize: '1.5rem' }}>{usuario.nombre}</h3>
            <span className="badge" style={{ backgroundColor: '#E0F2FE', color: '#0369A1' }}>
              {roleNames[usuario.rol] || usuario.rol}
            </span>
          </div>
        </div>

        <div style={{ borderTop: '1px solid var(--color-border)', paddingTop: '1.5rem' }}>
          <div style={{ marginBottom: '1rem' }}>
            <label style={{ display: 'block', fontSize: '0.875rem', color: 'var(--color-text-muted)', marginBottom: '0.25rem' }}>ID de Usuario</label>
            <div style={{ fontWeight: '500' }}>#{usuario.id}</div>
          </div>
          
          <div style={{ marginBottom: '1rem' }}>
            <label style={{ display: 'block', fontSize: '0.875rem', color: 'var(--color-text-muted)', marginBottom: '0.25rem' }}>Correo Electrónico</label>
            <div style={{ fontWeight: '500' }}>{usuario.email}</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PerfilPage;
