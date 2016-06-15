package com.wouterv.quantifiedstudents.canvasmodels;

import android.content.Context;

import com.android.volley.VolleyError;
import com.wouterv.quantifiedstudents.R;
import com.wouterv.quantifiedstudents.Volley.IResult;
import com.wouterv.quantifiedstudents.Volley.IResultJsonArray;
import com.wouterv.quantifiedstudents.Volley.VolleyService;
import com.wouterv.quantifiedstudents.Volley.VolleyServiceJsonArray;

import org.json.JSONArray;
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

    public Course(JSONObject response, Context context) throws JSONException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.id = response.getInt("id");
        this.name = response.getString("name");
        this.startsAt = format.parse(response.getString("start_at"));

        IResultJsonArray i = new IResultJsonArray() {
            @Override
            public void notifySuccess(String requestType, JSONArray response) {
                assignments = new ArrayList<>();
                for(int i =0; i<response.length();i++ ){
                    try {
                        assignments.add(new Assignment(response.getJSONObject(i)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void notifyError(String requestType, VolleyError error) {
                error.printStackTrace();
            }
        };
        new VolleyServiceJsonArray(i,context).
                getDataVolley(
                        String.format(
                                context.getString(R.string.assignments_by_course_id),id
                        )
                        , null);
    }
}
