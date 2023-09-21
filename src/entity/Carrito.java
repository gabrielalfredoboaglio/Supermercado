package entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<ProductoDeAlmacen> productos = new ArrayList<>();

    public void agregarProducto(ProductoDeAlmacen producto) {
        productos.add(producto);
    }

    public int getCantidadProductos() {
        int cantidadTotal = 0;
        for (ProductoDeAlmacen producto : productos) {
            cantidadTotal += producto.getStock();
        }
        return cantidadTotal;
    }


    public BigDecimal calcularImporteTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (ProductoDeAlmacen producto : productos) {
            total = total.add(producto.getPrecio().multiply(new BigDecimal(producto.getStock())));
        }
        return total;
    }

    public BigDecimal calcularImporteTotalConIVA() {
        BigDecimal totalConIVA = BigDecimal.ZERO;
        for (ProductoDeAlmacen producto : productos) {
            BigDecimal precioConIVA = producto.getPrecio().add(producto.calcularIVA());
            totalConIVA = totalConIVA.add(precioConIVA.multiply(new BigDecimal(producto.getStock())));
        }
        return totalConIVA;
    }


    public void mostrarContenidoCarrito() {
        System.out.println("Contenido del carrito:");
        for (ProductoDeAlmacen producto : productos) {
            System.out.println(producto.toString());
        }
    }
}
