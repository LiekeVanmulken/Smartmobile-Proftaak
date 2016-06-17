package com.wouterv.quantifiedstudents.canvasmodels;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wouter on 27-5-2016.
 */
public class Assignment {
    private int id;
    private String description;
    private Date dueAt;
    private int pointsPossible;
    private int courseId;
    private String name;
    private Submission submission;

    public Submission getSubmission() {
        return submission;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueAt() {
        return dueAt;
    }

    public int getPointsPossible() {
        return pointsPossible;
    }

    public String getName() {
        return name;
    }

    public Assignment(JSONObject response) throws JSONException, ParseException {
        if (response.has("points_possible")) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

            this.id = response.getInt("id");
            if (response.has("description"))
                this.description = response.getString("description");
            if (response.has("name"))
                this.name = response.getString("name");
            if (response.has("due_at")) {
                this.dueAt = format.parse(response.getString("due_at"));
            }
//            if(response.has("points_possible")) {
            this.pointsPossible = response.getInt("points_possible");
            if (response.has("course_id"))
                this.courseId = response.getInt("course_id");
//            this.hasSubmittedSubmissions = response.getBoolean("has_submitted_submissions");
            if (response.has("submission")) {
                JSONObject submisssion = response.getJSONObject("submission");
                int id = submisssion.getInt("id");

                int score = -1;
                String grade = "";
                if (submisssion.has("score")) {
                    score = submisssion.getInt("score");//might also need to add grade
                }
                if (submisssion.has("score")) {
                    grade = submisssion.getString("grade");
                }
                Date submittedAt = null;
                if (submisssion.has("submitted_at"))
                    submittedAt = format.parse(submisssion.getString("submitted_at"));
                else if (submisssion.has("graded_at"))
                    submittedAt = format.parse(submisssion.getString("graded_at"));
                if (submittedAt == null) {
                    Log.e("submittedat is null on", this.getName());
                }
                boolean late = false;
                if (submisssion.has("late")) late = submisssion.getBoolean("late");
                this.submission = new Submission(id, score, grade, pointsPossible, submittedAt, late);
            }
//        }
        }
    }
}
