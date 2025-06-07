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

// Import Mile class if it exists in another package
// import your.package.Mile;

/* Constructor to initialize the DAO with the SQLite database
 * and create the initial table schema if it does not exist.
 */
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
            "mileTargetDate	INTEGER, " +
            "mileCompleteDate INTEGER, " +
            "userID INTEGER, " +
            "PRIMARY KEY(mileID AUTOINCREMENT), " +
            "FOREIGN KEY(userID) REFERENCES user(userID) " +
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
        String sql = "INSERT INTO milestone (mileName, mileStatus, mileTargetDate, mileCompleteDate, userID) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mile.getMileName());
            pstmt.setString(2, mile.getMileStatus());
            pstmt.setString(3, mile.getMileTargetDate());
            pstmt.setString(4, mile.getMileCompleteDate());
            pstmt.setInt(5, mile.getUserID());
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
                    rs.getInt("userID")
                );
                miles.add(mile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return miles;
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
}

