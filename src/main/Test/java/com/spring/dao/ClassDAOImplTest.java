package com.spring.dao;

import com.spring.entity.Class;
import com.spring.entity.Journal;
import com.spring.entity.Student;
import com.spring.entity.Teacher;
import com.spring.exceptions.ClassNumberException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ClassDAOImplTest {

    private ClassDAOImpl classDAO;
    private final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Teacher.class)
            .addAnnotatedClass(Class.class)
            .addAnnotatedClass(Student.class)
            .addAnnotatedClass(Journal.class)
            .buildSessionFactory();

    @Before
    public void setUp() {
        classDAO = new ClassDAOImpl();
        classDAO.setSessionFactory(sessionFactory);
    }

    @Test
    public void getAllClass() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Class> expected = classDAO.getAllClass();

       session.getTransaction().commit();

        Assert.assertNotNull(expected);
    }

    @Test(expected = ClassNumberException.class)
    public void saveClass() throws ClassNumberException {
       Session session = sessionFactory.getCurrentSession();
       session.beginTransaction();


        Class expected = new Class();
        expected.setNumber("11a");
        classDAO.saveClass(expected);

        session.getTransaction().commit();
    }

    @Test
    public void getClassStudent() {
       Session session = sessionFactory.getCurrentSession();
       session.beginTransaction();

        List<Class> allClass = classDAO.getAllClass();
        for(Class cl : allClass){
            List<Student> expected = cl.getStudents();
            List<Student> actual = session.createQuery("from Student where cl =: this ",
                    Student.class).setParameter("this", cl).getResultList();
            Assert.assertEquals(expected.size(), actual.size());
        }
    }
}