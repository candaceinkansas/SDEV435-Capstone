/* Candace Sadowski
 * SDEV435 Applied Software Practice
 * Champlain College Online SUM 2025
 * 
 * Skill Tracker Application
 * Goal.java
 */

// This class represents a goal in the Skill Tracker application.
// It contains properties such as id, name, status, target date, compete date, and userID.
// It also includes a constructor to initialize these properties.

public class Goal {
    private int goalID;
    private String goalName;
    private String goalStatus;
    private String goalTargetDate;
    private String goalCompleteDate;
    private int userID;

    // Constructor to initialize the Goal properties
    public Goal(String goalName, String goalStatus, String goalTargetDate, String goalCompleteDate) {
        this.goalName = goalName;
        this.goalStatus = goalStatus; 
        this.goalTargetDate = goalTargetDate;
        this.goalCompleteDate = goalCompleteDate;
    }
    public Goal(int goalID, String goalName, String goalStatus, String goalTargetDate, String goalCompleteDate, int userID) {
        this(goalName, goalStatus, goalTargetDate, goalCompleteDate);
        this.goalID = goalID;
        this.userID = userID;
    }

    // Getters and setters for each property
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

}