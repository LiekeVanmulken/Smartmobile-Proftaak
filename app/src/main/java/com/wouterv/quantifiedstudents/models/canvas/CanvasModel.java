package com.wouterv.quantifiedstudents.models.canvas;

import com.wouterv.quantifiedstudents.entities.canvas.Assignment;
import com.wouterv.quantifiedstudents.entities.canvas.Course;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ivo on 19-6-2016.
 */
public class CanvasModel {
    private final Map<Date, Assignment> assignments;
    private final Map<Date, Course> courses;

    public CanvasModel() {
        this.assignments = Collections.synchronizedMap(new HashMap<Date, Assignment>());
        this.courses = Collections.synchronizedMap(new HashMap<Date, Course>());
    }

    public Map<Date, Assignment> getAssignments() {
        return Collections.unmodifiableMap(this.assignments);
    }

    public Map<Date, Course> getCourses() {
        return Collections.unmodifiableMap(this.courses);
    }

    public void modelAssignments(List<Assignment> assignments) {
        for (Assignment a : assignments)
            this.assignments.put(a.getSubmission().getSubmittedAt(), a);
    }

    public void modelCourses(List<Course> courses) {
        for (Course c : courses)
            this.courses.put(c.getStartsAt(), c);
    }
}
