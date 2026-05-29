import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { api } from '../../services/api'

export default function FacturaDetalle() {
  const { id } = useParams()
  const [factura, setFactura] = useState(null)

  useEffect(() => {
    api.obtenerFactura(id).then((data) => setFactura(data)).catch(() => setFactura(null))
  }, [id])

  if (!factura) return <div>Factura no encontrada</div>

  return (
    <section>
      <h2>Factura #{factura.id}</h2>
      <pre style={{ whiteSpace: 'pre-wrap', background: '#fafafa', padding: 12 }}>{JSON.stringify(factura, null, 2)}</pre>
    </section>
  )
}

