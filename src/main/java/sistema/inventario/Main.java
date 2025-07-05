package sistema.inventario;

import java.io.File;

/**
 * Clase principal para ejecutar el sistema de inventario.
 */
public class Main {

  /**
   * Método principal.
   *
   * @param args argumentos
   */
  public static void main(String[] args) {
    File outputDir = new File("output");
    if (!outputDir.exists()) {
      boolean created = outputDir.mkdirs();
      if (!created) {
        System.err.println("Error: No se pudo crear la carpeta output.");
        return;
      }
    }

    String htmlFilePath = "output/index.html";
    // Genera estructura HTML básica
    HtmlReportPageGenerator page = new HtmlReportPageGenerator(htmlFilePath);
    page.startHtml();

    IOutput htmlOutput = new HtmlOutput(htmlFilePath);
    Inventory htmlInventory = new Inventory(htmlOutput);
    htmlInventory.addProduct("Monitor", 5, 129.99);
    htmlInventory.addProduct("Teclado", 10, 39.99);
    htmlInventory.printInventory();

    // Enlaces a reportes
    page.addLinksToReports();
    page.endHtml();

    IOutput output = new ConsoleOutput();
    Inventory inventory = new Inventory(output);
    inventory.addProduct("Monitor", 5, 129.99);
    inventory.addProduct("Teclado", 10, 39.99);
    inventory.printInventory();

    System.out.println("Versión 1.0.0 lista para producción.");
  }
}