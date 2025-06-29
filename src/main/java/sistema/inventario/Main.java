package sistema.inventario;

/**
 * Clase principal para ejecutar el sistema de inventario.
 */
public class Main {

    /**
     * Método principal.
     * @param args argumentos
     */
    public static void main(String[] args) {
        IOutput output = new ConsoleOutput();
        Inventory inventory = new Inventory(output);

        inventory.addProduct("Monitor", 5, 129.99);
        inventory.addProduct("Teclado", 10, 39.99);
        inventory.printInventory();
    }
}
