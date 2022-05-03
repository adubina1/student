package service;

import dao.impl.StudentDaoImpl;
import entity.Program;
import entity.Student;

import java.util.List;

public class StudentService {

    private final StudentDaoImpl studentDao = new StudentDaoImpl();

    public void create(Student student) {
        if (student.getName() != null & student.getSurName() != null & student.getProgram() != null) {
            studentDao.create(student);
        } else {
            System.out.println("Students required information is not exist. Student is not created.");
        }
    }

    public void update(Student student, String name, String surName, Program program) {
        if (name != null & surName != null & program != null){
            student.setName(name);
            student.setSurName(surName);
            student.setProgram(program);
            studentDao.update(student);
        } else {
        System.out.println("Students required information is not exist.");
    }
    }

    public void update (Student student, String name, String surName) {
        if (name != null & surName != null){
            student.setName(name);
            student.setSurName(surName);
            studentDao.update(student);
        }
    }

    public void delete(String id) {
        studentDao.delete(id);
    }

    public Student findStudentById(String id) {
        return studentDao.findById(id);
    }

    public List<Student> findAllUsers() {
        return studentDao.findAll();
    }
}
