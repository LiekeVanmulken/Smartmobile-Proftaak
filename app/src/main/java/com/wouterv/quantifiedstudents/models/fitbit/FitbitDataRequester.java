package com.wouterv.quantifiedstudents.models.fitbit;

import android.content.Context;

import com.android.volley.VolleyError;
import com.wouterv.quantifiedstudents.Volley.IResultFitbit;
import com.wouterv.quantifiedstudents.Volley.VolleyServiceFitbit;
import com.wouterv.quantifiedstudents.entities.fitbit.Activity;
import com.wouterv.quantifiedstudents.entities.fitbit.Sleep;
import com.wouterv.quantifiedstudents.interfaces.fitbit.IFitbitAPIResult;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivo on 17-6-2016.
 */
public class FitbitDataRequester {
    public void getSleep(Context context, final IFitbitAPIResult callback) {
        IResultFitbit result = new IResultFitbit() {
            @Override
            public void notifySuccess(String requestType, JSONArray response) {
                List<Sleep> sleep = new ArrayList<>();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        sleep.add(new Sleep(response.getJSONObject(i)));
                    }
                    catch (JSONException | ParseException e) {
                        e.printStackTrace();
                    }
                }

                callback.returnResult(sleep);
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                System.out.println("Sleep request went wrong. Please read the stack trace.");
            }
        };

        VolleyServiceFitbit volleyService = new VolleyServiceFitbit(result, context);
        volleyService.getSleep();
    }

    public void getActivity(Context context, final IFitbitAPIResult callback) {
        IResultFitbit result = new IResultFitbit() {
            @Override
            public void notifySuccess(String requestType, JSONArray response) {
                List<Activity> activity = new ArrayList<>();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        activity.add(new Activity(response.getJSONObject(i)));
                    }
                    catch (JSONException | ParseException e) {
                        e.printStackTrace();
                    }
                }

                callback.returnResult(activity);
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                System.out.println("Activity request went wrong. Please read the stack trace.");
            }
        };

        VolleyServiceFitbit volleyService = new VolleyServiceFitbit(result, context);
        volleyService.getActivity();
    }
}
