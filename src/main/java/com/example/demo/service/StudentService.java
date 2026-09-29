package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // GET all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // GET student by ID
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id));
    }

    // CREATE student
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // UPDATE student
    public Student updateStudent(Integer id, Student studentDetails) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id));

        student.setStudentFirstName(studentDetails.getStudentFirstName());
        student.setStudentLastName(studentDetails.getStudentLastName());
        student.setStudentEmail(studentDetails.getStudentEmail());

        return studentRepository.save(student);
    }

    // DELETE student
    public void deleteStudent(Integer id) {

        if (!studentRepository.existsById(id)) {
            throw new RuntimeException(
                    "Student not found with id: " + id);
        }

        studentRepository.deleteById(id);
    }
}