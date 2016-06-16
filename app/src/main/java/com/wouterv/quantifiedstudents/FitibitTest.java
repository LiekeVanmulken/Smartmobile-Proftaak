package com.wouterv.quantifiedstudents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.wouterv.quantifiedstudents.Volley.IResultFitbit;
import com.wouterv.quantifiedstudents.Volley.VolleyServiceFitbit;
import com.wouterv.quantifiedstudents.fitbitmodels.Activity;
import com.wouterv.quantifiedstudents.fitbitmodels.Sleep;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Created by sander on 14-6-2016.
 */
public class FitibitTest extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitbit);

        IResultFitbit iResult = new IResultFitbit() {
            @Override
            public void notifySuccess(String requestType, JSONArray response) {

                List<Sleep> sleepList = new ArrayList<>();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        sleepList.add(new Sleep(response.getJSONObject(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }


                String test = "yaay";
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                String test = "nooh";
            }
        };

        VolleyServiceFitbit volleyService = new VolleyServiceFitbit(iResult, this);
        volleyService.getSleep();

    }
}
