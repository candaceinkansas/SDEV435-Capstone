/* Candace Sadowski
 * SDEV435 Applied Software Practice
 * Champlain College Online SUM 2025
 * 
 * Skill Tracker Application
 * MileDAO.java
 */

// Import necessary utilities
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/* This is a database access object (DAO) which is used to interact with the milestone table for my Skill Tracker application.
 * It contains methods to create the milestone table, add a new milestone, retrieve all milestones, and close the database connection.
 * This DAO is designed to work with a SQLite database and uses prepared statements to prevent SQL injection.
 * It is not tied to a specific database, allowing it to be reused in any application that requires milestone management.
 */

// Constructor to initialize the DAO with the SQLite database and create the initial table schema if it does not exist.
public class MileDAO {
    private Connection conn;

    public MileDAO(String dbPath) throws SQLException {
        // Create a connection to the SQLite database
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        createTable();
    }

    // Method to create the milestone table if it does not exist
    private void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS milestone (" +
            "mileID	INTEGER NOT NULL UNIQUE, " +
            "mileName	TEXT NOT NULL, " +
            "mileStatus TEXT NOT NULL CHECK(mileStatus IN ('Not Started', 'In Progress', 'Completed')), " +
            "mileTargetDate	TEXT, " +
            "mileCompleteDate TEXT, " +
            "goalID INTEGER, " +
            "PRIMARY KEY(mileID AUTOINCREMENT), " +
            "FOREIGN KEY(goalID) REFERENCES goal(goalID) " +
            ") STRICT";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) { // Handle SQL exceptions, such as duplicate entries
            e.printStackTrace();
        }
    }

    // Method to add a new milestone to the database
    // Uses a prepared statement to prevent SQL injection and handle null values
    public void addMile(Mile mile) throws SQLException {
        String sql = "INSERT INTO milestone (mileName, mileStatus, mileTargetDate, mileCompleteDate, goalID) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mile.getMileName());
            pstmt.setString(2, mile.getMileStatus());
            pstmt.setString(3, mile.getMileTargetDate());
            pstmt.setString(4, mile.getMileCompleteDate());
            pstmt.setInt(5, mile.getGoalID());
            pstmt.executeUpdate();
        } catch (SQLException e) {  // Handle SQL exceptions, such as duplicate entries
            e.printStackTrace();
        }
    }

    // Method to retrieve all milestones from the database
    public List<Mile> getAllMiles() throws SQLException {
        List<Mile> miles = new ArrayList<>();
        String sql = "SELECT * FROM milestone";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Mile mile = new Mile(
                    rs.getInt("mileID"),
                    rs.getString("mileName"),
                    rs.getString("mileStatus"),
                    rs.getString("mileTargetDate"),
                    rs.getString("mileCompleteDate"),
                    rs.getInt("goalID")
                );
                miles.add(mile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return miles;
    }

// Method to retrieve all milestones from the database; prints milestones ordered by user's last name and grouped by goalID
    public void getMilesWithGoalName() throws SQLException {
        String sql = "SELECT m.MileID, m.MileName, m.MileStatus, m.MileTargetDate, m.MileCompleteDate, m.GoalID, " +
                "u.FirstName, u.LastName, g.GoalName, g.GoalStatus FROM milestone m " +
                "INNER JOIN goal g ON m.GoalID = g.GoalID " +
                "INNER JOIN user u ON g.UserID = u.UserID " +
                "ORDER BY u.LastName, u.FirstName, m.GoalID";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            // Print the header for the milestone details
            System.out.printf("------------------------------------------------------------\n");
            System.out.printf("Milestones ordered by user name and grouped by goalID:\n");
            System.out.printf("------------------------------------------------------------\n");
            System.out.printf("%-20s\t%-50s\t%-15s\t%-10s\n", "USER", "MILESTONE ID & NAME", "MILESTONE STATUS", "FOR GOAL ID & NAME");

            //iterate through the result set and print each milestone's details
            while (rs.next()) {
                String goalID = rs.getString("goalID"); // convert goalID to String to handle null values for INTEGER field (if left as int, null values will return as "0")
                    if (goalID == null) {
                        goalID = "Standalone Milestone";
                    }
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String mileID = rs.getString("MileID"); 
                String mileName = rs.getString("MileName");
                String mileStatus = rs.getString("MileStatus");
                String goalName = rs.getString("GoalName");
                // Print the milestone details
                System.out.printf("%-20s\t%-50s\t%-15s\t\t%-10s\n", firstName + " " + lastName, mileID + " " + mileName, mileStatus, goalID + " " + goalName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
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
} // End of MileDAO class

