package com.wouterv.quantifiedstudents.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.wouterv.quantifiedstudents.MainActivity;
import com.wouterv.quantifiedstudents.Volley.IResultJsonArray;
import com.wouterv.quantifiedstudents.Volley.VolleyServiceJsonArray;
import com.wouterv.quantifiedstudents.canvasmodels.Config;
import com.wouterv.quantifiedstudents.canvasmodels.Course;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import com.wouterv.quantifiedstudents.R;

public class FHICTAPIResponseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fhictapiresponse);
//        Log.d("aaa", "fuuuuuuck1");
        String access_token = getAccesTokenFromString(getIntent().getData().toString());
        Log.d("access_token",access_token);
//        ((TextView) findViewById(R.id.acces_token)).setText(link);
        Config.getInstance().setAccess_token(access_token);
        IResultJsonArray resultCallback = new IResultJsonArray() {
            @Override
            public void notifySuccess(String requestType, JSONArray response) {
                Log.d("aaa","fuuuuuuck1");
                List<Course> courseList = new ArrayList<>();
                for(int i = 0; i<response.length();i++){
                    try {
                        courseList.add(new Course(response.getJSONObject(i),getApplicationContext()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Config.getInstance().setCourses(courseList);
                Log.d("aaa",courseList.toString());
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("aaa","fuuuuuuck2");
                error.printStackTrace();
            }


        };
        new VolleyServiceJsonArray(resultCallback,getApplicationContext()).getDataVolley(getString(R.string.course_list), null);

//        new JsonHandler().getJSON("https://api.fhict.nl/Canvas/Courses/me", link, this, JsonHandler.Mode.Courses);
    }

    public static String getAccesTokenFromString(String link) {
        link = link.replace("fhictnyx://nyxcallback/#access_token=", "");
        int bb = link.indexOf('&');
        Log.d("bb", "" + bb);
        return link.substring(0, link.indexOf('&'));
    }

}
