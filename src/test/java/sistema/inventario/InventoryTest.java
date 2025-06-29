package sistema.inventario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para la clase Inventory
 * Objetivo: Alcanzar 80% de cobertura de código
 */
public class InventoryTest {
    
    @Mock
    private IOutput mockOutput;
    
    private Inventory inventory;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inventory = new Inventory(mockOutput);
    }
    
    /**
     * Prueba el constructor con IOutput válido
     */
    @Test
    void testConstructor_ValidOutput() {
        // Arrange & Act
        Inventory newInventory = new Inventory(mockOutput);
        
        // Assert
        assertNotNull(newInventory);
        assertTrue(newInventory.getProducts().isEmpty());
    }
    
    /**
     * Prueba addProduct con datos válidos
     */
    @Test
    void testAddProduct_ValidData() {
        // Act
        inventory.addProduct("Laptop", 5, 1500.99);
        
        // Assert
        assertEquals(1, inventory.getProducts().size());
        Product addedProduct = inventory.getProducts().get(0);
        assertEquals("Laptop", addedProduct.getName());
        assertEquals(5, addedProduct.getQuantity());
        assertEquals(1500.99, addedProduct.getPrice(), 0.01);
        
        verify(mockOutput).print("Producto agregado correctamente.");
    }
    
    /**
     * Prueba addProduct con nombre nulo
     */
    @Test
    void testAddProduct_NullName() {
        // Act
        inventory.addProduct(null, 5, 100.0);
        
        // Assert
        assertTrue(inventory.getProducts().isEmpty());
        verify(mockOutput).print("Error: Datos inválidos para el producto.");
    }
    
    /**
     * Prueba addProduct con nombre vacío
     */
    @Test
    void testAddProduct_EmptyName() {
        // Act
        inventory.addProduct("", 5, 100.0);
        
        // Assert
        assertTrue(inventory.getProducts().isEmpty());
        verify(mockOutput).print("Error: Datos inválidos para el producto.");
    }
    
    /**
     * Prueba addProduct con nombre solo espacios en blanco
     */
    @Test
    void testAddProduct_BlankName() {
        // Act
        inventory.addProduct("   ", 5, 100.0);
        
        // Assert
        assertTrue(inventory.getProducts().isEmpty());
        verify(mockOutput).print("Error: Datos inválidos para el producto.");
    }
    
    /**
     * Prueba addProduct con cantidad negativa
     */
    @Test
    void testAddProduct_NegativeQuantity() {
        // Act
        inventory.addProduct("Producto", -1, 100.0);
        
        // Assert
        assertTrue(inventory.getProducts().isEmpty());
        verify(mockOutput).print("Error: Datos inválidos para el producto.");
    }
    
    /**
     * Prueba addProduct con precio negativo
     */
    @Test
    void testAddProduct_NegativePrice() {
        // Act
        inventory.addProduct("Producto", 5, -100.0);
        
        // Assert
        assertTrue(inventory.getProducts().isEmpty());
        verify(mockOutput).print("Error: Datos inválidos para el producto.");
    }
    
    /**
     * Prueba addProduct con cantidad y precio en cero (valores límite válidos)
     */
    @Test
    void testAddProduct_ZeroQuantityAndPrice() {
        // Act
        inventory.addProduct("Producto Gratis", 0, 0.0);
        
        // Assert
        assertEquals(1, inventory.getProducts().size());
        Product addedProduct = inventory.getProducts().get(0);
        assertEquals("Producto Gratis", addedProduct.getName());
        assertEquals(0, addedProduct.getQuantity());
        assertEquals(0.0, addedProduct.getPrice(), 0.01);
        
        verify(mockOutput).print("Producto agregado correctamente.");
    }
    
    /**
     * Prueba addProduct con producto duplicado (mismo nombre, diferentes mayúsculas)
     */
    @Test
    void testAddProduct_DuplicateProductIgnoreCase() {
        // Arrange
        inventory.addProduct("Laptop", 5, 1500.0);
        
        // Act
        inventory.addProduct("LAPTOP", 3, 1200.0);
        
        // Assert
        assertEquals(1, inventory.getProducts().size());
        verify(mockOutput).print("Producto agregado correctamente.");
        verify(mockOutput).print("Error: El producto ya existe en el inventario.");
    }
    
    /**
     * Prueba addProduct con producto duplicado (mismo nombre exacto)
     */
    @Test
    void testAddProduct_ExactDuplicateProduct() {
        // Arrange
        inventory.addProduct("Mouse", 10, 25.0);
        
        // Act
        inventory.addProduct("Mouse", 5, 30.0);
        
        // Assert
        assertEquals(1, inventory.getProducts().size());
        verify(mockOutput).print("Producto agregado correctamente.");
        verify(mockOutput).print("Error: El producto ya existe en el inventario.");
    }
    
    /**
     * Prueba addProduct con múltiples productos válidos
     */
    @Test
    void testAddProduct_MultipleValidProducts() {
        // Act
        inventory.addProduct("Laptop", 5, 1500.0);
        inventory.addProduct("Mouse", 10, 25.0);
        inventory.addProduct("Teclado", 8, 75.0);
        
        // Assert
        assertEquals(3, inventory.getProducts().size());
        verify(mockOutput, times(3)).print("Producto agregado correctamente.");
    }
    
    /**
     * Prueba printInventory con inventario vacío
     */
    @Test
    void testPrintInventory_EmptyInventory() {
        // Act
        inventory.printInventory();
        
        // Assert
        verify(mockOutput).print("Inventario vacío.");
        verify(mockOutput, never()).printProduct(any(Product.class));
    }
    
    /**
     * Prueba printInventory con un producto
     */
    @Test
    void testPrintInventory_SingleProduct() {
        // Arrange
        inventory.addProduct("Laptop", 5, 1500.0);
        
        // Act
        inventory.printInventory();
        
        // Assert
        verify(mockOutput).printProduct(any(Product.class));
    }
    
    /**
     * Prueba printInventory con múltiples productos
     */
    @Test
    void testPrintInventory_MultipleProducts() {
        // Arrange
        inventory.addProduct("Laptop", 5, 1500.0);
        inventory.addProduct("Mouse", 10, 25.0);
        inventory.addProduct("Teclado", 8, 75.0);
        
        // Act
        inventory.printInventory();
        
        // Assert
        verify(mockOutput, times(3)).printProduct(any(Product.class));
        verify(mockOutput, never()).print("Inventario vacío.");
    }
    
    /**
     * Prueba getProducts retorna lista correcta
     */
    @Test
    void testGetProducts_ReturnsCorrectList() {
        // Arrange
        inventory.addProduct("Producto1", 1, 10.0);
        inventory.addProduct("Producto2", 2, 20.0);
        
        // Act
        var products = inventory.getProducts();
        
        // Assert
        assertEquals(2, products.size());
        assertEquals("Producto1", products.get(0).getName());
        assertEquals("Producto2", products.get(1).getName());
    }
    
    /**
     * Prueba getProducts retorna lista vacía inicialmente
     */
    @Test
    void testGetProducts_InitiallyEmpty() {
        // Act
        var products = inventory.getProducts();
        
        // Assert
        assertNotNull(products);
        assertTrue(products.isEmpty());
    }
    
    /**
     * Prueba que getProducts retorna la misma referencia de lista
     */
    @Test
    void testGetProducts_ReturnsSameReference() {
        // Act
        var products1 = inventory.getProducts();
        var products2 = inventory.getProducts();
        
        // Assert
        assertSame(products1, products2);
    }
    
    /**
     * Prueba casos límite con nombres especiales
     */
    @ParameterizedTest
    @ValueSource(strings = {"Producto-Especial", "Producto_123", "Producto (Edición)", "Producto/Categoría"})
    void testAddProduct_SpecialCharactersInName(String productName) {
        // Act
        inventory.addProduct(productName, 1, 10.0);
        
        // Assert
        assertEquals(1, inventory.getProducts().size());
        assertEquals(productName, inventory.getProducts().get(0).getName());
        verify(mockOutput).print("Producto agregado correctamente.");
    }
    
    /**
     * Prueba integración completa: agregar y mostrar inventario
     */
    @Test
    void testCompleteWorkflow_AddAndPrintInventory() {
        // Arrange & Act
        inventory.addProduct("Laptop", 5, 1500.0);
        inventory.addProduct("Mouse", 10, 25.0);
        inventory.printInventory();
        
        // Assert
        assertEquals(2, inventory.getProducts().size());
        verify(mockOutput, times(2)).print("Producto agregado correctamente.");
        verify(mockOutput, times(2)).printProduct(any(Product.class));
        verify(mockOutput, never()).print("Inventario vacío.");
    }
    
    /**
     * Prueba manejo de errores múltiples
     */
    @Test
    void testMultipleErrors() {
        // Act
        inventory.addProduct(null, 5, 100.0);
        inventory.addProduct("", -1, 50.0);
        inventory.addProduct("Producto", 5, -100.0);
        
        // Assert
        assertTrue(inventory.getProducts().isEmpty());
        verify(mockOutput, times(3)).print("Error: Datos inválidos para el producto.");
        verify(mockOutput, never()).print("Producto agregado correctamente.");
    }
    
    /**
     * Prueba verificación de duplicados insensible a mayúsculas/minúsculas
     */
    @Test
    void testCaseInsensitiveDuplicateCheck() {
        // Arrange
        inventory.addProduct("producto", 1, 10.0);
        
        // Act & Assert - Todas estas variaciones deben ser rechazadas
        inventory.addProduct("PRODUCTO", 1, 10.0);
        inventory.addProduct("Producto", 1, 10.0);
        inventory.addProduct("pRoDuCtO", 1, 10.0);
        
        // Assert
        assertEquals(1, inventory.getProducts().size());
        verify(mockOutput, times(1)).print("Producto agregado correctamente.");
        verify(mockOutput, times(3)).print("Error: El producto ya existe en el inventario.");
    }
    
    /**
     * Prueba orden de productos en la lista
     */
    @Test
    void testProductOrder() {
        // Act - Agregar productos en orden específico
        inventory.addProduct("Zebra", 1, 100.0);
        inventory.addProduct("Alpha", 2, 200.0);
        inventory.addProduct("Beta", 3, 300.0);
        
        // Assert - Los productos deben mantenerse en el orden de inserción
        var products = inventory.getProducts();
        assertEquals("Zebra", products.get(0).getName());
        assertEquals("Alpha", products.get(1).getName());
        assertEquals("Beta", products.get(2).getName());
    }
}