package sistema.inventario;

/**
 * Interfaz para definir salidas del sistema de inventario.
 */
public interface IOutput {

    /**
     * Imprime un mensaje de texto.
     * @param message mensaje a imprimir
     */
    void print(String message);

    /**
     * Imprime la información de un producto.
     * @param product producto a mostrar
     */
    void printProduct(Product product);
}
