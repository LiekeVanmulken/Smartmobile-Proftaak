package com.wouterv.quantifiedstudents.partials;

import android.widget.ImageView;
import android.widget.TextView;

import com.wouterv.quantifiedstudents.models.performance.PerformanceAlgorithm;

/**
 * Created by Ivo on 17-6-2016.
 */
public class StepSmileyModel {
    private ImageView imageView;
    private TextView textView;

    private PerformanceAlgorithm performanceAlgorithm;

    private String[] motivation;

    public StepSmileyModel(ImageView imageView, TextView textView, PerformanceAlgorithm performanceAlgorithm) {
        this.imageView = imageView;
        this.textView = textView;

        this.motivation = new String[]{"Try harder", "Filthy scrub", "Just a little more", "You're almost there", "Good job"};
    }


}
