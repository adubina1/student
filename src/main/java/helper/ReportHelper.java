package helper;

import entity.Student;
import service.DateService;
import service.ProgramService;

public class ReportHelper {

    public static ProgramService programService = new ProgramService();

    public static void shortReport(Student student) {
        System.out.println(student.getName() + " " + student.getSurName() +
                "(" + student.getProgram().getProgramName() + "). ");
        DateService.dateDifference(programService.getProgramFinishDate(student.getProgram()));
    }

    public static void fullReport(Student student) {
        System.out.println("Student: " + student.getName() + " " + student.getSurName());
        System.out.println("Working time: from 10 to 18.");
        System.out.println("Program name: " + student.getProgram().getProgramName());
        System.out.println("Program duration: " + programService.getProgramDuration(student.getProgram()) + " hours.");
        System.out.println("Start date: " + programService.getProgramStartDate(student.getProgram()));
        System.out.println("Finish date: " + programService.getProgramFinishDate(student.getProgram()).toLocalDate());
        DateService.dateDifference(programService.getProgramFinishDate(student.getProgram()));
    }
}