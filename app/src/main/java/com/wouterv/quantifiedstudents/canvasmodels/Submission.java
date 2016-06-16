package com.wouterv.quantifiedstudents.canvasmodels;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by wouter on 2-6-2016.
 */
public class Submission {
    int id;
    int score;
    String grade;
    Date submittedAt;
    boolean late;
    int pointsPossible;

    public int getScore() {
        return score;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public boolean isLate() {
        return late;
    }

    public int getPointsPossible() {
        return pointsPossible;
    }

    public Submission(int id, int score, String grade, int pointsPossible, Date submittedAt, boolean late) {
        this.id = id;
        this.score = score;
        this.grade = grade;

        this.pointsPossible = pointsPossible;
        this.submittedAt = submittedAt;
        this.late = late;
    }

    public int getId() {

        return id;
    }

    public String getGrade() {
        return grade;
    }
}
