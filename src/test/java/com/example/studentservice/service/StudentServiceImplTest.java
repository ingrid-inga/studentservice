package com.example.studentservice.service;

import com.example.studentservice.exception.NoEntityException;
import com.example.studentservice.model.Student;
import com.example.studentservice.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentServiceImplTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentServiceImpl studentServiceImpl;

    Student student;

    @BeforeEach
    void setUp() {
        student = new Student(1L, "Paraguay", "Paola", LocalDate.of(1999, 9, 12));
    }

    @Test
    void createStudent() {
        Student newStudent = new Student(null, "Canada", "Collin", LocalDate.now());
        Student createdStudent = new Student(12L, "Canada", "Collin", LocalDate.now());
        when(studentRepository.save(newStudent)).thenReturn(createdStudent);
        assertNotNull(studentServiceImpl.createStudent(newStudent));
    }

    @Test
    void findAll() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1L, "Paraguay", "Paola", LocalDate.of(1999, 9, 12)));
        studentList.add(new Student(2L, "Uruguay", "Natalia", LocalDate.of(1990, 5, 20)));
        when(studentRepository.findAll()).thenReturn(studentList);
        assertFalse(studentServiceImpl.findAll().isEmpty());
    }

    @Test
    void deleteStudent() throws NoEntityException {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        doNothing().when(studentRepository).delete(student);
        studentRepository.delete(student);
        assertFalse(studentRepository.findById(student.getId()).isPresent());

    }

    @Test
    void updateStudent() throws NoEntityException {
        when(studentRepository.save(student)).thenReturn(student);
        when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(student));
        Student updateStudent = studentServiceImpl.updateStudent(student);
        System.out.println(studentServiceImpl.updateStudent(student));
        assertNotNull(updateStudent);
        assertNotNull(updateStudent.getId());
    }

    @Test
    void findById() throws NoEntityException {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        assertNotNull(studentServiceImpl.findById(1L));
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
    void getKids() {
    }

    @Test
    @Disabled
    void getOlderAverage() {
    }
}