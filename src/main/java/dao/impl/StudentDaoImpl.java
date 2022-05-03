package dao.impl;

import dao.StudentDao;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private static final List<Student> studentList = new ArrayList<>();

    public void create(Student student) {
        student.setId(generateId());
        studentList.add(student);
    }

    public void update(Student student) {
        Student inDbUser = findById(student.getId());
        inDbUser.setName(student.getName());
        inDbUser.setSurName(student.getSurName());
        inDbUser.setProgram(student.getProgram());
    }

    public void delete(String id) {
        Student student = findById(id);
        student.setDeleted(true);
    }

    public Student findById(String id) {
        return studentList.stream().filter(user -> user.getId().equals(id)).findFirst().get();
    }

    public List<Student> findAll() {
        List<Student> actualList = new ArrayList<>();
        for (Student s : studentList) {
            if (!s.isDeleted()) {
                actualList.add(s);
            }
        }
        return actualList;
    }

    private String generateId() {
        String id = java.util.UUID.randomUUID().toString();
        if (studentList.stream().anyMatch(user -> user.getId().equals(id))) {
            return generateId();
        }
        return id;
    }

}