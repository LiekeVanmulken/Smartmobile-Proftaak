package com.wouterv.quantifiedstudents.entities.canvas;

import com.wouterv.quantifiedstudents.canvasmodels.Config;
import com.wouterv.quantifiedstudents.entities.canvas.Assignment;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wouter on 27-5-2016.
 */
public class Course {
    private int id;
    private String name;
    private Date startsAt;
    private List<Assignment> assignments;
    private int point;

    public Course(JSONObject response) throws JSONException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.id = response.getInt("id");
        this.name = response.getString("name");
        this.startsAt = format.parse(response.getString("start_at"));
        this.point = (int) (Math.random() * 10) + 90;
    }

//    public Course(JSONObject response, Context context) throws JSONException, ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        this.id = response.getInt("id");
//        this.name = response.getString("name");
//        this.startsAt = format.parse(response.getString("start_at"));
//        final Course c = this;
//        final String name2 = this.name;
//
//        IResultJsonArray i = new IResultJsonArray() {
//            Course course = c;
//            String name = name2;
//
//            @Override
//            public void notifySuccess(String requestType, JSONArray response) {
//                assignments = Collections.synchronizedList(new ArrayList<Assignment>());
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        assignments.add(new Assignment(response.getJSONObject(i)),);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                Log.d("assigments added", "");
//            }
//
//            @Override
//            public void notifyError(String requestType, VolleyError error) {
//                error.printStackTrace();
//                Log.e("Course_notify_error", error.toString());
//                Config.getInstance().removeCourseFromCourses(course);
//            }
//        };
//        new VolleyServiceJsonArray(i, context).
//                getDataVolley(
//                        String.format(
//                                context.getString(R.string.assignments_by_course_id), id
//                        )
//                        , null);
//    }

    public List<Assignment> getAssignments() { return assignments; }

    public void setAssignments(List<Assignment> assignments) { this.assignments = assignments; }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public Date getStartsAt() {
        return startsAt;
    }

    public double getPoint() { return point; }

    public Assignment getRollCallAssignment() {
        for (Assignment a : assignments) {
            if (a.getName().toLowerCase().contains("rollcall")) {
                return a;
            }
        }
        return null;
    }

}
