package sistema.inventario;
public interface IOutput {
    void print(String message);
    void printProduct(Product product);  // 👈 Nuevo método
}