package com.wouterv.quantifiedstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.wouterv.quantifiedstudents.models.fitbit.Activity;
import com.wouterv.quantifiedstudents.models.fitbit.Sleep;
import com.wouterv.quantifiedstudents.models.FitbitModel;
import com.wouterv.quantifiedstudents.models.callbacks.IFitbitResponse;
import com.wouterv.quantifiedstudents.models.nyx.ActivityModel;
import com.wouterv.quantifiedstudents.models.nyx.SleepModel;
import com.wouterv.quantifiedstudents.ui.SleepStatus;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class OverviewActivity extends AppCompatActivity {
    private TextView currentSteps;
    private ImageView currentStepsEmoji;

    private TextView currentSleep;
    private ImageView currentSleepEmoji;

    private SleepStatus sleepStatus;

    private ActivityModel activityModel;
    private FitbitModel fitbitModel;
    private SleepModel sleepModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_overview);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.fitbitModel = new FitbitModel(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        this.currentSteps = (TextView) this.findViewById(R.id.currentSteps);
        this.currentStepsEmoji = (ImageView) this.findViewById(R.id.currentStepsEmoji);

        this.currentSleep = (TextView) this.findViewById(R.id.currentSleep);
        this.currentSleepEmoji = (ImageView) this.findViewById(R.id.currentSleepEmoji);

        this.sleepStatus = new SleepStatus(this.currentSleep);

        //this.currentSteps.setText("You have currently set " + "6821" + " steps. Keep it up!");
        this.currentStepsEmoji.setImageResource(R.drawable.happy_512px);

        //this.currentSleep.setText("You have currently set " + "6821" + " steps. Keep it up!");
        this.currentSleepEmoji.setImageResource(R.drawable.happy_512px);

        this.fitbitModel.getSleep(new IFitbitResponse() {
            @Override
            public void returnResult(Object items) {
                sleepModel = new SleepModel((List<Sleep>) items);
                Sleep s = sleepModel.getSleepData(new Date(116, 5, 15));

                sleepStatus.setMinutesOfSleep(s.getMinutesAsleep());
            }
        });

        this.fitbitModel.getActivity(new IFitbitResponse() {
            @Override
            public void returnResult(Object items) {
                activityModel = new ActivityModel((List<Activity>) items);
                Activity a = activityModel.getActivityData(new Date(116, 5, 15));

                currentSteps.setText("You have currently set " + a.getSteps() + " steps. Keep it up!");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return true;
    }
}
