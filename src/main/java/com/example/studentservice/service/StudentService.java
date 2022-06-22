package com.example.studentservice.service;

import com.example.studentservice.exception.NoEntityException;
import com.example.studentservice.model.Student;

import java.util.List;

public interface StudentService {

    public Student createStudent(Student student);

    public List<Student> findAll();

    public void deleteStudent(Long id) throws NoEntityException;

    public Student updateStudent(Student student) throws NoEntityException;

    public Student findById(Long id) throws NoEntityException;

    int calcularEdadPromedio();

    int mayor();

    int menor();

    String fullname();

    List<Student> getAdults();

    List<Student> getKids();

    int getOlderAverage();

    int getMinorsAverage();
}
