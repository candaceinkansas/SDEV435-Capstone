# SDEV435-Capstone
Capstone project for SDEV435 Applied Software Practice SUM 2025

## Skills Tracker
A simple Java CLI app that uses SQLite to track progress by category, goal, and milestone. 
Perfect for students, professionals, or anyone looking to organize their skill develpment journey. 
This project creates skills_tracker.db automatically in the project root if it doesn't exist. Run the app to initialize it.

## Prerequisites
The only requirement is **Java Development Kit (JDK) 8 or higher** installed on your system.
To verify Java installation, run:
  bash
  java -version
  javac -version

## How to Run
```bash
javac -cp "lib/sqlite-jdbc-3.49.1.0.jar" src/*.java
java -cp "lib/sqlite-jdbc-3.49.1.0.jar;src" Main

1. Download the Project
  - click the green "Code" button above
  - select "download ZIP"
  - Extract teh ZIP file to your preferred location

2. Navigate to the Project Directory
  - bash
  - cd path/to/SDEV435-Capstone

3. Compile the Application
  ```bash
javac -cp "lib/sqlite-jdbc-3.49.1.0.jar" src/*.java

4. Run the Application Windows:
```bash
java -cp "lib/sqlite-jdbc-3.49.1.0.jar;src"Main
Mac/Linux:
```bash
java -cp "lib/sqlite-jdbc-3.49.1.0.jar:src"Main

5. Start Tracking!
