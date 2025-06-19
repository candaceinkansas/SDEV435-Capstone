/* Candace Sadowski
 * SDEV435 Applied Software Practice
 * Champlain College Online SUM 2025
 * 
 * Skill Tracker Application
 * Cert.java
 */

// This class represents a certification in the Skill Tracker application.
// It contains properties such as id, name, status, target date, compete date, and userID.
// It also includes a constructor to initialize these properties.

public class Cert {
    private int certID;
    private String certName;
    private String certStatus;
    private String certTargetDate;
    private String certCompleteDate;
    private int userID;

    // Constructor to initialize the Certification properties
    public Cert(String certName, String certStatus, String certTargetDate, String certCompleteDate, int userID) {
        this.certName = certName;
        this.certStatus = certStatus; 
        this.certTargetDate = certTargetDate;
        this.certCompleteDate = certCompleteDate;
        this.userID = userID;
    }
        public Cert(int certID, String certName, String certStatus, String certTargetDate, String certCompleteDate, int userID) {
            this(certName, certStatus, certTargetDate, certCompleteDate, userID);
            this.certID = certID;
            //this.userID = userID;
        }

    // Getters and setters for each property
    public int getCertID() {
        return certID;
    }
    
    public String getCertName() {
        return certName;
    }
    public String getCertStatus() {
        return certStatus;
    }

    public String getCertTargetDate() {
        return certTargetDate;
    }

    public String getCertCompleteDate() {
        return certCompleteDate;
    }

    public int getUserID() {
        return userID;
    }

}