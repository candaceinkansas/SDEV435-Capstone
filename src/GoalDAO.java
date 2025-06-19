/* Candace Sadowski
 * SDEV435 Applied Software Practice
 * Champlain College Online SUM 2025
 * 
 * Skill Tracker Application
 * GoalDAO.java
 */

/* This is a database access object (DAO) which is used to interact with the goal table for my Skill Tracker application.
 * It contains methods to create the goal table, add a new goal, retrieve all goals, and close the database connection.
 * This DAO is designed to work with a SQLite database and uses prepared statements to prevent SQL injection.
 * It is not tied to a specific database, allowing it to be reused in any application that requires goal management.
 */

 // Import necessary utilities
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Constructor to initialize the DAO with the SQLite database and create the initial table schema if it does not exist.
public class GoalDAO {
    private Connection conn;

    public GoalDAO(String dbPath) throws SQLException {
        // Create a connection to the SQLite database
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        createTable();
    }

    // Method to create the goal table if it does not exist
    private void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS goal (" +
            "goalID	INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "goalName	TEXT NOT NULL, " +
            "goalStatus TEXT NOT NULL CHECK(goalStatus IN ('Not Started', 'In Progress', 'Completed')), " +
            "goalTargetDate	TEXT, " +
            "goalCompleteDate TEXT, " +
            "userID INTEGER, " +
            "certID INTEGER, " +
            "FOREIGN KEY(certID) REFERENCES certification(certID) " +
            "FOREIGN KEY(userID) REFERENCES user(userID) " +
            ") STRICT";


        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) { // Handle SQL exceptions, such as duplicate entries
            e.printStackTrace();
        }
    }

    // Method to add a new goal to the database
    // Uses a prepared statement to prevent SQL injection and handle null values
    public void addGoal(Goal goal) throws SQLException {
        String sql = "INSERT INTO goal (goalName, goalStatus, goalTargetDate, goalCompleteDate, userID) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, goal.getGoalName());
            pstmt.setString(2, goal.getGoalStatus());
            pstmt.setString(3, goal.getGoalTargetDate());
            pstmt.setString(4, goal.getGoalCompleteDate());
            pstmt.setInt(5, goal.getUserID());
            pstmt.executeUpdate();
        } catch (SQLException e) {  // Handle SQL exceptions, such as duplicate entries
            e.printStackTrace();
        }
    }

    // Method to retrieve all goals from the database
    public List<Goal> getAllGoals() throws SQLException {
        List<Goal> goals = new ArrayList<>();
        String sql = "SELECT * FROM goal";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Goal goal = new Goal(
                    rs.getInt("goalID"),
                    rs.getString("goalName"),
                    rs.getString("goalStatus"),
                    rs.getString("goalTargetDate"),
                    rs.getString("goalCompleteDate"),
                    rs.getInt("userID")
                );
                goals.add(goal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goals;
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
} // End of GoalDAO class

