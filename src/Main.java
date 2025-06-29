/* Candace Sadowski
 * SDEV435 Applied Software Practice
 * Champlain College Online SUM 2025
 * 
 * Skill Tracker Application
 * Main.java
 */

 // Import necessary utilities
 import java.sql.SQLException;
 import java.util.Scanner;
 
 // Main class to run the Skill Tracker application
 public class Main {
    public static void main(String[] args) {
        try {
            // Initialize the DAO for all tables within the SQLite database
            // If the database does not exist, it will be created
            // If the tables do not exist, they will be created
            UserDAO userDao = new UserDAO("skills_tracker.db");
            CertDAO certDAO = new CertDAO("skills_tracker.db");
            GoalDAO goalDAO = new GoalDAO("skills_tracker.db");
            MileDAO mileDAO = new MileDAO("skills_tracker.db");
            

            // Create a Scanner object to read user input
            Scanner scanner = new Scanner(System.in);

            // Check if the user table is empty and prompt to add a new user if it is
            if (userDao.getAllUsers().isEmpty()) {
                System.out.println("No users found. Please add a new user first.");
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();
                
                // Create a new User object and add it to the database
                userDao.addUser(new User(firstName, lastName));
                System.out.println(); // Print a blank line for readability
                System.out.println("Nice to meet you, " + firstName + "! You have been added to the database.");
            }

            // Print the greeting and menu options
            System.out.println(); // Print a blank line for readability
            System.out.println("Welcome to the Skill Tracker Application!");
            
            // Outer loop for the main menu
            boolean continueOuter = true; // create and initiate flag to control the outer loop for the main menu
            while (continueOuter) { // Outer loop for the main menu

                System.out.println(); // Print a blank line for before presenting the menu
                System.out.println("1. Manage Certifications");
                System.out.println("2. Manage Goals");
                System.out.println("3. Manage Milestones");
                System.out.println("4. Generate Reports");
                System.out.println("5. Add New User");
                System.out.println("6. List All Users");
                System.out.println("7. Exit");

                System.out.print("Please choose an option: ");
                
                int choice = scanner.nextInt(); //reads integer, leaves newline in the buffer
                scanner.nextLine(); // consumes the leftover newline character

                // Process the user's choice            
                switch (choice) {
                    case 1: // Manage Certifications
                        boolean continueCertInner = true; // Flag to control the inner loop for certification management
                        while (continueCertInner) { // Inner loop for certification management
                                                        
                            // Print the certification management menu
                            System.out.println(); // Print a blank line for readability
                            System.out.println("Manage Certifications");
                            System.out.println("1. Add Certification");
                            System.out.println("2. Update Certification");
                            System.out.println("3. View Certifications");
                            System.out.println("4. Return to Main Menu");
                            System.out.print("Please enter your selection (1-4): ");
                            int certChoice = scanner.nextInt();
                            scanner.nextLine(); // consumes the leftover newline character

                            // Handle certification management options here                            
                            switch (certChoice) {
                                case 1: // Add Certification
                                    System.out.println(); // Print a blank line for readability
                                    System.out.print("Enter certification name: ");
                                    String certName = scanner.nextLine();
                                    System.out.print("Enter certification status (Not Started, In Progress, Completed): ");
                                    String certStatus = scanner.nextLine();
                                    System.out.print("Enter target date (YYYY-MM-DD): ");
                                    String certTargetDate = scanner.nextLine();
                                    System.out.print("Enter completion date (YYYY-MM-DD): ");
                                    String certCompleteDate = scanner.nextLine();
                                    System.out.print("Enter user number: ");
                                    int userID = scanner.nextInt();
                                    scanner.nextLine(); // consumes the leftover newline character

                                    // Create a new Certification object and add it to the database
                                    certDAO.addCert(new Cert(certName, certStatus, certTargetDate, certCompleteDate, userID));
                                    System.out.println(); // Print a blank line for readability
                                    System.out.println("Process complete.  What's next?");
                                    break;
                                    // End of case 1: Add Certification

                                case 2: // Update Certification
                                    // Implementation not yet coded
                                    break;
                                    // End of case 2: Update Certification

                                case 3: // View Certifications
                                    System.out.println(); // Print a blank line for readability

                                    // Print all users to help the user select a user for certification management
                                    for (User user : userDao.getAllUsers()) {
                                        System.out.println("\t user number " + user.getUserID() + ": " + user.getFirstName() + " " + user.getLastName());
                                    } 
                                    
                                    // Prompt the user to select a user to view certifications
                                    System.out.println(); // Print a blank line for readability
                                    System.out.println("Which user's certifications would you like to view? Please enter the user number: ");
                                    int selectedUserID = scanner.nextInt();
                                    scanner.nextLine(); // consumes the leftover newline character
                                    
                                    // Print all certifications for the selected user
                                    System.out.println(); // Print a blank line for readability
                                    java.util.List<Cert> certs = certDAO.getCertByUserID(selectedUserID);
                                    System.out.println("Certifications for User " + selectedUserID + ":");
                                    for (Cert cert : certs) {
                                        System.out.println("  Certification " + cert.getCertID() + ": " + cert.getCertName() + ", Status: " + cert.getCertStatus() + ", Target Date: " + cert.getCertTargetDate() + ", Completion Date: " + cert.getCertCompleteDate());
                                    }
                                    break;
                                    // End of case 3: View Certifications

                                    // End of case 4: View Users
                                case 4: // Return to Main Menu
                                    continueCertInner = false; // allows exit of the certification management loop
                                    break;
                                    // End of case 4: Return to Main Menu
                                
                                default: // Handle invalid choices
                                    System.out.println(); // Print a blank line for readability
                                    System.out.println("Invalid choice, please try again.");
                            }
                        } break; // check continueCertInner flag and either return to the main menu or continue with the certification management loop
                    // End of case 1: Manage Certifications

                    case 2: // Manage Goals
                            boolean continueGoalInner = true; // create and initiate flag
                            while (continueGoalInner) { // Inner loop for goal management

                            // Print the goal management menu
                            System.out.println(); // Print a blank line for readability
                            System.out.println("Manage Goals");
                            System.out.println("1. Add Goal");
                            System.out.println("2. Update Goal");
                            System.out.println("3. View Goals");
                            System.out.println("4. Return to Main Menu");
                            System.out.print("Please enter your selection (1-4): ");
                            int goalChoice = scanner.nextInt();
                            scanner.nextLine(); // consumes the leftover newline character

                            // Handle goal management options here                            
                            switch (goalChoice) {
                                case 1: // Add Goal
                                    System.out.println(); // Print a blank line for readability
                                    System.out.print("Enter goal name: ");
                                    String goalName = scanner.nextLine();
                                    System.out.print("Enter goal status (Not Started, In Progress, Completed): ");
                                    String goalStatus = scanner.nextLine();
                                    System.out.print("Enter target date (YYYY-MM-DD): ");
                                    String goalTargetDate = scanner.nextLine();
                                    System.out.print("Enter completion date (YYYY-MM-DD): ");
                                    String goalCompleteDate = scanner.nextLine();
                                    
                                    System.out.println(); // Print a blank line for readability
                                    System.out.print("Which user has this goal?\n");
                                    // Print all users to help the user select a user for certification management
                                        for (User user : userDao.getAllUsers()) {
                                            System.out.println("\t user number " + user.getUserID() + ": " + user.getFirstName() + " " + user.getLastName());
                                        } 
                                        // Prompt the user to select a user to view certifications
                                        System.out.println("Please enter the user number: ");
                                        int selectedUserID = scanner.nextInt();
                                        scanner.nextLine(); // consumes the leftover newline character
                                    
                                    System.out.println(); // Print a blank line for readability
                                    System.out.print("Is this goal tied to a certification? (Y/N): ");
                                    String certResponse = scanner.next().trim().toUpperCase(); // Read the input and convert it to uppercase (expecting y or n)
                                    
                                    // Initialize CertID to null, which will be used if the goal is not tied to a certification
                                        Integer CertID = null;
                                        if (certResponse.equals("Y")) {
                                            // Print all certifications for selected user to help the user select a cert for goal management
                                            System.out.println(); // Print a blank line for readability
                                            java.util.List<Cert> certs = certDAO.getCertByUserID(selectedUserID);
                                            System.out.println("Certifications for User " + selectedUserID + ":");
                                                for (Cert cert : certs) {
                                                    System.out.println("  Certification " + cert.getCertID() + ": " + cert.getCertName() + ", Status: " + cert.getCertStatus() + ", Target Date: " + cert.getCertTargetDate() + ", Completion Date: " + cert.getCertCompleteDate());
                                                }
                                            System.out.print("Enter certification ID: ");
                                            CertID = scanner.nextInt();
                                            scanner.nextLine(); // Consume the newline character left by nextInt()
                                        }
                                    // Create a new Goal object and add it to the database
                                    goalDAO.addGoal(new Goal(goalName, goalStatus, goalTargetDate, goalCompleteDate, selectedUserID, CertID));
                                    System.out.println(); // Print a blank line for readability
                                    System.out.println("Process complete.  What's next?");
                                    break;  // End of case 1: Add Goal

                                case 2: // Update Goal Status
                                    System.out.println(); // Print a blank line for readability
                                    System.out.print("Update functionality not yet coded"); // Print a blank line for readability
                                    System.out.println(); // Print a blank line for readability
                                    System.out.println("Process complete.  What's next?");
                                    break; // End of case 2: Update Goal Status

                                case 3: // View Goals
                                    System.out.println(); // Print a blank line for readability
                                    System.out.println("Goals:");
                                    for (Goal goal : goalDAO.getAllGoals()) {
                                        System.out.println("  User " + goal.getUserID() + " " + " Goal " + goal.getGoalID() + ": " + goal.getGoalName() +
                                        ", Status: " + goal.getGoalStatus() + ", Target Date: " + goal.getGoalTargetDate() + ", Completion Date: " + goal.getGoalCompleteDate());
                                    }
                                    
                                    
/* Adding logic to view goals by user ID and certification ID
 * If this works, it will replace the above for loop
 */
                                    System.out.println(); // Print a blank line for readability
                                    // Print all goals for all users
                                    System.out.println("Goals for all users:");
                                    goalDAO.getGoalsWithCertID(); // Call the method to retrieve goals by user ID and certification ID
                                    // for (Goal goal : goalDAO.getGoalsByUserIDAndCertID()) {
                                    //     System.out.println("  User " + goal.getUserID() + ", CertID: " + goal.getCertID() + ", Goal: " + goal.getGoalID() + ": " + goal.getGoalName() + ", Status: " + goal.getGoalStatus() + ", Target Date: " + goal.getGoalTargetDate() + ", Completion Date: " + goal.getGoalCompleteDate());
                                    // }
/*
 * Delete above section if it doesn't work.
 * If it does work, consider replacing the original for loop with the above code. Or, have 2 options for returning goals: standalone goals or goals by user, including certIDs.
 */

                                    break; // End of case 3: View Goals

                                case 4: // Return to Main Menu
                                    continueGoalInner = false; // allows exit of the goal management loop
                                    break; // End of case 4: Return to Main Menu

                                default: // Handle invalid choices
                                    System.out.println(); // Print a blank line for readability
                                    System.out.println("Invalid choice, please try again.");
                            }
                        } break; // End of case 2: Manage Goals
                        // check continueGoalInner flag and either return to the main menu or continue with the goal management loop
                    // End of case 2: Manage Goals
                    
                    case 3: // Manage Milestones
                        boolean continueMileInner = true; // Flag to control the inner loop for milestone management
                        while (continueMileInner) {

                            System.out.println(); // Print a blank line for readability
                            System.out.println("Manage Milestones");
                            System.out.println("1. Add Milestone");
                            System.out.println("2. Update Milestones");
                            System.out.println("3. View Milestone");
                            System.out.println("4. Return to Main Menu");
                            System.out.print("Choose an option: ");
                            int mileChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                        
                            switch (mileChoice) {
                                case 1: // Add Milestone
                                    System.out.println(); // Print a blank line for readability
                                    System.out.print("Enter milestone name: ");
                                    String mileName = scanner.nextLine();
                                    System.out.print("Enter milestone status (Not Started, In Progress, Completed): ");
                                    String mileStatus = scanner.nextLine();
                                    System.out.print("Enter target date (YYYY-MM-DD): ");
                                    String mileTargetDate = scanner.nextLine();
                                    System.out.print("Enter completion date (YYYY-MM-DD): ");
                                    String mileCompleteDate = scanner.nextLine();
                                    System.out.print("Enter goal ID: ");
                                    int goalID = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline

                                    // Create a new Milestone object and add it to the database
                                    mileDAO.addMile(new Mile(mileName, mileStatus, mileTargetDate, mileCompleteDate, goalID));
                                    System.out.println(); // Print a blank line for readability
                                    System.out.println("Process complete.  What's next?");
                                    break;

                                case 2: // Update Milestone Status
                                    // Implementation not yet coded
                                    break;
                                
                                case 3: // View Milestones
                                    System.out.println(); // Print a blank line for readability
                                    System.out.println("Milestones:");
                                    for (Mile mile : mileDAO.getAllMiles()) {
                                        System.out.println("- " + mile.getMileName());
                                    }
                                    break;

                                case 4: // Return to Main Menu
                                    continueMileInner = false; // allows exit of the milestone management loop
                                    break;

                                default: // Handle invalid choices
                                    System.out.println(); // Print a blank line for readability
                                    System.out.println("Invalid choice, please try again.");
                            }
                        } break; // check continueMileInner flag and either return to the main menu or continue with the milestone management loop
                        // End of case 3: Manage Milestones
                    
                    case 4: // Generate Reports
                        boolean continueReportInner = true; // Flag to control the inner loop for report generation
                        while (continueReportInner) {
                            System.out.println(); // Print a blank line for readability
                            System.out.println("Generate Reports");
                            System.out.println("1. Generate Certification Report");
                            System.out.println("2. Generate Goal Report");
                            System.out.println("3. Generate Skill Report");
                            System.out.println("4. Return to Main Menu");
                            System.out.print("Choose an option: ");
                            int reportChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            switch (reportChoice) {
                                case 1: // Generate Certification Report
                                    // Implementation not yet coded
                                    break;

                                case 2: // Generate Goal Report
                                    // Implementation not yet coded
                                    break;

                                case 3: // Generate Skill Report
                                    // Implementation not yet coded
                                    break;

                                case 4: // Return to Main Menu
                                    continueReportInner = false; // allows exit of the report generation loop
                                    break;

                                default: // Handle invalid choices
                                    System.out.println(); // Print a blank line for readability
                                    System.out.println("Invalid choice, please try again.");
                            }
                        } break;
                        // End of case 4: Generate Reports

                    case 5: // Add New User
                        System.out.println(); // Print a blank line for readability
                        System.out.print("Enter first name: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Enter last name: ");
                        String lastName = scanner.nextLine();
                        userDao.addUser(new User(firstName, lastName));
                        System.out.println(); // Print a blank line for readability
                        System.out.println("Process complete.  What's next?");
                        break;
                    
                    case 6: // View User Information
                        System.out.println(); // Print a blank line for readability
                        System.out.println("User Information:");
                        for (User user : userDao.getAllUsers()) {
                            System.out.println("\t user number " + user.getUserID() + ": " + user.getFirstName() + " " + user.getLastName());
                        } break;
                        // End of case 6: View User Information

                    case 7: // Exit the application
                        System.out.println(); // Print a blank line for readability
                        // Close scanner and close all DAO connections before exiting
                        scanner.close();
                        certDAO.close();
                        userDao.close();
                        goalDAO.close();
                        mileDAO.close(); 
                        System.out.println("Exiting the application.");
                        System.out.println(); // Print a blank line before returning to system prompt 
                        System.exit(0);
                        return;
                        // End of case 7: Exit the application

                    default: // Handle invalid choices
                        System.out.println(); // Print a blank line for readability
                        System.out.println("Invalid choice, please try again.");       
                } // End of switch statement for main menu options

            } // End of outer loop for the main menu
            scanner.close(); // Close the scanner to prevent resource leaks
            
        } catch (SQLException e) {
            System.err.println("A database error occurred: " + e.getMessage());
            e.printStackTrace();
            
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } // End of try-catch block for main method

    } // End of main method

} // End of Main class
// End of Main.java