import { useState, useContext } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';
import { registrarUsuario, loginUsuario } from '../services/api';

const RegisterPage = () => {
  const [nombre, setNombre] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [rol, setRol] = useState('COMPRADOR');
  const [error, setError] = useState('');
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    setError('');
    try {
      // Registrar al usuario
      await registrarUsuario(nombre, email, password, rol);
      
      // Auto-login después de registrarse exitosamente
      const loginData = await loginUsuario(email, password);
      login(loginData); // Redirige automáticamente a su panel
    } catch (err) {
      setError(err.message);
    }
  };

  return (
    <div className="flex" style={{ justifyContent: 'center', alignItems: 'center', minHeight: '100vh', backgroundColor: 'var(--color-bg)' }}>
      <div className="card" style={{ maxWidth: '400px', width: '100%' }}>
        <div className="text-center mb-1">
          <h2 style={{ color: 'var(--color-primary)', marginBottom: '0.5rem' }}>Crear Cuenta</h2>
          <p style={{ color: 'var(--color-text-muted)' }}>Únete a DroguManager</p>
        </div>

        {error && (
          <div style={{ backgroundColor: '#FEE2E2', color: '#991B1B', padding: '0.75rem', borderRadius: '0.5rem', marginBottom: '1rem', fontSize: '0.875rem' }}>
            {error}
          </div>
        )}

        <form onSubmit={handleRegister}>
          <div className="form-group">
            <label className="form-label">Nombre Completo</label>
            <input 
              type="text" 
              className="form-control" 
              value={nombre}
              onChange={(e) => setNombre(e.target.value)}
              placeholder="Ej. Juan Pérez"
              required 
            />
          </div>

          <div className="form-group">
            <label className="form-label">Correo Electrónico</label>
            <input 
              type="email" 
              className="form-control" 
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="tu@email.com"
              required 
            />
          </div>
          
          <div className="form-group">
            <label className="form-label">Contraseña</label>
            <div style={{ position: 'relative' }}>
              <input 
                type={showPassword ? "text" : "password"} 
                className="form-control" 
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="••••••••"
                required 
                style={{ paddingRight: '2.5rem' }}
              />
              <button 
                type="button" 
                onClick={() => setShowPassword(!showPassword)}
                style={{ position: 'absolute', right: '0.75rem', top: '50%', transform: 'translateY(-50%)', background: 'none', border: 'none', cursor: 'pointer', color: 'var(--color-text-muted)' }}
              >
                {showPassword ? '👁️‍🗨️' : '👁️'}
              </button>
            </div>
          </div>

          <div className="form-group mb-1">
            <label className="form-label">Rol del Usuario</label>
            <select 
              className="form-control" 
              value={rol}
              onChange={(e) => setRol(e.target.value)}
              style={{ backgroundColor: 'white' }}
            >
              <option value="COMPRADOR">Cliente (Comprador)</option>
              <option value="GESTOR_PEDIDOS">Gestor de Pedidos</option>
              <option value="ADMIN_INVENTARIO">Administrador de Inventario</option>
            </select>
          </div>

          <button type="submit" className="btn btn-primary btn-block mt-1">Registrarse</button>
        </form>

        <div className="text-center mt-2" style={{ fontSize: '0.875rem' }}>
          <span style={{ color: 'var(--color-text-muted)' }}>¿Ya tienes cuenta? </span>
          <Link to="/" style={{ color: 'var(--color-primary)', fontWeight: '600', textDecoration: 'none' }}>
            Inicia sesión aquí
          </Link>
        </div>
      </div>
    </div>
  );
};

export default RegisterPage;
