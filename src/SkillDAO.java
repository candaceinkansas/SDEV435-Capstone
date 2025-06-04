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

public class SkillDAO {
    private Connection conn;

    public SkillDAO(String dbPath) throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        createTable();
    }

    private void createTable() throws SQLException {
        
        String sql = "CREATE TABLE IF NOT EXISTS skills (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "name TEXT NOT NULL UNIQUE" +
                     "prficiency TEXT," +
                     "category TEXT" + 
                     "last_used TEXT);";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }

    public void addSkill(Skill skill) throws SQLException {
        String sql = "INSERT INTO skills (name, proficiency, category, last_used) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, skill.getName());
            pstmt.setString(2, skill.getProficiency());
            pstmt.setString(3, skill.getCategory());
            pstmt.setString(4, skill.getLastUsed());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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



