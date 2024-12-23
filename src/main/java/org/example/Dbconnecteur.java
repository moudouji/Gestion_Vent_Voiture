package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Dbconnecteur {
    // Database credentials
    private static final String url = "jdbc:mysql://localhost:3307/voiture"; // Replace "itleji_db" with your database name
    private static final String user = "root"; // Default XAMPP username
    private static final String password = ""; // Default XAMPP password (empty)

    // Connection object
    public static Connection connection;

    // Constructor to establish a connection
    public static  Connection getConnection() {
        try {
            // Establish a connection
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully!");

        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }

        return connection;
    }

    // Method to close the connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing the connection: " + e.getMessage());
        }
    }
}
