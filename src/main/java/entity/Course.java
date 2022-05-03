package entity;

import lombok.*;

@Setter
@Getter
@ToString
@NonNull
public class Course {

    private String courseID;
    private String courseName;
    private int durationHours;
    private boolean isDeleted;

    public Course(String courseName, int durationHours) {
        if (courseName != null & durationHours > 0) {
            this.courseName = courseName;
            this.durationHours = durationHours;
            this.isDeleted = false;
        } else {
            System.out.println("Students required information is not exist.");
        }
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setDurationHours(int hours) {
        if (hours > 0) {
            durationHours = hours;
        } else {
            System.out.println("Duration is not added.\nPlease use valid duration.");
        }
    }

    @Override
    public String toString() {
        return "Course: " + courseName + ". Duration - " + durationHours + "h.";
    }
}
