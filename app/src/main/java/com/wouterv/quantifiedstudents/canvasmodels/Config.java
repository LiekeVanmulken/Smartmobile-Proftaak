package com.wouterv.quantifiedstudents.canvasmodels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wouter on 2-6-2016.
 */
public class Config {

    static Config instance;

    public boolean hasCompletedLoading() {
        if (courses == null) {
            return false;
        }
        for (Course c : courses) {
            if (c.getAssignments() == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return all assignments in order by date with a date smaller than today
     */
    public List<Assignment> getAllAssignmentsSortedByDateTillNow() {
        List<Assignment> assignments = new ArrayList<>();
        for (Course c : courses) {
            assignments.addAll(c.getAssignments());
        }
        for (Assignment a : assignments) {
            if (a.getDueAt().getDate() > Date.parse(GregorianCalendar.getInstance().toString())) {
                assignments.remove(a);
            }
        }
        Collections.sort(assignments, new Comparator<Assignment>() {
            @Override
            public int compare(Assignment lhs, Assignment rhs) {
                return lhs.getDueAt().compareTo(rhs.getDueAt());
            }
        });
        return assignments;
    }

    /**
     * @return all assignments with submissions
     */
    public List<Assignment> getAssignmentsWithSubmissions() {
        List<Assignment> assignments = new ArrayList<>();
        for (Course c : courses) {
            assignments.addAll(c.getAssignments());
        }
        for (Assignment a : assignments) {
            if (a.getSubmission() == null) {
                assignments.remove(a);
            }
        }
        return assignments;
    }

    /**
     * @return all assignments that use point for grading
     * also uses getAssignmentsWithSubmissions()
     */
    public List<Assignment> getAssignmentsWithPointSubmissions() {
        List<Assignment> assignments = getAssignmentsWithSubmissions();
        for (Assignment a : assignments) {
            if (a.getPointsPossible() <= 0) {
                assignments.remove(a);
            }
        }
        return assignments;
    }

    public List<Assignment> getCompletedAssignments() {
        List<Assignment> completedAssignments = new ArrayList<>();
        for (Course c : courses) {
            completedAssignments.addAll(c.getAssignments());
        }
        for(Assignment a : completedAssignments){
            if(a.getSubmission() == null){
                completedAssignments.remove(a);
                continue;
            }
            if(!a.getSubmission().getGrade().toLowerCase().contains("complete")){
                completedAssignments.remove(a);
            }
        }
        return completedAssignments;

    }




    public void removeCourseFromCourses(Course course) {
        courses.remove(course);
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

private List<Course> courses;
private String access_token;

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {

        return access_token;
    }

    public List<Course> getCourses() {
        return courses;
    }

}
