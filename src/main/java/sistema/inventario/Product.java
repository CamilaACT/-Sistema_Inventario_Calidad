package sistema.inventario;

/**
 * Clase que representa un producto del inventario.
 */
public class Product {

    private final String name;
    private int quantity;
    private double price;

    /**
     * Constructor del producto.
     * @param name nombre
     * @param quantity cantidad
     * @param price precio
     */
    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
