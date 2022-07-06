package com.spring.dao;

import com.spring.entity.Journal;
import com.spring.entity.Student;
import com.spring.exceptions.JournalDateException;

import java.util.List;

public interface JournalDAO {
    public void saveJournal(Journal journal, Student student) throws JournalDateException;

    public Journal getJournal(int id);

    public void deleteJournal(int id);
}
