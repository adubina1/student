package service;

import dao.impl.CourseDaoImpl;
import entity.Course;

import java.util.List;

public class CourseService {

    private final CourseDaoImpl courseDao = new CourseDaoImpl();

    public void create(Course course) {
        if (course.getCourseName() != null & course.getDurationHours() > 0) {
            if (courseDao.findByName(course.getCourseName()) == null) {
                courseDao.create(course);
            }
        }
    }

    public void update(Course course, String courseName, int duration) {
        if (courseName != null & duration > 0) {
            course.setCourseName(courseName);
            course.setDurationHours(duration);
            courseDao.update(course);
            System.out.println("Course" + course.getCourseName() + " is successfully updated.");
        } else {
            System.out.println("Course" + course.getCourseName() + " is not updated.");
        }
    }

    public void delete(String id) {
        if (courseDao.findById(id) != null){
            courseDao.delete(id);
            System.out.println("Course is successfully deleted.");
        }
        else {
            System.out.println("Course is not deleted.");
        }
    }

    public Course findCourseById(String id) {
        return courseDao.findById(id);
    }

    public boolean isCourseExist (Course course) {
        return courseDao.findByName(course.getCourseName()) != null;
    }

    public List<Course> findAllCourses() {
        return courseDao.findAll();
    }
}
