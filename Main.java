
import java.util.ArrayList;
import java.util.Scanner;

class Producto {
    int id;
    String nombre;
    double precio;

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               " | Nombre: " + nombre +
               " | Precio: " + precio + " €";
    }
}

public class Main {

    static ArrayList<Producto> productos = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int siguienteId = 1;

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE PRODUCTOS =====");
            System.out.println("1. Crear producto");
            System.out.println("2. Mostrar productos");
            System.out.println("3. Actualizar precio");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> crearProducto();
                case 2 -> mostrarProductos();
                case 3 -> actualizarProducto();
                case 4 -> eliminarProducto();
                case 5 -> System.out.println("Hasta pronto.");
                default -> System.out.println("Opción incorrecta.");
            }

        } while (opcion != 5);
    }

    // CREATE
    static void crearProducto() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();

        Producto producto = new Producto(siguienteId++, nombre, precio);
        productos.add(producto);

        System.out.println("Producto creado correctamente.");
    }

    // READ
    static void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos.");
            return;
        }

        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    // UPDATE
    static void actualizarProducto() {
        System.out.print("ID del producto: ");
        int id = scanner.nextInt();

        for (Producto producto : productos) {
            if (producto.id == id) {
                System.out.print("Nuevo precio: ");
                producto.precio = scanner.nextDouble();

                System.out.println("Producto actualizado.");
                return;
            }
        }

        System.out.println("Producto no encontrado.");
    }

    // DELETE
    static void eliminarProducto() {
        System.out.print("ID del producto: ");
        int id = scanner.nextInt();

        boolean eliminado = productos.removeIf(p -> p.id == id);

        if (eliminado) {
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
}
