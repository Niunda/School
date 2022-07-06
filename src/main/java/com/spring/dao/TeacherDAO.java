package com.spring.dao;

import com.spring.entity.Teacher;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface TeacherDAO {
    public List<Teacher> getAllTeacher();

    public void saveTeacher(Teacher teacher);

    public Teacher getTeacher(int id);

    public void deleteTeacher(int id);

    public void exportReport(String reportFormat) throws JRException, FileNotFoundException;
}
