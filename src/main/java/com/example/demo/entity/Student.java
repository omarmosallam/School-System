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

    @Column (name="Student_First_Name")
    private String studentFirstName;

    @Column (name="Student_Last_Name")
    private String studentLastName;

    @Column (name="Student_Age")
    private int StudentAge;

    @Column (name="Student_Phone_Number")
    private String StudentPhone;

    @Column (name="Student_Email")
    private String StudentEmail;

    //default constructor
    public Student(){
    }
    public Student(int studentId, String studentFirstName, String studentLastName, int studentAge, String studentPhone, String studentEmail) {
        this.studentId = studentId;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        StudentAge = studentAge;
        StudentPhone = studentPhone;
        StudentEmail = studentEmail;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
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

    public String getStudentEmail() {
        return StudentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        StudentEmail = studentEmail;
    }


    
}
