# SDEV435-Capstone
Capstone project for SDEV435 Applied Software Practice SUM 2025

## Skills Tracker
A simple Java CLI app that uses SQLite to track progress by category, goal, and milestone. 
Perfect for students, professionals, or anyone looking to organize their skill develpment journey. 
This project creates skills_tracker.db automatically in the project root if it doesn't exist. Run the app to initialize it.

## Prerequisites
The only requirement is **Java Development Kit (JDK) 21 or higher** installed on your system.
To verify Java installation, run (bash):
  ```bash
  java -version
  javac -version
```
If you do not have JDK21 installed, but your machine includes a Windows Package Manager (winget) you can install it through the command window:
1. Open PowerShell or Command Prompt as Administrator
2. Use the winget search for OpenJDK:
```bash
winget search Microsoft.OpenJDK
```
3. Install OpenJDK21:
```bash
winget install Microsoft.OpenJDK.21
```
4. Verify Installation:
```bash
javac -version
```
This should display information about the installed Java 21 version.


## Installation and Setup
1. Download the Project
    - click the green "Code" button above
    - select "download ZIP"
    - Extract the ZIP file to your preferred location

2. Navigate to the Project Directory (bash)
  ```bash
      cd path/to/SDEV435-Capstone
```

3. Compile the Application (bash)
  ```bash
      javac -cp "lib/sqlite-jdbc-3.49.1.0.jar" src/*.java
```
4. Run the Application **Windows** (bash):
```bash
    java -cp "lib/sqlite-jdbc-3.49.1.0.jar;src" Main
```
4. Run the Application **Mac/Linux** (bash):
```bash
    java -cp "lib/sqlite-jdbc-3.49.1.0.jar:src" Main
```
5. Start Tracking!

## Project Structure
<img width="969" height="288" alt="image" src="https://github.com/user-attachments/assets/c98cd327-233c-43a0-84a4-4e538e968e44" />

## How to Use
1. Launch the application using the run command above
2. Create categories to organize your skills (i.e. "Programming", "Languages", "Certifications" etc.)
3. Set goals within each category (e.g. "Learn Python", "Complete AWS Certification")
4. Add milestones to break down each goal into manageable steps
5. Track progress as you complete each milestone
6. Review your achievements and plan next steps

## Technical Details
 - **Language**: Java
 - **Database**: SQLite (embedded, no server required)
 - **Dependencies**: SQLite JDBC Driver (included in project)
 - **Architecture**: Command Line Interface (CLI)
 - **Data Persistence**: Local SQLite database file

## About This Project
This application was developed as a capstone project for SDEV435 Applied Software Practice (Summer 2025). It demonstrates:
 - Object-oriented programming principles in Java
 - Database integration with SQLite
 - User interface design for command-line applications
 - File I/O and data persistence
 - Error handling and user input validation
 - Software project organization and documentation

## Future Enhancements
Potential improvements for future versions:
 - Export progress reports to CSV/PDF
 - Set deadline reminders for goals
 - Add progress visualization/charts
 - Multi-user support
 - Web-based interface
 - Mobile companion app

## License
This project is created for educational purposes as part of coursework requirements.

## **Happy Learning!**
_Track your progress, achieve your goals, and celebrate your growth!_
