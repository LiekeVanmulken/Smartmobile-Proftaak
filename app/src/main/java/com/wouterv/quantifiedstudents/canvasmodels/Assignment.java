package com.wouterv.quantifiedstudents.canvasmodels;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wouter on 27-5-2016.
 */
public class Assignment {
    int id;
    String description;
    Date dueAt;
    int pointsPossible;
    String gradingType;
    int assigmentGroupId;
    Date createdAt;
    Date updatedAt;
    int position;
    int groupCategoryId;
    int courseId;
    String name;
    boolean hasSubmittedSubmissions;
    String htmlUrl;
    String submissionsDownloadUrl;
    List<Submission> submissions;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueAt() {
        return dueAt;
    }

    public int getPointsPossible() {
        return pointsPossible;
    }

    public String getGradingType() {
        return gradingType;
    }

    public int getAssigmentGroupId() {
        return assigmentGroupId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public int getPosition() {
        return position;
    }

    public int getGroupCategoryId() {
        return groupCategoryId;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public boolean isHasSubmittedSubmissions() {
        return hasSubmittedSubmissions;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getSubmissionsDownloadUrl() {
        return submissionsDownloadUrl;
    }

    public Assignment(JSONObject response) throws JSONException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.id = response.getInt("id");
        this.description = response.getString("description");
        this.dueAt = format.parse(response.getString("due_at"));
        this.pointsPossible = response.getInt("points_possible");
        this.gradingType = response.getString("grading_type");
        this.assigmentGroupId = response.getInt("assignment_group_id");
        this.createdAt = format.parse(response.getString("created_at"));
        this.updatedAt = format.parse(response.getString("updated_at"));
        this.position = response.getInt("position");
        this.groupCategoryId = response.getInt("group_category_id");
        this.courseId = response.getInt("course_id");
        this.name = response.getString("name");
        this.hasSubmittedSubmissions = response.getBoolean("has_submitted_submissions");
        this.htmlUrl = response.getString("html_url");
        this.submissionsDownloadUrl = response.getString("submissions_download_url");
    }
}
