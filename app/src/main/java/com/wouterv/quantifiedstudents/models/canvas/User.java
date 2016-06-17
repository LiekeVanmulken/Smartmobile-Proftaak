package com.wouterv.quantifiedstudents.models.canvas;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

/**
 * Created by wouter on 27-5-2016.
 */
public class User {
    int id;
    String name;
    String sortableName;
    String shortName;
    String avatarUrl;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSortableName() {
        return sortableName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    //    boolean canUpdateName; //maybe if we need this later
//    boolean canUpdateAvatar;

    public User(int id, String name, String sortableName, String shortName, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.sortableName = sortableName;
        this.shortName = shortName;
        this.avatarUrl = avatarUrl;
    }
    public User(JSONObject response) throws JSONException {
        this.id = response.getInt("id");
        this.name = response.getString("name");
        this.sortableName = response.getString("sortable_name");
        this.shortName = response.getString("short_name");
        this.avatarUrl = response.getString("avatar_url");
    }
}
