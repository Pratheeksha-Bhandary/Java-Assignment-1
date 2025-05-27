-- CREATE TABLE
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    department VARCHAR(100),
    gpa NUMERIC(3, 2)
);

-- INSERT
INSERT INTO students (name, email, department, gpa)
VALUES 
('Alice Johnson', 'alice@example.com', 'Engineering', 3.8),
('Bob Smith', 'bob@example.com', 'HR', 3.2);

-- SELECT
SELECT * FROM students;

-- UPDATE
UPDATE students
SET gpa = 4.0
WHERE name = 'Alice Johnson';

-- DELETE
DELETE FROM students
WHERE name = 'Bob Smith';
