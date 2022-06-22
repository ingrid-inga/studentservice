package com.example.studentservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    @Disabled
    void testEquals() {
    }

    @Test
    @Disabled
    void testHashCode() {
    }

    @Test
    void getId() {
        Student student = new Student(1L, "Egypt", "Helena", LocalDate.of(2000, 10,11));
        assertEquals(1L, student.getId());
        assertTrue(student.getId()==1);
    }

    @Test
    void getSurname() {
        Student student = new Student(1L, "Egypt", "Helena", LocalDate.of(2000, 10,11));
        assertEquals("Egypt", student.getSurname());
    }

    @Test
    void getName() {
        Student student = new Student(1L, "Egypt", "Helena", LocalDate.of(2000, 10,11));
        assertEquals("Helena", student.getName());
    }

    @Test
    void getBirthday() {
        Student student = new Student(1L, "Egypt", "Helena", LocalDate.of(2000, 10,11));
        assertEquals(LocalDate.of(2000, 10, 11), student.getBirthday());
    }

    @Test
    void setId() {
        Student student = new Student();
        student.setId(2L);
        assertEquals(2L,student.getId());
    }

    @Test
    void setSurname() {
        Student student = new Student();
        student.setSurname("Greece");
        assertEquals("Greece",student.getSurname());
    }

    @Test
    void setName() {
        Student student = new Student();
        student.setName("Macarena");
        assertEquals("Macarena",student.getName());
    }

    @Test
    void setBirthday() {
        Student student = new Student();
        student.setBirthday(LocalDate.of(2020,12,19));
        assertEquals(LocalDate.of(2020,12,19),student.getBirthday());
    }

    @Test
    void testToString() {
        Student student = new Student(1L, "Egypt", "Helena", LocalDate.of(2000, 10,11));
        assertEquals("Student(id=1, surname=Egypt, name=Helena, birthday=2000-10-11)", student.toString());
    }
}