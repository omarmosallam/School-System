package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="Student_ID")
    private int studentId;

    @Column (name="Student_Name")
    private String StudentName;

    @Column (name="Student_Age")
    private int StudentAge;

    @Column (name="Student_Phone_Number")
    private String StudentPhone;

    //default constructor
    public Student(){
    }
    public Student(int studentId, String studentName, int studentAge, String studentPhone) {
        this.studentId = studentId;
        StudentName = studentName;
        StudentAge = studentAge;
        StudentPhone = studentPhone;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public int getStudentAge() {
        return StudentAge;
    }

    public void setStudentAge(int studentAge) {
        StudentAge = studentAge;
    }

    public String getStudentPhone() {
        return StudentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        StudentPhone = studentPhone;
    }

    
}
