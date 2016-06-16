package com.wouterv.quantifiedstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.wouterv.quantifiedstudents.R;

import org.w3c.dom.Text;

public class OverviewActivity extends AppCompatActivity {
    TextView currentSteps;
    ImageView currentStepsEmoji;

    private TextView currentSleep;
    private ImageView currentSleepEmoji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
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
    }
}
