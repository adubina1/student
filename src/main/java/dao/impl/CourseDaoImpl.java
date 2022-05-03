package dao.impl;

import dao.CourseDao;
import entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    private static final List<Course> courses = new ArrayList<>();

    public void create(Course course) {
        course.setCourseID(generateId());
        courses.add(course);
    }

    public void update(Course course) {
        Course inDbCourse = findById(course.getCourseID());
        inDbCourse.setCourseName(course.getCourseName());
        inDbCourse.setDurationHours(course.getDurationHours());
    }

    public void delete(String id) {
        courses.removeIf(course -> course.getCourseID().equals(id));
    }

    public Course findById(String id) {
        return courses.stream().filter(course -> course.getCourseID().equals(id)).findFirst().orElse(null);
    }

    public Course findByName(String name) {
        return courses.stream().filter(course -> course.getCourseName().equals(name)).findFirst().orElse(null);
    }

    public List<Course> findAll() {
        List<Course> actualList = new ArrayList<>();
        for (Course s : courses) {
            if (!s.isDeleted()) {
                actualList.add(s);
            }
        }
        return actualList;
    }

    private String generateId() {
        String id = java.util.UUID.randomUUID().toString();
        if (courses.stream().anyMatch(course -> course.getCourseID().equals(id))) {
            return generateId();
        }
        return id;
    }
}