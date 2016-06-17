package com.wouterv.quantifiedstudents.models.nyx;

import com.wouterv.quantifiedstudents.models.fitbit.Activity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ivo on 16-6-2016.
 */
public class ActivityModel {
    private final HashMap<Date, Activity> activityModel;

    public ActivityModel(List<Activity> activity) {
        this.activityModel = new HashMap<>();
        this.translate(activity);
    }

    public Activity getActivityData(Date date) {
        return this.activityModel.get(date);
    }

    private void translate(List<Activity> activity) {
        for (Activity a : activity)
            this.activityModel.put(a.getDate(), a);
    }
}
