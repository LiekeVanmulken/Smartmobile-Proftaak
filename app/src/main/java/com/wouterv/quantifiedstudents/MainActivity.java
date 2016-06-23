package com.wouterv.quantifiedstudents;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.wouterv.quantifiedstudents.entities.canvas.Course;
import com.wouterv.quantifiedstudents.entities.fitbit.Activity;
import com.wouterv.quantifiedstudents.entities.fitbit.Sleep;
import com.wouterv.quantifiedstudents.entities.ui.EmotionText;
import com.wouterv.quantifiedstudents.entities.ui.MessageEntity;
import com.wouterv.quantifiedstudents.entities.ui.SmileyModel;
import com.wouterv.quantifiedstudents.interfaces.fitbit.IAPIResult;
import com.wouterv.quantifiedstudents.models.canvas.CanvasDataRequester;
import com.wouterv.quantifiedstudents.models.canvas.CanvasModel;
import com.wouterv.quantifiedstudents.models.fitbit.FitbitDataRequester;
import com.wouterv.quantifiedstudents.models.fitbit.FitbitModel;
import com.wouterv.quantifiedstudents.models.performance.PerformanceAlgorithm;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private SmileyModel quickViewModel;
    private EmotionText emotionModel;

    private CanvasDataRequester canvasDataRequester;
    private CanvasModel canvasModel;

    private FitbitDataRequester fitbitDataRequester;
    private FitbitModel fitbitModel;

    private PerformanceAlgorithm performanceAlgorithm;

    public MainActivity() {
        this.canvasDataRequester = new CanvasDataRequester();
        this.canvasModel = new CanvasModel();

        this.fitbitDataRequester = new FitbitDataRequester();
        this.fitbitModel = new FitbitModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        MessageEntity[] motivationMessages = new MessageEntity[] {
                new MessageEntity(0, 0.25, "Don\'t abandon me!", R.drawable.crying_512px),
                new MessageEntity(0.25, 0.7, "Can you do better? For me?", R.drawable.depression_512px),
                new MessageEntity(0.7, 0.9, "So close!", R.drawable.happy_512px),
                new MessageEntity(0.9, 1.1, "You\'re an amazing person!", R.drawable.smile_512px),
                new MessageEntity(1.1, 1.3, "So close!", R.drawable.happy_512px),
                new MessageEntity(1.3, 1.75, "Can you do better? For me?", R.drawable.depression_512px),
                new MessageEntity(1.75, Double.MAX_VALUE, "Don\'t abandon me!", R.drawable.crying_512px),
        };

        MessageEntity[] emotionMessages = new MessageEntity[] {
                new MessageEntity(0, 0.25, "very sad", Integer.MIN_VALUE),
                new MessageEntity(0.25, 0.7, "sad", Integer.MIN_VALUE),
                new MessageEntity(0.7, 0.9, "happy", Integer.MIN_VALUE),
                new MessageEntity(0.9, 1.1, "verry happy", Integer.MIN_VALUE),
                new MessageEntity(1.1, 1.3, "happy", Integer.MIN_VALUE),
                new MessageEntity(1.3, 1.75, "sad", Integer.MIN_VALUE),
                new MessageEntity(1.75, Double.MAX_VALUE, "very sad", Integer.MIN_VALUE),
        };

        this.emotionModel = new EmotionText((TextView) this.findViewById(R.id.currentNyxText), emotionMessages);
        this.quickViewModel = new SmileyModel((ImageView) this.findViewById(R.id.currentNyxEmoji), (TextView) this.findViewById(R.id.currentStatusText), motivationMessages);

        this.performanceAlgorithm = new PerformanceAlgorithm(this.canvasModel, this.fitbitModel, this.quickViewModel, this.emotionModel);

        this.fitbitDataRequester.getActivity(this, new IAPIResult() {
            @Override
            public void returnResult(Object result) {
                fitbitModel.modelActivityData((List<Activity>) result);

                performanceAlgorithm.registerOverviewDone();
            }
        });

        this.fitbitDataRequester.getSleep(this, new IAPIResult() {
            @Override
            public void returnResult(Object result) {
                fitbitModel.modelSleepData((List<Sleep>) result);

                performanceAlgorithm.registerOverviewDone();
            }
        });

        this.canvasDataRequester.getCourses(this, new IAPIResult() {
            @Override
            public void returnResult(Object result) {
                canvasModel.modelCourses((List<Course>) result);

                performanceAlgorithm.registerOverviewDone();
            }
        });
    }

    public void navigate(View view) {
        this.startActivity(new Intent(this, OverviewActivity.class));
    }
}
