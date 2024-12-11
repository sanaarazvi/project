      import java.sql.*;
import java.util.Scanner;

public class 18056v3 {
    public static void main(String[] args) {
        CafeDatabase db = new CafeDatabase();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to Mamaas Cafe!");

            while (true) {
                System.out.println("\n1. View Menu");
                System.out.println("2. Place Order");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                if (choice == 1) {
                    
                    db.viewMenu();
                } else if (choice == 2) {
                    
                    System.out.print("Enter menu item ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();

                    double price = db.getPriceById(id);
                    if (price == -1) {
                        System.out.println("Invalid menu item ID.");
                    } else {
                        double total = price * quantity;
                        System.out.printf("Order placed! Total: $%.2f%n", total);
                    }
                } else if (choice == 3) {
                    
                    System.out.println("Thank you for visiting Mamaas Cafe!");
                    break;
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            db.close();
        }
    }
}

class CafeDatabase {
    private final String DB_URL = "jdbc:mysql://localhost:3306/client";
    private final String DB_USER = "root"; 
    private final String DB_PASSWORD = ""; 
    private Connection conn;

    public CafeDatabase() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    public void viewMenu() {
        String query = "SELECT * FROM cafe";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\nMenu:");
            while (rs.next()) {
                System.out.printf("%d. %s - &%.2f%n", rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve the menu: " + e.getMessage());
        }
    }

    public double getPriceById(int id) {
        String query = "SELECT price FROM cafe WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("price");
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch price: " + e.getMessage());
        }
        return -1; 
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Failed to close the database connection: " + e.getMessage());
        }
    }
}
