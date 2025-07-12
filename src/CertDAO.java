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

/* This is a database access object (DAO) which is used to interact with the certification table for my Skill Tracker application.
 * It contains methods to create the certification table, add a new certification, retrieve all certifications, and close the database connection.
 * This DAO is designed to work with a SQLite database and uses prepared statements to prevent SQL injection.
 * It is not tied to a specific database, allowing it to be reused in any application that requires certification management.
 */

// Constructor to initialize the DAO with the SQLite database and create the initial table schema if it does not exist.
 
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
            "certTargetDate	TEXT, " +
            "certCompleteDate TEXT, " +
            "userID INTEGER, " +
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

    // Method to retrieve a certification by user ID
    public List<Cert> getCertByUserID(int userID) throws SQLException {        
        List<Cert> certs = new ArrayList<>();
        String sql = "SELECT * FROM certification WHERE userID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            try (ResultSet rs = pstmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return certs; // Return the certifications found
    }

       // Method to retrieve all goals from the database; prints goals ordered by user's last name and grouped by certID
    public void getCertsWithUserName() throws SQLException {
        String sql = "SELECT c.UserID, u.FirstName, u.LastName, c.CertName, c.CertStatus, c.CertID, c.CertTargetDate, " +
                "c.CertCompleteDate FROM certification c INNER JOIN user u ON c.UserID = u.UserID ORDER BY u.LastName, u.FirstName, c.CertID";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            // Print the header for the goal details
            System.out.printf("------------------------------------------------------------\n");
            System.out.printf("Certifications grouped by user:\n");
            System.out.printf("------------------------------------------------------------\n");
            System.out.printf("%-20s\t%-37s\t%-15s\n", "USER", "CERTIFICATION ID & NAME", "CERTIFICATION STATUS");

            //iterate through the result set and print each goal's details
            while (rs.next()) {
                String certID = rs.getString("certID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String certName = rs.getString("CertName");
                String certStatus = rs.getString("CertStatus");
                // Print the goal details
                System.out.printf("%-20s\t%-2s%-30s\t%-15s\n", firstName + " " + lastName, certID, certName, certStatus);
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
}

