package entity;


import entity.Producto;
import service.IVA;

import java.math.BigDecimal;

public class ProductoDeAlmacen extends Producto implements IVA {
        private int stock;

        public ProductoDeAlmacen(String nombre, BigDecimal precio, int stock) {
                super(nombre, precio);
                this.stock = stock;
        }

        public int getStock() {
                return stock;
        }

        public void setStock(int stock) {
                this.stock = stock;
        }

        @Override
        public BigDecimal calcularIVA() {
                // Supongamos un IVA del 21%
                BigDecimal iva = getPrecio().multiply(new BigDecimal("0.21"));
                return iva;
        }

        @Override
        public String toString() {
                return super.toString() + ", Stock: " + stock;
        }
}
