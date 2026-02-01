# assignment3-online-quiz-platform
This project is a simple Online Quiz Platform. Program allows creating quizzes with different difficulty levels and adding questions to each quiz
All data is stored in a PostgreSQL database, and the program connects to the database using JDBC. Quiz and Question are main parts of the system
Each quiz has a name and difficulty level, and each question belongs to a quiz and has text, correct answer and points

Program is divided into packages:
The model package contains classes that describe quizzes and questions
Repository package is responsible for working with the database
The db package contains the database connection
Custom exceptions are used to handle errors
Main class is used to show how the program work
<img width="850" height="395" alt="image" src="https://github.com/user-attachments/assets/2aae3b3d-b616-4b11-93d2-ec52367a6c01" />

