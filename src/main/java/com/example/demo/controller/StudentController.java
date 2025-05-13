package com.example.demo.controller;

import com.example.demo.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student(1, "Anna", 20));
        students.add(new Student(2, "Bobby", 22));
        students.add(new Student(3, "Matt", 25));
    }

    @GetMapping
    public Object getAllStudents() {
        try {
            return students;
        } catch (Exception e) {
            logger.error("Error while getting students: {}", e.getMessage());
            return "Oops! Please try again";
        }
    }

    @GetMapping("/{id}")
    public Object getStudentById(@PathVariable int id) {
        try {
            return students.stream()
                    .filter(student -> student.getId() == id)
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            logger.error("Error fetching student by ID: {}", e.getMessage());
            return "Oops! Please try again";
        }
    }

    @PostMapping
    public String addStudent(@RequestBody Student student) {
        try {
            students.add(student);
            return "Student added successfully!";
        } catch (Exception e) {
            logger.error("Error adding student: {}", e.getMessage());
            return "Could not add data!!";
        }
    }
}
