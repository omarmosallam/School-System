package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Enrollment;
import com.example.demo.entity.EnrollmentStatus;
import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.StudentRepository;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Integer id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));
    }

    public Enrollment createEnrollment(Enrollment request) {
        Enrollment enrollment = new Enrollment();
        apply(enrollment, request);
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment updateEnrollment(Integer id, Enrollment request) {
        Enrollment enrollment = getEnrollmentById(id);
        apply(enrollment, request);
        return enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(Integer id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Enrollment not found with id: " + id);
        }
        enrollmentRepository.deleteById(id);
    }

    private void apply(Enrollment target, Enrollment request) {
        if (request.getStudent() == null || request.getStudent().getStudentId() == 0) {
            throw new IllegalArgumentException("Student is required.");
        }
        if (request.getCourse() == null || request.getCourse().getId() == null) {
            throw new IllegalArgumentException("Course is required.");
        }
        int studentId = request.getStudent().getStudentId();
        Integer courseId = request.getCourse().getId();

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        target.setStudent(student);
        target.setCourse(course);
        target.setEnrollmentDate(request.getEnrollmentDate() != null ? request.getEnrollmentDate() : LocalDate.now());
        target.setGrade(request.getGrade());
        target.setStatus(request.getStatus() != null ? request.getStatus() : EnrollmentStatus.ACTIVE);
    }
}
