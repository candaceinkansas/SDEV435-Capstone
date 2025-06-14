/* Candace Sadowski
 * SDEV435 Applied Software Practice
 * Champlain College Online SUM 2025
 * 
 * Skill Tracker Application
 * CertDAO.java
 */

// Import necessary utilities
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Import Cert class if it exists in another package
// import your.package.Cert;

/* Constructor to initialize the DAO with the SQLite database
 * and create the initial table schema if it does not exist.
 */
public class CertDAO {
    private Connection conn;

    public CertDAO(String dbPath) throws SQLException {
        // Create a connection to the SQLite database
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        createTable();
    }

    // Method to create the certification table if it does not exist
    private void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS certification (" +
            "certID	INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "certName	TEXT NOT NULL, " +
            "certStatus TEXT NOT NULL CHECK(certStatus IN ('Not Started', 'In Progress', 'Completed')), " +
            "certTargetDate	INTEGER, " +
            "certCompleteDate INTEGER, " +
            "userID INTEGER, " +
            //"PRIMARY KEY(certID AUTOINCREMENT), " +
            "FOREIGN KEY(userID) REFERENCES user(userID) " +
            ") STRICT";


        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) { // Handle SQL exceptions, such as duplicate entries
            e.printStackTrace();
        }
    }

    // Method to add a new certification to the database
    // Uses a prepared statement to prevent SQL injection and handle null values
    public void addCert(Cert cert) throws SQLException {
        String sql = "INSERT INTO certification (certName, certStatus, certTargetDate, certCompleteDate, userID) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cert.getCertName());
            pstmt.setString(2, cert.getCertStatus());
            pstmt.setString(3, cert.getCertTargetDate());
            pstmt.setString(4, cert.getCertCompleteDate());
            pstmt.setInt(5, cert.getUserID());
            pstmt.executeUpdate();
        } catch (SQLException e) {  // Handle SQL exceptions, such as duplicate entries
            e.printStackTrace();
        }
    }

    // Method to retrieve all certifications from the database
    public List<Cert> getAllCerts() throws SQLException {
        List<Cert> certs = new ArrayList<>();
        String sql = "SELECT * FROM certification";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cert cert = new Cert(
                    rs.getInt("certID"),
                    rs.getString("certName"),
                    rs.getString("certStatus"),
                    rs.getString("certTargetDate"),
                    rs.getString("certCompleteDate"),
                    rs.getInt("userID")
                );
                certs.add(cert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return certs;
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

