/* Candace Sadowski
 * SDEV435 Applied Software Practice
 * Champlain College Online SUM 2025
 * 
 * Skill Tracker Application
 * User.java
 */

// This class represents a user for my Skill Tracker application.
// It contains properties such as id, first name, and last name.
// It also includes a full constructor to initialize these properties, along with individual getter and setter methods.

// This reusable plain Java object (POJO) is not tied to a specific database or framework. It could be reused in any
// application that requires a user model, whether data is loaded from a database, an API, JSON, or another source.

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

    // Getters for each property
    public int getUserID() {
        return userID;
    }
        public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    // Setters for each property (except primary key) to allow modification after creation
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
} // End of User class