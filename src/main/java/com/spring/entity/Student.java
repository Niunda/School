package com.spring.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "surname")
    @NotBlank(message = "The field cannot be empty.")
    @Size(max = 15, message = "Maximum 15 simbols.")
    private String surname;

    @Column(name = "name")
    @NotBlank(message = "The field cannot be empty.")
    @Size(max = 30, message = "Maximum 30 simbols.")
    private String name;

    @Transient
    private int classID;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class cl;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "student", fetch = FetchType.EAGER)
    @OrderBy("date")
    private List<Journal> journals;

    public Student() {
    }

    public Student(int id, String surname, String name) {
        this.id = id;
        this.surname = surname;
        this.name = name;
    }

    public void addJournal(Journal journal) {
        if (journals == null) {
            journals = new ArrayList<>();
        }

        journals.add(journal);
        journal.setStudent(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getCl() {
        return cl;
    }

    public void setCl(Class cl) {
        this.cl = cl;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public List<Journal> getJournals() {
        return journals;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }
}
