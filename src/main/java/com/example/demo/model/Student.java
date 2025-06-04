package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "name", length = 100)
    private String name;
    
    @Column(name = "email", length = 100, unique = true)
    private String email;
    
    @Column(name = "department", length = 100)
    private String department;
    
    @Column(name = "gpa")
    private double gpa;

    // Default constructor
    public Student() {}

    // Constructor with all fields
    public Student(int id, String name, String email, String department, double gpa) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.gpa = gpa;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }
}