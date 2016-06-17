package com.wouterv.quantifiedstudents.entities.fitbit;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sander on 13-6-2016.
 */
public class Activity {

    private Date date;
    private int caloriesBurned;
    private int steps;
    private double distance;
    private int floors;
    private int minutesSedentary;
    private int minutesLightlyActive;
    private int minutesFairlyActive;
    private int minutesVeryActive;
    private int activityCalories;

    public Activity(Date date, int caloriesBurned, int steps, double distance, int floors, int minutesSedentary, int minutesLightlyActive, int minutesFairlyActive, int minutesVeryActive, int activityCalories) {
        this.date = date;
        this.caloriesBurned = caloriesBurned;
        this.steps = steps;
        this.distance = distance;
        this.floors = floors;
        this.minutesSedentary = minutesSedentary;
        this.minutesLightlyActive = minutesLightlyActive;
        this.minutesFairlyActive = minutesFairlyActive;
        this.minutesVeryActive = minutesVeryActive;
        this.activityCalories = activityCalories;
    }

    public Activity(JSONObject response) throws JSONException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.date = format.parse(response.getString("date"));
        this.caloriesBurned = response.getInt("caloriesBurned");
        this.steps = response.getInt("steps");
        this.distance = response.getDouble("distance");
        this.floors = response.getInt("floors");
        this.minutesSedentary = response.getInt("minutesSedentary");
        this.minutesLightlyActive = response.getInt("minutesLightlyActive");
        this.minutesFairlyActive = response.getInt("minutesFairlyActive");
        this.minutesVeryActive = response.getInt("minutesVeryActive");
        this.activityCalories = response.getInt("activityCalories");
    }

    public int getActivityCalories() {
        return activityCalories;
    }

    public Date getDate() {
        return date;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public int getSteps() {
        return steps;
    }

    public double getDistance() {
        return distance;
    }

    public int getFloors() {
        return floors;
    }

    public int getMinutesSedentary() {
        return minutesSedentary;
    }

    public int getMinutesLightlyActive() {
        return minutesLightlyActive;
    }

    public int getMinutesFairlyActive() {
        return minutesFairlyActive;
    }

    public int getMinutesVeryActive() {
        return minutesVeryActive;
    }
}
