package com.wouterv.quantifiedstudents.ui;

import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Ivo on 16-6-2016.
 */
public class SleepStatus {
    private int minutesOfSleep;
    private TextView textView;

    public SleepStatus(TextView textView) {
        this.textView = textView;
    }

    public SleepStatus(TextView textView, int minutesOfSleep) {
        this.textView = textView;
        this.minutesOfSleep = minutesOfSleep;
    }

    public void setMinutesOfSleep(int minutes) {
        this.minutesOfSleep = minutes;

        this.setStatus();
    }

    public void setStatus() {
        this.textView.setText("You have currently slept " + this.minutesOfSleep / 60 + " hours and " + this.minutesOfSleep % 60 + " minutes.");
    }
}
