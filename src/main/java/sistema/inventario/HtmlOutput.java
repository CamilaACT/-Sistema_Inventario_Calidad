package sistema.inventario;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Implementación de salida en archivo HTML para IOutput.
 */
public class HtmlOutput implements IOutput {

  private static final Logger LOGGER = Logger.getLogger(HtmlOutput.class.getName());
  private final String filePath;

  public HtmlOutput(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void print(String message) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
      writer.printf("<p>%s</p>%n", escapeHtml(message));
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error al escribir mensaje en archivo HTML", e);
    }
  }

  @Override
  public void printProduct(Product product) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
      writer.printf(
          "<div><strong>Producto:</strong> %s | <strong>Cantidad:</strong> %d | <strong>Precio:</strong> $%.2f</div>%n",
          escapeHtml(product.getName()),
          product.getQuantity(),
          product.getPrice());
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error al escribir producto en archivo HTML", e);
    }
  }

  /**
   * Escapa caracteres HTML para prevenir inyección de código.
   *
   * @param text texto a escapar
   * @return texto escapado
   */
  private String escapeHtml(String text) {
    return text == null ? ""
        : text.replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#39;");
  }
}