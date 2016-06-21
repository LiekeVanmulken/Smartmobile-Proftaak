package com.wouterv.quantifiedstudents.entities.ui;

/**
 * Created by Ivo on 21-6-2016.
 */
public class MessageEntity {
    private double lowerBound;
    private double upperBound;

    private int imageResource;
    private String text;

    public MessageEntity(double lowerBound, double upperBound, String text, int imageResource) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;

        this.imageResource = imageResource;
        this.text = text;
    }

    public int getImageResource() {
        return this.imageResource;
    }

    public String getText() {
        return this.text;
    }

    public boolean isInBounds(double ratio) {
        return ratio >= this.lowerBound && ratio <= this.upperBound;
    }
}
