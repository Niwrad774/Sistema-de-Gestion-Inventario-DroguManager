import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { useAuth } from '../../context/AuthContext'

export default function Login() {
  const { loginAs } = useAuth()
  const navigate = useNavigate()
  const [email, setEmail] = useState('')
  const [nombre, setNombre] = useState('')
  const [rol, setRol] = useState('CLIENTE')

  function handleSubmit(e) {
    e.preventDefault()
    // Simulamos inicio de sesión
    const user = { id: Date.now(), nombre: nombre || 'Usuario', email: email || 'demo@example.com', rol }
    loginAs(user)
    // redirigir según rol
    if (rol === 'CLIENTE') navigate('/cliente')
    else if (rol === 'GESTOR_PEDIDOS') navigate('/gestor')
    else if (rol === 'ADMIN_INVENTARIO') navigate('/admin')
    else navigate('/cliente')
  }

  return (
    <div className="auth-page">
      <div className="card auth-card">
        <h2>Iniciar sesión</h2>
        <form onSubmit={handleSubmit} className="form">
          <label>
            Nombre
            <input value={nombre} onChange={(e) => setNombre(e.target.value)} placeholder="Ej. Juan Pérez" />
          </label>

          <label>
            Correo electrónico
            <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="ejemplo@dominio.com" />
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
            <button type="submit" className="btn btn-primary">Entrar</button>
            <Link to="/registro" className="btn btn-ghost">¿No tienes cuenta? Regístrate</Link>
          </div>
        </form>
      </div>
    </div>
  )
}


