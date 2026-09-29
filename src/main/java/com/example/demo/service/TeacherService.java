package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.entity.Teacher;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.TeacherRepository;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;

    public TeacherService(TeacherRepository teacherRepository, DepartmentRepository departmentRepository) {
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Integer id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
    }

    public Teacher createTeacher(Teacher request) {
        Teacher teacher = new Teacher(
                Validation.requireText(request.getFirstName(), "First name"),
                Validation.requireText(request.getLastName(), "Last name"),
                Validation.requireText(request.getEmail(), "Email"));
        teacher.setDepartment(resolveDepartment(request.getDepartment()));
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Integer id, Teacher request) {
        Teacher teacher = getTeacherById(id);
        teacher.setFirstName(Validation.requireText(request.getFirstName(), "First name"));
        teacher.setLastName(Validation.requireText(request.getLastName(), "Last name"));
        teacher.setEmail(Validation.requireText(request.getEmail(), "Email"));
        teacher.setDepartment(resolveDepartment(request.getDepartment()));
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Integer id) {
        if (!teacherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Teacher not found with id: " + id);
        }
        teacherRepository.deleteById(id);
    }

    private Department resolveDepartment(Department ref) {
        if (ref == null || ref.getId() == null) {
            return null;
        }
        return departmentRepository.findById(ref.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + ref.getId()));
    }
}
