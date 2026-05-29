import { useState } from 'react'
import { useCart } from '../../context/CartContext'

export default function ClienteCart() {
  const { items, removeFromCart, clearCart } = useCart()
  const [query, setQuery] = useState('')

  const filtered = items.filter((it) => it.nombre.toLowerCase().includes(query.toLowerCase()))

  return (
    <section>
      <h2>Carrito</h2>
      <div style={{ marginBottom: 8 }}>
        <input placeholder="Buscar en carrito" value={query} onChange={(e) => setQuery(e.target.value)} />
      </div>

      {items.length === 0 && <div>Carrito vacío</div>}

      {filtered.map((it) => (
        <div key={it.productoId} style={{ border: '1px solid #eee', padding: 8, marginBottom: 6 }}>
          <div style={{ display: 'flex', justifyContent: 'space-between' }}>
            <div>
              <div style={{ fontWeight: 'bold' }}>{it.nombre}</div>
              <div>Cantidad: {it.cantidad}</div>
            </div>
            <div>
              <div>${it.precio}</div>
              <button onClick={() => removeFromCart(it.productoId)} style={{ marginTop: 8 }}>Quitar</button>
            </div>
          </div>
        </div>
      ))}

      {items.length > 0 && (
        <div style={{ marginTop: 12 }}>
          <button onClick={clearCart}>Vaciar carrito</button>
        </div>
      )}
    </section>
  )
}

