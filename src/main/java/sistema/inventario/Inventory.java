package sistema.inventario;
 
import java.util.ArrayList;
import java.util.List;
 
public class Inventory {
    private final List<Product> products = new ArrayList<>();
    private final IOutput output;
 
    // Constructor: se inyecta una implementación de IOutput (ej: ConsoleOutput)
    public Inventory(IOutput output) {
        this.output = output;
    }
 
    // Agrega un producto a la lista
    public void addProduct(String name, int quantity, double price) {
        if (name == null || name.isBlank() || quantity < 0 || price < 0) {
            output.print("Error: Datos inválidos para el producto.");
            return;
        }

        // Validación de existencia por nombre (ignorando mayúsculas/minúsculas)
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
 
    // Imprime el inventario usando la salida definida
    public void printInventory() {
        if (products.isEmpty()) {
            output.print("Inventario vacío.");
            return;
        }
 
        for (Product product : products) {
            output.printProduct(product);
        }
    }
 
    // Getter para pruebas u otras operaciones
    public List<Product> getProducts() {
        return products;
    }
}