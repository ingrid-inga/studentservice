package com.example.studentservice.controller;

import com.example.studentservice.exception.NoEntityException;
import com.example.studentservice.model.Student;
import com.example.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")

public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student stu = studentService.createStudent(student);
        return new ResponseEntity<Student>(stu, HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
        try {
            Student student = studentService.findById(id);
            return ResponseEntity.ok(student);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST + "Student hasn't been founded ");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Student has been deleted");
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST + "Student hasn't been deleted");
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student snew = new Student();
        try {
            snew = studentService.updateStudent(student);
            return ResponseEntity.ok(snew);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(snew);
        }
    }

    //Calcular la edad promedio
    @GetMapping("edadpromedio")
    public int calcularEdadPromedio() {
        return studentService.calcularEdadPromedio();
    }

    //Student con más edad
    @GetMapping("mayor")
    public int mayor() {
        return studentService.mayor();
    }

    //Student con menor edad
    @GetMapping("menor")
    public int menor() {
        return studentService.menor();
    }

    //Mostrar id, surname, name
    @GetMapping("fullname")
    public String fullname(){
        return studentService.fullname();
    }

    //Mostrar mayores de edad
    @GetMapping("mayoresdeedad")
    public List<Student> getAdults(){
        return studentService.getAdults();
    }

    //Mostrar menores de edad
    @GetMapping("menoresdeedad")
    public List<Student> getkids(){
        return studentService.getKids();
    }

    //Mostrar edad promedio de los mayores de edad
    @GetMapping("promediodemayores")
    public int getOlderAverage(){
        return studentService.getOlderAverage();
    }

    //Mostrar edad promedio de los menores de edad
    @GetMapping("promediodemenores")
    public int getMinorsAverage(){
        return studentService.getMinorsAverage();
    }

}
