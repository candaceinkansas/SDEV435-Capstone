/* Candace Sadowski
 * SDEV435 Applied Software Practice
 * Champlain College Online SUM 2025
 * 
 * Skill Tracker Application
 * User.java
 */

// This class represents a user in the Skill Tracker application.
// It contains properties such as id, first name, and last name.
// It also includes a constructor to initialize these properties.

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    

    // Constructor to initialize the User properties
    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        
    }
        public User(int userID, String firstName, String lastName) {
            this.userID = userID;
            this.firstName = firstName;
            this.lastName = lastName;
        }

    // Getters and setters for each property
    public int getUserID() {
        return userID;
    }
    
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }


}