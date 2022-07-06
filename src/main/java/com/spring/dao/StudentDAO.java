package com.spring.dao;

import com.spring.entity.Journal;
import com.spring.entity.Student;

import java.util.List;

public interface StudentDAO {
    public List<Student> getAllStudent();

    public void saveStudent(Student student);

    public Student getStudent(int id);

    public void deleteStudent(int id);

    public List<Journal> getStudentJournal(int id);
}
