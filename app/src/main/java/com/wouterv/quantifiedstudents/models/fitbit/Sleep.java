package com.wouterv.quantifiedstudents.models.fitbit;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sander on 13-6-2016.
 */
public class Sleep {

    private Date date;
    private int minutesAsleep;
    private int minutesAwake;
    private int numberOfAwakenings;
    private int timeInBed;

    public Sleep(Date date, int minutesAsleep, int minutesAwake, int numberOfAwakenings, int timeInBed) {
        this.date = date;
        this.minutesAsleep = minutesAsleep;
        this.minutesAwake = minutesAwake;
        this.numberOfAwakenings = numberOfAwakenings;
        this.timeInBed = timeInBed;
    }

    public Sleep(JSONObject response) throws JSONException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.date = format.parse(response.getString("date"));
        this.minutesAsleep = response.getInt("minutesAsleep");
        this.minutesAwake = response.getInt("minutesAwake");
        this.numberOfAwakenings = response.getInt("minutesAwake");
        this.timeInBed = response.getInt("timeInBed");
    }

    public Date getDate() {
        return date;
    }

    public int getMinutesAsleep() {
        return minutesAsleep;
    }

    public int getMinutesAwake() {
        return minutesAwake;
    }

    public int getNumberOfAwakenings() {
        return numberOfAwakenings;
    }

    public int getTimeInBed() {
        return timeInBed;
    }
}
