package com.spring.dao;

import com.spring.entity.Class;
import com.spring.entity.Student;
import com.spring.exceptions.ClassNumberException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ClassDAOImpl implements ClassDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Class> getAllClass() {
        Session session = sessionFactory.getCurrentSession();
        List<Class> allClass = session.createQuery("from Class", Class.class).getResultList();
        return allClass;
    }

    @Override
    @Transactional
    public void saveClass(Class cl) throws ClassNumberException {
        Session session = sessionFactory.getCurrentSession();
        List<Class> allNumbers = session.createQuery("from Class where id !=: thisID",
                Class.class).setParameter("thisID", cl.getId()).getResultList();
        for (Class other : allNumbers)
            if(cl.getNumber().equalsIgnoreCase(other.getNumber()))
                throw new ClassNumberException("A class with this number already exists!");
        session.saveOrUpdate(cl);
    }

    @Override
    @Transactional
    public void updateClass(Class cl) {
        Session session = sessionFactory.getCurrentSession();
        session.update(cl);
    }

    @Override
    @Transactional
    public Class getClass(int id) {
        Session session = sessionFactory.getCurrentSession();
        Class cl = session.get(Class.class, id);
        return cl;
    }

    @Override
    @Transactional
    public void deleteClass(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getClass(id));
    }

    @Override
    @Transactional
    public List<Student> getClassStudent(int id) {
        Session session = sessionFactory.getCurrentSession();
        Class cl = session.get(Class.class, id);
        List<Student> classStudent = session.createQuery("from Student " +
                        "where cl =: this order by surname", Student.class)
                .setParameter("this", cl).getResultList();

        return classStudent;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
