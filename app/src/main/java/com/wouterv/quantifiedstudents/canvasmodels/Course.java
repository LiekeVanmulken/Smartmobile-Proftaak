package com.wouterv.quantifiedstudents.canvasmodels;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wouter on 27-5-2016.
 */
public class Course {
    int id;
    String name;
    Date startsAt;
    List<Assignment> assignments;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public Date getStartsAt() {
        return startsAt;
    }

    public Course(JSONObject response) throws JSONException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.id = response.getInt("id");
        this.name = response.getString("name");
        this.startsAt = format.parse(response.getString("start_at"));
    }
}
