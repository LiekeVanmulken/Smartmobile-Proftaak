package com.wouterv.quantifiedstudents.models.nyx;

import com.wouterv.quantifiedstudents.models.fitbit.Sleep;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ivo on 16-6-2016.
 */
public class SleepModel {
    private final HashMap<Date, Sleep> sleepModel;

    public SleepModel(List<Sleep> sleep) {
        this.sleepModel = new HashMap<>();
        this.translate(sleep);
    }

    public Sleep getSleepData(Date date) {
        return this.sleepModel.get(date);
    }

    private void translate(List<Sleep> sleep) {
        for (Sleep s : sleep)
            this.sleepModel.put(s.getDate(), s);
    }
}
