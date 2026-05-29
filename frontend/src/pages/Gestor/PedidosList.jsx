import { useState } from 'react'
import { Link } from 'react-router-dom'
import { api } from '../../services/api'

export default function GestorPedidos() {
  // Nota: el backend del proyecto académico no expone un endpoint para listar todos los pedidos.
  // Por eso aquí ofrecemos una búsqueda por ID: el gestor introduce un ID y puede "Controlar" ese pedido.
  const [query, setQuery] = useState('')
  const [pedidoEncontrado, setPedidoEncontrado] = useState(null)
  const [error, setError] = useState(null)

  function buscar() {
    setError(null)
    if (!query) return
    api.obtenerPedido(query)
      .then((data) => setPedidoEncontrado(data))
      .catch((err) => {
        setPedidoEncontrado(null)
        setError(err.message || 'No encontrado')
      })
  }

  return (
    <section>
      <h2>Pedidos (búsqueda por ID)</h2>
      <div style={{ marginBottom: 8 }}>
        <input placeholder="Ingrese ID de pedido" value={query} onChange={(e) => setQuery(e.target.value)} />
        <button onClick={buscar} style={{ marginLeft: 8 }}>Buscar</button>
      </div>

      {error && <div style={{ color: 'red' }}>{error}</div>}

      {pedidoEncontrado && (
        <div style={{ border: '1px solid #ddd', padding: 12, maxWidth: 560 }}>
          <div><strong>ID:</strong> {pedidoEncontrado.id}</div>
          <div><strong>Cliente:</strong> {pedidoEncontrado.cliente?.nombre || pedidoEncontrado.clienteId}</div>
          <div><strong>Estado:</strong> {pedidoEncontrado.estado}</div>
          <div style={{ marginTop: 8 }}>
            <Link to={`/gestor/pedidos/${pedidoEncontrado.id}`}><button>Controlar</button></Link>
          </div>
        </div>
      )}

      <div style={{ marginTop: 12 }}>
        <div style={{ fontSize: 13, color: '#666' }}>
          Nota: para listar todos los pedidos sería necesario agregar un endpoint GET /api/pedidos en el backend.
        </div>
      </div>
    </section>
  )
}


