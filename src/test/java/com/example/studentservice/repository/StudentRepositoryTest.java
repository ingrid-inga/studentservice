package com.example.studentservice.repository;

import com.example.studentservice.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    StudentRepository studentRepository;

    @Test
    void findById(){
        assertTrue(studentRepository.findById(1L).isPresent());
        assertEquals("Colombia" , studentRepository.findById(1L).get().getSurname());
    }

    @Test
    void findAll(){
        List<Student> list = studentRepository.findAll();
        assertFalse(list.isEmpty());
        assertTrue(list.size()>0);
    }

    @Test
    void create(){
        Student student = new Student(null, "Panama", "Marlon", LocalDate.of(1990, 5, 11));
        Student studentSave = studentRepository.save(student);
        assertTrue(studentSave.getId()>0);
    }


}
