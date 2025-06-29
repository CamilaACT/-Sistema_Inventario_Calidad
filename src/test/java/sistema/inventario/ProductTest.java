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
     * Prueba el constructor con nombre nulo
     */
    @Test
    void testConstructor_NullName() {
        // Arrange & Act
        Product nullNameProduct = new Product(null, 1, 10.0);
        
        // Assert
        assertNull(nullNameProduct.getName());
        assertEquals(1, nullNameProduct.getQuantity());
        assertEquals(10.0, nullNameProduct.getPrice(), 0.01);
    }
    
    /**
     * Prueba el constructor con nombre vacío
     */
    @Test
    void testConstructor_EmptyName() {
        // Arrange & Act
        Product emptyNameProduct = new Product("", 1, 10.0);
        
        // Assert
        assertEquals("", emptyNameProduct.getName());
        assertEquals(1, emptyNameProduct.getQuantity());
        assertEquals(10.0, emptyNameProduct.getPrice(), 0.01);
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
    @ValueSource(strings = {"Producto Simple", "PRODUCTO_MAYUSCULAS", "producto-con-guiones", "Producto123", "Producto con espacios múltiples"})
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
     * Prueba el setter setQuantity con valores negativos
     */
    @Test
    void testSetQuantity_NegativeValues() {
        // Act
        product.setQuantity(-5);
        
        // Assert
        assertEquals(-5, product.getQuantity());
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
     * Prueba el setter setPrice con valores negativos
     */
    @Test
    void testSetPrice_NegativeValues() {
        // Act
        product.setPrice(-100.0);
        
        // Assert
        assertEquals(-100.0, product.getPrice(), 0.01);
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
}