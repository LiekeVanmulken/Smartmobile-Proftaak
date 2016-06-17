package com.wouterv.quantifiedstudents.models.canvas;

import com.wouterv.quantifiedstudents.canvasmodels.Assignment;
import com.wouterv.quantifiedstudents.canvasmodels.Course;
import com.wouterv.quantifiedstudents.canvasmodels.Submission;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ivo on 17-6-2016.
 */
public class CanvasModel {
    private final HashMap<Date, Assignment> assignments;
    private final HashMap<Date, Course> courses;
    private final HashMap<Date, Submission> submissions;

    public CanvasModel() {
        this.assignments = new HashMap<>();
        this.courses = new HashMap<>();
        this.submissions = new HashMap<>();
    }

    public void modelAssignmentData(List<Assignment> assignments) {
        for (Assignment a : assignments)
            this.assignments.put(a.getDueAt(), a);
    }

    public void modelCourseData(List<Course> courses) {
        for (Course c : courses)
            this.courses.put(c.getStartsAt(), c);
    }

    public void modelSubmissionData(List<Submission> submissions) {
        for (Submission s : submissions)
            this.submissions.put(s.getSubmittedAt(), s);
    }
}
