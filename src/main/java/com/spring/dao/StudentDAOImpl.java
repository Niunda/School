package com.spring.dao;

import com.spring.entity.Class;
import com.spring.entity.Journal;
import com.spring.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Student> getAllStudent() {
        Session session = sessionFactory.getCurrentSession();
        List<Student> allStudent = session.createQuery("from Student order by Surname", Student.class).getResultList();
        return allStudent;
    }

    @Override
    @Transactional
    public void saveStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(student);
    }

    @Override
    @Transactional
    public Student getStudent(int id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, id);
        return student;
    }

    @Override
    @Transactional
    public void deleteStudent(int id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = getStudent(id);
        Class cl = student.getCl();
        cl.setStudentsCout(cl.getStudentsCout() - 1);
        cl.getStudents().remove(student);
        session.saveOrUpdate(cl);
        session.delete(student);
    }

    @Override
    @Transactional
    public List<Journal> getStudentJournal(int id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, id);
        List<Journal> studentJournal = session.createQuery("from Journal " +
                "where student =: this order by date", Journal.class)
                .setParameter("this", student).getResultList();

        return studentJournal;
    }
}
