import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../../context/AuthContext'

export default function Register() {
  const { register } = useAuth()
  const navigate = useNavigate()
  const [nombre, setNombre] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [rol, setRol] = useState('CLIENTE')
  const [error, setError] = useState(null)

  async function handleSubmit(e) {
    e.preventDefault()
    const res = register({ nombre, email, rol, password })
    if (!res.ok) {
      setError(res.message || 'Error')
      return
    }
    // al registrarse simulamos inicio y redirigimos por rol
    if (rol === 'CLIENTE') navigate('/cliente')
    else if (rol === 'GESTOR_PEDIDOS') navigate('/gestor')
    else if (rol === 'ADMIN_INVENTARIO') navigate('/admin')
    else navigate('/')
  }

  return (
    <div className="auth-page">
      <div className="card auth-card">
        <h2>Crear cuenta</h2>
        {error && <div className="alert">{error}</div>}
        <form onSubmit={handleSubmit} className="form">
          <label>
            Nombre completo
            <input value={nombre} onChange={(e) => setNombre(e.target.value)} placeholder="Ej. María López" />
          </label>

          <label>
            Correo electrónico
            <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="ejemplo@dominio.com" />
          </label>

          <label>
            Contraseña
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="********" />
          </label>

          <label>
            Rol
            <select value={rol} onChange={(e) => setRol(e.target.value)}>
              <option value="CLIENTE">CLIENTE</option>
              <option value="GESTOR_PEDIDOS">GESTOR_PEDIDOS</option>
              <option value="ADMIN_INVENTARIO">ADMIN_INVENTARIO</option>
            </select>
          </label>

          <div style={{ display: 'flex', gap: 8, marginTop: 12 }}>
            <button type="submit" className="btn btn-primary">Crear cuenta</button>
            <button type="button" className="btn btn-ghost" onClick={() => navigate('/')}>Volver al login</button>
          </div>
        </form>
      </div>
    </div>
  )
}


