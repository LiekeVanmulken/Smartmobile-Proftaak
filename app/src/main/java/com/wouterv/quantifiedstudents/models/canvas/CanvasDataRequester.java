package com.wouterv.quantifiedstudents.models.canvas;

import android.content.Context;

import com.android.volley.VolleyError;
import com.wouterv.quantifiedstudents.Volley.IResultJsonArray;
import com.wouterv.quantifiedstudents.Volley.VolleyServiceJsonArray;
import com.wouterv.quantifiedstudents.entities.canvas.Assignment;
import com.wouterv.quantifiedstudents.entities.canvas.Course;
import com.wouterv.quantifiedstudents.interfaces.fitbit.IAPIResult;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wouter on 17-6-2016.
 */
public class CanvasDataRequester {

    public void getCourses(Context context, final IAPIResult callback) {

        IResultJsonArray result = new IResultJsonArray() {
            @Override
            public void notifySuccess(String requestType, JSONArray response) {
                List<Course> courses = new ArrayList<>();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        courses.add(new Course(response.getJSONObject(i)));
                    }
                    catch (JSONException | ParseException e) {
                        e.printStackTrace();
                    }
                }

                callback.returnResult(courses);
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                System.out.println("Sleep request went wrong. Please read the stack trace.");
            }
        };

        VolleyServiceJsonArray volleyService = new VolleyServiceJsonArray(result, context);
        volleyService.getCourses();
    }
    public void getAssignmentsForCourse(Context context, final IAPIResult callback, final Course course) {

        IResultJsonArray result = new IResultJsonArray() {
            @Override
            public void notifySuccess(String requestType, JSONArray response) {
                List<Assignment> assignments = new ArrayList<>();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        assignments.add(new Assignment(response.getJSONObject(i),course));
                    }
                    catch (JSONException | ParseException e) {
                        e.printStackTrace();
                    }
                }
                course.setAssignments(assignments);
                callback.returnResult(assignments);
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                System.out.println("Sleep request went wrong. Please read the stack trace.");
            }
        };

        VolleyServiceJsonArray volleyService = new VolleyServiceJsonArray(result, context);
        volleyService.getAssignmentForCourse(course.getId());
    }



}
