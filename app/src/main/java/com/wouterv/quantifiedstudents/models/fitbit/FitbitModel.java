package com.wouterv.quantifiedstudents.models.fitbit;

import com.wouterv.quantifiedstudents.entities.fitbit.Activity;
import com.wouterv.quantifiedstudents.entities.fitbit.Sleep;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ivo on 17-6-2016.
 */
public class FitbitModel {
    private final Map<Date, Activity> activity;
    private final Map<Date, Sleep> sleep;

    public FitbitModel() {
        this.activity = Collections.synchronizedMap(new HashMap<Date, Activity>());
        this.sleep = Collections.synchronizedMap(new HashMap<Date, Sleep>());
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
