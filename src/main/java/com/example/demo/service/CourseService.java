package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Department;
import com.example.demo.entity.Teacher;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.TeacherRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;

    public CourseService(CourseRepository courseRepository,
                         TeacherRepository teacherRepository,
                         DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    public Course createCourse(Course request) {
        Course course = new Course(
                Validation.requireText(request.getName(), "Course name"),
                Validation.requireText(request.getCode(), "Course code"),
                request.getCredits());
        course.setTeacher(resolveTeacher(request.getTeacher()));
        course.setDepartment(resolveDepartment(request.getDepartment()));
        return courseRepository.save(course);
    }

    public Course updateCourse(Integer id, Course request) {
        Course course = getCourseById(id);
        course.setName(Validation.requireText(request.getName(), "Course name"));
        course.setCode(Validation.requireText(request.getCode(), "Course code"));
        course.setCredits(request.getCredits());
        course.setTeacher(resolveTeacher(request.getTeacher()));
        course.setDepartment(resolveDepartment(request.getDepartment()));
        return courseRepository.save(course);
    }

    public void deleteCourse(Integer id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    private Teacher resolveTeacher(Teacher ref) {
        if (ref == null || ref.getId() == null) {
            return null;
        }
        return teacherRepository.findById(ref.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + ref.getId()));
    }

    private Department resolveDepartment(Department ref) {
        if (ref == null || ref.getId() == null) {
            return null;
        }
        return departmentRepository.findById(ref.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + ref.getId()));
    }
}
