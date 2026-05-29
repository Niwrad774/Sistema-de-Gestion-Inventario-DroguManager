import { useAuth } from '../../context/AuthContext'

export default function ClientePerfil() {
  const { user } = useAuth()

  if (!user) return <div>No hay usuario autenticado</div>

  return (
    <section>
      <h2>Perfil</h2>
      <div><strong>Nombre:</strong> {user.nombre}</div>
      <div><strong>Email:</strong> {user.email}</div>
      <div><strong>Rol:</strong> {user.rol}</div>
    </section>
  )
}

