# SDEV435-Capstone
Capstone project for SDEV435 Applied Software Practice SUM 2025

## Skills Tracker
A simple Java CLI app that uses SQLite to track progress by category, goal, and milestone
This project creates skills_tracker.db automatically in the project root if it doesn't exist.
Run the app to initialize it.

## How to Run
```bash
javac -cp "lib/sqlite-jdbc-3.49.1.0.jar" src/*.java
java -cp "lib/sqlite-jdbc-3.49.1.0.jar;src" Main

