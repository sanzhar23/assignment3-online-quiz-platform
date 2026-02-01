A. SOLID Principles
Single Responsibility Principle (SRP)
Each class has only one responsibility. Quiz and Question store quiz and question data
QuizRepository and QuestionRepository work only with database operations
Controllers manage communication between user and services
Open/Closed Principle (OCP)
The system is open for extension but closed for modification
EasyQuiz and HardQuiz extend the abstract Quiz class
New quiz types can be added without changing existing code
Liskov Substitution Principle (LSP)
Subclasses can replace the base class without breaking the program
EasyQuiz and HardQuiz can be used wherever Quiz is expected
Interface Segregation Principle (ISP)
Interfaces are small and focused
QuizService and QuestionService contain only methods related to their tasks

Generics:Generics are used in the CrudRepository<T> interface
This allows working with different entities in a type-safe way
Lambdas: used to sort quizzes by name
Reflection:print class fields and methods at runtime
this shows how Java can analyze classes dynamically
Interface Default / Static Methods:Interfaces define clear method contracts(some utility logic can be added using default or static methods if needed)

Abstract Class and Subclasses:Quiz is an abstract class (EasyQuiz and HardQuiz are concrete subclasses)
Composition:A quiz contains multiple questions. Questions store quizId to link them to quizzes
Polymorphism: Methods like difficultyWeight() behave differently depending on quiz type. Code works with Quiz without knowing the exact subclass

DB shema <img width="1302" height="871" alt="image" src="https://github.com/user-attachments/assets/cebe374e-2351-4fa2-a9b4-bbea0456260d" />
Constraints:Quiz name cannot be empty
Question text and correct answer cannot be empty
Points must be positive
Duplicate questions are not allowed for the same quiz

Controller:Handles user requests and sends responses
Service:Contains business logic and validation
Repository:Works directly with the database using JDBC

