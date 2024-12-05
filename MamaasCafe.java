import java.util.Scanner;

public class MamaasCafe {
    public static void main(String[] args) {
        // Menu items and prices
        String[] menuItems = {"Coffee", "Tea", "Sandwich", "Cake", "Juice"};
        double[] menuPrices = {2.5, 1.5, 4.0, 3.0, 2.0};
        int[] orderQuantities = new int[menuItems.length];

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Java Cafe!");
        
        // Display the menu
        System.out.println("Here is our menu:");
        for (int i = 0; i < menuItems.length; i++) {
            System.out.printf("%d. %s - $%.2f\n", i + 1, menuItems[i], menuPrices[i]);
        }

        // Taking orders
        while (true) {
            System.out.print("\nEnter the item number to order (or 0 to finish): ");
            int itemNumber = scanner.nextInt();

            if (itemNumber == 0) {
                break; // Exit when the user enters 0
            }

            if (itemNumber < 1 || itemNumber > menuItems.length) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            System.out.print("Enter the quantity: ");
            int quantity = scanner.nextInt();
            if (quantity < 1) {
                System.out.println("Quantity must be at least 1. Try again.");
                continue;
            }

            orderQuantities[itemNumber - 1] += quantity;
            System.out.println("Item added to your order.");
        }

        // Display the order summary and calculate the total cost
        System.out.println("\nOrder Summary:");
        double totalCost = 0;
        for (int i = 0; i < menuItems.length; i++) {
            if (orderQuantities[i] > 0) {
                double itemCost = orderQuantities[i] * menuPrices[i];
                totalCost += itemCost;
                System.out.printf("%s x %d = $%.2f\n", menuItems[i], orderQuantities[i], itemCost);
            }
        }

        System.out.printf("Total Cost: $%.2f\n", totalCost);
        System.out.println("Thank you for visiting Java Cafe!");

        scanner.close();
    }
}
