package com.wouterv.quantifiedstudents.models.performance;

import com.wouterv.quantifiedstudents.models.canvas.CanvasModel;
import com.wouterv.quantifiedstudents.models.canvas.MockCanvasModel;
import com.wouterv.quantifiedstudents.models.fitbit.FitbitModel;

import java.util.Date;
import java.util.Map;

/**
 * Created by Ivo on 17-6-2016.
 */
public class PerformanceAlgorithm {
    private CanvasModel canvasModel;
    private FitbitModel fitbitModel;

    private int requestsBusy;

    private Date currentBestDay;

    public PerformanceAlgorithm(CanvasModel canvasModel, FitbitModel fitbitModel) {
        this.canvasModel = canvasModel;
        this.fitbitModel = fitbitModel;

        this.requestsBusy = 3;
    }

    public void calculateBestDay() {
//        for (Map.Entry<Date, Submission> submission : this.canvasModel.getSubmissions().entrySet())
//            System.out.println(submission);
    }

    public synchronized void registerDone() {
        this.requestsBusy--;

        if (this.requestsBusy == 0)
            System.out.println("Ready to do stuff");
    }
}
