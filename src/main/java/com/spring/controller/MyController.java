package com.spring.controller;

import com.spring.dao.ClassDAO;
import com.spring.dao.JournalDAO;
import com.spring.dao.StudentDAO;
import com.spring.dao.TeacherDAO;
import com.spring.entity.Class;
import com.spring.entity.Journal;
import com.spring.entity.Student;
import com.spring.entity.Teacher;
import com.spring.exceptions.ClassNumberException;
import com.spring.exceptions.JournalDateException;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class MyController {
    @Autowired
    private TeacherDAO teacherDAO;
    @Autowired
    private ClassDAO classDAO;
    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private JournalDAO journalDAO;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        format.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }

    @RequestMapping("/")
    public String showMenu() {
        return "menu";
    }

    @RequestMapping("/teacher")
    public String showAllTeacher(Model model) {
        List<Teacher> allTeaher = teacherDAO.getAllTeacher();
        model.addAttribute("allTeacher", allTeaher);

        return "all-teacher";
    }

    @RequestMapping("/addteacher")
    public String addTeacher(Model model) {
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "add-teacher";
    }

    @RequestMapping("/saveteacher")
    public String saveTeacher(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult bindingResult){
        if (bindingResult.hasErrors())
        {
            return "add-teacher";
        }
        else{
            teacherDAO.saveTeacher(teacher);
            return "redirect:/teacher";
        }
    }

    @RequestMapping("/updateteacher")
    public String updateTeacher(@RequestParam("teacherID") int id, Model model) {
        Teacher teacher = teacherDAO.getTeacher(id);
        model.addAttribute("teacher", teacher);
        return "add-teacher";
    }

    @RequestMapping("/deleteteacher")
    public String deleteTeacher(@RequestParam("teacherID") int id) {
        teacherDAO.deleteTeacher(id);
        return "redirect:/teacher";
    }

    @RequestMapping("/class")
    public String showAllClass(Model model) {
        List<Class> allClass = classDAO.getAllClass();
        model.addAttribute("allClass", allClass);

        return "all-class";
    }

    @RequestMapping("/addclass")
    public String addClass(Model model) {
       Class cl = new Class();
       model.addAttribute("class", cl);
       List<Teacher> allTeaher = teacherDAO.getAllTeacher();
       model.addAttribute("allTeacher", allTeaher);

       return "add-class";
    }

    @RequestMapping("/saveclass")
    public String saveClass(@Valid @ModelAttribute("class") Class cl, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors())
        {
            List<Teacher> allTeaher = teacherDAO.getAllTeacher();
            model.addAttribute("allTeacher", allTeaher);
            return "add-class";
        }
        else{
            cl.setTeacher(teacherDAO.getTeacher(cl.getTeacherID()));
            try {
                classDAO.saveClass(cl);
            }
            catch (ClassNumberException ex) {
                model.addAttribute("error", ex);
                return "show-error";
            }
            return "redirect:/class";
        }
    }

    @RequestMapping("/updateclass")
    public String updateClass(@RequestParam("classID") int id, Model model) {
        Class cl = classDAO.getClass(id);
        model.addAttribute("class", cl);
        List<Teacher> allTeaher = teacherDAO.getAllTeacher();
        model.addAttribute("allTeacher", allTeaher);
        return "add-class";
    }

    @RequestMapping("/deleteclass")
    public String deleteClass(@RequestParam("classID") int id) {
        classDAO.deleteClass(id);
        return "redirect:/class";
    }

    @RequestMapping("/student")
    public String showAllStudents(Model model) {
        List<Student> allStudent = studentDAO.getAllStudent();
        model.addAttribute("allStudent", allStudent);
        model.addAttribute("flag", 0);
        return "all-student";
    }

    @RequestMapping("/classstudents")
    public String showStudents(@RequestParam("classID") int id, Model model) {
        List<Student> classStudent = classDAO.getClassStudent(id);
        model.addAttribute("allStudent", classStudent);
        model.addAttribute("flag", id);
        return "all-student";
    }

    @RequestMapping("/addstudent")
    public String addStudent(@RequestParam("classID") int id, Model model) {
        Student student = new Student();
        student.setClassID(id);
        model.addAttribute("student", student);
        List<Class> allClass = classDAO.getAllClass();
        model.addAttribute("allClass", allClass);
        return "add-student";
    }

    @RequestMapping("/savestudent")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors())
        {
            List<Class> allClass = classDAO.getAllClass();
            model.addAttribute("allClass", allClass);
            return "add-student";
        }
        else{
            Class cl = classDAO.getClass(student.getClassID());
            if (student.getId() == 0) cl.setStudentsCout(cl.getStudentsCout() + 1);
            else if (cl.getId() != studentDAO.getStudent(student.getId()).getCl().getId()){
                cl.setStudentsCout(cl.getStudentsCout() + 1);
                Class cl2 = classDAO.getClass(((studentDAO.getStudent(student.getId())).getCl()).getId());
                cl2.setStudentsCout(cl2.getStudentsCout() - 1);
                classDAO.updateClass(cl2);
            }
            cl.addStudent(student);
            studentDAO.saveStudent(student);
            classDAO.updateClass(cl);
            return "redirect:/student";
        }
    }

    @RequestMapping("/updatestudent")
    public String updateStudent(@RequestParam("studentID") int id, Model model) {
        Student student = studentDAO.getStudent(id);
        student.setClassID(student.getCl().getId());
        Class cl = student.getCl();
        cl.getStudents().remove(student);
        classDAO.updateClass(cl);
        model.addAttribute("student", student);
        List<Class> allClass = classDAO.getAllClass();
        model.addAttribute("allClass", allClass);
        return "add-student";
    }

    @RequestMapping("/deletestudent")
    public String deleteStudent(@RequestParam("studentID") int id) {
        studentDAO.deleteStudent(id);
        return "redirect:/student";
    }

    @RequestMapping("/journal")
    public String showSJournal(@RequestParam("studID") int id, Model model) {
        List<Journal> journal = studentDAO.getStudentJournal(id);
        model.addAttribute("allJournal", journal);
        model.addAttribute("flag", id);
        return "all-journal";
    }

    @RequestMapping("/addjournal")
    public String addJournal(@RequestParam("studID") int id, Model model) {
        Journal journal = new Journal();
        journal.setStudentID(id);
        model.addAttribute("journal", journal);
        List<Teacher> allTeaher = teacherDAO.getAllTeacher();
        model.addAttribute("allTeacher", allTeaher);
        return "add-journal";
    }

    @RequestMapping("/savejournal")
    public String saveJournal(@Valid @ModelAttribute("journal") Journal journal, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors())
        {
            List<Teacher> allTeaher = teacherDAO.getAllTeacher();
            model.addAttribute("allTeacher", allTeaher);
            return "add-journal";
        }
        else{
            Teacher teacher = teacherDAO.getTeacher(journal.getTeacherID());
            journal.setTeacher(teacher);
            Student student = studentDAO.getStudent(journal.getStudentID());
            try {
                journalDAO.saveJournal(journal, student);
            }
            catch (JournalDateException ex) {
                model.addAttribute("error", ex);
                return "show-error";
            }
            String str = "redirect:/journal?studID=" + journal.getStudentID();
            return str;
        }
    }

    @RequestMapping("/updatejournal")
    public String updateJournal(@RequestParam("journalID") int id, Model model) {
        Journal journal = journalDAO.getJournal(id);
        journal.setTeacherID(journal.getTeacher().getId());
        journal.setStudentID(journal.getStudent().getId());
        Student student = journal.getStudent();
        student.getJournals().remove(journal);
        studentDAO.saveStudent(student);
        model.addAttribute("journal", journal);
        List<Teacher> allTeaher = teacherDAO.getAllTeacher();
        model.addAttribute("allTeacher", allTeaher);
        return "add-journal";
    }

    @RequestMapping("/deletejournal")
    public String deleteJournal(@RequestParam("journalID") int id, @RequestParam("studID") int sid) {
        journalDAO.deleteJournal(id);
        String str = "redirect:/journal?studID=" + sid;
        return str;
    }

    @RequestMapping("/report")
    public String makeReport() {

        Runnable report1 = () -> {
            try {
                teacherDAO.exportReport("pdf");
            } catch (JRException | FileNotFoundException e) {
                e.printStackTrace();
            }
        };

        Runnable report2 = () -> {
            try {
                teacherDAO.exportReport("html");
            } catch (JRException | FileNotFoundException e) {
                e.printStackTrace();
            }
        };

        Thread thread1 = new Thread(report1);
        Thread thread2 = new Thread(report2);
        thread1.start();
        thread2.start();

        return "report-teacher";
    }
}
