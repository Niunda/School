package com.spring.dao;

import com.spring.entity.Class;
import com.spring.entity.Student;
import com.spring.exceptions.ClassNumberException;

import java.util.List;

public interface ClassDAO {
    public List<Class> getAllClass();

    public void saveClass(Class cl) throws ClassNumberException;

    public void updateClass(Class cl);

    public Class getClass(int id);

    public void deleteClass(int id);

    public List<Student> getClassStudent(int id);
}
