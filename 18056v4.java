import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class 18056v4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CafeDatabase db = new CafeDatabase();
            new Cafe(db);
        });
    }
}

class CafeDatabase {
    private final String DB_URL = "jdbc:mysql://localhost:3306/client";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = ""; // Update password if necessary
    private Connection conn;

    public CafeDatabase() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect to the database: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String viewMenu() {
        StringBuilder menu = new StringBuilder();
        String query = "SELECT * FROM cafe";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                menu.append(rs.getInt("id"))
                    .append(". ")
                    .append(rs.getString("name"))
                    .append(" - $")
                    .append(String.format("%.2f", rs.getDouble("price")))
                    .append("\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to retrieve the menu: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return menu.toString();
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
            JOptionPane.showMessageDialog(null, "Failed to fetch price: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return -1; // Invalid ID
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to close database connection: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class CafeGUI extends JFrame {
    private final CafeDatabase db;

    public CafeGUI(CafeDatabase db) {
        this.db = db;

        setTitle("Mamaas Cafe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Components
        JTextArea menuArea = new JTextArea(10, 30);
        menuArea.setEditable(false);
        JButton viewMenuButton = new JButton("View Menu");
        JButton placeOrderButton = new JButton("Place Order");
        JButton exitButton = new JButton("Exit");

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewMenuButton);
        buttonPanel.add(placeOrderButton);
        buttonPanel.add(exitButton);

        // Add components
        add(new JScrollPane(menuArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action Listeners
        viewMenuButton.addActionListener(e -> {
            String menu = db.viewMenu();
            if (!menu.isEmpty()) {
                menuArea.setText(menu);
            } else {
                menuArea.setText("Menu is empty.");
            }
        });

        placeOrderButton.addActionListener(e -> {
            JTextField itemIdField = new JTextField(5);
            JTextField quantityField = new JTextField(5);

            JPanel orderPanel = new JPanel();
            orderPanel.add(new JLabel("Item ID:"));
            orderPanel.add(itemIdField);
            orderPanel.add(Box.createHorizontalStrut(15)); // Space between fields
            orderPanel.add(new JLabel("Quantity:"));
            orderPanel.add(quantityField);

            int result = JOptionPane.showConfirmDialog(null, orderPanel, 
                    "Place Order", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    int itemId = Integer.parseInt(itemIdField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());
                    double price = db.getPriceById(itemId);

                    if (price == -1) {
                        JOptionPane.showMessageDialog(null, "Invalid Item ID!", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        double total = price * quantity;
                        JOptionPane.showMessageDialog(null, 
                                "Order placed successfully!\nTotal Price: $" + String.format("%.2f", total), 
                                "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(e -> {
            db.close();
            System.exit(0);
        });

        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }
}
