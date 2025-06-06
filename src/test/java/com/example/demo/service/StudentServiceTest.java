package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student(1, "Alice", "alice@example.com", "Engineering", 3.8);
    }

    @Test
    void testGetAllStudents() {
        when(studentRepository.findAll()).thenReturn(List.of(student));
        List<Student> result = studentService.getAllStudents();
        assertEquals(1, result.size());
    }

    @Test
    void testGetStudentById_Found() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        Optional<Student> result = studentService.getStudentById(1);
        assertTrue(result.isPresent());
        assertEquals("Alice", result.get().getName());
    }

    @Test
    void testGetStudentById_NotFound() {
        when(studentRepository.findById(1)).thenReturn(Optional.empty());
        Optional<Student> result = studentService.getStudentById(1);
        assertTrue(result.isEmpty());
    }

    @Test
    void testAddStudent() {
        when(studentRepository.save(student)).thenReturn(student);
        String result = studentService.addStudent(student);
        assertEquals("Student added successfully!", result);
    }

    @Test
    void testUpdateStudent_Found() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student updated = new Student(1, "Alice Smith", "alice.smith@example.com", "IT", 3.9);
        String result = studentService.updateStudent(1, updated);
        assertEquals("Student updated successfully!", result);
    }

    @Test
    void testUpdateStudent_NotFound() {
        when(studentRepository.findById(1)).thenReturn(Optional.empty());
        String result = studentService.updateStudent(1, student);
        assertEquals("Student not found!", result);
    }

    @Test
    void testDeleteStudent_Found() {
        when(studentRepository.existsById(1)).thenReturn(true);
        doNothing().when(studentRepository).deleteById(1);
        String result = studentService.deleteStudent(1);
        assertEquals("Student deleted!", result);
    }

    @Test
    void testDeleteStudent_NotFound() {
        when(studentRepository.existsById(1)).thenReturn(false);
        String result = studentService.deleteStudent(1);
        assertEquals("Student not found!", result);
    }
}
