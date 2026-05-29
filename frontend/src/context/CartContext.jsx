import { createContext, useContext, useState } from 'react'

const CartContext = createContext()

export function CartProvider({ children }) {
  const [items, setItems] = useState([]) // { productoId, nombre, precio, cantidad }

  function addToCart(product) {
    setItems((prev) => {
      const found = prev.find((p) => p.productoId === product.productoId)
      if (found) {
        return prev.map((p) => p.productoId === product.productoId ? { ...p, cantidad: p.cantidad + 1 } : p)
      }
      return [...prev, { ...product, cantidad: 1 }]
    })
  }

  function removeFromCart(productoId) {
    setItems((prev) => prev.filter((p) => p.productoId !== productoId))
  }

  function clearCart() {
    setItems([])
  }

  return (
    <CartContext.Provider value={{ items, addToCart, removeFromCart, clearCart }}>
      {children}
    </CartContext.Provider>
  )
}

export function useCart() {
  return useContext(CartContext)
}

export default CartContext

