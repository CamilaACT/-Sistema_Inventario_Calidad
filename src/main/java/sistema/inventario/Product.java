package sistema.inventario;

import java.util.Objects;

/**
 * Clase que representa un producto del inventario.
 */
public class Product {

  private final String name;
  private int quantity;
  private double price;

  /**
   * Constructor del producto.
   *
   * @param name     nombre del producto
   * @param quantity cantidad disponible
   * @param price    precio del producto
   * @throws IllegalArgumentException si los parámetros son inválidos
   */
  public Product(String name, int quantity, double price) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío");
    }
    if (quantity < 0) {
      throw new IllegalArgumentException("La cantidad no puede ser negativa");
    }
    if (price < 0) {
      throw new IllegalArgumentException("El precio no puede ser negativo");
    }

    this.name = name.trim();
    this.quantity = quantity;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public int getQuantity() {
    return quantity;
  }

  /**
   * Establece la cantidad del producto.
   *
   * @param quantity nueva cantidad
   * @throws IllegalArgumentException si la cantidad es negativa
   */
  public void setQuantity(int quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException("La cantidad no puede ser negativa");
    }
    this.quantity = quantity;
  }

  public double getPrice() {
    return price;
  }

  /**
   * Establece el precio del producto.
   *
   * @param price nuevo precio
   * @throws IllegalArgumentException si el precio es negativo
   */
  public void setPrice(double price) {
    if (price < 0) {
      throw new IllegalArgumentException("El precio no puede ser negativo");
    }
    this.price = price;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Product product = (Product) obj;
    return quantity == product.quantity
        && Double.compare(product.price, price) == 0
        && Objects.equals(name, product.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, quantity, price);
  }

  @Override
  public String toString() {
    return String.format("Product{name='%s', quantity=%d, price=%.2f}", name, quantity, price);
  }
}