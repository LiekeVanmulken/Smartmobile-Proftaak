package com.wouterv.quantifiedstudents.fitbitmodels;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sander on 13-6-2016.
 */
public class Goal {

    private int caloriesOut;
    private double distance;
    private int floors;
    private int steps;

    public Goal(int caloriesOut, double distance, int floors, int steps) {
        this.caloriesOut = caloriesOut;
        this.distance = distance;
        this.floors = floors;
        this.steps = steps;
    }

    public Goal(JSONObject response) throws JSONException {
        this.caloriesOut = response.getInt("caloriesOut");
        this.distance = response.getDouble("distance");
        this.floors = response.getInt("floors");
        this.steps = response.getInt("steps");
    }

    public int getCaloriesOut() {
        return caloriesOut;
    }

    public double getDistance() {
        return distance;
    }

    public int getFloors() {
        return floors;
    }

    public int getSteps() {
        return steps;
    }
}
