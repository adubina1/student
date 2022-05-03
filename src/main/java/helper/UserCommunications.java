package helper;

import entity.Program;
import entity.Student;
import service.CourseService;
import service.ProgramService;
import service.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class UserCommunications {

    private static final String SEPARATOR = "___________________________________________";
    private static final String EXIT = "Exit from application - enter any symbol.";

    private static final ProgramService programService = new ProgramService();

    public static void greetings() {
        System.out.println("Hello!\nWelcome to 'Student' application!");
        StudentGenerationUtil.createPrograms();
    }

    private static void endOfProgram () {
        System.out.println("Thank you for using my application!\nGoodbye!");
        System.exit(0);
    }

    public static void reportSelection(Student student) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(SEPARATOR);
        System.out.println("View short report - enter 1.\n" +
                "View full report - enter 2.\n" + EXIT);
        try {
            System.out.println("Please, enter your choice below: ");
            String choice = reader.readLine();
            switch (choice) {
                case "1":
                    ReportHelper.shortReport(student);
                    System.out.println(SEPARATOR);
                    reportSelection(student);
                    break;
                case "2":
                    ReportHelper.fullReport(student);
                    System.out.println(SEPARATOR);
                    reportSelection(student);
                    break;
                default:
                    endOfProgram();
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    public static Student chooseStudent() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Student student = null;
        System.out.println(SEPARATOR);
        System.out.println("For selecting existing student - enter 1.\n" +
                "For creating new student - 2.\n" + EXIT);
        try {
            System.out.println("Please, enter your choice below:");
            String choice = reader.readLine();
            switch (choice) {
                case "1":
                    student = generateStudentAutomatically();
                    break;
                case "2":
                    student = createStudentManually();
                    break;
                default:
                    endOfProgram();
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return student;
    }

    public static Student createStudentManually() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(SEPARATOR);
        System.out.println("Create Student:");
        try {
            System.out.println("Please, enter student name:");
            String name = reader.readLine();
            System.out.println("Please, enter student surname:");
            String surname = reader.readLine();
            Student student = StudentGenerationUtil.generateUser(name, surname, chooseProgram());
            return student;
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    public static Student generateStudentAutomatically() {
        Student student = StudentGenerationUtil.generateUser(StudentGenerationUtil.NAME, StudentGenerationUtil.SURNAME,
                StudentGenerationUtil.createAutomationProgram());
        System.out.println("New student created: " + student.getName() + " " + student.getSurName());
        return student;
    }

    public static Program chooseProgram() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Program program = null;
        System.out.println(SEPARATOR);
        System.out.println("For adding program - enter 1.\n" + EXIT);
        try {
            String choice = reader.readLine();
            switch (choice) {
                case "1":
                    program = selectProgram();
                    break;
                default:
                    endOfProgram();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return program;
    }

    private static Program selectProgram() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Program program = null;
        System.out.println(SEPARATOR);
        System.out.println("Program list: ");
        System.out.println(programService.findAllPrograms().toString());
        System.out.println("Enter program id you want to add below: ");
        try {
            String programChoice = reader.readLine();
            if (programService.findAllPrograms().stream().anyMatch(e -> e.getProgramID().equals(programChoice))) {
                programService.findProgramById(programChoice).setStartDate(enterProgramStartDate());
                program = programService.findProgramById(programChoice);
                System.out.println("Program added.");
            } else {
                System.out.println("Program is not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return program;
    }

    private static LocalDate enterProgramStartDate() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter program start year: ");
        int year = Integer.parseInt(reader.readLine());
        System.out.println("Enter program start month (from 1 to 12) : ");
        int month = Integer.parseInt(reader.readLine());
        System.out.println("Enter program start date: ");
        int date = Integer.parseInt(reader.readLine());
        LocalDate localDate = LocalDate.of(year, month, date);
        return localDate;
    }

}