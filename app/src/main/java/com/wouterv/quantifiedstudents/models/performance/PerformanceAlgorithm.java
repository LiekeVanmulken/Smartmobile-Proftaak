package com.wouterv.quantifiedstudents.models.performance;

import com.wouterv.quantifiedstudents.entities.canvas.Course;
import com.wouterv.quantifiedstudents.models.canvas.CanvasModel;
import com.wouterv.quantifiedstudents.models.canvas.MockCanvasModel;
import com.wouterv.quantifiedstudents.models.fitbit.FitbitModel;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * Created by Ivo on 17-6-2016.
 */
public class PerformanceAlgorithm {
    private CanvasModel canvasModel;
    private FitbitModel fitbitModel;

    private int requestsBusy;

    private Date bestDate;
    private Date currentDate;

    private double stepRatio;
    private double sleepRatio;

    public PerformanceAlgorithm(CanvasModel canvasModel, FitbitModel fitbitModel) {
        this.canvasModel = canvasModel;
        this.fitbitModel = fitbitModel;

        this.requestsBusy = 3;
    }

    public void calculateBestDay() {
        int max = Integer.MIN_VALUE;
        Date currentBestDay = null;

        for (Map.Entry<Date, Course> courseEntry : this.canvasModel.getCourses().entrySet()) {
            if (courseEntry.getValue().getPoint() > max) {
                currentBestDay = courseEntry.getKey();
                max = courseEntry.getValue().getPoint();
            }
        }

        if (this.fitbitModel.getActivity(currentBestDay) == null)
            this.bestDate = this.fitbitModel.getActivity().get(0).getDate();
        else
            this.bestDate = currentBestDay;
    }

    public void calculateCurrentDay() {
        Date date = GregorianCalendar.getInstance().getTime();
        Date today = new Date(date.getYear(), date.getMonth(), GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH));

        if (this.fitbitModel.getActivity(today) == null)
            this.currentDate = this.fitbitModel.getActivity().get(this.fitbitModel.getActivity().size() - 1).getDate();
        else
            this.currentDate = today;
    }

    private void calculateSleepRatio() {
        this.sleepRatio = (double) this.fitbitModel.getSleep(this.currentDate).getMinutesAsleep() / (double) this.fitbitModel.getSleep(this.bestDate).getMinutesAsleep();
    }

    public void calculateStepRatio() {
        this.stepRatio = (double) this.fitbitModel.getActivity(this.currentDate).getSteps() / (double) this.fitbitModel.getActivity(this.bestDate).getSteps();
    }

    public synchronized void registerDone() {
        this.requestsBusy--;

        if (this.requestsBusy == 0) {
            this.calculateBestDay();
            this.calculateCurrentDay();

            this.calculateSleepRatio();
            this.calculateStepRatio();

            System.out.println(this.sleepRatio);
        }
    }
}
