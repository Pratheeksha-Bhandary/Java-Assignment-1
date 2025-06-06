package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        student = new Student(1, "Alice", "alice@example.com", "Engineering", 3.8);
    }

    @Test
    void testGetAllStudents() throws Exception {
        when(studentService.getAllStudents()).thenReturn(List.of(student));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetStudentById_Found() throws Exception {
        when(studentService.getStudentById(1)).thenReturn(Optional.of(student));

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    void testGetStudentById_NotFound() throws Exception {
        when(studentService.getStudentById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void testAddStudent() throws Exception {
        when(studentService.addStudent(any(Student.class))).thenReturn("Student added successfully!");

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(student)))
                .andExpect(status().isOk())
                .andExpect(content().string("Student added successfully!"));
    }

    @Test
    void testUpdateStudent() throws Exception {
        when(studentService.updateStudent(eq(1), any(Student.class))).thenReturn("Student updated successfully!");

        mockMvc.perform(put("/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(student)))
                .andExpect(status().isOk())
                .andExpect(content().string("Student updated successfully!"));
    }

    @Test
    void testDeleteStudent() throws Exception {
        when(studentService.deleteStudent(1)).thenReturn("Student deleted!");

        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Student deleted!"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
