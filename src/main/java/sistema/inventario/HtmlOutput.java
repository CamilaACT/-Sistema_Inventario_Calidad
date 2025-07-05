package sistema.inventario;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Implementación de salida en archivo HTML para IOutput.
 */
public class HtmlOutput implements IOutput {

    private final String filePath;

    public HtmlOutput(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void print(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.printf("<p>%s</p>%n", message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printProduct(Product product) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.printf("<div><strong>Producto:</strong> %s | <strong>Cantidad:</strong> %d | <strong>Precio:</strong> $%.2f</div>%n",
                    product.getName(),
                    product.getQuantity(),
                    product.getPrice());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}