package sistema.inventario;
 
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertTrue;
 
public class HtmlOutputTest {
 
    @Test
    void testPrintAndPrintProduct() {
        String testFilePath = "output/test_output.html";
        HtmlOutput output = new HtmlOutput(testFilePath);
 
        Product product = new Product("Monitor", 5, 129.99);
 
        output.print("Mensaje de prueba");
        output.printProduct(product);
 
        File file = new File(testFilePath);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
}
 