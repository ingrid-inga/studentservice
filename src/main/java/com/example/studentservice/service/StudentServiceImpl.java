package com.example.studentservice.service;

import com.example.studentservice.exception.NoEntityException;
import com.example.studentservice.model.Student;
import com.example.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService{

@Autowired
    private StudentRepository studentRepository;

    @Transactional
    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteStudent(Long id) throws NoEntityException {
        Student student = studentRepository.findById(id).orElseThrow(()-> new NoEntityException("Student doesn't exist with " + id));
        studentRepository.delete(student);
    }

    @Transactional
    @Override
    public Student updateStudent(Student student) throws NoEntityException {
        Student sold = studentRepository.findById(student.getId())
                .orElseThrow(()-> new NoEntityException("Student doesn't exist with " + student.getId()));
        sold.setSurname(student.getName());
        sold.setSurname(student.getSurname());
        sold.setBirthday(student.getBirthday());

        return studentRepository.save(sold);
    }

    @Override
    public Student findById(Long id) throws NoEntityException {
        return studentRepository.findById(id).orElseThrow(()-> new NoEntityException("Student doesn't exist with " + id));
    }

    @Override
    public int calcularEdadPromedio() {
        List<Student> studentList = studentRepository.findAll();
        return (int)studentList.stream().mapToInt(s -> Period.between(s.getBirthday(), LocalDate.now()).getYears()).average().orElseThrow();
    }

    @Override
    public int mayor() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().mapToInt(s->Period.between(s.getBirthday(),LocalDate.now()).getYears()).max().orElse(0);
    }

    @Override
    public int menor() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().mapToInt(s->Period.between(s.getBirthday(),LocalDate.now()).getYears()).min().orElse(0);
    }

    @Override
    public String fullname() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().map(student -> student.getId() + " - " + student.getSurname() + ", "+ student.getName()).collect(Collectors.joining(".   "));
    }

    @Override
    public List<Student> getAdults() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().filter(s -> Period.between(s.getBirthday(),LocalDate.now()).getYears()>=18)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getKids() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().filter(s -> Period.between(s.getBirthday(),LocalDate.now()).getYears()<18)
                .collect(Collectors.toList());
    }

       @Override
    public int getOlderAverage() {
       List<Student> studentList = studentRepository.findAll();
       return (int)studentList.stream()
               .filter(s -> Period.between(s.getBirthday(),LocalDate.now()).getYears()>=18).mapToInt(s -> Period.between(s.getBirthday(), LocalDate.now()).getYears()).average().orElseThrow();
    }

    @Override
    public int getMinorsAverage() {
        List<Student> studentList = studentRepository.findAll();
        return (int)studentList.stream()
                .filter(s -> Period.between(s.getBirthday(),LocalDate.now()).getYears()<18).mapToInt(s -> Period.between(s.getBirthday(), LocalDate.now()).getYears()).average().orElseThrow();
    }





}

