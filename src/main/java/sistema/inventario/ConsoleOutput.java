package sistema.inventario;
public class ConsoleOutput implements IOutput {
 
    @Override
    public void print(String message) {
        System.out.println(message);
    }
 
    @Override
    public void printProduct(Product product) {
        System.out.printf("Producto: %s | Cantidad: %d | Precio: $%.2f%n",
                          product.getName(),
                          product.getQuantity(),
                          product.getPrice());
    }
}