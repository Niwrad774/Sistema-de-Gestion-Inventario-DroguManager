import { useState } from 'react'
import { api } from '../../services/api'
import { Link } from 'react-router-dom'

export default function GestorFacturas() {
  const [pedidoId, setPedidoId] = useState('')
  const [factura, setFactura] = useState(null)
  const [error, setError] = useState(null)

  function buscarPorPedido() {
    setError(null)
    api.obtenerFacturaPorPedido(pedidoId)
      .then((data) => setFactura(data))
      .catch((err) => {
        setFactura(null)
        setError(err.message || 'No encontrada')
      })
  }

  return (
    <section>
      <h2>Facturas (buscar por Pedido ID)</h2>
      <div style={{ marginBottom: 8 }}>
        <input placeholder="Pedido ID" value={pedidoId} onChange={(e) => setPedidoId(e.target.value)} />
        <button onClick={buscarPorPedido} style={{ marginLeft: 8 }}>Buscar</button>
      </div>

      {error && <div style={{ color: 'red' }}>{error}</div>}

      {factura && (
        <div style={{ border: '1px solid #eee', padding: 8 }}>
          <div><strong>ID:</strong> {factura.id}</div>
          <div><strong>Pedido:</strong> {factura.pedidoId}</div>
          <div><strong>Total:</strong> ${factura.total}</div>
          <div style={{ marginTop: 6 }}><Link to={`/admin/facturas/${factura.id}`}><button>Consultar</button></Link></div>
        </div>
      )}

      <div style={{ marginTop: 12 }}>
        <div style={{ fontSize: 13, color: '#666' }}>
          Nota: para listar todas las facturas sería necesario añadir un endpoint GET /api/facturas en el backend.
        </div>
      </div>
    </section>
  )
}


