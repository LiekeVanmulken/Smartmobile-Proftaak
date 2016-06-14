package com.wouterv.quantifiedstudents.fitbitmodels;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sander on 13-6-2016.
 */
public class Sleep {

    private boolean isMainSleep;
    private int logId;
    private int efficiency;
    private Date startTime;
    private int duration;
    private int minutesToFallAsleep;
    private int minutesAsleep;
    private int minutesAwake;
    private int minutesAfterWakeup;
    private int awakeningsCount;
    private int awakeCount;
    private int awakeDuration;
    private int restlessCount;
    private int restlessDuration;
    private int timeInBed;

    public Sleep(boolean isMainSleep, int logId, int efficiency, Date startTime, int duration, int minutesToFallAsleep, int minutesAsleep, int minutesAwake, int minutesAfterWakeup, int awakeningsCount, int awakeCount, int awakeDuration, int restlessCount, int restlessDuration, int timeInBed) {
        this.isMainSleep = isMainSleep;
        this.logId = logId;
        this.efficiency = efficiency;
        this.startTime = startTime;
        this.duration = duration;
        this.minutesToFallAsleep = minutesToFallAsleep;
        this.minutesAsleep = minutesAsleep;
        this.minutesAwake = minutesAwake;
        this.minutesAfterWakeup = minutesAfterWakeup;
        this.awakeningsCount = awakeningsCount;
        this.awakeCount = awakeCount;
        this.awakeDuration = awakeDuration;
        this.restlessCount = restlessCount;
        this.restlessDuration = restlessDuration;
        this.timeInBed = timeInBed;
    }

    public Sleep(JSONObject response) throws JSONException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.isMainSleep = response.getBoolean("isMainSleep");
        this.logId = response.getInt("logId");
        this.efficiency = response.getInt("efficiency");
        this.startTime = format.parse(response.getString("startTime"));
        this.duration = response.getInt("duration");
        this.minutesToFallAsleep = response.getInt("minutesToFallAsleep");
        this.minutesAsleep = response.getInt("minutesAsleep");
        this.minutesAwake = response.getInt("minutesAwake");
        this.minutesAfterWakeup = response.getInt("minutesAfterWakeup");;
        this.awakeningsCount = response.getInt("awakeningsCount");;
        this.awakeCount = response.getInt("awakeCount");;
        this.awakeDuration = response.getInt("awakeDuration");;
        this.restlessCount = response.getInt("restlessCount");;
        this.restlessDuration = response.getInt("restlessDuration");;
        this.timeInBed = response.getInt("timeInBed");;
    }

    public boolean isMainSleep() {
        return isMainSleep;
    }

    public int getLogId() {
        return logId;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public Date getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public int getMinutesToFallAsleep() {
        return minutesToFallAsleep;
    }

    public int getMinutesAsleep() {
        return minutesAsleep;
    }

    public int getMinutesAwake() {
        return minutesAwake;
    }

    public int getMinutesAfterWakeup() {
        return minutesAfterWakeup;
    }

    public int getAwakeningsCount() {
        return awakeningsCount;
    }

    public int getAwakeCount() {
        return awakeCount;
    }

    public int getAwakeDuration() {
        return awakeDuration;
    }

    public int getRestlessCount() {
        return restlessCount;
    }

    public int getRestlessDuration() {
        return restlessDuration;
    }

    public int getTimeInBed() {
        return timeInBed;
    }
}
