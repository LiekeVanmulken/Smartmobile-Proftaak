package com.wouterv.quantifiedstudents.canvasmodels;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by wouter on 27-5-2016.
 */
public class Course {
    int id;
    String name;
    int accountId;
    Date startsAt;
    boolean isPublic;
    String courseCode;
    //    String defaultView:
    int enrollmentTermId;
//    boolean publicSyllabus;
    //might add enrollment later, but would currently not add anything

    public Course(int id, String name, int accountId, Date startsAt, boolean isPublic, String courseCode, int enrollmentTermId) {
        this.id = id;
        this.name = name;
        this.accountId = accountId;
        this.startsAt = startsAt;
        this.isPublic = isPublic;
        this.courseCode = courseCode;
        this.enrollmentTermId = enrollmentTermId;
    }

    public Course(JSONObject response) throws JSONException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        this.id = response.getInt("id");
        this.name = response.getString("name");
        this.accountId = response.getInt("account_id");
        this.startsAt = format.parse(response.getString("start_at"));
        this.isPublic = response.getBoolean("is_public");
        this.courseCode = response.getString("course_code");
        this.enrollmentTermId = response.getInt("enrollment_term_id");
    }
}
