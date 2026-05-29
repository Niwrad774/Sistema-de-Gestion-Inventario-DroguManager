import { useEffect, useState } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { api } from '../../services/api'

export default function ControlPedido() {
  const { id } = useParams()
  const navigate = useNavigate()
  const [pedido, setPedido] = useState(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    api.obtenerPedido(id)
      .then((data) => setPedido(data))
      .catch(() => setPedido(null))
      .finally(() => setLoading(false))
  }, [id])

  function marcarCompleto() {
    api.controlarPedido(id, { accion: 'COMPLETAR' })
      .then(() => {
        // navegar a facturas o refrescar
        navigate('/gestor/facturas')
      })
      .catch((err) => alert('Error: ' + err.message))
  }

  if (loading) return <div>Cargando pedido...</div>
  if (!pedido) return <div>Pedido no encontrado</div>

  return (
    <section>
      <h2>Controlar Pedido #{pedido.id}</h2>
      <div><strong>Cliente:</strong> {pedido.cliente?.nombre} {pedido.cliente?.apellido} - {pedido.cliente?.email}</div>
      <div><strong>Estado:</strong> {pedido.estado}</div>
      <h3>Items</h3>
      <ul>
        {pedido.items?.map((it) => (
          <li key={it.productoId}>{it.nombre} x{it.cantidad} - ${it.precio}</li>
        ))}
      </ul>

      <div style={{ marginTop: 12 }}>
        <button onClick={marcarCompleto}>Marcar como COMPLETADO y generar factura</button>
      </div>
    </section>
  )
}

