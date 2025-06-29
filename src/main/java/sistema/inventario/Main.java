package sistema.inventario;
 
public class Main {
    public static void main(String[] args) {
        IOutput output = new ConsoleOutput();
        Inventory inventory = new Inventory(output);
 
        inventory.addProduct("Laptop", 5, 1000.0);
        inventory.addProduct("Mouse", 10, 25.50);
        inventory.printInventory();
    }
}