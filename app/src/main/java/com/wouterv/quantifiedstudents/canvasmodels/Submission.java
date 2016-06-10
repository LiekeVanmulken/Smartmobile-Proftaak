package com.wouterv.quantifiedstudents.canvasmodels;

/**
 * Created by wouter on 2-6-2016.
 */
public class Submission {
    int id;
    String grade;

    public Submission(int id, String grade) {
        this.id = id;
        this.grade = grade;
    }

    public int getId() {

        return id;
    }

    public String getGrade() {
        return grade;
    }
}
