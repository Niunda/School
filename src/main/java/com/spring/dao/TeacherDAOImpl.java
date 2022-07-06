package com.spring.dao;

import com.spring.entity.Teacher;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TeacherDAOImpl implements TeacherDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Teacher> getAllTeacher() {
        Session session = sessionFactory.getCurrentSession();
        List<Teacher> allTeacher = session.createQuery("from Teacher order by Surname", Teacher.class).getResultList();
        return allTeacher;
    }

    @Override
    @Transactional
    public void saveTeacher(Teacher teacher) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(teacher);
    }

    @Override
    @Transactional
    public Teacher getTeacher(int id) {
        Session session = sessionFactory.getCurrentSession();
        Teacher teacher = session.get(Teacher.class, id);
        return teacher;
    }

    @Override
    @Transactional
    public void deleteTeacher(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Teacher> query = session.createQuery("delete from Teacher where id =:teacherid");
        query.setParameter("teacherid", id);
        query.executeUpdate();

    }

    @Override
    @Transactional
    public void exportReport(String reportFormat) throws JRException, FileNotFoundException {
        List<Teacher> allTeacher;
        synchronized (getAllTeacher()){
            allTeacher= getAllTeacher();
        }
        File file = ResourceUtils.getFile("classpath:teacher-report.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(allTeacher);
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint,
                    "C:\\IntelliJ IDEA\\SchoolData\\teacherReport.pdf");
        }
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint,
                    "C:\\IntelliJ IDEA\\SchoolData\\teacherReport.html");
        }
    }
}
