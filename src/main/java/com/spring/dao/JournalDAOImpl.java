package com.spring.dao;

import com.spring.entity.Class;
import com.spring.entity.Journal;
import com.spring.entity.Student;
import com.spring.exceptions.JournalDateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class JournalDAOImpl implements JournalDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void saveJournal(Journal journal, Student student) throws JournalDateException {
        Session session = sessionFactory.getCurrentSession();
        List<Journal> journals = student.getJournals();
        long thisTime = journal.getDate().getTime() / 1000 / 60 / 60 / 24;
        for(Journal other : journals) {
            long otherTime = other.getDate().getTime() / 1000 / 60 / 60 / 24;
            if ((thisTime == otherTime)&&(journal.getSubject().equalsIgnoreCase(other.getSubject())))
                if(journal.getId() != other.getId())
                    throw new JournalDateException("A grade with the same date and subject already exists!");
        }
        student.addJournal(journal);
        session.saveOrUpdate(journal);
        session.update(student);
    }

    @Override
    @Transactional
    public Journal getJournal(int id) {
        Session session = sessionFactory.getCurrentSession();
        Journal journal = session.get(Journal.class, id);
        return journal;
    }

    @Override
    @Transactional
    public void deleteJournal(int id) {
        Session session = sessionFactory.getCurrentSession();
        Journal journal = getJournal(id);
        Student student = journal.getStudent();
        student.getJournals().remove(journal);
        session.update(student);
        session.delete(journal);
    }
}
