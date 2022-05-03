package dao.impl;

import dao.ProgramDao;
import entity.Course;
import entity.Program;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgramDaoImpl implements ProgramDao {

    private static final List<Program> programs = new ArrayList<>();

    public void create(Program program) {
        program.setProgramID(generateId());
        programs.add(program);
    }

    public void update(Program program) {
        Program inDbProgram = findById(program.getProgramID());
        inDbProgram.setProgramName(program.getProgramName());
        inDbProgram.setCourses();
    }

    public int getProgramCoursesTime(Program program) {
        Program inDbProgram = findById(program.getProgramID());
        int totalTime = 0;
        for (Course s : inDbProgram.getCourses()) {
            totalTime += s.getDurationHours();
        }
        return totalTime;
    }

    public LocalDate getProgramStartTime(Program program) {
        LocalDate startTime = program.getStartDate();
        return startTime;
    }

    public void delete(String id) {
        Program program = findById(id);
        program.setDeleted(true);
    }

    @Override
    public Program findById(String id) {
        for (Program s : programs) {
            if (s != null) {
                if (!s.isDeleted() && s.getProgramID().equals(id)) {
                    return s;
                }
            }
        }
        return null;
    }

    public List<Program> findAll() {
        List<Program> actualList = new ArrayList<>();
        for (Program s : programs) {
            if (!s.isDeleted()) {
                actualList.add(s);
            }
        }
        return actualList;
    }

    private String generateId() {
        String id = java.util.UUID.randomUUID().toString();
        if (programs.stream().anyMatch(program -> program.getProgramID().equals(id))) {
            return generateId();
        }
        return id;
    }
}