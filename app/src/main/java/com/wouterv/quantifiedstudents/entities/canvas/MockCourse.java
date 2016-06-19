package com.wouterv.quantifiedstudents.entities.canvas;

import java.util.Date;

/**
 * Created by Ivo on 17-6-2016.
 */
public class MockCourse {
    private String name;
    private Date date;
    private int points;

    public MockCourse(String name, Date date, int points) {
        this.name = name;
        this.date = date;
        this.points = points;
    }

    public String getName() {
        return this.name;
    }

    public Date getDate() {
        return this.date;
    }

    public int getPoints() {
        return this.points;
    }
}
