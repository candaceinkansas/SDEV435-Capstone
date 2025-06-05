/* Candace Sadowski
 * SDEV435 Applied Software Practice
 * Champlain College Online SUM 2025
 * 
 * Skill Tracker Application
 * SkillDAO.java
 */

 // Import necessary utilities
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/* Constructor to initialize the DAO with the SQLite database
 * and create the initial table schema if it does not exist.
 */
public class SkillDAO {
    private Connection conn;

    public SkillDAO(String dbPath) throws SQLException {
        // Create a connection to the SQLite database
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        createTable();
    }

    // Method to create the skills table if it does not exist
    private void createTable() throws SQLException {
        
        String sql = "CREATE TABLE IF NOT EXISTS skills (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "name TEXT NOT NULL UNIQUE," +
                     "proficiency TEXT," +
                     "category TEXT," +
                     "last_used TEXT);";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) { // Handle SQL exceptions, such as duplicate entries
            e.printStackTrace();
        }
    }

    // Method to add a new skill to the database
    // Uses a prepared statement to prevent SQL injection and handle null values
    public void addSkill(Skill skill) throws SQLException {
        String sql = "INSERT INTO skills (name, proficiency, category, last_used) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, skill.getName());
            pstmt.setString(2, skill.getProficiency());
            pstmt.setString(3, skill.getCategory());
            pstmt.setString(4, skill.getLastUsed());
            pstmt.executeUpdate();
        } catch (SQLException e) {  // Handle SQL exceptions, such as duplicate entries
            e.printStackTrace();
        }
    }

    // Method to retrieve all skills from the database
    public List<Skill> getAllSkills() throws SQLException {
        List<Skill> skills = new ArrayList<>();
        String sql = "SELECT * FROM skills";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Skill skill = new Skill(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("proficiency"),
                    rs.getString("category"),
                    rs.getString("last_used")
                );
                skills.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
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

