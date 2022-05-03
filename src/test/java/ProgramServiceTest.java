import entity.Program;
import entity.Student;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import service.DateService;
import service.ProgramService;
import testHelper.StudentGenerationUtil;

import java.time.LocalDate;

public class ProgramServiceTest {

    public static ProgramService programService = new ProgramService();
    static Program program = StudentGenerationUtil.createOneDayProgram();
    static Program shortProgram = StudentGenerationUtil.createShortProgram();

    @Test
    public void checkReportDateForNotFinishedTrainingTest() {
        program.setStartDate(LocalDate.now());
        Student student = StudentGenerationUtil.generateUser("Name", "Surname", program);
        Assert.assertEquals("Program finish date is incorrect.", LocalDate.now().plusDays(1),
                programService.getProgramFinishDate(student.getProgram()).toLocalDate());
    }

    @Test
    public void checkReportDateForFinishedTrainingTest() {
        program.setStartDate(LocalDate.now().plusDays(-2));
        Student student = StudentGenerationUtil.generateUser("Name", "Surname", program);
        Assert.assertEquals("Program finish date is incorrect.", LocalDate.now().plusDays(-1),
                programService.getProgramFinishDate(student.getProgram()).toLocalDate());
    }

    @Test
    public void checkProgramDurationTimeTest() {
        int expectedProgramDuration = 8;
        Student student = StudentGenerationUtil.generateUser("Name", "Surname", program);
        Assert.assertEquals("Program duration time is incorrect.", expectedProgramDuration,
                programService.getProgramDuration(student.getProgram()));
    }

    //this test should be run during working hours
    @Test
    public void verifyShortReportTextTest() {
        String expectedText = "Training is finished right now.";
        shortProgram.setStartDate(LocalDate.now());
        Student student = StudentGenerationUtil.generateUser("Name", "Surname", shortProgram);
        Assert.assertEquals("Training finished text is incorrect.", expectedText,
                DateService.dateDifference(programService.getProgramFinishDate(student.getProgram())));
    }
}