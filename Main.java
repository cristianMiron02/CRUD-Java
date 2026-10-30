
import java.util.ArrayList;
import java.util.Scanner;

class Producto {
    private int id;
    private String nombre;
    private double precio;

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setPrecio(double precio) {
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

        while (true) {
            System.out.println("\n===== CRUD DE PRODUCTOS =====");
            System.out.println("1. Crear producto");
            System.out.println("2. Mostrar productos");
            System.out.println("3. Actualizar precio");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Salir");

            int opcion = leerEntero("Elige una opción: ");

            try {
                switch (opcion) {
                    case 1 -> crearProducto();
                    case 2 -> mostrarProductos();
                    case 3 -> actualizarProducto();
                    case 4 -> eliminarProducto();
                    case 5 -> {
                        System.out.println("Cerrando programa...");
                        return;
                    }
                    default -> System.out.println(
                        "Error: selecciona una opción entre 1 y 5."
                    );
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Leer números enteros sin cerrar el programa
    static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println(
                    "Error: introduce un número entero válido."
                );
            }
        }
    }

    // Leer precios válidos
    static double leerPrecio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine()
                                    .trim()
                                    .replace(',', '.');

            try {
                double precio = Double.parseDouble(entrada);

                if (!Double.isFinite(precio) || precio < 0) {
                    throw new IllegalArgumentException(
                        "El precio debe ser un número positivo o cero."
                    );
                }

                return precio;

            } catch (NumberFormatException e) {
                System.out.println(
                    "Error: introduce un precio numérico válido."
                );
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Leer nombres no vacíos
    static String leerNombre() {
        while (true) {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine().trim();

            if (!nombre.isEmpty()) {
                return nombre;
            }

            System.out.println(
                "Error: el nombre no puede estar vacío."
            );
        }
    }

    // CREATE
    static void crearProducto() {
        System.out.println("\n--- CREAR PRODUCTO ---");

        String nombre = leerNombre();
        double precio = leerPrecio("Precio: ");

        Producto producto = new Producto(
            siguienteId++, nombre, precio
        );

        productos.add(producto);

        System.out.println("Producto creado correctamente.");
    }

    // READ
    static void mostrarProductos() {
        System.out.println("\n--- LISTA DE PRODUCTOS ---");

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    // Buscar producto por ID
    static Producto buscarProducto(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }

        throw new IllegalArgumentException(
            "No existe ningún producto con ID " + id
        );
    }

    // UPDATE
    static void actualizarProducto() {
        System.out.println("\n--- ACTUALIZAR PRODUCTO ---");

        int id = leerEntero("ID del producto: ");
        Producto producto = buscarProducto(id);

        double nuevoPrecio = leerPrecio("Nuevo precio: ");
        producto.setPrecio(nuevoPrecio);

        System.out.println("Producto actualizado correctamente.");
    }

    // DELETE
    static void eliminarProducto() {
        System.out.println("\n--- ELIMINAR PRODUCTO ---");

        int id = leerEntero("ID del producto: ");
        Producto producto = buscarProducto(id);

        productos.remove(producto);

        System.out.println("Producto eliminado correctamente.");
    }
}