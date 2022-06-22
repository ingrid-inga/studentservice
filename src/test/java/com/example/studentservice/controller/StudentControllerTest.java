package com.example.studentservice.controller;

import com.example.studentservice.exception.NoEntityException;
import com.example.studentservice.model.Student;
import com.example.studentservice.repository.StudentRepository;
import com.example.studentservice.service.StudentServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest

class StudentControllerTest {

    @MockBean
    StudentServiceImpl studentServiceImpl;

    @MockBean
    StudentRepository studentRepository;

    @Autowired
    private MockMvc mockMvc;

    List<Student> listStudents;
    Student student;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        student = new Student(1L, "China", "Nicole", LocalDate.of(2001, 9, 24));
        listStudents = new ArrayList<>();
        listStudents.add(new Student(1L, "Spain", "Julieta", LocalDate.of(1998, 9, 23)));
        listStudents.add(new Student(2L, "Guatemala", "Gonzalo", LocalDate.of(2008, 7, 30)));
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void createStudent() throws Exception {
       Student newStudent = new Student(null, "Africa", "Ana", LocalDate.of(2003, 12, 9));
       Student createdStudent = new Student(1L, "Africa", "Ana", LocalDate.of(2003, 12, 9));

        when(studentServiceImpl.createStudent(any())).thenReturn(createdStudent);

        mockMvc.perform(MockMvcRequestBuilders.post("/student/create").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newStudent)))
        .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.surname").value("Africa"));
    }

    @Test
    void getAllStudents() throws Exception {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(15L, "Africa", "Ana", LocalDate.of(2003, 12, 9)));
        studentList.add(new Student(20L, "Brasil", "Bea", LocalDate.of(1998, 8, 18)));
        when(studentServiceImpl.findAll()).thenReturn(studentList);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/list").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$[0].surname").value("Africa"))
                .andExpect(jsonPath("$[1].surname").value("Brasil"));
    }

    @Test
    void getStudentById() throws Exception {
        when(studentServiceImpl.findById(1L)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/1").contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$.surname").value("China"));
    }

    @Test
    void deleteStudent() throws Exception {
        doNothing().when(studentServiceImpl).deleteStudent(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/student/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Student has been deleted"))
                .andDo(print());
    }

    @Test
    void updateStudent() throws Exception {
        Student newStudent = new Student(null, "China", "Nicole", LocalDate.of(1920,12,12));
        Student updatedStudent = new Student(1L, "Corea", "Nicole", LocalDate.of(1920,12,12));

        when(studentServiceImpl.updateStudent(any())).thenReturn(updatedStudent);

        mockMvc.perform(MockMvcRequestBuilders.put("/student/update/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedStudent)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.surname").value("Corea"));
    }

    @Test
    @Disabled
    void calcularEdadPromedio() {
    }

    @Test
    @Disabled
    void mayor() {
    }

    @Test
    @Disabled
    void menor() {
    }

    @Test
    @Disabled
    void fullname() {
    }

    @Test
    @Disabled
    void getAdults() {
    }

    @Test
    @Disabled
    void getkids() {
    }

    @Test
    @Disabled
    void getOlderAverage() {
    }
}