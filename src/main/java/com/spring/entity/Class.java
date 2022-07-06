package com.spring.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "class")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    @NotBlank(message = "The field cannot be empty.")
    @Size(max = 5, message = "Maximum 5 simbols.")
    private String number;

    @Column(name = "students_cout")
    private int studentsCout;

    @Transient
    private int teacherID;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "cl", fetch = FetchType.EAGER)
    @OrderBy("surname")
    private List<Student> students;

    public Class() {
    }

    public Class(int id,  String number, int studentsCout, int teacherID, Teacher teacher) {
        this.id = id;
        this.number = number;
        this.studentsCout = studentsCout;
        this.teacherID = teacherID;
        this.teacher = teacher;
    }
    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }

        students.add(student);
        student.setCl(this);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getStudentsCout() {
        return studentsCout;
    }

    public void setStudentsCout(int studentsCout) {
        this.studentsCout = studentsCout;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
