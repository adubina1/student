package helper;

import entity.Course;
import entity.Program;
import entity.Student;
import service.CourseService;
import service.ProgramService;
import service.StudentService;

import java.time.LocalDate;
import java.util.List;

public class StudentGenerationUtil {

    public static final String NAME = "Harry";
    public static final String SURNAME = "Potter";
    private static final String JAVA_AUTOMATION_QA = "Java Automation QA";
    private static final String JAVA_CORE = "Java Core";
    private static final String SQL = "SQL";
    private static final String TESTING_FUNDAMENTALS = "Testing Fundamentals";
    private static final int DURATION_1 = 15;
    private static final int DURATION_2 = 20;
    private static final int DURATION_3 = 10;
    private final static StudentService studentService = new StudentService();
    private final static ProgramService programService = new ProgramService();
    private final static LocalDate DATE = LocalDate.of(2022, 4, 20);

    public static Student generateUser(String name, String surName, Program program) {
        Student user = new Student(name, surName, program);
        studentService.create(user);
        return user;
    }
    public static void createPrograms() {
        createAutomationProgram();
        createJavaProgram();
        createQaProgram();
    }

    private static Program createProgram(String programName, Course... courses){
        Program program = new Program(programName, courses, DATE);
        programService.create(program);
        return program;
    }

    public static Program createAutomationProgram() {
        return createProgram(JAVA_AUTOMATION_QA, new Course(JAVA_CORE, DURATION_1), new Course(SQL, DURATION_2),
                new Course(TESTING_FUNDAMENTALS, DURATION_3));
    }

    public static Program createJavaProgram() {
        return createProgram(JAVA_CORE, new Course(JAVA_CORE, DURATION_2), new Course(SQL, DURATION_2));
    }

    public static Program createQaProgram() {
        return createProgram(TESTING_FUNDAMENTALS, new Course(TESTING_FUNDAMENTALS, DURATION_2), new Course(SQL, DURATION_3));
    }
}