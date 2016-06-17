package com.wouterv.quantifiedstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.wouterv.quantifiedstudents.canvasmodels.Config;
import com.wouterv.quantifiedstudents.entities.fitbit.Activity;
import com.wouterv.quantifiedstudents.entities.fitbit.Sleep;
import com.wouterv.quantifiedstudents.interfaces.fitbit.IAPIResult;
import com.wouterv.quantifiedstudents.models.canvas.CanvasModel;
import com.wouterv.quantifiedstudents.models.fitbit.FitbitDataRequester;
import com.wouterv.quantifiedstudents.models.fitbit.FitbitModel;
import com.wouterv.quantifiedstudents.models.performance.PerformanceAlgorithm;

import java.util.Date;
import java.util.List;

public class OverviewActivity extends AppCompatActivity {
    private TextView currentSteps;
    private ImageView currentStepsEmoji;

    private TextView currentSleep;
    private ImageView currentSleepEmoji;

    private CanvasModel canvasModel;
    private FitbitDataRequester fitbitDataRequester;
    private FitbitModel fitbitModel;

    private PerformanceAlgorithm performanceAlgorithm;

    public OverviewActivity() {
        this.canvasModel = new CanvasModel();
        this.fitbitDataRequester = new FitbitDataRequester();
        this.fitbitModel = new FitbitModel();
        this.performanceAlgorithm = new PerformanceAlgorithm(this.canvasModel, this.fitbitModel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        this.currentSteps = (TextView) this.findViewById(R.id.currentSteps);
        this.currentStepsEmoji = (ImageView) this.findViewById(R.id.currentStepsEmoji);

        this.currentSleep = (TextView) this.findViewById(R.id.currentSleep);
        this.currentSleepEmoji = (ImageView) this.findViewById(R.id.currentSleepEmoji);

        this.currentSteps.setText("You have currently set " + "6821" + " steps. Keep it up!");
        this.currentStepsEmoji.setImageResource(R.drawable.happy_512px);

        this.currentSleep.setText("You have currently set " + "6821" + " steps. Keep it up!");
        this.currentSleepEmoji.setImageResource(R.drawable.happy_512px);

        this.fitbitDataRequester.getActivity(this, new IAPIResult() {
            @Override
            public void returnResult(Object result) {
                fitbitModel.modelActivityData((List<Activity>) result);

                System.out.println(String.format("You set %d steps on the 15th of June.", fitbitModel.getActivity(new Date(116, 5, 15)).getSteps()));

                performanceAlgorithm.calculateBestDay();
            }
        });

        this.fitbitDataRequester.getSleep(this, new IAPIResult() {
            @Override
            public void returnResult(Object result) {
                fitbitModel.modelSleepData((List<Sleep>) result);
            }
        });

        this.canvasModel.modelAssignmentData(Config.getInstance().getAssignmentsWithSubmissions());
        this.canvasModel.modelCourseData(Config.getInstance().getCourses());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return true;
    }
}
