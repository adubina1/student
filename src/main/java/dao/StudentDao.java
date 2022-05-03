package dao;

import entity.Student;

import java.util.List;

public interface StudentDao {

    void create(Student student);

    void update(Student student);

    void delete(String id);

    Object findById(String id);

    List<Student> findAll();
}
