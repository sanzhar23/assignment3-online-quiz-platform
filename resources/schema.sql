DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS quizzes;

CREATE TABLE quizzes (
 id SERIAL PRIMARY KEY,
 name VARCHAR(150) NOT NULL UNIQUE,
 level VARCHAR(20) NOT NULL,
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 CONSTRAINT chk_level CHECK (level IN ('EASY', 'HARD'))
);

CREATE TABLE questions (
   id SERIAL PRIMARY KEY,
   quiz_id INT NOT NULL,
   text VARCHAR(500) NOT NULL,
   correct_answer VARCHAR(200) NOT NULL,
   points INT NOT NULL,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

   CONSTRAINT fk_quiz
       FOREIGN KEY (quiz_id)
           REFERENCES quizzes(id)
           ON DELETE CASCADE,

   CONSTRAINT chk_points CHECK (points > 0)
);

INSERT INTO quizzes (name, level) VALUES
('Java Basics', 'EASY'),
('OOP Advanced', 'HARD');

INSERT INTO questions (quiz_id, text, correct_answer, points) VALUES
(1, 'What does JVM stand for?', 'Java Virtual Machine', 1),
(1, 'Which keyword is used to inherit a class in Java?', 'extends', 1),
(2, 'What is polymorphism?', 'Ability to take many forms', 2),
(2, 'Difference between abstract class and interface?', 'Abstract can have state; interface defines contract', 2);
