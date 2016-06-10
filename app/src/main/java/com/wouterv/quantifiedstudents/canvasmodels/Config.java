package com.wouterv.quantifiedstudents.canvasmodels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wouter on 2-6-2016.
 */
public class Config {

    static Config instance;

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    private List<Course> courses;
    private String access_token;

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {

        return access_token;
    }

    public List<Course> getCourses() {
        return courses;
    }

}
