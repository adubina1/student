package dao;

import entity.Course;

import java.util.List;

public interface CourseDao {

    void create(Course course);

    void update(Course course);

    void delete(String id);

    Object findById(String id);

    List<Course> findAll();
}
