package com.wouterv.quantifiedstudents.models.performance;

import com.wouterv.quantifiedstudents.canvasmodels.Submission;
import com.wouterv.quantifiedstudents.models.canvas.CanvasModel;
import com.wouterv.quantifiedstudents.models.fitbit.FitbitModel;

import java.util.Date;
import java.util.Map;

/**
 * Created by Ivo on 17-6-2016.
 */
public class PerformanceAlgorithm {
    private CanvasModel canvasModel;
    private FitbitModel fitbitModel;

    private Date currentBestDay;

    public PerformanceAlgorithm(CanvasModel canvasModel, FitbitModel fitbitModel) {
        this.canvasModel = canvasModel;
        this.fitbitModel = fitbitModel;
    }

    public void calculateBestDay() {
        for (Map.Entry<Date, Submission> submission : this.canvasModel.getSubmissions().entrySet())
            System.out.println(submission);
    }
}
