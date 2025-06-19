/* Candace Sadowski
 * SDEV435 Applied Software Practice
 * Champlain College Online SUM 2025
 * 
 * Skill Tracker Application
 * UserDAO.java
 */

/* This is a database access object (DAO) which is used to interact with the user table for my Skill Tracker application.
 * It contains methods to create the user table, add a new user, retrieve all users, and close the database connection.
 * This DAO is designed to work with a SQLite database and uses prepared statements to prevent SQL injection.
 * It is not tied to a specific database, allowing it to be reused in any application that requires user management.
 */

// Import necessary utilities
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Constructor to initialize the DAO with the SQLite database and create the initial table schema if it does not exist.
public class UserDAO {
    private Connection conn;

    public UserDAO(String dbPath) throws SQLException {
        // Create a connection to the SQLite database
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        createTable();
    }

    // Method to create the user table if it does not exist
    private void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS user (" +
            "userID	INTEGER, " +
            "firstName	TEXT NOT NULL, " +
            "lastName TEXT NOT NULL, " +
            "PRIMARY KEY(userID AUTOINCREMENT) " +
            ") STRICT";


        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) { // Handle SQL exceptions, such as duplicate entries
            e.printStackTrace();
        }
    }

    // Method to add a new user to the database
    // Uses a prepared statement to prevent SQL injection and handle null values
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO user (firstName, lastName) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.executeUpdate();
        } catch (SQLException e) {  // Handle SQL exceptions, such as duplicate entries
            e.printStackTrace();
        }
    }

    // Method to retrieve all users from the database
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User(
                    rs.getInt("userID"),
                    rs.getString("firstName"),
                    rs.getString("lastName")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Method to close the database connection
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
} // End of UserDAO class

