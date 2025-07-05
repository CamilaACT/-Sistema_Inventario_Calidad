package sistema.inventario;
 
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;
 
public class HtmlReportPageGeneratorTest {
 
    @Test

    void testHtmlReportGeneration() {

        String testFilePath = "output/test_index.html";

        HtmlReportPageGenerator page = new HtmlReportPageGenerator(testFilePath);
 
        page.startHtml();

        page.addLinksToReports();

        page.endHtml();
 
        File file = new File(testFilePath);

        assertTrue(file.exists());

        assertTrue(file.length() > 0);

    }

}
 