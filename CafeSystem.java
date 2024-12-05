import java.util.Scanner;
import java.util.Vector;

public class CafeSystem {
    public static void main(String[] args) {
        // Menu items, prices, and quantities using Vectors
        Vector<String> menuItems = new Vector<>();
        Vector<Double> menuPrices = new Vector<>();
        Vector<Integer> orderQuantities = new Vector<>();

        // Initialize menu
        menuItems.add("Coffee");
        menuPrices.add(2.5);
        orderQuantities.add(0);

        menuItems.add("Tea");
        menuPrices.add(1.5);
        orderQuantities.add(0);

        menuItems.add("Sandwich");
        menuPrices.add(4.0);
        orderQuantities.add(0);

        menuItems.add("Cake");
        menuPrices.add(3.0);
        orderQuantities.add(0);

        menuItems.add("Juice");
        menuPrices.add(2.0);
        orderQuantities.add(0);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Java Cafe!");

        // Display the menu
        System.out.println("Here is our menu:");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.printf("%d. %s - $%.2f\n", i + 1, menuItems.get(i), menuPrices.get(i));
        }

        // Taking orders
        while (true) {
            System.out.print("\nEnter the item number to order (or 0 to finish): ");
            int itemNumber = scanner.nextInt();

            if (itemNumber == 0) {
                break; // Exit when the user enters 0
            }

            if (itemNumber < 1 || itemNumber > menuItems.size()) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            System.out.print("Enter the quantity: ");
            int quantity = scanner.nextInt();
            if (quantity < 1) {
                System.out.println("Quantity must be at least 1. Try again.");
                continue;
            }

            // Update order quantities
            int currentQuantity = orderQuantities.get(itemNumber - 1);
            orderQuantities.set(itemNumber - 1, currentQuantity + quantity);
            System.out.println("Item added to your order.");
        }

        // Display the order summary and calculate the total cost
        System.out.println("\nOrder Summary:");
        double totalCost = 0;
        for (int i = 0; i < menuItems.size(); i++) {
            if (orderQuantities.get(i) > 0) {
                double itemCost = orderQuantities.get(i) * menuPrices.get(i);
                totalCost += itemCost;
                System.out.printf("%s x %d = $%.2f\n", menuItems.get(i), orderQuantities.get(i), itemCost);
            }
        }

        System.out.printf("Total Cost: $%.2f\n", totalCost);
        System.out.println("Thank you for visiting Java Cafe!");

        scanner.close();
    }
}
