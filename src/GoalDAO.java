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
        }} // end of createTable method

    // Method to add a new goal to the database
    // Uses a prepared statement to prevent SQL injection and handle null values
    public void addGoal(Goal goal) throws SQLException {
        String sql = "INSERT INTO goal (goalName, goalStatus, goalTargetDate, goalCompleteDate, userID, certID) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, goal.getGoalName());
            pstmt.setString(2, goal.getGoalStatus());
            pstmt.setString(3, goal.getGoalTargetDate());
            pstmt.setString(4, goal.getGoalCompleteDate());
            pstmt.setInt(5, goal.getUserID());
            if (goal.getCertID() != null) {
                pstmt.setInt(6, goal.getCertID());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {  // Handle SQL exceptions, such as duplicate entries
            e.printStackTrace();
        }} // end of addGoal method

    // Method to update the goal status to "Completed" and set the completion date
    // Uses a prepared statement to prevent SQL injection and handle null values
    public void updateGoalCompleteDate(String newCompleteDate, int goalID) throws SQLException {
        String sql = "UPDATE goal SET goalCompleteDate = ?, goalStatus = 'Completed' WHERE goalID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newCompleteDate);
            pstmt.setInt(2, goalID);
            pstmt.executeUpdate();
        } catch (SQLException e) {  // Handle SQL exceptions, such as duplicate entries
            e.printStackTrace();
        }} // end of updateGoalCompleteDate method

    // Method to change goal status
    // Uses a prepared statement to prevent SQL injection and handle null values
    public void updateGoalStatus(String newStatus, int goalID) throws SQLException {
        String sql = "UPDATE goal SET goalStatus = ?, goalCompleteDate = null WHERE goalID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, goalID);
            pstmt.executeUpdate();
        } catch (SQLException e) {  // Handle SQL exceptions, such as duplicate entries
            e.printStackTrace();
        }} // end of updateGoalStatus method

    // Method to get a list of goals
    public List<Goal> getGoals(int userID) throws SQLException {
        List<Goal> goals = new ArrayList<>();
        String sql = "SELECT * FROM goal WHERE userID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                Goal goal = new Goal(
                    rs.getInt("goalID"),
                    rs.getString("goalName"),
                    rs.getString("goalStatus"),
                    rs.getString("goalTargetDate"),
                    rs.getString("goalCompleteDate"),
                    rs.getInt("userID"),
                    rs.getInt("certID")
                );
                goals.add(goal);
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goals; 
    }} // end of getGoals method

    // Method to retrieve all goals from the database; prints goals with user ID, goal ID, goal name, status, target date, and completion date
    public void getAllGoals() throws SQLException {
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
            System.out.println();
            System.out.println("All Goals:");
            // Print the goal details.  Fields returned differ based on goal status. Handles null values for target date and only includes completion date if goal is completed.
            for (Goal goal : goals) {
                if (goal.getGoalStatus().equals("Completed")) {
                    System.out.println("  User " + goal.getUserID() + " " + " Goal " + goal.getGoalID() + ": " + goal.getGoalName() +
                            ", Status: " + goal.getGoalStatus() + ", Target Date: " + goal.getGoalTargetDate() + ", Completion Date: " + goal.getGoalCompleteDate());
                } else if (goal.getGoalStatus().equals("In Progress") && (goal.getGoalTargetDate() == null || goal.getGoalTargetDate().isEmpty())) {
                    System.out.println("  User " + goal.getUserID() + " " + " Goal " + goal.getGoalID() + ": " + goal.getGoalName() +
                            ", Status: " + goal.getGoalStatus() + ", Target Date: Not yet defined"); // If in progress and target date is null or empty, print "Not yet defined"
                } else if (goal.getGoalStatus().equals("In Progress") && goal.getGoalTargetDate() != null) {
                    System.out.println("  User " + goal.getUserID() + " " + " Goal " + goal.getGoalID() + ": " + goal.getGoalName() +
                            ", Status: " + goal.getGoalStatus() + ", Target Date: " + goal.getGoalTargetDate()); // If in progress with a target date, print the target date
                } else if (goal.getGoalStatus().equals("Not Started") && (goal.getGoalTargetDate() == null || goal.getGoalTargetDate().isEmpty())) {
                    System.out.println("  User " + goal.getUserID() + " " + " Goal " + goal.getGoalID() + ": " + goal.getGoalName() +
                            ", Status: " + goal.getGoalStatus() + ", Target Date: Not yet defined"); // If not started and target date is null or empty, print "Not yet defined"
                } else if (goal.getGoalStatus().equals("Not Started") && goal.getGoalTargetDate() != null) {
                    System.out.println("  User " + goal.getUserID() + " " + " Goal " + goal.getGoalID() + ": " + goal.getGoalName() +
                            ", Status: " + goal.getGoalStatus() + ", Target Date: " + goal.getGoalTargetDate()); // If not started with a target date, print the target date       
                } else {
                    // If the goal status is not recognized, print a default message
                    System.out.println("  Error when attempting to retrieve goal information.");
                }
            } // End of while loop
        } catch (SQLException e) {
            e.printStackTrace();
        }} // end of getAllGoals method

    // Method to retrieve all goals from the database; prints goals ordered by user's last name and grouped by certID
    public void getGoalsWithCertID() throws SQLException {
        String sql = "SELECT g.UserID, u.FirstName, u.LastName, g.GoalName, g.GoalStatus, g.CertID, g.GoalTargetDate, " +
                "g.GoalCompleteDate FROM goal g INNER JOIN user u ON g.UserID = u.UserID ORDER BY u.LastName, u.FirstName,g.CertID";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            // Print the header for the goal details
            System.out.printf("------------------------------------------------------------\n");
            System.out.printf("Goals ordered by user name and grouped by certID:\n");
            System.out.printf("------------------------------------------------------------\n");
            System.out.printf("%-20s\t%-50s\t%-15s\t%-20s\n", "USER", "GOAL NAME", "GOAL STATUS", "FOR CERT ID");

            //iterate through the result set and print each goal's details
            while (rs.next()) {
                String certID = rs.getString("certID"); // convert certID to String to handle null values for INTEGER field (if left as int, null values will return as "0")
                    if (certID == null) {
                        certID = "Standalone Goal";
                    }
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String goalName = rs.getString("GoalName");
                String goalStatus = rs.getString("GoalStatus");
                // Print the goal details
                System.out.printf("%-20s\t%-50s\t%-15s\t%-20s\n", firstName + " " + lastName, goalName, goalStatus, certID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }} // end of getGoalsWithCertID method

    // Method to close the database connection
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }} // end of close method
} // End of GoalDAO class

