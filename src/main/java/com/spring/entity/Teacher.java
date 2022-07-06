package com.spring.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "teacher")
public class Teacher {
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

    @Column(name = "subject")
    @NotBlank(message = "The field cannot be empty.")
    @Size(max = 15, message = "Maximum 15 simbols.")
    private String subject;

    @Column(name = "cabinet")
    private int cabinet;

    public Teacher() {
    }

    public Teacher(int id, String surname, String name, String subject, int cabinet) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.subject = subject;
        this.cabinet = cabinet;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getCabinet() {
        return cabinet;
    }

    public void setCabinet(int cabinet) {
        this.cabinet = cabinet;
    }
}
