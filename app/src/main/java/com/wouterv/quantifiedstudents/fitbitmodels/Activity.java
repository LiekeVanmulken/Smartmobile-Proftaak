package com.wouterv.quantifiedstudents.fitbitmodels;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by sander on 13-6-2016.
 */
public class Activity {

    private int activityId;
    private int activityParentId;
    private int calories;
    private String description;
    private double distance;
    private int duration;
    private boolean hasStartTime;
    private boolean isFavorite;
    private int logId;
    private String name;
    private String startTime;
    private int steps;

    public Activity(int activityId, int activityParentId, int calories, String description, double distance, int duration, boolean hasStartTime, boolean isFavorite, int logId, String name, String startTime, int steps) {
        this.activityId = activityId;
        this.activityParentId = activityParentId;
        this.calories = calories;
        this.description = description;
        this.distance = distance;
        this.duration = duration;
        this.hasStartTime = hasStartTime;
        this.isFavorite = isFavorite;
        this.logId = logId;
        this.name = name;
        this.startTime = startTime;
        this.steps = steps;
    }

    public Activity(JSONObject response) throws JSONException {
        this.activityId = response.getInt("activityId");
        this.activityParentId = response.getInt("activityParentId");
        this.calories = response.getInt("calories");
        this.description = response.getString("description");
        this.distance = response.getDouble("distance");
        this.duration = response.getInt("duration");
        this.hasStartTime = response.getBoolean("hasStartTime");
        this.isFavorite = response.getBoolean("isFavorite");
        this.logId = response.getInt("logId");
        this.name = response.getString("name");
        this.startTime = response.getString("startTime");
        this.steps = response.getInt("steps");
    }

    public int getActivityId() {
        return activityId;
    }

    public int getActivityParentId() {
        return activityParentId;
    }

    public int getCalories() {
        return calories;
    }

    public String getDescription() {
        return description;
    }

    public double getDistance() {
        return distance;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isHasStartTime() {
        return hasStartTime;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public int getLogId() {
        return logId;
    }

    public String getName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getSteps() {
        return steps;
    }
}
