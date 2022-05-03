package service;

import dao.impl.ProgramDaoImpl;
import entity.Course;
import entity.Program;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.List;

public class ProgramService {

    private final ProgramDaoImpl programDao = new ProgramDaoImpl();

    public void create(Program program) {
        if (program.getProgramName() != null & program.getCourses().size() > 0 & program.getStartDate().isSupported(ChronoField.ERA)) {
            if (programDao.findById(program.getProgramName()) == null) {
                programDao.create(program);
            } else {
                System.out.println("Program" + program.getProgramName() + " is not created. Course name already exists.");
            }
        } else {
            System.out.println("Program required information is not exist.");
        }
    }

    public void update(Program program, String programName, List<Course> courses, LocalDate date) {
        if (program.getProgramName() != null & program.getCourses().size() > 0 & program.getStartDate().isSupported(ChronoField.ERA)) {
            program.setProgramName(programName);
            program.setCourses(courses);
            program.setStartDate(date);
            programDao.update(program);
            System.out.println("Program" + program.getProgramName() + " is successfully updated.");
        } else {
            System.out.println("Program" + program.getProgramName() + " is not updated.");
        }
    }

    public void delete(String id) {
        programDao.delete(id);
    }

    public Program findProgramById(String id) {
        return programDao.findById(id);
    }

    public List<Program> findAllPrograms() {
        return programDao.findAll();
    }

    public int getProgramDuration(Program program) {
        return programDao.getProgramCoursesTime(program);
    }

    public LocalDate getProgramStartDate(Program program) {
        return programDao.getProgramStartTime(program);
    }

    public LocalDateTime getProgramFinishDate(Program program) {
        LocalDate date = getProgramStartDate(program);
        int programDuration = getProgramDuration(program);
        int days = programDuration / 8;
        int hours = programDuration - days * 8;

        for (long i = 0; i < days; i++) {
            date = date.plusDays(1l);
            while (!DateService.isBusinessDay(date)) {
                date = date.plusDays(1l);
            }
        }

        LocalTime time = LocalTime.of(10 + hours, 0);
        LocalDateTime finishDate = LocalDateTime.of(date, time);

        return finishDate;
    }
}