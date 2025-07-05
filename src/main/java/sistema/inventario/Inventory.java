package sistema.inventario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase que gestiona el inventario de productos.
 */
public class Inventory {

  private final List<Product> products = new ArrayList<>();
  private final IOutput output;

  /**
   * Constructor con inyección de dependencia de salida.
   *
   * @param output implementación de IOutput (ej: ConsoleOutput)
   */
  public Inventory(IOutput output) {
    this.output = output;
  }

  /**
   * Agrega un nuevo producto al inventario.
   *
   * @param name     nombre del producto
   * @param quantity cantidad disponible
   * @param price    precio del producto
   */
  public void addProduct(String name, int quantity, double price) {
    if (!isValidProduct(name, quantity, price)) {
      output.print("Error: Datos inválidos para el producto.");
      return;
    }

    if (productExists(name)) {
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
   * Devuelve una copia inmutable de la lista de productos.
   *
   * @return lista inmutable de productos
   */
  public List<Product> getProducts() {
    return Collections.unmodifiableList(products);
  }

  /**
   * Valida si los datos del producto son válidos.
   *
   * @param name     nombre del producto
   * @param quantity cantidad del producto
   * @param price    precio del producto
   * @return true si los datos son válidos, false en caso contrario
   */
  private boolean isValidProduct(String name, int quantity, double price) {
    return name != null && !name.trim().isEmpty() && quantity >= 0 && price >= 0;
  }

  /**
   * Verifica si un producto ya existe en el inventario.
   *
   * @param name nombre del producto a verificar
   * @return true si el producto existe, false en caso contrario
   */
  private boolean productExists(String name) {
    return products.stream()
        .anyMatch(p -> p.getName().equalsIgnoreCase(name));
  }
}