/* Candace Sadowski
 * SDEV435 Applied Software Practice
 * Champlain College Online SUM 2025
 * 
 * Skill Tracker Application
 * Main.java
 */

 // Import necessary utilities
 // import java.util.List;   -----------------NOT USED
 import java.util.Scanner;
 
 // Main class to run the Skill Tracker application
 public class Main {
    public static void main(String[] args) {
        try {
            // Initialize the DAO for all tables within the SQLite database
            SkillDAO dao = new SkillDAO("skills_tracker.db");   // DELETE THIS ONE WHEN DONE TESTING
            CertDAO certDAO = new CertDAO("skills_tracker.db");
            GoalDAO goalDAO = new GoalDAO("skills_tracker.db");
            MileDAO mileDAO = new MileDAO("skills_tracker.db");
            UserDAO userDao = new UserDAO("skills_tracker.db");

            // Create a Scanner object to read user input
            Scanner scanner = new Scanner(System.in);

            // Initialize flags to control the loops for the main menu and sub-menus
            boolean continueOuter = true; // Flag to control the outer loop for the main menu
            boolean continueCertInner = true; // Flag to control the inner loop for certification management
            boolean continueGoalInner = true; // Flag to control the inner loop for goal management
            boolean continueMileInner = true; // Flag to control the inner loop for milestone management
            boolean continueReportInner = true; // Flag to control the inner loop for report generation

            // Check if the user table is empty and prompt to add a new user if it is
            if (userDao.getAllUsers().isEmpty()) {
                System.out.println("No users found. Please add a new user first.");
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();
                
                // Create a new User object and add it to the database
                userDao.addUser(new User(firstName, lastName));
                System.out.println("User added successfully.");
            }

            // Print the greeting and menu options
            System.out.println(); // Print a blank line for readability
            System.out.println("Welcome to the Skill Tracker Application!");
            
            // Outer loop for the main menu
            while (continueOuter) { // Outer loop for the main menu

                System.out.println(); // Print a blank line for before presenting the menu
                System.out.println("1. Manage Certifications");
                System.out.println("2. Manage Goals");
                System.out.println("3. Manage Milestones");
                System.out.println("4. Generate Reports");
                System.out.println("5. Exit");


                // DELETE THESE AFTER TESTING
                System.out.println("10. Add a new skill");
                System.out.println("12. View all skills");
                System.out.println("14. View all users");

                

                System.out.print("Please choose an option: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                // Process the user's choice            
                switch (choice) {
                    case 1: // Manage Certifications
                    
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
                            scanner.nextLine(); // Consume newline

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
                                    System.out.print("Enter user ID: ");
                                    int UserID = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline

                                    // Create a new Certification object and add it to the database
                                    
                                    certDAO.addCert(new Cert(certName, certStatus, certTargetDate, certCompleteDate, UserID));
                                    System.out.println(); // Print a blank line for readability
                                    System.out.println("Process complete.  What's next?");
                                    break;

                                case 2: // Update Certification
                                    // Implementation not yet coded
                                    break;

                                case 3: // View Certifications
                                    System.out.println(); // Print a blank line for readability
                                    System.out.println("Certifications:");
                                    for (Cert cert : certDAO.getAllCerts()) {
                                        System.out.println("- " + cert.getCertName());
                                    }
                                    break;

                                case 4: // Return to Main Menu
                                    continueCertInner = false; // allows exit of the certification management loop
                                    break;
                                
                                default: // Handle invalid choices
                                    System.out.println(); // Print a blank line for readability
                                    System.out.println("Invalid choice, please try again.");
                            }
                        } break; // check continueCertInner flag and either return to the main menu or continue with the certification management loop
                    // End of case 1: Manage Certifications

                    case 2: // Manage Goals
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
                            scanner.nextLine(); // Consume newline

                            // Handle goal management options here                            
                            switch (goalChoice) {
                                case 1: // Add Goal
                                    System.out.println(); // Print a blank line for readability
                                    System.out.print("Enter goal name: ");
                                    String goalName = scanner.nextLine();
                                    System.out.print("Enter goal description: ");
                                    String goalDescription = scanner.nextLine();
                                    System.out.print("Enter target date (YYYY-MM-DD): ");
                                    String goalTargetDate = scanner.nextLine();
                                    System.out.print("Enter completion date (YYYY-MM-DD): ");
                                    String goalCompleteDate = scanner.nextLine();
                                    System.out.print("Enter user ID: ");
                                    int UserID = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline

                                    // Create a new Goal object and add it to the database
                                    goalDAO.addGoal(new Goal(goalName, goalDescription, goalTargetDate, goalCompleteDate, UserID));
                                    System.out.println(); // Print a blank line for readability
                                    System.out.println("Process complete.  What's next?");
                                    break;System.out.println(); // Print a blank line for readability
                        System.out.println("Manage Goals");
                        System.out.println("1. Add Goal");
                        System.out.println("2. View Goals");
                        System.out.println("3. Update Goal");
                        System.out.println("4. Delete Goal");
                        System.out.print("Choose an option: ");
                        int goalChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        
                        // Handle goal management options here
                        // (Implementation not yet coded) 
                        break;
                    // End of case 2: Manage Goals
                    
                    case 3: // Manage Milestones
                        System.out.println(); // Print a blank line for readability
                        System.out.println("Manage Milestones");
                        System.out.println("1. Add Milestone");
                        System.out.println("2. View Milestones");
                        System.out.println("3. Update Milestone");
                        System.out.println("4. Return to Main Menu");
                        System.out.print("Choose an option: ");
                        int MileChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        
                        // Handle milestone management options here
                        // (Implementation not yet coded)
                        break;
                    // End of case 3: Manage Milestones
                    
                    case 4: // Generate Reports
                        System.out.println(); // Print a blank line for readability
                        System.out.println("Generate Reports");
                        System.out.println("1. Generate Certification Report");
                        System.out.println("2. Generate Goal Report");
                        System.out.println("3. Generate Skill Report");
                        System.out.print("Choose an option: ");
                        int reportChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        
                        // Handle report generation options here
                        // (Implementation not yet coded)
                        break;
                    // End of case 4: Generate Reports

                    case 5: // Exit the application
                        System.out.println(); // Print a blank line for readability
                        // Close all DAO connections and the scanner before exiting
                        scanner.close();
                        dao.close();  /// DELETE THIS ONE WHEN DONE TESTING
                        certDAO.close();
                        userDao.close();
                        goalDAO.close();
                        mileDAO.close(); 
                        System.out.println("Exiting the application.");
                        System.out.println(); // Print a blank line before returning to system prompt 
                        System.exit(0);
                        return;
                    // End of case 5: Exit the application



                    case 10: // Add a new skill
                        System.out.println(); // Print a blank line for readability
                        System.out.print("Enter skill name: ");
                        String skillName = scanner.nextLine();
                        System.out.print("Enter skill proficiency: ");
                        String proficiency = scanner.nextLine();
                        System.out.print("Enter skill category: ");
                        String category = scanner.nextLine();
                        System.out.print("Enter last used date (YYYY-MM-DD): ");
                        String lastUsed = scanner.nextLine();
        
                        // Create a new Skill object and add it to the database
                        dao.addSkill(new Skill(skillName, proficiency, category, lastUsed));
                        System.out.println(); // Print a blank line for readability
                        System.out.println("Process complete. What next?");
                        break;
                    case 12: // View all skills
                        System.out.println(); // Print a blank line for readability
                        System.out.println("Skills:");
                        for (Skill skill : dao.getAllSkills()) {
                            System.out.println("- " + skill.getName());
                            }
                        break;
                    case 14: // View all users
                        System.out.println(); // Print a blank line for readability
                        System.out.println("Users:");
                        for (User user : userDao.getAllUsers()) {
                            System.out.println("- " + user.getFirstName() + " " + user.getLastName());
                        }
                        break;
                    
                    
                    
                    default: // Handle invalid choices
                        System.out.println(); // Print a blank line for readability
                        System.out.println("Invalid choice, please try again.");       
                } // End of switch statement for main menu options

            } // End of outer loop for the main menu

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } // End of try-catch block for main method

    } // End of main method

} // End of Main class
// End of Main.java