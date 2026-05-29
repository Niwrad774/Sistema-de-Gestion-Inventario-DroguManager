import { useEffect, useState } from 'react'
import { useCart } from '../../context/CartContext'
import { api } from '../../services/api'

export default function ClienteHome() {
  const [productos, setProductos] = useState([])
  const [loading, setLoading] = useState(true)
  const { addToCart } = useCart()

  useEffect(() => {
    let mounted = true
    api.listarProductos()
      .then((data) => {
        if (mounted) setProductos(data || [])
      })
      .catch(() => setProductos([]))
      .finally(() => setLoading(false))
    return () => (mounted = false)
  }, [])

  if (loading) return <div>Cargando productos...</div>

  return (
    <section>
      <h2>Catálogo</h2>
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill,minmax(220px,1fr))', gap: 12 }}>
        {productos.length === 0 && <div>No hay productos</div>}
        {productos.map((p) => (
          <article key={p.id} style={{ border: '1px solid #ddd', padding: 12 }}>
            {/* Espacio reservado para imagen: colocar <img src={p.imagen} /> si existe */}
            <div style={{ height: 120, background: '#f6f6f6', marginBottom: 8, display: 'flex', alignItems: 'center', justifyContent: 'center' }}>[imagen]</div>
            <h3 style={{ margin: '4px 0' }}>{p.nombre || p.descripcion || 'Producto'}</h3>
            <div>Precio: ${p.precio || 0}</div>
            <button style={{ marginTop: 8 }} onClick={() => addToCart({ productoId: p.id, nombre: p.nombre, precio: p.precio })}>Agregar al carrito</button>
          </article>
        ))}
      </div>
    </section>
  )
}

