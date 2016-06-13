package com.wouterv.quantifiedstudents.fitbitmodels;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sander on 13-6-2016.
 */
public class Body {

    private Date date;
    private int fat;
    private int logId;
    private String time;
    private String source;

    public Body(Date date, int fat, int logId, String time, String source) {
        this.date = date;
        this.fat = fat;
        this.logId = logId;
        this.time = time;
        this.source = source;
    }

    public Body(JSONObject response) throws JSONException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.date = format.parse(response.getString("date"));
        this.fat = response.getInt("fat");
        this.logId = response.getInt("logId");
        this.time = response.getString("time");
        this.source = response.getString("source");
    }


    public Date getDate() {
        return date;
    }

    public int getFat() {
        return fat;
    }

    public int getLogId() {
        return logId;
    }

    public String getTime() {
        return time;
    }

    public String getSource() {
        return source;
    }
}
