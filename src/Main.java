import entity.Carrito;
import entity.ProductoDeAlmacen;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Carrito carrito = new Carrito();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Menú:");
                System.out.println("1. Agregar producto al carrito");
                System.out.println("2. Ver cantidad de productos en el carrito");
                System.out.println("3. Calcular importe total sin IVA");
                System.out.println("4. Calcular importe total con IVA");
                System.out.println("5. Ver contenido del carrito");
                System.out.println("6. Salir");

                int opcion = scanner.nextInt();
                scanner.nextLine();  // Consumir la nueva línea después de leer el número

                switch (opcion) {
                    case 1:
                        agregarProductoAlCarrito(carrito, scanner);
                        break;
                    case 2:
                        System.out.println("Cantidad de productos en el carrito: " + carrito.getCantidadProductos());
                        break;
                    case 3:
                        BigDecimal importeTotalSinIVA = carrito.calcularImporteTotal();
                        System.out.println("Importe total del carrito sin IVA: " + importeTotalSinIVA);
                        break;
                    case 4:
                        BigDecimal importeTotalConIVA = carrito.calcularImporteTotalConIVA();
                        System.out.println("Importe total del carrito con IVA: " + importeTotalConIVA);
                        break;
                    case 5:
                        carrito.mostrarContenidoCarrito();
                        break;
                    case 6:
                        System.out.println("Gracias por su compra. Saliendo...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un valor numérico válido.");
                scanner.nextLine(); // Limpiar el búfer de entrada
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }

    private static void agregarProductoAlCarrito(Carrito carrito, Scanner scanner) {
        try {
            System.out.println("Ingrese el nombre del producto:");
            String nombre = scanner.nextLine();
            if (!nombre.matches("[a-zA-Z\\s]+")) {
                throw new IllegalArgumentException("El nombre no debe contener números ni caracteres especiales.");
            }

            System.out.println("Ingrese el precio del producto:");
            BigDecimal precio = scanner.nextBigDecimal();
            if (precio.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El precio debe ser un valor positivo.");
            }

            System.out.println("Ingrese el stock del producto:");
            int stock = scanner.nextInt();
            if (stock <= 0) {
                throw new IllegalArgumentException("El stock debe ser un número entero positivo.");
            }
            scanner.nextLine(); // Consumir la nueva línea después de leer el número

            ProductoDeAlmacen producto = new ProductoDeAlmacen(nombre, precio, stock);
            carrito.agregarProducto(producto);
            System.out.println("Producto agregado al carrito.");
        } catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un valor numérico válido.");
            scanner.nextLine(); // Limpiar el búfer de entrada
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}
