import entity.Program;
import entity.Student;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import service.StudentService;
import testHelper.StudentGenerationUtil;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentServiceTest {

    private final static StudentService studentService = new StudentService();
    private final static int STUDENTS_SIZE = 10;
    static Program program = StudentGenerationUtil.createOneDayProgram();

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < STUDENTS_SIZE; i++) {
            StudentGenerationUtil.generateUser(StudentGenerationUtil.NAME,
                    StudentGenerationUtil.SURNAME, program);
        }
        Assertions.assertEquals(STUDENTS_SIZE, studentService.findAllUsers().size());
    }

    @Test
    @Order(1)
    public void createStudentIfProgramNotExists() {
        StudentGenerationUtil.generateUser("Name", "Surname", null);
        Assert.assertNotEquals("Student is created without program.", STUDENTS_SIZE + 1, studentService.findAllUsers().size());
    }

    @Test
    @Order(2)
    public void createStudentIfProgramValid() {
        StudentGenerationUtil.generateUser("Name", "Surname", program);
        Assertions.assertEquals(STUDENTS_SIZE + 1, studentService.findAllUsers().size(), "Student with valid data is not created.");
    }

    @Test
    @Order(3)
    public void updateStudentNameSurname() {
        List<Student> users = studentService.findAllUsers();
        Student user = users.get(0);
        studentService.update(user, "1", "2");
        Assertions.assertTrue(users.get(0).getName().equals("1"), "User's name is not updated.");
        Assertions.assertTrue(users.get(0).getSurName().equals("2"), "User's surname is not updated.");
    }

    @Test
    @Order(4)
    public void findAllStudents() {
        Assertions.assertEquals(STUDENTS_SIZE + 1, studentService.findAllUsers().size(), "Student list is not shown.");
    }

    @Test
    @Order(5)
    public void deleteStudent() {
        studentService.delete(studentService.findAllUsers().get(0).getId());
        Assertions.assertEquals(STUDENTS_SIZE, studentService.findAllUsers().size(), "Student is not deleted.");
    }
}