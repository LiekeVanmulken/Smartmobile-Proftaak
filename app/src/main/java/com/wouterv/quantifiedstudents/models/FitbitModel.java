package com.wouterv.quantifiedstudents.models;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.wouterv.quantifiedstudents.Volley.IResultFitbit;
import com.wouterv.quantifiedstudents.Volley.VolleyServiceFitbit;
import com.wouterv.quantifiedstudents.models.fitbit.Activity;
import com.wouterv.quantifiedstudents.models.fitbit.Sleep;
import com.wouterv.quantifiedstudents.models.callbacks.IFitbitResponse;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivo on 16-6-2016.
 */
public class FitbitModel {
    private Context context;

    public FitbitModel(Context context) {
        this.context = context;
    }

    public void getSleep(final IFitbitResponse callback) {
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

                callback.returnResult(sleepList);
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                System.out.println(error.getMessage());
            }
        };

        VolleyServiceFitbit volleyService = new VolleyServiceFitbit(iResult, this.context);
        volleyService.getSleep();
    }

    public void getActivity(final IFitbitResponse callback) {
        IResultFitbit iResult = new IResultFitbit() {
            @Override
            public void notifySuccess(String requestType, JSONArray response) {

                List<Activity> activityList = new ArrayList<>();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        activityList.add(new Activity(response.getJSONObject(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                callback.returnResult(activityList);
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                String test = "nooh";
            }
        };

        VolleyServiceFitbit volleyService = new VolleyServiceFitbit(iResult, this.context);
        volleyService.getActivity();
    }
}
