/* Candace Sadowski
 * SDEV435 Applied Software Practice
 * Champlain College Online SUM 2025
 * 
 * Skill Tracker Application
 * Mile.java
 */

// This class represents a milestone in the Skill Tracker application.
// It contains properties such as id, name, status, target date, compete date, and userID.
// It also includes a constructor to initialize these properties.

public class Mile {
    private int mileID;
    private String mileName;
    private String mileStatus;
    private String mileTargetDate;
    private String mileCompleteDate;
    private int userID;

    // Constructor to initialize the Milestone properties
    public Mile(String mileName, String mileStatus, String mileTargetDate, String mileCompleteDate) {
        this.mileName = mileName;
        this.mileStatus = mileStatus; 
        this.mileTargetDate = mileTargetDate;
        this.mileCompleteDate = mileCompleteDate;
    }
    public Mile(int mileID, String mileName, String mileStatus, String mileTargetDate, String mileCompleteDate, int userID) {
        this(mileName, mileStatus, mileTargetDate, mileCompleteDate);
        this.mileID = mileID;
        this.userID = userID;
    }

    // Getters and setters for each property
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

}