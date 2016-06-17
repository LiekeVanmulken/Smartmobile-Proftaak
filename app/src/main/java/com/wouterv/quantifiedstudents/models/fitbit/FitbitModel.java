package com.wouterv.quantifiedstudents.models.fitbit;

import com.wouterv.quantifiedstudents.entities.fitbit.Activity;
import com.wouterv.quantifiedstudents.entities.fitbit.Sleep;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Ivo on 17-6-2016.
 */
public class FitbitModel {
    private final HashMap<Date, Activity> activity;
    private final HashMap<Date, Sleep> sleep;

    public FitbitModel() {
        this.activity = new HashMap<>();
        this.sleep = new HashMap<>();
    }

    public Activity getActivity(Date date) {
        return this.activity.get(date);
    }

    public Sleep getSleep(Date date) {
        return this.sleep.get(date);
    }

    public void modelActivityData(List<Activity> activity) {
        for (Activity a : activity)
            this.activity.put(a.getDate(), a);
    }

    public void modelSleepData(List<Sleep> sleep) {
        for (Sleep s : sleep)
            this.sleep.put(s.getDate(), s);
    }
}
