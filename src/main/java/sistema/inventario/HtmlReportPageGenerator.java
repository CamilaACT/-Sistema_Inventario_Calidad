package sistema.inventario;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Generador base de página HTML para reportes.
 */
public class HtmlReportPageGenerator {

  private static final Logger LOGGER = Logger.getLogger(HtmlReportPageGenerator.class.getName());
  private final String filePath;

  public HtmlReportPageGenerator(String filePath) {
    this.filePath = filePath;
  }

  public void startHtml() {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
      writer.println("<!DOCTYPE html>");
      writer.println("<html lang=\"es\">");
      writer.println("<head>");
      writer.println("  <meta charset=\"UTF-8\">");
      writer.println("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
      writer.println("  <title>Reporte de Inventario</title>");
      writer.println("  <style>");
      writer.println("    body { font-family: Arial, sans-serif; margin: 20px; }");
      writer.println("    div { margin: 5px 0; }");
      writer.println("    ul { list-style-type: square; }");
      writer.println("  </style>");
      writer.println("</head>");
      writer.println("<body>");
      writer.println("  <h1>Inventario generado por Java CI/CD</h1>");
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error al inicializar archivo HTML", e);
    }
  }

  public void addLinksToReports() {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
      writer.println("<hr>");
      writer.println("<h2>Reportes:</h2>");
      writer.println("<ul>");
      writer.println("  <li><a href=\"checkstyle.html\">Ver reporte Checkstyle</a></li>");
      writer.println("  <li><a href=\"pmd.html\">Ver reporte PMD</a></li>");
      writer.println("  <li><a href=\"jacoco/index.html\">Ver reporte de cobertura Jacoco</a></li>");
      writer.println("</ul>");
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error al agregar enlaces a reportes", e);
    }
  }

  public void endHtml() {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
      writer.println("</body>");
      writer.println("</html>");
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error al cerrar archivo HTML", e);
    }
  }
}