package entity;

import lombok.*;

@Setter
@Getter
@ToString
public class Student {

    private String id;
    private String name;
    private String surName;
    private Program program;
    private boolean isDeleted;

    public Student(String name, String surNam, Program program) {
        this.name = name;
        this.surName = surNam;
        this.program = program;
        this.isDeleted = false;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}