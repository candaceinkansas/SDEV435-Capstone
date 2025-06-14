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
            // Initialize the DAO with the SQLite database
            SkillDAO dao = new SkillDAO("skills_tracker.db");
            Scanner scanner = new Scanner(System.in);

            // Check if the user table is empty and prompt to add a new user if it is
            UserDAO userDao = new UserDAO("skills_tracker.db");
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
            while (true) {
                System.out.println(); // Print a blank line for before presenting the menu
                System.out.println("1. Manage Certifications");
                System.out.println("2. Manage Goals");
                System.out.println("3. Manage Milestones");
                System.out.println("4. Generate Reports");
                System.out.println("5. Exit");

                // DELETE THESE
                System.out.println("10. Add a new skill");
                System.out.println("12. View all skills");
                System.out.println("14. View all users"); // Added option to view all users

                

                System.out.print("Please choose an option: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                // Process the user's choice            
                switch (choice) {
                    case 1: // Manage Certifications
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
                        switch (choice) {
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
                                CertDAO certDAO = new CertDAO("skills_tracker.db");
                                certDAO.addCert(new Cert(certName, certStatus, certTargetDate, certCompleteDate, UserID));
                                System.out.println(); // Print a blank line for readability
                                System.out.println("Process complete.  What's next?");
                                break;

                            case 2: // Update Certification
                                // Implementation not yet coded
                                break;

                            case 3: // View Certifications
                                // Implementation not yet coded
                                break;

                            case 4: // Return to Main Menu
                                break;

                            default: // Handle invalid choices
                                System.out.println(); // Print a blank line for readability
                                System.out.println("Invalid choice, please try again.");
                        }
                        break;

                    case 2: // Manage Goals
                        System.out.println(); // Print a blank line for readability
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
                    case 5: // Exit the application
                        System.out.println(); // Print a blank line for readability
                        System.out.println("Exiting the application.");
                        System.out.println(); // Print a blank line before returning to system prompt 
                        scanner.close();
                        System.exit(0);
                        return;
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
                }

            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
