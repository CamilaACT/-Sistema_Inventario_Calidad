package sistema.inventario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Pruebas unitarias para la clase ConsoleOutput
 * Objetivo: Alcanzar 80% de cobertura de código
 */
public class ConsoleOutputTest {
    
    private ConsoleOutput consoleOutput;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    void setUp() {
        consoleOutput = new ConsoleOutput();
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
    
    /**
     * Prueba el método print con un mensaje simple
     */
    @Test
    void testPrint_SimpleMessage() {
        // Arrange
        String message = "Mensaje de prueba";
        
        // Act
        consoleOutput.print(message);
        
        // Assert
        String expectedOutput = message + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
    
    /**
     * Prueba el método print con un mensaje vacío
     */
    @Test
    void testPrint_EmptyMessage() {
        // Arrange
        String message = "";
        
        // Act
        consoleOutput.print(message);
        
        // Assert
        String expectedOutput = "" + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
    
    /**
     * Prueba el método print con un mensaje nulo
     */
    @Test
    void testPrint_NullMessage() {
        // Arrange
        String message = null;
        
        // Act
        consoleOutput.print(message);
        
        // Assert
        String expectedOutput = "null" + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
    
    /**
     * Prueba el método print con caracteres especiales
     */
    @Test
    void testPrint_SpecialCharacters() {
        // Arrange
        String message = "Mensaje con caracteres especiales: áéíóú ñ @#$%";
        
        // Act
        consoleOutput.print(message);
        
        // Assert
        String expectedOutput = message + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
    
    /**
     * Prueba el método printProduct con un producto válido
     */
    @Test
    void testPrintProduct_ValidProduct() {
        // Arrange
        Product product = new Product("Laptop", 5, 1500.99);
        
        // Act
        consoleOutput.printProduct(product);
        
        // Assert
        String expectedOutput = "Producto: Laptop | Cantidad: 5 | Precio: $1500.99" + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
    
    /**
     * Prueba el método printProduct con un producto con precio decimal
     */
    @Test
    void testPrintProduct_ProductWithDecimalPrice() {
        // Arrange
        Product product = new Product("Mouse", 10, 25.50);
        
        // Act
        consoleOutput.printProduct(product);
        
        // Assert
        String expectedOutput = "Producto: Mouse | Cantidad: 10 | Precio: $25.50" + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
    
    /**
     * Prueba el método printProduct con un producto con precio entero
     */
    @Test
    void testPrintProduct_ProductWithIntegerPrice() {
        // Arrange
        Product product = new Product("Teclado", 3, 75.00);
        
        // Act
        consoleOutput.printProduct(product);
        
        // Assert
        String expectedOutput = "Producto: Teclado | Cantidad: 3 | Precio: $75.00" + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
    
    /**
     * Prueba el método printProduct con un producto con cantidad cero
     */
    @Test
    void testPrintProduct_ProductWithZeroQuantity() {
        // Arrange
        Product product = new Product("Monitor", 0, 300.00);
        
        // Act
        consoleOutput.printProduct(product);
        
        // Assert
        String expectedOutput = "Producto: Monitor | Cantidad: 0 | Precio: $300.00" + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
    
    /**
     * Prueba el método printProduct con un producto con nombre que contiene espacios
     */
    @Test
    void testPrintProduct_ProductWithSpacesInName() {
        // Arrange
        Product product = new Product("Disco Duro Externo", 2, 150.75);
        
        // Act
        consoleOutput.printProduct(product);
        
        // Assert
        String expectedOutput = "Producto: Disco Duro Externo | Cantidad: 2 | Precio: $150.75" + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
    
    /**
     * Prueba el método printProduct con un producto con precio muy pequeño
     */
    @Test
    void testPrintProduct_ProductWithSmallPrice() {
        // Arrange
        Product product = new Product("Cable USB", 100, 0.99);
        
        // Act
        consoleOutput.printProduct(product);
        
        // Assert
        String expectedOutput = "Producto: Cable USB | Cantidad: 100 | Precio: $0.99" + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
    
    /**
     * Prueba que ConsoleOutput implementa correctamente la interfaz IOutput
     */
    @Test
    void testConsoleOutput_ImplementsIOutput() {
        // Assert
        assertTrue(consoleOutput instanceof IOutput);
    }
    
    /**
     * Prueba múltiples llamadas al método print
     */
    @Test
    void testPrint_MultipleCalls() {
        // Arrange
        String message1 = "Primera línea";
        String message2 = "Segunda línea";
        
        // Act
        consoleOutput.print(message1);
        consoleOutput.print(message2);
        
        // Assert
        String expectedOutput = message1 + System.lineSeparator() + 
                               message2 + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
    
    /**
     * Prueba múltiples llamadas al método printProduct
     */
    @Test
    void testPrintProduct_MultipleCalls() {
        // Arrange
        Product product1 = new Product("Producto1", 1, 10.00);
        Product product2 = new Product("Producto2", 2, 20.00);
        
        // Act
        consoleOutput.printProduct(product1);
        consoleOutput.printProduct(product2);
        
        // Assert
        String expectedOutput = "Producto: Producto1 | Cantidad: 1 | Precio: $10.00" + System.lineSeparator() +
                               "Producto: Producto2 | Cantidad: 2 | Precio: $20.00" + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}