package com.wouterv.quantifiedstudents.fitbitmodels;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sander on 13-6-2016.
 */
public class SleepSummary {

    private int totalMinutesAsleep;
    private int totalSleepRecords;
    private int totalTimeInBed;

    public SleepSummary(int totalMinutesAsleep, int totalSleepRecords, int totalTimeInBed) {
        this.totalMinutesAsleep = totalMinutesAsleep;
        this.totalSleepRecords = totalSleepRecords;
        this.totalTimeInBed = totalTimeInBed;
    }

    public SleepSummary(JSONObject response) throws JSONException {
        this.totalMinutesAsleep = response.getInt("totalMinutesAsleep");
        this.totalSleepRecords = response.getInt("totalSleepRecords");
        this.totalTimeInBed = response.getInt("totalTimeInBed");
    }

    public int getTotalMinutesAsleep() {
        return totalMinutesAsleep;
    }

    public int getTotalSleepRecords() {
        return totalSleepRecords;
    }

    public int getTotalTimeInBed() {
        return totalTimeInBed;
    }
}
