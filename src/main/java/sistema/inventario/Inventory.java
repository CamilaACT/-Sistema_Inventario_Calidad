package sistema.inventario;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona el inventario de productos.
 */
public class Inventory {

    private final List<Product> products = new ArrayList<>();
    private final IOutput output;

    /**
     * Constructor con inyección de dependencia de salida.
     * @param output implementación de IOutput (ej: ConsoleOutput)
     */
    public Inventory(IOutput output) {
        this.output = output;
    }

    /**
     * Agrega un nuevo producto al inventario.
     * @param name nombre del producto
     * @param quantity cantidad disponible
     * @param price precio del producto
     */
    public void addProduct(String name, int quantity, double price) {
        if (name == null || name.isBlank() || quantity < 0 || price < 0) {
            output.print("Error: Datos inválidos para el producto.");
            return;
        }

        boolean exists = products.stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(name));

        if (exists) {
            output.print("Error: El producto ya existe en el inventario.");
            return;
        }

        Product product = new Product(name, quantity, price);
        products.add(product);
        output.print("Producto agregado correctamente.");
    }

    /**
     * Imprime todos los productos del inventario.
     */
    public void printInventory() {
        if (products.isEmpty()) {
            output.print("Inventario vacío.");
            return;
        }

        for (Product product : products) {
            output.printProduct(product);
        }
    }

    /**
     * Devuelve la lista de productos.
     * @return lista de productos
     */
    public List<Product> getProducts() {
        return products;
    }
}
