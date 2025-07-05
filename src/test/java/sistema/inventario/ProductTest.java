package sistema.inventario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Product
 * Objetivo: Alcanzar 80% de cobertura de código
 */
public class ProductTest {
    
    private Product product;
    
    @BeforeEach
    void setUp() {
        product = new Product("Laptop", 10, 1500.99);
    }
    
    /**
     * Prueba el constructor con valores válidos
     */
    @Test
    void testConstructor_ValidValues() {
        // Arrange & Act
        Product newProduct = new Product("Mouse", 5, 25.50);
        
        // Assert
        assertEquals("Mouse", newProduct.getName());
        assertEquals(5, newProduct.getQuantity());
        assertEquals(25.50, newProduct.getPrice(), 0.01);
    }
    
    /**
     * Prueba el constructor con valores límite
     */
    @Test
    void testConstructor_BoundaryValues() {
        // Arrange & Act
        Product zeroProduct = new Product("Cable", 0, 0.0);
        
        // Assert
        assertEquals("Cable", zeroProduct.getName());
        assertEquals(0, zeroProduct.getQuantity());
        assertEquals(0.0, zeroProduct.getPrice(), 0.01);
    }
    
    /**
     * Prueba el constructor con nombre nulo - debe lanzar excepción
     */
    @Test
    void testConstructor_NullName() {
        // Arrange & Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Product(null, 1, 10.0));
        
        assertEquals("El nombre del producto no puede ser nulo o vacío", exception.getMessage());
    }
    
    /**
     * Prueba el constructor con nombre vacío - debe lanzar excepción
     */
    @Test
    void testConstructor_EmptyName() {
        // Arrange & Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Product("", 1, 10.0));
        
        assertEquals("El nombre del producto no puede ser nulo o vacío", exception.getMessage());
    }
    
    /**
     * Prueba el constructor con nombre solo espacios - debe lanzar excepción
     */
    @Test
    void testConstructor_WhitespaceOnlyName() {
        // Arrange & Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Product("   ", 1, 10.0));
        
        assertEquals("El nombre del producto no puede ser nulo o vacío", exception.getMessage());
    }
    
    /**
     * Prueba el constructor con cantidad negativa - debe lanzar excepción
     */
    @Test
    void testConstructor_NegativeQuantity() {
        // Arrange & Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Product("Producto", -1, 10.0));
        
        assertEquals("La cantidad no puede ser negativa", exception.getMessage());
    }
    
    /**
     * Prueba el constructor con precio negativo - debe lanzar excepción
     */
    @Test
    void testConstructor_NegativePrice() {
        // Arrange & Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Product("Producto", 1, -10.0));
        
        assertEquals("El precio no puede ser negativo", exception.getMessage());
    }
    
    /**
     * Prueba el constructor con nombre con espacios al inicio y final - debe hacer trim
     */
    @Test
    void testConstructor_NameWithSpaces() {
        // Arrange & Act
        Product trimmedProduct = new Product("  Producto con espacios  ", 1, 10.0);
        
        // Assert
        assertEquals("Producto con espacios", trimmedProduct.getName());
    }
    
    /**
     * Prueba el getter getName
     */
    @Test
    void testGetName() {
        // Assert
        assertEquals("Laptop", product.getName());
    }
    
    /**
     * Prueba el getter getName con diferentes tipos de nombres
     */
    @ParameterizedTest
    @ValueSource(strings = {"Producto Simple", "PRODUCTO_MAYUSCULAS", "producto-con-guiones", "Producto123"})
    void testGetName_DifferentNameFormats(String name) {
        // Arrange
        Product testProduct = new Product(name, 1, 10.0);
        
        // Act & Assert
        assertEquals(name, testProduct.getName());
    }
    
    /**
     * Prueba el getter getQuantity
     */
    @Test
    void testGetQuantity() {
        // Assert
        assertEquals(10, product.getQuantity());
    }
    
    /**
     * Prueba el setter setQuantity con valores válidos
     */
    @Test
    void testSetQuantity_ValidValues() {
        // Act
        product.setQuantity(15);
        
        // Assert
        assertEquals(15, product.getQuantity());
    }
    
    /**
     * Prueba el setter setQuantity con valor cero
     */
    @Test
    void testSetQuantity_ZeroValue() {
        // Act
        product.setQuantity(0);
        
        // Assert
        assertEquals(0, product.getQuantity());
    }
    
    /**
     * Prueba el setter setQuantity con valores negativos - debe lanzar excepción
     */
    @Test
    void testSetQuantity_NegativeValues() {
        // Arrange & Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> product.setQuantity(-5));
        
        assertEquals("La cantidad no puede ser negativa", exception.getMessage());
    }
    
    /**
     * Prueba el setter setQuantity con valores grandes
     */
    @Test
    void testSetQuantity_LargeValues() {
        // Arrange
        int largeQuantity = 1000000;
        
        // Act
        product.setQuantity(largeQuantity);
        
        // Assert
        assertEquals(largeQuantity, product.getQuantity());
    }
    
    /**
     * Prueba el getter getPrice
     */
    @Test
    void testGetPrice() {
        // Assert
        assertEquals(1500.99, product.getPrice(), 0.01);
    }
    
    /**
     * Prueba el setter setPrice con valores válidos
     */
    @Test
    void testSetPrice_ValidValues() {
        // Act
        product.setPrice(2000.50);
        
        // Assert
        assertEquals(2000.50, product.getPrice(), 0.01);
    }
    
