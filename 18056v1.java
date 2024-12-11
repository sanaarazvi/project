import java.util.Scanner;

public class MamaasCafe1 {

    public static void main(String[] args) {
        Cafe cafe = new Cafe();
        cafe.start();
    }
}

class MenuItem {
    int id;
    String name;
    double price;

    public MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

class Cafe {

    private MenuItem[] menu;

    public Cafe() {
        
        menu = new MenuItem[]{
                new MenuItem(1, "Cake", 3.00),
                new MenuItem(2, "Coffee", 1.00),
                new MenuItem(3, "Maggie", 2.00),
                new MenuItem(4, "Lemon Mojito", 1.40)
        };
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            
            System.out.println("\nWelcome to Mamaas Cafe");
            System.out.println("1. View Menu");
            System.out.println("2. Place Order");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1/2/3): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    viewMenu();
                    break;
                case 2:
                    placeOrder(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for visiting Mamaas Cafe. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    public void viewMenu() {
        System.out.println("\nMenu:");
        for (MenuItem item : menu) {
            System.out.printf("%d. %s - $%.2f\n", item.id, item.name, item.price); 
        }
    }

    public void placeOrder(Scanner scanner) {
        System.out.print("Enter the Item ID you want to order: ");
        int itemId = scanner.nextInt();
        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt();

       
        MenuItem item = getItemById(itemId);

        if (item != null) {
            double totalPrice = item.price * quantity;
            System.out.printf("\nOrder placed successfully!\n" +
                    "Item: %s\n" +
                    "Quantity: %d\n" +
                    "Total Price: $%.2f\n", item.name, quantity, totalPrice); 
        } else {
            System.out.println("Invalid Item ID! Please try again.");
        }
    }

    private MenuItem getItemById(int id) {
        for (MenuItem item : menu) {
            if (item.id == id) {
                return item;
            }
        }
        return null; 
    }
}
