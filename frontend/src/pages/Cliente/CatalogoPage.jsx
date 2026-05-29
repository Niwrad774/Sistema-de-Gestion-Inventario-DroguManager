import { useState, useEffect } from 'react';
import { obtenerProductos } from '../../services/api';

const CatalogoPage = () => {
  const [productos, setProductos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProductos = async () => {
      try {
        const data = await obtenerProductos();
        setProductos(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchProductos();
  }, []);

  if (loading) return <div className="text-center mt-2">Cargando productos...</div>;
  if (error) return <div className="text-center mt-2" style={{color: 'red'}}>{error}</div>;

  return (
    <div>
      <h2 style={{ color: 'var(--color-primary)', marginBottom: '1.5rem' }}>Catálogo de Productos</h2>
      
      {productos.length === 0 ? (
        <p>No hay productos disponibles en el inventario.</p>
      ) : (
        <div className="grid grid-cols-3 gap-1">
          {productos.map(producto => (
            <div key={producto.id} className="card" style={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
              <h3 style={{ margin: '0 0 0.5rem 0', color: 'var(--color-text-main)' }}>{producto.nombre}</h3>
              <p style={{ margin: '0 0 1rem 0', fontSize: '1.25rem', fontWeight: 'bold', color: 'var(--color-primary)' }}>
                ${producto.precio.toFixed(2)}
              </p>
              
              <div style={{ marginTop: 'auto' }}>
                <p style={{ fontSize: '0.875rem', color: 'var(--color-text-muted)', marginBottom: '1rem' }}>
                  Stock disponible: <span style={{ fontWeight: '600', color: producto.cantidadStock > 0 ? 'inherit' : 'red' }}>{producto.cantidadStock}</span>
                </p>
                <button 
                  className="btn btn-primary btn-block" 
                  disabled={producto.cantidadStock <= 0}
                  style={{ opacity: producto.cantidadStock <= 0 ? 0.5 : 1 }}
                >
                  {producto.cantidadStock > 0 ? 'Añadir al Carrito' : 'Agotado'}
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default CatalogoPage;
