/* Candace Sadowski
 * SDEV435 Applied Software Practice
 * Champlain College Online SUM 2025
 * 
 * Skill Tracker Application
 * Goal.java
 */

// This class represents a goal in the Skill Tracker application.
// It contains properties such as id, name, status, target date, compete date, and userID.
// It contains a full constructor to initialize all properties, along with individual getter and setter methods.
// This class can be used in conjunction with a database access object (DAO) to perform CRUD operations on goals.

// This reusable plain Java object (POJO) is not tied to a specific database or framework. It could be reused in any
// application that requires a goal model, whether data is loaded from a database, an API, JSON, or another source.

public class Goal {
    private int goalID;
    private String goalName;
    private String goalStatus;
    private String goalTargetDate;
    private String goalCompleteDate;
    private int userID;
    private Integer certID;

    // Constructor to initialize the Goal properties
    public Goal(String goalName, String goalStatus, String goalTargetDate, String goalCompleteDate, int userID) {
        this.goalName = goalName;
        this.goalStatus = goalStatus; 
        this.goalTargetDate = goalTargetDate;
        this.goalCompleteDate = goalCompleteDate;
    }
    public Goal(int goalID, String goalName, String goalStatus, String goalTargetDate, String goalCompleteDate, int userID) {
        this(goalName, goalStatus, goalTargetDate, goalCompleteDate, userID);
        this.goalID = goalID;
        this.userID = userID;
    }
    public Goal(int goalID, String goalName, String goalStatus, String goalTargetDate, String goalCompleteDate, int userID, int certID) {
        this(goalName, goalStatus, goalTargetDate, goalCompleteDate, userID);
        this.goalID = goalID;
        this.certID = certID;
    }
    public Goal(String goalName, String goalStatus, String goalTargetDate, String goalCompleteDate, int userID, Integer certID) {
        this.goalName = goalName;
        this.goalStatus = goalStatus;
        this.goalTargetDate = goalTargetDate;
        this.goalCompleteDate = goalCompleteDate;
        this.userID = userID;
        this.certID = certID;
}

    // Getters for each property
    public int getGoalID() {
        return goalID;
    }
    public String getGoalName() {
        return goalName;
    }
    public String getGoalStatus() {
        return goalStatus;
    }
    public String getGoalTargetDate() {
        return goalTargetDate;
    }
    public String getGoalCompleteDate() {
        return goalCompleteDate;
    }
    public int getUserID() {
        return userID;
    }
    public Integer getCertID() {
        return certID;
    }

    // Setters for each property (except primary and foreign keys) to allow modification after creation
    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }
    public void setGoalStatus(String goalStatus) {
        this.goalStatus = goalStatus;
    }
    public void setGoalTargetDate(String goalTargetDate) {
        this.goalTargetDate = goalTargetDate;
    }
    public void setGoalCompleteDate(String goalCompleteDate) {
        this.goalCompleteDate = goalCompleteDate;
    }

} // End of Goal.java