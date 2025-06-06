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
            //Initialize the DAO with the SQLite database
            SkillDAO dao = new SkillDAO("skills_tracker.db");
            Scanner scanner = new Scanner(System.in);

            //print the greeting and menu options
            System.out.println(); // Print a blank line for readability
            System.out.println("Welcome to the Skill Tracker Application!");
            while (true) {
                System.out.println(); // Print a blank line for before presenting the menu
                System.out.println("1. Add a new skill");
                System.out.println("2. View all skills");
                System.out.println("3. Exit");

                System.out.print("Please choose an option: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                // Process the user's choice            
                switch (choice) {
                    case 1:
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
                        System.out.println("Skill added successfully!");
                        break;
                    case 2:
                        System.out.println(); // Print a blank line for readability
                        System.out.println("Skills:");
                        for (Skill skill : dao.getAllSkills()) {
                            System.out.println("- " + skill.getName());
                            }
                        break;
                    case 3:
                        System.out.println(); // Print a blank line for readability
                        System.out.println("Exiting the application.");
                        System.out.println(); // Print a blank line before returning to system prompt 
                        scanner.close();
                        System.exit(0);
                        return;
                    default:
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
