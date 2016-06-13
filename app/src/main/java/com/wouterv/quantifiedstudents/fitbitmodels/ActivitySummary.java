package com.wouterv.quantifiedstudents.fitbitmodels;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sander on 13-6-2016.
 */
public class ActivitySummary {

    private int activityCalories;
    private int caloriesBMR;
    private int caloriesOut;
    private double elevation;
    private int fairlyActiveMinutes;
    private int floors;
    private int lightlyActiveMinutes;
    private int marginalCalories;
    private int sedentaryMinutes;
    private int steps;
    private int veryActiveMinutes;

    public ActivitySummary(int activityCalories, int caloriesBMR, int caloriesOut, double elevation, int fairlyActiveMinutes, int floors, int lightlyActiveMinutes, int marginalCalories, int sedentaryMinutes, int steps, int veryActiveMinutes) {
        this.activityCalories = activityCalories;
        this.caloriesBMR = caloriesBMR;
        this.caloriesOut = caloriesOut;
        this.elevation = elevation;
        this.fairlyActiveMinutes = fairlyActiveMinutes;
        this.floors = floors;
        this.lightlyActiveMinutes = lightlyActiveMinutes;
        this.marginalCalories = marginalCalories;
        this.sedentaryMinutes = sedentaryMinutes;
        this.steps = steps;
        this.veryActiveMinutes = veryActiveMinutes;
    }

    public ActivitySummary(JSONObject response) throws JSONException {
        this.activityCalories = response.getInt("activityCalories");
        this.caloriesBMR = response.getInt("caloriesBMR");
        this.caloriesOut = response.getInt("caloriesOut");
        this.elevation = response.getDouble("elevation");
        this.fairlyActiveMinutes = response.getInt("fairlyActiveMinutes");
        this.floors = response.getInt("floors");
        this.lightlyActiveMinutes = response.getInt("lightlyActiveMinutes");
        this.marginalCalories = response.getInt("marginalCalories");
        this.sedentaryMinutes = response.getInt("sedentaryMinutes");
        this.steps = response.getInt("steps");
        this.veryActiveMinutes = response.getInt("veryActiveMinutes");
    }


    public int getActivityCalories() {
        return activityCalories;
    }

    public int getCaloriesBMR() {
        return caloriesBMR;
    }

    public int getCaloriesOut() {
        return caloriesOut;
    }

    public double getElevation() {
        return elevation;
    }

    public int getFairlyActiveMinutes() {
        return fairlyActiveMinutes;
    }

    public int getFloors() {
        return floors;
    }

    public int getLightlyActiveMinutes() {
        return lightlyActiveMinutes;
    }

    public int getMarginalCalories() {
        return marginalCalories;
    }

    public int getSedentaryMinutes() {
        return sedentaryMinutes;
    }

    public int getSteps() {
        return steps;
    }

    public int getVeryActiveMinutes() {
        return veryActiveMinutes;
    }
}
