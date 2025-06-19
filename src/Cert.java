/* Candace Sadowski
 * SDEV435 Applied Software Practice
 * Champlain College Online SUM 2025
 * 
 * Skill Tracker Application
 * Cert.java
 */

// This class represents a certification for my Skill Tracker application.
// It contains properties such as id, name, status, target date, compete date, and userID.
// It contains a full constructor to initialize all properties, along with individual getter and setter methods.

// This reusable plain Java object (POJO) is not tied to a specific database or framework. It could be reused in any
// application that requires a certification model, whether data is loaded from a database, an API, JSON, or any other source.

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

    // Getters for each property
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

    // Setters for each property (except primary and foreign keys) to allow modification after creation
    public void setCertName(String certName) {
        this.certName = certName;
    }

    public void setCertStatus(String certStatus) {
        this.certStatus = certStatus;
    }

    public void setCertTargetDate(String certTargetDate) {
        this.certTargetDate = certTargetDate;
    }

    public void setCertCompleteDate(String certCompleteDate) {
        this.certCompleteDate = certCompleteDate;
    }




}