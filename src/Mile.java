/* Candace Sadowski
 * SDEV435 Applied Software Practice
 * Champlain College Online SUM 2025
 * 
 * Skill Tracker Application
 * Mile.java
 */

// This class represents a milestone in the Skill Tracker application.
// It contains properties such as id, name, status, target date, compete date, and userID.
// It contains a full constructor to initialize all properties, along with individual getter and setter methods.
// This class can be used in conjunction with a database access object (DAO) to perform CRUD operations on milestones.

// This reusable plain Java object (POJO) is not tied to a specific database or framework. It could be reused in any
// application that requires a milestone model, whether data is loaded from a database, an API, JSON, or another source.

public class Mile {
    private int mileID;
    private String mileName;
    private String mileStatus;
    private String mileTargetDate;
    private String mileCompleteDate;
    private int userID;

    // Constructor to initialize the Milestone properties
    public Mile(String mileName, String mileStatus, String mileTargetDate, String mileCompleteDate, int userID) {
        this.mileName = mileName;
        this.mileStatus = mileStatus; 
        this.mileTargetDate = mileTargetDate;
        this.mileCompleteDate = mileCompleteDate;
    }
    public Mile(int mileID, String mileName, String mileStatus, String mileTargetDate, String mileCompleteDate, int userID) {
        this(mileName, mileStatus, mileTargetDate, mileCompleteDate, userID);
        this.mileID = mileID;
        this.userID = userID;
    }

    // Getters for each property
    public int getMileID() {
        return mileID;
    }
    public String getMileName() {
        return mileName;
    }
    public String getMileStatus() {
        return mileStatus;
    }
    public String getMileTargetDate() {
        return mileTargetDate;
    }
    public String getMileCompleteDate() {
        return mileCompleteDate;
    }
    public int getUserID() {
        return userID;
    }
    // Setters for each property (except primary and foreign keys) to allow modification after creation
    public void setMileName(String mileName) {
        this.mileName = mileName;
    }
    public void setMileStatus(String mileStatus) {
        this.mileStatus = mileStatus;
    }
    public void setMileTargetDate(String mileTargetDate) {
        this.mileTargetDate = mileTargetDate;
    }
    public void setMileCompleteDate(String mileCompleteDate) {
        this.mileCompleteDate = mileCompleteDate;
    }
    
} // end of Mile.java