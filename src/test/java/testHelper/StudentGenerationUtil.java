package testHelper;

import entity.Course;
import entity.Program;
import entity.Student;
import service.ProgramService;
import service.StudentService;

import java.time.LocalDate;

public class StudentGenerationUtil {

    public static final String NAME = "Test";
    public static final String SURNAME = "Test";
    public static final String PROGRAM = "TestProgram";
    public static final String COURSE_1 = "TestCourse_1";
    public static final String COURSE_2 = "TestCourse_2";
    public static final int DURATION_0 = 0;
    public static final int DURATION_1 = 4;
    public static final int DURATION_2 = 4;
    private final static StudentService studentService = new StudentService();
    private final static ProgramService programService = new ProgramService();
    private final static LocalDate DATE = LocalDate.of(2022, 2, 20);

    public static Student generateUser(String name, String surName, Program program) {
        Student user = new Student(name, surName, program);
        studentService.create(user);
        return user;
    }
    private static Program createProgram(String programName, Course... courses){
        Program program = new Program(programName, courses, DATE);
        programService.create(program);
        return program;
    }

    public static Program createOneDayProgram() {
        return createProgram(PROGRAM, new Course(COURSE_1, DURATION_1), new Course(COURSE_2, DURATION_2));
    }

    public static Program createShortProgram() {
        return createProgram(PROGRAM, new Course(COURSE_1, DURATION_0));
    }
}