import { useEffect, useState } from 'react'
import { api } from '../../services/api'

export default function ClientePedidos() {
  const [pedidos, setPedidos] = useState([])

  useEffect(() => {
    api.listarPedidosPropios()
      .then((data) => setPedidos(data || []))
      .catch(() => setPedidos([]))
  }, [])

  return (
    <section>
      <h2>Mis Pedidos</h2>
      {pedidos.length === 0 && <div>No hay pedidos</div>}
      {pedidos.map((p) => (
        <div key={p.id} style={{ border: '1px solid #eee', padding: 8, marginBottom: 8 }}>
          <div><strong>ID:</strong> {p.id}</div>
          <div><strong>Estado:</strong> {p.estado}</div>
          <div><strong>Total:</strong> ${p.total}</div>
        </div>
      ))}
    </section>
  )
}

