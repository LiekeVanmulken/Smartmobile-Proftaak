package com.wouterv.quantifiedstudents;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.wouterv.quantifiedstudents.canvasmodels.Course;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wouter on 27-5-2016.
 * All of this code should be replaced by Sanders implementaion of this.
 */
public class JsonHandler {

    public void getJSON(String link, final Activity activity, final Mode mode) {
        Response.Listener<JSONObject> succesListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                switch (mode){
                    case Courses:
                        List<Course> courseList = new ArrayList<>();
                        for(int i = 0 ;response.length()<i;i++){
                            try {
                                courseList.add(new Course(response));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        ((MainActivity)activity).setSubmission(courseList);
                        break;
                    case Assignments:
                        break;
                    case Submissions:
                        break;
                }
            }
        };

        Response.ErrorListener errorListerner = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {}
        };

        int method = Request.Method.GET;
        JSONObject request = null;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(method, link, request, succesListener, errorListerner);
        RequestQueue queue = Volley.newRequestQueue(activity.getApplicationContext());
        queue.add(jsObjRequest);
    }
    public enum Mode{
        Courses,
        Assignments,
        Submissions
    }

}
