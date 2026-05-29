import { useEffect, useState } from 'react'
import { api } from '../../services/api'
import { Link } from 'react-router-dom'

export default function AdminFacturas() {
  const [facturas, setFacturas] = useState([])

  useEffect(() => {
    api.listarFacturas().then((data) => setFacturas(data || [])).catch(() => setFacturas([]))
  }, [])

  return (
    <section>
      <h2>Facturas (administrador)</h2>
      {facturas.length === 0 && <div>No hay facturas</div>}
      {facturas.map((f) => (
        <div key={f.id} style={{ border: '1px solid #eee', padding: 8, marginBottom: 8 }}>
          <div><strong>ID:</strong> {f.id}</div>
          <div><strong>Pedido:</strong> {f.pedidoId}</div>
          <div><strong>Total:</strong> ${f.total}</div>
          <div style={{ marginTop: 6 }}><Link to={`/admin/facturas/${f.id}`}><button>Consultar</button></Link></div>
        </div>
      ))}
    </section>
  )
}

