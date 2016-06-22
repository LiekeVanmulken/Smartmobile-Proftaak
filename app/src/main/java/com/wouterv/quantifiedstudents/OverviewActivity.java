package com.wouterv.quantifiedstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.wouterv.quantifiedstudents.entities.canvas.Assignment;
import com.wouterv.quantifiedstudents.entities.canvas.Course;
import com.wouterv.quantifiedstudents.entities.fitbit.Activity;
import com.wouterv.quantifiedstudents.entities.fitbit.Sleep;
import com.wouterv.quantifiedstudents.entities.ui.MessageEntity;
import com.wouterv.quantifiedstudents.entities.ui.SmileyModel;
import com.wouterv.quantifiedstudents.interfaces.fitbit.IAPIResult;
import com.wouterv.quantifiedstudents.models.canvas.CanvasDataRequester;
import com.wouterv.quantifiedstudents.models.canvas.CanvasModel;
import com.wouterv.quantifiedstudents.models.fitbit.FitbitDataRequester;
import com.wouterv.quantifiedstudents.models.fitbit.FitbitModel;
import com.wouterv.quantifiedstudents.models.performance.PerformanceAlgorithm;

import java.util.List;

public class OverviewActivity extends AppCompatActivity {
    private CanvasDataRequester canvasDataRequester;
    private CanvasModel canvasModel;

    private FitbitDataRequester fitbitDataRequester;
    private FitbitModel fitbitModel;

    private PerformanceAlgorithm performanceAlgorithm;

    private SmileyModel sleepModel;

    private SmileyModel stepModel;

    public OverviewActivity() {
        this.canvasDataRequester = new CanvasDataRequester();
        this.canvasModel = new CanvasModel();

        this.fitbitDataRequester = new FitbitDataRequester();
        this.fitbitModel = new FitbitModel();
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

        MessageEntity[] sleepMessages = new MessageEntity[] {
                new MessageEntity(0, 0.25, "Stop watching Netflix and go to bed!", R.drawable.crying_512px),
                new MessageEntity(0.25, 0.7, "Try going to bed earlier.", R.drawable.depression_512px),
                new MessageEntity(0.7, 0.9, "You\'re almost there!", R.drawable.happy_512px),
                new MessageEntity(0.9, 1.1, "This is amazing, keep this up!", R.drawable.smile_512px),
                new MessageEntity(1.1, 1.3, "Try to keep your sleep a little shorter!", R.drawable.happy_512px),
                new MessageEntity(1.3, 1.75, "Whoa, don\'t go into hibernation, yet.", R.drawable.depression_512px),
                new MessageEntity(1.75, Double.MAX_VALUE, "Groundhog Day isn\'t dependent on you!", R.drawable.crying_512px),
        };

        MessageEntity[] stepMessages = new MessageEntity[] {
                new MessageEntity(0, 0.25, "I know a sloth is amazing, but don\'t become one.", R.drawable.crying_512px),
                new MessageEntity(0.25, 0.7, "Try to excercise more.", R.drawable.depression_512px),
                new MessageEntity(0.7, 0.9, "You\'re almost there!", R.drawable.happy_512px),
                new MessageEntity(0.9, 1.1, "This is amazing, keep this up!", R.drawable.smile_512px),
                new MessageEntity(1.1, 1.3, "Keep it down a little.", R.drawable.happy_512px),
                new MessageEntity(1.3, 1.75, "Whoa, don\'t excercise too much.", R.drawable.depression_512px),
                new MessageEntity(1.75, Double.MAX_VALUE, "Be careful of your muscles!", R.drawable.crying_512px),
        };

        this.sleepModel = new SmileyModel((ImageView) this.findViewById(R.id.currentSleepEmoji), (TextView) this.findViewById(R.id.currentSleep), sleepMessages);
        this.stepModel = new SmileyModel((ImageView) this.findViewById(R.id.currentStepsEmoji), (TextView) this.findViewById(R.id.currentSteps), stepMessages);

        this.performanceAlgorithm = new PerformanceAlgorithm(this.canvasModel, this.fitbitModel, this.sleepModel, this.stepModel);

        this.fitbitDataRequester.getActivity(this, new IAPIResult() {
            @Override
            public void returnResult(Object result) {
                fitbitModel.modelActivityData((List<Activity>) result);

                performanceAlgorithm.registerDone();
            }
        });

        this.fitbitDataRequester.getSleep(this, new IAPIResult() {
            @Override
            public void returnResult(Object result) {
                fitbitModel.modelSleepData((List<Sleep>) result);

                performanceAlgorithm.registerDone();
            }
        });

        this.canvasDataRequester.getCourses(this, new IAPIResult() {
            @Override
            public void returnResult(Object result) {
                canvasModel.modelCourses((List<Course>) result);

                performanceAlgorithm.registerDone();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return true;
    }
}
