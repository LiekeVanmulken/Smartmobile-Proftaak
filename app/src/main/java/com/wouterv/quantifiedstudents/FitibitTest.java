package com.wouterv.quantifiedstudents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.wouterv.quantifiedstudents.Volley.IResult;
import com.wouterv.quantifiedstudents.Volley.VolleyService;
import com.wouterv.quantifiedstudents.fitbitmodels.Sleep;
import com.wouterv.quantifiedstudents.fitbitmodels.SleepSummary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

/**
 * Created by sander on 14-6-2016.
 */
public class FitibitTest extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitbit);

        IResult iResult = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {

                try {
                    JSONArray sleep = response.getJSONArray("sleep");

                    Sleep sleep1 = new Sleep(sleep.getJSONObject(0));
                    Sleep sleep2 = new Sleep(sleep.getJSONObject(1));
                    SleepSummary summary = new SleepSummary(response.getJSONObject("summary"));



                    String test = "jajaja";
                    test+= "asdf";


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                String err = error.getMessage();
            }
        };

        VolleyService volleyService = new VolleyService(iResult, this);
        volleyService.getDataVolley("http://fitbit.zlst.nl?action=getsleep", null);

    }
}
