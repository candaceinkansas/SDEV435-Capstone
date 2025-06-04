/* Candace Sadowski
 * SDEV435 Applied Software Practice
 * Champlain College Online SUM 2025
 * 
 * Skill Tracker Application
 * Skill.java
 */

// This class represents a skill in the Skill Tracker application.
// It contains properties such as id, name, proficiency, category, and last used date.
// It also includes a constructor to initialize these properties.

public class Skill {
    private int id;
    private String name;
    private String proficiency;
    private String category;
    private String lastUsed;

    public Skill(String name, String proficiency, String category, String lastUsed) {
        this.name = name;
        this.proficiency = proficiency;
        this.category = category;
        this.lastUsed = lastUsed;  
    }
        public Skill(int id, String name, String proficiency, String category, String lastUsed) {
        this(name, proficiency, category, lastUsed);
        this.id = id;
        }

    // Getters and setters for each property
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    public String getProficiency() {
        return proficiency;
    }

    public String getCategory() {
        return category;
    }

    public String getLastUsed() {
        return lastUsed;
    }
}