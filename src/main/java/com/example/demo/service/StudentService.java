package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public Student createStudent(Student student) {
        Validation.requireText(student.getStudentFirstName(), "First name");
        Validation.requireText(student.getStudentLastName(), "Last name");
        student.setStudentId(0); // always insert a new row
        return studentRepository.save(student);
    }

    public Student updateStudent(Integer id, Student details) {
        Student student = getStudentById(id);

        student.setStudentFirstName(Validation.requireText(details.getStudentFirstName(), "First name"));
        student.setStudentLastName(Validation.requireText(details.getStudentLastName(), "Last name"));
        student.setStudentAge(details.getStudentAge());
        student.setStudentPhone(details.getStudentPhone());
        student.setStudentEmail(details.getStudentEmail());

        return studentRepository.save(student);
    }

    public void deleteStudent(Integer id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}
