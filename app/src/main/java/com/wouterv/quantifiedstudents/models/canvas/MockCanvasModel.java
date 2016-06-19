package com.wouterv.quantifiedstudents.models.canvas;

import com.wouterv.quantifiedstudents.entities.canvas.MockCourse;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Ivo on 17-6-2016.
 */
public class MockCanvasModel {
    private Map<Date, MockCourse> courses;

    public MockCanvasModel() {
        this.courses = Collections.synchronizedMap(new HashMap<Date, MockCourse>());
    }

    public void generateMockCourses(int amount) {
        for (int i = 0; i < amount; i++) {
            Date date = new Date(116, 5, new Random().nextInt(15) + 1);

            this.courses.put(date, new MockCourse("SE42", date, new Random().nextInt(101)));
        }
    }

    public Date getBestDate() {
        Date date = null;
        int highest = Integer.MIN_VALUE;

        for (Map.Entry<Date, MockCourse> entry : this.courses.entrySet())
            if (entry.getValue().getPoints() > highest) {
                date = entry.getKey();
                highest = entry.getValue().getPoints();
            }

        return date;
    }

    public MockCourse getCourseEntry(Date date) {
        return this.courses.get(date);
    }
}