    /**
     * Prueba el setter setPrice con valor cero
     */
    @Test
    void testSetPrice_ZeroValue() {
        // Act
        product.setPrice(0.0);
        
        // Assert
        assertEquals(0.0, product.getPrice(), 0.01);
    }
    
    /**
     * Prueba el setter setPrice con valores negativos - debe lanzar excepción
     */
    @Test
    void testSetPrice_NegativeValues() {
        // Arrange & Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> product.setPrice(-100.0));
        
        assertEquals("El precio no puede ser negativo", exception.getMessage());
    }
    
    /**
     * Prueba el setter setPrice con valores decimales precisos
     */
    @Test
    void testSetPrice_PreciseDecimals() {
        // Act
        product.setPrice(99.99);
        
        // Assert
        assertEquals(99.99, product.getPrice(), 0.001);
    }
    
    /**
     * Prueba el setter setPrice con valores muy pequeños
     */
    @Test
    void testSetPrice_SmallValues() {
        // Act
        product.setPrice(0.01);
        
        // Assert
        assertEquals(0.01, product.getPrice(), 0.001);
    }
    
    /**
     * Prueba que el nombre es inmutable (final)
     */
    @Test
    void testName_IsImmutable() {
        // Arrange
        String originalName = product.getName();
        
        // Act & Assert - No hay setter para name, debe ser inmutable
        assertEquals(originalName, product.getName());
        
        // Verificar que el nombre no cambia después de crear el objeto
        Product anotherProduct = new Product("Otro", 1, 1.0);
        assertEquals("Laptop", product.getName()); // El nombre original no cambia
        assertEquals("Otro", anotherProduct.getName());
    }
    
    /**
     * Prueba múltiples operaciones de modificación
     */
    @Test
    void testMultipleOperations() {
        // Act
        product.setQuantity(20);
        product.setPrice(1800.75);
        
        // Assert
        assertEquals("Laptop", product.getName()); // Nombre sigue siendo inmutable
        assertEquals(20, product.getQuantity());
        assertEquals(1800.75, product.getPrice(), 0.01);
    }
    
    /**
     * Prueba estado inicial del producto
     */
    @Test
    void testInitialState() {
        // Assert - Verificar estado inicial del producto creado en setUp
        assertEquals("Laptop", product.getName());
        assertEquals(10, product.getQuantity());
        assertEquals(1500.99, product.getPrice(), 0.01);
    }
    
    /**
     * Prueba crear productos con nombres especiales
     */
    @Test
    void testSpecialCharactersInName() {
        // Arrange & Act
        Product specialProduct = new Product("Producto-Ñandú_123 (Edición Especial)", 1, 50.0);
        
        // Assert
        assertEquals("Producto-Ñandú_123 (Edición Especial)", specialProduct.getName());
        assertEquals(1, specialProduct.getQuantity());
        assertEquals(50.0, specialProduct.getPrice(), 0.01);
    }
    
    /**
     * Prueba el método equals con objetos iguales
     */
    @Test
    void testEquals_SameObjects() {
        // Arrange
        Product product1 = new Product("Test", 5, 10.0);
        Product product2 = new Product("Test", 5, 10.0);
        
        // Act & Assert
        assertEquals(product1, product2);
        assertEquals(product1, product1); // Reflexivo
    }
    
    /**
     * Prueba el método equals con objetos diferentes
     */
    @Test
    void testEquals_DifferentObjects() {
        // Arrange
        Product product1 = new Product("Test", 5, 10.0);
        Product product2 = new Product("Test", 6, 10.0);
        Product product3 = new Product("Test", 5, 11.0);
        Product product4 = new Product("Different", 5, 10.0);
        
        // Act & Assert
        assertNotEquals(product1, product2);
        assertNotEquals(product1, product3);
        assertNotEquals(product1, product4);
        assertNotEquals(product1, null);
        assertNotEquals(product1, "Not a product");
    }
    
    /**
     * Prueba el método hashCode
     */
    @Test
    void testHashCode() {
        // Arrange
        Product product1 = new Product("Test", 5, 10.0);
        Product product2 = new Product("Test", 5, 10.0);
        
        // Act & Assert
        assertEquals(product1.hashCode(), product2.hashCode());
    }
    
    /**
     * Prueba el método toString
     */
    @Test
    void testToString() {
        // Arrange
        Product testProduct = new Product("Test Product", 5, 10.50);
        
        // Act
        String result = testProduct.toString();
        
        // Assert
        assertTrue(result.contains("Test Product"));
        assertTrue(result.contains("5"));
        assertTrue(result.contains("10.50"));
        assertEquals("Product{name='Test Product', quantity=5, price=10.50}", result);
    }
    
    /**
     * Prueba cobertura completa del constructor con todas las validaciones
     */
    @Test
    void testConstructor_AllValidations() {
        // Test casos extremos que activan todas las validaciones
        assertThrows(IllegalArgumentException.class, () -> new Product(null, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Product("", 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Product("Valid", -1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Product("Valid", 0, -1));
        
        // Test caso válido
        Product validProduct = new Product("Valid", 0, 0);
        assertEquals("Valid", validProduct.getName());
        assertEquals(0, validProduct.getQuantity());
        assertEquals(0.0, validProduct.getPrice());
    }
}