package entity;

import lombok.*;
import service.DateService;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@ToString
@NonNull
public class Program {

    private String programID;
    private String programName;
    private List<Course> courses;
    private LocalDate startDate;
    private boolean isDeleted;

    public Program(String programName, Course [] courses, LocalDate date) {
        if (programName != null & courses.length>0 & date.isSupported(ChronoField.ERA)) {
            this.programName = programName;
            setCourses(courses);
            setStartDate(date);
            this.isDeleted = false;
        } else {
            System.out.println("Program required information is not exist or not vaLid.");
        }
    }

    public void setStartDate(LocalDate startDate) {
        while (!DateService.isBusinessDay(startDate)) {
            startDate = startDate.plusDays(1l);
        }
        this.startDate = startDate;
    }

    public void setCourses(Course... courses) {
        this.courses = Arrays.stream(courses).collect(Collectors.toList());
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Program ID: " + programID + ". " +
                "Program name: " + programName + ". " +
                "Courses: " + courses.toString() + ".\n" ;
    }
}