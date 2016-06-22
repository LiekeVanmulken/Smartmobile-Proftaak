package com.wouterv.quantifiedstudents.models.performance;

import com.wouterv.quantifiedstudents.entities.canvas.Course;
import com.wouterv.quantifiedstudents.entities.ui.SmileyModel;
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

    private SmileyModel quickviewSmiley;
    private SmileyModel sleepSmiley;
    private SmileyModel stepSmiley;

    private int requestsBusy;

    private Date bestDate;
    private Date currentDate;

    private double stepRatio;
    private double sleepRatio;

    public PerformanceAlgorithm(CanvasModel canvasModel, FitbitModel fitbitModel, SmileyModel sleepSmiley, SmileyModel stepSmiley) {
        this.canvasModel = canvasModel;
        this.fitbitModel = fitbitModel;

        this.sleepSmiley = sleepSmiley;
        this.stepSmiley = stepSmiley;

        this.requestsBusy = 3;
    }

    public PerformanceAlgorithm(CanvasModel canvasModel, FitbitModel fitbitModel, SmileyModel quickviewSmiley) {
        this.canvasModel = canvasModel;
        this.fitbitModel = fitbitModel;

        this.quickviewSmiley = quickviewSmiley;

        this.requestsBusy = 3;
    }

    private void calculateBestDay() {
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

    private void calculateCurrentDay() {
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

    private void calculateStepRatio() {
        this.stepRatio = (double) this.fitbitModel.getActivity(this.currentDate).getSteps() / (double) this.fitbitModel.getActivity(this.bestDate).getSteps();
    }

    public synchronized void registerDone() {
        this.requestsBusy--;

        if (this.requestsBusy == 0) {
            this.calculateBestDay();
            this.calculateCurrentDay();

            this.calculateSleepRatio();
            this.calculateStepRatio();

            this.setSleepMessage();
            this.setStepMessage();
        }
    }

    public synchronized void registerOverviewDone() {
        this.requestsBusy--;

        if (this.requestsBusy == 0) {
            this.calculateBestDay();
            this.calculateCurrentDay();

            this.calculateSleepRatio();
            this.calculateStepRatio();

            this.setOverviewMessage();
        }
    }

    private void setSleepMessage() {
        this.sleepSmiley.setSmiley(String.format("You have slept %d minutes. ", this.fitbitModel.getSleep(this.currentDate).getMinutesAsleep()), this.sleepRatio);
    }

    private void setStepMessage() {
        this.stepSmiley.setSmiley(String.format("You have currently set %d steps. ", this.fitbitModel.getActivity(this.currentDate).getSteps()), this.stepRatio);
    }

    private void setOverviewMessage() {
        this.quickviewSmiley.setSmiley("", (this.stepRatio + this.sleepRatio) / 2d);
    }
}
